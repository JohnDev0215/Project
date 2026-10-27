package com.amazon.mp3.external.api.uri;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.external.api.uri.FindUriByAsinJob;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class SyncAndPlayActivity extends DialogActivity {
    public static final String INTENT_EXTRA_ASIN;
    public static final String INTENT_EXTRA_CONTENT_ACTION;
    public static final String INTENT_EXTRA_CONTENT_TYPE;
    public static final String INTENT_EXTRA_TRACK_ASIN;
    private static final String TAG = "SyncAndPlayActivity";
    private ContentType mContentType;
    private long mSyncFindJobId = -1;
    private ContentAction mContentAction = ContentAction.VIEW;
    private final long mStartTime = Clock.now();

    public enum ContentAction {
        PLAY,
        PLAY_NOW_PLAYING,
        PLAY_DETAIL,
        VIEW
    }

    public enum ContentType {
        TRACK,
        ALBUM,
        PLAYLIST
    }

    static {
        String name = SyncAndPlayActivity.class.getName();
        INTENT_EXTRA_ASIN = name + ".extra.ASIN";
        INTENT_EXTRA_CONTENT_TYPE = name + ".extra.CONTENT_TYPE";
        INTENT_EXTRA_CONTENT_ACTION = name + ".extra.CONTENT_ACTION";
        INTENT_EXTRA_TRACK_ASIN = name + ".extra.TRACK_ASIN";
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, (Class<?>) SyncAndPlayActivity.class);
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        ContentAction contentAction;
        FindUriByAsinJob.QueryByAsin querySongByAsin;
        FindUriByAsinJob findUriByAsinJob;
        super.onCreate(savedInstanceState);
        if (this.mSyncFindJobId != -1) {
            return;
        }
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(INTENT_EXTRA_ASIN);
        if (stringExtra == null) {
            onError();
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra(INTENT_EXTRA_CONTENT_TYPE);
        if (serializableExtra instanceof ContentType) {
            ContentType contentType = (ContentType) serializableExtra;
            this.mContentType = contentType;
            Serializable serializableExtra2 = intent.getSerializableExtra(INTENT_EXTRA_CONTENT_ACTION);
            if (serializableExtra2 instanceof ContentAction) {
                contentAction = (ContentAction) serializableExtra2;
            } else {
                Log.warning(TAG, "Passed in an invalid type for the content action");
                contentAction = ContentAction.VIEW;
            }
            this.mContentAction = contentAction;
            int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$external$api$uri$SyncAndPlayActivity$ContentType[contentType.ordinal()];
            if (i == 1) {
                querySongByAsin = new QuerySongByAsin(this);
            } else if (i == 2) {
                querySongByAsin = new QueryAlbumByAsin(this);
            } else if (i == 3) {
                querySongByAsin = new QueryPlaylistByAsin(this);
            } else {
                onError();
                return;
            }
            FindUriByAsinJob.QueryByAsin queryByAsin = querySongByAsin;
            String stringExtra2 = intent.getStringExtra(INTENT_EXTRA_TRACK_ASIN);
            if (TextUtils.isEmpty(stringExtra2)) {
                findUriByAsinJob = new FindUriByAsinJob(queryByAsin, stringExtra, 15L);
            } else {
                findUriByAsinJob = new FindUriByAsinJob(queryByAsin, stringExtra, stringExtra2, 15L);
            }
            this.mSyncFindJobId = addJob(findUriByAsinJob);
            return;
        }
        onError();
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        Dialog dialog = new Dialog(this, R.style.Theme_Dialog_windowNoTitle_progressbar);
        dialog.setContentView(R.layout.common_progress_spinner);
        dialog.setCancelable(false);
        TextView textView = (TextView) dialog.findViewById(R.id.message);
        textView.setText(R.string.dmusic_status_loading);
        textView.setVisibility(0);
        return dialog;
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    protected void onJobFinished(final long jobId, final Job job, final int resultCode, final Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        if (this.mSyncFindJobId == jobId) {
            if (resultCode == 1 && resultBundle != null) {
                onSuccess(resultBundle);
            } else {
                onError();
            }
        }
    }

    private void onError() {
        startActivity(LibraryActivityFactory.getIntentForContentUri(this, MediaProvider.SmartPlaylists.getContentUri("cirrus", 1L)));
        finish();
    }

    private void onSuccess(Bundle successfulResultBundle) {
        Uri uri = (Uri) successfulResultBundle.getParcelable(FindUriByAsinJob.RESULT_COLLECTION_URI);
        if (uri == null) {
            onError();
            return;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$external$api$uri$SyncAndPlayActivity$ContentAction[this.mContentAction.ordinal()];
        if (i == 1) {
            startPlayback(successfulResultBundle);
        } else if (i == 2) {
            startPlayback(successfulResultBundle);
            startActivity(LibraryActivityFactory.getIntentForContentUri(this, uri));
        } else if (i != 3) {
            if (i == 4) {
                startActivity(LibraryActivityFactory.getIntentForContentUri(this, uri));
            }
        } else if (startPlayback(successfulResultBundle)) {
            startActivity(LibraryActivityFactory.getIntentForContentUri(this, MediaProvider.NowPlaying.CONTENT_URI));
        } else {
            startActivity(LibraryActivityFactory.getIntentForContentUri(this, uri));
        }
        finish();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.external.api.uri.SyncAndPlayActivity$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$external$api$uri$SyncAndPlayActivity$ContentAction;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$external$api$uri$SyncAndPlayActivity$ContentType;

        static {
            int[] iArr = new int[ContentAction.values().length];
            $SwitchMap$com$amazon$mp3$external$api$uri$SyncAndPlayActivity$ContentAction = iArr;
            try {
                iArr[ContentAction.PLAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$external$api$uri$SyncAndPlayActivity$ContentAction[ContentAction.PLAY_DETAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$external$api$uri$SyncAndPlayActivity$ContentAction[ContentAction.PLAY_NOW_PLAYING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$external$api$uri$SyncAndPlayActivity$ContentAction[ContentAction.VIEW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ContentType.values().length];
            $SwitchMap$com$amazon$mp3$external$api$uri$SyncAndPlayActivity$ContentType = iArr2;
            try {
                iArr2[ContentType.TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$external$api$uri$SyncAndPlayActivity$ContentType[ContentType.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$external$api$uri$SyncAndPlayActivity$ContentType[ContentType.PLAYLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private boolean startPlayback(Bundle successfulResultBundle) {
        Uri uri = (Uri) successfulResultBundle.getParcelable(FindUriByAsinJob.RESULT_PLAYABLE_URI);
        if (uri != null) {
            String str = FindUriByAsinJob.RESULT_START_TRACK_INDEX;
            int i = successfulResultBundle.containsKey(str) ? successfulResultBundle.getInt(str, -1) : 0;
            if (i >= 0) {
                PlayQueueSequencer.getInstance().start(ControlSource.APP_UI, uri, null, null, null, i, false, new PlaybackMetricInformation(ContentType.TRACK.equals(this.mContentType) ? DirectedPlayStatus.DIRECTED : DirectedPlayStatus.NOT_DIRECTED, PlaybackPageType.MOBILE_WEB), this.mStartTime);
                return true;
            }
        }
        return false;
    }
}
