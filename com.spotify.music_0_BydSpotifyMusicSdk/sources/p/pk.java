package p;

import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class pk extends qk {
    public final Set a;

    public pk(Set set) {
        v03.j(set, "possibleTypes");
        this.a = set;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof pk) && v03.b(this.a, ((pk) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "BPossibleTypes(possibleTypes=" + this.a + ')';
    }
}
