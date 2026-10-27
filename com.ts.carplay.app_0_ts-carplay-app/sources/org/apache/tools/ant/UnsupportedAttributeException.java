package org.apache.tools.ant;

/* JADX INFO: loaded from: classes.dex */
public class UnsupportedAttributeException extends BuildException {
    private String attribute;

    public UnsupportedAttributeException(String msg, String attribute) {
        super(msg);
        this.attribute = attribute;
    }

    public String getAttribute() {
        return this.attribute;
    }
}
