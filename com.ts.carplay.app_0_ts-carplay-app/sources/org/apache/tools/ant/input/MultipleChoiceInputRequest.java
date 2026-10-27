package org.apache.tools.ant.input;

import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class MultipleChoiceInputRequest extends InputRequest {
    private Vector choices;

    public MultipleChoiceInputRequest(String prompt, Vector choices) {
        super(prompt);
        this.choices = new Vector();
        if (choices == null) {
            throw new IllegalArgumentException("choices must not be null");
        }
        this.choices = choices;
    }

    public Vector getChoices() {
        return this.choices;
    }

    @Override // org.apache.tools.ant.input.InputRequest
    public boolean isInputValid() {
        return this.choices.contains(getInput());
    }
}
