package com.byd.card.floatcard;

import android.view.View;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class MyViewHolder extends RecyclerView.ViewHolder {
    View mContent;
    public SpringAnimation springAnimation;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.springAnimation = new SpringAnimation(this.itemView, SpringAnimation.TRANSLATION_X).setSpring(new SpringForce().setFinalPosition(0.0f).setDampingRatio(1.0f).setStiffness(200.0f));
        this.mContent = itemView;
    }

    public View getContent() {
        return this.mContent;
    }
}
