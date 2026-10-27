package com.amazon.podcast.views.episodeRowItemsListView;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.ButtonElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.CompletedElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import UpsellInterface.v1_0.PlaybackMode;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.completed.Completed;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.Accessibility;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.SmallUpsellButton;
import com.amazon.podcast.views.Strings;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.contextMenu.ContextMenus;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodeRowItemView extends ConstraintLayout implements Playback.StateCallback, Media.MediaMetadataCallback, Accessibility {
    private static final Logger logger = LoggerFactory.getLogger("EpisodeRowItemView");
    private Completed completed;
    private ImageView completedIndicator;
    private LiveData<Completed> completedLiveData;
    private UiMetricAttributes$ContentName contentName;
    private Context context;
    private ImageView contextMenu;
    private EmberTextView description;
    private EpisodeRowItemDescriptionBadge descriptionBadge;
    private DownloadView downloadView;
    private EpisodeRowItemElement episodeRowItemElement;
    private ImageView equalizer;
    private AnimationDrawable equalizerDrawable;
    private FragmentManager fragmentManager;
    private ImageView image;
    private EpisodeRowItemImageBadge imageBadge;
    private int imageDimension;
    private ImageView imageOverlay;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private UiMetricAttributes$PageType pageType;
    private PlayView playView;
    private Playback playback;
    private EmberTextView primaryText;
    private EmberTextView publishDateLabel;
    private Resources resources;
    private EmberTextView secondaryText;
    private String seeAllPageTitle;
    private TemplateContext templateContext;
    private ConstraintLayout textView;
    private RoundedCornersTransformation transformation;
    private SmallUpsellButton upsellButton;

    public EpisodeRowItemView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.resources = context.getResources();
        init();
    }

    public EpisodeRowItemView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final TemplateContext templateContext, final UiMetricAttributes$PageType pageType, final String seeAllPageTitle) {
        super(context);
        this.context = context;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.resources = context.getResources();
        this.templateContext = templateContext;
        this.fragmentManager = templateContext.getFragmentManager();
        this.playback = Podcast.getPlayback();
        this.pageType = pageType;
        this.contentName = EpisodeRowItemsMethods.getContentName(pageType);
        this.seeAllPageTitle = seeAllPageTitle;
        init();
    }

    public EpisodeRowItemView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final FragmentManager fragmentManager, final UiMetricAttributes$PageType pageType, final String seeAllPageTitle) {
        super(context);
        this.context = context;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.resources = context.getResources();
        this.fragmentManager = fragmentManager;
        this.playback = Podcast.getPlayback();
        this.pageType = pageType;
        this.contentName = EpisodeRowItemsMethods.getContentName(pageType);
        this.seeAllPageTitle = seeAllPageTitle;
        init();
    }

    public void bindMetadata(final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final TemplateContext templateContext, final UiMetricAttributes$PageType pageType) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.resources = this.context.getResources();
        this.templateContext = templateContext;
        this.fragmentManager = templateContext.getFragmentManager();
        this.playback = Podcast.getPlayback();
        this.pageType = pageType;
        this.contentName = EpisodeRowItemsMethods.getContentName(pageType);
    }

    public EpisodeRowItemView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final FragmentManager fragmentManager, final UiMetricAttributes$PageType pageType) {
        super(context);
        this.context = context;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.fragmentManager = fragmentManager;
        this.resources = context.getResources();
        this.playback = Podcast.getPlayback();
        this.pageType = pageType;
        this.contentName = EpisodeRowItemsMethods.getContentName(pageType);
        init();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void bind(final EpisodeRowItemElement element) {
        String description;
        EpisodeRowItemElement episodeRowItemElement = this.episodeRowItemElement;
        if (episodeRowItemElement != null) {
            String mediaId = episodeRowItemElement.getMediaId();
            if (!StringUtils.isBlank(mediaId) && !mediaId.equals(element.getMediaId())) {
                clearPreviousState();
                clearCallbacks();
            }
        }
        this.episodeRowItemElement = element;
        this.primaryText.setText(element.getPrimaryText());
        String secondaryText = element.getSecondaryText();
        if (!StringUtils.isBlank(secondaryText)) {
            this.secondaryText.setText(secondaryText);
            this.secondaryText.setVisibility(0);
        }
        if (element.getDescription() != null) {
            if (StringUtils.isBlank(element.getDescription())) {
                description = this.context.getResources().getString(R$string.podcast_description_placeholder_text);
            } else {
                description = element.getDescription();
            }
            EmberTextView emberTextView = this.description;
            emberTextView.setText(Strings.convertToSpannable(description, emberTextView));
            this.description.setVisibility(0);
        }
        String tertiaryText = element.getTertiaryText();
        this.publishDateLabel.setText(tertiaryText);
        RequestCreator requestCreatorError = Picasso.get().load(element.getImage()).centerCrop().placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId());
        int i = this.imageDimension;
        requestCreatorError.resize(i, i).transform(this.transformation).into(this.image);
        this.imageBadge.bind(this.episodeRowItemElement, this.methodsDispatcher, this.ownerId);
        this.descriptionBadge.bind(this.episodeRowItemElement, this.methodsDispatcher, this.ownerId);
        bindUpsellButton(this.episodeRowItemElement.getButton());
        this.completedLiveData = Podcast.getAppSync().completed().readLive(this.context, element.getCompleted().getId());
        bindCompletedElement(element.getCompleted());
        setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EpisodeRowItemView.this.methodsDispatcher.dispatch(EpisodeRowItemView.this.ownerId, element.getOnItemSelected());
            }
        });
        this.textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EpisodeRowItemView.this.methodsDispatcher.dispatch(EpisodeRowItemView.this.ownerId, element.getOnItemSelected());
            }
        });
        if (element.getEpisodeOptions() == null) {
            this.contextMenu.setVisibility(8);
        }
        if (element.getDownload() == null) {
            this.downloadView.setVisibility(8);
        } else {
            this.downloadView.setVisibility(0);
        }
        if (PlaybackMode.NONE.equals(this.episodeRowItemElement.getPlaybackMode())) {
            this.playView.setVisibility(8);
            return;
        }
        bindContextMenuButton(element.getEpisodeOptions());
        String mediaId2 = this.episodeRowItemElement.getMediaId();
        this.playView.bind(mediaId2, element.getPrimaryText(), element.getOnPlaySelected(), element.getOnPauseSelected(), element.getOnResumeSelected(), element.getBookmark(), element.getCompleted(), this.lifecycleOwner, this.methodsDispatcher, this.ownerId, tertiaryText, secondaryText);
        String strMediaId = this.playback.getMedia().mediaId();
        bindPlaybackState(mediaId2, strMediaId, this.playback.getPlaybackState());
        this.playView.setVisibility(0);
        this.playback.getMedia().addMediaMetadataCallback(this);
        if (UiMetricAttributes$PageType.PODCASTS_SEE_ALL_EPISODES.equals(this.pageType)) {
            this.downloadView.bind(element.getDownload(), element.getPrimaryText(), this.lifecycleOwner, this.methodsDispatcher, this.ownerId, this.pageType, this.seeAllPageTitle);
        } else {
            this.downloadView.bind(element.getDownload(), element.getPrimaryText(), this.lifecycleOwner, this.methodsDispatcher, this.ownerId, this.pageType, this.contentName);
        }
        if (mediaId2 != null && mediaId2.equals(strMediaId)) {
            this.playback.addStateCallback(this);
        } else {
            clearCallbacks();
        }
    }

    private void bindUpsellButton(final ButtonElement buttonElement) {
        if (buttonElement == null) {
            this.upsellButton.setVisibility(8);
            return;
        }
        this.upsellButton.bindUpsellButtonText(buttonElement.getText());
        this.upsellButton.setUpsellButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EpisodeRowItemView.this.methodsDispatcher.dispatch(EpisodeRowItemView.this.ownerId, buttonElement.getOnItemSelected());
            }
        });
        this.upsellButton.setVisibility(0);
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(final int state) {
        bindPlaybackState(this.episodeRowItemElement.getMediaId(), this.playback.getMedia().mediaId(), state);
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(final MediaMetadataElement metadataElement) {
        if (metadataElement == null) {
            return;
        }
        String mediaId = metadataElement.getMediaId();
        String mediaId2 = this.episodeRowItemElement.getMediaId();
        bindPlaybackState(mediaId2, mediaId, this.playback.getPlaybackState());
        if (mediaId2 != null && mediaId2.equals(mediaId)) {
            this.playback.addStateCallback(this);
        } else {
            this.playback.removeStateCallback(this);
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        EpisodeRowItemElement episodeRowItemElement = this.episodeRowItemElement;
        if (episodeRowItemElement == null) {
            return;
        }
        String mediaId = episodeRowItemElement.getMediaId();
        Media media = this.playback.getMedia();
        String strMediaId = media.mediaId();
        bindPlaybackState(mediaId, strMediaId, this.playback.getPlaybackState());
        if (visibility == 0) {
            if (mediaId != null && mediaId.equals(strMediaId)) {
                this.playback.addStateCallback(this);
            }
            media.addMediaMetadataCallback(this);
            return;
        }
        this.playback.removeStateCallback(this);
        media.removeMediaMetadataCallback(this);
    }

    private void clearCallbacks() {
        Playback playback = this.playback;
        if (playback == null) {
            return;
        }
        playback.removeStateCallback(this);
        Media media = this.playback.getMedia();
        if (media == null) {
            return;
        }
        media.removeMediaMetadataCallback(this);
    }

    private void clearPreviousState() {
        this.completedLiveData.removeObservers(this.lifecycleOwner);
        this.completedIndicator.setVisibility(4);
        this.imageOverlay.setVisibility(4);
    }

    private void init() {
        View viewInflateLayout = inflateLayout();
        this.primaryText = (EmberTextView) findViewById(R$id.podcast_episode_row_item_primary_text);
        this.secondaryText = (EmberTextView) findViewById(R$id.podcast_episode_row_item_secondary_text);
        this.description = (EmberTextView) findViewById(R$id.podcast_episode_row_item_description);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_episode_row_item_publish_date_label);
        this.publishDateLabel = emberTextView;
        emberTextView.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
        this.image = (ImageView) findViewById(R$id.podcast_episode_row_item_image);
        this.completedIndicator = (ImageView) findViewById(R$id.podcast_episode_row_item_completed_indicator);
        this.imageOverlay = (ImageView) findViewById(R$id.podcast_episode_row_item_image_overlay);
        if (PodcastFeatureGating.IS_SONIC_RUSH.isEnabled()) {
            this.equalizer = (ImageView) findViewById(R$id.refresh_podcast_episode_row_item_image_equalizer);
        } else {
            this.equalizer = (ImageView) findViewById(R$id.podcast_episode_row_item_image_equalizer);
        }
        this.playView = (PlayView) findViewById(R$id.podcast_episode_row_item_play_view);
        this.downloadView = (DownloadView) findViewById(R$id.podcast_episode_row_item_download_view);
        this.contextMenu = (ImageView) findViewById(R$id.podcast_episode_row_item_context_menu);
        this.imageBadge = (EpisodeRowItemImageBadge) viewInflateLayout.findViewById(R$id.podcast_episode_row_item_image_badge);
        this.descriptionBadge = (EpisodeRowItemDescriptionBadge) viewInflateLayout.findViewById(R$id.podcast_episode_row_item_description_badge);
        this.upsellButton = (SmallUpsellButton) viewInflateLayout.findViewById(R$id.podcast_episode_row_item_upsell_button);
        this.equalizerDrawable = (AnimationDrawable) this.equalizer.getDrawable();
        this.imageDimension = this.resources.getDimensionPixelSize(R$dimen.podcast_visual_row_item_image_dimension);
        this.transformation = new RoundedCornersTransformation(this.resources.getDimensionPixelSize(R$dimen.podcast_visual_row_item_image_corner_radius));
        this.textView = (ConstraintLayout) findViewById(R$id.podcast_episode_row_item_text_view);
        this.playView.setAccessibilityCallback(this);
    }

    private View inflateLayout() {
        if (UiMetricAttributes$PageType.BROWSE_HOME.equals(this.pageType) || UiMetricAttributes$PageType.PODCASTS_HOME.equals(this.pageType)) {
            return View.inflate(getContext(), R$layout.podcast_episode_descriptive_shoveler_row_item, this);
        }
        return View.inflate(getContext(), R$layout.podcast_episode_row_item, this);
    }

    private void bindContextMenuButton(final EpisodeOptionsElement episodeOptionsElement) {
        if (episodeOptionsElement == null) {
            return;
        }
        this.contextMenu.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemView.4
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                ContextMenus.show(episodeOptionsElement, EpisodeRowItemView.this.context, EpisodeRowItemView.this.resources, EpisodeRowItemView.this.methodsDispatcher, EpisodeRowItemView.this.ownerId, EpisodeRowItemView.this.lifecycleOwner, EpisodeRowItemView.this.fragmentManager, EpisodeRowItemView.this.contextMenu, "EpisodeRowItemView", EpisodeRowItemView.this.pageType);
            }
        });
        this.contextMenu.setVisibility(0);
        this.contextMenu.setContentDescription(String.format(this.resources.getString(R$string.podcast_more_options_for_episode_button), this.episodeRowItemElement.getPrimaryText()));
    }

    private void bindPlaybackState(final String playId, final String mediaId, final int state) {
        if (playId != null && !playId.equals(mediaId)) {
            hideEqualizer();
            bindCompletedIndicator(this.completed);
        } else if (state == 3) {
            this.imageOverlay.setVisibility(0);
            startEqualizer();
            this.completedIndicator.setVisibility(4);
        } else {
            if (state == 6 || state == 0) {
                return;
            }
            hideEqualizer();
            bindCompletedIndicator(this.completed);
        }
    }

    private void startEqualizer() {
        AnimationDrawable animationDrawable = this.equalizerDrawable;
        if (animationDrawable != null) {
            animationDrawable.start();
        }
        this.equalizer.setVisibility(0);
    }

    private void hideEqualizer() {
        AnimationDrawable animationDrawable = this.equalizerDrawable;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        this.equalizer.setVisibility(4);
    }

    private void bindCompletedElement(final CompletedElement completedElement) {
        this.completedLiveData.observe(this.lifecycleOwner, new Observer<Completed>() { // from class: com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemView.5
            @Override // androidx.lifecycle.Observer
            public void onChanged(Completed completed) {
                String mediaId = EpisodeRowItemView.this.episodeRowItemElement.getMediaId();
                String strMediaId = EpisodeRowItemView.this.playback.getMedia().mediaId();
                int playbackState = EpisodeRowItemView.this.playback.getPlaybackState();
                EpisodeRowItemView.this.completed = completed;
                if ((mediaId != null && mediaId.equals(strMediaId) && playbackState == 3) || completed == null || !completed.getId().equals(mediaId)) {
                    return;
                }
                EpisodeRowItemView.this.bindCompletedIndicator(completed);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindCompletedIndicator(final Completed completed) {
        String mediaId = this.episodeRowItemElement.getMediaId();
        if (completed == null || completed.getId().equals(mediaId)) {
            if (completed == null ? false : completed.getCompleted()) {
                this.completedIndicator.setVisibility(0);
                this.imageOverlay.setVisibility(0);
            } else {
                this.completedIndicator.setVisibility(4);
                this.imageOverlay.setVisibility(4);
            }
        }
    }

    @Override // com.amazon.podcast.views.Accessibility
    public void onAccessibilityLabelChange(String newLabel) {
        this.textView.setContentDescription(newLabel);
    }
}
