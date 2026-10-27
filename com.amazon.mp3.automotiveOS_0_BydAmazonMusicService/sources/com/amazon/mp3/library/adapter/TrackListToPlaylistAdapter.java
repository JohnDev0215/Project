package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class TrackListToPlaylistAdapter extends TrackListAdapter {
    private static final String TAG = TrackListToPlaylistAdapter.class.getSimpleName();
    private Hashtable<String, PlaylistUtil.PlaylistTrack> mCachePlaylistTracks;
    private View.OnClickListener mInternalAddClickListener;
    private OnAddButtonClickListener mOnAddButtonClickListener;
    private OnDeleteButtonClickListener mOnDeleteButtonClickListener;
    private Set<String> mPlaylistTrackLuids;

    public interface OnAddButtonClickListener {
        void onClick(Uri trackUri);
    }

    public interface OnDeleteButtonClickListener {
        void onClick(long playlistTrackId, long udo);
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int position) {
        return false;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter
    protected boolean shouldShowDownloadBadging() {
        return false;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter
    protected boolean shouldShowTrackDownloadProgress() {
        return false;
    }

    private static class AddButtonHolder {
        String mAsin;
        String mLuid;
        Uri mTrackUri;

        private AddButtonHolder() {
        }
    }

    public TrackListToPlaylistAdapter(Context context, Uri contentUri, IArtCache artCache, OnAddButtonClickListener onAddButtonClickListener, OnDeleteButtonClickListener onDeleteButtonClickListener, boolean shouldShowPrimeBadging) {
        super(context, contentUri, artCache, ImageLoaderFactory.ItemType.ALBUM, shouldShowPrimeBadging);
        this.mCachePlaylistTracks = new Hashtable<>();
        this.mPlaylistTrackLuids = new HashSet();
        this.mInternalAddClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.TrackListToPlaylistAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                View viewFindViewById = v.findViewById(R.id.AddIcon);
                AddButtonHolder addButtonHolder = (AddButtonHolder) viewFindViewById.getTag();
                if (viewFindViewById instanceof ImageView) {
                    ImageView imageView = (ImageView) viewFindViewById;
                    if (StringUtils.isBlank(addButtonHolder.mAsin)) {
                        imageView.setImageResource(R.drawable.playlist_btn_clear);
                        Log.debug(TrackListToPlaylistAdapter.TAG, "no longer support adding sideloaded songs to any playlists");
                        return;
                    }
                    if (addButtonHolder.mTrackUri != null) {
                        if (addButtonHolder.mLuid == null && !PrimePlaylistUtil.isAsin(addButtonHolder.mAsin)) {
                            BauhausToastUtils.showDefaultToast(TrackListToPlaylistAdapter.this.mContext, R.string.dmusic_playlist_edit_error_adding_song_toast_msg, 0);
                            return;
                        }
                        PlaylistUtil.PlaylistTrack playlistTrack = null;
                        String strFindObjectIdByTrackLocalLuid = StringUtils.isNotBlank(addButtonHolder.mLuid) ? TrackUtil.findObjectIdByTrackLocalLuid(TrackListToPlaylistAdapter.this.mContext, addButtonHolder.mLuid) : null;
                        if (TrackListToPlaylistAdapter.this.mCachePlaylistTracks != null && ((addButtonHolder.mLuid != null && TrackListToPlaylistAdapter.this.mCachePlaylistTracks.containsKey(addButtonHolder.mLuid)) || ((addButtonHolder.mAsin != null && TrackListToPlaylistAdapter.this.mCachePlaylistTracks.containsKey(addButtonHolder.mAsin)) || (strFindObjectIdByTrackLocalLuid != null && TrackListToPlaylistAdapter.this.mCachePlaylistTracks.containsKey(strFindObjectIdByTrackLocalLuid))))) {
                            if (TrackListToPlaylistAdapter.this.mPlaylistTrackLuids.contains(addButtonHolder.mLuid) || TrackListToPlaylistAdapter.this.mPlaylistTrackLuids.contains(addButtonHolder.mAsin) || TrackListToPlaylistAdapter.this.mPlaylistTrackLuids.contains(strFindObjectIdByTrackLocalLuid)) {
                                return;
                            }
                            if (addButtonHolder.mLuid != null && (playlistTrack = (PlaylistUtil.PlaylistTrack) TrackListToPlaylistAdapter.this.mCachePlaylistTracks.get(addButtonHolder.mLuid)) == null) {
                                Log.warning(TrackListToPlaylistAdapter.TAG, "Cannot retrieve cached playlist track with luid: %s", addButtonHolder.mLuid);
                            }
                            if (playlistTrack == null && addButtonHolder.mAsin != null && (playlistTrack = (PlaylistUtil.PlaylistTrack) TrackListToPlaylistAdapter.this.mCachePlaylistTracks.get(addButtonHolder.mAsin)) == null) {
                                Log.warning(TrackListToPlaylistAdapter.TAG, "Cannot retrieve cached playlist track with ASIN: %s", addButtonHolder.mAsin);
                            }
                            if (playlistTrack != null) {
                                imageView.setImageResource(R.drawable.playlist_btn_add);
                                TrackListToPlaylistAdapter.this.mOnDeleteButtonClickListener.onClick(playlistTrack.mPlaylistTrackId, playlistTrack.mUdo);
                                return;
                            } else {
                                imageView.setImageResource(R.drawable.playlist_btn_clear);
                                TrackListToPlaylistAdapter.this.mOnAddButtonClickListener.onClick(addButtonHolder.mTrackUri);
                                return;
                            }
                        }
                        imageView.setImageResource(R.drawable.playlist_btn_clear);
                        TrackListToPlaylistAdapter.this.mOnAddButtonClickListener.onClick(addButtonHolder.mTrackUri);
                    }
                }
            }
        };
        this.mOnAddButtonClickListener = onAddButtonClickListener;
        this.mOnDeleteButtonClickListener = onDeleteButtonClickListener;
    }

    public void setPlaylistTrackLuids(Set<String> playlistTrackLuids) {
        this.mPlaylistTrackLuids.clear();
        this.mPlaylistTrackLuids.addAll(playlistTrackLuids);
    }

    public void setCachePlaylist(Hashtable<String, PlaylistUtil.PlaylistTrack> cachePlaylistTracks) {
        this.mCachePlaylistTracks = cachePlaylistTracks;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View viewNewView = super.newView(context, cursor, parent);
        View viewFindViewById = viewNewView.findViewById(R.id.AddIcon);
        viewNewView.setOnClickListener(this.mInternalAddClickListener);
        viewFindViewById.setTag(new AddButtonHolder());
        return viewNewView;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        Hashtable<String, PlaylistUtil.PlaylistTrack> hashtable;
        String str;
        super.bindView(view, context, cursor);
        TrackListAdapter.RowViewHolder rowViewHolder = (TrackListAdapter.RowViewHolder) view.getTag();
        rowViewHolder.mOverflowButtonView.setVisibility(8);
        boolean zIsAvailable = ((MusicTrack) rowViewHolder.mLibraryItem).isAvailable();
        rowViewHolder.mMissingSDCardIcon.setVisibility(8);
        rowViewHolder.mStationIconInRightViews.setVisibility(8);
        ImageView imageView = (ImageView) view.findViewById(R.id.AddIcon);
        if (zIsAvailable || (this.mIsNightwingOnlyUser && this.mFreeTierUserPlaylistEditingEnabled)) {
            String strFindObjectIdByTrackLocalLuid = StringUtils.isNotBlank(rowViewHolder.mLuid) ? TrackUtil.findObjectIdByTrackLocalLuid(context, rowViewHolder.mLuid) : null;
            if (StringUtils.isBlank(rowViewHolder.mAsin)) {
                imageView.setImageResource(R.drawable.playlist_btn_clear);
            } else if (rowViewHolder.mAsin != null && (hashtable = this.mCachePlaylistTracks) != null && (((str = rowViewHolder.mLuid) != null && hashtable.containsKey(str)) || this.mCachePlaylistTracks.containsKey(rowViewHolder.mAsin) || (strFindObjectIdByTrackLocalLuid != null && this.mCachePlaylistTracks.containsKey(strFindObjectIdByTrackLocalLuid)))) {
                if (this.mPlaylistTrackLuids.contains(rowViewHolder.mLuid) || this.mPlaylistTrackLuids.contains(strFindObjectIdByTrackLocalLuid) || this.mPlaylistTrackLuids.contains(rowViewHolder.mAsin)) {
                    imageView.setImageResource(R.drawable.playlist_btn_added);
                } else {
                    imageView.setImageResource(R.drawable.playlist_btn_clear);
                }
            } else {
                imageView.setImageResource(R.drawable.playlist_btn_add);
            }
        }
        if (!isRowLoaded(cursor) || (!zIsAvailable && (!this.mIsNightwingOnlyUser || !this.mFreeTierUserPlaylistEditingEnabled))) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
        }
        AddButtonHolder addButtonHolder = (AddButtonHolder) imageView.getTag();
        addButtonHolder.mTrackUri = rowViewHolder.mTrackUri;
        addButtonHolder.mLuid = rowViewHolder.mLuid;
        addButtonHolder.mAsin = rowViewHolder.mAsin;
    }
}
