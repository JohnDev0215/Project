package com.amazon.podcast.views.contextMenu;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.EmberTextView;
import com.squareup.picasso.Picasso;

/* JADX INFO: loaded from: classes5.dex */
public final class ContextMenuView extends RelativeLayout {
    private ContextMenuItemsAdapter adapter;
    private String artwork;
    private View bottomSpacer;
    private View containerTopStroke;
    private RelativeLayout contextMenuInnerContainer;
    private EmberTextView dismiss;
    private RecyclerView.ItemDecoration divider;
    private ImageView image;
    private boolean isHeaderShowing;
    private boolean isPopUp;
    private RelativeLayout layout;
    private LinearLayoutManager layoutManager;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private UiMetricAttributes$PageType pageType;
    private RecyclerView recyclerView;
    private Resources resources;
    private RoundedCornersTransformation roundedCornersTransformation;
    private EmberTextView subtitle;
    private EmberTextView title;
    private LinearLayout titleSubtitleContainer;

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        if (!this.isPopUp && visibility == 0) {
            resizeRecyclerView();
        }
    }

    public ContextMenuView(final Context context) {
        super(context);
        this.isPopUp = false;
        this.isHeaderShowing = false;
        init();
    }

    private void init() {
        Context context = getContext();
        View.inflate(getContext(), R$layout.podcast_context_menu_view, this);
        this.resources = getResources();
        this.roundedCornersTransformation = new RoundedCornersTransformation(getResources().getDimensionPixelSize(R$dimen.podcast_mini_player_artwork_corner_radius));
        this.layout = (RelativeLayout) findViewById(R$id.podcast_context_menu_layout);
        this.title = (EmberTextView) findViewById(R$id.podcast_context_menu_title);
        this.subtitle = (EmberTextView) findViewById(R$id.podcast_context_menu_sub_title);
        this.image = (ImageView) findViewById(R$id.podcast_context_menu_image);
        this.titleSubtitleContainer = (LinearLayout) findViewById(R$id.podcast_context_menu_text);
        this.contextMenuInnerContainer = (RelativeLayout) findViewById(R$id.podcast_context_menu_container);
        this.dismiss = (EmberTextView) findViewById(R$id.podcast_context_menu_dismiss);
        this.containerTopStroke = findViewById(R$id.podcast_overlay_context_menu_container_top_stroke);
        this.recyclerView = (RecyclerView) findViewById(R$id.podcast_context_menu_items_recycler_view);
        this.bottomSpacer = findViewById(R$id.podcast_pop_up_context_menu_bottom_spacer);
        this.layoutManager = new LinearLayoutManager(getContext(), 1, false);
        ContextMenuItemsAdapter contextMenuItemsAdapter = new ContextMenuItemsAdapter(this.resources, context);
        this.adapter = contextMenuItemsAdapter;
        this.recyclerView.setAdapter(contextMenuItemsAdapter);
        this.recyclerView.setLayoutManager(this.layoutManager);
        DividerItemDecorator dividerItemDecorator = new DividerItemDecorator(ContextCompat.getDrawable(context, R$drawable.visual_row_items_divider_view));
        this.divider = dividerItemDecorator;
        this.recyclerView.addItemDecoration(dividerItemDecorator);
    }

    public void bind(final EpisodeOptionsElement optionsElement, final UiMetricAttributes$PageType pageType) {
        if (optionsElement == null) {
            return;
        }
        this.pageType = pageType;
        this.artwork = optionsElement.getImage();
        String title = optionsElement.getTitle();
        String subTitle = optionsElement.getSubTitle();
        if (this.artwork != null && this.title != null && this.subtitle != null) {
            int dimensionPixelSize = this.resources.getDimensionPixelSize(R$dimen.podcast_horizontal_row_item_image_dimension);
            Picasso.get().load(this.artwork).centerCrop().placeholder(R$drawable.artwork_placeholder).resize(dimensionPixelSize, dimensionPixelSize).transform(this.roundedCornersTransformation).into(this.image);
            this.image.setVisibility(0);
            this.title.setText(title);
            this.subtitle.setText(subTitle);
            this.titleSubtitleContainer.setVisibility(0);
            this.isHeaderShowing = true;
        }
        if (this.isPopUp) {
            this.containerTopStroke.setVisibility(8);
            this.layout.setBackground(this.resources.getDrawable(R$drawable.podcast_context_menu_pop_up_corner_radius));
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.contextMenuInnerContainer.getLayoutParams();
            int bottomSpacerHeight = getBottomSpacerHeight();
            marginLayoutParams.setMarginStart(bottomSpacerHeight);
            marginLayoutParams.setMarginEnd(bottomSpacerHeight);
            this.contextMenuInnerContainer.setPadding(0, 0, 0, 0);
        }
        this.adapter.bind(optionsElement, this.methodsDispatcher, this.ownerId, this.lifecycleOwner, this.dismiss, this.isPopUp, pageType);
    }

    public void setMethodsDispatcherAndLifecycleOwner(final MethodsDispatcher methodsDispatcher, final String ownerId, final LifecycleOwner lifecycleOwner) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
    }

    public void bindDismissListeners(final View.OnClickListener clickListener, final View.OnTouchListener touchListener) {
        this.dismiss.setOnClickListener(clickListener);
        if (touchListener == null) {
            return;
        }
        this.dismiss.setOnTouchListener(touchListener);
    }

    public void hideDismissButton() {
        this.dismiss.setVisibility(8);
    }

    public void showBottomSpacer() {
        this.bottomSpacer.setVisibility(0);
    }

    public boolean isHeaderShowing() {
        return this.isHeaderShowing;
    }

    public int getHeaderHeight() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.image.getLayoutParams();
        return this.resources.getDimensionPixelSize(R$dimen.podcast_horizontal_row_item_image_dimension) + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public int getRecyclerViewHeight(final boolean forPopUp) {
        int dimensionPixelSize = this.resources.getDimensionPixelSize(R$dimen.podcast_large_list_item_height);
        if (forPopUp) {
            dimensionPixelSize = this.resources.getDimensionPixelSize(R$dimen.podcast_medium_list_item_height);
        }
        return this.adapter.getItemCount() * dimensionPixelSize;
    }

    public int getBottomSpacerHeight() {
        return this.resources.getDimensionPixelSize(R$dimen.podcast_spacer_medium);
    }

    public int getStatusBarHeight() {
        int identifier = this.resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return this.resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public void setIsPopUp(final boolean isPopUp) {
        this.isPopUp = isPopUp;
    }

    private void resizeRecyclerView() {
        DisplayMetrics displayMetrics = this.resources.getDisplayMetrics();
        int statusBarHeight = getStatusBarHeight();
        int dimensionPixelSize = this.resources.getDimensionPixelSize(R$dimen.podcast_divider_height);
        int recyclerViewHeight = getRecyclerViewHeight(false) + dimensionPixelSize;
        int dimensionPixelSize2 = this.titleSubtitleContainer.getVisibility() == 0 ? 0 + this.resources.getDimensionPixelSize(R$dimen.podcast_horizontal_row_item_image_dimension) + (this.resources.getDimensionPixelSize(R$dimen.podcast_spacer_medium) * 2) : 0;
        int dimensionPixelSize3 = this.resources.getDimensionPixelSize(R$dimen.podcast_playback_speed_item_height);
        int dimensionPixelSize4 = this.resources.getDimensionPixelSize(R$dimen.podcast_spacer_small);
        if (recyclerViewHeight + statusBarHeight + dimensionPixelSize2 + dimensionPixelSize3 + dimensionPixelSize4 >= displayMetrics.heightPixels) {
            this.recyclerView.getLayoutParams().height = ((((displayMetrics.heightPixels - dimensionPixelSize3) - dimensionPixelSize2) - dimensionPixelSize4) - dimensionPixelSize) - statusBarHeight;
        }
    }
}
