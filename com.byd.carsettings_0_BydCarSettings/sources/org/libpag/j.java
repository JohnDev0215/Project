package org.libpag;

import org.libpag.l;

/* JADX INFO: loaded from: classes3.dex */
public class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4063a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PAGImageView f4064b;

    public j(PAGImageView pAGImageView, int i) {
        this.f4064b = pAGImageView;
        this.f4063a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        PAGImageView pAGImageView = this.f4064b;
        PAGImageView.a(l.a.a(pAGImageView, pAGImageView.t.f4035b, this.f4063a, this.f4064b.w));
    }
}
