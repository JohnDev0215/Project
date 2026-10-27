package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.music.destination.HomeDestination;
import com.amazon.music.destination.IamDestination;
import com.amazon.music.inappmessaging.external.IAMRequestBuilder;
import com.amazon.music.inappmessaging.external.WeblabHelper;
import com.amazon.music.inappmessaging.external.model.DynamicMessageContextualEventBuilder;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.router.DestinationHandler;

/* JADX INFO: loaded from: classes3.dex */
public class IamDestinationHandler implements DestinationHandler<IamDestination> {
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(final Context context, final IamDestination destination) {
        if (WeblabHelper.getInstance().isSplashV2Enabled()) {
            DynamicMessageContextualEventBuilder.Builder builder = DynamicMessageContextualEventBuilder.builder(Trigger.APP_START);
            DynamicMessageEvent dynamicMessageEventBuild = builder.build();
            if (destination.getCategory() != null) {
                dynamicMessageEventBuild = builder.withCategoryName(destination.getCategory()).build();
            }
            if (destination.getCampaignId() != null) {
                dynamicMessageEventBuild = builder.withCampaignName(destination.getCampaignId()).build();
            }
            Intent intent = new HomeDestinationHandler().getIntent(context, new HomeDestination(null, null, destination.getRef(), destination.getTag()));
            intent.putExtra("com.amazon.mp3.iam.DM_DEEPLINK_REQUEST", dynamicMessageEventBuild.getEvent());
            context.startActivity(intent);
        }
        String json = new IAMRequestBuilder().shouldThrottle(false).withCampaignId(destination.getCampaignId()).withCategory(destination.getCategory()).build().toJson();
        Intent intent2 = new HomeDestinationHandler().getIntent(context, new HomeDestination(null, null, destination.getRef(), destination.getTag()));
        intent2.putExtra("com.amazon.mp3.iam.IAM_REQUEST", json);
        context.startActivity(intent2);
    }
}
