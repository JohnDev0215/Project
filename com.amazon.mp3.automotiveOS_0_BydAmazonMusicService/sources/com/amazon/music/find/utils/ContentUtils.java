package com.amazon.music.find.utils;

import android.util.Log;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.model.SearchSuggestionEntity;
import com.amazon.music.find.model.SearchSuggestionText;
import com.amazon.music.find.model.metrics.EntityContentInfo;
import com.amazon.music.metrics.mts.event.types.ActivityIdType;
import com.amazon.music.metrics.mts.event.types.ResponseIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.find.common.EntityIdType;
import com.amazon.music.platform.find.common.EntityType;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.dataformat.cbor.databind.CBORMapper;
import com.google.android.gms.actions.SearchIntents;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\u0001H\u0002J6\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00172\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004JZ\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00172\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\u00042\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!2\b\u0010#\u001a\u0004\u0018\u00010\u0004J\u001a\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\"2\u0006\u0010'\u001a\u00020\u001aH\u0002J\u0018\u0010(\u001a\u0004\u0018\u00010\u00042\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n \r*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\n \r*\u0004\u0018\u00010\u00110\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/amazon/music/find/utils/ContentUtils;", "", "()V", ContentUtils.CLEAR_SEARCH_HISTORY_BUTTON_NAME, "", "KEY_ACTIVITY_ID", "KEY_ACTIVITY_ID_TYPE", "KEY_EVENT_ID", "KEY_RESPONSE_ID", "KEY_RESPONSE_ID_TYPE", "KEY_SEARCH_QUERY", "KEY_VIEW_REFERENCE_ID", "TAG", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "base64", "Lcom/fasterxml/jackson/core/Base64Variant;", "cborObjectMapper", "Lcom/fasterxml/jackson/dataformat/cbor/databind/CBORMapper;", "encodeContext", "obj", "getContentViewDataForSearchHistory", "", ContentUtils.KEY_EVENT_ID, "pos", "", "containerType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ContainerType;", SearchIntents.EXTRA_QUERY, "getContentViewDataForSearchSuggestion", ContentUtils.KEY_ACTIVITY_ID, "suggestions", "", "Lcom/amazon/music/find/model/SearchSuggestion;", ContentUtils.KEY_RESPONSE_ID, "getEntityContentInfo", "Lcom/amazon/music/find/model/metrics/EntityContentInfo;", "searchSuggestion", "index", "getSerializedContentInfo", "gzip", "", "byteArray", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContentUtils {
    public static final String CLEAR_SEARCH_HISTORY_BUTTON_NAME = "CLEAR_SEARCH_HISTORY_BUTTON_NAME";
    public static final String KEY_ACTIVITY_ID = "activityId";
    public static final String KEY_ACTIVITY_ID_TYPE = "activityIdType";
    public static final String KEY_EVENT_ID = "eventId";
    public static final String KEY_RESPONSE_ID = "responseId";
    public static final String KEY_RESPONSE_ID_TYPE = "responseIdType";
    public static final String KEY_SEARCH_QUERY = "searchQuery";
    public static final String KEY_VIEW_REFERENCE_ID = "viewReferenceId";
    public static final ContentUtils INSTANCE = new ContentUtils();
    private static final Base64Variant base64 = Base64Variants.getDefaultVariant();
    private static final CBORMapper cborObjectMapper = new CBORMapper();
    private static final String TAG = ContentUtils.class.getSimpleName();

    private ContentUtils() {
    }

    public final String getTAG() {
        return TAG;
    }

    public final Map<String, String> getContentViewDataForSearchSuggestion(String eventId, int pos, ContainerType containerType, String activityId, String query, List<? extends SearchSuggestion> suggestions, String responseId) {
        String serializedContentInfo;
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        Intrinsics.checkNotNullParameter(containerType, "containerType");
        Map mapMapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(KEY_EVENT_ID, eventId), TuplesKt.to(KEY_VIEW_REFERENCE_ID, eventId + '_' + pos), TuplesKt.to(KEY_ACTIVITY_ID, activityId), TuplesKt.to(KEY_ACTIVITY_ID_TYPE, ActivityIdType.SEARCH_SUGGESTION_ID.getMetricsValue()), TuplesKt.to(KEY_RESPONSE_ID, responseId), TuplesKt.to(KEY_RESPONSE_ID_TYPE, ResponseIdType.INSTANT_SEARCH_ID.getMetricsValue()), TuplesKt.to(KEY_SEARCH_QUERY, query));
        Map mapMutableMapOf = MapsKt__MapsKt.mutableMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT), TuplesKt.to("contentName", SearchMetricsServiceDefault.CONTENT_NAME_INLINE_SEARCH_SUGGESTIONS), TuplesKt.to("containerIndex", String.valueOf(pos)), TuplesKt.to("containerType", containerType.getMetricsValue()), TuplesKt.to("pageType", PageType.BROWSE_SEARCH_SUGGESTIONS.getMetricsValue()));
        if (suggestions != null && (serializedContentInfo = INSTANCE.getSerializedContentInfo(suggestions)) != null) {
            mapMutableMapOf.put("entitiesSerialized", serializedContentInfo);
        }
        return MapsKt__MapsKt.plus(mapMapOf, mapMutableMapOf);
    }

    private final EntityContentInfo getEntityContentInfo(SearchSuggestion searchSuggestion, int index) {
        if (searchSuggestion instanceof SearchSuggestionEntity) {
            SearchSuggestionEntity searchSuggestionEntity = (SearchSuggestionEntity) searchSuggestion;
            return new EntityContentInfo(searchSuggestionEntity.getSearchEntity().getEntityType(), searchSuggestionEntity.getSearchEntity().getEntityIdType(), index, searchSuggestionEntity.getSearchEntity().getEntityId(), null, 16, null);
        }
        if (!(searchSuggestion instanceof SearchSuggestionText)) {
            return null;
        }
        return new EntityContentInfo(EntityType.SEARCH_SUGGESTION, EntityIdType.SEARCH_SUGGESTION_ID, index, ((SearchSuggestionText) searchSuggestion).getId(), searchSuggestion.getTitle());
    }

    public final Map<String, String> getContentViewDataForSearchHistory(String eventId, int pos, ContainerType containerType, String query) {
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        Intrinsics.checkNotNullParameter(containerType, "containerType");
        return MapsKt__MapsKt.plus(MapsKt__MapsKt.mapOf(TuplesKt.to(KEY_EVENT_ID, eventId), TuplesKt.to(KEY_VIEW_REFERENCE_ID, eventId + '_' + pos), TuplesKt.to(KEY_SEARCH_QUERY, query)), MapsKt__MapsKt.mapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT), TuplesKt.to("contentName", SearchMetricsServiceDefault.CONTENT_NAME_SEARCH_HISTORY), TuplesKt.to("containerIndex", String.valueOf(pos)), TuplesKt.to("containerType", containerType.getMetricsValue()), TuplesKt.to("pageType", PageType.BROWSE_SEARCH_HISTORY.getMetricsValue())));
    }

    private final String encodeContext(Object obj) {
        try {
            Base64Variant base64Variant = base64;
            byte[] bArrWriteValueAsBytes = cborObjectMapper.writeValueAsBytes(obj);
            Intrinsics.checkNotNullExpressionValue(bArrWriteValueAsBytes, "cborObjectMapper.writeValueAsBytes(obj)");
            return base64Variant.encode(gzip(bArrWriteValueAsBytes));
        } catch (Exception e) {
            Log.e(TAG, "Encountered exception during serialization", e);
            return null;
        }
    }

    private final byte[] gzip(byte[] byteArray) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(byteArray.length);
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(byteArray);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(gZIPOutputStream, null);
                byte[] byteArray2 = byteArrayOutputStream.toByteArray();
                Intrinsics.checkNotNullExpressionValue(byteArray2, "byteStream.toByteArray()");
                CloseableKt.closeFinally(byteArrayOutputStream, null);
                return byteArray2;
            } finally {
            }
        } finally {
        }
    }

    private final String getSerializedContentInfo(List<? extends SearchSuggestion> suggestions) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : suggestions) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            EntityContentInfo entityContentInfo = INSTANCE.getEntityContentInfo((SearchSuggestion) obj, i);
            if (entityContentInfo != null) {
                arrayList.add(entityContentInfo);
            }
            i = i2;
        }
        return encodeContext(arrayList);
    }
}
