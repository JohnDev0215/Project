package com.amazon.mp3.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.service.job.JobSessionFragment;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class BaseFragment extends JobSessionFragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    protected Handler mHandler = new Handler();
    private boolean mViewCreated = false;
    private boolean mDestroyed = false;
    private boolean mActivated = false;
    private Context mContext = AmazonApplication.getContext();

    public void onActivated() {
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onDeactivated() {
    }

    public boolean onSearchRequested() {
        return false;
    }

    public void onWindowFocusChanged(boolean hasFocus) {
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(final Bundle instanceState) {
        super.onCreate(instanceState);
        this.mViewCreated = false;
        this.mDestroyed = false;
        this.mActivated = false;
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mDestroyed = true;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        setActivated(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mViewCreated = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mViewCreated = false;
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setActivated(getUserVisibleHint());
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.mViewCreated) {
            setActivated(isVisibleToUser);
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        setActivated(getUserVisibleHint());
    }

    public boolean isActivated() {
        return this.mActivated;
    }

    private void setActivated(boolean activated) {
        boolean z = this.mActivated;
        this.mActivated = activated;
        if (activated && !z) {
            onActivated();
        } else {
            if (activated || !z) {
                return;
            }
            onDeactivated();
        }
    }

    protected boolean isDestroyed() {
        return this.mDestroyed;
    }

    protected void registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        this.mContext.registerReceiver(receiver, filter);
    }

    protected void unregisterReceiver(BroadcastReceiver receiver) {
        try {
            this.mContext.unregisterReceiver(receiver);
        } catch (IllegalArgumentException unused) {
            Log.debug(TAG, "failed to unregister receiver: " + receiver.getClass().toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Context getApplication() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ContentResolver getContentResolver() {
        return this.mContext.getContentResolver();
    }

    protected void showDialog(int id, Dialog dialog) {
        ((BaseActivity) getActivity()).showFragmentDialog(id, dialog);
    }
}
