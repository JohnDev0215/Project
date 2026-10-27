package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.amazon.mp3.library.adapter.TileAdapter.TileViewHolder;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.view.ContentDisabledHelper;
import com.amazon.mp3.view.PlayNotificationIcon;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.uicontentview.ContentViewedListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TileAdapter<T, H extends TileViewHolder> extends ArrayAdapter<T> implements IArtCache.IArtLoadedListener {
    private String mContainerBlockRef;
    private ContentViewedListener mContentViewedListener;
    protected Set<View> mCreatedViews;
    protected View mEmptyView;
    private boolean mIsOfflineLibrary;
    protected final ItemButtonListener mOverflowButtonListener;
    private final View.OnClickListener mOverflowClickListener;
    protected boolean mShowEmptyLayout;

    public interface ItemButtonListener {
        void onClick(final View v, final int position);
    }

    public static class TileViewHolder {
        public PlayNotificationIcon mPlaystateIcon = null;
    }

    protected boolean isItemPlaying(H holder) {
        return false;
    }

    @Override // com.amazon.mp3.library.cache.image.IArtCache.IArtLoadedListener
    public void onDrawableLoaded(Drawable d, String source, long id) {
    }

    public void onDrawableLoaded(Drawable d, String source, String id) {
    }

    public TileAdapter(Context context, int resource, ItemButtonListener overflowButtonListener) {
        super(context, resource);
        this.mCreatedViews = Collections.newSetFromMap(new WeakHashMap());
        this.mOverflowClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.TileAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                TileAdapter.this.mOverflowButtonListener.onClick(v, ((Integer) v.getTag()).intValue());
            }
        };
        this.mOverflowButtonListener = overflowButtonListener;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        if (this.mShowEmptyLayout) {
            return 1;
        }
        return super.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int position) {
        if (this.mShowEmptyLayout) {
            return false;
        }
        return super.isEnabled(position);
    }

    protected void tagButtons(int position, ImageView overflowButton) {
        overflowButton.setOnClickListener(this.mOverflowClickListener);
        overflowButton.setTag(Integer.valueOf(position));
    }

    protected void updatePlaystateIcon(H viewHolder) {
        if (isCurrentContentPlaying() && isItemPlaying(viewHolder)) {
            viewHolder.mPlaystateIcon.setVisibility(0);
            viewHolder.mPlaystateIcon.setIsPlaying(PlayNotificationIcon.shouldAnimate());
        } else {
            viewHolder.mPlaystateIcon.setVisibility(8);
        }
    }

    private boolean isCurrentContentPlaying() {
        return MediaItemHelper.getCurrentUri() != null;
    }

    protected void notifyItemRendered(final SearchItem item, final int position) {
        Map<String, String> map;
        String str;
        if (this.mContentViewedListener == null) {
            return;
        }
        String str2 = this.mContainerBlockRef;
        if (str2 != null) {
            map = new HashMap<>();
            map.put("blockRef", this.mContainerBlockRef);
            map.put("numItemsAccessible", Integer.toString(getCount()));
        } else {
            MetricsUtil.MetricsItemType metricsItemType = SearchItemUtils.getMetricsItemType(item);
            Map<String, String> contentInfoForSearchLibraryContent = MetricsUtil.getContentInfoForSearchLibraryContent(null, getCount(), "SEARCH_CARD", 0, MetricsUtil.getContentName(metricsItemType, this.mIsOfflineLibrary));
            if (MetricsUtil.MetricsItemType.TRACK.equals(metricsItemType)) {
                str = "tracks";
            } else if (MetricsUtil.MetricsItemType.PLAYLIST.equals(metricsItemType)) {
                str = "playlists";
            } else if (MetricsUtil.MetricsItemType.ALBUM.equals(metricsItemType)) {
                str = "albums";
            } else if (MetricsUtil.MetricsItemType.ARTIST.equals(metricsItemType)) {
                str = "artists";
            } else {
                map = contentInfoForSearchLibraryContent;
                str2 = null;
            }
            str2 = str;
            map = contentInfoForSearchLibraryContent;
        }
        this.mContentViewedListener.onItemRenderedInSequence(str2, position, map);
    }

    protected void checkContentEnabled(View view, boolean enabled) {
        ContentDisabledHelper.setContentEnabled(view, enabled);
    }
}
