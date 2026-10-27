package com.amazon.mp3.prime.requester;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: HybridQueueTrackRequester.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J2\u0010\u000b\u001a\u00020\t2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH&¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/prime/requester/HybridQueueTrackRequester;", "", "Landroid/content/Context;", "context", "", "doToggle", "", "asinToSkip", "Lkotlin/Function0;", "", "onFinished", "requestHybridQueueAndUpdateDb", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface HybridQueueTrackRequester {
    void requestHybridQueueAndUpdateDb(Context context, boolean doToggle, String asinToSkip, Function0<Unit> onFinished);
}
