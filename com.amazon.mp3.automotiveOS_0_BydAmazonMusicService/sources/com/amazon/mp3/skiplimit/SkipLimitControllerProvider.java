package com.amazon.mp3.skiplimit;

import com.amazon.mp3.skiplimit.impl.DefaultSkipLimitController;
import com.amazon.mp3.skiplimit.impl.SkipLimitProviderImpl;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: SkipLimitControllerProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/skiplimit/SkipLimitControllerProvider;", "", "()V", "controller", "Lcom/amazon/mp3/skiplimit/impl/DefaultSkipLimitController;", "getController", "()Lcom/amazon/mp3/skiplimit/impl/DefaultSkipLimitController;", "controller$delegate", "Lkotlin/Lazy;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SkipLimitControllerProvider {
    public static final SkipLimitControllerProvider INSTANCE = new SkipLimitControllerProvider();

    /* JADX INFO: renamed from: controller$delegate, reason: from kotlin metadata */
    private static final Lazy controller = LazyKt__LazyJVMKt.lazy(new Function0<DefaultSkipLimitController>() { // from class: com.amazon.mp3.skiplimit.SkipLimitControllerProvider$controller$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DefaultSkipLimitController invoke() {
            return new DefaultSkipLimitController(SkipLimitProviderImpl.INSTANCE);
        }
    });

    private SkipLimitControllerProvider() {
    }

    public final DefaultSkipLimitController getController() {
        return (DefaultSkipLimitController) controller.getValue();
    }
}
