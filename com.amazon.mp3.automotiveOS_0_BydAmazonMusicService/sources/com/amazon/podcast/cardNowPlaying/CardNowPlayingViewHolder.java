package com.amazon.podcast.cardNowPlaying;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardElement;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.FollowButtonElement;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.SaveButtonElement;
import android.content.Context;
import android.content.res.Resources;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$anim;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.R$string;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.follow.Follow;
import com.amazon.podcast.haptics.PodcastHapticsUtil;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.save.Save;
import com.amazon.podcast.transformations.BlurTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.Strings;
import com.amazon.podcast.views.ToastView;
import com.amazon.podcast.views.contextMenu.ContextMenus;
import com.squareup.picasso.Picasso;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class CardNowPlayingViewHolder extends RecyclerView.ViewHolder implements Media.MediaMetadataCallback, Playback.StateCallback, Playback.DurationCallback, Playback.PositionCallback {
    private static final String TAG = "CardNowPlayingViewHolder";
    private static final Logger logger = LoggerFactory.getLogger(CardNowPlayingViewHolder.class.getSimpleName());
    private final EmberTextView aboutDescription;
    private ImageButton aboutMinimizeButton;
    private CardView aboutView;
    private EmberTextView aboutViewTitle;
    private ImageView artwork_background;
    private BlurTransformation blurTransformation;
    private int cardHeight;
    private final EmberTextView cardPodcastDescription;
    private final LinearLayout cardPodcastDescriptionMetadata;
    private final EmberTextView cardPodcastDescriptionReadMore;
    private final LinearLayout cardTitlesMetadata;
    private CardView cardView;
    private int cardWidth;
    private final Context context;
    private final EmberTextView durationText;
    private EmberTextView followButton;
    private FollowButtonElement followButtonElement;
    private ToastView followToastView;
    private FragmentManager fragmentManager;
    private final ImageView image;
    private boolean isFollowed;
    private boolean isSaved;
    private LifecycleOwner lifecycleOwner;
    private int maxLineCount;
    private String mediaId;
    private final MethodsDispatcher methodsDispatcher;
    private ImageButton miniPlayButton;
    private final ImageView overflowButton;
    private final String ownerId;
    private Playback playback;
    private LinearLayout primaryButton;
    private EmberTextView primaryButtonText;
    private int primaryButtonWidth;
    private final EmberTextView primaryText;
    private final EmberTextView progressDurationDivider;
    private final EmberTextView progressText;
    private final Resources resources;
    private LinearLayout saveButton;
    private SaveButtonElement saveButtonElement;
    private ImageView saveButtonIcon;
    private EmberTextView saveEpisodeButtonText;
    private ToastView savePodcastToastView;
    private final EmberTextView secondaryText;

    public CardNowPlayingViewHolder(@NonNull final View itemView, final Resources resources, final String ownerId, final MethodsDispatcher methodsDispatcher, final FragmentManager fragmentManager, final int cardWidth, final int cardHeight) {
        super(itemView);
        Context context = itemView.getContext();
        this.context = context;
        this.ownerId = ownerId;
        this.resources = resources;
        this.methodsDispatcher = methodsDispatcher;
        this.fragmentManager = fragmentManager;
        this.cardWidth = cardWidth;
        this.cardHeight = cardHeight;
        this.maxLineCount = resources.getInteger(R$integer.podcast_card_description_max_lines);
        RuntimeStyleSheet runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        this.cardView = (CardView) itemView.findViewById(R$id.podcast_card_view);
        EmberTextView emberTextView = (EmberTextView) itemView.findViewById(R$id.podcast_card_primary_text);
        this.primaryText = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.secondaryText = (EmberTextView) itemView.findViewById(R$id.podcast_card_secondary_text);
        this.image = (ImageView) itemView.findViewById(R$id.podcast_card_now_playing_artwork);
        this.artwork_background = (ImageView) itemView.findViewById(R$id.podcast_card_artwork_background);
        this.miniPlayButton = (ImageButton) itemView.findViewById(R$id.podcast_card_mini_play_button);
        LinearLayout linearLayout = (LinearLayout) itemView.findViewById(R$id.podcast_card_now_playing_primary_button);
        this.primaryButton = linearLayout;
        linearLayout.setBackground(resources.getDrawable(runtimeStyleSheet.getAccentRoundedCornersDrawableId()));
        this.primaryButtonText = (EmberTextView) itemView.findViewById(R$id.podcast_card_primary_button_text);
        this.saveButton = (LinearLayout) itemView.findViewById(R$id.podcast_card_save_button);
        ImageView imageView = (ImageView) itemView.findViewById(R$id.podcast_card_save_button_icon);
        this.saveButtonIcon = imageView;
        imageView.setColorFilter(runtimeStyleSheet.getAccentColor());
        EmberTextView emberTextView2 = (EmberTextView) itemView.findViewById(R$id.podcast_card_save_button_text);
        this.saveEpisodeButtonText = emberTextView2;
        emberTextView2.setTextColor(runtimeStyleSheet.getAccentColor());
        this.savePodcastToastView = new ToastView(context, ToastView.ToastViewType.PLAYLIST, "ADD_PODCAST_NAVIGATION_TOAST_FLAG");
        this.followButton = (EmberTextView) itemView.findViewById(R$id.podcast_card_follow_button);
        this.followToastView = new ToastView(context, ToastView.ToastViewType.FOLLOW, "FOLLOW_PODCAST_NAVIGATION_TOAST_FLAG");
        CardView cardView = (CardView) itemView.findViewById(R$id.podcast_card_about);
        this.aboutView = cardView;
        cardView.setBackground(resources.getDrawable(Podcast.getRuntimeStyleSheet().getCardAboutBackgroundGradientDrawableId()));
        EmberTextView emberTextView3 = (EmberTextView) itemView.findViewById(R$id.podcast_card_now_playing_about_view_title_label);
        this.aboutViewTitle = emberTextView3;
        emberTextView3.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.aboutMinimizeButton = (ImageButton) itemView.findViewById(R$id.podcast_card_now_playing_about_view_dismiss_button);
        this.aboutDescription = (EmberTextView) itemView.findViewById(R$id.podcast_card_now_playing_about_view_description);
        this.progressText = (EmberTextView) itemView.findViewById(R$id.podcast_card_progress_time);
        this.durationText = (EmberTextView) itemView.findViewById(R$id.podcast_card_duration_time);
        this.progressDurationDivider = (EmberTextView) itemView.findViewById(R$id.podcast_card_progress_duration_divider);
        this.overflowButton = (ImageView) itemView.findViewById(R$id.podcast_card_overflow_button);
        this.cardPodcastDescriptionMetadata = (LinearLayout) itemView.findViewById(R$id.podcast_card_description_metadata);
        this.cardPodcastDescription = (EmberTextView) itemView.findViewById(R$id.podcast_card_description_text);
        this.cardPodcastDescriptionReadMore = (EmberTextView) itemView.findViewById(R$id.podcast_card_description_read_more);
        this.cardTitlesMetadata = (LinearLayout) itemView.findViewById(R$id.podcast_card_episode_podcast_titles_metadata);
        this.playback = Podcast.getPlayback();
        this.blurTransformation = new BlurTransformation(context);
        this.primaryButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder$$ExternalSyntheticLambda2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f$0.lambda$new$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        this.primaryButtonWidth = this.primaryButton.getWidth();
        ViewGroup.LayoutParams layoutParams = this.followButton.getLayoutParams();
        layoutParams.width = this.primaryButtonWidth;
        this.followButton.setLayoutParams(layoutParams);
        this.followButton.setGravity(17);
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    public void bind(final CardElement element) {
        this.mediaId = element.getId();
        setMainCardElementsAccessibilityState(1);
        if (element.getPrimaryText() != null) {
            this.primaryText.setText(element.getPrimaryText());
        }
        bindImage(element);
        if (element.getSecondaryText() != null) {
            this.secondaryText.setText(element.getSecondaryText());
        }
        bindAbout(element);
        bindMiniPlayButton(element);
        bindPrimaryButton(element);
        if (element.getSave() != null) {
            this.followButton.setVisibility(8);
            bindSave(element.getSave());
        } else if (element.getFollow() != null) {
            this.saveButton.setVisibility(8);
            bindFollow(element.getFollow());
        } else {
            logger.debug("Neither save nor follow element present in card element");
        }
        bindContextMenuButton(element.getEpisodeOptions());
    }

    private void bindAbout(final CardElement element) {
        if (StringUtils.isBlank(element.getDescription())) {
            String string = this.resources.getString(R$string.podcast_description_placeholder_text);
            EmberTextView emberTextView = this.aboutDescription;
            emberTextView.setText(Strings.convertToSpannable(string, emberTextView));
            this.cardPodcastDescriptionMetadata.setVisibility(8);
        } else {
            setAboutDescription(element);
            setCardPodcastDescription(element);
        }
        this.aboutMinimizeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CardNowPlayingViewHolder.this.setAboutViewVisibility(8);
            }
        });
    }

    private void setAboutDescription(final CardElement element) {
        this.aboutDescription.setText(Strings.convertToSpannable(element.getDescription(), this.aboutDescription));
        Linkify.addLinks(this.aboutDescription, 15);
        this.aboutDescription.setContentDescription(String.format(this.resources.getString(R$string.podcast_card_now_playing_about_view_description), this.aboutDescription.getText()));
    }

    private void setCardPodcastDescription(final CardElement element) {
        this.cardPodcastDescription.setMaxLines(this.maxLineCount + 1);
        this.cardPodcastDescription.setText(Strings.convertToSpannable(element.getDescription(), this.cardPodcastDescription));
        this.cardPodcastDescription.post(new Runnable() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder.2
            @Override // java.lang.Runnable
            public void run() {
                if (CardNowPlayingViewHolder.this.cardPodcastDescription.getLineCount() > CardNowPlayingViewHolder.this.maxLineCount) {
                    CardNowPlayingViewHolder.this.cardPodcastDescriptionReadMore.setVisibility(0);
                    CardNowPlayingViewHolder.this.cardPodcastDescriptionMetadata.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder.2.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            CardNowPlayingViewHolder.this.methodsDispatcher.dispatch(CardNowPlayingViewHolder.this.ownerId, element.getOnAboutSelected());
                            CardNowPlayingViewHolder.this.setAboutViewVisibility(0);
                            CardNowPlayingViewHolder.this.aboutDescription.requestFocus();
                            CardNowPlayingViewHolder.this.aboutDescription.sendAccessibilityEvent(8);
                        }
                    });
                    CardNowPlayingViewHolder.this.cardPodcastDescription.setMaxLines(CardNowPlayingViewHolder.this.maxLineCount);
                } else {
                    CardNowPlayingViewHolder.this.cardPodcastDescriptionReadMore.setVisibility(8);
                    CardNowPlayingViewHolder.this.cardPodcastDescriptionMetadata.setClickable(false);
                    CardNowPlayingViewHolder.this.cardPodcastDescriptionMetadata.setOnClickListener(null);
                    Linkify.addLinks(CardNowPlayingViewHolder.this.cardPodcastDescription, 15);
                }
                CardNowPlayingViewHolder.this.cardPodcastDescriptionMetadata.setContentDescription(String.format(CardNowPlayingViewHolder.this.resources.getString(R$string.podcast_card_now_playing_card_podcast_description), CardNowPlayingViewHolder.this.cardPodcastDescription.getText()));
            }
        });
    }

    private void bindPrimaryButton(final CardElement element) {
        if (element.getPrimaryButton() == null) {
            return;
        }
        String text = element.getPrimaryButton().getText();
        if (text != null) {
            this.primaryButtonText.setText(text);
            this.primaryButton.setContentDescription(String.format(this.resources.getString(R$string.podcast_card_now_playing_primary_button), text));
        }
        this.primaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CardNowPlayingViewHolder.this.removePlaybackCallbacks();
                PodcastHapticsUtil.triggerHaptics();
                if (CardNowPlayingViewHolder.this.context instanceof CardNowPlayingActivity) {
                    CardNowPlayingActivity cardNowPlayingActivity = (CardNowPlayingActivity) CardNowPlayingViewHolder.this.context;
                    cardNowPlayingActivity.setShouldClearMedia(false);
                    CardNowPlayingViewHolder.this.playback.dispatch(CardNowPlayingViewHolder.this.ownerId, cardNowPlayingActivity.getOnDismissed());
                }
                CardNowPlayingViewHolder.this.playback.dispatch(CardNowPlayingViewHolder.this.ownerId, element.getPrimaryButton().getOnItemSelected());
            }
        });
    }

    private void bindMiniPlayButton(final CardElement element) {
        this.miniPlayButton.setEnabled(false);
        this.miniPlayButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PodcastHapticsUtil.triggerHaptics();
                if (CardNowPlayingViewHolder.this.playback.getPlaybackState() != 3) {
                    if (CardNowPlayingViewHolder.this.playback.getPlaybackState() == 2) {
                        CardNowPlayingViewHolder.this.methodsDispatcher.dispatch(CardNowPlayingViewHolder.this.ownerId, element.getOnPlaySelected());
                    }
                } else {
                    CardNowPlayingViewHolder.this.methodsDispatcher.dispatch(CardNowPlayingViewHolder.this.ownerId, element.getOnPauseSelected());
                }
                CardNowPlayingViewHolder.this.bitePlayPause(element);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bitePlayPause(final CardElement element) {
        if (this.playback.getMedia() == null || !this.mediaId.equals(element.getId())) {
            return;
        }
        this.playback.playPause();
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(MediaMetadataElement metadataElement) {
        String str = this.mediaId;
        if (str == null || metadataElement == null || !str.equals(metadataElement.getMediaId())) {
            return;
        }
        this.playback.addDurationCallback(this);
        this.playback.addStateCallback(this);
        this.miniPlayButton.setEnabled(true);
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(int state) {
        if (this.miniPlayButton.getAnimation() != null) {
            this.miniPlayButton.getAnimation().cancel();
        }
        if (state == 3) {
            this.miniPlayButton.setContentDescription(this.resources.getString(R$string.podcast_card_now_playing_pause));
            this.miniPlayButton.setImageResource(R$drawable.ic_playback_pause_mini_player);
        } else if (state == 2 || state == 7) {
            this.miniPlayButton.setContentDescription(this.resources.getString(R$string.podcast_card_now_playing_play));
            this.miniPlayButton.setImageResource(R$drawable.ic_play);
        } else {
            this.miniPlayButton.setImageResource(R$drawable.ic_other_loader);
            this.miniPlayButton.startAnimation(AnimationUtils.loadAnimation(this.context, R$anim.podcast_loading_spinner));
        }
    }

    @Override // com.amazon.podcast.media.playback.Playback.DurationCallback
    public void onPlaybackDurationChange(final long duration) {
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder.5
            @Override // java.lang.Runnable
            public void run() {
                CardNowPlayingViewHolder.this.durationText.setText(String.format("%s", Playback.formatTime(Long.valueOf(duration))));
                CardNowPlayingViewHolder.this.cardTitlesMetadata.setContentDescription(String.format(CardNowPlayingViewHolder.this.resources.getString(R$string.podcast_card_now_playing_titles), CardNowPlayingViewHolder.this.primaryText.getText(), CardNowPlayingViewHolder.this.secondaryText.getText(), String.format("%d", Long.valueOf(duration / 1000))));
            }
        });
        this.playback.addPositionCallback(this);
    }

    @Override // com.amazon.podcast.media.playback.Playback.PositionCallback
    public void onPositionChange(final long position) {
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder.6
            @Override // java.lang.Runnable
            public void run() {
                CardNowPlayingViewHolder.this.progressText.setText(String.format("%s", Playback.formatTime(Long.valueOf(position))));
                CardNowPlayingViewHolder.this.progressDurationDivider.setVisibility(0);
            }
        });
    }

    private void bindContextMenuButton(final EpisodeOptionsElement episodeOptionsElement) {
        if (episodeOptionsElement == null) {
            return;
        }
        this.overflowButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ContextMenus.show(episodeOptionsElement, CardNowPlayingViewHolder.this.context, CardNowPlayingViewHolder.this.resources, CardNowPlayingViewHolder.this.playback, CardNowPlayingViewHolder.this.ownerId, CardNowPlayingViewHolder.this.lifecycleOwner, CardNowPlayingViewHolder.this.fragmentManager, CardNowPlayingViewHolder.this.overflowButton, CardNowPlayingViewHolder.TAG, UiMetricAttributes$PageType.PODCAST_NOW_PLAYING);
            }
        });
        this.overflowButton.setVisibility(0);
        this.overflowButton.setContentDescription(this.resources.getString(R$string.podcast_card_now_playing_context_menu_button));
    }

    public void bindSave(final SaveButtonElement element) {
        if (element == null) {
            return;
        }
        this.saveButtonElement = element;
        Podcast.getAppSync().save().readLive(this.context, this.saveButtonElement.getId()).observe(this.lifecycleOwner, new Observer<Save>() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder.8
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Save save) {
                CardNowPlayingViewHolder.this.isSaved = save == null ? false : save.isSaved();
                if (CardNowPlayingViewHolder.this.isSaved) {
                    CardNowPlayingViewHolder.this.saveButtonIcon.setImageResource(R$drawable.ic_podcast_action_cancel);
                    CardNowPlayingViewHolder.this.saveEpisodeButtonText.setText(CardNowPlayingViewHolder.this.saveButtonElement.getUnsaveText());
                    CardNowPlayingViewHolder.this.saveButton.setContentDescription(CardNowPlayingViewHolder.this.resources.getString(R$string.podcast_card_now_playing_save_button_unsave));
                } else {
                    CardNowPlayingViewHolder.this.saveButtonIcon.setImageResource(R$drawable.ic_action_add);
                    CardNowPlayingViewHolder.this.saveEpisodeButtonText.setText(CardNowPlayingViewHolder.this.saveButtonElement.getSaveText());
                    CardNowPlayingViewHolder.this.saveButton.setContentDescription(CardNowPlayingViewHolder.this.resources.getString(R$string.podcast_card_now_playing_save_button_save));
                }
            }
        });
        this.saveButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CardNowPlayingViewHolder.this.handleSaveClick();
            }
        });
        this.saveButton.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSaveClick() {
        if (this.isSaved) {
            this.methodsDispatcher.dispatch(this.ownerId, this.saveButtonElement.getOnUnsave());
            return;
        }
        this.savePodcastToastView.dismissToast();
        this.savePodcastToastView.showToast();
        this.methodsDispatcher.dispatch(this.ownerId, this.saveButtonElement.getOnSave());
    }

    public void bindFollow(final FollowButtonElement element) {
        if (element == null) {
            return;
        }
        this.followButtonElement = element;
        Podcast.getAppSync().follow().readLive(this.context, this.followButtonElement.getId()).observe(this.lifecycleOwner, new Observer() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$bindFollow$1((Follow) obj);
            }
        });
        this.followButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingViewHolder$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindFollow$2(view);
            }
        });
        this.followButton.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindFollow$1(Follow follow) {
        boolean z = follow != null && follow.isFollowed();
        this.isFollowed = z;
        if (z) {
            this.followButton.setText(this.followButtonElement.getUnfollowText());
        } else {
            this.followButton.setText(this.followButtonElement.getFollowText());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindFollow$2(View view) {
        handleFollowClick();
    }

    private void handleFollowClick() {
        if (this.isFollowed) {
            this.methodsDispatcher.dispatch(this.ownerId, this.followButtonElement.getOnUnfollow());
            return;
        }
        this.followToastView.dismissToast();
        this.followToastView.showToast();
        this.methodsDispatcher.dispatch(this.ownerId, this.followButtonElement.getOnFollow());
    }

    public void addMediaMetadataCallbacks() {
        this.playback.getMedia().addMediaMetadataCallback(this);
    }

    public void removePlaybackCallbacks() {
        this.miniPlayButton.setEnabled(false);
        this.playback.getMedia().removeMediaMetadataCallback(this);
        this.playback.removeDurationCallback(this);
        this.playback.removePositionCallback(this);
        this.playback.removeStateCallback(this);
    }

    private void bindImage(final CardElement element) {
        int dimensionPixelSize = this.resources.getDimensionPixelSize(R$dimen.podcast_spacer_base);
        int dimensionPixelSize2 = this.resources.getDimensionPixelSize(R$dimen.podcast_card_minimum_metadata_height);
        int i = this.cardHeight;
        if (i - dimensionPixelSize2 <= 0) {
            this.image.setVisibility(8);
            return;
        }
        int i2 = i - dimensionPixelSize2;
        int i3 = this.cardWidth;
        if (i2 < i3) {
            i3 = (i - dimensionPixelSize2) - dimensionPixelSize;
            ((ViewGroup.MarginLayoutParams) this.image.getLayoutParams()).setMargins(0, dimensionPixelSize, 0, 0);
            this.maxLineCount = 2;
            this.cardPodcastDescription.setMaxLines(2);
        }
        if (element.getImage() != null) {
            ViewGroup.LayoutParams layoutParams = this.image.getLayoutParams();
            layoutParams.width = i3;
            layoutParams.height = i3;
            this.image.setLayoutParams(layoutParams);
            Picasso.get().load(element.getImage()).placeholder(R$drawable.artwork_placeholder).transform(this.blurTransformation).into(this.artwork_background);
            Picasso.get().load(element.getImage()).centerCrop().placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).resize(i3, i3).into(this.image);
        }
    }

    public void setAboutViewVisibility(final int visibility) {
        this.aboutView.setVisibility(visibility);
        if (visibility == 0) {
            setMainCardElementsAccessibilityState(2);
        } else if (visibility == 8) {
            setMainCardElementsAccessibilityState(1);
        }
    }

    public void setMainCardElementsAccessibilityState(final int accessibilityState) {
        this.primaryButton.setImportantForAccessibility(accessibilityState);
        this.saveButton.setImportantForAccessibility(accessibilityState);
        this.cardPodcastDescriptionMetadata.setImportantForAccessibility(accessibilityState);
        this.cardTitlesMetadata.setImportantForAccessibility(accessibilityState);
        this.overflowButton.setImportantForAccessibility(accessibilityState);
    }
}
