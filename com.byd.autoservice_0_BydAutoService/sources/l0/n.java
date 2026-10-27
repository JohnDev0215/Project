package l0;

import android.view.View;

/* JADX INFO: compiled from: NestedScrollingParent.java */
/* JADX INFO: loaded from: classes.dex */
public interface n {
    boolean onNestedFling(View view, float f3, float f4, boolean z3);

    boolean onNestedPreFling(View view, float f3, float f4);

    void onNestedPreScroll(View view, int i3, int i4, int[] iArr);

    void onNestedScroll(View view, int i3, int i4, int i5, int i6);

    void onNestedScrollAccepted(View view, View view2, int i3);

    boolean onStartNestedScroll(View view, View view2, int i3);

    void onStopNestedScroll(View view);
}
