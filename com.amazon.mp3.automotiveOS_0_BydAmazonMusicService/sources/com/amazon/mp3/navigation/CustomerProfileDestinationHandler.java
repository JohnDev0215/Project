package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.CustomerProfileDestination;
import com.amazon.music.router.DestinationHandler;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class CustomerProfileDestinationHandler implements DestinationHandler<CustomerProfileDestination> {
    private static final String TAG = "CustomerProfileDestinationHandler";

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final CustomerProfileDestination profileDestination) {
        BootstrapSingletonTask.get().registerFeatureFlagObserver().subscribe(new Action1<Void>() { // from class: com.amazon.mp3.navigation.CustomerProfileDestinationHandler.1
            @Override // rx.functions.Action1
            public void call(Void aVoid) {
                if (AmazonApplication.getCapabilities().isCustomerProfileSupported()) {
                    Log.debug(CustomerProfileDestinationHandler.TAG, "Customer profile supported, deeplink to profile detected");
                    String profileKey = profileDestination.getProfileKey();
                    Intent intentCreateIntent = new BrushFragmentNavigation.Builder("profile/full/" + profileKey).withProfileId(profileKey).withRefMarker(profileDestination.getRef()).withAssociateTag(profileDestination.getTag()).createIntent(context);
                    intentCreateIntent.setFlags(268435456);
                    context.startActivity(intentCreateIntent);
                    return;
                }
                Log.debug(CustomerProfileDestinationHandler.TAG, "Profiles feature is turned off, not initializing customer's social profile");
            }
        });
    }
}
