package android.location;

import android.location.LocationManager;
import com.android.internal.util.function.QuadConsumer;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: android.location.-$$Lambda$LocationManager$LocationListenerTransport$OfJ8qeu44rva3AgarDg5NIlE0KU, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$LocationManager$LocationListenerTransport$OfJ8qeu44rva3AgarDg5NIlE0KU implements QuadConsumer {
    public static final /* synthetic */ $$Lambda$LocationManager$LocationListenerTransport$OfJ8qeu44rva3AgarDg5NIlE0KU INSTANCE = new $$Lambda$LocationManager$LocationListenerTransport$OfJ8qeu44rva3AgarDg5NIlE0KU();

    private /* synthetic */ $$Lambda$LocationManager$LocationListenerTransport$OfJ8qeu44rva3AgarDg5NIlE0KU() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((LocationManager.LocationListenerTransport) obj).acceptProviderChange((Executor) obj2, (String) obj3, ((Boolean) obj4).booleanValue());
    }
}
