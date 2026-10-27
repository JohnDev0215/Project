package com.amazon.music.inappmessaging.internal.handler;

import android.content.Context;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Ribbon;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public class DigitalMusicDynMsgRibbonTemplateHandler implements TemplateHandler {
    private static volatile DigitalMusicDynMsgRibbonTemplateHandler digitalMusicDynMsgRibbonTemplateHandler;

    private DigitalMusicDynMsgRibbonTemplateHandler() {
    }

    public static DigitalMusicDynMsgRibbonTemplateHandler get() {
        if (digitalMusicDynMsgRibbonTemplateHandler == null) {
            synchronized (DigitalMusicDynMsgRibbonTemplateHandler.class) {
                if (digitalMusicDynMsgRibbonTemplateHandler == null) {
                    digitalMusicDynMsgRibbonTemplateHandler = new DigitalMusicDynMsgRibbonTemplateHandler();
                }
            }
        }
        return digitalMusicDynMsgRibbonTemplateHandler;
    }

    @Override // com.amazon.music.inappmessaging.internal.handler.TemplateHandler
    public Observable<Boolean> displayMessage(final Context context, final DynamicMessage message, final DynamicMessageMetricsAttributes metricsAttributes) {
        return RibbonSurfaceHandler.get().displayMessage(context, Ribbon.fromDynamicMessage(message), metricsAttributes);
    }
}
