package f4;

import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.text.TextUtils;

/* JADX INFO: compiled from: CurrentMediaInfoManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private MediaMetadataCompat f6357b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    i3.m f6358c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6356a = "CurrentMediaInfoManager";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile boolean f6359d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Object f6360e = new Object();

    /* JADX INFO: compiled from: CurrentMediaInfoManager.java */
    class a extends MediaControllerCompat.a {
        a() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            super.d(mediaMetadataCompat);
            b.this.f6357b = mediaMetadataCompat;
            h3.i.b("CurrentMediaInfoManager").b("onMetadataChanged  isMediaInvalid " + b.this.e(), new Object[0]);
        }
    }

    public b(i3.m mVar) {
        this.f6358c = mVar;
        mVar.h(new a());
        i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        MediaMetadataCompat mediaMetadataCompat = this.f6357b;
        return mediaMetadataCompat == null || TextUtils.isEmpty(mediaMetadataCompat.m("android.media.metadata.MEDIA_URI"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        for (int i7 = 0; i7 <= 10; i7++) {
            try {
                this.f6357b = this.f6358c.n();
                boolean zE = e();
                h3.i.b("CurrentMediaInfoManager").b(i7 + "  isMediaInvalid " + zE, new Object[0]);
                if (zE && i7 < 10) {
                    Thread.sleep(100L);
                }
                g();
                return;
            } catch (Exception e7) {
                h3.i.b("CurrentMediaInfoManager").e(e7, "request data", new Object[0]);
                g();
                return;
            }
        }
    }

    private void g() {
        synchronized (this.f6360e) {
            this.f6359d = false;
            h3.i.b("CurrentMediaInfoManager").b("load data finished! notify", new Object[0]);
            this.f6360e.notifyAll();
        }
    }

    private void h() {
        synchronized (this.f6360e) {
            h3.i.b("CurrentMediaInfoManager").b("set Data Loading", new Object[0]);
            while (this.f6359d) {
                try {
                    h3.i.b("CurrentMediaInfoManager").b("current is loaded data, waiting...", new Object[0]);
                    this.f6360e.wait();
                    h3.i.b("CurrentMediaInfoManager").b("waited by notify", new Object[0]);
                } catch (InterruptedException e7) {
                    h3.i.b("CurrentMediaInfoManager").e(e7, "setDataLoading", new Object[0]);
                }
            }
            this.f6359d = true;
        }
    }

    private void i() {
        h();
        e5.a.b().b(new Runnable() { // from class: f4.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f6355e.f();
            }
        });
    }

    private void j() {
        synchronized (this.f6360e) {
            h3.i.b("CurrentMediaInfoManager").b("try Wait Data Loaded", new Object[0]);
            while (this.f6359d) {
                try {
                    h3.i.b("CurrentMediaInfoManager").b("current is loaded data, waiting...", new Object[0]);
                    this.f6360e.wait();
                    h3.i.b("CurrentMediaInfoManager").b("waited by notify", new Object[0]);
                } catch (InterruptedException e7) {
                    h3.i.b("CurrentMediaInfoManager").e(e7, "tryWaitDataLoaded", new Object[0]);
                }
            }
        }
    }

    public MediaMetadataCompat d() {
        h3.i.b("CurrentMediaInfoManager").b("getCurrentMedia", new Object[0]);
        if (e()) {
            j();
        }
        h3.i.b("CurrentMediaInfoManager").b("return CurrentMedia", new Object[0]);
        return this.f6357b;
    }
}
