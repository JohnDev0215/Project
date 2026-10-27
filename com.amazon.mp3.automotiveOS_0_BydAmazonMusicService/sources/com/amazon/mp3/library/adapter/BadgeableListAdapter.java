package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.DownloadProgressListener;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.adapter.AlbumListAdapter;
import com.amazon.mp3.library.adapter.ArtistListAdapter;
import com.amazon.mp3.library.adapter.GenreListAdapter;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.view.IBadgeableView;
import com.amazon.mp3.view.PlayNotificationIcon;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BadgeableListAdapter extends CursorListAdapter {
    private static final String TAG = BadgeableListAdapter.class.getSimpleName();
    protected IArtCache mArtCache;
    private boolean mCanUpdateProgress;
    private DownloadProgressListener mDownloadProgressListener;
    private final Map<String, Integer> mDownloadProgressMap;
    private final Handler mHandler;
    private int mLastFirstVisible;
    protected AnimationDrawable mNowPlayingDrawable;
    private final HashMap<Integer, Long> mPosCache;
    protected boolean mResetOnTrackFinished;
    private final Map<String, WeakReference<BaseRowViewHolder>> mRowProgressMap;
    protected IBadgeableView mSelectedGridCover;

    public static abstract class BaseRowViewHolder {
        public String mAsin;
        public IBadgeableView mBadgeableCoverView;
        public ImageView mDimAlbumArtOverlay;
        public String mDownloadId;
        public AbstractItem mLibraryItem;
        public String mLuid;
        public ImageView mPlaystateIcon;
        public int mPosition = -1;

        public abstract void updateProgress(int percent);
    }

    protected abstract int getImageIdColumn();

    protected abstract ImageLoaderFactory.ItemType getImageType();

    protected String getSource() {
        return null;
    }

    protected boolean isTrackBeingPlayed(BaseRowViewHolder holder) {
        return false;
    }

    public void addProgress(BaseRowViewHolder viewHolder) {
        Integer numValueOf = Integer.valueOf(getProgress(viewHolder));
        this.mRowProgressMap.put(viewHolder.mDownloadId, new WeakReference<>(viewHolder));
        viewHolder.updateProgress(numValueOf == null ? 0 : numValueOf.intValue());
        this.mDownloadProgressListener.addRequestId(viewHolder.mDownloadId);
    }

    public void removeProgress(BaseRowViewHolder viewHolder) {
        this.mDownloadProgressListener.removeRequestId(viewHolder.mDownloadId);
    }

    public int getProgress(BaseRowViewHolder viewHolder) {
        Integer num = this.mDownloadProgressMap.get(viewHolder.mDownloadId);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public void updateBadgeContainer(View badgeContainer, boolean enable) {
        if (badgeContainer != null) {
            badgeContainer.setClickable(enable);
        }
    }

    public void bindBadgeContainer(View badgeContainer, int downloadState) {
        if (downloadState == -1 || downloadState == 5) {
            updateBadgeContainer(badgeContainer, false);
        } else {
            updateBadgeContainer(badgeContainer, true);
        }
    }

    public BadgeableListAdapter(Context context, Cursor cursor, boolean autoRequery) {
        super(context, cursor, autoRequery);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mPosCache = new HashMap<>();
        this.mDownloadProgressMap = Collections.synchronizedMap(new HashMap());
        this.mRowProgressMap = new HashMap();
        this.mLastFirstVisible = Integer.MIN_VALUE;
        this.mDownloadProgressListener = new DownloadProgressListener(new HashSet()) { // from class: com.amazon.mp3.library.adapter.BadgeableListAdapter.1
            @Override // com.amazon.music.downloads.notification.DownloadListener
            public void onProgressUpdate(final String id, final Group group, final float percentageDownloaded) {
                onProgressUpdate(id, percentageDownloaded);
            }

            @Override // com.amazon.music.downloads.notification.DownloadListener
            public void onProgressUpdate(final String id, final Item item, final float percentageDownloaded) {
                onProgressUpdate(id, percentageDownloaded);
            }

            private void onProgressUpdate(final String id, final float percentageDownloaded) {
                final int i = (int) percentageDownloaded;
                BadgeableListAdapter.this.mDownloadProgressMap.put(id, Integer.valueOf(i));
                BadgeableListAdapter.this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.adapter.BadgeableListAdapter.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BaseRowViewHolder baseRowViewHolder;
                        WeakReference weakReference = (WeakReference) BadgeableListAdapter.this.mRowProgressMap.get(id);
                        if (weakReference != null && (baseRowViewHolder = (BaseRowViewHolder) weakReference.get()) != null && BadgeableListAdapter.this.mCanUpdateProgress && id.equals(baseRowViewHolder.mDownloadId)) {
                            baseRowViewHolder.updateProgress(i);
                        }
                    }
                });
            }
        };
    }

    public synchronized void onPauseOrDestroy(Context context) {
        this.mCanUpdateProgress = false;
        MusicDownloader.getInstance(context).unregisterDownloadListener(this.mDownloadProgressListener);
    }

    public synchronized void onResume(Context context) {
        this.mCanUpdateProgress = true;
        MusicDownloader.getInstance(context).registerDownloadListener(this.mDownloadProgressListener);
    }

    public void showGridItemSelected(View view) {
        IBadgeableView iBadgeableView;
        resetSelectedGridCover();
        BaseRowViewHolder baseRowViewHolder = (BaseRowViewHolder) view.getTag();
        if (baseRowViewHolder == null || (iBadgeableView = baseRowViewHolder.mBadgeableCoverView) == null) {
            return;
        }
        this.mSelectedGridCover = iBadgeableView;
        iBadgeableView.setOverlayBadgeVisible(true);
    }

    public void resetSelectedGridCover() {
        IBadgeableView iBadgeableView = this.mSelectedGridCover;
        if (iBadgeableView != null) {
            iBadgeableView.setOverlayBadgeVisible(false);
            this.mSelectedGridCover = null;
        }
    }

    private long getIdForPosition(int position, Cursor c, int index) {
        Long l = this.mPosCache.get(Integer.valueOf(position));
        if (l != null) {
            return l.longValue();
        }
        if (!c.moveToPosition(position)) {
            return 0L;
        }
        long j = c.getLong(index);
        this.mPosCache.put(Integer.valueOf(position), Long.valueOf(j));
        return j;
    }

    public void onScroll(int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int i;
        int imageIdColumn = getImageIdColumn();
        Cursor cursor = getCursor();
        if (imageIdColumn == -1 || this.mArtCache == null || cursor == null || cursor.isClosed() || firstVisibleItem == (i = this.mLastFirstVisible) || visibleItemCount <= 0) {
            return;
        }
        if (Math.abs(firstVisibleItem - i) <= 16) {
            boolean z = firstVisibleItem > this.mLastFirstVisible;
            int position = cursor.getPosition();
            ImageLoaderFactory.ItemType imageType = getImageType();
            String source = getSource();
            if (z) {
                for (int iMin = Math.min(visibleItemCount + firstVisibleItem + 8, totalItemCount); iMin > firstVisibleItem; iMin--) {
                    this.mArtCache.getDrawable(imageType, source, getIdForPosition(iMin, cursor, imageIdColumn));
                }
            } else {
                int i2 = visibleItemCount + firstVisibleItem;
                for (int iMax = Math.max(firstVisibleItem - 8, 0); iMax < i2; iMax++) {
                    this.mArtCache.getDrawable(imageType, source, getIdForPosition(iMax, cursor, imageIdColumn));
                }
            }
            cursor.moveToPosition(position);
        }
        this.mLastFirstVisible = firstVisibleItem;
    }

    protected void bindPlaystateIcon(final Context context, BaseRowViewHolder holder) {
        boolean z;
        if (isTrackBeingPlayed(holder)) {
            if (holder instanceof AlbumListAdapter.RowViewHolder) {
                z = ((AlbumListAdapter.RowViewHolder) holder).mUseGridView;
            } else if (holder instanceof ArtistListAdapter.RowViewHolder) {
                z = ((ArtistListAdapter.RowViewHolder) holder).mUseGridView;
            } else {
                z = holder instanceof GenreListAdapter.RowViewHolder ? ((GenreListAdapter.RowViewHolder) holder).mUseGridView : false;
            }
            if (PlayNotificationIcon.shouldAnimate()) {
                AnimationDrawable animationDrawable = (AnimationDrawable) context.getResources().getDrawable(z ? R.drawable.now_playing_animation_list_gridview : R.drawable.now_playing_animation_list);
                this.mNowPlayingDrawable = animationDrawable;
                holder.mPlaystateIcon.setImageDrawable(animationDrawable);
                this.mNowPlayingDrawable.start();
                ImageView imageView = holder.mDimAlbumArtOverlay;
                if (imageView != null) {
                    imageView.setVisibility(0);
                }
                holder.mPlaystateIcon.setVisibility(0);
                return;
            }
            AnimationDrawable animationDrawable2 = this.mNowPlayingDrawable;
            if (animationDrawable2 != null) {
                animationDrawable2.stop();
            }
            holder.mPlaystateIcon.setImageDrawable(context.getResources().getDrawable(z ? R.drawable.mp3_icon_songplaying_paused_grid : R.drawable.mp3_icon_songplaying_paused));
            ImageView imageView2 = holder.mDimAlbumArtOverlay;
            if (imageView2 != null) {
                imageView2.setVisibility(0);
            }
            holder.mPlaystateIcon.setVisibility(0);
            return;
        }
        ImageView imageView3 = holder.mDimAlbumArtOverlay;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
        }
        holder.mPlaystateIcon.setVisibility(8);
    }

    @Override // com.amazon.mp3.library.adapter.CursorListAdapter, android.widget.CursorAdapter
    public void changeCursor(Cursor cursor) {
        this.mPosCache.clear();
        super.changeCursor(cursor);
    }

    @Override // com.amazon.mp3.library.adapter.CursorListAdapter, android.widget.CursorAdapter
    public Cursor swapCursor(Cursor newCursor) {
        this.mPosCache.clear();
        return super.swapCursor(newCursor);
    }

    @Override // android.widget.CursorAdapter
    protected void onContentChanged() {
        this.mPosCache.clear();
        super.onContentChanged();
    }
}
