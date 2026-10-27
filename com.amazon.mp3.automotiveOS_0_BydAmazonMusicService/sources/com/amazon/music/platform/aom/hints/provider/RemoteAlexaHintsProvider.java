package com.amazon.music.platform.aom.hints.provider;

import com.amazon.music.platform.aom.Outcome;
import com.amazon.music.platform.aom.hints.AlexaHints;
import com.amazon.music.platform.aom.hints.dae.DAE;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: RemoteAlexaHintsProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"Lcom/amazon/music/platform/aom/hints/provider/RemoteAlexaHintsProvider;", "", "getAlexaHints", "Lcom/amazon/music/platform/aom/Outcome;", "Lcom/amazon/music/platform/aom/hints/AlexaHints;", "dae", "Lcom/amazon/music/platform/aom/hints/dae/DAE;", "(Lcom/amazon/music/platform/aom/hints/dae/DAE;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface RemoteAlexaHintsProvider {
    Object getAlexaHints(DAE dae, Continuation<? super Outcome<AlexaHints>> continuation);
}
