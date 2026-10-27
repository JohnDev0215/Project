package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.AlexaDestination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class AlexaDestinationHandler implements DestinationHandler<AlexaDestination> {
    private static String TAG = "AlexaDestinationHandler";

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final AlexaDestination destination) {
        if (!AmazonApplication.getCapabilities().isAlexaEnabled()) {
            Log.info(TAG, "Received an Alexa deeplink, but Alexa is disabled.");
            return;
        }
        Intent startIntent = MusicHomeActivity.getStartIntent(context);
        startIntent.putExtra("com.amazon.mp3.navigate.to.screen", LastViewedScreenUtil.LastViewedScreen.ALEXA);
        startIntent.setFlags(268435456);
        startIntent.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), destination.getRef());
        startIntent.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), destination.getTag());
        context.startActivity(startIntent);
    }
}
