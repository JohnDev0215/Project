package o0;

import android.view.View;

/* JADX INFO: compiled from: NestedScrollingParent.java */
/* JADX INFO: loaded from: classes.dex */
public interface n {
    boolean onNestedFling(View view, float f8, float f9, boolean z7);

    boolean onNestedPreFling(View view, float f8, float f9);

    void onNestedPreScroll(View view, int i8, int i9, int[] iArr);

    void onNestedScroll(View view, int i8, int i9, int i10, int i11);

    void onNestedScrollAccepted(View view, View view2, int i8);

    boolean onStartNestedScroll(View view, View view2, int i8);

    void onStopNestedScroll(View view);
}
