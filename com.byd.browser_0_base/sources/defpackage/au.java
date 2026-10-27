package defpackage;

import android.view.View;
import org.chromium.chrome.R$id;
import org.chromium.ui.widget.LoadingView;

/* JADX INFO: compiled from: ClickableTextBubble.java */
/* JADX INFO: loaded from: classes.dex */
public final class au implements LoadingView.c {
    public final /* synthetic */ int e;
    public final /* synthetic */ bu f;

    public au(bu buVar, int i) {
        this.f = buVar;
        this.e = i;
    }

    @Override // org.chromium.ui.widget.LoadingView.c
    public final void M() {
        this.f.b();
    }

    @Override // org.chromium.ui.widget.LoadingView.c
    public final void V0() {
        bu buVar = this.f;
        View viewFindViewById = buVar.n.findViewById(R$id.loading_view_container);
        viewFindViewById.setVisibility(0);
        viewFindViewById.setContentDescription(buVar.q.getString(this.e));
        buVar.n.findViewById(R$id.image).setVisibility(8);
        buVar.d(0L);
    }
}
