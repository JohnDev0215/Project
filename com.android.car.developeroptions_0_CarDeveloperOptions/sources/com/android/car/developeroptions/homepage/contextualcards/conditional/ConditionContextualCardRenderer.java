package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardRenderer;
import com.android.car.developeroptions.homepage.contextualcards.ControllerRendererPool;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

/* JADX INFO: loaded from: classes.dex */
public class ConditionContextualCardRenderer implements ContextualCardRenderer {
    private final Context mContext;
    private final ControllerRendererPool mControllerRendererPool;

    public ConditionContextualCardRenderer(Context context, ControllerRendererPool controllerRendererPool) {
        this.mContext = context;
        this.mControllerRendererPool = controllerRendererPool;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardRenderer
    public RecyclerView.ViewHolder createViewHolder(View view, int i) {
        return new ConditionalCardHolder(view);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardRenderer
    public void bindView(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard) {
        ConditionalCardHolder conditionalCardHolder = (ConditionalCardHolder) viewHolder;
        ConditionalContextualCard conditionalContextualCard = (ConditionalContextualCard) contextualCard;
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider();
        metricsFeatureProvider.visible(this.mContext, 1502, conditionalContextualCard.getMetricsConstant(), 0);
        initializePrimaryClick(conditionalCardHolder, conditionalContextualCard, metricsFeatureProvider);
        initializeView(conditionalCardHolder, conditionalContextualCard);
        initializeActionButton(conditionalCardHolder, conditionalContextualCard, metricsFeatureProvider);
    }

    private void initializePrimaryClick(ConditionalCardHolder conditionalCardHolder, final ConditionalContextualCard conditionalContextualCard, final MetricsFeatureProvider metricsFeatureProvider) {
        conditionalCardHolder.itemView.findViewById(R.id.content).setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.homepage.contextualcards.conditional.-$$Lambda$ConditionContextualCardRenderer$8tEPGdpvT3-pymbnqkvY1Uc-gWE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initializePrimaryClick$0$ConditionContextualCardRenderer(metricsFeatureProvider, conditionalContextualCard, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$initializePrimaryClick$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$initializePrimaryClick$0$ConditionContextualCardRenderer(MetricsFeatureProvider metricsFeatureProvider, ConditionalContextualCard conditionalContextualCard, View view) {
        metricsFeatureProvider.action(this.mContext, 375, conditionalContextualCard.getMetricsConstant());
        this.mControllerRendererPool.getController(this.mContext, conditionalContextualCard.getCardType()).onPrimaryClick(conditionalContextualCard);
    }

    private void initializeView(ConditionalCardHolder conditionalCardHolder, ConditionalContextualCard conditionalContextualCard) {
        conditionalCardHolder.icon.setImageDrawable(conditionalContextualCard.getIconDrawable());
        conditionalCardHolder.title.setText(conditionalContextualCard.getTitleText());
        conditionalCardHolder.summary.setText(conditionalContextualCard.getSummaryText());
    }

    private void initializeActionButton(ConditionalCardHolder conditionalCardHolder, final ConditionalContextualCard conditionalContextualCard, final MetricsFeatureProvider metricsFeatureProvider) {
        CharSequence actionText = conditionalContextualCard.getActionText();
        boolean z = !TextUtils.isEmpty(actionText);
        Button button = (Button) conditionalCardHolder.itemView.findViewById(R.id.first_action);
        if (z) {
            button.setVisibility(0);
            button.setText(actionText);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.homepage.contextualcards.conditional.-$$Lambda$ConditionContextualCardRenderer$hSFo8awc7pJU-bKq64ARZKD5elw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initializeActionButton$1$ConditionContextualCardRenderer(metricsFeatureProvider, conditionalContextualCard, view);
                }
            });
            return;
        }
        button.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$initializeActionButton$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$initializeActionButton$1$ConditionContextualCardRenderer(MetricsFeatureProvider metricsFeatureProvider, ConditionalContextualCard conditionalContextualCard, View view) {
        metricsFeatureProvider.action(view.getContext(), 376, conditionalContextualCard.getMetricsConstant());
        this.mControllerRendererPool.getController(this.mContext, conditionalContextualCard.getCardType()).onActionClick(conditionalContextualCard);
    }

    public static class ConditionalCardHolder extends RecyclerView.ViewHolder {
        public final ImageView icon;
        public final TextView summary;
        public final TextView title;

        public ConditionalCardHolder(View view) {
            super(view);
            this.icon = (ImageView) view.findViewById(android.R.id.icon);
            this.title = (TextView) view.findViewById(android.R.id.title);
            this.summary = (TextView) view.findViewById(android.R.id.summary);
        }
    }
}
