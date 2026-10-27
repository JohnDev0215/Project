package com.amazon.mp3.lyrics;

import android.content.Context;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.lyrics.exception.LyricsParseExeption;
import com.amazon.mp3.lyrics.exception.NetworkException;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.lyrics.item.LyricsStatusChanges;
import com.amazon.mp3.lyrics.item.LyricsTrackInfo;
import com.amazon.mp3.lyrics.item.UnparsedLyrics;
import com.amazon.mp3.util.Log;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class LyricsManager {
    private static String TAG = "LyricsManager";
    private final LyricsDB mLyricsDB;
    private LyricsFetcher mLyricsFetcher;
    private final LyricsFile mLyricsFile;
    private final LyricsStorage mLyricsStorage;

    private boolean shouldCheckForUpdate(long currentTime, long prevTime, long frequency) {
        long j = currentTime - prevTime;
        return j < 0 || j >= frequency;
    }

    public LyricsManager(Context applicationContext, LyricsDB lyricsDB) {
        this.mLyricsDB = lyricsDB;
        this.mLyricsFetcher = new LibraryLyricsFetcher(applicationContext, AccountDetailUtil.getMusicTerritoryOfResidence());
        this.mLyricsFile = new LyricsFile(applicationContext);
        this.mLyricsStorage = new LyricsStorage(applicationContext);
    }

    public Lyrics loadLyrics(LyricsTrackInfo info) throws LyricsParseExeption, FileNotFoundException {
        return this.mLyricsFile.load(info);
    }

    public void updateLyrics() throws Throwable {
        downloadLyrics();
        if (shouldCheckForRefresh()) {
            refreshLyrics();
        }
        if (shouldCheckForTakedowns()) {
            removeTakedowns();
            removeLyricsNotInList(this.mLyricsDB.loadTrackInfoForAllTracksWithLyrics());
        }
    }

    public Lyrics downloadLyrics(LyricsTrackInfo info) throws Throwable {
        UnparsedLyrics unparsedLyrics = this.mLyricsFetcher.downloadLyrics(Collections.singletonList(info)).get(info);
        List<String> listSingletonList = Collections.singletonList(info.getAsin());
        if (unparsedLyrics != null) {
            LyricsStatus status = unparsedLyrics.getStatus();
            LyricsStatus lyricsStatus = LyricsStatus.AVAILABLE;
            if (status == lyricsStatus) {
                String rawLyrics = unparsedLyrics.getRawLyrics();
                Lyrics lyrics = new LyricsParser().parse(rawLyrics);
                this.mLyricsFile.save(info, rawLyrics);
                this.mLyricsDB.updateLyricsStatusForAsins(lyricsStatus, listSingletonList);
                return lyrics;
            }
        }
        this.mLyricsDB.updateLyricsStatusForAsins(LyricsStatus.NO_LYRICS, listSingletonList);
        return null;
    }

    public void downloadLyrics() throws Throwable {
        List<LyricsTrackInfo> listLoadTrackInfoForNewTracks = this.mLyricsDB.loadTrackInfoForNewTracks(25);
        while (listLoadTrackInfoForNewTracks != null && !listLoadTrackInfoForNewTracks.isEmpty()) {
            downloadLyrics(listLoadTrackInfoForNewTracks);
            listLoadTrackInfoForNewTracks = this.mLyricsDB.loadTrackInfoForNewTracks(25);
        }
        this.mLyricsStorage.setLastFullDownloadLocalTimeInMillis(System.currentTimeMillis());
    }

    public void downloadLyrics(List<LyricsTrackInfo> infoList) throws Throwable {
        Map<LyricsTrackInfo, UnparsedLyrics> mapDownloadLyrics = this.mLyricsFetcher.downloadLyrics(infoList);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (LyricsTrackInfo lyricsTrackInfo : infoList) {
            UnparsedLyrics unparsedLyrics = mapDownloadLyrics.get(lyricsTrackInfo);
            if (unparsedLyrics != null) {
                String rawLyrics = unparsedLyrics.getRawLyrics();
                if (unparsedLyrics.getStatus() == LyricsStatus.AVAILABLE) {
                    arrayList.add(lyricsTrackInfo.getAsin());
                    this.mLyricsFile.save(lyricsTrackInfo, rawLyrics);
                } else {
                    arrayList2.add(lyricsTrackInfo.getAsin());
                }
            }
        }
        this.mLyricsDB.updateLyricsStatusForAsins(LyricsStatus.AVAILABLE, arrayList);
        this.mLyricsDB.updateLyricsStatusForAsins(LyricsStatus.NO_LYRICS, arrayList2);
    }

    public void refreshLyrics() throws Throwable {
        long lastUpdateCompletedTimestamp = getLastUpdateCompletedTimestamp();
        if (lastUpdateCompletedTimestamp == 0) {
            Log.debug(TAG, "Run download lyrics first? Cannot run refresh lyrics on a clean slate");
            return;
        }
        String pagingToken = null;
        do {
            LyricsStatusChanges refreshList = this.mLyricsFetcher.getRefreshList(lastUpdateCompletedTimestamp, pagingToken);
            downloadLyrics(this.mLyricsDB.loadTrackInfoForAsins(refreshList.getAsinList()));
            this.mLyricsStorage.setUpdatesCompletedTimestamp(refreshList.getLastFetchTimestamp());
            pagingToken = refreshList.getPagingToken();
            if (pagingToken == null) {
                break;
            }
        } while (!pagingToken.isEmpty());
        this.mLyricsStorage.setLastRefreshLocalTimeInMillis(System.currentTimeMillis());
    }

    private void removeTakedowns() throws NetworkException {
        long lastTakeDownsCompletedTimestamp = getLastTakeDownsCompletedTimestamp();
        if (lastTakeDownsCompletedTimestamp == 0) {
            Log.debug(TAG, "Run download lyrics first? Cannot run take downs on a clean slate");
            return;
        }
        String pagingToken = null;
        do {
            LyricsStatusChanges takedownList = this.mLyricsFetcher.getTakedownList(lastTakeDownsCompletedTimestamp, pagingToken);
            Iterator<LyricsTrackInfo> it = this.mLyricsDB.loadTrackInfoForAsins(takedownList.getAsinList()).iterator();
            while (it.hasNext()) {
                this.mLyricsFile.delete(it.next());
            }
            this.mLyricsDB.updateLyricsStatusForAsins(LyricsStatus.NO_LYRICS, takedownList.getAsinList());
            this.mLyricsStorage.setTakeDownsCompletedTimestamp(takedownList.getLastFetchTimestamp());
            pagingToken = takedownList.getPagingToken();
            if (pagingToken == null) {
                break;
            }
        } while (!pagingToken.isEmpty());
        this.mLyricsStorage.setLastTakedownLocalTimeInMillis(System.currentTimeMillis());
    }

    private long getLastUpdateCompletedTimestamp() {
        long updatesCompletedTimestamp = this.mLyricsStorage.getUpdatesCompletedTimestamp();
        return updatesCompletedTimestamp == 0 ? this.mLyricsStorage.getLastFullDownloadLocalTimeInMillis() : updatesCompletedTimestamp;
    }

    private long getLastTakeDownsCompletedTimestamp() {
        long takeDownsCompletedTimestamp = this.mLyricsStorage.getTakeDownsCompletedTimestamp();
        return takeDownsCompletedTimestamp == 0 ? this.mLyricsStorage.getLastFullDownloadLocalTimeInMillis() : takeDownsCompletedTimestamp;
    }

    private boolean shouldCheckForTakedowns() {
        return shouldCheckForUpdate(System.currentTimeMillis(), this.mLyricsStorage.getLastTakedownLocalTimeInMillis(), 86400000L);
    }

    private boolean shouldCheckForRefresh() {
        return shouldCheckForUpdate(System.currentTimeMillis(), this.mLyricsStorage.getLastRefreshLocalTimeInMillis(), 604800000L);
    }

    private void removeLyricsNotInList(Set<LyricsTrackInfo> infoSet) {
        this.mLyricsFile.removeLyricsNotInList(infoSet);
    }

    public static void removeAllLyrics(Context context) {
        final Context applicationContext = context.getApplicationContext();
        new Thread("Lyrics delete") { // from class: com.amazon.mp3.lyrics.LyricsManager.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                new LyricsFile(applicationContext).removeLyricsNotInList(null);
            }
        }.start();
    }
}
