package p;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class zh extends m40 {
    public final Context a;
    public final yt b;
    public final yt c;
    public final String d;

    public zh(Context context, yt ytVar, yt ytVar2, String str) {
        if (context == null) {
            throw new NullPointerException("Null applicationContext");
        }
        this.a = context;
        if (ytVar == null) {
            throw new NullPointerException("Null wallClock");
        }
        this.b = ytVar;
        if (ytVar2 == null) {
            throw new NullPointerException("Null monotonicClock");
        }
        this.c = ytVar2;
        if (str == null) {
            throw new NullPointerException("Null backendName");
        }
        this.d = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof m40)) {
            return false;
        }
        m40 m40Var = (m40) obj;
        if (this.a.equals(((zh) m40Var).a)) {
            zh zhVar = (zh) m40Var;
            if (this.b.equals(zhVar.b) && this.c.equals(zhVar.c) && this.d.equals(zhVar.d)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CreationContext{applicationContext=");
        sb.append(this.a);
        sb.append(", wallClock=");
        sb.append(this.b);
        sb.append(", monotonicClock=");
        sb.append(this.c);
        sb.append(", backendName=");
        return u4.m(sb, this.d, "}");
    }
}
