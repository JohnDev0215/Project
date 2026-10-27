package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodeFeaturedPlayElement;
import Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastFeaturedPlayElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.haptics.PodcastHapticsUtil;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.transformations.BlurTransformation;
import com.amazon.podcast.transformations.GradientTransformation;
import com.amazon.podcast.transformations.HorizontalGradientTransformation;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class FeaturePlayViewHolder extends RecyclerView.ViewHolder implements Playback.StateCallback {
    private static final Logger logger = LoggerFactory.getLogger(FeaturePlayViewHolder.class.getSimpleName());
    private ImageView backgroundImageView;
    private Context context;
    private EpisodeFeaturedPlayElement episodeFeaturedPlayElement;
    private ImageView foregroundImageView;
    private float gradientHeightOffsetBackground;
    private float gradientHeightOffsetForeground;
    private float gradientTopOffsetBackground;
    private boolean isActionBarHidden;
    private boolean isFirstView;
    private final Boolean isScreenSizeGreaterThanMD;
    private TextView labelTextView;
    private int largeSpace;
    private final LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private int miniSpace;
    private List<Method> onContentFirstViewed;
    private List<Method> onPlaySelectedMethods;
    private List<Method> onViewed;
    private final String ownerId;
    private Drawable pausedDrawable;
    private ImageButton playButton;
    private IngressPlayButton playButtonWideScreen;
    private Drawable playDrawable;
    private AnimationDrawable playingDrawable;
    private int podcastFeaturePlayRightTextMargin;
    private PodcastFeaturedPlayElement podcastFeaturedPlayElement;
    private TextView primaryTextView;
    private final Resources resources;
    private TextView secondaryTextView;
    private TextView tertiaryTextView;
    private TextView titleTextView;

    public FeaturePlayViewHolder(final View itemView, final LifecycleOwner lifecycleOwner, final MethodsDispatcher methodsDispatcher, final String ownerId, final Resources resources, final Boolean isScreenSizeGreaterThanMD, final Boolean isActionBarHidden) {
        super(itemView);
        this.isFirstView = true;
        this.lifecycleOwner = lifecycleOwner;
        this.methodsDispatcher = methodsDispatcher;
        this.ownerId = ownerId;
        this.resources = resources;
        this.isScreenSizeGreaterThanMD = isScreenSizeGreaterThanMD;
        this.isActionBarHidden = isActionBarHidden.booleanValue();
        init(itemView);
    }

    private void init(final View itemView) {
        this.context = itemView.getContext();
        this.backgroundImageView = (ImageView) itemView.findViewById(R$id.background_gradient_image_view);
        this.foregroundImageView = (ImageView) itemView.findViewById(R$id.foreground_gradient_image_view);
        this.titleTextView = (TextView) itemView.findViewById(R$id.title);
        RuntimeStyleSheet runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        this.titleTextView.setTypeface(runtimeStyleSheet.getHeadline2TypeFace());
        this.titleTextView.setTextSize(0, runtimeStyleSheet.getHeadline2TextSize());
        this.titleTextView.setAllCaps(runtimeStyleSheet.isHeadline2AllCaps());
        if (Build.VERSION.SDK_INT >= 28) {
            this.titleTextView.setLineHeight(runtimeStyleSheet.getHeadline2LineHeight());
        }
        this.primaryTextView = (TextView) itemView.findViewById(R$id.primary_text);
        this.secondaryTextView = (TextView) itemView.findViewById(R$id.secondary_text);
        this.tertiaryTextView = (TextView) itemView.findViewById(R$id.tertiary_text);
        TextView textView = (TextView) itemView.findViewById(R$id.label);
        this.labelTextView = textView;
        textView.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
        this.playButton = (ImageButton) itemView.findViewById(R$id.PlayButton);
        this.playButtonWideScreen = (IngressPlayButton) itemView.findViewById(R$id.PlayButtonWideScreen);
        if (PodcastFeatureGating.IS_SONIC_RUSH.isEnabled()) {
            this.playDrawable = AppCompatResources.getDrawable(this.context, R$drawable.refresh_player_btn_play);
            this.playButton.setBackground(AppCompatResources.getDrawable(this.context, R$drawable.refresh_button_solid_background));
            this.playingDrawable = (AnimationDrawable) AppCompatResources.getDrawable(this.context, R$drawable.podcast_equalizer_secondary_feature_play);
            this.pausedDrawable = AppCompatResources.getDrawable(this.context, R$drawable.ic_refresh_playback_equalizeroff);
        } else {
            this.playDrawable = AppCompatResources.getDrawable(this.context, R$drawable.player_btn_play);
            this.playButton.setBackground(this.resources.getDrawable(R$drawable.button_circle));
            this.playingDrawable = (AnimationDrawable) AppCompatResources.getDrawable(this.context, R$drawable.podcast_equalizer_primary_feature_play);
            this.pausedDrawable = AppCompatResources.getDrawable(this.context, R$drawable.ic_playback_equalizeroff);
        }
        this.gradientHeightOffsetForeground = this.resources.getDimension(R$dimen.gradient_height_offset_foreground);
        this.gradientHeightOffsetBackground = this.resources.getDimension(R$dimen.gradient_height_offset_background);
        this.gradientTopOffsetBackground = this.resources.getDimension(R$dimen.gradient_top_offset_background);
        this.miniSpace = (int) this.resources.getDimension(R$dimen.podcast_spacer_mini);
        this.largeSpace = (int) this.resources.getDimension(R$dimen.podcast_spacer_large);
        this.podcastFeaturePlayRightTextMargin = (int) this.resources.getDimension(R$dimen.podcast_feature_play_right_text_margin);
        if (this.isActionBarHidden) {
            ((ViewGroup.MarginLayoutParams) this.backgroundImageView.getLayoutParams()).topMargin = (int) this.resources.getDimension(R$dimen.podcast_spacer_huge);
        }
        addPlaybackStateCallbackAndSetPlaybackState();
    }

    public void addPlaybackStateCallbackAndSetPlaybackState() {
        Podcast.getPlayback().addStateCallback(this);
        if (isFeaturePlayCurrentlyPlaying()) {
            bindPlaybackState(Podcast.getPlayback().getPlaybackState());
        }
    }

    public void removePlaybackStateCallback() {
        Podcast.getPlayback().removeStateCallback(this);
    }

    public void bind(final EpisodeFeaturedPlayElement element) {
        this.onViewed = element.getOnViewed();
        this.onContentFirstViewed = element.getOnContentFirstViewed();
        this.episodeFeaturedPlayElement = element;
        setOrHideText(this.labelTextView, element.getLabel());
        setOrHideText(this.titleTextView, element.getTitle());
        this.titleTextView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.podcast.views.galleryTemplate.FeaturePlayViewHolder.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                FeaturePlayViewHolder featurePlayViewHolder = FeaturePlayViewHolder.this;
                if (featurePlayViewHolder.isTextViewEllipsized(featurePlayViewHolder.titleTextView)) {
                    FeaturePlayViewHolder.this.titleTextView.setTextSize(0, FeaturePlayViewHolder.this.resources.getDimension(R$dimen.podcast_detail_template_header_truncated_primary_text_size));
                }
                FeaturePlayViewHolder.this.titleTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        setPrimaryText(element.getPrimaryText());
        setSecondaryText(element.getSecondaryText());
        setTertiaryText(element.getTertiaryText());
        this.backgroundImageView.setClickable(true);
        this.backgroundImageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.FeaturePlayViewHolder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FeaturePlayViewHolder.this.methodsDispatcher.dispatch(FeaturePlayViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
        this.titleTextView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.FeaturePlayViewHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FeaturePlayViewHolder.this.methodsDispatcher.dispatch(FeaturePlayViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
        this.backgroundImageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.podcast.views.galleryTemplate.FeaturePlayViewHolder.4
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                FeaturePlayViewHolder.this.backgroundImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                FeaturePlayViewHolder.this.bindImages(element, FeaturePlayViewHolder.this.backgroundImageView.getMeasuredHeight());
                return true;
            }
        });
        bindPlayback(element);
    }

    public void bind(final PodcastFeaturedPlayElement element) {
        this.onViewed = element.getOnViewed();
        this.onContentFirstViewed = element.getOnContentFirstViewed();
        this.podcastFeaturedPlayElement = element;
        setOrHideText(this.labelTextView, element.getLabel());
        setOrHideText(this.titleTextView, element.getTitle());
        this.titleTextView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.podcast.views.galleryTemplate.FeaturePlayViewHolder.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                FeaturePlayViewHolder featurePlayViewHolder = FeaturePlayViewHolder.this;
                if (featurePlayViewHolder.isTextViewEllipsized(featurePlayViewHolder.titleTextView)) {
                    FeaturePlayViewHolder.this.titleTextView.setTextSize(0, FeaturePlayViewHolder.this.resources.getDimension(R$dimen.podcast_detail_template_header_truncated_primary_text_size));
                }
                FeaturePlayViewHolder.this.titleTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        setPrimaryText(element.getPrimaryText());
        setSecondaryText(element.getSecondaryText());
        setTertiaryText(element.getTertiaryText());
        this.backgroundImageView.setClickable(true);
        this.backgroundImageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.FeaturePlayViewHolder.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FeaturePlayViewHolder.this.methodsDispatcher.dispatch(FeaturePlayViewHolder.this.ownerId, element.getOnItemSelected());
            }
        });
        this.backgroundImageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.podcast.views.galleryTemplate.FeaturePlayViewHolder.7
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                FeaturePlayViewHolder.this.backgroundImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                FeaturePlayViewHolder.this.bindImages(element, FeaturePlayViewHolder.this.backgroundImageView.getMeasuredHeight());
                return true;
            }
        });
        bindPlayback(element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindImages(final EpisodeFeaturedPlayElement element, final int backgroundImageViewHeight) {
        if (this.isScreenSizeGreaterThanMD.booleanValue()) {
            bindImagesForWideScreen(element, backgroundImageViewHeight);
        } else {
            bindImagesForNormalScreen(element, backgroundImageViewHeight);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindImages(final PodcastFeaturedPlayElement element, final int finalImageViewHeight) {
        if (this.isScreenSizeGreaterThanMD.booleanValue()) {
            bindImagesForWideScreen(element, finalImageViewHeight);
        } else {
            bindImagesForNormalScreen(element, finalImageViewHeight);
        }
    }

    private void bindImagesForWideScreen(final EpisodeFeaturedPlayElement element, final int backgroundImageViewHeight) {
        DisplayMetrics displayMetrics = this.itemView.getResources().getDisplayMetrics();
        Picasso.get().load(element.getBackgroundImage()).resize(displayMetrics.widthPixels, backgroundImageViewHeight).centerCrop().transform(new BlurTransformation(this.context)).transform(new GradientTransformation(this.gradientHeightOffsetBackground)).into(this.backgroundImageView);
        Picasso.get().load(element.getForegroundImage()).placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).resize(displayMetrics.widthPixels / 2, backgroundImageViewHeight).centerCrop().transform(new GradientTransformation(0.0f)).transform(new HorizontalGradientTransformation()).into(this.foregroundImageView);
    }

    private void bindImagesForWideScreen(final PodcastFeaturedPlayElement element, final int backgroundImageViewHeight) {
        DisplayMetrics displayMetrics = this.itemView.getResources().getDisplayMetrics();
        Picasso.get().load(element.getBackgroundImage()).resize(displayMetrics.widthPixels, backgroundImageViewHeight).centerCrop().transform(new BlurTransformation(this.context)).transform(new GradientTransformation(this.gradientHeightOffsetBackground)).into(this.backgroundImageView);
        Picasso.get().load(element.getForegroundImage()).placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).resize(displayMetrics.widthPixels / 2, backgroundImageViewHeight).centerCrop().transform(new GradientTransformation(0.0f)).transform(new HorizontalGradientTransformation()).into(this.foregroundImageView);
    }

    private void bindImagesForNormalScreen(final EpisodeFeaturedPlayElement element, final int backgroundImageViewHeight) {
        GradientTransformation gradientTransformation;
        DisplayMetrics displayMetrics = this.itemView.getResources().getDisplayMetrics();
        if (this.isActionBarHidden) {
            gradientTransformation = new GradientTransformation(0.0f, this.gradientTopOffsetBackground);
        } else {
            gradientTransformation = new GradientTransformation(0.0f);
        }
        Picasso.get().load(element.getBackgroundImage()).resize(displayMetrics.widthPixels, backgroundImageViewHeight).centerCrop().transform(new BlurTransformation(this.context)).transform(gradientTransformation).into(this.backgroundImageView);
        Picasso.get().load(element.getForegroundImage()).placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).resize((int) this.resources.getDimension(R$dimen.podcast_feature_play_foreground_image_height), (int) this.resources.getDimension(R$dimen.podcast_feature_play_foreground_image_width)).centerCrop().transform(new RoundedCornersTransformation(this.resources.getDimension(R$dimen.podcast_corner_radius_medium))).transform(new GradientTransformation(this.gradientHeightOffsetForeground)).into(this.foregroundImageView);
    }

    private void bindImagesForNormalScreen(final PodcastFeaturedPlayElement element, final int backgroundImageViewHeight) {
        GradientTransformation gradientTransformation;
        DisplayMetrics displayMetrics = this.itemView.getResources().getDisplayMetrics();
        if (this.isActionBarHidden) {
            gradientTransformation = new GradientTransformation(0.0f, this.gradientTopOffsetBackground);
        } else {
            gradientTransformation = new GradientTransformation(0.0f);
        }
        Picasso.get().load(element.getBackgroundImage()).resize(displayMetrics.widthPixels, backgroundImageViewHeight).centerCrop().transform(new BlurTransformation(this.context)).transform(gradientTransformation).into(this.backgroundImageView);
        Picasso.get().load(element.getForegroundImage()).placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).resize((int) this.resources.getDimension(R$dimen.podcast_feature_play_foreground_image_height), (int) this.resources.getDimension(R$dimen.podcast_feature_play_foreground_image_width)).centerCrop().transform(new RoundedCornersTransformation(this.resources.getDimension(R$dimen.podcast_corner_radius_medium))).transform(new GradientTransformation(this.gradientHeightOffsetForeground)).into(this.foregroundImageView);
    }

    private void bindPlayback(final EpisodeFeaturedPlayElement element) {
        this.onPlaySelectedMethods = element.getPlay().getOnPlaySelected();
        bindPlaybackState(Podcast.getPlayback().getPlaybackState());
    }

    private void bindPlayback(final PodcastFeaturedPlayElement element) {
        this.onPlaySelectedMethods = element.getPlay().getOnPlaySelected();
        bindPlaybackState(Podcast.getPlayback().getPlaybackState());
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(final int state) {
        bindPlaybackState(state);
    }

    private void bindPlaybackState(final int state) {
        if (!isFeaturePlayCurrentlyPlaying()) {
            stopEqualizer();
            return;
        }
        if (state == 3) {
            startEqualizer();
        } else if (state == 2 || state == 6) {
            pauseEqualizer();
        } else {
            stopEqualizer();
        }
    }

    private void startEqualizer() {
        if (this.isScreenSizeGreaterThanMD.booleanValue()) {
            this.playButtonWideScreen.bindPlaying();
            this.playButtonWideScreen.setOnClickListener(null);
        } else {
            this.playButton.setImageDrawable(this.playingDrawable);
            this.playingDrawable.start();
            this.playButton.setOnClickListener(null);
        }
    }

    private void pauseEqualizer() {
        if (this.isScreenSizeGreaterThanMD.booleanValue()) {
            this.playButtonWideScreen.bindPaused();
            this.playButtonWideScreen.setOnClickListener(null);
        } else {
            this.playButton.setImageDrawable(this.pausedDrawable);
            this.playButton.setOnClickListener(null);
        }
    }

    private void stopEqualizer() {
        if (this.isScreenSizeGreaterThanMD.booleanValue()) {
            this.playButtonWideScreen.bindPlay();
            this.playButtonWideScreen.setClickable(true);
            this.playButtonWideScreen.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.FeaturePlayViewHolder.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PodcastHapticsUtil.triggerHaptics();
                    FeaturePlayViewHolder.this.methodsDispatcher.dispatch(FeaturePlayViewHolder.this.ownerId, FeaturePlayViewHolder.this.onPlaySelectedMethods);
                }
            });
        } else {
            this.playButton.setImageDrawable(this.playDrawable);
            this.playButton.setClickable(true);
            this.playButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.FeaturePlayViewHolder.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PodcastHapticsUtil.triggerHaptics();
                    FeaturePlayViewHolder.this.methodsDispatcher.dispatch(FeaturePlayViewHolder.this.ownerId, FeaturePlayViewHolder.this.onPlaySelectedMethods);
                }
            });
        }
    }

    public void handleOnViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onViewed);
        if (this.isFirstView) {
            handleOnContentFirstViewed();
        }
    }

    private void handleOnContentFirstViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onContentFirstViewed);
        this.isFirstView = false;
    }

    private void setPrimaryText(final CharSequence text) {
        setOrHideText(this.primaryTextView, text);
        if (TextUtils.isEmpty(text) || this.isScreenSizeGreaterThanMD.booleanValue()) {
            return;
        }
        setMargins(this.primaryTextView, this.largeSpace);
    }

    private void setSecondaryText(final CharSequence text) {
        setOrHideText(this.secondaryTextView, text);
        if (TextUtils.isEmpty(text) || this.isScreenSizeGreaterThanMD.booleanValue()) {
            return;
        }
        setMargins(this.primaryTextView, this.miniSpace);
        setMargins(this.secondaryTextView, this.largeSpace);
    }

    private void setTertiaryText(final CharSequence text) {
        setOrHideText(this.tertiaryTextView, text);
        if (TextUtils.isEmpty(text) || this.isScreenSizeGreaterThanMD.booleanValue()) {
            return;
        }
        setMargins(this.secondaryTextView, this.miniSpace);
        setMargins(this.tertiaryTextView, this.largeSpace);
    }

    private void setOrHideText(final TextView textView, final CharSequence text) {
        textView.setText(text);
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private boolean isFeaturePlayCurrentlyPlaying() {
        EpisodeFeaturedPlayElement episodeFeaturedPlayElement = this.episodeFeaturedPlayElement;
        if (episodeFeaturedPlayElement != null) {
            String mediaId = episodeFeaturedPlayElement.getPlay().getMediaId();
            logger.debug("featurePlayMediaId: " + mediaId + " mediaId: " + Podcast.getMediaId());
            return mediaId.equals(Podcast.getMediaId());
        }
        PodcastFeaturedPlayElement podcastFeaturedPlayElement = this.podcastFeaturedPlayElement;
        if (podcastFeaturedPlayElement == null) {
            return false;
        }
        String collectionId = podcastFeaturedPlayElement.getPlay().getCollectionId();
        logger.debug("featurePlayCollectionId: " + collectionId + " mediaCollectionId: " + Podcast.getMediaCollectionId());
        return collectionId.equals(Podcast.getMediaCollectionId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTextViewEllipsized(final TextView textView) {
        Layout layout;
        return textView != null && (layout = textView.getLayout()) != null && layout.getLineCount() > 1 && layout.getEllipsisCount(1) > 0;
    }

    private void setMargins(View view, int bottom) {
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin = bottom;
    }
}
