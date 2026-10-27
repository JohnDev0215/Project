package com.amazon.music.platform.playback.capabilities.error;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.AtomicMapKt;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.providers.InterfaceProvider;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: CapabilityProviderErrorHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00062\u001a\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t0\u0007J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\bR6\u0010\u0003\u001a*\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t0\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/error/CapabilityProviderErrorHandler;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "()V", "errorMapping", "Lcom/amazon/music/platform/AtomicMap;", "Lkotlin/reflect/KClass;", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityError;", "Lkotlin/Function2;", "Lcom/amazon/music/platform/data/entity/EntityItem;", "", "addErrorHandling", "eligibilityError", "onError", "handleError", "", "entityItem", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class CapabilityProviderErrorHandler implements InterfaceProvider {
    public static final CapabilityProviderErrorHandler INSTANCE = new CapabilityProviderErrorHandler();
    private static final AtomicMap<KClass<? extends EligibilityError>, Function2<EligibilityError, EntityItem, Unit>> errorMapping = AtomicMapKt.atomicMapOf();

    private CapabilityProviderErrorHandler() {
    }

    public final void addErrorHandling(EligibilityError eligibilityError, Function2<? super EligibilityError, ? super EntityItem, Unit> onError) {
        Intrinsics.checkNotNullParameter(eligibilityError, "eligibilityError");
        Intrinsics.checkNotNullParameter(onError, "onError");
        AtomicMapKt.plusAssign(errorMapping, TuplesKt.to(Reflection.getOrCreateKotlinClass(eligibilityError.getClass()), onError));
    }

    public final boolean handleError(EligibilityError eligibilityError, EntityItem entityItem) {
        Intrinsics.checkNotNullParameter(eligibilityError, "eligibilityError");
        Function2<EligibilityError, EntityItem, Unit> function2 = errorMapping.get(Reflection.getOrCreateKotlinClass(eligibilityError.getClass()));
        if (function2 != null) {
            function2.mo4invoke(eligibilityError, entityItem);
        }
        return function2 != null;
    }
}
