package android.location;

import android.location.LocationManager;
import com.android.internal.util.function.QuadConsumer;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: android.location.-$$Lambda$LocationManager$LocationListenerTransport$C3xaM63A8GAwfJNN4R634OLsvDc, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$LocationManager$LocationListenerTransport$C3xaM63A8GAwfJNN4R634OLsvDc implements QuadConsumer {
    public static final /* synthetic */ $$Lambda$LocationManager$LocationListenerTransport$C3xaM63A8GAwfJNN4R634OLsvDc INSTANCE = new $$Lambda$LocationManager$LocationListenerTransport$C3xaM63A8GAwfJNN4R634OLsvDc();

    private /* synthetic */ $$Lambda$LocationManager$LocationListenerTransport$C3xaM63A8GAwfJNN4R634OLsvDc() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((LocationManager.LocationListenerTransport) obj).acceptProviderChange((Executor) obj2, (String) obj3, ((Boolean) obj4).booleanValue());
    }
}
