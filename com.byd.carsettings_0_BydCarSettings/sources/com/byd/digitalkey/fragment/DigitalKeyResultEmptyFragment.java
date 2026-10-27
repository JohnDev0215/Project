package com.byd.digitalkey.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.j.b.a;
import com.byd.base.view.BaseWebViewActivity;
import com.byd.digitalkey.R$color;
import com.byd.digitalkey.R$drawable;
import com.byd.digitalkey.R$id;
import com.byd.digitalkey.R$layout;
import com.byd.digitalkey.R$string;
import com.byd.digitalkey.activity.DigitalKeyActivateNewActivity;
import com.byd.digitalkey.activity.UserAgreementActivity;
import com.byd.digitalkey.activity.UserHelpActivity;
import com.bydux.api.common.views.window.BydFragment;
import com.bydux.api.common.views.window.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnPageChangeListener;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class DigitalKeyResultEmptyFragment extends BydFragment implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.j.c f3571b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f3572c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Banner f3573d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CircleIndicator f3574e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f3575f;
    public ImageView g;
    public TextView h;
    public TextView i;
    public b.b.a.j.b.a j;
    public b.b.a.j.b.a k;
    public b.b.a.j.b.a l;
    public b.b.a.j.b.a m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3570a = getClass().getSimpleName();
    public final b.b.j.a n = new c();

    public class a implements Runnable {

        /* JADX INFO: renamed from: com.byd.digitalkey.fragment.DigitalKeyResultEmptyFragment$a$a, reason: collision with other inner class name */
        public class C0097a implements a.g {
            public C0097a() {
            }

            @Override // b.b.a.j.b.a.g
            public void a(View view) {
                DigitalKeyResultEmptyFragment.this.S();
            }
        }

        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DigitalKeyResultEmptyFragment.this.k == null) {
                DigitalKeyResultEmptyFragment digitalKeyResultEmptyFragment = DigitalKeyResultEmptyFragment.this;
                digitalKeyResultEmptyFragment.k = new b.b.a.j.b.a(digitalKeyResultEmptyFragment.getActivity());
            }
            DigitalKeyResultEmptyFragment.this.k.setTitle(R$string.nfc_tip);
            DigitalKeyResultEmptyFragment.this.k.t(R$string.nfc_key_not_in_car);
            DigitalKeyResultEmptyFragment.this.k.o(R$string.nfc_sure_retry);
            DigitalKeyResultEmptyFragment.this.k.q(new C0097a());
            DigitalKeyResultEmptyFragment.this.k.h().setTag(2);
            if (DigitalKeyResultEmptyFragment.this.k.isShowing()) {
                b.b.a.f.f.b(DigitalKeyResultEmptyFragment.this.f3570a, "mKeyNotInCarDialog isShowing", new Object[0]);
            } else {
                DigitalKeyResultEmptyFragment.this.k.show();
            }
            DigitalKeyResultEmptyFragment digitalKeyResultEmptyFragment2 = DigitalKeyResultEmptyFragment.this;
            digitalKeyResultEmptyFragment2.m = digitalKeyResultEmptyFragment2.k;
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DigitalKeyResultEmptyFragment.this.l == null) {
                DigitalKeyResultEmptyFragment digitalKeyResultEmptyFragment = DigitalKeyResultEmptyFragment.this;
                digitalKeyResultEmptyFragment.l = new b.b.a.j.b.a(digitalKeyResultEmptyFragment.getActivity());
            }
            DigitalKeyResultEmptyFragment.this.l.l();
            DigitalKeyResultEmptyFragment.this.l.setTitle(R$string.nfc_opening_failed);
            DigitalKeyResultEmptyFragment.this.l.t(R$string.nfc_opening_failed_details);
            DigitalKeyResultEmptyFragment.this.l.o(R$string.digital_key_kown);
            DigitalKeyResultEmptyFragment.this.l.h().setTag(3);
            if (DigitalKeyResultEmptyFragment.this.l.isShowing()) {
                b.b.a.f.f.b(DigitalKeyResultEmptyFragment.this.f3570a, "mKeyErrorDialog isShowing", new Object[0]);
            } else {
                DigitalKeyResultEmptyFragment.this.l.show();
            }
            DigitalKeyResultEmptyFragment digitalKeyResultEmptyFragment2 = DigitalKeyResultEmptyFragment.this;
            digitalKeyResultEmptyFragment2.m = digitalKeyResultEmptyFragment2.l;
        }
    }

    public class c extends b.b.j.a {
        public c() {
        }

        @Override // b.b.j.a, android.hardware.bydauto.digital.IDigitalKeyLisenter
        public void onKeyFindingResults(int i, int i2) {
            super.onKeyFindingResults(i, i2);
            if (i == 0) {
                return;
            }
            b.b.j.e.b(250001);
            if (DigitalKeyResultEmptyFragment.this.isResumed()) {
                DigitalKeyResultEmptyFragment.this.K(i);
            } else {
                b.b.a.f.f.b(DigitalKeyResultEmptyFragment.this.f3570a, "not on resume,return!!", new Object[0]);
            }
        }

        @Override // b.b.j.a, android.hardware.bydauto.digital.IDigitalKeyLisenter
        public void onOwnerKeyIdentifierResult(int i, int i2) {
            super.onOwnerKeyIdentifierResult(i, i2);
            if (i == 0) {
                return;
            }
            b.b.j.e.b(250000);
            DigitalKeyResultEmptyFragment.this.O();
            if (DigitalKeyResultEmptyFragment.this.isResumed()) {
                DigitalKeyResultEmptyFragment.this.L(i);
            } else {
                b.b.a.f.f.b(DigitalKeyResultEmptyFragment.this.f3570a, "not on resume,return!!", new Object[0]);
            }
        }
    }

    public class d extends ClickableSpan {
        public d() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(@NonNull View view) {
            DigitalKeyResultEmptyFragment digitalKeyResultEmptyFragment = DigitalKeyResultEmptyFragment.this;
            digitalKeyResultEmptyFragment.startActivity(new Intent(digitalKeyResultEmptyFragment.getContext(), (Class<?>) UserHelpActivity.class));
        }
    }

    public class e extends ClickableSpan {
        public e() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(@NonNull View view) {
            BaseWebViewActivity.v(DigitalKeyResultEmptyFragment.this.getActivity(), b.b.j.h.a.a(DigitalKeyResultEmptyFragment.this.getActivity()), DigitalKeyResultEmptyFragment.this.getResources().getString(R$string.nfc_user_agreement));
        }
    }

    public class f extends BannerImageAdapter {
        public f(List list) {
            super(list);
        }

        @Override // com.youth.banner.holder.IViewHolder
        public void onBindView(Object obj, Object obj2, int i, int i2) {
            BannerImageHolder bannerImageHolder = (BannerImageHolder) obj;
            bannerImageHolder.imageView.setBackground((Drawable) obj2);
            bannerImageHolder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }

    public class g implements OnPageChangeListener {
        public g() {
        }

        @Override // com.youth.banner.listener.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // com.youth.banner.listener.OnPageChangeListener
        public void onPageScrolled(int i, float f2, int i2) {
        }

        @Override // com.youth.banner.listener.OnPageChangeListener
        public void onPageSelected(int i) {
            System.out.println("Banner onPageSelected:" + i);
            if (i == 0) {
                DigitalKeyResultEmptyFragment.this.h.setText(DigitalKeyResultEmptyFragment.this.getString(R$string.nfc_banner_1_des));
            } else if (i == 1) {
                DigitalKeyResultEmptyFragment.this.h.setText(DigitalKeyResultEmptyFragment.this.getString(R$string.nfc_banner_2_des));
            } else if (i == 2) {
                DigitalKeyResultEmptyFragment.this.h.setText(DigitalKeyResultEmptyFragment.this.getString(R$string.nfc_banner_3_des));
            }
        }
    }

    public class h implements Runnable {
        public h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DigitalKeyResultEmptyFragment.this.T();
            DigitalKeyResultEmptyFragment.this.f3571b.z(1);
            DigitalKeyResultEmptyFragment.this.f3572c.setEnabled(false);
        }
    }

    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DigitalKeyResultEmptyFragment.this.g.clearAnimation();
            DigitalKeyResultEmptyFragment.this.f3575f.setVisibility(8);
            DigitalKeyResultEmptyFragment.this.f3572c.setEnabled(true);
        }
    }

    public static DigitalKeyResultEmptyFragment N() {
        return new DigitalKeyResultEmptyFragment();
    }

    public final void I() {
        String string = getContext().getString(R$string.nfc_phone_mode);
        String string2 = getContext().getString(R$string.nfc_use_tips);
        getContext().getString(R$string.nfc_phone_modele);
        String string3 = getContext().getString(R$string.nfc_use_service_proto);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        SpannableString spannableString = new SpannableString(string2);
        Context context = getContext();
        int i2 = R$color.nfc_apple_support_adapter_color;
        spannableString.setSpan(new ForegroundColorSpan(context.getColor(i2)), 0, string2.length(), 17);
        SpannableString spannableString2 = new SpannableString(string3);
        spannableString2.setSpan(new ForegroundColorSpan(getContext().getColor(i2)), 0, string3.length(), 17);
        spannableString.setSpan(new d(), 0, string2.length(), 17);
        spannableString2.setSpan(new e(), 0, string3.length(), 17);
        spannableString.setSpan(new UnderlineSpan() { // from class: com.byd.digitalkey.fragment.DigitalKeyResultEmptyFragment.4
            @Override // android.text.style.UnderlineSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        }, 0, string2.length(), 17);
        spannableString2.setSpan(new UnderlineSpan() { // from class: com.byd.digitalkey.fragment.DigitalKeyResultEmptyFragment.5
            @Override // android.text.style.UnderlineSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        }, 0, string3.length(), 17);
        spannableStringBuilder.append((CharSequence) spannableString);
        spannableStringBuilder.append((CharSequence) " ");
        this.i.setText(spannableStringBuilder);
        this.i.setMovementMethod(LinkMovementMethod.getInstance());
        this.i.setHighlightColor(0);
    }

    public String J() {
        return this.f3570a;
    }

    public final void K(int i2) {
        if (1 == i2) {
            if (getActivity() != null) {
                startActivity(new Intent(getActivity(), (Class<?>) DigitalKeyActivateNewActivity.class));
            }
        } else if (2 == i2 || 3 == i2) {
            Q();
        }
        O();
    }

    public final void L(int i2) {
        if (1 == i2 || i2 == 0) {
            P();
            O();
        } else if (2 == i2) {
            S();
        }
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public final void M(int i2) {
        ArrayList arrayList = new ArrayList();
        if (getContext() != null) {
            if (i2 == 123) {
                arrayList.add(getContext().getDrawable(R$drawable.mr_right_nfc_banner_1));
                arrayList.add(getContext().getDrawable(R$drawable.mr_right_nfc_banner_2));
                arrayList.add(getContext().getDrawable(R$drawable.mr_right_nfc_banner_3));
            } else if (i2 == 143) {
                arrayList.add(getContext().getDrawable(R$drawable.pa_nfc_banner_1));
                arrayList.add(getContext().getDrawable(R$drawable.pa_nfc_banner_2));
                arrayList.add(getContext().getDrawable(R$drawable.pa_nfc_banner_3));
            } else if (i2 == 180) {
                arrayList.add(getContext().getDrawable(R$drawable.mc_nfc_banner_1));
                arrayList.add(getContext().getDrawable(R$drawable.mc_nfc_banner_2));
                arrayList.add(getContext().getDrawable(R$drawable.mc_nfc_banner_3));
            } else if (i2 == 182) {
                arrayList.add(getContext().getDrawable(R$drawable.uke_left_nfc_banner_1));
                arrayList.add(getContext().getDrawable(R$drawable.uke_left_nfc_banner_2));
                arrayList.add(getContext().getDrawable(R$drawable.uke_left_nfc_banner_3));
            } else if (i2 != 208) {
                arrayList.add(getContext().getDrawable(R$drawable.nfc_banner_1));
                arrayList.add(getContext().getDrawable(R$drawable.nfc_banner_2));
                arrayList.add(getContext().getDrawable(R$drawable.nfc_banner_3));
            } else {
                arrayList.add(getContext().getDrawable(R$drawable.uke_right_nfc_banner_1));
                arrayList.add(getContext().getDrawable(R$drawable.uke_right_nfc_banner_2));
                arrayList.add(getContext().getDrawable(R$drawable.uke_right_nfc_banner_3));
            }
        }
        this.f3573d.setIndicator(new CircleIndicator(getContext()), true);
        this.f3573d.setIndicatorGravity(2);
        this.f3573d.setLoopTime(3000L);
        this.f3573d.setAdapter(new f(arrayList));
        this.f3573d.addOnPageChangeListener(new g());
    }

    public final void O() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new i());
        }
    }

    public final void P() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new b());
        }
    }

    public final void Q() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new a());
        }
    }

    public final void R() {
        if (this.j == null) {
            this.j = new b.b.a.j.b.a(getActivity());
        }
        this.j.setTitle(R$string.nfc_tip);
        this.j.t(R$string.nfc_safe_diviver_tips);
        this.j.l();
        this.j.o(R$string.digital_key_kown);
        this.j.h().setTag(1);
        if (this.j.isShowing()) {
            b.b.a.f.f.b(this.f3570a, "mNotSafeGearDialog isShowing", new Object[0]);
        } else {
            this.j.show();
        }
        this.m = this.j;
    }

    public final void S() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new h());
        }
    }

    public final void T() {
        if (this.f3575f.getVisibility() == 0) {
            return;
        }
        this.f3575f.setVisibility(0);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1500L);
        rotateAnimation.setRepeatCount(-1);
        this.g.startAnimation(rotateAnimation);
        this.f3572c.setEnabled(false);
    }

    public void U() {
        b.b.j.c cVar = this.f3571b;
        if (cVar == null) {
            b.b.a.f.f.a(this.f3570a, "carDigitalKeyImp is null", new Object[0]);
        } else if (!cVar.q(getActivity())) {
            R();
        } else {
            this.f3571b.u(1);
            T();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            int i2 = bundle.getInt("dialog_restore_bundle_key", 0);
            b.b.a.f.f.b(this.f3570a, "onActivityCreated dialog：%d:", Integer.valueOf(i2));
            if (i2 == 1) {
                R();
            } else if (i2 == 2) {
                Q();
            } else if (i2 == 3) {
                P();
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R$id.add_digital_key_btn || getContext() == null || getActivity() == null) {
            return;
        }
        Intent intent = new Intent(getActivity(), (Class<?>) UserAgreementActivity.class);
        intent.putExtra("from_source_page", 1);
        getActivity().startActivityForResult(intent, 1);
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R$layout.fragment_digital_key_empty, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R$id.add_digital_key_btn);
        this.f3572c = textView;
        textView.setOnClickListener(this);
        viewInflate.findViewById(R$id.tv_user_report).setOnClickListener(this);
        this.f3573d = (Banner) viewInflate.findViewById(R$id.banner_view);
        this.h = (TextView) viewInflate.findViewById(R$id.tv_banner_des);
        this.f3574e = (CircleIndicator) viewInflate.findViewById(R$id.circle_indicator);
        this.f3575f = viewInflate.findViewById(R$id.loading_ll);
        this.g = (ImageView) viewInflate.findViewById(R$id.iv_loading);
        this.i = (TextView) viewInflate.findViewById(R$id.nfc_activate_service_text);
        this.f3575f.setVisibility(8);
        b.b.j.c cVarI = b.b.j.c.i(getContext());
        this.f3571b = cVarI;
        M(cVarI.e(getContext()));
        EventBus.getDefault().register(this);
        I();
        this.f3571b.v(this.n);
        return viewInflate;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f3571b.C(this.n);
        this.f3573d.destroy();
        EventBus.getDefault().unregister(this);
        b.b.a.j.b.a aVar = this.m;
        if (aVar == null || !aVar.isShowing()) {
            return;
        }
        this.m.dismiss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.j.f fVar) {
        if (!isResumed()) {
            O();
            b.b.a.f.f.b(this.f3570a, "not on resume,return!!", new Object[0]);
        }
        switch (fVar.a()) {
            case 250000:
                ToastUtils.show(getContext(), getString(R$string.nfc_func_not_config));
                O();
                break;
            case 250001:
                K(this.f3571b.j());
                break;
        }
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.f3573d.stop();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.f3573d.start();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        b.b.a.j.b.a aVar = this.m;
        if (aVar == null || !aVar.isShowing()) {
            return;
        }
        this.m.dismiss();
        bundle.putInt("dialog_restore_bundle_key", ((Integer) this.m.h().getTag()).intValue());
    }
}
