package b.a.a.i.m;

import android.view.View;
import android.widget.ViewSwitcher;
import com.byd.hvac.view.seat.SeatHeatVentilationFragment;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements ViewSwitcher.ViewFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SeatHeatVentilationFragment f926a;

    public /* synthetic */ a(SeatHeatVentilationFragment seatHeatVentilationFragment) {
        this.f926a = seatHeatVentilationFragment;
    }

    @Override // android.widget.ViewSwitcher.ViewFactory
    public final View makeView() {
        return this.f926a.N0();
    }
}
