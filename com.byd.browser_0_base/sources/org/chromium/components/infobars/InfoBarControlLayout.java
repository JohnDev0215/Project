package org.chromium.components.infobars;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.ku2;

/* JADX INFO: loaded from: classes2.dex */
public final class InfoBarControlLayout extends ViewGroup {
    public final int e;
    public final int f;

    public static final class a extends ViewGroup.LayoutParams {
        public int a;
        public int b;
        public int c;
        public boolean d;

        public a() {
            super(-1, -2);
        }
    }

    public InfoBarControlLayout(Context context) {
        this(context, null);
    }

    public static View d(int i, Context context, ViewGroup viewGroup) {
        ku2 ku2VarT = ku2.t();
        try {
            View viewInflate = LayoutInflater.from(context).inflate(i, viewGroup, false);
            ku2VarT.close();
            return viewInflate;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final TextView a(int i, CharSequence charSequence) {
        a aVar = new a();
        aVar.d = true;
        TextView textView = (TextView) d(R$layout.dialog_control_description, getContext(), this);
        addView(textView, aVar);
        textView.setText(charSequence);
        if (i != 0) {
            textView.setTextAppearance(getContext(), i);
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        return textView;
    }

    public final LinearLayout b(int i, int i2, String str, String str2) {
        LinearLayout linearLayout = (LinearLayout) d(R$layout.infobar_control_icon_with_description, getContext(), this);
        addView(linearLayout, new a());
        ((ImageView) linearLayout.findViewById(R$id.control_icon)).setImageResource(i);
        TextView textView = (TextView) linearLayout.findViewById(R$id.control_message);
        textView.setText(str);
        textView.setTextSize(0, getContext().getResources().getDimension(i2));
        TextView textView2 = (TextView) linearLayout.findViewById(R$id.control_secondary_message);
        if (str2 == null) {
            linearLayout.removeView(textView2);
        } else {
            textView2.setText(str2);
            textView2.setTextSize(0, getContext().getResources().getDimension(i2));
        }
        return linearLayout;
    }

    public final void c(int i, String str) {
        LinearLayout linearLayout = (LinearLayout) d(R$layout.infobar_control_icon_with_title, getContext(), this);
        addView(linearLayout, new a());
        ((ImageView) linearLayout.findViewById(R$id.control_title_icon)).setImageResource(i);
        TextView textView = (TextView) linearLayout.findViewById(R$id.control_title);
        textView.setText(str);
        textView.setTextSize(0, getContext().getResources().getDimension(R$dimen.infobar_text_size));
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new a();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        boolean z2 = getLayoutDirection() == 1;
        for (int i6 = 0; i6 < getChildCount(); i6++) {
            View childAt = getChildAt(i6);
            int measuredWidth = ((a) childAt.getLayoutParams()).a;
            if (z2) {
                measuredWidth = (i5 - measuredWidth) - childAt.getMeasuredWidth();
            }
            int i7 = ((a) childAt.getLayoutParams()).b;
            childAt.layout(measuredWidth, i7, childAt.getMeasuredWidth() + measuredWidth, childAt.getMeasuredHeight() + i7);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getMode(i) == 0 ? Integer.MAX_VALUE : View.MeasureSpec.getSize(i);
        int i3 = this.f;
        int iMax = Math.max(0, (size - i3) / 2);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        int iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(iMax, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        int iMakeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(0, 0);
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            View childAt = getChildAt(i4);
            measureChild(childAt, iMakeMeasureSpec, iMakeMeasureSpec4);
            if (childAt.getMeasuredWidth() > iMax || ((a) childAt.getLayoutParams()).d) {
                ((a) childAt.getLayoutParams()).c = 2;
            } else {
                ((a) childAt.getLayoutParams()).c = 1;
            }
        }
        int i5 = 0;
        while (i5 < getChildCount()) {
            a aVar = (a) getChildAt(i5).getLayoutParams();
            if (i5 == getChildCount() - 1) {
                aVar.c = 2;
            } else {
                int i6 = i5 + 1;
                if (aVar.c + ((a) getChildAt(i6).getLayoutParams()).c > 2) {
                    aVar.c = 2;
                } else {
                    i5 = i6;
                }
            }
            i5++;
        }
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt2 = getChildAt(i7);
            measureChild(childAt2, ((a) childAt2.getLayoutParams()).c == 1 ? iMakeMeasureSpec3 : iMakeMeasureSpec2, iMakeMeasureSpec4);
        }
        int i8 = 2;
        int i9 = 0;
        int iMax2 = 0;
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < getChildCount(); i12++) {
            View childAt3 = getChildAt(i12);
            a aVar2 = (a) childAt3.getLayoutParams();
            if (i8 < aVar2.c) {
                i9 += iMax2 + this.e;
                i8 = 2;
                iMax2 = 0;
                i10 = 0;
                i11 = i9;
            }
            aVar2.b = i11;
            aVar2.a = i10;
            iMax2 = Math.max(iMax2, childAt3.getMeasuredHeight());
            int i13 = aVar2.c;
            i8 -= i13;
            i10 += (iMax + i3) * i13;
        }
        setMeasuredDimension(View.resolveSize(size, i), View.resolveSize(i9 + iMax2, i2));
    }

    public InfoBarControlLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = context.getResources();
        this.e = resources.getDimensionPixelSize(R$dimen.infobar_control_margin_between_rows);
        this.f = resources.getDimensionPixelSize(R$dimen.infobar_control_margin_between_columns);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a();
    }
}
