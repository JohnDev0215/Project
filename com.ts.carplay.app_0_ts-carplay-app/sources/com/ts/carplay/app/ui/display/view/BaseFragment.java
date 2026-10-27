package com.ts.carplay.app.ui.display.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.ts.carplay.app.ui.display.view.DisplayContract;
import com.ts.carplay.utils.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    public CarPlayDisplayActivity mActivity;
    public int mDevMode = -1;
    public DisplayContract.Presenter mDisplayPresenter;

    @Override // android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = (CarPlayDisplayActivity) getActivity();
        LogUtil.debug(TAG, "onCreate: activity: " + this.mActivity + " Fragment: " + this);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.debug(TAG, "onResume: activity: " + this.mActivity + " Fragment: " + this);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        LogUtil.debug(TAG, "onPause: activity: " + this.mActivity + " Fragment: " + this);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        LogUtil.debug(TAG, "onStart: activity: " + this.mActivity + " Fragment: " + this);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        LogUtil.debug(TAG, "onStop: activity: " + this.mActivity + " Fragment: " + this);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LogUtil.debug(TAG, "onDestroy: activity: " + this.mActivity + " Fragment: " + this);
    }

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.debug(TAG, "onAttach: activity: " + this.mActivity + " Fragment: " + this);
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        LogUtil.debug(TAG, "onDetach: activity: " + this.mActivity + " Fragment: " + this);
        this.mActivity = null;
    }

    public void onFinish() {
    }

    public void setPresenter(DisplayContract.Presenter presenter) {
        LogUtil.debug(TAG, "setPresenter: " + presenter);
        this.mDisplayPresenter = presenter;
    }

    public void setHandler(Handler handler) {
    }
}
