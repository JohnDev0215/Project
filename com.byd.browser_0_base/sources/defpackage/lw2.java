package defpackage;

import defpackage.lk1;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: compiled from: SuggestionsUiDelegateImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class lw2 implements kw2 {
    public final ArrayList a = new ArrayList();
    public final hw2 b;
    public final mk1 c;
    public final bx0 d;

    public lw2(hw2 hw2Var, Profile profile, lk1.b bVar) {
        this.b = hw2Var;
        this.d = new bx0(profile);
        this.c = bVar;
    }

    public final void a() {
        bx0 bx0Var = this.d;
        LargeIconBridge largeIconBridge = bx0Var.b;
        if (largeIconBridge != null) {
            largeIconBridge.a();
            bx0Var.b = null;
        }
        ArrayList arrayList = this.a;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((x80) it.next()).onDestroy();
        }
        arrayList.clear();
    }

    @Override // defpackage.kw2
    public boolean isVisible() {
        lk1.b bVar = (lk1.b) this.c;
        return bVar.a == bVar.c.i();
    }
}
