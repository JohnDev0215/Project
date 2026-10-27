package defpackage;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/* JADX INFO: renamed from: gB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0814gB implements AdapterView.OnItemClickListener {
    public final /* synthetic */ ListView s;
    public final /* synthetic */ C0926iB t;

    public C0814gB(C0926iB c0926iB, ListView listView) {
        this.t = c0926iB;
        this.s = listView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.t.d(C0926iB.c(this.s));
        this.t.a.dismiss();
    }
}
