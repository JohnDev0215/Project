package android.car;

/* JADX INFO: loaded from: classes.dex */
public class XCarTransactionException extends UnsupportedOperationException {
    public XCarTransactionException(Throwable th, String str, Object... objArr) {
        super(String.format(str, objArr), th);
    }
}
