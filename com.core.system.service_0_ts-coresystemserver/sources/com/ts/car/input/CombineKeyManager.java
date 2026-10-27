package com.ts.car.input;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CombineKeyManager {
    private static final String COMBINE_THREAD = "combine_thread";
    private static final String DISPLAY = "display";
    private static final long DOWNTIME_RESET = 0;
    private static final String KEY = "key";
    private static final int MIN_COMBINE_KEY_NUM = 2;
    private static final int MSG_FIRST_COMBINE_KEY_DOWN = 2000;
    private static final int MSG_FIRST_COMBINE_KEY_UP = 2100;
    private static final String TAG = "HardKeyService-CombineKeyManager";
    private long combineKey1DownTime;
    private boolean combineKey2Pressed;
    private boolean mCombineKey1Pressed;
    private boolean mCombineKeyTimeoutReached;
    private IKeyEventAvailableListener mKeyEventAvailableListener;
    private Handler mProcessHandler;
    private HandlerThread mProcessThread;
    private int mTransferKeyCode = -1;
    private List<Integer> mCombineKeyDownList = new ArrayList();
    private List<Integer> mCombineKeyUpList = new ArrayList();
    private List<KeyCodeCombine> keyCodeCombineConfigList = ConfigParser.getConfiguration().getKeyCodeCombineList();
    List<KeyCodeCombine> preJudgeList = new ArrayList();

    public interface IKeyEventAvailableListener {
        void onKeyAvailable(KeyEvent keyEvent, int i, boolean z);
    }

    public synchronized void init(IKeyEventAvailableListener listener) {
        this.mKeyEventAvailableListener = listener;
        if (this.mProcessThread == null) {
            HandlerThread handlerThread = new HandlerThread(COMBINE_THREAD);
            this.mProcessThread = handlerThread;
            handlerThread.start();
            this.mProcessHandler = new ProcessHandler(this.mProcessThread.getLooper());
        }
    }

    public synchronized void prepareForCombineKey(KeyEvent keyEvent, int targetDisplay) {
        int action = keyEvent.getAction();
        LogUtils.log(TAG, "prepareForCombineKey keyEvent: " + keyEvent.toString());
        if (action == 0) {
            prepareForCombineKeyDown(keyEvent, targetDisplay);
        } else if (action == 1) {
            prepareForCombineKeyUp(keyEvent, targetDisplay);
        } else {
            LogUtils.logw(TAG, "illegal key action:" + keyEvent.toString());
        }
    }

    private void prepareForCombineKeyDown(KeyEvent keyEvent, int targetDisplay) {
        int keycode = keyEvent.getKeyCode();
        for (KeyCodeCombine keyCodeCombine : this.keyCodeCombineConfigList) {
            List<Integer> combineCodeList = keyCodeCombine.getCombineKeyList();
            int preJudgeListSize = this.preJudgeList.size();
            if (combineCodeList.contains(Integer.valueOf(keycode)) && preJudgeListSize == 0) {
                List<Integer> expectedCodeList = new ArrayList<>();
                int preTransferCode = keyCodeCombine.getTransferCode();
                long preTimeout = keyCodeCombine.getCombineTimeout().longValue();
                expectedCodeList.addAll(combineCodeList);
                expectedCodeList.remove(Integer.valueOf(keycode));
                LogUtils.log(TAG, "prepareForCombineKeyDown preTransferCode: " + preTransferCode + ",preTimeout: " + preTimeout + ",expCode.size: " + expectedCodeList.size());
                KeyCodeCombine cob = new KeyCodeCombine(preTransferCode, Long.valueOf(preTimeout));
                cob.addCombineCodeList(expectedCodeList);
                this.preJudgeList.add(cob);
                if (!this.mCombineKeyDownList.contains(Integer.valueOf(keycode))) {
                    this.mCombineKeyDownList.add(Integer.valueOf(keycode));
                    if (this.mCombineKey1Pressed) {
                        LogUtils.logw(TAG, "keycode :" + keyEvent.getKeyCode() + " in combine key list, and prejudge list is null ,but mCombineKey1Pressed is false,pls check code logic ");
                    } else {
                        this.mCombineKey1Pressed = true;
                        this.combineKey1DownTime = SystemClock.uptimeMillis();
                        LogUtils.log(TAG, "prepareForCombineKeyDown mCombineKey1Pressed combineKey1DownTime: " + this.combineKey1DownTime);
                        Message msg = this.mProcessHandler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(KEY, keyEvent);
                        bundle.putInt(DISPLAY, targetDisplay);
                        msg.what = 2000;
                        msg.obj = bundle;
                        this.mProcessHandler.sendMessageDelayed(msg, getIntervalOfCombineKeys());
                    }
                }
            } else if (preJudgeListSize > 0) {
                long now = SystemClock.uptimeMillis();
                LogUtils.log(TAG, "preJudgeList size >0, keycode: " + keycode + ",now: " + now);
                for (KeyCodeCombine p : this.preJudgeList) {
                    LogUtils.log(TAG, "KeyCodeCombine p: " + p);
                    if (p.getCombineKeyList().contains(Integer.valueOf(keycode))) {
                        LogUtils.log(TAG, "getCombineKeyList: " + p.getCombineKeyList());
                        if (now <= this.combineKey1DownTime + getIntervalOfCombineKeys() && !this.combineKey2Pressed) {
                            LogUtils.log(TAG, "preJudgeList has keycode: " + keycode);
                            if (this.mCombineKey1Pressed) {
                                this.mCombineKeyDownList.add(Integer.valueOf(keycode));
                                this.combineKey2Pressed = true;
                                int transferCode = keyCodeCombine.getTransferCode();
                                long timeout = keyCodeCombine.getCombineTimeout().longValue();
                                this.mProcessHandler.removeMessages(2000);
                                LogUtils.log(TAG, "transferCode : " + transferCode + ",timeout: " + timeout);
                                interceptForCombineKeyDown(transferCode, timeout, targetDisplay);
                                return;
                            }
                        }
                    }
                }
                if (!this.mCombineKeyDownList.contains(Integer.valueOf(keycode)) || now > this.combineKey1DownTime + getIntervalOfCombineKeys()) {
                    if (!ConfigParser.anyEmpty(this.mCombineKeyDownList)) {
                        LogUtils.logw(TAG, "The 2nd key of combine key did not pressed within valid time interval OR the 2nd key isn't combkey. key: " + keycode + ",OR other key" + keycode + "pressed after comb key down triggered");
                        if (!this.mCombineKeyTimeoutReached && this.mCombineKeyDownList.size() == 1) {
                            int key = this.mCombineKeyDownList.get(0).intValue();
                            resetAllCombineData();
                            LogUtils.log(TAG, "can not combine, pressed key: " + key + ",process it delay a while.");
                            KeyEvent preKeyEvent = mockKeyEvent(key, 0);
                            this.mKeyEventAvailableListener.onKeyAvailable(preKeyEvent, targetDisplay, false);
                        }
                    }
                    resetAllCombineData();
                    this.mKeyEventAvailableListener.onKeyAvailable(keyEvent, targetDisplay, false);
                }
            } else {
                continue;
            }
        }
        if (!this.mCombineKeyDownList.contains(Integer.valueOf(keycode))) {
            LogUtils.log(TAG, "keycode " + keycode + "not in any combine list");
            resetAllCombineData();
            this.mKeyEventAvailableListener.onKeyAvailable(keyEvent, targetDisplay, false);
        }
    }

    private void prepareForCombineKeyUp(KeyEvent keyEvent, int targetDisplay) {
        int keycode = keyEvent.getKeyCode();
        if (this.mCombineKeyTimeoutReached && this.mCombineKeyDownList.size() >= 2) {
            if (this.mCombineKeyDownList.contains(Integer.valueOf(keycode)) && !this.mCombineKeyUpList.contains(Integer.valueOf(keycode))) {
                this.mCombineKeyUpList.add(Integer.valueOf(keycode));
                LogUtils.log(TAG, "mCombineReleaseList add keycode: " + keycode);
                if (this.mCombineKeyUpList.size() < 2) {
                    Message msg = this.mProcessHandler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(KEY, keyEvent);
                    bundle.putInt(DISPLAY, targetDisplay);
                    msg.what = MSG_FIRST_COMBINE_KEY_UP;
                    msg.obj = bundle;
                    this.mProcessHandler.sendMessageDelayed(msg, getIntervalOfCombineKeys());
                    return;
                }
                if (isListEqual(this.mCombineKeyUpList, this.mCombineKeyDownList)) {
                    LogUtils.log(TAG, "combine key triggered,process it. keycode: " + keyEvent.getKeyCode() + ", action : " + keyEvent.getAction());
                    this.mProcessHandler.removeMessages(MSG_FIRST_COMBINE_KEY_UP);
                    KeyEvent combKeyEventUp = mockKeyEvent(this.mTransferKeyCode, 1);
                    this.mKeyEventAvailableListener.onKeyAvailable(combKeyEventUp, targetDisplay, true);
                    resetAllCombineData();
                    return;
                }
                return;
            }
            return;
        }
        LogUtils.log(TAG, "prepareForCombineKeyUp keycode: " + keycode + ",mCombinePressList.size():" + this.mCombineKeyDownList.size());
        if (this.mCombineKeyDownList.contains(Integer.valueOf(keycode)) && this.mCombineKeyDownList.size() < 2) {
            LogUtils.log(TAG, "prepareForCombineKeyUp only one comb key pressed");
            long now = SystemClock.uptimeMillis();
            int key = this.mCombineKeyDownList.get(0).intValue();
            if (now < this.combineKey1DownTime + getIntervalOfCombineKeys() && keycode == key) {
                LogUtils.log(TAG, "can not combine, pressed key: " + key + ",mock KeyEvent down");
                resetAllCombineData();
                KeyEvent preKeyEvent = mockKeyEvent(key, 0);
                this.mKeyEventAvailableListener.onKeyAvailable(preKeyEvent, targetDisplay, false);
            }
            resetAllCombineData();
        }
        this.mKeyEventAvailableListener.onKeyAvailable(keyEvent, targetDisplay, false);
    }

    private class ProcessHandler extends Handler {
        public ProcessHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            synchronized (CombineKeyManager.this) {
                Bundle bundle = (Bundle) msg.obj;
                KeyEvent keyEvent = (KeyEvent) bundle.getParcelable(CombineKeyManager.KEY);
                int targetDisplay = bundle.getInt(CombineKeyManager.DISPLAY);
                int i = msg.what;
                if (i == 2000) {
                    Log.d(CombineKeyManager.TAG, "combkey down not triggered within valid interval.");
                    CombineKeyManager.this.resetAllCombineData();
                    CombineKeyManager.this.mKeyEventAvailableListener.onKeyAvailable(keyEvent, targetDisplay, false);
                } else if (i == CombineKeyManager.MSG_FIRST_COMBINE_KEY_UP) {
                    Log.d(CombineKeyManager.TAG, "combkey up not triggerd within valid interval");
                    CombineKeyManager.this.resetAllCombineData();
                    CombineKeyManager.this.mKeyEventAvailableListener.onKeyAvailable(keyEvent, targetDisplay, false);
                } else {
                    CombineKeyManager.this.mTransferKeyCode = msg.what;
                    Log.d(CombineKeyManager.TAG, "combine key down time reached, transfer to: " + CombineKeyManager.this.mTransferKeyCode);
                    CombineKeyManager.this.mCombineKeyTimeoutReached = true;
                }
            }
        }
    }

    private void interceptForCombineKeyDown(int transferCode, long timeout, int targetDisplay) {
        KeyEvent keyEvent = mockKeyEvent(transferCode, 0);
        Message msg = this.mProcessHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY, keyEvent);
        bundle.putInt(DISPLAY, targetDisplay);
        msg.what = transferCode;
        msg.obj = bundle;
        this.mProcessHandler.sendMessageDelayed(msg, timeout);
        LogUtils.log(TAG, "interceptForCombineKeyDown");
    }

    private KeyEvent mockKeyEvent(int keycode, int action) {
        KeyEvent event = new KeyEvent(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), action, keycode, 0, 0, 0, 0, KeycodeUtil.FLAG_NOT_INTERCEPT, 1);
        return event;
    }

    private long getIntervalOfCombineKeys() {
        HashMap<String, String> prop = ConfigParser.getConfiguration().getPropertiesMap();
        if (prop != null) {
            return StringUtils.str2Long(prop.get(ConfigParser.COMBINE_KEY_INTERVAL)).longValue();
        }
        return -1L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetAllCombineData() {
        LogUtils.log(TAG, "resetAllCombineData");
        this.mCombineKeyDownList.clear();
        this.mCombineKeyUpList.clear();
        this.mCombineKey1Pressed = false;
        this.combineKey2Pressed = false;
        this.combineKey1DownTime = DOWNTIME_RESET;
        this.preJudgeList.clear();
        this.mProcessHandler.removeCallbacksAndMessages(null);
        this.mCombineKeyTimeoutReached = false;
        this.mTransferKeyCode = -1;
    }

    private boolean isListEqual(List list1, List list2) {
        if (ConfigParser.isEmpty(list1) && ConfigParser.isEmpty(list2)) {
            return true;
        }
        if (ConfigParser.isEmpty(list1) || ConfigParser.isEmpty(list2)) {
            return false;
        }
        if (list1 == list2) {
            return true;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        for (Object o : list1) {
            if (!list2.contains(o)) {
                return false;
            }
        }
        for (Object o2 : list2) {
            if (!list1.contains(o2)) {
                return false;
            }
        }
        return true;
    }
}
