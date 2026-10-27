package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.music.destination.MerchDetailDestination;
import com.amazon.music.explore.ExploreFactory;
import com.amazon.music.router.DestinationHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MerchDetailDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/navigation/MerchDetailDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/MerchDetailDestination;", "()V", "navigateTo", "", "context", "Landroid/content/Context;", "destination", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MerchDetailDestinationHandler implements DestinationHandler<MerchDetailDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, MerchDetailDestination destination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intent startIntent = MusicHomeActivity.getStartIntent(context);
        ExploreFactory.initMerchDetailIntent(startIntent, destination);
        startIntent.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), destination.getRef());
        startIntent.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), destination.getTag());
        startIntent.addFlags(268435456);
        context.startActivity(startIntent);
    }
}
