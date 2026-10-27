package com.amazon.music.inappmessaging.internal.handler;

import android.content.Context;
import android.content.Intent;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Ribbon;
import com.google.gson.Gson;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public class RibbonSurfaceHandler {
    private static final Gson gsonObject = new Gson();
    private static Intent intent = new Intent();
    private static volatile RibbonSurfaceHandler ribbonSurfaceHandler;

    public static RibbonSurfaceHandler get() {
        if (ribbonSurfaceHandler == null) {
            synchronized (RibbonSurfaceHandler.class) {
                if (ribbonSurfaceHandler == null) {
                    ribbonSurfaceHandler = new RibbonSurfaceHandler();
                }
            }
        }
        return ribbonSurfaceHandler;
    }

    private RibbonSurfaceHandler() {
    }

    public Observable<Boolean> displayMessage(final Context context, final Ribbon ribbon, final DynamicMessageMetricsAttributes metricsAttributes) {
        Gson gson = gsonObject;
        String json = gson.toJson(ribbon);
        String json2 = gson.toJson(metricsAttributes);
        intent.setAction(InAppMessageBroadcasts.DISPLAY_NOTIFICATION_RIBBON);
        intent.putExtra("json", json);
        intent.putExtra("metricsAttributesJson", json2);
        context.sendBroadcast(intent);
        return Observable.just(Boolean.TRUE);
    }
}
