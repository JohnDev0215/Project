package defpackage;

import android.view.View;

/* JADX INFO: compiled from: FullscreenHtmlApiHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class oq0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ View e;
    public final /* synthetic */ qq0 f;

    public oq0(qq0 qq0Var, View view) {
        this.f = qq0Var;
        this.e = view;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i4 - i2 <= i8 - i6) {
            py2.d(this.f.t, true);
            this.e.removeOnLayoutChangeListener(this);
        }
    }
}
