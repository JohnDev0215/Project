package com.amazon.music.find.model.search;

import com.amazon.music.ContentAccessType;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchStation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R \u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0005¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/find/model/search/SearchStation;", "Lcom/amazon/music/find/model/search/SearchItem;", "SFASeed", "", "getSFASeed", "()Ljava/lang/String;", "explicitMap", "", "Lcom/amazon/music/ContentAccessType;", "", "getExplicitMap", "()Ljava/util/Map;", "key", "getKey", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchStation extends SearchItem {
    Map<ContentAccessType, Boolean> getExplicitMap();

    String getKey();

    String getSFASeed();
}
