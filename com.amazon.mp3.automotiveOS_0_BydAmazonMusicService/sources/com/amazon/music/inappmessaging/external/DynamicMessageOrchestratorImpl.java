package com.amazon.music.inappmessaging.external;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.internal.cache.DynMsgCacheHandler;
import com.amazon.music.inappmessaging.internal.content.DynMsgActionHandler;
import com.amazon.music.inappmessaging.internal.content.DynMsgContentHandler;
import com.amazon.music.inappmessaging.internal.handler.DynamicMessageTemplateHandler;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageOrchestratorImpl extends DynamicMessageOrchestrator {
    private static final String DYN_MSG_EVENT_KEY = "dynMsgEvent";
    private static final String TAG = "DynamicMessageOrchestratorImpl";
    public static boolean sAppStartReceiverReady = false;
    public static boolean sResendAppStartDynMsgBroadcast = false;
    private final Context context;
    private final DynMsgActionHandler dynMsgActionHandler;
    private final DynMsgCacheHandler dynMsgCacheHandler;
    private final DynMsgContentHandler dynMsgContentHandler;
    private final DynamicMessageTemplateHandler dynMsgTemplateHandler;

    protected DynamicMessageOrchestratorImpl(final Context context, final DynMsgCacheHandler dynMsgCacheHandler, final DynMsgContentHandler dynMsgContentHandler, final DynamicMessageTemplateHandler dynMsgTemplateHandler, final DynMsgActionHandler dynMsgActionHandler) {
        this.context = context;
        this.dynMsgCacheHandler = dynMsgCacheHandler;
        this.dynMsgContentHandler = dynMsgContentHandler;
        this.dynMsgTemplateHandler = dynMsgTemplateHandler;
        this.dynMsgActionHandler = dynMsgActionHandler;
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator
    public Observable<Fragment> displayNonBlockingDynamicMessage(final FragmentActivity fragmentActivity, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes) {
        if (event.getEventType() != DynamicMessageEvent.DynamicMessageEventType.TRIGGER) {
            Log.i(TAG, "NonBlocking surface is only supported for trigger type event");
            return Observable.just(null);
        }
        return getDynamicMessageFragment(Observable.just(Boolean.TRUE).observeOn(Schedulers.io()).flatMap(new Func1() { // from class: com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl$$ExternalSyntheticLambda4
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$displayNonBlockingDynamicMessage$0(fragmentActivity, event, metricsAttributes, (Boolean) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()), metricsAttributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Observable lambda$displayNonBlockingDynamicMessage$0(FragmentActivity fragmentActivity, DynamicMessageEvent dynamicMessageEvent, DynamicMessageMetricsAttributes dynamicMessageMetricsAttributes, Boolean bool) {
        return this.dynMsgContentHandler.getDynamicMessage(fragmentActivity, dynamicMessageEvent, dynamicMessageMetricsAttributes);
    }

    private Observable<Fragment> getDynamicMessageFragment(final Observable<DynamicMessage> whenGetDynamicMessage, final DynamicMessageMetricsAttributes metricsAttributes) {
        return whenGetDynamicMessage.observeOn(AndroidSchedulers.mainThread()).flatMap(new Func1<DynamicMessage, Observable<Fragment>>() { // from class: com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl.1
            @Override // rx.functions.Func1
            public Observable<Fragment> call(final DynamicMessage dynamicMessage) {
                if (dynamicMessage == null) {
                    return Observable.just(null);
                }
                return Observable.just(DynamicMessageFragmentFactory.createDynamicMessageFragment(dynamicMessage, metricsAttributes, Boolean.FALSE));
            }
        }).onErrorReturn(new Func1() { // from class: com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl$$ExternalSyntheticLambda1
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return DynamicMessageOrchestratorImpl.lambda$getDynamicMessageFragment$1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Fragment lambda$getDynamicMessageFragment$1(Throwable th) {
        Log.e(TAG, "Unable to get dynamic message:" + th.getMessage());
        return null;
    }

    /* JADX INFO: renamed from: com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$inappmessaging$external$model$DynamicMessageEvent$DynamicMessageEventType;

        static {
            int[] iArr = new int[DynamicMessageEvent.DynamicMessageEventType.values().length];
            $SwitchMap$com$amazon$music$inappmessaging$external$model$DynamicMessageEvent$DynamicMessageEventType = iArr;
            try {
                iArr[DynamicMessageEvent.DynamicMessageEventType.ACTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$DynamicMessageEvent$DynamicMessageEventType[DynamicMessageEvent.DynamicMessageEventType.TRIGGER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$inappmessaging$external$model$DynamicMessageEvent$DynamicMessageEventType[DynamicMessageEvent.DynamicMessageEventType.HTTPS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator
    public Observable<Boolean> displayDynamicMessage(final FragmentActivity fragmentActivity, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes) {
        Observable<DynamicMessage> observableObserveOn;
        int i = AnonymousClass7.$SwitchMap$com$amazon$music$inappmessaging$external$model$DynamicMessageEvent$DynamicMessageEventType[event.getEventType().ordinal()];
        if (i == 1) {
            observableObserveOn = Observable.just(Boolean.TRUE).observeOn(Schedulers.io()).flatMap(new Func1() { // from class: com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl$$ExternalSyntheticLambda2
                @Override // rx.functions.Func1
                public final Object call(Object obj) {
                    return this.f$0.lambda$displayDynamicMessage$2(fragmentActivity, event, (Boolean) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread());
        } else if (i == 2) {
            observableObserveOn = Observable.just(Boolean.TRUE).observeOn(Schedulers.io()).flatMap(new Func1() { // from class: com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl$$ExternalSyntheticLambda3
                @Override // rx.functions.Func1
                public final Object call(Object obj) {
                    return this.f$0.lambda$displayDynamicMessage$3(fragmentActivity, event, metricsAttributes, (Boolean) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread());
        } else {
            if (i == 3) {
                try {
                    DeeplinksManager.get(this.context).handle(Uri.parse(event.getEvent()));
                } catch (Exception e) {
                    Log.e(TAG, "Unable to handle deeplink: " + event.getEvent() + " with exception: " + e.toString());
                }
            }
            return Observable.just(Boolean.FALSE);
        }
        return displayMessageForTemplate(observableObserveOn, event, metricsAttributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Observable lambda$displayDynamicMessage$2(FragmentActivity fragmentActivity, DynamicMessageEvent dynamicMessageEvent, Boolean bool) {
        return this.dynMsgActionHandler.getDynamicMessage(fragmentActivity, dynamicMessageEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Observable lambda$displayDynamicMessage$3(FragmentActivity fragmentActivity, DynamicMessageEvent dynamicMessageEvent, DynamicMessageMetricsAttributes dynamicMessageMetricsAttributes, Boolean bool) {
        return this.dynMsgContentHandler.getDynamicMessage(fragmentActivity, dynamicMessageEvent, dynamicMessageMetricsAttributes);
    }

    private Observable<Boolean> displayMessageForTemplate(final Observable<DynamicMessage> whenGetDynamicMessage, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes) {
        return whenGetDynamicMessage.observeOn(Schedulers.io()).flatMap(new Func1<DynamicMessage, Observable<Boolean>>() { // from class: com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl.2
            @Override // rx.functions.Func1
            public Observable<Boolean> call(final DynamicMessage dynamicMessage) {
                if (dynamicMessage != null) {
                    return DynamicMessageOrchestratorImpl.this.dynMsgTemplateHandler.displayMessageForTemplate(DynamicMessageOrchestratorImpl.this.context, dynamicMessage, event, metricsAttributes);
                }
                return Observable.just(Boolean.FALSE);
            }
        }).onErrorReturn(new Func1() { // from class: com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return DynamicMessageOrchestratorImpl.lambda$displayMessageForTemplate$4((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$displayMessageForTemplate$4(Throwable th) {
        Log.e(TAG, "Unable to get dynamic message:" + th.getMessage());
        return Boolean.FALSE;
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator
    public void loadDynamicMessage() {
        this.dynMsgCacheHandler.loadDynamicMessagesInCache().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() { // from class: com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl.3
            @Override // rx.functions.Action1
            public void call(final Boolean loadingSuccessful) {
                if (loadingSuccessful.booleanValue()) {
                    if (DynamicMessageOrchestratorImpl.sAppStartReceiverReady) {
                        DynamicMessageOrchestratorImpl.this.context.sendBroadcast(new Intent(InAppMessageBroadcasts.DISPLAY_APP_START_DYNAMIC_MESSAGE));
                    } else {
                        DynamicMessageOrchestratorImpl.sResendAppStartDynMsgBroadcast = true;
                    }
                }
            }
        }, new Action1<Throwable>() { // from class: com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl.4
            @Override // rx.functions.Action1
            public void call(Throwable throwable) {
                Log.e(DynamicMessageOrchestratorImpl.TAG, "Unable to load dynamic messages in cache.");
            }
        });
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator
    public void loadDeeplinkDynamicMessage(final DynamicMessageEvent event, final boolean isFTU) {
        this.dynMsgCacheHandler.loadDeeplinkDynamicMessage(event, isFTU).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() { // from class: com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl.5
            @Override // rx.functions.Action1
            public void call(final Boolean loadingSuccessful) {
                if (loadingSuccessful.booleanValue()) {
                    Intent intent = new Intent(InAppMessageBroadcasts.DISPLAY_DEEPLINK_DYNAMIC_MESSAGE);
                    intent.putExtra(DynamicMessageOrchestratorImpl.DYN_MSG_EVENT_KEY, event.getEvent());
                    DynamicMessageOrchestratorImpl.this.context.sendBroadcast(intent);
                }
            }
        }, new Action1<Throwable>() { // from class: com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl.6
            @Override // rx.functions.Action1
            public void call(Throwable throwable) {
                Log.e(DynamicMessageOrchestratorImpl.TAG, "Unable to load dynamic message for given deeplink");
                DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.DEEPLINK_FAILURE).withErrorMessage("Unable to handle dynamic message deeplink event: " + event.getEvent()).withTrigger(event.getEvent()).send();
                DynamicMessageOrchestratorImpl.this.context.sendBroadcast(new Intent(InAppMessageBroadcasts.DISPLAY_FAILURE_DEEPLINK_DYNAMIC_MESSAGE));
            }
        });
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator
    public void clearCache() {
        this.dynMsgCacheHandler.clearCache();
    }
}
