package p;

/* JADX INFO: loaded from: classes.dex */
public final class y51 implements f82 {
    public final String a;

    public y51(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof y51) && v03.b(this.a, ((y51) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("InvalidPartnerVoice(errorMessage="), this.a, ')');
    }
}
