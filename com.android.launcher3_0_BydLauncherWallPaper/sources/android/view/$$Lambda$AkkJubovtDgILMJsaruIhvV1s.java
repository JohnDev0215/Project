package android.view;

import java.util.function.Consumer;

/* JADX INFO: renamed from: android.view.-$$Lambda$AkkJubovtDgIL-MJsaruIhv-V1s, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$AkkJubovtDgILMJsaruIhvV1s implements Consumer {
    public static final /* synthetic */ $$Lambda$AkkJubovtDgILMJsaruIhvV1s INSTANCE = new $$Lambda$AkkJubovtDgILMJsaruIhvV1s();

    private /* synthetic */ $$Lambda$AkkJubovtDgILMJsaruIhvV1s() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((SurfaceControl) obj).release();
    }
}
