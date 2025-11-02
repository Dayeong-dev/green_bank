package com.example.green_bank.questions.service;

import com.example.green_bank.admin.entity.Admin;
import com.example.green_bank.admin.repository.AdminRepository;
import com.example.green_bank.questions.repository.AnswerRepository;
import com.example.green_bank.questions.dto.QuestionAnswerDTO;
import com.example.green_bank.questions.dto.QuestionDTO;
import com.example.green_bank.questions.entity.Answer;
import com.example.green_bank.questions.entity.Question;
import com.example.green_bank.questions.entity.QuestionType;
import com.example.green_bank.questions.repository.QuestionRepository;
import com.example.green_bank.questions.util.AnswerMapper;
import com.example.green_bank.questions.util.QuestionMapper;
import com.example.green_bank.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AdminRepository adminRepository;

    private final QuestionMapper questionMapper = new QuestionMapper();
    private final AnswerMapper answerMapper = new AnswerMapper();

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository, AdminRepository adminRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.adminRepository = adminRepository;
    }

    public boolean regQuestion(QuestionDTO questionDTO, Integer typeId, String username) {
        if(typeId == 0 || username == null) {
            return false;
        }

        User user = new User();
        user.setUsername(username);

        QuestionType questionType = new QuestionType();
        questionType.setTypeid(typeId);

        questionDTO.setUser(user);
        questionDTO.setQuestionType(questionType);
        questionDTO.setIsanswered("0");

        Question question = questionMapper.toEntity(questionDTO);
        questionRepository.save(question);

        return true;
    }

    public List<QuestionDTO> getQuestionsByUsername(String username) {
        List<Question> result = questionRepository.findAllByUser_Username(username);

        List<QuestionDTO> questionList = new ArrayList<>();

        for(Question question : result) {
            questionList.add(questionMapper.toDTO(question));
        }

        return questionList;
    }

    @Transactional
    public QuestionAnswerDTO getQuestionAnswerByQno(Integer qno) {
        QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO();

        Optional<Question> questionResult = questionRepository.findById(qno);
        if(questionResult.isEmpty()) {
            return null;
        }

        Question question = questionResult.get();
        questionAnswerDTO.setQuestionDTO(questionMapper.toDTO(question));

        if(question.getIsanswered().equals("1")) {
            Optional<Answer> optional = answerRepository.findByQuestion_Qno(qno);

            if(optional.isPresent()) {
            	Answer answer = optional.get();
                questionAnswerDTO.setAnswerDTO(answerMapper.toDTO(answer));
            }
        }

        return questionAnswerDTO;
    }

    public QuestionDTO getQuestionByQno(Integer qno) {
        Optional<Question> result = questionRepository.findById(qno);

        if(result.isEmpty()) {
            return null;
        }
        Question question = result.get();

        return questionMapper.toDTO(question);
    }

    @Transactional
    public boolean deleteQuestion(Integer qno) {
        if(!questionRepository.existsById(qno)) {
            return false;
        }
        questionRepository.deleteById(qno);
        return true;
    }

    @Transactional
    public boolean updateQuestion(Integer qno, QuestionDTO questionDTO) {
        if(qno == 0 || questionDTO == null) {   // 조회 혹은 수정할 값이 없음
            return false;
        }

        Optional<Question> result = questionRepository.findById(qno);

        if(result.isEmpty()) {      // 해당 문의 번호가 DB에 없음
            return false;
        }
        Question curr_question = result.get();

        curr_question.setTitle(questionDTO.getTitle());
        curr_question.setContent(questionDTO.getContent());

        questionRepository.save(curr_question);

        return true;
    }

    public List<QuestionDTO> getAllQuestions() {
        List<Question> result = questionRepository.findAll();
        List<QuestionDTO> questionList = new ArrayList<>();

        for(Question question : result) {
            questionList.add(questionMapper.toDTO(question));
        }

        return questionList;
    }

    @Transactional
    public List<QuestionDTO> getQuestionsByTypeIdAndIsanswered(String adminId) {
        Optional<Admin> result1 = adminRepository.findById(adminId);

        if(result1.isEmpty()) {
            return null;
        }

        Admin admin = result1.get();

        List<Question> result2 = questionRepository.findAllByQuestionType_TypeidAndIsanswered(admin.getQuestionType().getTypeid(), "0");
        List<QuestionDTO> questionList = new ArrayList<>();

        for(Question question : result2) {
            questionList.add(questionMapper.toDTO(question));
        }

        return questionList;
    }
}
