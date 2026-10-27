package org.chromium.chrome.browser.payments.ui;

import J.N;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;
import defpackage.ao3;
import defpackage.dh2;
import defpackage.g71;
import defpackage.go0;
import defpackage.h21;
import defpackage.hy1;
import defpackage.in3;
import defpackage.lf0;
import defpackage.o4;
import defpackage.om3;
import defpackage.qp;
import defpackage.sl2;
import defpackage.tf0;
import defpackage.tl2;
import defpackage.yq;
import defpackage.z7;
import defpackage.za3;
import java.util.ArrayList;
import java.util.WeakHashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.payments.ui.PaymentRequestSection;
import org.chromium.chrome.browser.payments.ui.a;
import org.chromium.chrome.browser.payments.ui.a.C0106a;
import org.chromium.chrome.browser.payments.ui.j;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;
import org.chromium.components.omnibox.OmniboxUrlEmphasizer;
import org.chromium.components.payments.PaymentApp;

/* JADX INFO: compiled from: PaymentRequestUI.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e implements a.c, View.OnClickListener, PaymentRequestSection.a, hy1 {
    public PaymentRequestSection.OptionSection A;
    public PaymentRequestSection.OptionSection B;
    public PaymentRequestSection.OptionSection C;
    public ArrayList D;
    public PaymentRequestSection E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f75J;
    public boolean K;
    public boolean L;
    public dh2 M;
    public dh2 N;
    public dh2 O;
    public dh2 P;
    public Animator Q;
    public go0 R;
    public final Activity f;
    public final b g;
    public final boolean h;
    public final j.d i;
    public final Profile j;
    public final org.chromium.chrome.browser.payments.ui.a k;
    public final tf0 l;
    public final ViewGroup m;
    public final sl2 o;
    public final int p;
    public FadingEdgeScrollView q;
    public LinearLayout r;
    public TextView s;
    public ViewGroup t;
    public Button u;
    public Button v;
    public View w;
    public View x;
    public PaymentRequestSection.LineItemBreakdownSection y;
    public PaymentRequestSection.OptionSection z;
    public final c e = new c(new org.chromium.chrome.browser.payments.ui.b(this));
    public final org.chromium.chrome.browser.payments.ui.c n = new org.chromium.chrome.browser.payments.ui.c(this);

    /* JADX INFO: compiled from: PaymentRequestUI.java */
    public class a implements Callback<tl2> {
        public a() {
        }

        @Override // org.chromium.base.Callback
        public final void onResult(tl2 tl2Var) {
            e eVar = e.this;
            eVar.k(tl2Var);
            eVar.p();
        }
    }

    /* JADX INFO: compiled from: PaymentRequestUI.java */
    public interface b {
    }

    /* JADX INFO: compiled from: PaymentRequestUI.java */
    public static class c {
        public final Handler a = new Handler();
        public final g b;
        public boolean c;

        public c(org.chromium.chrome.browser.payments.ui.b bVar) {
            this.b = new g(this, bVar);
        }
    }

    /* JADX INFO: compiled from: PaymentRequestUI.java */
    public class d extends AnimatorListenerAdapter implements View.OnLayoutChangeListener {
        public d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            e.this.Q = null;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            e eVar = e.this;
            eVar.m.removeOnLayoutChangeListener(this);
            eVar.Q = ObjectAnimator.ofFloat(eVar.m, (Property<ViewGroup, Float>) View.TRANSLATION_Y, eVar.p, 0.0f);
            eVar.Q.setDuration(225L);
            eVar.Q.setInterpolator(h21.g);
            eVar.Q.addListener(this);
            eVar.Q.start();
        }
    }

    /* JADX INFO: renamed from: org.chromium.chrome.browser.payments.ui.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PaymentRequestUI.java */
    public class ViewOnLayoutChangeListenerC0107e extends AnimatorListenerAdapter implements View.OnLayoutChangeListener {
        public final boolean e;
        public int f;

        /* JADX INFO: renamed from: org.chromium.chrome.browser.payments.ui.e$e$a */
        /* JADX INFO: compiled from: PaymentRequestUI.java */
        public class a implements ValueAnimator.AnimatorUpdateListener {
            public a() {
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ViewOnLayoutChangeListenerC0107e viewOnLayoutChangeListenerC0107e = ViewOnLayoutChangeListenerC0107e.this;
                float f = viewOnLayoutChangeListenerC0107e.f * fFloatValue;
                e eVar = e.this;
                eVar.m.setTranslationY(f);
                if (viewOnLayoutChangeListenerC0107e.e) {
                    eVar.t.setTranslationY(-f);
                    eVar.q.setBottom(Math.min(eVar.q.getMeasuredHeight() + eVar.q.getTop(), eVar.t.getTop()));
                }
            }
        }

        public ViewOnLayoutChangeListenerC0107e(boolean z) {
            this.e = z;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            e eVar = e.this;
            eVar.m.setTranslationY(0.0f);
            eVar.t.setTranslationY(0.0f);
            ao3.e(eVar.m, "PaymentRequestUI.SheetEnlargingAnimator.onAnimationEnd");
            eVar.Q = null;
            c cVar = eVar.e;
            if (cVar.c) {
                return;
            }
            cVar.c = true;
            cVar.a.post(cVar.b);
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            e eVar = e.this;
            Animator animator = eVar.Q;
            if (animator != null) {
                animator.cancel();
            }
            eVar.m.removeOnLayoutChangeListener(this);
            this.f = (i4 - i2) - (i8 - i6);
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            valueAnimatorOfFloat.addUpdateListener(new a());
            eVar.Q = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(225L);
            eVar.Q.setInterpolator(h21.g);
            eVar.Q.addListener(this);
            eVar.Q.start();
        }
    }

    public e(ChromeActivity chromeActivity, j jVar, boolean z, String str, String str2, int i, sl2 sl2Var, j.d dVar, Profile profile) {
        this.f = chromeActivity;
        this.g = jVar;
        this.h = z;
        this.p = chromeActivity.getResources().getDimensionPixelSize(R$dimen.payments_ui_translation);
        this.j = profile;
        this.o = sl2Var;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(chromeActivity).inflate(R$layout.payment_request, (ViewGroup) null);
        this.m = viewGroup;
        this.x = viewGroup.findViewById(R$id.payment_request_spinny);
        this.f75J = true;
        ((TextView) viewGroup.findViewById(R$id.message)).setText(R$string.payments_loading_message);
        PaymentRequestHeader paymentRequestHeader = (PaymentRequestHeader) viewGroup.findViewById(R$id.header);
        ((TextView) paymentRequestHeader.findViewById(R$id.page_title)).setText(str);
        TextView textView = (TextView) paymentRequestHeader.findViewById(R$id.hostname);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str2);
        boolean z2 = !z7.h0(paymentRequestHeader.e);
        qp qpVar = new qp(profile);
        OmniboxUrlEmphasizer.a(spannableStringBuilder, paymentRequestHeader.f, qpVar, i, z2, true);
        qpVar.a();
        textView.setText(spannableStringBuilder);
        if (str2.startsWith("https://")) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(za3.b(paymentRequestHeader.f, R$drawable.omnibox_https_valid, R$color.default_green), (Drawable) null, (Drawable) null, (Drawable) null);
            textView.setPaddingRelative(0, 0, 0, 0);
        }
        View viewFindViewById = viewGroup.findViewById(R$id.close_button);
        this.w = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R$id.bottom_bar);
        this.t = viewGroup2;
        Button button = (Button) viewGroup2.findViewById(R$id.button_primary);
        this.v = button;
        button.setOnClickListener(this);
        this.v.setText(R$string.payments_continue_button);
        Button button2 = (Button) this.t.findViewById(R$id.button_secondary);
        this.u = button2;
        button2.setOnClickListener(this);
        this.D = new ArrayList();
        this.q = (FadingEdgeScrollView) viewGroup.findViewById(R$id.option_container);
        this.r = (LinearLayout) viewGroup.findViewById(R$id.payment_container_layout);
        this.s = (TextView) viewGroup.findViewById(R$id.retry_error);
        this.y = new PaymentRequestSection.LineItemBreakdownSection(chromeActivity, chromeActivity.getString(R$string.payments_order_summary_label), this, chromeActivity.getString(R$string.payments_updated_label));
        this.z = new PaymentRequestSection.OptionSection(chromeActivity, chromeActivity.getString(sl2Var.a), this);
        this.A = new PaymentRequestSection.OptionSection(chromeActivity, chromeActivity.getString(sl2Var.b), this);
        this.B = new PaymentRequestSection.OptionSection(chromeActivity, chromeActivity.getString(R$string.payments_contact_details_label), this);
        this.C = new PaymentRequestSection.OptionSection(chromeActivity, chromeActivity.getString(R$string.payments_method_of_payment_label), this);
        this.z.setDisplaySummaryInSingleLineInNormalMode(false);
        this.A.setSplitSummaryInDisplayModeNormal(true);
        this.A.setCanAddItems(false);
        this.C.setCanAddItems(false);
        boolean zM1X7xdZV = N.M1X7xdZV("WebPaymentsMethodSectionOrderV2");
        this.r.addView(this.y, new LinearLayout.LayoutParams(-1, -2));
        if (zM1X7xdZV) {
            this.D.add(new PaymentRequestSection.SectionSeparator(-1, this.r));
            this.r.addView(this.C, new LinearLayout.LayoutParams(-1, -2));
        }
        PaymentRequestSection.SectionSeparator sectionSeparator = new PaymentRequestSection.SectionSeparator(-1, this.r);
        this.D.add(sectionSeparator);
        this.r.addView(this.z, new LinearLayout.LayoutParams(-1, -2));
        if (!jVar.w()) {
            this.z.setVisibility(8);
            sectionSeparator.setVisibility(8);
        }
        if (!zM1X7xdZV) {
            this.D.add(new PaymentRequestSection.SectionSeparator(-1, this.r));
            this.r.addView(this.C, new LinearLayout.LayoutParams(-1, -2));
        }
        PaymentRequestSection.SectionSeparator sectionSeparator2 = new PaymentRequestSection.SectionSeparator(-1, this.r);
        this.D.add(sectionSeparator2);
        this.r.addView(this.B, new LinearLayout.LayoutParams(-1, -2));
        if (!jVar.v()) {
            this.B.setVisibility(8);
            sectionSeparator2.setVisibility(8);
        }
        viewGroup.addOnLayoutChangeListener(new d());
        this.v.setEnabled(false);
        tf0 tf0Var = new tf0(chromeActivity, null, profile, true);
        this.l = tf0Var;
        Window window = tf0Var.getWindow();
        View rootView = window.getDecorView().getRootView();
        boolean z3 = !z7.h0(window.getStatusBarColor());
        int systemUiVisibility = rootView.getSystemUiVisibility();
        rootView.setSystemUiVisibility(z3 ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
        this.k = new org.chromium.chrome.browser.payments.ui.a(chromeActivity, this);
        this.i = dVar;
    }

    public final void a(boolean z) {
        if (this.f75J == z) {
            return;
        }
        this.f75J = z;
        ViewGroup viewGroup = this.m;
        if (z) {
            this.q.setVisibility(8);
            this.t.setVisibility(8);
            this.w.setVisibility(8);
            this.x.setVisibility(0);
            ((FrameLayout.LayoutParams) viewGroup.getLayoutParams()).height = -2;
            ao3.e(viewGroup, "PaymentRequestUI.changeSpinnerVisibility show");
            return;
        }
        this.q.setVisibility(0);
        this.t.setVisibility(0);
        this.w.setVisibility(0);
        this.x.setVisibility(8);
        if (this.F) {
            ((FrameLayout.LayoutParams) viewGroup.getLayoutParams()).height = -1;
            ao3.e(viewGroup, "PaymentRequestUI.changeSpinnerVisibility expanded to full height");
        }
    }

    public final void b(boolean z) {
        this.L = true;
        org.chromium.chrome.browser.payments.ui.a aVar = this.k;
        if (z) {
            aVar.getClass();
            aVar.new C0106a();
        } else {
            aVar.a.dismiss();
            aVar.a();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(org.chromium.chrome.browser.payments.ui.PaymentRequestSection r12) {
        /*
            Method dump skipped, instruction units count: 333
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.payments.ui.e.c(org.chromium.chrome.browser.payments.ui.PaymentRequestSection):void");
    }

    public final boolean d() {
        return (this.k.e || this.Q != null || this.R != null || this.G || this.K || this.L) ? false : true;
    }

    public final boolean e() {
        return (!d() || this.M == null || this.I) ? false : true;
    }

    @Override // defpackage.hy1
    public final void f() {
        o4 o4Var = this.k.a;
        o4Var.getWindow().setAttributes(o4Var.getWindow().getAttributes());
    }

    public final void g(PaymentRequestSection paymentRequestSection) {
        PaymentRequestSection.OptionSection optionSection = this.z;
        b bVar = this.g;
        r(paymentRequestSection, paymentRequestSection == optionSection ? ((j) bVar).r(this.n, 1) : paymentRequestSection == this.B ? ((j) bVar).r(null, 3) : paymentRequestSection == this.C ? ((j) bVar).r(null, 4) : 3);
    }

    public final void i(String str) {
        TextView textView = this.s;
        if (textView == null) {
            return;
        }
        textView.setText(str);
        if (TextUtils.isEmpty(str)) {
            this.s.setVisibility(8);
            return;
        }
        if (this.F) {
            int dimensionPixelSize = this.f.getResources().getDimensionPixelSize(R$dimen.editor_dialog_section_large_spacing);
            TextView textView2 = this.s;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.e.i(textView2, 0, dimensionPixelSize, 0, dimensionPixelSize);
        } else {
            TextView textView3 = this.s;
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            om3.e.i(textView3, 0, 0, 0, 0);
        }
        this.s.setVisibility(0);
    }

    public final void k(tl2 tl2Var) {
        if (tl2Var == null || tl2Var.a == null) {
            this.y.setVisibility(8);
            return;
        }
        this.y.setVisibility(0);
        PaymentRequestSection.LineItemBreakdownSection lineItemBreakdownSection = this.y;
        Context context = lineItemBreakdownSection.v.getContext();
        g71 g71Var = tl2Var.a;
        SpannableStringBuilder spannableStringBuilderF = PaymentRequestSection.LineItemBreakdownSection.f(g71Var.b, g71Var.c, true);
        if (lineItemBreakdownSection.getSummaryRightTextView().getText() != null && !TextUtils.equals(lineItemBreakdownSection.getSummaryRightTextView().getText(), spannableStringBuilderF) && lineItemBreakdownSection.getSummaryRightTextView().getVisibility() == 0) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(lineItemBreakdownSection.w.getAlpha(), 1.0f);
            alphaAnimation.setDuration(500L);
            alphaAnimation.setInterpolator(h21.g);
            alphaAnimation.setFillAfter(true);
            lineItemBreakdownSection.w.startAnimation(alphaAnimation);
            Handler handler = lineItemBreakdownSection.z;
            PaymentRequestSection.LineItemBreakdownSection.a aVar = lineItemBreakdownSection.y;
            handler.removeCallbacks(aVar);
            handler.postDelayed(aVar, 5000L);
        }
        lineItemBreakdownSection.d(tl2Var.a.a, spannableStringBuilderF);
        lineItemBreakdownSection.v.removeAllViews();
        ArrayList arrayList = lineItemBreakdownSection.x;
        arrayList.clear();
        if (tl2Var.a() == null) {
            return;
        }
        int i = 2;
        int width = (((View) lineItemBreakdownSection.v.getParent()).getWidth() * 2) / 3;
        int size = tl2Var.a().size();
        lineItemBreakdownSection.v.setRowCount(size);
        int i2 = 0;
        while (i2 < size) {
            g71 g71Var2 = tl2Var.a().get(i2);
            TextView textView = new TextView(context);
            textView.setTextAppearance(textView.getContext(), g71Var2.d ? R$style.TextAppearance_PaymentsUiSectionPendingTextEndAligned : R$style.TextAppearance_PaymentsUiSectionDescriptiveTextEndAligned);
            textView.setText(g71Var2.a);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setMaxLines(i);
            if (width > 0) {
                textView.setMaxWidth(width);
            }
            TextView textView2 = new TextView(context);
            textView2.setTextAppearance(textView2.getContext(), g71Var2.d ? R$style.TextAppearance_PaymentsUiSectionPendingTextEndAligned : R$style.TextAppearance_PaymentsUiSectionDescriptiveTextEndAligned);
            textView2.setText(PaymentRequestSection.LineItemBreakdownSection.f(g71Var2.b, g71Var2.c, false));
            arrayList.add(textView2);
            GridLayout.d dVar = GridLayout.z;
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(GridLayout.l(i2, 1, dVar, 0.0f), GridLayout.l(0, 1, dVar, 0.0f));
            GridLayout.LayoutParams layoutParams2 = new GridLayout.LayoutParams(GridLayout.l(i2, 1, dVar, 0.0f), GridLayout.l(1, 1, dVar, 0.0f));
            layoutParams2.setMarginStart(context.getResources().getDimensionPixelSize(R$dimen.payments_section_descriptive_item_spacing));
            lineItemBreakdownSection.v.addView(textView, layoutParams);
            lineItemBreakdownSection.v.addView(textView2, layoutParams2);
            i2++;
            i = 2;
        }
    }

    public final void m() {
        dh2 dh2Var;
        dh2 dh2Var2;
        dh2 dh2Var3;
        dh2 dh2Var4;
        j jVar = (j) this.g;
        boolean z = false;
        boolean z2 = (jVar.v() && ((dh2Var4 = this.P) == null || dh2Var4.b() == null)) ? false : true;
        boolean z3 = (jVar.w() && ((dh2Var3 = this.N) == null || dh2Var3.b() == null)) ? false : true;
        boolean z4 = (jVar.w() && ((dh2Var2 = this.O) == null || dh2Var2.b() == null)) ? false : true;
        Button button = this.v;
        if (z2 && z3 && z4 && (dh2Var = this.M) != null && dh2Var.b() != null && !this.I && !this.K && !this.L) {
            z = true;
        }
        button.setEnabled(z);
        c cVar = this.e;
        if (cVar.c) {
            return;
        }
        cVar.c = true;
        cVar.a.post(cVar.b);
    }

    public final void n(int i, dh2 dh2Var) {
        if (i == 1) {
            this.N = dh2Var;
            this.z.h(dh2Var);
        } else if (i == 2) {
            this.O = dh2Var;
            this.A.h(dh2Var);
            if (((j) this.g).w() && !this.O.a.isEmpty() && this.r.indexOfChild(this.A) == -1) {
                int iIndexOfChild = this.r.indexOfChild(this.z);
                PaymentRequestSection.SectionSeparator sectionSeparator = new PaymentRequestSection.SectionSeparator(iIndexOfChild + 1, this.r);
                this.D.add(sectionSeparator);
                if (this.F) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) sectionSeparator.getLayoutParams();
                    layoutParams.setMarginStart(0);
                    layoutParams.setMarginEnd(0);
                }
                this.r.addView(this.A, iIndexOfChild + 2, new LinearLayout.LayoutParams(-1, -2));
                ao3.e(this.r, "PaymentRequestUI.addShippingOptionSectionIfNecessary");
            }
        } else if (i == 3) {
            this.P = dh2Var;
            this.B.h(dh2Var);
        } else if (i == 4) {
            this.M = dh2Var;
            this.C.h(dh2Var);
        }
        this.K = false;
        o();
        m();
    }

    public final void o() {
        boolean z = !this.I;
        for (int i = 0; i < this.r.getChildCount(); i++) {
            View childAt = this.r.getChildAt(i);
            if (childAt instanceof PaymentRequestSection) {
                PaymentRequestSection paymentRequestSection = (PaymentRequestSection) childAt;
                paymentRequestSection.setIsEditButtonEnabled(z);
                if (paymentRequestSection.getEditButtonState() != 0) {
                    z = false;
                }
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (d()) {
            if (view == this.w) {
                b(true);
                return;
            }
            if (e()) {
                if (!(view instanceof PaymentRequestSection) || ((PaymentRequestSection) view).getEditButtonState() == 0) {
                    PaymentRequestSection.LineItemBreakdownSection lineItemBreakdownSection = this.y;
                    if (view == lineItemBreakdownSection) {
                        c(lineItemBreakdownSection);
                    } else {
                        PaymentRequestSection.OptionSection optionSection = this.z;
                        if (view == optionSection) {
                            c(optionSection);
                        } else {
                            PaymentRequestSection.OptionSection optionSection2 = this.A;
                            if (view == optionSection2) {
                                c(optionSection2);
                            } else {
                                PaymentRequestSection.OptionSection optionSection3 = this.B;
                                if (view == optionSection3) {
                                    c(optionSection3);
                                } else {
                                    PaymentRequestSection.OptionSection optionSection4 = this.C;
                                    if (view == optionSection4) {
                                        c(optionSection4);
                                    } else if (view == this.v) {
                                        this.G = true;
                                        dh2 dh2Var = this.N;
                                        lf0 lf0VarB = dh2Var == null ? null : dh2Var.b();
                                        dh2 dh2Var2 = this.O;
                                        lf0 lf0VarB2 = dh2Var2 == null ? null : dh2Var2.b();
                                        lf0 lf0VarB3 = this.M.b();
                                        j jVar = (j) this.g;
                                        jVar.getClass();
                                        if (((yq) jVar.o).c(lf0VarB, lf0VarB2, (PaymentApp) lf0VarB3)) {
                                            a(true);
                                        } else {
                                            j.d dVar = this.i;
                                            dVar.b = false;
                                            dVar.a();
                                        }
                                    } else if (view == this.u) {
                                        if (this.F) {
                                            b(true);
                                        } else {
                                            c(lineItemBreakdownSection);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i(null);
                    m();
                }
            }
        }
    }

    public final void p() {
        this.R = new go0(this.r, this.E, new f(this));
        PaymentRequestSection.LineItemBreakdownSection lineItemBreakdownSection = this.y;
        boolean z = this.E == lineItemBreakdownSection;
        lineItemBreakdownSection.getClass();
        lineItemBreakdownSection.setDisplayMode(z ? 5 : 4);
        j jVar = (j) this.g;
        if (jVar.w()) {
            PaymentRequestSection.OptionSection optionSection = this.z;
            optionSection.g(this.E == optionSection);
            PaymentRequestSection.OptionSection optionSection2 = this.A;
            optionSection2.g(this.E == optionSection2);
        }
        if (jVar.v()) {
            PaymentRequestSection.OptionSection optionSection3 = this.B;
            optionSection3.g(this.E == optionSection3);
        }
        PaymentRequestSection.OptionSection optionSection4 = this.C;
        optionSection4.g(this.E == optionSection4);
        o();
    }

    public final void r(PaymentRequestSection paymentRequestSection, int i) {
        boolean z = i == 1;
        this.I = z;
        this.K = i == 2;
        if (z) {
            this.E = paymentRequestSection;
            p();
            paymentRequestSection.setDisplayMode(6);
        } else {
            c(null);
        }
        m();
    }

    @Override // defpackage.hy1
    public final void h() {
    }
}
