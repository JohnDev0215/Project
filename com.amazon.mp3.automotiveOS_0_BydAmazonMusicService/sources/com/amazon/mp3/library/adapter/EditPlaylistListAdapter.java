package com.amazon.mp3.library.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.activity.AllAccessPlaylistLimitReachedActivity;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.util.AllAccessUserPlaylistLimitTracker;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.view.ContentDisabledHelper;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class EditPlaylistListAdapter extends TrackListAdapter {
    private Uri mContentUri;
    private boolean mMutated;
    private View.OnClickListener mOnDeleteClickListener;
    private int mPlaylistTrackIdIndex;
    private int mUdoIndex;

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter
    protected boolean enableAlphabetIndexer() {
        return false;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, com.amazon.mp3.library.adapter.CursorListAdapter, android.widget.SectionIndexer
    public Object[] getSections() {
        return new Object[0];
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int position) {
        return false;
    }

    public static class RowViewHolder {
        ImageView mAlbumArt;
        long mAlbumId;
        TextView mArtist;
        ImageView mDeleteIcon;
        long mPlaylistTrackId;
        TextView mTitle;
        TextView mTrackDuration;
        long mUdo;

        public CharSequence getTitle() {
            TextView textView = this.mTitle;
            return textView != null ? textView.getText() : "";
        }

        public CharSequence getArtist() {
            TextView textView = this.mArtist;
            return textView != null ? textView.getText() : "";
        }

        public Drawable getAlbumArt() {
            ImageView imageView = this.mAlbumArt;
            if (imageView != null) {
                return imageView.getDrawable();
            }
            return null;
        }
    }

    public EditPlaylistListAdapter(Context context, Uri contentUri, IArtCache artCache, boolean shouldShowPrimeBadging) {
        super(context, contentUri, artCache, ImageLoaderFactory.ItemType.ALBUM, shouldShowPrimeBadging);
        this.mOnDeleteClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.EditPlaylistListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                final ContentResolver contentResolver = EditPlaylistListAdapter.this.mContext.getContentResolver();
                String source = MediaProvider.getSource(EditPlaylistListAdapter.this.mContentUri);
                RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
                AllAccessUserPlaylistLimitTracker.Response responseCanRemove = AllAccessUserPlaylistLimitTracker.canRemove(EditPlaylistListAdapter.this.mContentUri);
                if (responseCanRemove != AllAccessUserPlaylistLimitTracker.Response.SUCCESS) {
                    EditPlaylistListAdapter.this.mContext.startActivity(AllAccessPlaylistLimitReachedActivity.getStartIntent(responseCanRemove.toString()));
                    return;
                }
                final Uri uriBuild = MediaProvider.UdoPlaylistTracks.getContentUri(source, EditPlaylistListAdapter.this.getPlaylistId(), rowViewHolder.mPlaylistTrackId).buildUpon().appendQueryParameter("udo", String.valueOf(rowViewHolder.mUdo)).build();
                if (CirrusMediaSource.match(uriBuild)) {
                    uriBuild = PlaylistUtil.withAppendedScratchArgument(uriBuild);
                }
                new Thread(new Runnable() { // from class: com.amazon.mp3.library.adapter.EditPlaylistListAdapter.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (contentResolver.delete(uriBuild, null, null) > 0) {
                            PlaylistUtil.setMutatedFlag(EditPlaylistListAdapter.this.mContext);
                            EditPlaylistListAdapter.this.mMutated = true;
                        }
                    }
                }, "Track delete thread").start();
                AllAccessUserPlaylistLimitTracker.incrementPendingRemoveChanges(EditPlaylistListAdapter.this.mContentUri);
            }
        };
        this.mContentUri = contentUri;
    }

    public boolean userMutatedPlaylist() {
        return this.mMutated;
    }

    public static String[] getDefaultProjection() {
        String[] trackProjection = AmazonApplication.getLibraryItemFactory().getTrackProjection();
        String[] strArr = (String[]) Arrays.copyOf(trackProjection, trackProjection.length + 2);
        strArr[trackProjection.length] = "udo_playlist_track_id";
        strArr[trackProjection.length + 1] = "udo";
        return strArr;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, com.amazon.mp3.library.adapter.BadgeableListAdapter, com.amazon.mp3.library.adapter.CursorListAdapter, android.widget.CursorAdapter
    public void changeCursor(Cursor c) {
        super.changeCursor(c);
        if (c != null) {
            this.mPlaylistTrackIdIndex = c.getColumnIndexOrThrow("udo_playlist_track_id");
            this.mUdoIndex = c.getColumnIndexOrThrow("udo");
        }
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View viewInflate = View.inflate(context, R.layout.library_edit_playlist_row, null);
        RowViewHolder rowViewHolder = new RowViewHolder();
        rowViewHolder.mTitle = (TextView) viewInflate.findViewById(R.id.TrackTitle);
        rowViewHolder.mArtist = (TextView) viewInflate.findViewById(R.id.ArtistName);
        rowViewHolder.mDeleteIcon = (ImageView) viewInflate.findViewById(R.id.DeleteIcon);
        rowViewHolder.mAlbumArt = (ImageView) viewInflate.findViewById(R.id.AlbumArtwork);
        rowViewHolder.mTrackDuration = (TextView) viewInflate.findViewById(R.id.TrackDuration);
        rowViewHolder.mDeleteIcon.setTag(rowViewHolder);
        viewInflate.setTag(rowViewHolder);
        this.mCreatedViews.put(viewInflate, null);
        return viewInflate;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, com.amazon.mp3.library.cache.image.IArtCache.IArtLoadedListener
    public void onDrawableLoaded(Drawable d, String source, long id) {
        ImageView imageView;
        Iterator<View> it = this.mCreatedViews.keySet().iterator();
        while (it.hasNext()) {
            RowViewHolder rowViewHolder = (RowViewHolder) it.next().getTag();
            if (rowViewHolder.mAlbumId == id && (imageView = rowViewHolder.mAlbumArt) != null) {
                imageView.setImageDrawable(d);
            }
        }
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        RowViewHolder rowViewHolder = (RowViewHolder) view.getTag();
        MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(cursor);
        int position = cursor.getPosition();
        rowViewHolder.mUdo = cursor.getInt(this.mUdoIndex);
        TextView textView = rowViewHolder.mTitle;
        if (textView != null) {
            textView.setText(String.valueOf(position + 1) + ". " + track.getTitle());
        }
        TextView textView2 = rowViewHolder.mArtist;
        if (textView2 != null) {
            textView2.setText(track.getArtistName());
        }
        ImageView imageView = rowViewHolder.mDeleteIcon;
        if (imageView != null) {
            imageView.setOnClickListener(this.mOnDeleteClickListener);
        }
        rowViewHolder.mPlaylistTrackId = cursor.getLong(this.mPlaylistTrackIdIndex);
        if (rowViewHolder.mTrackDuration != null) {
            long duration = track.getDuration();
            if (this.mIsCirrus) {
                duration *= 1000;
            }
            rowViewHolder.mTrackDuration.setText(StringUtil.getCachedFormattedTime(duration));
            rowViewHolder.mTrackDuration.setVisibility(4);
        }
        if (this.mArtCache != null) {
            long albumId = track.getAlbumId();
            rowViewHolder.mAlbumId = albumId;
            ImageView imageView2 = rowViewHolder.mAlbumArt;
            if (imageView2 != null) {
                imageView2.setImageDrawable(this.mArtCache.getDrawable(ImageLoaderFactory.ItemType.ALBUM, (String) null, albumId));
            }
        }
        ImageView imageView3 = rowViewHolder.mDeleteIcon;
        if (imageView3 != null && imageView3.getVisibility() == 0) {
            ContentDisabledHelper.setContentEnabled(view, true);
        } else {
            ContentDisabledHelper.setContentEnabled(view, track.isAvailable());
        }
    }

    protected long getPlaylistId() {
        return Long.parseLong(this.mContentUri.getPathSegments().get(4));
    }
}
