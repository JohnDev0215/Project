package com.amazon.music.inappmessaging.internal.handler;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.FullScreenDeeplinkTemplate;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageViewUtil;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public class DynMsgDeeplinkTemplateHandler implements TemplateHandler {
    private static final String TAG = "DynMsgDeeplinkTemplateHandler";
    private static volatile DynMsgDeeplinkTemplateHandler dynMsgDeeplinkTemplateHandler;

    private DynMsgDeeplinkTemplateHandler() {
    }

    public static DynMsgDeeplinkTemplateHandler get() {
        if (dynMsgDeeplinkTemplateHandler == null) {
            synchronized (DynMsgDeeplinkTemplateHandler.class) {
                if (dynMsgDeeplinkTemplateHandler == null) {
                    dynMsgDeeplinkTemplateHandler = new DynMsgDeeplinkTemplateHandler();
                }
            }
        }
        return dynMsgDeeplinkTemplateHandler;
    }

    @Override // com.amazon.music.inappmessaging.internal.handler.TemplateHandler
    public Observable<Boolean> displayMessage(Context context, DynamicMessage message, DynamicMessageMetricsAttributes metricsAttributes) {
        try {
            FullScreenDeeplinkTemplate fullScreenDeeplinkTemplateFromDynamicMessage = FullScreenDeeplinkTemplate.fromDynamicMessage(message);
            DeeplinksManager.get(context).handle(Uri.parse(fullScreenDeeplinkTemplateFromDynamicMessage.deeplink));
            DynamicMessageViewUtil.sendUIPageViewEvent(fullScreenDeeplinkTemplateFromDynamicMessage.messageRef, false);
            return Observable.just(Boolean.TRUE);
        } catch (Exception e) {
            Log.e(TAG, "Unable to handle deeplink due to: " + e.toString());
            return Observable.just(Boolean.FALSE);
        }
    }
}
