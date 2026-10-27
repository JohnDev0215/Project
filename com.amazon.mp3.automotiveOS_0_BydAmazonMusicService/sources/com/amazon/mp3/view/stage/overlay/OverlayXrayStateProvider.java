package com.amazon.mp3.view.stage.overlay;

import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesTemplate;
import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetItemElement;
import com.amazon.music.media.playback.MediaItem;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: OverlayXrayStateProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0006\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010+¢\u0006\u0004\b2\u00103J\u0006\u0010\u0003\u001a\u00020\u0002J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010\u001f\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010\u001a\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR$\u0010\"\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R$\u0010(\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010#\u001a\u0004\b)\u0010%\"\u0004\b*\u0010'R$\u0010,\u001a\u0004\u0018\u00010+8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u00064"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlayXrayStateProvider;", "", "", "reset", "", "toString", "", "hashCode", "other", "", "equals", "LTouch/WidgetsInterface/v1_0/PillNavigatorWidgetItemElement;", "pillNavigatorWidgetItemElement", "LTouch/WidgetsInterface/v1_0/PillNavigatorWidgetItemElement;", "getPillNavigatorWidgetItemElement", "()LTouch/WidgetsInterface/v1_0/PillNavigatorWidgetItemElement;", "setPillNavigatorWidgetItemElement", "(LTouch/WidgetsInterface/v1_0/PillNavigatorWidgetItemElement;)V", "LTouch/SwipeablePagesTemplateInterface/v1_0/SwipeablePagesTemplate;", "swipeablePagesTemplate", "LTouch/SwipeablePagesTemplateInterface/v1_0/SwipeablePagesTemplate;", "getSwipeablePagesTemplate", "()LTouch/SwipeablePagesTemplateInterface/v1_0/SwipeablePagesTemplate;", "setSwipeablePagesTemplate", "(LTouch/SwipeablePagesTemplateInterface/v1_0/SwipeablePagesTemplate;)V", "currentPageIndex", "I", "getCurrentPageIndex", "()I", "setCurrentPageIndex", "(I)V", "currentSectionIndex", "getCurrentSectionIndex", "setCurrentSectionIndex", "hasUserInteracted", "Ljava/lang/Boolean;", "getHasUserInteracted", "()Ljava/lang/Boolean;", "setHasUserInteracted", "(Ljava/lang/Boolean;)V", "wasXrayShownInPreview", "getWasXrayShownInPreview", "setWasXrayShownInPreview", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "getMediaItem", "()Lcom/amazon/music/media/playback/MediaItem;", "setMediaItem", "(Lcom/amazon/music/media/playback/MediaItem;)V", "<init>", "(LTouch/WidgetsInterface/v1_0/PillNavigatorWidgetItemElement;LTouch/SwipeablePagesTemplateInterface/v1_0/SwipeablePagesTemplate;IILjava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/music/media/playback/MediaItem;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class OverlayXrayStateProvider {
    private int currentPageIndex;
    private int currentSectionIndex;
    private Boolean hasUserInteracted;
    private MediaItem mediaItem;
    private PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement;
    private SwipeablePagesTemplate swipeablePagesTemplate;
    private Boolean wasXrayShownInPreview;

    public OverlayXrayStateProvider() {
        this(null, null, 0, 0, null, null, null, WorkQueueKt.MASK, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OverlayXrayStateProvider)) {
            return false;
        }
        OverlayXrayStateProvider overlayXrayStateProvider = (OverlayXrayStateProvider) other;
        return Intrinsics.areEqual(this.pillNavigatorWidgetItemElement, overlayXrayStateProvider.pillNavigatorWidgetItemElement) && Intrinsics.areEqual(this.swipeablePagesTemplate, overlayXrayStateProvider.swipeablePagesTemplate) && this.currentPageIndex == overlayXrayStateProvider.currentPageIndex && this.currentSectionIndex == overlayXrayStateProvider.currentSectionIndex && Intrinsics.areEqual(this.hasUserInteracted, overlayXrayStateProvider.hasUserInteracted) && Intrinsics.areEqual(this.wasXrayShownInPreview, overlayXrayStateProvider.wasXrayShownInPreview) && Intrinsics.areEqual(this.mediaItem, overlayXrayStateProvider.mediaItem);
    }

    public int hashCode() {
        PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement = this.pillNavigatorWidgetItemElement;
        int iHashCode = (pillNavigatorWidgetItemElement == null ? 0 : pillNavigatorWidgetItemElement.hashCode()) * 31;
        SwipeablePagesTemplate swipeablePagesTemplate = this.swipeablePagesTemplate;
        int iHashCode2 = (((((iHashCode + (swipeablePagesTemplate == null ? 0 : swipeablePagesTemplate.hashCode())) * 31) + Integer.hashCode(this.currentPageIndex)) * 31) + Integer.hashCode(this.currentSectionIndex)) * 31;
        Boolean bool = this.hasUserInteracted;
        int iHashCode3 = (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.wasXrayShownInPreview;
        int iHashCode4 = (iHashCode3 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        MediaItem mediaItem = this.mediaItem;
        return iHashCode4 + (mediaItem != null ? mediaItem.hashCode() : 0);
    }

    public String toString() {
        return "OverlayXrayStateProvider(pillNavigatorWidgetItemElement=" + this.pillNavigatorWidgetItemElement + ", swipeablePagesTemplate=" + this.swipeablePagesTemplate + ", currentPageIndex=" + this.currentPageIndex + ", currentSectionIndex=" + this.currentSectionIndex + ", hasUserInteracted=" + this.hasUserInteracted + ", wasXrayShownInPreview=" + this.wasXrayShownInPreview + ", mediaItem=" + this.mediaItem + ')';
    }

    public OverlayXrayStateProvider(PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement, SwipeablePagesTemplate swipeablePagesTemplate, int i, int i2, Boolean bool, Boolean bool2, MediaItem mediaItem) {
        this.pillNavigatorWidgetItemElement = pillNavigatorWidgetItemElement;
        this.swipeablePagesTemplate = swipeablePagesTemplate;
        this.currentPageIndex = i;
        this.currentSectionIndex = i2;
        this.hasUserInteracted = bool;
        this.wasXrayShownInPreview = bool2;
        this.mediaItem = mediaItem;
    }

    public /* synthetic */ OverlayXrayStateProvider(PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement, SwipeablePagesTemplate swipeablePagesTemplate, int i, int i2, Boolean bool, Boolean bool2, MediaItem mediaItem, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : pillNavigatorWidgetItemElement, (i3 & 2) != 0 ? null : swipeablePagesTemplate, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? null : bool, (i3 & 32) != 0 ? null : bool2, (i3 & 64) != 0 ? null : mediaItem);
    }

    public final PillNavigatorWidgetItemElement getPillNavigatorWidgetItemElement() {
        return this.pillNavigatorWidgetItemElement;
    }

    public final void setPillNavigatorWidgetItemElement(PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
        this.pillNavigatorWidgetItemElement = pillNavigatorWidgetItemElement;
    }

    public final SwipeablePagesTemplate getSwipeablePagesTemplate() {
        return this.swipeablePagesTemplate;
    }

    public final void setSwipeablePagesTemplate(SwipeablePagesTemplate swipeablePagesTemplate) {
        this.swipeablePagesTemplate = swipeablePagesTemplate;
    }

    public final int getCurrentPageIndex() {
        return this.currentPageIndex;
    }

    public final void setCurrentPageIndex(int i) {
        this.currentPageIndex = i;
    }

    public final int getCurrentSectionIndex() {
        return this.currentSectionIndex;
    }

    public final void setCurrentSectionIndex(int i) {
        this.currentSectionIndex = i;
    }

    public final Boolean getHasUserInteracted() {
        return this.hasUserInteracted;
    }

    public final void setHasUserInteracted(Boolean bool) {
        this.hasUserInteracted = bool;
    }

    public final Boolean getWasXrayShownInPreview() {
        return this.wasXrayShownInPreview;
    }

    public final void setWasXrayShownInPreview(Boolean bool) {
        this.wasXrayShownInPreview = bool;
    }

    public final MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public final void setMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    public final void reset() {
        this.pillNavigatorWidgetItemElement = null;
        this.swipeablePagesTemplate = null;
        this.currentPageIndex = 0;
        this.currentSectionIndex = 0;
        this.hasUserInteracted = null;
        this.wasXrayShownInPreview = null;
        this.mediaItem = null;
    }
}
