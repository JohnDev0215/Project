package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class A4 implements Runnable {
    public final /* synthetic */ ArrayList s;
    public final /* synthetic */ Map t;

    public A4(C4 c4, ArrayList arrayList, Map map) {
        this.s = arrayList;
        this.t = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        int size = this.s.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.s.get(i);
            String strP = AbstractC1251o3.p(view);
            if (strP != null) {
                Iterator it = this.t.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        str = null;
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    if (strP.equals(entry.getValue())) {
                        str = (String) entry.getKey();
                        break;
                    }
                }
                view.setTransitionName(str);
            }
        }
    }
}
