package com.android.car.developeroptions.homepage.contextualcards.legacysuggestion;

import android.R;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCardRenderer;
import com.android.car.developeroptions.homepage.contextualcards.ControllerRendererPool;

/* JADX INFO: loaded from: classes.dex */
public class LegacySuggestionContextualCardRenderer implements ContextualCardRenderer {
    private final Context mContext;
    private final ControllerRendererPool mControllerRendererPool;

    public LegacySuggestionContextualCardRenderer(Context context, ControllerRendererPool controllerRendererPool) {
        this.mContext = context;
        this.mControllerRendererPool = controllerRendererPool;
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardRenderer
    public RecyclerView.ViewHolder createViewHolder(View view, int i) {
        return new LegacySuggestionViewHolder(view);
    }

    @Override // com.android.car.developeroptions.homepage.contextualcards.ContextualCardRenderer
    public void bindView(RecyclerView.ViewHolder viewHolder, final ContextualCard contextualCard) {
        LegacySuggestionViewHolder legacySuggestionViewHolder = (LegacySuggestionViewHolder) viewHolder;
        legacySuggestionViewHolder.icon.setImageDrawable(contextualCard.getIconDrawable());
        legacySuggestionViewHolder.title.setText(contextualCard.getTitleText());
        legacySuggestionViewHolder.summary.setText(contextualCard.getSummaryText());
        legacySuggestionViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.homepage.contextualcards.legacysuggestion.-$$Lambda$LegacySuggestionContextualCardRenderer$ASYzpYd-IRRoB3_FUKNLJtZVhJM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindView$0$LegacySuggestionContextualCardRenderer(contextualCard, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindView$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$bindView$0$LegacySuggestionContextualCardRenderer(ContextualCard contextualCard, View view) {
        this.mControllerRendererPool.getController(this.mContext, contextualCard.getCardType()).onPrimaryClick(contextualCard);
    }

    private static class LegacySuggestionViewHolder extends RecyclerView.ViewHolder {
        public final ImageView icon;
        public final TextView summary;
        public final TextView title;

        public LegacySuggestionViewHolder(View view) {
            super(view);
            this.icon = (ImageView) view.findViewById(R.id.icon);
            this.title = (TextView) view.findViewById(R.id.title);
            this.summary = (TextView) view.findViewById(R.id.summary);
        }
    }
}
