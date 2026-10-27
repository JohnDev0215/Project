package android.permission;

import com.android.internal.infra.AndroidFuture;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: android.permission.-$$Lambda$iNX3LK7OVLRPv9_Lf_TwPCbUW98, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$iNX3LK7OVLRPv9_Lf_TwPCbUW98 implements Consumer {
    public final /* synthetic */ AndroidFuture f$0;

    public /* synthetic */ $$Lambda$iNX3LK7OVLRPv9_Lf_TwPCbUW98(AndroidFuture androidFuture) {
        this.f$0 = androidFuture;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f$0.complete((List) obj);
    }
}
