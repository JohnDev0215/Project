package b.u.b;

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
    public static final ViewGroup.MarginLayoutParams f1572a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public LinearLayoutManager f1573b;

    /* JADX INFO: renamed from: b.u.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AnimateLayoutChangeDetector.java */
    public class C0043a implements Comparator<int[]> {
        public C0043a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(int[] iArr, int[] iArr2) {
            return iArr[0] - iArr2[0];
        }
    }

    static {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -1);
        f1572a = marginLayoutParams;
        marginLayoutParams.setMargins(0, 0, 0, 0);
    }

    public a(LinearLayoutManager linearLayoutManager) {
        this.f1573b = linearLayoutManager;
    }

    public static boolean c(View view) {
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

    public final boolean a() {
        int top;
        int i;
        int bottom;
        int i2;
        int iJ = this.f1573b.J();
        if (iJ == 0) {
            return true;
        }
        boolean z = this.f1573b.p2() == 0;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, iJ, 2);
        for (int i3 = 0; i3 < iJ; i3++) {
            View viewI = this.f1573b.I(i3);
            if (viewI == null) {
                throw new IllegalStateException("null view contained in the view hierarchy");
            }
            ViewGroup.LayoutParams layoutParams = viewI.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : f1572a;
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
        Arrays.sort(iArr, new C0043a());
        for (int i4 = 1; i4 < iJ; i4++) {
            if (iArr[i4 - 1][1] != iArr[i4][0]) {
                return false;
            }
        }
        return iArr[0][0] <= 0 && iArr[iJ - 1][1] >= iArr[0][1] - iArr[0][0];
    }

    public final boolean b() {
        int iJ = this.f1573b.J();
        for (int i = 0; i < iJ; i++) {
            if (c(this.f1573b.I(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean d() {
        return (!a() || this.f1573b.J() <= 1) && b();
    }
}
