package com.android.settingslib.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class BarView extends LinearLayout {
    private TextView mBarSummary;
    private TextView mBarTitle;
    private View mBarView;
    private ImageView mIcon;

    public BarView(Context context) {
        super(context);
        init();
    }

    public BarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
        int color = context.obtainStyledAttributes(new int[]{R.attr.colorAccent}).getColor(0, 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SettingsBarView);
        int color2 = typedArrayObtainStyledAttributes.getColor(R$styleable.SettingsBarView_barColor, color);
        typedArrayObtainStyledAttributes.recycle();
        this.mBarView.setBackgroundColor(color2);
    }

    void updateView(BarViewInfo barViewInfo) {
        setOnClickListener(barViewInfo.getClickListener());
        this.mBarView.getLayoutParams().height = barViewInfo.getNormalizedHeight();
        this.mIcon.setImageDrawable(barViewInfo.getIcon());
        this.mBarTitle.setText(barViewInfo.getTitle());
        this.mBarSummary.setText(barViewInfo.getSummary());
        CharSequence contentDescription = barViewInfo.getContentDescription();
        if (TextUtils.isEmpty(contentDescription) || TextUtils.equals(barViewInfo.getTitle(), contentDescription)) {
            return;
        }
        this.mIcon.setContentDescription(barViewInfo.getContentDescription());
    }

    CharSequence getTitle() {
        return this.mBarTitle.getText();
    }

    CharSequence getSummary() {
        return this.mBarSummary.getText();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R$layout.settings_bar_view, this);
        setOrientation(1);
        setGravity(81);
        this.mBarView = findViewById(R$id.bar_view);
        this.mIcon = (ImageView) findViewById(R$id.icon_view);
        this.mBarTitle = (TextView) findViewById(R$id.bar_title);
        this.mBarSummary = (TextView) findViewById(R$id.bar_summary);
    }

    private void setOnClickListner(View.OnClickListener onClickListener) {
        this.mBarView.setOnClickListener(onClickListener);
    }
}
