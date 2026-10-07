package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    @Test
    void testDefaultValuesInConstructor() {
        int id = 1;
        long tgId =123333;
        User user = new User(id, tgId);

        assertEquals(id, user.id);
        assertEquals(tgId, user.telegramId);
        assertEquals("", user.getNickname());
        assertEquals("", user.getUsername());
        assertEquals(0, user.getTotalScore());
    }

    @Test
    void testSomeValuesInConstructor(){
        int id = 1;
        long tgId =123333;
        String username = "@qwer";
        String nickname = "alex123";
        int totalScore = 123;

        User user = new User(id, tgId, username, nickname, totalScore);

        assertEquals(id, user.id);
        assertEquals(tgId, user.telegramId);
        assertEquals(username, user.getUsername());
        assertEquals(nickname, user.getNickname());
        assertEquals(totalScore, user.getTotalScore());
    }

    @Test
    void testZeroInScoreInConstructor(){
        int id = 1;
        long tgId = 123333;
        String username = "@qwer";
        String nickname = "alex123";
        int totalScore = 0;

        User user = new User(id, tgId, username, nickname, totalScore);
        assertEquals(totalScore, user.getTotalScore());
    }

    @Test
    void testZeroInIdInConstructor(){
        int id = 0;
        long tgId = 123333;

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User(id, tgId);
        });

        Assertions.assertEquals("User id must be positive", thrown.getMessage());
    }

    @Test
    void testZeroInTgIdInConstructor(){
        int id = 1;
        long tgId = 0;

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User(id, tgId);
        });

        Assertions.assertEquals("Telegram id must be positive", thrown.getMessage());
    }

    @Test
    void testExpectedNegativeTotalScoreInConstructor(){
        int id = 1;
        long tgId = 123333;
        String username = "@qwer";
        String nickname = "alex123";
        int totalScore = -123;


        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User(id, tgId, username, nickname, totalScore);
        });

        Assertions.assertEquals("Score cannot be negative", thrown.getMessage());
    }


    @Test
    void testExpectedNegativeIdInConstructor(){
        int id = -1;
        long tgId = 123333;

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User(id, tgId);
        });

        Assertions.assertEquals("User id must be positive", thrown.getMessage());
    }

    @Test
    void testExpectedNegativeTgIdInConstructor(){
        int id = 1;
        long tgId = -123333;

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User(id, tgId);
        });

        Assertions.assertEquals("Telegram id must be positive", thrown.getMessage());
    }

    @Test
    void testExpectedNegativeUpdateTotalScore(){
        int id = 1;
        long tgId = 123333;
        String username = "@qwer";
        String nickname = "alex123";
        int totalScore = 123;

        User user = new User(id, tgId, username, nickname, totalScore);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.updateTotalScore(-100);
        });

        Assertions.assertEquals("Score cannot be negative", thrown.getMessage());
    }

    @Test
    void testPositiveUpdateTotalScore () {
        int id = 1;
        long tgId = 123333;
        String username = "@qwer";
        String nickname = "alex123";
        int totalScoreCurrent = 123;
        int totalScoreNew1 = 124;
        int totalScoreNew2 = 124;
        int totalScoreNew3 = 0;

        User user = new User(id, tgId, username, nickname, totalScoreCurrent);
        user.updateTotalScore(totalScoreNew1);
        assertEquals(totalScoreNew1, user.getTotalScore());
        user.updateTotalScore(totalScoreNew2);
        assertEquals(totalScoreNew2, user.getTotalScore());
        user.updateTotalScore(totalScoreNew3);
        assertEquals(totalScoreNew2, user.getTotalScore());
    }

    @Test
    void testSetEmptyUsername (){
        int id = 1;
        long tgId = 123333;

        User user = new User(id, tgId);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("");
        });

        Assertions.assertEquals("Username must not be Blank", thrown.getMessage());
    }

    @Test
    void testSetWrongMiddleUsername(){
        int id = 1;
        long tgId = 123333;

        User user = new User(id, tgId);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("123@");
        });

        Assertions.assertEquals("Username must start with @", thrown.getMessage());
    }

    @Test
    void testSetWrongUsername(){
        int id = 1;
        long tgId = 123333;

        User user = new User(id, tgId);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("123");
        });

        Assertions.assertEquals("Username must start with @", thrown.getMessage());
    }

    @Test
    void testSetUsername() {
        int id = 1;
        long tgId = 123333;
        String username = "@asodihohfsa";

        User user = new User(id, tgId);
        user.setUsername("@asodihohfsa");

        assertEquals(username, user.getUsername());
    }

    @Test
    void testSetEmptyNickname (){
        int id = 1;
        long tgId = 123333;

        User user = new User(id, tgId);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            user.setNickname("");
        });

        Assertions.assertEquals("Nickname must not be Blank", thrown.getMessage());
    }

    @Test
    void testSetNickname() {
        int id = 1;
        long tgId = 123333;
        String nickname = "AlexanderPoop";

        User user = new User(id, tgId);
        user.setNickname("AlexanderPoop");

        assertEquals(nickname, user.getNickname());
    }
}