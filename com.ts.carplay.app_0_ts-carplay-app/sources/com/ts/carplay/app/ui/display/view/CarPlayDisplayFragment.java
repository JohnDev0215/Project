package com.ts.carplay.app.ui.display.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ts.carplay.app.R;
import com.ts.carplay.app.service.manager.CarPlayRemoteManager;
import com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener;
import com.ts.carplay.utils.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayDisplayFragment extends BaseFragment {
    private static final int SCREEN_WIGHT = 1920;
    private static final int SURFACE_HEIGHT = 906;
    private static final int SURFACE_WIGHT = 1920;
    private static final String TAG = CarPlayDisplayFragment.class.getSimpleName();
    private static volatile CarPlayDisplayFragment mCurrentFragment = null;
    private CarPlayRemoteManager mCarPlayRemoteManager;
    private ImageView mCarlifeLogoImageView;
    private LinearLayout mCarlifeLogoLayout;
    private int mCurrentIndex;
    private SurfaceView mSurfaceView;
    private Surface mDisplaySurface = null;
    private volatile boolean mHasShown = false;
    private int mDeviceMode = 0;
    boolean mIsLastMoving = false;
    private ICarPlayRemoteManagerListener mCarPlayRemoteManagerListener = new CarPlayRemoteManagerListener();
    private final View.OnTouchListener mOnTouchListener = new View.OnTouchListener() { // from class: com.ts.carplay.app.ui.display.view.CarPlayDisplayFragment.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent event) {
            if (event != null) {
                if (2 == event.getAction()) {
                    if (!CarPlayDisplayFragment.this.mIsLastMoving) {
                        CarPlayDisplayFragment.this.mIsLastMoving = true;
                    } else {
                        CarPlayDisplayFragment.this.mIsLastMoving = false;
                        return true;
                    }
                } else {
                    CarPlayDisplayFragment.this.mIsLastMoving = false;
                }
            }
            if (CarPlayDisplayFragment.this.mDisplayPresenter != null) {
                CarPlayDisplayFragment.this.mDisplayPresenter.screenTouchEvent(event);
            }
            return true;
        }
    };
    private final SurfaceHolder.Callback mSurfaceHolderCallback = new SurfaceHolder.Callback() { // from class: com.ts.carplay.app.ui.display.view.CarPlayDisplayFragment.2
        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder holder) {
            LogUtil.debug(CarPlayDisplayFragment.TAG, "mSurfaceHolderCallback---surfaceCreated");
            if (CarPlayDisplayFragment.this.mDisplaySurface != null) {
                CarPlayDisplayFragment.this.mDisplaySurface.release();
            }
            CarPlayDisplayFragment.this.mDisplaySurface = holder.getSurface();
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            LogUtil.debug(CarPlayDisplayFragment.TAG, "mSurfaceHolderCallback---surfaceChanged width: " + width + ", height: " + height + " mHasShown: " + CarPlayDisplayFragment.this.mHasShown);
            if (CarPlayDisplayFragment.this.mDisplaySurface == null) {
                LogUtil.debug(CarPlayDisplayFragment.TAG, "surfaceChanged create Surface");
                CarPlayDisplayFragment.this.mDisplaySurface = holder.getSurface();
            }
            if (CarPlayDisplayFragment.this.shouldSetRender()) {
                CarPlayDisplayFragment carPlayDisplayFragment = CarPlayDisplayFragment.this;
                carPlayDisplayFragment.mHasShown = carPlayDisplayFragment.mDisplayPresenter.show(CarPlayDisplayFragment.this.mDisplaySurface, 1920, height);
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder holder) {
            LogUtil.debug(CarPlayDisplayFragment.TAG, "mSurfaceHolderCallback---surfaceDestroyed");
            if (CarPlayDisplayFragment.this.mDisplaySurface != null) {
                CarPlayDisplayFragment.this.mDisplaySurface.release();
                CarPlayDisplayFragment.this.mDisplaySurface = null;
            }
            CarPlayDisplayFragment.this.clearSurface();
        }
    };

    @Override // com.ts.carplay.app.ui.display.view.BaseFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentFragment = this;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_surface, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        LogUtil.debug(TAG, "initView");
        SurfaceView surfaceView = (SurfaceView) view.findViewById(R.id.surface_projection);
        this.mSurfaceView = surfaceView;
        if (surfaceView != null && surfaceView.getHolder() != null) {
            if (getActivity().getRequestedOrientation() != 0) {
                this.mCurrentIndex = 1;
            } else {
                this.mCurrentIndex = 0;
            }
            this.mSurfaceView.getHolder().setFixedSize(1920, 906);
            this.mSurfaceView.getHolder().addCallback(this.mSurfaceHolderCallback);
            this.mSurfaceView.setOnTouchListener(this.mOnTouchListener);
        }
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.debug(TAG, "onDestroyView mSurfaceHolderCallback");
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView != null && surfaceView.getHolder() != null) {
            this.mSurfaceView.getHolder().removeCallback(this.mSurfaceHolderCallback);
        }
        this.mSurfaceView = null;
        CarPlayRemoteManager carPlayRemoteManager = this.mCarPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.removeCarPlayRemoteManagerListener(this.mCarPlayRemoteManagerListener);
            this.mCarPlayRemoteManager = null;
        }
    }

    @Override // com.ts.carplay.app.ui.display.view.BaseFragment, android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (mCurrentFragment == this) {
            mCurrentFragment = null;
        }
    }

    @Override // com.ts.carplay.app.ui.display.view.BaseFragment
    public void onFinish() {
        LogUtil.debug(TAG, "onFinish");
        clearSurface();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldSetRender() {
        return (this.mDisplayPresenter == null || this.mHasShown || getActivity().isFinishing() || getActivity().isDestroyed()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSurface() {
        LogUtil.debug(TAG, "clearSurface mCurrentFragment: " + mCurrentFragment + " this: " + this);
        if (this != mCurrentFragment) {
            LogUtil.info(TAG, "clearSurface, do not need hide surface");
            return;
        }
        if (this.mDisplayPresenter != null) {
            this.mDisplayPresenter.hide();
        }
        this.mHasShown = false;
    }

    private class CarPlayRemoteManagerListener implements ICarPlayRemoteManagerListener {
        private CarPlayRemoteManagerListener() {
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onRequestViewArea(int index) {
            LogUtil.info(CarPlayDisplayFragment.TAG, "onRequestViewArea index: " + index + " mCurrentIndex:" + CarPlayDisplayFragment.this.mCurrentIndex);
            if (CarPlayDisplayFragment.this.mCurrentIndex != index) {
                CarPlayDisplayFragment.this.mCurrentIndex = index;
                CarPlayDisplayFragment.this.getActivity().setRequestedOrientation(index == 0 ? 2 : 1);
            }
        }
    }
}
