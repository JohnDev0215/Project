package androidx.mediarouter.media;

import androidx.mediarouter.media.MediaRouteProvider;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: compiled from: MediaRouteProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {
    public final /* synthetic */ MediaRouteProvider.b.c e;
    public final /* synthetic */ d f;
    public final /* synthetic */ Collection g;
    public final /* synthetic */ MediaRouteProvider.b h;

    public e(MediaRouteProvider.b bVar, MediaRouteProvider.b.c cVar, d dVar, ArrayList arrayList) {
        this.h = bVar;
        this.e = cVar;
        this.f = dVar;
        this.g = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.c(this.h, this.f, this.g);
    }
}
