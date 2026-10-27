package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class B4 implements Runnable {
    public final /* synthetic */ ArrayList s;
    public final /* synthetic */ Map t;

    public B4(C4 c4, ArrayList arrayList, Map map) {
        this.s = arrayList;
        this.t = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        int size = this.s.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.s.get(i);
            view.setTransitionName((String) this.t.get(AbstractC1251o3.p(view)));
        }
    }
}
