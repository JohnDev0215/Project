package com.amazon.music.find.uicontentview;

import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.ActivityIdType;
import com.amazon.music.metrics.mts.event.types.ResponseIdType;
import com.amazon.music.uicontentview.ContentViewedListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;

/* JADX INFO: compiled from: SearchContentViewManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006H\u0002J\b\u0010\r\u001a\u00020\tH\u0002J,\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00062\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006H\u0002J \u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006H\u0002J&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u001a\u0010\u0013\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00060\u0014H\u0002J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014H\u0002J,\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00062\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006H\u0002J*\u0010\u0017\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0006H\u0016J2\u0010\u0019\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001a\u001a\u00020\u001b2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0006H\u0016J\u0006\u0010\u001c\u001a\u00020\tRF\u0010\u0003\u001a:\u0012\u0004\u0012\u00020\u0005\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00060\u0004j\u001c\u0012\u0004\u0012\u00020\u0005\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/find/uicontentview/SearchContentViewManager;", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "()V", "contentViewedMap", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "applyEventAttributes", "", "builder", "Lcom/amazon/music/metrics/mts/event/definition/uiinteraction/UiContentViewEvent$Builder;", "additionalAttributes", "clearContentViewed", "getEventAttributes", ContextMappingConstants.CONTENT_INFO, "getEventId", "getUiContentViewEvent", "Lcom/amazon/music/metrics/mts/event/definition/uiinteraction/UiContentViewEvent;", "contentInfos", "", "getUiContentViewEvents", "normalize", "onItemRendered", "identifier", "onItemRenderedInSequence", "pos", "", "sendUiContentViewEvent", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchContentViewManager implements ContentViewedListener {
    private static final List<String> ADDITIONAL_EVENT_ATTRIBUTES;
    private static final List<String> SANITIZED_EVENT_ATTRIBUTES;
    private static final String UNDEFINED_EVENT_ID = "UNDEFINED_EVENT_ID";
    private LinkedHashMap<String, Map<String, String>> contentViewedMap = new LinkedHashMap<>();

    static {
        List<String> listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{ContentUtils.KEY_ACTIVITY_ID, ContentUtils.KEY_ACTIVITY_ID_TYPE, ContentUtils.KEY_RESPONSE_ID, ContentUtils.KEY_RESPONSE_ID_TYPE, ContentUtils.KEY_SEARCH_QUERY});
        ADDITIONAL_EVENT_ATTRIBUTES = listListOf;
        SANITIZED_EVENT_ATTRIBUTES = CollectionsKt___CollectionsKt.plus((Collection) CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{ContentUtils.KEY_EVENT_ID, ContentUtils.KEY_VIEW_REFERENCE_ID}), (Iterable) listListOf);
    }

    @Override // com.amazon.music.uicontentview.ContentViewedListener
    public void onItemRendered(String identifier, Map<String, String> contentInfo) {
        if (identifier == null || contentInfo == null || this.contentViewedMap.containsKey(identifier)) {
            return;
        }
        DateTime dateTime = new DateTime(DateTimeZone.UTC);
        Map<String, String> mutableMap = MapsKt__MapsKt.toMutableMap(contentInfo);
        mutableMap.put("impressionTimestamp", ISODateTimeFormat.dateTime().print(dateTime));
        this.contentViewedMap.put(identifier, mutableMap);
    }

    @Override // com.amazon.music.uicontentview.ContentViewedListener
    public void onItemRenderedInSequence(String identifier, int pos, Map<String, String> contentInfo) {
        onItemRendered(identifier, contentInfo);
    }

    public final void sendUiContentViewEvent() {
        Runnable runnable = new Runnable() { // from class: com.amazon.music.find.uicontentview.SearchContentViewManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SearchContentViewManager.m2060sendUiContentViewEvent$lambda1(this.f$0);
            }
        };
        HandlerThread handlerThread = new HandlerThread("SearchContentView Module handler thread", 10);
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sendUiContentViewEvent$lambda-1, reason: not valid java name */
    public static final void m2060sendUiContentViewEvent$lambda1(SearchContentViewManager this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<UiContentViewEvent> uiContentViewEvents = this$0.getUiContentViewEvents();
        if (uiContentViewEvents == null) {
            return;
        }
        Iterator<T> it = uiContentViewEvents.iterator();
        while (it.hasNext()) {
            MetricsHolder.getManager().handleEvent((UiContentViewEvent) it.next());
        }
        this$0.clearContentViewed();
    }

    private final void clearContentViewed() {
        this.contentViewedMap = new LinkedHashMap<>();
    }

    private final List<UiContentViewEvent> getUiContentViewEvents() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<Map.Entry<String, Map<String, String>>> it = this.contentViewedMap.entrySet().iterator();
        while (it.hasNext()) {
            Map<String, String> value = it.next().getValue();
            String eventId = getEventId(value);
            if (eventId == null) {
                eventId = UNDEFINED_EVENT_ID;
            }
            List arrayList = (List) linkedHashMap.get(eventId);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(value);
            linkedHashMap.put(eventId, arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = linkedHashMap.entrySet().iterator();
        while (it2.hasNext()) {
            UiContentViewEvent uiContentViewEvent = getUiContentViewEvent((List) ((Map.Entry) it2.next()).getValue());
            if (uiContentViewEvent != null) {
                arrayList2.add(uiContentViewEvent);
            }
        }
        return arrayList2;
    }

    private final String getEventId(Map<String, String> contentInfo) {
        return contentInfo.get(ContentUtils.KEY_EVENT_ID);
    }

    private final UiContentViewEvent getUiContentViewEvent(List<? extends Map<String, String>> contentInfos) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = contentInfos.iterator();
        boolean z = true;
        Map<String, String> eventAttributes = null;
        boolean z2 = true;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map<String, String> map = (Map) it.next();
            if (eventAttributes == null) {
                eventAttributes = getEventAttributes(map);
            } else if (!eventAttributes.equals(getEventAttributes(map))) {
                z2 = false;
            }
            Map<String, String> mapNormalize = normalize(map);
            if (!(mapNormalize == null || mapNormalize.isEmpty())) {
                arrayList.add(mapNormalize);
            }
        }
        UiContentViewEvent.Builder builder = UiContentViewEvent.builder().withContentInfo(arrayList).withEventTime(System.currentTimeMillis());
        if (z2) {
            if (eventAttributes != null && !eventAttributes.isEmpty()) {
                z = false;
            }
            if (!z) {
                Intrinsics.checkNotNullExpressionValue(builder, "builder");
                Intrinsics.checkNotNull(eventAttributes);
                applyEventAttributes(builder, eventAttributes);
            }
        }
        return builder.build();
    }

    private final Map<String, String> getEventAttributes(Map<String, String> contentInfo) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : ADDITIONAL_EVENT_ATTRIBUTES) {
            linkedHashMap.put(str, contentInfo.get(str));
        }
        return MapsKt__MapsKt.toMap(linkedHashMap);
    }

    private final Map<String, String> normalize(Map<String, String> contentInfo) {
        Map mutableMap = MapsKt__MapsKt.toMutableMap(contentInfo);
        Iterator<T> it = SANITIZED_EVENT_ATTRIBUTES.iterator();
        while (it.hasNext()) {
            mutableMap.remove((String) it.next());
        }
        return MapsKt__MapsKt.toMap(mutableMap);
    }

    private final void applyEventAttributes(UiContentViewEvent.Builder builder, Map<String, String> additionalAttributes) {
        String str = additionalAttributes.get(ContentUtils.KEY_ACTIVITY_ID);
        if (str != null) {
            builder.withActivityId(str);
        }
        String str2 = additionalAttributes.get(ContentUtils.KEY_ACTIVITY_ID_TYPE);
        if (str2 != null) {
            builder.withActivityIdType(ActivityIdType.fromString(str2));
        }
        String str3 = additionalAttributes.get(ContentUtils.KEY_RESPONSE_ID);
        if (str3 != null) {
            builder.withResponseId(str3);
        }
        String str4 = additionalAttributes.get(ContentUtils.KEY_RESPONSE_ID_TYPE);
        if (str4 != null) {
            builder.withResponseIdType(ResponseIdType.fromString(str4));
        }
        String str5 = additionalAttributes.get(ContentUtils.KEY_SEARCH_QUERY);
        if (str5 == null) {
            return;
        }
        builder.withSearchQuery(str5);
    }
}
