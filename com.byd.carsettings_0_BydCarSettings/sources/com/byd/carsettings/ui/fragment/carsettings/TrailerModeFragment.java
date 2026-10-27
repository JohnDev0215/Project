package com.byd.carsettings.ui.fragment.carsettings;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import b.b.a.h.l;
import b.b.g.n.h.n;
import b.b.g.q.d.i;
import b.b.g.q.d.k;
import b.b.g.q.d.q;
import b.b.g.r.e;
import b.b.g.r.z;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class TrailerModeFragment extends XFragment<n> implements View.OnClickListener, b.b.o.b.a.a {
    public BydSwitch m;
    public ImageView n;
    public Context o;
    public ImageView p;
    public LottieAnimationView q;
    public LottieDrawable r;
    public b.b.o.a.b.a s;
    public int t;
    public double u = 3.0d;
    public i v;
    public Dialog w;

    public class a implements BydSwitch.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3116a;

        public a(Context context) {
            this.f3116a = context;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.base.view.BydSwitch.a
        public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
            TrailerModeFragment.this.m.playSoundEffect(0);
            if (motionEvent.getAction() != 1) {
                return;
            }
            if (!z) {
                ((n) TrailerModeFragment.this.mo7getP()).f(2);
                TrailerModeFragment trailerModeFragment = TrailerModeFragment.this;
                trailerModeFragment.V(trailerModeFragment.R(trailerModeFragment.t, ((n) TrailerModeFragment.this.mo7getP()).d() == 1 ? 31 : 32));
                TrailerModeFragment.this.m.setChecked(false, false);
                return;
            }
            if (b.b.g.n.q.n.b().c() >= 3.0d || ((n) TrailerModeFragment.this.mo7getP()).d() == 2 || ((n) TrailerModeFragment.this.mo7getP()).d() == 0) {
                Context context = this.f3116a;
                z.c(context, context.getString(R.string.vehiclesettings_trailer_drive_not_trailer_mode_toast_tips));
                return;
            }
            TrailerModeFragment.this.m.setChecked(true, false);
            ((n) TrailerModeFragment.this.mo7getP()).f(1);
            TrailerModeFragment.this.V(2);
            e.i(240009);
            TrailerModeFragment.this.Y();
        }
    }

    public class b implements q.b {
        public b() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            TrailerModeFragment.this.w.dismiss();
        }
    }

    public class c implements OnCompositionLoadedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3119a;

        public c(Context context) {
            this.f3119a = context;
        }

        @Override // com.airbnb.lottie.OnCompositionLoadedListener
        public void onCompositionLoaded(@Nullable LottieComposition lottieComposition) {
            TrailerModeFragment.this.r.setComposition(lottieComposition);
            TrailerModeFragment.this.r.playAnimation();
            TrailerModeFragment.this.r.setImagesAssetsFolder(null);
            TrailerModeFragment.this.r.setRepeatCount(-1);
            l.m(TrailerModeFragment.this.q, TrailerModeFragment.this.r, this.f3119a, false);
        }
    }

    public class d implements q.b {
        public d() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            TrailerModeFragment.this.w.dismiss();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.o = context;
        this.m = (BydSwitch) this.f3717c.findViewById(R.id.trailer_signal_sw);
        ImageView imageView = (ImageView) this.f3717c.findViewById(R.id.trailer_signal_iv);
        this.n = imageView;
        l.F(this, imageView);
        this.p = (ImageView) this.f3717c.findViewById(R.id.image);
        this.q = (LottieAnimationView) this.f3717c.findViewById(R.id.trailer_lottie_view);
        X();
        if (((n) mo7getP()).c() == 1 && ((n) mo7getP()).d() == 1) {
            this.q.setImageDrawable(context.getDrawable(R(31, 0)));
        }
        this.m.setOnInterceptTouchListener(new a(context));
    }

    public int R(int i, int i2) {
        if (this.s == null) {
            this.s = b.b.o.a.b.d.a("trailer");
        }
        return this.s.a(i, i2);
    }

    public final i S(String str) {
        if (this.v == null) {
            this.v = new i(getContext());
        }
        this.v.x(str);
        return this.v;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int T() {
        if (((n) mo7getP()).c() == 1 && ((n) mo7getP()).d() == 1 && !((n) mo7getP()).e()) {
            return 2;
        }
        return ((n) mo7getP()).d() == 1 ? 1 : 0;
    }

    public final void U(Context context) {
        if (this.r == null) {
            this.r = new LottieDrawable();
        }
        if (this.r != null) {
            LottieComposition.Factory.fromRawFile(context, R(33, 0), new c(context));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void V(int i) {
        if (i == 1) {
            l.m(this.q, this.o.getDrawable(R(31, 0)), this.o, false);
            return;
        }
        if (i != 2) {
            l.m(this.q, this.o.getDrawable(R(32, 0)), this.o, false);
            return;
        }
        if (this.r == null) {
            U(getContext());
        }
        if (((n) mo7getP()).c() == 1) {
            l.m(this.q, this.o.getDrawable(R(31, 0)), this.o, false);
        }
        l.m(this.q, this.r, this.o, false);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public n newP() {
        return new n();
    }

    public final void X() {
        V(T());
    }

    public final void Y() {
        this.w = S("trailer_reminder").B(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_trailer_reminder_title, R.string.vehiclesettings_trailer_light_inspection_signal_remind_tips, -1, -1, R.string.vehiclesettings_button_confirm, new b(), -1, null);
    }

    public final void Z(String str) {
        str.hashCode();
        if (str.equals("trailer_reminder")) {
            Y();
        } else if (str.equals("trailer_light_check")) {
            a0();
        }
    }

    public final void a0() {
        this.w = S("trailer_light_check").B(R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_trailer_drive_mode_with_trailer_signal, R.string.vehiclesettings_signal_trailer_light_inspection_tips, -1, -1, R.string.vehiclesettings_button_confirm, new d(), -1, null);
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_carsetting_rtl_trailer_mode;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_trailer_mode;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_carsetting_right_trailer_mode;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        for (String str : k.b()) {
            if (str.startsWith("trailer_")) {
                Z(str);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.trailer_signal_iv) {
            a0();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        LottieDrawable lottieDrawable = this.r;
        if (lottieDrawable != null && lottieDrawable.isAnimating()) {
            this.r.cancelAnimation();
            this.r.clearComposition();
        }
        LottieAnimationView lottieAnimationView = this.q;
        if (lottieAnimationView != null) {
            lottieAnimationView.setImageDrawable(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        switch (aVar.h()) {
            case 240008:
                BydLogHelper.logD("TRAILER_MODE_STATE :" + aVar.c());
                if (aVar.c() != 0) {
                    this.m.setChecked(((n) mo7getP()).c() == 1 && ((n) mo7getP()).d() == 1 && !((n) mo7getP()).e(), false);
                    X();
                } else {
                    BydLogHelper.logD("TRAILER_MODE_STATE : trailer mode getInValid value");
                }
                break;
            case 240009:
                BydLogHelper.logD("TRAILER_LIGHT_CHECK_STATE :" + aVar.c());
                int iC = aVar.c();
                if (e.e(aVar)) {
                    iC = ((n) mo7getP()).c();
                }
                this.m.setChecked(iC == 1 && ((n) mo7getP()).d() == 1 && !((n) mo7getP()).e(), false);
                X();
                break;
            case 240010:
                BydLogHelper.logD("TRAILER_MODE_AUTO_ENTER_MESSAGE :" + aVar.c());
                break;
            case 240011:
                double dA = aVar.a();
                boolean z = (dA - 3.0d) * (this.u - 3.0d) > 0.0d;
                BydLogHelper.logD("TRAILER_MODE_SPEED_VALUE_MESSAGE :" + aVar.a() + " last speed : " + this.u);
                this.u = dA;
                if (!z) {
                    this.m.setChecked(((n) mo7getP()).c() == 1 && ((n) mo7getP()).d() == 1 && !((n) mo7getP()).e(), false);
                    X();
                } else {
                    BydLogHelper.logD(" TRAILER_MODE_SPEED_VALUE_MESSAGE : this speed and last speed both more or less");
                }
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        this.m.setChecked(((n) mo7getP()).c() == 1 && ((n) mo7getP()).d() == 1, false);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.j.b.b bVar) {
        Dialog dialog;
        if (bVar.a() == 4 || (dialog = this.w) == null) {
            return;
        }
        dialog.dismiss();
        this.w = null;
    }
}
