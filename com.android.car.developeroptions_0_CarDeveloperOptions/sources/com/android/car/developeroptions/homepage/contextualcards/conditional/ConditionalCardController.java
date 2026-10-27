package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.Context;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;

/* JADX INFO: loaded from: classes.dex */
public interface ConditionalCardController {
    ContextualCard buildContextualCard();

    long getId();

    boolean isDisplayable();

    void onActionClick();

    void onPrimaryClick(Context context);

    void startMonitoringStateChange();

    void stopMonitoringStateChange();
}
