package com.amazon.music.find.model.search;

import com.amazon.music.ContentAccessType;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.google.android.gms.cast.MediaTrack;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PodcastEpisodeSearchItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b:\b&\u0018\u00002\u00020\u0001B\u009f\u0002\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013\u0012\u001c\b\u0002\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0016\u0018\u00010\u0006\u0012\u001c\b\u0002\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0016\u0018\u00010\u0006\u0012\u0016\b\u0002\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\u0002\u0010\u001dR\u0018\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0016\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\"R(\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\"\"\u0004\b+\u0010$R\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R(\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010'\"\u0004\b1\u0010)R(\u00103\u001a\u0004\u0018\u00010\n2\b\u00102\u001a\u0004\u0018\u00010\n8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b4\u0010\u001f\"\u0004\b5\u00106R\u0014\u00107\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u001a\u0010\u000e\u001a\u00020\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u00108\"\u0004\b9\u0010:R\u0014\u0010;\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b;\u00108R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\"\"\u0004\bA\u0010$R.\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0016\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010'\"\u0004\bC\u0010)R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\"\"\u0004\bE\u0010$R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\"\"\u0004\bG\u0010$R.\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0016\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010'\"\u0004\bI\u0010)R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\"\"\u0004\bK\u0010$R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u0016\u0010P\u001a\u0004\u0018\u00010\u0003X\u0096D¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010\"R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\"\"\u0004\bS\u0010$R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010\"\"\u0004\bU\u0010$¨\u0006V"}, d2 = {"Lcom/amazon/music/find/model/search/PodcastEpisodeSearchItem;", "Lcom/amazon/music/find/model/search/SearchPodcastEpisode;", "title", "", "artworkUri", "contentInfoMap", "", "blockRef", "asin", "artworkId", "", "podcastEpisodeId", "podcastShowId", MediaTrack.ROLE_SUBTITLE, BrushV2Converter.KEY_IS_EXPLICIT, "", "podcastEpisodeVariantId", "podcastShowVariantId", "contentAccessSet", "", "Lcom/amazon/music/ContentAccessType;", "podcastEpisodeTierBenefits", "", "podcastShowTierBenefits", "availabilityDate", "Ljava/util/Date;", "publishDate", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/util/Set;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Date;Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "getArtworkId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getArtworkUri", "()Ljava/lang/String;", "setArtworkUri", "(Ljava/lang/String;)V", "getAsin", "getAvailabilityDate", "()Ljava/util/Map;", "setAvailabilityDate", "(Ljava/util/Map;)V", "getBlockRef", "setBlockRef", "getContentAccessSet", "()Ljava/util/Set;", "setContentAccessSet", "(Ljava/util/Set;)V", "getContentInfoMap", "setContentInfoMap", "value", "id", "getId", "setId", "(Ljava/lang/Long;)V", "isAvailable", "()Z", "setExplicit", "(Z)V", "isInLibrary", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "setPlaymodeEligibility", "(Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "getPodcastEpisodeId", "setPodcastEpisodeId", "getPodcastEpisodeTierBenefits", "setPodcastEpisodeTierBenefits", "getPodcastEpisodeVariantId", "setPodcastEpisodeVariantId", "getPodcastShowId", "setPodcastShowId", "getPodcastShowTierBenefits", "setPodcastShowTierBenefits", "getPodcastShowVariantId", "setPodcastShowVariantId", "getPublishDate", "()Ljava/util/Date;", "setPublishDate", "(Ljava/util/Date;)V", PageUriUtils.SOURCE_QUERY_PARAM, "getSource", "getSubtitle", "setSubtitle", "getTitle", "setTitle", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class PodcastEpisodeSearchItem implements SearchPodcastEpisode {
    private final Long artworkId;
    private String artworkUri;
    private final String asin;
    private Map<String, ? extends Date> availabilityDate;
    private String blockRef;
    private Set<? extends ContentAccessType> contentAccessSet;
    private Map<String, String> contentInfoMap;
    private final boolean isAvailable;
    private boolean isExplicit;
    private final boolean isInLibrary;
    private PlaymodeEligibility playmodeEligibility;
    private String podcastEpisodeId;
    private Map<String, ? extends List<String>> podcastEpisodeTierBenefits;
    private String podcastEpisodeVariantId;
    private String podcastShowId;
    private Map<String, ? extends List<String>> podcastShowTierBenefits;
    private String podcastShowVariantId;
    private Date publishDate;
    private final String source;
    private String subtitle;
    private String title;

    @JvmOverloads
    public PodcastEpisodeSearchItem() {
        this(null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, 262143, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str) {
        this(str, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, 262142, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2) {
        this(str, str2, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, 262140, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map) {
        this(str, str2, map, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, 262136, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3) {
        this(str, str2, map, str3, null, null, null, null, null, false, null, null, null, null, null, null, null, null, 262128, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4) {
        this(str, str2, map, str3, str4, null, null, null, null, false, null, null, null, null, null, null, null, null, 262112, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l) {
        this(str, str2, map, str3, str4, l, null, null, null, false, null, null, null, null, null, null, null, null, 262080, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5) {
        this(str, str2, map, str3, str4, l, str5, null, null, false, null, null, null, null, null, null, null, null, 262016, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6) {
        this(str, str2, map, str3, str4, l, str5, str6, null, false, null, null, null, null, null, null, null, null, 261888, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, String str7) {
        this(str, str2, map, str3, str4, l, str5, str6, str7, false, null, null, null, null, null, null, null, null, 261632, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, String str7, boolean z) {
        this(str, str2, map, str3, str4, l, str5, str6, str7, z, null, null, null, null, null, null, null, null, 261120, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, String str7, boolean z, String str8) {
        this(str, str2, map, str3, str4, l, str5, str6, str7, z, str8, null, null, null, null, null, null, null, 260096, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, String str7, boolean z, String str8, String str9) {
        this(str, str2, map, str3, str4, l, str5, str6, str7, z, str8, str9, null, null, null, null, null, null, 258048, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, String str7, boolean z, String str8, String str9, Set<? extends ContentAccessType> set) {
        this(str, str2, map, str3, str4, l, str5, str6, str7, z, str8, str9, set, null, null, null, null, null, 253952, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, String str7, boolean z, String str8, String str9, Set<? extends ContentAccessType> set, Map<String, ? extends List<String>> map2) {
        this(str, str2, map, str3, str4, l, str5, str6, str7, z, str8, str9, set, map2, null, null, null, null, 245760, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, String str7, boolean z, String str8, String str9, Set<? extends ContentAccessType> set, Map<String, ? extends List<String>> map2, Map<String, ? extends List<String>> map3) {
        this(str, str2, map, str3, str4, l, str5, str6, str7, z, str8, str9, set, map2, map3, null, null, null, 229376, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, String str7, boolean z, String str8, String str9, Set<? extends ContentAccessType> set, Map<String, ? extends List<String>> map2, Map<String, ? extends List<String>> map3, Map<String, ? extends Date> map4) {
        this(str, str2, map, str3, str4, l, str5, str6, str7, z, str8, str9, set, map2, map3, map4, null, null, 196608, null);
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, String str7, boolean z, String str8, String str9, Set<? extends ContentAccessType> set, Map<String, ? extends List<String>> map2, Map<String, ? extends List<String>> map3, Map<String, ? extends Date> map4, Date date) {
        this(str, str2, map, str3, str4, l, str5, str6, str7, z, str8, str9, set, map2, map3, map4, date, null, 131072, null);
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Long getId() {
        return null;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public void setId(Long l) {
    }

    @JvmOverloads
    public PodcastEpisodeSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, String str7, boolean z, String str8, String str9, Set<? extends ContentAccessType> set, Map<String, ? extends List<String>> map2, Map<String, ? extends List<String>> map3, Map<String, ? extends Date> map4, Date date, PlaymodeEligibility playmodeEligibility) {
        this.title = str;
        this.artworkUri = str2;
        this.contentInfoMap = map;
        this.blockRef = str3;
        this.asin = str4;
        this.artworkId = l;
        this.podcastEpisodeId = str5;
        this.podcastShowId = str6;
        this.subtitle = str7;
        this.isExplicit = z;
        this.podcastEpisodeVariantId = str8;
        this.podcastShowVariantId = str9;
        this.contentAccessSet = set;
        this.podcastEpisodeTierBenefits = map2;
        this.podcastShowTierBenefits = map3;
        this.availabilityDate = map4;
        this.publishDate = date;
        this.playmodeEligibility = playmodeEligibility;
        this.isAvailable = true;
        this.source = "PODCAST";
    }

    public /* synthetic */ PodcastEpisodeSearchItem(String str, String str2, Map map, String str3, String str4, Long l, String str5, String str6, String str7, boolean z, String str8, String str9, Set set, Map map2, Map map3, Map map4, Date date, PlaymodeEligibility playmodeEligibility, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : map, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : l, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, (i & 512) != 0 ? false : z, (i & 1024) != 0 ? null : str8, (i & 2048) != 0 ? null : str9, (i & 4096) != 0 ? null : set, (i & 8192) != 0 ? null : map2, (i & 16384) != 0 ? null : map3, (i & 32768) != 0 ? null : map4, (i & 65536) != 0 ? null : date, (i & 131072) != 0 ? null : playmodeEligibility);
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getArtworkUri() {
        return this.artworkUri;
    }

    public void setArtworkUri(String str) {
        this.artworkUri = str;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Map<String, String> getContentInfoMap() {
        return this.contentInfoMap;
    }

    public void setContentInfoMap(Map<String, String> map) {
        this.contentInfoMap = map;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getBlockRef() {
        return this.blockRef;
    }

    public void setBlockRef(String str) {
        this.blockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public String getAsin() {
        return this.asin;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Long getArtworkId() {
        return this.artworkId;
    }

    @Override // com.amazon.music.find.model.search.SearchPodcastEpisode
    public String getPodcastEpisodeId() {
        return this.podcastEpisodeId;
    }

    public void setPodcastEpisodeId(String str) {
        this.podcastEpisodeId = str;
    }

    @Override // com.amazon.music.find.model.search.SearchPodcastEpisode
    public String getPodcastShowId() {
        return this.podcastShowId;
    }

    public void setPodcastShowId(String str) {
        this.podcastShowId = str;
    }

    @Override // com.amazon.music.find.model.search.SearchPodcastEpisode
    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    @Override // com.amazon.music.find.model.search.SearchPodcastEpisode
    /* JADX INFO: renamed from: isExplicit, reason: from getter */
    public boolean getIsExplicit() {
        return this.isExplicit;
    }

    public void setExplicit(boolean z) {
        this.isExplicit = z;
    }

    public final String getPodcastEpisodeVariantId() {
        return this.podcastEpisodeVariantId;
    }

    public final void setPodcastEpisodeVariantId(String str) {
        this.podcastEpisodeVariantId = str;
    }

    public final String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }

    public final void setPodcastShowVariantId(String str) {
        this.podcastShowVariantId = str;
    }

    public final Set<ContentAccessType> getContentAccessSet() {
        return this.contentAccessSet;
    }

    public final void setContentAccessSet(Set<? extends ContentAccessType> set) {
        this.contentAccessSet = set;
    }

    public final Map<String, List<String>> getPodcastEpisodeTierBenefits() {
        return this.podcastEpisodeTierBenefits;
    }

    public final void setPodcastEpisodeTierBenefits(Map<String, ? extends List<String>> map) {
        this.podcastEpisodeTierBenefits = map;
    }

    public final Map<String, List<String>> getPodcastShowTierBenefits() {
        return this.podcastShowTierBenefits;
    }

    public final void setPodcastShowTierBenefits(Map<String, ? extends List<String>> map) {
        this.podcastShowTierBenefits = map;
    }

    public final Map<String, Date> getAvailabilityDate() {
        return this.availabilityDate;
    }

    public final void setAvailabilityDate(Map<String, ? extends Date> map) {
        this.availabilityDate = map;
    }

    public final Date getPublishDate() {
        return this.publishDate;
    }

    public final void setPublishDate(Date date) {
        this.publishDate = date;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    /* JADX INFO: renamed from: isInLibrary, reason: from getter */
    public boolean getIsInLibrary() {
        return this.isInLibrary;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    /* JADX INFO: renamed from: isAvailable, reason: from getter */
    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    @Override // com.amazon.music.find.model.search.SearchItem, com.amazon.mp3.library.item.CatalogContent
    public String getSource() {
        return this.source;
    }
}
