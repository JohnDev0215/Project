package com.amazon.music.inappmessaging.internal.cache;

import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public interface DynMsgCacheHandler {
    void clearCache();

    Observable<DynamicMessage> getDynamicMessage(final DynamicMessageEvent event);

    Observable<Boolean> loadDeeplinkDynamicMessage(final DynamicMessageEvent event, final boolean isFTU);

    Observable<Boolean> loadDynamicMessagesInCache();
}
