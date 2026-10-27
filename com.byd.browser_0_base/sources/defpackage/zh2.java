package defpackage;

/* JADX INFO: compiled from: SelectPopupItem.java */
/* JADX INFO: loaded from: classes3.dex */
public final class zh2 extends de0 {
    public final String a;
    public final int b;

    public zh2(String str, int i) {
        this.a = str;
        this.b = i;
    }

    @Override // defpackage.de0, defpackage.ce0
    public final boolean a() {
        return this.b == 0;
    }

    @Override // defpackage.ce0
    public final String f() {
        return this.a;
    }

    @Override // defpackage.de0, defpackage.ce0
    public final boolean isEnabled() {
        int i = this.b;
        return i == 2 || i == 0;
    }
}
