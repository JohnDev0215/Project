package com.amazon.music.widget.eux;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: loaded from: classes4.dex */
public class EarlyUserExperienceView extends FrameLayout {
    private TextView buttonTextView;
    private TextView headerTextView;
    private ImageView imageView;
    private TextView subtextTextView;

    public EarlyUserExperienceView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        View.inflate(context, R$layout.eux_layout, this);
        this.imageView = (ImageView) findViewById(R$id.eux_image);
        this.headerTextView = (TextView) findViewById(R$id.eux_header);
        this.subtextTextView = (TextView) findViewById(R$id.eux_subtext);
        this.buttonTextView = (TextView) findViewById(R$id.eux_button);
    }

    public void setImageDrawableResource(int i) {
        this.imageView.setImageResource(i);
    }

    public void setHeaderText(int i) {
        this.headerTextView.setText(getContext().getString(i));
    }

    public void setSubtextText(int i) {
        this.subtextTextView.setText(getContext().getString(i));
    }

    public void setButtonText(int i) {
        this.buttonTextView.setText(getContext().getString(i));
    }

    public void setButtonOnClickListener(View.OnClickListener onClickListener) {
        this.buttonTextView.setOnClickListener(onClickListener);
    }

    public void hideButton() {
        this.buttonTextView.setVisibility(8);
    }

    public void showButton() {
        this.buttonTextView.setVisibility(0);
    }
}
