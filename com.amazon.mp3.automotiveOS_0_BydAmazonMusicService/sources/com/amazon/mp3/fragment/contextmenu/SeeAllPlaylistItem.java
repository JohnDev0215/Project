package com.amazon.mp3.fragment.contextmenu;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SeeAllPlaylistItem.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0011\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0015\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/SeeAllPlaylistItem;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "luId", "getLuId", "name", "getName", "index", "I", "getIndex", "()I", "isActive", "Z", "()Z", "setActive", "(Z)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZ)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class SeeAllPlaylistItem {
    private final String id;
    private final int index;
    private boolean isActive;
    private final String luId;
    private final String name;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SeeAllPlaylistItem)) {
            return false;
        }
        SeeAllPlaylistItem seeAllPlaylistItem = (SeeAllPlaylistItem) other;
        return Intrinsics.areEqual(this.id, seeAllPlaylistItem.id) && Intrinsics.areEqual(this.luId, seeAllPlaylistItem.luId) && Intrinsics.areEqual(this.name, seeAllPlaylistItem.name) && this.index == seeAllPlaylistItem.index && this.isActive == seeAllPlaylistItem.isActive;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public int hashCode() {
        int iHashCode = ((((((this.id.hashCode() * 31) + this.luId.hashCode()) * 31) + this.name.hashCode()) * 31) + Integer.hashCode(this.index)) * 31;
        boolean z = this.isActive;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "SeeAllPlaylistItem(id=" + this.id + ", luId=" + this.luId + ", name=" + this.name + ", index=" + this.index + ", isActive=" + this.isActive + ')';
    }

    public SeeAllPlaylistItem(String id, String luId, String name, int i, boolean z) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(luId, "luId");
        Intrinsics.checkNotNullParameter(name, "name");
        this.id = id;
        this.luId = luId;
        this.name = name;
        this.index = i;
        this.isActive = z;
    }

    public /* synthetic */ SeeAllPlaylistItem(String str, String str2, String str3, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, i, (i2 & 16) != 0 ? false : z);
    }

    public final String getId() {
        return this.id;
    }

    public final String getLuId() {
        return this.luId;
    }

    public final String getName() {
        return this.name;
    }

    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: isActive, reason: from getter */
    public final boolean getIsActive() {
        return this.isActive;
    }

    public final void setActive(boolean z) {
        this.isActive = z;
    }
}
