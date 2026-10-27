package com.byd.digitalkey.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.hardware.bydauto.TsNormalNum;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import b.b.a.j.b.a;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.digitalkey.R$dimen;
import com.byd.digitalkey.R$drawable;
import com.byd.digitalkey.R$id;
import com.byd.digitalkey.R$layout;
import com.byd.digitalkey.R$raw;
import com.byd.digitalkey.R$string;
import com.byd.digitalkey.activity.DigitalKeyActivity;
import com.bydux.api.common.views.window.BydFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class KeyActivateFragment extends BydFragment implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.j.c f3589b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f3590c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f3591d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public LottieAnimationView f3592e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f3593f;
    public View g;
    public ImageView h;
    public TextView i;
    public CountDownTimer j;
    public CountDownTimer k;
    public CountDownTimer l;
    public boolean n;
    public int o;
    public b.b.a.j.b.a q;
    public b.b.a.j.b.a r;
    public b.b.a.j.b.a s;
    public b.b.a.j.b.a t;
    public b.b.a.j.b.a u;
    public int v;
    public int w;
    public boolean x;
    public String z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3588a = getClass().getSimpleName();
    public int m = 1;
    public long p = 120000;
    public int y = BYDAutoEngineDevice.ENGINE_SPEED_MAX;
    public final b.b.j.a A = new a();

    public class a extends b.b.j.a {

        /* JADX INFO: renamed from: com.byd.digitalkey.fragment.KeyActivateFragment$a$a, reason: collision with other inner class name */
        public class RunnableC0098a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f3595a;

            public RunnableC0098a(int i) {
                this.f3595a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                int i = this.f3595a;
                if (i == 1) {
                    KeyActivateFragment.this.a0();
                } else if (i == 2) {
                    KeyActivateFragment.this.Q();
                } else if (i == 3) {
                    KeyActivateFragment.this.V();
                }
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f3597a;

            public b(int i) {
                this.f3597a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.b.a.f.f.b(KeyActivateFragment.this.f3588a, "开卡结果判断--result:%d", Integer.valueOf(this.f3597a));
                int i = this.f3597a;
                if (i == 1) {
                    KeyActivateFragment.this.U(2, KeyActivateFragment.this.f3589b.r() ? 2 : -1);
                } else if (i == 2) {
                    KeyActivateFragment.this.U(1, -1);
                }
            }
        }

        public a() {
        }

        @Override // b.b.j.a, android.hardware.bydauto.digital.IDigitalKeyLisenter
        public void onKeyFindingResults(int i, int i2) {
            super.onKeyFindingResults(i, i2);
            if (i == 0) {
                return;
            }
            b.b.j.e.b(250001);
            if (KeyActivateFragment.this.isResumed()) {
                KeyActivateFragment.this.N(i);
            } else {
                b.b.a.f.f.b(KeyActivateFragment.this.f3588a, "not on resume,return!!", new Object[0]);
            }
        }

        @Override // b.b.j.a, android.hardware.bydauto.digital.IDigitalKeyLisenter
        public void onPairingProcessCallBack(int i, int i2) {
            super.onPairingProcessCallBack(i, i2);
            FragmentActivity activity = KeyActivateFragment.this.getActivity();
            if (activity == null) {
                b.b.a.f.f.b(KeyActivateFragment.this.f3588a, "activity == null,return!!", new Object[0]);
                return;
            }
            if (!KeyActivateFragment.this.isResumed()) {
                b.b.a.f.f.b(KeyActivateFragment.this.f3588a, "not on resume,return!!", new Object[0]);
            } else if (KeyActivateFragment.this.u == null || !KeyActivateFragment.this.u.isShowing()) {
                activity.runOnUiThread(new RunnableC0098a(i));
            } else {
                b.b.a.f.f.b(KeyActivateFragment.this.f3588a, "当前有弹框正在显示,不响应ECU配对过程响应报文", new Object[0]);
            }
        }

        @Override // b.b.j.a, android.hardware.bydauto.digital.IDigitalKeyLisenter
        public void onResponseFeedbackInform(int i) {
            super.onResponseFeedbackInform(i);
            FragmentActivity activity = KeyActivateFragment.this.getActivity();
            if (activity == null) {
                b.b.a.f.f.b(KeyActivateFragment.this.f3588a, "activity == null,return!!", new Object[0]);
            } else {
                activity.runOnUiThread(new b(i));
            }
        }
    }

    public class b implements a.g {
        public b() {
        }

        @Override // b.b.a.j.b.a.g
        public void a(View view) {
            KeyActivateFragment.this.p = 120000L;
            KeyActivateFragment.this.Z();
        }
    }

    public class c implements a.e {
        public c() {
        }

        @Override // b.b.a.j.b.a.e
        public void a(View view) {
            if (KeyActivateFragment.this.getActivity() != null) {
                Intent intent = new Intent(KeyActivateFragment.this.getActivity(), (Class<?>) DigitalKeyActivity.class);
                KeyActivateFragment.this.getActivity().finish();
                KeyActivateFragment.this.startActivity(intent);
            }
        }
    }

    public class d extends CountDownTimer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.a.j.b.a f3601a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(long j, long j2, b.b.a.j.b.a aVar) {
            super(j, j2);
            this.f3601a = aVar;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (KeyActivateFragment.this.getActivity() != null) {
                Intent intent = new Intent(KeyActivateFragment.this.getActivity(), (Class<?>) DigitalKeyActivity.class);
                KeyActivateFragment.this.getActivity().finish();
                KeyActivateFragment.this.startActivity(intent);
                b.b.a.f.f.b(KeyActivateFragment.this.f3588a, "startOpenSuccessTimer onfinish()", new Object[0]);
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            int i = (int) (j / 1000);
            if (i != 0) {
                this.f3601a.p(KeyActivateFragment.this.getString(R$string.digital_key_kown_s, Integer.valueOf(i)));
            } else {
                this.f3601a.p(KeyActivateFragment.this.getString(R$string.digital_key_kown));
            }
        }
    }

    public class e implements Runnable {

        public class a implements a.g {
            public a() {
            }

            @Override // b.b.a.j.b.a.g
            public void a(View view) {
                KeyActivateFragment.this.Z();
            }
        }

        public class b implements a.e {
            public b() {
            }

            @Override // b.b.a.j.b.a.e
            public void a(View view) {
                if (KeyActivateFragment.this.getActivity() != null) {
                    KeyActivateFragment.this.getActivity().finish();
                }
            }
        }

        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            KeyActivateFragment.this.f3592e.pauseAnimation();
            if (KeyActivateFragment.this.t == null) {
                KeyActivateFragment keyActivateFragment = KeyActivateFragment.this;
                keyActivateFragment.t = new b.b.a.j.b.a(keyActivateFragment.getActivity());
            }
            KeyActivateFragment.this.t.setTitle(R$string.nfc_tip);
            KeyActivateFragment.this.t.t(R$string.nfc_key_not_in_car);
            KeyActivateFragment.this.t.o(R$string.nfc_sure_retry);
            KeyActivateFragment.this.t.q(new a());
            KeyActivateFragment.this.t.n(new b());
            KeyActivateFragment.this.t.h().setTag(2);
            if (KeyActivateFragment.this.t.isShowing()) {
                b.b.a.f.f.b(KeyActivateFragment.this.f3588a, "mKeyNotInCarDialog isShowing", new Object[0]);
            } else {
                KeyActivateFragment.this.t.show();
            }
            KeyActivateFragment keyActivateFragment2 = KeyActivateFragment.this;
            keyActivateFragment2.u = keyActivateFragment2.t;
        }
    }

    public class f implements a.g {
        public f() {
        }

        @Override // b.b.a.j.b.a.g
        public void a(View view) {
            KeyActivateFragment.this.r.dismiss();
            KeyActivateFragment.this.Z();
        }
    }

    public class g implements a.e {
        public g() {
        }

        @Override // b.b.a.j.b.a.e
        public void a(View view) {
            if (KeyActivateFragment.this.getActivity() != null) {
                KeyActivateFragment.this.getActivity().finish();
            }
        }
    }

    public class h extends CountDownTimer {
        public h(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            KeyActivateFragment.this.f3592e.pauseAnimation();
            KeyActivateFragment.this.W();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            KeyActivateFragment.this.p = j;
            TextView textView = KeyActivateFragment.this.f3593f;
            KeyActivateFragment keyActivateFragment = KeyActivateFragment.this;
            textView.setText(keyActivateFragment.getString(R$string.nfc_open_step1, Long.valueOf(keyActivateFragment.p / 1000)));
        }
    }

    public class i implements a.g {
        public i() {
        }

        @Override // b.b.a.j.b.a.g
        public void a(View view) {
            KeyActivateFragment.this.p = 120000L;
            KeyActivateFragment.this.Z();
        }
    }

    public class j implements a.e {
        public j() {
        }

        @Override // b.b.a.j.b.a.e
        public void a(View view) {
            if (KeyActivateFragment.this.getActivity() != null) {
                KeyActivateFragment.this.getActivity().finish();
            }
        }
    }

    public class k extends CountDownTimer {
        public k(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            int i = (int) (j / 1000);
            if (i == 0) {
                return;
            }
            String str = "0%";
            switch (i) {
                case 1:
                case 2:
                    str = "90%";
                    break;
                case 3:
                case 4:
                case 5:
                    str = "50%";
                    break;
                case 6:
                    str = "35%";
                    break;
                case 7:
                    str = "18%";
                    break;
            }
            KeyActivateFragment.this.i.setText(str);
        }
    }

    public class l implements a.g {
        public l() {
        }

        @Override // b.b.a.j.b.a.g
        public void a(View view) {
            if (KeyActivateFragment.this.getActivity() != null) {
                Intent intent = new Intent(KeyActivateFragment.this.getActivity(), (Class<?>) DigitalKeyActivity.class);
                KeyActivateFragment.this.getActivity().finish();
                KeyActivateFragment.this.startActivity(intent);
            }
        }
    }

    public static KeyActivateFragment P(int i2, boolean z) {
        KeyActivateFragment keyActivateFragment = new KeyActivateFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pair_state", i2);
        bundle.putBoolean("pair_from", z);
        keyActivateFragment.setArguments(bundle);
        return keyActivateFragment;
    }

    public String M() {
        return this.f3588a;
    }

    public final void N(int i2) {
        if (1 == i2) {
            b.b.a.f.f.b(this.f3588a, "handleKeyFindResult 寻到实体钥匙", new Object[0]);
        } else if (2 == i2) {
            T();
        }
    }

    public final void O() {
        if (this.f3589b == null) {
            b.b.j.c cVarI = b.b.j.c.i(getContext());
            this.f3589b = cVarI;
            cVarI.v(this.A);
        }
        int iM = this.f3589b.m();
        if (iM == 1) {
            a0();
        } else if (iM == 2) {
            Q();
        } else {
            Z();
        }
    }

    public final void Q() {
        this.g.setVisibility(0);
        this.f3591d.setVisibility(8);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1500L);
        rotateAnimation.setRepeatCount(-1);
        this.h.startAnimation(rotateAnimation);
        b0();
        this.x = true;
    }

    public void R() {
        try {
            O();
            b.b.a.j.b.a aVar = this.u;
            if (aVar == null || !aVar.isShowing()) {
                return;
            }
            this.u.dismiss();
        } catch (Exception e2) {
            b.b.a.f.f.b(this.f3588a, "onNewIntent is Exception:" + e2, new Object[0]);
        }
    }

    public final void S() {
        b.b.a.j.b.a aVar = this.u;
        if (aVar == null || !aVar.isShowing()) {
            return;
        }
        CountDownTimer countDownTimer = this.j;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.f3592e.pauseAnimation();
    }

    public final void T() {
        b.b.a.j.b.a aVar = this.u;
        if (aVar == null) {
            return;
        }
        int iIntValue = ((Integer) aVar.h().getTag()).intValue();
        if (iIntValue == 8 || iIntValue == 9 || iIntValue == 10 || iIntValue == 2) {
            CountDownTimer countDownTimer = this.j;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            if (getActivity() != null) {
                getActivity().runOnUiThread(new e());
            }
        }
    }

    public final void U(int i2, int i3) {
        this.x = false;
        if (i2 == 3) {
            b.b.a.f.f.b(this.f3588a, "result为配对取消,return", new Object[0]);
            return;
        }
        this.v = i2;
        this.w = i3;
        this.h.clearAnimation();
        if (this.q == null) {
            this.q = new b.b.a.j.b.a(getActivity());
        }
        this.q.j();
        this.q.setCancelable(false);
        View viewInflate = View.inflate(getActivity(), R$layout.digital_key_result, null);
        ImageView imageView = (ImageView) viewInflate.findViewById(R$id.iv_open_result);
        TextView textView = (TextView) viewInflate.findViewById(R$id.tv_result_des1);
        TextView textView2 = (TextView) viewInflate.findViewById(R$id.tv_result_des2);
        this.q.r(viewInflate);
        this.q.h().setTag(10);
        this.q.u(0, (int) getResources().getDimension(R$dimen.dp_20), 0, 0);
        this.q.m(0, (int) getResources().getDimension(R$dimen.dp_30), 0, 0);
        Resources resources = getResources();
        int i4 = R$dimen.dp_285;
        int dimension = (int) resources.getDimension(i4);
        if (i2 == 1) {
            this.f3589b.t();
            this.i.setText("100%");
            dimension = (int) getResources().getDimension(i4);
            textView.setText(R$string.nfc_open_result_success);
            textView2.setVisibility(8);
            imageView.setBackgroundResource(R$drawable.icon_dark_public_green_cherk_center_white_72);
            this.q.l();
            this.q.p(getString(R$string.digital_key_kown_s, 5L));
            X(this.q);
            this.q.q(new l());
        } else if (i2 == 2) {
            textView.setText(R$string.nfc_opening_failed);
            textView2.setVisibility(0);
            imageView.setBackgroundResource(R$drawable.icon_dark_public_redx_center_white_72);
            this.q.o(R$string.nfc_sure_retry);
            this.q.q(new b());
            this.q.n(new c());
            if (i3 == 2) {
                dimension = (int) getResources().getDimension(R$dimen.dp_370);
                textView2.setText(R$string.digital_key_open_error_2);
            } else {
                int iB = a.a.a.a(getContext()).b();
                String string = getString(R$string.nfc_brand_byd);
                if (iB == 1) {
                    string = getString(R$string.nfc_brand_denz);
                }
                dimension = (int) getResources().getDimension(R$dimen.dp_400);
                textView2.setText(getString(R$string.digital_key_open_error_3_2, string));
                textView2.setMovementMethod(ScrollingMovementMethod.getInstance());
                textView2.setScrollbarFadingEnabled(false);
            }
        }
        this.u = this.q;
        if (getActivity() != null && !getActivity().isFinishing()) {
            if (this.q.isShowing()) {
                b.b.a.f.f.b(this.f3588a, "mPairResultDialog is showing", new Object[0]);
            } else {
                this.q.dismiss();
                this.q.show();
                this.q.s((int) getResources().getDimension(R$dimen.dp_560), dimension);
            }
        }
        S();
    }

    public final void V() {
        b.b.a.j.b.a aVar = this.s;
        if (aVar != null && aVar.isShowing()) {
            b.b.a.f.f.b(this.f3588a, "120s倒计时时间超时弹框正在显示，配对过程响应超时提示return", new Object[0]);
            return;
        }
        if (this.r == null) {
            this.r = new b.b.a.j.b.a(getActivity());
        }
        this.r.setTitle(R$string.nfc_tip);
        this.r.t(R$string.digital_key_open_prepare_timeout);
        this.r.o(R$string.nfc_sure_retry);
        this.r.setCancelable(false);
        this.r.h().setTag(8);
        this.r.q(new f());
        this.r.n(new g());
        b.b.a.j.b.a aVar2 = this.r;
        this.u = aVar2;
        if (aVar2.isShowing()) {
            b.b.a.f.f.b(this.f3588a, "mPairTimeOutDialog is showing", new Object[0]);
        } else {
            this.r.dismiss();
            this.r.show();
        }
        S();
    }

    public final void W() {
        CountDownTimer countDownTimer = this.j;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        b.b.a.j.b.a aVar = this.r;
        if (aVar != null && aVar.isShowing()) {
            b.b.a.f.f.b(this.f3588a, "配对过程响应超时弹框正在显示，120s倒计时超时弹框return", new Object[0]);
            return;
        }
        if (this.s == null) {
            this.s = new b.b.a.j.b.a(getActivity());
        }
        this.s.setTitle(R$string.nfc_tip);
        this.s.t(R$string.nfc_check_timeout);
        this.s.o(R$string.nfc_sure_retry);
        this.s.setCancelable(false);
        this.s.h().setTag(9);
        this.s.q(new i());
        this.s.n(new j());
        b.b.a.j.b.a aVar2 = this.s;
        this.u = aVar2;
        if (aVar2.isShowing()) {
            b.b.a.f.f.b(this.f3588a, "mCheckKeyTimeoutDialog is showing", new Object[0]);
        } else {
            this.s.dismiss();
            this.s.show();
        }
    }

    public final void X(b.b.a.j.b.a aVar) {
        if (this.k == null) {
            this.k = new d(5000L, 1000L, aVar);
        }
        this.k.cancel();
        this.k.start();
    }

    public final void Y(int i2) {
        this.f3592e.pauseAnimation();
        if (i2 == 1) {
            this.f3593f.setText(getString(R$string.nfc_open_step1, Long.valueOf(this.p / 1000)));
            int i3 = this.o;
            if (i3 != 123) {
                if (i3 != 143) {
                    if (i3 != 180) {
                        if (i3 != 182) {
                            if (i3 != 208) {
                                if (this.n) {
                                    this.f3592e.setAnimation(R$raw.app_pair_prepare);
                                } else {
                                    this.f3592e.setAnimation(R$raw.pad_pair_prepare);
                                }
                            } else if (this.n) {
                                this.f3592e.setAnimation(R$raw.uke_right_app_pair_prepare);
                            } else {
                                this.f3592e.setAnimation(R$raw.uke_right_pad_pair_prepare);
                            }
                        } else if (this.n) {
                            this.f3592e.setAnimation(R$raw.uke_left_app_pair_prepare);
                        } else {
                            this.f3592e.setAnimation(R$raw.uke_left_pad_pair_prepare);
                        }
                    } else if (this.n) {
                        this.f3592e.setAnimation(R$raw.mc_app_pair_prepare);
                    } else {
                        this.f3592e.setAnimation(R$raw.mc_pad_pair_prepare);
                    }
                } else if (this.n) {
                    this.f3592e.setAnimation(R$raw.pa_app_pair_prepare);
                } else {
                    this.f3592e.setAnimation(R$raw.pa_pad_pair_prepare);
                }
            } else if (this.n) {
                this.f3592e.setAnimation(R$raw.mr_right_app_pair_prepare);
            } else {
                this.f3592e.setAnimation(R$raw.mr_right_pad_pair_prepare);
            }
        } else {
            this.f3593f.setText(getString(R$string.digital_key_open_step2));
            int i4 = this.o;
            if (i4 == 123) {
                this.f3592e.setAnimation(R$raw.mr_right_pad_pair_confirm);
            } else if (i4 == 143) {
                this.f3592e.setAnimation(R$raw.pa_pad_pair_confirm);
            } else if (i4 == 180) {
                this.f3592e.setAnimation(R$raw.mc_pad_pair_confirm);
            } else if (i4 == 182) {
                this.f3592e.setAnimation(R$raw.uke_left_pad_pair_confirm);
            } else if (i4 != 208) {
                this.f3592e.setAnimation(R$raw.pad_pair_confirm);
            } else {
                this.f3592e.setAnimation(R$raw.uke_right_pad_pair_confirm);
            }
        }
        if (i2 == 1) {
            b.b.a.f.f.b(this.f3588a, "startOpeningLottie-duration:" + this.p, new Object[0]);
            if (this.j == null) {
                this.j = new h(this.p, 1000L);
            }
            this.j.cancel();
            this.j.start();
        }
        this.f3592e.loop(true);
        this.f3592e.playAnimation();
    }

    public final void Z() {
        this.g.setVisibility(8);
        this.f3591d.setVisibility(0);
        if (this.n) {
            b.b.a.f.f.b(this.f3588a, "2024-03-01,同步产品通知,APP触发的开卡无需寻钥匙", new Object[0]);
        } else {
            this.f3589b.z(1);
        }
        Y(1);
    }

    public final void a0() {
        this.g.setVisibility(8);
        this.f3591d.setVisibility(0);
        Y(2);
        CountDownTimer countDownTimer = this.j;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.f3589b.A();
    }

    public final void b0() {
        if (!b.b.a.h.j.c(this.z)) {
            if ("90%".equals(this.z)) {
                this.i.setText(this.z);
                return;
            }
            if ("50%".equals(this.z)) {
                this.y = TsNormalNum.THOUSAND_FIVE;
            } else if ("35%".equals(this.z)) {
                this.y = TsNormalNum.THOUSAND_SIX;
            } else if ("18%".equals(this.z)) {
                this.y = 7000;
            }
            b.b.a.f.f.b(this.f3588a, "updateLoadingProgress:" + this.y, new Object[0]);
        }
        k kVar = new k(this.y, 1000L);
        this.l = kVar;
        kVar.cancel();
        this.l.start();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            int i2 = bundle.getInt("dialog_restore_bundle_key", 0);
            b.b.a.f.f.b(this.f3588a, "onActivityCreated dialog：%d:", Integer.valueOf(i2));
            if (i2 == 8) {
                V();
                return;
            }
            if (i2 == 9) {
                W();
                return;
            }
            if (i2 == 2) {
                T();
                return;
            }
            if (i2 == 10) {
                this.g.setVisibility(0);
                this.f3591d.setVisibility(8);
                this.v = bundle.getInt("pair_result");
                int i3 = bundle.getInt("pair_error_code");
                this.w = i3;
                U(this.v, i3);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R$id.tv_cancel_activate) {
            this.f3589b.z(2);
            if (getActivity() != null) {
                getActivity().finish();
            }
        }
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R$layout.fragment_key_activate, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R$id.tv_cancel_activate);
        this.f3590c = textView;
        textView.setOnClickListener(this);
        this.f3591d = viewInflate.findViewById(R$id.pre_loading_ll);
        this.f3592e = (LottieAnimationView) viewInflate.findViewById(R$id.digital_key_activate_lottie);
        this.f3593f = (TextView) viewInflate.findViewById(R$id.tv_des);
        this.g = viewInflate.findViewById(R$id.loading_ll);
        this.h = (ImageView) viewInflate.findViewById(R$id.iv_loading);
        this.i = (TextView) viewInflate.findViewById(R$id.open_loading_progress_tv);
        b.b.j.c cVarI = b.b.j.c.i(getContext());
        this.f3589b = cVarI;
        cVarI.v(this.A);
        this.o = this.f3589b.e(getContext());
        if (bundle != null) {
            this.p = bundle.getLong("duration");
            this.x = bundle.getBoolean("is_key_opening");
            this.z = bundle.getString("loading_tick");
            b.b.a.f.f.b(this.f3588a, "onCreateView-duration:" + this.p + ",loadingTick:" + this.z, new Object[0]);
        }
        if (getArguments() != null) {
            this.m = getArguments().getInt("pair_state", 1);
            this.n = getArguments().getBoolean("pair_from");
            b.b.a.f.f.b(this.f3588a, "mPairState:" + this.m + ",mPairFromApp:" + this.n, new Object[0]);
        }
        if (this.m == 3 || this.x) {
            Q();
        } else {
            O();
        }
        EventBus.getDefault().register(this);
        return viewInflate;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        this.f3589b.C(this.A);
        this.h.clearAnimation();
        this.f3592e.clearAnimation();
        CountDownTimer countDownTimer = this.j;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = this.k;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        CountDownTimer countDownTimer3 = this.l;
        if (countDownTimer3 != null) {
            countDownTimer3.cancel();
        }
        b.b.a.j.b.a aVar = this.u;
        if (aVar == null || !aVar.isShowing()) {
            return;
        }
        this.u.dismiss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.j.f fVar) {
        if (!isResumed()) {
            b.b.a.f.f.b(this.f3588a, "not on resume,return!!", new Object[0]);
        } else {
            if (fVar.a() != 250001) {
                return;
            }
            N(this.f3589b.j());
        }
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        S();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        int iO = this.f3589b.o();
        b.b.a.f.f.b(this.f3588a, "onResume-result:" + iO, new Object[0]);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong("duration", this.p);
        bundle.putInt("pair_result", this.v);
        bundle.putInt("pair_error_code", this.w);
        bundle.putBoolean("is_key_opening", this.x);
        bundle.putString("loading_tick", this.i.getText().toString());
        b.b.a.f.f.b(this.f3588a, "onSaveInstanceState-duration:" + this.p + ",loading_tick:" + this.z, new Object[0]);
        b.b.a.j.b.a aVar = this.u;
        if (aVar == null || !aVar.isShowing()) {
            return;
        }
        bundle.putInt("dialog_restore_bundle_key", ((Integer) this.u.h().getTag()).intValue());
    }
}
