package defpackage;

import defpackage.mn0;

/* JADX INFO: compiled from: Flags.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class mn0<F extends mn0<F>> {
    public boolean b = false;
    public int a = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.a == ((mn0) obj).a;
    }

    public final int hashCode() {
        return this.a;
    }
}
