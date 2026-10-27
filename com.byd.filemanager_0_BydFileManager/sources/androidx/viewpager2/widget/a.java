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
    private static final ViewGroup.MarginLayoutParams f1564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LinearLayoutManager f1565b;

    /* JADX INFO: renamed from: androidx.viewpager2.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AnimateLayoutChangeDetector.java */
    class C0023a implements Comparator<int[]> {
        C0023a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(int[] iArr, int[] iArr2) {
            return iArr[0] - iArr2[0];
        }
    }

    static {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -1);
        f1564a = marginLayoutParams;
        marginLayoutParams.setMargins(0, 0, 0, 0);
    }

    a(LinearLayoutManager linearLayoutManager) {
        this.f1565b = linearLayoutManager;
    }

    private boolean a() {
        int top;
        int i;
        int bottom;
        int i2;
        int iT = this.f1565b.T();
        if (iT == 0) {
            return true;
        }
        boolean z = this.f1565b.B2() == 0;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, iT, 2);
        for (int i3 = 0; i3 < iT; i3++) {
            View viewS = this.f1565b.S(i3);
            if (viewS == null) {
                throw new IllegalStateException("null view contained in the view hierarchy");
            }
            ViewGroup.LayoutParams layoutParams = viewS.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : f1564a;
            int[] iArr2 = iArr[i3];
            if (z) {
                top = viewS.getLeft();
                i = marginLayoutParams.leftMargin;
            } else {
                top = viewS.getTop();
                i = marginLayoutParams.topMargin;
            }
            iArr2[0] = top - i;
            int[] iArr3 = iArr[i3];
            if (z) {
                bottom = viewS.getRight();
                i2 = marginLayoutParams.rightMargin;
            } else {
                bottom = viewS.getBottom();
                i2 = marginLayoutParams.bottomMargin;
            }
            iArr3[1] = bottom + i2;
        }
        Arrays.sort(iArr, new C0023a());
        for (int i4 = 1; i4 < iT; i4++) {
            if (iArr[i4 - 1][1] != iArr[i4][0]) {
                return false;
            }
        }
        return iArr[0][0] <= 0 && iArr[iT - 1][1] >= iArr[0][1] - iArr[0][0];
    }

    private boolean b() {
        int iT = this.f1565b.T();
        for (int i = 0; i < iT; i++) {
            if (c(this.f1565b.S(i))) {
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
        return (!a() || this.f1565b.T() <= 1) && b();
    }
}
