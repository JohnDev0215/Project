package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.billing.InAppBillingActivity;
import com.amazon.music.destination.InAppPurchaseDestination;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class InAppPurchaseDestinationHandler implements DestinationHandler<InAppPurchaseDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final InAppPurchaseDestination destination) {
        Intent startIntent = InAppBillingActivity.getStartIntent(context);
        startIntent.putExtra("com.amazon.mp3.billing.EXTRA_SCHEDULE_ID", destination.getScheduleId());
        startIntent.putExtra("com.amazon.mp3.billing.EXTRA_SKU_NAME", destination.getSku());
        startIntent.putExtra("com.amazon.mp3.billing.EXTRA_ASSOCIATES_TAG", destination.getTag());
        startIntent.putExtra("com.amazon.mp3.billing.EXTRA_REF", destination.getRef());
        context.startActivity(startIntent);
    }
}
