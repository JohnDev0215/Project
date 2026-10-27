package com.byd.card.floatcard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class CardAppViewHolder extends MyViewHolder {
    public ConstraintLayout constraintLayout;
    public ImageView delete;
    public ImageView icon;
    View mContent;
    public TextView name;

    public CardAppViewHolder(View itemView) {
        super(itemView);
        this.mContent = itemView;
        this.constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.card_app_cl);
        this.icon = (ImageView) itemView.findViewById(R.id.card_app_icon);
        this.name = (TextView) itemView.findViewById(R.id.card_app_name);
        this.delete = (ImageView) itemView.findViewById(R.id.card_delete);
        this.icon.setClickable(false);
    }

    @Override // com.byd.card.floatcard.MyViewHolder
    public View getContent() {
        return this.mContent;
    }
}
