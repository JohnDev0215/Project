package com.amazon.mp3.navigation.webtargetconverter;

import android.content.Context;
import com.amazon.mp3.upsellweb.DigitalSubscriptionsWebTargetBuilder;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.destination.DigitalSubscriptionsDestination;

/* JADX INFO: loaded from: classes3.dex */
public class DigitalSubscriptionsWebTargetConverter {
    public WebTarget convert(final Context context, final DigitalSubscriptionsDestination destination) {
        return new DigitalSubscriptionsWebTargetBuilder(context).withSegments(destination.getSegments()).withNotNowRedirectURL().withRedirectURL().withQueryParamsToForward(destination.getQueryParameters()).build();
    }
}
