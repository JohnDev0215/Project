package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: HistoricalEntry.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qu0 {
    public final int a;
    public final String b;
    public final List<Tab> c;

    public qu0(Tab tab) {
        this.a = -1;
        this.b = null;
        this.c = Collections.singletonList(tab);
    }

    public final boolean a() {
        return this.c.size() == 1 && this.a == -1;
    }

    public qu0(int i, String str, ArrayList arrayList) {
        this.a = i;
        this.b = str;
        this.c = arrayList;
    }
}
