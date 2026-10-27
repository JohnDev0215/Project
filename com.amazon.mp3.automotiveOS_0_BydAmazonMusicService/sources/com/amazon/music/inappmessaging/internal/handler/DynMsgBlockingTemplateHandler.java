package com.amazon.music.inappmessaging.internal.handler;

import android.content.Context;
import android.content.Intent;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.google.gson.Gson;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public class DynMsgBlockingTemplateHandler implements TemplateHandler {
    private static volatile DynMsgBlockingTemplateHandler dynMsgBlockingTemplateHandler;
    private static final Gson gsonObject = new Gson();
    private static Intent intent = new Intent();

    private DynMsgBlockingTemplateHandler() {
    }

    public static DynMsgBlockingTemplateHandler get() {
        if (dynMsgBlockingTemplateHandler == null) {
            synchronized (DynMsgBlockingTemplateHandler.class) {
                if (dynMsgBlockingTemplateHandler == null) {
                    dynMsgBlockingTemplateHandler = new DynMsgBlockingTemplateHandler();
                }
            }
        }
        return dynMsgBlockingTemplateHandler;
    }

    @Override // com.amazon.music.inappmessaging.internal.handler.TemplateHandler
    public Observable<Boolean> displayMessage(final Context context, final DynamicMessage message, final DynamicMessageMetricsAttributes metricsAttributes) {
        Gson gson = gsonObject;
        String json = gson.toJson(message);
        String json2 = gson.toJson(metricsAttributes);
        intent.setAction(InAppMessageBroadcasts.DISPLAY_BLOCKING_DYNAMIC_MESSAGE);
        intent.putExtra("json", json);
        intent.putExtra("metricsAttributesJson", json2);
        context.sendBroadcast(intent);
        return Observable.just(Boolean.TRUE);
    }
}
