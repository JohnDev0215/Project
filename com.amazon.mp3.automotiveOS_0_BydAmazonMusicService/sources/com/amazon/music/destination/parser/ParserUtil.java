package com.amazon.music.destination.parser;

import android.net.Uri;
import android.text.TextUtils;
import com.amazon.music.destination.Target;
import com.amazon.music.destination.TargetType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class ParserUtil {
    public static final String ACTION_QUERY_PARAM_NAME = "do";
    public static final String ASIN_REGEX = "[A-Za-z0-9]+";
    public static final String CUSTOMER_PROFILE_REGEX = ".+";
    public static final String EXPERIENCE_ID_QUERY_PARAM_NAME = "experienceId";
    public static final String GENRE_SEGMENT_NAME = "genre";
    private static final String IS_BLOCKING_VIEW_PARAM = "isBlockingView";
    private static final String NEXT_QUERY_PARAM_NAME = "next";
    public static final String SCHEDULE_ID_QUERY_PARAM_NAME = "scheduleId";
    public static final String SKU_REGEX = "[A-Za-z0-9\\.]+";
    public static final String STORY_ASIN_QUERY_PARAM_NAME = "storyAsin";
    public static final String STORY_ID_QUERY_PARAM_NAME = "story";
    public static final String TAG_QUERY_PARAM_NAME = "tag";
    public static final String TRACK_ASIN_QUERY_PARAM_NAME = "trackAsin";
    private static final int UPSELL_REASON_PATH_INDEX = 1;
    private static final int UPSELL_SOURCE_PATH_INDEX = 2;
    public static final String URL_PATH_SEPARATOR = "/";
    protected static final String USER_CONFIRMATION_PARAM_NAME = "userConfirmationRequired";
    public static final String UTF_8 = "UTF-8";
    protected static final String VIEW_TYPE_QUERY_PARAM_NAME = "viewType";
    public static final String WILDCARD = ".*";
    public static final List<String> REF_QUERY_PARAM_NAMES = Arrays.asList("ref", "ref_");
    private static final Logger LOG = LoggerFactory.getLogger(ParserUtil.class.getName());

    static String optional(String str) {
        return "(" + str + ")?";
    }

    public static Action getAction(Uri uri) {
        return Action.fromString(uri.getQueryParameter("do"));
    }

    public static String getRefQueryParam(Uri uri) {
        if (uri == null) {
            return null;
        }
        Iterator<String> it = REF_QUERY_PARAM_NAMES.iterator();
        while (it.hasNext()) {
            String queryParameter = uri.getQueryParameter(it.next());
            if (queryParameter != null) {
                return queryParameter;
            }
        }
        return null;
    }

    public static String getTagQueryParam(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.getQueryParameter(TAG_QUERY_PARAM_NAME);
    }

    public static String getExperienceIdQueryParam(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.getQueryParameter("experienceId");
    }

    public static String getStoryAsinQueryParam(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.getQueryParameter(STORY_ASIN_QUERY_PARAM_NAME);
    }

    public static String getScheduleIdQueryParam(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.getQueryParameter(SCHEDULE_ID_QUERY_PARAM_NAME);
    }

    public static String getNextQueryParam(Uri uri) {
        return uri.getQueryParameter(NEXT_QUERY_PARAM_NAME);
    }

    public static String getStoryIdQueryParam(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.getQueryParameter(STORY_ID_QUERY_PARAM_NAME);
    }

    public static String getUserConfirmationParam(Uri uri) {
        return uri.getQueryParameter(USER_CONFIRMATION_PARAM_NAME);
    }

    static String getTrackAsin(Uri uri) {
        return uri.getQueryParameter(TRACK_ASIN_QUERY_PARAM_NAME);
    }

    static String getViewTypeQueryParam(Uri uri) {
        return uri.getQueryParameter(VIEW_TYPE_QUERY_PARAM_NAME);
    }

    static String getGenre(Uri uri) {
        return findSegmentAfter(uri, GENRE_SEGMENT_NAME);
    }

    static String findSegmentAfter(Uri uri, String str) {
        List<String> pathSegments = uri.getPathSegments();
        for (int i = 0; i < pathSegments.size(); i++) {
            if (pathSegments.get(i).equals(str) && i < pathSegments.size() - 1) {
                return pathSegments.get(i + 1);
            }
        }
        return null;
    }

    static List<String> findSegmentsAfter(Uri uri, String str) {
        List<String> pathSegments = uri.getPathSegments();
        for (int i = 0; i < pathSegments.size(); i++) {
            if (pathSegments.get(i).equals(str) && i < pathSegments.size() - 1) {
                return pathSegments.subList(i + 1, pathSegments.size());
            }
        }
        return Collections.emptyList();
    }

    static String findSegmentAfterRegex(Uri uri, String str) {
        List<String> pathSegments = uri.getPathSegments();
        for (int i = 0; i < pathSegments.size(); i++) {
            if (pathSegments.get(i).matches(str) && i < pathSegments.size() - 1) {
                return pathSegments.get(i + 1);
            }
        }
        return null;
    }

    static String getLastSegment(Uri uri) {
        return uri.getLastPathSegment();
    }

    static Target getTarget(Uri uri) {
        for (TargetType targetType : TargetType.values()) {
            String queryParameter = uri.getQueryParameter(targetType.getIdKey());
            if (queryParameter != null) {
                return new Target(targetType, queryParameter);
            }
        }
        return null;
    }

    static Map<String, String> getQueryParameters(Uri uri) {
        HashMap map = new HashMap();
        try {
            for (NameValuePair nameValuePair : URLEncodedUtils.parse(new URI(uri.toString()), UTF_8)) {
                String name = nameValuePair.getName();
                String value = nameValuePair.getValue();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(value)) {
                    map.put(name, value);
                }
            }
            return map;
        } catch (IllegalArgumentException | URISyntaxException e) {
            LOG.error("Could not parse query params from uri: [ %s ]. Exception: %s", uri, e.getStackTrace());
            return map;
        }
    }

    static String getUpsellReason(final List<String> list) {
        return (String) Optional.ofNullable(list).map(new Function() { // from class: com.amazon.music.destination.parser.ParserUtil$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ParserUtil.lambda$getUpsellReason$0(list, (List) obj);
            }
        }).orElse(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getUpsellReason$0(List list, List list2) {
        if (1 < list.size()) {
            return (String) list.get(1);
        }
        return null;
    }

    static String getUpsellSource(final List<String> list) {
        return (String) Optional.ofNullable(list).map(new Function() { // from class: com.amazon.music.destination.parser.ParserUtil$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ParserUtil.lambda$getUpsellSource$1(list, (List) obj);
            }
        }).orElse(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getUpsellSource$1(List list, List list2) {
        if (2 < list.size()) {
            return (String) list.get(2);
        }
        return null;
    }

    static boolean getUpsellIsBlocking(Uri uri) {
        return ((Boolean) Optional.ofNullable(uri).map(new Function() { // from class: com.amazon.music.destination.parser.ParserUtil$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ParserUtil.lambda$getUpsellIsBlocking$2((Uri) obj);
            }
        }).map(new Function() { // from class: com.amazon.music.destination.parser.ParserUtil$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ParserUtil.lambda$getUpsellIsBlocking$3((String) obj);
            }
        }).orElse(Boolean.FALSE)).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$getUpsellIsBlocking$2(Uri uri) {
        return uri.getQueryParameter(IS_BLOCKING_VIEW_PARAM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$getUpsellIsBlocking$3(String str) {
        try {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        } catch (Exception unused) {
            return Boolean.FALSE;
        }
    }
}
