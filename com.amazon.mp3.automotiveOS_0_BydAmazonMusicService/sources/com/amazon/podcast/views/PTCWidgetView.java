package com.amazon.podcast.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;

/* JADX INFO: loaded from: classes5.dex */
public class PTCWidgetView extends LinearLayout {
    private EmberTextView button;
    private EmberTextView primaryText;
    private View view;

    public PTCWidgetView(final Context context) {
        super(context);
        init();
    }

    public PTCWidgetView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.view = View.inflate(getContext(), R$layout.ptc_widget, this);
        this.button = (EmberTextView) findViewById(R$id.podcast_ptc_button);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_ptc_primary_text);
        this.primaryText = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
    }

    public void bind(final String header, final View.OnClickListener onClickListener, final boolean isViewClickable) {
        this.button.setText(header);
        bind(onClickListener, isViewClickable);
    }

    public void bind(final View.OnClickListener onClickListener, final boolean isViewClickable) {
        if (isViewClickable) {
            this.view.setOnClickListener(onClickListener);
        }
        this.button.setOnClickListener(onClickListener);
    }
}
