package org.chromium.content.browser;

import defpackage.SD;
import defpackage.Ws;
import defpackage.Xs;
import defpackage.Ys;
import java.util.Arrays;
import java.util.HashSet;
import org.chromium.services.media_session.MediaImage;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MediaSessionImpl extends SD {
    public Ys a;
    public Xs b;

    public MediaSessionImpl(long j) {
        Ys ys = new Ys();
        this.a = ys;
        this.b = ys.h();
    }

    public static MediaSessionImpl create(long j) {
        return new MediaSessionImpl(j);
    }

    private boolean hasObservers() {
        return !(this.a.u == 0);
    }

    private void mediaSessionActionsChanged(int[] iArr) {
        HashSet hashSet = new HashSet();
        for (int i : iArr) {
            hashSet.add(Integer.valueOf(i));
        }
        ((Ws) this.b).b();
        if (this.b.hasNext()) {
            throw null;
        }
    }

    private void mediaSessionArtworkChanged(MediaImage[] mediaImageArr) {
        Arrays.asList(mediaImageArr);
        ((Ws) this.b).b();
        if (this.b.hasNext()) {
            throw null;
        }
    }

    private void mediaSessionDestroyed() {
        ((Ws) this.b).b();
        if (this.b.hasNext()) {
            throw null;
        }
        ((Ws) this.b).b();
        if (this.b.hasNext()) {
            throw null;
        }
        this.a.clear();
    }

    private void mediaSessionMetadataChanged(MediaMetadata mediaMetadata) {
        ((Ws) this.b).b();
        if (this.b.hasNext()) {
            throw null;
        }
    }

    private void mediaSessionPositionChanged(MediaPosition mediaPosition) {
        ((Ws) this.b).b();
        if (this.b.hasNext()) {
            throw null;
        }
    }

    private void mediaSessionStateChanged(boolean z, boolean z2) {
        ((Ws) this.b).b();
        if (this.b.hasNext()) {
            throw null;
        }
    }
}
