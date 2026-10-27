package defpackage;

import defpackage.zy2;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: TabSuggestion.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v33 {
    public final List<zy2.b> a;
    public final int b;

    public v33() {
        throw null;
    }

    public v33(int i, AbstractList abstractList) {
        this.a = Collections.unmodifiableList(abstractList);
        this.b = i;
    }
}
