package defpackage;

import defpackage.zy2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: BaselineTabSuggestionProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yd implements x33 {
    public final int a;

    public yd(int i) {
        this.a = i;
    }

    @Override // defpackage.x33
    public final List<v33> a(zy2 zy2Var) {
        List<zy2.b> list = zy2Var.a;
        if (list == null || list.size() < 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        return Arrays.asList(new v33(this.a, arrayList));
    }
}
