package com.amazon.music.find.model.search;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\tR \u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0005\"\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0012\u0010\u0019\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0018R\u0012\u0010\u001a\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\tR\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001dX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\tR\u0014\u0010\"\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\t¨\u0006$"}, d2 = {"Lcom/amazon/music/find/model/search/SearchItem;", "", "artworkId", "", "getArtworkId", "()Ljava/lang/Long;", "artworkUri", "", "getArtworkUri", "()Ljava/lang/String;", "asin", "getAsin", "blockRef", "getBlockRef", "contentInfoMap", "", "getContentInfoMap", "()Ljava/util/Map;", "id", "getId", "setId", "(Ljava/lang/Long;)V", "isAvailable", "", "()Z", "isInLibrary", "itemTypeName", "getItemTypeName", "playmodeEligibility", "Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", "getPlaymodeEligibility", "()Lcom/amazon/music/platform/data/eligibility/PlaymodeEligibility;", PageUriUtils.SOURCE_QUERY_PARAM, "getSource", "title", "getTitle", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchItem {
    Long getArtworkId();

    String getArtworkUri();

    String getAsin();

    String getBlockRef();

    Map<String, String> getContentInfoMap();

    Long getId();

    String getItemTypeName();

    PlaymodeEligibility getPlaymodeEligibility();

    String getSource();

    String getTitle();

    boolean isAvailable();

    boolean isInLibrary();

    void setId(Long l);
}
