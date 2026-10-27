package com.amazon.mp3.library.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.download.library.scanner.MediaScanBroadcaster;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.service.metrics.UxMetricsLogger;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DialogUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.visualon.OSMPUtils.voOSType;
import java.lang.ref.WeakReference;
import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryBaseFragment extends BaseFragment implements NetworkErrorDialog.INetworkErrorDialogHandler, OverflowMenuReceiver {
    private static final IntentFilter CONNECTION_CHANGED_FILTER;
    private static final IntentFilter GENERAL_ACTION_FILTER;
    private static final IntentFilter MEDIA_ACTION_FILTER;
    private static final HashSet<String> VALID_REMEMBERED_SOURCE_IDS;
    private ActionBarProvider mActionBarProvider;
    private Uri mContentUri;
    private MediaScanBroadcaster mMediaScanBroadcaster;
    protected NavigationProvider mNavigationProvider;
    private Dialog mNetworkErrorDialog;
    private ProgressDialog mProgressDialog;
    private String mProgressMessage;
    private String mSourceId;
    protected String mTabHostName;
    private static final String TAG = LibraryBaseFragment.class.getSimpleName();
    private static final int CONNECTION_ERROR_DIALOG_ID = UniqueCodeUtil.nextUniqueCode();
    protected boolean mFromTabHost = false;
    private ShowProgressRunnable mShowProgressRunnable = new ShowProgressRunnable(this);
    private Runnable mAllowProgressCancelRunnable = new AllowProgressCancelRunnable(this);
    private BroadcastReceiver mGeneralBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.fragment.LibraryBaseFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.amazon.mp3.library.service.sync.ACTION_SYNC_EVENT")) {
                LibraryBaseFragment.this.onSyncEvent(intent.getExtras());
            }
            if (action.equals("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STARTED")) {
                LibraryBaseFragment.this.onSyncServiceStarted();
                return;
            }
            if (action.equals("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STOPPED")) {
                LibraryBaseFragment.this.onSyncServiceStopped();
            } else {
                if (!action.equals("com.amazon.mp3.ACTION_CACHE_CLEARED") || !LibraryBaseFragment.this.finishOnCirrusDatabaseCleared() || (intent.getIntExtra("com.amazon.mp3.activity.EXTRA_CLEAR_FLAGS", -108809) & 8) == 0 || LibraryBaseFragment.this.getActivity() == null) {
                    return;
                }
                LibraryBaseFragment.this.getActivity().finish();
            }
        }
    };
    private final BroadcastReceiver mUpsellBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.fragment.LibraryBaseFragment.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            UpsellInformation upsellInformation;
            if (LibraryBaseFragment.this.getActivity() == null || !"com.amazon.mp3.upsell.skip_limit_upsell".equals(intent.getAction()) || intent.getExtras() == null || (upsellInformation = (UpsellInformation) intent.getExtras().getParcelable("com.amazon.music.playback.EXTRA_SHOW_UNLIMITED_UPSELL")) == null) {
                return;
            }
            UpsellUtil.showBlockingUpsell(LibraryBaseFragment.this.getActivity(), upsellInformation);
        }
    };
    private BroadcastReceiver mMediaScanReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.fragment.LibraryBaseFragment.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.amazon.mp3.ACTION_MEDIA_SCANNER_SCANNED_FILE")) {
                LibraryBaseFragment.this.onMediaScannerFinished();
            }
        }
    };
    private BroadcastReceiver mMediaActionReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.fragment.LibraryBaseFragment.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                LibraryBaseFragment.this.onMediaMounted();
            } else if (action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
                LibraryBaseFragment.this.onMediaUnmounted();
            }
        }
    };
    private final BroadcastReceiver mConnectivityReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.fragment.LibraryBaseFragment.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            if (intent != null) {
                LibraryBaseFragment.this.onConnectivityChanged(!intent.getBooleanExtra("noConnectivity", false));
            }
        }
    };

    public boolean getHasOptionsMenu() {
        return false;
    }

    public void onConnectionErrorDialogCancel() {
    }

    public void onDialogDismiss() {
    }

    protected void onNetworkConnectionRestored() {
    }

    protected void onSyncEvent(Bundle extras) {
    }

    protected void onSyncServiceStarted() {
    }

    protected void onSyncServiceStopped() {
    }

    protected boolean requireNetworkConnection() {
        return false;
    }

    protected boolean shouldSaveLastViewedSource() {
        return true;
    }

    static {
        HashSet<String> hashSet = new HashSet<>();
        VALID_REMEMBERED_SOURCE_IDS = hashSet;
        hashSet.add("cirrus");
        hashSet.add("cirrus-local");
        IntentFilter intentFilter = new IntentFilter();
        GENERAL_ACTION_FILTER = intentFilter;
        intentFilter.addAction("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STARTED");
        intentFilter.addAction("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STOPPED");
        intentFilter.addAction("com.amazon.mp3.library.service.sync.ACTION_SYNC_EVENT");
        intentFilter.addAction("com.amazon.mp3.ACTION_CACHE_CLEARED");
        IntentFilter intentFilter2 = new IntentFilter();
        MEDIA_ACTION_FILTER = intentFilter2;
        intentFilter2.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter2.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter2.addDataScheme("file");
        CONNECTION_CHANGED_FILTER = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    }

    public static boolean isValidLibrarySource(String sourceId) {
        return VALID_REMEMBERED_SOURCE_IDS.contains(sourceId);
    }

    public LibraryBaseFragment() {
        setArguments(new Bundle());
    }

    public void setActionBarProvider(ActionBarProvider provider) {
        this.mActionBarProvider = provider;
    }

    protected void setHeaderView(final View headerView) {
        ActionBarProvider actionBarProvider = this.mActionBarProvider;
        if (actionBarProvider != null) {
            actionBarProvider.setHeaderView(headerView);
        }
    }

    protected void restoreHomeButton() {
        ActionBarProvider actionBarProvider = this.mActionBarProvider;
        if (actionBarProvider != null) {
            actionBarProvider.restoreHomeButton();
        }
    }

    protected void requestHomeButtonAsUp() {
        ActionBarProvider actionBarProvider = this.mActionBarProvider;
        if (actionBarProvider != null) {
            actionBarProvider.requestHomeButtonAsBack();
        }
    }

    protected void removeHeaderView() {
        ActionBarProvider actionBarProvider = this.mActionBarProvider;
        if (actionBarProvider != null) {
            actionBarProvider.removeHeaderView();
        }
    }

    protected void setHomeButtonVisibility(int visibility) {
        ActionBarProvider actionBarProvider = this.mActionBarProvider;
        if (actionBarProvider != null) {
            actionBarProvider.setHomeButtonVisibility(visibility);
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mMediaScanBroadcaster = new MediaScanBroadcaster(getContext());
        registerReceiver(this.mGeneralBroadcastReceiver, GENERAL_ACTION_FILTER);
        registerReceiver(this.mMediaActionReceiver, MEDIA_ACTION_FILTER);
        onRestoreInstanceState(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments == null) {
            throw new IllegalStateException("setArguments should be called before onCreate");
        }
        this.mSourceId = arguments.getString("com.amazon.mp3.library.EXTRA_SOURCE_ID");
        Uri uri = (Uri) arguments.getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI");
        this.mContentUri = uri;
        if (uri == null || this.mSourceId != null) {
            return;
        }
        this.mSourceId = MediaProvider.getSource(uri);
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initHomeNav();
    }

    protected void initHomeNav() {
        restoreHomeButton();
    }

    protected void resetContent(@Nullable final Uri uri, final String sourceId) {
        Bundle arguments = getArguments();
        arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", sourceId);
        this.mSourceId = sourceId;
        if (uri != null) {
            arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", uri);
            this.mContentUri = uri;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof NavigationProvider) {
            setNavProvider((NavigationProvider) activity);
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        unregisterReceivers();
        this.mActionBarProvider = null;
        this.mNavigationProvider = null;
        this.mMediaScanBroadcaster = null;
        super.onDestroy();
    }

    private void unregisterReceivers() {
        unregisterReceiver(this.mGeneralBroadcastReceiver);
        unregisterReceiver(this.mMediaActionReceiver);
        unregisterReceiver(this.mUpsellBroadcastReceiver);
        unregisterReceiver(this.mConnectivityReceiver);
        MediaScanBroadcaster mediaScanBroadcaster = this.mMediaScanBroadcaster;
        if (mediaScanBroadcaster != null) {
            mediaScanBroadcaster.unregisterMediaScanBroadcastReceiver(this.mMediaScanReceiver);
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        unregisterReceiver(this.mUpsellBroadcastReceiver);
        unregisterReceiver(this.mConnectivityReceiver);
        this.mMediaScanBroadcaster.unregisterMediaScanBroadcastReceiver(this.mMediaScanReceiver);
        super.onPause();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        storeLastViewedPrefs();
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.show();
        }
        UxMetricsLogger.registerApplicationAction(UxMetricsLogger.ApplicationAction.LIBRARY_ACTIVITY_COMPLETE);
        registerReceiver(this.mConnectivityReceiver, CONNECTION_CHANGED_FILTER);
        this.mMediaScanBroadcaster.registerMediaScanBroadcastReceiver(this.mMediaScanReceiver);
        registerReceiver(this.mUpsellBroadcastReceiver, new IntentFilter("com.amazon.mp3.upsell.skip_limit_upsell"));
        if (noNetworkConnection()) {
            showNetworkErrorDialog(this);
            Log.info(TAG, "No network connection");
        }
    }

    protected boolean noNetworkConnection() {
        return requireNetworkConnection() && !ConnectivityUtil.hasAnyInternetConnection();
    }

    @Override // androidx.fragment.app.Fragment
    public void startActivityForResult(Intent intent, int requestCode) {
        if (this.mFromTabHost) {
            getParentFragment().startActivityForResult(intent, requestCode);
        } else {
            super.startActivityForResult(intent, requestCode);
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment
    public void onActivated() {
        super.onActivated();
        SyncService.startTimeCheckedSync(getApplication());
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("com.amazon.mp3.library.EXTRA_SHOW_PROGRESS_DIALOG", this.mProgressDialog != null);
        outState.putString("com.amazon.mp3.library.EXTRA_PROGRESS_DIALOG_MESSAGE", this.mProgressMessage);
    }

    public String getSourceId() {
        return this.mSourceId;
    }

    @Nullable
    public Uri getContentUri() {
        return this.mContentUri;
    }

    public boolean isSourceLocal() {
        return "cirrus-local".equals(getSourceId());
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.mProgressMessage = savedInstanceState.getString("com.amazon.mp3.library.EXTRA_PROGRESS_DIALOG_MESSAGE");
            if (savedInstanceState.getBoolean("com.amazon.mp3.library.EXTRA_SHOW_PROGRESS_DIALOG", false)) {
                ProgressDialog progressDialog = new ProgressDialog(getActivity());
                this.mProgressDialog = progressDialog;
                progressDialog.setMessage(this.mProgressMessage);
            }
        }
    }

    protected void onMediaScannerFinished() {
        if (getActivity() != null) {
            SyncService.startSync(getActivity(), voOSType.VOOSMP_PID_ANALYTICS_AGENT);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMediaMounted() {
        if (getActivity() != null) {
            SyncService.startSync(getActivity(), voOSType.VOOSMP_PID_ANALYTICS_AGENT);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMediaUnmounted() {
        FragmentActivity activity;
        if (!isSourceLocal() || (activity = getActivity()) == null) {
            return;
        }
        activity.finish();
    }

    protected boolean finishOnCirrusDatabaseCleared() {
        if (isSourceLocal()) {
            return false;
        }
        FragmentActivity activity = getActivity();
        return activity == null || !(activity instanceof MusicHomeActivity);
    }

    protected void showProgressDialog(int messageId, int delay) {
        showProgressDialog(messageId != 0 ? getString(messageId) : "", delay);
    }

    protected void showProgressDialog(String message, int delay) {
        if (this.mProgressDialog != null) {
            return;
        }
        this.mProgressMessage = message;
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        this.mProgressDialog = progressDialog;
        progressDialog.setMessage(this.mProgressMessage);
        this.mProgressDialog.setCancelable(false);
        DialogUtil.installDefaultKeyListener(this.mProgressDialog);
        if (delay > 0) {
            this.mHandler.removeCallbacks(this.mShowProgressRunnable);
            this.mHandler.postDelayed(this.mShowProgressRunnable, delay);
        } else {
            this.mProgressDialog.show();
            this.mHandler.postDelayed(this.mAllowProgressCancelRunnable, 10000L);
        }
    }

    protected void hideProgressDialog() {
        this.mHandler.removeCallbacks(this.mShowProgressRunnable);
        this.mHandler.removeCallbacks(this.mAllowProgressCancelRunnable);
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    protected void storeLastViewedPrefs() {
        Context application = getApplication();
        String sourceId = getSourceId();
        if (shouldSaveLastViewedSource() && isValidLibrarySource(sourceId)) {
            LastViewedScreenUtil.setLastViewed(application, LastViewedScreenUtil.LastViewedScreen.LIBRARY, LastViewedScreenUtil.LastViewedSource.fromCirrusMediaSource(sourceId));
        } else {
            LastViewedScreenUtil.setLastViewed(application, LastViewedScreenUtil.LastViewedScreen.LIBRARY);
        }
    }

    protected void onConnectivityChanged(boolean hasConnection) {
        if (requireNetworkConnection()) {
            if (hasConnection || !isNetworkErrorDialogShowing()) {
                if (hasConnection && ConnectivityUtil.hasAnyInternetConnection(getContext())) {
                    return;
                }
                showNetworkErrorDialog(this);
            }
        }
    }

    protected boolean isNetworkErrorDialogShowing() {
        Dialog dialog = this.mNetworkErrorDialog;
        return dialog != null && dialog.isShowing();
    }

    public void showNetworkErrorDialog(NetworkErrorDialog.INetworkErrorDialogHandler handler) {
        Dialog dialogCreate = NetworkErrorDialog.create(getActivity(), handler);
        this.mNetworkErrorDialog = dialogCreate;
        showDialog(CONNECTION_ERROR_DIALOG_ID, dialogCreate);
    }

    public void onRetryConnection() {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            showNetworkErrorDialog(this);
        } else {
            onNetworkConnectionRestored();
        }
    }

    private static final class ShowProgressRunnable implements Runnable {
        private final WeakReference<LibraryBaseFragment> mWeakReferenceFragment;

        public ShowProgressRunnable(final LibraryBaseFragment libraryBaseFragment) {
            this.mWeakReferenceFragment = new WeakReference<>(libraryBaseFragment);
        }

        @Override // java.lang.Runnable
        public void run() {
            LibraryBaseFragment libraryBaseFragment = this.mWeakReferenceFragment.get();
            if (libraryBaseFragment == null || libraryBaseFragment.isPaused() || libraryBaseFragment.getActivity() == null || libraryBaseFragment.getActivity().isFinishing() || libraryBaseFragment.mProgressDialog == null || libraryBaseFragment.mProgressDialog.isShowing()) {
                return;
            }
            libraryBaseFragment.mProgressDialog.show();
            ((BaseFragment) libraryBaseFragment).mHandler.postDelayed(libraryBaseFragment.mAllowProgressCancelRunnable, 10000L);
        }
    }

    private static final class AllowProgressCancelRunnable implements Runnable {
        private final WeakReference<LibraryBaseFragment> mWeakReferenceFragment;

        public AllowProgressCancelRunnable(final LibraryBaseFragment libraryBaseFragment) {
            this.mWeakReferenceFragment = new WeakReference<>(libraryBaseFragment);
        }

        @Override // java.lang.Runnable
        public void run() {
            LibraryBaseFragment libraryBaseFragment = this.mWeakReferenceFragment.get();
            if (libraryBaseFragment == null || libraryBaseFragment.mProgressDialog == null) {
                return;
            }
            FragmentActivity activity = libraryBaseFragment.getActivity();
            if (libraryBaseFragment.isPaused() || activity == null || activity.isFinishing() || activity.isDestroyed()) {
                if (libraryBaseFragment.mProgressDialog.isShowing()) {
                    libraryBaseFragment.mProgressDialog.dismiss();
                    return;
                }
                return;
            }
            libraryBaseFragment.mProgressDialog.setCancelable(true);
        }
    }

    public void setNavProvider(final NavigationProvider provider) {
        this.mNavigationProvider = provider;
    }
}
