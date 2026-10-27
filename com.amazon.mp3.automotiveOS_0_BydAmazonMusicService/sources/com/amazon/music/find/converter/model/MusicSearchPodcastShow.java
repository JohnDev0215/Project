package com.amazon.music.find.converter.model;

import com.amazon.musicensembleservice.brush.PodcastShow;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicSearchPodcastShow.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u001c\b\u0002\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001d\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003JM\u0010\u0016\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u001c\b\u0002\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR%\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/find/converter/model/MusicSearchPodcastShow;", "Lcom/amazon/musicensembleservice/brush/PodcastShow;", "contentTiers", "", "", "tierBenefits", "", "enableOrdinal", "", "showOrdinal", "(Ljava/util/List;Ljava/util/Map;ZZ)V", "getContentTiers", "()Ljava/util/List;", "getEnableOrdinal", "()Z", "getShowOrdinal", "getTierBenefits", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class MusicSearchPodcastShow extends PodcastShow {
    private final List<String> contentTiers;
    private final boolean enableOrdinal;
    private final boolean showOrdinal;
    private final Map<String, List<String>> tierBenefits;

    public MusicSearchPodcastShow() {
        this(null, null, false, false, 15, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MusicSearchPodcastShow copy$default(MusicSearchPodcastShow musicSearchPodcastShow, List list, Map map, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = musicSearchPodcastShow.contentTiers;
        }
        if ((i & 2) != 0) {
            map = musicSearchPodcastShow.tierBenefits;
        }
        if ((i & 4) != 0) {
            z = musicSearchPodcastShow.enableOrdinal;
        }
        if ((i & 8) != 0) {
            z2 = musicSearchPodcastShow.showOrdinal;
        }
        return musicSearchPodcastShow.copy(list, map, z, z2);
    }

    public final List<String> component1() {
        return this.contentTiers;
    }

    public final Map<String, List<String>> component2() {
        return this.tierBenefits;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public final MusicSearchPodcastShow copy(List<String> contentTiers, Map<String, ? extends List<String>> tierBenefits, boolean enableOrdinal, boolean showOrdinal) {
        return new MusicSearchPodcastShow(contentTiers, tierBenefits, enableOrdinal, showOrdinal);
    }

    @Override // com.amazon.musicensembleservice.brush.PodcastShow, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MusicSearchPodcastShow)) {
            return false;
        }
        MusicSearchPodcastShow musicSearchPodcastShow = (MusicSearchPodcastShow) other;
        return Intrinsics.areEqual(this.contentTiers, musicSearchPodcastShow.contentTiers) && Intrinsics.areEqual(this.tierBenefits, musicSearchPodcastShow.tierBenefits) && this.enableOrdinal == musicSearchPodcastShow.enableOrdinal && this.showOrdinal == musicSearchPodcastShow.showOrdinal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3 */
    @Override // com.amazon.musicensembleservice.brush.PodcastShow, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        List<String> list = this.contentTiers;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        Map<String, List<String>> map = this.tierBenefits;
        int iHashCode2 = (iHashCode + (map != null ? map.hashCode() : 0)) * 31;
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
        return "MusicSearchPodcastShow(contentTiers=" + this.contentTiers + ", tierBenefits=" + this.tierBenefits + ", enableOrdinal=" + this.enableOrdinal + ", showOrdinal=" + this.showOrdinal + ')';
    }

    public /* synthetic */ MusicSearchPodcastShow(List list, Map map, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i & 2) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i & 4) != 0 ? true : z, (i & 8) != 0 ? false : z2);
    }

    public final List<String> getContentTiers() {
        return this.contentTiers;
    }

    public final Map<String, List<String>> getTierBenefits() {
        return this.tierBenefits;
    }

    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MusicSearchPodcastShow(List<String> list, Map<String, ? extends List<String>> map, boolean z, boolean z2) {
        this.contentTiers = list;
        this.tierBenefits = map;
        this.enableOrdinal = z;
        this.showOrdinal = z2;
    }
}
