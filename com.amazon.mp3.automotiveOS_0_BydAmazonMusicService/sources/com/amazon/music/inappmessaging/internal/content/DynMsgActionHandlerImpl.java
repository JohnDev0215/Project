package com.amazon.music.inappmessaging.internal.content;

import android.app.Activity;
import android.util.Log;
import com.amazon.music.inappmessaging.external.AssociateTagVendor;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.external.IAMCredentials;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.HandleActionRequest;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.inappmessaging.internal.model.HandleActionResponse;
import com.amazon.music.inappmessaging.internal.service.InAppMessagesApi;
import com.amazon.music.inappmessaging.internal.util.DynMsgMessageFormatter;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import java.util.ArrayList;
import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class DynMsgActionHandlerImpl implements DynMsgActionHandler {
    public static final ArrayList<String> ASSOCIATE_TAG_ALLOW_LIST;
    private static final String CREATE_SUBSCRIPTION = "action:/createSubscription";
    private static final String TAG;
    private final AssociateTagVendor associateTagVendor;
    private final Observable<IAMCredentials> credentials;
    private final Observable<DeviceInformation> deviceInformation;
    private final DynMsgNativeContentHandler dynMsgNativeContentHandler;
    private final InAppMessagesApi inAppMessagesApiClient;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Observable lambda$callHandleActionAPI$1(Observable observable) {
        return observable;
    }

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        ASSOCIATE_TAG_ALLOW_LIST = arrayList;
        arrayList.add(null);
        arrayList.add("google-play");
        arrayList.add("androidmarket01-20");
        arrayList.add("androidmarket00-20");
        arrayList.add("amazoukandro-21");
        arrayList.add("android-1-21");
        arrayList.add("amamp3andmob-21");
        arrayList.add("amamp3andmob2-21");
        arrayList.add("androidfr-21");
        arrayList.add("fr-android-1-21");
        arrayList.add("amazonitandro-21");
        arrayList.add("amazonesandro-21");
        arrayList.add("amazojpandro-21");
        TAG = DynMsgActionHandlerImpl.class.getSimpleName();
    }

    public DynMsgActionHandlerImpl(final InAppMessagesApi inAppMessagesApiClient, final Observable<IAMCredentials> iamCredentials, final Observable<DeviceInformation> deviceInformation, final AssociateTagVendor associateTagVendor, final DynMsgNativeContentHandler dynMsgNativeContentHandler) {
        this.inAppMessagesApiClient = inAppMessagesApiClient;
        this.credentials = iamCredentials;
        this.deviceInformation = deviceInformation;
        this.associateTagVendor = associateTagVendor;
        this.dynMsgNativeContentHandler = dynMsgNativeContentHandler;
    }

    @Override // com.amazon.music.inappmessaging.internal.content.DynMsgActionHandler
    public Observable<DynamicMessage> getDynamicMessage(final Activity activity, final DynamicMessageEvent event) {
        if (event.getEvent().startsWith(CREATE_SUBSCRIPTION) && !ASSOCIATE_TAG_ALLOW_LIST.contains(this.associateTagVendor.getAssociateTag())) {
            DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.INVALID_ASSOCIATE_TAG).withErrorMessage("Fallback due to invalid associate tag: " + this.associateTagVendor.getAssociateTag()).withEvent(event.getEvent()).send();
            return Observable.just(this.dynMsgNativeContentHandler.getDynamicMessage(activity, event));
        }
        return callHandleActionAPI(event).flatMap(new Func1<HandleActionResponse, Observable<DynamicMessage>>() { // from class: com.amazon.music.inappmessaging.internal.content.DynMsgActionHandlerImpl.1
            @Override // rx.functions.Func1
            public Observable<DynamicMessage> call(final HandleActionResponse response) {
                DynamicMessage dynamicMessage = response.nextMessage;
                if (dynamicMessage == null) {
                    return Observable.just(DynMsgActionHandlerImpl.this.dynMsgNativeContentHandler.getDynamicMessage(activity, event));
                }
                if (DynMsgMessageFormatter.templateAttributesContainPlaceholder(dynamicMessage.templateAttributes)) {
                    DynMsgMessageFormatter.formatDynamicMessage(response.nextMessage, event);
                }
                return Observable.just(response.nextMessage);
            }
        }).onErrorReturn(new Func1() { // from class: com.amazon.music.inappmessaging.internal.content.DynMsgActionHandlerImpl$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$getDynamicMessage$0(event, activity, (Throwable) obj);
            }
        }).subscribeOn(Schedulers.io());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DynamicMessage lambda$getDynamicMessage$0(DynamicMessageEvent dynamicMessageEvent, Activity activity, Throwable th) {
        Log.e(TAG, "Error in getting dynamic messages from handle action API: " + th.getMessage());
        DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.HANDLE_ACTION_API_ERROR).withErrorMessage(th.getMessage()).withEvent(dynamicMessageEvent.getEvent()).send();
        return this.dynMsgNativeContentHandler.getDynamicMessage(activity, dynamicMessageEvent);
    }

    private Observable<HandleActionResponse> callHandleActionAPI(final DynamicMessageEvent event) {
        return Observable.zip(this.credentials, this.deviceInformation, new Func2<IAMCredentials, DeviceInformation, Observable<HandleActionResponse>>() { // from class: com.amazon.music.inappmessaging.internal.content.DynMsgActionHandlerImpl.2
            @Override // rx.functions.Func2
            public Observable<HandleActionResponse> call(final IAMCredentials iamCredentials, final DeviceInformation deviceInformation) {
                try {
                    return DynMsgActionHandlerImpl.this.inAppMessagesApiClient.handleAction(iamCredentials, deviceInformation, new HandleActionRequest(event));
                } catch (Exception e) {
                    Log.e(DynMsgActionHandlerImpl.TAG, "Error in calling handle action api: " + e.toString());
                    throw Exceptions.propagate(e);
                }
            }
        }).flatMap(new Func1() { // from class: com.amazon.music.inappmessaging.internal.content.DynMsgActionHandlerImpl$$ExternalSyntheticLambda1
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return DynMsgActionHandlerImpl.lambda$callHandleActionAPI$1((Observable) obj);
            }
        }).subscribeOn(Schedulers.io());
    }
}
