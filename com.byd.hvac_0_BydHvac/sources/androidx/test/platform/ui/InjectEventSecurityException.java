package androidx.test.platform.ui;

import androidx.test.platform.TestFrameworkException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class InjectEventSecurityException extends Exception implements TestFrameworkException {
    public InjectEventSecurityException(String str) {
        super(str);
    }

    public InjectEventSecurityException(Throwable th) {
        super(th);
    }

    public InjectEventSecurityException(String str, Throwable th) {
        super(str, th);
    }
}
