package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardRenderer;
import com.android.car.developeroptions.homepage.contextualcards.ControllerRendererPool;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

/* JADX INFO: loaded from: classes.dex */
public class ConditionFooterContextualCardRenderer implements ContextualCardRenderer {
    private final Context mContext;
    private final ControllerRendererPool mControllerRendererPool;

    public ConditionFooterContextualCardRenderer(Context context, ControllerRendererPool controllerRendererPool) {
        this.mContext = context;
        this.mControllerRendererPool = controllerRendererPool;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardRenderer
    public RecyclerView.ViewHolder createViewHolder(View view, int i) {
        return new ConditionFooterCardHolder(view);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardRenderer
    public void bindView(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard) {
        final MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.homepage.contextualcards.conditional.-$$Lambda$ConditionFooterContextualCardRenderer$16t4OH1s3CSL5-i8YKoGyb7tyNE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindView$0$ConditionFooterContextualCardRenderer(metricsFeatureProvider, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindView$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$bindView$0$ConditionFooterContextualCardRenderer(MetricsFeatureProvider metricsFeatureProvider, View view) {
        metricsFeatureProvider.action(0, 373, 1502, null, 0);
        ConditionContextualCardController conditionContextualCardController = (ConditionContextualCardController) this.mControllerRendererPool.getController(this.mContext, 5);
        conditionContextualCardController.setIsExpanded(false);
        conditionContextualCardController.onConditionsChanged();
    }

    public static class ConditionFooterCardHolder extends RecyclerView.ViewHolder {
        public ConditionFooterCardHolder(View view) {
            super(view);
        }
    }
}
