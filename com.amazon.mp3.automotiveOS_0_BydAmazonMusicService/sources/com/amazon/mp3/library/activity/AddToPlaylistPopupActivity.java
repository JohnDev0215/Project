package com.amazon.mp3.library.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.util.AddToPlaylistManager;
import com.amazon.mp3.library.util.CreatePlaylistTask;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.browse.GetAlbumTracksAndAddJob;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ListViewUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class AddToPlaylistPopupActivity extends LibraryBaseActivity {
    private static final String TAG = "AddToPlaylistPopupActivity";
    private static long sLastRequestId = -1;
    private static long sNextRequestId;
    private Subscription cursorSubscription;
    private AddToPlaylistManager mAddToPlaylistManager;
    private String mAlbumAsin;
    private Uri mCurrentlyAddingPlaylist;
    private PageType mPageType;
    private List<Playlist> mPlaylists;
    private boolean mPrimeCollection;
    private String mSortOrder;
    private String mSourceId;
    private Uri mTracksUri;
    private Long mJobId = -1L;
    private long mAddTracksJobId = -1;
    private long mRequestId = -1;
    private final AddToPlaylistManager.AddToPlaylistListener mAddToPlaylistListener = new AddToPlaylistManager.AddToPlaylistListener() { // from class: com.amazon.mp3.library.activity.AddToPlaylistPopupActivity.1
        @Override // com.amazon.mp3.library.util.AddToPlaylistManager.AddToPlaylistListener
        public void onSuccess() {
            AddToPlaylistPopupActivity.this.hideProgressDialog();
            AddToPlaylistPopupActivity.this.finish();
            AddToPlaylistPopupActivity.setLastRequestId(AddToPlaylistPopupActivity.this.mRequestId);
        }

        @Override // com.amazon.mp3.library.util.AddToPlaylistManager.AddToPlaylistListener
        public void onError(final AddToPlaylistManager.ErrorReason errorCode) {
            AddToPlaylistPopupActivity.this.hideProgressDialog();
            int i = AnonymousClass7.$SwitchMap$com$amazon$mp3$library$util$AddToPlaylistManager$ErrorReason[errorCode.ordinal()];
            if (i == 1) {
                AddToPlaylistPopupActivity addToPlaylistPopupActivity = AddToPlaylistPopupActivity.this;
                BauhausToastUtils.showDefaultToast(addToPlaylistPopupActivity, addToPlaylistPopupActivity.getDuplicateTracksMessage(0), 0);
            } else if (i == 2) {
                AddToPlaylistPopupActivity addToPlaylistPopupActivity2 = AddToPlaylistPopupActivity.this;
                addToPlaylistPopupActivity2.startActivity(DialogActivity.getStartIntent(addToPlaylistPopupActivity2, R.string.dmusic_playlist_edit_max_size_title, R.string.dmusic_playlist_edit_max_size_desc));
            } else if (i == 3 || i == 4) {
                AddToPlaylistPopupActivity.this.startActivity(AllAccessPlaylistLimitReachedActivity.getStartIntent(errorCode.toString()));
            }
            AddToPlaylistPopupActivity.this.finish();
            AddToPlaylistPopupActivity.setLastRequestId(AddToPlaylistPopupActivity.this.mRequestId);
        }
    };
    private final AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.amazon.mp3.library.activity.AddToPlaylistPopupActivity.6
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (id != -1) {
                AddToPlaylistPopupActivity.this.addToCirrusPlaylist(MediaProvider.UdoPlaylistTracks.getContentUri(AddToPlaylistPopupActivity.this.mSourceId, Long.parseLong(((Playlist) AddToPlaylistPopupActivity.this.mPlaylists.get((int) id)).getId())));
                AddToPlaylistPopupActivity.this.sendUiClickMetrics(ActionType.ADD_TO_PLAYLIST_OVERFLOW);
                return;
            }
            if (AddToPlaylistPopupActivity.this.mJobId.longValue() == -1) {
                AddToPlaylistPopupActivity addToPlaylistPopupActivity = AddToPlaylistPopupActivity.this;
                if (PlaylistUtil.maxNumberOfPlaylistsReached(addToPlaylistPopupActivity)) {
                    AddToPlaylistPopupActivity.this.startActivity(NumberOfPlaylistsLimitReachedActivity.getStartIntent());
                    return;
                }
                AddToPlaylistPopupActivity addToPlaylistPopupActivity2 = AddToPlaylistPopupActivity.this;
                addToPlaylistPopupActivity2.startActivityForResult(CreateOrRenamePlaylistActivity.getStartIntentForCreate(addToPlaylistPopupActivity, addToPlaylistPopupActivity2.mPageType), CreateOrRenamePlaylistActivity.REQUEST_CODE);
                AddToPlaylistPopupActivity.this.sendUiClickMetrics(ActionType.CREATE_PLAYLIST_ATTEMPT_OVERFLOW);
            }
        }
    };

    private class PlaylistAdapter extends ArrayAdapter<Playlist> {
        public PlaylistAdapter(Context context, List<Playlist> playlists) {
            super(context, 0, playlists);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            Playlist playlist = (Playlist) getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(AddToPlaylistPopupActivity.this).inflate(R.layout.library_popup_listview_rows, parent, false);
            }
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            if (textView != null) {
                textView.setText(playlist.getName());
            }
            return convertView;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.activity.AddToPlaylistPopupActivity$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$util$AddToPlaylistManager$ErrorReason;

        static {
            int[] iArr = new int[AddToPlaylistManager.ErrorReason.values().length];
            $SwitchMap$com$amazon$mp3$library$util$AddToPlaylistManager$ErrorReason = iArr;
            try {
                iArr[AddToPlaylistManager.ErrorReason.ITEM_ALREADY_IN_PLAYLIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$util$AddToPlaylistManager$ErrorReason[AddToPlaylistManager.ErrorReason.MAX_PLAYLIST_SIZE_REACHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$util$AddToPlaylistManager$ErrorReason[AddToPlaylistManager.ErrorReason.AAUP_PLAYLIST_TRACK_LIMIT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$util$AddToPlaylistManager$ErrorReason[AddToPlaylistManager.ErrorReason.AAUP_RESTRICTION_LIMIT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static Intent getStartIntent(Context context, Uri tracksUri, String sortOrder, String objectName, boolean primeCollection, String albumAsin, PageType pageType) {
        if (context == null) {
            throw new IllegalArgumentException("sourceId must be valid and non-null, context must be non-null");
        }
        Intent intent = new Intent(context, (Class<?>) AddToPlaylistPopupActivity.class);
        intent.putExtra("com.amazon.mp3.library.activity.EXTRA_TRACKS_CONTENT_URI", tracksUri);
        intent.putExtra("com.amazon.mp3.library.activity.EXTRA_TRACKS_SORT_ORDER", sortOrder);
        intent.putExtra("com.amazon.mp3.library.activity.EXTRA_ADD_TO_LAST_OBJECTNAME", objectName);
        long j = sNextRequestId;
        sNextRequestId = 1 + j;
        intent.putExtra("com.amazon.mp3.library.activity.EXTRA_UNIQUE_ADD_ID", j);
        intent.putExtra("com.amazon.mp3.library.activity.EXTRA_PRIME_COLLECTION", primeCollection);
        intent.putExtra("com.amazon.mp3.library.activity.EXTRA_PRIME_ALBUM_ASIN", albumAsin);
        intent.putExtra("com.amazon.mp3.library.activity.EXTRA_PAGE_TYPE", pageType);
        return intent;
    }

    public static Intent getStartIntent(Context context, Uri tracksUri, PageType pageType) {
        return getStartIntent(context, tracksUri, null, pageType);
    }

    public static Intent getStartIntent(Context context, Uri tracksUri, String sortOrder, PageType pageType) {
        return getStartIntent(context, tracksUri, sortOrder, null, false, null, pageType);
    }

    public static Intent getStartIntent(Context context, Uri tracksUri, boolean primeCollection, String albumAsin, PageType pageType) {
        return getStartIntent(context, tracksUri, null, null, primeCollection, albumAsin, pageType);
    }

    public static Intent getStartIntentForCurrentItem(Context context, PageType pageType) {
        Uri contentUri = MediaItemHelper.getContentUri(PlaybackControllerProvider.getController(ControlSource.NONE).getCurrentMediaItem());
        if (contentUri == null) {
            return null;
        }
        return getStartIntent(context, contentUri, pageType);
    }

    @Override // com.amazon.mp3.library.activity.LibraryBaseActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle instanceState) {
        requestWindowFeature(1);
        Intent intent = getIntent();
        this.mTracksUri = (Uri) intent.getParcelableExtra("com.amazon.mp3.library.activity.EXTRA_TRACKS_CONTENT_URI");
        this.mSortOrder = intent.getStringExtra("com.amazon.mp3.library.activity.EXTRA_TRACKS_SORT_ORDER");
        this.mSourceId = MediaProvider.getSource(this.mTracksUri);
        this.mRequestId = intent.getLongExtra("com.amazon.mp3.library.activity.EXTRA_UNIQUE_ADD_ID", -1L);
        this.mPrimeCollection = intent.getBooleanExtra("com.amazon.mp3.library.activity.EXTRA_PRIME_COLLECTION", false);
        this.mAlbumAsin = intent.getStringExtra("com.amazon.mp3.library.activity.EXTRA_PRIME_ALBUM_ASIN");
        this.mPageType = (PageType) intent.getSerializableExtra("com.amazon.mp3.library.activity.EXTRA_PAGE_TYPE");
        super.onCreate(instanceState);
        if (!ConnectivityUtil.hasAnyInternetConnection() && "cirrus".equals(MediaProvider.getSource(this.mTracksUri))) {
            startActivity(NetworkErrorDialogActivity.getStartIntent(this));
            finish();
            setLastRequestId(this.mRequestId);
        } else {
            setContentView(R.layout.library_popup_listview);
            this.cursorSubscription = Observable.create(new Observable.OnSubscribe<Cursor>() { // from class: com.amazon.mp3.library.activity.AddToPlaylistPopupActivity.4
                @Override // rx.functions.Action1
                public void call(final Subscriber<? super Cursor> subscriber) {
                    subscriber.onNext(AddToPlaylistPopupActivity.this.createCursor());
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Cursor>() { // from class: com.amazon.mp3.library.activity.AddToPlaylistPopupActivity.2
                @Override // rx.functions.Action1
                public void call(final Cursor cursor) {
                    AddToPlaylistPopupActivity.this.initViews(cursor);
                }
            }, new Action1<Throwable>() { // from class: com.amazon.mp3.library.activity.AddToPlaylistPopupActivity.3
                @Override // rx.functions.Action1
                public void call(final Throwable throwable) {
                    Log.error(AddToPlaylistPopupActivity.TAG, "error when creating cursor ", throwable);
                }
            });
            this.mAddToPlaylistManager = new AddToPlaylistManager(this, this.mAddToPlaylistListener);
        }
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        createSessionIfNecessary();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setLastRequestId(long requestId) {
        sLastRequestId = requestId;
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    protected void onSessionConnected(long sessionId) {
        super.onSessionConnected(sessionId);
        if (this.mRequestId == sLastRequestId) {
            finish();
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        hideProgressDialog();
        this.mJobId = -1L;
        long j = this.mAddTracksJobId;
        if (jobId == j && resultCode == 1) {
            this.mAddTracksJobId = -1L;
            addTracksToPlaylist(this.mCurrentlyAddingPlaylist);
        } else if (jobId != j && resultCode == 1) {
            finish();
            setLastRequestId(this.mRequestId);
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    @Override // com.amazon.mp3.library.activity.LibraryBaseActivity
    protected String getSourceId() {
        return this.mSourceId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Cursor createCursor() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("_id");
        arrayList.add("name");
        arrayList.add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        arrayList.add("dateLastModified");
        return getContentResolver().query(MediaProvider.UdoPlaylists.getContentUri(this.mSourceId), (String[]) arrayList.toArray(new String[arrayList.size()]), null, null, "dateLastModified");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addToCirrusPlaylist(Uri playlistUri) {
        if (this.mJobId.longValue() == -1) {
            if (!ConnectivityUtil.hasAnyInternetConnection() && "cirrus".equals(MediaProvider.getSource(playlistUri))) {
                startActivity(NetworkErrorDialogActivity.getStartIntent(this));
            } else if (MediaProvider.Albums.Tracks.isAlbumTracks(this.mTracksUri) && this.mPrimeCollection) {
                addPrimeAlbumTracks(playlistUri);
            } else {
                addTracksToPlaylist(playlistUri);
            }
        }
    }

    private void addPrimeAlbumTracks(Uri playlistUri) {
        this.mAddTracksJobId = addJob(new GetAlbumTracksAndAddJob(this, this.mAlbumAsin));
        showProgressDialog(R.string.dmusic_playlist_progress_adding_songs, 0);
        this.mCurrentlyAddingPlaylist = playlistUri;
    }

    private void addTracksToPlaylist(Uri playlistUri) {
        showProgressDialog(R.string.dmusic_playlist_progress_updating, 0);
        this.mAddToPlaylistManager.addToPlaylist(playlistUri, this.mTracksUri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDuplicateTracksMessage(int tracks) {
        int iMatch = DefaultUriMatcher.match(this.mTracksUri);
        if (iMatch == 1) {
            return getResources().getQuantityString(R.plurals.dmusic_playlist_track_already_in_playlist, tracks);
        }
        if (iMatch == 5) {
            return getResources().getQuantityString(R.plurals.dmusic_playlist_album_already_in_playlist, tracks);
        }
        if (iMatch == 9) {
            return getResources().getQuantityString(R.plurals.dmusic_playlist_artist_already_in_playlist, tracks);
        }
        if (iMatch == 26) {
            return getResources().getQuantityString(R.plurals.dmusic_playlist_genre_already_in_playlist, tracks);
        }
        if (iMatch == 15 || iMatch == 16) {
            return getResources().getQuantityString(R.plurals.dmusic_playlist_playlist_already_in_playlist, tracks);
        }
        return getResources().getQuantityString(R.plurals.dmusic_playlist_track_already_in_playlist, tracks);
    }

    private void createPlaylist(String name) {
        new CreatePlaylistTask(this, this.mSourceId, name, new CreatePlaylistTask.OnPlaylistCreatedListener() { // from class: com.amazon.mp3.library.activity.AddToPlaylistPopupActivity.5
            @Override // com.amazon.mp3.library.util.CreatePlaylistTask.OnPlaylistCreatedListener
            public void onPlaylistCreated(final CreatePlaylistTask.Result result, final boolean success) {
                if (success) {
                    boolean z = this.getSharedPreferences("com.amazon.mp3.CreatePlaylistDialogFragment", 0).getBoolean(AddToPlaylistPopupActivity.this.getString(R.string.playlist_creation_public_toggle_last_state), false);
                    if (z) {
                        PlaylistUtil.setPlaylistVisibility(this, result.mPlaylistUri, MediaProvider.Playlists.VisibilityState.PUBLIC);
                    }
                    UiClickEvent.Builder builderWithEventTime = UiClickEvent.builder(z ? ActionType.CREATE_PUBLIC_PLAYLIST : ActionType.CREATE_PRIVATE_PLAYLIST).withInteractionType(InteractionType.TAP).withPageType(AddToPlaylistPopupActivity.this.mPageType).withEntityId(MediaProvider.Playlists.getPlaylistLuid(result.mPlaylistUri)).withEntityIdType(EntityIdType.PLAYLIST_ID).withEventTime(System.currentTimeMillis());
                    if (AddToPlaylistPopupActivity.this.mPageType != null) {
                        builderWithEventTime = builderWithEventTime.withPageType(AddToPlaylistPopupActivity.this.mPageType);
                    }
                    MetricsHolder.getManager().handleEvent(builderWithEventTime.build());
                    AddToPlaylistPopupActivity.this.addToCirrusPlaylist(MediaProvider.UdoPlaylistTracks.getContentUri(AddToPlaylistPopupActivity.this.mSourceId, MediaProvider.Playlists.getPlaylistId(result.mPlaylistUri)));
                }
            }
        }).execute(new Void[0]);
    }

    private List<Playlist> constructPlaylists(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        this.mPlaylists = arrayList;
        if (cursor == null) {
            return arrayList;
        }
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("name");
        int columnIndex3 = cursor.getColumnIndex(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        while (cursor.moveToNext()) {
            Playlist playlist = new Playlist();
            playlist.setId(String.valueOf(cursor.getLong(columnIndex)));
            playlist.setName(cursor.getString(columnIndex2));
            playlist.setType(cursor.getString(columnIndex3));
            this.mPlaylists.add(playlist);
        }
        return this.mPlaylists;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViews(Cursor c) {
        ListView listView = (ListView) findViewById(R.id.ListView);
        TextView textView = (TextView) findViewById(R.id.library_add_to_playlist_title);
        View viewInflate = getLayoutInflater().inflate(R.layout.library_popup_listview_rows, (ViewGroup) listView, false);
        ((TextView) viewInflate.findViewById(android.R.id.text1)).setText(R.string.dmusic_playlist_create_playlist);
        listView.addHeaderView(viewInflate);
        if (c != null) {
            startManagingCursor(c);
        }
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(this, constructPlaylists(c));
        ListViewUtil.stopScroll(listView);
        listView.setAdapter((ListAdapter) playlistAdapter);
        if (textView != null) {
            if ("cirrus".equals(this.mSourceId)) {
                textView.setText(R.string.dmusic_cloud_playlist_add_to_popup_title);
            } else {
                textView.setText(R.string.dmusic_offline_playlist_add_to_popup_title);
            }
        }
        listView.setOnItemClickListener(this.mOnItemClickListener);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CreateOrRenamePlaylistActivity.REQUEST_CODE && resultCode == -1) {
            createPlaylist(data.getStringExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_NAME"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUiClickMetrics(final ActionType actionType) {
        UiClickEvent.Builder builderWithEventTime = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis());
        PageType pageType = this.mPageType;
        if (pageType != null) {
            builderWithEventTime = builderWithEventTime.withPageType(pageType);
        }
        MetricsHolder.getManager().handleEvent(builderWithEventTime.build());
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override // com.amazon.mp3.library.activity.LibraryBaseActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Subscription subscription = this.cursorSubscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        this.cursorSubscription.unsubscribe();
    }
}
