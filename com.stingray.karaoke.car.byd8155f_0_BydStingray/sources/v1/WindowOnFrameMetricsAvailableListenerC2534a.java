package v1;

import android.util.SparseIntArray;
import android.view.FrameMetrics;
import android.view.Window;
import h2.s;

/* JADX INFO: renamed from: v1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class WindowOnFrameMetricsAvailableListenerC2534a implements Window.OnFrameMetricsAvailableListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ s f22942a;

    public WindowOnFrameMetricsAvailableListenerC2534a(s sVar) {
        this.f22942a = sVar;
    }

    @Override // android.view.Window.OnFrameMetricsAvailableListener
    public final void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i7) {
        s sVar = this.f22942a;
        if ((sVar.f15237a & 1) != 0) {
            s.b(((SparseIntArray[]) sVar.f15238b)[0], frameMetrics.getMetric(8));
        }
        if ((sVar.f15237a & 2) != 0) {
            s.b(((SparseIntArray[]) sVar.f15238b)[1], frameMetrics.getMetric(1));
        }
        if ((sVar.f15237a & 4) != 0) {
            s.b(((SparseIntArray[]) sVar.f15238b)[2], frameMetrics.getMetric(3));
        }
        if ((sVar.f15237a & 8) != 0) {
            s.b(((SparseIntArray[]) sVar.f15238b)[3], frameMetrics.getMetric(4));
        }
        if ((sVar.f15237a & 16) != 0) {
            s.b(((SparseIntArray[]) sVar.f15238b)[4], frameMetrics.getMetric(5));
        }
        if ((sVar.f15237a & 64) != 0) {
            s.b(((SparseIntArray[]) sVar.f15238b)[6], frameMetrics.getMetric(7));
        }
        if ((sVar.f15237a & 32) != 0) {
            s.b(((SparseIntArray[]) sVar.f15238b)[5], frameMetrics.getMetric(6));
        }
        if ((sVar.f15237a & 128) != 0) {
            s.b(((SparseIntArray[]) sVar.f15238b)[7], frameMetrics.getMetric(0));
        }
        if ((sVar.f15237a & 256) != 0) {
            s.b(((SparseIntArray[]) sVar.f15238b)[8], frameMetrics.getMetric(2));
        }
    }
}
