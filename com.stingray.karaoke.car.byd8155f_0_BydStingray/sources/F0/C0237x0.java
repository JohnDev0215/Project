package F0;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import com.stingray.drivemodedetector.R;
import n0.AbstractC1874c;
import n0.InterfaceC1889r;

/* JADX INFO: renamed from: F0.x0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0237x0 extends ViewGroup {
    public boolean l;

    public C0237x0(Context context) {
        super(context);
        setClipChildren(false);
        setTag(R.id.hide_in_inspector_tag, Boolean.TRUE);
    }

    public final void a(InterfaceC1889r interfaceC1889r, Z0 z02, long j3) {
        super.drawChild(AbstractC1874c.a(interfaceC1889r), z02, j3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        int childCount = super.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            A7.m.d(childAt, "null cannot be cast to non-null type androidx.compose.ui.platform.ViewLayer");
            if (((Z0) childAt).f2160s) {
                this.l = true;
                try {
                    super.dispatchDraw(canvas);
                    return;
                } finally {
                    this.l = false;
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    public int getChildCount() {
        if (this.l) {
            return super.getChildCount();
        }
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
    }
}
