package defpackage;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.view.ViewStub;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.widget.ViewResourceFrameLayout;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: OfflineIndicatorControllerV2.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mq1 {
    public final Context a;
    public final ft2 b;
    public final Handler c;
    public iq1 d;
    public zp1<Boolean> e;
    public final nw2<Boolean> f;
    public jq1 g;
    public final kq1 h;
    public final qm0 i;
    public final lq1 j;
    public Runnable k;
    public final kq1 l;
    public long m;
    public boolean n;
    public boolean o;
    public boolean p;
    public final oq1 q;

    /* JADX WARN: Type inference failed for: r0v4, types: [jq1] */
    /* JADX WARN: Type inference failed for: r3v12, types: [jq1, org.chromium.base.Callback] */
    /* JADX WARN: Type inference failed for: r3v13, types: [kq1] */
    /* JADX WARN: Type inference failed for: r3v9, types: [kq1] */
    public mq1(AppCompatActivity appCompatActivity, ft2 ft2Var, aq1 aq1Var, nw2 nw2Var) {
        if (iv.e().g("force-online-connection-state-for-indicator")) {
            return;
        }
        this.a = appCompatActivity;
        this.b = ft2Var;
        this.c = new Handler();
        this.q = new oq1();
        this.m = SystemClock.elapsedRealtime() - 5000;
        final int i = 0;
        iq1 iq1Var = new iq1(new ko0(19, this), new Callback(this) { // from class: jq1
            public final /* synthetic */ mq1 f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                Runnable runnable;
                int i2 = i;
                mq1 mq1Var = this.f;
                switch (i2) {
                    case 0:
                        mq1Var.getClass();
                        mq1Var.a(((Boolean) obj).booleanValue());
                        break;
                    default:
                        mq1Var.getClass();
                        if (!((Boolean) obj).booleanValue() && (runnable = mq1Var.k) != null) {
                            runnable.run();
                            mq1Var.k = null;
                            break;
                        }
                        break;
                }
            }
        });
        this.d = iq1Var;
        final int i2 = 1;
        a(iq1Var.n == 1);
        this.h = new Runnable(this) { // from class: kq1
            public final /* synthetic */ mq1 f;

            {
                this.f = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i3 = i;
                mq1 mq1Var = this.f;
                switch (i3) {
                    case 0:
                        mq1Var.getClass();
                        n3.X0("OfflineIndicator.Shown");
                        oq1 oq1Var = mq1Var.q;
                        if (!oq1Var.a) {
                            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            oq1Var.a = true;
                            oq1Var.c = jCurrentTimeMillis;
                            sharedPreferencesManager.n(jCurrentTimeMillis, "Chrome.OfflineIndicatorV2.WallTimeShownMs");
                            oq1Var.d = jCurrentTimeMillis;
                            sharedPreferencesManager.n(jCurrentTimeMillis, "Chrome.OfflineIndicatorV2.LastUpdateWallTimeMs");
                        }
                        mq1Var.m = SystemClock.elapsedRealtime();
                        int i4 = R$color.offline_indicator_offline_color;
                        Context context = mq1Var.a;
                        int color = context.getColor(i4);
                        int color2 = context.getColor(R$color.default_text_color_light);
                        Drawable drawable = context.getDrawable(R$drawable.ic_cloud_offline_24dp);
                        int color3 = context.getColor(R$color.default_icon_color_light);
                        String string = context.getString(R$string.offline_indicator_v2_offline_text);
                        ft2 ft2Var2 = mq1Var.b;
                        if (!ft2Var2.c) {
                            ft2Var2.c = true;
                            boolean z = ft2Var2.k;
                            qt2 qt2Var = ft2Var2.a;
                            if (!z) {
                                ViewResourceFrameLayout viewResourceFrameLayout = (ViewResourceFrameLayout) ((ViewStub) ft2Var2.i.findViewById(R$id.status_indicator_stub)).inflate();
                                int id = viewResourceFrameLayout.getId();
                                ft2Var2.e = id;
                                st2 st2Var = ft2Var2.b;
                                st2Var.g = id;
                                ft2Var2.f = viewResourceFrameLayout.getResourceAdapter();
                                jb1 jb1Var = new jb1(3, ft2Var2);
                                HashMap mapE = PropertyModel.e(rt2.k);
                                PropertyModel.p pVar = rt2.c;
                                int i5 = zj.a;
                                PropertyModel.d dVar = new PropertyModel.d();
                                dVar.a = 8;
                                mapE.put(pVar, dVar);
                                PropertyModel.n nVar = rt2.d;
                                PropertyModel.a aVar = new PropertyModel.a();
                                aVar.a = false;
                                mapE.put(nVar, aVar);
                                PropertyModel propertyModel = new PropertyModel(mapE);
                                j.b(propertyModel, new tt2(viewResourceFrameLayout, st2Var), new ag(11));
                                f52 f52Var = new f52(9, ft2Var2);
                                d92 d92Var = new d92(3, ft2Var2);
                                zx1 zx1Var = new zx1(12, viewResourceFrameLayout);
                                qt2Var.e = propertyModel;
                                qt2Var.k = f52Var;
                                qt2Var.l = d92Var;
                                qt2Var.n = jb1Var;
                                qt2Var.o = zx1Var;
                                qt2Var.h.b(qt2Var);
                                viewResourceFrameLayout.addOnLayoutChangeListener(qt2Var);
                                ft2Var2.d = new l91(ft2Var2, 27, viewResourceFrameLayout);
                                ft2Var2.k = true;
                            }
                            qt2Var.k.run();
                            qt2Var.v = false;
                            qt2Var.u = 0;
                            gt2 gt2Var = new gt2(qt2Var, string, drawable, color, color2, color3);
                            int iIntValue = qt2Var.i.get().intValue();
                            if (iIntValue != color) {
                                ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(iIntValue, color);
                                qt2Var.p = valueAnimatorOfInt;
                                valueAnimatorOfInt.setEvaluator(new ArgbEvaluator());
                                qt2Var.p.setInterpolator(h21.e);
                                qt2Var.p.setDuration(200L);
                                qt2Var.p.addUpdateListener(new ht2(0, qt2Var));
                                qt2Var.p.addListener(new kt2(qt2Var, gt2Var));
                                qt2Var.p.start();
                            } else {
                                gt2Var.run();
                            }
                            break;
                        }
                        break;
                    default:
                        boolean z2 = mq1Var.d.g;
                        if (z2 != mq1Var.n) {
                            mq1Var.b(z2);
                        }
                        break;
                }
            }
        };
        this.j = new lq1(this, 0);
        this.i = new qm0(12, this);
        this.e = aq1Var;
        this.f = nw2Var;
        ?? r3 = new Callback(this) { // from class: jq1
            public final /* synthetic */ mq1 f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                Runnable runnable;
                int i22 = i2;
                mq1 mq1Var = this.f;
                switch (i22) {
                    case 0:
                        mq1Var.getClass();
                        mq1Var.a(((Boolean) obj).booleanValue());
                        break;
                    default:
                        mq1Var.getClass();
                        if (!((Boolean) obj).booleanValue() && (runnable = mq1Var.k) != null) {
                            runnable.run();
                            mq1Var.k = null;
                            break;
                        }
                        break;
                }
            }
        };
        this.g = r3;
        aq1Var.p(r3);
        this.l = new Runnable(this) { // from class: kq1
            public final /* synthetic */ mq1 f;

            {
                this.f = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i3 = i2;
                mq1 mq1Var = this.f;
                switch (i3) {
                    case 0:
                        mq1Var.getClass();
                        n3.X0("OfflineIndicator.Shown");
                        oq1 oq1Var = mq1Var.q;
                        if (!oq1Var.a) {
                            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            oq1Var.a = true;
                            oq1Var.c = jCurrentTimeMillis;
                            sharedPreferencesManager.n(jCurrentTimeMillis, "Chrome.OfflineIndicatorV2.WallTimeShownMs");
                            oq1Var.d = jCurrentTimeMillis;
                            sharedPreferencesManager.n(jCurrentTimeMillis, "Chrome.OfflineIndicatorV2.LastUpdateWallTimeMs");
                        }
                        mq1Var.m = SystemClock.elapsedRealtime();
                        int i4 = R$color.offline_indicator_offline_color;
                        Context context = mq1Var.a;
                        int color = context.getColor(i4);
                        int color2 = context.getColor(R$color.default_text_color_light);
                        Drawable drawable = context.getDrawable(R$drawable.ic_cloud_offline_24dp);
                        int color3 = context.getColor(R$color.default_icon_color_light);
                        String string = context.getString(R$string.offline_indicator_v2_offline_text);
                        ft2 ft2Var2 = mq1Var.b;
                        if (!ft2Var2.c) {
                            ft2Var2.c = true;
                            boolean z = ft2Var2.k;
                            qt2 qt2Var = ft2Var2.a;
                            if (!z) {
                                ViewResourceFrameLayout viewResourceFrameLayout = (ViewResourceFrameLayout) ((ViewStub) ft2Var2.i.findViewById(R$id.status_indicator_stub)).inflate();
                                int id = viewResourceFrameLayout.getId();
                                ft2Var2.e = id;
                                st2 st2Var = ft2Var2.b;
                                st2Var.g = id;
                                ft2Var2.f = viewResourceFrameLayout.getResourceAdapter();
                                jb1 jb1Var = new jb1(3, ft2Var2);
                                HashMap mapE = PropertyModel.e(rt2.k);
                                PropertyModel.p pVar = rt2.c;
                                int i5 = zj.a;
                                PropertyModel.d dVar = new PropertyModel.d();
                                dVar.a = 8;
                                mapE.put(pVar, dVar);
                                PropertyModel.n nVar = rt2.d;
                                PropertyModel.a aVar = new PropertyModel.a();
                                aVar.a = false;
                                mapE.put(nVar, aVar);
                                PropertyModel propertyModel = new PropertyModel(mapE);
                                j.b(propertyModel, new tt2(viewResourceFrameLayout, st2Var), new ag(11));
                                f52 f52Var = new f52(9, ft2Var2);
                                d92 d92Var = new d92(3, ft2Var2);
                                zx1 zx1Var = new zx1(12, viewResourceFrameLayout);
                                qt2Var.e = propertyModel;
                                qt2Var.k = f52Var;
                                qt2Var.l = d92Var;
                                qt2Var.n = jb1Var;
                                qt2Var.o = zx1Var;
                                qt2Var.h.b(qt2Var);
                                viewResourceFrameLayout.addOnLayoutChangeListener(qt2Var);
                                ft2Var2.d = new l91(ft2Var2, 27, viewResourceFrameLayout);
                                ft2Var2.k = true;
                            }
                            qt2Var.k.run();
                            qt2Var.v = false;
                            qt2Var.u = 0;
                            gt2 gt2Var = new gt2(qt2Var, string, drawable, color, color2, color3);
                            int iIntValue = qt2Var.i.get().intValue();
                            if (iIntValue != color) {
                                ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(iIntValue, color);
                                qt2Var.p = valueAnimatorOfInt;
                                valueAnimatorOfInt.setEvaluator(new ArgbEvaluator());
                                qt2Var.p.setInterpolator(h21.e);
                                qt2Var.p.setDuration(200L);
                                qt2Var.p.addUpdateListener(new ht2(0, qt2Var));
                                qt2Var.p.addListener(new kt2(qt2Var, gt2Var));
                                qt2Var.p.start();
                            } else {
                                gt2Var.run();
                            }
                            break;
                        }
                        break;
                    default:
                        boolean z2 = mq1Var.d.g;
                        if (z2 != mq1Var.n) {
                            mq1Var.b(z2);
                        }
                        break;
                }
            }
        };
    }

    public final void a(boolean z) {
        if (this.p == z) {
            return;
        }
        oq1 oq1Var = this.q;
        if (z) {
            if (oq1Var.a) {
                oq1Var.b();
            }
            oq1Var.b = true;
        } else {
            if (oq1Var.a) {
                oq1Var.c();
                SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                if (!sharedPreferencesManager.contains("Chrome.OfflineIndicatorV2.FirstTimeInForegroundMs")) {
                    long j = oq1Var.e;
                    oq1Var.g = j;
                    sharedPreferencesManager.n(j, "Chrome.OfflineIndicatorV2.FirstTimeInForegroundMs");
                }
                int i = oq1Var.h + 1;
                oq1Var.h = i;
                sharedPreferencesManager.m(i, "Chrome.OfflineIndicatorV2.NumTimesBackgrounded");
            }
            oq1Var.b = false;
        }
        this.p = z;
    }

    public final void b(boolean z) {
        this.n = z;
        if (!this.o) {
            oq1 oq1Var = this.q;
            if (oq1Var.a && !z) {
                oq1Var.a();
            }
        }
        int i = 1;
        if (!this.o && !z) {
            this.o = true;
            return;
        }
        this.o = true;
        boolean zBooleanValue = this.e.get().booleanValue();
        nw2<Boolean> nw2Var = this.f;
        Runnable runnable = this.i;
        kq1 kq1Var = this.h;
        if (!zBooleanValue) {
            if (z) {
                runnable = kq1Var;
            }
            runnable.run();
            i = nw2Var.get().booleanValue() ? 0 : 2;
        } else {
            if ((!z && this.k == kq1Var) || (z && this.k == runnable)) {
                this.k = null;
                return;
            }
            if (z) {
                runnable = kq1Var;
            }
            this.k = runnable;
            if (!nw2Var.get().booleanValue()) {
                i = 3;
            }
        }
        oj2.U(i, 4, "OfflineIndicator.ConnectivityChanged.DeviceState.".concat(z ? "Offline" : "Online"));
    }
}
