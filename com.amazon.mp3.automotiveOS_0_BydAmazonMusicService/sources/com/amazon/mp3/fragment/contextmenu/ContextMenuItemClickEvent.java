package com.amazon.mp3.fragment.contextmenu;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContextMenuMetricHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010%\u001a\u0004\u0018\u00010$\u0012\u0014\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010)¢\u0006\u0004\b.\u0010/J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00018\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0019\u0010 \u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b \u0010\u000e\u001a\u0004\b!\u0010\u0010R\u0019\u0010\"\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\"\u0010\u000e\u001a\u0004\b#\u0010\u0010R\u0019\u0010%\u001a\u0004\u0018\u00010$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R%\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-¨\u00060"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/ContextMenuItemClickEvent;", "", "", "toString", "", "hashCode", "other", "", "equals", "item", "Ljava/lang/Object;", "getItem", "()Ljava/lang/Object;", "sourceId", "Ljava/lang/String;", "getSourceId", "()Ljava/lang/String;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getActionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "getInteractionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "blockRef", "getBlockRef", ContextMappingConstants.ENTITY_ID, "getEntityId", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "getEntityIdType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "", ContextMappingConstants.CONTENT_INFO, "Ljava/util/Map;", "getContentInfo", "()Ljava/util/Map;", "<init>", "(Ljava/lang/Object;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;Ljava/util/Map;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ContextMenuItemClickEvent {
    private final ActionType actionType;
    private final String blockRef;
    private final Map<String, String> contentInfo;
    private final String entityId;
    private final EntityIdType entityIdType;
    private final InteractionType interactionType;
    private final Object item;
    private final PageType pageType;
    private final String sourceId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContextMenuItemClickEvent)) {
            return false;
        }
        ContextMenuItemClickEvent contextMenuItemClickEvent = (ContextMenuItemClickEvent) other;
        return Intrinsics.areEqual(this.item, contextMenuItemClickEvent.item) && Intrinsics.areEqual(this.sourceId, contextMenuItemClickEvent.sourceId) && this.actionType == contextMenuItemClickEvent.actionType && this.interactionType == contextMenuItemClickEvent.interactionType && this.pageType == contextMenuItemClickEvent.pageType && Intrinsics.areEqual(this.blockRef, contextMenuItemClickEvent.blockRef) && Intrinsics.areEqual(this.entityId, contextMenuItemClickEvent.entityId) && this.entityIdType == contextMenuItemClickEvent.entityIdType && Intrinsics.areEqual(this.contentInfo, contextMenuItemClickEvent.contentInfo);
    }

    public int hashCode() {
        Object obj = this.item;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        String str = this.sourceId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        ActionType actionType = this.actionType;
        int iHashCode3 = (iHashCode2 + (actionType == null ? 0 : actionType.hashCode())) * 31;
        InteractionType interactionType = this.interactionType;
        int iHashCode4 = (iHashCode3 + (interactionType == null ? 0 : interactionType.hashCode())) * 31;
        PageType pageType = this.pageType;
        int iHashCode5 = (iHashCode4 + (pageType == null ? 0 : pageType.hashCode())) * 31;
        String str2 = this.blockRef;
        int iHashCode6 = (iHashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.entityId;
        int iHashCode7 = (iHashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31;
        EntityIdType entityIdType = this.entityIdType;
        int iHashCode8 = (iHashCode7 + (entityIdType == null ? 0 : entityIdType.hashCode())) * 31;
        Map<String, String> map = this.contentInfo;
        return iHashCode8 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "ContextMenuItemClickEvent(item=" + this.item + ", sourceId=" + ((Object) this.sourceId) + ", actionType=" + this.actionType + ", interactionType=" + this.interactionType + ", pageType=" + this.pageType + ", blockRef=" + ((Object) this.blockRef) + ", entityId=" + ((Object) this.entityId) + ", entityIdType=" + this.entityIdType + ", contentInfo=" + this.contentInfo + ')';
    }

    public ContextMenuItemClickEvent(Object obj, String str, ActionType actionType, InteractionType interactionType, PageType pageType, String str2, String str3, EntityIdType entityIdType, Map<String, String> map) {
        this.item = obj;
        this.sourceId = str;
        this.actionType = actionType;
        this.interactionType = interactionType;
        this.pageType = pageType;
        this.blockRef = str2;
        this.entityId = str3;
        this.entityIdType = entityIdType;
        this.contentInfo = map;
    }

    public final ActionType getActionType() {
        return this.actionType;
    }
}
