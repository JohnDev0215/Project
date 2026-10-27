package com.amazon.mp3.prime;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPlaybackComponent;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeArtistPlaybackTask extends AsyncTask<Void, Void, Integer> {
    private static final String TAG = PrimeArtistPlaybackTask.class.getSimpleName();
    private Context mContext;
    private PrimeArtist mPrimeArtist;
    List<PrimeTrack> mPrimeTracks;
    private int mStartingTrackPosition;
    private Task mTask;
    private final SQLiteDatabase mWritableDb;
    private ControlSource mControlSource = ControlSource.APP_UI;
    private final long mStartTime = Clock.now();

    public enum Task {
        NONE,
        PLAYBACK,
        PLAYBACK_SHUFFLE,
        PLAYBACK_FULLSCREEN
    }

    public static PrimeArtistPlaybackTask createPlaybackTask(final Context context, final PrimeArtist artist, final List<PrimeTrack> primeTracks, final Task playbackTask, ControlSource controlSource) {
        PrimeArtistPlaybackTask primeArtistPlaybackTask = new PrimeArtistPlaybackTask(context, artist, primeTracks, 0, playbackTask);
        primeArtistPlaybackTask.setControlSource(controlSource);
        return primeArtistPlaybackTask;
    }

    public PrimeArtistPlaybackTask(final Context context, final PrimeArtist artist, final List<PrimeTrack> primeTracks, final int startingTrackPosition, final Task task) {
        this.mContext = context;
        this.mPrimeArtist = artist;
        this.mPrimeTracks = primeTracks;
        this.mStartingTrackPosition = startingTrackPosition;
        this.mTask = task;
        this.mWritableDb = CirrusDatabase.getWritableDatabase(context);
    }

    @Override // android.os.AsyncTask
    public Integer doInBackground(Void... params) {
        deleteTracks();
        for (PrimeTrack primeTrack : this.mPrimeTracks) {
            insertTrackIntoCTAPrimeCache(primeTrack);
            insertTrackIntoPrimeArtist(primeTrack);
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    @SuppressLint({"CheckResult"})
    public void onPostExecute(Integer integer) {
        if (integer.intValue() == 1) {
            if (AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
                Task task = this.mTask;
                Task task2 = Task.PLAYBACK_SHUFFLE;
                boolean z = task == task2 || task == Task.PLAYBACK_FULLSCREEN;
                ArrayList arrayList = new ArrayList();
                if (this.mPrimeArtist.getAsin() != null) {
                    arrayList.add(this.mPrimeArtist.getAsin());
                }
                StartPlaybackUtil.startCollectionPlayback(this.mContext, this.mPrimeArtist.getTitle(), getContentUri(), this.mPrimeTracks, 0, this.mTask == task2, z, null, PlaybackPageType.ARTIST_DETAIL, false, this.mControlSource, null, false, null, null, false, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.prime.PrimeArtistPlaybackTask.1
                    @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                    public void handlePlayback() {
                        PrimeArtistPlaybackTask.this.startPlaybackHelper();
                    }
                });
                return;
            }
            startPlaybackHelper();
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.PrimeArtistPlaybackTask$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$PrimeArtistPlaybackTask$Task;

        static {
            int[] iArr = new int[Task.values().length];
            $SwitchMap$com$amazon$mp3$prime$PrimeArtistPlaybackTask$Task = iArr;
            try {
                iArr[Task.PLAYBACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$PrimeArtistPlaybackTask$Task[Task.PLAYBACK_SHUFFLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$PrimeArtistPlaybackTask$Task[Task.PLAYBACK_FULLSCREEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlaybackHelper() {
        int i = AnonymousClass2.$SwitchMap$com$amazon$mp3$prime$PrimeArtistPlaybackTask$Task[this.mTask.ordinal()];
        if (i == 1) {
            ContextMenuPlaybackComponent.startPlayback(this.mContext, getContentUri(), this.mStartingTrackPosition, null, null, PlaybackPageType.ARTIST_DETAIL, this.mStartTime, false, false, getContentUri());
        } else if (i == 2) {
            ContextMenuPlaybackComponent.startPlayback(this.mContext, getContentUri(), this.mStartingTrackPosition, null, null, PlaybackPageType.ARTIST_DETAIL, this.mStartTime, true, true, false, getContentUri());
        } else {
            if (i != 3) {
                return;
            }
            ContextMenuPlaybackComponent.startPlayback(this.mContext, getContentUri(), this.mStartingTrackPosition, null, null, PlaybackPageType.ARTIST_DETAIL, this.mStartTime, true, false, getContentUri());
        }
    }

    private void deleteTracks() {
        this.mWritableDb.delete("PrimeArtistTracks", "prime_artist_asin=?", new String[]{this.mPrimeArtist.getAsin()});
    }

    private int insertTrackIntoCTAPrimeCache(final PrimeTrack primeTrack) {
        try {
            return new CTAPrimeCache(this.mContext).insertPrimeTracks(primeTrack.getAlbumAsin(), primeTrack.getAlbum(), primeTrack.getAlbumArtist(), Collections.singletonList(primeTrack));
        } catch (SQLiteDatabaseLockedException e) {
            Log.error(TAG, "Failed to insert track to Db due to SQLiteDatabaseLockedException with message: " + e.getMessage());
            return 0;
        }
    }

    private long insertTrackIntoPrimeArtist(final PrimeTrack primeTrack) {
        ContentValues primeArtistContentValues = toPrimeArtistContentValues(primeTrack);
        this.mWritableDb.beginTransaction();
        try {
            long jInsert = this.mWritableDb.insert("PrimeArtistTracks", null, primeArtistContentValues);
            this.mWritableDb.setTransactionSuccessful();
            return jInsert;
        } finally {
            this.mWritableDb.endTransaction();
        }
    }

    private ContentValues toPrimeArtistContentValues(final PrimeTrack track) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("track_asin", TextUtils.isEmpty(track.getAsin()) ? null : track.getAsin());
        contentValues.put("track_luid", TextUtils.isEmpty(track.getLuid()) ? null : track.getLuid());
        contentValues.put("prime_artist_id", Long.valueOf(getArtistId()));
        contentValues.put("prime_artist_asin", this.mPrimeArtist.getAsin());
        return contentValues;
    }

    private Uri getContentUri() {
        return MediaProvider.PrimeArtists.getContentUri("cirrus", IdGenerator.generateArtistId(this.mPrimeArtist.getTitle()));
    }

    private long getArtistId() {
        return IdGenerator.generateArtistId(this.mPrimeArtist.getTitle());
    }

    void setControlSource(ControlSource controlSource) {
        this.mControlSource = controlSource;
    }
}
