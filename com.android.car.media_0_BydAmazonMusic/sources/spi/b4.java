package spi;

import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.rotary.FocusParkingView;

/* JADX INFO: compiled from: FocusParkingView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b4 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ RecyclerView a;
    public final /* synthetic */ FocusParkingView b;

    public b4(FocusParkingView focusParkingView, RecyclerView recyclerView) {
        this.b = focusParkingView;
        this.a = recyclerView;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        this.a.sendAccessibilityEvent(8);
        this.b.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
}
