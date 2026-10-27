package com.android.car.developeroptions.homepage.contextualcards.slices;

import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slice.Slice;
import androidx.slice.SliceMetadata;
import androidx.slice.core.SliceAction;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.logging.ContextualCardLogUtils;
import com.android.car.developeroptions.overlay.FeatureFactory;

/* JADX INFO: loaded from: classes.dex */
class SliceHalfCardRendererHelper {
    private final Context mContext;

    SliceHalfCardRendererHelper(Context context) {
        this.mContext = context;
    }

    RecyclerView.ViewHolder createViewHolder(View view) {
        return new HalfCardViewHolder(view);
    }

    void bindView(RecyclerView.ViewHolder viewHolder, final ContextualCard contextualCard, Slice slice) {
        final HalfCardViewHolder halfCardViewHolder = (HalfCardViewHolder) viewHolder;
        final SliceAction primaryAction = SliceMetadata.from(this.mContext, slice).getPrimaryAction();
        halfCardViewHolder.icon.setImageDrawable(primaryAction.getIcon().loadDrawable(this.mContext));
        halfCardViewHolder.title.setText(primaryAction.getTitle());
        halfCardViewHolder.content.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.homepage.contextualcards.slices.-$$Lambda$SliceHalfCardRendererHelper$ddXJxXh4CGJZ0Mp6A4irLRnoMzw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindView$0$SliceHalfCardRendererHelper(primaryAction, contextualCard, halfCardViewHolder, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindView$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$bindView$0$SliceHalfCardRendererHelper(SliceAction sliceAction, ContextualCard contextualCard, HalfCardViewHolder halfCardViewHolder, View view) {
        try {
            sliceAction.getAction().send();
        } catch (PendingIntent.CanceledException unused) {
            Log.w("SliceHCRendererHelper", "Failed to start intent " + ((Object) sliceAction.getTitle()));
        }
        FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider().action(this.mContext, 1666, ContextualCardLogUtils.buildCardClickLog(contextualCard, 0, 3, halfCardViewHolder.getAdapterPosition()));
    }

    static class HalfCardViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout content;
        public final ImageView icon;
        public final TextView title;

        public HalfCardViewHolder(View view) {
            super(view);
            this.content = (LinearLayout) view.findViewById(R.id.content);
            this.icon = (ImageView) view.findViewById(android.R.id.icon);
            this.title = (TextView) view.findViewById(android.R.id.title);
        }
    }
}
