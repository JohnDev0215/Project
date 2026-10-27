package com.amazon.music.platform.playback.capabilities.error;

import com.amazon.music.platform.data.entity.EntityItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EligibilityThrowable.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¨\u0006\u0005"}, d2 = {"handleError", "", "Lcom/amazon/music/platform/playback/capabilities/error/EligibilityThrowable;", "entityItem", "Lcom/amazon/music/platform/data/entity/EntityItem;", "MusicPlatformCore_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class EligibilityThrowableKt {
    public static final void handleError(EligibilityThrowable eligibilityThrowable, EntityItem entityItem) {
        Intrinsics.checkNotNullParameter(eligibilityThrowable, "<this>");
        CapabilityProviderErrorHandler.INSTANCE.handleError(eligibilityThrowable, entityItem);
    }
}
