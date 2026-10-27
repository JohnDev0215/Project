package defpackage;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: Relationship.java */
/* JADX INFO: loaded from: classes2.dex */
public final class la2 {
    public final String a;
    public final ft1 b;
    public final String c;
    public final List<String> d;

    public la2(String str, List<String> list, ft1 ft1Var, String str2) {
        this.a = str;
        this.d = list;
        this.b = ft1Var;
        this.c = str2;
    }

    public final String toString() {
        String strJoin;
        List<String> list = this.d;
        if (list != null) {
            Collections.sort(list);
            strJoin = String.join(",", list);
        } else {
            strJoin = "";
        }
        return this.a + "," + this.b + "," + this.c + "," + strJoin;
    }
}
