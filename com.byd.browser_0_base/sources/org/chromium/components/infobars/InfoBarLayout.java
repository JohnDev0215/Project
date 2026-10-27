package org.chromium.components.infobars;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.bz;
import defpackage.l51;
import defpackage.un1;
import defpackage.wz0;
import java.util.ArrayList;
import org.chromium.components.browser_ui.widget.DualControlLayout;
import org.chromium.components.infobars.InfoBarControlLayout;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.ChromeImageButton;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: loaded from: classes2.dex */
public final class InfoBarLayout extends ViewGroup implements View.OnClickListener {
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final wz0 i;
    public final ChromeImageButton j;
    public final InfoBarControlLayout k;
    public final ArrayList l;
    public ViewGroup m;
    public final TextView n;
    public final ChromeImageView o;
    public DualControlLayout p;
    public CharSequence q;
    public String r;

    public static class a extends ViewGroup.LayoutParams {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public int e;
        public int f;

        public a(int i, int i2, int i3) {
            super(-2, -2);
            this.a = 0;
            this.c = i;
            this.b = i2;
            this.d = i3;
        }
    }

    public InfoBarLayout(Context context, wz0 wz0Var, int i, int i2, Bitmap bitmap, CharSequence charSequence) {
        super(context);
        this.l = new ArrayList();
        this.i = wz0Var;
        Resources resources = getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.infobar_small_icon_size);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.infobar_small_icon_margin);
        resources.getDimensionPixelSize(R$dimen.infobar_big_icon_size);
        resources.getDimensionPixelSize(R$dimen.infobar_big_icon_margin);
        this.e = resources.getDimensionPixelSize(R$dimen.infobar_margin_above_button_row);
        this.f = resources.getDimensionPixelSize(R$dimen.infobar_margin_above_control_groups);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.infobar_padding);
        this.g = dimensionPixelOffset;
        this.h = resources.getDimensionPixelSize(R$dimen.infobar_min_width);
        ChromeImageButton chromeImageButtonB = b(context);
        this.j = chromeImageButtonB;
        chromeImageButtonB.setOnClickListener(this);
        chromeImageButtonB.setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
        int i3 = -dimensionPixelOffset;
        chromeImageButtonB.setLayoutParams(new a(i3, i3, i3));
        ChromeImageView chromeImageViewC = c(context, i, i2, bitmap);
        this.o = chromeImageViewC;
        if (chromeImageViewC != null) {
            chromeImageViewC.setLayoutParams(new a(0, dimensionPixelSize2, 0));
            chromeImageViewC.getLayoutParams().width = dimensionPixelSize;
            chromeImageViewC.getLayoutParams().height = dimensionPixelSize;
        }
        this.q = charSequence;
        InfoBarControlLayout infoBarControlLayout = new InfoBarControlLayout(context);
        this.k = infoBarControlLayout;
        SpannableStringBuilder spannableStringBuilderG = g();
        InfoBarControlLayout.a aVar = new InfoBarControlLayout.a();
        aVar.d = true;
        TextView textView = (TextView) InfoBarControlLayout.d(R$layout.infobar_control_message, infoBarControlLayout.getContext(), infoBarControlLayout);
        infoBarControlLayout.addView(textView, aVar);
        textView.setText(spannableStringBuilderG);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.n = textView;
    }

    public static ChromeImageButton b(Context context) {
        ColorStateList colorStateListB = bz.b(context, R$color.default_icon_color_tint_list);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackground});
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        ChromeImageButton chromeImageButton = new ChromeImageButton(context);
        chromeImageButton.setId(R$id.infobar_close_button);
        chromeImageButton.setImageResource(R$drawable.btn_close);
        chromeImageButton.setImageTintList(colorStateListB);
        chromeImageButton.setBackground(drawable);
        chromeImageButton.setContentDescription(context.getString(R$string.close));
        chromeImageButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return chromeImageButton;
    }

    public static ChromeImageView c(Context context, int i, int i2, Bitmap bitmap) {
        if (i == 0 && bitmap == null) {
            return null;
        }
        ChromeImageView chromeImageView = new ChromeImageView(context);
        if (i != 0) {
            chromeImageView.setImageDrawable(AppCompatResources.a(context, i));
            if (i2 != 0) {
                chromeImageView.setImageTintList(bz.b(context, i2));
            }
        } else {
            chromeImageView.setImageBitmap(bitmap);
        }
        chromeImageView.setFocusable(false);
        chromeImageView.setId(R$id.infobar_icon);
        chromeImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return chromeImageView;
    }

    public static int d(View view) {
        if (view == null) {
            return 0;
        }
        return view.getMeasuredHeight() + ((a) view.getLayoutParams()).c + ((a) view.getLayoutParams()).d;
    }

    public static a e(View view) {
        return (a) view.getLayoutParams();
    }

    public static void f(View view, int i) {
        a aVar = (a) view.getLayoutParams();
        view.measure(View.MeasureSpec.makeMeasureSpec((i - aVar.a) - aVar.b, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    public final InfoBarControlLayout a() {
        InfoBarControlLayout infoBarControlLayout = new InfoBarControlLayout(getContext());
        this.l.add(infoBarControlLayout);
        return infoBarControlLayout;
    }

    public final SpannableStringBuilder g() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(this.q)) {
            spannableStringBuilder.append((CharSequence) new SpannableString(this.q));
        }
        if (!TextUtils.isEmpty(this.r)) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append((CharSequence) " ");
            }
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) this.r);
            spannableStringBuilder.setSpan(new un1(getContext(), new l51(13, this)), length, spannableStringBuilder.length(), 33);
        }
        return spannableStringBuilder;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new a(0, 0, 0);
    }

    public ImageView getIcon() {
        return this.o;
    }

    public InfoBarControlLayout getMessageLayout() {
        return this.k;
    }

    public TextView getMessageTextView() {
        return this.n;
    }

    public ButtonCompat getPrimaryButton() {
        DualControlLayout dualControlLayout = this.p;
        if (dualControlLayout == null) {
            return null;
        }
        return (ButtonCompat) dualControlLayout.findViewById(R$id.button_primary);
    }

    public final void h(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ButtonCompat buttonCompatA = !TextUtils.isEmpty(str2) ? DualControlLayout.a(getContext(), false, str2, this) : null;
        ButtonCompat buttonCompatA2 = DualControlLayout.a(getContext(), true, str, this);
        DualControlLayout dualControlLayout = new DualControlLayout(getContext(), null);
        this.p = dualControlLayout;
        dualControlLayout.setAlignment(1);
        this.p.setStackedMargin(getResources().getDimensionPixelSize(R$dimen.infobar_margin_between_stacked_buttons));
        this.p.addView(buttonCompatA2);
        if (buttonCompatA != null) {
            this.p.addView(buttonCompatA);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        wz0 wz0Var = this.i;
        ((InfoBar) wz0Var).m = false;
        if (view.getId() == R$id.infobar_close_button) {
            wz0Var.k();
        } else if (view.getId() == R$id.button_primary) {
            wz0Var.c(true);
        } else if (view.getId() == R$id.button_secondary) {
            wz0Var.c(false);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        boolean z2 = getLayoutDirection() == 1;
        for (int i6 = 0; i6 < getChildCount(); i6++) {
            View childAt = getChildAt(i6);
            a aVar = (a) childAt.getLayoutParams();
            int i7 = aVar.e;
            int measuredWidth = childAt.getMeasuredWidth() + i7;
            if (z2) {
                int i8 = i5 - measuredWidth;
                measuredWidth = i5 - i7;
                i7 = i8;
            }
            int i9 = aVar.f;
            childAt.layout(i7, i9, measuredWidth, childAt.getMeasuredHeight() + i9);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int iMax = Math.max(View.MeasureSpec.getSize(i), this.h);
        int i3 = this.g;
        int i4 = iMax - i3;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ChromeImageView chromeImageView = this.o;
        if (chromeImageView != null) {
            a aVar = (a) chromeImageView.getLayoutParams();
            measureChild(chromeImageView, iMakeMeasureSpec, iMakeMeasureSpec);
            aVar.e = aVar.a + i3;
            aVar.f = aVar.c + i3;
        }
        int measuredWidth = chromeImageView == null ? 0 : chromeImageView.getMeasuredWidth() + ((a) chromeImageView.getLayoutParams()).a + ((a) chromeImageView.getLayoutParams()).b;
        ChromeImageButton chromeImageButton = this.j;
        a aVarE = e(chromeImageButton);
        measureChild(chromeImageButton, iMakeMeasureSpec, iMakeMeasureSpec);
        aVarE.e = (i4 - aVarE.b) - chromeImageButton.getMeasuredWidth();
        aVarE.f = aVarE.c + i3;
        int i5 = i4 - i3;
        int i6 = i5 - measuredWidth;
        int measuredWidth2 = i6 - ((chromeImageButton.getMeasuredWidth() + ((a) chromeImageButton.getLayoutParams()).a) + ((a) chromeImageButton.getLayoutParams()).b);
        InfoBarControlLayout infoBarControlLayout = this.k;
        a aVarE2 = e(infoBarControlLayout);
        f(infoBarControlLayout, measuredWidth2);
        int i7 = measuredWidth + i3;
        aVarE2.e = i7;
        aVarE2.f = i3;
        int iMax2 = Math.max(d(infoBarControlLayout), d(chromeImageButton)) + i3;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.l;
            if (i8 >= arrayList.size()) {
                break;
            }
            View view = (View) arrayList.get(i8);
            f(view, i6);
            int i9 = iMax2 + this.f;
            ((a) view.getLayoutParams()).e = i7;
            ((a) view.getLayoutParams()).f = i9;
            iMax2 = i9 + view.getMeasuredHeight();
            i8++;
        }
        int iMax3 = Math.max(iMax2, d(chromeImageView));
        DualControlLayout dualControlLayout = this.p;
        if (dualControlLayout != null) {
            f(dualControlLayout, i5);
            int i10 = iMax3 + this.e;
            e(this.p).e = i3;
            e(this.p).f = i10;
            iMax3 = i10 + this.p.getMeasuredHeight();
        }
        int measuredHeight = iMax3 + i3;
        ViewGroup viewGroup = this.m;
        if (viewGroup != null) {
            a aVar2 = (a) viewGroup.getLayoutParams();
            f(this.m, iMax);
            aVar2.e = 0;
            aVar2.f = measuredHeight;
            measuredHeight += this.m.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(iMax, i), View.resolveSize(measuredHeight, i2));
    }

    public void setMessage(CharSequence charSequence) {
        this.q = charSequence;
        this.n.setText(g());
    }
}
