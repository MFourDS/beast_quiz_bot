package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

class QuestionTest {


    @Test
    void  generalAccessPermission(){
        int id = 1;
        String question = "2+2 = ?";
        List<String> answers = List.of("1", "No", "14", "4");
        Set<Integer> correctAnswerIndexes = Set.of(3);
        Question qt = new Question(id, question, answers, correctAnswerIndexes);

        assertEquals(qt.id(), id);
        assertEquals(qt.text(), question);
        assertEquals(qt.answers(), answers);
        assertEquals(qt.correctAnswerIndexes(), correctAnswerIndexes);
    }


    @Test
    void testConstructorZeroId(){
        int id = 0;
        String question = "2+2 = ?";
        List<String> answers = List.of("1", "No", "14", "4");
        Set<Integer> correctAnswerIndexes = Set.of(3);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Question(id, question, answers, correctAnswerIndexes);
        });

        Assertions.assertEquals("Question id is must be positive", thrown.getMessage());
    }

    @Test
    void testConstructorNegativeId(){
        int id = -100;
        String question = "2+2 = ?";
        List<String> answers = List.of("1", "No", "14", "4");
        Set<Integer> correctAnswerIndexes = Set.of(3);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Question(id, question, answers, correctAnswerIndexes);
        });

        Assertions.assertEquals("Question id is must be positive", thrown.getMessage());
    }

    @Test
    void testConstructorNoQuestion(){
        int id = 100;
        String question = "";
        List<String> answers = List.of("1", "No", "14", "4");
        Set<Integer> correctAnswerIndexes = Set.of(3);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Question(id, question, answers, correctAnswerIndexes);
        });

        Assertions.assertEquals("Question text must not be blank", thrown.getMessage());
    }

    @Test
    void testConstructorNoAnswers(){
        int id = 100;
        String question = "2+2 = ?";
        List<String> answers = List.of();
        Set<Integer> correctAnswerIndexes = Set.of(3);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Question(id, question, answers, correctAnswerIndexes);
        });

        Assertions.assertEquals("List of answers must not be empty", thrown.getMessage());
    }

    @Test
    void testConstructorNoCorrectAnswerIndexes(){
        int id = 100;
        String question = "2+2 = ?";
        List<String> answers = List.of("1", "No", "14", "4");
        Set<Integer> correctAnswerIndexes = Set.of();

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Question(id, question, answers, correctAnswerIndexes);
        });

        Assertions.assertEquals("Sert of correctAnswerIndexes must not be empty", thrown.getMessage());
    }

    @Test
    void testConstructorCorrectAnswerIndexesOutOfRange(){
        int id = 100;
        String question = "2+2 = ?";
        List<String> answers = List.of("1", "No", "14", "4");
        Set<Integer> correctAnswerIndexes = Set.of(4, 1, 2);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Question(id, question, answers, correctAnswerIndexes);
        });

        Assertions.assertEquals("Correct answer index is out of range", thrown.getMessage());
    }


    @Test
    void constructorNullSafety(){
        int id = 100;
        String question = "2+2 = ?";
        List<String> answers = List.of("1", "No", "14", "4");
        Set<Integer> correctAnswerIndexes = Set.of(4, 1, 2);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(id, null, answers, correctAnswerIndexes)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(id, question, null, correctAnswerIndexes)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Question(id, question, answers, null)
        );
    }

    @Test
    void correctAnswerIndex() {
        int id = 100;
        String question = "2+2 = ?";
        List<String> answers = List.of("1", "No", "14", "4");
        Set<Integer> correctAnswerIndexes = new CopyOnWriteArraySet<>(Set.of(0, 1, 2));

        Question qt = new Question(id, question, answers, correctAnswerIndexes);


        correctAnswerIndexes.clear();
        assertEquals(Set.of(0, 1, 2), qt.correctAnswerIndexes());

        Set<Integer> newCorrectAnswerIndexes = qt.correctAnswerIndexes();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> newCorrectAnswerIndexes.clear());

    }

    @Test
    void answers(){
        int id = 100;
        String question = "2+2 = ?";
        List<String> answers = new ArrayList<>(List.of("1", "No", "14", "4"));
        Set<Integer> correctAnswerIndexes = Set.of(0, 1, 2);

        Question qt = new Question(id, question, answers, correctAnswerIndexes);


        answers.clear();
        assertEquals(List.of("1", "No", "14", "4"), qt.answers());

        List<String> newAnswers = qt.answers();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> newAnswers.clear());
    }

    @Test
    void testIsCorrectAnswerEmptyInput(){
        int id = 100;
        String question = "2+2 = ?";
        List<String> answers = List.of("1", "No", "14", "4");
        Set<Integer> correctAnswerIndexes = Set.of(3);

        Question qt = new Question(id, question, answers, correctAnswerIndexes);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            qt.isCorrectAnswer(Set.of());
        });

        Assertions.assertEquals("Sert of ansIndexes must not be empty", thrown.getMessage());
    }

    @Test
    void testIsCorrectAnswerIndexOutOfRange(){
        int id = 100;
        String question = "2+2 = ?";
        List<String> answers = List.of("1", "No", "14", "4");
        Set<Integer> correctAnswerIndexes = Set.of(3);

        Question qt = new Question(id, question, answers, correctAnswerIndexes);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            qt.isCorrectAnswer(Set.of(100));
        });

        Assertions.assertEquals("Correct answer index is out of range", thrown.getMessage());
    }

    @Test
    void testIsCorrectAnswerOneRightAnswers(){
        int id = 100;
        String question = "2+2 = ?";
        List<String> answers = List.of("1", "No", "14", "4");
        Set<Integer> correctAnswerIndexes = Set.of(3);

        Question qt = new Question(id, question, answers, correctAnswerIndexes);

        assertEquals(true, qt.isCorrectAnswer(correctAnswerIndexes));
    }

    @Test
    void testIsCorrectAnswerSomeRightAnswers(){
        int id = 100;
        String question = "2+2 = ?";
        List<String> answers = List.of("4/1", "8/2", "4.0 + 0", "4");
        Set<Integer> correctAnswerIndexes = Set.of(0,1,2,3);

        Question qt = new Question(id, question, answers, correctAnswerIndexes);

        assertEquals(true, qt.isCorrectAnswer(correctAnswerIndexes));
    }

    @Test
    void testIsCorrectAnswerWrongAnswers(){
        int id = 100;
        String question = "2+2 = ?";
        List<String> answers = List.of("1", "No", "14", "4");
        Set<Integer> correctAnswerIndexes = Set.of(3);

        Question qt = new Question(id, question, answers, correctAnswerIndexes);

        assertEquals(false, qt.isCorrectAnswer(Set.of(0)));
    }
}