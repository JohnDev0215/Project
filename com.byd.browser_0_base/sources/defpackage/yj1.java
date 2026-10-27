package defpackage;

import J.N;

/* JADX INFO: compiled from: MutableFlagWithSafeDefault.java */
/* JADX INFO: loaded from: classes.dex */
public final class yj1 extends ln0 {
    public final boolean c;
    public Boolean d;

    public yj1(String str, boolean z) {
        super(str);
        this.c = z;
    }

    public final boolean a() {
        Boolean bool = this.d;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (!a8.Q()) {
            return this.c;
        }
        sk skVar = bq.a;
        Boolean boolValueOf = Boolean.valueOf(N.M09VlOh_(this.a));
        this.d = boolValueOf;
        return boolValueOf.booleanValue();
    }
}
