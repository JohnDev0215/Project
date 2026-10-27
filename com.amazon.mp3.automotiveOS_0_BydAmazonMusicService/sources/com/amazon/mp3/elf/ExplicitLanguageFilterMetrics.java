package com.amazon.mp3.elf;

import com.amazon.mp3.AmazonApplication;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class ExplicitLanguageFilterMetrics {
    public static void emitSettingClickMetric() {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(getSettingActionType()).withInteractionType(InteractionType.TAP).withContentInfo(getSettingsContentInfo()).build());
    }

    public static void emitToggleClickMetric(final boolean fromSettingsPage) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(getToggleActionType()).withInteractionType(InteractionType.TAP).withContentInfo(getDialogContentInfo(fromSettingsPage)).build());
    }

    public static void emitCancelClickMetric(final boolean fromSettingsPage) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SELECT_CANCEL).withInteractionType(InteractionType.TAP).withContentInfo(getDialogContentInfo(fromSettingsPage)).build());
    }

    private static boolean isExplicitFilterEnabled() {
        return AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled();
    }

    private static ActionType getSettingActionType() {
        if (isExplicitFilterEnabled()) {
            return ActionType.SELECT_EXPLICIT_LANGUAGE_FILTER_OFF;
        }
        return ActionType.SELECT_EXPLICIT_LANGUAGE_FILTER_ON;
    }

    private static ActionType getToggleActionType() {
        if (isExplicitFilterEnabled()) {
            return ActionType.CONFIRM_EXPLICIT_LANGUAGE_FILTER_OFF;
        }
        return ActionType.CONFIRM_EXPLICIT_LANGUAGE_FILTER_ON;
    }

    private static List<Map<String, String>> getSettingsContentInfo() {
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        map.put("contentName", "explicitLanguageFilterSetting");
        arrayList.add(map);
        return arrayList;
    }

    private static List<Map<String, String>> getDialogContentInfo(final boolean fromSettingsPage) {
        String str;
        if (fromSettingsPage) {
            str = isExplicitFilterEnabled() ? "confirmExplicitLanguageFilterOffDialog" : "confirmExplicitLanguageFilterOnDialog";
        } else {
            str = "explicitSongsBlockedDialog";
        }
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        map.put("contentName", str);
        arrayList.add(map);
        return arrayList;
    }
}
