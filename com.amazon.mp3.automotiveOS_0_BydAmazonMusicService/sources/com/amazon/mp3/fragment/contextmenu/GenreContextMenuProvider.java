package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent;
import com.amazon.mp3.library.activity.AddToPlaylistPopupActivity;
import com.amazon.mp3.library.adapter.GenreListAdapter;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;

/* JADX INFO: loaded from: classes3.dex */
public class GenreContextMenuProvider extends ContextMenuProvider<GenreListAdapter.RowViewHolder> {
    private static final int CONFIRM_DELETE_GENRE_ID = UniqueCodeUtil.nextUniqueCode();

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.library_genre_context;
    }

    public GenreContextMenuProvider(BaseActivity activity) {
        super(activity);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        super.onContextMenuItemSelected(activity, item, menuInfo, sourceID);
        GenreListAdapter.RowViewHolder rowViewHolder = (GenreListAdapter.RowViewHolder) menuInfo.targetView.getTag();
        if (rowViewHolder == null) {
            return false;
        }
        Uri contentUri = MediaProvider.Genres.Tracks.getContentUri(sourceID, menuInfo.id);
        String string = rowViewHolder.mName.getText().toString();
        Genre genre = (Genre) rowViewHolder.mLibraryItem;
        setClickedEntityIdAndType(rowViewHolder.mAsin, EntityIdType.ASIN);
        int itemId = item.getItemId();
        if (itemId == R.id.MenuGenreContextDownload) {
            sendMetricEvent(ActionType.DOWNLOAD_OVERFLOW);
            if (UserSubscriptionUtil.isNightwingOnly() && !genre.isAllOwned()) {
                UpsellUtil.showBlockingUpsell(activity, rowViewHolder.mAsin, UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.GENRE);
                return true;
            }
            NotificationInfo notificationInfo = new NotificationInfo(string, null, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.GENRE.intValue(), rowViewHolder.mGenreId).toString());
            String str = rowViewHolder.mDownloadId;
            ContextMenuDownloadComponent.startDownload(activity, str, Uri.parse(str), notificationInfo);
            return true;
        }
        if (itemId == R.id.MenuGenreContextCancelDownload) {
            sendMetricEvent(ActionType.DOWNLOAD_CANCEL_ALL);
            ContextMenuDownloadComponent.cancelDownload(activity, rowViewHolder.mDownloadId);
            return true;
        }
        if (itemId == R.id.MenuGenreContextDelete) {
            sendMetricEvent(ActionType.REMOVE_FROM_DEVICE_OVERFLOW);
            ContextMenuDownloadComponent.deleteDeviceContent(activity, CONFIRM_DELETE_GENRE_ID, genre.getContentUri(), new ContextMenuDownloadComponent.ConfirmDeleteDialogHolder(genre, R.string.dmusic_genre_confirm_delete_title, activity.getString(R.string.dmusic_genre_confirm_delete_msg, string), 0, 0, 0, 0, R.string.genre_removed_from_device_content_description));
            return true;
        }
        if (itemId == R.id.MenuGenreContextDeleteFromCirrus) {
            sendMetricEvent(ActionType.REMOVE_FROM_CLOUD_OVERFLOW);
            ContextMenuDownloadComponent.deleteCloudContent(activity, CONFIRM_DELETE_GENRE_ID, genre.getContentUri(), new ContextMenuDownloadComponent.ConfirmDeleteDialogHolder(genre, R.string.dmusic_context_delete_cloud, activity.getResources().getQuantityString(R.plurals.dmusic_genre_confirm_delete_cloud_msg, (int) genre.getTrackCount(), Integer.valueOf((int) genre.getTrackCount())), R.string.dmusic_genre_toast_deleting_track_cloud, R.string.dmusic_button_yes, R.string.dmusic_button_no, R.string.genre_removed_content_description, R.string.genre_removed_from_device_content_description), false, null);
            return true;
        }
        if (itemId == R.id.MenuGenreAddToPlaylist) {
            sendMetricEvent(ActionType.ADD_ALL_TO_PLAYLIST_ATTEMPT_OVERFLOW);
            activity.startActivity(AddToPlaylistPopupActivity.getStartIntent(activity, contentUri, "album_id,disc_num,track_num", this.mPageType));
            return true;
        }
        if (itemId == R.id.MenuGenreAddToPlayQueue) {
            sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
            if (UserSubscriptionUtil.isNightwingOnly() && !genre.isAllOwned()) {
                UpsellUtil.showBlockingUpsell(activity, rowViewHolder.mAsin, UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.GENRE);
                return true;
            }
            NowPlayingQueueUtil.getInstance().addToPlayQueue(contentUri, false);
            return true;
        }
        if (itemId != R.id.MenuGenrePlayNext) {
            return false;
        }
        sendMetricEvent(ActionType.PLAY_NEXT_OVERFLOW);
        if (UserSubscriptionUtil.isNightwingOnly() && !genre.isAllOwned()) {
            UpsellUtil.showBlockingUpsell(activity, rowViewHolder.mAsin, UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.GENRE);
            return true;
        }
        NowPlayingQueueUtil.getInstance().addToPlayQueue(contentUri, true);
        return true;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        GenreListAdapter.RowViewHolder rowViewHolder = (GenreListAdapter.RowViewHolder) tag;
        Genre genre = (Genre) rowViewHolder.mLibraryItem;
        setStyledHeaderTitle(genre.getName(), menu);
        if (genre.isRemote()) {
            int i = rowViewHolder.mDownloadState;
            if (i == 0) {
                menu.removeItem(R.id.MenuGenreContextDownload);
                menu.removeItem(R.id.MenuGenreContextCancelDownload);
            } else if (i == 1 || i == 2 || i == 3 || i == 4) {
                menu.removeItem(R.id.MenuGenreContextDownload);
                menu.removeItem(R.id.MenuGenreContextDelete);
            } else {
                menu.removeItem(R.id.MenuGenreContextCancelDownload);
            }
            int i2 = rowViewHolder.mDownloadGroupState;
            if (i2 == 0 || i2 == 1 || i2 == 2) {
                menu.removeItem(R.id.MenuGenreContextDelete);
            }
        } else {
            menu.removeItem(R.id.MenuGenreContextDownload);
            menu.removeItem(R.id.MenuGenreContextCancelDownload);
            menu.removeItem(R.id.MenuGenreContextDeleteFromCirrus);
        }
        if (!SequencerHelper.canModifyQueue()) {
            menu.removeItem(R.id.MenuGenreAddToPlayQueue);
            menu.removeItem(R.id.MenuGenrePlayNext);
        }
        if (genre.isAllOwned()) {
            return;
        }
        StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuGenreContextDownload), this.mActivity);
        StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuGenreAddToPlayQueue), this.mActivity);
        StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuGenrePlayNext), this.mActivity);
    }
}
