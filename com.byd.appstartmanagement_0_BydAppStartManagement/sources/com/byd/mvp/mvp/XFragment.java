package com.byd.mvp.mvp;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.d.d.e;
import b.a.d.d.f;
import b.a.d.f.d;
import com.byd.mvp.common.BydLayoutDirection;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.utils.AppConfig;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.BydFragment;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XFragment<P extends e> extends BydFragment implements f<P> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public P f657c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile ViewGroup f658d;
    public LayoutInflater e;
    public volatile BydLayoutDirection k;
    public volatile BydLayoutDirection l;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f656b = getClass().getSimpleName();
    public volatile boolean f = true;
    public volatile boolean g = false;
    public volatile int h = 0;
    public volatile int i = -1;
    public volatile int j = -1;
    public CarModel$Brand m = CarModel$Brand.DYNASTY;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f659a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f660b;

        static {
            int[] iArr = new int[CarModel$Brand.values().length];
            f660b = iArr;
            try {
                iArr[CarModel$Brand.DYNASTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f660b[CarModel$Brand.DENZA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[BydLayoutDirection.values().length];
            f659a = iArr2;
            try {
                iArr2[BydLayoutDirection.LTR_Rudder_L.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f659a[BydLayoutDirection.LTR_Rudder_R.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f659a[BydLayoutDirection.RTL_Rudder_L.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f659a[BydLayoutDirection.RTL_Rudder_R.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public XFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(getClass().getName(), getClass().getName());
        setArguments(bundle);
    }

    @Override // b.a.d.d.f
    public void a(P p) {
        this.f657c = p;
    }

    @Override // b.a.d.d.f
    public P b() {
        return this.f657c;
    }

    public void g(int i) {
        BydLogHelper.logV("XFragment----checkSubindexOnResume");
    }

    public void h(int i, String str, String str2) {
        BydLogHelper.logV("XFragment----checkSubindexOnResume");
    }

    public void i() {
        BydLogHelper.logV("XFragment----clearCache");
    }

    public void j(Bundle bundle) {
    }

    public abstract void k(Context context);

    public void l() {
        BydLogHelper.logV("XFragment----initLazyData");
    }

    public abstract void m(Context context);

    public void n(CarModel$Brand carModel$Brand) {
        b.a.d.b.a.d("XFragment", "---loadUIStyleOnCarBrand----brand: " + carModel$Brand + ", " + getClass().getName());
        if (carModel$Brand == null) {
            return;
        }
        this.m = carModel$Brand;
        boolean z = this.j == 2;
        int i = a.f660b[carModel$Brand.ordinal()];
        if (i == 1) {
            p(z);
        } else {
            if (i != 2) {
                return;
            }
            o(z);
        }
    }

    public void o(boolean z) {
        BydLogHelper.logV("XFragment----loadUIStyleOnDenzaBrand");
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b.a.d.b.a.a("XFragment", "---onConfigurationChanged----getLayoutDirection: " + configuration.getLayoutDirection());
        w(configuration);
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.e = layoutInflater;
        this.g = false;
        if (c() > 0) {
            this.f658d = (ViewGroup) layoutInflater.inflate(c(), (ViewGroup) null);
            d();
        }
        if (this.f658d != null) {
            m(this.f658d.getContext());
            k(this.f658d.getContext());
            l();
        }
        w(getResources().getConfiguration());
        n(AppConfig.a());
        return this.f658d;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (e() != null) {
            e().c();
        }
        if (this.f658d != null) {
            this.f658d.removeAllViews();
        }
        this.f657c = null;
        this.f658d = null;
        this.e = null;
        this.g = false;
        i();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        this.f = false;
        super.onResume();
        this.h = b.a.d.d.g.a.a(getClass().getName());
        b.a.d.b.a.d("XFragment", "onResume", ", curIndex = " + this.h + ", " + getClass().getName());
        if (!this.g) {
            this.g = true;
        }
        String strB = b.a.d.d.g.a.b("moduleKey");
        String strB2 = b.a.d.d.g.a.b("moduleAction");
        b.a.d.b.a.d("XFragment", "onResume", ", moduleKey = " + strB + ", " + getClass().getName());
        if (TextUtils.isEmpty(strB)) {
            g(this.h);
        } else {
            h(this.h, strB, strB2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        v(bundle);
        super.onSaveInstanceState(bundle);
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.f = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(@Nullable Bundle bundle) {
        j(bundle);
        super.onViewStateRestored(bundle);
    }

    public void p(boolean z) {
        BydLogHelper.logV("XFragment----loadUIStyleOnDynastyBrand");
    }

    public void q(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        b.a.d.b.a.d("XFragment", "---onBydLayoutDirectionChanged----direction: " + bydLayoutDirection + ", rtl: " + z + ", land: " + z2 + ", rightRudder: " + z3);
        int i = a.f659a[bydLayoutDirection.ordinal()];
        if (i == 1) {
            s(z2);
        } else if (i == 2) {
            t(z2);
        } else {
            if (i != 3) {
                return;
            }
            u(z2);
        }
    }

    public void r(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        BydLogHelper.logV("XFragment----onLayoutDirectionChanged");
    }

    public void s(boolean z) {
        BydLogHelper.logV("XFragment----onLtrRudderL");
    }

    public void t(boolean z) {
        BydLogHelper.logV("XFragment----onLtrRudderR");
    }

    public void u(boolean z) {
        BydLogHelper.logV("XFragment----onRtlRudderL");
    }

    public void v(Bundle bundle) {
    }

    public void w(Configuration configuration) {
        if (d.a()) {
            return;
        }
        int layoutDirection = configuration.getLayoutDirection();
        int i = configuration.orientation;
        if (this.i == layoutDirection && this.j == i) {
            return;
        }
        this.i = layoutDirection;
        this.j = i;
        boolean z = layoutDirection == 1;
        boolean z2 = i == 2;
        boolean zB = AppConfig.b();
        BydLayoutDirection bydLayoutDirection = z ? zB ? BydLayoutDirection.RTL_Rudder_R : BydLayoutDirection.RTL_Rudder_L : zB ? BydLayoutDirection.LTR_Rudder_R : BydLayoutDirection.LTR_Rudder_L;
        b.a.d.b.a.d("XFragment", "---setLayoutDirection----direction: " + bydLayoutDirection + ", " + getClass().getName());
        this.k = bydLayoutDirection;
        r(bydLayoutDirection, z, z2, AppConfig.b());
        if (bydLayoutDirection == this.l) {
            return;
        }
        this.l = bydLayoutDirection;
        q(bydLayoutDirection, z, z2, AppConfig.b());
    }
}
