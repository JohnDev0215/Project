package org.apache.tools.ant.input;

/* JADX INFO: loaded from: classes.dex */
public class InputRequest {
    private String input;
    private String prompt;

    public InputRequest(String prompt) {
        if (prompt == null) {
            throw new IllegalArgumentException("prompt must not be null");
        }
        this.prompt = prompt;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public boolean isInputValid() {
        return true;
    }

    public String getInput() {
        return this.input;
    }
}
