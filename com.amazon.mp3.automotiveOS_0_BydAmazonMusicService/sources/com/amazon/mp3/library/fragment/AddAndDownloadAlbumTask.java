package com.amazon.mp3.library.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.CtaAlbumRequester;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.notification.NotificationInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AddAndDownloadAlbumTask extends AddAndDownloadTask {
    private static final String TAG = AddAndDownloadAlbumTask.class.getSimpleName();
    private final PrimeAlbum mAlbum;

    @Override // com.amazon.mp3.library.fragment.AddAndDownloadTask
    public void onItemAdded() {
    }

    public AddAndDownloadAlbumTask(Context context, PrimeAlbum album, boolean download) {
        super(context, download);
        this.mAlbum = album;
    }

    @Override // com.amazon.mp3.library.fragment.AddAndDownloadTask
    protected List<String> getTracksAsinsToAdd() {
        List<String> arrayList;
        Exception e;
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(getContext());
        String asin = this.mAlbum.getAsin();
        Cursor cursorQuery = readOnlyDatabase.query("Track", new String[]{"asin", "ownership_status", "prime_status"}, "album_asin=?", new String[]{asin}, null, null, null);
        if (cursorQuery == null) {
            return Collections.emptyList();
        }
        List<String> listEmptyList = Collections.emptyList();
        try {
            try {
                List<PrimeTrack> listRequestTracksForAsin = new CtaAlbumRequester(getContext(), false).requestTracksForAsin(asin);
                arrayList = new ArrayList<>(listRequestTracksForAsin.size());
                try {
                    for (PrimeTrack primeTrack : listRequestTracksForAsin) {
                        if (primeTrack.isAnyCatalog()) {
                            arrayList.add(primeTrack.getAsin());
                        }
                    }
                    if (!listRequestTracksForAsin.isEmpty() && cursorQuery.moveToFirst()) {
                        do {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("asin"));
                            if (ContentOwnershipStatus.fromValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("ownership_status"))).isInLibrary()) {
                                arrayList.remove(string);
                            }
                        } while (cursorQuery.moveToNext());
                    }
                    return arrayList;
                } catch (Exception e2) {
                    e = e2;
                    Log.error(TAG, "ClientBuddy CTA failed for Album ASIN: " + asin, e);
                    return arrayList;
                }
            } finally {
                cursorQuery.close();
            }
        } catch (Exception e3) {
            arrayList = listEmptyList;
            e = e3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onProgressUpdate(Integer... values) {
        if (values[0].intValue() == 1) {
            BauhausToastUtils.showDefaultToast(getContext(), R.string.dmusic_prime_album_added_to_library, 1);
        } else {
            super.onProgressUpdate((Object[]) values);
        }
    }

    @Override // com.amazon.mp3.library.fragment.AddAndDownloadTask
    protected Uri getContentUriToDownload() {
        return this.mAlbum.getTrackListContentUri();
    }

    @Override // com.amazon.mp3.library.fragment.AddAndDownloadTask
    protected String getRequestIdForDownload() {
        return this.mAlbum.getTrackListContentUri().toString();
    }

    @Override // com.amazon.mp3.library.fragment.AddAndDownloadTask
    protected NotificationInfo getNotificationInfoForDownload() {
        return new NotificationInfo(this.mAlbum.getTitle(), this.mAlbum.getArtist(), ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), IdGenerator.generateAlbumId(this.mAlbum.getAsin(), this.mAlbum.getTitle())).toString());
    }
}
