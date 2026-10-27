package com.amazon.music.destination;

import android.net.Uri;
import com.amazon.music.destination.core.Destination;
import com.amazon.music.destination.parser.ParserUtil;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes4.dex */
public class FindDestination extends Destination {
    private static final String CONTENT_POS_QUERY_PARAM = "contentPos";
    private static final int ENTITY_TYPE_PATH_SEGMENT_INDEX = 2;
    private static final String FILTER_QUERY_PARAM = "filter";
    private static final String PLAYBACK_DIRECTIVE_PARAM = "do";
    private static final String QUERY_METADATA_QUERY_PARAM = "queryMetadata";
    private static final String SEARCH_HISTORY_METADATA_QUERY_PARAM = "history";
    private static final int SEARCH_TERM_PATH_SEGMENT_INDEX = 1;
    private static final String SOURCE_QUERY_PARAM = "source";
    private final Integer contentPosition;
    private final boolean isSearchHistory;
    private final String playbackDirective;
    private final String queryMetadata;
    private final String searchFilter;
    private final String searchTerm;
    private final String seeMoreEntityType;
    private final String source;
    private final Uri uri;

    public FindDestination(Uri uri) {
        super(ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
        this.uri = uri;
        this.searchTerm = getSearchTerm(uri);
        this.seeMoreEntityType = getSeeMoreEntityType(uri);
        this.contentPosition = getContentPosition(uri);
        this.playbackDirective = getPlaybackDirective(uri);
        this.searchFilter = getFilter(uri);
        this.source = getSource(uri);
        this.queryMetadata = getQueryMetadata(uri);
        this.isSearchHistory = getIsSearchHistory(uri);
    }

    public Uri getUri() {
        return this.uri;
    }

    public String getSearchTerm() {
        return this.searchTerm;
    }

    public String getSeeMoreEntityType() {
        return this.seeMoreEntityType;
    }

    public Integer getContentPosition() {
        return this.contentPosition;
    }

    public String getPlaybackDirective() {
        return this.playbackDirective;
    }

    public String getSearchFilter() {
        return this.searchFilter;
    }

    public String getSource() {
        return this.source;
    }

    public String getQueryMetadata() {
        return this.queryMetadata;
    }

    public boolean isSearchHistory() {
        return this.isSearchHistory;
    }

    public boolean isFindHome() {
        return this.searchTerm == null && !this.isSearchHistory;
    }

    private static String getSearchTerm(Uri uri) {
        return (String) Optional.ofNullable(uri).map(new Function() { // from class: com.amazon.music.destination.FindDestination$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Uri) obj).getPathSegments();
            }
        }).map(new Function() { // from class: com.amazon.music.destination.FindDestination$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FindDestination.lambda$getSearchTerm$1((List) obj);
            }
        }).orElse(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getSearchTerm$1(List list) {
        if (1 < list.size()) {
            return (String) list.get(1);
        }
        return null;
    }

    private static String getSeeMoreEntityType(Uri uri) {
        return (String) Optional.ofNullable(uri).map(new Function() { // from class: com.amazon.music.destination.FindDestination$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Uri) obj).getPathSegments();
            }
        }).map(new Function() { // from class: com.amazon.music.destination.FindDestination$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FindDestination.lambda$getSeeMoreEntityType$3((List) obj);
            }
        }).orElse(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getSeeMoreEntityType$3(List list) {
        if (2 < list.size()) {
            return (String) list.get(2);
        }
        return null;
    }

    private static Integer getContentPosition(Uri uri) {
        return (Integer) Optional.ofNullable(uri).map(new Function() { // from class: com.amazon.music.destination.FindDestination$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FindDestination.lambda$getContentPosition$4((Uri) obj);
            }
        }).map(new Function() { // from class: com.amazon.music.destination.FindDestination$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FindDestination.lambda$getContentPosition$5((String) obj);
            }
        }).filter(new Predicate() { // from class: com.amazon.music.destination.FindDestination$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return FindDestination.lambda$getContentPosition$6((Integer) obj);
            }
        }).orElse(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getContentPosition$4(Uri uri) {
        return uri.getQueryParameter("contentPos");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$getContentPosition$5(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getContentPosition$6(Integer num) {
        return num.intValue() >= 0;
    }

    private static String getPlaybackDirective(Uri uri) {
        if (uri != null) {
            return uri.getQueryParameter("do");
        }
        return null;
    }

    private static String getFilter(Uri uri) {
        if (uri != null) {
            return uri.getQueryParameter("filter");
        }
        return null;
    }

    private static String getSource(Uri uri) {
        if (uri != null) {
            return uri.getQueryParameter("source");
        }
        return null;
    }

    private static String getQueryMetadata(Uri uri) {
        if (uri != null) {
            return uri.getQueryParameter("queryMetadata");
        }
        return null;
    }

    private static boolean getIsSearchHistory(Uri uri) {
        if (uri != null) {
            return "true".equals(uri.getQueryParameter("history"));
        }
        return false;
    }
}
