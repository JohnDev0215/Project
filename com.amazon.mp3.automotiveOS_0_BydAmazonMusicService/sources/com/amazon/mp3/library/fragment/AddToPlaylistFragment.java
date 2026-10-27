package com.amazon.mp3.library.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.library.adapter.AddToPlaylistAdapter;
import com.amazon.mp3.library.adapter.EmptyFilterAdapter;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.util.LibraryFilters;
import com.amazon.mp3.library.util.PlaylistMgmt;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Hashtable;
import java.util.Set;
import rx.Completable;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes3.dex */
public class AddToPlaylistFragment extends TrackListFragment {
    private AddToPlaylistAdapter mAdapter;
    private AutoCompleteTextView mFilterEditText;
    private InputMethodManager mImm;
    private PlaylistMgmt mPlaylistMgmt;
    private Uri mPlaylistUri;
    private Subscription mQuerySubscription;
    private CompositeSubscription mSubscriptions;
    private String TAG = AddToPlaylistFragment.class.getSimpleName();
    PlaylistMgmt.OnPlaylistMgmt mOnPlaylistMgmt = new PlaylistMgmt.OnPlaylistMgmt() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment.4
        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public Cursor onAddAllTracksCursor() {
            return null;
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onAddAllTracksCursorFinish() {
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onAddAllTracksJobFinish() {
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onPlaylistChangedObserver() {
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public boolean onShuffleExist() {
            return false;
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onUpdateCachePlaylist(Hashtable<String, PlaylistUtil.PlaylistTrack> cachePlaylist) {
            AddToPlaylistFragment.this.mAdapter.setCachePlaylist(cachePlaylist);
            if (AddToPlaylistFragment.this.getListView() != null) {
                ((BaseFragment) AddToPlaylistFragment.this).mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AddToPlaylistFragment.this.getListView().invalidateViews();
                    }
                });
            }
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onSetDuration(String duration) {
            PlaylistMgmt.OnPlaylistMgmt onPlaylistMgmt = getOnPlaylistMgmt();
            if (onPlaylistMgmt != null) {
                onPlaylistMgmt.onSetDuration(duration);
            }
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public void onCachePlaylistJobFinish() {
            PlaylistMgmt.OnPlaylistMgmt onPlaylistMgmt = getOnPlaylistMgmt();
            if (onPlaylistMgmt != null) {
                onPlaylistMgmt.onCachePlaylistJobFinish();
            }
        }

        @Override // com.amazon.mp3.library.util.PlaylistMgmt.OnPlaylistMgmt
        public long onAddJob(Job job) {
            return AddToPlaylistFragment.this.addJob(job);
        }

        private PlaylistMgmt.OnPlaylistMgmt getOnPlaylistMgmt() {
            if (AddToPlaylistFragment.this.getActivity() instanceof PlaylistMgmt.OnPlaylistMgmt) {
                return (PlaylistMgmt.OnPlaylistMgmt) AddToPlaylistFragment.this.getActivity();
            }
            return null;
        }
    };
    private final TextWatcher mOnFilterTextChangeListener = new TextWatcher() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment.5
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            AddToPlaylistFragment.this.getApplication().sendBroadcast(LibraryFilters.set(AddToPlaylistFragment.this.getFilterId(), s.toString()));
        }
    };
    private AddToPlaylistAdapter.OnAddButtonClickListener mOnAddButtonClickListener = new AnonymousClass6();
    private AddToPlaylistAdapter.OnDeleteButtonClickListener mOnDeleteButtonClickListener = new AnonymousClass7();
    private TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment.8
        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            AddToPlaylistFragment.this.hideSoftKeyboard();
            return true;
        }
    };
    private AdapterView.OnItemClickListener mOnSuggestionDropDownClickListener = new AdapterView.OnItemClickListener() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment.9
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
            AddToPlaylistFragment.this.hideSoftKeyboard();
        }
    };

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected void onCreateListViewContextMenu(ContextMenu menu, View unused, AdapterView.AdapterContextMenuInfo menuInfo) {
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected void onItemClick(AbsListView listView, View view, int position, long id) {
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlaylistMgmt playlistMgmt = new PlaylistMgmt(null, getActivity(), getContentUri(), this.mOnPlaylistMgmt, new Intent().putExtras(getArguments()));
        this.mPlaylistMgmt = playlistMgmt;
        playlistMgmt.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            PlaylistUtil.clearMutatedFlag(getActivity());
        }
    }

    @Override // com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle instanceState) {
        super.onActivityCreated(instanceState);
        init();
        this.mQuerySubscription = Observable.create(new Observable.OnSubscribe<Boolean>() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment.3
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(Boolean.valueOf("cirrus".equals(MediaProvider.getSource(AddToPlaylistFragment.this.mPlaylistUri)) && PlaylistUtil.getPlaylistCount(AddToPlaylistFragment.this.getActivity(), AddToPlaylistFragment.this.mPlaylistUri) >= PlaylistConfigurationStorage.get().getMaximumNumberOfTracksInPlaylist()));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment.1
            @Override // rx.functions.Action1
            public void call(final Boolean shouldExit) {
                if (shouldExit.booleanValue() && ActivityKt.isAvailable(AddToPlaylistFragment.this.getActivity())) {
                    AddToPlaylistFragment addToPlaylistFragment = AddToPlaylistFragment.this;
                    addToPlaylistFragment.startActivity(DialogActivity.getStartIntent(addToPlaylistFragment.getActivity(), R.string.dmusic_playlist_edit_max_size_title, R.string.dmusic_playlist_edit_max_size_desc));
                    AddToPlaylistFragment.this.getActivity().finish();
                }
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment.2
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.error(AddToPlaylistFragment.this.TAG, "error fetching query count ", throwable);
                if (ActivityKt.isAvailable(AddToPlaylistFragment.this.getActivity())) {
                    AddToPlaylistFragment.this.getActivity().finish();
                }
            }
        });
        this.mPlaylistMgmt.setActivity(getActivity());
        this.mPlaylistMgmt.onActivityCreated(instanceState);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        PlaylistUtil.setPlaylistBeingEdited(this.mPlaylistUri);
        this.mPlaylistMgmt.onResume();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        PlaylistUtil.setPlaylistBeingEdited(null);
        this.mPlaylistMgmt.onPause();
        CompositeSubscription compositeSubscription = this.mSubscriptions;
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (this.mFilterEditText == null || !hidden) {
            return;
        }
        hideSoftKeyboard();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        LibraryFilters.remove(getFilterId());
        super.onDestroy();
        Subscription subscription = this.mQuerySubscription;
        if (subscription != null && !subscription.isUnsubscribed()) {
            this.mQuerySubscription.unsubscribe();
        }
        this.mPlaylistMgmt.onDestroy();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.mPlaylistMgmt.onActivityResult(requestCode, resultCode, data);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PlaylistMgmt playlistMgmt = this.mPlaylistMgmt;
        if (playlistMgmt != null) {
            playlistMgmt.onSaveInstanceState(outState);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment
    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        PlaylistMgmt playlistMgmt = this.mPlaylistMgmt;
        if (playlistMgmt != null) {
            playlistMgmt.restoreInstanceState(inState);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected TrackListAdapter createTrackListAdapter() {
        AddToPlaylistAdapter addToPlaylistAdapter = new AddToPlaylistAdapter(getApplication(), getContentUri(), this, this.mOnAddButtonClickListener, this.mOnDeleteButtonClickListener, false);
        this.mAdapter = addToPlaylistAdapter;
        return addToPlaylistAdapter;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment
    protected CursorAdapter getEmptyFilterListAdapter() {
        return new EmptyFilterAdapter(getApplication(), null, R.string.dmusic_library_empty_filter_no_tracks_found, false);
    }

    private void init() {
        this.mPlaylistUri = this.mPlaylistMgmt.getPlaylistUri();
        setFilterId(LibraryFilters.createFilterId());
        this.mImm = (InputMethodManager) getApplication().getSystemService("input_method");
        View viewInflateHeader = inflateHeader(R.layout.headerbar_search);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewInflateHeader.getLayoutParams();
        marginLayoutParams.setMargins((int) getResources().getDimension(R.dimen.library_detail_padding), marginLayoutParams.topMargin, (int) getResources().getDimension(R.dimen.library_detail_padding), marginLayoutParams.bottomMargin);
        if (R.id.HeaderViewStub == viewInflateHeader.getId()) {
            viewInflateHeader.setId(-1);
        }
        new UpdatePlaylistTrackLuids().execute(new Void[0]);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) viewInflateHeader.findViewById(R.id.FilterEditText);
        this.mFilterEditText = autoCompleteTextView;
        autoCompleteTextView.setDropDownBackgroundResource(R.color.primo_blue);
        this.mFilterEditText.setImeOptions(3);
        this.mFilterEditText.addTextChangedListener(this.mOnFilterTextChangeListener);
        this.mFilterEditText.setOnEditorActionListener(this.mOnEditorActionListener);
        this.mFilterEditText.setOnItemClickListener(this.mOnSuggestionDropDownClickListener);
        this.mFilterEditText.setHint(CirrusMediaSource.matchCloud(getContentUri()) ? R.string.dmusic_library_udo_playlists_filter_hint_cirrus : R.string.dmusic_library_udo_playlists_filter_hint_local);
        viewInflateHeader.setFocusable(true);
        viewInflateHeader.setFocusableInTouchMode(true);
        viewInflateHeader.requestFocus();
        this.mFilterEditText.clearFocus();
        getListView().setOnCreateContextMenuListener(null);
        hideSourceToggle();
    }

    private void hideSourceToggle() {
        View viewFindViewById = getView().findViewById(R.id.ActionBarSourceToggle);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSoftKeyboard() {
        this.mFilterEditText.clearFocus();
        this.mImm.hideSoftInputFromWindow(this.mFilterEditText.getWindowToken(), 0);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getSelection() {
        return isSourceLocal() ? "asin NOT NULL " : super.getSelection();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.fragment.AddToPlaylistFragment$6, reason: invalid class name */
    class AnonymousClass6 implements AddToPlaylistAdapter.OnAddButtonClickListener {
        AnonymousClass6() {
        }

        @Override // com.amazon.mp3.library.adapter.AddToPlaylistAdapter.OnAddButtonClickListener
        public void onClick(final Uri trackUri) {
            if (AddToPlaylistFragment.this.mSubscriptions == null) {
                AddToPlaylistFragment.this.mSubscriptions = new CompositeSubscription();
            }
            AddToPlaylistFragment.this.mSubscriptions.add(Completable.defer(new Func0() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment$6$$ExternalSyntheticLambda0
                @Override // rx.functions.Func0, java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$onClick$1(trackUri);
                }
            }).subscribeOn(Schedulers.computation()).doOnError(new Action1() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment$6$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$onClick$2((Throwable) obj);
                }
            }).subscribe());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Completable lambda$onClick$1(final Uri uri) {
            return Completable.fromAction(new Action0() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment$6$$ExternalSyntheticLambda2
                @Override // rx.functions.Action0
                public final void call() {
                    this.f$0.lambda$onClick$0(uri);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onClick$0(Uri uri) {
            AddToPlaylistFragment.this.mPlaylistMgmt.processAddButtonClick(uri, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onClick$2(Throwable th) {
            Log.error(AddToPlaylistFragment.this.TAG, th.getMessage(), th);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.fragment.AddToPlaylistFragment$7, reason: invalid class name */
    class AnonymousClass7 implements AddToPlaylistAdapter.OnDeleteButtonClickListener {
        AnonymousClass7() {
        }

        @Override // com.amazon.mp3.library.adapter.AddToPlaylistAdapter.OnDeleteButtonClickListener
        public void onClick(final long playlistTrackId, final long udo) {
            if (AddToPlaylistFragment.this.mSubscriptions == null) {
                AddToPlaylistFragment.this.mSubscriptions = new CompositeSubscription();
            }
            AddToPlaylistFragment.this.mSubscriptions.add(Completable.defer(new Func0() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment$7$$ExternalSyntheticLambda0
                @Override // rx.functions.Func0, java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$onClick$1(playlistTrackId, udo);
                }
            }).subscribeOn(Schedulers.computation()).doOnError(new Action1() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment$7$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$onClick$2((Throwable) obj);
                }
            }).subscribe());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Completable lambda$onClick$1(final long j, final long j2) {
            return Completable.fromAction(new Action0() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment$7$$ExternalSyntheticLambda2
                @Override // rx.functions.Action0
                public final void call() {
                    this.f$0.lambda$onClick$0(j, j2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onClick$0(long j, long j2) {
            AddToPlaylistFragment.this.mPlaylistMgmt.processDeleteButtonClick(j, j2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onClick$2(Throwable th) {
            Log.error(AddToPlaylistFragment.this.TAG, th.getMessage(), th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.service.job.JobSessionFragment
    public void onJobFinished(final long jobId, final Job job, final int resultCode, final Bundle resultBundle) {
        this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.AddToPlaylistFragment.10
            @Override // java.lang.Runnable
            public void run() {
                AddToPlaylistFragment.this.mPlaylistMgmt.onJobFinished(jobId, job, resultCode, resultBundle);
                AddToPlaylistFragment.super.onJobFinished(jobId, job, resultCode, resultBundle);
            }
        });
    }

    private class UpdatePlaylistTrackLuids extends AsyncTask<Void, Void, Void> {
        private Set<String> mPlaylistTrackLuids;

        private UpdatePlaylistTrackLuids() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            this.mPlaylistTrackLuids = PlaylistUtil.getUdoPlaylistTracksLuids(AddToPlaylistFragment.this.getActivity(), AddToPlaylistFragment.this.mPlaylistMgmt.getPlaylistUri());
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void result) {
            AddToPlaylistFragment.this.mAdapter.setPlaylistTrackLuids(this.mPlaylistTrackLuids);
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.UNKNOWN;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        return PageType.ADD_TO_PLAYLIST_SONGS;
    }
}
