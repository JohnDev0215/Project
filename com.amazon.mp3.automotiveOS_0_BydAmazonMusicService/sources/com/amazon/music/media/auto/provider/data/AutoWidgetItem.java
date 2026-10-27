package com.amazon.music.media.auto.provider.data;

import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.ui.PresentationViewType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoWidgetItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u0015\u001a\u00020\u0004\u0012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\u0004\b\u001f\u0010 J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0012\u0010\u0014R\u0017\u0010\u0015\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e¨\u0006!"}, d2 = {"Lcom/amazon/music/media/auto/provider/data/AutoWidgetItem;", "", "", "toString", "", "hashCode", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "Lcom/amazon/music/media/auto/ui/PresentationViewType;", "secondaryPresentationViewType", "Lcom/amazon/music/media/auto/ui/PresentationViewType;", "getSecondaryPresentationViewType", "()Lcom/amazon/music/media/auto/ui/PresentationViewType;", "isBrowsable", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "initialEntityCount", "I", "getInitialEntityCount", "()I", "", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "entities", "Ljava/util/List;", "getEntities", "()Ljava/util/List;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/media/auto/ui/PresentationViewType;Ljava/lang/Boolean;ILjava/util/List;)V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AutoWidgetItem {
    private final List<AutoMediaItem> entities;
    private final int initialEntityCount;
    private final Boolean isBrowsable;
    private final PresentationViewType secondaryPresentationViewType;
    private final String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutoWidgetItem)) {
            return false;
        }
        AutoWidgetItem autoWidgetItem = (AutoWidgetItem) other;
        return Intrinsics.areEqual(this.title, autoWidgetItem.title) && this.secondaryPresentationViewType == autoWidgetItem.secondaryPresentationViewType && Intrinsics.areEqual(this.isBrowsable, autoWidgetItem.isBrowsable) && this.initialEntityCount == autoWidgetItem.initialEntityCount && Intrinsics.areEqual(this.entities, autoWidgetItem.entities);
    }

    public int hashCode() {
        int iHashCode = ((this.title.hashCode() * 31) + this.secondaryPresentationViewType.hashCode()) * 31;
        Boolean bool = this.isBrowsable;
        return ((((iHashCode + (bool == null ? 0 : bool.hashCode())) * 31) + Integer.hashCode(this.initialEntityCount)) * 31) + this.entities.hashCode();
    }

    public String toString() {
        return "AutoWidgetItem(title=" + this.title + ", secondaryPresentationViewType=" + this.secondaryPresentationViewType + ", isBrowsable=" + this.isBrowsable + ", initialEntityCount=" + this.initialEntityCount + ", entities=" + this.entities + ')';
    }

    public AutoWidgetItem(String title, PresentationViewType secondaryPresentationViewType, Boolean bool, int i, List<AutoMediaItem> entities) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(secondaryPresentationViewType, "secondaryPresentationViewType");
        Intrinsics.checkNotNullParameter(entities, "entities");
        this.title = title;
        this.secondaryPresentationViewType = secondaryPresentationViewType;
        this.isBrowsable = bool;
        this.initialEntityCount = i;
        this.entities = entities;
    }

    public final String getTitle() {
        return this.title;
    }

    public final PresentationViewType getSecondaryPresentationViewType() {
        return this.secondaryPresentationViewType;
    }

    /* JADX INFO: renamed from: isBrowsable, reason: from getter */
    public final Boolean getIsBrowsable() {
        return this.isBrowsable;
    }

    public final int getInitialEntityCount() {
        return this.initialEntityCount;
    }

    public final List<AutoMediaItem> getEntities() {
        return this.entities;
    }
}
