package com.amazon.podcast.views.ptcPodcastsTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElement;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.fragment.app.FragmentManager;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.transformations.PTCPodcastRoundedBorderTransformation;
import com.amazon.podcast.transformations.PTCPodcastRoundedCornersTransformation;
import com.amazon.podcast.views.ptcPodcastsTemplate.PTCGridElementViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/* JADX INFO: loaded from: classes5.dex */
public final class PTCGridItemView extends FrameLayout {
    private ImageButton aboutButton;
    private PTCGridElementViewHolder.OnBottomSheetSelectedListener bottomSheetListener;
    private Context context;
    private int dimension;
    private ImageButton followImageButton;
    private FragmentManager fragmentManager;
    private PTCGridElementViewHolder.OnGridItemUpdateListener gridItemUpdateListener;
    private Boolean isSelected;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private ImageView podcastImage;
    private ImageView podcastImageBorder;
    private Resources resources;
    private View view;

    public PTCGridItemView(final Context context) {
        super(context);
        init();
    }

    private void init() {
        View viewInflate = View.inflate(getContext(), R$layout.ptc_grid_item, this);
        this.view = viewInflate;
        ImageView imageView = (ImageView) viewInflate.findViewById(R$id.ptc_single_podcast_image);
        this.podcastImage = imageView;
        imageView.setBackgroundResource(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId());
        this.aboutButton = (ImageButton) this.view.findViewById(R$id.ptc_podcast_about_button);
        this.followImageButton = (ImageButton) this.view.findViewById(R$id.ptc_podcast_follow_icon);
        this.podcastImageBorder = (ImageView) this.view.findViewById(R$id.ptc_podcast_image_border);
    }

    public void bind(final String ownerId, final Resources resources, final MethodsDispatcher methodsDispatcher, final FragmentManager fragmentManager, final PTCGridElementViewHolder.OnGridItemUpdateListener gridItemUpdateListener, final PTCGridElementViewHolder.OnBottomSheetSelectedListener bottomSheetSelectedListener, final int dimension, final GridItemElement element, final boolean isSelected) {
        this.ownerId = ownerId;
        this.resources = resources;
        this.methodsDispatcher = methodsDispatcher;
        this.fragmentManager = fragmentManager;
        this.gridItemUpdateListener = gridItemUpdateListener;
        this.bottomSheetListener = bottomSheetSelectedListener;
        this.dimension = dimension;
        this.context = this.view.getContext();
        this.view.getLayoutParams().height = dimension;
        this.view.getLayoutParams().width = dimension;
        loadImageWithTransform(element);
        this.podcastImage.setContentDescription(element.getAltText());
        bindOnClickImage(element);
        bindOnClickAbout(element);
        this.aboutButton.setContentDescription(resources.getString(R$string.podcast_about_view_tab_label));
        restoreState(element, isSelected);
    }

    private void loadImageBorder() {
        RequestCreator requestCreatorTransform = Picasso.get().load(R$drawable.podcast_empty_state_image).centerCrop().transform(new PTCPodcastRoundedBorderTransformation(this.resources.getDimensionPixelSize(R$dimen.podcast_spacer_mini), this.resources.getDimensionPixelSize(R$dimen.ptc_grid_item_radius_transformation), this.resources.getColor(R$color.white)));
        int i = this.dimension;
        requestCreatorTransform.resize(i, i).into(this.podcastImageBorder);
    }

    private void bindOnClickAbout(final GridItemElement gridItemElement) {
        if (gridItemElement == null) {
            return;
        }
        this.aboutButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCGridItemView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindOnClickAbout$0(gridItemElement, view);
            }
        });
        this.aboutButton.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindOnClickAbout$0(GridItemElement gridItemElement, View view) {
        this.methodsDispatcher.dispatch(this.ownerId, gridItemElement.getOnAboutSelected());
        bindBottomSheet(gridItemElement);
    }

    private void bindBottomSheet(final GridItemElement gridItemElement) {
        PTCBottomSheet.bindBottomSheet(gridItemElement, this.isSelected.booleanValue(), this.context, this.resources, this.aboutButton, this.ownerId, this.methodsDispatcher, this.fragmentManager, this, this.bottomSheetListener);
    }

    public void bindOnClickImage(final GridItemElement gridItemElement) {
        if (gridItemElement == null) {
            return;
        }
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCGridItemView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindOnClickImage$1(gridItemElement, view);
            }
        };
        this.podcastImage.setOnClickListener(onClickListener);
        this.followImageButton.setOnClickListener(onClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindOnClickImage$1(GridItemElement gridItemElement, View view) {
        if (!this.isSelected.booleanValue()) {
            handleSelected(gridItemElement);
            this.methodsDispatcher.dispatch(this.ownerId, gridItemElement.getOnItemSelected());
        } else {
            handleDeselected(gridItemElement);
            this.methodsDispatcher.dispatch(this.ownerId, gridItemElement.getOnItemDeselected());
        }
    }

    void handleSelected(final GridItemElement gridItemElement) {
        this.isSelected = Boolean.TRUE;
        loadImageBorder();
        this.podcastImage.setColorFilter(this.resources.getColor(R$color.secondary_glass_4));
        this.podcastImageBorder.setVisibility(0);
        this.podcastImage.setVisibility(0);
        this.followImageButton.setVisibility(0);
        this.gridItemUpdateListener.onGridItemStateUpdate(gridItemElement, this.isSelected.booleanValue());
    }

    void handleDeselected(final GridItemElement gridItemElement) {
        this.isSelected = Boolean.FALSE;
        this.podcastImage.setColorFilter((ColorFilter) null);
        this.podcastImageBorder.setVisibility(8);
        this.followImageButton.setVisibility(8);
        this.gridItemUpdateListener.onGridItemStateUpdate(gridItemElement, this.isSelected.booleanValue());
    }

    private void loadImageWithTransform(final GridItemElement gridItemElement) {
        PTCPodcastRoundedCornersTransformation pTCPodcastRoundedCornersTransformation = new PTCPodcastRoundedCornersTransformation(this.resources.getDimensionPixelSize(R$dimen.podcast_spacer_mini), this.resources.getDimensionPixelSize(R$dimen.ptc_grid_item_radius_transformation));
        Callback onImageLoadCallBack = getOnImageLoadCallBack(pTCPodcastRoundedCornersTransformation);
        RequestCreator requestCreatorTransform = Picasso.get().load(gridItemElement.getImage()).centerCrop().placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).transform(pTCPodcastRoundedCornersTransformation);
        int i = this.dimension;
        requestCreatorTransform.resize(i, i).into(this.podcastImage, onImageLoadCallBack);
        this.podcastImage.setVisibility(0);
    }

    private Callback getOnImageLoadCallBack(final PTCPodcastRoundedCornersTransformation transformation) {
        return new Callback() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCGridItemView.1
            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
            }

            @Override // com.squareup.picasso.Callback
            public void onError(Exception e) {
                Picasso.get().load(R$drawable.podcast_empty_state_image).centerCrop().placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).transform(transformation).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).resize(PTCGridItemView.this.dimension, PTCGridItemView.this.dimension).into(PTCGridItemView.this.podcastImage);
            }
        };
    }

    private void restoreState(final GridItemElement gridItemElement, final boolean isSelectedState) {
        if (this.isSelected != null) {
            return;
        }
        if (isSelectedState) {
            handleSelected(gridItemElement);
        } else {
            handleDeselected(gridItemElement);
        }
    }
}
