package com.amazon.music.find.utils;

import android.net.Uri;
import com.amazon.music.destination.FindDestination;
import com.amazon.music.destination.parser.FindDeeplinkParser;
import com.amazon.music.find.viewmodels.SearchAction;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.platform.find.common.EntityType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: PageUriUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\u001eJ\u0014\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0012\u0010!\u001a\u00020\"2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0014\u0010#\u001a\u0004\u0018\u00010\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0007J\u001c\u0010$\u001a\u0004\u0018\u00010\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u00042\u0006\u0010%\u001a\u00020\u0004H\u0003J\u0014\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0014\u0010(\u001a\u0004\u0018\u00010\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0014\u0010)\u001a\u0004\u0018\u00010*2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020-H\u0007J\u0012\u0010.\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010/\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020-H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n \u0015*\u0004\u0018\u00010\u00140\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/amazon/music/find/utils/PageUriUtils;", "", "()V", "APP_INTERACTION_SEGMENT", "", "CONTENT_POS_QUERY_PARAM", "DO_ACTION_QUERY_PARAM", "DO_PLAYFULL_VALUE", "DO_PLAY_VALUE", "DO_SEE_MORE_VALUE", "ENTITY_TYPE_QUERY_PARAM", "FILTER_QUERY_LIBRARY_SEARCH_VALUE", "FILTER_QUERY_PARAM", "QUERYMETADATA_QUERY_PARAM", "SEARCH_HISTORY_QUERY_PARAM", "SEARCH_SEGMENT", "SEARCH_TERM_QUERY_PARAM", "SOURCE_QUERY_PARAM", "SOURCE_QUERY_VOICE_VALUE", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "constructPageUri", "findDestination", "Lcom/amazon/music/destination/FindDestination;", "containsLibraryFilter", "", "pageUri", "getContentPosition", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "getExperienceMode", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "getInteractionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "getQueryMetadata", "getQueryValue", "queryParam", "getSearchAction", "Lcom/amazon/music/find/viewmodels/SearchAction;", "getSearchTerm", "getSeeMoreEntityType", "Lcom/amazon/music/platform/find/common/EntityType;", "isAppInteraction", "uri", "Landroid/net/Uri;", "isSearchHistory", "matchesFindRegex", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PageUriUtils {
    public static final String APP_INTERACTION_SEGMENT = "appInteraction";
    public static final String CONTENT_POS_QUERY_PARAM = "contentPos";
    public static final String DO_ACTION_QUERY_PARAM = "do";
    public static final String DO_PLAYFULL_VALUE = "playfull";
    public static final String DO_PLAY_VALUE = "play";
    public static final String DO_SEE_MORE_VALUE = "seeMore";
    public static final String ENTITY_TYPE_QUERY_PARAM = "entityType";
    public static final String FILTER_QUERY_LIBRARY_SEARCH_VALUE = "isLibrary|true";
    public static final String FILTER_QUERY_PARAM = "filter";
    public static final String QUERYMETADATA_QUERY_PARAM = "queryMetadata";
    public static final String SEARCH_HISTORY_QUERY_PARAM = "history";
    public static final String SEARCH_SEGMENT = "search";
    public static final String SEARCH_TERM_QUERY_PARAM = "q";
    public static final String SOURCE_QUERY_PARAM = "source";
    public static final String SOURCE_QUERY_VOICE_VALUE = "voice";
    public static final PageUriUtils INSTANCE = new PageUriUtils();
    private static final Logger logger = LoggerFactory.getLogger(PageUriUtils.class.getName());

    private PageUriUtils() {
    }

    @JvmStatic
    public static final boolean matchesFindRegex(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        String path = uri.getPath();
        if (path == null) {
            return false;
        }
        String findUrlRegex = FindDeeplinkParser.getFindUrlRegex();
        Intrinsics.checkNotNullExpressionValue(findUrlRegex, "getFindUrlRegex()");
        return new Regex(findUrlRegex).matches(path);
    }

    @JvmStatic
    public static final String constructPageUri(FindDestination findDestination) {
        Intrinsics.checkNotNullParameter(findDestination, "findDestination");
        String searchTerm = findDestination.getSearchTerm();
        String seeMoreEntityType = findDestination.getSeeMoreEntityType();
        Integer contentPosition = findDestination.getContentPosition();
        String playbackDirective = findDestination.getPlaybackDirective();
        String searchFilter = findDestination.getSearchFilter();
        String source = findDestination.getSource();
        String queryMetadata = findDestination.getQueryMetadata();
        boolean zIsSearchHistory = findDestination.isSearchHistory();
        Uri.Builder builder = new Uri.Builder();
        builder.appendPath("search");
        if (searchTerm != null) {
            builder.appendQueryParameter(SEARCH_TERM_QUERY_PARAM, searchTerm);
        }
        if (seeMoreEntityType != null) {
            builder.appendQueryParameter(ENTITY_TYPE_QUERY_PARAM, seeMoreEntityType);
        }
        if (contentPosition != null) {
            builder.appendQueryParameter(CONTENT_POS_QUERY_PARAM, String.valueOf(contentPosition.intValue()));
        }
        if (playbackDirective != null) {
            builder.appendQueryParameter("do", playbackDirective);
        }
        if (searchFilter != null) {
            builder.appendQueryParameter(FILTER_QUERY_PARAM, searchFilter);
        }
        if (source != null) {
            builder.appendQueryParameter(SOURCE_QUERY_PARAM, source);
        }
        if (queryMetadata != null) {
            builder.appendQueryParameter(QUERYMETADATA_QUERY_PARAM, queryMetadata);
        }
        builder.appendQueryParameter(SEARCH_HISTORY_QUERY_PARAM, String.valueOf(zIsSearchHistory));
        return builder.toString();
    }

    @JvmStatic
    public static final boolean isAppInteraction(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        List<String> pathSegments = uri.getPathSegments();
        return pathSegments.size() == 1 && Intrinsics.areEqual(pathSegments.get(0), APP_INTERACTION_SEGMENT);
    }

    @JvmStatic
    private static final String getQueryValue(String pageUri, String queryParam) {
        if (pageUri == null) {
            return null;
        }
        try {
            return Uri.parse(pageUri).getQueryParameter(queryParam);
        } catch (Exception e) {
            logger.warn("Exception while parsing pageUri for query param [" + queryParam + "]: " + ((Object) e.getMessage()));
            return null;
        }
    }

    @JvmStatic
    public static final String getSearchTerm(String pageUri) {
        return getQueryValue(pageUri, SEARCH_TERM_QUERY_PARAM);
    }

    @JvmStatic
    public static final EntityType getSeeMoreEntityType(String pageUri) {
        String queryValue = getQueryValue(pageUri, ENTITY_TYPE_QUERY_PARAM);
        if (queryValue == null) {
            return null;
        }
        return EntityTypeUtils.INSTANCE.convertFromString(queryValue);
    }

    @JvmStatic
    public static final boolean isSearchHistory(String pageUri) {
        return Intrinsics.areEqual("true", getQueryValue(pageUri, SEARCH_HISTORY_QUERY_PARAM));
    }

    @JvmStatic
    public static final Integer getContentPosition(String pageUri) {
        String queryValue = getQueryValue(pageUri, CONTENT_POS_QUERY_PARAM);
        if (queryValue == null) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(queryValue));
        } catch (Exception e) {
            logger.warn("Exception while getting contentPosition from pageUri [" + ((Object) pageUri) + "]: " + ((Object) e.getMessage()));
            return null;
        }
    }

    @JvmStatic
    public static final SearchAction getSearchAction(String pageUri) {
        String queryValue = getQueryValue(pageUri, "do");
        if (queryValue == null) {
            return null;
        }
        int iHashCode = queryValue.hashCode();
        if (iHashCode == 3443508) {
            if (queryValue.equals(DO_PLAY_VALUE)) {
                return SearchAction.PLAY;
            }
            return null;
        }
        if (iHashCode == 1879307203) {
            if (queryValue.equals(DO_PLAYFULL_VALUE)) {
                return SearchAction.PLAY_FULL;
            }
            return null;
        }
        if (iHashCode == 1970932296 && queryValue.equals(DO_SEE_MORE_VALUE)) {
            return SearchAction.SEE_MORE;
        }
        return null;
    }

    @JvmStatic
    public static final SearchExperienceMode getExperienceMode(String pageUri) {
        return (getSearchTerm(pageUri) == null || getSeeMoreEntityType(pageUri) == null) ? getSearchTerm(pageUri) != null ? SearchExperienceMode.SEARCH_RESULT : isSearchHistory(pageUri) ? SearchExperienceMode.SEARCH_HISTORY : SearchExperienceMode.SEARCH_HISTORY : SearchExperienceMode.SEE_ALL;
    }

    @JvmStatic
    public static final boolean containsLibraryFilter(String pageUri) {
        String queryValue = getQueryValue(pageUri, FILTER_QUERY_PARAM);
        if (queryValue == null) {
            return false;
        }
        return StringsKt__StringsKt.contains$default((CharSequence) queryValue, (CharSequence) FILTER_QUERY_LIBRARY_SEARCH_VALUE, false, 2, (Object) null);
    }

    @JvmStatic
    public static final InteractionType getInteractionType(String pageUri) {
        return Intrinsics.areEqual(getQueryValue(pageUri, SOURCE_QUERY_PARAM), SOURCE_QUERY_VOICE_VALUE) ? InteractionType.VOICE : InteractionType.TAP;
    }

    @JvmStatic
    public static final InteractionType getInteractionType(FindDestination findDestination) {
        Intrinsics.checkNotNullParameter(findDestination, "findDestination");
        return Intrinsics.areEqual(findDestination.getSource(), SOURCE_QUERY_VOICE_VALUE) ? InteractionType.VOICE : InteractionType.TAP;
    }

    @JvmStatic
    public static final String getQueryMetadata(String pageUri) {
        return getQueryValue(pageUri, QUERYMETADATA_QUERY_PARAM);
    }
}
