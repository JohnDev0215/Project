package io.sentry.android.core;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.SparseIntArray;
import androidx.core.app.FrameMetricsAggregator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import v1.WindowOnFrameMetricsAvailableListenerC2534a;

/* JADX INFO: renamed from: io.sentry.android.core.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC1426b implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ N0.q f16147m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Activity f16148n;

    public /* synthetic */ RunnableC1426b(N0.q qVar, Activity activity, int i7) {
        this.l = i7;
        this.f16147m = qVar;
        this.f16148n = activity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Activity activity;
        switch (this.l) {
            case 0:
                h2.s sVar = ((FrameMetricsAggregator) this.f16147m.f4399m).f11736a;
                sVar.getClass();
                if (h2.s.f15235f == null) {
                    HandlerThread handlerThread = new HandlerThread("FrameMetricsAggregator");
                    h2.s.f15235f = handlerThread;
                    handlerThread.start();
                    h2.s.f15236g = new Handler(h2.s.f15235f.getLooper());
                }
                for (int i7 = 0; i7 <= 8; i7++) {
                    SparseIntArray[] sparseIntArrayArr = (SparseIntArray[]) sVar.f15238b;
                    if (sparseIntArrayArr[i7] == null && (sVar.f15237a & (1 << i7)) != 0) {
                        sparseIntArrayArr[i7] = new SparseIntArray();
                    }
                }
                Activity activity2 = this.f16148n;
                activity2.getWindow().addOnFrameMetricsAvailableListener((WindowOnFrameMetricsAvailableListenerC2534a) sVar.d, h2.s.f15236g);
                ((ArrayList) sVar.c).add(new WeakReference(activity2));
                break;
            default:
                h2.s sVar2 = ((FrameMetricsAggregator) this.f16147m.f4399m).f11736a;
                ArrayList arrayList = (ArrayList) sVar2.c;
                Iterator it = arrayList.iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    activity = this.f16148n;
                    if (zHasNext) {
                        WeakReference weakReference = (WeakReference) it.next();
                        if (weakReference.get() == activity) {
                            arrayList.remove(weakReference);
                        }
                    }
                }
                activity.getWindow().removeOnFrameMetricsAvailableListener((WindowOnFrameMetricsAvailableListenerC2534a) sVar2.d);
                break;
        }
    }
}
