package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.generator.LocalLuidGenerator;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.UserSubscriptionUtil;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class AddToPlaylistAdapter extends TrackListAdapter {
    private Hashtable<String, PlaylistUtil.PlaylistTrack> mCachePlaylistTracks;
    private View.OnClickListener mInternalAddClickListener;
    private OnAddButtonClickListener mOnAddButtonClickListener;
    private OnDeleteButtonClickListener mOnDeleteButtonClickListener;
    private final String mPlaylistBtnAddDescription;
    private final String mPlaylistBtnAddedDescription;
    private final String mPlaylistBtnClearDescription;
    private Set<String> mPlaylistTrackLuids;
    private BauhausTextStyler mStyler;

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

    public AddToPlaylistAdapter(Context context, Uri contentUri, IArtCache artCache, OnAddButtonClickListener onAddButtonClickListener, OnDeleteButtonClickListener onDeleteButtonClickListener, boolean shouldShowPrimeBadging) {
        super(context, contentUri, artCache, ImageLoaderFactory.ItemType.ALBUM, shouldShowPrimeBadging);
        this.mCachePlaylistTracks = new Hashtable<>();
        this.mPlaylistTrackLuids = new HashSet();
        this.mInternalAddClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.AddToPlaylistAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                View viewFindViewById = v.findViewById(R.id.AddIcon);
                AddButtonHolder addButtonHolder = (AddButtonHolder) viewFindViewById.getTag();
                boolean zIsNightwingOnly = UserSubscriptionUtil.isNightwingOnly();
                if (viewFindViewById instanceof ImageView) {
                    ImageView imageView = (ImageView) viewFindViewById;
                    if (addButtonHolder.mTrackUri != null) {
                        if (addButtonHolder.mLuid == null) {
                            BauhausToastUtils.showDefaultToast(AddToPlaylistAdapter.this.mContext, R.string.dmusic_playlist_edit_error_adding_song_toast_msg, 0);
                            return;
                        }
                        MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(addButtonHolder.mTrackUri);
                        String str = addButtonHolder.mLuid;
                        String strFindObjectIdByTrackLocalLuid = str != null ? TrackUtil.findObjectIdByTrackLocalLuid(AddToPlaylistAdapter.this.mContext, str) : null;
                        if (AddToPlaylistAdapter.this.mCachePlaylistTracks != null && ((addButtonHolder.mLuid != null && AddToPlaylistAdapter.this.mCachePlaylistTracks.containsKey(addButtonHolder.mLuid)) || ((addButtonHolder.mAsin != null && AddToPlaylistAdapter.this.mCachePlaylistTracks.containsKey(addButtonHolder.mAsin)) || (strFindObjectIdByTrackLocalLuid != null && AddToPlaylistAdapter.this.mCachePlaylistTracks.containsKey(strFindObjectIdByTrackLocalLuid))))) {
                            if (AddToPlaylistAdapter.this.mPlaylistTrackLuids.contains(addButtonHolder.mLuid) || AddToPlaylistAdapter.this.mPlaylistTrackLuids.contains(addButtonHolder.mAsin) || AddToPlaylistAdapter.this.mPlaylistTrackLuids.contains(strFindObjectIdByTrackLocalLuid)) {
                                return;
                            }
                            PlaylistUtil.PlaylistTrack playlistTrack = (PlaylistUtil.PlaylistTrack) AddToPlaylistAdapter.this.mCachePlaylistTracks.get(addButtonHolder.mLuid);
                            if (playlistTrack == null) {
                                playlistTrack = (PlaylistUtil.PlaylistTrack) AddToPlaylistAdapter.this.mCachePlaylistTracks.get(addButtonHolder.mAsin);
                            }
                            imageView.setImageResource(R.drawable.playlist_btn_add);
                            imageView.setContentDescription(AddToPlaylistAdapter.this.mPlaylistBtnAddDescription);
                            AddToPlaylistAdapter.this.mOnDeleteButtonClickListener.onClick(playlistTrack.mPlaylistTrackId, playlistTrack.mUdo);
                            return;
                        }
                        if (!zIsNightwingOnly || track.isAllOwned() || AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled()) {
                            imageView.setImageResource(R.drawable.playlist_btn_clear);
                            imageView.setContentDescription(AddToPlaylistAdapter.this.mPlaylistBtnClearDescription);
                            AddToPlaylistAdapter.this.mOnAddButtonClickListener.onClick(addButtonHolder.mTrackUri);
                        }
                    }
                }
            }
        };
        this.mOnAddButtonClickListener = onAddButtonClickListener;
        this.mOnDeleteButtonClickListener = onDeleteButtonClickListener;
        this.mPlaylistBtnAddDescription = context.getString(R.string.dmusic_playlist_btn_add_description);
        this.mPlaylistBtnClearDescription = context.getString(R.string.remove_added_track_from_playlist_content_description);
        this.mPlaylistBtnAddedDescription = context.getString(R.string.dmusic_playlist_already_in_playlist);
        this.mStyler = new BauhausTextStyler();
    }

    public void setPlaylistTrackLuids(Set<String> playlistTrackLuids) {
        this.mPlaylistTrackLuids.clear();
        this.mPlaylistTrackLuids.addAll(playlistTrackLuids);
    }

    public void setCachePlaylist(Hashtable<String, PlaylistUtil.PlaylistTrack> cachePlaylistTracks) {
        this.mCachePlaylistTracks.clear();
        this.mCachePlaylistTracks.putAll(cachePlaylistTracks);
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View viewNewView = super.newView(context, cursor, parent);
        View viewFindViewById = viewNewView.findViewById(R.id.AddIcon);
        if (AccessibilityUtil.isAccessibilityEnabled(context)) {
            viewFindViewById.setOnClickListener(this.mInternalAddClickListener);
        } else {
            viewNewView.setOnClickListener(this.mInternalAddClickListener);
        }
        viewFindViewById.setTag(new AddButtonHolder());
        return viewNewView;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter
    public void bindView(View view, final Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        final TrackListAdapter.RowViewHolder rowViewHolder = (TrackListAdapter.RowViewHolder) view.getTag();
        rowViewHolder.mOverflowButtonView.setVisibility(8);
        rowViewHolder.mMissingSDCardIcon.setVisibility(8);
        rowViewHolder.mStationIconInRightViews.setVisibility(8);
        final ImageView imageView = (ImageView) view.findViewById(R.id.AddIcon);
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.adapter.AddToPlaylistAdapter$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$bindView$0(rowViewHolder, context, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.amazon.mp3.library.adapter.AddToPlaylistAdapter.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
            }

            @Override // rx.Observer
            public void onNext(final String cloudTrackLuid) {
                if (AddToPlaylistAdapter.this.mCachePlaylistTracks != null && ((rowViewHolder.mLuid != null && AddToPlaylistAdapter.this.mCachePlaylistTracks.containsKey(rowViewHolder.mLuid)) || ((rowViewHolder.mAsin != null && AddToPlaylistAdapter.this.mCachePlaylistTracks.containsKey(rowViewHolder.mAsin)) || (cloudTrackLuid != null && AddToPlaylistAdapter.this.mCachePlaylistTracks.containsKey(cloudTrackLuid))))) {
                    if (AddToPlaylistAdapter.this.mPlaylistTrackLuids.contains(rowViewHolder.mLuid) || AddToPlaylistAdapter.this.mPlaylistTrackLuids.contains(rowViewHolder.mAsin) || AddToPlaylistAdapter.this.mPlaylistTrackLuids.contains(cloudTrackLuid)) {
                        imageView.setImageResource(R.drawable.playlist_btn_added);
                        imageView.setContentDescription(AddToPlaylistAdapter.this.mPlaylistBtnAddedDescription);
                        rowViewHolder.mArtist.setText(R.string.dmusic_playlist_already_in_playlist);
                        return;
                    } else {
                        imageView.setImageResource(R.drawable.playlist_btn_clear);
                        imageView.setContentDescription(AddToPlaylistAdapter.this.mPlaylistBtnClearDescription);
                        TrackListAdapter.RowViewHolder rowViewHolder2 = rowViewHolder;
                        rowViewHolder2.mArtist.setText(rowViewHolder2.mArtistLabel);
                        return;
                    }
                }
                imageView.setImageResource(R.drawable.playlist_btn_add);
                imageView.setContentDescription(AddToPlaylistAdapter.this.mPlaylistBtnAddDescription);
            }
        });
        if (!isRowLoaded(cursor)) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
        }
        AddButtonHolder addButtonHolder = (AddButtonHolder) imageView.getTag();
        addButtonHolder.mTrackUri = rowViewHolder.mTrackUri;
        addButtonHolder.mLuid = rowViewHolder.mLuid;
        addButtonHolder.mAsin = rowViewHolder.mAsin;
        rowViewHolder.mPlaystateIcon.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(TrackListAdapter.RowViewHolder rowViewHolder, Context context, Subscriber subscriber) {
        String luid;
        MusicTrack trackForAsin;
        try {
            if (rowViewHolder.mAsin == null || (trackForAsin = new CirrusSourceLibraryItemFactory(context).getTrackForAsin(rowViewHolder.mAsin)) == null) {
                luid = null;
            } else {
                luid = trackForAsin.getLuid();
                if (luid == null || LocalLuidGenerator.isLocalLuid(luid)) {
                    luid = TrackUtil.findObjectIdByTrackLocalLuid(this.mContext, luid);
                }
            }
            subscriber.onNext(luid);
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }
}
