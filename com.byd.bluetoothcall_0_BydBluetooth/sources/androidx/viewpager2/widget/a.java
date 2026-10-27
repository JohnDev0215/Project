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

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ViewGroup.MarginLayoutParams f1946a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LinearLayoutManager f1947b;

    /* JADX INFO: renamed from: androidx.viewpager2.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AnimateLayoutChangeDetector.java */
    class C0036a implements Comparator<int[]> {
        C0036a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(int[] iArr, int[] iArr2) {
            return iArr[0] - iArr2[0];
        }
    }

    static {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -1);
        f1946a = marginLayoutParams;
        marginLayoutParams.setMargins(0, 0, 0, 0);
    }

    a(LinearLayoutManager linearLayoutManager) {
        this.f1947b = linearLayoutManager;
    }

    private boolean a() {
        int top;
        int i;
        int bottom;
        int i2;
        int iJ = this.f1947b.J();
        if (iJ == 0) {
            return true;
        }
        boolean z = this.f1947b.r2() == 0;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, iJ, 2);
        for (int i3 = 0; i3 < iJ; i3++) {
            View viewI = this.f1947b.I(i3);
            if (viewI == null) {
                throw new IllegalStateException("null view contained in the view hierarchy");
            }
            ViewGroup.LayoutParams layoutParams = viewI.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : f1946a;
            int[] iArr2 = iArr[i3];
            if (z) {
                top = viewI.getLeft();
                i = marginLayoutParams.leftMargin;
            } else {
                top = viewI.getTop();
                i = marginLayoutParams.topMargin;
            }
            iArr2[0] = top - i;
            int[] iArr3 = iArr[i3];
            if (z) {
                bottom = viewI.getRight();
                i2 = marginLayoutParams.rightMargin;
            } else {
                bottom = viewI.getBottom();
                i2 = marginLayoutParams.bottomMargin;
            }
            iArr3[1] = bottom + i2;
        }
        Arrays.sort(iArr, new C0036a());
        for (int i4 = 1; i4 < iJ; i4++) {
            if (iArr[i4 - 1][1] != iArr[i4][0]) {
                return false;
            }
        }
        return iArr[0][0] <= 0 && iArr[iJ - 1][1] >= iArr[0][1] - iArr[0][0];
    }

    private boolean b() {
        int iJ = this.f1947b.J();
        for (int i = 0; i < iJ; i++) {
            if (c(this.f1947b.I(i))) {
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
            for (int i = 0; i < childCount; i++) {
                if (c(viewGroup.getChildAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean d() {
        return (!a() || this.f1947b.J() <= 1) && b();
    }
}
