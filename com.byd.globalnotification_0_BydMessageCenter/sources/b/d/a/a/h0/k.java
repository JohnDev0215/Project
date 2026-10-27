package b.d.a.a.h0;

import android.widget.AutoCompleteTextView;

/* JADX INFO: loaded from: classes.dex */
public class k implements AutoCompleteTextView.OnDismissListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h f1925a;

    public k(h hVar) {
        this.f1925a = hVar;
    }

    @Override // android.widget.AutoCompleteTextView.OnDismissListener
    public void onDismiss() {
        h hVar = this.f1925a;
        hVar.i = true;
        hVar.k = System.currentTimeMillis();
        h.g(this.f1925a, false);
    }
}
