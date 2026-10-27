package org.chromium.chrome.browser.feed;

import J.N;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.protobuf.d0;
import defpackage.a8;
import defpackage.al0;
import defpackage.am0;
import defpackage.aq1;
import defpackage.bq;
import defpackage.c01;
import defpackage.c3;
import defpackage.fa1;
import defpackage.ji;
import defpackage.l10;
import defpackage.oj2;
import defpackage.on2;
import defpackage.pq2;
import defpackage.qm0;
import defpackage.rh3;
import defpackage.sk;
import defpackage.tf2;
import defpackage.wp2;
import defpackage.xl0;
import defpackage.ya0;
import defpackage.yk0;
import defpackage.yl0;
import defpackage.z7;
import defpackage.z8;
import defpackage.zl0;
import defpackage.zv0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.feed.FeedServiceBridge;
import org.chromium.chrome.browser.feed.FeedStream;
import org.chromium.chrome.browser.feed.NativeViewListRenderer;
import org.chromium.chrome.browser.feed.a;
import org.chromium.chrome.browser.feed.f;
import org.chromium.chrome.browser.feed.g;
import org.chromium.chrome.browser.feed.webfeed.i;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class FeedStream implements org.chromium.chrome.browser.feed.g {
    public boolean A;
    public long B;
    public f.c D;
    public final ji E;
    public final int a;
    public final Activity b;
    public final long c;
    public final int e;
    public final wp2 f;
    public final WindowAndroid g;
    public final g h;
    public yk0 i;
    public final g.b j;
    public final i k;
    public int m;
    public final int n;
    public boolean o;
    public final org.chromium.chrome.browser.feed.b q;
    public org.chromium.chrome.browser.feed.a r;
    public final f s;
    public final HashMap t;
    public final e u;
    public final FeedReliabilityLoggingBridge v;
    public RecyclerView w;
    public org.chromium.chrome.browser.feed.f x;
    public zv0 y;
    public int z;
    public final org.chromium.base.b<g.a> d = new org.chromium.base.b<>();
    public final c l = new c();
    public final d p = new d();
    public final ArrayList<wp2.b> C = new ArrayList<>();

    public class a {
    }

    public class b {
    }

    public class c {
        public final aq1<Boolean> a;

        public class a implements Callback<Boolean> {
            public final Runnable e;

            public a(z8 z8Var) {
                this.e = z8Var;
                c.this.a.p(this);
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Boolean bool) {
                if (bool.booleanValue()) {
                    return;
                }
                PostTask.c(rh3.a, this.e);
                c.this.a.d(this);
            }
        }

        public c() {
            new HashSet();
            aq1<Boolean> aq1Var = new aq1<>();
            this.a = aq1Var;
            aq1Var.r(Boolean.FALSE);
        }
    }

    public class d extends RecyclerView.g {
        public d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void d(int i, int i2) {
            FeedStream.this.getClass();
        }
    }

    public class e implements ya0.a {
        public e() {
        }

        @Override // ya0.a
        public final void w(int i) {
            FeedStream.this.getClass();
        }
    }

    public class f extends tf2 {
        public f() {
        }
    }

    public class g extends FeedServiceBridge.UnreadContentObserver {
        public final aq1<Boolean> b;

        public g() {
            aq1<Boolean> aq1Var = new aq1<>();
            this.b = aq1Var;
            aq1Var.r(Boolean.FALSE);
        }

        @Override // org.chromium.chrome.browser.feed.FeedServiceBridge.UnreadContentObserver
        public final void hasUnreadContentChanged(boolean z) {
            this.b.r(Boolean.valueOf(z));
        }
    }

    public class h implements a.InterfaceC0083a {
        public h() {
        }
    }

    public FeedStream(Activity activity, wp2 wp2Var, ji jiVar, boolean z, WindowAndroid windowAndroid, int i, yk0 yk0Var, g.b bVar, on2 on2Var) {
        this.b = activity;
        this.e = i;
        FeedReliabilityLoggingBridge feedReliabilityLoggingBridge = new FeedReliabilityLoggingBridge();
        this.v = feedReliabilityLoggingBridge;
        long j = feedReliabilityLoggingBridge.a;
        if (i == 3) {
            this.c = N.MfPuO5qK(this, (byte[]) on2Var.b, j, on2Var.a);
        } else {
            this.c = N.MIFFwCrU(this, i, j);
        }
        this.E = jiVar;
        this.f = wp2Var;
        this.A = z;
        this.g = windowAndroid;
        this.u = new e();
        this.i = yk0Var;
        this.j = bVar;
        this.k = new i(activity, i == 2 ? new c01(8, this) : new pq2(13, this), windowAndroid.n(), wp2Var);
        HashMap map = new HashMap();
        this.t = map;
        map.put("GeneralActions", new b());
        map.put("FeedActions", new a());
        this.a = N.M6BYCIpb();
        h(new g.a() { // from class: kl0
            @Override // org.chromium.chrome.browser.feed.g.a
            public final void a(ArrayList arrayList) {
                FeedStream feedStream = this.a;
                boolean z2 = feedStream.A;
                if (z2 && z2 && feedStream.x != null) {
                    ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(feedStream.w.getBackground(), PropertyValuesHolder.ofInt("alpha", 255));
                    objectAnimatorOfPropertyValuesHolder.setTarget(feedStream.w.getBackground());
                    objectAnimatorOfPropertyValuesHolder.setDuration(feedStream.w.getItemAnimator().c).setInterpolator(h21.f);
                    objectAnimatorOfPropertyValuesHolder.start();
                    feedStream.A = false;
                }
            }
        });
        this.s = new f();
        this.n = N.M4qKtJTt();
        this.q = new org.chromium.chrome.browser.feed.b(this);
        if (i == 2) {
            this.h = new g();
        }
    }

    @Override // org.chromium.chrome.browser.feed.g
    public final int a() {
        return this.e;
    }

    @Override // org.chromium.chrome.browser.feed.g
    public final void b(RecyclerView recyclerView, org.chromium.chrome.browser.feed.f fVar, z7 z7Var, zv0 zv0Var, al0 al0Var, int i) {
        long j = this.c;
        N.MI3u$s7A(j, this);
        System.nanoTime();
        this.v.b = al0Var;
        HashMap map = this.t;
        HashMap map2 = fVar.c;
        map2.clear();
        map2.putAll(map);
        org.chromium.chrome.browser.feed.a aVar = new org.chromium.chrome.browser.feed.a(recyclerView, this.b, fVar, ((NativeViewListRenderer) zv0Var).j, new h());
        this.r = aVar;
        aVar.f.getViewTreeObserver().addOnPreDrawListener(aVar);
        aVar.m = 0L;
        recyclerView.addOnScrollListener(this.q);
        recyclerView.getAdapter().A(this.p);
        this.w = recyclerView;
        this.x = fVar;
        this.y = zv0Var;
        this.z = i;
        ya0 ya0Var = this.g.h;
        if (ya0Var != null) {
            ya0Var.a.put(this.u, null);
        }
        if (this.A) {
            this.w.getBackground().setAlpha(0);
        }
        N.M5EFM3b8(j, this);
    }

    @Override // org.chromium.chrome.browser.feed.g
    public final void c(g.a aVar) {
        this.d.c(aVar);
    }

    @Override // org.chromium.chrome.browser.feed.g
    public final void d(int i) {
        this.z = i;
    }

    @Override // org.chromium.chrome.browser.feed.g
    public final void destroy() {
        g gVar = this.h;
        if (gVar != null) {
            N.MVfuS_0P(gVar.a);
            gVar.a = 0L;
        }
        N.MFvLOb3J(this.v.a);
    }

    @Override // org.chromium.chrome.browser.feed.g
    public final void e(c3 c3Var) {
        Iterator<wp2.b> it = this.C.iterator();
        while (it.hasNext()) {
            this.f.a(it.next());
        }
        z8 z8Var = new z8(this, 14, c3Var);
        c cVar = this.l;
        if (cVar.a.f.booleanValue()) {
            cVar.new a(z8Var);
        } else {
            PostTask.c(rh3.a, z8Var);
        }
    }

    @Override // org.chromium.chrome.browser.feed.g
    public final boolean f() {
        sk skVar = bq.a;
        return N.M09VlOh_("WebFeedSort") && this.e == 2;
    }

    @Override // org.chromium.chrome.browser.feed.g
    public final void g(boolean z) {
        ArrayList<wp2.b> arrayList = this.C;
        Iterator<wp2.b> it = arrayList.iterator();
        while (it.hasNext()) {
            this.f.a(it.next());
        }
        arrayList.clear();
        final i iVar = this.k;
        iVar.getClass();
        new ArrayList(iVar.e).forEach(new Consumer() { // from class: ir3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                iVar.c.a((wp2.b) obj);
            }
        });
        org.chromium.chrome.browser.feed.a aVar = this.r;
        RecyclerView recyclerView = aVar.f;
        if (recyclerView != null && recyclerView.getViewTreeObserver().isAlive()) {
            aVar.f.getViewTreeObserver().removeOnPreDrawListener(aVar);
        }
        aVar.c();
        aVar.f = null;
        aVar.k = null;
        aVar.g = null;
        aVar.l = null;
        aVar.h = null;
        this.r = null;
        this.m = 0;
        this.s.a();
        ArrayList<f.b> arrayList2 = new ArrayList<>();
        if (z) {
            if (this.D == null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                Activity activity = this.b;
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                FrameLayout frameLayout = new FrameLayout(activity);
                this.D = new f.c(j(), frameLayout, "Spacer");
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, displayMetrics.heightPixels));
            }
            arrayList2.add(this.D);
        }
        n(arrayList2);
        org.chromium.chrome.browser.feed.f fVar = this.x;
        HashMap map = new HashMap();
        HashMap map2 = fVar.c;
        map2.clear();
        map2.putAll(map);
        this.x = null;
        this.w.removeOnScrollListener(this.q);
        this.w.getAdapter().C(this.p);
        this.w = null;
        ya0 ya0Var = this.g.h;
        if (ya0Var != null) {
            ya0Var.a.remove(this.u);
        }
        N.Mbe8Jcnk(this.c, this);
    }

    @Override // org.chromium.chrome.browser.feed.g
    public final void h(g.a aVar) {
        this.d.a(aVar);
    }

    public final String i() {
        return String.valueOf(this.B);
    }

    public final int j() {
        return this.b.getResources().getDimensionPixelSize(R$dimen.ntp_header_lateral_paddings_v2);
    }

    public final aq1 k() {
        g gVar = this.h;
        if (gVar != null) {
            return gVar.b;
        }
        aq1 aq1Var = new aq1();
        aq1Var.r(Boolean.FALSE);
        return aq1Var;
    }

    public final boolean l(int i) {
        RecyclerView.m layoutManager;
        int iD;
        int iD2;
        int i2;
        RecyclerView recyclerView = this.w;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (iD = layoutManager.D()) < 0 || iD < (iD2 = ((NativeViewListRenderer) this.y).j.d()) || (i2 = iD - iD2) > i) {
            return false;
        }
        if (!this.o) {
            this.o = true;
            int i3 = this.e - 1;
            oj2.P(iD, a8.q[i3]);
            oj2.Q(i2, a8.r[i3]);
            PostTask.c(rh3.a, new qm0(2, this));
        }
        return true;
    }

    public final boolean m(z7 z7Var) {
        if (z7Var == null) {
            return true;
        }
        z7Var.getClass();
        z7Var.getClass();
        int iD = this.x.d();
        z7Var.getClass();
        if (iD <= 0 || this.x.c(0).a()) {
            return false;
        }
        NativeViewListRenderer.a aVar = ((NativeViewListRenderer) this.y).j;
        if (aVar == null) {
            return true;
        }
        z7Var.getClass();
        z7Var.getClass();
        aVar.a(0, 0);
        return true;
    }

    public final void n(ArrayList<f.b> arrayList) {
        org.chromium.chrome.browser.feed.f fVar = this.x;
        if (!fVar.e(arrayList, this.z, fVar.d() - this.z)) {
            return;
        }
        Iterator<g.a> it = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            }
            g.a aVar2 = (g.a) aVar.next();
            org.chromium.chrome.browser.feed.f fVar2 = this.x;
            aVar2.a(fVar2 != null ? fVar2.a : null);
        }
    }

    @CalledByNative
    public void onStreamUpdated(byte[] bArr) {
        int i;
        f.b cVar;
        View viewInflate;
        FeedReliabilityLoggingBridge feedReliabilityLoggingBridge = this.v;
        if (this.x == null) {
            return;
        }
        try {
            am0 am0VarT = am0.T(bArr);
            this.B = am0VarT.O();
            xl0 xl0VarP = am0VarT.P();
            xl0VarP.getClass();
            xl0VarP.P().x();
            for (yl0 yl0Var : am0VarT.Q()) {
                zv0 zv0Var = this.y;
                yl0Var.O().x();
                zv0Var.getClass();
            }
            ArrayList<f.b> arrayList = new ArrayList<>();
            Iterator<E> it = am0VarT.S().iterator();
            boolean z = false;
            while (true) {
                boolean zHasNext = it.hasNext();
                i = this.e;
                if (!zHasNext) {
                    break;
                }
                am0.a aVar = (am0.a) it.next();
                if (aVar.Q()) {
                    zl0 zl0VarO = aVar.O();
                    String strQ = zl0VarO.Q();
                    if (zl0VarO.V()) {
                        zl0VarO.S().P().x();
                        cVar = new f.a(strQ);
                    } else if (zl0VarO.U()) {
                        if (this.A) {
                            cVar = null;
                        } else {
                            sk skVar = bq.a;
                            cVar = (N.M09VlOh_("FeedLoadingPlaceholder") && zl0VarO.P().P()) ? new f.c(j(), R$layout.feed_placeholder_layout, strQ) : new f.c(j(), R$layout.feed_spinner, strQ);
                        }
                    } else if (i == 2) {
                        cVar = new f.c(j(), R$layout.following_empty_state, strQ);
                    } else if (i == 3) {
                        int iP = zl0VarO.T().P();
                        Activity activity = this.b;
                        if (iP == 2) {
                            viewInflate = LayoutInflater.from(activity).inflate(R$layout.creator_content_unavailable_error, (ViewGroup) this.w, false);
                        } else {
                            this.j.a();
                            viewInflate = LayoutInflater.from(activity).inflate(R$layout.creator_general_error, (ViewGroup) this.w, false);
                        }
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                        ((ViewGroup.MarginLayoutParams) viewInflate.getLayoutParams()).setMargins(0, displayMetrics.heightPixels / 4, 0, activity.getResources().getDimensionPixelSize(R$dimen.creator_error_margin_bottom));
                        cVar = new f.c(j(), viewInflate, strQ);
                    } else {
                        cVar = zl0VarO.T().P() == 3 ? new f.c(j(), R$layout.no_connection, strQ) : new f.c(j(), R$layout.no_content_v2, strQ);
                    }
                    if (cVar != null) {
                        arrayList.add(cVar);
                        if (!cVar.a()) {
                            z = true;
                        }
                    }
                } else {
                    int iB = this.x.b(aVar.P());
                    if (iB != -1) {
                        arrayList.add(this.x.c(iB));
                        if (!this.x.c(iB).a()) {
                            z = true;
                        }
                    }
                }
            }
            n(arrayList);
            RecyclerView recyclerView = this.w;
            Objects.requireNonNull(feedReliabilityLoggingBridge);
            recyclerView.post(new l10(11, feedReliabilityLoggingBridge));
            yk0 yk0Var = this.i;
            if (yk0Var != null && z) {
                yk0Var.a(i);
                this.i = null;
            }
            l(0);
        } catch (d0 e2) {
            fa1.n("FeedStream", "Unable to parse StreamUpdate proto data", e2);
            feedReliabilityLoggingBridge.b.getClass();
        }
    }

    @CalledByNative
    public void removeDataStoreEntry(String str) {
    }

    @CalledByNative
    public void replaceDataStoreEntry(String str, byte[] bArr) {
    }
}
