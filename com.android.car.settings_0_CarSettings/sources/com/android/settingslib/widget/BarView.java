package com.android.settingslib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes6.dex */
public class BarView extends LinearLayout {
    private static final String TAG = "BarView";
    private TextView mBarSummary;
    private TextView mBarTitle;
    private View mBarView;
    private ImageView mIcon;

    public BarView(Context context) {
        super(context);
        init();
    }

    public BarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        int colorAccent = context.obtainStyledAttributes(new int[]{android.R.attr.colorAccent}).getColor(0, 0);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SettingsBarView);
        int barColor = a.getColor(R.styleable.SettingsBarView_barColor, colorAccent);
        a.recycle();
        this.mBarView.setBackgroundColor(barColor);
    }

    void updateView(BarViewInfo barViewInfo) {
        setOnClickListener(barViewInfo.getClickListener());
        this.mBarView.getLayoutParams().height = barViewInfo.getNormalizedHeight();
        this.mIcon.setImageDrawable(barViewInfo.getIcon());
        this.mBarTitle.setText(barViewInfo.getTitle());
        this.mBarSummary.setText(barViewInfo.getSummary());
        CharSequence barViewInfoContent = barViewInfo.getContentDescription();
        if (!TextUtils.isEmpty(barViewInfoContent) && !TextUtils.equals(barViewInfo.getTitle(), barViewInfoContent)) {
            this.mIcon.setContentDescription(barViewInfo.getContentDescription());
        }
    }

    CharSequence getTitle() {
        return this.mBarTitle.getText();
    }

    CharSequence getSummary() {
        return this.mBarSummary.getText();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.settings_bar_view, this);
        setOrientation(1);
        setGravity(81);
        this.mBarView = findViewById(R.id.bar_view);
        this.mIcon = (ImageView) findViewById(R.id.icon_view);
        this.mBarTitle = (TextView) findViewById(R.id.bar_title);
        this.mBarSummary = (TextView) findViewById(R.id.bar_summary);
    }

    private void setOnClickListner(View.OnClickListener listener) {
        this.mBarView.setOnClickListener(listener);
    }
}
