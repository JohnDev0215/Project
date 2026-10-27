package com.amazon.mp3.platform.util;

import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.ActivityIdType;
import com.amazon.music.metrics.mts.event.types.MetricsEnumUtil;
import com.amazon.music.metrics.mts.event.types.ResponseIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ViewType;
import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.metrics.event.MetricsWrapper;
import com.amazon.music.platform.metrics.event.MusicMetricsWrapper;
import com.amazon.music.platform.metrics.event.UIClickWrapper;
import com.amazon.music.platform.metrics.event.UIContentViewWrapper;
import com.amazon.music.platform.metrics.event.UIPageViewWrapper;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: MetricsConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J7\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\u000e\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\rH\u0002¢\u0006\u0002\u0010\u000eJ\f\u0010\u0003\u001a\u00020\u000f*\u00020\u0010H\u0002J\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0011H\u0002J\f\u0010\u0003\u001a\u00020\u0012*\u00020\u0013H\u0002J\f\u0010\u0003\u001a\u00020\u0014*\u00020\u0015H\u0002J\f\u0010\u0003\u001a\u00020\u0016*\u00020\u0017H\u0002¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/platform/util/MetricsConverter;", "", "()V", "convertToEvent", "Lcom/amazon/music/metrics/mts/MTSEvent;", "metricsWrapper", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "getByName", "T", "", "name", "", "kClass", "Lkotlin/reflect/KClass;", "(Ljava/lang/String;Lkotlin/reflect/KClass;)Ljava/lang/Enum;", "Lcom/amazon/music/metrics/mts/event/definition/flex/FlexEvent;", "Lcom/amazon/music/platform/metrics/event/FlexWrapper;", "Lcom/amazon/music/platform/metrics/event/MusicMetricsWrapper;", "Lcom/amazon/music/metrics/mts/event/definition/uiinteraction/UiClickEvent;", "Lcom/amazon/music/platform/metrics/event/UIClickWrapper;", "Lcom/amazon/music/metrics/mts/event/definition/uiinteraction/UiContentViewEvent;", "Lcom/amazon/music/platform/metrics/event/UIContentViewWrapper;", "Lcom/amazon/music/metrics/mts/event/definition/uiinteraction/UiPageViewEvent;", "Lcom/amazon/music/platform/metrics/event/UIPageViewWrapper;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MetricsConverter {
    public static final MetricsConverter INSTANCE = new MetricsConverter();

    private MetricsConverter() {
    }

    public final MTSEvent convertToEvent(MetricsWrapper metricsWrapper) {
        Intrinsics.checkNotNullParameter(metricsWrapper, "metricsWrapper");
        if (metricsWrapper instanceof FlexWrapper) {
            return convertToEvent((FlexWrapper) metricsWrapper);
        }
        if (metricsWrapper instanceof MusicMetricsWrapper) {
            return convertToEvent((MusicMetricsWrapper) metricsWrapper);
        }
        if (metricsWrapper instanceof UIPageViewWrapper) {
            return convertToEvent((UIPageViewWrapper) metricsWrapper);
        }
        if (metricsWrapper instanceof UIClickWrapper) {
            return convertToEvent((UIClickWrapper) metricsWrapper);
        }
        if (metricsWrapper instanceof UIContentViewWrapper) {
            return convertToEvent((UIContentViewWrapper) metricsWrapper);
        }
        Log.warning(MetricsConverter.class.getSimpleName(), "Cannot convert the metricsWrapper type " + ((Object) metricsWrapper.getClass().getSimpleName()) + " to an MTSEvent");
        return null;
    }

    private final FlexEvent convertToEvent(FlexWrapper flexWrapper) {
        FlexEvent flexEventBuild = FlexEvent.builder(flexWrapper.getFlexEventName()).withFlexNum1(flexWrapper.getNum1()).withFlexNum2(flexWrapper.getNum2()).withFlexNum3(flexWrapper.getNum3()).withFlexNum4(flexWrapper.getNum4()).withFlexStr1(flexWrapper.getStr1()).withFlexStr2(flexWrapper.getStr2()).withFlexStr3(flexWrapper.getStr3()).withFlexStr4(flexWrapper.getStr4()).withHcFlexStr1(flexWrapper.getHcStr1()).withHcFlexStr2(flexWrapper.getHcStr2()).withHcFlexStr3(flexWrapper.getHcStr3()).withHcFlexStr4(flexWrapper.getHcStr4()).build();
        Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(flexEventName)\n …tr4)\n            .build()");
        return flexEventBuild;
    }

    private final MTSEvent convertToEvent(MusicMetricsWrapper musicMetricsWrapper) {
        MTSEvent mTSEvent = new MTSEvent(musicMetricsWrapper.getMetricsEventName(), musicMetricsWrapper.getEventVersion());
        mTSEvent.addAttributes(musicMetricsWrapper.getAttributes());
        mTSEvent.addContentInfoAttributes(musicMetricsWrapper.getContentInfoAttributes());
        mTSEvent.setMetricsContext(musicMetricsWrapper.getMetricsContext());
        mTSEvent.setTimestamp(musicMetricsWrapper.getTimeStamp());
        return mTSEvent;
    }

    private final UiPageViewEvent convertToEvent(UIPageViewWrapper uIPageViewWrapper) {
        UiPageViewEvent uiPageViewEventBuild = UiPageViewEvent.builder(uIPageViewWrapper.getPageType()).withAssociateTag(uIPageViewWrapper.getAssociateTag()).withBlockRef(uIPageViewWrapper.getBlockRef()).withDetailPageItemId(uIPageViewWrapper.getDetailPageItemId()).withDetailPageItemIdType(uIPageViewWrapper.getDetailPageItemIdType()).withEventTime(uIPageViewWrapper.getTimeStamp()).withExternalReference(uIPageViewWrapper.getExternalReference()).withLoadTime(uIPageViewWrapper.getLoadTime()).withPageSubType(uIPageViewWrapper.getPageSubType()).withViewType((ViewType) getByName(uIPageViewWrapper.getViewType(), Reflection.getOrCreateKotlinClass(ViewType.class))).withActivityId(uIPageViewWrapper.getActivityId()).withActivityIdType((ActivityIdType) getByName(uIPageViewWrapper.getActivityIdType(), Reflection.getOrCreateKotlinClass(ActivityIdType.class))).withResponseId(uIPageViewWrapper.getResponseId()).withResponseIdType((ResponseIdType) getByName(uIPageViewWrapper.getResponseIdType(), Reflection.getOrCreateKotlinClass(ResponseIdType.class))).withSearchQuery(uIPageViewWrapper.getSearchQuery()).withLocale(uIPageViewWrapper.getLocale()).withUrl(uIPageViewWrapper.getUrl()).withUserAgent(uIPageViewWrapper.getUserAgent()).withCsSessionId(uIPageViewWrapper.getCsSessionId()).build();
        Intrinsics.checkNotNullExpressionValue(uiPageViewEventBuild, "builder(pageType)\n      …nId)\n            .build()");
        return uiPageViewEventBuild;
    }

    private final UiClickEvent convertToEvent(UIClickWrapper uIClickWrapper) {
        String actionType = uIClickWrapper.getActionType();
        UiClickEvent.Builder builder = actionType == null ? null : UiClickEvent.builder(actionType);
        if (builder == null) {
            builder = UiClickEvent.builder(uIClickWrapper.getUrl(), uIClickWrapper.getBlockRef());
        }
        UiClickEvent uiClickEventBuild = builder.withContentInfo(uIClickWrapper.getContentInfo()).withCustomPageType(uIClickWrapper.getPageType()).withPageSubType(uIClickWrapper.getPageSubType()).withBlockRef(uIClickWrapper.getBlockRef()).withRefMarker(uIClickWrapper.getRefMarker()).withCustomEntityType(uIClickWrapper.getEntityType()).withEntityId(uIClickWrapper.getEntityId()).withCustomEntityIdType(uIClickWrapper.getEntityIdType()).withEntityPos(uIClickWrapper.getEntityPos()).withInteractionType((InteractionType) getByName(uIClickWrapper.getInteractionType(), Reflection.getOrCreateKotlinClass(InteractionType.class))).withUrl(uIClickWrapper.getUrl()).withContentInfo(uIClickWrapper.getContentInfo()).withLocalContentId(uIClickWrapper.getLocalContentId()).withActivityId(uIClickWrapper.getActivityId()).withActivityIdType((ActivityIdType) getByName(uIClickWrapper.getActivityIdType(), Reflection.getOrCreateKotlinClass(ActivityIdType.class))).withResponseId(uIClickWrapper.getResponseId()).withResponseIdType((ResponseIdType) getByName(uIClickWrapper.getResponseIdType(), Reflection.getOrCreateKotlinClass(ResponseIdType.class))).withSearchQuery(uIClickWrapper.getSearchQuery()).withPageEntityId(uIClickWrapper.getPageEntityId()).withCustomPageEntityIdType(uIClickWrapper.getPageEntityIdType()).withCustomPageEntityType(uIClickWrapper.getPageEntityType()).build();
        Intrinsics.checkNotNullExpressionValue(uiClickEventBuild, "builder.withContentInfo(…ype)\n            .build()");
        return uiClickEventBuild;
    }

    private final <T extends Enum<T>> T getByName(String name, KClass<T> kClass) {
        if (name == null) {
            return null;
        }
        return (T) MetricsEnumUtil.fromStringValue(JvmClassMappingKt.getJavaClass((KClass) kClass), name);
    }

    private final UiContentViewEvent convertToEvent(UIContentViewWrapper uIContentViewWrapper) {
        UiContentViewEvent.ClientEventBuilder clientEventBuilderWithBlockRef = UiContentViewEvent.clientEventBuilder().withBlockRef(uIContentViewWrapper.getBlockRef());
        Long containerIndex = uIContentViewWrapper.getContainerIndex();
        UiContentViewEvent.ClientEventBuilder clientEventBuilderWithContentName = clientEventBuilderWithBlockRef.withContainerIndex(containerIndex == null ? 0L : containerIndex.longValue()).withContainerType((ContainerType) getByName(uIContentViewWrapper.getContainerType(), Reflection.getOrCreateKotlinClass(ContainerType.class))).withContentName((ContentName) getByName(uIContentViewWrapper.getContentName(), Reflection.getOrCreateKotlinClass(ContentName.class)));
        Long numItemsAccessible = uIContentViewWrapper.getNumItemsAccessible();
        UiContentViewEvent.ClientEventBuilder clientEventBuilderWithEventTime = clientEventBuilderWithContentName.withNumItemsAccessible(numItemsAccessible == null ? 0L : numItemsAccessible.longValue()).withEventTime(uIContentViewWrapper.getTimeStamp());
        Long impressionTimestamp = uIContentViewWrapper.getImpressionTimestamp();
        UiContentViewEvent.ClientEventBuilder clientEventBuilderWithPageType = clientEventBuilderWithEventTime.withImpressionTimestamp(impressionTimestamp != null ? impressionTimestamp.longValue() : 0L).withActivityId(uIContentViewWrapper.getActivityId()).withActivityIdType((ActivityIdType) getByName(uIContentViewWrapper.getActivityIdType(), Reflection.getOrCreateKotlinClass(ActivityIdType.class))).withResponseId(uIContentViewWrapper.getResponseId()).withResponseIdType((ResponseIdType) getByName(uIContentViewWrapper.getResponseIdType(), Reflection.getOrCreateKotlinClass(ResponseIdType.class))).withSearchQuery(uIContentViewWrapper.getSearchQuery()).withPageType(uIContentViewWrapper.getPageType());
        if (Intrinsics.areEqual(uIContentViewWrapper.getEmittedOnView(), Boolean.TRUE)) {
            clientEventBuilderWithPageType.emittedOnView();
        }
        UiContentViewEvent uiContentViewEventBuild = clientEventBuilderWithPageType.build();
        Intrinsics.checkNotNullExpressionValue(uiContentViewEventBuild, "clientEventBuilder()\n   …w()\n            }.build()");
        return uiContentViewEventBuild;
    }
}
