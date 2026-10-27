package defpackage;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class L6 implements Y6 {
    public final /* synthetic */ View a;
    public final /* synthetic */ ArrayList b;

    public L6(O6 o6, View view, ArrayList arrayList) {
        this.a = view;
        this.b = arrayList;
    }

    @Override // defpackage.Y6
    public void a(Z6 z6) {
    }

    @Override // defpackage.Y6
    public void b(Z6 z6) {
    }

    @Override // defpackage.Y6
    public void c(Z6 z6) {
    }

    @Override // defpackage.Y6
    public void d(Z6 z6) {
        z6.v(this);
        this.a.setVisibility(8);
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ((View) this.b.get(i)).setVisibility(0);
        }
    }
}
