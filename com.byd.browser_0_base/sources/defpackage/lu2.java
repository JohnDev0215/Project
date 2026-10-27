package defpackage;

/* JADX INFO: compiled from: StrictModePolicyViolation.java */
/* JADX INFO: loaded from: classes3.dex */
public final class lu2 extends Error {
    /* JADX WARN: Illegal instructions before constructor call */
    public lu2(jo3 jo3Var) {
        int i = jo3Var.a;
        super((i & 1) != 0 ? "DISK_WRITE" : (i & 2) != 0 ? "DISK_READ" : (i & 16) != 0 ? "RESOURCE_MISMATCH" : "UNKNOWN");
        StackTraceElement[] stackTraceElementArr = jo3Var.b;
        if (stackTraceElementArr.length == 0) {
            super.fillInStackTrace();
        } else {
            setStackTrace(stackTraceElementArr);
        }
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        return this;
    }
}
