package util;

import java.util.Comparator;

public class AnswerClassComparator implements Comparator<Answer> {
    @Override
    public int compare(Answer o1, Answer o2) {
        return o1.getLike().compareTo(o2.getLike());
    }
}
