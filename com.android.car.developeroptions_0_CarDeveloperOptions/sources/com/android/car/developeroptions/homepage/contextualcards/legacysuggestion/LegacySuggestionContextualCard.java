package com.android.car.developeroptions.homepage.contextualcards.legacysuggestion;

import android.app.PendingIntent;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;

/* JADX INFO: loaded from: classes.dex */
public class LegacySuggestionContextualCard extends ContextualCard {
    private final PendingIntent mPendingIntent;

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCard
    public int getCardType() {
        return 2;
    }

    public LegacySuggestionContextualCard(Builder builder) {
        super(builder);
        this.mPendingIntent = builder.mPendingIntent;
    }

    public PendingIntent getPendingIntent() {
        return this.mPendingIntent;
    }

    public static class Builder extends ContextualCard.Builder {
        private PendingIntent mPendingIntent;

        @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCard.Builder
        public /* bridge */ /* synthetic */ ContextualCard.Builder setCardType(int i) {
            setCardType(i);
            throw null;
        }

        public Builder setPendingIntent(PendingIntent pendingIntent) {
            this.mPendingIntent = pendingIntent;
            return this;
        }

        @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCard.Builder
        public Builder setCardType(int i) {
            throw new IllegalArgumentException("Cannot change card type for " + Builder.class.getName());
        }

        @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCard.Builder
        public LegacySuggestionContextualCard build() {
            return new LegacySuggestionContextualCard(this);
        }
    }
}
