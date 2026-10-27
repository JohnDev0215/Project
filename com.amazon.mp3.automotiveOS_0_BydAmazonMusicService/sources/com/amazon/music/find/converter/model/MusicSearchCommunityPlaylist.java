package com.amazon.music.find.converter.model;

import com.amazon.musicensembleservice.brush.CommunityPlaylist;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicSearchCommunityPlaylist.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0003J/\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/find/converter/model/MusicSearchCommunityPlaylist;", "Lcom/amazon/musicensembleservice/brush/CommunityPlaylist;", "enableOrdinal", "", "showOrdinal", "contentTiers", "", "", "(ZZLjava/util/List;)V", "getContentTiers", "()Ljava/util/List;", "setContentTiers", "(Ljava/util/List;)V", "getEnableOrdinal", "()Z", "getShowOrdinal", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class MusicSearchCommunityPlaylist extends CommunityPlaylist {
    private List<String> contentTiers;
    private final boolean enableOrdinal;
    private final boolean showOrdinal;

    public MusicSearchCommunityPlaylist() {
        this(false, false, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MusicSearchCommunityPlaylist copy$default(MusicSearchCommunityPlaylist musicSearchCommunityPlaylist, boolean z, boolean z2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            z = musicSearchCommunityPlaylist.enableOrdinal;
        }
        if ((i & 2) != 0) {
            z2 = musicSearchCommunityPlaylist.showOrdinal;
        }
        if ((i & 4) != 0) {
            list = musicSearchCommunityPlaylist.contentTiers;
        }
        return musicSearchCommunityPlaylist.copy(z, z2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public final List<String> component3() {
        return this.contentTiers;
    }

    public final MusicSearchCommunityPlaylist copy(boolean enableOrdinal, boolean showOrdinal, List<String> contentTiers) {
        return new MusicSearchCommunityPlaylist(enableOrdinal, showOrdinal, contentTiers);
    }

    @Override // com.amazon.musicensembleservice.brush.CommunityPlaylist, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MusicSearchCommunityPlaylist)) {
            return false;
        }
        MusicSearchCommunityPlaylist musicSearchCommunityPlaylist = (MusicSearchCommunityPlaylist) other;
        return this.enableOrdinal == musicSearchCommunityPlaylist.enableOrdinal && this.showOrdinal == musicSearchCommunityPlaylist.showOrdinal && Intrinsics.areEqual(this.contentTiers, musicSearchCommunityPlaylist.contentTiers);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    @Override // com.amazon.musicensembleservice.brush.CommunityPlaylist, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        boolean z = this.enableOrdinal;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.showOrdinal;
        int i2 = (i + (z2 ? 1 : z2)) * 31;
        List<String> list = this.contentTiers;
        return i2 + (list == null ? 0 : list.hashCode());
    }

    public String toString() {
        return "MusicSearchCommunityPlaylist(enableOrdinal=" + this.enableOrdinal + ", showOrdinal=" + this.showOrdinal + ", contentTiers=" + this.contentTiers + ')';
    }

    public /* synthetic */ MusicSearchCommunityPlaylist(boolean z, boolean z2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? null : list);
    }

    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public final List<String> getContentTiers() {
        return this.contentTiers;
    }

    public final void setContentTiers(List<String> list) {
        this.contentTiers = list;
    }

    public MusicSearchCommunityPlaylist(boolean z, boolean z2, List<String> list) {
        this.enableOrdinal = z;
        this.showOrdinal = z2;
        this.contentTiers = list;
    }
}
