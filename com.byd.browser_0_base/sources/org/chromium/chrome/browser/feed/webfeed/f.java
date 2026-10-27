package org.chromium.chrome.browser.feed.webfeed;

import android.app.Activity;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.ar3;
import defpackage.bi1;
import defpackage.c01;
import defpackage.i7;
import defpackage.lk3;
import defpackage.nw2;
import defpackage.wp2;
import defpackage.wz;
import defpackage.z2;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: WebFeedRecommendationFollowAcceleratorController.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public final Activity a;
    public final ar3 b;
    public final nw2<Tab> c;
    public final i d;

    /* JADX INFO: compiled from: WebFeedRecommendationFollowAcceleratorController.java */
    public static class a implements lk3 {
        public final byte[] e;

        public a(byte[] bArr) {
            this.e = bArr;
        }
    }

    public f(AppCompatActivity appCompatActivity, i7 i7Var, z2 z2Var, ImageButton imageButton, c01 c01Var, bi1 bi1Var, wp2 wp2Var) {
        this.a = appCompatActivity;
        this.c = z2Var;
        this.d = new i(appCompatActivity, c01Var, bi1Var, wp2Var);
        this.b = new ar3(appCompatActivity, i7Var, imageButton, null, new wz(9));
    }
}
