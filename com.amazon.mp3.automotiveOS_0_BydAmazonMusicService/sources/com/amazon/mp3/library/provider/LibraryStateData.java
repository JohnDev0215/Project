package com.amazon.mp3.library.provider;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentModelLibraryStateManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000bR\u0019\u0010\f\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\f\u0010\n\u001a\u0004\b\f\u0010\u000bR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/library/provider/LibraryStateData;", "", "", "toString", "", "hashCode", "other", "", "equals", "isOwned", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "isInLibrary", "downloadState", "Ljava/lang/Integer;", "getDownloadState", "()Ljava/lang/Integer;", "<init>", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LibraryStateData {
    private final Integer downloadState;
    private final Boolean isInLibrary;
    private final Boolean isOwned;

    public LibraryStateData() {
        this(null, null, null, 7, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LibraryStateData)) {
            return false;
        }
        LibraryStateData libraryStateData = (LibraryStateData) other;
        return Intrinsics.areEqual(this.isOwned, libraryStateData.isOwned) && Intrinsics.areEqual(this.isInLibrary, libraryStateData.isInLibrary) && Intrinsics.areEqual(this.downloadState, libraryStateData.downloadState);
    }

    public int hashCode() {
        Boolean bool = this.isOwned;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Boolean bool2 = this.isInLibrary;
        int iHashCode2 = (iHashCode + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Integer num = this.downloadState;
        return iHashCode2 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "LibraryStateData(isOwned=" + this.isOwned + ", isInLibrary=" + this.isInLibrary + ", downloadState=" + this.downloadState + ')';
    }

    public LibraryStateData(Boolean bool, Boolean bool2, Integer num) {
        this.isOwned = bool;
        this.isInLibrary = bool2;
        this.downloadState = num;
    }

    public /* synthetic */ LibraryStateData(Boolean bool, Boolean bool2, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? null : bool2, (i & 4) != 0 ? null : num);
    }

    /* JADX INFO: renamed from: isOwned, reason: from getter */
    public final Boolean getIsOwned() {
        return this.isOwned;
    }

    /* JADX INFO: renamed from: isInLibrary, reason: from getter */
    public final Boolean getIsInLibrary() {
        return this.isInLibrary;
    }

    public final Integer getDownloadState() {
        return this.downloadState;
    }
}
