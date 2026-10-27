package defpackage;

/* JADX INFO: compiled from: WebappVerifier.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ss3 implements il3 {
    @Override // defpackage.il3
    public final String a(String str) {
        return f(str) ? e() : str;
    }

    @Override // defpackage.il3
    public final boolean b(String str) {
        return f(str);
    }

    @Override // defpackage.il3
    public final boolean c(String str) {
        return f(str);
    }

    @Override // defpackage.il3
    public final s62<Boolean> d(String str) {
        return s62.c(Boolean.valueOf(f(str)));
    }

    public abstract String e();

    public abstract boolean f(String str);
}
