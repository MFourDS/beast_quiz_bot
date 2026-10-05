package domain;

import java.util.Set;

/**Domain - класс заглушка, чтобы смотреть ответы, ничего лучше не придумалось
 *
 * @param correct
 * @param selectedAnswers
 * @param correctAnswers
 */
public record AnswerResult(
        boolean correct,
        Set<Integer> selectedAnswers,
        Set<Integer> correctAnswers) {

}