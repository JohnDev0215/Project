package com.amazon.music.find.converter.model;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.musicensembleservice.brush.Artist;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicSearchArtist.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J9\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/find/converter/model/MusicSearchArtist;", "Lcom/amazon/musicensembleservice/brush/Artist;", "contentTiers", "", "", PageUriUtils.SOURCE_QUERY_PARAM, "enableOrdinal", "", "showOrdinal", "(Ljava/util/List;Ljava/lang/String;ZZ)V", "getContentTiers", "()Ljava/util/List;", "getEnableOrdinal", "()Z", "getShowOrdinal", "getSource", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class MusicSearchArtist extends Artist {
    private final List<String> contentTiers;
    private final boolean enableOrdinal;
    private final boolean showOrdinal;
    private final String source;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MusicSearchArtist copy$default(MusicSearchArtist musicSearchArtist, List list, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = musicSearchArtist.contentTiers;
        }
        if ((i & 2) != 0) {
            str = musicSearchArtist.source;
        }
        if ((i & 4) != 0) {
            z = musicSearchArtist.enableOrdinal;
        }
        if ((i & 8) != 0) {
            z2 = musicSearchArtist.showOrdinal;
        }
        return musicSearchArtist.copy(list, str, z, z2);
    }

    public final List<String> component1() {
        return this.contentTiers;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public final MusicSearchArtist copy(List<String> contentTiers, String source, boolean enableOrdinal, boolean showOrdinal) {
        Intrinsics.checkNotNullParameter(contentTiers, "contentTiers");
        return new MusicSearchArtist(contentTiers, source, enableOrdinal, showOrdinal);
    }

    @Override // com.amazon.musicensembleservice.brush.Artist, com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MusicSearchArtist)) {
            return false;
        }
        MusicSearchArtist musicSearchArtist = (MusicSearchArtist) other;
        return Intrinsics.areEqual(this.contentTiers, musicSearchArtist.contentTiers) && Intrinsics.areEqual(this.source, musicSearchArtist.source) && this.enableOrdinal == musicSearchArtist.enableOrdinal && this.showOrdinal == musicSearchArtist.showOrdinal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v2 */
    @Override // com.amazon.musicensembleservice.brush.Artist, com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        int iHashCode = this.contentTiers.hashCode() * 31;
        String str = this.source;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        boolean z = this.enableOrdinal;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode2 + r1) * 31;
        boolean z2 = this.showOrdinal;
        return i + (z2 ? 1 : z2);
    }

    public String toString() {
        return "MusicSearchArtist(contentTiers=" + this.contentTiers + ", source=" + ((Object) this.source) + ", enableOrdinal=" + this.enableOrdinal + ", showOrdinal=" + this.showOrdinal + ')';
    }

    public /* synthetic */ MusicSearchArtist(List list, String str, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, str, (i & 4) != 0 ? true : z, (i & 8) != 0 ? false : z2);
    }

    public final List<String> getContentTiers() {
        return this.contentTiers;
    }

    public final String getSource() {
        return this.source;
    }

    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public MusicSearchArtist(List<String> contentTiers, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(contentTiers, "contentTiers");
        this.contentTiers = contentTiers;
        this.source = str;
        this.enableOrdinal = z;
        this.showOrdinal = z2;
    }
}
