package com.android.car.developeroptions.panel;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slice.Slice;
import androidx.slice.SliceItem;
import androidx.slice.widget.EventInfo;
import androidx.slice.widget.SliceLiveData;
import androidx.slice.widget.SliceView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.CustomSliceRegistry;
import com.google.android.setupdesign.DividerItemDecoration;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PanelSlicesAdapter extends RecyclerView.Adapter<SliceRowViewHolder> {
    private final PanelContent mPanelContent;
    private final PanelFragment mPanelFragment;
    private final List<Uri> mSliceUris;

    public PanelSlicesAdapter(PanelFragment panelFragment, PanelContent panelContent) {
        this.mPanelFragment = panelFragment;
        this.mSliceUris = panelContent.getSlices();
        this.mPanelContent = panelContent;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SliceRowViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SliceRowViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.panel_slice_row, viewGroup, false), this.mPanelContent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SliceRowViewHolder sliceRowViewHolder, int i) {
        sliceRowViewHolder.onBind(this.mPanelFragment, this.mSliceUris.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mSliceUris.size();
    }

    List<Uri> getData() {
        return this.mSliceUris;
    }

    public static class SliceRowViewHolder extends RecyclerView.ViewHolder implements DividerItemDecoration.DividedViewHolder {
        private boolean mDividerAllowedAbove;
        private final PanelContent mPanelContent;
        LiveData<Slice> sliceLiveData;
        final SliceView sliceView;

        @Override // com.google.android.setupdesign.DividerItemDecoration.DividedViewHolder
        public boolean isDividerAllowedBelow() {
            return true;
        }

        public SliceRowViewHolder(View view, PanelContent panelContent) {
            super(view);
            this.mDividerAllowedAbove = true;
            SliceView sliceView = (SliceView) view.findViewById(R.id.slice_view);
            this.sliceView = sliceView;
            sliceView.setMode(2);
            this.sliceView.showTitleItems(true);
            this.mPanelContent = panelContent;
        }

        public void onBind(PanelFragment panelFragment, final Uri uri) {
            final Context context = this.sliceView.getContext();
            LiveData<Slice> liveDataFromUri = SliceLiveData.fromUri(context, uri);
            this.sliceLiveData = liveDataFromUri;
            liveDataFromUri.observe(panelFragment.getViewLifecycleOwner(), this.sliceView);
            if (uri.equals(CustomSliceRegistry.MEDIA_OUTPUT_INDICATOR_SLICE_URI)) {
                this.mDividerAllowedAbove = false;
            }
            this.sliceView.setOnSliceActionListener(new SliceView.OnSliceActionListener() { // from class: com.android.car.developeroptions.panel.-$$Lambda$PanelSlicesAdapter$SliceRowViewHolder$NihG9JWHN1IQifNhln7QNRaoDMg
                @Override // androidx.slice.widget.SliceView.OnSliceActionListener
                public final void onSliceAction(EventInfo eventInfo, SliceItem sliceItem) {
                    this.f$0.lambda$onBind$0$PanelSlicesAdapter$SliceRowViewHolder(context, uri, eventInfo, sliceItem);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onBind$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onBind$0$PanelSlicesAdapter$SliceRowViewHolder(Context context, Uri uri, EventInfo eventInfo, SliceItem sliceItem) {
            FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(0, 1658, this.mPanelContent.getMetricsCategory(), uri.toString(), eventInfo.actionType);
        }

        @Override // com.google.android.setupdesign.DividerItemDecoration.DividedViewHolder
        public boolean isDividerAllowedAbove() {
            return this.mDividerAllowedAbove;
        }
    }
}
