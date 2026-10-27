package p;

import androidx.work.WorkInfo$State;

/* JADX INFO: loaded from: classes.dex */
public final class jy2 {
    public String a;
    public WorkInfo$State b;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof jy2)) {
            return false;
        }
        jy2 jy2Var = (jy2) obj;
        return v03.b(this.a, jy2Var.a) && this.b == jy2Var.b;
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "IdAndState(id=" + this.a + ", state=" + this.b + ')';
    }
}
