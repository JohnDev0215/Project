package defpackage;

import android.R;
import android.app.Activity;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;

/* JADX INFO: compiled from: CustomTabCompositorContentInitializer.java */
/* JADX INFO: loaded from: classes.dex */
public final class k30 implements hk1 {
    public final ArrayList e = new ArrayList();
    public final q2 f;
    public final Activity g;
    public final p61<CompositorViewHolder> h;
    public final zp1<TabContentManager> i;
    public final CompositorViewHolder.e j;
    public final xd3 k;
    public boolean l;

    public k30(q2 q2Var, Activity activity, p61<CompositorViewHolder> p61Var, zp1<TabContentManager> zp1Var, CompositorViewHolder.e eVar, xd3 xd3Var) {
        this.f = q2Var;
        this.g = activity;
        this.h = p61Var;
        this.i = zp1Var;
        this.j = eVar;
        this.k = xd3Var;
        q2Var.f(this);
    }

    @Override // defpackage.hk1
    public final void G0() {
        Activity activity = this.g;
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(R.id.content);
        h61 h61Var = new h61(this.h.get(), viewGroup, this.i, new yq2(5, this));
        this.j.T(h61Var, activity.findViewById(R$id.url_bar), (e00) activity.findViewById(R$id.control_container));
        ArrayList arrayList = this.e;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onResult(h61Var);
        }
        this.l = true;
        arrayList.clear();
        this.f.e(this);
    }
}
