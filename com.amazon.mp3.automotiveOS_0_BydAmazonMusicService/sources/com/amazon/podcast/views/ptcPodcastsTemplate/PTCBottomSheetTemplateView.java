package com.amazon.podcast.views.ptcPodcastsTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.AboutElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.NegativeButtonElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PlayButtonElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PositiveButtonElement;
import SOACoreInterface.v1_0.Method;
import android.animation.LayoutTransition;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.OverlayViewFragment;
import com.amazon.podcast.views.Strings;
import com.amazon.podcast.views.ToastView;
import com.amazon.podcast.views.detailTemplate.FollowButton;
import com.amazon.podcast.views.ptcPodcastsTemplate.PTCGridElementViewHolder;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.gms.common.util.CollectionUtils;
import com.squareup.picasso.Picasso;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class PTCBottomSheetTemplateView extends LinearLayout {
    private PTCCompactPillItemAdapter adapter;
    private LinearLayout animationLayout;
    private LinearLayout bottomReplacement;
    private LinearLayout bottomSheetLayout;
    private PopupWindow bottomSheetPopUp;
    private PTCGridElementViewHolder.OnBottomSheetSelectedListener bottomSheetSelectedListener;
    private View bottomSpacer;
    private RecyclerView categoryPillsView;
    private EmberTextView description;
    private EmberTextView dismissButton;
    private FollowButton followButton;
    private GridItemElement gridItemElement;
    private boolean isPopUp;
    private boolean isSelected;
    private FlexboxLayoutManager layoutManager;
    private MethodsDispatcher methodsDispatcher;
    private List<Method> onViewed;
    private String ownerId;
    PTCBottomSheetPlayView playView;
    private ImageView podcastImage;
    private OverlayViewFragment ptcBottomSheetOverlay;
    private PTCGridItemView ptcGridItemView;
    private ToastView ptcPlaybackErrorToastView;
    private NestedScrollView scrollView;
    private EmberTextView subtitleText;
    private EmberTextView titleText;

    public PTCBottomSheetTemplateView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final boolean isSelected, final GridItemElement gridItemElement, final PTCGridItemView ptcGridItemView, final PTCGridElementViewHolder.OnBottomSheetSelectedListener bottomSheetSelectedListener, final OverlayViewFragment ptcBottomSheetOverlay, final PopupWindow bottomSheetPopUp) {
        super(context);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.isSelected = isSelected;
        this.gridItemElement = gridItemElement;
        this.ptcGridItemView = ptcGridItemView;
        this.bottomSheetSelectedListener = bottomSheetSelectedListener;
        this.ptcBottomSheetOverlay = ptcBottomSheetOverlay;
        this.bottomSheetPopUp = bottomSheetPopUp;
        setIsPopUp();
        init();
    }

    private void init() {
        Context context = getContext();
        View.inflate(context, R$layout.ptc_bottom_sheet_view, this);
        initAnimations();
        this.titleText = (EmberTextView) findViewById(R$id.podcast_ptc_bottom_sheet_title);
        this.subtitleText = (EmberTextView) findViewById(R$id.podcast_ptc_bottom_sheet_subtitle);
        this.podcastImage = (ImageView) findViewById(R$id.podcast_ptc_bottom_sheet_image);
        this.scrollView = (NestedScrollView) findViewById(R$id.ptc_bottom_sheet_scroll_view);
        this.description = (EmberTextView) findViewById(R$id.podcast_ptc_bottom_sheet_description);
        this.categoryPillsView = (RecyclerView) findViewById(R$id.podcast_ptc_bottom_sheet_category_view);
        this.followButton = (FollowButton) findViewById(R$id.ptc_bottom_sheet_follow_button);
        this.dismissButton = (EmberTextView) findViewById(R$id.ptc_bottom_sheet_dismiss);
        this.bottomSpacer = findViewById(R$id.ptc_pop_up_bottom_sheet_bottom_spacer);
        this.playView = (PTCBottomSheetPlayView) findViewById(R$id.ptc_bottom_sheet_play_view);
        this.ptcPlaybackErrorToastView = new ToastView(context, ToastView.ToastViewType.PTC_PLAYBACK_ERROR, null);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context) { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCBottomSheetTemplateView.1
            @Override // com.google.android.flexbox.FlexboxLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        this.layoutManager = flexboxLayoutManager;
        flexboxLayoutManager.setFlexDirection(0);
        this.layoutManager.setFlexWrap(1);
        this.layoutManager.setMaxLine(3);
        this.categoryPillsView.setLayoutManager(this.layoutManager);
        PTCCompactPillItemAdapter pTCCompactPillItemAdapter = new PTCCompactPillItemAdapter();
        this.adapter = pTCCompactPillItemAdapter;
        this.categoryPillsView.setAdapter(pTCCompactPillItemAdapter);
        this.bottomSheetSelectedListener.onAboutSelected(this.gridItemElement.getId(), this);
    }

    private void initAnimations() {
        this.bottomSheetLayout = (LinearLayout) findViewById(R$id.ptc_bottom_sheet_layout);
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.ptc_bottom_sheet_replacement);
        this.bottomReplacement = linearLayout;
        if (!this.isPopUp) {
            linearLayout.setVisibility(0);
        }
        this.animationLayout = (LinearLayout) findViewById(R$id.ptc_bottom_sheet_animation_layout);
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setAnimateParentHierarchy(false);
        this.bottomSheetLayout.setLayoutTransition(layoutTransition);
        this.animationLayout.setLayoutTransition(layoutTransition);
        this.bottomSheetLayout.setVisibility(0);
    }

    public void bind(final AboutElement aboutElement) {
        if (aboutElement == null) {
            return;
        }
        this.titleText.setText(aboutElement.getTitle());
        this.subtitleText.setText(aboutElement.getSubtitle());
        this.adapter.bind(aboutElement.getPills());
        bindDescription(aboutElement.getDescription());
        bindFollowButton(aboutElement.getPositiveButton());
        bindDismissButton(aboutElement.getNegativeButton());
        bindImage(aboutElement.getImage());
        bindPlayButton(aboutElement.getPlayButton());
        bindDismissToBottomSheetReplacementBlock();
        bindOnViewed(aboutElement);
        if (this.isPopUp) {
            this.bottomSheetLayout.setBackground(getResources().getDrawable(R$drawable.podcast_context_menu_pop_up_corner_radius));
        }
    }

    private void bindDismissToBottomSheetReplacementBlock() {
        this.bottomReplacement.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCBottomSheetTemplateView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PTCBottomSheetTemplateView.this.dismissBottomSheet();
            }
        });
    }

    private void bindPlayButton(final PlayButtonElement playButtonElement) {
        if (playButtonElement == null) {
            return;
        }
        this.playView.setAlpha(0.0f);
        this.playView.setVisibility(0);
        this.playView.animate().translationY(this.playView.getHeight()).alpha(1.0f).setListener(null);
        this.playView.bind(playButtonElement, this.ownerId, this.methodsDispatcher);
        hideAnimatedBottomSheetReplacement();
    }

    private void bindDescription(final String descriptionText) {
        if (StringUtils.isBlank(descriptionText)) {
            return;
        }
        this.description.setVisibility(0);
        EmberTextView emberTextView = this.description;
        emberTextView.setText(Strings.convertToSpannable(descriptionText, emberTextView));
    }

    private void bindFollowButton(final PositiveButtonElement positiveButtonElement) {
        if (positiveButtonElement == null) {
            return;
        }
        if (this.isSelected) {
            this.followButton.bindFollow(positiveButtonElement.getSelectedText());
        } else {
            this.followButton.bindUnFollow(positiveButtonElement.getDeselectedText());
        }
        this.followButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCBottomSheetTemplateView.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                if (PTCBottomSheetTemplateView.this.isSelected) {
                    PTCBottomSheetTemplateView.this.ptcGridItemView.handleDeselected(PTCBottomSheetTemplateView.this.gridItemElement);
                    PTCBottomSheetTemplateView.this.handleDeselected(positiveButtonElement);
                } else {
                    PTCBottomSheetTemplateView.this.ptcGridItemView.handleSelected(PTCBottomSheetTemplateView.this.gridItemElement);
                    PTCBottomSheetTemplateView.this.handleSelected(positiveButtonElement);
                }
                PTCBottomSheetTemplateView.this.dismissBottomSheet();
            }
        });
        this.followButton.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSelected(final PositiveButtonElement positiveButtonElement) {
        this.isSelected = true;
        this.followButton.bindFollow(positiveButtonElement.getSelectedText());
        this.methodsDispatcher.dispatch(this.ownerId, positiveButtonElement.getOnItemSelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeselected(final PositiveButtonElement positiveButtonElement) {
        this.isSelected = false;
        this.followButton.bindUnFollow(positiveButtonElement.getDeselectedText());
        this.methodsDispatcher.dispatch(this.ownerId, positiveButtonElement.getOnItemDeselected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissBottomSheet() {
        this.bottomSheetSelectedListener.onAboutSelected(null, null);
        OverlayViewFragment overlayViewFragment = this.ptcBottomSheetOverlay;
        if (overlayViewFragment != null) {
            overlayViewFragment.dismiss();
        }
        PopupWindow popupWindow = this.bottomSheetPopUp;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    private void bindDismissButton(final NegativeButtonElement negativeButtonElement) {
        if (negativeButtonElement == null || this.isPopUp) {
            return;
        }
        this.dismissButton.setText(negativeButtonElement.getText());
        this.dismissButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCBottomSheetTemplateView.4
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                PTCBottomSheetTemplateView.this.methodsDispatcher.dispatch(PTCBottomSheetTemplateView.this.ownerId, negativeButtonElement.getOnItemSelected());
                PTCBottomSheetTemplateView.this.dismissBottomSheet();
            }
        });
        this.dismissButton.setVisibility(0);
    }

    private void bindImage(final String imageUrl) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.ptc_bottom_sheet_image_size);
        Picasso.get().load(imageUrl).centerInside().placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).transform(new RoundedCornersTransformation(getResources().getDimensionPixelSize(R$dimen.podcast_featured_single_item_image_rounded_corner))).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).resize(dimensionPixelSize, dimensionPixelSize).into(this.podcastImage);
    }

    private void bindOnViewed(final AboutElement aboutElement) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            List<Method> onViewed = aboutElement.getOnViewed();
            this.onViewed = onViewed;
            this.methodsDispatcher.dispatch(this.ownerId, onViewed);
        }
    }

    public void showBottomSpacer() {
        this.bottomSpacer.setVisibility(0);
    }

    private void setIsPopUp() {
        if (this.bottomSheetPopUp == null) {
            this.isPopUp = false;
        } else {
            this.isPopUp = true;
        }
    }

    public void displayPlaybackErrorToast(final String errorMessage) {
        this.ptcPlaybackErrorToastView.dismissToast();
        this.ptcPlaybackErrorToastView.setToastText(errorMessage);
        this.ptcPlaybackErrorToastView.showToast();
    }

    final void setOverlayViewFragment(final OverlayViewFragment ptcBottomSheetOverlay) {
        this.ptcBottomSheetOverlay = ptcBottomSheetOverlay;
    }

    final boolean getIsTrailerPlayed() {
        PTCBottomSheetPlayView pTCBottomSheetPlayView = this.playView;
        if (pTCBottomSheetPlayView == null) {
            return false;
        }
        return pTCBottomSheetPlayView.getIsTrailerPlayed();
    }

    private final void hideAnimatedBottomSheetReplacement() {
        this.bottomReplacement.setVisibility(8);
    }
}
