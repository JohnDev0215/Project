package p;

import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;

/* JADX INFO: loaded from: classes.dex */
public final class v0 implements wu2 {
    public boolean a = false;
    public int b;
    public final /* synthetic */ ActionBarContextView c;

    public v0(ActionBarContextView actionBarContextView) {
        this.c = actionBarContextView;
    }

    @Override // p.wu2
    public final void a() {
        if (this.a) {
            return;
        }
        ActionBarContextView actionBarContextView = this.c;
        actionBarContextView.n = null;
        super/*android.view.View*/.setVisibility(this.b);
    }

    @Override // p.wu2
    public final void b(View view) {
        this.a = true;
    }

    @Override // p.wu2
    public final void c() {
        super/*android.view.View*/.setVisibility(0);
        this.a = false;
    }
}
