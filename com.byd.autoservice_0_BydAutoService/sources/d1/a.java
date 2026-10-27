package d1;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: AnimateLayoutChangeDetector.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ViewGroup.MarginLayoutParams f2677a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public LinearLayoutManager f2678b;

    /* JADX INFO: renamed from: d1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AnimateLayoutChangeDetector.java */
    public class C0009a implements Comparator<int[]> {
        public C0009a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(int[] iArr, int[] iArr2) {
            return iArr[0] - iArr2[0];
        }
    }

    static {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -1);
        f2677a = marginLayoutParams;
        marginLayoutParams.setMargins(0, 0, 0, 0);
    }

    public a(LinearLayoutManager linearLayoutManager) {
        this.f2678b = linearLayoutManager;
    }

    public static boolean c(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
            if (layoutTransition != null && layoutTransition.isChangingLayout()) {
                return true;
            }
            int childCount = viewGroup.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                if (c(viewGroup.getChildAt(i3))) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean a() {
        int top;
        int i3;
        int bottom;
        int i4;
        int iJ = this.f2678b.J();
        if (iJ == 0) {
            return true;
        }
        boolean z3 = this.f2678b.p2() == 0;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, iJ, 2);
        for (int i5 = 0; i5 < iJ; i5++) {
            View viewI = this.f2678b.I(i5);
            if (viewI == null) {
                throw new IllegalStateException("null view contained in the view hierarchy");
            }
            ViewGroup.LayoutParams layoutParams = viewI.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : f2677a;
            int[] iArr2 = iArr[i5];
            if (z3) {
                top = viewI.getLeft();
                i3 = marginLayoutParams.leftMargin;
            } else {
                top = viewI.getTop();
                i3 = marginLayoutParams.topMargin;
            }
            iArr2[0] = top - i3;
            int[] iArr3 = iArr[i5];
            if (z3) {
                bottom = viewI.getRight();
                i4 = marginLayoutParams.rightMargin;
            } else {
                bottom = viewI.getBottom();
                i4 = marginLayoutParams.bottomMargin;
            }
            iArr3[1] = bottom + i4;
        }
        Arrays.sort(iArr, new C0009a());
        for (int i6 = 1; i6 < iJ; i6++) {
            if (iArr[i6 - 1][1] != iArr[i6][0]) {
                return false;
            }
        }
        return iArr[0][0] <= 0 && iArr[iJ - 1][1] >= iArr[0][1] - iArr[0][0];
    }

    public final boolean b() {
        int iJ = this.f2678b.J();
        for (int i3 = 0; i3 < iJ; i3++) {
            if (c(this.f2678b.I(i3))) {
                return true;
            }
        }
        return false;
    }

    public boolean d() {
        return (!a() || this.f2678b.J() <= 1) && b();
    }
}
