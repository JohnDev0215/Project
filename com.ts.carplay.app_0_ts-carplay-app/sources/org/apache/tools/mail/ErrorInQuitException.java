package org.apache.tools.mail;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ErrorInQuitException extends IOException {
    public ErrorInQuitException(IOException e) {
        super(e.getMessage());
    }
}
