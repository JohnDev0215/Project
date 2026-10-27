package com.byd.vrassistant.view.viewBinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.vrassistant.bean.VoiceBarListBean;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.utils.ScreenUtil;
import com.drakeet.multitype.ItemViewBinder;

/* JADX INFO: loaded from: classes2.dex */
public class ContactViewBinder extends ItemViewBinder<VoiceBarListBean, ContactViewHolder> {
    OnItemClickListener onItemClickListener;

    public ContactViewBinder(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override // com.drakeet.multitype.ItemViewBinder
    public ContactViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return new ContactViewHolder(layoutInflater.inflate(R.layout.item_voice_bar_phone, viewGroup, false));
    }

    @Override // com.drakeet.multitype.ItemViewDelegate
    public void onBindViewHolder(final ContactViewHolder contactViewHolder, VoiceBarListBean voiceBarListBean) {
        contactViewHolder.setSkin(ScreenUtil.getSkinType());
        contactViewHolder.tvTop.setText(voiceBarListBean.getTxtTop());
        contactViewHolder.tvNum.setText(String.valueOf(contactViewHolder.getLayoutPosition() + 1));
        contactViewHolder.tvBot.setText(voiceBarListBean.getTxtBottom());
        contactViewHolder.tvEnd.setText(voiceBarListBean.getTxtEnd());
        contactViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.view.viewBinder.ContactViewBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m227x9c1a5574(contactViewHolder, view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onBindViewHolder$0$com-byd-vrassistant-view-viewBinder-ContactViewBinder, reason: not valid java name */
    /* synthetic */ void m227x9c1a5574(ContactViewHolder contactViewHolder, View view) {
        this.onItemClickListener.onClick(view, contactViewHolder.getAbsoluteAdapterPosition());
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvBot;
        TextView tvEnd;
        TextView tvNum;
        TextView tvTop;

        public ContactViewHolder(View view) {
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
