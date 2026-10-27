package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import b.b.a.a.a;
import b.b.a.a.b;
import b.b.a.a.c;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class FlexboxLayout extends ViewGroup implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1128a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1129b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1130c;
    public int d;
    public int e;
    public int f;

    @Nullable
    public Drawable g;

    @Nullable
    public Drawable h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int[] m;
    public SparseIntArray n;
    public c o;
    public List<b> p;
    public c.b q;

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    public final void A() {
        if (this.g == null && this.h == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }

    @Override // b.b.a.a.a
    public View a(int i) {
        return getChildAt(i);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.n == null) {
            this.n = new SparseIntArray(getChildCount());
        }
        this.m = this.o.n(view, i, layoutParams, this.n);
        super.addView(view, i, layoutParams);
    }

    @Override // b.b.a.a.a
    public int b(View view, int i, int i2) {
        int i3;
        int i4;
        if (i()) {
            i3 = s(i, i2) ? 0 + this.l : 0;
            if ((this.j & 4) <= 0) {
                return i3;
            }
            i4 = this.l;
        } else {
            i3 = s(i, i2) ? 0 + this.k : 0;
            if ((this.i & 4) <= 0) {
                return i3;
            }
            i4 = this.k;
        }
        return i3 + i4;
    }

    @Override // b.b.a.a.a
    public void c(b bVar) {
        if (i()) {
            if ((this.j & 4) > 0) {
                int i = bVar.e;
                int i2 = this.l;
                bVar.e = i + i2;
                bVar.f += i2;
                return;
            }
            return;
        }
        if ((this.i & 4) > 0) {
            int i3 = bVar.e;
            int i4 = this.k;
            bVar.e = i3 + i4;
            bVar.f += i4;
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // b.b.a.a.a
    public int d(int i, int i2, int i3) {
        return ViewGroup.getChildMeasureSpec(i, i2, i3);
    }

    @Override // b.b.a.a.a
    public View e(int i) {
        return r(i);
    }

    @Override // b.b.a.a.a
    public void f(View view, int i, int i2, b bVar) {
        if (s(i, i2)) {
            if (i()) {
                int i3 = bVar.e;
                int i4 = this.l;
                bVar.e = i3 + i4;
                bVar.f += i4;
                return;
            }
            int i5 = bVar.e;
            int i6 = this.k;
            bVar.e = i5 + i6;
            bVar.f += i6;
        }
    }

    @Override // b.b.a.a.a
    public int g(int i, int i2, int i3) {
        return ViewGroup.getChildMeasureSpec(i, i2, i3);
    }

    @Override // b.b.a.a.a
    public int getAlignContent() {
        return this.e;
    }

    @Override // b.b.a.a.a
    public int getAlignItems() {
        return this.d;
    }

    @Nullable
    public Drawable getDividerDrawableHorizontal() {
        return this.g;
    }

    @Nullable
    public Drawable getDividerDrawableVertical() {
        return this.h;
    }

    @Override // b.b.a.a.a
    public int getFlexDirection() {
        return this.f1128a;
    }

    @Override // b.b.a.a.a
    public int getFlexItemCount() {
        return getChildCount();
    }

    public List<b> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.p.size());
        for (b bVar : this.p) {
            if (bVar.c() != 0) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    @Override // b.b.a.a.a
    public List<b> getFlexLinesInternal() {
        return this.p;
    }

    @Override // b.b.a.a.a
    public int getFlexWrap() {
        return this.f1129b;
    }

    public int getJustifyContent() {
        return this.f1130c;
    }

    @Override // b.b.a.a.a
    public int getLargestMainSize() {
        Iterator<b> it = this.p.iterator();
        int iMax = Integer.MIN_VALUE;
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().e);
        }
        return iMax;
    }

    @Override // b.b.a.a.a
    public int getMaxLine() {
        return this.f;
    }

    public int getShowDividerHorizontal() {
        return this.i;
    }

    public int getShowDividerVertical() {
        return this.j;
    }

    @Override // b.b.a.a.a
    public int getSumOfCrossSize() {
        int size = this.p.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.p.get(i2);
            if (t(i2)) {
                i += i() ? this.k : this.l;
            }
            if (u(i2)) {
                i += i() ? this.k : this.l;
            }
            i += bVar.g;
        }
        return i;
    }

    @Override // b.b.a.a.a
    public void h(int i, View view) {
    }

    @Override // b.b.a.a.a
    public boolean i() {
        int i = this.f1128a;
        return i == 0 || i == 1;
    }

    @Override // b.b.a.a.a
    public int j(View view) {
        return 0;
    }

    public final boolean k(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (this.p.get(i2).c() > 0) {
                return false;
            }
        }
        return true;
    }

    public final boolean l(int i, int i2) {
        for (int i3 = 1; i3 <= i2; i3++) {
            View viewR = r(i - i3);
            if (viewR != null && viewR.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    public final void m(Canvas canvas, boolean z, boolean z2) {
        int paddingLeft = getPaddingLeft();
        int iMax = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.p.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.p.get(i);
            for (int i2 = 0; i2 < bVar.h; i2++) {
                int i3 = bVar.o + i2;
                View viewR = r(i3);
                if (viewR != null && viewR.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) viewR.getLayoutParams();
                    if (s(i3, i2)) {
                        p(canvas, z ? viewR.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin : (viewR.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - this.l, bVar.f953b, bVar.g);
                    }
                    if (i2 == bVar.h - 1 && (this.j & 4) > 0) {
                        p(canvas, z ? (viewR.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - this.l : viewR.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, bVar.f953b, bVar.g);
                    }
                }
            }
            if (t(i)) {
                o(canvas, paddingLeft, z2 ? bVar.d : bVar.f953b - this.k, iMax);
            }
            if (u(i) && (this.i & 4) > 0) {
                o(canvas, paddingLeft, z2 ? bVar.f953b - this.k : bVar.d, iMax);
            }
        }
    }

    public final void n(Canvas canvas, boolean z, boolean z2) {
        int paddingTop = getPaddingTop();
        int iMax = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.p.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.p.get(i);
            for (int i2 = 0; i2 < bVar.h; i2++) {
                int i3 = bVar.o + i2;
                View viewR = r(i3);
                if (viewR != null && viewR.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) viewR.getLayoutParams();
                    if (s(i3, i2)) {
                        o(canvas, bVar.f952a, z2 ? viewR.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin : (viewR.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - this.k, bVar.g);
                    }
                    if (i2 == bVar.h - 1 && (this.i & 4) > 0) {
                        o(canvas, bVar.f952a, z2 ? (viewR.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - this.k : viewR.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, bVar.g);
                    }
                }
            }
            if (t(i)) {
                p(canvas, z ? bVar.f954c : bVar.f952a - this.l, paddingTop, iMax);
            }
            if (u(i) && (this.j & 4) > 0) {
                p(canvas, z ? bVar.f952a - this.l : bVar.f954c, paddingTop, iMax);
            }
        }
    }

    public final void o(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.g;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(i, i2, i3 + i, this.k + i2);
        this.g.draw(canvas);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.h == null && this.g == null) {
            return;
        }
        if (this.i == 0 && this.j == 0) {
            return;
        }
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int i = this.f1128a;
        if (i == 0) {
            m(canvas, layoutDirection == 1, this.f1129b == 2);
            return;
        }
        if (i == 1) {
            m(canvas, layoutDirection != 1, this.f1129b == 2);
            return;
        }
        if (i == 2) {
            boolean z = layoutDirection == 1;
            if (this.f1129b == 2) {
                z = !z;
            }
            n(canvas, z, false);
            return;
        }
        if (i != 3) {
            return;
        }
        boolean z2 = layoutDirection == 1;
        if (this.f1129b == 2) {
            z2 = !z2;
        }
        n(canvas, z2, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int i5 = this.f1128a;
        if (i5 == 0) {
            v(layoutDirection == 1, i, i2, i3, i4);
            return;
        }
        if (i5 == 1) {
            v(layoutDirection != 1, i, i2, i3, i4);
            return;
        }
        if (i5 == 2) {
            z2 = layoutDirection == 1;
            w(this.f1129b == 2 ? !z2 : z2, false, i, i2, i3, i4);
        } else if (i5 == 3) {
            z2 = layoutDirection == 1;
            w(this.f1129b == 2 ? !z2 : z2, true, i, i2, i3, i4);
        } else {
            throw new IllegalStateException("Invalid flex direction is set: " + this.f1128a);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (this.n == null) {
            this.n = new SparseIntArray(getChildCount());
        }
        if (this.o.N(this.n)) {
            this.m = this.o.m(this.n);
        }
        int i3 = this.f1128a;
        if (i3 == 0 || i3 == 1) {
            x(i, i2);
            return;
        }
        if (i3 == 2 || i3 == 3) {
            y(i, i2);
            return;
        }
        throw new IllegalStateException("Invalid value for the flex direction is set: " + this.f1128a);
    }

    public final void p(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.h;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(i, i2, this.l + i, i3 + i2);
        this.h.draw(canvas);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public View r(int i) {
        if (i < 0) {
            return null;
        }
        int[] iArr = this.m;
        if (i >= iArr.length) {
            return null;
        }
        return getChildAt(iArr[i]);
    }

    public final boolean s(int i, int i2) {
        return l(i, i2) ? i() ? (this.j & 1) != 0 : (this.i & 1) != 0 : i() ? (this.j & 2) != 0 : (this.i & 2) != 0;
    }

    public void setAlignContent(int i) {
        if (this.e != i) {
            this.e = i;
            requestLayout();
        }
    }

    public void setAlignItems(int i) {
        if (this.d != i) {
            this.d = i;
            requestLayout();
        }
    }

    public void setDividerDrawable(Drawable drawable) {
        setDividerDrawableHorizontal(drawable);
        setDividerDrawableVertical(drawable);
    }

    public void setDividerDrawableHorizontal(@Nullable Drawable drawable) {
        if (drawable == this.g) {
            return;
        }
        this.g = drawable;
        if (drawable != null) {
            this.k = drawable.getIntrinsicHeight();
        } else {
            this.k = 0;
        }
        A();
        requestLayout();
    }

    public void setDividerDrawableVertical(@Nullable Drawable drawable) {
        if (drawable == this.h) {
            return;
        }
        this.h = drawable;
        if (drawable != null) {
            this.l = drawable.getIntrinsicWidth();
        } else {
            this.l = 0;
        }
        A();
        requestLayout();
    }

    public void setFlexDirection(int i) {
        if (this.f1128a != i) {
            this.f1128a = i;
            requestLayout();
        }
    }

    @Override // b.b.a.a.a
    public void setFlexLines(List<b> list) {
        this.p = list;
    }

    public void setFlexWrap(int i) {
        if (this.f1129b != i) {
            this.f1129b = i;
            requestLayout();
        }
    }

    public void setJustifyContent(int i) {
        if (this.f1130c != i) {
            this.f1130c = i;
            requestLayout();
        }
    }

    public void setMaxLine(int i) {
        if (this.f != i) {
            this.f = i;
            requestLayout();
        }
    }

    public void setShowDivider(int i) {
        setShowDividerVertical(i);
        setShowDividerHorizontal(i);
    }

    public void setShowDividerHorizontal(int i) {
        if (i != this.i) {
            this.i = i;
            requestLayout();
        }
    }

    public void setShowDividerVertical(int i) {
        if (i != this.j) {
            this.j = i;
            requestLayout();
        }
    }

    public final boolean t(int i) {
        if (i < 0 || i >= this.p.size()) {
            return false;
        }
        return k(i) ? i() ? (this.i & 1) != 0 : (this.j & 1) != 0 : i() ? (this.i & 2) != 0 : (this.j & 2) != 0;
    }

    public final boolean u(int i) {
        if (i < 0 || i >= this.p.size()) {
            return false;
        }
        for (int i2 = i + 1; i2 < this.p.size(); i2++) {
            if (this.p.get(i2).c() > 0) {
                return false;
            }
        }
        return i() ? (this.i & 4) != 0 : (this.j & 4) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v(boolean r29, int r30, int r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 557
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.v(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void w(boolean r30, boolean r31, int r32, int r33, int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 541
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.w(boolean, boolean, int, int, int, int):void");
    }

    public final void x(int i, int i2) {
        this.p.clear();
        this.q.a();
        this.o.c(this.q, i, i2);
        this.p = this.q.f958a;
        this.o.p(i, i2);
        if (this.d == 3) {
            for (b bVar : this.p) {
                int iMax = Integer.MIN_VALUE;
                for (int i3 = 0; i3 < bVar.h; i3++) {
                    View viewR = r(bVar.o + i3);
                    if (viewR != null && viewR.getVisibility() != 8) {
                        LayoutParams layoutParams = (LayoutParams) viewR.getLayoutParams();
                        iMax = this.f1129b != 2 ? Math.max(iMax, viewR.getMeasuredHeight() + Math.max(bVar.l - viewR.getBaseline(), ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) : Math.max(iMax, viewR.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + Math.max((bVar.l - viewR.getMeasuredHeight()) + viewR.getBaseline(), ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin));
                    }
                }
                bVar.g = iMax;
            }
        }
        this.o.o(i, i2, getPaddingTop() + getPaddingBottom());
        this.o.W();
        z(this.f1128a, i, i2, this.q.f959b);
    }

    public final void y(int i, int i2) {
        this.p.clear();
        this.q.a();
        this.o.f(this.q, i, i2);
        this.p = this.q.f958a;
        this.o.p(i, i2);
        this.o.o(i, i2, getPaddingLeft() + getPaddingRight());
        this.o.W();
        z(this.f1128a, i, i2, this.q.f959b);
    }

    public final void z(int i, int i2, int i3, int i4) {
        int sumOfCrossSize;
        int largestMainSize;
        int iResolveSizeAndState;
        int iResolveSizeAndState2;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (i == 0 || i == 1) {
            sumOfCrossSize = getSumOfCrossSize() + getPaddingTop() + getPaddingBottom();
            largestMainSize = getLargestMainSize();
        } else {
            if (i != 2 && i != 3) {
                throw new IllegalArgumentException("Invalid flex direction: " + i);
            }
            sumOfCrossSize = getLargestMainSize();
            largestMainSize = getSumOfCrossSize() + getPaddingLeft() + getPaddingRight();
        }
        if (mode == Integer.MIN_VALUE) {
            if (size < largestMainSize) {
                i4 = View.combineMeasuredStates(i4, 16777216);
            } else {
                size = largestMainSize;
            }
            iResolveSizeAndState = View.resolveSizeAndState(size, i2, i4);
        } else if (mode == 0) {
            iResolveSizeAndState = View.resolveSizeAndState(largestMainSize, i2, i4);
        } else {
            if (mode != 1073741824) {
                throw new IllegalStateException("Unknown width mode is set: " + mode);
            }
            if (size < largestMainSize) {
                i4 = View.combineMeasuredStates(i4, 16777216);
            }
            iResolveSizeAndState = View.resolveSizeAndState(size, i2, i4);
        }
        if (mode2 == Integer.MIN_VALUE) {
            if (size2 < sumOfCrossSize) {
                i4 = View.combineMeasuredStates(i4, 256);
            } else {
                size2 = sumOfCrossSize;
            }
            iResolveSizeAndState2 = View.resolveSizeAndState(size2, i3, i4);
        } else if (mode2 == 0) {
            iResolveSizeAndState2 = View.resolveSizeAndState(sumOfCrossSize, i3, i4);
        } else {
            if (mode2 != 1073741824) {
                throw new IllegalStateException("Unknown height mode is set: " + mode2);
            }
            if (size2 < sumOfCrossSize) {
                i4 = View.combineMeasuredStates(i4, 256);
            }
            iResolveSizeAndState2 = View.resolveSizeAndState(size2, i3, i4);
        }
        setMeasuredDimension(iResolveSizeAndState, iResolveSizeAndState2);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = -1;
        this.o = new c(this);
        this.p = new ArrayList();
        this.q = new c.b();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FlexboxLayout, i, 0);
        this.f1128a = typedArrayObtainStyledAttributes.getInt(R$styleable.FlexboxLayout_flexDirection, 0);
        this.f1129b = typedArrayObtainStyledAttributes.getInt(R$styleable.FlexboxLayout_flexWrap, 0);
        this.f1130c = typedArrayObtainStyledAttributes.getInt(R$styleable.FlexboxLayout_justifyContent, 0);
        this.d = typedArrayObtainStyledAttributes.getInt(R$styleable.FlexboxLayout_alignItems, 4);
        this.e = typedArrayObtainStyledAttributes.getInt(R$styleable.FlexboxLayout_alignContent, 5);
        this.f = typedArrayObtainStyledAttributes.getInt(R$styleable.FlexboxLayout_maxLine, -1);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.FlexboxLayout_dividerDrawable);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(R$styleable.FlexboxLayout_dividerDrawableHorizontal);
        if (drawable2 != null) {
            setDividerDrawableHorizontal(drawable2);
        }
        Drawable drawable3 = typedArrayObtainStyledAttributes.getDrawable(R$styleable.FlexboxLayout_dividerDrawableVertical);
        if (drawable3 != null) {
            setDividerDrawableVertical(drawable3);
        }
        int i2 = typedArrayObtainStyledAttributes.getInt(R$styleable.FlexboxLayout_showDivider, 0);
        if (i2 != 0) {
            this.j = i2;
            this.i = i2;
        }
        int i3 = typedArrayObtainStyledAttributes.getInt(R$styleable.FlexboxLayout_showDividerVertical, 0);
        if (i3 != 0) {
            this.j = i3;
        }
        int i4 = typedArrayObtainStyledAttributes.getInt(R$styleable.FlexboxLayout_showDividerHorizontal, 0);
        if (i4 != 0) {
            this.i = i4;
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1131a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1132b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1133c;
        public int d;
        public float e;
        public int f;
        public int g;
        public int h;
        public int i;
        public boolean j;

        /* JADX INFO: compiled from: caradapter-bug */
        public static class a implements Parcelable.Creator<LayoutParams> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public LayoutParams[] newArray(int i) {
                return new LayoutParams[i];
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1131a = 1;
            this.f1132b = 0.0f;
            this.f1133c = 1.0f;
            this.d = -1;
            this.e = -1.0f;
            this.h = ViewCompat.MEASURED_SIZE_MASK;
            this.i = ViewCompat.MEASURED_SIZE_MASK;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FlexboxLayout_Layout);
            this.f1131a = typedArrayObtainStyledAttributes.getInt(R$styleable.FlexboxLayout_Layout_layout_order, 1);
            this.f1132b = typedArrayObtainStyledAttributes.getFloat(R$styleable.FlexboxLayout_Layout_layout_flexGrow, 0.0f);
            this.f1133c = typedArrayObtainStyledAttributes.getFloat(R$styleable.FlexboxLayout_Layout_layout_flexShrink, 1.0f);
            this.d = typedArrayObtainStyledAttributes.getInt(R$styleable.FlexboxLayout_Layout_layout_alignSelf, -1);
            this.e = typedArrayObtainStyledAttributes.getFraction(R$styleable.FlexboxLayout_Layout_layout_flexBasisPercent, 1, 1, -1.0f);
            this.f = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.FlexboxLayout_Layout_layout_minWidth, 0);
            this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.FlexboxLayout_Layout_layout_minHeight, 0);
            this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.FlexboxLayout_Layout_layout_maxWidth, ViewCompat.MEASURED_SIZE_MASK);
            this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.FlexboxLayout_Layout_layout_maxHeight, ViewCompat.MEASURED_SIZE_MASK);
            this.j = typedArrayObtainStyledAttributes.getBoolean(R$styleable.FlexboxLayout_Layout_layout_wrapBefore, false);
            typedArrayObtainStyledAttributes.recycle();
        }

        @Override // com.google.android.flexbox.FlexItem
        public int a() {
            return ((ViewGroup.MarginLayoutParams) this).rightMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int b() {
            return this.g;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int c() {
            return this.f;
        }

        @Override // com.google.android.flexbox.FlexItem
        public boolean d() {
            return this.j;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float e() {
            return this.f1132b;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int f() {
            return this.i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int g() {
            return ((ViewGroup.MarginLayoutParams) this).bottomMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getHeight() {
            return ((ViewGroup.MarginLayoutParams) this).height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getOrder() {
            return this.f1131a;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getWidth() {
            return ((ViewGroup.MarginLayoutParams) this).width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float h() {
            return this.e;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int i() {
            return ((ViewGroup.MarginLayoutParams) this).leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int j() {
            return this.d;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float k() {
            return this.f1133c;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int l() {
            return this.h;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int m() {
            return ((ViewGroup.MarginLayoutParams) this).topMargin;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1131a);
            parcel.writeFloat(this.f1132b);
            parcel.writeFloat(this.f1133c);
            parcel.writeInt(this.d);
            parcel.writeFloat(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            parcel.writeByte(this.j ? (byte) 1 : (byte) 0);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).bottomMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).leftMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).rightMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).topMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).height);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).width);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.f1131a = 1;
            this.f1132b = 0.0f;
            this.f1133c = 1.0f;
            this.d = -1;
            this.e = -1.0f;
            this.h = ViewCompat.MEASURED_SIZE_MASK;
            this.i = ViewCompat.MEASURED_SIZE_MASK;
            this.f1131a = layoutParams.f1131a;
            this.f1132b = layoutParams.f1132b;
            this.f1133c = layoutParams.f1133c;
            this.d = layoutParams.d;
            this.e = layoutParams.e;
            this.f = layoutParams.f;
            this.g = layoutParams.g;
            this.h = layoutParams.h;
            this.i = layoutParams.i;
            this.j = layoutParams.j;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1131a = 1;
            this.f1132b = 0.0f;
            this.f1133c = 1.0f;
            this.d = -1;
            this.e = -1.0f;
            this.h = ViewCompat.MEASURED_SIZE_MASK;
            this.i = ViewCompat.MEASURED_SIZE_MASK;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1131a = 1;
            this.f1132b = 0.0f;
            this.f1133c = 1.0f;
            this.d = -1;
            this.e = -1.0f;
            this.h = ViewCompat.MEASURED_SIZE_MASK;
            this.i = ViewCompat.MEASURED_SIZE_MASK;
        }

        public LayoutParams(Parcel parcel) {
            super(0, 0);
            this.f1131a = 1;
            this.f1132b = 0.0f;
            this.f1133c = 1.0f;
            this.d = -1;
            this.e = -1.0f;
            this.h = ViewCompat.MEASURED_SIZE_MASK;
            this.i = ViewCompat.MEASURED_SIZE_MASK;
            this.f1131a = parcel.readInt();
            this.f1132b = parcel.readFloat();
            this.f1133c = parcel.readFloat();
            this.d = parcel.readInt();
            this.e = parcel.readFloat();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            this.j = parcel.readByte() != 0;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).leftMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).rightMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).topMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).height = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).width = parcel.readInt();
        }
    }
}
