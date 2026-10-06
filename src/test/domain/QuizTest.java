package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizTest {

    @Test
    void  ConstructorTest(){
        List<Question> qts = List.of(new Question(1,"2+2 = ?", List.of("1", "No", "14", "4"), Set.of(3)),
                new Question(2, "3+3 = ?",  List.of("6", "12/2", "No", "Why"), Set.of(1,2))
        );

        Quiz quiz = new Quiz(qts);

        assertEquals(0, quiz.getCurrentQuestionIndex());
        assertEquals(0, quiz.getScore());
        assertEquals(qts, quiz.getQuestions());
    }

    @Test
    void ConstructorEmptyInput(){
        List<Question> qts = List.of();

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Quiz(qts);
        });

        Assertions.assertEquals("Question list is empty", thrown.getMessage());

    }

    @Test
    void getQuestions() {
        List<Question> qts1 = new ArrayList<>(List.of(new Question(1,"2+2 = ?", List.of("1", "No", "14", "4"), Set.of(3)),
                new Question(2, "3+3 = ?",  List.of("6", "12/2", "No", "Why"), Set.of(1,2))
        ));

        List<Question> qts2 = List.of(new Question(1,"2+2 = ?", List.of("1", "No", "14", "4"), Set.of(3)),
                new Question(2, "3+3 = ?",  List.of("6", "12/2", "No", "Why"), Set.of(1,2))
        );


        Quiz quiz = new Quiz(qts1);

        qts1.clear();
        assertEquals(qts2, quiz.getQuestions());

        List<Question> newQuestions = quiz.getQuestions();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> newQuestions.clear());
    }

    @Test
    void start() {
        List<Question> qts = List.of(new Question(1,"2+2 = ?", List.of("1", "No", "14", "4"), Set.of(3)),
                new Question(2, "3+3 = ?",  List.of("6", "12/2", "No", "Why"), Set.of(1,2))
        );

        Quiz quiz = new Quiz(qts);


        quiz.start();
        IllegalStateException thrown1 = Assertions.assertThrows(IllegalStateException.class, () -> {
            quiz.start();
        });
        Assertions.assertEquals("Quiz cannot be started", thrown1.getMessage());


        quiz.finish();
        IllegalStateException thrown2 = Assertions.assertThrows(IllegalStateException.class, () -> {
            quiz.start();
        });
        Assertions.assertEquals("Quiz cannot be started", thrown2.getMessage());

        quiz.abort();
        IllegalStateException thrown3 = Assertions.assertThrows(IllegalStateException.class, () -> {
            quiz.start();
        });
        Assertions.assertEquals("Quiz cannot be started", thrown3.getMessage());
    }

    @Test
    void finish() {
        List<Question> qts = List.of(new Question(1,"2+2 = ?", List.of("1", "No", "14", "4"), Set.of(3)),
                new Question(2, "3+3 = ?",  List.of("6", "12/2", "No", "Why"), Set.of(1,2))
        );

        Quiz quiz1 = new Quiz(qts);

        IllegalStateException thrown1 = Assertions.assertThrows(IllegalStateException.class, () -> {
            quiz1.finish();
        });
        Assertions.assertEquals("Quiz is not in progress", thrown1.getMessage());

        quiz1.start();
        quiz1.finish();
        IllegalStateException thrown2 = Assertions.assertThrows(IllegalStateException.class, () -> {
            quiz1.finish();
        });
        Assertions.assertEquals("Quiz is not in progress", thrown2.getMessage());

        Quiz quiz2 = new Quiz(qts);
        quiz2.start();
        quiz2.abort();
        IllegalStateException thrown3 = Assertions.assertThrows(IllegalStateException.class, () -> {
            quiz2.finish();
        });
        Assertions.assertEquals("Quiz is not in progress", thrown3.getMessage());
    }

    @Test
    void submitAnswer() {
        List<Question> qts = List.of(new Question(1,"2+2 = ?", List.of("1", "No", "14", "4"), Set.of(3)),
                new Question(2, "3+3 = ?",  List.of("6", "12/2", "No", "Why"), Set.of(1,2))
        );

        Quiz quiz = new Quiz(qts);

        Set<Integer> ans = Set.of(0);

        IllegalStateException thrown1 = Assertions.assertThrows(IllegalStateException.class, () -> {
            quiz.submitAnswer(ans);
        });
        Assertions.assertEquals("Quiz is not in progress", thrown1.getMessage());


        quiz.start();
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            quiz.submitAnswer(Set.of());
        });
        Assertions.assertEquals("Selected answers must not be Empty", thrown2.getMessage());



        Set<Integer> ans1 = Set.of(3);
        AnswerResult answerResult1 = quiz.submitAnswer(ans1);

        assertEquals(qts.get(0).isCorrectAnswer(ans1), answerResult1.correct());

        assertEquals(ans1, answerResult1.selectedAnswers());
        assertEquals(qts.get(0).correctAnswerIndexes(), answerResult1.correctAnswers());

        Set<Integer> ans2 = Set.of(3);
        AnswerResult answerResult2 = quiz.submitAnswer(ans2);
        assertNotEquals(qts.get(1).isCorrectAnswer(ans2), answerResult2.correct());
    }

    @Test
    void nextQuestion() {
        List<Question> qts = List.of(new Question(1,"2+2 = ?", List.of("1", "No", "14", "4"), Set.of(3)),
                new Question(2, "3+3 = ?",  List.of("6", "12/2", "No", "Why"), Set.of(1,2))
        );

        Quiz quiz = new Quiz(qts);

        IllegalStateException thrown1 = Assertions.assertThrows(IllegalStateException.class, () -> {
            quiz.nextQuestion();
        });
        Assertions.assertEquals("Quiz is not in progress", thrown1.getMessage());

        quiz.start();
        quiz.finish();
        IllegalStateException thrown2 = Assertions.assertThrows(IllegalStateException.class, () -> {
            quiz.nextQuestion();
        });
        Assertions.assertEquals("Quiz is not in progress", thrown2.getMessage());

        quiz.abort();
        IllegalStateException thrown3 = Assertions.assertThrows(IllegalStateException.class, () -> {
            quiz.nextQuestion();
        });
        Assertions.assertEquals("Quiz is not in progress", thrown3.getMessage());
    }
}