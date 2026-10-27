package defpackage;

import android.view.View;
import android.widget.AdapterView;
import org.chromium.content.browser.picker.DateTimeSuggestion;

/* JADX INFO: renamed from: jC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0982jC implements AdapterView.OnItemClickListener {
    public final /* synthetic */ C0927iC s;
    public final /* synthetic */ int t;
    public final /* synthetic */ double u;
    public final /* synthetic */ double v;
    public final /* synthetic */ double w;
    public final /* synthetic */ double x;
    public final /* synthetic */ C1589uC y;

    public C0982jC(C1589uC c1589uC, C0927iC c0927iC, int i, double d, double d2, double d3, double d4) {
        this.y = c1589uC;
        this.s = c0927iC;
        this.t = i;
        this.u = d;
        this.v = d2;
        this.w = d3;
        this.x = d4;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        if (i == this.s.getCount() - 1) {
            this.y.a();
            this.y.c(this.t, this.u, this.v, this.w, this.x);
        } else {
            ((QA) this.y.d).a(((DateTimeSuggestion) this.s.getItem(i)).a);
            this.y.a();
            this.y.b = true;
        }
    }
}
