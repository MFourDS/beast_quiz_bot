package domain;

import java.util.List;

/**<h6>Класс отвечает за статус квиза</h6>
 * <h7>Это защита от начала квиза после того как он закончен и отправки ответа в рандомный момент и тп.</h7>
 * Порядок только такой:
 *  <ul>
 *     <li>NOT_STARTED -> IN_PROGRESS</li>
 *     <li>IN_PROGRESS -> FINISHED</li>
 *  <ul>
 */
enum QuizStatus {
    NOT_STARTED,
    IN_PROGRESS,
    FINISHED
}

/**Domain - Квиз*/
public class Quiz {
    /**Массив вопросов для пользователя*/
    private final List<Question> questions;
    /** Индекс в массиве вопросов, на котором сейчас пользователь*/
    private int currentQuestionIndex;
    private int score;
    private QuizStatus status;


    /** Конструктор создает квиз
     *
     * @param questions список подготовленных вопросов
     *
     * @throws IllegalArgumentException если список вопросов пустой
     */
    Quiz(List<Question> questions) {
        if (questions.isEmpty()) throw new IllegalArgumentException("Question list is empty");

        this.questions = List.copyOf(questions);
        this.status = QuizStatus.NOT_STARTED;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    /**Передается копия списка вопросов, чтобы не изменить оригинал*/
    public List<Question> getQuestions() {
        return  List.copyOf(questions);
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public int getScore() {
        return score;
    }

    /**Функция меняет статус квиза с NOT_STARTED на IN_PROGRESS
     * @throws IllegalStateException если квиз не в NOT_STARTED
     */
    public void start() {
        if (status != QuizStatus.NOT_STARTED) {
            throw new IllegalStateException("Quiz cannot be started");
        }

        this.status = QuizStatus.IN_PROGRESS;
    }

    /**Функция меняет статус квиза с IN_PROGRESS на FINISHED
     * @throws IllegalStateException если квиз не в IN_PROGRESS
     */
    public void finish() {
        if (status != QuizStatus.IN_PROGRESS) {
            throw new IllegalStateException("Quiz is not in progress");
        }

        status = QuizStatus.FINISHED;
    }

    /**Функция проверяет ответ на вопрос.
     * Она создает копию текущего вопроса и сверят у него ответ.
     * Прибавляет очки за каждый правильный ответ
     *
     * @param answer индекс в списке вопросов
     * @throws IllegalStateException если статус квиза не IN_PROGRESS
     * @return правильный вопрос или нет
     */
    public boolean submitAnswer(int answer) {
        if (status != QuizStatus.IN_PROGRESS) {
            throw new IllegalStateException("Quiz is not in progress");
        }

        Question question = questions.get(currentQuestionIndex);

        boolean correct = question.isCorrectAnswer(answer);

        if (correct) {
            score += calkScore();
        }

        return correct;
    }

    /**Формула для подсчета очков*/
    private int calkScore(){
        return 10;
    }

    /**Функция инкрементирует индекс в массиве вопросов
     * @throws IllegalStateException если статус квиза не IN_PROGRESS
     *
     * WARNING: инкрементирует, даже если массив закончился, возможен выход за границы массива
     */
    public void nextQuestion() {
        if (status != QuizStatus.IN_PROGRESS) {
            throw new IllegalStateException("Quiz is not in progress");
        }

        currentQuestionIndex++;
    }
}
