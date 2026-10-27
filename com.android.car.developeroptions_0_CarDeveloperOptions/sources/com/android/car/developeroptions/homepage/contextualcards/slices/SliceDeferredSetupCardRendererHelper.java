package com.android.car.developeroptions.homepage.contextualcards.slices;

import android.R;
import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slice.Slice;
import androidx.slice.SliceMetadata;
import androidx.slice.core.SliceAction;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.logging.ContextualCardLogUtils;
import com.android.car.developeroptions.overlay.FeatureFactory;

/* JADX INFO: loaded from: classes.dex */
class SliceDeferredSetupCardRendererHelper {
    private final Context mContext;

    SliceDeferredSetupCardRendererHelper(Context context) {
        this.mContext = context;
    }

    RecyclerView.ViewHolder createViewHolder(View view) {
        return new DeferredSetupCardViewHolder(view);
    }

    void bindView(RecyclerView.ViewHolder viewHolder, final ContextualCard contextualCard, Slice slice) {
        final DeferredSetupCardViewHolder deferredSetupCardViewHolder = (DeferredSetupCardViewHolder) viewHolder;
        SliceMetadata sliceMetadataFrom = SliceMetadata.from(this.mContext, slice);
        final SliceAction primaryAction = sliceMetadataFrom.getPrimaryAction();
        deferredSetupCardViewHolder.icon.setImageDrawable(primaryAction.getIcon().loadDrawable(this.mContext));
        deferredSetupCardViewHolder.title.setText(primaryAction.getTitle());
        deferredSetupCardViewHolder.summary.setText(sliceMetadataFrom.getSubtitle());
        deferredSetupCardViewHolder.button.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.homepage.contextualcards.slices.-$$Lambda$SliceDeferredSetupCardRendererHelper$qkga7n4QusGxJ6w2tevndJ8Miq0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindView$0$SliceDeferredSetupCardRendererHelper(primaryAction, contextualCard, deferredSetupCardViewHolder, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindView$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$bindView$0$SliceDeferredSetupCardRendererHelper(SliceAction sliceAction, ContextualCard contextualCard, DeferredSetupCardViewHolder deferredSetupCardViewHolder, View view) {
        try {
            sliceAction.getAction().send();
        } catch (PendingIntent.CanceledException unused) {
            Log.w("SliceDSCRendererHelper", "Failed to start intent " + ((Object) sliceAction.getTitle()));
        }
        FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider().action(this.mContext, 1666, ContextualCardLogUtils.buildCardClickLog(contextualCard, 0, 3, deferredSetupCardViewHolder.getAdapterPosition()));
    }

    static class DeferredSetupCardViewHolder extends RecyclerView.ViewHolder {
        public final Button button;
        public final ImageView icon;
        public final TextView summary;
        public final TextView title;

        public DeferredSetupCardViewHolder(View view) {
            super(view);
            this.icon = (ImageView) view.findViewById(R.id.icon);
            this.title = (TextView) view.findViewById(R.id.title);
            this.summary = (TextView) view.findViewById(R.id.summary);
            this.button = (Button) view.findViewById(com.android.car.developeroptions.R.id.finish_setup);
        }
    }
}
