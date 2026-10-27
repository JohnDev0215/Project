package com.amazon.podcast.views.refinements;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
public final class RefinementBarItemView extends LinearLayout {
    private ImageView leftIcon;
    private LinearLayout linearLayout;
    private ImageView rightIcon;
    private EmberTextView textView;

    public RefinementBarItemView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_refinement_bar_item, this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.podcast_refinement_bar_button_layout);
        this.linearLayout = linearLayout;
        linearLayout.setBackground(getResources().getDrawable(Podcast.getRuntimeStyleSheet().getButtonRefinementSelectorDrawableId()));
        this.leftIcon = (ImageView) findViewById(R$id.podcast_refinement_bar_item_icon_left);
        this.textView = (EmberTextView) findViewById(R$id.podcast_refinement_bar_item_text);
        this.rightIcon = (ImageView) findViewById(R$id.podcast_refinement_bar_item_icon_right);
        setClickable(true);
    }

    public void setText(final String text) {
        this.textView.setText(text);
    }

    public void setLeftIcon(final int resourceId) {
        this.leftIcon.setImageResource(resourceId);
    }

    public void setLeftIconVisibile(final boolean isVisible) {
        if (isVisible) {
            this.leftIcon.setVisibility(0);
        } else {
            this.leftIcon.setVisibility(8);
        }
    }

    public void setRightIcon(final int resourceId) {
        this.rightIcon.setImageResource(resourceId);
    }

    public void setRightIconVisibile(final boolean isVisible) {
        if (isVisible) {
            this.rightIcon.setVisibility(0);
        } else {
            this.rightIcon.setVisibility(8);
        }
    }
}
