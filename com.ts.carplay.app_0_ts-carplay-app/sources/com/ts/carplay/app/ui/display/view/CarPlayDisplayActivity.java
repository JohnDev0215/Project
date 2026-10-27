package com.ts.carplay.app.ui.display.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.ts.carplay.app.R;
import com.ts.carplay.app.ui.display.constants.DisplayConstants;
import com.ts.carplay.app.ui.display.presenter.DisplayPresenter;
import com.ts.carplay.app.ui.display.view.DisplayContract;
import com.ts.carplay.utils.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayDisplayActivity extends Activity implements DisplayContract.DisplayView {
    private static final String BROADCAST_FINISH_CARPLAY_ACTIVITY = "com.ts.carplay.action.FINISH_ACTIVITY";
    private static final int CANCEL = -1;
    private static final int MESSAGE_CHANGE_FRAGMENT = 0;
    private static final String TAG = CarPlayDisplayActivity.class.getSimpleName();
    private DisplayContract.Presenter mDisplayPresenter;
    private FinishActivityReceiver mFinishActivityReceiver;
    private BaseFragment mFragment;
    private DisplayConstants.FragmentType mCurFragmentType = DisplayConstants.FragmentType.DEFAULT_FRAGMENT;
    private Context mContext = null;
    private Handler mHandler = new Handler() { // from class: com.ts.carplay.app.ui.display.view.CarPlayDisplayActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                CarPlayDisplayActivity.this.showFragment((DisplayConstants.FragmentType) msg.obj);
            }
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        LogUtil.debug(TAG, "Lifecycle --- onCreate: " + this);
        this.mContext = this;
        registerFinishActivityReceiver();
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_display);
        this.mDisplayPresenter = new DisplayPresenter(this.mContext, this);
        initCarPlayManager();
        requestUi(getIntent().getAction());
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        LogUtil.debug(TAG, "Lifecycle --- onNewIntent: " + this);
        setIntent(intent);
        super.onNewIntent(intent);
        initCarPlayManager();
        requestUi(intent.getAction());
    }

    public void requestUi(String action) {
        if (action != null && action.equals("DeviceAsk")) {
            LogUtil.debug(TAG, "device ask for carplayUi");
        } else if (this.mDisplayPresenter == null) {
            LogUtil.debug(TAG, "mDisplayPresenter == null,return");
        } else {
            LogUtil.debug(TAG, "accessory ask for carplayUi");
            this.mDisplayPresenter.requestUi(0);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        LogUtil.debug(TAG, "Lifecycle --- onStart: " + this);
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
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LogUtil.debug(TAG, "Lifecycle --- onWindowFocusChanged hasFocus: " + hasFocus);
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
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.debug(TAG, "Lifecycle --- onDestroy: " + this);
        DisplayContract.Presenter presenter = this.mDisplayPresenter;
        if (presenter != null) {
            presenter.release(this.mContext);
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        unregisterFinishActivityReceiver();
    }

    @Override // com.ts.carplay.app.ui.display.view.DisplayContract.DisplayView
    public void onStatusChanged(int status) {
        LogUtil.debug(TAG, "onStatusChanged --- status: " + status);
        DisplayConstants.FragmentType type = switchFragmentType(status);
        if (DisplayConstants.FragmentType.DEFAULT_FRAGMENT == type) {
            LogUtil.debug(TAG, "onStatusChanged --- return");
        } else {
            changeFragment(type);
        }
    }

    public DisplayConstants.FragmentType switchFragmentType(int linkStatus) {
        LogUtil.info(TAG, "switchFragmentType --- linkStatus: " + linkStatus);
        DisplayConstants.FragmentType type = DisplayConstants.FragmentType.DEFAULT_FRAGMENT;
        if (linkStatus == 2) {
            return DisplayConstants.FragmentType.DISPLAY_PROJECTION;
        }
        if ((linkStatus == 3 || linkStatus == 4) && !isFinishing()) {
            finish();
            return type;
        }
        return type;
    }

    @Override // com.ts.carplay.app.ui.display.view.DisplayContract.DisplayView
    public void requestVideoFocus(int requestType) {
        LogUtil.debug(TAG, "requestVideoFocus type: " + requestType);
        if (requestType == 1 || requestType == 2) {
            LogUtil.debug(TAG, "requestVideoFocus finish the activity");
            if (!isFinishing()) {
                finish();
            }
        }
    }

    private void initCarPlayManager() {
        LogUtil.debug(TAG, "initDeviceMirrorManager --- mDisplayPresenter: " + this.mDisplayPresenter);
        DisplayContract.Presenter presenter = this.mDisplayPresenter;
        if (presenter != null) {
            presenter.initManager(this.mContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFragment(DisplayConstants.FragmentType fragmentType) {
        LogUtil.info(TAG, "showFragment --- fragmentType: " + fragmentType);
        if (fragmentType == DisplayConstants.FragmentType.DEFAULT_FRAGMENT) {
            return;
        }
        if (fragmentType == this.mCurFragmentType) {
            LogUtil.debug(TAG, "showFragment --- fragmentType equals mCurFragmentType");
            return;
        }
        if (AnonymousClass2.$SwitchMap$com$ts$carplay$app$ui$display$constants$DisplayConstants$FragmentType[fragmentType.ordinal()] == 1) {
            CarPlayDisplayFragment carPlayDisplayFragment = new CarPlayDisplayFragment();
            this.mFragment = carPlayDisplayFragment;
            carPlayDisplayFragment.setPresenter(this.mDisplayPresenter);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment, this.mFragment);
            fragmentTransaction.commitAllowingStateLoss();
            this.mCurFragmentType = fragmentType;
            return;
        }
        LogUtil.debug(TAG, "showFragment --- default return");
    }

    /* JADX INFO: renamed from: com.ts.carplay.app.ui.display.view.CarPlayDisplayActivity$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$carplay$app$ui$display$constants$DisplayConstants$FragmentType;

        static {
            int[] iArr = new int[DisplayConstants.FragmentType.values().length];
            $SwitchMap$com$ts$carplay$app$ui$display$constants$DisplayConstants$FragmentType = iArr;
            try {
                iArr[DisplayConstants.FragmentType.DISPLAY_PROJECTION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public void changeFragment(DisplayConstants.FragmentType fragmentType) {
        LogUtil.info(TAG, "changeFragment --- fragmentType: " + fragmentType);
        if (fragmentType != DisplayConstants.FragmentType.DEFAULT_FRAGMENT && this.mHandler != null) {
            Message message = new Message();
            message.what = 0;
            message.obj = fragmentType;
            this.mHandler.sendMessage(message);
        }
    }

    private void registerFinishActivityReceiver() {
        this.mFinishActivityReceiver = new FinishActivityReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_FINISH_CARPLAY_ACTIVITY);
        registerReceiver(this.mFinishActivityReceiver, intentFilter);
    }

    private void unregisterFinishActivityReceiver() {
        FinishActivityReceiver finishActivityReceiver = this.mFinishActivityReceiver;
        if (finishActivityReceiver != null) {
            unregisterReceiver(finishActivityReceiver);
            this.mFinishActivityReceiver = null;
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        LogUtil.info(TAG, "finish");
        BaseFragment baseFragment = this.mFragment;
        if (baseFragment != null) {
            baseFragment.onFinish();
        }
    }

    private class FinishActivityReceiver extends BroadcastReceiver {
        private FinishActivityReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.info(CarPlayDisplayActivity.TAG, "onReceive broadcast action: " + action);
            if (!CarPlayDisplayActivity.this.isFinishing()) {
                CarPlayDisplayActivity.this.finish();
            }
        }
    }
}
