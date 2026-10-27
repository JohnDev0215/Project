package defpackage;

import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: renamed from: jB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0981jB implements AdapterView.OnItemClickListener {
    public final /* synthetic */ C1149mB s;

    public C0981jB(C1149mB c1149mB) {
        this.s = c1149mB;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        C1149mB c1149mB = this.s;
        int[] iArr = {i};
        if (!c1149mB.c) {
            c1149mB.a.a(iArr);
            c1149mB.c = true;
        }
        this.s.b(false);
    }
}
