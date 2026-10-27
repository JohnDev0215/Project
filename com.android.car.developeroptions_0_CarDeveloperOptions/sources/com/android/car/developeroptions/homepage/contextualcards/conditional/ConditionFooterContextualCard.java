package com.android.car.developeroptions.homepage.contextualcards.conditional;

import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;

/* JADX INFO: loaded from: classes.dex */
public class ConditionFooterContextualCard extends ContextualCard {
    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCard
    public int getCardType() {
        return 5;
    }

    private ConditionFooterContextualCard(Builder builder) {
        super(builder);
    }

    public static class Builder extends ContextualCard.Builder {
        @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCard.Builder
        public /* bridge */ /* synthetic */ ContextualCard.Builder setCardType(int i) {
            setCardType(i);
            throw null;
        }

        @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCard.Builder
        public Builder setCardType(int i) {
            throw new IllegalArgumentException("Cannot change card type for " + Builder.class.getName());
        }

        @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCard.Builder
        public ConditionFooterContextualCard build() {
            return new ConditionFooterContextualCard(this);
        }
    }
}
