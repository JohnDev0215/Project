package l3;

import android.content.Context;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import i3.m;

/* JADX INFO: compiled from: ClusterManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Context f7949a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final m f7950b;

    /* JADX INFO: compiled from: ClusterManager.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ int f7951e;

        a(int i7) {
            this.f7951e = i7;
        }

        @Override // java.lang.Runnable
        public void run() {
            PlaybackStateCompat playbackStateCompatQ = b.this.f7950b.q();
            if (playbackStateCompatQ == null || playbackStateCompatQ.t() != 3) {
                return;
            }
            b.this.i(this.f7951e);
        }
    }

    /* JADX INFO: renamed from: l3.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ClusterManager.java */
    class C0116b extends MediaControllerCompat.a {
        C0116b() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            super.d(mediaMetadataCompat);
            if (mediaMetadataCompat != null) {
                b.this.f();
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            super.e(playbackStateCompat);
            if (playbackStateCompat != null) {
                int iT = playbackStateCompat.t();
                if (iT != 0) {
                    if (iT == 1) {
                        b.this.e();
                        return;
                    } else if (iT != 2) {
                        if (iT == 3) {
                            b.this.d();
                            return;
                        } else if (iT != 6 && iT != 7) {
                            return;
                        }
                    }
                }
                b.this.c();
            }
        }
    }

    public b(Context context, m mVar) {
        this.f7949a = context.getApplicationContext();
        this.f7950b = mVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i7) {
        e5.a.b().b(new a(i7));
    }

    private void l() {
        this.f7950b.i(new m.e() { // from class: l3.a
            @Override // i3.m.e
            public final void a(int i7) {
                this.f7948a.b(i7);
            }
        });
        this.f7950b.h(new C0116b());
    }

    protected void c() {
        g();
    }

    protected void d() {
        h();
        f();
        j();
    }

    protected void e() {
        k();
    }

    protected abstract void f();

    protected abstract void g();

    protected abstract void h();

    protected abstract void i(int i7);

    protected abstract void j();

    protected abstract void k();

    public void m() {
        l();
    }
}
