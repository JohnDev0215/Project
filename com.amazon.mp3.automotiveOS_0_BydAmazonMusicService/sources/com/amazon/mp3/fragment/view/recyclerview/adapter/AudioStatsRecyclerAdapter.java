package com.amazon.mp3.fragment.view.recyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.model.AudioStatsRowModel;
import com.amazon.mp3.fragment.view.recyclerview.listener.RecyclerViewItemClickListener;
import com.amazon.mp3.fragment.view.recyclerview.viewholder.AbstractViewHolder;
import com.amazon.mp3.util.StringUtil;

/* JADX INFO: loaded from: classes3.dex */
public class AudioStatsRecyclerAdapter extends AbstractRecyclerViewAdapter<AudioStatsRowModel, RecyclerViewItemClickListener, ViewHolder> {
    public AudioStatsRecyclerAdapter(Context context, RecyclerViewItemClickListener listener) {
        super(context, listener);
    }

    @Override // com.amazon.mp3.fragment.view.recyclerview.adapter.AbstractRecyclerViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(R.layout.katana_audio_stats_row, parent, false), getListener());
    }

    public class ViewHolder extends AbstractViewHolder<AudioStatsRowModel, RecyclerViewItemClickListener> {
        final TextView currentPropertyTitle;
        final TextView currentPropertyValue;

        public ViewHolder(final View view, final RecyclerViewItemClickListener listener) {
            super(view, listener);
            this.currentPropertyTitle = (TextView) view.findViewById(R.id.current_property_title);
            this.currentPropertyValue = (TextView) view.findViewById(R.id.current_property_value);
            if (listener != null) {
                view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.view.recyclerview.adapter.AudioStatsRecyclerAdapter$ViewHolder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f$0.lambda$new$0(listener, view, view2);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(RecyclerViewItemClickListener recyclerViewItemClickListener, View view, View view2) {
            recyclerViewItemClickListener.onClick(view, getAdapterPosition());
        }

        @Override // com.amazon.mp3.fragment.view.recyclerview.viewholder.AbstractViewHolder
        public void onBind(final AudioStatsRowModel audioStatsRowModel) {
            this.currentPropertyTitle.setText(audioStatsRowModel.getPropertyTitle());
            String propertyValue = audioStatsRowModel.getPropertyValue();
            if (StringUtil.isNullOrWhiteSpaces(propertyValue)) {
                return;
            }
            this.currentPropertyValue.setText(propertyValue);
            this.currentPropertyValue.setVisibility(0);
        }
    }
}
