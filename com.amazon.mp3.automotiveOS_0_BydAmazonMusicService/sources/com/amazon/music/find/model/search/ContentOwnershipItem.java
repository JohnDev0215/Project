package com.amazon.music.find.model.search;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentOwnershipItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ2\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "", "asin", "", "id", "", "ownershipStatus", "", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Integer;)V", "getAsin", "()Ljava/lang/String;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getOwnershipStatus", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Integer;)Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "equals", "", "other", "hashCode", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class ContentOwnershipItem {
    private final String asin;
    private final Long id;
    private final Integer ownershipStatus;

    public static /* synthetic */ ContentOwnershipItem copy$default(ContentOwnershipItem contentOwnershipItem, String str, Long l, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = contentOwnershipItem.asin;
        }
        if ((i & 2) != 0) {
            l = contentOwnershipItem.id;
        }
        if ((i & 4) != 0) {
            num = contentOwnershipItem.ownershipStatus;
        }
        return contentOwnershipItem.copy(str, l, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAsin() {
        return this.asin;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getOwnershipStatus() {
        return this.ownershipStatus;
    }

    public final ContentOwnershipItem copy(String asin, Long id, Integer ownershipStatus) {
        return new ContentOwnershipItem(asin, id, ownershipStatus);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContentOwnershipItem)) {
            return false;
        }
        ContentOwnershipItem contentOwnershipItem = (ContentOwnershipItem) other;
        return Intrinsics.areEqual(this.asin, contentOwnershipItem.asin) && Intrinsics.areEqual(this.id, contentOwnershipItem.id) && Intrinsics.areEqual(this.ownershipStatus, contentOwnershipItem.ownershipStatus);
    }

    public int hashCode() {
        String str = this.asin;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Long l = this.id;
        int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
        Integer num = this.ownershipStatus;
        return iHashCode2 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "ContentOwnershipItem(asin=" + ((Object) this.asin) + ", id=" + this.id + ", ownershipStatus=" + this.ownershipStatus + ')';
    }

    public ContentOwnershipItem(String str, Long l, Integer num) {
        this.asin = str;
        this.id = l;
        this.ownershipStatus = num;
    }

    public final String getAsin() {
        return this.asin;
    }

    public final Long getId() {
        return this.id;
    }

    public final Integer getOwnershipStatus() {
        return this.ownershipStatus;
    }
}
