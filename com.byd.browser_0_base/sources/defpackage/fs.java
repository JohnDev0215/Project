package defpackage;

import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fs implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ CompositorViewHolder f;

    public /* synthetic */ fs(CompositorViewHolder compositorViewHolder, int i) {
        this.e = i;
        this.f = compositorViewHolder;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        CompositorViewHolder compositorViewHolder = this.f;
        switch (i) {
        }
        return compositorViewHolder.getDynamicResourceLoader();
    }
}
