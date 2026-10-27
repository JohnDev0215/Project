package com.amazon.music.inappmessaging.internal.handler;

import android.content.Context;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public interface TemplateHandler {
    Observable<Boolean> displayMessage(final Context context, final DynamicMessage message, final DynamicMessageMetricsAttributes metricsAttributes);
}
