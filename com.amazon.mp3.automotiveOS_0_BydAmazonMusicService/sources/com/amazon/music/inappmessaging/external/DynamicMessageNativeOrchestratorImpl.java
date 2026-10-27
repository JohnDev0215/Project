package com.amazon.music.inappmessaging.external;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.internal.content.DynMsgNativeContentHandler;
import com.amazon.music.inappmessaging.internal.handler.DynamicMessageTemplateHandler;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageNativeOrchestratorImpl extends DynamicMessageOrchestrator {
    private static final String TAG = "DynamicMessageNativeOrchestratorImpl";
    private final Context context;
    private final DynMsgNativeContentHandler dynMsgNativeContentHandler;
    private final DynamicMessageTemplateHandler dynMsgTemplateHandler;

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator
    public void clearCache() {
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator
    public void loadDeeplinkDynamicMessage(DynamicMessageEvent event, boolean isFTU) {
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator
    public void loadDynamicMessage() {
    }

    protected DynamicMessageNativeOrchestratorImpl(final Context context, final DynMsgNativeContentHandler dynMsgNativeContentHandler, final DynamicMessageTemplateHandler dynMsgTemplateHandler) {
        this.context = context;
        this.dynMsgNativeContentHandler = dynMsgNativeContentHandler;
        this.dynMsgTemplateHandler = dynMsgTemplateHandler;
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator
    public Observable<Fragment> displayNonBlockingDynamicMessage(FragmentActivity fragmentActivity, DynamicMessageEvent event, DynamicMessageMetricsAttributes metricsAttributes) {
        if (event.isCritical()) {
            return Observable.just(DynamicMessageFragmentFactory.createDynamicMessageFragment(this.dynMsgNativeContentHandler.getDynamicMessage(fragmentActivity, event), metricsAttributes, Boolean.FALSE));
        }
        return Observable.just(null);
    }

    @Override // com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator
    public Observable<Boolean> displayDynamicMessage(final FragmentActivity fragmentActivity, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes) {
        if (event.isCritical()) {
            return displayMessageForTemplate(this.dynMsgNativeContentHandler.getDynamicMessage(fragmentActivity, event), event, metricsAttributes);
        }
        return Observable.just(Boolean.FALSE);
    }

    private Observable<Boolean> displayMessageForTemplate(final DynamicMessage dynamicMessage, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes) {
        if (dynamicMessage == null) {
            DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.OTHER).withErrorMessage("No native content available for critical event: " + event).withTrigger(event.getEvent()).send();
            return Observable.just(Boolean.FALSE);
        }
        return this.dynMsgTemplateHandler.displayMessageForTemplate(this.context, dynamicMessage, event, metricsAttributes);
    }
}
