package com.amazon.music.find.model.search;

import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchAlbum.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u001a\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/find/model/search/SearchAlbum;", "Lcom/amazon/music/find/model/search/SearchItem;", "Lcom/amazon/music/find/model/search/ContentEncodingAware;", "artist", "", "getArtist", "()Ljava/lang/String;", "artistAsin", "getArtistAsin", ContextMappingConstants.CONTENT_ENCODING, "", "Lcom/amazon/music/contentEncoding/ContentEncoding;", "getContentEncoding", "()Ljava/util/List;", "languageOfPerformances", "getLanguageOfPerformances", "trackCount", "", "getTrackCount", "()I", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchAlbum extends SearchItem, ContentEncodingAware {
    String getArtist();

    String getArtistAsin();

    List<ContentEncoding> getContentEncoding();

    List<String> getLanguageOfPerformances();

    int getTrackCount();
}
