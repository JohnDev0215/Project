package androidx.viewpager2.widget;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: AnimateLayoutChangeDetector.java */
/* JADX INFO: loaded from: classes.dex */
final class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final ViewGroup.MarginLayoutParams f3337b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private LinearLayoutManager f3338a;

    /* JADX INFO: renamed from: androidx.viewpager2.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AnimateLayoutChangeDetector.java */
    class C0030a implements Comparator<int[]> {
        C0030a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(int[] iArr, int[] iArr2) {
            return iArr[0] - iArr2[0];
        }
    }

    static {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -1);
        f3337b = marginLayoutParams;
        marginLayoutParams.setMargins(0, 0, 0, 0);
    }

    a(LinearLayoutManager linearLayoutManager) {
        this.f3338a = linearLayoutManager;
    }

    private boolean a() {
        int top;
        int i7;
        int bottom;
        int i8;
        int iJ = this.f3338a.J();
        if (iJ == 0) {
            return true;
        }
        boolean z6 = this.f3338a.p2() == 0;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, iJ, 2);
        for (int i9 = 0; i9 < iJ; i9++) {
            View viewI = this.f3338a.I(i9);
            if (viewI == null) {
                throw new IllegalStateException("null view contained in the view hierarchy");
            }
            ViewGroup.LayoutParams layoutParams = viewI.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : f3337b;
            int[] iArr2 = iArr[i9];
            if (z6) {
                top = viewI.getLeft();
                i7 = marginLayoutParams.leftMargin;
            } else {
                top = viewI.getTop();
                i7 = marginLayoutParams.topMargin;
            }
            iArr2[0] = top - i7;
            int[] iArr3 = iArr[i9];
            if (z6) {
                bottom = viewI.getRight();
                i8 = marginLayoutParams.rightMargin;
            } else {
                bottom = viewI.getBottom();
                i8 = marginLayoutParams.bottomMargin;
            }
            iArr3[1] = bottom + i8;
        }
        Arrays.sort(iArr, new C0030a());
        for (int i10 = 1; i10 < iJ; i10++) {
            if (iArr[i10 - 1][1] != iArr[i10][0]) {
                return false;
            }
        }
        return iArr[0][0] <= 0 && iArr[iJ - 1][1] >= iArr[0][1] - iArr[0][0];
    }

    private boolean b() {
        int iJ = this.f3338a.J();
        for (int i7 = 0; i7 < iJ; i7++) {
            if (c(this.f3338a.I(i7))) {
                return true;
            }
        }
        return false;
    }

    private static boolean c(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
            if (layoutTransition != null && layoutTransition.isChangingLayout()) {
                return true;
            }
            int childCount = viewGroup.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                if (c(viewGroup.getChildAt(i7))) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean d() {
        return (!a() || this.f3338a.J() <= 1) && b();
    }
}
