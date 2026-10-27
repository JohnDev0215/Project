package com.amazon.music.inappmessaging.internal.content;

import android.app.Activity;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public interface DynMsgActionHandler {
    Observable<DynamicMessage> getDynamicMessage(final Activity activity, final DynamicMessageEvent event);
}
