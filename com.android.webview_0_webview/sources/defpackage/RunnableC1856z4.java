package defpackage;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: renamed from: z4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1856z4 implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ ArrayList t;
    public final /* synthetic */ ArrayList u;
    public final /* synthetic */ ArrayList v;
    public final /* synthetic */ ArrayList w;

    public RunnableC1856z4(C4 c4, int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.s = i;
        this.t = arrayList;
        this.u = arrayList2;
        this.v = arrayList3;
        this.w = arrayList4;
    }

    @Override // java.lang.Runnable
    public void run() {
        for (int i = 0; i < this.s; i++) {
            AbstractC1251o3.I((View) this.t.get(i), (String) this.u.get(i));
            ((View) this.v.get(i)).setTransitionName((String) this.w.get(i));
        }
    }
}
