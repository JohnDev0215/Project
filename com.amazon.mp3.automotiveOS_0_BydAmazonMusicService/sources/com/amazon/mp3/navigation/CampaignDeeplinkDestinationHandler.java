package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity;
import com.amazon.mp3.navigation.webtargetconverter.CampaignWebTargetConverter;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.music.destination.CampaignDestination;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.router.DestinationHandler;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CampaignDeeplinkDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0002H\u0016J$\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u0011H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/navigation/CampaignDeeplinkDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/CampaignDestination;", "webTargetConverter", "Lcom/amazon/mp3/navigation/webtargetconverter/CampaignWebTargetConverter;", "activityStacker", "Lcom/amazon/mp3/navigation/ActivityStacker;", "(Lcom/amazon/mp3/navigation/webtargetconverter/CampaignWebTargetConverter;Lcom/amazon/mp3/navigation/ActivityStacker;)V", "navigateTo", "", "context", "Landroid/content/Context;", "campaignDestination", "sendUIPageViewEvent", "refTag", "", "params", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CampaignDeeplinkDestinationHandler implements DestinationHandler<CampaignDestination> {
    private final ActivityStacker activityStacker;
    private final CampaignWebTargetConverter webTargetConverter;

    public CampaignDeeplinkDestinationHandler(CampaignWebTargetConverter webTargetConverter, ActivityStacker activityStacker) {
        Intrinsics.checkNotNullParameter(webTargetConverter, "webTargetConverter");
        Intrinsics.checkNotNullParameter(activityStacker, "activityStacker");
        this.webTargetConverter = webTargetConverter;
        this.activityStacker = activityStacker;
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, CampaignDestination campaignDestination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(campaignDestination, "campaignDestination");
        String ref = campaignDestination.getRef();
        Intrinsics.checkNotNullExpressionValue(ref, "campaignDestination.ref");
        Map<String, String> queryParameters = campaignDestination.getQueryParameters();
        Intrinsics.checkNotNullExpressionValue(queryParameters, "campaignDestination.queryParameters");
        sendUIPageViewEvent(ref, queryParameters);
        Intent startIntent = UnlimitedUpsellActivity.getStartIntent(context, this.webTargetConverter.convert(context, campaignDestination));
        Intent intentChainIntents = this.activityStacker.chainIntents(PrimeActivityFactory.getHomeIntent(context), startIntent);
        startIntent.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), campaignDestination.getRef());
        intentChainIntents.putExtra("com.amazon.mp3.navigate.to.nextActivityScreen", true);
        intentChainIntents.setFlags(268435456);
        context.startActivity(intentChainIntents);
    }

    private final void sendUIPageViewEvent(String refTag, Map<String, String> params) {
        UiPageViewEvent.Builder builderWithExternalReference = UiPageViewEvent.builder("InAppCampaign").withExternalReference(refTag);
        String str = params.get("campaignType");
        String str2 = params.get("entityAsin");
        if (str != null) {
            if (str2 != null) {
                StringBuilder sb = new StringBuilder();
                sb.append((Object) str);
                sb.append('_');
                sb.append((Object) str2);
                builderWithExternalReference.withPageSubType(sb.toString());
            } else {
                builderWithExternalReference.withPageSubType(str);
            }
        }
        MetricsHolder.getManager().handleEvent(builderWithExternalReference.build());
    }
}
