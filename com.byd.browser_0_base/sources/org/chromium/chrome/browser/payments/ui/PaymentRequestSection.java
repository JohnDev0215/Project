package org.chromium.chrome.browser.payments.ui;

import J.N;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;
import defpackage.ao3;
import defpackage.c6;
import defpackage.dh2;
import defpackage.fh2;
import defpackage.gh2;
import defpackage.h21;
import defpackage.ii2;
import defpackage.lf0;
import defpackage.qa;
import defpackage.sv0;
import defpackage.yp;
import defpackage.za3;
import java.util.ArrayList;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.payments.ui.e;
import org.chromium.components.browser_ui.widget.DualControlLayout;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PaymentRequestSection extends LinearLayout implements View.OnClickListener {
    public final a e;
    public final int f;
    public final ButtonCompat g;
    public final boolean h;
    public int i;
    public final int j;
    public final int k;
    public final int l;
    public final LinearLayout m;
    public final ImageView n;
    public final ImageView o;
    public TextView p;
    public LinearLayout q;
    public TextView r;
    public TextView s;
    public Drawable t;
    public boolean u;

    public static class LineItemBreakdownSection extends PaymentRequestSection {
        public GridLayout v;
        public TextView w;
        public final ArrayList x;
        public final a y;
        public final Handler z;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                LineItemBreakdownSection lineItemBreakdownSection = LineItemBreakdownSection.this;
                AlphaAnimation alphaAnimation = new AlphaAnimation(lineItemBreakdownSection.w.getAlpha(), 0.0f);
                alphaAnimation.setDuration(500L);
                alphaAnimation.setInterpolator(h21.g);
                alphaAnimation.setFillAfter(true);
                lineItemBreakdownSection.w.startAnimation(alphaAnimation);
            }
        }

        public LineItemBreakdownSection(ChromeActivity chromeActivity, String str, a aVar, String str2) {
            super(chromeActivity, str, aVar);
            this.x = new ArrayList();
            this.y = new a();
            this.z = new Handler();
            this.w.setText(str2);
        }

        public static SpannableStringBuilder f(String str, String str2, boolean z) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) str);
            spannableStringBuilder.append((CharSequence) " ");
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) str2);
            if (z) {
                spannableStringBuilder.setSpan(new StyleSpan(1), length, str2.length() + length, 0);
            }
            return spannableStringBuilder;
        }

        @Override // org.chromium.chrome.browser.payments.ui.PaymentRequestSection
        public final void a(LinearLayout linearLayout) {
            Context context = linearLayout.getContext();
            Context context2 = linearLayout.getContext();
            TextView textView = new TextView(context2);
            this.w = textView;
            textView.setTextAppearance(textView.getContext(), R$style.TextAppearance_TextLarge_Primary);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            this.w.setTextAlignment(3);
            this.w.setTextColor(context2.getColor(R$color.google_green_600));
            Resources resources = context2.getResources();
            int i = R$dimen.editor_dialog_section_small_spacing;
            layoutParams.setMarginStart(resources.getDimensionPixelSize(i));
            layoutParams.setMarginEnd(context2.getResources().getDimensionPixelSize(i));
            this.w.setVisibility(4);
            getSummaryLayout().addView(this.w, getSummaryLayout().getChildCount() - 1, layoutParams);
            GridLayout gridLayout = new GridLayout(context);
            this.v = gridLayout;
            gridLayout.setColumnCount(2);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.gravity = 8388613;
            linearLayout.addView(this.v, layoutParams2);
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) getSummaryRightTextView().getLayoutParams();
            layoutParams3.width = 0;
            layoutParams3.weight = 1.0f;
        }

        @Override // org.chromium.chrome.browser.payments.ui.PaymentRequestSection
        public final void e() {
            if (this.h) {
                this.v.setVisibility(this.i == 5 ? 0 : 8);
                super.e();
            }
        }

        public int getNumberOfLineItemsForTest() {
            return this.x.size();
        }

        @Override // org.chromium.chrome.browser.payments.ui.PaymentRequestSection
        public void setDisplayMode(int i) {
            if (i == 5) {
                c(TextUtils.TruncateAt.END, false);
                getSummaryLeftTextView().setMaxLines(3);
            } else {
                c(TextUtils.TruncateAt.END, true);
                getSummaryLeftTextView().setMaxLines(1);
            }
            super.setDisplayMode(i);
        }
    }

    public static class OptionSection extends PaymentRequestSection {

        /* JADX INFO: renamed from: J, reason: collision with root package name */
        public static final /* synthetic */ int f74J = 0;
        public final int A;
        public GridLayout B;
        public ViewGroup C;
        public dh2 D;
        public boolean E;
        public boolean F;
        public boolean G;
        public boolean H;
        public a I;
        public final ArrayList v;
        public boolean w;
        public final int x;
        public final ArrayList<b> y;
        public final int z;

        public interface a {
        }

        public class b {
            public final lf0 a;
            public final View b;
            public final TextView c;
            public final ImageView d;
            public final View e;

            /* JADX WARN: Removed duplicated region for block: B:59:0x012e  */
            /* JADX WARN: Removed duplicated region for block: B:62:0x013f  */
            /* JADX WARN: Removed duplicated region for block: B:71:0x0180  */
            /* JADX WARN: Removed duplicated region for block: B:74:0x0185  */
            /* JADX WARN: Removed duplicated region for block: B:75:0x01ad  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public b(androidx.gridlayout.widget.GridLayout r19, int r20, int r21, defpackage.lf0 r22, boolean r23) {
                /*
                    Method dump skipped, instruction units count: 434
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.payments.ui.PaymentRequestSection.OptionSection.b.<init>(org.chromium.chrome.browser.payments.ui.PaymentRequestSection$OptionSection, androidx.gridlayout.widget.GridLayout, int, int, lf0, boolean):void");
            }
        }

        public OptionSection(ChromeActivity chromeActivity, String str, a aVar) {
            super(chromeActivity, str, aVar);
            this.v = new ArrayList();
            this.w = true;
            this.y = new ArrayList<>();
            this.F = true;
            this.x = chromeActivity.getResources().getDimensionPixelSize(R$dimen.editor_dialog_section_small_spacing);
            this.z = chromeActivity.getResources().getDimensionPixelSize(R$dimen.editable_option_section_logo_width);
            this.A = chromeActivity.getResources().getDimensionPixelSize(R$dimen.payments_favicon_size);
            d(null, null);
        }

        private void setSpinnerVisibility(boolean z) {
            if (!z) {
                if (this.C.getParent() == null) {
                    return;
                }
                ((ViewGroup) this.C.getParent()).removeView(this.C);
            } else {
                if (this.C.getParent() != null) {
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) this.B.getParent();
                viewGroup.addView(this.C, viewGroup.indexOfChild(this.B));
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.C.getLayoutParams();
                marginLayoutParams.width = -1;
                marginLayoutParams.height = -2;
                marginLayoutParams.bottomMargin = getContext().getResources().getDimensionPixelSize(R$dimen.payments_section_checking_spacing);
                ao3.e(this.C, "PaymentRequestSection.OptionRow.setSpinnerVisibility");
            }
        }

        @Override // org.chromium.chrome.browser.payments.ui.PaymentRequestSection
        public final void a(LinearLayout linearLayout) {
            Context context = linearLayout.getContext();
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R$layout.payment_request_spinny, (ViewGroup) null);
            ((TextView) viewGroup.findViewById(R$id.message)).setText(getContext().getString(R$string.payments_checking_option));
            this.C = viewGroup;
            GridLayout gridLayout = new GridLayout(context);
            this.B = gridLayout;
            gridLayout.setColumnCount(4);
            linearLayout.addView(this.B, new LinearLayout.LayoutParams(-1, -2));
        }

        @Override // org.chromium.chrome.browser.payments.ui.PaymentRequestSection
        public final void b(View view) {
            int iT;
            int i = 0;
            while (true) {
                ArrayList<b> arrayList = this.y;
                if (i >= arrayList.size()) {
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        b bVar = arrayList.get(i2);
                        View view2 = bVar.b;
                        boolean z = view2 == view || bVar.c == view || bVar.d == view;
                        lf0 lf0Var = bVar.a;
                        if (lf0Var != null) {
                            ((RadioButton) view2).setChecked(z);
                            if (z) {
                                OptionSection optionSection = OptionSection.this;
                                optionSection.i(lf0Var);
                                e eVar = (e) optionSection.e;
                                OptionSection optionSection2 = eVar.z;
                                c cVar = eVar.n;
                                e.b bVar2 = eVar.g;
                                if (optionSection == optionSection2 && eVar.N.b() != lf0Var) {
                                    eVar.N.d(lf0Var);
                                    iT = ((j) bVar2).t(1, lf0Var, cVar);
                                } else if (optionSection == eVar.A && eVar.O.b() != lf0Var) {
                                    eVar.O.d(lf0Var);
                                    iT = ((j) bVar2).t(2, lf0Var, cVar);
                                } else if (optionSection == eVar.B) {
                                    eVar.P.d(lf0Var);
                                    iT = ((j) bVar2).t(3, lf0Var, cVar);
                                } else if (optionSection == eVar.C) {
                                    eVar.M.d(lf0Var);
                                    iT = ((j) bVar2).t(4, lf0Var, null);
                                } else {
                                    iT = 3;
                                }
                                eVar.r(optionSection, iT);
                            }
                        }
                    }
                    return;
                }
                b bVar3 = arrayList.get(i);
                boolean z2 = bVar3.b == view || bVar3.c == view || bVar3.d == view;
                a aVar = this.e;
                lf0 lf0Var2 = bVar3.a;
                if (lf0Var2 == null && z2) {
                    ((e) aVar).g(this);
                    return;
                }
                if (lf0Var2 != null && bVar3.e == view) {
                    e eVar2 = (e) aVar;
                    OptionSection optionSection3 = eVar2.z;
                    e.b bVar4 = eVar2.g;
                    int iS = this == optionSection3 ? ((j) bVar4).s(1, lf0Var2, eVar2.n) : 3;
                    if (this == eVar2.B) {
                        iS = ((j) bVar4).s(3, lf0Var2, null);
                    }
                    if (this == eVar2.C) {
                        iS = ((j) bVar4).s(4, lf0Var2, null);
                    }
                    eVar2.r(this, iS);
                    return;
                }
                i++;
            }
        }

        @Override // org.chromium.chrome.browser.payments.ui.PaymentRequestSection
        public final void e() {
            if (this.h) {
                int i = this.i;
                if (i == 5) {
                    setIsSummaryAllowed(false);
                    this.B.setVisibility(0);
                    setSpinnerVisibility(false);
                } else if (i == 6) {
                    setIsSummaryAllowed(false);
                    this.B.setVisibility(8);
                    setSpinnerVisibility(true);
                } else {
                    setIsSummaryAllowed(true);
                    this.B.setVisibility(8);
                    setSpinnerVisibility(false);
                }
                super.e();
            }
        }

        public final SpannableStringBuilder f(lf0 lf0Var, boolean z, boolean z2, boolean z3) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (!z) {
                spannableStringBuilder.append((CharSequence) lf0Var.i[0]);
                if (z2) {
                    spannableStringBuilder.setSpan(new StyleSpan(1), 0, spannableStringBuilder.length(), 0);
                }
            }
            String string = z3 ? getContext().getString(R$string.autofill_address_summary_separator) : "\n";
            boolean zIsEmpty = TextUtils.isEmpty(lf0Var.i[1]);
            String[] strArr = lf0Var.i;
            if (!zIsEmpty) {
                if (spannableStringBuilder.length() > 0) {
                    spannableStringBuilder.append((CharSequence) string);
                }
                spannableStringBuilder.append((CharSequence) strArr[1]);
            }
            if (!TextUtils.isEmpty(strArr[2])) {
                if (spannableStringBuilder.length() > 0) {
                    spannableStringBuilder.append((CharSequence) string);
                }
                spannableStringBuilder.append((CharSequence) strArr[2]);
            }
            if (!TextUtils.isEmpty(lf0Var.f)) {
                if (spannableStringBuilder.length() > 0) {
                    spannableStringBuilder.append((CharSequence) string);
                }
                spannableStringBuilder.append((CharSequence) lf0Var.f);
            }
            if (!lf0Var.c() && !TextUtils.isEmpty(lf0Var.d)) {
                if (spannableStringBuilder.length() > 0) {
                    spannableStringBuilder.append((CharSequence) string);
                }
                String str = lf0Var.d;
                spannableStringBuilder.append((CharSequence) str);
                Context context = getContext();
                String str2 = ii2.a;
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(context.getColor(org.chromium.components.browser_ui.styles.R$color.dialog_color_text_message_info_hint_url));
                AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(14, true);
                int length = spannableStringBuilder.length() - str.length();
                spannableStringBuilder.setSpan(foregroundColorSpan, length, spannableStringBuilder.length(), 0);
                spannableStringBuilder.setSpan(absoluteSizeSpan, length, spannableStringBuilder.length(), 0);
            }
            return spannableStringBuilder;
        }

        public final void g(boolean z) {
            dh2 dh2Var = this.D;
            if (!(dh2Var != null && dh2Var.c() > 0) && z) {
                setDisplayMode(3);
                return;
            }
            a aVar = this.I;
            if (aVar != null && this.D != null) {
                j jVar = (j) aVar;
                if (jVar.A.b() != null) {
                    qa qaVar = (qa) jVar.A.b();
                    if (z) {
                        qaVar.i();
                    } else {
                        if (qaVar.n == null) {
                            PersonalDataManager personalDataManagerC = PersonalDataManager.c();
                            qaVar.n = N.MGlLlw0K(personalDataManagerC.a, personalDataManagerC, qaVar.l);
                        }
                        PersonalDataManager.AutofillProfile autofillProfile = qaVar.l;
                        String str = qaVar.n;
                        autofillProfile.q = str;
                        qaVar.i[1] = str;
                    }
                    jVar.x.n(1, jVar.A);
                }
            }
            int i = this.i;
            setDisplayMode(z ? 5 : 4);
            dh2 dh2Var2 = this.D;
            if (dh2Var2 == null || i != 3) {
                return;
            }
            i(dh2Var2.b());
        }

        @Override // org.chromium.chrome.browser.payments.ui.PaymentRequestSection
        public int getEditButtonState() {
            dh2 dh2Var = this.D;
            if (dh2Var == null) {
                return 0;
            }
            if (dh2Var.c() == 0 && this.w) {
                return 2;
            }
            return this.D.b() == null ? 1 : 0;
        }

        public TextView getLeftSummaryLabelForTest() {
            return getSummaryLeftTextView();
        }

        public int getNumberOfOptionLabelsForTest() {
            return this.v.size();
        }

        public TextView getRightSummaryLabelForTest() {
            return getSummaryRightTextView();
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x005d  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x009e  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x00ce  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00e1  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00e4  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x010b  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x0110  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void h(defpackage.dh2 r20) {
            /*
                Method dump skipped, instruction units count: 310
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.payments.ui.PaymentRequestSection.OptionSection.h(dh2):void");
        }

        public final void i(lf0 lf0Var) {
            if (lf0Var == null || (this.i == 3 && this.F)) {
                if (!this.E) {
                    c(TextUtils.TruncateAt.END, true);
                    this.E = true;
                }
            } else if (this.E) {
                c(null, false);
                this.E = false;
            }
            if (lf0Var == null) {
                setLogoDrawable(null);
                if (!this.H) {
                    c6.f(getSummaryLeftTextView(), R$style.TextAppearance_TextMedium_Secondary);
                    this.H = true;
                }
                Context context = getContext();
                dh2 dh2Var = this.D;
                TextView summaryLeftTextView = getSummaryLeftTextView();
                int iC = dh2Var.c();
                if (iC == 0) {
                    summaryLeftTextView.setText((CharSequence) null);
                } else {
                    if (summaryLeftTextView.getLayout() == null && iC > 1) {
                        summaryLeftTextView.addOnLayoutChangeListener(new fh2(context, summaryLeftTextView, dh2Var));
                    }
                    summaryLeftTextView.setText(gh2.a(context, dh2Var, summaryLeftTextView.getLayout(), summaryLeftTextView.getPaint()));
                }
            } else {
                setLogoDrawable(lf0Var.h);
                if (this.H) {
                    c6.f(getSummaryLeftTextView(), R$style.TextAppearance_TextLarge_Primary);
                    this.H = false;
                }
                if (this.G && this.i == 3) {
                    d(lf0Var.i[0], f(lf0Var, true, false, this.E));
                } else {
                    d(f(lf0Var, false, false, this.E), null);
                }
            }
            e();
        }

        public void setCanAddItems(boolean z) {
            this.w = z;
        }

        public void setDisplaySummaryInSingleLineInNormalMode(boolean z) {
            this.F = z;
        }

        public void setOptionSectionFocusChangedObserver(a aVar) {
            this.I = aVar;
        }

        public void setSplitSummaryInDisplayModeNormal(boolean z) {
            this.G = z;
        }
    }

    public static class SectionSeparator extends View {
        public SectionSeparator() {
            throw null;
        }

        public SectionSeparator(int i, LinearLayout linearLayout) {
            super(linearLayout.getContext());
            Resources resources = linearLayout.getContext().getResources();
            Context context = getContext();
            int i2 = sv0.e;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.listDivider});
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
            typedArrayObtainStyledAttributes.recycle();
            setBackground(new sv0(new Drawable[]{drawable}));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, resources.getDimensionPixelSize(R$dimen.divider_height));
            int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.editor_dialog_section_large_spacing);
            layoutParams.setMarginStart(dimensionPixelSize);
            layoutParams.setMarginEnd(dimensionPixelSize);
            linearLayout.addView(this, i, layoutParams);
        }
    }

    public interface a extends View.OnClickListener {
    }

    public PaymentRequestSection(ChromeActivity chromeActivity, String str, a aVar) {
        super(chromeActivity);
        this.i = 3;
        this.u = true;
        this.e = aVar;
        setOnClickListener(aVar);
        setOrientation(0);
        setGravity(16);
        String str2 = ii2.a;
        this.k = yp.c(chromeActivity, org.chromium.components.browser_ui.styles.R$dimen.sheet_bg_color_elev);
        this.l = yp.c(chromeActivity, org.chromium.components.browser_ui.styles.R$dimen.default_elevation_1);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.editor_dialog_section_large_spacing);
        this.f = dimensionPixelSize;
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.payments_section_vertical_spacing);
        this.j = dimensionPixelSize2;
        setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        addView(linearLayout, layoutParams);
        TextView textView = new TextView(getContext());
        this.p = textView;
        textView.setText(str);
        c6.f(this.p, R$style.TextAppearance_TextMedium_Accent1);
        linearLayout.addView(this.p, new LinearLayout.LayoutParams(-1, -2));
        TextView textView2 = new TextView(getContext());
        this.r = textView2;
        textView2.setId(R$id.payments_left_summary_label);
        TextView textView3 = this.r;
        int i = R$style.TextAppearance_TextLarge_Primary;
        c6.f(textView3, i);
        TextView textView4 = new TextView(getContext());
        this.s = textView4;
        textView4.setTextAppearance(textView4.getContext(), i);
        this.s.setTextAlignment(3);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2);
        layoutParams2.weight = 1.0f;
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.setMarginStart(getContext().getResources().getDimensionPixelSize(R$dimen.editor_dialog_section_small_spacing));
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        this.q = linearLayout2;
        linearLayout2.addView(this.r, layoutParams2);
        this.q.addView(this.s, layoutParams3);
        linearLayout.addView(this.q, new LinearLayout.LayoutParams(-1, -2));
        ImageView imageView = null;
        d(null, null);
        a(linearLayout);
        this.m = linearLayout;
        if (this instanceof OptionSection) {
            imageView = new ImageView(getContext());
            imageView.setMaxWidth(getContext().getResources().getDimensionPixelSize(R$dimen.editable_option_section_logo_width));
            imageView.setAdjustViewBounds(true);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams4.setMarginStart(dimensionPixelSize);
            addView(imageView, layoutParams4);
        }
        this.n = imageView;
        ButtonCompat buttonCompatA = DualControlLayout.a(getContext(), true, getResources().getString(R$string.choose), this);
        buttonCompatA.setId(R$id.payments_section);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.setMarginStart(dimensionPixelSize);
        addView(buttonCompatA, layoutParams5);
        this.g = buttonCompatA;
        za3 za3VarB = za3.b(getContext(), R$drawable.ic_expand_more_black_24dp, R$color.payments_section_chevron);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setImageDrawable(za3VarB);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams6.setMarginStart(dimensionPixelSize);
        addView(imageView2, layoutParams6);
        this.o = imageView2;
        this.h = true;
        setDisplayMode(3);
    }

    public abstract void a(LinearLayout linearLayout);

    public final void c(TextUtils.TruncateAt truncateAt, boolean z) {
        this.r.setEllipsize(truncateAt);
        this.r.setSingleLine(z);
        this.s.setEllipsize(null);
        this.s.setSingleLine(false);
    }

    public final void d(CharSequence charSequence, SpannableStringBuilder spannableStringBuilder) {
        this.r.setText(charSequence);
        this.s.setText(spannableStringBuilder);
        this.s.setVisibility(TextUtils.isEmpty(spannableStringBuilder) ? 8 : 0);
        e();
    }

    public void e() {
        if (this.h) {
            int i = this.i;
            boolean z = i == 5 || i == 6;
            setBackgroundColor(z ? this.l : this.k);
            ImageView imageView = this.n;
            if (imageView != null) {
                imageView.setVisibility(this.t != null && this.i != 5 ? 0 : 8);
            }
            int editButtonState = getEditButtonState();
            ImageView imageView2 = this.o;
            ButtonCompat buttonCompat = this.g;
            if (editButtonState == 0) {
                buttonCompat.setVisibility(8);
                imageView2.setVisibility(this.i == 4 ? 0 : 8);
            } else {
                int i2 = this.i;
                boolean z2 = i2 == 4 || i2 == 3;
                imageView2.setVisibility(8);
                buttonCompat.setVisibility(z2 ? 0 : 8);
                buttonCompat.setText(editButtonState == 1 ? R$string.choose : R$string.add);
            }
            this.q.setVisibility(this.u ? 0 : 8);
            int i3 = 0;
            int i4 = 0;
            while (true) {
                LinearLayout linearLayout = this.m;
                if (i3 >= linearLayout.getChildCount()) {
                    break;
                }
                if (linearLayout.getChildAt(i3).getVisibility() == 0) {
                    i4++;
                }
                i3++;
            }
            boolean z3 = i4 > 1 && z;
            int i5 = ((ViewGroup.MarginLayoutParams) this.p.getLayoutParams()).bottomMargin;
            int i6 = z3 ? this.j : 0;
            if (i5 != i6) {
                ((ViewGroup.MarginLayoutParams) this.p.getLayoutParams()).bottomMargin = i6;
                ao3.e(this, "PaymentRequestSection.UpdateControlLayout");
            }
        }
    }

    public int getEditButtonState() {
        return 0;
    }

    public LinearLayout getSummaryLayout() {
        return this.q;
    }

    public TextView getSummaryLeftTextView() {
        return this.r;
    }

    public TextView getSummaryRightTextView() {
        return this.s;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        e eVar = (e) this.e;
        if (eVar.e()) {
            if (view != this.g) {
                b(view);
                e();
            } else if (getEditButtonState() == 2) {
                eVar.g(this);
            } else {
                eVar.c(this);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !((e) this.e).e();
    }

    public void setDisplayMode(int i) {
        this.i = i;
        e();
    }

    public void setIsEditButtonEnabled(boolean z) {
        this.g.setEnabled(z);
    }

    public void setIsSummaryAllowed(boolean z) {
        this.u = z;
    }

    public void setLogoDrawable(Drawable drawable) {
        this.t = drawable;
        ImageView imageView = this.n;
        imageView.setBackgroundResource(0);
        imageView.setImageDrawable(this.t);
    }

    public void setTitleAppearance(int i) {
        c6.f(this.p, i);
    }

    public void b(View view) {
    }
}
