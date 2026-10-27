package com.amazon.mp3.providers;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UIEventHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/providers/UIEventHandler;", "", "()V", "emitUiClickMetric", "", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UIEventHandler {
    public static final UIEventHandler INSTANCE = new UIEventHandler();

    private UIEventHandler() {
    }

    public final void emitUiClickMetric(ActionType actionType, ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        UiClickEvent.Builder builderWithBlockRef = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withBlockRef(metadata.getViewId());
        if (metadata instanceof ArtistMetadata) {
            builderWithBlockRef.withEntityType(EntityType.ARTIST).withEntityIdType(EntityIdType.ASIN).withEntityId(((ArtistMetadata) metadata).getAsin());
        }
        UiClickEvent uiClickEventBuild = builderWithBlockRef.build();
        Intrinsics.checkNotNullExpressionValue(uiClickEventBuild, "builder.build()");
        MetricsHolder.getManager().handleEvent(uiClickEventBuild);
    }
}
