package domain;

/**Статус пользователя - задел на будущее*/
enum UserPermissions{
    ADMIN,
    NORMAL_PLAYER
}

/**Domain - Юзер*/
public class User {
    /**Свой идентификатор пользователя*/
    public final int id;
    /**Идентификатор тг: 10 значное число или меньше, уникальное для любого пользователя и бота*/
    public final long telegramId;
    /**Имя пользователя (имя с @)*/
    private String username;
    /**Ник пользователя (без @)*/
    private String nickname;
    /**Счет за все партии*/
    private int totalScore;
    /**Статус пользователя*/
    private UserPermissions permissions;

    /** Перегруженный конструктор создает объект и проверяет корректность telegramId и id.
     * Можно на этих полях или на всех
     *
     * @throws IllegalArgumentException если id или telegramId неверные
     */
    public User(int id, long telegramId) {
        this(id, telegramId, "", "", 0);
    }


    public User(int id, long telegramId, String username, String nickname, int totalScore) {
        if (id <= 0) throw new IllegalArgumentException("User id must be positive");
        if (telegramId <= 0) throw new IllegalArgumentException("Telegram id  must be positive");
        if (totalScore < 0) {
            throw new IllegalArgumentException("Highest score cannot be negative");
        }

        this.id = id;
        this.telegramId = telegramId;
        this.username = username;
        this.nickname = nickname;
        this.totalScore = totalScore;
        this.permissions = UserPermissions.NORMAL_PLAYER;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSurname(String nickname) {
        this.nickname = nickname;
    }

    public void updateTotalScore(int score) {
        this.totalScore = Math.max(this.totalScore, score);
    }

    public boolean isUserAdmin(){
        return UserPermissions.ADMIN == permissions;
    }
}