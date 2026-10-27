package com.byd.card.floatcard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class CardAddViewHolder extends MyViewHolder {
    public ConstraintLayout card_app_cl;
    public ImageView icon;
    View mContent;
    public TextView name;

    public CardAddViewHolder(View itemView) {
        super(itemView);
        this.mContent = itemView;
        this.icon = (ImageView) itemView.findViewById(R.id.card_app_icon);
        this.card_app_cl = (ConstraintLayout) itemView.findViewById(R.id.card_app_cl);
        this.name = (TextView) itemView.findViewById(R.id.card_app_name);
    }

    @Override // com.byd.card.floatcard.MyViewHolder
    public View getContent() {
        return this.mContent;
    }
}
