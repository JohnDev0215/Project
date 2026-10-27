package com.android.car.developeroptions.homepage.contextualcards;

/* JADX INFO: loaded from: classes.dex */
public interface ContextualCardController {
    void onActionClick(ContextualCard contextualCard);

    void onDismissed(ContextualCard contextualCard);

    void onPrimaryClick(ContextualCard contextualCard);

    void setCardUpdateListener(ContextualCardUpdateListener contextualCardUpdateListener);
}
