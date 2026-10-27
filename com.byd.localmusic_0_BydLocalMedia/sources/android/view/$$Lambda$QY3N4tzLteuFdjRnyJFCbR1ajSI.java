package android.view;

import android.util.SparseArray;
import java.util.function.IntFunction;

/* JADX INFO: renamed from: android.view.-$$Lambda$QY3N4tzLteuFdjRnyJFCbR1ajSI, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$QY3N4tzLteuFdjRnyJFCbR1ajSI implements IntFunction {
    public final /* synthetic */ SparseArray f$0;

    public /* synthetic */ $$Lambda$QY3N4tzLteuFdjRnyJFCbR1ajSI(SparseArray sparseArray) {
        this.f$0 = sparseArray;
    }

    @Override // java.util.function.IntFunction
    public final Object apply(int i) {
        return (String) this.f$0.get(i);
    }
}
