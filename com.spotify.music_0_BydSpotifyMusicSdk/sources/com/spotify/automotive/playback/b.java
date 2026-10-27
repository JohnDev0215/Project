package com.spotify.automotive.playback;

import p.vr2;

/* JADX INFO: loaded from: classes.dex */
public final class b implements io.reactivex.rxjava3.functions.a {
    public final /* synthetic */ c c;
    public final /* synthetic */ boolean e;

    public b(c cVar, boolean z) {
        this.c = cVar;
        this.e = z;
    }

    @Override // io.reactivex.rxjava3.functions.a
    public final void run() {
        c cVar = this.c;
        vr2.l(cVar.j, null, null, new FullCorePlaybackControllerImpl$resumeCompletable$1$1$1(cVar, null, this.e), 3);
    }
}
