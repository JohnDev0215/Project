package com.android.car.developeroptions.homepage.contextualcards.conditional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardRenderer;
import com.android.car.developeroptions.homepage.contextualcards.ControllerRendererPool;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class ConditionHeaderContextualCardRenderer implements ContextualCardRenderer {
    private final Context mContext;
    private final ControllerRendererPool mControllerRendererPool;

    public ConditionHeaderContextualCardRenderer(Context context, ControllerRendererPool controllerRendererPool) {
        this.mContext = context;
        this.mControllerRendererPool = controllerRendererPool;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardRenderer
    public RecyclerView.ViewHolder createViewHolder(View view, int i) {
        return new ConditionHeaderCardHolder(view);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardRenderer
    public void bindView(RecyclerView.ViewHolder viewHolder, ContextualCard contextualCard) {
        final ConditionHeaderCardHolder conditionHeaderCardHolder = (ConditionHeaderCardHolder) viewHolder;
        final MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider();
        conditionHeaderCardHolder.icons.removeAllViews();
        ((ConditionHeaderContextualCard) contextualCard).getConditionalCards().stream().forEach(new Consumer() { // from class: com.android.car.developeroptions.homepage.contextualcards.conditional.-$$Lambda$ConditionHeaderContextualCardRenderer$xaykZYeEdrfrqIE5Jpx4xfg0sh0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$bindView$0$ConditionHeaderContextualCardRenderer(conditionHeaderCardHolder, (ContextualCard) obj);
            }
        });
        conditionHeaderCardHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.homepage.contextualcards.conditional.-$$Lambda$ConditionHeaderContextualCardRenderer$Y3gtcnJLBNNz0HuXdhIXbTpycOY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindView$1$ConditionHeaderContextualCardRenderer(metricsFeatureProvider, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindView$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$bindView$0$ConditionHeaderContextualCardRenderer(ConditionHeaderCardHolder conditionHeaderCardHolder, ContextualCard contextualCard) {
        ImageView imageView = (ImageView) LayoutInflater.from(this.mContext).inflate(R.layout.conditional_card_header_icon, (ViewGroup) conditionHeaderCardHolder.icons, false);
        imageView.setImageDrawable(contextualCard.getIconDrawable());
        conditionHeaderCardHolder.icons.addView(imageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindView$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$bindView$1$ConditionHeaderContextualCardRenderer(MetricsFeatureProvider metricsFeatureProvider, View view) {
        metricsFeatureProvider.action(0, 373, 1502, null, 1);
        ConditionContextualCardController conditionContextualCardController = (ConditionContextualCardController) this.mControllerRendererPool.getController(this.mContext, 4);
        conditionContextualCardController.setIsExpanded(true);
        conditionContextualCardController.onConditionsChanged();
    }

    public static class ConditionHeaderCardHolder extends RecyclerView.ViewHolder {
        public final LinearLayout icons;

        public ConditionHeaderCardHolder(View view) {
            super(view);
            this.icons = (LinearLayout) view.findViewById(R.id.header_icons_container);
        }
    }
}
