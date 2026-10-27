package com.amazon.music.find.model;

import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentMetadataPlaybackState.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J#\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/find/model/ContentMetadataPlaybackState;", "", "contentMetadataList", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "positionToStart", "", "(Ljava/util/List;I)V", "getContentMetadataList", "()Ljava/util/List;", "setContentMetadataList", "(Ljava/util/List;)V", "getPositionToStart", "()I", "setPositionToStart", "(I)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class ContentMetadataPlaybackState {
    private List<? extends ContentMetadata> contentMetadataList;
    private int positionToStart;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ContentMetadataPlaybackState copy$default(ContentMetadataPlaybackState contentMetadataPlaybackState, List list, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = contentMetadataPlaybackState.contentMetadataList;
        }
        if ((i2 & 2) != 0) {
            i = contentMetadataPlaybackState.positionToStart;
        }
        return contentMetadataPlaybackState.copy(list, i);
    }

    public final List<ContentMetadata> component1() {
        return this.contentMetadataList;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getPositionToStart() {
        return this.positionToStart;
    }

    public final ContentMetadataPlaybackState copy(List<? extends ContentMetadata> contentMetadataList, int positionToStart) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        return new ContentMetadataPlaybackState(contentMetadataList, positionToStart);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContentMetadataPlaybackState)) {
            return false;
        }
        ContentMetadataPlaybackState contentMetadataPlaybackState = (ContentMetadataPlaybackState) other;
        return Intrinsics.areEqual(this.contentMetadataList, contentMetadataPlaybackState.contentMetadataList) && this.positionToStart == contentMetadataPlaybackState.positionToStart;
    }

    public int hashCode() {
        return (this.contentMetadataList.hashCode() * 31) + Integer.hashCode(this.positionToStart);
    }

    public String toString() {
        return "ContentMetadataPlaybackState(contentMetadataList=" + this.contentMetadataList + ", positionToStart=" + this.positionToStart + ')';
    }

    public ContentMetadataPlaybackState(List<? extends ContentMetadata> contentMetadataList, int i) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        this.contentMetadataList = contentMetadataList;
        this.positionToStart = i;
    }

    public final List<ContentMetadata> getContentMetadataList() {
        return this.contentMetadataList;
    }

    public final void setContentMetadataList(List<? extends ContentMetadata> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.contentMetadataList = list;
    }

    public final int getPositionToStart() {
        return this.positionToStart;
    }

    public final void setPositionToStart(int i) {
        this.positionToStart = i;
    }
}
