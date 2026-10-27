package a.b.a.a.g;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ View f60a;
    public final /* synthetic */ boolean b;

    public f(View view, boolean z) {
        this.f60a = view;
        this.b = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f60a.setVisibility(this.b ? 0 : 4);
    }
}
