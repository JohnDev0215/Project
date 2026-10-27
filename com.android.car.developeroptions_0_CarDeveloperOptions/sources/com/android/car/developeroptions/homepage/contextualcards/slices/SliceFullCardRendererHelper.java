package com.android.car.developeroptions.homepage.contextualcards.slices;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slice.Slice;
import androidx.slice.SliceItem;
import androidx.slice.widget.EventInfo;
import androidx.slice.widget.SliceView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.homepage.contextualcards.ContextualCard;
import com.android.car.developeroptions.homepage.contextualcards.logging.ContextualCardLogUtils;
import com.android.car.developeroptions.overlay.FeatureFactory;

/* JADX INFO: loaded from: classes.dex */
class SliceFullCardRendererHelper {
    private final Context mContext;

    SliceFullCardRendererHelper(Context context) {
        this.mContext = context;
    }

    RecyclerView.ViewHolder createViewHolder(View view) {
        return new SliceViewHolder(view);
    }

    void bindView(RecyclerView.ViewHolder viewHolder, final ContextualCard contextualCard, Slice slice) {
        final SliceViewHolder sliceViewHolder = (SliceViewHolder) viewHolder;
        sliceViewHolder.sliceView.setScrollable(false);
        sliceViewHolder.sliceView.setTag(contextualCard.getSliceUri());
        sliceViewHolder.sliceView.setMode(2);
        sliceViewHolder.sliceView.setSlice(slice);
        sliceViewHolder.sliceView.setOnSliceActionListener(new SliceView.OnSliceActionListener() { // from class: com.android.car.developeroptions.homepage.contextualcards.slices.-$$Lambda$SliceFullCardRendererHelper$2nGVG5Zo1mq3mWk_J_OeEklLerU
            @Override // androidx.slice.widget.SliceView.OnSliceActionListener
            public final void onSliceAction(EventInfo eventInfo, SliceItem sliceItem) {
                this.f$0.lambda$bindView$0$SliceFullCardRendererHelper(contextualCard, sliceViewHolder, eventInfo, sliceItem);
            }
        });
        sliceViewHolder.sliceView.showTitleItems(true);
        if (contextualCard.isLargeCard()) {
            sliceViewHolder.sliceView.showHeaderDivider(true);
            sliceViewHolder.sliceView.showActionDividers(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindView$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$bindView$0$SliceFullCardRendererHelper(ContextualCard contextualCard, SliceViewHolder sliceViewHolder, EventInfo eventInfo, SliceItem sliceItem) {
        FeatureFactory.getFactory(this.mContext).getMetricsFeatureProvider().action(this.mContext, 1666, ContextualCardLogUtils.buildCardClickLog(contextualCard, eventInfo.rowIndex, eventInfo.actionType, sliceViewHolder.getAdapterPosition()));
    }

    static class SliceViewHolder extends RecyclerView.ViewHolder {
        public final SliceView sliceView;

        public SliceViewHolder(View view) {
            super(view);
            this.sliceView = (SliceView) view.findViewById(R.id.slice_view);
        }
    }
}
