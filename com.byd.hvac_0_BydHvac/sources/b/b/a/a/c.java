package b.b.a.a;

import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.view.MarginLayoutParamsCompat;
import com.google.android.flexbox.FlexItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.a.a.a f955a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean[] f956b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    public int[] f957c;

    @Nullable
    public long[] d;

    @Nullable
    public long[] e;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public List<b.b.a.a.b> f958a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f959b;

        public void a() {
            this.f958a = null;
            this.f959b = 0;
        }
    }

    /* JADX INFO: renamed from: b.b.a.a.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public static class C0008c implements Comparable<C0008c> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f960a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f961b;

        public C0008c() {
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(@NonNull C0008c c0008c) {
            int i = this.f961b;
            int i2 = c0008c.f961b;
            return i != i2 ? i - i2 : this.f960a - c0008c.f960a;
        }

        public String toString() {
            return "Order{order=" + this.f961b + ", index=" + this.f960a + '}';
        }
    }

    public c(b.b.a.a.a aVar) {
        this.f955a = aVar;
    }

    public final int A(FlexItem flexItem, boolean z) {
        return z ? flexItem.g() : flexItem.a();
    }

    public final int B(FlexItem flexItem, boolean z) {
        return z ? flexItem.a() : flexItem.g();
    }

    public final int C(FlexItem flexItem, boolean z) {
        return z ? flexItem.m() : flexItem.i();
    }

    public final int D(FlexItem flexItem, boolean z) {
        return z ? flexItem.i() : flexItem.m();
    }

    public final int E(FlexItem flexItem, boolean z) {
        return z ? flexItem.getHeight() : flexItem.getWidth();
    }

    public final int F(FlexItem flexItem, boolean z) {
        return z ? flexItem.getWidth() : flexItem.getHeight();
    }

    public final int G(boolean z) {
        return z ? this.f955a.getPaddingBottom() : this.f955a.getPaddingEnd();
    }

    public final int H(boolean z) {
        return z ? this.f955a.getPaddingEnd() : this.f955a.getPaddingBottom();
    }

    public final int I(boolean z) {
        return z ? this.f955a.getPaddingTop() : this.f955a.getPaddingStart();
    }

    public final int J(boolean z) {
        return z ? this.f955a.getPaddingStart() : this.f955a.getPaddingTop();
    }

    public final int K(View view, boolean z) {
        return z ? view.getMeasuredHeight() : view.getMeasuredWidth();
    }

    public final int L(View view, boolean z) {
        return z ? view.getMeasuredWidth() : view.getMeasuredHeight();
    }

    public final boolean M(int i, int i2, b.b.a.a.b bVar) {
        return i == i2 - 1 && bVar.c() != 0;
    }

    public boolean N(SparseIntArray sparseIntArray) {
        int flexItemCount = this.f955a.getFlexItemCount();
        if (sparseIntArray.size() != flexItemCount) {
            return true;
        }
        for (int i = 0; i < flexItemCount; i++) {
            View viewA = this.f955a.a(i);
            if (viewA != null && ((FlexItem) viewA.getLayoutParams()).getOrder() != sparseIntArray.get(i)) {
                return true;
            }
        }
        return false;
    }

    public final boolean O(View view, int i, int i2, int i3, int i4, FlexItem flexItem, int i5, int i6, int i7) {
        if (this.f955a.getFlexWrap() == 0) {
            return false;
        }
        if (flexItem.d()) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        int maxLine = this.f955a.getMaxLine();
        if (maxLine != -1 && maxLine <= i7 + 1) {
            return false;
        }
        int iB = this.f955a.b(view, i5, i6);
        if (iB > 0) {
            i4 += iB;
        }
        return i2 < i3 + i4;
    }

    public void P(View view, b.b.a.a.b bVar, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.f955a.getAlignItems();
        if (flexItem.j() != -1) {
            alignItems = flexItem.j();
        }
        int i5 = bVar.g;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (this.f955a.getFlexWrap() == 2) {
                    view.layout(i, (i2 - i5) + view.getMeasuredHeight() + flexItem.m(), i3, (i4 - i5) + view.getMeasuredHeight() + flexItem.m());
                    return;
                } else {
                    int i6 = i2 + i5;
                    view.layout(i, (i6 - view.getMeasuredHeight()) - flexItem.g(), i3, i6 - flexItem.g());
                    return;
                }
            }
            if (alignItems == 2) {
                int measuredHeight = (((i5 - view.getMeasuredHeight()) + flexItem.m()) - flexItem.g()) / 2;
                if (this.f955a.getFlexWrap() != 2) {
                    int i7 = i2 + measuredHeight;
                    view.layout(i, i7, i3, view.getMeasuredHeight() + i7);
                    return;
                } else {
                    int i8 = i2 - measuredHeight;
                    view.layout(i, i8, i3, view.getMeasuredHeight() + i8);
                    return;
                }
            }
            if (alignItems == 3) {
                if (this.f955a.getFlexWrap() != 2) {
                    int iMax = Math.max(bVar.l - view.getBaseline(), flexItem.m());
                    view.layout(i, i2 + iMax, i3, i4 + iMax);
                    return;
                } else {
                    int iMax2 = Math.max((bVar.l - view.getMeasuredHeight()) + view.getBaseline(), flexItem.g());
                    view.layout(i, i2 - iMax2, i3, i4 - iMax2);
                    return;
                }
            }
            if (alignItems != 4) {
                return;
            }
        }
        if (this.f955a.getFlexWrap() != 2) {
            view.layout(i, i2 + flexItem.m(), i3, i4 + flexItem.m());
        } else {
            view.layout(i, i2 - flexItem.g(), i3, i4 - flexItem.g());
        }
    }

    public void Q(View view, b.b.a.a.b bVar, boolean z, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.f955a.getAlignItems();
        if (flexItem.j() != -1) {
            alignItems = flexItem.j();
        }
        int i5 = bVar.g;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (z) {
                    view.layout((i - i5) + view.getMeasuredWidth() + flexItem.i(), i2, (i3 - i5) + view.getMeasuredWidth() + flexItem.i(), i4);
                    return;
                } else {
                    view.layout(((i + i5) - view.getMeasuredWidth()) - flexItem.a(), i2, ((i3 + i5) - view.getMeasuredWidth()) - flexItem.a(), i4);
                    return;
                }
            }
            if (alignItems == 2) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int measuredWidth = (((i5 - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(marginLayoutParams)) - MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) / 2;
                if (z) {
                    view.layout(i - measuredWidth, i2, i3 - measuredWidth, i4);
                    return;
                } else {
                    view.layout(i + measuredWidth, i2, i3 + measuredWidth, i4);
                    return;
                }
            }
            if (alignItems != 3 && alignItems != 4) {
                return;
            }
        }
        if (z) {
            view.layout(i - flexItem.a(), i2, i3 - flexItem.a(), i4);
        } else {
            view.layout(i + flexItem.i(), i2, i3 + flexItem.i(), i4);
        }
    }

    @VisibleForTesting
    public long R(int i, int i2) {
        return (((long) i) & 4294967295L) | (((long) i2) << 32);
    }

    public final void S(int i, int i2, b.b.a.a.b bVar, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int iMax;
        int i7 = bVar.e;
        float f = bVar.k;
        float f2 = 0.0f;
        if (f <= 0.0f || i3 > i7) {
            return;
        }
        float f3 = (i7 - i3) / f;
        bVar.e = i4 + bVar.f;
        if (!z) {
            bVar.g = Integer.MIN_VALUE;
        }
        int i8 = 0;
        boolean z2 = false;
        int i9 = 0;
        float f4 = 0.0f;
        while (i8 < bVar.h) {
            int i10 = bVar.o + i8;
            View viewE = this.f955a.e(i10);
            if (viewE == null || viewE.getVisibility() == 8) {
                i5 = i7;
                i6 = i8;
            } else {
                FlexItem flexItem = (FlexItem) viewE.getLayoutParams();
                int flexDirection = this.f955a.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    i5 = i7;
                    int i11 = i8;
                    int measuredWidth = viewE.getMeasuredWidth();
                    long[] jArr = this.e;
                    if (jArr != null) {
                        measuredWidth = x(jArr[i10]);
                    }
                    int measuredHeight = viewE.getMeasuredHeight();
                    long[] jArr2 = this.e;
                    if (jArr2 != null) {
                        measuredHeight = w(jArr2[i10]);
                    }
                    if (this.f956b[i10] || flexItem.k() <= 0.0f) {
                        i6 = i11;
                    } else {
                        float fK = measuredWidth - (flexItem.k() * f3);
                        i6 = i11;
                        if (i6 == bVar.h - 1) {
                            fK += f4;
                            f4 = 0.0f;
                        }
                        int iRound = Math.round(fK);
                        if (iRound < flexItem.c()) {
                            iRound = flexItem.c();
                            this.f956b[i10] = true;
                            bVar.k -= flexItem.k();
                            z2 = true;
                        } else {
                            f4 += fK - iRound;
                            double d = f4;
                            if (d > 1.0d) {
                                iRound++;
                                f4 -= 1.0f;
                            } else if (d < -1.0d) {
                                iRound--;
                                f4 += 1.0f;
                            }
                        }
                        int iY = y(i2, flexItem, bVar.m);
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iRound, 1073741824);
                        viewE.measure(iMakeMeasureSpec, iY);
                        int measuredWidth2 = viewE.getMeasuredWidth();
                        int measuredHeight2 = viewE.getMeasuredHeight();
                        Y(i10, iMakeMeasureSpec, iY, viewE);
                        this.f955a.h(i10, viewE);
                        measuredWidth = measuredWidth2;
                        measuredHeight = measuredHeight2;
                    }
                    int iMax2 = Math.max(i9, measuredHeight + flexItem.m() + flexItem.g() + this.f955a.j(viewE));
                    bVar.e += measuredWidth + flexItem.i() + flexItem.a();
                    iMax = iMax2;
                } else {
                    int measuredHeight3 = viewE.getMeasuredHeight();
                    long[] jArr3 = this.e;
                    if (jArr3 != null) {
                        measuredHeight3 = w(jArr3[i10]);
                    }
                    int measuredWidth3 = viewE.getMeasuredWidth();
                    long[] jArr4 = this.e;
                    if (jArr4 != null) {
                        measuredWidth3 = x(jArr4[i10]);
                    }
                    if (this.f956b[i10] || flexItem.k() <= f2) {
                        i5 = i7;
                        i6 = i8;
                    } else {
                        float fK2 = measuredHeight3 - (flexItem.k() * f3);
                        if (i8 == bVar.h - 1) {
                            fK2 += f4;
                            f4 = f2;
                        }
                        int iRound2 = Math.round(fK2);
                        if (iRound2 < flexItem.b()) {
                            iRound2 = flexItem.b();
                            this.f956b[i10] = true;
                            bVar.k -= flexItem.k();
                            i5 = i7;
                            i6 = i8;
                            z2 = true;
                        } else {
                            f4 += fK2 - iRound2;
                            i5 = i7;
                            i6 = i8;
                            double d2 = f4;
                            if (d2 > 1.0d) {
                                iRound2++;
                                f4 -= 1.0f;
                            } else if (d2 < -1.0d) {
                                iRound2--;
                                f4 += 1.0f;
                            }
                        }
                        int iZ = z(i, flexItem, bVar.m);
                        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iRound2, 1073741824);
                        viewE.measure(iZ, iMakeMeasureSpec2);
                        measuredWidth3 = viewE.getMeasuredWidth();
                        int measuredHeight4 = viewE.getMeasuredHeight();
                        Y(i10, iZ, iMakeMeasureSpec2, viewE);
                        this.f955a.h(i10, viewE);
                        measuredHeight3 = measuredHeight4;
                    }
                    iMax = Math.max(i9, measuredWidth3 + flexItem.i() + flexItem.a() + this.f955a.j(viewE));
                    bVar.e += measuredHeight3 + flexItem.m() + flexItem.g();
                }
                bVar.g = Math.max(bVar.g, iMax);
                i9 = iMax;
            }
            i8 = i6 + 1;
            i7 = i5;
            f2 = 0.0f;
        }
        int i12 = i7;
        if (!z2 || i12 == bVar.e) {
            return;
        }
        S(i, i2, bVar, i3, i4, true);
    }

    public final int[] T(int i, List<C0008c> list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        int i2 = 0;
        for (C0008c c0008c : list) {
            int i3 = c0008c.f960a;
            iArr[i2] = i3;
            sparseIntArray.append(i3, c0008c.f961b);
            i2++;
        }
        return iArr;
    }

    public final void U(View view, int i, int i2) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int iMin = Math.min(Math.max(((i - flexItem.i()) - flexItem.a()) - this.f955a.j(view), flexItem.c()), flexItem.l());
        long[] jArr = this.e;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(jArr != null ? w(jArr[i2]) : view.getMeasuredHeight(), 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMin, 1073741824);
        view.measure(iMakeMeasureSpec2, iMakeMeasureSpec);
        Y(i2, iMakeMeasureSpec2, iMakeMeasureSpec, view);
        this.f955a.h(i2, view);
    }

    public final void V(View view, int i, int i2) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int iMin = Math.min(Math.max(((i - flexItem.m()) - flexItem.g()) - this.f955a.j(view), flexItem.b()), flexItem.f());
        long[] jArr = this.e;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(jArr != null ? x(jArr[i2]) : view.getMeasuredWidth(), 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMin, 1073741824);
        view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        Y(i2, iMakeMeasureSpec, iMakeMeasureSpec2, view);
        this.f955a.h(i2, view);
    }

    public void W() {
        X(0);
    }

    public void X(int i) {
        View viewE;
        if (i >= this.f955a.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.f955a.getFlexDirection();
        if (this.f955a.getAlignItems() != 4) {
            for (b.b.a.a.b bVar : this.f955a.getFlexLinesInternal()) {
                for (Integer num : bVar.n) {
                    View viewE2 = this.f955a.e(num.intValue());
                    if (flexDirection == 0 || flexDirection == 1) {
                        V(viewE2, bVar.g, num.intValue());
                    } else {
                        if (flexDirection != 2 && flexDirection != 3) {
                            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                        }
                        U(viewE2, bVar.g, num.intValue());
                    }
                }
            }
            return;
        }
        int[] iArr = this.f957c;
        List<b.b.a.a.b> flexLinesInternal = this.f955a.getFlexLinesInternal();
        int size = flexLinesInternal.size();
        for (int i2 = iArr != null ? iArr[i] : 0; i2 < size; i2++) {
            b.b.a.a.b bVar2 = flexLinesInternal.get(i2);
            int i3 = bVar2.h;
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = bVar2.o + i4;
                if (i4 < this.f955a.getFlexItemCount() && (viewE = this.f955a.e(i5)) != null && viewE.getVisibility() != 8) {
                    FlexItem flexItem = (FlexItem) viewE.getLayoutParams();
                    if (flexItem.j() == -1 || flexItem.j() == 4) {
                        if (flexDirection == 0 || flexDirection == 1) {
                            V(viewE, bVar2.g, i5);
                        } else {
                            if (flexDirection != 2 && flexDirection != 3) {
                                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                            }
                            U(viewE, bVar2.g, i5);
                        }
                    }
                }
            }
        }
    }

    public final void Y(int i, int i2, int i3, View view) {
        long[] jArr = this.d;
        if (jArr != null) {
            jArr[i] = R(i2, i3);
        }
        long[] jArr2 = this.e;
        if (jArr2 != null) {
            jArr2[i] = R(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    public final void a(List<b.b.a.a.b> list, b.b.a.a.b bVar, int i, int i2) {
        bVar.m = i2;
        this.f955a.c(bVar);
        bVar.p = i;
        list.add(bVar);
    }

    public void b(b bVar, int i, int i2, int i3, int i4, int i5, @Nullable List<b.b.a.a.b> list) {
        int i6;
        b bVar2;
        int i7;
        int i8;
        int i9;
        List<b.b.a.a.b> list2;
        int i10;
        View view;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17 = i;
        int i18 = i2;
        int i19 = i5;
        boolean zI = this.f955a.i();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        List<b.b.a.a.b> arrayList = list == null ? new ArrayList() : list;
        bVar.f958a = arrayList;
        boolean z = i19 == -1;
        int iJ = J(zI);
        int iH = H(zI);
        int I = I(zI);
        int iG = G(zI);
        b.b.a.a.b bVar3 = new b.b.a.a.b();
        int i20 = i4;
        bVar3.o = i20;
        int i21 = iH + iJ;
        bVar3.e = i21;
        int flexItemCount = this.f955a.getFlexItemCount();
        boolean z2 = z;
        int i22 = Integer.MIN_VALUE;
        int i23 = 0;
        int iCombineMeasuredStates = 0;
        int i24 = 0;
        while (true) {
            if (i20 >= flexItemCount) {
                i6 = iCombineMeasuredStates;
                bVar2 = bVar;
                break;
            }
            View viewE = this.f955a.e(i20);
            if (viewE == null) {
                if (M(i20, flexItemCount, bVar3)) {
                    a(arrayList, bVar3, i20, i23);
                }
            } else if (viewE.getVisibility() == 8) {
                bVar3.i++;
                bVar3.h++;
                if (M(i20, flexItemCount, bVar3)) {
                    a(arrayList, bVar3, i20, i23);
                }
            } else {
                FlexItem flexItem = (FlexItem) viewE.getLayoutParams();
                int i25 = flexItemCount;
                if (flexItem.j() == 4) {
                    bVar3.n.add(Integer.valueOf(i20));
                }
                int iF = F(flexItem, zI);
                if (flexItem.h() != -1.0f && mode == 1073741824) {
                    iF = Math.round(size * flexItem.h());
                }
                if (zI) {
                    int iG2 = this.f955a.g(i17, i21 + D(flexItem, true) + B(flexItem, true), iF);
                    i7 = size;
                    i8 = mode;
                    int iD = this.f955a.d(i18, I + iG + C(flexItem, true) + A(flexItem, true) + i23, E(flexItem, true));
                    viewE.measure(iG2, iD);
                    Y(i20, iG2, iD, viewE);
                    i9 = iG2;
                } else {
                    i7 = size;
                    i8 = mode;
                    int iG3 = this.f955a.g(i18, I + iG + C(flexItem, false) + A(flexItem, false) + i23, E(flexItem, false));
                    int iD2 = this.f955a.d(i17, D(flexItem, false) + i21 + B(flexItem, false), iF);
                    viewE.measure(iG3, iD2);
                    Y(i20, iG3, iD2, viewE);
                    i9 = iD2;
                }
                this.f955a.h(i20, viewE);
                i(viewE, i20);
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, viewE.getMeasuredState());
                int i26 = i23;
                int i27 = i21;
                b.b.a.a.b bVar4 = bVar3;
                int i28 = i20;
                list2 = arrayList;
                int i29 = i9;
                if (O(viewE, i8, i7, bVar3.e, B(flexItem, zI) + L(viewE, zI) + D(flexItem, zI), flexItem, i28, i24, arrayList.size())) {
                    if (bVar4.c() > 0) {
                        a(list2, bVar4, i28 > 0 ? i28 - 1 : 0, i26);
                        i23 = bVar4.g + i26;
                    } else {
                        i23 = i26;
                    }
                    if (!zI) {
                        i10 = i2;
                        view = viewE;
                        i20 = i28;
                        if (flexItem.getWidth() == -1) {
                            b.b.a.a.a aVar = this.f955a;
                            view.measure(aVar.g(i10, aVar.getPaddingLeft() + this.f955a.getPaddingRight() + flexItem.i() + flexItem.a() + i23, flexItem.getWidth()), i29);
                            i(view, i20);
                        }
                    } else if (flexItem.getHeight() == -1) {
                        b.b.a.a.a aVar2 = this.f955a;
                        i10 = i2;
                        i20 = i28;
                        view = viewE;
                        view.measure(i29, aVar2.d(i10, aVar2.getPaddingTop() + this.f955a.getPaddingBottom() + flexItem.m() + flexItem.g() + i23, flexItem.getHeight()));
                        i(view, i20);
                    } else {
                        i10 = i2;
                        view = viewE;
                        i20 = i28;
                    }
                    bVar3 = new b.b.a.a.b();
                    bVar3.h = 1;
                    i11 = i27;
                    bVar3.e = i11;
                    bVar3.o = i20;
                    i13 = Integer.MIN_VALUE;
                    i12 = 0;
                } else {
                    i10 = i2;
                    view = viewE;
                    i20 = i28;
                    bVar3 = bVar4;
                    i11 = i27;
                    bVar3.h++;
                    i12 = i24 + 1;
                    i23 = i26;
                    i13 = i22;
                }
                int[] iArr = this.f957c;
                if (iArr != null) {
                    iArr[i20] = list2.size();
                }
                bVar3.e += L(view, zI) + D(flexItem, zI) + B(flexItem, zI);
                bVar3.j += flexItem.e();
                bVar3.k += flexItem.k();
                this.f955a.f(view, i20, i12, bVar3);
                int iMax = Math.max(i13, K(view, zI) + C(flexItem, zI) + A(flexItem, zI) + this.f955a.j(view));
                bVar3.g = Math.max(bVar3.g, iMax);
                if (zI) {
                    if (this.f955a.getFlexWrap() != 2) {
                        bVar3.l = Math.max(bVar3.l, view.getBaseline() + flexItem.m());
                    } else {
                        bVar3.l = Math.max(bVar3.l, (view.getMeasuredHeight() - view.getBaseline()) + flexItem.g());
                    }
                }
                i14 = i25;
                if (M(i20, i14, bVar3)) {
                    a(list2, bVar3, i20, i23);
                    i23 += bVar3.g;
                }
                i15 = i5;
                if (i15 != -1 && list2.size() > 0) {
                    if (list2.get(list2.size() - 1).p >= i15 && i20 >= i15 && !z2) {
                        i23 = -bVar3.a();
                        i16 = i3;
                        z2 = true;
                    }
                    if (i23 <= i16 && z2) {
                        bVar2 = bVar;
                        i6 = iCombineMeasuredStates;
                        break;
                    }
                    i24 = i12;
                    i22 = iMax;
                    i20++;
                    i17 = i;
                    flexItemCount = i14;
                    i18 = i10;
                    i21 = i11;
                    arrayList = list2;
                    mode = i8;
                    i19 = i15;
                    size = i7;
                }
                i16 = i3;
                if (i23 <= i16) {
                }
                i24 = i12;
                i22 = iMax;
                i20++;
                i17 = i;
                flexItemCount = i14;
                i18 = i10;
                i21 = i11;
                arrayList = list2;
                mode = i8;
                i19 = i15;
                size = i7;
            }
            i7 = size;
            i8 = mode;
            i10 = i18;
            i15 = i19;
            list2 = arrayList;
            i11 = i21;
            i14 = flexItemCount;
            i20++;
            i17 = i;
            flexItemCount = i14;
            i18 = i10;
            i21 = i11;
            arrayList = list2;
            mode = i8;
            i19 = i15;
            size = i7;
        }
        bVar2.f959b = i6;
    }

    public void c(b bVar, int i, int i2) {
        b(bVar, i, i2, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 0, -1, null);
    }

    public void d(b bVar, int i, int i2, int i3, int i4, @Nullable List<b.b.a.a.b> list) {
        b(bVar, i, i2, i3, i4, -1, list);
    }

    public void e(b bVar, int i, int i2, int i3, int i4, List<b.b.a.a.b> list) {
        b(bVar, i, i2, i3, 0, i4, list);
    }

    public void f(b bVar, int i, int i2) {
        b(bVar, i2, i, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 0, -1, null);
    }

    public void g(b bVar, int i, int i2, int i3, int i4, @Nullable List<b.b.a.a.b> list) {
        b(bVar, i2, i, i3, i4, -1, list);
    }

    public void h(b bVar, int i, int i2, int i3, int i4, List<b.b.a.a.b> list) {
        b(bVar, i2, i, i3, 0, i4, list);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.FlexItem r0 = (com.google.android.flexbox.FlexItem) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.c()
            r4 = 1
            if (r1 >= r3) goto L1b
            int r1 = r0.c()
        L19:
            r3 = r4
            goto L27
        L1b:
            int r3 = r0.l()
            if (r1 <= r3) goto L26
            int r1 = r0.l()
            goto L19
        L26:
            r3 = 0
        L27:
            int r5 = r0.b()
            if (r2 >= r5) goto L32
            int r2 = r0.b()
            goto L3e
        L32:
            int r5 = r0.f()
            if (r2 <= r5) goto L3d
            int r2 = r0.f()
            goto L3e
        L3d:
            r4 = r3
        L3e:
            if (r4 == 0) goto L55
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
            r6.Y(r8, r1, r0, r7)
            b.b.a.a.a r0 = r6.f955a
            r0.h(r8, r7)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.a.c.i(android.view.View, int):void");
    }

    public void j(List<b.b.a.a.b> list, int i) {
        int i2 = this.f957c[i];
        if (i2 == -1) {
            i2 = 0;
        }
        for (int size = list.size() - 1; size >= i2; size--) {
            list.remove(size);
        }
        int[] iArr = this.f957c;
        int length = iArr.length - 1;
        if (i > length) {
            Arrays.fill(iArr, -1);
        } else {
            Arrays.fill(iArr, i, length, -1);
        }
        long[] jArr = this.d;
        int length2 = jArr.length - 1;
        if (i > length2) {
            Arrays.fill(jArr, 0L);
        } else {
            Arrays.fill(jArr, i, length2, 0L);
        }
    }

    public final List<b.b.a.a.b> k(List<b.b.a.a.b> list, int i, int i2) {
        int i3 = (i - i2) / 2;
        ArrayList arrayList = new ArrayList();
        b.b.a.a.b bVar = new b.b.a.a.b();
        bVar.g = i3;
        int size = list.size();
        for (int i4 = 0; i4 < size; i4++) {
            if (i4 == 0) {
                arrayList.add(bVar);
            }
            arrayList.add(list.get(i4));
            if (i4 == list.size() - 1) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    @NonNull
    public final List<C0008c> l(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            FlexItem flexItem = (FlexItem) this.f955a.a(i2).getLayoutParams();
            C0008c c0008c = new C0008c();
            c0008c.f961b = flexItem.getOrder();
            c0008c.f960a = i2;
            arrayList.add(c0008c);
        }
        return arrayList;
    }

    public int[] m(SparseIntArray sparseIntArray) {
        int flexItemCount = this.f955a.getFlexItemCount();
        return T(flexItemCount, l(flexItemCount), sparseIntArray);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int[] n(View view, int i, ViewGroup.LayoutParams layoutParams, SparseIntArray sparseIntArray) {
        int flexItemCount = this.f955a.getFlexItemCount();
        List<C0008c> listL = l(flexItemCount);
        C0008c c0008c = new C0008c();
        if (view == null || !(layoutParams instanceof FlexItem)) {
            c0008c.f961b = 1;
        } else {
            c0008c.f961b = ((FlexItem) layoutParams).getOrder();
        }
        if (i == -1 || i == flexItemCount || i >= this.f955a.getFlexItemCount()) {
            c0008c.f960a = flexItemCount;
        } else {
            c0008c.f960a = i;
            while (i < flexItemCount) {
                listL.get(i).f960a++;
                i++;
            }
        }
        listL.add(c0008c);
        return T(flexItemCount + 1, listL, sparseIntArray);
    }

    public void o(int i, int i2, int i3) {
        int mode;
        int size;
        int flexDirection = this.f955a.getFlexDirection();
        if (flexDirection == 0 || flexDirection == 1) {
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            mode = mode2;
            size = size2;
        } else {
            if (flexDirection != 2 && flexDirection != 3) {
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
            }
            mode = View.MeasureSpec.getMode(i);
            size = View.MeasureSpec.getSize(i);
        }
        List<b.b.a.a.b> flexLinesInternal = this.f955a.getFlexLinesInternal();
        if (mode == 1073741824) {
            int sumOfCrossSize = this.f955a.getSumOfCrossSize() + i3;
            int i4 = 0;
            if (flexLinesInternal.size() == 1) {
                flexLinesInternal.get(0).g = size - i3;
                return;
            }
            if (flexLinesInternal.size() >= 2) {
                int alignContent = this.f955a.getAlignContent();
                if (alignContent == 1) {
                    int i5 = size - sumOfCrossSize;
                    b.b.a.a.b bVar = new b.b.a.a.b();
                    bVar.g = i5;
                    flexLinesInternal.add(0, bVar);
                    return;
                }
                if (alignContent == 2) {
                    this.f955a.setFlexLines(k(flexLinesInternal, size, sumOfCrossSize));
                    return;
                }
                if (alignContent == 3) {
                    if (sumOfCrossSize >= size) {
                        return;
                    }
                    float size3 = (size - sumOfCrossSize) / (flexLinesInternal.size() - 1);
                    ArrayList arrayList = new ArrayList();
                    int size4 = flexLinesInternal.size();
                    float f = 0.0f;
                    while (i4 < size4) {
                        arrayList.add(flexLinesInternal.get(i4));
                        if (i4 != flexLinesInternal.size() - 1) {
                            b.b.a.a.b bVar2 = new b.b.a.a.b();
                            if (i4 == flexLinesInternal.size() - 2) {
                                bVar2.g = Math.round(f + size3);
                                f = 0.0f;
                            } else {
                                bVar2.g = Math.round(size3);
                            }
                            int i6 = bVar2.g;
                            f += size3 - i6;
                            if (f > 1.0f) {
                                bVar2.g = i6 + 1;
                                f -= 1.0f;
                            } else if (f < -1.0f) {
                                bVar2.g = i6 - 1;
                                f += 1.0f;
                            }
                            arrayList.add(bVar2);
                        }
                        i4++;
                    }
                    this.f955a.setFlexLines(arrayList);
                    return;
                }
                if (alignContent == 4) {
                    if (sumOfCrossSize >= size) {
                        this.f955a.setFlexLines(k(flexLinesInternal, size, sumOfCrossSize));
                        return;
                    }
                    int size5 = (size - sumOfCrossSize) / (flexLinesInternal.size() * 2);
                    ArrayList arrayList2 = new ArrayList();
                    b.b.a.a.b bVar3 = new b.b.a.a.b();
                    bVar3.g = size5;
                    for (b.b.a.a.b bVar4 : flexLinesInternal) {
                        arrayList2.add(bVar3);
                        arrayList2.add(bVar4);
                        arrayList2.add(bVar3);
                    }
                    this.f955a.setFlexLines(arrayList2);
                    return;
                }
                if (alignContent == 5 && sumOfCrossSize < size) {
                    float size6 = (size - sumOfCrossSize) / flexLinesInternal.size();
                    int size7 = flexLinesInternal.size();
                    float f2 = 0.0f;
                    while (i4 < size7) {
                        b.b.a.a.b bVar5 = flexLinesInternal.get(i4);
                        float f3 = bVar5.g + size6;
                        if (i4 == flexLinesInternal.size() - 1) {
                            f3 += f2;
                            f2 = 0.0f;
                        }
                        int iRound = Math.round(f3);
                        f2 += f3 - iRound;
                        if (f2 > 1.0f) {
                            iRound++;
                            f2 -= 1.0f;
                        } else if (f2 < -1.0f) {
                            iRound--;
                            f2 += 1.0f;
                        }
                        bVar5.g = iRound;
                        i4++;
                    }
                }
            }
        }
    }

    public void p(int i, int i2) {
        q(i, i2, 0);
    }

    public void q(int i, int i2, int i3) {
        int size;
        int paddingLeft;
        int paddingRight;
        r(this.f955a.getFlexItemCount());
        if (i3 >= this.f955a.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.f955a.getFlexDirection();
        int flexDirection2 = this.f955a.getFlexDirection();
        if (flexDirection2 == 0 || flexDirection2 == 1) {
            int mode = View.MeasureSpec.getMode(i);
            size = View.MeasureSpec.getSize(i);
            if (mode != 1073741824) {
                size = this.f955a.getLargestMainSize();
            }
            paddingLeft = this.f955a.getPaddingLeft();
            paddingRight = this.f955a.getPaddingRight();
        } else {
            if (flexDirection2 != 2 && flexDirection2 != 3) {
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
            }
            int mode2 = View.MeasureSpec.getMode(i2);
            size = View.MeasureSpec.getSize(i2);
            if (mode2 != 1073741824) {
                size = this.f955a.getLargestMainSize();
            }
            paddingLeft = this.f955a.getPaddingTop();
            paddingRight = this.f955a.getPaddingBottom();
        }
        int i4 = paddingLeft + paddingRight;
        int[] iArr = this.f957c;
        int i5 = iArr != null ? iArr[i3] : 0;
        List<b.b.a.a.b> flexLinesInternal = this.f955a.getFlexLinesInternal();
        int size2 = flexLinesInternal.size();
        for (int i6 = i5; i6 < size2; i6++) {
            b.b.a.a.b bVar = flexLinesInternal.get(i6);
            if (bVar.e < size) {
                v(i, i2, bVar, size, i4, false);
            } else {
                S(i, i2, bVar, size, i4, false);
            }
        }
    }

    public final void r(int i) {
        boolean[] zArr = this.f956b;
        if (zArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.f956b = new boolean[i];
        } else {
            if (zArr.length >= i) {
                Arrays.fill(zArr, false);
                return;
            }
            int length = zArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.f956b = new boolean[i];
        }
    }

    public void s(int i) {
        int[] iArr = this.f957c;
        if (iArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.f957c = new int[i];
        } else if (iArr.length < i) {
            int length = iArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.f957c = Arrays.copyOf(iArr, i);
        }
    }

    public void t(int i) {
        long[] jArr = this.d;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.d = new long[i];
        } else if (jArr.length < i) {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.d = Arrays.copyOf(jArr, i);
        }
    }

    public void u(int i) {
        long[] jArr = this.e;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.e = new long[i];
        } else if (jArr.length < i) {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.e = Arrays.copyOf(jArr, i);
        }
    }

    public final void v(int i, int i2, b.b.a.a.b bVar, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int iMax;
        double d;
        int i7;
        double d2;
        float f = bVar.j;
        float f2 = 0.0f;
        if (f <= 0.0f || i3 < (i5 = bVar.e)) {
            return;
        }
        float f3 = (i3 - i5) / f;
        bVar.e = i4 + bVar.f;
        if (!z) {
            bVar.g = Integer.MIN_VALUE;
        }
        int i8 = 0;
        boolean z2 = false;
        int i9 = 0;
        float f4 = 0.0f;
        while (i8 < bVar.h) {
            int i10 = bVar.o + i8;
            View viewE = this.f955a.e(i10);
            if (viewE == null || viewE.getVisibility() == 8) {
                i6 = i5;
            } else {
                FlexItem flexItem = (FlexItem) viewE.getLayoutParams();
                int flexDirection = this.f955a.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    int i11 = i5;
                    int measuredWidth = viewE.getMeasuredWidth();
                    long[] jArr = this.e;
                    if (jArr != null) {
                        measuredWidth = x(jArr[i10]);
                    }
                    int measuredHeight = viewE.getMeasuredHeight();
                    long[] jArr2 = this.e;
                    i6 = i11;
                    if (jArr2 != null) {
                        measuredHeight = w(jArr2[i10]);
                    }
                    if (!this.f956b[i10] && flexItem.e() > 0.0f) {
                        float fE = measuredWidth + (flexItem.e() * f3);
                        if (i8 == bVar.h - 1) {
                            fE += f4;
                            f4 = 0.0f;
                        }
                        int iRound = Math.round(fE);
                        if (iRound > flexItem.l()) {
                            iRound = flexItem.l();
                            this.f956b[i10] = true;
                            bVar.j -= flexItem.e();
                            z2 = true;
                        } else {
                            f4 += fE - iRound;
                            double d3 = f4;
                            if (d3 > 1.0d) {
                                iRound++;
                                d = d3 - 1.0d;
                            } else if (d3 < -1.0d) {
                                iRound--;
                                d = d3 + 1.0d;
                            }
                            f4 = (float) d;
                        }
                        int iY = y(i2, flexItem, bVar.m);
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iRound, 1073741824);
                        viewE.measure(iMakeMeasureSpec, iY);
                        int measuredWidth2 = viewE.getMeasuredWidth();
                        int measuredHeight2 = viewE.getMeasuredHeight();
                        Y(i10, iMakeMeasureSpec, iY, viewE);
                        this.f955a.h(i10, viewE);
                        measuredWidth = measuredWidth2;
                        measuredHeight = measuredHeight2;
                    }
                    int iMax2 = Math.max(i9, measuredHeight + flexItem.m() + flexItem.g() + this.f955a.j(viewE));
                    bVar.e += measuredWidth + flexItem.i() + flexItem.a();
                    iMax = iMax2;
                } else {
                    int measuredHeight3 = viewE.getMeasuredHeight();
                    long[] jArr3 = this.e;
                    if (jArr3 != null) {
                        measuredHeight3 = w(jArr3[i10]);
                    }
                    int measuredWidth3 = viewE.getMeasuredWidth();
                    long[] jArr4 = this.e;
                    if (jArr4 != null) {
                        measuredWidth3 = x(jArr4[i10]);
                    }
                    if (this.f956b[i10] || flexItem.e() <= f2) {
                        i7 = i5;
                    } else {
                        float fE2 = measuredHeight3 + (flexItem.e() * f3);
                        if (i8 == bVar.h - 1) {
                            fE2 += f4;
                            f4 = f2;
                        }
                        int iRound2 = Math.round(fE2);
                        if (iRound2 > flexItem.f()) {
                            iRound2 = flexItem.f();
                            this.f956b[i10] = true;
                            bVar.j -= flexItem.e();
                            i7 = i5;
                            z2 = true;
                        } else {
                            f4 += fE2 - iRound2;
                            i7 = i5;
                            double d4 = f4;
                            if (d4 > 1.0d) {
                                iRound2++;
                                d2 = d4 - 1.0d;
                            } else if (d4 < -1.0d) {
                                iRound2--;
                                d2 = d4 + 1.0d;
                            }
                            f4 = (float) d2;
                        }
                        int iZ = z(i, flexItem, bVar.m);
                        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iRound2, 1073741824);
                        viewE.measure(iZ, iMakeMeasureSpec2);
                        measuredWidth3 = viewE.getMeasuredWidth();
                        int measuredHeight4 = viewE.getMeasuredHeight();
                        Y(i10, iZ, iMakeMeasureSpec2, viewE);
                        this.f955a.h(i10, viewE);
                        measuredHeight3 = measuredHeight4;
                    }
                    iMax = Math.max(i9, measuredWidth3 + flexItem.i() + flexItem.a() + this.f955a.j(viewE));
                    bVar.e += measuredHeight3 + flexItem.m() + flexItem.g();
                    i6 = i7;
                }
                bVar.g = Math.max(bVar.g, iMax);
                i9 = iMax;
            }
            i8++;
            i5 = i6;
            f2 = 0.0f;
        }
        int i12 = i5;
        if (!z2 || i12 == bVar.e) {
            return;
        }
        v(i, i2, bVar, i3, i4, true);
    }

    public int w(long j) {
        return (int) (j >> 32);
    }

    public int x(long j) {
        return (int) j;
    }

    public final int y(int i, FlexItem flexItem, int i2) {
        b.b.a.a.a aVar = this.f955a;
        int iD = aVar.d(i, aVar.getPaddingTop() + this.f955a.getPaddingBottom() + flexItem.m() + flexItem.g() + i2, flexItem.getHeight());
        int size = View.MeasureSpec.getSize(iD);
        return size > flexItem.f() ? View.MeasureSpec.makeMeasureSpec(flexItem.f(), View.MeasureSpec.getMode(iD)) : size < flexItem.b() ? View.MeasureSpec.makeMeasureSpec(flexItem.b(), View.MeasureSpec.getMode(iD)) : iD;
    }

    public final int z(int i, FlexItem flexItem, int i2) {
        b.b.a.a.a aVar = this.f955a;
        int iG = aVar.g(i, aVar.getPaddingLeft() + this.f955a.getPaddingRight() + flexItem.i() + flexItem.a() + i2, flexItem.getWidth());
        int size = View.MeasureSpec.getSize(iG);
        return size > flexItem.l() ? View.MeasureSpec.makeMeasureSpec(flexItem.l(), View.MeasureSpec.getMode(iG)) : size < flexItem.c() ? View.MeasureSpec.makeMeasureSpec(flexItem.c(), View.MeasureSpec.getMode(iG)) : iG;
    }
}
