package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.IArtCache;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.ImageProcessor;
import com.amazon.mp3.util.RoundedCornerImageProcessor;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.BadgeView;
import com.amazon.mp3.view.IContentDisabledMarkableView;
import com.amazon.mp3.view.LibraryGroupedTrackRow;

/* JADX INFO: loaded from: classes3.dex */
public class GroupedTrackListAdapter extends TrackListAdapter {
    private ImageProcessor mImageProcessor;
    private final View.OnClickListener mOverflowMenuClickListener;

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 2;
    }

    private static class HeaderViewHolder extends ContentDisabledViewHolder {
        protected TextView mAlbumTitle;

        private HeaderViewHolder() {
            super();
        }
    }

    private static class ContentDisabledViewHolder extends TrackListAdapter.RowViewHolder {
        protected IContentDisabledMarkableView mContentDisabledView;

        private ContentDisabledViewHolder() {
        }
    }

    public GroupedTrackListAdapter(Context context, Uri contentUri, IArtCache artCache, boolean shouldShowPrimeBadging) {
        super(context, contentUri, artCache, ImageLoaderFactory.ItemType.ALBUM, shouldShowPrimeBadging);
        this.mOverflowMenuClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.GroupedTrackListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                v.performLongClick();
            }
        };
        this.mImageProcessor = new RoundedCornerImageProcessor(context);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int position) {
        return isHeaderView(position) ? 0 : 1;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter
    public View newView(final Context context, final Cursor cursor, final ViewGroup parent) {
        if (getItemViewType(cursor.getPosition()) == 0) {
            return createHeaderView(context, parent);
        }
        return createStandardView(context, parent);
    }

    private View createHeaderView(final Context context, final ViewGroup parent) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.library_grouped_track_row_header, parent, false);
        HeaderViewHolder headerViewHolder = new HeaderViewHolder();
        headerViewHolder.mAlbumTitle = (TextView) viewInflate.findViewById(R.id.AlbumItemTitle);
        initializeCommonProperties(headerViewHolder, viewInflate);
        this.mCreatedViews.put(viewInflate, null);
        return viewInflate;
    }

    private View createStandardView(final Context context, final ViewGroup parent) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.library_grouped_track_row_standard, parent, false);
        initializeCommonProperties(new ContentDisabledViewHolder(), viewInflate);
        this.mCreatedViews.put(viewInflate, null);
        return viewInflate;
    }

    private void initializeCommonProperties(ContentDisabledViewHolder rowViewHolder, View view) {
        TextView textView;
        LibraryGroupedTrackRow libraryGroupedTrackRow = (LibraryGroupedTrackRow) view.findViewById(R.id.TrackRowViewGroup);
        rowViewHolder.mIPrimeMarkableView = libraryGroupedTrackRow;
        rowViewHolder.mDownloadProgressView = libraryGroupedTrackRow;
        rowViewHolder.mContentDisabledView = libraryGroupedTrackRow;
        rowViewHolder.mLyricsBadgeView = (BadgeView) view.findViewById(R.id.LyricsView);
        rowViewHolder.mKatanaBadge = (BadgeView) view.findViewById(R.id.KatanaBadge);
        rowViewHolder.mImmersiveBadge = (BadgeView) view.findViewById(R.id.ImmersiveBadge);
        rowViewHolder.mPlaystateIcon = (ImageView) view.findViewById(R.id.PlaystateIcon);
        rowViewHolder.mAlbumArt = (ImageView) view.findViewById(R.id.AlbumArtwork);
        rowViewHolder.mTitle = (TextView) view.findViewById(R.id.PrimaryTitleTextView);
        rowViewHolder.mArtist = (TextView) view.findViewById(R.id.SecondaryTitleTextView);
        rowViewHolder.mDuration = (TextView) view.findViewById(R.id.Duration);
        rowViewHolder.mOverflowButtonView = view.findViewById(R.id.overflow_button_open);
        rowViewHolder.mStationIconInRightViews = (ImageView) view.findViewById(R.id.station_icon);
        if (ScreenUtil.isNormalOrSmallScreen() && (textView = rowViewHolder.mDuration) != null) {
            textView.setVisibility(8);
        }
        View clickableBadgeView = rowViewHolder.mDownloadProgressView.getClickableBadgeView();
        if (clickableBadgeView != null) {
            clickableBadgeView.setOnClickListener(getBadgeClickListener());
            clickableBadgeView.setTag(rowViewHolder);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.missing_sd_card);
        rowViewHolder.mMissingSDCardIcon = imageView;
        imageView.setOnClickListener(this.mMissingSDCardClickListener);
        updateBadgeContainer(rowViewHolder.mBadgeContainer, false);
        view.setTag(rowViewHolder);
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter
    public void bindView(final View view, final Context context, final Cursor cursor) {
        if (getItemViewType(cursor.getPosition()) == 0) {
            bindHeaderView(context, view, cursor);
        } else {
            bindStandardView(context, view, cursor);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0072 A[PHI: r3
  0x0072: PHI (r3v9 com.amazon.mp3.prime.ContentUnavailableReason) = (r3v5 com.amazon.mp3.prime.ContentUnavailableReason), (r3v4 com.amazon.mp3.prime.ContentUnavailableReason) binds: [B:22:0x007c, B:16:0x006f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0074 A[PHI: r3
  0x0074: PHI (r3v6 com.amazon.mp3.prime.ContentUnavailableReason) = 
  (r3v5 com.amazon.mp3.prime.ContentUnavailableReason)
  (r3v4 com.amazon.mp3.prime.ContentUnavailableReason)
  (r3v4 com.amazon.mp3.prime.ContentUnavailableReason)
 binds: [B:22:0x007c, B:14:0x0069, B:16:0x006f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void bindHeaderView(final android.content.Context r9, final android.view.View r10, final android.database.Cursor r11) {
        /*
            r8 = this;
            com.amazon.mp3.library.item.factory.LibraryItemFactory r0 = com.amazon.mp3.AmazonApplication.getLibraryItemFactory()
            com.amazon.mp3.library.item.MusicTrack r4 = r0.getTrack(r11)
            java.lang.Object r10 = r10.getTag()
            com.amazon.mp3.library.adapter.GroupedTrackListAdapter$HeaderViewHolder r10 = (com.amazon.mp3.library.adapter.GroupedTrackListAdapter.HeaderViewHolder) r10
            boolean r0 = r4.hasLyrics()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L1e
            boolean r0 = r8.isLyricsSupported()
            if (r0 == 0) goto L1e
            r0 = r2
            goto L1f
        L1e:
            r0 = r1
        L1f:
            r10.mHasLyrics = r0
            java.lang.String r0 = r4.getAlbumName()
            int r3 = r11.getPosition()
            boolean r3 = r8.isDiscHeader(r3)
            if (r3 == 0) goto L4b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = " - "
            r3.append(r0)
            int r0 = r11.getPosition()
            java.lang.CharSequence r0 = r8.getDiscHeaderText(r0)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
        L4b:
            android.widget.TextView r3 = r10.mAlbumTitle
            r3.setText(r0)
            r10.mLibraryItem = r4
            java.lang.String r0 = r8.getSource()
            java.lang.String r3 = "cirrus-local"
            boolean r0 = r3.equals(r0)
            r3 = 0
            boolean r5 = r8.mIsNightwingOnlyUser
            if (r5 == 0) goto L76
            com.amazon.mp3.capability.Capabilities r5 = com.amazon.mp3.AmazonApplication.getCapabilities()
            boolean r5 = r5.isExplicitLanguageFilterEnabled()
            if (r5 == 0) goto L74
            boolean r5 = r4.isExplicit()
            if (r5 != 0) goto L72
            goto L74
        L72:
            r5 = r1
            goto L83
        L74:
            r5 = r2
            goto L83
        L76:
            if (r0 == 0) goto L7f
            com.amazon.mp3.prime.ContentUnavailableReason r3 = r4.getOfflinePlaybackUnavailabilityReason()
            if (r3 != 0) goto L72
            goto L74
        L7f:
            boolean r5 = r4.isAvailable()
        L83:
            r7 = r5
            if (r0 == 0) goto L8c
            com.amazon.mp3.prime.ContentUnavailableReason r0 = com.amazon.mp3.prime.ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK
            if (r3 != r0) goto L8c
            r6 = r2
            goto L8d
        L8c:
            r6 = r1
        L8d:
            r1 = r8
            r2 = r10
            r3 = r9
            r5 = r11
            r1.bindCommonViews(r2, r3, r4, r5, r6)
            com.amazon.mp3.view.IContentDisabledMarkableView r9 = r10.mContentDisabledView
            r9.setContentEnabled(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.adapter.GroupedTrackListAdapter.bindHeaderView(android.content.Context, android.view.View, android.database.Cursor):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0034 A[PHI: r5
  0x0034: PHI (r5v2 com.amazon.mp3.prime.ContentUnavailableReason) = 
  (r5v1 com.amazon.mp3.prime.ContentUnavailableReason)
  (r5v0 com.amazon.mp3.prime.ContentUnavailableReason)
  (r5v0 com.amazon.mp3.prime.ContentUnavailableReason)
 binds: [B:13:0x003c, B:5:0x0029, B:7:0x002f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0032 A[PHI: r5
  0x0032: PHI (r5v5 com.amazon.mp3.prime.ContentUnavailableReason) = (r5v1 com.amazon.mp3.prime.ContentUnavailableReason), (r5v0 com.amazon.mp3.prime.ContentUnavailableReason) binds: [B:13:0x003c, B:7:0x002f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void bindStandardView(final android.content.Context r9, final android.view.View r10, final android.database.Cursor r11) {
        /*
            r8 = this;
            com.amazon.mp3.library.item.factory.LibraryItemFactory r0 = com.amazon.mp3.AmazonApplication.getLibraryItemFactory()
            com.amazon.mp3.library.item.MusicTrack r0 = r0.getTrack(r11)
            java.lang.Object r10 = r10.getTag()
            com.amazon.mp3.library.adapter.GroupedTrackListAdapter$ContentDisabledViewHolder r10 = (com.amazon.mp3.library.adapter.GroupedTrackListAdapter.ContentDisabledViewHolder) r10
            r10.mLibraryItem = r0
            java.lang.String r1 = r8.getSource()
            java.lang.String r2 = "cirrus-local"
            boolean r1 = r2.equals(r1)
            boolean r2 = r8.mIsNightwingOnlyUser
            r3 = 0
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L36
            com.amazon.mp3.capability.Capabilities r2 = com.amazon.mp3.AmazonApplication.getCapabilities()
            boolean r2 = r2.isExplicitLanguageFilterEnabled()
            if (r2 == 0) goto L34
            boolean r2 = r0.isExplicit()
            if (r2 != 0) goto L32
            goto L34
        L32:
            r2 = r3
            goto L43
        L34:
            r2 = r4
            goto L43
        L36:
            if (r1 == 0) goto L3f
            com.amazon.mp3.prime.ContentUnavailableReason r5 = r0.getOfflinePlaybackUnavailabilityReason()
            if (r5 != 0) goto L32
            goto L34
        L3f:
            boolean r2 = r0.isAvailable()
        L43:
            r7 = r2
            if (r1 == 0) goto L4c
            com.amazon.mp3.prime.ContentUnavailableReason r1 = com.amazon.mp3.prime.ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK
            if (r5 != r1) goto L4c
            r6 = r4
            goto L4d
        L4c:
            r6 = r3
        L4d:
            r1 = r8
            r2 = r10
            r3 = r9
            r4 = r0
            r5 = r11
            r1.bindCommonViews(r2, r3, r4, r5, r6)
            long r0 = r0.getDuration()
            r8.bindDuration(r10, r0)
            com.amazon.mp3.view.IContentDisabledMarkableView r9 = r10.mContentDisabledView
            r9.setContentEnabled(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.adapter.GroupedTrackListAdapter.bindStandardView(android.content.Context, android.view.View, android.database.Cursor):void");
    }

    private void bindCommonViews(TrackListAdapter.RowViewHolder holder, Context context, MusicTrack track, final Cursor cursor, final boolean shouldBindMissingSDCardIcon) {
        holder.mTrackUri = track.getContentUri();
        holder.mLuid = track.getLuid();
        String artistName = track.getArtistName();
        holder.mArtist.setText(artistName);
        holder.mArtist.setVisibility((TextUtils.isEmpty(artistName) || artistName.equals(track.getAlbumArtistName())) ? 8 : 0);
        holder.mTitle.setText(track.getTitle());
        holder.mLibraryItem = track;
        holder.mAsin = track.getAsin();
        bindImageView(holder, track, cursor);
        bindDownload(holder, track, cursor);
        bindPlaystateIconAndTrackCount(context, holder);
        bindPrimeView(holder, track);
        holder.mLyricsBadgeView.setBadgeVisiblility(track.hasLyrics() && isLyricsSupported());
        holder.mKatanaBadge.populate(ContentEncodingDecoder.getEncodingBadgeModel(track.getContentEncodings(), this.mContext, 8));
        holder.mImmersiveBadge.populate(ContentEncodingDecoder.getImmersiveContentBadgeModel(track.getContentEncodings(), this.mContext, 8));
        bindArtistLabel(holder, track.getAlbumArtistName(), track.getArtistName());
        if (shouldBindMissingSDCardIcon) {
            bindMissingSDCardIcon(holder, track);
        }
        holder.mOverflowButtonView.setOnClickListener(this.mOverflowMenuClickListener);
        if (this.mIsNightwingOnlyUser) {
            if (this.mShouldShowStationIcon && track.isNotOwned()) {
                holder.mStationIconInRightViews.setVisibility(0);
            } else {
                holder.mStationIconInRightViews.setVisibility(8);
            }
        }
    }

    private void bindDownload(TrackListAdapter.RowViewHolder holder, MusicTrack track, Cursor cursor) {
        if (track.getAsin() == null) {
            holder.mDownloadState = -1;
            return;
        }
        holder.mDownloadId = !TextUtils.isEmpty(holder.mAsin) ? holder.mAsin : holder.mLuid;
        int downloadState = track.getDownloadState();
        holder.mDownloadState = downloadState;
        holder.mDownloadProgressView.setDownloadState(downloadState);
        int i = holder.mDownloadState;
        if (i == 4 || i == 3) {
            addProgress(holder);
            holder.mDownloadProgressView.setProgress(getProgress(holder));
        } else {
            holder.mDownloadProgressView.setProgress(0);
        }
    }

    void bindMissingSDCardIcon(final TrackListAdapter.RowViewHolder holder, final MusicTrack track) {
        if (holder.mDownloadState == 0) {
            holder.mDuration.setVisibility(8);
            holder.mDownloadProgressView.getClickableBadgeView().setVisibility(8);
            holder.mStationIconInRightViews.setVisibility(8);
            holder.mMissingSDCardIcon.setVisibility(0);
            holder.mMissingSDCardIcon.setTag(holder);
            return;
        }
        holder.mMissingSDCardIcon.setVisibility(8);
    }

    private void bindImageView(final TrackListAdapter.RowViewHolder holder, final MusicTrack track, Cursor cursor) {
        int position = cursor.getPosition();
        boolean z = true;
        boolean z2 = true;
        while (cursor.getLong(this.mAlbumIdIndex) == ((MusicTrack) holder.mLibraryItem).getAlbumId()) {
            int i = cursor.getInt(this.mContentOwnershipStatusIndex);
            int i2 = cursor.getInt(this.mContentCatalogStatusIndex);
            if (z && ContentOwnershipStatus.fromValue(i).isOwned()) {
                z = false;
            }
            if (z2 && !ContentCatalogStatus.fromValue(i2).isPreviouslyCatalog()) {
                z2 = false;
            }
            if ((!z && !z2) || !cursor.moveToNext()) {
                break;
            }
        }
        cursor.moveToPosition(position);
        long albumId = track.getAlbumId();
        holder.mAlbumId = albumId;
        this.mImageProcessor.process((BitmapDrawable) this.mArtCache.getDrawable(this.mImageItemType, (String) null, albumId), holder.mAlbumArt);
        String albumName = track.getAlbumName();
        Context context = this.mContext;
        Object[] objArr = new Object[1];
        if (albumName == null) {
            albumName = "";
        }
        objArr[0] = albumName;
        holder.mAlbumArt.setContentDescription(context.getString(R.string.album_string_content_description, objArr));
        holder.mAlbumArt.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.GroupedTrackListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                Album album;
                Intent intentForContentUri;
                MusicTrack musicTrack = (MusicTrack) holder.mLibraryItem;
                if (musicTrack == null || (album = musicTrack.getAlbum()) == null || (intentForContentUri = LibraryActivityFactory.getIntentForContentUri(GroupedTrackListAdapter.this.mContext, album.getContentUri())) == null) {
                    return;
                }
                intentForContentUri.addFlags(268435456);
                GroupedTrackListAdapter.this.mContext.startActivity(intentForContentUri);
            }
        });
    }

    protected void bindPlaystateIconAndTrackCount(final Context context, TrackListAdapter.RowViewHolder holder) {
        bindPlaystateIcon(context, holder);
        holder.mPlaystateIcon.getVisibility();
    }

    private boolean isHeaderView(int position) {
        if (position == 0) {
            return true;
        }
        Cursor cursor = getCursor();
        if (cursor != null) {
            int position2 = cursor.getPosition();
            if (cursor.moveToPosition(position)) {
                long j = cursor.getLong(this.mAlbumIdIndex);
                int i = this.mDiscNumIndex;
                boolean z = i >= 0;
                long j2 = z ? cursor.getLong(i) : -1L;
                cursor.moveToPosition(position - 1);
                long j3 = cursor.getLong(this.mAlbumIdIndex);
                long j4 = z ? cursor.getLong(this.mDiscNumIndex) : -1L;
                cursor.moveToPosition(position2);
                return (j == j3 && j2 == j4) ? false : true;
            }
        }
        return false;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter
    protected int getSortIndexColumn(Cursor c) {
        return c.getColumnIndexOrThrow("sort_album");
    }
}
