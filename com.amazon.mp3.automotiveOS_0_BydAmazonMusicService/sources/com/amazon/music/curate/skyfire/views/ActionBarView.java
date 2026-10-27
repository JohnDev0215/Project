package com.amazon.music.curate.skyfire.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amazon.music.curate.R;
import com.squareup.picasso.Picasso;

/* JADX INFO: loaded from: classes4.dex */
public final class ActionBarView extends RelativeLayout {
    private ImageButton button;
    private EmberTextView header;
    private ImageView miniHeader;

    public ActionBarView(final Context context) {
        super(context);
        init();
    }

    public ActionBarView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ActionBarView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ActionBarView(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.action_bar, this);
        this.button = (ImageButton) findViewById(R.id.action_bar_back);
        this.header = (EmberTextView) findViewById(R.id.action_bar_header);
        this.miniHeader = (ImageView) findViewById(R.id.action_bar_mini_header);
    }

    public void bindMiniHeader(final String imageHeaderUrl) {
        if (imageHeaderUrl == null) {
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.icon_button_size_medium);
        Picasso.get().load(imageHeaderUrl).placeholder(R.drawable.artwork_placeholder).resize(dimensionPixelSize, dimensionPixelSize).centerCrop().into(this.miniHeader);
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
}
