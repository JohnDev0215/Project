package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RemoteViews;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.dx2;
import defpackage.kj;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.customtabs.CustomTabBottomBarView;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: CustomTabBottomBarDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class d30 implements kj.a, dx2.a {
    public final Activity e;
    public final WindowAndroid f;
    public final jj g;
    public final zp1<Integer> h;
    public final oj i;
    public final a30 j;
    public final a40 k;
    public final fy2 l;
    public CustomTabBottomBarView m;
    public PendingIntent n;
    public int[] o;
    public PendingIntent p;
    public final a q = new a();

    /* JADX INFO: compiled from: CustomTabBottomBarDelegate.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            d30 d30Var = d30.this;
            if (d30Var.n == null) {
                return;
            }
            Intent intent = new Intent();
            int id = view.getId();
            if (bq.l.a()) {
                id = ((Integer) view.getTag(R$id.view_id_tag_key)).intValue();
            }
            intent.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_CLICKED_ID", id);
            d30.d(d30Var.n, intent, d30Var.e, d30Var.j);
        }
    }

    /* JADX INFO: compiled from: CustomTabBottomBarDelegate.java */
    public class b implements View.OnLayoutChangeListener {
        public final /* synthetic */ View e;

        public b(View view) {
            this.e = view;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.e.removeOnLayoutChangeListener(this);
            d30 d30Var = d30.this;
            d30Var.g.k(d30Var.a(), 0);
        }
    }

    public d30(Activity activity, WindowAndroid windowAndroid, oj ojVar, jj jjVar, zp1<Integer> zp1Var, a40 a40Var, fy2 fy2Var, a30 a30Var, k30 k30Var) {
        this.e = activity;
        this.f = windowAndroid;
        this.i = ojVar;
        this.g = jjVar;
        this.h = zp1Var;
        this.k = a40Var;
        this.l = fy2Var;
        this.j = a30Var;
        jjVar.o(this);
        so soVar = new so(19, this);
        if (k30Var.l) {
            soVar.onResult(k30Var.h.get().getLayoutManager());
        } else {
            k30Var.e.add(soVar);
        }
        wb wbVar = new wb(16, this);
        windowAndroid.m.p(wbVar);
        zp1Var.p(wbVar);
    }

    public static void d(PendingIntent pendingIntent, Intent intent, Activity activity, a30 a30Var) {
        Intent intent2 = intent == null ? new Intent() : new Intent(intent);
        Tab tab = a30Var.b;
        if (tab != null) {
            intent2.setData(Uri.parse(tab.getUrl().j()));
        }
        try {
            pendingIntent.send(activity, 0, intent2, null, null);
        } catch (PendingIntent.CanceledException unused) {
            Log.e("cr_CustomTab", "CanceledException when sending pending intent.");
        }
    }

    public static void g(View view) {
        view.setTag(R$id.view_id_tag_key, Integer.valueOf(view.getId()));
        view.setId(-1);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                g(viewGroup.getChildAt(i));
            }
        }
    }

    public final int a() {
        CustomTabBottomBarView customTabBottomBarView;
        oj ojVar = this.i;
        if (!((ojVar.n().isEmpty() && ojVar.h() == null) ? false : true) || (customTabBottomBarView = this.m) == null || customTabBottomBarView.getChildCount() < 2) {
            return 0;
        }
        return this.m.getChildAt(1).getHeight();
    }

    public final CustomTabBottomBarView b() {
        if (this.m == null) {
            this.m = (CustomTabBottomBarView) ((ViewStub) this.e.findViewById(R$id.bottombar_stub)).inflate();
        }
        return this.m;
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        CustomTabBottomBarView customTabBottomBarView = this.m;
        if (customTabBottomBarView != null) {
            customTabBottomBarView.setTranslationY(i3);
        }
        if (a() != 0) {
            i = i3;
        }
        int iA = a();
        jj jjVar = this.g;
        if (Math.abs(i) == (iA == 0 ? jjVar.l() : jjVar.g()) || i == 0) {
            CustomTabsConnection customTabsConnectionE = CustomTabsConnection.e();
            CustomTabsSessionToken customTabsSessionTokenZ = this.i.z();
            boolean z2 = i != 0;
            customTabsConnectionE.getClass();
            Bundle bundle = new Bundle();
            bundle.putBoolean("hidden", z2);
            if (customTabsConnectionE.r(customTabsSessionTokenZ, "onBottomBarScrollStateChanged", bundle) && customTabsConnectionE.d) {
                customTabsConnectionE.l(Boolean.valueOf(z2), "extraCallback(onBottomBarScrollStateChanged)");
            }
        }
    }

    public final boolean e(RemoteViews remoteViews) {
        View viewInflate;
        CustomTabBottomBarView customTabBottomBarViewB = b();
        boolean zD = this.k.d();
        View viewApply = null;
        if (zD == this.l.b) {
            try {
                viewApply = remoteViews.apply(xz.a, customTabBottomBarViewB);
            } catch (RuntimeException e) {
                Log.e("cr_RemoteViewsInflater", "Failed to inflate the RemoteViews", e);
            }
        } else {
            try {
                Context contextA = xz.a;
                String str = remoteViews.getPackage();
                if (!contextA.getPackageName().equals(str)) {
                    contextA = pn1.a(0, contextA.createPackageContext(str, 4), zD);
                }
                Context context = xz.a;
                viewInflate = LayoutInflater.from(context).cloneInContext(new pa2(context, contextA)).inflate(remoteViews.getLayoutId(), (ViewGroup) customTabBottomBarViewB, false);
                remoteViews.reapply(context, viewInflate);
            } catch (PackageManager.NameNotFoundException | RuntimeException e2) {
                Log.e("cr_RemoteViewsInflater", "Failed to inflate the RemoteViews", e2);
                viewInflate = null;
            }
            if (viewInflate == null) {
                try {
                    viewApply = remoteViews.apply(xz.a, customTabBottomBarViewB);
                } catch (RuntimeException e3) {
                    Log.e("cr_RemoteViewsInflater", "Failed to inflate the RemoteViews", e3);
                }
            } else {
                viewApply = viewInflate;
            }
        }
        if (viewApply == null) {
            return false;
        }
        int[] iArr = this.o;
        if (iArr != null && this.n != null) {
            for (int i : iArr) {
                if (i < 0) {
                    return false;
                }
                View viewFindViewById = viewApply.findViewById(i);
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(this.q);
                }
            }
        }
        if (bq.l.a()) {
            g(viewApply);
        }
        b().addView(viewApply, 1);
        viewApply.addOnLayoutChangeListener(new b(viewApply));
        return true;
    }

    @Override // kj.a
    public final void l(int i) {
        if ((this.m == null && this.e.findViewById(R$id.bottombar_stub) == null) ? false : true) {
            b().setTranslationY(this.g.p() * i);
        }
    }

    @Override // dx2.a
    public final boolean q(int i) {
        return i == 3 && b().getVisibility() == 0;
    }

    @Override // dx2.a
    public final void v(int i) {
        PendingIntent pendingIntent = this.p;
        if (pendingIntent == null) {
            return;
        }
        d(pendingIntent, null, this.e, this.j);
    }
}
