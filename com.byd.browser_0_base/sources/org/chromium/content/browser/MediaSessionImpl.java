package org.chromium.content.browser;

import defpackage.ke1;
import defpackage.vd1;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.services.media_session.MediaImage;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;

/* JADX INFO: loaded from: classes3.dex */
public class MediaSessionImpl extends vd1 {
    public long k;
    public final b<ke1> l;
    public final b.a m;
    public boolean n;
    public Boolean o;
    public MediaMetadata p;
    public List<MediaImage> q;
    public HashSet<Integer> r;
    public MediaPosition s;

    public MediaSessionImpl(long j) {
        this.k = j;
        b<ke1> bVar = new b<>();
        this.l = bVar;
        this.m = bVar.d();
    }

    @CalledByNative
    public static MediaSessionImpl create(long j) {
        return new MediaSessionImpl(j);
    }

    @CalledByNative
    public final boolean hasObservers() {
        return !this.l.isEmpty();
    }

    @CalledByNative
    public final void mediaSessionActionsChanged(int[] iArr) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i : iArr) {
            hashSet.add(Integer.valueOf(i));
        }
        this.r = hashSet;
        b.a aVar = this.m;
        aVar.c();
        while (aVar.hasNext()) {
            ((ke1) aVar.next()).a(hashSet);
        }
    }

    @CalledByNative
    public final void mediaSessionArtworkChanged(MediaImage[] mediaImageArr) {
        this.q = Arrays.asList(mediaImageArr);
        b.a aVar = this.m;
        aVar.c();
        while (aVar.hasNext()) {
            ((ke1) aVar.next()).b(this.q);
        }
    }

    @CalledByNative
    public final void mediaSessionDestroyed() {
        b.a aVar = this.m;
        aVar.c();
        while (aVar.hasNext()) {
            ((ke1) aVar.next()).c();
        }
        aVar.c();
        while (aVar.hasNext()) {
            ke1 ke1Var = (ke1) aVar.next();
            MediaSessionImpl mediaSessionImpl = ke1Var.a;
            if (mediaSessionImpl != null) {
                mediaSessionImpl.l.c(ke1Var);
                ke1Var.a = null;
            }
        }
        this.l.clear();
        this.k = 0L;
    }

    @CalledByNative
    public final void mediaSessionMetadataChanged(MediaMetadata mediaMetadata) {
        this.p = mediaMetadata;
        b.a aVar = this.m;
        aVar.c();
        while (aVar.hasNext()) {
            ((ke1) aVar.next()).d(mediaMetadata);
        }
    }

    @CalledByNative
    public final void mediaSessionPositionChanged(MediaPosition mediaPosition) {
        this.s = mediaPosition;
        b.a aVar = this.m;
        aVar.c();
        while (aVar.hasNext()) {
            ((ke1) aVar.next()).e(mediaPosition);
        }
    }

    @CalledByNative
    public final void mediaSessionStateChanged(boolean z, boolean z2) {
        this.n = z;
        this.o = Boolean.valueOf(z2);
        b.a aVar = this.m;
        aVar.c();
        while (aVar.hasNext()) {
            ((ke1) aVar.next()).f(z, z2);
        }
    }
}
