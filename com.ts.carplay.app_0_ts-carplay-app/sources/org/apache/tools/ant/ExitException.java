package org.apache.tools.ant;

/* JADX INFO: loaded from: classes.dex */
public class ExitException extends SecurityException {
    private int status;

    public ExitException(int status) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ExitException: status ");
        stringBuffer.append(status);
        super(stringBuffer.toString());
        this.status = status;
    }

    public ExitException(String msg, int status) {
        super(msg);
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}
