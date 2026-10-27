package a5;

import android.widget.AutoCompleteTextView;

/* JADX INFO: loaded from: classes.dex */
public final class n implements AutoCompleteTextView.OnDismissListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ o f11159a;

    public n(o oVar) {
        this.f11159a = oVar;
    }

    @Override // android.widget.AutoCompleteTextView.OnDismissListener
    public final void onDismiss() {
        o oVar = this.f11159a;
        oVar.f11164i = true;
        oVar.f11166k = System.currentTimeMillis();
        oVar.g(false);
    }
}
