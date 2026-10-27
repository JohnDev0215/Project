package com.amazon.podcast.views.podcastDetailTemplate;

import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AboutElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ButtonElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FollowElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.HeaderElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShareElement;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.podcast.AutoSizeTextUtil;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.follow.Follow;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.sharing.SharingManager;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.LargeUpsellButton;
import com.amazon.podcast.views.ToastView;
import com.amazon.podcast.views.detailTemplate.FollowButton;
import com.amazonaws.util.StringUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import org.apache.commons.lang3.text.WordUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class HeaderViewV0 extends LinearLayout {
    private ImageButton aboutButton;
    private FollowButton followButton;
    private FollowElement followElement;
    private ToastView followToastView;
    private ImageView image;
    private int imageDimension;
    private boolean isFollowed;
    private EmberTextView label;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private Playback playback;
    private EmberTextView primaryText;
    private EmberTextView secondaryText;
    private ImageButton shareButton;
    private RoundedCornersTransformation transformation;
    private LargeUpsellButton upsellButton;
    private EmberTextView upsellText;

    public HeaderViewV0(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        bindFollow(this.followElement);
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_detail_template_header_v0, this);
        this.followToastView = new ToastView(getContext(), ToastView.ToastViewType.FOLLOW, "FOLLOW_PODCAST_NAVIGATION_TOAST_FLAG");
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_detail_template_header_label);
        this.label = emberTextView;
        emberTextView.setTextColor(Podcast.getRuntimeStyleSheet().getAccentColor());
        EmberTextView emberTextView2 = (EmberTextView) findViewById(R$id.podcast_detail_template_header_primary_text);
        this.primaryText = emberTextView2;
        emberTextView2.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline3TypeFace());
        this.secondaryText = (EmberTextView) findViewById(R$id.podcast_detail_template_header_secondary_text);
        this.image = (ImageView) findViewById(R$id.podcast_detail_template_header_image);
        this.upsellText = (EmberTextView) findViewById(R$id.podcast_detail_template_header_upsell_text);
        this.upsellButton = (LargeUpsellButton) findViewById(R$id.podcast_detail_template_header_upsell_button);
        this.aboutButton = (ImageButton) findViewById(R$id.podcast_more_information_button);
        this.followButton = (FollowButton) findViewById(R$id.podcast_detail_template_header_follow_button);
        this.shareButton = (ImageButton) findViewById(R$id.podcast_share_button);
        this.playback = Podcast.getPlayback();
    }

    public void bindAboutButton(final AboutElement aboutElement) {
        if (aboutElement == null) {
            return;
        }
        this.aboutButton.setContentDescription(getResources().getString(R$string.podcast_about_view_tab_label));
        this.aboutButton.setVisibility(0);
        this.aboutButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.podcastDetailTemplate.HeaderViewV0.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                HeaderViewV0.this.methodsDispatcher.dispatch(HeaderViewV0.this.ownerId, aboutElement.getOnItemSelected());
            }
        });
    }

    public void bindShare(final ShareElement shareElement) {
        boolean zIsEnabled = PodcastFeatureGating.SHARING.isEnabled();
        if (shareElement == null || !zIsEnabled) {
            return;
        }
        this.shareButton.setContentDescription(getResources().getString(R$string.podcast_share_show_prompt));
        this.shareButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.podcastDetailTemplate.HeaderViewV0.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharingManager.handlePodcastDetailTemplateV2ShareClick(HeaderViewV0.this.getContext(), shareElement);
                HeaderViewV0.this.methodsDispatcher.dispatch(HeaderViewV0.this.ownerId, shareElement.getOnShare());
            }
        });
        this.shareButton.setVisibility(0);
    }

    public boolean isHeaderImageViewOnScreen() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        Rect rect = new Rect();
        Rect rect2 = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.image.getGlobalVisibleRect(rect);
        return rect.intersect(rect2);
    }

    public void setOwnerId(final String ownerId) {
        this.ownerId = ownerId;
    }

    public void setMethodsDispatcher(final MethodsDispatcher methodsDispatcher) {
        this.methodsDispatcher = methodsDispatcher;
    }

    public void setLifeCyclceOwner(final LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    public void bind(final HeaderElement headerElement) {
        if (headerElement == null) {
            return;
        }
        if (!StringUtils.isBlank(headerElement.getLabel())) {
            this.label.setText(headerElement.getLabel());
        }
        this.primaryText.setText(WordUtils.capitalize(headerElement.getPrimaryText()));
        AutoSizeTextUtil.continuousAutoSizeTextType(getResources().getDimensionPixelSize(R$dimen.podcast_text_size_h4), getResources().getDimensionPixelSize(R$dimen.podcast_text_size_h3), getResources().getDimensionPixelSize(R$dimen.refresh_line_height_h4), getResources().getDimensionPixelSize(R$dimen.refresh_line_height_h3), this.primaryText);
        this.secondaryText.setText(headerElement.getSecondaryText());
        bindRectangleImage(this.image, headerElement.getImage());
    }

    public void bindUpsellText(final String text) {
        if (StringUtils.isBlank(text)) {
            return;
        }
        this.upsellText.setText(text);
        this.upsellText.setVisibility(0);
    }

    public void bindUpsellButton(final ButtonElement buttonElement) {
        if (buttonElement == null) {
            return;
        }
        this.upsellButton.bindUpsellButtonText(buttonElement.getText());
        this.upsellButton.setUpsellButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.podcastDetailTemplate.HeaderViewV0.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HeaderViewV0.this.methodsDispatcher.dispatch(HeaderViewV0.this.ownerId, buttonElement.getOnItemSelected());
            }
        });
        this.upsellButton.setVisibility(0);
    }

    public void bindFollow(final FollowElement element) {
        if (element == null) {
            return;
        }
        this.followElement = element;
        Podcast.getAppSync().follow().readLive(getContext(), this.followElement.getId()).observe(this.lifecycleOwner, new Observer<Follow>() { // from class: com.amazon.podcast.views.podcastDetailTemplate.HeaderViewV0.4
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Follow follow) {
                HeaderViewV0.this.isFollowed = follow == null ? false : follow.isFollowed();
                if (HeaderViewV0.this.isFollowed) {
                    HeaderViewV0.this.followButton.bindFollow(HeaderViewV0.this.followElement.getDescriptions().get("FOLLOW"));
                    HeaderViewV0.this.followButton.setContentDescription(HeaderViewV0.this.getResources().getString(R$string.podcast_unFollow_label));
                } else {
                    HeaderViewV0.this.followButton.bindUnFollow(HeaderViewV0.this.followElement.getDescriptions().get("UNFOLLOW"));
                    HeaderViewV0.this.followButton.setContentDescription(HeaderViewV0.this.getResources().getString(R$string.podcast_follow_label));
                }
            }
        });
        this.followButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.podcastDetailTemplate.HeaderViewV0.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HeaderViewV0.this.handleFollowClick();
            }
        });
        this.followButton.setVisibility(0);
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

    private void bindRectangleImage(final ImageView imageView, final String url) {
        this.imageDimension = getResources().getDimensionPixelSize(R$dimen.podcast_detail_template_header_image_dimension);
        this.transformation = new RoundedCornersTransformation(r0.getDimensionPixelSize(R$dimen.podcast_detail_template_header_image_rounded_corner));
        RequestCreator requestCreatorError = Picasso.get().load(url).centerCrop().placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).transform(this.transformation).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId());
        int i = this.imageDimension;
        requestCreatorError.resize(i, i).into(imageView);
    }
}
