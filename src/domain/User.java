package domain;

/**Domain - Юзер*/
public class User {
    /**Свой идентификатор пользователя*/
    final int id;
    /**Идентификатор тг: 10 значное число или меньше, уникальное для любого пользователя и бота*/
    final long telegramId;
    /**Имя пользователя (имя с @)*/
    private String username;
    /**Ник пользователя (без @)*/
    private String surname;
    /**Наибольший счет*/
    private int highestScore;

    /** Перегруженный конструктор создает объект и проверяет корректность telegramId и id.
     * Можно на этих полях или на всех
     *
     * @throws IllegalArgumentException если id или telegramId неверные
     */
    public User(int id, long telegramId) {
        this(id, telegramId, "", "", 0);
    }


    public User(int id, long telegramId, String username, String surname, int highestScore) {
        if (id <= 0) throw new IllegalArgumentException("User id must be positive");
        if (telegramId <= 0) throw new IllegalArgumentException("Telegram id  must be positive");
        if (highestScore < 0) {
            throw new IllegalArgumentException("Highest score cannot be negative");
        }

        this.id = id;
        this.telegramId = telegramId;
        this.username = username;
        this.surname = surname;
        this.highestScore = highestScore;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**Обновляет максимальный счет пользователя
     * Берет максимум из двух
     */
    public void updateHighestScore(int score) {
        this.highestScore = Math.max(this.highestScore, score);
    }
}