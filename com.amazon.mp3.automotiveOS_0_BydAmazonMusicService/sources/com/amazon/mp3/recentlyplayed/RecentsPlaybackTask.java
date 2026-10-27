package com.amazon.mp3.recentlyplayed;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.mp3.external.api.uri.FindUriByAsinJob;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.external.api.uri.QueryArtistByAsin;
import com.amazon.mp3.external.api.uri.QueryPlaylistByAsin;
import com.amazon.mp3.external.api.uri.QueryPlaylistByLuid;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPlaybackComponent;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;

/* JADX INFO: loaded from: classes4.dex */
public class RecentsPlaybackTask extends AsyncTask<Void, Void, Integer> {
    private Context mContext;
    private RecentlyPlayedItem mPlayedItem;
    private final long mStartTime = Clock.now();
    private int mStartingTrackPosition;
    private Task mTask;

    public enum Task {
        NONE,
        PLAYBACK,
        PLAYBACK_FULLSCREEN
    }

    public static RecentsPlaybackTask createPlaybackTask(final Context context, final RecentlyPlayedItem item, final boolean isFullScreen) {
        return new RecentsPlaybackTask(context, item, isFullScreen ? Task.PLAYBACK_FULLSCREEN : Task.PLAYBACK);
    }

    public RecentsPlaybackTask(final Context context, final RecentlyPlayedItem item, final Task task) {
        this.mContext = context;
        this.mPlayedItem = item;
        this.mTask = task;
    }

    @Override // android.os.AsyncTask
    public Integer doInBackground(Void... params) {
        FindUriByAsinJob.QueryEntityById queryEntityById = getQueryEntityById();
        if (queryEntityById != null) {
            Uri contentUri = this.mPlayedItem.getContentUri();
            if (!TextUtils.isEmpty(this.mPlayedItem.getTrackLuid())) {
                int iFindTrackIndexInCollectionByLuid = queryEntityById.findTrackIndexInCollectionByLuid(queryEntityById.convertCollectionUriToTracksUri(contentUri), this.mPlayedItem.getTrackLuid());
                this.mStartingTrackPosition = iFindTrackIndexInCollectionByLuid;
                if (iFindTrackIndexInCollectionByLuid < 0) {
                    this.mStartingTrackPosition = 0;
                }
            }
            return 1;
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Integer integer) {
        Uri tracksUri;
        if (integer.intValue() != 1 || (tracksUri = getTracksUri()) == null) {
            return;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackTask$Task[this.mTask.ordinal()];
        if (i == 1) {
            ContextMenuPlaybackComponent.startPlayback(this.mContext, tracksUri, this.mStartingTrackPosition, null, null, PlaybackPageType.RECENTLY_PLAYED, this.mStartTime, true, false, this.mPlayedItem.getContentUri());
        } else {
            if (i != 2) {
                return;
            }
            ContextMenuPlaybackComponent.startPlayback(this.mContext, tracksUri, this.mStartingTrackPosition, null, null, PlaybackPageType.RECENTLY_PLAYED, this.mStartTime, false, false, this.mPlayedItem.getContentUri());
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.recentlyplayed.RecentsPlaybackTask$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackTask$Task;

        static {
            int[] iArr = new int[Task.values().length];
            $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackTask$Task = iArr;
            try {
                iArr[Task.PLAYBACK_FULLSCREEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentsPlaybackTask$Task[Task.PLAYBACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Nullable
    private Uri getTracksUri() {
        RecentlyPlayedItem recentlyPlayedItem = this.mPlayedItem;
        if (recentlyPlayedItem == null || recentlyPlayedItem.getContentUri() == null) {
            return null;
        }
        return this.mPlayedItem.getContentUri().buildUpon().appendEncodedPath("tracks").build();
    }

    @Nullable
    private FindUriByAsinJob.QueryEntityById getQueryEntityById() {
        RecentlyPlayedItem recentlyPlayedItem = this.mPlayedItem;
        if (recentlyPlayedItem == null) {
            return null;
        }
        RecentlyPlayedItem.Type itemType = recentlyPlayedItem.getItemType();
        if (itemType.equals(RecentlyPlayedItem.Type.ALBUM)) {
            return new QueryAlbumByAsin(this.mContext);
        }
        if (itemType.equals(RecentlyPlayedItem.Type.PRIME_PLAYLIST) || itemType.equals(RecentlyPlayedItem.Type.SMART_PLAYLIST)) {
            return new QueryPlaylistByAsin(this.mContext);
        }
        if (itemType.equals(RecentlyPlayedItem.Type.PLAYLIST)) {
            return new QueryPlaylistByLuid(this.mContext);
        }
        if (itemType.equals(RecentlyPlayedItem.Type.ARTIST)) {
            return new QueryArtistByAsin(this.mContext);
        }
        return new QuerySongByAsin(this.mContext);
    }
}
