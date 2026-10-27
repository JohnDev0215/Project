package com.amazon.mp3.library.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.activity.AddToPlaylistTabActivity;
import com.amazon.mp3.library.activity.SavePlaylistActivity;
import com.amazon.mp3.library.adapter.EditPlaylistListAdapter;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.job.RenamePlaylistJob;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.util.AllAccessUserPlaylistLimitTracker;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.view.DragDropListView;
import com.amazon.music.curate.data.PlaylistIntentReceiverService;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class EditPlaylistFragment extends PlaylistDetailEditFragment {
    private Uri mContentUri;
    private Button mDoneButton;
    private View mHeaderView;
    private boolean mMutated;
    private EditText mPlayListNameEditText;
    private TextView mPlaylistNameTextView;
    private Uri mPlaylistUri;
    private TextView mTrackCountTextView;
    private static final int REQUEST_SAVE_AND_FINISH = UniqueCodeUtil.nextUniqueCode();
    private static final int REQUEST_SAVE_AND_ADD = UniqueCodeUtil.nextUniqueCode();
    private static final String TAG = EditPlaylistFragment.class.getSimpleName();
    private boolean mPlayListNameHasChanged = false;
    private EditPlaylistListAdapter mAdapter = null;
    private TextWatcher mTextChangeListener = new TextWatcher() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment.2
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            EditPlaylistFragment.this.mDoneButton.setEnabled(!TextUtils.isEmpty(s) && TextUtils.getTrimmedLength(s) > 0);
            EditPlaylistFragment.this.mPlayListNameHasChanged = true;
        }
    };
    private final View.OnClickListener mOnAddToClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            EditPlaylistFragment.this.renamePlaylist(null);
            EditPlaylistFragment.this.startAddToPlaylistActivity();
        }
    };
    private final View.OnClickListener mOnCancelClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment.4
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            FragmentActivity activity = EditPlaylistFragment.this.getActivity();
            if (activity != null) {
                activity.setResult(-1);
                AllAccessUserPlaylistLimitTracker.resetPendingChanges(EditPlaylistFragment.this.mPlaylistUri);
                activity.finish();
            }
        }
    };
    private final View.OnClickListener mOnDoneClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment.5
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            EditPlaylistFragment.this.savePlaylistAndFinish();
        }
    };
    private DragDropListView.DropListener mDropListener = new DragDropListView.DropListener() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment.6
        @Override // com.amazon.mp3.view.DragDropListView.DropListener
        public void drop(int from, int to) {
            if (from >= EditPlaylistFragment.this.getCursor().getCount() || to >= EditPlaylistFragment.this.getCursor().getCount()) {
                return;
            }
            final int udoAtPosition = EditPlaylistFragment.this.getUdoAtPosition(from);
            final int udoAtPosition2 = EditPlaylistFragment.this.getUdoAtPosition(to);
            new Thread(new Runnable() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment.6.1
                @Override // java.lang.Runnable
                public void run() {
                    if (PlaylistUtil.moveUdoPlaylistTrack(EditPlaylistFragment.this.getApplication(), EditPlaylistFragment.this.mPlaylistUri, udoAtPosition, udoAtPosition2)) {
                        PlaylistUtil.setMutatedFlag(EditPlaylistFragment.this.getActivity());
                        EditPlaylistFragment.this.mMutated = true;
                        EditPlaylistFragment.this.requeryCursorInBackground();
                    }
                }
            }).start();
        }
    };

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getContentViewLayoutId() {
        return R.layout.library_list_fragment;
    }

    @Override // com.amazon.mp3.library.fragment.AbstractListFragment
    protected int getEmptyLayoutID() {
        return R.layout.library_list_fragment;
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.AbstractListFragment
    protected PageType getPageType() {
        return null;
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment
    protected boolean showDownloadButton() {
        return false;
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment
    protected void updateHeaderButtons(Cursor c) {
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Thread(new Runnable() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment.1
            @Override // java.lang.Runnable
            public void run() {
                if (!"cirrus".equals(MediaProvider.getSource(EditPlaylistFragment.this.mPlaylistUri)) || EditPlaylistFragment.this.getPlaylistCount() < PlaylistConfigurationStorage.get().getMaximumNumberOfTracksInPlaylist()) {
                    return;
                }
                EditPlaylistFragment editPlaylistFragment = EditPlaylistFragment.this;
                editPlaylistFragment.startActivity(DialogActivity.getStartIntent(editPlaylistFragment.getActivity(), R.string.dmusic_playlist_edit_max_size_title, R.string.dmusic_playlist_edit_max_size_desc));
                EditPlaylistFragment.this.getActivity().finish();
            }
        }).start();
        restorePlaylistNameEditView(savedInstanceState);
        if (savedInstanceState == null) {
            PlaylistUtil.clearMutatedFlag(getActivity());
        }
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.ArtListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment
    public void onActivated() {
        super.onActivated();
        sendUiPageViewMetric("editPlaylist", Long.toString(getPlaylistId()), "PLAYLIST_ID");
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        PlaylistUtil.setPlaylistBeingEdited(this.mPlaylistUri);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.CursorListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        PlaylistUtil.setPlaylistBeingEdited(null);
    }

    @Override // com.amazon.mp3.fragment.BaseFragment
    public boolean onBackPressed() {
        if (!userMutatedPlaylist() && !this.mPlayListNameHasChanged) {
            return false;
        }
        savePlaylistAndFinish();
        return true;
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment
    protected Uri getSinglePlaylistUri() {
        return PlaylistUtil.withAppendedScratchArgument(super.getSinglePlaylistUri());
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment
    protected void doInit(Bundle instanceState) {
        this.mPlaylistUri = MediaProvider.UdoPlaylists.getContentUri(getSourceId(), PlaylistUtil.getPlaylistId(getContentUri()));
        if (CirrusMediaSource.match(getContentUri())) {
            this.mPlaylistUri = PlaylistUtil.withAppendedScratchArgument(this.mPlaylistUri);
        }
        ViewStub viewStub = (ViewStub) getActivity().findViewById(R.id.HeaderViewStub);
        int i = R.layout.library_edit_playlist_detail_header_with_add;
        if (AmazonApplication.getCapabilities().isPlaylistSongSearchEnabled()) {
            i = R.layout.library_edit_playlist_detail_header;
        }
        viewStub.setLayoutResource(i);
        initHeaderBar(viewStub);
        DragDropListView dragDropListView = (DragDropListView) getListView();
        getAdapter();
        dragDropListView.initDragDrop(getResources().getDimensionPixelSize(R.dimen.listview_row_height), R.dimen.library_edit_playlist_drag_gripper_size);
        dragDropListView.setOnItemClickListener(null);
        dragDropListView.setOnCreateContextMenuListener(null);
        dragDropListView.setDropListener(this.mDropListener);
    }

    private void initHeaderBar(ViewStub stub) {
        View viewInflate = stub.inflate();
        this.mHeaderView = viewInflate;
        this.mHeaderButton = viewInflate.findViewById(R.id.HeaderButton);
        View.OnClickListener onClickListener = this.mOnAddToClickListener;
        if (AmazonApplication.getCapabilities().isPlaylistSongSearchEnabled()) {
            onClickListener = this.mOnCancelClickListener;
        }
        this.mHeaderButton.setOnClickListener(onClickListener);
        Button button = (Button) this.mHeaderView.findViewById(R.id.DoneButton);
        this.mDoneButton = button;
        button.setOnClickListener(this.mOnDoneClickListener);
        EditText editText = (EditText) this.mHeaderView.findViewById(R.id.PlaylistName);
        this.mPlayListNameEditText = editText;
        editText.setSelectAllOnFocus(true);
        this.mPlaylistNameTextView = (TextView) this.mHeaderView.findViewById(R.id.PlaylistNameTextView);
        this.mTrackCountTextView = (TextView) this.mHeaderView.findViewById(R.id.TrackCount);
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("com.amazon.mp3.library.EXTRA_PLAYLIST_MUTATED", this.mMutated);
        if (this.mPlayListNameHasChanged) {
            outState.putString("com.amazon.mp3.library.EXTRA_PLAYLIST_NEW_NAME", this.mPlayListNameEditText.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.mMutated = savedInstanceState.getBoolean("com.amazon.mp3.library.EXTRA_PLAYLIST_MUTATED", false);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void restorePlaylistNameEditView(Bundle savedInstanceState) {
        String string;
        if (savedInstanceState == null || (string = savedInstanceState.getString("com.amazon.mp3.library.EXTRA_PLAYLIST_NEW_NAME")) == null) {
            return;
        }
        this.mPlayListNameHasChanged = true;
        this.mPlayListNameEditText.setText(string);
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment, com.amazon.mp3.library.fragment.AbstractListFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SAVE_AND_FINISH && resultCode == -1) {
            PlaylistUtil.clearMutatedFlag(getActivity());
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.setResult(-1);
                activity.finish();
                return;
            }
            return;
        }
        if (requestCode == REQUEST_SAVE_AND_ADD) {
            startAddToPlaylistActivity();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAddToPlaylistActivity() {
        startActivity(AddToPlaylistTabActivity.getStartIntent(getActivity(), PlaylistUtil.getAddToPlaylistUri(this.mPlaylistUri, userMutatedPlaylist()), MediaProvider.getSource(this.mPlaylistUri), false));
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected TrackListAdapter createTrackListAdapter() {
        EditPlaylistListAdapter editPlaylistListAdapter = new EditPlaylistListAdapter(getActivity(), getContentUri(), this, false);
        this.mAdapter = editPlaylistListAdapter;
        return editPlaylistListAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void renamePlaylist(final Runnable onRenamePlaylistFinished) {
        final String string = this.mPlayListNameEditText.getText().toString();
        if (TextUtils.getTrimmedLength(string) == 0) {
            if (onRenamePlaylistFinished != null) {
                onRenamePlaylistFinished.run();
            }
        } else if (this.mPlayListNameHasChanged && !super.getPlaylistName().equals(string)) {
            Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$renamePlaylist$0(string, (Subscriber) obj);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    EditPlaylistFragment.lambda$renamePlaylist$1(onRenamePlaylistFinished, (Integer) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment$$ExternalSyntheticLambda3
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    EditPlaylistFragment.lambda$renamePlaylist$2(onRenamePlaylistFinished, (Throwable) obj);
                }
            });
        } else if (onRenamePlaylistFinished != null) {
            onRenamePlaylistFinished.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$renamePlaylist$0(String str, Subscriber subscriber) {
        try {
            subscriber.onNext(Integer.valueOf(new RenamePlaylistJob(getSourceId(), str, getPlaylistId(), getActivity().getApplication()).run()));
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$renamePlaylist$1(Runnable runnable, Integer num) {
        if (runnable != null) {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$renamePlaylist$2(Runnable runnable, Throwable th) {
        Log.error(TAG, "An error happened when renaming playing, message: " + th.getMessage());
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment
    protected String getPlaylistName() {
        return this.mPlayListNameHasChanged ? this.mPlayListNameEditText.getText().toString() : super.getPlaylistName();
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment
    protected void updateCountAndDuration(String trackcount) {
        TextView textView = this.mTrackCountTextView;
        if (textView != null) {
            textView.setText(trackcount);
        }
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.AbstractListFragment, com.amazon.mp3.library.fragment.LibraryBaseFragment
    public Uri getContentUri() {
        if (this.mContentUri == null) {
            Uri contentUri = super.getContentUri();
            this.mContentUri = contentUri;
            if (CirrusMediaSource.match(contentUri)) {
                this.mContentUri = PlaylistUtil.withAppendedScratchArgument(this.mContentUri);
            }
        }
        return this.mContentUri;
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String[] getProjection() {
        return EditPlaylistListAdapter.getDefaultProjection();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String getSelection() {
        return TrackListAdapter.getPlaylistSelection();
    }

    @Override // com.amazon.mp3.library.fragment.TrackListFragment
    protected String[] getSelectionArgs() {
        return TrackListAdapter.getPlaylistSelectionArgs();
    }

    protected void savePlaylistAndFinish() {
        renamePlaylist(new Runnable() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$savePlaylistAndFinish$5();
            }
        });
        AllAccessUserPlaylistLimitTracker.savePendingChanges(this.mPlaylistUri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$savePlaylistAndFinish$5() {
        if (userMutatedPlaylist()) {
            startActivityForResult(SavePlaylistActivity.getStartIntent(getApplication(), this.mPlaylistUri), REQUEST_SAVE_AND_FINISH);
            final Context applicationContext = getContext().getApplicationContext();
            Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment$$ExternalSyntheticLambda4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$savePlaylistAndFinish$3(applicationContext, (Subscriber) obj);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.library.fragment.EditPlaylistFragment$$ExternalSyntheticLambda5
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    PlaylistIntentReceiverService.processUpdatePlaylist(applicationContext, (String) obj);
                }
            });
        } else {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.setResult(-1);
                activity.finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$savePlaylistAndFinish$3(Context context, Subscriber subscriber) {
        subscriber.onNext(PlaylistUtil.getPlaylistLuid(context, this.mPlaylistUri));
        subscriber.onCompleted();
    }

    private boolean userMutatedPlaylist() {
        return this.mMutated || this.mAdapter.userMutatedPlaylist() || PlaylistUtil.getMutatedFlag(getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getUdoAtPosition(int position) {
        Cursor cursor = getCursor();
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("udo");
        cursor.moveToPosition(position);
        return cursor.getInt(columnIndexOrThrow);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPlaylistCount() {
        if (this.mPlaylistUri != null) {
            Cursor cursorQuery = getContentResolver().query(this.mPlaylistUri.buildUpon().appendEncodedPath("tracks").build(), new String[]{"_id"}, null, null, null);
            count = cursorQuery != null ? cursorQuery.getCount() : 0;
            DbUtil.closeCursor(cursorQuery);
        }
        return count;
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment
    protected void updatePlaylistName(final boolean canRenamePlaylist) {
        if (canRenamePlaylist) {
            this.mPlayListNameEditText.setText(getPlaylistName());
            this.mPlayListNameEditText.addTextChangedListener(this.mTextChangeListener);
        } else {
            this.mPlaylistNameTextView.setText(getPlaylistName());
            this.mPlayListNameEditText.setVisibility(8);
            this.mPlaylistNameTextView.setVisibility(0);
        }
    }

    @Override // com.amazon.mp3.library.fragment.PlaylistDetailEditFragment, com.amazon.mp3.library.fragment.TrackListFragment
    protected PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.UNKNOWN;
    }
}
