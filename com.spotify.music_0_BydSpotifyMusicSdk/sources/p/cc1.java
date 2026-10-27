package p;

import android.os.LocaleList;

/* JADX INFO: loaded from: classes.dex */
public final class cc1 implements bc1 {
    public final LocaleList a;

    public cc1(Object obj) {
        this.a = (LocaleList) obj;
    }

    @Override // p.bc1
    public final Object a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        return this.a.equals(((bc1) obj).a());
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.toString();
    }
}
