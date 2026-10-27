package com.amazon.podcast.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.squareup.picasso.Picasso;

/* JADX INFO: loaded from: classes5.dex */
public final class ActionBarView extends RelativeLayout {
    private ImageButton button;
    private LinearLayout filterButton;
    private EmberTextView header;
    private ImageView miniHeader;

    public ActionBarView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_action_bar, this);
        this.button = (ImageButton) findViewById(R$id.podcast_action_bar_back);
        this.filterButton = (LinearLayout) findViewById(R$id.filter_button);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_action_bar_header);
        this.header = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.miniHeader = (ImageView) findViewById(R$id.podcast_action_bar_mini_header);
    }

    public ImageButton getBackButton() {
        return this.button;
    }

    public void bindMiniHeader(final String imageHeaderUrl) {
        if (imageHeaderUrl == null) {
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.podcast_medium_icon_button);
        Picasso.get().load(imageHeaderUrl).placeholder(R$drawable.artwork_placeholder).resize(dimensionPixelSize, dimensionPixelSize).centerCrop().into(this.miniHeader);
    }

    public void showMiniHeader(final boolean showHeader) {
        if (showHeader) {
            this.miniHeader.setVisibility(0);
        } else {
            this.miniHeader.setVisibility(8);
        }
    }

    public void showTitle(final boolean showTitle) {
        if (showTitle) {
            this.header.setVisibility(0);
        } else {
            this.header.setVisibility(8);
        }
    }

    public void bindTitle(final String headerText) {
        if (headerText == null) {
            return;
        }
        this.header.setText(headerText);
        this.header.setVisibility(0);
    }

    public void bindBackButton(final View.OnClickListener onClickListener) {
        this.button.setOnClickListener(onClickListener);
    }

    public void bindFilterButton(final View.OnClickListener onClickListener) {
        this.filterButton.setOnClickListener(onClickListener);
    }

    public void showFilterButton() {
        if (this.filterButton.getVisibility() == 0) {
            return;
        }
        this.filterButton.setVisibility(0);
    }

    public void hideFilterButton() {
        if (this.filterButton.getVisibility() == 8) {
            return;
        }
        this.filterButton.setVisibility(8);
    }
}
