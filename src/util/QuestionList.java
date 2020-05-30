package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class QuestionList implements Serializable{
    private static final long serialVersionUID = 1113799434508676L;
    public LinkedList<Question> questionArrayList;

    public QuestionList(LinkedList<Question> questionArrayList) {
        this.questionArrayList = questionArrayList;
    }

    @Override
    public String toString() {
        return (questionArrayList.size()!=0 ? ("Not null" ): ("Null"));
    }
}
