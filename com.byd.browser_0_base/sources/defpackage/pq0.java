package defpackage;

import android.view.View;
import org.chromium.components.embedder_support.view.ContentView;

/* JADX INFO: compiled from: FullscreenHtmlApiHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class pq0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ View e;
    public final /* synthetic */ qq0 f;

    public pq0(qq0 qq0Var, ContentView contentView) {
        this.f = qq0Var;
        this.e = contentView;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        qq0 qq0Var = this.f;
        qq0Var.f.sendEmptyMessage(1);
        if (i4 - i2 > i8 - i6 || i3 - i > i7 - i5) {
            qq0Var.l().d();
            this.e.removeOnLayoutChangeListener(this);
        }
    }
}
