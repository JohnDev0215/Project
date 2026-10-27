package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElement;
import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.BookmarksElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.metrics.ui.UIContentViewCache.UIContentViewWidgetIndexCache;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.galleryTemplate.BookmarksVisualShovelerCompactAdapter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
final class BookmarksVisualShovelerCompactView extends LinearLayout implements ScrollableWidgetView {
    final UIContentViewWidgetIndexCache contentViewCache;
    private EmberTextView header;
    private boolean isFirstView;
    private final LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private List<Method> onContentFirstViewed;
    private List<Method> onViewed;
    private String ownerId;
    private RecyclerView recyclerView;
    private EmberTextView seeMoreButton;
    private int verticalItemDimensions;
    private View view;
    private Integer visibleVerticalItemCount;
    private String widgetId;

    public BookmarksVisualShovelerCompactView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner) {
        super(context);
        this.contentViewCache = UIContentViewWidgetIndexCache.INSTANCE;
        this.isFirstView = true;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_visual_shoveler_compact, this);
        Resources resources = getResources();
        this.visibleVerticalItemCount = Integer.valueOf(resources.getInteger(R$integer.podcast_vertical_item_count));
        this.verticalItemDimensions = getVerticalItemDimension(resources);
        this.view = findViewById(R$id.visual_shoveler_compact_view);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.visual_shoveler_compact_header);
        this.header = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.seeMoreButton = (EmberTextView) findViewById(R$id.visual_shoveler_see_all_button);
        this.recyclerView = (RecyclerView) findViewById(R$id.visual_shoveler_compact_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.recyclerView.setAdapter(new BookmarksVisualShovelerCompactAdapter(this.ownerId, this.methodsDispatcher, getResources(), this.verticalItemDimensions, this.lifecycleOwner, getContext()));
    }

    public void bind(final View itemsView, final LifecycleOwner lifecycleOwner, final BookmarksElement bookmarksElement) {
        LiveData<List<Bookmark>> all = Podcast.getAppSync().bookmark().readAll(getContext());
        if (all == null) {
            return;
        }
        this.widgetId = bookmarksElement.getId();
        this.onViewed = bookmarksElement.getOnViewed();
        this.onContentFirstViewed = bookmarksElement.getOnContentFirstViewed();
        final boolean z = !StringUtils.isBlank(this.widgetId);
        all.observe(lifecycleOwner, new Observer<List<Bookmark>>() { // from class: com.amazon.podcast.views.galleryTemplate.BookmarksVisualShovelerCompactView.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(List<Bookmark> bookmarks) {
                if (bookmarks == null || bookmarks.isEmpty()) {
                    BookmarksVisualShovelerCompactView.this.view.setVisibility(8);
                    return;
                }
                BookmarksVisualShovelerCompactView.this.view.setVisibility(0);
                BookmarksVisualShovelerCompactView.this.header.setText(bookmarksElement.getHeader());
                List<BookmarksVisualShovelerCompactAdapter.BookmarkVerticalItem> listBookmarkVerticalItems = BookmarksVisualShovelerCompactView.this.bookmarkVerticalItems(bookmarks);
                if (BookmarksVisualShovelerCompactView.this.visibleVerticalItemCount == null || listBookmarkVerticalItems.size() > BookmarksVisualShovelerCompactView.this.visibleVerticalItemCount.intValue() + 1) {
                    BookmarksVisualShovelerCompactView.this.seeMoreButton.setVisibility(0);
                    BookmarksVisualShovelerCompactView.this.seeMoreButton.setText(bookmarksElement.getSeeMoreText());
                    BookmarksVisualShovelerCompactView.this.seeMoreButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.BookmarksVisualShovelerCompactView.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            BookmarksVisualShovelerCompactView.this.methodsDispatcher.dispatch(BookmarksVisualShovelerCompactView.this.ownerId, bookmarksElement.getOnSeeMoreSelected());
                        }
                    });
                } else {
                    BookmarksVisualShovelerCompactView.this.seeMoreButton.setVisibility(4);
                }
                ((BookmarksVisualShovelerCompactAdapter) BookmarksVisualShovelerCompactView.this.recyclerView.getAdapter()).bind(listBookmarkVerticalItems);
                if (z) {
                    BookmarksVisualShovelerCompactView bookmarksVisualShovelerCompactView = BookmarksVisualShovelerCompactView.this;
                    bookmarksVisualShovelerCompactView.contentViewCache.addWidget(bookmarksVisualShovelerCompactView.widgetId);
                    BookmarksVisualShovelerCompactView bookmarksVisualShovelerCompactView2 = BookmarksVisualShovelerCompactView.this;
                    bookmarksVisualShovelerCompactView2.contentViewCache.updateNumItemsAccessible(bookmarksVisualShovelerCompactView2.widgetId, bookmarks.size());
                }
            }
        });
        if (z) {
            handleOnContentFirstViewed();
        }
    }

    public void handleOnViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onViewed);
    }

    private void handleOnContentFirstViewed() {
        if (this.isFirstView) {
            addOnScrollListener(this.recyclerView, this.widgetId, this.methodsDispatcher, this.ownerId, this.onContentFirstViewed);
            handleFirstOnContentFirstViewed(this.recyclerView, this.widgetId, this.methodsDispatcher, this.ownerId, this.onContentFirstViewed);
            this.isFirstView = false;
        }
    }

    private int getVerticalItemDimension(final Resources resources) {
        int i = resources.getDisplayMetrics().widthPixels;
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.podcast_spacer_giant) * 3;
        int iIntValue = this.visibleVerticalItemCount.intValue();
        int i2 = R$dimen.gutter;
        int dimensionPixelSize2 = iIntValue * resources.getDimensionPixelSize(i2);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R$dimen.margin);
        int dimensionPixelSize4 = resources.getDimensionPixelSize(i2);
        int dimensionPixelSize5 = resources.getDimensionPixelSize(R$dimen.podcast_min_vertical_item_width);
        int iIntValue2 = (i - (((dimensionPixelSize2 + dimensionPixelSize3) + dimensionPixelSize4) + dimensionPixelSize)) / this.visibleVerticalItemCount.intValue();
        return iIntValue2 < dimensionPixelSize5 ? dimensionPixelSize5 : iIntValue2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<BookmarksVisualShovelerCompactAdapter.BookmarkVerticalItem> bookmarkVerticalItems(final List<Bookmark> bookmarks) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Bookmark bookmark : bookmarks) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(DeeplinkMethods.episodePlay(bookmark, getResources()));
            arrayList2.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_HOME, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, bookmark.getId(), Integer.valueOf(i), UiMetricAttributes$ContentName.PODCASTS_BOOKMARKS_SHOVELER));
            arrayList.add(new BookmarksVisualShovelerCompactAdapter.BookmarkVerticalItem(VerticalItemElement.builder().withOnItemSelected(arrayList2).withImage(bookmark.getPodcastImage()).withBookmark(BookmarkElement.builder().withId(bookmark.getId()).build()).withPrimaryText(bookmark.getTitle()).build(), bookmark.getProgressMilliseconds(), bookmark.getTotalDurationMilliseconds()));
            i++;
        }
        return arrayList;
    }
}
