package org.chromium.chrome.browser.feed.webfeed;

import J.N;
import android.content.Context;
import defpackage.bi1;
import defpackage.c3;
import defpackage.l10;
import defpackage.oj2;
import defpackage.tp2;
import defpackage.ul0;
import defpackage.vq3;
import defpackage.wb;
import defpackage.wp2;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebFeedSnackbarController.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static final int f = (int) TimeUnit.SECONDS.toMillis(8);
    public final Context a;
    public final a b;
    public final wp2 c;
    public final org.chromium.chrome.browser.feed.webfeed.a d;
    public final ArrayList e = new ArrayList();

    /* JADX INFO: compiled from: WebFeedSnackbarController.java */
    @FunctionalInterface
    public interface a {
        void o();
    }

    /* JADX INFO: compiled from: WebFeedSnackbarController.java */
    public class b extends c {
        public final byte[] i;
        public final GURL j;
        public final String k;
        public final int l;
        public final int m;

        public b(byte[] bArr, GURL gurl, String str, int i, int i2) {
            super();
            this.i = bArr;
            this.j = gurl;
            this.k = str;
            this.l = i;
            this.m = i2;
        }

        @Override // wp2.b
        public final void j(Object obj) {
            j jVar = this.g;
            if (jVar != null) {
                this.e.t(jVar);
                this.g = null;
            }
            ul0.b().b.c(this);
            i.this.e.remove(this);
            N.MxULk9PS(0, this.l);
            byte[] bArr = this.i;
            boolean z = (bArr == null || bArr.length == 0) ? false : true;
            int i = this.m;
            if (z) {
                N.MtL148iF(bArr, false, i, new c3(23, this));
            } else {
                N.MztsaYnr(new WebFeedBridge$WebFeedPageInformation(this.e, this.j), i, new wb(29, this));
            }
        }
    }

    /* JADX INFO: compiled from: WebFeedSnackbarController.java */
    public class c implements wp2.b, ul0.a {
        public Tab e;
        public GURL f;
        public j g;

        public c() {
            ul0.b().b.a(this);
            i.this.e.add(this);
        }

        @Override // ul0.a
        public final void a() {
            i.this.c.a(this);
        }

        @Override // wp2.b
        public final void d(Object obj) {
            j jVar = this.g;
            if (jVar != null) {
                this.e.t(jVar);
                this.g = null;
            }
            ul0.b().b.c(this);
            i.this.e.remove(this);
        }
    }

    public i(Context context, a aVar, bi1 bi1Var, wp2 wp2Var) {
        this.a = context;
        this.b = aVar;
        this.c = wp2Var;
        this.d = new org.chromium.chrome.browser.feed.webfeed.a(bi1Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(Tab tab, WebFeedBridge$FollowResults webFeedBridge$FollowResults, byte[] bArr, GURL gurl, String str, int i) {
        int i2 = webFeedBridge$FollowResults.a;
        int i3 = 0;
        objArr = 0;
        Object[] objArr = 0;
        if (i2 == 1) {
            WebFeedBridge$WebFeedMetadata webFeedBridge$WebFeedMetadata = webFeedBridge$FollowResults.b;
            if (webFeedBridge$WebFeedMetadata != null) {
                b(webFeedBridge$WebFeedMetadata.b, webFeedBridge$WebFeedMetadata.e == 2);
                return;
            } else {
                b(str, false);
                return;
            }
        }
        int i4 = R$string.web_feed_follow_generic_failure_snackbar_message;
        if (i2 == 2) {
            i4 = R$string.web_feed_offline_failure_snackbar_message;
        }
        int i5 = i4;
        b bVar = new b(bArr, gurl, str, 34, i);
        if (((bArr != null && bArr.length != 0) == true || (tab != null && gurl.equals(tab.v()))) != false) {
            int i6 = R$string.web_feed_generic_failure_snackbar_action;
            if (bArr != null && bArr.length != 0) {
                objArr = 1;
            }
            if (objArr == 0) {
                bVar.f = gurl;
                bVar.e = tab;
                j jVar = new j(bVar);
                bVar.g = jVar;
                tab.x(jVar);
            }
            i3 = i6;
        }
        d(this.a.getString(i5), bVar, 40, i3);
    }

    public final void b(String str, boolean z) {
        if (!oj2.t(Profile.d()).shouldTriggerHelpUI("IPH_WebFeedPostFollowDialog")) {
            c(0, str);
            return;
        }
        Context context = this.a;
        org.chromium.chrome.browser.feed.webfeed.a aVar = this.d;
        aVar.getClass();
        aVar.a(context, new l10(13, this.b), R$string.web_feed_post_follow_dialog_go_to_following, str, z, true);
        vq3 vq3Var = aVar.a;
        vq3Var.a.j(vq3Var.b, 1, false);
    }

    public final void c(int i, String str) {
        d(this.a.getString(R$string.web_feed_follow_success_snackbar_message, str), new g(this, i), 39, i == 2 ? R$string.web_feed_follow_success_snackbar_action_refresh : R$string.web_feed_follow_success_snackbar_action_go_to_following);
    }

    public final void d(String str, c cVar, int i, int i2) {
        tp2 tp2VarA = tp2.a(str, cVar, 0, i);
        tp2VarA.i = false;
        tp2VarA.j = f;
        if (i2 != 0) {
            tp2VarA.d = this.a.getString(i2);
            tp2VarA.e = null;
        }
        this.c.c(tp2VarA);
    }

    public final void e(int i, byte[] bArr, GURL gurl, String str, int i2) {
        Context context = this.a;
        if (i == 1) {
            d(context.getString(R$string.web_feed_unfollow_success_snackbar_message, str), new b(bArr, gurl, str, 35, i2), 41, R$string.web_feed_unfollow_success_snackbar_action);
            return;
        }
        int i3 = R$string.web_feed_unfollow_generic_failure_snackbar_message;
        if (i == 2) {
            i3 = R$string.web_feed_offline_failure_snackbar_message;
        }
        d(context.getString(i3), new h(this, bArr, i2, gurl, str), 42, R$string.web_feed_generic_failure_snackbar_action);
    }
}
