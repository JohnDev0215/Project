package com.amazon.mp3.store.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.store.dialog.NetworkErrorDialog;
import com.amazon.mp3.util.UniqueCodeUtil;

/* JADX INFO: loaded from: classes4.dex */
public class StoreBaseFragment extends BaseFragment implements NetworkErrorDialog.INetworkErrorDialogHandler, OverflowMenuReceiver {
    protected static final int CONNECTION_ERROR_DIALOG_ID = UniqueCodeUtil.nextUniqueCode();
    public static final Uri CONTENT_URI = Uri.parse("content://com.amazon.mp3.Store/store/search");
    protected ActionBarProvider mActionBarProvider;
    private Intent mIntent;
    protected long mLastRetryTime = 0;
    private Runnable mShowConnectionErrorDialogRunnable = new Runnable() { // from class: com.amazon.mp3.store.fragment.StoreBaseFragment.1
        @Override // java.lang.Runnable
        public void run() {
            if (StoreBaseFragment.this.isPaused()) {
                return;
            }
            StoreBaseFragment.this.mLastRetryTime = System.currentTimeMillis();
            BaseActivity baseActivity = (BaseActivity) StoreBaseFragment.this.getActivity();
            StoreBaseFragment storeBaseFragment = StoreBaseFragment.this;
            storeBaseFragment.showDialog(StoreBaseFragment.CONNECTION_ERROR_DIALOG_ID, NetworkErrorDialog.create(baseActivity, storeBaseFragment));
        }
    };

    protected long getRetryThrottleDuration() {
        return 10000L;
    }

    @Override // com.amazon.mp3.store.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onRetryConnection() {
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mIntent != null || savedInstanceState == null) {
            return;
        }
        this.mIntent = (Intent) savedInstanceState.getParcelable("com.amazon.mp3.library.EXTRA_INTENT");
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("com.amazon.mp3.library.EXTRA_INTENT", this.mIntent);
    }

    @Override // com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver
    public void setActionBarProvider(ActionBarProvider provider) {
        this.mActionBarProvider = provider;
    }

    @Override // com.amazon.mp3.store.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
    public void onConnectionErrorDialogCancel() {
        getActivity().finish();
    }

    protected void showConnectionErrorDialog() {
        this.mHandler.postDelayed(this.mShowConnectionErrorDialogRunnable, getRetryThrottleDuration() - (System.currentTimeMillis() - this.mLastRetryTime));
    }
}
