package org.apache.tools.ant;

/* JADX INFO: loaded from: classes.dex */
public class UnsupportedElementException extends BuildException {
    private String element;

    public UnsupportedElementException(String msg, String element) {
        super(msg);
        this.element = element;
    }

    public String getElement() {
        return this.element;
    }
}
