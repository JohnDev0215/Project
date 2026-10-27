package androidx.core.app;

import android.util.SparseIntArray;
import h2.s;
import java.util.ArrayList;
import v1.WindowOnFrameMetricsAvailableListenerC2534a;

/* JADX INFO: loaded from: classes.dex */
public class FrameMetricsAggregator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s f11736a;

    public FrameMetricsAggregator() {
        this(1);
    }

    public FrameMetricsAggregator(int i7) {
        s sVar = new s();
        sVar.f15238b = new SparseIntArray[9];
        sVar.c = new ArrayList();
        sVar.d = new WindowOnFrameMetricsAvailableListenerC2534a(sVar);
        sVar.f15237a = i7;
        this.f11736a = sVar;
    }
}
