package defpackage;

import android.view.ViewStructure;

/* JADX INFO: compiled from: WebContentsAccessibilityImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class lq3 implements Runnable {
    public final /* synthetic */ ViewStructure e;

    public lq3(ViewStructure viewStructure) {
        this.e = viewStructure;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.asyncCommit();
    }
}
