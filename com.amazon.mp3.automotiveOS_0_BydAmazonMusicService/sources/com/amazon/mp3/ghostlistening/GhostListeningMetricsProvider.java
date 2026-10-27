package com.amazon.mp3.ghostlistening;

import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.InteractionType;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class GhostListeningMetricsProvider {
    public static UserInteractionAppEvent.Builder getContinueListeningMetric(final boolean viaDialog) {
        UserInteractionAppEvent.Builder builderWithContentInfo = UserInteractionAppEvent.builder("continueListeningGhostListening").withContentInfo(buildContentInfoForGhostListeningDialog());
        if (viaDialog) {
            builderWithContentInfo.withInteractionType(InteractionType.TAP);
        }
        return builderWithContentInfo;
    }

    public static UserInteractionAppEvent.Builder getDismissMetric() {
        return UserInteractionAppEvent.builder("dismissGhostListening").withInteractionType(InteractionType.TAP).withContentInfo(buildContentInfoForGhostListeningDialog());
    }

    public static final List<Map<String, String>> buildContentInfoForGhostListeningDialog() {
        return Collections.singletonList(Collections.singletonMap("contentName", "ghostListening"));
    }
}
