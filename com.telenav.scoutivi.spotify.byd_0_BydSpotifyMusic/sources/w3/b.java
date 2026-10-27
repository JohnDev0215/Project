package w3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView;
import h3.i;

/* JADX INFO: compiled from: VividRecyclerView.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends BaseRecyclerView {
    private final String X0;
    private int Y0;
    private int Z0;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    private int f9729a1;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    private int f9730b1;

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.X0 = "VividRecyclerView";
        G1();
    }

    private void G1() {
        this.f9730b1 = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean zK = getLayoutManager().k();
        boolean zL = getLayoutManager().l();
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            this.Y0 = motionEvent.getPointerId(0);
            this.Z0 = (int) (motionEvent.getX() + 0.5f);
            this.f9729a1 = (int) (motionEvent.getY() + 0.5f);
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (actionMasked != 2) {
            if (actionMasked != 5) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            this.Y0 = motionEvent.getPointerId(actionIndex);
            this.Z0 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.f9729a1 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            return super.onInterceptTouchEvent(motionEvent);
        }
        int iFindPointerIndex = motionEvent.findPointerIndex(this.Y0);
        if (iFindPointerIndex < 0) {
            i.b("VividRecyclerView").d("Error processing scroll; pointer index for id " + this.Y0 + " not found. Did any MotionEvents get skipped?", new Object[0]);
            return false;
        }
        int x6 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
        int y6 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
        if (getScrollState() == 1) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        int i7 = x6 - this.Z0;
        int i8 = y6 - this.f9729a1;
        boolean z6 = zK && Math.abs(i7) > this.f9730b1 && Math.abs(i7) > Math.abs(i8);
        if (zL && Math.abs(i8) > this.f9730b1 && Math.abs(i8) > Math.abs(i7)) {
            z6 = true;
        }
        i.b("VividRecyclerView").g("canX:" + zK + "--canY" + zL + "--dx:" + i7 + "--dy:" + i8 + "--startScorll:" + z6 + "--mTouchSlop" + this.f9730b1);
        return z6 && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setScrollingTouchSlop(int i7) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i7 != 0) {
            if (i7 != 1) {
                i.b("VividRecyclerView").i("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i7 + "; using default value");
            }
            super.setScrollingTouchSlop(i7);
        }
        this.f9730b1 = viewConfiguration.getScaledTouchSlop();
        this.f9730b1 = viewConfiguration.getScaledPagingTouchSlop();
        super.setScrollingTouchSlop(i7);
    }
}
