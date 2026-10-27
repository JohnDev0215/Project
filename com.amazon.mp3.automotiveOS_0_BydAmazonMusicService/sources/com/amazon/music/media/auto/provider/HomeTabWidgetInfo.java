package com.amazon.music.media.auto.provider;

import com.amazon.music.media.auto.ui.PresentationViewType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeTabWidgetInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0010\u0017\u001a\u00020\u0007\u0012\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b$\u0010%J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR(\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u001e\u001a\u00020\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006&"}, d2 = {"Lcom/amazon/music/media/auto/provider/HomeTabWidgetInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "entities", "Ljava/util/List;", "getEntities", "()Ljava/util/List;", "setEntities", "(Ljava/util/List;)V", "shouldHaveSeeMoreNode", "Z", "getShouldHaveSeeMoreNode", "()Z", "setShouldHaveSeeMoreNode", "(Z)V", "Lcom/amazon/music/media/auto/ui/PresentationViewType;", "seeMorePresentationViewType", "Lcom/amazon/music/media/auto/ui/PresentationViewType;", "getSeeMorePresentationViewType", "()Lcom/amazon/music/media/auto/ui/PresentationViewType;", "setSeeMorePresentationViewType", "(Lcom/amazon/music/media/auto/ui/PresentationViewType;)V", "<init>", "(Ljava/lang/String;Ljava/util/List;ZLcom/amazon/music/media/auto/ui/PresentationViewType;)V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class HomeTabWidgetInfo {
    private List<AutoMediaItem> entities;
    private PresentationViewType seeMorePresentationViewType;
    private boolean shouldHaveSeeMoreNode;
    private String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeTabWidgetInfo)) {
            return false;
        }
        HomeTabWidgetInfo homeTabWidgetInfo = (HomeTabWidgetInfo) other;
        return Intrinsics.areEqual(this.title, homeTabWidgetInfo.title) && Intrinsics.areEqual(this.entities, homeTabWidgetInfo.entities) && this.shouldHaveSeeMoreNode == homeTabWidgetInfo.shouldHaveSeeMoreNode && this.seeMorePresentationViewType == homeTabWidgetInfo.seeMorePresentationViewType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    public int hashCode() {
        int iHashCode = ((this.title.hashCode() * 31) + this.entities.hashCode()) * 31;
        boolean z = this.shouldHaveSeeMoreNode;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return ((iHashCode + r1) * 31) + this.seeMorePresentationViewType.hashCode();
    }

    public String toString() {
        return "HomeTabWidgetInfo(title=" + this.title + ", entities=" + this.entities + ", shouldHaveSeeMoreNode=" + this.shouldHaveSeeMoreNode + ", seeMorePresentationViewType=" + this.seeMorePresentationViewType + ')';
    }

    public HomeTabWidgetInfo(String title, List<AutoMediaItem> entities, boolean z, PresentationViewType seeMorePresentationViewType) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(entities, "entities");
        Intrinsics.checkNotNullParameter(seeMorePresentationViewType, "seeMorePresentationViewType");
        this.title = title;
        this.entities = entities;
        this.shouldHaveSeeMoreNode = z;
        this.seeMorePresentationViewType = seeMorePresentationViewType;
    }

    public final String getTitle() {
        return this.title;
    }

    public final List<AutoMediaItem> getEntities() {
        return this.entities;
    }

    public final void setEntities(List<AutoMediaItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.entities = list;
    }

    public final boolean getShouldHaveSeeMoreNode() {
        return this.shouldHaveSeeMoreNode;
    }

    public final PresentationViewType getSeeMorePresentationViewType() {
        return this.seeMorePresentationViewType;
    }
}
