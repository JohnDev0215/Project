package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.AbstractC0371Um;
import defpackage.AbstractC1251o3;
import defpackage.AbstractC1358q0;
import defpackage.C1303p0;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AlertDialogLayout extends AbstractC1358q0 {
    public AlertDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    private void d(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                C1303p0 c1303p0 = (C1303p0) childAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) c1303p0).width == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) c1303p0).height;
                    ((ViewGroup.MarginLayoutParams) c1303p0).height = childAt.getMeasuredHeight();
                    measureChildWithMargins(childAt, iMakeMeasureSpec, 0, i2, 0);
                    ((ViewGroup.MarginLayoutParams) c1303p0).height = i4;
                }
            }
        }
    }

    public static int n(View view) {
        int iL = AbstractC1251o3.l(view);
        if (iL > 0) {
            return iL;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                return n(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0099  */
    @Override // defpackage.AbstractC1358q0, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r10, int r11, int r12, int r13, int r14) {
        /*
            r9 = this;
            int r10 = r9.getPaddingLeft()
            int r13 = r13 - r11
            int r11 = r9.getPaddingRight()
            int r11 = r13 - r11
            int r13 = r13 - r10
            int r0 = r9.getPaddingRight()
            int r13 = r13 - r0
            int r0 = r9.getMeasuredHeight()
            int r1 = r9.getChildCount()
            int r2 = r9.w
            r3 = r2 & 112(0x70, float:1.57E-43)
            r4 = 8388615(0x800007, float:1.1754953E-38)
            r2 = r2 & r4
            r4 = 16
            if (r3 == r4) goto L37
            r4 = 80
            if (r3 == r4) goto L2e
            int r12 = r9.getPaddingTop()
            goto L41
        L2e:
            int r3 = r9.getPaddingTop()
            int r3 = r3 + r14
            int r3 = r3 - r12
            int r12 = r3 - r0
            goto L41
        L37:
            int r3 = r9.getPaddingTop()
            int r14 = r14 - r12
            int r14 = r14 - r0
            int r14 = r14 / 2
            int r12 = r14 + r3
        L41:
            android.graphics.drawable.Drawable r14 = r9.C
            r0 = 0
            if (r14 != 0) goto L48
            r14 = 0
            goto L4c
        L48:
            int r14 = r14.getIntrinsicHeight()
        L4c:
            if (r0 >= r1) goto Lab
            android.view.View r3 = r9.getChildAt(r0)
            if (r3 == 0) goto La8
            int r4 = r3.getVisibility()
            r5 = 8
            if (r4 == r5) goto La8
            int r4 = r3.getMeasuredWidth()
            int r5 = r3.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r6 = r3.getLayoutParams()
            p0 r6 = (defpackage.C1303p0) r6
            int r7 = r6.b
            if (r7 >= 0) goto L6f
            r7 = r2
        L6f:
            int r8 = defpackage.AbstractC1251o3.k(r9)
            int r7 = android.view.Gravity.getAbsoluteGravity(r7, r8)
            r7 = r7 & 7
            r8 = 1
            if (r7 == r8) goto L88
            r8 = 5
            if (r7 == r8) goto L83
            int r7 = r6.leftMargin
            int r7 = r7 + r10
            goto L93
        L83:
            int r7 = r11 - r4
            int r8 = r6.rightMargin
            goto L92
        L88:
            int r7 = r13 - r4
            int r7 = r7 / 2
            int r7 = r7 + r10
            int r8 = r6.leftMargin
            int r7 = r7 + r8
            int r8 = r6.rightMargin
        L92:
            int r7 = r7 - r8
        L93:
            boolean r8 = r9.k(r0)
            if (r8 == 0) goto L9a
            int r12 = r12 + r14
        L9a:
            int r8 = r6.topMargin
            int r12 = r12 + r8
            int r4 = r4 + r7
            int r8 = r5 + r12
            r3.layout(r7, r12, r4, r8)
            int r3 = r6.bottomMargin
            int r5 = r5 + r3
            int r5 = r5 + r12
            r12 = r5
        La8:
            int r0 = r0 + 1
            goto L4c
        Lab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AlertDialogLayout.onLayout(boolean, int, int, int, int):void");
    }

    @Override // defpackage.AbstractC1358q0, android.view.View
    public void onMeasure(int i, int i2) {
        int iCombineMeasuredStates;
        int iN;
        int measuredHeight;
        int measuredHeight2;
        int childCount = getChildCount();
        View view = null;
        boolean z = false;
        View view2 = null;
        View view3 = null;
        int i3 = 0;
        while (true) {
            if (i3 < childCount) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() != 8) {
                    int id = childAt.getId();
                    if (id == AbstractC0371Um.Z4) {
                        view = childAt;
                    } else if (id == AbstractC0371Um.W) {
                        view2 = childAt;
                    } else if ((id != AbstractC0371Um.p0 && id != AbstractC0371Um.B0) || view3 != null) {
                        break;
                    } else {
                        view3 = childAt;
                    }
                }
                i3++;
            } else {
                int mode = View.MeasureSpec.getMode(i2);
                int size = View.MeasureSpec.getSize(i2);
                int mode2 = View.MeasureSpec.getMode(i);
                int paddingBottom = getPaddingBottom() + getPaddingTop();
                if (view != null) {
                    view.measure(i, 0);
                    paddingBottom += view.getMeasuredHeight();
                    iCombineMeasuredStates = View.combineMeasuredStates(0, view.getMeasuredState());
                } else {
                    iCombineMeasuredStates = 0;
                }
                if (view2 != null) {
                    view2.measure(i, 0);
                    iN = n(view2);
                    measuredHeight = view2.getMeasuredHeight() - iN;
                    paddingBottom += iN;
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view2.getMeasuredState());
                } else {
                    iN = 0;
                    measuredHeight = 0;
                }
                if (view3 != null) {
                    view3.measure(i, mode == 0 ? 0 : View.MeasureSpec.makeMeasureSpec(Math.max(0, size - paddingBottom), mode));
                    measuredHeight2 = view3.getMeasuredHeight();
                    paddingBottom += measuredHeight2;
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view3.getMeasuredState());
                } else {
                    measuredHeight2 = 0;
                }
                int i4 = size - paddingBottom;
                if (view2 != null) {
                    int i5 = paddingBottom - iN;
                    int iMin = Math.min(i4, measuredHeight);
                    if (iMin > 0) {
                        i4 -= iMin;
                        iN += iMin;
                    }
                    view2.measure(i, View.MeasureSpec.makeMeasureSpec(iN, 1073741824));
                    paddingBottom = i5 + view2.getMeasuredHeight();
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view2.getMeasuredState());
                }
                if (view3 != null && i4 > 0) {
                    view3.measure(i, View.MeasureSpec.makeMeasureSpec(measuredHeight2 + i4, mode));
                    paddingBottom = (paddingBottom - measuredHeight2) + view3.getMeasuredHeight();
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view3.getMeasuredState());
                }
                int iMax = 0;
                for (int i6 = 0; i6 < childCount; i6++) {
                    View childAt2 = getChildAt(i6);
                    if (childAt2.getVisibility() != 8) {
                        iMax = Math.max(iMax, childAt2.getMeasuredWidth());
                    }
                }
                setMeasuredDimension(View.resolveSizeAndState(getPaddingRight() + getPaddingLeft() + iMax, i, iCombineMeasuredStates), View.resolveSizeAndState(paddingBottom, i2, 0));
                if (mode2 != 1073741824) {
                    d(childCount, i2);
                }
                z = true;
            }
        }
        if (z) {
            return;
        }
        super.onMeasure(i, i2);
    }
}
