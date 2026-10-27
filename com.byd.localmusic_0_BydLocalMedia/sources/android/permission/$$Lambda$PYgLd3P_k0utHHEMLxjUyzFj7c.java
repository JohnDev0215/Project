package android.permission;

import com.android.internal.infra.AndroidFuture;
import java.util.function.Consumer;

/* JADX INFO: renamed from: android.permission.-$$Lambda$PYgLd3P_k0utHHEMLxjUyz-Fj7c, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$PYgLd3P_k0utHHEMLxjUyzFj7c implements Consumer {
    public final /* synthetic */ AndroidFuture f$0;

    public /* synthetic */ $$Lambda$PYgLd3P_k0utHHEMLxjUyzFj7c(AndroidFuture androidFuture) {
        this.f$0 = androidFuture;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f$0.complete((Boolean) obj);
    }
}
