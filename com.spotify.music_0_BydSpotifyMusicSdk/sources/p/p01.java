package p;

import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContentInfo;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputContentInfo;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.protobuf.ByteString;
import com.spotify.automotive.baseui.login.AAMLoginScannableActivity;
import com.spotify.automotive.ubi.LoginUbiLogger$ErrorInfo;
import com.spotify.automotive.ubi.LoginUbiLogger$Page;
import com.spotify.mediasession.mediaitem.MediaBrowserItemsExtrasBuilder$IconHint;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;
import kotlin.NoWhenBranchMatchedException;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public class p01 implements eh1, nj1, j4, kp1, wo2, iz, r41, eq1, xp1, pp1, mn, zj, h00 {
    public static volatile p01 k;
    public final /* synthetic */ int c;
    public Object e;

    public /* synthetic */ p01(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    public final void A() {
        ((Bundle) this.e).putBoolean("com.spotify.music.extra.CAN_ADD_TO_COLLECTION", true);
    }

    public final void B(MediaBrowserItemsExtrasBuilder$IconHint mediaBrowserItemsExtrasBuilder$IconHint) {
        ((Bundle) this.e).putString("com.spotify.music.extra.COLLECTION_ICON_HINT", mediaBrowserItemsExtrasBuilder$IconHint.value);
    }

    public final void C(double d) {
        if (d < 0.0d || d > 1.0d) {
            return;
        }
        ((Bundle) this.e).putDouble("androidx.media.MediaItem.Extras.COMPLETION_PERCENTAGE", d);
    }

    public final void D(int i) {
        ((Bundle) this.e).putInt("android.media.browse.CONTENT_STYLE_BROWSABLE_HINT", i);
    }

    public final void E(int i) {
        ((Bundle) this.e).putInt("android.media.browse.CONTENT_STYLE_PLAYABLE_HINT", i);
    }

    public final void F(ArrayList arrayList) {
        ((Bundle) this.e).putStringArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ID_LIST", arrayList);
    }

    public final void G(long j) {
        if (j <= 0) {
            ((Bundle) this.e).remove("android.media.metadata.DURATION");
        } else {
            ((Bundle) this.e).putLong("android.media.metadata.DURATION", j);
        }
    }

    public final void H(String str) {
        if (TextUtils.isEmpty(str)) {
            ((Bundle) this.e).remove("android.media.browse.CONTENT_STYLE_GROUP_TITLE_HINT");
        } else {
            ((Bundle) this.e).putString("android.media.browse.CONTENT_STYLE_GROUP_TITLE_HINT", str);
        }
    }

    public final void I(boolean z) {
        ((Bundle) this.e).putLong("com.spotify.music.extra.IS_19_PLUS", z ? 1L : 0L);
    }

    public final void J(boolean z) {
        ((Bundle) this.e).putLong("android.media.IS_EXPLICIT", z ? 1L : 0L);
    }

    public final void K(int i, boolean z) {
        ((com.google.protobuf.h) this.e).S(i, z);
    }

    public final void L(int i, ByteString byteString) {
        ((com.google.protobuf.h) this.e).U(i, byteString);
    }

    public final void M(int i, double d) {
        com.google.protobuf.h hVar = (com.google.protobuf.h) this.e;
        hVar.getClass();
        hVar.Y(i, Double.doubleToRawLongBits(d));
    }

    public final void N(int i, int i2) {
        ((com.google.protobuf.h) this.e).a0(i, i2);
    }

    public final void O(int i, int i2) {
        ((com.google.protobuf.h) this.e).W(i, i2);
    }

    public final void P(int i, long j) {
        ((com.google.protobuf.h) this.e).Y(i, j);
    }

    public final void Q(float f, int i) {
        com.google.protobuf.h hVar = (com.google.protobuf.h) this.e;
        hVar.getClass();
        hVar.W(i, Float.floatToRawIntBits(f));
    }

    public final void R(int i, k72 k72Var, Object obj) {
        com.google.protobuf.h hVar = (com.google.protobuf.h) this.e;
        hVar.g0(i, 3);
        k72Var.e((wj1) obj, hVar.g);
        hVar.g0(i, 4);
    }

    public final void S(int i, int i2) {
        ((com.google.protobuf.h) this.e).a0(i, i2);
    }

    public final void T(int i, long j) {
        ((com.google.protobuf.h) this.e).j0(i, j);
    }

    public final void U(int i, k72 k72Var, Object obj) {
        ((com.google.protobuf.h) this.e).c0(i, (wj1) obj, k72Var);
    }

    public final void V(int i, int i2) {
        ((com.google.protobuf.h) this.e).W(i, i2);
    }

    public final void W(int i, long j) {
        ((com.google.protobuf.h) this.e).Y(i, j);
    }

    public final void X(int i, int i2) {
        ((com.google.protobuf.h) this.e).h0(i, (i2 >> 31) ^ (i2 << 1));
    }

    public final void Y(int i, long j) {
        ((com.google.protobuf.h) this.e).j0(i, (j >> 63) ^ (j << 1));
    }

    public final void Z(int i, int i2) {
        ((com.google.protobuf.h) this.e).h0(i, i2);
    }

    @Override // p.eh1
    public final PlaybackStateCompat a() {
        try {
            return ((android.support.v4.media.session.b) this.e).a();
        } catch (RemoteException e) {
            Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
            return null;
        }
    }

    public final void a0(int i, long j) {
        ((com.google.protobuf.h) this.e).j0(i, j);
    }

    @Override // p.nj1
    public final void b(si1 si1Var, boolean z) {
        if (si1Var instanceof qg2) {
            si1Var.k().c(false);
        }
        nj1 nj1Var = ((g4) this.e).m;
        if (nj1Var != null) {
            nj1Var.b(si1Var, z);
        }
    }

    @Override // p.zj
    public final void c() {
        qd1 qd1Var = ((AAMLoginScannableActivity) this.e).I;
        qd1Var.i.d();
        ((com.spotify.pinpairing.pinpairingimpl.c) qd1Var.e).c.remove(qd1Var);
    }

    @Override // p.h00
    public final Object convert(Object obj) {
        return Optional.ofNullable(((h00) this.e).convert((ResponseBody) obj));
    }

    @Override // p.xp1
    public final void d(Exception exc) {
        ((CountDownLatch) this.e).countDown();
    }

    @Override // p.iz
    public final ClipData e() {
        return ((ContentInfo) this.e).getClip();
    }

    @Override // p.mn
    public final void f(g30 g30Var) {
        this.e = g30Var;
        Log.isLoggable("FirebaseCrashlytics", 3);
    }

    @Override // p.pp1
    public final void g() {
        ((CountDownLatch) this.e).countDown();
    }

    @Override // p.r41
    public final ClipDescription getDescription() {
        return ((InputContentInfo) this.e).getDescription();
    }

    @Override // p.iz
    public final int getFlags() {
        return ((ContentInfo) this.e).getFlags();
    }

    @Override // p.r41
    public final Object h() {
        return (InputContentInfo) this.e;
    }

    @Override // p.kp1
    public final fx2 i(View view, fx2 fx2Var) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.e;
        if (!ap1.a(coordinatorLayout.v, fx2Var)) {
            coordinatorLayout.v = fx2Var;
            boolean z = fx2Var.d() > 0;
            coordinatorLayout.w = z;
            coordinatorLayout.setWillNotDraw(!z && coordinatorLayout.getBackground() == null);
            dx2 dx2Var = fx2Var.a;
            if (!dx2Var.m()) {
                int childCount = coordinatorLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = coordinatorLayout.getChildAt(i);
                    WeakHashMap weakHashMap = zt2.a;
                    if (it2.b(childAt) && ((m00) childAt.getLayoutParams()).a != null && dx2Var.m()) {
                        break;
                    }
                }
            }
            coordinatorLayout.requestLayout();
        }
        return fx2Var;
    }

    @Override // p.r41
    public final Uri j() {
        return ((InputContentInfo) this.e).getContentUri();
    }

    @Override // p.wo2
    public final int k(Object obj) {
        return ((aw0) obj).b;
    }

    @Override // p.wo2
    public final boolean l(Object obj) {
        return ((aw0) obj).c;
    }

    @Override // p.zj
    public final void m(yj yjVar) {
        AAMLoginScannableActivity aAMLoginScannableActivity = (AAMLoginScannableActivity) this.e;
        ad1 ad1Var = ((ek) yjVar).b.f;
        int i = AAMLoginScannableActivity.W;
        aAMLoginScannableActivity.getClass();
        new n(aAMLoginScannableActivity);
        com.spotify.pinpairing.pinpairingimpl.c cVar = new com.spotify.pinpairing.pinpairingimpl.c();
        qd1 qd1Var = new qd1(aAMLoginScannableActivity, new Handler(), ad1Var, new l0(aAMLoginScannableActivity), cVar, new y3(aAMLoginScannableActivity, 1), aAMLoginScannableActivity.U);
        aAMLoginScannableActivity.I = qd1Var;
        qd1Var.a();
        aAMLoginScannableActivity.I.c();
    }

    @Override // p.r41
    public final void n() {
        ((InputContentInfo) this.e).requestPermission();
    }

    @Override // p.r41
    public final Uri o() {
        return ((InputContentInfo) this.e).getLinkUri();
    }

    @Override // p.eq1
    public final void onSuccess(Object obj) {
        ((CountDownLatch) this.e).countDown();
    }

    @Override // p.eh1
    public final boolean p(KeyEvent keyEvent) {
        try {
            ((android.support.v4.media.session.b) this.e).f0(keyEvent);
            return false;
        } catch (RemoteException e) {
            Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", e);
            return false;
        }
    }

    @Override // p.iz
    public final ContentInfo q() {
        return (ContentInfo) this.e;
    }

    @Override // p.iz
    public final int r() {
        return ((ContentInfo) this.e).getSource();
    }

    @Override // p.nj1
    public final boolean s(si1 si1Var) {
        Object obj = this.e;
        if (si1Var == ((g4) obj).k) {
            return false;
        }
        ((qg2) si1Var).A.getClass();
        ((g4) obj).getClass();
        nj1 nj1Var = ((g4) this.e).m;
        if (nj1Var != null) {
            return nj1Var.s(si1Var);
        }
        return false;
    }

    public final String t(Object obj) {
        StringWriter stringWriter = new StringWriter();
        try {
            u(obj, stringWriter);
        } catch (IOException unused) {
        }
        return stringWriter.toString();
    }

    public final String toString() {
        switch (this.c) {
            case 6:
                return "ContentInfoCompat{" + ((ContentInfo) this.e) + "}";
            default:
                return super.toString();
        }
    }

    public final void u(Object obj, Writer writer) throws IOException {
        g71 g71Var = (g71) this.e;
        y71 y71Var = new y71(writer, g71Var.a, g71Var.b, g71Var.c, g71Var.d);
        y71Var.g(obj);
        y71Var.i();
        y71Var.b.flush();
    }

    public final void v(LoginUbiLogger$Page loginUbiLogger$Page, LoginUbiLogger$ErrorInfo loginUbiLogger$ErrorInfo) {
        mp2 mp2VarH;
        v03.j(loginUbiLogger$Page, "page");
        int iOrdinal = loginUbiLogger$Page.ordinal();
        if (iOrdinal == 0) {
            gj gjVar = new gj(LoginUbiLogger$Page.QR_CODE.a());
            switch (loginUbiLogger$ErrorInfo.ordinal()) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    mp2VarH = new v4(gjVar, loginUbiLogger$ErrorInfo.a()).H();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } else if (iOrdinal != 1) {
            mp2VarH = null;
            if (iOrdinal == 2) {
                zi ziVar = new zi(LoginUbiLogger$Page.PASSWORD.a());
                int iOrdinal2 = loginUbiLogger$ErrorInfo.ordinal();
                if (iOrdinal2 == 0 || iOrdinal2 == 1 || iOrdinal2 == 3 || iOrdinal2 == 4 || iOrdinal2 == 5 || iOrdinal2 == 6) {
                    mp2VarH = new v4(ziVar, loginUbiLogger$ErrorInfo.a()).H();
                }
            } else if (iOrdinal != 3) {
                throw new NoWhenBranchMatchedException();
            }
        } else {
            fj fjVar = new fj(LoginUbiLogger$Page.PIN.a());
            switch (loginUbiLogger$ErrorInfo.ordinal()) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    mp2VarH = new kk0(fjVar, loginUbiLogger$ErrorInfo.a()).u();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        if (mp2VarH != null) {
            ((tp2) ((ur2) this.e)).c(mp2VarH);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void w(com.spotify.automotive.ubi.LoginUbiLogger$Page r19, com.spotify.automotive.ubi.LoginUbiLogger$Target r20) {
        /*
            Method dump skipped, instruction units count: 1002
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.p01.w(com.spotify.automotive.ubi.LoginUbiLogger$Page, com.spotify.automotive.ubi.LoginUbiLogger$Target):void");
    }

    public final void x(LoginUbiLogger$Page loginUbiLogger$Page) {
        mp2 mp2Var;
        v03.j(loginUbiLogger$Page, "page");
        int iOrdinal = loginUbiLogger$Page.ordinal();
        if (iOrdinal == 0) {
            String strA = LoginUbiLogger$Page.QR_CODE.a();
            cp2 cp2Var = cp2.b;
            v03.j(cp2Var, "parentAbsoluteLocation");
            ip2 ip2Var = ip2.i;
            hp2 hp2VarM = qo.m();
            hp2VarM.h = "automotive";
            hp2VarM.a = "automotive-login-qrcode";
            hp2VarM.f = "3.0.0";
            hp2VarM.g = "15.3.0";
            hp2VarM.b = strA;
            ip2 ip2VarA = hp2VarM.a();
            lp2 lp2Var = new lp2();
            lp2Var.c(ip2VarA);
            lp2Var.b = cp2Var;
            mp2Var = (mp2) lp2Var.a();
        } else if (iOrdinal == 1) {
            String strA2 = LoginUbiLogger$Page.PIN.a();
            cp2 cp2Var2 = cp2.b;
            v03.j(cp2Var2, "parentAbsoluteLocation");
            ip2 ip2Var2 = ip2.i;
            hp2 hp2VarM2 = qo.m();
            hp2VarM2.h = "automotive";
            hp2VarM2.a = "automotive-login-pin";
            hp2VarM2.f = "2.0.0";
            hp2VarM2.g = "15.3.0";
            hp2VarM2.b = strA2;
            ip2 ip2VarA2 = hp2VarM2.a();
            lp2 lp2Var2 = new lp2();
            lp2Var2.c(ip2VarA2);
            lp2Var2.b = cp2Var2;
            mp2Var = (mp2) lp2Var2.a();
        } else if (iOrdinal == 2) {
            String strA3 = LoginUbiLogger$Page.PASSWORD.a();
            cp2 cp2Var3 = cp2.b;
            v03.j(cp2Var3, "parentAbsoluteLocation");
            ip2 ip2Var3 = ip2.i;
            hp2 hp2VarM3 = qo.m();
            hp2VarM3.h = "automotive";
            hp2VarM3.a = "automotive-login-password";
            hp2VarM3.f = "1.0.0";
            hp2VarM3.g = "15.3.0";
            hp2VarM3.b = strA3;
            ip2 ip2VarA3 = hp2VarM3.a();
            lp2 lp2Var3 = new lp2();
            lp2Var3.c(ip2VarA3);
            lp2Var3.b = cp2Var3;
            mp2Var = (mp2) lp2Var3.a();
        } else {
            if (iOrdinal != 3) {
                throw new NoWhenBranchMatchedException();
            }
            mp2Var = null;
        }
        if (mp2Var != null) {
            ((tp2) ((ur2) this.e)).c(mp2Var);
        }
    }

    public final void y(com.google.firebase.crashlytics.internal.settings.a aVar, Thread thread, Throwable th) {
        com.google.firebase.crashlytics.internal.common.a aVar2 = (com.google.firebase.crashlytics.internal.common.a) this.e;
        synchronized (aVar2) {
            try {
                Objects.toString(th);
                thread.getName();
                Log.isLoggable("FirebaseCrashlytics", 3);
                try {
                    try {
                        ks2.a(aVar2.e.y(new c30(aVar2, System.currentTimeMillis(), th, thread, aVar)));
                    } catch (Exception e) {
                        Log.e("FirebaseCrashlytics", "Error handling uncaught exception", e);
                    }
                } catch (TimeoutException unused) {
                    Log.e("FirebaseCrashlytics", "Cannot send reports. Timed out while fetching settings.", null);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public final void z(String str) {
        v03.j(str, "parentId");
        sg1 sg1Var = ((bh1) ((eg1) this.e)).c;
        sg1Var.a(str);
        sg1Var.d.n.post(new xf1(sg1Var, str, null, 4));
    }

    public /* synthetic */ p01() {
        this.c = 15;
        this.e = new CountDownLatch(1);
    }

    public p01(eg1 eg1Var) {
        this.c = 22;
        v03.j(eg1Var, "mbsCapabilities");
        this.e = eg1Var;
    }

    public p01(ur2 ur2Var) {
        this.c = 24;
        v03.j(ur2Var, "logger");
        this.e = ur2Var;
    }

    public p01(int i) {
        this.c = i;
        if (i != 18) {
            if (i != 25) {
                this.e = new HashSet();
            } else {
                this.e = new Bundle();
            }
        }
    }

    public p01(com.google.protobuf.h hVar) {
        this.c = 21;
        Charset charset = w51.a;
        if (hVar != null) {
            this.e = hVar;
            hVar.g = this;
            return;
        }
        throw new NullPointerException("output");
    }

    public p01(Object obj) {
        this.c = 7;
        this.e = (InputContentInfo) obj;
    }

    public p01(ContentInfo contentInfo) {
        this.c = 6;
        contentInfo.getClass();
        this.e = ez.f(contentInfo);
    }

    public p01(SwipeDismissBehavior swipeDismissBehavior) {
        this.c = 17;
        swipeDismissBehavior.getClass();
        swipeDismissBehavior.e = Math.min(Math.max(0.0f, 0.1f), 1.0f);
        swipeDismissBehavior.f = Math.min(Math.max(0.0f, 0.6f), 1.0f);
        swipeDismissBehavior.c = 0;
    }
}
