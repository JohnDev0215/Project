package p;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class wt0 {
    public final String a;
    public final Map b;

    public wt0(String str, Map map) {
        this.a = str;
        this.b = map;
    }

    public static ay2 a(String str) {
        ay2 ay2Var = new ay2();
        ay2Var.e = null;
        ay2Var.c = str;
        return ay2Var;
    }

    public static wt0 b(String str) {
        return new wt0(str, Collections.emptyMap());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof wt0)) {
            return false;
        }
        wt0 wt0Var = (wt0) obj;
        return this.a.equals(wt0Var.a) && this.b.equals(wt0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "FieldDescriptor{name=" + this.a + ", properties=" + this.b.values() + "}";
    }
}
