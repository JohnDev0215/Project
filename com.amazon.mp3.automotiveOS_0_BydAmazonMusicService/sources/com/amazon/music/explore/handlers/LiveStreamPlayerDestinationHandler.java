package com.amazon.music.explore.handlers;

import android.content.Context;
import com.amazon.music.destination.LiveStreamPlayerDestination;
import com.amazon.music.explore.ExploreFactory;
import com.amazon.music.router.AsyncDestinationHandler;

/* JADX INFO: loaded from: classes4.dex */
public class LiveStreamPlayerDestinationHandler implements AsyncDestinationHandler<LiveStreamPlayerDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, LiveStreamPlayerDestination liveStreamPlayerDestination) {
        navigateTo(context, liveStreamPlayerDestination, (Runnable) null);
    }

    @Override // com.amazon.music.router.AsyncDestinationHandler
    public void navigateTo(Context context, LiveStreamPlayerDestination liveStreamPlayerDestination, Runnable runnable) {
        ExploreFactory.openLiveStreamPage(context, liveStreamPlayerDestination.getLiveStreamId(), liveStreamPlayerDestination.getRef(), liveStreamPlayerDestination.getTag());
        if (runnable != null) {
            runnable.run();
        }
    }
}
