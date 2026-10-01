package domain;

import java.util.List;

/** Domain - Вопрос
 *
 * @param id идентификатор вопроса
 * @param text текст вопроса
 * @param answers список ответов
 * @param correctAnswerIndex номер правильного ответа в списке
 */
public record Question(int id, String text, List<String> answers, int correctAnswerIndex) {

    /** Создает вопрос и проверяет правильность ввода данных
     *
     * @throws IllegalArgumentException если данные вопроса некорректны
     */
    public Question(int id, String text, List<String> answers, int correctAnswerIndex) {

        if (id <= 0) throw new IllegalArgumentException("Question id is must be positive");
        if (text.isBlank()) throw new IllegalArgumentException("Question text must not be blank");
        if (answers.isEmpty()) throw new IllegalArgumentException("List of answers must not be empty");
        if (correctAnswerIndex < 0 || correctAnswerIndex >= answers.size()) {
            throw new IllegalArgumentException("Question's correctAnswerIndex must be in range");
        }

        this.id = id;
        this.text = text;
        this.answers = List.copyOf(answers);
        this.correctAnswerIndex = correctAnswerIndex;
    }

    /**Возвращается копия списка ответов, чтобы не изменить оригинал*/
    @Override
    public List<String> answers() {
        return List.copyOf(answers);
    }


    /**Проверяет, является ли данный индекс правильным ответом
     *
     * @param ansIndex индекс выбранного ответа
     * @return true, если ответ правильный, иначе false
     * @throws IllegalArgumentException если индекс выходит за пределы списка answers
     */
    public boolean isCorrectAnswer(int ansIndex) {
        if (ansIndex < 0 || ansIndex >= answers.size()) {
            throw new IllegalArgumentException("Answer index is out of range");
        }

        return ansIndex == correctAnswerIndex;
    }
}