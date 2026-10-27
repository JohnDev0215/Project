package com.byd.mvp.mvp;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.m.e.g;
import b.b.m.e.h;
import com.byd.mvp.common.BydLayoutDirection;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.utils.AppConfig;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.BydFragment;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XFragment<P extends g> extends BydFragment implements h<P> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public P f3716b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile ViewGroup f3717c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public LayoutInflater f3718d;
    public volatile BydLayoutDirection j;
    public volatile BydLayoutDirection k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3715a = getClass().getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile boolean f3719e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile boolean f3720f = false;
    public volatile int g = 0;
    public volatile int h = -1;
    public volatile int i = -1;
    public CarModel$Brand l = CarModel$Brand.DYNASTY;

    public class a implements View.OnLayoutChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ScrollView f3721a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f3722b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f3723c;

        public a(ScrollView scrollView, int i, int i2) {
            this.f3721a = scrollView;
            this.f3722b = i;
            this.f3723c = i2;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.f3721a.removeOnLayoutChangeListener(this);
            this.f3721a.scrollTo(this.f3722b, this.f3723c);
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3725a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f3726b;

        static {
            int[] iArr = new int[CarModel$Brand.values().length];
            f3726b = iArr;
            try {
                iArr[CarModel$Brand.DYNASTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3726b[CarModel$Brand.DENZA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[BydLayoutDirection.values().length];
            f3725a = iArr2;
            try {
                iArr2[BydLayoutDirection.LTR_Rudder_L.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3725a[BydLayoutDirection.LTR_Rudder_R.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3725a[BydLayoutDirection.RTL_Rudder_L.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3725a[BydLayoutDirection.RTL_Rudder_R.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public XFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(getClass().getName(), getClass().getName());
        setArguments(bundle);
    }

    public abstract void A(Context context);

    public boolean B() {
        return (getResources().getConfiguration().uiMode & 48) == 32;
    }

    public void C(CarModel$Brand carModel$Brand) {
        b.b.m.c.a.d("XFragment", "---loadUIStyleOnCarBrand----brand: " + carModel$Brand + ", " + getClass().getName());
        if (carModel$Brand == null) {
            return;
        }
        this.l = carModel$Brand;
        boolean z = this.i == 2;
        int i = b.f3726b[carModel$Brand.ordinal()];
        if (i == 1) {
            E(z);
        } else {
            if (i != 2) {
                return;
            }
            D(z);
        }
    }

    public void D(boolean z) {
        BydLogHelper.logV("XFragment----loadUIStyleOnDenzaBrand");
    }

    public void E(boolean z) {
        BydLogHelper.logV("XFragment----loadUIStyleOnDynastyBrand");
    }

    public void F(Bundle bundle) {
    }

    public void G(ScrollView scrollView, int i, int i2) {
        if (scrollView == null) {
            return;
        }
        if (scrollView.getWidth() <= 0) {
            scrollView.addOnLayoutChangeListener(new a(scrollView, i, i2));
        } else {
            scrollView.scrollTo(i, i2);
        }
    }

    public void H(ScrollView scrollView, int i) {
        b.b.m.c.a.a(this.f3715a, "scrollY", "searchStata: " + b.b.m.e.i.a.i());
        if (scrollView == null || !b.b.m.e.i.a.i()) {
            return;
        }
        b.b.m.c.a.a("XFragment", "scrollY", "index = " + i + ", " + getClass().getName());
        if (i < 3) {
            G(scrollView, 0, 0);
            return;
        }
        int i2 = (i - 2) * 150;
        b.b.m.c.a.a("XFragment", "scrollY", "index = " + i + ", y = " + i2 + ", " + getClass().getName());
        G(scrollView, 0, i2);
        BydLogHelper.logV("XFragment----scrollY");
    }

    public void I(ScrollView scrollView, int i, int i2) {
        b.b.m.c.a.a(this.f3715a, "scrollY", "searchStata: " + b.b.m.e.i.a.i());
        if (scrollView == null || !b.b.m.e.i.a.i()) {
            return;
        }
        b.b.m.c.a.a("XFragment", "scrollY", "stepY: " + i + ",index: " + i2 + ", " + getClass().getName());
        if (i2 < 3) {
            G(scrollView, 0, 0);
            return;
        }
        int i3 = i * (i2 - 2);
        b.b.m.c.a.a("XFragment", "scrollY", "index: " + i2 + ", y: " + i3 + ", " + getClass().getName());
        G(scrollView, 0, i3);
        BydLogHelper.logV("XFragment----scrollY");
    }

    public void J(int i) {
        b.b.m.c.a.d("XFragment", "---setCurIndex---index = " + i + ", " + getClass().getName());
        this.g = i;
        b.b.m.e.i.a.k(getClass().getName(), i);
    }

    @Override // b.b.m.e.h
    public P getRefP() {
        return this.f3716b;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onBydLayoutDirectionChanged(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        b.b.m.c.a.d("XFragment", "---onBydLayoutDirectionChanged----direction: " + bydLayoutDirection + ", rtl: " + z + ", land: " + z2 + ", rightRudder: " + z3);
        int i = b.f3725a[bydLayoutDirection.ordinal()];
        if (i == 1) {
            onLtrRudderL(z2);
        } else if (i == 2) {
            onLtrRudderR(z2);
        } else {
            if (i != 3) {
                return;
            }
            onRtlRudderL(z2);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b.b.m.c.a.a("XFragment", "---onConfigurationChanged----getLayoutDirection: " + configuration.getLayoutDirection());
        setLayoutDirection(configuration);
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.f3718d = layoutInflater;
        this.f3720f = false;
        if (getLayoutId() > 0) {
            this.f3717c = (ViewGroup) layoutInflater.inflate(getLayoutId(), (ViewGroup) null);
            bindEvent();
        }
        if (this.f3717c != null) {
            A(this.f3717c.getContext());
            y(this.f3717c.getContext());
            z();
        }
        setLayoutDirection(getResources().getConfiguration());
        C(AppConfig.a());
        return this.f3717c;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (mo7getP() != null) {
            mo7getP().unbindV();
        }
        if (this.f3717c != null) {
            this.f3717c.removeAllViews();
        }
        this.f3716b = null;
        this.f3717c = null;
        this.f3718d = null;
        this.f3720f = false;
        v();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    public void onLayoutDirectionChanged(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        BydLogHelper.logV("XFragment----onLayoutDirectionChanged");
    }

    public void onLtrRudderL(boolean z) {
        BydLogHelper.logV("XFragment----onLtrRudderL");
    }

    public void onLtrRudderR(boolean z) {
        BydLogHelper.logV("XFragment----onLtrRudderR");
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        this.f3719e = false;
        super.onResume();
        this.g = b.b.m.e.i.a.d(getClass().getName());
        b.b.m.c.a.d("XFragment", "onResume", ", curIndex = " + this.g + ", " + getClass().getName());
        if (!this.f3720f) {
            this.f3720f = true;
        }
        String strG = b.b.m.e.i.a.g("moduleKey");
        String strG2 = b.b.m.e.i.a.g("moduleAction");
        b.b.m.c.a.d("XFragment", "onResume", ", moduleKey = " + strG + ", " + getClass().getName());
        if (TextUtils.isEmpty(strG)) {
            t(this.g);
        } else {
            u(this.g, strG, strG2);
        }
    }

    public void onRtlRudderL(boolean z) {
        BydLogHelper.logV("XFragment----onRtlRudderL");
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        F(bundle);
        super.onSaveInstanceState(bundle);
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.f3719e = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(@Nullable Bundle bundle) {
        x(bundle);
        super.onViewStateRestored(bundle);
    }

    public void setLayoutDirection(Configuration configuration) {
        if (b.b.m.g.g.e()) {
            return;
        }
        int layoutDirection = configuration.getLayoutDirection();
        int i = configuration.orientation;
        if (this.h == layoutDirection && this.i == i) {
            return;
        }
        this.h = layoutDirection;
        this.i = i;
        boolean z = layoutDirection == 1;
        boolean z2 = i == 2;
        boolean zD = AppConfig.d();
        BydLayoutDirection bydLayoutDirection = z ? zD ? BydLayoutDirection.RTL_Rudder_R : BydLayoutDirection.RTL_Rudder_L : zD ? BydLayoutDirection.LTR_Rudder_R : BydLayoutDirection.LTR_Rudder_L;
        b.b.m.c.a.d("XFragment", "---setLayoutDirection----direction: " + bydLayoutDirection + ", " + getClass().getName());
        this.j = bydLayoutDirection;
        onLayoutDirectionChanged(bydLayoutDirection, z, z2, AppConfig.d());
        if (bydLayoutDirection == this.k) {
            return;
        }
        this.k = bydLayoutDirection;
        onBydLayoutDirectionChanged(bydLayoutDirection, z, z2, AppConfig.d());
    }

    @Override // b.b.m.e.h
    public void setRefP(P p) {
        this.f3716b = p;
    }

    public void setViewGroupRtl(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.setLayoutDirection(z ? 1 : 0);
    }

    public void t(int i) {
        BydLogHelper.logV("XFragment----checkSubindexOnResume");
    }

    public void u(int i, String str, String str2) {
        BydLogHelper.logV("XFragment----checkSubindexOnResume");
    }

    public void v() {
        BydLogHelper.logV("XFragment----clearCache");
    }

    public int w() {
        return this.g;
    }

    public void x(Bundle bundle) {
    }

    public abstract void y(Context context);

    public void z() {
        BydLogHelper.logV("XFragment----initLazyData");
    }
}
