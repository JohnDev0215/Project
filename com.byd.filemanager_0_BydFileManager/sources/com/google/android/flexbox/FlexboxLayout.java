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
import b.g.k.t;
import com.google.android.flexbox.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FlexboxLayout extends ViewGroup implements a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3923b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3924c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3925d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3926e;
    private int f;
    private int g;
    private Drawable h;
    private Drawable i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int[] n;
    private SparseIntArray o;
    private c p;
    private List<b> q;
    private c.b r;

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    private void A() {
        if (this.h == null && this.i == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }

    private boolean e(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (this.q.get(i2).c() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean l(int i, int i2) {
        for (int i3 = 1; i3 <= i2; i3++) {
            View viewR = r(i - i3);
            if (viewR != null && viewR.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    private void m(Canvas canvas, boolean z, boolean z2) {
        int paddingLeft = getPaddingLeft();
        int iMax = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.q.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.q.get(i);
            for (int i2 = 0; i2 < bVar.h; i2++) {
                int i3 = bVar.o + i2;
                View viewR = r(i3);
                if (viewR != null && viewR.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) viewR.getLayoutParams();
                    if (s(i3, i2)) {
                        p(canvas, z ? viewR.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin : (viewR.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - this.m, bVar.f3944b, bVar.g);
                    }
                    if (i2 == bVar.h - 1 && (this.k & 4) > 0) {
                        p(canvas, z ? (viewR.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - this.m : viewR.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, bVar.f3944b, bVar.g);
                    }
                }
            }
            if (t(i)) {
                o(canvas, paddingLeft, z2 ? bVar.f3946d : bVar.f3944b - this.l, iMax);
            }
            if (u(i) && (this.j & 4) > 0) {
                o(canvas, paddingLeft, z2 ? bVar.f3944b - this.l : bVar.f3946d, iMax);
            }
        }
    }

    private void n(Canvas canvas, boolean z, boolean z2) {
        int paddingTop = getPaddingTop();
        int iMax = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.q.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.q.get(i);
            for (int i2 = 0; i2 < bVar.h; i2++) {
                int i3 = bVar.o + i2;
                View viewR = r(i3);
                if (viewR != null && viewR.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) viewR.getLayoutParams();
                    if (s(i3, i2)) {
                        o(canvas, bVar.f3943a, z2 ? viewR.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin : (viewR.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - this.l, bVar.g);
                    }
                    if (i2 == bVar.h - 1 && (this.j & 4) > 0) {
                        o(canvas, bVar.f3943a, z2 ? (viewR.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - this.l : viewR.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, bVar.g);
                    }
                }
            }
            if (t(i)) {
                p(canvas, z ? bVar.f3945c : bVar.f3943a - this.m, paddingTop, iMax);
            }
            if (u(i) && (this.k & 4) > 0) {
                p(canvas, z ? bVar.f3943a - this.m : bVar.f3945c, paddingTop, iMax);
            }
        }
    }

    private void o(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.h;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(i, i2, i3 + i, this.l + i2);
        this.h.draw(canvas);
    }

    private void p(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.i;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(i, i2, this.m + i, i3 + i2);
        this.i.draw(canvas);
    }

    private boolean s(int i, int i2) {
        return l(i, i2) ? j() ? (this.k & 1) != 0 : (this.j & 1) != 0 : j() ? (this.k & 2) != 0 : (this.j & 2) != 0;
    }

    private boolean t(int i) {
        if (i < 0 || i >= this.q.size()) {
            return false;
        }
        return e(i) ? j() ? (this.j & 1) != 0 : (this.k & 1) != 0 : j() ? (this.j & 2) != 0 : (this.k & 2) != 0;
    }

    private boolean u(int i) {
        if (i < 0 || i >= this.q.size()) {
            return false;
        }
        for (int i2 = i + 1; i2 < this.q.size(); i2++) {
            if (this.q.get(i2).c() > 0) {
                return false;
            }
        }
        return j() ? (this.j & 4) != 0 : (this.k & 4) != 0;
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
    private void v(boolean r29, int r30, int r31, int r32, int r33) {
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
    private void w(boolean r30, boolean r31, int r32, int r33, int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 541
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.w(boolean, boolean, int, int, int, int):void");
    }

    private void x(int i, int i2) {
        this.q.clear();
        this.r.a();
        this.p.c(this.r, i, i2);
        this.q = this.r.f3953a;
        this.p.p(i, i2);
        if (this.f3926e == 3) {
            for (b bVar : this.q) {
                int iMax = Integer.MIN_VALUE;
                for (int i3 = 0; i3 < bVar.h; i3++) {
                    View viewR = r(bVar.o + i3);
                    if (viewR != null && viewR.getVisibility() != 8) {
                        LayoutParams layoutParams = (LayoutParams) viewR.getLayoutParams();
                        iMax = this.f3924c != 2 ? Math.max(iMax, viewR.getMeasuredHeight() + Math.max(bVar.l - viewR.getBaseline(), ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) : Math.max(iMax, viewR.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + Math.max((bVar.l - viewR.getMeasuredHeight()) + viewR.getBaseline(), ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin));
                    }
                }
                bVar.g = iMax;
            }
        }
        this.p.o(i, i2, getPaddingTop() + getPaddingBottom());
        this.p.W();
        z(this.f3923b, i, i2, this.r.f3954b);
    }

    private void y(int i, int i2) {
        this.q.clear();
        this.r.a();
        this.p.f(this.r, i, i2);
        this.q = this.r.f3953a;
        this.p.p(i, i2);
        this.p.o(i, i2, getPaddingLeft() + getPaddingRight());
        this.p.W();
        z(this.f3923b, i, i2, this.r.f3954b);
    }

    private void z(int i, int i2, int i3, int i4) {
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

    @Override // com.google.android.flexbox.a
    public View a(int i) {
        return getChildAt(i);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.o == null) {
            this.o = new SparseIntArray(getChildCount());
        }
        this.n = this.p.n(view, i, layoutParams, this.o);
        super.addView(view, i, layoutParams);
    }

    @Override // com.google.android.flexbox.a
    public int b(View view, int i, int i2) {
        int i3;
        int i4;
        if (j()) {
            i3 = s(i, i2) ? 0 + this.m : 0;
            if ((this.k & 4) <= 0) {
                return i3;
            }
            i4 = this.m;
        } else {
            i3 = s(i, i2) ? 0 + this.l : 0;
            if ((this.j & 4) <= 0) {
                return i3;
            }
            i4 = this.l;
        }
        return i3 + i4;
    }

    @Override // com.google.android.flexbox.a
    public void c(b bVar) {
        if (j()) {
            if ((this.k & 4) > 0) {
                int i = bVar.f3947e;
                int i2 = this.m;
                bVar.f3947e = i + i2;
                bVar.f += i2;
                return;
            }
            return;
        }
        if ((this.j & 4) > 0) {
            int i3 = bVar.f3947e;
            int i4 = this.l;
            bVar.f3947e = i3 + i4;
            bVar.f += i4;
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // com.google.android.flexbox.a
    public int d(int i, int i2, int i3) {
        return ViewGroup.getChildMeasureSpec(i, i2, i3);
    }

    @Override // com.google.android.flexbox.a
    public View f(int i) {
        return r(i);
    }

    @Override // com.google.android.flexbox.a
    public void g(View view, int i, int i2, b bVar) {
        if (s(i, i2)) {
            if (j()) {
                int i3 = bVar.f3947e;
                int i4 = this.m;
                bVar.f3947e = i3 + i4;
                bVar.f += i4;
                return;
            }
            int i5 = bVar.f3947e;
            int i6 = this.l;
            bVar.f3947e = i5 + i6;
            bVar.f += i6;
        }
    }

    @Override // com.google.android.flexbox.a
    public int getAlignContent() {
        return this.f;
    }

    @Override // com.google.android.flexbox.a
    public int getAlignItems() {
        return this.f3926e;
    }

    public Drawable getDividerDrawableHorizontal() {
        return this.h;
    }

    public Drawable getDividerDrawableVertical() {
        return this.i;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexDirection() {
        return this.f3923b;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexItemCount() {
        return getChildCount();
    }

    public List<b> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.q.size());
        for (b bVar : this.q) {
            if (bVar.c() != 0) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.flexbox.a
    public List<b> getFlexLinesInternal() {
        return this.q;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexWrap() {
        return this.f3924c;
    }

    public int getJustifyContent() {
        return this.f3925d;
    }

    @Override // com.google.android.flexbox.a
    public int getLargestMainSize() {
        Iterator<b> it = this.q.iterator();
        int iMax = Integer.MIN_VALUE;
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().f3947e);
        }
        return iMax;
    }

    @Override // com.google.android.flexbox.a
    public int getMaxLine() {
        return this.g;
    }

    public int getShowDividerHorizontal() {
        return this.j;
    }

    public int getShowDividerVertical() {
        return this.k;
    }

    @Override // com.google.android.flexbox.a
    public int getSumOfCrossSize() {
        int size = this.q.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.q.get(i2);
            if (t(i2)) {
                i += j() ? this.l : this.m;
            }
            if (u(i2)) {
                i += j() ? this.l : this.m;
            }
            i += bVar.g;
        }
        return i;
    }

    @Override // com.google.android.flexbox.a
    public int h(int i, int i2, int i3) {
        return ViewGroup.getChildMeasureSpec(i, i2, i3);
    }

    @Override // com.google.android.flexbox.a
    public void i(int i, View view) {
    }

    @Override // com.google.android.flexbox.a
    public boolean j() {
        int i = this.f3923b;
        return i == 0 || i == 1;
    }

    @Override // com.google.android.flexbox.a
    public int k(View view) {
        return 0;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.i == null && this.h == null) {
            return;
        }
        if (this.j == 0 && this.k == 0) {
            return;
        }
        int iZ = t.z(this);
        int i = this.f3923b;
        if (i == 0) {
            m(canvas, iZ == 1, this.f3924c == 2);
            return;
        }
        if (i == 1) {
            m(canvas, iZ != 1, this.f3924c == 2);
            return;
        }
        if (i == 2) {
            boolean z = iZ == 1;
            if (this.f3924c == 2) {
                z = !z;
            }
            n(canvas, z, false);
            return;
        }
        if (i != 3) {
            return;
        }
        boolean z2 = iZ == 1;
        if (this.f3924c == 2) {
            z2 = !z2;
        }
        n(canvas, z2, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        int iZ = t.z(this);
        int i5 = this.f3923b;
        if (i5 == 0) {
            v(iZ == 1, i, i2, i3, i4);
            return;
        }
        if (i5 == 1) {
            v(iZ != 1, i, i2, i3, i4);
            return;
        }
        if (i5 == 2) {
            z2 = iZ == 1;
            w(this.f3924c == 2 ? !z2 : z2, false, i, i2, i3, i4);
        } else if (i5 == 3) {
            z2 = iZ == 1;
            w(this.f3924c == 2 ? !z2 : z2, true, i, i2, i3, i4);
        } else {
            throw new IllegalStateException("Invalid flex direction is set: " + this.f3923b);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.o == null) {
            this.o = new SparseIntArray(getChildCount());
        }
        if (this.p.N(this.o)) {
            this.n = this.p.m(this.o);
        }
        int i3 = this.f3923b;
        if (i3 == 0 || i3 == 1) {
            x(i, i2);
            return;
        }
        if (i3 == 2 || i3 == 3) {
            y(i, i2);
            return;
        }
        throw new IllegalStateException("Invalid value for the flex direction is set: " + this.f3923b);
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
        int[] iArr = this.n;
        if (i >= iArr.length) {
            return null;
        }
        return getChildAt(iArr[i]);
    }

    public void setAlignContent(int i) {
        if (this.f != i) {
            this.f = i;
            requestLayout();
        }
    }

    public void setAlignItems(int i) {
        if (this.f3926e != i) {
            this.f3926e = i;
            requestLayout();
        }
    }

    public void setDividerDrawable(Drawable drawable) {
        setDividerDrawableHorizontal(drawable);
        setDividerDrawableVertical(drawable);
    }

    public void setDividerDrawableHorizontal(Drawable drawable) {
        if (drawable == this.h) {
            return;
        }
        this.h = drawable;
        if (drawable != null) {
            this.l = drawable.getIntrinsicHeight();
        } else {
            this.l = 0;
        }
        A();
        requestLayout();
    }

    public void setDividerDrawableVertical(Drawable drawable) {
        if (drawable == this.i) {
            return;
        }
        this.i = drawable;
        if (drawable != null) {
            this.m = drawable.getIntrinsicWidth();
        } else {
            this.m = 0;
        }
        A();
        requestLayout();
    }

    public void setFlexDirection(int i) {
        if (this.f3923b != i) {
            this.f3923b = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public void setFlexLines(List<b> list) {
        this.q = list;
    }

    public void setFlexWrap(int i) {
        if (this.f3924c != i) {
            this.f3924c = i;
            requestLayout();
        }
    }

    public void setJustifyContent(int i) {
        if (this.f3925d != i) {
            this.f3925d = i;
            requestLayout();
        }
    }

    public void setMaxLine(int i) {
        if (this.g != i) {
            this.g = i;
            requestLayout();
        }
    }

    public void setShowDivider(int i) {
        setShowDividerVertical(i);
        setShowDividerHorizontal(i);
    }

    public void setShowDividerHorizontal(int i) {
        if (i != this.j) {
            this.j = i;
            requestLayout();
        }
    }

    public void setShowDividerVertical(int i) {
        if (i != this.k) {
            this.k = i;
            requestLayout();
        }
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = -1;
        this.p = new c(this);
        this.q = new ArrayList();
        this.r = new c.b();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.f3957a, i, 0);
        this.f3923b = typedArrayObtainStyledAttributes.getInt(d.g, 0);
        this.f3924c = typedArrayObtainStyledAttributes.getInt(d.h, 0);
        this.f3925d = typedArrayObtainStyledAttributes.getInt(d.i, 0);
        this.f3926e = typedArrayObtainStyledAttributes.getInt(d.f3959c, 4);
        this.f = typedArrayObtainStyledAttributes.getInt(d.f3958b, 5);
        this.g = typedArrayObtainStyledAttributes.getInt(d.j, -1);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(d.f3960d);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(d.f3961e);
        if (drawable2 != null) {
            setDividerDrawableHorizontal(drawable2);
        }
        Drawable drawable3 = typedArrayObtainStyledAttributes.getDrawable(d.f);
        if (drawable3 != null) {
            setDividerDrawableVertical(drawable3);
        }
        int i2 = typedArrayObtainStyledAttributes.getInt(d.k, 0);
        if (i2 != 0) {
            this.k = i2;
            this.j = i2;
        }
        int i3 = typedArrayObtainStyledAttributes.getInt(d.m, 0);
        if (i3 != 0) {
            this.k = i3;
        }
        int i4 = typedArrayObtainStyledAttributes.getInt(d.l, 0);
        if (i4 != 0) {
            this.j = i4;
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3927b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f3928c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f3929d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f3930e;
        private float f;
        private int g;
        private int h;
        private int i;
        private int j;
        private boolean k;

        static class a implements Parcelable.Creator<LayoutParams> {
            a() {
            }

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
            this.f3927b = 1;
            this.f3928c = 0.0f;
            this.f3929d = 1.0f;
            this.f3930e = -1;
            this.f = -1.0f;
            this.i = 16777215;
            this.j = 16777215;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.n);
            this.f3927b = typedArrayObtainStyledAttributes.getInt(d.w, 1);
            this.f3928c = typedArrayObtainStyledAttributes.getFloat(d.q, 0.0f);
            this.f3929d = typedArrayObtainStyledAttributes.getFloat(d.r, 1.0f);
            this.f3930e = typedArrayObtainStyledAttributes.getInt(d.o, -1);
            this.f = typedArrayObtainStyledAttributes.getFraction(d.p, 1, 1, -1.0f);
            this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(d.v, 0);
            this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(d.u, 0);
            this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(d.t, 16777215);
            this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(d.s, 16777215);
            this.k = typedArrayObtainStyledAttributes.getBoolean(d.x, false);
            typedArrayObtainStyledAttributes.recycle();
        }

        @Override // com.google.android.flexbox.FlexItem
        public int a() {
            return ((ViewGroup.MarginLayoutParams) this).width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int b() {
            return ((ViewGroup.MarginLayoutParams) this).height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int c() {
            return ((ViewGroup.MarginLayoutParams) this).rightMargin;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int e() {
            return this.h;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int f() {
            return this.g;
        }

        @Override // com.google.android.flexbox.FlexItem
        public boolean g() {
            return this.k;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getOrder() {
            return this.f3927b;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float h() {
            return this.f3928c;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int k() {
            return this.j;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int l() {
            return ((ViewGroup.MarginLayoutParams) this).bottomMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float m() {
            return this.f;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int n() {
            return ((ViewGroup.MarginLayoutParams) this).leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int o() {
            return this.f3930e;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float q() {
            return this.f3929d;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int r() {
            return this.i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int t() {
            return ((ViewGroup.MarginLayoutParams) this).topMargin;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f3927b);
            parcel.writeFloat(this.f3928c);
            parcel.writeFloat(this.f3929d);
            parcel.writeInt(this.f3930e);
            parcel.writeFloat(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            parcel.writeInt(this.j);
            parcel.writeByte(this.k ? (byte) 1 : (byte) 0);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).bottomMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).leftMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).rightMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).topMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).height);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).width);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.f3927b = 1;
            this.f3928c = 0.0f;
            this.f3929d = 1.0f;
            this.f3930e = -1;
            this.f = -1.0f;
            this.i = 16777215;
            this.j = 16777215;
            this.f3927b = layoutParams.f3927b;
            this.f3928c = layoutParams.f3928c;
            this.f3929d = layoutParams.f3929d;
            this.f3930e = layoutParams.f3930e;
            this.f = layoutParams.f;
            this.g = layoutParams.g;
            this.h = layoutParams.h;
            this.i = layoutParams.i;
            this.j = layoutParams.j;
            this.k = layoutParams.k;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f3927b = 1;
            this.f3928c = 0.0f;
            this.f3929d = 1.0f;
            this.f3930e = -1;
            this.f = -1.0f;
            this.i = 16777215;
            this.j = 16777215;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f3927b = 1;
            this.f3928c = 0.0f;
            this.f3929d = 1.0f;
            this.f3930e = -1;
            this.f = -1.0f;
            this.i = 16777215;
            this.j = 16777215;
        }

        protected LayoutParams(Parcel parcel) {
            super(0, 0);
            this.f3927b = 1;
            this.f3928c = 0.0f;
            this.f3929d = 1.0f;
            this.f3930e = -1;
            this.f = -1.0f;
            this.i = 16777215;
            this.j = 16777215;
            this.f3927b = parcel.readInt();
            this.f3928c = parcel.readFloat();
            this.f3929d = parcel.readFloat();
            this.f3930e = parcel.readInt();
            this.f = parcel.readFloat();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readByte() != 0;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).leftMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).rightMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).topMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).height = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).width = parcel.readInt();
        }
    }
}
