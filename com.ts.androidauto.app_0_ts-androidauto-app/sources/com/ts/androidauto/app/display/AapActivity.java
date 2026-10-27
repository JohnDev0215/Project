package com.ts.androidauto.app.display;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.ts.androidauto.app.R;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.display.DisplayContract;
import com.ts.androidauto.app.display.presenter.DisplayPresenter;
import ts.car.service.androidauto.data.VehicleData;

/* JADX INFO: loaded from: classes2.dex */
public class AapActivity extends Activity implements DisplayContract.DisplayView {
    private static final int AAP_DEFAULT_HEIGHT = 480;
    private static final int AAP_DEFAULT_WIDTH = 800;
    private static final int AAP_DELAY_REMOVE_COVER_MESSAGE = 3;
    private static final int AAP_DELAY_REMOVE_COVER_TIME = 500;
    private static final int AAP_DISPLAY_MODE_1080P = 2;
    private static final int AAP_DISPLAY_MODE_480P = 0;
    private static final int AAP_DISPLAY_MODE_720P = 1;
    private static final int AAP_SET_SCREEN_LANDSCAPE_MODE = 5;
    private static final int AAP_SHOW_PROJECTION_MESSAGE = 4;
    private static final int LANDSCAPE_MODE = 1;
    private static final String RECEIVER_FINISH_ACTION = "com.ts.androidauto.app.display.aapactivity.RECEIVER_FINISH_ACTION";
    private static final String TAG = "AAP" + AapActivity.class.getSimpleName();
    private ImageView mCoverView;
    private DisplayContract.Presenter mDisplayPresenter;
    private HorizontalScrollView mHorizontalScrollView;
    private ScrollView mScrollView;
    private SurfaceView mSurfaceProjection;
    private int mAapDisplayMode = 2;
    private int mScreenWidth = -1;
    private int mScreenHeight = -1;
    private int mScrollVerticalLength = 0;
    private int mScrollHorizontalLength = 0;
    private VehicleData mVehicleInfo = null;
    private Context mContext = null;
    private boolean isRelease = false;
    private boolean isHideSurface = false;
    private boolean isAlreadyShow = false;
    private LinearLayout mDisplayArea = null;
    private Surface mDisplaySurface = null;
    private Object mLock = new Object();
    private boolean mIsRegisterReceiver = false;
    private boolean mIsenableProjectionMode = false;
    private boolean mIsAlreadyInit = false;
    private boolean mIsOnPause = false;
    private boolean mIsPhoneRequestHide = false;
    private int mAapDisplayAreaWidth = 800;
    private int mAapDisplayAreaHeight = 480;
    private int mAapDisplayAreaMarginLeft = 0;
    private int mAapDisplayAreaMarginTop = 0;
    private Handler mHandler = new Handler() { // from class: com.ts.androidauto.app.display.AapActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i == 0) {
                AapActivity.this.mAapDisplayMode = 0;
                if (AapActivity.this.mVehicleInfo != null) {
                    AapActivity aapActivity = AapActivity.this;
                    aapActivity.updateDisplayParams(aapActivity.mVehicleInfo.mAapVideoConfig480p);
                    return;
                }
                return;
            }
            if (i == 1) {
                AapActivity.this.mAapDisplayMode = 1;
                if (AapActivity.this.mVehicleInfo != null) {
                    AapActivity aapActivity2 = AapActivity.this;
                    aapActivity2.updateDisplayParams(aapActivity2.mVehicleInfo.mAapVideoConfig720p);
                    return;
                }
                return;
            }
            if (i == 2) {
                AapActivity.this.mAapDisplayMode = 2;
                if (AapActivity.this.mVehicleInfo != null) {
                    AapActivity aapActivity3 = AapActivity.this;
                    aapActivity3.updateDisplayParams(aapActivity3.mVehicleInfo.mAapVideoConfig1080p);
                    return;
                }
                return;
            }
            if (i == 3) {
                LogUtil.debug(AapActivity.TAG, "Delay remove cover view");
                AapActivity.this.setShowVisibility(3, false);
            } else {
                if (i != 4) {
                    if (i == 5 && AapActivity.this.mDisplayPresenter != null) {
                        LogUtil.debug(AapActivity.TAG, "onResume force set screen to landscape");
                        AapActivity.this.mDisplayPresenter.setScreenRotationS(1);
                        return;
                    }
                    return;
                }
                AapActivity.this.handleShowProjection(msg.arg1);
            }
        }
    };
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.ts.androidauto.app.display.AapActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.debug(AapActivity.TAG, "onReceive --- intent: " + intent);
            if (AapActivity.RECEIVER_FINISH_ACTION.equals(intent.getAction())) {
                LogUtil.debug(AapActivity.TAG, "onReceive --- isRelease: " + AapActivity.this.isRelease);
                AapActivity.this.releaseSurface();
                AapActivity.this.finish();
            }
        }
    };
    private final SurfaceHolder.Callback mSurfaceHolderCallback = new SurfaceHolder.Callback() { // from class: com.ts.androidauto.app.display.AapActivity.5
        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder holder) {
            LogUtil.debug(AapActivity.TAG, "mSurfaceHolderCallback---surfaceCreated");
            AapActivity.this.isRelease = false;
            AapActivity.this.mDisplaySurface = holder.getSurface();
            synchronized (AapActivity.this.mLock) {
                AapActivity.this.mScreenWidth = -1;
                AapActivity.this.mScreenHeight = -1;
                AapActivity.this.isAlreadyShow = false;
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            LogUtil.debug(AapActivity.TAG, "mSurfaceHolderCallback---surfaceChanged width: " + width + ", height: " + height);
            if (AapActivity.this.mDisplaySurface == null) {
                LogUtil.debug(AapActivity.TAG, "surfaceChanged create Surface");
                AapActivity.this.mDisplaySurface = holder.getSurface();
            }
            synchronized (AapActivity.this.mLock) {
                AapActivity.this.mScreenWidth = width;
                AapActivity.this.mScreenHeight = height;
                if (AapActivity.this.mDisplayPresenter != null && !AapActivity.this.isAlreadyShow) {
                    AapActivity.this.isAlreadyShow = AapActivity.this.mDisplayPresenter.show(AapActivity.this.mDisplaySurface, width, height);
                }
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder holder) {
            LogUtil.debug(AapActivity.TAG, "mSurfaceHolderCallback---surfaceDestroyed " + AapActivity.this.mIsPhoneRequestHide);
            synchronized (AapActivity.this.mLock) {
                if (AapActivity.this.mDisplayPresenter != null && !AapActivity.this.mIsPhoneRequestHide) {
                    AapActivity.this.mDisplayPresenter.notifySurfaceDestoryed();
                }
            }
            AapActivity.this.mDisplaySurface = null;
        }
    };
    private final View.OnTouchListener mOnTouchListener = new View.OnTouchListener() { // from class: com.ts.androidauto.app.display.AapActivity.6
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent event) {
            AapActivity.this.mSurfaceProjection.getParent().requestDisallowInterceptTouchEvent(true);
            if (AapActivity.this.mDisplayPresenter != null) {
                AapActivity.this.mDisplayPresenter.screenTouchEvent(event);
            }
            return true;
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        LogUtil.debug(TAG, "Lifecycle --- onCreate: " + this);
        LogUtil.debug(TAG, "Lifecycle --- onCreate isRelease: " + this.isRelease);
        LogUtil.debug(TAG, "Lifecycle --- onCreate mIsRegisterReceiver: " + this.mIsRegisterReceiver);
        getWindow().addFlags(512);
        requestWindowFeature(1);
        setContentView(R.layout.activity_display);
        this.mContext = this;
        this.mDisplayArea = (LinearLayout) findViewById(R.id.layout_display_area);
        this.mDisplayPresenter = new DisplayPresenter(this.mContext, this);
        initDisplayParams();
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontal_scrollView);
        this.mHorizontalScrollView = horizontalScrollView;
        horizontalScrollView.setOnTouchListener(new View.OnTouchListener() { // from class: com.ts.androidauto.app.display.AapActivity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });
        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_view);
        this.mScrollView = scrollView;
        scrollView.setOnTouchListener(new View.OnTouchListener() { // from class: com.ts.androidauto.app.display.AapActivity.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });
        this.mCoverView = (ImageView) findViewById(R.id.cover_projection);
        this.isAlreadyShow = false;
        init();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        LogUtil.debug(TAG, "Lifecycle --- onNewIntent: " + this);
        LogUtil.debug(TAG, "Lifecycle --- mIsRegisterReceiver: " + this.mIsRegisterReceiver);
        LogUtil.debug(TAG, "Lifecycle --- mIsAlreadyInit: " + this.mIsAlreadyInit);
        setIntent(intent);
        super.onNewIntent(intent);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        LogUtil.debug(TAG, "Lifecycle --- onStart: " + this);
        LogUtil.debug(TAG, "Lifecycle --- onStart. mIsRegisterReceiver: " + this.mIsRegisterReceiver);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        LogUtil.debug(TAG, "Lifecycle --- onRestart: " + this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        LogUtil.debug(TAG, "Lifecycle --- onResume: " + this);
        LogUtil.debug(TAG, "Lifecycle --- onResume mIsenableProjectionMode: " + this.mIsenableProjectionMode);
        sendEmptyMessageDelayed(3, 500L);
        init();
        synchronized (this.mLock) {
            if (this.mIsOnPause && this.mDisplayPresenter != null && !this.isAlreadyShow && this.mDisplaySurface != null && this.mScreenWidth > 0 && this.mScreenHeight > 0) {
                this.isAlreadyShow = this.mDisplayPresenter.show(this.mDisplaySurface, this.mScreenWidth, this.mScreenHeight);
            }
            if (!this.mIsenableProjectionMode) {
                LogUtil.debug(TAG, "onResume ---Update AA display mode: " + this.mAapDisplayMode);
                if (this.mAapDisplayMode >= 0 && this.mAapDisplayMode <= 2) {
                    Message message = Message.obtain();
                    message.what = this.mAapDisplayMode;
                    this.mHandler.sendMessage(message);
                }
                this.mIsenableProjectionMode = true;
            }
        }
        DisplayContract.Presenter presenter = this.mDisplayPresenter;
        if (presenter != null) {
            presenter.subscribeHmiKeys();
        }
        this.mHandler.sendEmptyMessage(5);
        this.mIsOnPause = false;
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        LogUtil.debug(TAG, "Lifecycle --- onPause: " + this);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        LogUtil.debug(TAG, "Lifecycle --- onStop: " + this);
        LogUtil.debug(TAG, "Lifecycle --- onStop isRelease: " + this.isRelease);
        LogUtil.debug(TAG, "Lifecycle --- onStop mIsRegisterReceiver: " + this.mIsRegisterReceiver);
        LogUtil.debug(TAG, "Lifecycle --- onStop mIsenableProjectionMode: " + this.mIsenableProjectionMode);
        onPauseSetVisibility();
        DisplayContract.Presenter presenter = this.mDisplayPresenter;
        if (presenter != null) {
            presenter.unsubscribeHmiKeys();
        }
        hideSurface();
        this.mIsOnPause = true;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.debug(TAG, "Lifecycle --- onDestroy " + this.isRelease + ", instance: " + this);
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Lifecycle --- mIsenableProjectionMode ");
        sb.append(this.mIsenableProjectionMode);
        LogUtil.debug(str, sb.toString());
        this.mIsPhoneRequestHide = false;
        synchronized (this.mLock) {
            if (this.mIsRegisterReceiver && this.mContext != null) {
                LogUtil.debug(TAG, "onDestroy unregisterReceiver Broadcast");
                this.mContext.unregisterReceiver(this.mBroadcastReceiver);
                this.mIsRegisterReceiver = false;
            }
            this.isAlreadyShow = false;
            this.mIsOnPause = false;
        }
    }

    @Override // com.ts.androidauto.app.display.BaseView
    public void setPresenter(DisplayContract.Presenter presenter) {
    }

    @Override // com.ts.androidauto.app.display.DisplayContract.DisplayView
    public void showProjectionScreen(int linkStatus) {
        LogUtil.debug(TAG, "showProjectionScreen --- isRelease: " + this.isRelease);
        LogUtil.debug(TAG, "showProjectionScreen --- isAlreadyShow: " + this.isAlreadyShow);
        LogUtil.info(TAG, "showProjectionScreen linkStatus=" + linkStatus);
        Message message = this.mHandler.obtainMessage(4);
        message.arg1 = linkStatus;
        this.mHandler.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleShowProjection(int linkStatus) {
        LogUtil.info(TAG, "handleShowProjection linkStatus=" + linkStatus);
        if (linkStatus == 4) {
            hideStatusNavigationBar();
            synchronized (this.mLock) {
                if (this.mDisplayPresenter != null && !this.isAlreadyShow && this.mDisplaySurface != null && this.mScreenWidth > 0 && this.mScreenHeight > 0) {
                    this.isAlreadyShow = this.mDisplayPresenter.show(this.mDisplaySurface, this.mScreenWidth, this.mScreenHeight);
                }
            }
            return;
        }
        releaseSurface();
        finish();
    }

    @Override // com.ts.androidauto.app.display.DisplayContract.DisplayView
    public void requestVideoFocus(int requestType) {
        LogUtil.debug(TAG, "requestVideoFocus type: " + requestType + " isRelease:" + this.isRelease);
        if (requestType == 0) {
            LogUtil.debug(TAG, "requestVideoFocus finish the activity");
            this.mIsPhoneRequestHide = true;
            releaseSurface();
            finish();
            return;
        }
        if (requestType == 1) {
            Message message = Message.obtain();
            message.what = 0;
            this.mHandler.sendMessage(message);
        } else if (requestType == 2) {
            Message message2 = Message.obtain();
            message2.what = 1;
            this.mHandler.sendMessage(message2);
        } else if (requestType == 3) {
            Message message3 = Message.obtain();
            message3.what = 2;
            this.mHandler.sendMessage(message3);
        }
    }

    private void hideSurface() {
        LogUtil.debug(TAG, "hideSurface isHideSurface:" + this.isHideSurface);
        if (!this.isHideSurface) {
            DisplayContract.Presenter presenter = this.mDisplayPresenter;
            if (presenter != null) {
                presenter.hide();
                this.isAlreadyShow = false;
            }
            this.isHideSurface = true;
        }
        if (this.mDisplayPresenter != null) {
            LogUtil.debug(TAG, "Display Presenter is release!");
            this.mDisplayPresenter.release();
        }
        this.mIsAlreadyInit = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseSurface() {
        LogUtil.debug(TAG, "releaseSurface isReleas=" + this.isRelease + ", isHideSurface=" + this.isHideSurface);
        if (!this.isRelease) {
            if (this.mDisplaySurface != null) {
                LogUtil.debug(TAG, "skip Surface is release!");
                this.mDisplaySurface = null;
            }
            if (!this.isHideSurface) {
                DisplayContract.Presenter presenter = this.mDisplayPresenter;
                if (presenter != null) {
                    presenter.hide();
                    this.isAlreadyShow = false;
                }
                this.isHideSurface = true;
            }
            this.isRelease = true;
            this.mIsAlreadyInit = false;
        }
    }

    private void init() {
        LogUtil.debug(TAG, "init --- mIsAlreadyInit: " + this.mIsAlreadyInit);
        LogUtil.debug(TAG, "init --- mIsRegisterReceiver: " + this.mIsRegisterReceiver);
        if (!this.mIsAlreadyInit) {
            synchronized (this.mLock) {
                this.isAlreadyShow = false;
                this.mDisplayPresenter.init();
                this.mIsenableProjectionMode = false;
                if (!this.mIsRegisterReceiver) {
                    IntentFilter onFinishIntentFilter = new IntentFilter(RECEIVER_FINISH_ACTION);
                    this.mContext.registerReceiver(this.mBroadcastReceiver, onFinishIntentFilter);
                    this.mIsRegisterReceiver = true;
                }
            }
            this.isHideSurface = false;
            this.isRelease = false;
            this.mIsAlreadyInit = true;
            SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surface_projection);
            this.mSurfaceProjection = surfaceView;
            surfaceView.setOnTouchListener(this.mOnTouchListener);
            this.mSurfaceProjection.getHolder().addCallback(this.mSurfaceHolderCallback);
            setDisplayParams();
        }
    }

    private void initDisplayParams() {
        DisplayContract.Presenter presenter = this.mDisplayPresenter;
        if (presenter != null) {
            VehicleData vehicleInfo = presenter.getVehicleInfo();
            this.mVehicleInfo = vehicleInfo;
            if (vehicleInfo != null) {
                LogUtil.debug(TAG, "initDisplayParams vehicleInfo OK.");
                this.mAapDisplayAreaWidth = this.mVehicleInfo.mAapVideoConfig1080p.mAapDisplayAreaWidth;
                this.mAapDisplayAreaHeight = this.mVehicleInfo.mAapVideoConfig1080p.mAapDisplayAreaHeight;
                this.mAapDisplayAreaMarginLeft = this.mVehicleInfo.mAapVideoConfig1080p.mAapDisplayAreaMarginLeft;
                this.mAapDisplayAreaMarginTop = this.mVehicleInfo.mAapVideoConfig1080p.mAapDisplayAreaMarginTop;
            }
        }
    }

    private void setDisplayParams() {
        LogUtil.debug(TAG, "setDisplayParams enter.");
        LinearLayout linearLayout = this.mDisplayArea;
        if (linearLayout == null) {
            LogUtil.debug(TAG, "mDisplayArea == null ");
            return;
        }
        if (this.mSurfaceProjection == null) {
            LogUtil.debug(TAG, "mSurfaceProjection == null ");
            return;
        }
        if (this.mCoverView == null) {
            LogUtil.debug(TAG, "mCoverView == null ");
            return;
        }
        FrameLayout.LayoutParams displayParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
        FrameLayout.LayoutParams surfaceParams = (FrameLayout.LayoutParams) this.mSurfaceProjection.getLayoutParams();
        if (displayParams == null) {
            LogUtil.debug(TAG, "displayParams == null ");
            return;
        }
        if (surfaceParams == null) {
            LogUtil.debug(TAG, "surfaceParams == null ");
            return;
        }
        displayParams.width = this.mAapDisplayAreaWidth;
        displayParams.height = this.mAapDisplayAreaHeight;
        surfaceParams.width = this.mAapDisplayAreaWidth;
        surfaceParams.height = this.mAapDisplayAreaHeight;
        displayParams.setMargins(this.mAapDisplayAreaMarginLeft, this.mAapDisplayAreaMarginTop, 0, 0);
        this.mDisplayArea.setLayoutParams(displayParams);
        this.mSurfaceProjection.setLayoutParams(surfaceParams);
        this.mCoverView.setLayoutParams(surfaceParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDisplayParams(VehicleData.AapVideoConfig info) {
        FrameLayout.LayoutParams params;
        FrameLayout.LayoutParams displayParams;
        if (info != null) {
            this.mAapDisplayAreaWidth = info.mAapDisplayAreaWidth;
            this.mAapDisplayAreaHeight = info.mAapDisplayAreaHeight;
            this.mAapDisplayAreaMarginLeft = info.mAapDisplayAreaMarginLeft;
            this.mAapDisplayAreaMarginTop = info.mAapDisplayAreaMarginTop;
            this.mScrollHorizontalLength = info.mAapSurfaceViewScrollX;
            this.mScrollVerticalLength = info.mAapSurfaceViewScrollY;
            LinearLayout linearLayout = this.mDisplayArea;
            if (linearLayout != null && (displayParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams()) != null) {
                displayParams.width = this.mAapDisplayAreaWidth;
                displayParams.height = this.mAapDisplayAreaHeight;
                displayParams.setMargins(this.mAapDisplayAreaMarginLeft, this.mAapDisplayAreaMarginTop, 0, 0);
                this.mDisplayArea.setLayoutParams(displayParams);
            }
            SurfaceView surfaceView = this.mSurfaceProjection;
            if (surfaceView != null && this.mCoverView != null && (params = (FrameLayout.LayoutParams) surfaceView.getLayoutParams()) != null) {
                params.width = info.mAapSurfaceViewWidth;
                params.height = info.mAapSurfaceViewHeight;
                this.mSurfaceProjection.setLayoutParams(params);
                this.mCoverView.setLayoutParams(params);
            }
            if (this.mScrollView != null) {
                LogUtil.debug(TAG, "Scroll up " + this.mScrollVerticalLength + " pixels");
                this.mScrollView.post(new Runnable() { // from class: com.ts.androidauto.app.display.AapActivity.7
                    @Override // java.lang.Runnable
                    public void run() {
                        AapActivity.this.mScrollView.smoothScrollTo(0, AapActivity.this.mScrollVerticalLength);
                    }
                });
            }
            if (this.mHorizontalScrollView != null) {
                LogUtil.debug(TAG, "Horizontal Scroll up " + this.mScrollHorizontalLength + " pixels");
                this.mHorizontalScrollView.post(new Runnable() { // from class: com.ts.androidauto.app.display.AapActivity.8
                    @Override // java.lang.Runnable
                    public void run() {
                        AapActivity.this.mHorizontalScrollView.smoothScrollTo(AapActivity.this.mScrollHorizontalLength, 0);
                    }
                });
            }
        }
    }

    private void sendEmptyMessageDelayed(int what, long delayTime) {
        if (this.mHandler != null) {
            LogUtil.info(TAG, "sendEmptyMessageDelayed what:" + what);
            this.mHandler.sendEmptyMessageDelayed(what, delayTime);
        }
    }

    private void removeMessage(int what) {
        Handler handler = this.mHandler;
        if (handler != null && handler.hasMessages(what)) {
            LogUtil.info(TAG, "removeMessage what:" + what);
            this.mHandler.removeMessages(what);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setShowVisibility(int type, boolean visible) {
        ImageView imageView;
        LogUtil.debug(TAG, "setShowVisibility type " + type + " visible " + visible);
        if (type != 2) {
            if (type == 3 && (imageView = this.mCoverView) != null) {
                if (visible) {
                    imageView.setVisibility(0);
                    return;
                } else {
                    imageView.setVisibility(4);
                    return;
                }
            }
            return;
        }
        SurfaceView surfaceView = this.mSurfaceProjection;
        if (surfaceView != null) {
            if (visible) {
                surfaceView.setVisibility(0);
            } else {
                surfaceView.setVisibility(4);
            }
        }
    }

    private void onPauseSetVisibility() {
        LogUtil.debug(TAG, "onPauseSetVisibility start");
        removeMessage(3);
        setShowVisibility(3, true);
        LogUtil.debug(TAG, "onPauseSetVisibility end");
    }

    private void hideStatusNavigationBar() {
        LogUtil.debug(TAG, "not hideStatusNavigationBar");
    }
}
