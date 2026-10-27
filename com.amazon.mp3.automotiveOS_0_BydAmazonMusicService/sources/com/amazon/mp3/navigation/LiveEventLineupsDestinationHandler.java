package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.util.Log;
import com.amazon.music.alps.gating.AlpsFeatureGating;
import com.amazon.music.alps.liveevent.LiveEventDetailFragment;
import com.amazon.music.destination.LiveEventLineupsDestination;
import com.amazon.music.router.DestinationHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveEventLineupsDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/navigation/LiveEventLineupsDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/LiveEventLineupsDestination;", "()V", "navigateTo", "", "context", "Landroid/content/Context;", "destination", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LiveEventLineupsDestinationHandler implements DestinationHandler<LiveEventLineupsDestination> {
    private static final String TAG = LiveEventLineupsDestinationHandler.class.getSimpleName();

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, LiveEventLineupsDestination destination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (!AmazonApplication.getCapabilities().isLiveEventEnabled() || !AlpsFeatureGating.LIVE_EVENT_DETAIL.isEnabled()) {
            Log.warning(TAG, "Ignoring navigation to LiveEventLineups; disabled");
            return;
        }
        Intent startIntent = MusicHomeActivity.getStartIntent(context);
        LiveEventDetailFragment.Companion companion = LiveEventDetailFragment.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(startIntent, "this");
        companion.initLineupsIntent(startIntent, destination);
        startIntent.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), destination.getRef());
        startIntent.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), destination.getTag());
        startIntent.addFlags(268435456);
        context.startActivity(startIntent);
    }
}
