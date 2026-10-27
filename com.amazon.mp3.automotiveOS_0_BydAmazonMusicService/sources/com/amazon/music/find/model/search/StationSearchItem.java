package com.amazon.music.find.model.search;

import com.amazon.music.ContentAccessType;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.google.android.gms.cast.CastStatusCodes;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: StationSearchItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B©\u0001\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\t\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014J\u0016\u0010:\u001a\u00020;2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0011H&J\u0010\u0010=\u001a\u00020;2\u0006\u0010>\u001a\u00020\u000fH&R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0016\"\u0004\b\u001d\u0010\u0018R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0016\"\u0004\b \u0010\u0018R\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R(\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R(\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010&\"\u0004\b*\u0010(R(\u0010,\u001a\u0004\u0018\u00010\u00072\b\u0010+\u001a\u0004\u0018\u00010\u00078V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b-\u0010\u001a\"\u0004\b.\u0010/R\u0014\u00100\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0016\"\u0004\b3\u0010\u0018R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0016\"\u0004\b9\u0010\u0018¨\u0006?"}, d2 = {"Lcom/amazon/music/find/model/search/StationSearchItem;", "Lcom/amazon/music/find/model/search/SearchStation;", "title", "", "artworkUri", "asin", "artworkId", "", "contentInfoMap", "", "blockRef", "key", "SFASeed", "explicitMap", "Lcom/amazon/music/ContentAccessType;", "", "contentAccessSet", "", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Set;Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "getSFASeed", "()Ljava/lang/String;", "setSFASeed", "(Ljava/lang/String;)V", "getArtworkId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getArtworkUri", "setArtworkUri", "getAsin", "getBlockRef", "setBlockRef", "getContentAccessSet", "()Ljava/util/Set;", "setContentAccessSet", "(Ljava/util/Set;)V", "getContentInfoMap", "()Ljava/util/Map;", "setContentInfoMap", "(Ljava/util/Map;)V", "getExplicitMap", "setExplicitMap", "value", "id", "getId", "setId", "(Ljava/lang/Long;)V", "isInLibrary", "()Z", "getKey", "setKey", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "setPlaymodeEligibility", "(Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "getTitle", "setTitle", "setCatalogStatus", "", "catalogStatus", "setExplicit", BrushV2Converter.KEY_IS_EXPLICIT, "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class StationSearchItem implements SearchStation {
    private String SFASeed;
    private final Long artworkId;
    private String artworkUri;
    private final String asin;
    private String blockRef;
    private Set<? extends ContentAccessType> contentAccessSet;
    private Map<String, String> contentInfoMap;
    private Map<ContentAccessType, Boolean> explicitMap;
    private final boolean isInLibrary;
    private String key;
    private PlaymodeEligibility playmodeEligibility;
    private String title;

    @JvmOverloads
    public StationSearchItem() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }

    @JvmOverloads
    public StationSearchItem(String str) {
        this(str, null, null, null, null, null, null, null, null, null, null, 2046, null);
    }

    @JvmOverloads
    public StationSearchItem(String str, String str2) {
        this(str, str2, null, null, null, null, null, null, null, null, null, 2044, null);
    }

    @JvmOverloads
    public StationSearchItem(String str, String str2, String str3) {
        this(str, str2, str3, null, null, null, null, null, null, null, null, 2040, null);
    }

    @JvmOverloads
    public StationSearchItem(String str, String str2, String str3, Long l) {
        this(str, str2, str3, l, null, null, null, null, null, null, null, 2032, null);
    }

    @JvmOverloads
    public StationSearchItem(String str, String str2, String str3, Long l, Map<String, String> map) {
        this(str, str2, str3, l, map, null, null, null, null, null, null, CastStatusCodes.DEVICE_CONNECTION_SUSPENDED, null);
    }

    @JvmOverloads
    public StationSearchItem(String str, String str2, String str3, Long l, Map<String, String> map, String str4) {
        this(str, str2, str3, l, map, str4, null, null, null, null, null, 1984, null);
    }

    @JvmOverloads
    public StationSearchItem(String str, String str2, String str3, Long l, Map<String, String> map, String str4, String str5) {
        this(str, str2, str3, l, map, str4, str5, null, null, null, null, 1920, null);
    }

    @JvmOverloads
    public StationSearchItem(String str, String str2, String str3, Long l, Map<String, String> map, String str4, String str5, String str6) {
        this(str, str2, str3, l, map, str4, str5, str6, null, null, null, 1792, null);
    }

    @JvmOverloads
    public StationSearchItem(String str, String str2, String str3, Long l, Map<String, String> map, String str4, String str5, String str6, Map<ContentAccessType, Boolean> map2) {
        this(str, str2, str3, l, map, str4, str5, str6, map2, null, null, 1536, null);
    }

    @JvmOverloads
    public StationSearchItem(String str, String str2, String str3, Long l, Map<String, String> map, String str4, String str5, String str6, Map<ContentAccessType, Boolean> map2, Set<? extends ContentAccessType> set) {
        this(str, str2, str3, l, map, str4, str5, str6, map2, set, null, 1024, null);
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Long getId() {
        return null;
    }

    public abstract void setCatalogStatus(Set<? extends ContentAccessType> catalogStatus);

    public abstract void setExplicit(boolean isExplicit);

    @Override // com.amazon.music.find.model.search.SearchItem
    public void setId(Long l) {
    }

    @JvmOverloads
    public StationSearchItem(String str, String str2, String str3, Long l, Map<String, String> map, String str4, String str5, String str6, Map<ContentAccessType, Boolean> map2, Set<? extends ContentAccessType> set, PlaymodeEligibility playmodeEligibility) {
        this.title = str;
        this.artworkUri = str2;
        this.asin = str3;
        this.artworkId = l;
        this.contentInfoMap = map;
        this.blockRef = str4;
        this.key = str5;
        this.SFASeed = str6;
        this.explicitMap = map2;
        this.contentAccessSet = set;
        this.playmodeEligibility = playmodeEligibility;
    }

    public /* synthetic */ StationSearchItem(String str, String str2, String str3, Long l, Map map, String str4, String str5, String str6, Map map2, Set set, PlaymodeEligibility playmodeEligibility, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : l, (i & 16) != 0 ? null : map, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : map2, (i & 512) != 0 ? null : set, (i & 1024) == 0 ? playmodeEligibility : null);
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
    public String getAsin() {
        return this.asin;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Long getArtworkId() {
        return this.artworkId;
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

    @Override // com.amazon.music.find.model.search.SearchStation
    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    @Override // com.amazon.music.find.model.search.SearchStation
    public String getSFASeed() {
        return this.SFASeed;
    }

    public void setSFASeed(String str) {
        this.SFASeed = str;
    }

    @Override // com.amazon.music.find.model.search.SearchStation
    public Map<ContentAccessType, Boolean> getExplicitMap() {
        return this.explicitMap;
    }

    public void setExplicitMap(Map<ContentAccessType, Boolean> map) {
        this.explicitMap = map;
    }

    public final Set<ContentAccessType> getContentAccessSet() {
        return this.contentAccessSet;
    }

    public final void setContentAccessSet(Set<? extends ContentAccessType> set) {
        this.contentAccessSet = set;
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
}
