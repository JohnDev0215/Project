package com.amazon.podcast.views.galleryTemplate;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
public final class IngressPlayButton extends RelativeLayout {
    private ImageView image;
    private Boolean isSonicRushEnabled;
    private Drawable pausedDrawable;
    private Drawable playDrawable;
    private AnimationDrawable playingDrawable;
    private EmberTextView text;

    public IngressPlayButton(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_widescreen_ingress_button, this);
        setClickable(true);
        setFocusable(true);
        PodcastFeatureGating podcastFeatureGating = PodcastFeatureGating.IS_SONIC_RUSH;
        this.isSonicRushEnabled = Boolean.valueOf(podcastFeatureGating.isEnabled());
        this.image = (ImageView) findViewById(R$id.podcast_widescreen_ingress_button_image);
        this.text = (EmberTextView) findViewById(R$id.podcast_widescreen_ingress_button_text);
        if (podcastFeatureGating.isEnabled()) {
            this.playDrawable = AppCompatResources.getDrawable(getContext(), R$drawable.refresh_player_btn_play);
            this.pausedDrawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_refresh_playback_equalizeroff);
            this.playingDrawable = (AnimationDrawable) AppCompatResources.getDrawable(getContext(), R$drawable.podcast_equalizer_secondary_feature_play);
        } else {
            this.playDrawable = AppCompatResources.getDrawable(getContext(), R$drawable.player_btn_play);
            this.pausedDrawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_playback_equalizeroff);
            this.playingDrawable = (AnimationDrawable) AppCompatResources.getDrawable(getContext(), R$drawable.podcast_equalizer_primary_feature_play);
        }
    }

    public void bindPaused() {
        this.image.setImageDrawable(this.pausedDrawable);
        if (this.isSonicRushEnabled.booleanValue()) {
            setBackgroundResource(R$drawable.refresh_button_solid_background);
            this.text.setTextColor(getResources().getColor(R$color.refresh_base));
        } else {
            setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
            this.text.setTextColor(getResources().getColor(R$color.primary));
        }
        this.text.setText(R$string.podcast_ingress_button_paused);
    }

    public void bindPlaying() {
        this.image.setImageDrawable(this.playingDrawable);
        this.playingDrawable.start();
        if (this.isSonicRushEnabled.booleanValue()) {
            this.text.setTextColor(getResources().getColor(R$color.secondary));
            setBackgroundResource(R$drawable.refresh_button_solid_background);
        } else {
            this.text.setTextColor(getResources().getColor(R$color.primary));
            setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
        }
        this.text.setText(R$string.podcast_ingress_button_playing);
    }

    public void bindPlay() {
        this.image.setImageDrawable(this.playDrawable);
        this.text.setText(R$string.podcast_ingress_button_play);
        if (this.isSonicRushEnabled.booleanValue()) {
            setBackgroundResource(R$drawable.refresh_button_solid_background);
            this.text.setTextColor(getResources().getColor(R$color.refresh_base));
        } else {
            setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
            this.text.setTextColor(getResources().getColor(R$color.primary));
        }
    }
}
