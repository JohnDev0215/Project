package com.amazon.music.find.model.search;

import android.net.Uri;
import androidx.core.app.FrameMetricsAggregator;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.google.android.gms.cast.MediaTrack;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SportsSearchItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001B\u007f\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010R\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R(\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R(\u0010'\u001a\u0004\u0018\u00010\n2\b\u0010&\u001a\u0004\u0018\u00010\n8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b(\u0010\u0012\"\u0004\b)\u0010\u0014R\u0014\u0010*\u001a\u00020+X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b*\u0010,R\u0014\u0010-\u001a\u00020+X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b-\u0010,R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0017\"\u0004\b3\u0010\u0019R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0017\"\u0004\b5\u0010\u0019¨\u00066"}, d2 = {"Lcom/amazon/music/find/model/search/SportsSearchItem;", "Lcom/amazon/music/find/model/search/SearchSports;", "title", "", "artworkUri", "contentInfoMap", "", "blockRef", "asin", "artworkId", "", MediaTrack.ROLE_SUBTITLE, "deeplink", "Landroid/net/Uri;", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Landroid/net/Uri;Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "getArtworkId", "()Ljava/lang/Long;", "setArtworkId", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getArtworkUri", "()Ljava/lang/String;", "setArtworkUri", "(Ljava/lang/String;)V", "getAsin", "setAsin", "getBlockRef", "setBlockRef", "getContentInfoMap", "()Ljava/util/Map;", "setContentInfoMap", "(Ljava/util/Map;)V", "getDeeplink", "()Landroid/net/Uri;", "setDeeplink", "(Landroid/net/Uri;)V", "value", "id", "getId", "setId", "isAvailable", "", "()Z", "isInLibrary", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "setPlaymodeEligibility", "(Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;)V", "getSubtitle", "setSubtitle", "getTitle", "setTitle", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class SportsSearchItem implements SearchSports {
    private Long artworkId;
    private String artworkUri;
    private String asin;
    private String blockRef;
    private Map<String, String> contentInfoMap;
    private Uri deeplink;
    private final boolean isAvailable;
    private final boolean isInLibrary;
    private PlaymodeEligibility playmodeEligibility;
    private String subtitle;
    private String title;

    @JvmOverloads
    public SportsSearchItem() {
        this(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    @JvmOverloads
    public SportsSearchItem(String str) {
        this(str, null, null, null, null, null, null, null, null, 510, null);
    }

    @JvmOverloads
    public SportsSearchItem(String str, String str2) {
        this(str, str2, null, null, null, null, null, null, null, 508, null);
    }

    @JvmOverloads
    public SportsSearchItem(String str, String str2, Map<String, String> map) {
        this(str, str2, map, null, null, null, null, null, null, 504, null);
    }

    @JvmOverloads
    public SportsSearchItem(String str, String str2, Map<String, String> map, String str3) {
        this(str, str2, map, str3, null, null, null, null, null, 496, null);
    }

    @JvmOverloads
    public SportsSearchItem(String str, String str2, Map<String, String> map, String str3, String str4) {
        this(str, str2, map, str3, str4, null, null, null, null, 480, null);
    }

    @JvmOverloads
    public SportsSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l) {
        this(str, str2, map, str3, str4, l, null, null, null, 448, null);
    }

    @JvmOverloads
    public SportsSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5) {
        this(str, str2, map, str3, str4, l, str5, null, null, 384, null);
    }

    @JvmOverloads
    public SportsSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, Uri uri) {
        this(str, str2, map, str3, str4, l, str5, uri, null, 256, null);
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public Long getId() {
        return null;
    }

    @Override // com.amazon.music.find.model.search.SearchItem
    public void setId(Long l) {
    }

    @JvmOverloads
    public SportsSearchItem(String str, String str2, Map<String, String> map, String str3, String str4, Long l, String str5, Uri uri, PlaymodeEligibility playmodeEligibility) {
        this.title = str;
        this.artworkUri = str2;
        this.contentInfoMap = map;
        this.blockRef = str3;
        this.asin = str4;
        this.artworkId = l;
        this.subtitle = str5;
        this.deeplink = uri;
        this.playmodeEligibility = playmodeEligibility;
        this.isAvailable = true;
    }

    public /* synthetic */ SportsSearchItem(String str, String str2, Map map, String str3, String str4, Long l, String str5, Uri uri, PlaymodeEligibility playmodeEligibility, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : map, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : l, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : uri, (i & 256) == 0 ? playmodeEligibility : null);
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

    @Override // com.amazon.music.find.model.search.SearchSports
    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    @Override // com.amazon.music.find.model.search.SearchSports
    public Uri getDeeplink() {
        return this.deeplink;
    }

    public void setDeeplink(Uri uri) {
        this.deeplink = uri;
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
}
