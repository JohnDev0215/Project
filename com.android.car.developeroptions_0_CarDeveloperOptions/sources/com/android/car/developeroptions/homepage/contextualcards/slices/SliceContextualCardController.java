package com.android.car.developeroptions.homepage.contextualcards.slices;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.CardDatabaseHelper;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardController;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardFeedbackDialog;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardUpdateListener;
import com.android.car.developeroptions.homepage.contextualcards.logging.ContextualCardLogUtils;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.utils.ThreadUtils;

/* JADX INFO: loaded from: classes.dex */
public class SliceContextualCardController implements ContextualCardController {
    private final Context mContext;

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardController
    public void onActionClick(ContextualCard contextualCard) {
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardController
    public void onPrimaryClick(ContextualCard contextualCard) {
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardController
    public void setCardUpdateListener(ContextualCardUpdateListener contextualCardUpdateListener) {
    }

    public SliceContextualCardController(Context context) {
        this.mContext = context;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardController
    public void onDismissed(final ContextualCard contextualCard) {
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.homepage.contextualcards.slices.-$$Lambda$SliceContextualCardController$vb-iuhlz6HQE8my5X3o8_uTfiDs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDismissed$0$SliceContextualCardController(contextualCard);
            }
        });
        showFeedbackDialog(contextualCard);
        FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider().action(this.mContext, 1665, ContextualCardLogUtils.buildCardDismissLog(contextualCard));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onDismissed$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onDismissed$0$SliceContextualCardController(ContextualCard contextualCard) {
        CardDatabaseHelper.getInstance(this.mContext).markContextualCardAsDismissed(this.mContext, contextualCard.getName());
    }

    void showFeedbackDialog(ContextualCard contextualCard) {
        String string = this.mContext.getString(R.string.config_contextual_card_feedback_email);
        if (isFeedbackEnabled(string)) {
            Intent intent = new Intent(this.mContext, (Class<?>) ContextualCardFeedbackDialog.class);
            intent.putExtra("card_name", getSimpleCardName(contextualCard));
            intent.putExtra("feedback_email", string);
            intent.addFlags(268435456);
            this.mContext.startActivity(intent);
        }
    }

    boolean isFeedbackEnabled(String str) {
        return !TextUtils.isEmpty(str) && Build.IS_DEBUGGABLE;
    }

    private String getSimpleCardName(ContextualCard contextualCard) {
        return contextualCard.getName().split("/")[r0.length - 1];
    }
}
