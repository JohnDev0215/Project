package com.amazon.music.inappmessaging.internal.content;

import android.app.Activity;
import android.util.Log;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandler;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.inappmessaging.internal.util.DynMsgMessageFormatter;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageViewUtil;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import rx.Observable;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes4.dex */
public class DynMsgContentHandlerImpl implements DynMsgContentHandler {
    private static final String TAG = "DynMsgContentHandlerImpl";
    private final DynMsgCacheHandler dynMsgCacheHandler;
    private final DynMsgNativeContentHandler dynMsgNativeContentHandler;

    public DynMsgContentHandlerImpl(final DynMsgCacheHandler dynMsgCacheHandler, final DynMsgNativeContentHandler dynMsgNativeContentHandler) {
        this.dynMsgCacheHandler = dynMsgCacheHandler;
        this.dynMsgNativeContentHandler = dynMsgNativeContentHandler;
    }

    @Override // com.amazon.music.inappmessaging.internal.content.DynMsgContentHandler
    public Observable<DynamicMessage> getDynamicMessage(final Activity activity, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes) {
        return this.dynMsgCacheHandler.getDynamicMessage(event).flatMap(new Func1<DynamicMessage, Observable<DynamicMessage>>() { // from class: com.amazon.music.inappmessaging.internal.content.DynMsgContentHandlerImpl.1
            @Override // rx.functions.Func1
            public Observable<DynamicMessage> call(final DynamicMessage dynamicMessage) {
                return Observable.just(DynMsgContentHandlerImpl.this.getCachedResponseForTrigger(activity, event, dynamicMessage, metricsAttributes));
            }
        }).onErrorReturn(new Func1() { // from class: com.amazon.music.inappmessaging.internal.content.DynMsgContentHandlerImpl$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$getDynamicMessage$0(event, activity, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicMessage lambda$getDynamicMessage$0(DynamicMessageEvent dynamicMessageEvent, Activity activity, Throwable th) {
        String str = "Unable to get dynamic message from cache:" + th.getMessage();
        DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.MESSAGE_NOT_IN_CACHE).withErrorMessage(str).withTrigger(dynamicMessageEvent.getEvent()).send();
        Log.e(TAG, str);
        return getNativeResponseForTrigger(activity, dynamicMessageEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DynamicMessage getCachedResponseForTrigger(final Activity activity, final DynamicMessageEvent event, final DynamicMessage dynamicMessage, final DynamicMessageMetricsAttributes metricsAttributes) {
        Boolean bool;
        if (event.getEvent().equals(Trigger.FIRST_STREAM.getName())) {
            if (dynamicMessage == null) {
                return null;
            }
            BehavioralAdsUtil.saveBehavioralAdsNotificationState(activity.getApplicationContext());
            return dynamicMessage;
        }
        if (dynamicMessage == null) {
            String str = "Unable to get dynamic message from cache for trigger: " + event.getEvent();
            DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.MESSAGE_NOT_IN_CACHE).withErrorMessage(str).withTrigger(event.getEvent()).send();
            Log.e(TAG, str);
            if (event.isCritical()) {
                return getNativeResponseForTrigger(activity, event);
            }
        }
        if (dynamicMessage != null && (bool = dynamicMessage.shouldRecordEmptyControl) != null && bool.booleanValue()) {
            DynamicMessageViewUtil.sendViewEvent(dynamicMessage, metricsAttributes);
            return getNativeResponseForTrigger(activity, event);
        }
        if (dynamicMessage != null && DynMsgMessageFormatter.templateAttributesContainPlaceholder(dynamicMessage.templateAttributes)) {
            DynMsgMessageFormatter.formatDynamicMessage(dynamicMessage, event);
        }
        return dynamicMessage;
    }

    private DynamicMessage getNativeResponseForTrigger(final Activity activity, final DynamicMessageEvent event) {
        if (event.isCritical()) {
            return this.dynMsgNativeContentHandler.getDynamicMessage(activity, event);
        }
        return null;
    }
}
