package com.amazon.podcast.nowplaying;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.transformations.BlurTransformation;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

/* JADX INFO: loaded from: classes5.dex */
final class AboutView extends LinearLayout implements Media.MediaMetadataCallback {
    private ImageView background;
    private BlurTransformation blurTransformation;
    private FragmentManager fragmentManager;
    private MiniPlayerView miniPlayerView;
    private Playback playback;
    private RoundedCornersTransformation roundedCornersTransformation;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public AboutView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_now_playing_about_view, this);
        this.blurTransformation = new BlurTransformation(getContext());
        this.roundedCornersTransformation = new RoundedCornersTransformation(getResources().getDimensionPixelSize(R$dimen.podcast_mini_player_artwork_corner_radius));
        this.playback = Podcast.getPlayback();
        this.background = (ImageView) findViewById(R$id.now_playing_about_background);
        this.miniPlayerView = (MiniPlayerView) findViewById(R$id.mini_player_view);
        TabLayout tabLayout = (TabLayout) findViewById(R$id.now_playing_about_view_tab_layout);
        this.tabLayout = tabLayout;
        tabLayout.setSelectedTabIndicatorColor(Podcast.getRuntimeStyleSheet().getAccentColor());
        this.viewPager = (ViewPager) findViewById(R$id.now_playing_about_view_view_pager);
        Podcast.getPlayback().getMedia().addMediaMetadataCallback(this);
        this.miniPlayerView.setFromAboutView();
    }

    public void bindMetadata(final MediaMetadataElement metadataElement) {
        if (metadataElement == null) {
            return;
        }
        Picasso.get().load(metadataElement.getArtwork()).placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).transform(this.blurTransformation).into(this.background);
        this.viewPager.setAdapter(new TabAdapter(getContext(), this.fragmentManager));
        this.tabLayout.setupWithViewPager(this.viewPager);
        setCustomFontOnTabs();
        this.viewPager.setCurrentItem(0);
    }

    public void setMinimizeOnClickListener(final View.OnClickListener listener) {
        this.miniPlayerView.setMinimizeOnClickListener(listener);
    }

    public void setFragmentManager(final FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(final MediaMetadataElement mediaMetadata) {
        bindMetadata(mediaMetadata);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        if (visibility == 0) {
            Media media = this.playback.getMedia();
            bindMetadata(media.getMediaMetadataElement());
            media.addMediaMetadataCallback(this);
            return;
        }
        this.playback.getMedia().removeMediaMetadataCallback(this);
    }

    private void setCustomFontOnTabs() {
        ViewGroup viewGroup = (ViewGroup) this.tabLayout.getChildAt(0);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.getChildAt(i);
            int childCount2 = viewGroup2.getChildCount();
            for (int i2 = 0; i2 < childCount2; i2++) {
                View childAt = viewGroup2.getChildAt(i2);
                if (childAt instanceof TextView) {
                    ((TextView) childAt).setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/AmazonEmber_Bd.ttf"));
                }
            }
        }
    }
}
