package com.amazon.music.uiinteraction.mapping;

import com.amazon.music.events.ContentViewedAppEvent;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.Orientation;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ViewType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class LocalMapper implements Mapper {
    private static final Logger LOG = LoggerFactory.getLogger(LocalMapper.class.getSimpleName());
    private Map<String, Map<String, String>> uiClickMap;
    private Map<String, Map<String, String>> uiPageViewMap;

    public LocalMapper(ObjectMapper objectMapper, InputStream inputStream) {
        try {
            Map map = (Map) objectMapper.readValue(inputStream, Map.class);
            if (map == null) {
                LOG.warn("Mappings returned null object, no metrics will be reported");
            } else {
                this.uiClickMap = (Map) map.get("uiClick");
                this.uiPageViewMap = (Map) map.get("uiPageView");
            }
        } catch (IOException unused) {
            LOG.warn("Failed to set up the mapping, no metrics will be reported");
        }
    }

    @Override // com.amazon.music.uiinteraction.mapping.Mapper
    public UiContentViewEvent mapUiContentView(ContentViewedAppEvent contentViewedAppEvent) {
        if (contentViewedAppEvent == null || contentViewedAppEvent.getContentInfo() == null) {
            return null;
        }
        UiContentViewEvent.Builder builderWithContentInfo = UiContentViewEvent.builder().withEventTime(contentViewedAppEvent.getEventTime()).withContentInfo(contentViewedAppEvent.getContentInfo());
        if (contentViewedAppEvent.getEmittedOnView()) {
            builderWithContentInfo.emittedOnView();
        }
        return builderWithContentInfo.build();
    }

    @Override // com.amazon.music.uiinteraction.mapping.Mapper
    public UiClickEvent mapUiClick(UserInteractionAppEvent userInteractionAppEvent) {
        Map<String, String> map;
        InteractionType interactionTypeValueOf;
        PageType pageTypeValueOf;
        Map<String, Map<String, String>> map2 = this.uiClickMap;
        if (map2 == null || userInteractionAppEvent == null || (map = map2.get(userInteractionAppEvent.getIdentifier())) == null) {
            return null;
        }
        ActionType actionTypeValueOf = ActionType.valueOf(map.get("actionType"));
        if (map.get("interactionType") != null) {
            interactionTypeValueOf = InteractionType.valueOf(map.get("interactionType"));
        } else {
            interactionTypeValueOf = userInteractionAppEvent.getInteractionType() != null ? InteractionType.valueOf(userInteractionAppEvent.getInteractionType().toString()) : null;
        }
        if (map.get("pageType") != null) {
            pageTypeValueOf = PageType.valueOf(map.get("pageType"));
        } else {
            pageTypeValueOf = userInteractionAppEvent.getPageType() != null ? PageType.valueOf(userInteractionAppEvent.getPageType()) : null;
        }
        return UiClickEvent.builder(actionTypeValueOf).withEventTime(userInteractionAppEvent.getEventTime()).withInteractionType(interactionTypeValueOf).withPageType(pageTypeValueOf).withEntityId(userInteractionAppEvent.getEntityId()).withEntityIdType(userInteractionAppEvent.getEntityIdType() != null ? EntityIdType.valueOf(userInteractionAppEvent.getEntityIdType().toString()) : null).withEntityPos(Math.max(userInteractionAppEvent.getEntityPos(), -1)).withContentInfo(userInteractionAppEvent.getContentInfo()).withPageSubType(userInteractionAppEvent.getPageSubType()).withEntityType(userInteractionAppEvent.getEntityType() != null ? EntityType.valueOf(userInteractionAppEvent.getEntityType().toString()) : null).withBlockRef(userInteractionAppEvent.getBlockRef()).build();
    }

    @Override // com.amazon.music.uiinteraction.mapping.Mapper
    public UiPageViewEvent mapUiPageView(NavigationAppEvent navigationAppEvent) {
        String identifier;
        Map<String, String> map;
        String str;
        ViewType viewTypeForOrientation = null;
        if (this.uiPageViewMap == null || navigationAppEvent == null || (identifier = navigationAppEvent.getIdentifier()) == null) {
            return null;
        }
        if (navigationAppEvent.useIdentifierAsPageType()) {
            str = identifier;
            map = null;
        } else {
            map = this.uiPageViewMap.get(identifier);
            if (map == null) {
                return null;
            }
            str = map.get("pageType");
        }
        if (map != null && map.get("viewType") != null) {
            viewTypeForOrientation = ViewType.valueOf(map.get("viewType"));
        } else {
            Orientation orientation = navigationAppEvent.getOrientation();
            if (orientation != null) {
                viewTypeForOrientation = getViewTypeForOrientation(orientation);
            }
        }
        return UiPageViewEvent.builder(str).withEventTime(navigationAppEvent.getEventTime()).withViewType(viewTypeForOrientation).withExternalReference(navigationAppEvent.getRefMarker()).withAssociateTag(navigationAppEvent.getAssociateTag()).withDetailPageItemIdType(navigationAppEvent.getItemIdentifierType()).withDetailPageItemId(navigationAppEvent.getItemIdentifier()).withPageSubType(navigationAppEvent.getItemIdentifierSubType()).withBlockRef(navigationAppEvent.getBlockRef()).build();
    }

    /* JADX INFO: renamed from: com.amazon.music.uiinteraction.mapping.LocalMapper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$events$types$Orientation;

        static {
            int[] iArr = new int[Orientation.values().length];
            $SwitchMap$com$amazon$music$events$types$Orientation = iArr;
            try {
                iArr[Orientation.LANDSCAPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$events$types$Orientation[Orientation.PORTRAIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$events$types$Orientation[Orientation.UNDEFINED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$events$types$Orientation[Orientation.SQUARE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private ViewType getViewTypeForOrientation(Orientation orientation) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$events$types$Orientation[orientation.ordinal()];
        if (i == 1) {
            return ViewType.LANDSCAPE;
        }
        if (i == 2) {
            return ViewType.PORTRAIT;
        }
        if (i == 3) {
            return ViewType.UNDEFINED;
        }
        if (i != 4) {
            return null;
        }
        return ViewType.SQUARE;
    }
}
