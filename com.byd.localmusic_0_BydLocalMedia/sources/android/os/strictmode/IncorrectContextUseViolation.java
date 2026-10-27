package android.os.strictmode;

/* JADX INFO: loaded from: classes2.dex */
public final class IncorrectContextUseViolation extends Violation {
    public IncorrectContextUseViolation(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
