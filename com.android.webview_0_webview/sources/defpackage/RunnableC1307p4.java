package defpackage;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: renamed from: p4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1307p4 implements Runnable {
    public final /* synthetic */ Object s;
    public final /* synthetic */ C4 t;
    public final /* synthetic */ View u;
    public final /* synthetic */ K3 v;
    public final /* synthetic */ ArrayList w;
    public final /* synthetic */ ArrayList x;
    public final /* synthetic */ ArrayList y;
    public final /* synthetic */ Object z;

    public RunnableC1307p4(Object obj, C4 c4, View view, K3 k3, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
        this.s = obj;
        this.t = c4;
        this.u = view;
        this.v = k3;
        this.w = arrayList;
        this.x = arrayList2;
        this.y = arrayList3;
        this.z = obj2;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj = this.s;
        if (obj != null) {
            this.t.m(obj, this.u);
            this.x.addAll(AbstractC1526t4.h(this.t, this.s, this.v, this.w, this.u));
        }
        if (this.y != null) {
            if (this.z != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.u);
                this.t.n(this.z, this.y, arrayList);
            }
            this.y.clear();
            this.y.add(this.u);
        }
    }
}
