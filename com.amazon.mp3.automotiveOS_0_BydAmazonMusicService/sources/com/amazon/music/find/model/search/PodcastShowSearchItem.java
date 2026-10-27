package com.amazon.music.find.model.search;

import com.amazon.music.ContentAccessType;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.google.android.gms.cast.MediaTrack;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PodcastShowSearchItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b0\b&\u0018\u00002\u00020\u0001BÅ\u0001\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011\u0012\u001c\b\u0002\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0014\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010\u0017R\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001e\"\u0004\b\"\u0010 R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001e\"\u0004\b$\u0010 R\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R(\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R(\u0010.\u001a\u0004\u0018\u00010\n2\b\u0010-\u001a\u0004\u0018\u00010\n8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b/\u0010\u0019\"\u0004\b0\u0010\u001bR\u0014\u00101\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u001a\u0010\r\u001a\u00020\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u00102\"\u0004\b3\u00104R\u0014\u00105\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b5\u00102R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u001e\"\u0004\b;\u0010 R\u0016\u0010<\u001a\u0004\u0018\u00010\u0003X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u001eR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u001e\"\u0004\b?\u0010 R.\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0014\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010*\"\u0004\bA\u0010,R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u001e\"\u0004\bC\u0010 R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u001e\"\u0004\bE\u0010 ¨\u0006F"}, d2 = {"Lcom/amazon/music/find/model/search/PodcastShowSearchItem;", "Lcom/amazon/music/find/model/search/SearchPodcastShow;", "title", "", "artworkUri", "contentInfoMap", "", "blockRef", "asin", "artworkId", "", "podcastShowId", MediaTrack.ROLE_SUBTITLE, BrushV2Converter.KEY_IS_EXPLICIT, "", "variantId", "contentAccessSet", "", "Lcom/amazon/music/ContentAccessType;", "tierBenefits", "", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/util/Set;Ljava/util/Map;Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "getArtworkId", "()Ljava/lang/Long;", "setArtworkId", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getArtworkUri", "()Ljava/lang/String;", "setArtworkUri", "(Ljava/lang/String;)V", "getAsin", "setAsin", "getBlockRef", "setBlockRef", "getContentAccessSet", "()Ljava/util/Set;", "setContentAccessSet", "(Ljava/util/Set;)V", "getContentInfoMap", "()Ljava/util/Map;", "setContentInfoMap", "(Ljava/util/Map;)V", "value", "id", "getId", "setId", "isAvailable", "()Z", "setExplicit", "(Z)V", "isInLibrary", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "setPlaymodeEligibility", "(Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "getPodcastShowId", "setPodcastShowId", PageUriUtils.SOURCE_QUERY_PARAM, "getSource", "getSubtitle", "setSubtitle", "getTierBenefits", "setTierBenefits", "getTitle", "setTitle", "getVariantId", "setVariantId", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class PodcastShowSearchItem implements SearchPodcastShow {
    private Long artworkId;
    private String artworkUri;
    private String asin;
    private String blockRef;
    private Set<? extends ContentAccessType> contentAccessSet;
    private Map<String, String> contentInfoMap;
    private final boolean isAvailable;
    private boolean isExplicit;
    private final boolean isInLibrary;
    private PlaymodeEligibility playmodeEligibility;
    private String podcastShowId;
    private final String source;
    private String subtitle;
    private Map<String, ? extends List<String>> tierBenefits;
    private String title;
    private String variantId;

    @JvmOverloads
    public PodcastShowSearchItem() {
        this(null, null, null, null, null, null, null, null, false, null, null, null, null, 8191, null);
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str) {
        this(str, null, null, null, null, null, null, null, false, null, null, null, null, 8190, null);
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str, String str2) {
        this(str, str2, null, null, null, null, null, null, false, null, null, null, null, 8188, null);
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str, String str2, Map<String, String> map) {
        this(str, str2, map, null, null, null, null, null, false, null, null, null, null, 8184, null);
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str, String str2, Map<String, String> map, String str3) {
        this(str, str2, map, str3, null, null, null, null, false, null, null, null, null, 8176, null);
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str, String str2, Map<String, String> map, String str3, String str4) {
        this(str, str2, map, str3, str4, null, null, null, false, null, null, null, null, 8160, null);
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l) {
        this(str, str2, map, str3, str4, l, null, null, false, null, null, null, null, 8128, null);
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5) {
        this(str, str2, map, str3, str4, l, str5, null, false, null, null, null, null, 8064, null);
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6) {
        this(str, str2, map, str3, str4, l, str5, str6, false, null, null, null, null, 7936, null);
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, boolean z) {
        this(str, str2, map, str3, str4, l, str5, str6, z, null, null, null, null, 7680, null);
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, boolean z, String str7) {
        this(str, str2, map, str3, str4, l, str5, str6, z, str7, null, null, null, 7168, null);
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, boolean z, String str7, Set<? extends ContentAccessType> set) {
        this(str, str2, map, str3, str4, l, str5, str6, z, str7, set, null, null, 6144, null);
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, boolean z, String str7, Set<? extends ContentAccessType> set, Map<String, ? extends List<String>> map2) {
        this(str, str2, map, str3, str4, l, str5, str6, z, str7, set, map2, null, 4096, null);
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Long getId() {
        return null;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public void setId(Long l) {
    }

    @JvmOverloads
    public PodcastShowSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, String str6, boolean z, String str7, Set<? extends ContentAccessType> set, Map<String, ? extends List<String>> map2, PlaymodeEligibility playmodeEligibility) {
        this.title = str;
        this.artworkUri = str2;
        this.contentInfoMap = map;
        this.blockRef = str3;
        this.asin = str4;
        this.artworkId = l;
        this.podcastShowId = str5;
        this.subtitle = str6;
        this.isExplicit = z;
        this.variantId = str7;
        this.contentAccessSet = set;
        this.tierBenefits = map2;
        this.playmodeEligibility = playmodeEligibility;
        this.isAvailable = true;
        this.source = "PODCAST";
    }

    public /* synthetic */ PodcastShowSearchItem(String str, String str2, Map map, String str3, String str4, Long l, String str5, String str6, boolean z, String str7, Set set, Map map2, PlaymodeEligibility playmodeEligibility, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : map, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : l, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? false : z, (i & 512) != 0 ? null : str7, (i & 1024) != 0 ? null : set, (i & 2048) != 0 ? null : map2, (i & 4096) == 0 ? playmodeEligibility : null);
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

    public void setAsin(String str) {
        this.asin = str;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Long getArtworkId() {
        return this.artworkId;
    }

    public void setArtworkId(Long l) {
        this.artworkId = l;
    }

    @Override // com.amazon.music.find.model.search.SearchPodcastShow
    public String getPodcastShowId() {
        return this.podcastShowId;
    }

    public void setPodcastShowId(String str) {
        this.podcastShowId = str;
    }

    @Override // com.amazon.music.find.model.search.SearchPodcastShow
    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    @Override // com.amazon.music.find.model.search.SearchPodcastShow
    /* JADX INFO: renamed from: isExplicit, reason: from getter */
    public boolean getIsExplicit() {
        return this.isExplicit;
    }

    public void setExplicit(boolean z) {
        this.isExplicit = z;
    }

    public final String getVariantId() {
        return this.variantId;
    }

    public final void setVariantId(String str) {
        this.variantId = str;
    }

    public final Set<ContentAccessType> getContentAccessSet() {
        return this.contentAccessSet;
    }

    public final void setContentAccessSet(Set<? extends ContentAccessType> set) {
        this.contentAccessSet = set;
    }

    public final Map<String, List<String>> getTierBenefits() {
        return this.tierBenefits;
    }

    public final void setTierBenefits(Map<String, ? extends List<String>> map) {
        this.tierBenefits = map;
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
