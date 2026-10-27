package com.amazon.podcast.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;

/* JADX INFO: loaded from: classes5.dex */
public class MessageView extends LinearLayout {
    private EmberTextView messageButtonText;
    private ImageView messageIcon;
    private EmberTextView primaryText;
    private EmberTextView secondaryText;

    public MessageView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private final void init() {
        View.inflate(getContext(), R$layout.podcast_message_view, this);
        this.messageIcon = (ImageView) findViewById(R$id.podcast_message_icon);
        this.primaryText = (EmberTextView) findViewById(R$id.podcast_message_view_primary_text);
        this.secondaryText = (EmberTextView) findViewById(R$id.podcast_message_view_secondary_text);
        this.messageButtonText = (EmberTextView) findViewById(R$id.podcast_message_view_button);
    }

    public void bind(final String primaryMessage, final String secondaryMessage) {
        this.primaryText.setText(primaryMessage);
        this.secondaryText.setText(secondaryMessage);
        this.primaryText.setVisibility(0);
        this.secondaryText.setVisibility(0);
    }

    public void bindIcon(final int drawableRes) {
        this.messageIcon.setBackgroundResource(drawableRes);
        this.messageIcon.setVisibility(0);
    }

    public void bindButton(final String buttonText, final View.OnClickListener onClickListener) {
        this.messageButtonText.setText(buttonText);
        this.messageButtonText.setOnClickListener(onClickListener);
        this.messageButtonText.setVisibility(0);
    }

    public void hideButton() {
        this.messageButtonText.setVisibility(8);
    }

    public void hideIcon() {
        this.messageIcon.setVisibility(8);
    }
}
