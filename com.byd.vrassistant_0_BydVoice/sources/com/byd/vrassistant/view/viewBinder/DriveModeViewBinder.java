package com.byd.vrassistant.view.viewBinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cerence.ark.assistant.controller.struct.DrivingModeInfo;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.utils.ScreenUtil;
import com.drakeet.multitype.ItemViewBinder;

/* JADX INFO: loaded from: classes2.dex */
public class DriveModeViewBinder extends ItemViewBinder<DrivingModeInfo, DriveModeViewHolder> {
    OnItemClickListener onItemClickListener;

    public DriveModeViewBinder(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override // com.drakeet.multitype.ItemViewBinder
    public DriveModeViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return new DriveModeViewHolder(layoutInflater.inflate(R.layout.item_voice_bar_phone, viewGroup, false));
    }

    @Override // com.drakeet.multitype.ItemViewDelegate
    public void onBindViewHolder(final DriveModeViewHolder driveModeViewHolder, DrivingModeInfo drivingModeInfo) {
        driveModeViewHolder.setSkin(ScreenUtil.getSkinType());
        driveModeViewHolder.tvNum.setText(String.valueOf(driveModeViewHolder.getLayoutPosition() + 1));
        driveModeViewHolder.tvTop.setText(drivingModeInfo.getMode());
        driveModeViewHolder.tvBot.setText(drivingModeInfo.getLabel());
        driveModeViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.view.viewBinder.DriveModeViewBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m228x198a8221(driveModeViewHolder, view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onBindViewHolder$0$com-byd-vrassistant-view-viewBinder-DriveModeViewBinder, reason: not valid java name */
    /* synthetic */ void m228x198a8221(DriveModeViewHolder driveModeViewHolder, View view) {
        this.onItemClickListener.onClick(view, driveModeViewHolder.getAbsoluteAdapterPosition());
    }

    static class DriveModeViewHolder extends RecyclerView.ViewHolder {
        TextView tvBot;
        TextView tvNum;
        TextView tvTop;

        public DriveModeViewHolder(View view) {
            super(view);
            this.tvTop = (TextView) view.findViewById(R.id.tv_top);
            this.tvNum = (TextView) view.findViewById(R.id.tv_num);
            this.tvBot = (TextView) view.findViewById(R.id.tv_bottom);
        }

        public void setSkin(int i) {
            Context context = this.itemView.getContext();
            if (i == 1) {
                this.tvTop.setTextColor(context.getColor(R.color.list_text_light));
                this.tvNum.setTextColor(context.getColor(R.color.list_text_55_light));
                this.tvBot.setTextColor(context.getColor(R.color.list_text_55_light));
            } else {
                this.tvTop.setTextColor(context.getColor(R.color.list_text_dark));
                this.tvNum.setTextColor(context.getColor(R.color.list_text_55_dark));
                this.tvBot.setTextColor(context.getColor(R.color.list_text_55_dark));
            }
        }
    }
}
