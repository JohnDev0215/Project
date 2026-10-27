package defpackage;

import J.N;
import android.content.Context;
import defpackage.r12;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.video_tutorials.Tutorial;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: VideoPlayerMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class am3 implements r12.a {
    public final Context a;
    public final em3 b;
    public final PropertyModel c;
    public final k51 d;
    public final WebContents e;
    public Tutorial f;
    public final Callback<Tutorial> g;
    public final Runnable h;
    public final r12 i;
    public long j;

    public am3(Context context, PropertyModel propertyModel, em3 em3Var, k51 k51Var, WebContents webContents, r12 r12Var, c3 c3Var, aw awVar) {
        this.a = context;
        this.c = propertyModel;
        this.b = em3Var;
        this.d = k51Var;
        this.e = webContents;
        this.g = c3Var;
        this.h = awVar;
        this.i = r12Var;
        final int i = 0;
        propertyModel.l(bm3.a, false);
        propertyModel.l(bm3.b, false);
        d();
        propertyModel.p(bm3.k, new wl3(this, i));
        propertyModel.p(bm3.l, new Runnable(this) { // from class: xl3
            public final /* synthetic */ am3 f;

            {
                this.f = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i2 = i;
                am3 am3Var = this.f;
                switch (i2) {
                    case 0:
                        am3Var.getClass();
                        am3Var.c.l(bm3.b, true);
                        am3Var.d.a(am3Var.f.a, new zl3(am3Var, 1), new wl3(am3Var, 2));
                        n3.a1(am3Var.f.a, 0);
                        break;
                    default:
                        am3Var.e(am3Var.f);
                        break;
                }
            }
        });
        propertyModel.p(bm3.m, new yl3(this, i));
        propertyModel.p(bm3.n, new zl3(this, i));
        final int i2 = 1;
        propertyModel.p(bm3.o, new wl3(this, i2));
        propertyModel.p(bm3.i, new Runnable(this) { // from class: xl3
            public final /* synthetic */ am3 f;

            {
                this.f = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i22 = i2;
                am3 am3Var = this.f;
                switch (i22) {
                    case 0:
                        am3Var.getClass();
                        am3Var.c.l(bm3.b, true);
                        am3Var.d.a(am3Var.f.a, new zl3(am3Var, 1), new wl3(am3Var, 2));
                        n3.a1(am3Var.f.a, 0);
                        break;
                    default:
                        am3Var.e(am3Var.f);
                        break;
                }
            }
        });
    }

    @Override // r12.a
    public final void b() {
        n3.b1(this.f.a, 3);
        if (this.j != 0) {
            oj2.Y(System.currentTimeMillis() - this.j, "VideoTutorials.Player.LoadTimeLatency");
            this.j = 0L;
        }
        this.c.l(bm3.a, false);
        d();
    }

    @Override // r12.a
    public final void c() {
        n3.b1(this.f.a, 1);
        PropertyModel.n nVar = bm3.g;
        int i = this.f.a;
        em3 em3Var = this.b;
        boolean z = em3Var.a(i).size() > 1;
        PropertyModel propertyModel = this.c;
        propertyModel.l(nVar, z);
        em3Var.d(new n62(this.f, 20, new l51(1, this)));
        PropertyModel.n nVar2 = bm3.c;
        int i2 = this.f.a;
        propertyModel.l(nVar2, i2 == 4 || i2 == 5);
        propertyModel.p(bm3.p, r12.b.a.ENDED);
        PropertyModel.n nVar3 = bm3.d;
        sk skVar = bq.a;
        propertyModel.l(nVar3, N.M6bsIDpc("VideoTutorials", "enable_share", true));
        propertyModel.l(bm3.e, true);
        propertyModel.l(bm3.h, true);
    }

    public final void d() {
        PropertyModel.n nVar = bm3.c;
        PropertyModel propertyModel = this.c;
        propertyModel.l(nVar, false);
        propertyModel.l(bm3.f, false);
        propertyModel.l(bm3.g, false);
        PropertyModel.n nVar2 = bm3.d;
        sk skVar = bq.a;
        propertyModel.l(nVar2, N.M6bsIDpc("VideoTutorials", "enable_share", true));
        propertyModel.l(bm3.e, true);
        propertyModel.l(bm3.h, false);
    }

    public final void e(Tutorial tutorial) {
        r12 r12Var = this.i;
        r12Var.d = null;
        r12Var.e = false;
        r12Var.f = false;
        r12Var.c = new r12.b();
        n3.b1(this.f.a, 0);
        this.j = System.currentTimeMillis();
        this.f = tutorial;
        LoadUrlParams loadUrlParams = new LoadUrlParams("chrome-untrusted://video-tutorials/?video_url=" + tutorial.c + "&poster_url=" + tutorial.d + "&caption_url=" + tutorial.g, 0);
        loadUrlParams.r = true;
        this.e.m().b(loadUrlParams);
        this.c.l(bm3.a, false);
        d();
    }

    @Override // r12.a
    public final void onPause() {
        n3.b1(this.f.a, 2);
        PropertyModel.n nVar = bm3.f;
        PropertyModel propertyModel = this.c;
        propertyModel.l(nVar, false);
        propertyModel.l(bm3.g, false);
        PropertyModel.n nVar2 = bm3.c;
        int i = this.f.a;
        propertyModel.l(nVar2, i == 4 || i == 5);
        propertyModel.p(bm3.p, r12.b.a.PAUSED);
        PropertyModel.n nVar3 = bm3.d;
        sk skVar = bq.a;
        propertyModel.l(nVar3, N.M6bsIDpc("VideoTutorials", "enable_share", true));
        propertyModel.l(bm3.e, true);
        propertyModel.l(bm3.h, false);
    }

    @Override // r12.a
    public final void a() {
    }
}
