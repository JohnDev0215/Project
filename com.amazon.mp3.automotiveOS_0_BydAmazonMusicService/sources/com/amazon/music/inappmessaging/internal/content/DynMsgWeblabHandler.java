package com.amazon.music.inappmessaging.internal.content;

import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.internal.model.Weblab;
import java.util.List;
import rx.Observable;

/* JADX INFO: loaded from: classes4.dex */
public interface DynMsgWeblabHandler {
    Observable<Boolean> recordWeblabTriggers(final List<Weblab> weblabs, final DynamicMessageEvent event);
}
