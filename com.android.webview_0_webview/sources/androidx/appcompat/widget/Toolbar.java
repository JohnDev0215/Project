package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.customview.view.AbsSavedState;
import defpackage.AbstractC0245Nm;
import defpackage.AbstractC0678dn;
import defpackage.AbstractC1251o3;
import defpackage.AbstractC1521t;
import defpackage.C0803g0;
import defpackage.C0915i0;
import defpackage.C1082l0;
import defpackage.C1467s0;
import defpackage.L0;
import defpackage.M0;
import defpackage.N0;
import defpackage.O0;
import defpackage.Q0;
import defpackage.X0;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public C1467s0 F;
    public int G;
    public int H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public CharSequence f16J;
    public CharSequence K;
    public int L;
    public int M;
    public boolean N;
    public boolean O;
    public final ArrayList P;
    public final ArrayList Q;
    public final int[] R;
    public Q0 S;
    public final Runnable T;
    public TextView s;
    public TextView t;
    public ImageButton u;
    public ImageView v;
    public int w;
    public int x;
    public int y;
    public int z;

    /* JADX WARN: Illegal instructions before constructor call */
    public Toolbar(Context context, AttributeSet attributeSet) {
        int i = AbstractC0245Nm.I8;
        super(context, attributeSet, i);
        this.I = 8388627;
        this.P = new ArrayList();
        this.Q = new ArrayList();
        this.R = new int[2];
        this.T = new M0(this);
        L0 l0L = L0.l(getContext(), attributeSet, AbstractC0678dn.H0, i, 0);
        this.x = l0L.i(25, 0);
        this.y = l0L.i(16, 0);
        this.I = l0L.b.getInteger(27, this.I);
        this.z = l0L.b.getInteger(0, 48);
        int iD = l0L.d(19, 0);
        iD = l0L.k(24) ? l0L.d(24, iD) : iD;
        this.E = iD;
        this.D = iD;
        this.C = iD;
        this.B = iD;
        int iD2 = l0L.d(22, -1);
        if (iD2 >= 0) {
            this.B = iD2;
        }
        int iD3 = l0L.d(21, -1);
        if (iD3 >= 0) {
            this.C = iD3;
        }
        int iD4 = l0L.d(23, -1);
        if (iD4 >= 0) {
            this.D = iD4;
        }
        int iD5 = l0L.d(20, -1);
        if (iD5 >= 0) {
            this.E = iD5;
        }
        this.A = l0L.e(11, -1);
        int iD6 = l0L.d(7, Integer.MIN_VALUE);
        int iD7 = l0L.d(3, Integer.MIN_VALUE);
        int iE = l0L.e(5, 0);
        int iE2 = l0L.e(6, 0);
        c();
        C1467s0 c1467s0 = this.F;
        c1467s0.h = false;
        if (iE != Integer.MIN_VALUE) {
            c1467s0.e = iE;
            c1467s0.a = iE;
        }
        if (iE2 != Integer.MIN_VALUE) {
            c1467s0.f = iE2;
            c1467s0.b = iE2;
        }
        if (iD6 != Integer.MIN_VALUE || iD7 != Integer.MIN_VALUE) {
            this.F.a(iD6, iD7);
        }
        this.G = l0L.d(8, Integer.MIN_VALUE);
        this.H = l0L.d(4, Integer.MIN_VALUE);
        l0L.f(2);
        l0L.j(1);
        CharSequence charSequenceJ = l0L.j(18);
        if (!TextUtils.isEmpty(charSequenceJ)) {
            x(charSequenceJ);
        }
        CharSequence charSequenceJ2 = l0L.j(15);
        if (!TextUtils.isEmpty(charSequenceJ2)) {
            w(charSequenceJ2);
        }
        getContext();
        v(l0L.i(14, 0));
        Drawable drawableF = l0L.f(13);
        if (drawableF != null) {
            u(drawableF);
        }
        CharSequence charSequenceJ3 = l0L.j(12);
        if (!TextUtils.isEmpty(charSequenceJ3)) {
            t(charSequenceJ3);
        }
        Drawable drawableF2 = l0L.f(9);
        if (drawableF2 != null) {
            s(drawableF2);
        }
        CharSequence charSequenceJ4 = l0L.j(10);
        if (!TextUtils.isEmpty(charSequenceJ4)) {
            if (!TextUtils.isEmpty(charSequenceJ4) && this.v == null) {
                this.v = new C0915i0(getContext(), null);
            }
            ImageView imageView = this.v;
            if (imageView != null) {
                imageView.setContentDescription(charSequenceJ4);
            }
        }
        if (l0L.k(26)) {
            int iB = l0L.b(26, -1);
            this.L = iB;
            TextView textView = this.s;
            if (textView != null) {
                textView.setTextColor(iB);
            }
        }
        if (l0L.k(17)) {
            int iB2 = l0L.b(17, -1);
            this.M = iB2;
            TextView textView2 = this.t;
            if (textView2 != null) {
                textView2.setTextColor(iB2);
            }
        }
        l0L.b.recycle();
    }

    public final void a(List list, int i) {
        boolean z = AbstractC1251o3.k(this) == 1;
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i, getLayoutDirection());
        list.clear();
        if (!z) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                N0 n0 = (N0) childAt.getLayoutParams();
                if (n0.b == 0 && y(childAt) && g(n0.a) == absoluteGravity) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = childCount - 1; i3 >= 0; i3--) {
            View childAt2 = getChildAt(i3);
            N0 n02 = (N0) childAt2.getLayoutParams();
            if (n02.b == 0 && y(childAt2) && g(n02.a) == absoluteGravity) {
                list.add(childAt2);
            }
        }
    }

    public final void b(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        N0 n0GenerateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (N0) layoutParams;
        n0GenerateDefaultLayoutParams.b = 1;
        addView(view, n0GenerateDefaultLayoutParams);
    }

    public final void c() {
        if (this.F == null) {
            this.F = new C1467s0();
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof N0);
    }

    public final void d() {
        if (this.u == null) {
            this.u = new C0803g0(getContext(), null, AbstractC0245Nm.H8);
            N0 n0GenerateDefaultLayoutParams = generateDefaultLayoutParams();
            n0GenerateDefaultLayoutParams.a = 8388611 | (this.z & 112);
            this.u.setLayoutParams(n0GenerateDefaultLayoutParams);
        }
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public N0 generateDefaultLayoutParams() {
        return new N0(-2, -2);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public N0 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof N0 ? new N0((N0) layoutParams) : layoutParams instanceof AbstractC1521t ? new N0((AbstractC1521t) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new N0((ViewGroup.MarginLayoutParams) layoutParams) : new N0(layoutParams);
    }

    public final int g(int i) {
        int iK = AbstractC1251o3.k(this);
        int absoluteGravity = Gravity.getAbsoluteGravity(i, iK) & 7;
        return (absoluteGravity == 1 || absoluteGravity == 3 || absoluteGravity == 5) ? absoluteGravity : iK == 1 ? 5 : 3;
    }

    public final int h(View view, int i) {
        N0 n0 = (N0) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int i3 = n0.a & 112;
        if (i3 != 16 && i3 != 48 && i3 != 80) {
            i3 = this.I & 112;
        }
        if (i3 == 48) {
            return getPaddingTop() - i2;
        }
        if (i3 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) n0).bottomMargin) - i2;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i4 = ((ViewGroup.MarginLayoutParams) n0).topMargin;
        if (iMax < i4) {
            iMax = i4;
        } else {
            int i5 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i6 = ((ViewGroup.MarginLayoutParams) n0).bottomMargin;
            if (i5 < i6) {
                iMax = Math.max(0, iMax - (i6 - i5));
            }
        }
        return paddingTop + iMax;
    }

    public int i() {
        C1467s0 c1467s0 = this.F;
        if (c1467s0 != null) {
            return c1467s0.g ? c1467s0.a : c1467s0.b;
        }
        return 0;
    }

    public int j() {
        if (l() != null) {
            C1467s0 c1467s0 = this.F;
            return Math.max(c1467s0 != null ? c1467s0.g ? c1467s0.b : c1467s0.a : 0, Math.max(this.G, 0));
        }
        C1467s0 c1467s02 = this.F;
        return c1467s02 != null ? c1467s02.g ? c1467s02.b : c1467s02.a : 0;
    }

    public final int k(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
    }

    public Drawable l() {
        ImageButton imageButton = this.u;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public final int m(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public final boolean n(View view) {
        return view.getParent() == this || this.Q.contains(view);
    }

    public final int o(View view, int i, int[] iArr, int i2) {
        N0 n0 = (N0) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) n0).leftMargin - iArr[0];
        int iMax = Math.max(0, i3) + i;
        iArr[0] = Math.max(0, -i3);
        int iH = h(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iH, iMax + measuredWidth, view.getMeasuredHeight() + iH);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) n0).rightMargin + iMax;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.T);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.O = false;
        }
        if (!this.O) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.O = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.O = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 828
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        char c;
        char c2;
        int iK;
        int iMax;
        int iCombineMeasuredStates;
        int iCombineMeasuredStates2;
        int iM;
        int[] iArr = this.R;
        int iMax2 = 0;
        if (X0.a(this)) {
            c = 1;
            c2 = 0;
        } else {
            c = 0;
            c2 = 1;
        }
        if (y(this.u)) {
            r(this.u, i, 0, i2, 0, this.A);
            iK = k(this.u) + this.u.getMeasuredWidth();
            iMax = Math.max(0, m(this.u) + this.u.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.u.getMeasuredState());
        } else {
            iK = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (y(null)) {
            throw null;
        }
        int iJ = j();
        int iMax3 = Math.max(iJ, iK) + 0;
        iArr[c] = Math.max(0, iJ - iK);
        if (y(null)) {
            throw null;
        }
        int i3 = i();
        int iMax4 = Math.max(i3, 0) + iMax3;
        iArr[c2] = Math.max(0, i3 - 0);
        if (y(null)) {
            throw null;
        }
        if (y(this.v)) {
            iMax4 += q(this.v, i, iMax4, i2, 0, iArr);
            iMax = Math.max(iMax, m(this.v) + this.v.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.v.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (((N0) childAt.getLayoutParams()).b == 0 && y(childAt)) {
                iMax4 += q(childAt, i, iMax4, i2, 0, iArr);
                iMax = Math.max(iMax, m(childAt) + childAt.getMeasuredHeight());
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i5 = this.D + this.E;
        int i6 = this.B + this.C;
        if (y(this.s)) {
            q(this.s, i, iMax4 + i6, i2, i5, iArr);
            iMax2 = k(this.s) + this.s.getMeasuredWidth();
            int measuredHeight = this.s.getMeasuredHeight() + m(this.s);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.s.getMeasuredState());
            iM = measuredHeight;
        } else {
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iM = 0;
        }
        if (y(this.t)) {
            iMax2 = Math.max(iMax2, q(this.t, i, iMax4 + i6, i2, iM + i5, iArr));
            iM += m(this.t) + this.t.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.t.getMeasuredState());
        }
        int iMax5 = Math.max(iMax, iM);
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax4 + iMax2, getSuggestedMinimumWidth()), i, (-16777216) & iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax5, getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.s);
        int i = savedState.u;
        if (savedState.v) {
            removeCallbacks(this.T);
            post(this.T);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        c();
        C1467s0 c1467s0 = this.F;
        boolean z = i == 1;
        if (z == c1467s0.g) {
            return;
        }
        c1467s0.g = z;
        if (!c1467s0.h) {
            c1467s0.a = c1467s0.e;
            c1467s0.b = c1467s0.f;
            return;
        }
        if (z) {
            int i2 = c1467s0.d;
            if (i2 == Integer.MIN_VALUE) {
                i2 = c1467s0.e;
            }
            c1467s0.a = i2;
            int i3 = c1467s0.c;
            if (i3 == Integer.MIN_VALUE) {
                i3 = c1467s0.f;
            }
            c1467s0.b = i3;
            return;
        }
        int i4 = c1467s0.c;
        if (i4 == Integer.MIN_VALUE) {
            i4 = c1467s0.e;
        }
        c1467s0.a = i4;
        int i5 = c1467s0.d;
        if (i5 == Integer.MIN_VALUE) {
            i5 = c1467s0.f;
        }
        c1467s0.b = i5;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.v = false;
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.N = false;
        }
        if (!this.N) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.N = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.N = false;
        }
        return true;
    }

    public final int p(View view, int i, int[] iArr, int i2) {
        N0 n0 = (N0) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) n0).rightMargin - iArr[1];
        int iMax = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int iH = h(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iH, iMax, view.getMeasuredHeight() + iH);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) n0).leftMargin);
    }

    public final int q(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i6) + Math.max(0, i5);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + iMax + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public final void r(View view, int i, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public void s(Drawable drawable) {
        if (drawable != null) {
            if (this.v == null) {
                this.v = new C0915i0(getContext(), null);
            }
            if (!n(this.v)) {
                b(this.v, true);
            }
        } else {
            ImageView imageView = this.v;
            if (imageView != null && n(imageView)) {
                removeView(this.v);
                this.Q.remove(this.v);
            }
        }
        ImageView imageView2 = this.v;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void t(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            d();
        }
        ImageButton imageButton = this.u;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void u(Drawable drawable) {
        if (drawable != null) {
            d();
            if (!n(this.u)) {
                b(this.u, true);
            }
        } else {
            ImageButton imageButton = this.u;
            if (imageButton != null && n(imageButton)) {
                removeView(this.u);
                this.Q.remove(this.u);
            }
        }
        ImageButton imageButton2 = this.u;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void v(int i) {
        if (this.w != i) {
            this.w = i;
            if (i == 0) {
                getContext();
            } else {
                new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void w(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.t;
            if (textView != null && n(textView)) {
                removeView(this.t);
                this.Q.remove(this.t);
            }
        } else {
            if (this.t == null) {
                Context context = getContext();
                C1082l0 c1082l0 = new C1082l0(context, null, R.attr.textViewStyle);
                this.t = c1082l0;
                c1082l0.setSingleLine();
                this.t.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.y;
                if (i != 0) {
                    this.t.setTextAppearance(context, i);
                }
                int i2 = this.M;
                if (i2 != 0) {
                    this.t.setTextColor(i2);
                }
            }
            if (!n(this.t)) {
                b(this.t, true);
            }
        }
        TextView textView2 = this.t;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.K = charSequence;
    }

    public void x(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.s;
            if (textView != null && n(textView)) {
                removeView(this.s);
                this.Q.remove(this.s);
            }
        } else {
            if (this.s == null) {
                Context context = getContext();
                C1082l0 c1082l0 = new C1082l0(context, null, R.attr.textViewStyle);
                this.s = c1082l0;
                c1082l0.setSingleLine();
                this.s.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.x;
                if (i != 0) {
                    this.s.setTextAppearance(context, i);
                }
                int i2 = this.L;
                if (i2 != 0) {
                    this.s.setTextColor(i2);
                }
            }
            if (!n(this.s)) {
                b(this.s, true);
            }
        }
        TextView textView2 = this.s;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.f16J = charSequence;
    }

    public final boolean y(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new N0(getContext(), attributeSet);
    }

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new O0();
        public int u;
        public boolean v;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.u = parcel.readInt();
            this.v = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.s, i);
            parcel.writeInt(this.u);
            parcel.writeInt(this.v ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
