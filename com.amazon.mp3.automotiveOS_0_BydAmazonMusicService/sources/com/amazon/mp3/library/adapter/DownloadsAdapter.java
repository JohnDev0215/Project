package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.amazon.mp3.activity.DownloadDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.DownloadItem;
import com.amazon.mp3.download.DownloadProgressListener;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.helper.DownloadErrorConverter;
import com.amazon.mp3.download.redownload.RedownloadMediaTypeCheckUtil;
import com.amazon.mp3.library.adapter.BadgeableListAdapter;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.fragment.DownloadsFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.view.IBadgeableView;
import com.amazon.mp3.view.IDownloadProgressView;
import com.amazon.mp3.view.IPrimeMarkableView;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.ErrorReason;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.NotificationInfo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadsAdapter extends BaseAdapter {
    private static final String TAG = DownloadsAdapter.class.getSimpleName();
    protected IArtCache mArtCache;
    private CacheManager mCacheManager;
    private boolean mCanUpdateProgress;
    private final Context mContext;
    private final DownloadsFragment mFragment;
    private ArrayList<DownloadItem> mData = new ArrayList<>();
    Handler mHandler = new Handler(Looper.getMainLooper());
    private final WeakHashMap<View, Object> mCreatedViews = new WeakHashMap<>();
    private Map<String, Integer> mDownloadProgessMap = new HashMap();
    private Map<String, WeakReference<RowViewHolder>> mProgressMap = new HashMap();
    private final View.OnClickListener mPauseClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.DownloadsAdapter.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
            MusicDownloader musicDownloader = MusicDownloader.getInstance(DownloadsAdapter.this.mContext);
            int i = AnonymousClass3.$SwitchMap$com$amazon$music$downloads$DownloadState[rowViewHolder.mItem.getDownloadState().ordinal()];
            if (i == 1) {
                rowViewHolder.mPauseButton.setImageResource(R.drawable.download_btn_start);
                rowViewHolder.mPauseButton.setContentDescription(DownloadsAdapter.this.mContext.getString(R.string.resume_download_content_description));
                Log.info(DownloadsAdapter.TAG, "User pause from download page with item " + rowViewHolder.mItem.getRequestId() + " with state " + rowViewHolder.mItem.getDownloadState());
                musicDownloader.pause(rowViewHolder.mDownloadId);
                rowViewHolder.mActionProgressBar.setVisibility(0);
                rowViewHolder.mPauseButton.setVisibility(8);
                return;
            }
            if (i != 2 && i != 3 && i != 4) {
                if (i != 5) {
                    return;
                }
                MusicDownloader.showRetryDialog(DownloadsAdapter.this.mContext, rowViewHolder.mItem.getTracksUri(), rowViewHolder.mDownloadId);
                return;
            }
            Log.info(DownloadsAdapter.TAG, "User resume from download page with item " + rowViewHolder.mItem.getRequestId() + " with state " + rowViewHolder.mItem.getDownloadState());
            if (ConnectivityUtil.isWifiRequiredForDownloads(DownloadsAdapter.this.mContext) && !ConnectivityUtil.isWifiConnected(DownloadsAdapter.this.mContext)) {
                Intent startIntent = DownloadDialogActivity.getStartIntent(DownloadsAdapter.this.mContext, 1);
                startIntent.setFlags(268435456);
                DownloadsAdapter.this.mContext.startActivity(startIntent);
            } else {
                if (!ConnectivityUtil.isAvailable()) {
                    DownloadsAdapter.this.mFragment.displayNetworkErrorDlg();
                    return;
                }
                rowViewHolder.mPauseButton.setImageResource(R.drawable.download_btn_pause);
                rowViewHolder.mPauseButton.setContentDescription(DownloadsAdapter.this.mContext.getString(R.string.pause_download_content_description));
                musicDownloader.resume(rowViewHolder.mDownloadId);
                rowViewHolder.mActionProgressBar.setVisibility(0);
                rowViewHolder.mPauseButton.setVisibility(8);
            }
        }
    };
    private DownloadProgressListener mDownloadProgressListener = new DownloadProgressListener(new HashSet()) { // from class: com.amazon.mp3.library.adapter.DownloadsAdapter.2
        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String id, final Group group, final float percentageDownloaded) {
            onProgressUpdate(id, percentageDownloaded);
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String id, final Item item, final float percentageDownloaded) {
            onProgressUpdate(id, percentageDownloaded);
        }

        private synchronized void onProgressUpdate(final String id, final float percentageDownloaded) {
            final int i = (int) percentageDownloaded;
            DownloadsAdapter.this.mDownloadProgessMap.put(id, Integer.valueOf(i));
            DownloadsAdapter.this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.adapter.DownloadsAdapter.2.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (DownloadsAdapter.this) {
                        WeakReference weakReference = (WeakReference) DownloadsAdapter.this.mProgressMap.get(id);
                        if (weakReference == null) {
                            return;
                        }
                        RowViewHolder rowViewHolder = (RowViewHolder) weakReference.get();
                        if (rowViewHolder == null) {
                            return;
                        }
                        if (DownloadsAdapter.this.mCanUpdateProgress && id.equals(rowViewHolder.mDownloadId)) {
                            rowViewHolder.updateProgress(i);
                        }
                    }
                }
            });
        }
    };
    private String mSource = "cirrus";

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public void addProgress(RowViewHolder viewHolder) {
        Integer num = this.mDownloadProgessMap.get(viewHolder.mDownloadId);
        this.mProgressMap.put(viewHolder.mDownloadId, new WeakReference<>(viewHolder));
        viewHolder.updateProgress(num == null ? 0 : num.intValue());
        this.mDownloadProgressListener.addRequestId(viewHolder.mDownloadId);
    }

    public int getProgress(RowViewHolder viewHolder) {
        Integer num = this.mDownloadProgessMap.get(viewHolder.mDownloadId);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.adapter.DownloadsAdapter$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$DownloadState;

        static {
            int[] iArr = new int[DownloadState.values().length];
            $SwitchMap$com$amazon$music$downloads$DownloadState = iArr;
            try {
                iArr[DownloadState.DOWNLOADING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_USER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_PRIORITY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_POLICY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.QUEUED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.CANCELED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.DOWNLOADED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public void addItems(final List<DownloadItem> items) {
        Iterator<DownloadItem> it = items.iterator();
        while (it.hasNext()) {
            this.mData.add(it.next());
        }
    }

    public void onResume() {
        this.mCanUpdateProgress = true;
        MusicDownloader.getInstance(this.mContext).registerDownloadListener(this.mDownloadProgressListener);
    }

    public void onPause() {
        this.mCanUpdateProgress = false;
        MusicDownloader.getInstance(this.mContext).unregisterDownloadListener(this.mDownloadProgressListener);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mData.size();
    }

    @Override // android.widget.Adapter
    public DownloadItem getItem(int position) {
        if (position < 0 || position >= this.mData.size()) {
            return null;
        }
        return this.mData.get(position);
    }

    public void clear() {
        this.mData.clear();
        notifyDataSetChanged();
    }

    public void removeItem(String requestId) {
        for (DownloadItem downloadItem : this.mData) {
            if (downloadItem.getRequestId().equals(requestId)) {
                this.mData.remove(downloadItem);
                return;
            }
        }
    }

    public void updateItem(String requestId, DownloadState downloadState, ErrorReason errorReason) {
        for (DownloadItem downloadItem : this.mData) {
            if (downloadItem.getRequestId().equals(requestId)) {
                downloadItem.setDownloadState(downloadState);
                downloadItem.setErrorReason(errorReason);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public static final class RowViewHolder extends BadgeableListAdapter.BaseRowViewHolder {
        public ProgressBar mActionProgressBar;
        public TextView mCounts;
        public IDownloadProgressView mDownloadProgressView;
        public DownloadItem mItem;
        public TextView mName;
        public ImageButton mPauseButton;
        public IPrimeMarkableView mPrimeMarkableView;
        public TextView mQueued;

        @Override // com.amazon.mp3.library.adapter.BadgeableListAdapter.BaseRowViewHolder
        public void updateProgress(int percent) {
            this.mDownloadProgressView.setProgress(percent);
        }
    }

    public DownloadsAdapter(Context context, DownloadsFragment fragment) {
        this.mArtCache = null;
        this.mFragment = fragment;
        this.mContext = context;
        this.mArtCache = fragment;
    }

    private int getArtSize() {
        return this.mContext.getResources().getDimensionPixelSize(R.dimen.default_listview_art_size);
    }

    public void onDrawableLoaded(Uri imageCacheUri, Drawable d) {
        Iterator<View> it = this.mCreatedViews.keySet().iterator();
        while (it.hasNext()) {
            RowViewHolder rowViewHolder = (RowViewHolder) it.next().getTag();
            if (imageCacheUri.toString().equals(rowViewHolder.mItem.getNotificationInfo().getImageUri())) {
                rowViewHolder.mDownloadProgressView.setArtworkDrawable(d);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        RowViewHolder rowViewHolder;
        View view2;
        String str;
        String countText;
        if (view != null) {
            rowViewHolder = (RowViewHolder) view.getTag();
            view2 = view;
        } else {
            RowViewHolder rowViewHolder2 = new RowViewHolder();
            View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.library_downloads_row, viewGroup, false);
            rowViewHolder2.mDownloadProgressView = (IDownloadProgressView) viewInflate;
            rowViewHolder2.mPrimeMarkableView = (IPrimeMarkableView) viewInflate;
            rowViewHolder2.mName = (TextView) viewInflate.findViewById(R.id.DownloadName);
            rowViewHolder2.mCounts = (TextView) viewInflate.findViewById(R.id.Counts);
            rowViewHolder2.mQueued = (TextView) viewInflate.findViewById(R.id.Queued);
            ImageButton imageButton = (ImageButton) viewInflate.findViewById(R.id.PauseButton);
            rowViewHolder2.mPauseButton = imageButton;
            imageButton.setContentDescription(this.mContext.getString(R.string.pause_download_content_description));
            rowViewHolder2.mActionProgressBar = (ProgressBar) viewInflate.findViewById(R.id.ActionProgress);
            KeyEvent.Callback callbackFindViewById = viewInflate.findViewById(R.id.cover);
            if (callbackFindViewById instanceof IBadgeableView) {
                rowViewHolder2.mBadgeableCoverView = (IBadgeableView) callbackFindViewById;
            }
            viewInflate.setTag(rowViewHolder2);
            View clickableBadgeView = rowViewHolder2.mDownloadProgressView.getClickableBadgeView();
            if (clickableBadgeView != null) {
                clickableBadgeView.setTag(rowViewHolder2);
                rowViewHolder2.mPauseButton.setOnClickListener(this.mPauseClickListener);
                rowViewHolder2.mPauseButton.setTag(rowViewHolder2);
            }
            this.mCreatedViews.put(viewInflate, null);
            rowViewHolder = rowViewHolder2;
            view2 = viewInflate;
        }
        DownloadItem item = getItem(i);
        rowViewHolder.mItem = item;
        rowViewHolder.mDownloadId = item.getRequestId();
        Uri uri = Uri.parse(item.getRequestId());
        NotificationInfo notificationInfo = item.getNotificationInfo();
        if (notificationInfo.getImageUri() != null) {
            Uri uri2 = Uri.parse(notificationInfo.getImageUri());
            if (ImageCacheUri.isImageCacheUri(uri2)) {
                rowViewHolder.mDownloadProgressView.setArtworkDrawable(this.mArtCache.getDrawable(ImageLoaderFactory.ItemType.getItemType(ImageCacheUri.getImageItemType(uri2)), this.mSource, ImageCacheUri.getId(uri2)));
            }
        }
        if (MediaProvider.Albums.Tracks.isAlbumTracks(uri)) {
            rowViewHolder.mPrimeMarkableView.markAsPrime(!item.isAnyOwned());
            str = this.mContext.getString(R.string.dmusic_download_album) + ": " + notificationInfo.getTitle();
            countText = getCountText(item.getSize(), item.getRequestId());
        } else if (MediaProvider.Artists.Tracks.isArtistTracks(uri)) {
            rowViewHolder.mPrimeMarkableView.markAsPrime(false);
            str = this.mContext.getString(R.string.dmusic_download_artist) + ": " + notificationInfo.getTitle();
            countText = getCountText(item.getSize(), item.getRequestId());
        } else if (MediaProvider.PrimePlaylists.isPrimePlaylist(uri)) {
            rowViewHolder.mPrimeMarkableView.markAsPrime(true);
            str = this.mContext.getString(R.string.dmusic_download_playlist) + ": " + notificationInfo.getTitle();
            countText = getCountText(item.getSize(), item.getRequestId());
        } else if (MediaProvider.Playlists.isPlaylist(uri) || RedownloadMediaTypeCheckUtil.isPlaylist(notificationInfo.getTitle(), this.mContext)) {
            rowViewHolder.mPrimeMarkableView.markAsPrime(false);
            if (this.mCacheManager == null) {
                this.mCacheManager = CacheManager.getInstance();
            }
            if (notificationInfo.getImageUri() != null) {
                Uri uri3 = Uri.parse(notificationInfo.getImageUri());
                if (ImageCacheUri.isImageCacheUri(uri3)) {
                    rowViewHolder.mDownloadProgressView.setArtworkDrawable(this.mCacheManager.get(ImageLoaderFactory.ItemType.getItemType(ImageCacheUri.getImageItemType(uri3)), this.mSource, getArtSize(), ImageCacheUri.getId(uri3)));
                }
            }
            str = this.mContext.getString(R.string.dmusic_download_playlist) + ": " + notificationInfo.getTitle();
            countText = getCountText(item.getSize(), item.getRequestId());
        } else if (MediaProvider.Genres.isGenre(uri)) {
            rowViewHolder.mPrimeMarkableView.markAsPrime(false);
            str = this.mContext.getString(R.string.dmusic_download_genre) + ": " + notificationInfo.getTitle();
            countText = getCountText(item.getSize(), item.getRequestId());
        } else {
            rowViewHolder.mPrimeMarkableView.markAsPrime(!item.isAnyOwned());
            str = this.mContext.getString(R.string.dmusic_download_song) + ": " + notificationInfo.getTitle();
            countText = getCountText(item.getSize(), item.getRequestId());
        }
        rowViewHolder.mName.setText(str);
        addProgress(rowViewHolder);
        rowViewHolder.mPauseButton.setVisibility(4);
        rowViewHolder.mDownloadProgressView.getClickableBadgeView().setVisibility(8);
        rowViewHolder.mActionProgressBar.setVisibility(8);
        String message = "";
        switch (AnonymousClass3.$SwitchMap$com$amazon$music$downloads$DownloadState[rowViewHolder.mItem.getDownloadState().ordinal()]) {
            case 1:
            case 3:
                rowViewHolder.mQueued.setVisibility(8);
                rowViewHolder.mDownloadProgressView.setProgress(getProgress(rowViewHolder));
                rowViewHolder.mPauseButton.setImageResource(R.drawable.download_btn_pause);
                rowViewHolder.mPauseButton.setVisibility(0);
                rowViewHolder.mPauseButton.setContentDescription(this.mContext.getString(R.string.pause_download_content_description));
                rowViewHolder.mDownloadProgressView.getClickableBadgeView().setVisibility(0);
                break;
            case 2:
            case 4:
                rowViewHolder.mQueued.setVisibility(0);
                message = this.mContext.getString(R.string.dmusic_download_state_downloading_paused) + "";
                rowViewHolder.mPauseButton.setImageResource(R.drawable.download_btn_start);
                rowViewHolder.mPauseButton.setVisibility(0);
                rowViewHolder.mPauseButton.setContentDescription(this.mContext.getString(R.string.resume_download_content_description));
                break;
            case 5:
                rowViewHolder.mQueued.setVisibility(0);
                message = DownloadErrorConverter.toMessage(this.mContext, rowViewHolder.mItem.getErrorReason());
                this.mDownloadProgessMap.put(rowViewHolder.mDownloadId, 0);
                rowViewHolder.mPauseButton.setImageResource(R.drawable.download_btn_start);
                rowViewHolder.mPauseButton.setVisibility(0);
                rowViewHolder.mPauseButton.setContentDescription(this.mContext.getString(R.string.resume_download_content_description));
                break;
            case 6:
                rowViewHolder.mQueued.setVisibility(0);
                rowViewHolder.mDownloadProgressView.setProgress(0);
                message = this.mContext.getString(R.string.dmusic_download_state_waiting) + "";
                break;
        }
        rowViewHolder.mDownloadProgressView.setDownloadState(toAppDownloadState(item.getDownloadState()));
        rowViewHolder.mCounts.setText(countText);
        rowViewHolder.mQueued.setText(message);
        view2.setTag(rowViewHolder);
        return view2;
    }

    private String getCountText(int size, String requestId) {
        int downloadedItemCount;
        Group group = MusicDownloader.getInstance(this.mContext).getGroup(requestId);
        if (group != null && (downloadedItemCount = group.getDownloadedItemCount()) > 0) {
            return this.mContext.getResources().getString(R.string.dmusic_download_tracks_progress, Integer.valueOf(downloadedItemCount), Integer.valueOf(size));
        }
        return this.mContext.getResources().getQuantityString(R.plurals.dmusic_download_str_to_download, size, Integer.valueOf(size));
    }

    private int toAppDownloadState(DownloadState state) {
        switch (AnonymousClass3.$SwitchMap$com$amazon$music$downloads$DownloadState[state.ordinal()]) {
            case 1:
                return 4;
            case 2:
            case 3:
            case 4:
                return 3;
            case 5:
                return 2;
            case 6:
                return 1;
            case 7:
            default:
                return 5;
            case 8:
                return 0;
        }
    }
}
