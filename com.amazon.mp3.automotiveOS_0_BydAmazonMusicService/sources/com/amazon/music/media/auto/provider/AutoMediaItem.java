package com.amazon.music.media.auto.provider;

import com.amazon.music.views.library.metadata.ContentMetadata;
import com.google.android.gms.cast.MediaTrack;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoMediaItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0004\u0012\u0010\b\u0002\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001c\u0012\b\b\u0002\u0010!\u001a\u00020\u0004\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b(\u0010)J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\n\u001a\u0004\b\u0017\u0010\fR\u0017\u0010\u0018\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001f\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010!\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b!\u0010\u0019\u001a\u0004\b\"\u0010\u001bR\u0019\u0010#\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b#\u0010\n\u001a\u0004\b$\u0010\fR\u0019\u0010%\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b%\u0010'¨\u0006*"}, d2 = {"Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "", "", "toString", "", "hashCode", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "subTitle", "getSubTitle", MediaTrack.ROLE_DESCRIPTION, "getDescription", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getMetadata", "()Lcom/amazon/music/views/library/metadata/ContentMetadata;", "blockRef", "getBlockRef", "contentType", "I", "getContentType", "()I", "", "metadataList", "Ljava/util/List;", "getMetadataList", "()Ljava/util/List;", "trackIndex", "getTrackIndex", "blockTitle", "getBlockTitle", "isBrowsable", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/String;ILjava/util/List;ILjava/lang/String;Ljava/lang/Boolean;)V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AutoMediaItem {
    private final String blockRef;
    private final String blockTitle;
    private final int contentType;
    private final String description;
    private final Boolean isBrowsable;
    private final ContentMetadata metadata;
    private final List<ContentMetadata> metadataList;
    private final String subTitle;
    private final String title;
    private final int trackIndex;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutoMediaItem)) {
            return false;
        }
        AutoMediaItem autoMediaItem = (AutoMediaItem) other;
        return Intrinsics.areEqual(this.title, autoMediaItem.title) && Intrinsics.areEqual(this.subTitle, autoMediaItem.subTitle) && Intrinsics.areEqual(this.description, autoMediaItem.description) && Intrinsics.areEqual(this.metadata, autoMediaItem.metadata) && Intrinsics.areEqual(this.blockRef, autoMediaItem.blockRef) && this.contentType == autoMediaItem.contentType && Intrinsics.areEqual(this.metadataList, autoMediaItem.metadataList) && this.trackIndex == autoMediaItem.trackIndex && Intrinsics.areEqual(this.blockTitle, autoMediaItem.blockTitle) && Intrinsics.areEqual(this.isBrowsable, autoMediaItem.isBrowsable);
    }

    public int hashCode() {
        int iHashCode = this.title.hashCode() * 31;
        String str = this.subTitle;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.description;
        int iHashCode3 = (((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.metadata.hashCode()) * 31;
        String str3 = this.blockRef;
        int iHashCode4 = (((iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + Integer.hashCode(this.contentType)) * 31;
        List<ContentMetadata> list = this.metadataList;
        int iHashCode5 = (((iHashCode4 + (list == null ? 0 : list.hashCode())) * 31) + Integer.hashCode(this.trackIndex)) * 31;
        String str4 = this.blockTitle;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Boolean bool = this.isBrowsable;
        return iHashCode6 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "AutoMediaItem(title=" + this.title + ", subTitle=" + ((Object) this.subTitle) + ", description=" + ((Object) this.description) + ", metadata=" + this.metadata + ", blockRef=" + ((Object) this.blockRef) + ", contentType=" + this.contentType + ", metadataList=" + this.metadataList + ", trackIndex=" + this.trackIndex + ", blockTitle=" + ((Object) this.blockTitle) + ", isBrowsable=" + this.isBrowsable + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AutoMediaItem(String title, String str, String str2, ContentMetadata metadata, String str3, int i, List<? extends ContentMetadata> list, int i2, String str4, Boolean bool) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.title = title;
        this.subTitle = str;
        this.description = str2;
        this.metadata = metadata;
        this.blockRef = str3;
        this.contentType = i;
        this.metadataList = list;
        this.trackIndex = i2;
        this.blockTitle = str4;
        this.isBrowsable = bool;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final ContentMetadata getMetadata() {
        return this.metadata;
    }

    public final int getContentType() {
        return this.contentType;
    }

    public final List<ContentMetadata> getMetadataList() {
        return this.metadataList;
    }

    public final int getTrackIndex() {
        return this.trackIndex;
    }

    public /* synthetic */ AutoMediaItem(String str, String str2, String str3, ContentMetadata contentMetadata, String str4, int i, List list, int i2, String str5, Boolean bool, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? null : str3, contentMetadata, (i3 & 16) != 0 ? null : str4, i, (i3 & 64) != 0 ? null : list, (i3 & 128) != 0 ? 0 : i2, (i3 & 256) != 0 ? null : str5, (i3 & 512) != 0 ? Boolean.FALSE : bool);
    }

    /* JADX INFO: renamed from: isBrowsable, reason: from getter */
    public final Boolean getIsBrowsable() {
        return this.isBrowsable;
    }
}
