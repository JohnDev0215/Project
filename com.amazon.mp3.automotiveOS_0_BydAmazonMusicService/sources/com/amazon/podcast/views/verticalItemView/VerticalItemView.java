package com.amazon.podcast.views.verticalItemView;

import Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElement;
import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.completed.Completed;
import com.amazon.podcast.haptics.PodcastHapticsUtil;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import com.amazon.podcast.views.BadgeView;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.Strings;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemsMethods;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class VerticalItemView extends RelativeLayout {
    private static final String TAG;
    private static final Logger logger;
    private final int SPACER_COUNT;
    private BadgeView badgeView;
    private Bookmark bookmark;
    private BookmarkElement bookmarkElement;
    private Completed completed;
    private UiMetricAttributes$ContentName contentName;
    private Context context;
    private FragmentManager fragmentManager;
    private int imageDimension;
    private ImageView imageView;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private UiMetricAttributes$PageType pageType;
    private ImageButton playButton;
    private Playback playback;
    private ProgressBar progressBar;
    private Resources resources;
    private RoundedCornersTransformation transformation;
    private EmberTextView verticalItemPrimaryText;
    private EmberTextView verticalItemSecondaryText;
    private int visibleVerticalItemCount;

    static {
        String simpleName = VerticalItemView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public VerticalItemView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner) {
        super(context);
        this.SPACER_COUNT = 3;
        this.context = context;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        Resources resources = context.getResources();
        this.resources = resources;
        this.visibleVerticalItemCount = resources.getInteger(R$integer.podcast_vertical_item_count);
        init();
    }

    public VerticalItemView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final FragmentManager fragmentManager, final UiMetricAttributes$PageType pageType) {
        super(context);
        this.SPACER_COUNT = 3;
        this.context = context;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.fragmentManager = fragmentManager;
        this.resources = context.getResources();
        this.playback = Podcast.getPlayback();
        this.pageType = pageType;
        this.contentName = EpisodeRowItemsMethods.getContentName(pageType);
        this.visibleVerticalItemCount = this.resources.getInteger(R$integer.podcast_vertical_item_count);
        init();
    }

    public void bind(final VerticalItemElement element) {
        if (element.getImage().isEmpty()) {
            RequestCreator requestCreatorTransform = Picasso.get().load(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).transform(this.transformation);
            int i = this.imageDimension;
            requestCreatorTransform.resize(i, i).into(this.imageView);
        } else {
            RequestCreator requestCreatorTransform2 = Picasso.get().load(element.getImage()).placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).error(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).transform(this.transformation);
            int i2 = this.imageDimension;
            requestCreatorTransform2.resize(i2, i2).into(this.imageView);
        }
        bindBookmark(element);
        String primaryText = element.getPrimaryText();
        String secondaryText = element.getSecondaryText();
        if (primaryText != null && !primaryText.isEmpty()) {
            this.verticalItemPrimaryText.setText(element.getPrimaryText());
            this.verticalItemPrimaryText.setVisibility(0);
        } else {
            this.verticalItemPrimaryText.setVisibility(8);
        }
        if (secondaryText != null && !secondaryText.isEmpty()) {
            this.verticalItemSecondaryText.setText(element.getSecondaryText());
            this.verticalItemSecondaryText.setVisibility(0);
        } else {
            this.verticalItemSecondaryText.setVisibility(8);
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.verticalItemView.VerticalItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VerticalItemView.this.methodsDispatcher.dispatch(VerticalItemView.this.ownerId, element.getOnItemSelected());
            }
        });
        setContentDescription(element.getAltText());
        if (element.getBadge() == null) {
            this.badgeView.setVisibility(8);
            return;
        }
        this.badgeView.bind(element.getBadge(), this.methodsDispatcher, this.ownerId);
        this.badgeView.setVisibility(0);
        this.verticalItemPrimaryText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.podcast.views.verticalItemView.VerticalItemView.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                VerticalItemView.this.setMinimumHeight(Math.round(VerticalItemView.this.getPrimaryTextViewHeight() + VerticalItemView.this.getSecondaryTextViewHeight() + VerticalItemView.this.getBadgeViewHeight() + VerticalItemView.this.imageDimension));
                VerticalItemView.this.verticalItemPrimaryText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getPrimaryTextViewHeight() {
        if (this.verticalItemPrimaryText.getVisibility() == 0) {
            return this.verticalItemPrimaryText.getHeight() + this.resources.getDimension(R$dimen.podcast_spacer_small);
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getSecondaryTextViewHeight() {
        if (this.verticalItemSecondaryText.getVisibility() == 0) {
            return this.verticalItemSecondaryText.getHeight() + this.resources.getDimension(R$dimen.podcast_spacer_micro);
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getBadgeViewHeight() {
        if (this.badgeView.getVisibility() == 0) {
            return this.badgeView.getHeight() + this.resources.getDimension(R$dimen.podcast_spacer_micro) + this.resources.getDimension(R$dimen.podcast_spacer_nano);
        }
        return 0.0f;
    }

    private void init() {
        View.inflate(this.context, R$layout.podcast_vertical_item, this);
        this.ownerId = this.ownerId;
        this.transformation = new RoundedCornersTransformation(this.resources.getDimensionPixelSize(R$dimen.podcast_vertical_item_image_corner_radius));
        this.imageDimension = getVerticalItemDimension(this.resources);
        this.lifecycleOwner = this.lifecycleOwner;
        this.context = this.context;
        this.resources = this.resources;
        this.imageView = (ImageView) findViewById(R$id.vertical_item_image);
        this.progressBar = (ProgressBar) findViewById(R$id.vertical_item_horizontal_progress_bar);
        this.verticalItemPrimaryText = (EmberTextView) findViewById(R$id.vertical_item_primary_text);
        this.verticalItemSecondaryText = (EmberTextView) findViewById(R$id.vertical_item_secondary_text);
        this.badgeView = (BadgeView) findViewById(R$id.badge_view);
        this.playButton = (ImageButton) findViewById(R$id.vertical_item_play_button);
        this.imageView.getLayoutParams().height = this.imageDimension;
        this.imageView.getLayoutParams().width = this.imageDimension;
        this.progressBar.getLayoutParams().width = this.imageDimension;
        this.verticalItemPrimaryText.getLayoutParams().width = this.imageDimension;
        this.verticalItemSecondaryText.getLayoutParams().width = this.imageDimension;
    }

    private void bindBookmark(final VerticalItemElement verticalItemElement) {
        BookmarkElement bookmark = verticalItemElement.getBookmark();
        if (bookmark == null) {
            this.playButton.setVisibility(8);
            this.progressBar.setVisibility(8);
            return;
        }
        this.bookmarkElement = bookmark;
        bindPlayButton(verticalItemElement);
        LiveData<Completed> live = Podcast.getAppSync().completed().readLive(this.context, this.bookmarkElement.getId());
        LifecycleOwner lifecycleOwner = this.lifecycleOwner;
        if (lifecycleOwner == null) {
            return;
        }
        live.observe(lifecycleOwner, new Observer<Completed>() { // from class: com.amazon.podcast.views.verticalItemView.VerticalItemView.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Completed completed) {
                VerticalItemView.this.setCompleted(completed);
            }
        });
        Podcast.getAppSync().bookmark().readLive(this.context, this.bookmarkElement.getId()).observe(this.lifecycleOwner, new Observer<Bookmark>() { // from class: com.amazon.podcast.views.verticalItemView.VerticalItemView.4
            @Override // androidx.lifecycle.Observer
            public void onChanged(final Bookmark bookmark2) {
                if (bookmark2 == null) {
                    VerticalItemView.this.progressBar.setVisibility(8);
                } else {
                    VerticalItemView.this.bindProgress(bookmark2);
                    VerticalItemView.this.bindPlayButton(verticalItemElement);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCompleted(final Completed completed) {
        this.completed = completed;
        if (completed == null ? false : completed.getCompleted()) {
            this.progressBar.setProgress(Integer.MAX_VALUE);
            this.progressBar.setVisibility(0);
            Bookmark bookmark = this.bookmark;
            if (bookmark != null) {
                this.verticalItemSecondaryText.setText(Strings.getDurationString(this.resources, bookmark.getTotalDurationMilliseconds()));
                return;
            }
            return;
        }
        this.progressBar.setProgress(0);
        this.progressBar.setVisibility(4);
        Bookmark bookmark2 = this.bookmark;
        if (bookmark2 != null) {
            this.verticalItemSecondaryText.setText(Strings.getDurationString(this.resources, bookmark2.getTotalDurationMilliseconds()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindPlayButton(final VerticalItemElement verticalItemElement) {
        this.playButton.setVisibility(0);
        this.playButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.verticalItemView.VerticalItemView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PodcastHapticsUtil.triggerHaptics();
                VerticalItemView.this.methodsDispatcher.dispatch(VerticalItemView.this.ownerId, verticalItemElement.getOnItemSelected());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindProgress(final Bookmark bookmark) {
        this.bookmark = bookmark;
        long totalDurationMilliseconds = bookmark.getTotalDurationMilliseconds();
        long progressMilliseconds = bookmark.getProgressMilliseconds();
        long j = totalDurationMilliseconds - progressMilliseconds;
        Completed completed = this.completed;
        boolean completed2 = completed == null ? false : completed.getCompleted();
        if (progressMilliseconds > 0 && j > 0) {
            this.progressBar.setMax((int) totalDurationMilliseconds);
            this.progressBar.setProgress((int) progressMilliseconds);
            this.progressBar.setVisibility(0);
            this.verticalItemSecondaryText.setText(Strings.getRemainingDurationString(this.resources, j));
        } else if (completed2) {
            this.progressBar.setProgress(Integer.MAX_VALUE);
            this.progressBar.setVisibility(0);
            this.verticalItemSecondaryText.setText(Strings.getDurationString(this.resources, bookmark.getTotalDurationMilliseconds()));
        } else {
            this.progressBar.setProgress(0);
            this.progressBar.setVisibility(8);
            this.verticalItemSecondaryText.setText(Strings.getDurationString(this.resources, totalDurationMilliseconds));
        }
        this.verticalItemSecondaryText.setVisibility(0);
    }

    private int getVerticalItemDimension(final Resources resources) {
        int i = resources.getDisplayMetrics().widthPixels;
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.podcast_spacer_giant) * 3;
        int i2 = this.visibleVerticalItemCount;
        int i3 = R$dimen.gutter;
        int dimensionPixelSize2 = i2 * resources.getDimensionPixelSize(i3);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R$dimen.margin);
        int dimensionPixelSize4 = resources.getDimensionPixelSize(i3);
        int dimensionPixelSize5 = resources.getDimensionPixelSize(R$dimen.podcast_min_vertical_item_width);
        int i4 = (i - (((dimensionPixelSize2 + dimensionPixelSize3) + dimensionPixelSize4) + dimensionPixelSize)) / this.visibleVerticalItemCount;
        return i4 < dimensionPixelSize5 ? dimensionPixelSize5 : i4;
    }
}
