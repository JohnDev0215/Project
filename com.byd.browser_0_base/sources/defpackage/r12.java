package defpackage;

import android.os.SystemClock;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.services.media_session.MediaPosition;

/* JADX INFO: compiled from: PlaybackStateObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r12 extends ke1 {
    public final nw2<a> b;
    public b c;
    public MediaPosition d;
    public boolean e;
    public boolean f;

    /* JADX INFO: compiled from: PlaybackStateObserver.java */
    public interface a {
        void a();

        void b();

        void c();

        void onPause();
    }

    /* JADX INFO: compiled from: PlaybackStateObserver.java */
    public static class b {
        public a a = a.INITIAL;
        public long b;
        public long c;

        /* JADX INFO: compiled from: PlaybackStateObserver.java */
        public enum a {
            INITIAL,
            PLAYING,
            PAUSED,
            ENDED,
            /* JADX INFO: Fake field, exist only in values array */
            ERROR
        }
    }

    public r12(MediaSessionImpl mediaSessionImpl, yo yoVar) {
        super(mediaSessionImpl);
        this.c = new b();
        this.b = yoVar;
    }

    public static long g(MediaPosition mediaPosition) {
        if (mediaPosition == null) {
            return 0L;
        }
        long jMin = Math.min((long) ((mediaPosition.c.floatValue() * (SystemClock.elapsedRealtime() - mediaPosition.d.longValue())) + mediaPosition.b.longValue()), mediaPosition.a());
        return mediaPosition.a() - jMin < 100 ? mediaPosition.a() : jMin;
    }

    @Override // defpackage.ke1
    public final void e(MediaPosition mediaPosition) {
        if (mediaPosition != null) {
            this.c.b = mediaPosition.a();
        }
        h(mediaPosition);
        this.c.c = g(mediaPosition == null ? this.d : mediaPosition);
        this.d = mediaPosition;
    }

    @Override // defpackage.ke1
    public final void f(boolean z, boolean z2) {
        this.e = z;
        this.f = z2;
        h(this.d);
    }

    public final void h(MediaPosition mediaPosition) {
        b.a aVar = this.c.a;
        if (this.e) {
            aVar = this.f ? b.a.PAUSED : b.a.PLAYING;
        } else if (mediaPosition == null) {
            MediaPosition mediaPosition2 = this.d;
            if (mediaPosition2 == null) {
                aVar = b.a.INITIAL;
            } else if (mediaPosition2.a() == g(this.d)) {
                aVar = b.a.ENDED;
            }
        }
        b bVar = this.c;
        if (aVar == bVar.a) {
            return;
        }
        bVar.a = aVar;
        int iOrdinal = aVar.ordinal();
        if (iOrdinal != 0) {
            nw2<a> nw2Var = this.b;
            if (iOrdinal == 1) {
                nw2Var.get().b();
                return;
            }
            if (iOrdinal == 2) {
                nw2Var.get().onPause();
            } else if (iOrdinal == 3) {
                nw2Var.get().c();
            } else {
                if (iOrdinal != 4) {
                    return;
                }
                nw2Var.get().a();
            }
        }
    }
}
