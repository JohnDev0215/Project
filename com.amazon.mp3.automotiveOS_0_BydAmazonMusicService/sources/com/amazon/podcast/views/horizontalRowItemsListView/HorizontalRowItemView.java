package com.amazon.podcast.views.horizontalRowItemsListView;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.AccentOutlineBadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.BadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.FollowElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.LightUnborderedBadgeElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.UnborderedBadgeElement;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.providers.EntityTrackingIntentServiceProvider;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.follow.Follow;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.ToastView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class HorizontalRowItemView extends ConstraintLayout {
    private static final String TAG;
    private static final Logger logger;
    private HorizontalRowItemBadge badge;
    private final Context context;
    private ImageView followButton;
    private LinearLayout followButtonContainer;
    private FollowElement followElement;
    private ToastView followToastView;
    private ImageView image;
    private int imageDimension;
    private EmberTextView index;
    private boolean isFollowed;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private EmberTextView primaryText;
    private final Resources resources;
    private EmberTextView secondaryText;
    private EmberTextView tertiaryText;
    private RoundedCornersTransformation transformation;

    static {
        String simpleName = HorizontalRowItemView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public HorizontalRowItemView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final boolean isUsedOnMixedScreen) {
        super(context);
        this.context = context;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.resources = context.getResources();
        inflateLayout(isUsedOnMixedScreen);
        init();
    }

    public void bind(final int position, final HorizontalItemElement element) {
        bind(position, element, null, false, null);
    }

    public void bind(final int position, final HorizontalItemElement element, final BaseViewContentModel model, final boolean didNavigateFromSearchResults, final EntityTrackingIntentServiceProvider entityTrackingIntentServiceProvider) {
        if (position < 0) {
            this.index.setVisibility(8);
        } else {
            this.index.setText(String.valueOf(position));
            this.index.setVisibility(0);
        }
        this.primaryText.setText(element.getPrimaryText());
        String secondaryText = element.getSecondaryText();
        if (TextUtils.isEmpty(secondaryText)) {
            this.secondaryText.setVisibility(8);
        } else {
            this.secondaryText.setVisibility(0);
            this.secondaryText.setText(secondaryText);
        }
        String image = element.getImage() == null ? "https://m.media-amazon.com/images/G/01/dmpexperience/emptyStateImage3x.png" : element.getImage();
        BadgeElement badge = element.getBadge();
        String tertiaryText = element.getTertiaryText();
        if (badge == null && StringUtils.isNotBlank(tertiaryText)) {
            EmberTextView emberTextView = this.tertiaryText;
            emberTextView.setTypeface(emberTextView.getTypeface(), 2);
            this.tertiaryText.setText(tertiaryText);
            this.badge.setVisibility(8);
            this.tertiaryText.setVisibility(0);
        } else {
            this.tertiaryText.setVisibility(8);
            if (badge instanceof UnborderedBadgeElement) {
                this.badge.setBackgroundDrawable(getResources().getDrawable(R$drawable.redhood_plus_unbordered_badge_background));
                this.badge.setTextColor(getResources().getColor(R$color.primary_glass_5));
            } else if (badge instanceof LightUnborderedBadgeElement) {
                this.badge.setBackgroundDrawable(getResources().getDrawable(R$drawable.redhood_plus_regular_badge_background));
                this.badge.setTextColor(getResources().getColor(R$color.secondary));
            } else if (badge instanceof AccentOutlineBadgeElement) {
                this.badge.setBackgroundDrawable(getResources().getDrawable(R$drawable.redhood_plus_accent_bordered_badge_background));
                this.badge.setTextColor(getResources().getColor(R$color.refresh_podcast_accent));
            }
            this.badge.bind(badge, this.methodsDispatcher, this.ownerId);
        }
        RequestCreator requestCreatorError = Picasso.get().load(image).centerCrop().placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId());
        int i = this.imageDimension;
        requestCreatorError.resize(i, i).transform(this.transformation).into(this.image);
        setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.horizontalRowItemsListView.HorizontalRowItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseViewContentModel baseViewContentModel;
                PodcastMetadata podcastMetadataForSearch = didNavigateFromSearchResults && (baseViewContentModel = model) != null && (baseViewContentModel.getMetadata() instanceof PodcastMetadata) ? HorizontalRowItemView.this.getPodcastMetadataForSearch((PodcastMetadata) model.getMetadata(), element) : null;
                if (didNavigateFromSearchResults && entityTrackingIntentServiceProvider != null && HorizontalRowItemView.this.context != null && podcastMetadataForSearch != null) {
                    entityTrackingIntentServiceProvider.processEntityVisit(HorizontalRowItemView.this.context, podcastMetadataForSearch, PageType.BROWSE_SEARCH);
                }
                HorizontalRowItemView.this.methodsDispatcher.dispatch(HorizontalRowItemView.this.ownerId, element.getOnItemSelected());
            }
        });
        bindFollow(element.getFollow());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PodcastMetadata getPodcastMetadataForSearch(final PodcastMetadata podcastMetadata, final HorizontalItemElement element) {
        String strPodcastForSearch;
        if (podcastMetadata == null || element == null) {
            return null;
        }
        String variantId = podcastMetadata.getVariantId();
        String catalogId = podcastMetadata.getCatalogId();
        String title = podcastMetadata.getTitle();
        String subTitle = podcastMetadata.getSubTitle();
        String podcastShowCatalogId = podcastMetadata.getPodcastShowCatalogId();
        String image = element.getImage() == null ? "https://m.media-amazon.com/images/G/01/dmpexperience/emptyStateImage3x.png" : element.getImage();
        if (!Deeplinks.getEpisodeIdFromDeeplink(podcastMetadata.getTarget()).isEmpty()) {
            strPodcastForSearch = Deeplinks.episodeForSearch(podcastShowCatalogId, catalogId, subTitle, title);
        } else {
            strPodcastForSearch = Deeplinks.podcastForSearch(catalogId, title);
        }
        return new PodcastMetadata(podcastMetadata.getViewId(), podcastMetadata.getUri(), podcastMetadata.getTags(), catalogId, strPodcastForSearch, podcastMetadata.getBackgroundTarget(), title, subTitle, image, podcastShowCatalogId, variantId);
    }

    private void init() {
        this.index = (EmberTextView) findViewById(R$id.podcast_row_item_index);
        this.primaryText = (EmberTextView) findViewById(R$id.title);
        this.secondaryText = (EmberTextView) findViewById(R$id.subtitle);
        this.tertiaryText = (EmberTextView) findViewById(R$id.podcast_row_item_tertiary_text);
        HorizontalRowItemBadge horizontalRowItemBadge = (HorizontalRowItemBadge) findViewById(R$id.podcast_horizontal_row_item_badge);
        this.badge = horizontalRowItemBadge;
        horizontalRowItemBadge.setTypeface(Typeface.createFromAsset(this.context.getAssets(), RuntimeStyleSheet.FONT_AMAZON_EMBER_BOLD_PATH));
        this.image = (ImageView) findViewById(R$id.image_view);
        int i = R$id.podcast_row_item_follow_button;
        this.followButton = (ImageView) findViewById(i);
        this.transformation = new RoundedCornersTransformation(this.resources.getDimensionPixelSize(R$dimen.podcast_horizontal_row_item_image_corner_radius));
        this.imageDimension = this.resources.getDimensionPixelSize(R$dimen.podcast_horizontal_row_item_image_dimension);
        this.followToastView = new ToastView(this.context, ToastView.ToastViewType.FOLLOW, "FOLLOW_PODCAST_NAVIGATION_TOAST_FLAG");
        this.followButtonContainer = (LinearLayout) findViewById(R$id.podcast_horizontal_row_item_follow_button_container);
        this.followButton = (ImageView) findViewById(i);
    }

    private void inflateLayout(final boolean isUsedOnMixedScreen) {
        if (isUsedOnMixedScreen) {
            View.inflate(this.context, R$layout.podcast_horizontal_row_item_mixed_screen, this);
        }
        View.inflate(this.context, R$layout.podcast_horizontal_row_item, this);
    }

    private void bindFollow(final FollowElement element) {
        if (element == null) {
            this.followButton.setVisibility(8);
            this.followButtonContainer.setVisibility(8);
            return;
        }
        this.followElement = element;
        Podcast.getAppSync().follow().readLive(this.context, this.followElement.getId()).observe(this.lifecycleOwner, new Observer<Follow>() { // from class: com.amazon.podcast.views.horizontalRowItemsListView.HorizontalRowItemView.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Follow follow) {
                HorizontalRowItemView.this.isFollowed = follow == null ? false : follow.isFollowed();
                if (HorizontalRowItemView.this.isFollowed) {
                    HorizontalRowItemView.this.followButton.setImageResource(R$drawable.ic_following);
                    HorizontalRowItemView.this.followButtonContainer.setContentDescription(HorizontalRowItemView.this.resources.getString(R$string.podcast_unFollow_label));
                } else {
                    HorizontalRowItemView.this.followButton.setImageResource(R$drawable.ic_follow);
                    HorizontalRowItemView.this.followButtonContainer.setContentDescription(HorizontalRowItemView.this.resources.getString(R$string.podcast_follow_label));
                }
            }
        });
        this.followButtonContainer.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.horizontalRowItemsListView.HorizontalRowItemView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HorizontalRowItemView.this.handleFollowClick();
            }
        });
        this.followButton.setVisibility(0);
        this.followButtonContainer.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFollowClick() {
        if (this.isFollowed) {
            this.methodsDispatcher.dispatch(this.ownerId, this.followElement.getOnUnFollow());
            return;
        }
        this.followToastView.dismissToast();
        this.followToastView.showToast();
        this.methodsDispatcher.dispatch(this.ownerId, this.followElement.getOnFollow());
    }
}
