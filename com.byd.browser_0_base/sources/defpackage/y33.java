package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TabSuggestionsClientFetcher.java */
/* JADX INFO: loaded from: classes2.dex */
public final class y33 implements z33 {
    public final ArrayList a;

    public y33() {
        sk skVar = bq.a;
        if (!N.M6bsIDpc("CloseTabSuggestions", "baseline_tab_suggestions", false)) {
            this.a = new ArrayList(Arrays.asList(new hr2()));
            return;
        }
        ArrayList arrayList = new ArrayList();
        this.a = arrayList;
        if (N.M6bsIDpc("CloseTabSuggestions", "baseline_group_tab_suggestions", false)) {
            arrayList.add(new yd(0));
        }
        if (N.M6bsIDpc("CloseTabSuggestions", "baseline_close_tab_suggestions", false)) {
            arrayList.add(new yd(1));
        }
    }

    @Override // defpackage.z33
    public final void a(zy2 zy2Var, jb1 jb1Var) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            List<v33> listA = ((x33) it.next()).a(zy2Var);
            if (listA != null && !listA.isEmpty()) {
                arrayList.addAll(listA);
            }
        }
        jb1Var.onResult(new a43(arrayList, zy2Var));
    }

    @Override // defpackage.z33
    public final void isEnabled() {
    }
}
