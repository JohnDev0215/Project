package com.byd.vrassistant.view.viewBinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.utils.ScreenUtil;
import com.drakeet.multitype.ItemViewBinder;

/* JADX INFO: loaded from: classes2.dex */
public class PoiViewBinder extends ItemViewBinder<PlaceInfo, PoiViewHolder> {
    OnItemClickListener onItemClickListener;

    public PoiViewBinder(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override // com.drakeet.multitype.ItemViewBinder
    public PoiViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return new PoiViewHolder(layoutInflater.inflate(R.layout.item_voice_bar_nav, viewGroup, false));
    }

    @Override // com.drakeet.multitype.ItemViewDelegate
    public void onBindViewHolder(final PoiViewHolder poiViewHolder, PlaceInfo placeInfo) {
        poiViewHolder.setSkin(ScreenUtil.getSkinType());
        poiViewHolder.tvNum.setText(String.valueOf(poiViewHolder.getLayoutPosition() + 1));
        poiViewHolder.tvTop.setText(placeInfo.getName());
        poiViewHolder.tvBot.setText(placeInfo.getAddress());
        poiViewHolder.tvEnd.setText(placeInfo.getDistance() + placeInfo.getUnitOfDistance());
        poiViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.view.viewBinder.PoiViewBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m229xf4cfc19e(poiViewHolder, view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onBindViewHolder$0$com-byd-vrassistant-view-viewBinder-PoiViewBinder, reason: not valid java name */
    /* synthetic */ void m229xf4cfc19e(PoiViewHolder poiViewHolder, View view) {
        this.onItemClickListener.onClick(view, poiViewHolder.getAbsoluteAdapterPosition());
    }

    static class PoiViewHolder extends RecyclerView.ViewHolder {
        TextView tvBot;
        TextView tvEnd;
        TextView tvNum;
        TextView tvTop;

        public PoiViewHolder(View view) {
            super(view);
            this.tvTop = (TextView) view.findViewById(R.id.tv_top);
            this.tvNum = (TextView) view.findViewById(R.id.tv_num);
            this.tvBot = (TextView) view.findViewById(R.id.tv_bottom);
            this.tvEnd = (TextView) view.findViewById(R.id.tv_end);
        }

        public void setSkin(int i) {
            Context context = this.itemView.getContext();
            if (i == 1) {
                this.tvTop.setTextColor(context.getColor(R.color.list_text_light));
                this.tvNum.setTextColor(context.getColor(R.color.list_text_55_light));
                this.tvBot.setTextColor(context.getColor(R.color.list_text_55_light));
                this.tvEnd.setTextColor(context.getColor(R.color.list_text_55_light));
                return;
            }
            this.tvTop.setTextColor(context.getColor(R.color.list_text_dark));
            this.tvNum.setTextColor(context.getColor(R.color.list_text_55_dark));
            this.tvBot.setTextColor(context.getColor(R.color.list_text_55_dark));
            this.tvEnd.setTextColor(context.getColor(R.color.list_text_55_dark));
        }
    }
}
