package com.amazon.podcast;

import android.content.Context;
import com.amazon.music.destination.PodcastDestination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes5.dex */
public class PodcastDestinationHandler implements DestinationHandler<PodcastDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, PodcastDestination destination) {
        context.startActivity(PodcastDeeplinks.getIntentForPodcast(context, destination.getDeeplinkUrl()));
    }
}
