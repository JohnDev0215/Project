package com.amazon.podcast.views.detailTemplate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
public final class FollowButton extends RelativeLayout {
    private ImageView image;
    private EmberTextView text;

    public FollowButton(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_follow_button, this);
        setClickable(true);
        setFocusable(true);
        this.image = (ImageView) findViewById(R$id.podcast_follow_button_image);
        this.text = (EmberTextView) findViewById(R$id.podcast_follow_button_text);
    }

    public void bindUnFollow(final String followText) {
        this.image.setImageResource(R$drawable.ic_follow);
        this.text.setTextColor(getResources().getColor(R$color.primary));
        this.text.setText(followText);
        setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
    }

    public void bindFollow(final String followText) {
        this.image.setImageResource(R$drawable.ic_following);
        this.text.setTextColor(getResources().getColor(R$color.primary));
        this.text.setText(followText);
        setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
    }
}
