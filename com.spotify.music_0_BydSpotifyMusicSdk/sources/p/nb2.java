package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class nb2 extends zu {
    public static jb2 H0(Iterator it) {
        v03.j(it, "<this>");
        sb sbVar = new sb(3, it);
        return sbVar instanceof wx ? sbVar : new wx(sbVar);
    }
}
