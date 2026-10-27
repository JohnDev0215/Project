package org.chromium.chrome.browser.feed.webfeed;

import J.N;
import android.app.Activity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.ar;
import defpackage.ar3;
import defpackage.bi1;
import defpackage.bq;
import defpackage.c01;
import defpackage.i20;
import defpackage.i7;
import defpackage.oj2;
import defpackage.qm0;
import defpackage.sk;
import defpackage.wb;
import defpackage.wd0;
import defpackage.wp2;
import defpackage.xe3;
import defpackage.z2;
import defpackage.zp1;
import java.util.concurrent.TimeUnit;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.prefs.PrefService;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebFeedFollowIntroController.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {
    public static final long m = TimeUnit.DAYS.toMillis(1);
    public static final /* synthetic */ int n = 0;
    public final wd0 a = new wd0(9);
    public final Activity b;
    public final i20 c;
    public final PrefService d;
    public final SharedPreferencesManager e;
    public final xe3 f;
    public final i g;
    public final ar3 h;
    public final zp1<Tab> i;
    public final f j;
    public final a k;
    public final long l;

    /* JADX INFO: compiled from: WebFeedFollowIntroController.java */
    public static class a {
        public final int a;
        public final int b;
        public final PrefService c;
        public C0085a d;

        /* JADX INFO: renamed from: org.chromium.chrome.browser.feed.webfeed.e$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: WebFeedFollowIntroController.java */
        public static class C0085a {
            public Tab a;
            public GURL b;
            public Callback<b> c;
        }

        public a(PrefService prefService) {
            this.c = prefService;
            sk skVar = bq.a;
            this.a = N.M37SqSAy("WebFeed", "intro-num-visit-min", 3);
            this.b = N.M37SqSAy("WebFeed", "intro-daily-visit-min", 3);
        }

        public final boolean a(C0085a c0085a) {
            return this.d == c0085a && c0085a.a.getUrl().equals(c0085a.b);
        }

        public final void b(C0085a c0085a, b bVar) {
            if (this.d == c0085a) {
                Callback<b> callback = c0085a.c;
                if (!a(c0085a)) {
                    bVar = null;
                }
                callback.onResult(bVar);
            }
        }
    }

    /* JADX INFO: compiled from: WebFeedFollowIntroController.java */
    public static class b {
        public byte[] a;
        public GURL b;
        public String c;
    }

    public e(AppCompatActivity appCompatActivity, i7 i7Var, z2 z2Var, ImageButton imageButton, c01 c01Var, bi1 bi1Var, wp2 wp2Var) {
        PrefService prefServiceK = oj2.k(Profile.d());
        this.d = prefServiceK;
        this.e = SharedPreferencesManager.getInstance();
        this.k = new a(prefServiceK);
        this.j = new f(appCompatActivity, i7Var, z2Var, imageButton, c01Var, bi1Var, wp2Var);
        this.b = appCompatActivity;
        this.i = z2Var;
        xe3 xe3VarT = oj2.t(Profile.d());
        this.f = xe3VarT;
        this.g = new i(appCompatActivity, c01Var, bi1Var, wp2Var);
        this.h = new ar3(appCompatActivity, i7Var, imageButton, xe3VarT, new qm0(3, this));
        TimeUnit timeUnit = TimeUnit.MINUTES;
        sk skVar = bq.a;
        this.l = timeUnit.toMillis(N.M37SqSAy("WebFeed", "intro-appearance-threshold-minutes", 15));
        this.c = new i20(z2Var, new org.chromium.chrome.browser.feed.webfeed.b(this), new wb(27, this));
    }

    public final void a(b bVar) {
        if (!this.d.a("webfeed_follow_intro_debug.enable")) {
            switch (this.a.e) {
                case 9:
                default:
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    SharedPreferencesManager sharedPreferencesManager = this.e;
                    sharedPreferencesManager.n(jCurrentTimeMillis, "Chrome.WebFeed.IntroLastShownTimeMs");
                    sharedPreferencesManager.n(jCurrentTimeMillis, ar.p.b(Base64.encodeToString(bVar.a, 0)));
                    String strB = ar.q.b(Base64.encodeToString(bVar.a, 0));
                    sharedPreferencesManager.n(sharedPreferencesManager.f(strB) + 1, strB);
                    break;
            }
        }
        Log.i("cr_WFFollowIntroCtrl", "Allowed intro: all requirements met");
    }
}
