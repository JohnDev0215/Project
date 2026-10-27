package com.amazon.mp3.library.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.FragmentTabActivity;
import com.amazon.mp3.fragment.FragmentTabHost;
import com.amazon.mp3.library.fragment.LibraryFragmentFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.service.sync.PlaylistSyncHelper;
import com.amazon.mp3.library.util.LibraryFilters;
import com.amazon.mp3.library.util.PlaylistMgmt;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.music.curate.data.PlaylistIntentReceiverService;
import java.util.Hashtable;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class AddToPlaylistTabActivity extends FragmentTabActivity implements PlaylistMgmt.OnPlaylistMgmt {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final String TAG = "AddToPlaylistTabActivity";
    private boolean mIsLocal;
    private String mPlaylistDuration;
    private TextView mPlaylistName;
    private TextView mPlaylistTrackCount;
    private Uri mPlaylistUri;
    private boolean mSaveOnDone;
    private String mSourceId;
    private final int mFilterId = LibraryFilters.createFilterId();
    private long mUpdateSongsCountAndDurationJobId = -1;
    private String mSongsCountAndDuration = null;
    private final View.OnClickListener mOnDoneClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.activity.AddToPlaylistTabActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (AddToPlaylistTabActivity.this.savePlaylistIfChanged()) {
                return;
            }
            AddToPlaylistTabActivity.this.finish();
        }
    };

    @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
    public Cursor onAddAllTracksCursor() {
        return null;
    }

    @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
    public void onAddAllTracksCursorFinish() {
    }

    @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
    public long onAddJob(Job job) {
        return 0L;
    }

    @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
    public void onPlaylistChangedObserver() {
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onSearchRequested() {
        return false;
    }

    @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
    public boolean onShuffleExist() {
        return false;
    }

    @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
    public void onUpdateCachePlaylist(Hashtable<String, PlaylistUtil.PlaylistTrack> cachePlaylist) {
    }

    public static Intent getStartIntent(final Context context, final Uri playlistUri, final String sourceId, final boolean saveOnDone) {
        Intent intent = new Intent(context, (Class<?>) AddToPlaylistTabActivity.class);
        intent.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", playlistUri);
        intent.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", sourceId);
        intent.putExtra("com.amazon.mp3.library.EXTRA_SHOULD_SAVE", saveOnDone);
        return intent;
    }

    @Override // com.amazon.mp3.fragment.FragmentTabActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        FragmentTabHost tabHost = getTabHost();
        tabHost.setLeftAligned(true);
        ViewStub viewStub = (ViewStub) findViewById(R.id.PlaylistHeaderViewStub);
        if (viewStub != null) {
            viewStub.setLayoutResource(R.layout.library_add_to_playlist_header);
            View viewFindViewById = viewStub.inflate().findViewById(R.id.HeaderBarFilter);
            if (viewFindViewById != null) {
                viewFindViewById.setVisibility(8);
            }
            this.mPlaylistName = (TextView) findViewById(R.id.PlaylistName);
            this.mPlaylistTrackCount = (TextView) findViewById(R.id.TrackCount);
            findViewById(R.id.DoneButton).setOnClickListener(this.mOnDoneClickListener);
        }
        checkAndModifyPlaylist();
        String source = MediaProvider.getSource(this.mPlaylistUri);
        this.mSourceId = source;
        this.mIsLocal = "cirrus-local".equals(source);
        if (savedInstanceState == null) {
            PlaylistUtil.clearMutatedFlag(this);
            for (int i = 0; i < 5; i++) {
                if (i == 0) {
                    tabHost.addTab(null, R.string.dmusic_library_playlists_tab);
                } else if (i == 1) {
                    tabHost.addTab(null, R.string.dmusic_library_artists_tab);
                } else if (i == 2) {
                    tabHost.addTab(null, R.string.dmusic_library_albums_tab);
                } else if (i == 3) {
                    tabHost.addTab(null, R.string.dmusic_library_songs_tab);
                } else if (i == 4) {
                    tabHost.addTab(null, R.string.dmusic_library_genres_tab);
                }
            }
            Uri uri = this.mPlaylistUri;
            if (uri != null && CirrusMediaSource.match(uri)) {
                this.mPlaylistUri = PlaylistUtil.withAppendedScratchArgument(this.mPlaylistUri);
            }
            tabHost.setSelectedTab(3);
        }
        updatePlaylistInfo();
    }

    private void handleIntent(final Intent intent) {
        String string;
        if (intent == null || intent.getExtras() == null) {
            string = null;
        } else {
            this.mPlaylistUri = (Uri) intent.getParcelableExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI");
            this.mSaveOnDone = intent.getBooleanExtra("com.amazon.mp3.library.EXTRA_SHOULD_SAVE", true);
            string = intent.getExtras().getString("com.amazon.mp3.library.EXTRA_SOURCE_ID");
        }
        if (string == null) {
            string = ConnectivityUtil.allRadiosOff() ? "cirrus-local" : "cirrus";
            if (intent != null) {
                intent.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", string);
            }
        }
        if ("cirrus".equals(string) && !AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            string = "cirrus-local";
        }
        this.mSourceId = string;
        this.mIsLocal = "cirrus-local".equals(string);
    }

    @Override // com.amazon.mp3.fragment.FragmentTabActivity
    public Fragment lazyCreateFragmentForTab(int tabId) {
        if (tabId == 0) {
            Fragment fragment = LibraryFragmentFactory.getFragment(this, MediaProvider.Playlists.getFilterContentUri(this.mSourceId, "").buildUpon().appendQueryParameter("playlist_edit_mode", "true").build(), this.mPlaylistUri);
            Bundle arguments = fragment.getArguments();
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_PLAYLIST_URI", this.mPlaylistUri);
            arguments.putInt("com.amazon.mp3.EXTRA_LIBRARY_FILTER_ID", this.mFilterId);
            return fragment;
        }
        if (tabId == 1) {
            Fragment fragment2 = LibraryFragmentFactory.getFragment(this, MediaProvider.Artists.getFilterContentUri(this.mSourceId, ""));
            Bundle arguments2 = fragment2.getArguments();
            arguments2.putParcelable("com.amazon.mp3.library.EXTRA_PLAYLIST_URI", this.mPlaylistUri);
            arguments2.putInt("com.amazon.mp3.EXTRA_LIBRARY_FILTER_ID", this.mFilterId);
            return fragment2;
        }
        if (tabId == 2) {
            Fragment fragment3 = LibraryFragmentFactory.getFragment(this, MediaProvider.Albums.getFilterContentUri(this.mSourceId, ""));
            Bundle arguments3 = fragment3.getArguments();
            arguments3.putParcelable("com.amazon.mp3.library.EXTRA_PLAYLIST_URI", this.mPlaylistUri);
            arguments3.putInt("com.amazon.mp3.EXTRA_LIBRARY_FILTER_ID", this.mFilterId);
            return fragment3;
        }
        if (tabId == 3) {
            Fragment fragment4 = LibraryFragmentFactory.getFragment(this, this.mPlaylistUri);
            Bundle arguments4 = fragment4.getArguments();
            arguments4.putParcelable("com.amazon.mp3.library.EXTRA_PLAYLIST_URI", this.mPlaylistUri);
            arguments4.putInt("com.amazon.mp3.EXTRA_LIBRARY_FILTER_ID", this.mFilterId);
            return fragment4;
        }
        if (tabId != 4) {
            return null;
        }
        Fragment fragment5 = LibraryFragmentFactory.getFragment(this, MediaProvider.Genres.getFilterContentUri(this.mSourceId, ""));
        Bundle arguments5 = fragment5.getArguments();
        arguments5.putParcelable("com.amazon.mp3.library.EXTRA_PLAYLIST_URI", this.mPlaylistUri);
        arguments5.putInt("com.amazon.mp3.EXTRA_LIBRARY_FILTER_ID", this.mFilterId);
        return fragment5;
    }

    private void checkAndModifyPlaylist() {
        if ("tracks".equals(this.mPlaylistUri.getLastPathSegment())) {
            List<String> pathSegments = this.mPlaylistUri.getPathSegments();
            String authority = this.mPlaylistUri.getAuthority();
            String encodedQuery = this.mPlaylistUri.getEncodedQuery();
            String scheme = this.mPlaylistUri.getScheme();
            Uri.Builder builder = new Uri.Builder();
            builder.scheme(scheme);
            builder.authority(authority);
            for (String str : pathSegments) {
                if ("tracks".equals(str)) {
                    break;
                } else {
                    builder.appendPath(str);
                }
            }
            builder.encodedQuery(encodedQuery);
            this.mPlaylistUri = builder.build();
        }
        boolean zEquals = "true".equals(this.mPlaylistUri.getQueryParameter("scratch"));
        if (!CirrusMediaSource.match(this.mPlaylistUri) || zEquals) {
            return;
        }
        this.mPlaylistUri = PlaylistUtil.withAppendedScratchArgument(this.mPlaylistUri);
    }

    private void updateSongsCountAndDuration() {
        if (getTabHost().getSelectedTab() == 3 || this.mUpdateSongsCountAndDurationJobId != -1 || ((TextView) findViewById(R.id.TrackCount)) == null) {
            return;
        }
        this.mUpdateSongsCountAndDurationJobId = addJob(new UpdateSongsCountAndDurationJob());
    }

    private class UpdateSongsCountAndDurationJob extends Job {
        private UpdateSongsCountAndDurationJob() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            if (AddToPlaylistTabActivity.this.getTabHost().getSelectedTab() == 3) {
                return 0;
            }
            boolean zMatch = CirrusMediaSource.match(AddToPlaylistTabActivity.this.mPlaylistUri);
            AddToPlaylistTabActivity addToPlaylistTabActivity = AddToPlaylistTabActivity.this;
            PlaylistUtil.PlaylistTrackInfo playlistTrackInfoCreatePlaylistInfo = PlaylistUtil.createPlaylistInfo(addToPlaylistTabActivity, addToPlaylistTabActivity.mPlaylistUri, zMatch);
            AddToPlaylistTabActivity addToPlaylistTabActivity2 = AddToPlaylistTabActivity.this;
            addToPlaylistTabActivity2.mSongsCountAndDuration = PlaylistUtil.calculateSongsDuration(addToPlaylistTabActivity2, playlistTrackInfoCreatePlaylistInfo.mTrackCount, playlistTrackInfoCreatePlaylistInfo.mCalcDuration);
            return 0;
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        if (this.mUpdateSongsCountAndDurationJobId == jobId) {
            this.mUpdateSongsCountAndDurationJobId = -1L;
            TextView textView = (TextView) findViewById(R.id.TrackCount);
            if (textView != null) {
                if (this.mSongsCountAndDuration == null) {
                    this.mSongsCountAndDuration = PlaylistUtil.calculateSongsDuration(this, 0, 0);
                }
                textView.setText(this.mSongsCountAndDuration);
                return;
            }
            return;
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.fragment.FragmentTabActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        initializeAlexa();
        updateSongsCountAndDuration();
        if (this.mIsLocal || !AccountCredentialUtil.get(this).isSignedOut()) {
            return;
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (handleBackButtonForAlexa() || savePlaylistIfChanged()) {
            return;
        }
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean savePlaylistIfChanged() {
        boolean z;
        final Context applicationContext = getApplicationContext();
        if (this.mPlaylistUri != null && this.mSaveOnDone && PlaylistUtil.getMutatedFlag(this)) {
            startActivityForResult(SavePlaylistActivity.getStartIntent(this, this.mPlaylistUri), SavePlaylistActivity.REQUEST_CODE);
            Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.activity.AddToPlaylistTabActivity$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$savePlaylistIfChanged$0(applicationContext, (Subscriber) obj);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.library.activity.AddToPlaylistTabActivity$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$savePlaylistIfChanged$1(applicationContext, (String) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.library.activity.AddToPlaylistTabActivity$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    AddToPlaylistTabActivity.lambda$savePlaylistIfChanged$2((Throwable) obj);
                }
            });
            z = true;
        } else {
            z = false;
        }
        if (this.mIsLocal) {
            if (z) {
                PlaylistUtil.setUpdatedSinceLastSync(applicationContext, MediaProvider.Playlists.getPlaylistId(this.mPlaylistUri), true);
            } else {
                PlaylistSyncHelper.syncUnifiedPlaylistsToCloud(applicationContext);
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$savePlaylistIfChanged$0(Context context, Subscriber subscriber) {
        subscriber.onNext(PlaylistUtil.getPlaylistLuid(context, this.mPlaylistUri));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$savePlaylistIfChanged$1(Context context, String str) {
        if (StringUtils.isNotBlank(str)) {
            PlaylistIntentReceiverService.processUpdatePlaylist(context, str);
            return;
        }
        String str2 = TAG;
        Object[] objArr = new Object[1];
        Uri uri = this.mPlaylistUri;
        objArr[0] = uri != null ? uri.toString() : "null";
        Log.warning(str2, "Luid not found for playlist: %s", objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$savePlaylistIfChanged$2(Throwable th) {
        Log.error(TAG, "Error when save playlist", th.getMessage());
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SavePlaylistActivity.REQUEST_CODE) {
            PlaylistUtil.clearMutatedFlag(this);
            setResult(resultCode);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDuration() {
        if (this.mPlaylistDuration == null) {
            this.mPlaylistDuration = PlaylistUtil.calculateSongsDuration(this, 0, 0);
        }
        return this.mPlaylistDuration;
    }

    private void setPlaylistDuration(String duration) {
        this.mPlaylistDuration = duration;
    }

    private class UpdatePlaylistInfoTask extends AsyncTask<Void, Void, Void> {
        private String mTitle;

        private UpdatePlaylistInfoTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            AddToPlaylistTabActivity addToPlaylistTabActivity = AddToPlaylistTabActivity.this;
            this.mTitle = addToPlaylistTabActivity.getString(R.string.dmusic_title_add_to_playlist, PlaylistUtil.getPlaylistName(addToPlaylistTabActivity, addToPlaylistTabActivity.mPlaylistUri));
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void result) {
            String duration = AddToPlaylistTabActivity.this.getDuration();
            if (!AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
                AddToPlaylistTabActivity.this.mPlaylistName.setText(this.mTitle);
                AddToPlaylistTabActivity.this.mPlaylistTrackCount.setText(duration);
            } else {
                BauhausTextStyler bauhausTextStyler = new BauhausTextStyler();
                AddToPlaylistTabActivity.this.mPlaylistName.setText(bauhausTextStyler.styleText(this.mTitle));
                AddToPlaylistTabActivity.this.mPlaylistTrackCount.setText(bauhausTextStyler.styleText(duration));
            }
        }
    }

    @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
    public void onCachePlaylistJobFinish() {
        updatePlaylistInfo();
    }

    @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
    public void onSetDuration(String duration) {
        setPlaylistDuration(duration);
    }

    @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
    public void onAddAllTracksJobFinish() {
        updatePlaylistInfo();
    }

    private void updatePlaylistInfo() {
        new UpdatePlaylistInfoTask().execute(new Void[0]);
    }
}
