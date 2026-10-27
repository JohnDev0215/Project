package defpackage;

/* JADX INFO: compiled from: KeyPrefix.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i41 {
    public final String a;

    public i41(String str) {
        this.a = str.substring(0, str.length() - 1);
    }

    public final String a(int i) {
        return this.a + i;
    }

    public final String b(String str) {
        return gq2.i(new StringBuilder(), this.a, str);
    }
}
