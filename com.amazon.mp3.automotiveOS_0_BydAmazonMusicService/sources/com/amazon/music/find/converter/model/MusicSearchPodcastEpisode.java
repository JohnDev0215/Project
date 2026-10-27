package com.amazon.music.find.converter.model;

import com.amazon.musicensembleservice.brush.PodcastEpisode;
import java.util.Date;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicSearchPodcastEpisode.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u007f\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u001c\b\u0002\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u0006\u0012\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u0006\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ\u0011\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001d\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u0006HÆ\u0003J\u001d\u0010\u0019\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u0006HÆ\u0003J\u0017\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u000bHÆ\u0003J\t\u0010\u001c\u001a\u00020\u000bHÆ\u0003J\u0083\u0001\u0010\u001d\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u001c\b\u0002\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u00062\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u00062\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t\u0018\u00010\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u000b2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0004HÖ\u0001R\u001f\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R%\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR%\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013¨\u0006$"}, d2 = {"Lcom/amazon/music/find/converter/model/MusicSearchPodcastEpisode;", "Lcom/amazon/musicensembleservice/brush/PodcastEpisode;", "contentTiers", "", "", "podcastEpisodeTierBenefits", "", "podcastShowTierBenefits", "availabilityDisplayDate", "Ljava/util/Date;", "enableOrdinal", "", "showOrdinal", "(Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;ZZ)V", "getAvailabilityDisplayDate", "()Ljava/util/Map;", "getContentTiers", "()Ljava/util/List;", "getEnableOrdinal", "()Z", "getPodcastEpisodeTierBenefits", "getPodcastShowTierBenefits", "getShowOrdinal", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class MusicSearchPodcastEpisode extends PodcastEpisode {
    private final Map<String, Date> availabilityDisplayDate;
    private final List<String> contentTiers;
    private final boolean enableOrdinal;
    private final Map<String, List<String>> podcastEpisodeTierBenefits;
    private final Map<String, List<String>> podcastShowTierBenefits;
    private final boolean showOrdinal;

    public MusicSearchPodcastEpisode() {
        this(null, null, null, null, false, false, 63, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MusicSearchPodcastEpisode copy$default(MusicSearchPodcastEpisode musicSearchPodcastEpisode, List list, Map map, Map map2, Map map3, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = musicSearchPodcastEpisode.contentTiers;
        }
        if ((i & 2) != 0) {
            map = musicSearchPodcastEpisode.podcastEpisodeTierBenefits;
        }
        Map map4 = map;
        if ((i & 4) != 0) {
            map2 = musicSearchPodcastEpisode.podcastShowTierBenefits;
        }
        Map map5 = map2;
        if ((i & 8) != 0) {
            map3 = musicSearchPodcastEpisode.availabilityDisplayDate;
        }
        Map map6 = map3;
        if ((i & 16) != 0) {
            z = musicSearchPodcastEpisode.enableOrdinal;
        }
        boolean z3 = z;
        if ((i & 32) != 0) {
            z2 = musicSearchPodcastEpisode.showOrdinal;
        }
        return musicSearchPodcastEpisode.copy(list, map4, map5, map6, z3, z2);
    }

    public final List<String> component1() {
        return this.contentTiers;
    }

    public final Map<String, List<String>> component2() {
        return this.podcastEpisodeTierBenefits;
    }

    public final Map<String, List<String>> component3() {
        return this.podcastShowTierBenefits;
    }

    public final Map<String, Date> component4() {
        return this.availabilityDisplayDate;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    public final MusicSearchPodcastEpisode copy(List<String> contentTiers, Map<String, ? extends List<String>> podcastEpisodeTierBenefits, Map<String, ? extends List<String>> podcastShowTierBenefits, Map<String, ? extends Date> availabilityDisplayDate, boolean enableOrdinal, boolean showOrdinal) {
        return new MusicSearchPodcastEpisode(contentTiers, podcastEpisodeTierBenefits, podcastShowTierBenefits, availabilityDisplayDate, enableOrdinal, showOrdinal);
    }

    @Override // com.amazon.musicensembleservice.brush.PodcastEpisode, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MusicSearchPodcastEpisode)) {
            return false;
        }
        MusicSearchPodcastEpisode musicSearchPodcastEpisode = (MusicSearchPodcastEpisode) other;
        return Intrinsics.areEqual(this.contentTiers, musicSearchPodcastEpisode.contentTiers) && Intrinsics.areEqual(this.podcastEpisodeTierBenefits, musicSearchPodcastEpisode.podcastEpisodeTierBenefits) && Intrinsics.areEqual(this.podcastShowTierBenefits, musicSearchPodcastEpisode.podcastShowTierBenefits) && Intrinsics.areEqual(this.availabilityDisplayDate, musicSearchPodcastEpisode.availabilityDisplayDate) && this.enableOrdinal == musicSearchPodcastEpisode.enableOrdinal && this.showOrdinal == musicSearchPodcastEpisode.showOrdinal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [int] */
    /* JADX WARN: Type inference failed for: r2v9 */
    @Override // com.amazon.musicensembleservice.brush.PodcastEpisode, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        List<String> list = this.contentTiers;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        Map<String, List<String>> map = this.podcastEpisodeTierBenefits;
        int iHashCode2 = (iHashCode + (map == null ? 0 : map.hashCode())) * 31;
        Map<String, List<String>> map2 = this.podcastShowTierBenefits;
        int iHashCode3 = (iHashCode2 + (map2 == null ? 0 : map2.hashCode())) * 31;
        Map<String, Date> map3 = this.availabilityDisplayDate;
        int iHashCode4 = (iHashCode3 + (map3 != null ? map3.hashCode() : 0)) * 31;
        boolean z = this.enableOrdinal;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode4 + r1) * 31;
        boolean z2 = this.showOrdinal;
        return i + (z2 ? 1 : z2);
    }

    public String toString() {
        return "MusicSearchPodcastEpisode(contentTiers=" + this.contentTiers + ", podcastEpisodeTierBenefits=" + this.podcastEpisodeTierBenefits + ", podcastShowTierBenefits=" + this.podcastShowTierBenefits + ", availabilityDisplayDate=" + this.availabilityDisplayDate + ", enableOrdinal=" + this.enableOrdinal + ", showOrdinal=" + this.showOrdinal + ')';
    }

    public /* synthetic */ MusicSearchPodcastEpisode(List list, Map map, Map map2, Map map3, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, (i & 2) != 0 ? MapsKt__MapsKt.emptyMap() : map, (i & 4) != 0 ? MapsKt__MapsKt.emptyMap() : map2, (i & 8) != 0 ? MapsKt__MapsKt.emptyMap() : map3, (i & 16) != 0 ? true : z, (i & 32) != 0 ? false : z2);
    }

    public final List<String> getContentTiers() {
        return this.contentTiers;
    }

    public final Map<String, List<String>> getPodcastEpisodeTierBenefits() {
        return this.podcastEpisodeTierBenefits;
    }

    public final Map<String, List<String>> getPodcastShowTierBenefits() {
        return this.podcastShowTierBenefits;
    }

    public final Map<String, Date> getAvailabilityDisplayDate() {
        return this.availabilityDisplayDate;
    }

    public final boolean getEnableOrdinal() {
        return this.enableOrdinal;
    }

    public final boolean getShowOrdinal() {
        return this.showOrdinal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MusicSearchPodcastEpisode(List<String> list, Map<String, ? extends List<String>> map, Map<String, ? extends List<String>> map2, Map<String, ? extends Date> map3, boolean z, boolean z2) {
        this.contentTiers = list;
        this.podcastEpisodeTierBenefits = map;
        this.podcastShowTierBenefits = map2;
        this.availabilityDisplayDate = map3;
        this.enableOrdinal = z;
        this.showOrdinal = z2;
    }
}
