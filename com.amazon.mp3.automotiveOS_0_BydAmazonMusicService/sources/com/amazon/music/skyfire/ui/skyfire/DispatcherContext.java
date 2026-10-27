package com.amazon.music.skyfire.ui.skyfire;

import com.amazon.music.skyfire.SkyFireApplication;
import kotlin.Metadata;

/* JADX INFO: compiled from: DispatcherContext.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "", "listenerId", "", "getListenerId", "()Ljava/lang/String;", "skyFireApplication", "Lcom/amazon/music/skyfire/SkyFireApplication;", "getSkyFireApplication", "()Lcom/amazon/music/skyfire/SkyFireApplication;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface DispatcherContext {
    String getListenerId();

    SkyFireApplication getSkyFireApplication();
}
