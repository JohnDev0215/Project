package com.facebook.react.uimanager;

import androidx.annotation.Nullable;
import com.facebook.react.common.MapBuilder;
import com.facebook.systrace.SystraceMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
class UIManagerModuleConstantsHelper {
    UIManagerModuleConstantsHelper() {
    }

    static Map<String, Object> createConstants(ViewManagerResolver viewManagerResolver) {
        Map<String, Object> constants = UIManagerModuleConstants.getConstants();
        constants.put("ViewManagerNames", new ArrayList(viewManagerResolver.getViewManagerNames()));
        constants.put("LazyViewManagersEnabled", Boolean.TRUE);
        return constants;
    }

    static Map<String, Object> getDefaultExportableEventTypes() {
        return MapBuilder.of("bubblingEventTypes", UIManagerModuleConstants.getBubblingEventTypeConstants(), "directEventTypes", UIManagerModuleConstants.getDirectEventTypeConstants());
    }

    static Map<String, Object> createConstants(List<ViewManager> list, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2) {
        Map<String, Object> constants = UIManagerModuleConstants.getConstants();
        Map<? extends String, ? extends Object> bubblingEventTypeConstants = UIManagerModuleConstants.getBubblingEventTypeConstants();
        Map<? extends String, ? extends Object> directEventTypeConstants = UIManagerModuleConstants.getDirectEventTypeConstants();
        if (map != null) {
            map.putAll(bubblingEventTypeConstants);
        }
        if (map2 != null) {
            map2.putAll(directEventTypeConstants);
        }
        for (ViewManager viewManager : list) {
            String name = viewManager.getName();
            SystraceMessage.beginSection(0L, "UIManagerModuleConstantsHelper.createConstants").arg("ViewManager", name).arg("Lazy", Boolean.FALSE).flush();
            try {
                Map<String, Object> mapCreateConstantsForViewManager = createConstantsForViewManager(viewManager, null, null, map, map2);
                if (!mapCreateConstantsForViewManager.isEmpty()) {
                    constants.put(name, mapCreateConstantsForViewManager);
                }
            } finally {
                SystraceMessage.endSection(0L);
            }
        }
        constants.put("genericBubblingEventTypes", bubblingEventTypeConstants);
        constants.put("genericDirectEventTypes", directEventTypeConstants);
        return constants;
    }

    static Map<String, Object> createConstantsForViewManager(ViewManager viewManager, @Nullable Map map, @Nullable Map map2, @Nullable Map map3, @Nullable Map map4) {
        HashMap mapNewHashMap = MapBuilder.newHashMap();
        Map<String, Object> exportedCustomBubblingEventTypeConstants = viewManager.getExportedCustomBubblingEventTypeConstants();
        if (exportedCustomBubblingEventTypeConstants != null) {
            recursiveMerge(map3, exportedCustomBubblingEventTypeConstants);
            recursiveMerge(exportedCustomBubblingEventTypeConstants, map);
            mapNewHashMap.put("bubblingEventTypes", exportedCustomBubblingEventTypeConstants);
        } else if (map != null) {
            mapNewHashMap.put("bubblingEventTypes", map);
        }
        Map<String, Object> exportedCustomDirectEventTypeConstants = viewManager.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants != null) {
            recursiveMerge(map4, exportedCustomDirectEventTypeConstants);
            recursiveMerge(exportedCustomDirectEventTypeConstants, map2);
            mapNewHashMap.put("directEventTypes", exportedCustomDirectEventTypeConstants);
        } else if (map2 != null) {
            mapNewHashMap.put("directEventTypes", map2);
        }
        Map<String, Object> exportedViewConstants = viewManager.getExportedViewConstants();
        if (exportedViewConstants != null) {
            mapNewHashMap.put("Constants", exportedViewConstants);
        }
        Map<String, Integer> commandsMap = viewManager.getCommandsMap();
        if (commandsMap != null) {
            mapNewHashMap.put("Commands", commandsMap);
        }
        Map<String, String> nativeProps = viewManager.getNativeProps();
        if (!nativeProps.isEmpty()) {
            mapNewHashMap.put("NativeProps", nativeProps);
        }
        return mapNewHashMap;
    }

    private static void recursiveMerge(@Nullable Map map, @Nullable Map map2) {
        if (map == null || map2 == null || map2.isEmpty()) {
            return;
        }
        for (Object obj : map2.keySet()) {
            Object obj2 = map2.get(obj);
            Object obj3 = map.get(obj);
            if (obj3 != null && (obj2 instanceof Map) && (obj3 instanceof Map)) {
                recursiveMerge((Map) obj3, (Map) obj2);
            } else {
                map.put(obj, obj2);
            }
        }
    }
}
