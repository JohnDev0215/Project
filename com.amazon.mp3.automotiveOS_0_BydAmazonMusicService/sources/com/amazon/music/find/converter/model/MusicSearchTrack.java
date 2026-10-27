package com.amazon.music.find.converter.model;

import com.amazon.music.search.RelatedPlaylist;
import com.amazon.musicensembleservice.brush.Track;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicSearchTrack.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\u0002\u0010\fJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0014J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u0011\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bHÆ\u0003JR\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u000bHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014¨\u0006#"}, d2 = {"Lcom/amazon/music/find/converter/model/MusicSearchTrack;", "Lcom/amazon/musicensembleservice/brush/Track;", "trackId", "", "enableOrdinal", "", "showOrdinal", "relatedPlaylists", "", "Lcom/amazon/music/search/RelatedPlaylist;", "languageOfPerformances", "", "(Ljava/lang/Long;ZZLjava/util/List;Ljava/util/List;)V", "getEnableOrdinal", "()Z", "getLanguageOfPerformances", "()Ljava/util/List;", "getRelatedPlaylists", "getShowOrdinal", "getTrackId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Long;ZZLjava/util/List;Ljava/util/List;)Lcom/amazon/music/find/converter/model/MusicSearchTrack;", "equals", "other", "", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class MusicSearchTrack extends Track {
    private final boolean enableOrdinal;
    private final List<String> languageOfPerformances;
    private final List<RelatedPlaylist> relatedPlaylists;
    private final boolean showOrdinal;
    private final Long trackId;

    public MusicSearchTrack() {
        this(null, false, false, null, null, 31, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MusicSearchTrack copy$default(MusicSearchTrack musicSearchTrack, Long l, boolean z, boolean z2, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            l = musicSearchTrack.trackId;
        }
        if ((i & 2) != 0) {
            z = musicSearchTrack.enableOrdinal;
        }
        boolean z3 = z;
        if ((i & 4) != 0) {
            z2 = musicSearchTrack.showOrdinal;
        }
        boolean z4 = z2;
        if ((i & 8) != 0) {
            list = musicSearchTrack.relatedPlaylists;
        }
        List list3 = list;
        if ((i & 16) != 0) {
            list2 = musicSearchTrack.languageOfPerformances;
        }
        return musicSearchTrack.copy(l, z3, z4, list3, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Long getTrackId() {
        return this.trackId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public final List<RelatedPlaylist> component4() {
        return this.relatedPlaylists;
    }

    public final List<String> component5() {
        return this.languageOfPerformances;
    }

    public final MusicSearchTrack copy(Long trackId, boolean enableOrdinal, boolean showOrdinal, List<RelatedPlaylist> relatedPlaylists, List<String> languageOfPerformances) {
        return new MusicSearchTrack(trackId, enableOrdinal, showOrdinal, relatedPlaylists, languageOfPerformances);
    }

    @Override // com.amazon.musicensembleservice.brush.Track, com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MusicSearchTrack)) {
            return false;
        }
        MusicSearchTrack musicSearchTrack = (MusicSearchTrack) other;
        return Intrinsics.areEqual(this.trackId, musicSearchTrack.trackId) && this.enableOrdinal == musicSearchTrack.enableOrdinal && this.showOrdinal == musicSearchTrack.showOrdinal && Intrinsics.areEqual(this.relatedPlaylists, musicSearchTrack.relatedPlaylists) && Intrinsics.areEqual(this.languageOfPerformances, musicSearchTrack.languageOfPerformances);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    @Override // com.amazon.musicensembleservice.brush.Track, com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        Long l = this.trackId;
        int iHashCode = (l == null ? 0 : l.hashCode()) * 31;
        boolean z = this.enableOrdinal;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode + r2) * 31;
        boolean z2 = this.showOrdinal;
        int i2 = (i + (z2 ? 1 : z2)) * 31;
        List<RelatedPlaylist> list = this.relatedPlaylists;
        int iHashCode2 = (i2 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.languageOfPerformances;
        return iHashCode2 + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "MusicSearchTrack(trackId=" + this.trackId + ", enableOrdinal=" + this.enableOrdinal + ", showOrdinal=" + this.showOrdinal + ", relatedPlaylists=" + this.relatedPlaylists + ", languageOfPerformances=" + this.languageOfPerformances + ')';
    }

    public /* synthetic */ MusicSearchTrack(Long l, boolean z, boolean z2, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : l, (i & 2) != 0 ? true : z, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? null : list, (i & 16) != 0 ? null : list2);
    }

    public final Long getTrackId() {
        return this.trackId;
    }

    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public final List<RelatedPlaylist> getRelatedPlaylists() {
        return this.relatedPlaylists;
    }

    public final List<String> getLanguageOfPerformances() {
        return this.languageOfPerformances;
    }

    public MusicSearchTrack(Long l, boolean z, boolean z2, List<RelatedPlaylist> list, List<String> list2) {
        this.trackId = l;
        this.enableOrdinal = z;
        this.showOrdinal = z2;
        this.relatedPlaylists = list;
        this.languageOfPerformances = list2;
    }
}
