package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.adapter.TrackListToPlaylistAdapter;
import com.amazon.mp3.library.cache.image.IArtCache;

/* JADX INFO: loaded from: classes3.dex */
public class AddAlbumDetailToPlaylistAdapter extends TrackListToPlaylistAdapter {
    public AddAlbumDetailToPlaylistAdapter(Context context, Uri contentUri, IArtCache artCache, TrackListToPlaylistAdapter.OnAddButtonClickListener onAddButtonClickListener, TrackListToPlaylistAdapter.OnDeleteButtonClickListener onDeleteButtonClickListener, boolean shouldShowPrimeBadging) {
        super(context, contentUri, artCache, onAddButtonClickListener, onDeleteButtonClickListener, shouldShowPrimeBadging);
    }

    @Override // com.amazon.mp3.library.adapter.TrackListToPlaylistAdapter, com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        TrackListAdapter.RowViewHolder rowViewHolder = (TrackListAdapter.RowViewHolder) view.getTag();
        rowViewHolder.mOverflowButtonView.setVisibility(8);
        rowViewHolder.mStationIconInRightViews.setVisibility(8);
        ImageView imageView = rowViewHolder.mAlbumArt;
        if (imageView != null && 8 != imageView.getVisibility()) {
            rowViewHolder.mAlbumArt.setVisibility(8);
        }
        TextView textView = rowViewHolder.mArtist;
        if (textView == null || 8 == textView.getVisibility()) {
            return;
        }
        rowViewHolder.mArtist.setVisibility(8);
    }
}
