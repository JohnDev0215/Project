package defpackage;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: VerificationResultStore.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class gl3 {
    public static final Set<String> a = Collections.synchronizedSet(new HashSet());

    public static boolean b(String str, String str2, ft1 ft1Var) {
        String strJoin = "";
        List listAsList = Arrays.asList("");
        if (listAsList != null) {
            Collections.sort(listAsList);
            strJoin = String.join(",", listAsList);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(",");
        sb.append(ft1Var);
        sb.append(",");
        sb.append(str2);
        return a.contains(gq2.i(sb, ",", strJoin));
    }

    public abstract HashSet a();
}
