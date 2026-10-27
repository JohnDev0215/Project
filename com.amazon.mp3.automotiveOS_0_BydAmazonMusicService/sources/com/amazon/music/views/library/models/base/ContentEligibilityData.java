package com.amazon.music.views.library.models.base;

import com.amazon.music.ContentAccessType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Eligible.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000bR\u0019\u0010\f\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\f\u0010\n\u001a\u0004\b\f\u0010\u000bR\u001f\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/views/library/models/base/ContentEligibilityData;", "", "", "toString", "", "hashCode", "other", "", "equals", "isOwned", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "isDownloaded", "", "Lcom/amazon/music/ContentAccessType;", "supportedTiers", "Ljava/util/Set;", "getSupportedTiers", "()Ljava/util/Set;", "<init>", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/Set;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ContentEligibilityData {
    private final Boolean isDownloaded;
    private final Boolean isOwned;
    private final Set<ContentAccessType> supportedTiers;

    public ContentEligibilityData() {
        this(null, null, null, 7, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContentEligibilityData)) {
            return false;
        }
        ContentEligibilityData contentEligibilityData = (ContentEligibilityData) other;
        return Intrinsics.areEqual(this.isOwned, contentEligibilityData.isOwned) && Intrinsics.areEqual(this.isDownloaded, contentEligibilityData.isDownloaded) && Intrinsics.areEqual(this.supportedTiers, contentEligibilityData.supportedTiers);
    }

    public int hashCode() {
        Boolean bool = this.isOwned;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Boolean bool2 = this.isDownloaded;
        int iHashCode2 = (iHashCode + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Set<ContentAccessType> set = this.supportedTiers;
        return iHashCode2 + (set != null ? set.hashCode() : 0);
    }

    public String toString() {
        return "ContentEligibilityData(isOwned=" + this.isOwned + ", isDownloaded=" + this.isDownloaded + ", supportedTiers=" + this.supportedTiers + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ContentEligibilityData(Boolean bool, Boolean bool2, Set<? extends ContentAccessType> set) {
        this.isOwned = bool;
        this.isDownloaded = bool2;
        this.supportedTiers = set;
    }

    public /* synthetic */ ContentEligibilityData(Boolean bool, Boolean bool2, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? null : bool2, (i & 4) != 0 ? null : set);
    }

    /* JADX INFO: renamed from: isOwned, reason: from getter */
    public final Boolean getIsOwned() {
        return this.isOwned;
    }

    /* JADX INFO: renamed from: isDownloaded, reason: from getter */
    public final Boolean getIsDownloaded() {
        return this.isDownloaded;
    }

    public final Set<ContentAccessType> getSupportedTiers() {
        return this.supportedTiers;
    }
}
