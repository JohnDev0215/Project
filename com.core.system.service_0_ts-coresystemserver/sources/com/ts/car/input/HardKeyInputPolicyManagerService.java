package com.ts.car.input;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;
import com.core.system.foundationinterface.ICoreAdminService;
import com.core.system.foundationinterface.ICoreService;
import com.ts.car.input.CombineKeyManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ts.car.input.IHardKeyPolicyManager;
import ts.car.input.IKeyEventCallback;

/* JADX INFO: loaded from: classes.dex */
public class HardKeyInputPolicyManagerService extends IHardKeyPolicyManager.Stub implements ICoreService {
    private static final String CHECK_PERMISSION_PROP = "ro.config.skip_check";
    private static final String CORE_SERVICE_NAME = "input_policy";
    private static final String DESCRIPTOR = "ts.car.input.IHardKeyPolicyManager";
    private static final String DISPLAY = "display";
    private static final String HARDKEY_PERMISSION = "ts.permission.QUERY_KEYEVENT";
    private static final String HARDKEY_PERSSION = "android.car.permission.BIND_CAR_INPUT_SERVICE";
    private static final String KEY = "key";
    private static final String LONGPRESS_SPLITE = "longpress_split";
    private static final String LONG_CONTINUED_THREAD = "long_continued_thread";
    private static final String MAX_TIME = "max_time";
    private static final String NOTIN_LONGPRESS_CONFIG = "notin_longpress_config";
    private static final String POLICY = "policy";
    private static final String SCENE = "scene";
    private static final String TAG = "HardKeyService";
    private static final String TIME = "time";
    private static final int TRANSACTION_GETHANDLEDKEYS = 101;
    private static final int TRANSACTION_SENDKEYEVENT = 100;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private Handler mLongContinuedHandler;
    private HandlerThread mLongContinuedThread;
    private final Object mLock = new Object();
    private boolean bIsSkipCheck = false;
    private volatile HashMap<IBinder, ArrayList<KeyEventCallback>> mCallbackMap = new HashMap<>();
    private volatile HashMap<IBinder, ArrayList<KeyEventCallback>> mLongPressCbkMap = new HashMap<>();
    private volatile HashMap<Integer, ArrayList<SceneTypePolicy>> mKeycodeMap = new HashMap<>();
    private volatile HashMap<Integer, ArrayList<SceneTypePolicy>> mKeyLongPressMap = new HashMap<>();
    private volatile HashMap<Integer, CallbackInfo> mSceneCbkMap = new HashMap<>();
    private volatile HashMap<Integer, CallbackInfo> mSceneCbkLongPress = new HashMap<>();
    private volatile HashSet<Integer> mKeyDownSet = new HashSet<>();
    private CombineKeyManager.IKeyEventAvailableListener mKeyAvailableListener = new CombineKeyManager.IKeyEventAvailableListener() { // from class: com.ts.car.input.HardKeyInputPolicyManagerService.1
        @Override // com.ts.car.input.CombineKeyManager.IKeyEventAvailableListener
        public void onKeyAvailable(KeyEvent keyEvent, int displayId, boolean isCombineKeyMode) {
            HardKeyInputPolicyManagerService.this.processHardKeyFromPolicyInternal(keyEvent, displayId, isCombineKeyMode);
        }
    };
    boolean injectExist = false;
    boolean ignoreExist = false;
    private CombineKeyManager mCombineKeyManager = new CombineKeyManager();

    private void addToCallbackMap(KeyEventCallback call, IBinder binder, HashMap<IBinder, ArrayList<KeyEventCallback>> map) {
        LogUtils.log(TAG, "addToCallbackMap call.hashcode :" + call.hashCode() + ",map.size :" + map.size());
        ArrayList<KeyEventCallback> list = map.get(binder);
        if (list == null) {
            list = new ArrayList<>();
            map.put(binder, list);
        }
        list.add(call);
        try {
            binder.linkToDeath(call, 0);
        } catch (RemoteException ex) {
            LogUtils.logwtf(TAG, ex);
        }
    }

    public void addKeyEventCallBack(IKeyEventCallback callback, int scene) {
        checkPermission();
        int callingPid = Binder.getCallingPid();
        LogUtils.log(TAG, "addKeyEventCallBack callingPid : " + callingPid + "scene :" + scene);
        synchronized (this.mLock) {
            if (!ConfigUtils.getAllSceneList(false).contains(Integer.valueOf(scene))) {
                throw new IllegalArgumentException("The scene type didn't configed in config file!");
            }
            addKeyEventCallBackInternal(callback, scene, callingPid, false);
        }
    }

    private void addKeyEventCallBackInternal(IKeyEventCallback callback, int scene, int callingPid, boolean longCbk) {
        LogUtils.log(TAG, "scene:" + scene + ",callingPid : " + callingPid + ",longCbk : " + longCbk);
        KeyEventCallback call = new KeyEventCallback(callback, scene, callingPid);
        IBinder binder = callback.asBinder();
        HashMap<IBinder, ArrayList<KeyEventCallback>> cbkMap = getCbkMap(longCbk);
        HashMap<Integer, ArrayList<SceneTypePolicy>> keyMap = getKeyMap(longCbk);
        addToCallbackMap(call, binder, cbkMap);
        addToKeyCodeMap(scene, longCbk, keyMap, callingPid);
        addToSceneCallBackMap(callback, scene, longCbk, callingPid);
    }

    private void addToKeyCodeMap(int scene, boolean longCbk, HashMap<Integer, ArrayList<SceneTypePolicy>> keyMap, int callingPid) {
        ArrayList<SceneTypePolicy> list = ConfigUtils.getSceneMap(longCbk).get(Integer.valueOf(scene));
        if (list != null && list.size() > 0) {
            for (int m = 0; m < list.size(); m++) {
                int keycode = list.get(m).getKeycode();
                int priorityNew = list.get(m).getPriority();
                int sceneNew = list.get(m).getSceneType();
                list.get(m).setCallingPid(callingPid);
                if (!keyMap.containsKey(Integer.valueOf(keycode))) {
                    ArrayList<SceneTypePolicy> listKey = new ArrayList<>();
                    listKey.add(list.get(m));
                    keyMap.put(Integer.valueOf(keycode), listKey);
                } else {
                    ArrayList<SceneTypePolicy> listKey2 = keyMap.get(Integer.valueOf(keycode));
                    if (listKey2 != null) {
                        LogUtils.log(TAG, "ADD cbk keycode :" + keycode + ",listKey.size :" + listKey2.size());
                    }
                    boolean inserted = false;
                    if (listKey2 != null && listKey2.size() > 0) {
                        int j = 0;
                        while (true) {
                            if (j >= listKey2.size()) {
                                break;
                            }
                            int priorityOld = listKey2.get(j).getPriority();
                            int sceneOld = listKey2.get(j).getSceneType();
                            if (sceneNew == sceneOld || priorityNew >= priorityOld) {
                                j++;
                            } else {
                                listKey2.add(j, list.get(m));
                                inserted = true;
                                break;
                            }
                        }
                    }
                    if (listKey2 != null && !inserted) {
                        listKey2.add(list.get(m));
                    }
                }
            }
        }
    }

    private void addToSceneCallBackMap(IKeyEventCallback callback, int scene, boolean longCbk, int callingPid) {
        HashMap<Integer, CallbackInfo> sceneCbkMap = getSceneCbkMap(longCbk);
        if (!sceneCbkMap.containsKey(Integer.valueOf(scene))) {
            CallbackInfo cbkInfo = new CallbackInfo(callback, scene, callingPid);
            sceneCbkMap.put(Integer.valueOf(scene), cbkInfo);
            this.mLongContinuedHandler.removeMessages(scene);
        } else {
            LogUtils.loge(TAG, "The scene :" + scene + " has added callback to service! One scene can only add to callback for once at the sametime!");
        }
    }

    private void removeFromSceneCallBackMap(IKeyEventCallback callback, int scene, boolean longCbk) {
        HashMap<Integer, CallbackInfo> sceneCbkMap = getSceneCbkMap(longCbk);
        if (!sceneCbkMap.containsKey(Integer.valueOf(scene))) {
            throw new IllegalArgumentException("From the scene :" + scene + ",there didn't found the callback and callingPid!");
        }
        CallbackInfo cbkInfo = sceneCbkMap.get(Integer.valueOf(scene));
        if (cbkInfo.binder == callback.asBinder()) {
            sceneCbkMap.remove(Integer.valueOf(scene), cbkInfo);
            this.mLongContinuedHandler.removeMessages(scene);
            return;
        }
        LogUtils.loge(TAG, "The scene :" + scene + " and the callback not match! Pleaseconfirm your params are right!");
    }

    public void removeKeyEventCallBack(IKeyEventCallback callback, int scene) {
        checkPermission();
        int callingPid = Binder.getCallingPid();
        synchronized (this.mLock) {
            if (!this.mSceneCbkMap.containsKey(Integer.valueOf(scene))) {
                throw new IllegalArgumentException("The scene type doesn't registe yet!");
            }
            if (!this.mCallbackMap.containsKey(callback.asBinder())) {
                throw new IllegalArgumentException("scene type and callback pair was not legal!");
            }
            removeKeyEventCallBackInternal(callback, scene, callingPid, false);
        }
    }

    private HashMap<IBinder, ArrayList<KeyEventCallback>> getCbkMap(boolean longCbk) {
        if (longCbk) {
            return this.mLongPressCbkMap;
        }
        return this.mCallbackMap;
    }

    private HashMap<Integer, ArrayList<SceneTypePolicy>> getKeyMap(boolean longCbk) {
        if (longCbk) {
            return this.mKeyLongPressMap;
        }
        return this.mKeycodeMap;
    }

    private void removeKeyEventCallBackInternal(IKeyEventCallback callback, int scene, int callingPid, boolean longCbk) {
        HashMap<IBinder, ArrayList<KeyEventCallback>> callbackMap = getCbkMap(longCbk);
        HashMap<Integer, ArrayList<SceneTypePolicy>> keycodeMap = getKeyMap(longCbk);
        removeFromCallbackMap(callback, scene, callbackMap);
        removeFromKeyCodeMap(scene, longCbk, keycodeMap);
        removeFromSceneCallBackMap(callback, scene, longCbk);
    }

    private void removeFromKeyCodeMap(int scene, boolean longCbk, HashMap<Integer, ArrayList<SceneTypePolicy>> keycodeMap) {
        ArrayList<SceneTypePolicy> list = ConfigUtils.getSceneMap(longCbk).get(Integer.valueOf(scene));
        if (list != null && list.size() > 0) {
            LogUtils.log(TAG, "remove cbk list.size : " + list.size());
            for (int i = 0; i < list.size(); i++) {
                int keycode = list.get(i).getKeycode();
                ArrayList<SceneTypePolicy> sceneLst = keycodeMap.get(Integer.valueOf(keycode));
                if (sceneLst != null) {
                    LogUtils.log(TAG, "remove cbk keycode: " + keycode + ",i: " + i + ",sceneLst.size:" + sceneLst.size() + ".scene:" + scene);
                    Iterator<SceneTypePolicy> it = sceneLst.iterator();
                    while (it.hasNext()) {
                        SceneTypePolicy sceneTypePolicy = it.next();
                        if (sceneTypePolicy.getSceneType() == scene) {
                            it.remove();
                        }
                    }
                }
                if (sceneLst != null && sceneLst.size() == 0) {
                    keycodeMap.remove(Integer.valueOf(keycode));
                    LogUtils.log(TAG, "remove cbk key's scenelist.size is 0!");
                }
            }
        }
    }

    private void removeFromCallbackMap(IKeyEventCallback callback, int scene, HashMap<IBinder, ArrayList<KeyEventCallback>> callbakMap) {
        IBinder binder = callback.asBinder();
        ArrayList<KeyEventCallback> list = callbakMap.get(binder);
        if (list != null && list.size() > 0) {
            LogUtils.log(TAG, "remove list.size :" + list.size());
            int i = list.size();
            while (true) {
                if (i <= 0) {
                    break;
                }
                if (list.get(i - 1).getSceneType() != scene) {
                    i--;
                } else {
                    KeyEventCallback cbk = list.get(i - 1);
                    binder.unlinkToDeath(cbk, 0);
                    list.remove(i - 1);
                    break;
                }
            }
        }
        if (list != null && list.size() == 0) {
            callbakMap.remove(binder);
        }
    }

    public void addLongPressCallBack(IKeyEventCallback callback, int scene) {
        checkPermission();
        int callingPid = Binder.getCallingPid();
        LogUtils.log(TAG, "addLongPressCallBack callingPid : " + callingPid + "scene :" + scene);
        synchronized (this.mLock) {
            if (!ConfigUtils.getAllSceneList(true).contains(Integer.valueOf(scene))) {
                throw new IllegalArgumentException("The scene type didn't configed in config file!");
            }
            addKeyEventCallBackInternal(callback, scene, callingPid, true);
        }
    }

    public void removeLongPressCallBack(IKeyEventCallback callback, int scene) {
        checkPermission();
        IBinder binder = callback.asBinder();
        int callingPid = Binder.getCallingPid();
        LogUtils.log(TAG, "removeLongPressCallBack callingPid : " + callingPid + "scene :" + scene);
        synchronized (this.mLock) {
            if (!this.mSceneCbkLongPress.containsKey(Integer.valueOf(scene))) {
                throw new IllegalArgumentException("The scene type doesn't registe yet!");
            }
            if (!this.mLongPressCbkMap.containsKey(binder)) {
                throw new IllegalArgumentException("priority and callback pair was not legal!");
            }
            removeKeyEventCallBackInternal(callback, scene, callingPid, true);
        }
    }

    public void removeKeyEventCbk(IKeyEventCallback callback) {
        checkPermission();
        IBinder binder = callback.asBinder();
        int callingPid = Binder.getCallingPid();
        LogUtils.log(TAG, "removeKeyEventCbk callingPid : " + callingPid);
        synchronized (this.mLock) {
            if (!this.mCallbackMap.containsKey(binder)) {
                throw new IllegalArgumentException("priority and callback pair was not legal!");
            }
            removeKeyEventCbkInternal(callback, false);
        }
    }

    public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        KeyEvent keyEvent;
        if (code == TRANSACTION_SENDKEYEVENT) {
            data.enforceInterface(DESCRIPTOR);
            if (data.readInt() != 0) {
                keyEvent = (KeyEvent) KeyEvent.CREATOR.createFromParcel(data);
            } else {
                keyEvent = null;
            }
            Log.i(TAG, "onTransact " + keyEvent);
            if (keyEvent != null) {
                try {
                    processHardKeyFromPolicy(keyEvent, 0);
                } catch (Exception ex) {
                    Log.w(TAG, "send keyEvent goes wrong", ex);
                }
            }
            reply.writeNoException();
            return true;
        }
        if (code == TRANSACTION_GETHANDLEDKEYS) {
            data.enforceInterface(DESCRIPTOR);
            int[] codes = new int[ConfigUtils.getKeyCodes().size()];
            int index = 0;
            Iterator<Integer> it = ConfigUtils.getKeyCodes().iterator();
            while (it.hasNext()) {
                int keyCode = it.next().intValue();
                codes[index] = keyCode;
                index++;
            }
            Log.i(TAG, "onTransact: " + Arrays.toString(codes));
            reply.writeNoException();
            reply.writeIntArray(codes);
            return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeKeyEventCbkInternal(IKeyEventCallback callback, boolean longCbk) {
        int callingPid = Binder.getCallingPid();
        HashMap<IBinder, ArrayList<KeyEventCallback>> cbkMap = getCbkMap(longCbk);
        ArrayList<KeyEventCallback> list = cbkMap.get(callback.asBinder());
        if (list == null || list.size() <= 0) {
            return;
        }
        ArrayList<KeyEventCallback> lst = (ArrayList) list.clone();
        for (int i = lst.size(); i > 0; i--) {
            int scene = lst.get(i - 1).getSceneType();
            removeKeyEventCallBackInternal(callback, scene, callingPid, longCbk);
        }
    }

    public void removeLongPressCbk(IKeyEventCallback callback) {
        checkPermission();
        IBinder binder = callback.asBinder();
        int callingPid = Binder.getCallingPid();
        LogUtils.log(TAG, "removeLongPressCallBack callingPid : " + callingPid);
        synchronized (this.mLock) {
            if (!this.mLongPressCbkMap.containsKey(binder)) {
                throw new IllegalArgumentException("priority and callback pair was not legal!");
            }
            removeKeyEventCbkInternal(callback, true);
        }
    }

    public void removeAllKeyEventCallBack() {
        checkPermission();
        int callingPid = Binder.getCallingPid();
        synchronized (this.mLock) {
            removeAllFromMap(callingPid, false);
            removeAllFromMap(callingPid, true);
        }
    }

    private void removeAllFromMap(int callingPid, boolean longPress) {
        HashMap<IBinder, ArrayList<KeyEventCallback>> cbkMap;
        HashMap<Integer, ArrayList<SceneTypePolicy>> keyMap;
        HashMap<Integer, CallbackInfo> sceneMap;
        if (longPress) {
            cbkMap = this.mLongPressCbkMap;
            keyMap = this.mKeyLongPressMap;
            sceneMap = this.mSceneCbkLongPress;
        } else {
            cbkMap = this.mCallbackMap;
            keyMap = this.mKeycodeMap;
            sceneMap = this.mSceneCbkMap;
        }
        Iterator<Map.Entry<IBinder, ArrayList<KeyEventCallback>>> itCbk = cbkMap.entrySet().iterator();
        while (itCbk.hasNext()) {
            Map.Entry<IBinder, ArrayList<KeyEventCallback>> item = itCbk.next();
            ArrayList<KeyEventCallback> list = item.getValue();
            for (int i = list.size() - 1; i >= 0; i--) {
                if (list.get(i).getCallingPid() == callingPid) {
                    list.remove(i);
                }
            }
            int i2 = list.size();
            if (i2 <= 0) {
                itCbk.remove();
            }
        }
        Iterator<Map.Entry<Integer, ArrayList<SceneTypePolicy>>> itKey = keyMap.entrySet().iterator();
        while (itKey.hasNext()) {
            Map.Entry<Integer, ArrayList<SceneTypePolicy>> itemK = itKey.next();
            ArrayList<SceneTypePolicy> listKey = itemK.getValue();
            for (int j = listKey.size() - 1; j >= 0; j--) {
                if (listKey.get(j).getCallingPid() == callingPid) {
                    listKey.remove(j);
                }
            }
            int j2 = listKey.size();
            if (j2 <= 0) {
                itKey.remove();
            }
        }
        Iterator<Map.Entry<Integer, CallbackInfo>> itScene = sceneMap.entrySet().iterator();
        while (itScene.hasNext()) {
            Map.Entry<Integer, CallbackInfo> itemScene = itScene.next();
            CallbackInfo callbackInfo = itemScene.getValue();
            if (callbackInfo.getCallingPid() == callingPid) {
                itScene.remove();
            }
        }
    }

    private boolean checkUidPermission() {
        int callingUid = Binder.getCallingUid();
        LogUtils.log(TAG, "callingUid :" + callingUid);
        return this.mContext.checkCallingOrSelfPermission(HARDKEY_PERSSION) == 0 || callingUid == 1000;
    }

    public void processHardKeyFromPolicy(KeyEvent keyEvent, int targetDisplay) {
        if (!checkUidPermission()) {
            return;
        }
        this.mLongContinuedHandler.removeCallbacksAndMessages(null);
        if (!this.mKeyDownSet.contains(Integer.valueOf(keyEvent.getKeyCode())) && keyEvent.getAction() == 0) {
            this.mKeyDownSet.add(Integer.valueOf(keyEvent.getKeyCode()));
        } else if (this.mKeyDownSet.contains(Integer.valueOf(keyEvent.getKeyCode())) && keyEvent.getAction() == 1) {
            this.mKeyDownSet.remove(Integer.valueOf(keyEvent.getKeyCode()));
        } else if (!this.mKeyDownSet.contains(Integer.valueOf(keyEvent.getKeyCode())) && keyEvent.getAction() == 1) {
            LogUtils.loge(TAG, "processHardKeyFromPolicy action up without down return, key:" + keyEvent.toString());
            return;
        }
        this.mCombineKeyManager.prepareForCombineKey(keyEvent, targetDisplay);
    }

    @Override // com.core.system.foundationinterface.ICoreService
    public String serviceName() {
        return CORE_SERVICE_NAME;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.core.system.foundationinterface.ICoreService
    public IBinder getServiceBinder() {
        return this;
    }

    @Override // com.core.system.foundationinterface.ICoreService
    public void init(Context context, ICoreAdminService coreAdminService) {
        this.mContext = context;
        ConfigParser actionConfig = new ConfigParser();
        LogUtils.log(TAG, "ActionConfig parser :" + actionConfig.toString());
        ConfigUtils uiConfig = new ConfigUtils();
        LogUtils.log(TAG, "UiConfig :" + uiConfig.toString());
        init();
        if (coreAdminService != null) {
            coreAdminService.notifyServiceReady(this);
        }
    }

    public void init() {
        this.bIsSkipCheck = SystemProperties.getBoolean(CHECK_PERMISSION_PROP, false);
        LogUtils.log(TAG, "bIsCheckPremission = " + SystemProperties.get(CHECK_PERMISSION_PROP));
        this.mCombineKeyManager.init(this.mKeyAvailableListener);
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread(TAG);
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            this.mHandler = new LongHandler(this.mHandlerThread.getLooper());
        }
        if (this.mLongContinuedThread == null) {
            HandlerThread handlerThread2 = new HandlerThread(LONG_CONTINUED_THREAD);
            this.mLongContinuedThread = handlerThread2;
            handlerThread2.start();
            this.mLongContinuedHandler = new LongContinuedHandler(this.mLongContinuedThread.getLooper());
        }
    }

    @Override // com.core.system.foundationinterface.ICoreService
    public void release(Context context, ICoreAdminService coreAdminService) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processHardKeyFromPolicyInternal(KeyEvent keyEvent, int displayId, boolean isCombineKeyMode) {
        int keycode = keyEvent.getKeyCode();
        Log.d(TAG, "process policy ,keycode :" + keycode);
        synchronized (this.mLock) {
            if (!this.mKeycodeMap.containsKey(Integer.valueOf(keycode)) && !this.mKeyLongPressMap.containsKey(Integer.valueOf(keycode))) {
                LogUtils.log(TAG, "All bg scene do not configured any keycode inthe config file :keycode-ui.xml, derectly inject to system");
                injectHardKeyToSystem(keyEvent, displayId);
                return;
            }
            List<Integer> combkeyList = ConfigParser.getCombineKeys();
            if (combkeyList.contains(Integer.valueOf(keycode))) {
                processHardKey(keyEvent, displayId, isCombineKeyMode);
                return;
            }
            if (this.mKeyLongPressMap.containsKey(Integer.valueOf(keycode))) {
                Log.d(TAG, "mKeyLongPressMap.size: " + this.mKeyLongPressMap.size());
                ArrayList<SceneTypePolicy> scenePolicy = getSceneTypePolicy(keycode, true);
                if (scenePolicy.size() > 0) {
                    prepareLongPress(keyEvent, displayId, scenePolicy, false);
                    return;
                }
            }
            processHardKey(keyEvent, displayId, isCombineKeyMode);
        }
    }

    private void processBgNotNofityPolicy(int policyBg, int sceneBg) {
        if (policyBg == 1) {
            setInjectExist();
            LogUtils.log(TAG, "Top priority bg scene type:" + sceneBg + ", policy is INJECT.");
            return;
        }
        if (policyBg == 0) {
            setIgnoreExist();
            LogUtils.log(TAG, "Top priority bg scene type:" + sceneBg + ", policy is IGNORE.");
        }
    }

    private void setInjectExist() {
        this.injectExist = true;
    }

    private void setIgnoreExist() {
        this.ignoreExist = true;
    }

    private ArrayList<SceneTypePolicy> getSceneTypePolicy(int keycode, boolean longPress) {
        int i = keycode;
        HashMap<Integer, ArrayList<SceneTypePolicy>> keyMap = getKeyMap(longPress);
        LogUtils.log(TAG, "getSceneTypePolicy keycode: " + i + ",longPress: " + longPress);
        ArrayList<SceneTypePolicy> sceneTypePolicyListBg = new ArrayList<>();
        ArrayList<SceneTypePolicy> list = keyMap.get(Integer.valueOf(keycode));
        int sceneBg = -1;
        int priorityBg = 0;
        int policyBg = 1;
        this.injectExist = false;
        this.ignoreExist = false;
        if (list != null && list.size() > 0) {
            int index = list.size() - 1;
            sceneBg = list.get(index).getSceneType();
            priorityBg = list.get(index).getPriority();
            policyBg = list.get(index).getPolicy();
            SceneTypePolicy scenePolicyBg = new SceneTypePolicy(i);
            scenePolicyBg.setSceneType(sceneBg);
            scenePolicyBg.setPolicy(policyBg);
            sceneTypePolicyListBg.add(scenePolicyBg);
            LogUtils.log(TAG, "sceneBg: " + sceneBg + ",priorityBg: " + priorityBg + ",policyBg: " + policyBg + ",sceneTypePolicyListBg.size: " + sceneTypePolicyListBg.size());
            processBgNotNofityPolicy(policyBg, sceneBg);
            if (index > 0) {
                int j = index - 1;
                while (true) {
                    if (j < 0) {
                        break;
                    }
                    index--;
                    int priorityBgNext = list.get(j).getPriority();
                    StringBuilder sb = new StringBuilder();
                    HashMap<Integer, ArrayList<SceneTypePolicy>> keyMap2 = keyMap;
                    sb.append("priorityBgCurrent: ");
                    sb.append(list.get(j + 1).getPriority());
                    sb.append(",priorityBgNext");
                    sb.append(priorityBgNext);
                    LogUtils.log(TAG, sb.toString());
                    if (priorityBgNext == list.get(j + 1).getPriority() || list.get(j + 1).getPolicy() == 3) {
                        int sceneBgNext = list.get(j).getSceneType();
                        int policyBgNext = list.get(j).getPolicy();
                        ArrayList<SceneTypePolicy> list2 = list;
                        SceneTypePolicy scenePolicyBgNext = new SceneTypePolicy(i);
                        scenePolicyBgNext.setSceneType(sceneBgNext);
                        scenePolicyBgNext.setPolicy(policyBgNext);
                        if (policyBgNext == 1) {
                            if (this.injectExist) {
                                LogUtils.logw(TAG, "Shouldn't config diffrent scene type with the same process policy : inject with the same priority,pls check the config file.");
                            } else {
                                sceneTypePolicyListBg.add(scenePolicyBgNext);
                                setInjectExist();
                            }
                        }
                        if (policyBgNext == 0) {
                            if (this.ignoreExist) {
                                LogUtils.logw(TAG, "Top priority with policy : IGNORE has exist, shouldn't config diffrent scene type with other policy,pls check the config file.");
                            } else {
                                sceneTypePolicyListBg.add(scenePolicyBgNext);
                                setIgnoreExist();
                            }
                        }
                        if (checkDeliverOrPost(policyBgNext)) {
                            sceneTypePolicyListBg.add(scenePolicyBgNext);
                            LogUtils.log(TAG, "scenePolicyBgNext" + scenePolicyBgNext + ",policyBgNext: " + policyBgNext);
                        }
                        LogUtils.log(TAG, "sceneTypePolicyListBg size: " + sceneTypePolicyListBg.size());
                        j += -1;
                        i = keycode;
                        keyMap = keyMap2;
                        list = list2;
                    } else {
                        LogUtils.log(TAG, "getSceneTypePolicy break!");
                        break;
                    }
                }
            }
        }
        LogUtils.log(TAG, "sceneBg: " + sceneBg + ",policyBg :" + policyBg + ",priorityBg: " + priorityBg);
        this.injectExist = false;
        this.ignoreExist = false;
        return sceneTypePolicyListBg;
    }

    private boolean checkDeliverOrPost(int policyBgNext) {
        if (policyBgNext == 2 || policyBgNext == 3) {
            return true;
        }
        return false;
    }

    private void notifyKeyEvent(KeyEvent keyEvent, int scene, int displayId, boolean longPress) {
        synchronized (this.mLock) {
            HashMap<Integer, CallbackInfo> sceneCbkMap = getSceneCbkMap(longPress);
            if (sceneCbkMap != null) {
                if (sceneCbkMap.containsKey(Integer.valueOf(scene))) {
                    CallbackInfo cbkInfo = sceneCbkMap.get(Integer.valueOf(scene));
                    IKeyEventCallback callback = cbkInfo.getKeyEventCallback();
                    if (callback != null) {
                        try {
                            if (longPress) {
                                callback.onKeyLongPress(keyEvent, scene, displayId);
                                LogUtils.log(TAG, "notifyKeyEvent long : scene : " + scene);
                            } else {
                                callback.onKeyEvent(keyEvent, scene, displayId);
                                LogUtils.log(TAG, "notifyKeyEvent  scene : " + scene);
                            }
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                        }
                    }
                    return;
                }
                int keycode = keyEvent.getKeyCode();
                LogUtils.loge(TAG, "Front scene:" + scene + " set the process policy for the keycode:" + keycode + "is notify callback, but didn't find the callback with the scene type regist to service!");
            }
        }
    }

    private HashMap<Integer, CallbackInfo> getSceneCbkMap(boolean longPress) {
        if (longPress) {
            return this.mSceneCbkLongPress;
        }
        return this.mSceneCbkMap;
    }

    private void processHardKey(KeyEvent keyEvent, int targetDisplay, boolean isCombineKeyMode) {
        int keycode = keyEvent.getKeyCode();
        ArrayList<SceneTypePolicy> scenePolicyList = getSceneTypePolicy(keycode, false);
        HashMap<Integer, ArrayList<Integer>> keyMap = ConfigParser.getPassThroughKeymap();
        LogUtils.log(TAG, "scenePolicyList.size : " + scenePolicyList.size() + ",keyMap.size: " + keyMap.size());
        if (keyMap.containsKey(Integer.valueOf(keycode))) {
            ArrayList<Integer> list = keyMap.get(Integer.valueOf(keycode));
            LogUtils.log(TAG, "keyMap list.size: " + list.size());
            if (scenePolicyList != null && scenePolicyList.size() > 0) {
                int i = 0;
                while (i < scenePolicyList.size()) {
                    SceneTypePolicy spolicy = scenePolicyList.get(i);
                    int scene = spolicy.getSceneType();
                    int policy = spolicy.getPolicy();
                    LogUtils.log(TAG, "spolicy  scene: " + scene + ",policy: " + policy);
                    if (list.contains(Integer.valueOf(scene))) {
                        if (policy == 0) {
                            scenePolicyList.remove(spolicy);
                        } else if (policy == 1) {
                            injectHardKeyToSystem(keyEvent, targetDisplay);
                            scenePolicyList.remove(spolicy);
                            i--;
                        } else if (policy == 2 || policy == 3) {
                            notifyKeyEvent(keyEvent, scene, targetDisplay, false);
                            scenePolicyList.remove(spolicy);
                            i--;
                        }
                    } else {
                        prepareLongPress(keyEvent, targetDisplay, scenePolicyList, true);
                    }
                    i++;
                }
                return;
            }
            return;
        }
        if (!isCombineKeyMode) {
            prepareLongPress(keyEvent, targetDisplay, scenePolicyList, true);
            return;
        }
        ArrayList<SceneTypePolicy> scenePolicyShort = getSceneTypePolicy(keycode, false);
        ArrayList<Integer> sceneListShort = new ArrayList<>();
        ArrayList<Integer> policyListShort = new ArrayList<>();
        if (scenePolicyShort != null && scenePolicyShort.size() > 0) {
            int listSizeShort = scenePolicyShort.size();
            for (int i2 = 0; i2 < listSizeShort; i2++) {
                int sceneShort = scenePolicyShort.get(i2).getSceneType();
                int policyShort = scenePolicyShort.get(i2).getPolicy();
                sceneListShort.add(Integer.valueOf(sceneShort));
                policyListShort.add(Integer.valueOf(policyShort));
                LogUtils.log(TAG, "Combine  Key triggered  sceneShort :" + sceneShort + ",policyShort :" + policyShort);
            }
            if (policyListShort.contains(1)) {
                KeyEvent eventDown = mockKeyEvent(keycode, 0);
                injectHardKeyToSystem(eventDown, targetDisplay);
            }
            for (int j = 0; j < scenePolicyShort.size(); j++) {
                int sceneType = sceneListShort.get(j).intValue();
                int policys = policyListShort.get(j).intValue();
                onKeyUp(keyEvent, sceneType, targetDisplay, policys);
            }
        }
    }

    private ArrayList<SceneTypePolicy> sendKeycodePassThrough(KeyEvent keyEvent, ArrayList<SceneTypePolicy> scenePolicyList, int targetDisplay) {
        int keycode = keyEvent.getKeyCode();
        HashMap<Integer, ArrayList<Integer>> keyMap = ConfigParser.getPassThroughKeymap();
        if (keyMap.containsKey(Integer.valueOf(keycode))) {
            ArrayList<Integer> list = keyMap.get(Integer.valueOf(keycode));
            if (scenePolicyList != null && scenePolicyList.size() > 0) {
                int i = 0;
                while (i < scenePolicyList.size()) {
                    SceneTypePolicy spolicy = scenePolicyList.get(i);
                    int scene = spolicy.getSceneType();
                    int policy = spolicy.getPolicy();
                    LogUtils.log(TAG, "spolicy  scene: " + scene + ",policy: " + policy);
                    if (list.contains(Integer.valueOf(scene))) {
                        if (policy == 0) {
                            LogUtils.logw(TAG, "The policy in keycode-ui.xml is ignore,should this scene type configure in the keycode_config to receive the real keycode down and up?");
                        } else if (policy == 1) {
                            injectHardKeyToSystem(keyEvent, targetDisplay);
                        } else if (policy == 2 || policy == 3) {
                            notifyKeyEvent(keyEvent, scene, targetDisplay, false);
                        }
                        scenePolicyList.remove(spolicy);
                        i--;
                    }
                    i++;
                }
            }
        }
        return scenePolicyList;
    }

    private void injectHardKeyToSystem(KeyEvent keyEvent, int targetDisplay) {
        LogUtils.log(TAG, "injectHardKeyToSystem keycode : " + keyEvent.getKeyCode());
        KeycodeUtil.sendKeyEvent(this.mContext, keyEvent, targetDisplay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleContinuedKey(Message msg) {
        Bundle obj = (Bundle) msg.obj;
        KeyEvent keyEvent = (KeyEvent) obj.getParcelable(KEY);
        int displayid = obj.getInt(DISPLAY);
        int scene = obj.getInt(SCENE);
        int delay = obj.getInt(LONGPRESS_SPLITE);
        int time = obj.getInt(TIME);
        int maxTime = obj.getInt(MAX_TIME);
        int time2 = time + 1;
        if (time2 >= maxTime) {
            LogUtils.log(TAG, "handleContinuedKey max time: " + maxTime);
            return;
        }
        Message message = this.mLongContinuedHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY, keyEvent);
        bundle.putInt(DISPLAY, displayid);
        bundle.putInt(SCENE, scene);
        bundle.putInt(LONGPRESS_SPLITE, delay);
        bundle.putInt(TIME, time2);
        bundle.putInt(MAX_TIME, maxTime);
        message.what = scene;
        message.obj = bundle;
        this.mLongContinuedHandler.sendMessageDelayed(message, delay);
        LogUtils.log(TAG, "handleContinuedKey sendMessageDelayed scene: " + scene + ", time: " + time2);
        notifyKeyEvent(keyEvent, scene, displayid, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLongKey(Bundle obj) {
        KeyEvent keyEvent = (KeyEvent) obj.getParcelable(KEY);
        int displayid = obj.getInt(DISPLAY);
        boolean notInLongpressConfig = obj.getBoolean(NOTIN_LONGPRESS_CONFIG);
        ArrayList<Integer> policyList = obj.getIntegerArrayList(POLICY);
        ArrayList<Integer> sceneList = obj.getIntegerArrayList(SCENE);
        KeyEvent keyEvent2 = KeyEvent.changeFlags(keyEvent, 128);
        boolean longpressToShortpress = ConfigParser.getShortKeycodeList().contains(Integer.valueOf(keyEvent2.getKeyCode()));
        LogUtils.log(TAG, "handleLongKey longpressToShortpress: " + longpressToShortpress + ", notInLongpressConfig: " + notInLongpressConfig + ", keyEvent: " + keyEvent2.getKeyCode());
        int policySize = policyList.size();
        int sceneSize = sceneList.size();
        if (notInLongpressConfig && !longpressToShortpress) {
            LogUtils.log(TAG, "current fg scene and bg ground scene don't care about the longpress of keycode : " + keyEvent2.getKeyCode() + ",(action:down)ignore it.");
            return;
        }
        if (policySize > 0 && sceneSize > 0) {
            if (policySize != sceneSize) {
                LogUtils.loge(TAG, "The size of policyList and sceneSize not match!");
                return;
            }
            for (int i = 0; i < policySize; i++) {
                int policy = policyList.get(i).intValue();
                int scene = sceneList.get(i).intValue();
                LogUtils.log(TAG, "handleLongKey policy: " + policy + ",scene: " + scene);
                LongpressConfiguration configuration = ConfigParser.getLongConfigurationMap().get(Integer.valueOf(keyEvent2.getKeyCode()));
                StringBuilder sb = new StringBuilder();
                sb.append("handleLongKey configuration :");
                sb.append(configuration);
                LogUtils.log(TAG, sb.toString());
                if (configuration != null && configuration.getSceneList().contains(Integer.valueOf(scene)) && ((policy == 2 || policy == 3) && !notInLongpressConfig)) {
                    onKeyLongPressContinued(configuration, keyEvent2, scene, displayid);
                } else if (longpressToShortpress) {
                    onKeyLongPressToShortpress(keyEvent2, scene, displayid, policy);
                } else {
                    onKeyLongPress(keyEvent2, scene, displayid, policy);
                }
            }
        }
    }

    private void onKeyLongPressContinued(LongpressConfiguration configuration, KeyEvent event, int scene, int displayid) {
        LogUtils.log(TAG, "onKeyLongPressContinued keycode : " + event.getKeyCode());
        KeyEvent keyEvent = mockKeyEvent(event.getKeyCode(), 1);
        Message msg = this.mLongContinuedHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY, keyEvent);
        bundle.putInt(DISPLAY, displayid);
        bundle.putInt(SCENE, scene);
        bundle.putInt(LONGPRESS_SPLITE, configuration.getLongpressSplit());
        bundle.putInt(TIME, 1);
        bundle.putInt(MAX_TIME, configuration.getMaxtime());
        msg.what = scene;
        msg.obj = bundle;
        this.mLongContinuedHandler.sendMessageDelayed(msg, configuration.getLongpressSplit());
        notifyKeyEvent(keyEvent, scene, displayid, false);
    }

    private void onKeyLongPressToShortpress(KeyEvent event, int scene, int displayid, int policy) {
        LogUtils.log(TAG, "onKeyLongPressToShortpress keycode : " + event.getKeyCode() + ",policy : " + policy);
        KeyEvent keyEvent = mockKeyEvent(event.getKeyCode(), 1);
        if (policy == 2 || policy == 3) {
            notifyKeyEvent(keyEvent, scene, displayid, false);
        } else {
            policyWithoutCallbak(keyEvent, displayid, policy);
        }
    }

    private void onKeyLongPress(KeyEvent keyEvent, int scene, int displayid, int policy) {
        LogUtils.log(TAG, "onKeyLongPress keycode : " + keyEvent.getKeyCode() + ",policy : " + policy);
        if (policy == 2 || policy == 3) {
            notifyKeyEvent(keyEvent, scene, displayid, true);
        } else {
            policyWithoutCallbak(keyEvent, displayid, policy);
        }
    }

    private void policyWithoutCallbak(KeyEvent keyEvent, int displayid, int policy) {
        if (policy == 0) {
            return;
        }
        if (policy == 1 || policy == -1) {
            injectHardKeyToSystem(keyEvent, displayid);
        }
    }

    private void onKeyUp(KeyEvent keyEvent, int scene, int displayid, int policy) {
        if (policy == 2 || policy == 3) {
            notifyKeyEvent(keyEvent, scene, displayid, false);
        } else {
            policyWithoutCallbak(keyEvent, displayid, policy);
        }
    }

    private void onKeyUpForLongPress(KeyEvent keyEvent, int scene, int displayid, int policy) {
        if (policy == 2 || policy == 3) {
            LongpressConfiguration configuration = ConfigParser.getLongConfigurationMap().get(Integer.valueOf(keyEvent.getKeyCode()));
            LogUtils.log(TAG, "onKeyUpForLongPress configuration :" + configuration);
            if (configuration != null && configuration.getSceneList().contains(Integer.valueOf(scene))) {
                LogUtils.log(TAG, "onKeyUpForLongPress return");
                return;
            } else {
                notifyKeyEvent(keyEvent, scene, displayid, true);
                return;
            }
        }
        policyWithoutCallbak(keyEvent, displayid, policy);
    }

    private void setLongPressList(ArrayList<SceneTypePolicy> scenePolicyList, ArrayList<Integer> sceneList, ArrayList<Integer> policyList, boolean longPressInject) {
        if (scenePolicyList != null && scenePolicyList.size() > 0) {
            int listSize = scenePolicyList.size();
            for (int i = 0; i < listSize; i++) {
                int scene = scenePolicyList.get(i).getSceneType();
                int policy = scenePolicyList.get(i).getPolicy();
                sceneList.add(Integer.valueOf(scene));
                policyList.add(Integer.valueOf(policy));
                LogUtils.log(TAG, "prepareLongPress scene :" + scene + ",policy :" + policy + ", longPressInject : " + longPressInject + ",listSize: " + listSize);
            }
        }
    }

    private void reduceList(ArrayList<SceneTypePolicy> list, ArrayList<Integer> slist, ArrayList<Integer> plist, boolean longPressInject) {
        ArrayList<Integer> sceneListTmp = new ArrayList<>();
        ArrayList<Integer> policyListTmp = new ArrayList<>();
        setLongPressList(list, sceneListTmp, policyListTmp, longPressInject);
        if (slist != null && plist != null) {
            slist.clear();
            plist.clear();
            slist.addAll(sceneListTmp);
            plist.addAll(policyListTmp);
        }
    }

    private void prepareLongPress(KeyEvent keyEvent, int displayid, ArrayList<SceneTypePolicy> scenePolicyList, boolean longPressInject) {
        ArrayList<SceneTypePolicy> scenePolicyList2;
        ArrayList<Integer> sceneList;
        ArrayList<Integer> policyList;
        ArrayList<Integer> sceneList2;
        ArrayList<Integer> policyList2;
        ArrayList<SceneTypePolicy> scenePolicyList3 = scenePolicyList;
        int keycode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        LogUtils.log(TAG, "prepareLongPress keycode: " + keycode + "action: " + action + ",longPressInject: " + longPressInject);
        ArrayList<Integer> sceneList3 = new ArrayList<>();
        ArrayList<Integer> policyListShort = new ArrayList<>();
        setLongPressList(scenePolicyList3, sceneList3, policyListShort, longPressInject);
        HashMap<Integer, ArrayList<Integer>> keyMap = ConfigParser.getPassThroughKeymap();
        LogUtils.log(TAG, "keyMap size: " + keyMap.size());
        if (action == 0) {
            if (longPressInject && (!this.mKeycodeMap.containsKey(Integer.valueOf(keycode)) || scenePolicyList.size() == 0)) {
                injectHardKeyToSystem(keyEvent, displayid);
                LogUtils.log(TAG, "none care about this key, inject immediatly");
                return;
            }
            if (keyMap.containsKey(Integer.valueOf(keycode))) {
                Log.d(TAG, "The keycode : " + keycode + " should delivery to scene with real keycode down.");
                scenePolicyList3 = sendKeycodePassThrough(keyEvent, scenePolicyList3, displayid);
                reduceList(scenePolicyList3, sceneList3, policyListShort, longPressInject);
            }
            Message msg = this.mHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY, keyEvent);
            bundle.putInt(DISPLAY, displayid);
            bundle.putIntegerArrayList(POLICY, policyListShort);
            bundle.putIntegerArrayList(SCENE, sceneList3);
            bundle.putBoolean(NOTIN_LONGPRESS_CONFIG, longPressInject);
            msg.what = keycode;
            msg.obj = bundle;
            this.mHandler.sendMessageDelayed(msg, getLongPressTimeout(keycode));
            preLongPressDown(keyEvent, displayid, policyListShort, sceneList3);
        }
        if (action == 1) {
            if (longPressInject && (!this.mKeycodeMap.containsKey(Integer.valueOf(keycode)) || scenePolicyList3.size() == 0)) {
                injectHardKeyToSystem(keyEvent, displayid);
                return;
            }
            boolean hasDown = this.mHandler.hasMessages(keycode);
            LogUtils.log(TAG, "prepareLongPress keyup hasDown:" + hasDown);
            if (!hasDown) {
                scenePolicyList2 = scenePolicyList3;
                sceneList = sceneList3;
                policyList = policyListShort;
            } else {
                this.mHandler.removeMessages(keycode);
                ArrayList<SceneTypePolicy> scenePolicyShort = getSceneTypePolicy(keycode, false);
                ArrayList<Integer> sceneListShort = new ArrayList<>();
                ArrayList<Integer> policyListShort2 = new ArrayList<>();
                if (keyMap.containsKey(Integer.valueOf(keycode))) {
                    scenePolicyShort = sendKeycodePassThrough(keyEvent, scenePolicyShort, displayid);
                    reduceList(scenePolicyShort, sceneList3, policyListShort, longPressInject);
                }
                if (checkScenePolicyShort(scenePolicyShort)) {
                    int listSizeShort = scenePolicyShort.size();
                    int i = 0;
                    while (i < listSizeShort) {
                        int action2 = action;
                        int sceneShort = scenePolicyShort.get(i).getSceneType();
                        int listSizeShort2 = listSizeShort;
                        int policyShort = scenePolicyShort.get(i).getPolicy();
                        sceneListShort.add(Integer.valueOf(sceneShort));
                        ArrayList<Integer> policyList3 = policyListShort;
                        ArrayList<Integer> policyList4 = policyListShort2;
                        policyList4.add(Integer.valueOf(policyShort));
                        LogUtils.log(TAG, "prepareLongPress sceneShort :" + sceneShort + ",policyShort :" + policyShort);
                        i++;
                        scenePolicyList3 = scenePolicyList3;
                        policyListShort2 = policyList4;
                        sceneList3 = sceneList3;
                        action = action2;
                        listSizeShort = listSizeShort2;
                        policyListShort = policyList3;
                    }
                    ArrayList<Integer> policyList5 = policyListShort2;
                    if (policyList5.contains(1)) {
                        KeyEvent eventDown = mockKeyEvent(keycode, 0);
                        injectHardKeyToSystem(eventDown, displayid);
                    }
                    for (int j = 0; j < scenePolicyShort.size(); j++) {
                        int sceneType = sceneListShort.get(j).intValue();
                        int policys = policyList5.get(j).intValue();
                        onKeyUp(keyEvent, sceneType, displayid, policys);
                    }
                    return;
                }
                sceneList = sceneList3;
                policyList = policyListShort;
                scenePolicyList2 = scenePolicyList3;
            }
            if (!longPressInject && !hasDown) {
                if (keyMap.containsKey(Integer.valueOf(keycode))) {
                    LogUtils.log(TAG, "prepareLongPress keyup keycode" + keycode);
                    sceneList2 = sceneList;
                    policyList2 = policyList;
                    reduceList(sendKeycodePassThrough(keyEvent, scenePolicyList2, displayid), sceneList2, policyList2, longPressInject);
                } else {
                    sceneList2 = sceneList;
                    policyList2 = policyList;
                }
                handleLongpressKeyUp(KeyEvent.changeFlags(keyEvent, 128), sceneList2, policyList2, displayid);
            }
            ignoreTheKeyEvent(longPressInject, keycode);
        }
    }

    private void preLongPressDown(KeyEvent keyEvent, int displayid, ArrayList<Integer> policyList, ArrayList<Integer> sceneList) {
        LogUtils.log(TAG, "preLongPressDown: " + policyList.size());
        int keycode = keyEvent.getKeyCode();
        List<Integer> list = ConfigParser.getShortKeycodeList();
        if (list.contains(Integer.valueOf(keycode))) {
            LogUtils.log(TAG, "preLongPressDown: longpress to shortpress" + keycode);
            return;
        }
        ConfigParser.getPassThroughKeymap();
        HashMap<Integer, LongpressConfiguration> longConfigurationMap = ConfigParser.getLongConfigurationMap();
        if (longConfigurationMap.containsKey(Integer.valueOf(keycode))) {
            LogUtils.log(TAG, "preLongPressDown: longpress to short " + keycode);
            return;
        }
        List<Integer> longPressDispatchShortDown = ConfigParser.getLongPressDispatchShortDownList();
        if (!longPressDispatchShortDown.contains(Integer.valueOf(keycode))) {
            LogUtils.log(TAG, "preLongPressDown: longpress to shortpress " + keycode);
            return;
        }
        LogUtils.log(TAG, "preLongPressDown: " + keyEvent);
        KeyEvent keyEvent2 = KeyEvent.changeFlags(keyEvent, 128);
        int policySize = policyList.size();
        int sceneSize = sceneList.size();
        LogUtils.log(TAG, "preLongPressDown: " + sceneSize + " : " + policySize);
        if (policySize > 0 && sceneSize > 0 && policySize == sceneSize) {
            for (int i = 0; i < policySize; i++) {
                int scene = sceneList.get(i).intValue();
                notifyKeyEvent(keyEvent2, scene, displayid, false);
            }
        }
    }

    private boolean checkScenePolicyShort(ArrayList<SceneTypePolicy> scenePolicyShort) {
        if (scenePolicyShort != null && scenePolicyShort.size() > 0) {
            return true;
        }
        return false;
    }

    private void ignoreTheKeyEvent(boolean longPressInject, int keycode) {
        if (longPressInject) {
            LogUtils.log(TAG, "current fg scene and bg ground scene don't care about the longpress of keycode : " + keycode + ",ignore it.");
        }
    }

    private void handleLongpressKeyUp(KeyEvent keyEvent, ArrayList<Integer> sceneList, ArrayList<Integer> policyList, int displayId) {
        LogUtils.log(TAG, "handleLongpressKeyUp policyList.size : " + policyList.size() + ",sceneList.size : " + sceneList.size());
        if (sceneList.size() > 0 && policyList.size() > 0) {
            if (sceneList.size() != policyList.size()) {
                LogUtils.loge(TAG, "the policyList and the sceneList not match!pls check them!");
                return;
            }
            for (int m = 0; m < sceneList.size(); m++) {
                int scene = sceneList.get(m).intValue();
                int policyl = policyList.get(m).intValue();
                onKeyUpForLongPress(keyEvent, scene, displayId, policyl);
            }
        }
    }

    private KeyEvent mockKeyEvent(int keycode, int action) {
        KeyEvent event = new KeyEvent(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), action, keycode, 0, 0, 0, 0, KeycodeUtil.FLAG_NOT_INTERCEPT, 1);
        return event;
    }

    private long getLongPressTimeout(int keycode) {
        Long timeout = ConfigParser.TIME_SETS.get(keycode);
        return timeout != null ? timeout.longValue() : KeycodeUtil.getDefaultLongpressTimeout();
    }

    private class KeyEventCallback implements IBinder.DeathRecipient {
        final IBinder binder;
        final IKeyEventCallback callback;
        int callingPid;
        int sceneType;

        public KeyEventCallback(IKeyEventCallback callback, int scene, int pid) {
            this.callback = callback;
            this.binder = callback.asBinder();
            this.sceneType = scene;
            this.callingPid = pid;
            LogUtils.log(HardKeyInputPolicyManagerService.TAG, "KeyEventCallback ");
        }

        public IKeyEventCallback getCallback() {
            return this.callback;
        }

        public int getSceneType() {
            return this.sceneType;
        }

        public int getCallingPid() {
            return this.callingPid;
        }

        public IBinder getBinder() {
            return this.binder;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            synchronized (HardKeyInputPolicyManagerService.this.mLock) {
                HardKeyInputPolicyManagerService.this.removeKeyEventCbkInternal(this.callback, true);
                HardKeyInputPolicyManagerService.this.removeKeyEventCbkInternal(this.callback, false);
            }
            LogUtils.log(HardKeyInputPolicyManagerService.TAG, "binderDied ");
        }
    }

    private class LongHandler extends Handler {
        public LongHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            HardKeyInputPolicyManagerService.this.handleLongKey((Bundle) msg.obj);
        }
    }

    private class LongContinuedHandler extends Handler {
        public LongContinuedHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            HardKeyInputPolicyManagerService.this.handleContinuedKey(msg);
        }
    }

    private class CallbackInfo {
        private IBinder binder;
        private int callingPid;
        IKeyEventCallback cbk;
        private int sceneType;

        public CallbackInfo(IKeyEventCallback callback, int scene, int pid) {
            this.cbk = callback;
            this.binder = callback.asBinder();
            this.sceneType = scene;
            this.callingPid = pid;
        }

        public int getSceneType() {
            return this.sceneType;
        }

        public int getCallingPid() {
            return this.callingPid;
        }

        public IKeyEventCallback getKeyEventCallback() {
            return this.cbk;
        }
    }

    private void dumpCallbackMap(PrintWriter pw) {
        for (Map.Entry<IBinder, ArrayList<KeyEventCallback>> item : this.mCallbackMap.entrySet()) {
            IBinder binder = item.getKey();
            pw.println("    binder " + binder);
            ArrayList<KeyEventCallback> list = item.getValue();
            if (list != null && list.size() > 0) {
                for (int i = list.size() - 1; i >= 0; i += -1) {
                    pw.println("        mCallbackList index: " + i + ", list.sceneType: " + list.get(i).getSceneType());
                }
            }
        }
    }

    private void dumpLongPressCallbackMap(PrintWriter pw) {
        for (Map.Entry<IBinder, ArrayList<KeyEventCallback>> iteml : this.mLongPressCbkMap.entrySet()) {
            IBinder binder = iteml.getKey();
            pw.println("    binder " + binder);
            ArrayList<KeyEventCallback> listl = iteml.getValue();
            if (listl != null && listl.size() > 0) {
                for (int i = listl.size() - 1; i >= 0; i += -1) {
                    pw.println("        mLongPressCbkMap index: " + i + ", list.sceneType: " + listl.get(i).getSceneType());
                }
            }
        }
    }

    private void dumpKeycodeMap(PrintWriter pw) {
        for (Map.Entry<Integer, ArrayList<SceneTypePolicy>> itemK : this.mKeycodeMap.entrySet()) {
            pw.println("    mKeycodeMap.keycode: " + itemK.getKey());
            ArrayList<SceneTypePolicy> listKey = itemK.getValue();
            if (listKey != null && listKey.size() > 0) {
                for (int j = listKey.size() - 1; j >= 0; j += -1) {
                    pw.println("        mKeycodeMap index: " + j + ",listKey.sceneType: " + listKey.get(j).getSceneType() + ",getPriority: " + listKey.get(j).getPriority() + ",getPolicy: " + listKey.get(j).getPolicy() + ",listKey.size: " + listKey.size());
                }
            }
        }
    }

    private void dumpKeyLongPressMap(PrintWriter pw) {
        for (Map.Entry<Integer, ArrayList<SceneTypePolicy>> itemKl : this.mKeyLongPressMap.entrySet()) {
            pw.println("    mKeyLongPressMap.keycode: " + itemKl.getKey());
            ArrayList<SceneTypePolicy> listKeyl = itemKl.getValue();
            if (listKeyl != null && listKeyl.size() > 0) {
                for (int j = listKeyl.size() - 1; j >= 0; j += -1) {
                    pw.println("        mKeyLongPressMap index: " + j + ",listKey.sceneType: " + listKeyl.get(j).getSceneType() + ",getPriority: " + listKeyl.get(j).getPriority() + ",getPolicy: " + listKeyl.get(j).getPolicy() + ",listKey.size: " + listKeyl.size());
                }
            }
        }
    }

    private void dumpSceneCbkMap(PrintWriter pw) {
        for (Map.Entry<Integer, CallbackInfo> itemScene : this.mSceneCbkMap.entrySet()) {
            int sceneType = itemScene.getKey().intValue();
            pw.println("    sceneType : " + sceneType);
        }
    }

    private void dumpSceneCbkLongPress(PrintWriter pw) {
        for (Map.Entry<Integer, CallbackInfo> itemScenel : this.mSceneCbkLongPress.entrySet()) {
            int sceneTypel = itemScenel.getKey().intValue();
            pw.println("   LongPress sceneType : " + sceneTypel);
        }
    }

    private void dumpPassThroughKeymap(PrintWriter pw) {
        HashMap<Integer, ArrayList<Integer>> map = ConfigParser.getPassThroughKeymap();
        for (Map.Entry<Integer, ArrayList<Integer>> itemThrough : map.entrySet()) {
            ArrayList<Integer> listThrough = itemThrough.getValue();
            if (listThrough != null && listThrough.size() > 0) {
                for (int i = listThrough.size() - 1; i >= 0; i += -1) {
                    pw.println("        mKeyThroughMap index: " + i + ",listThrough.sceneType: " + listThrough.get(i) + ",listThrough.size: " + listThrough.size());
                }
            }
        }
    }

    private void dumpLongPressTimeout(PrintWriter pw) {
        if (ConfigParser.TIME_SETS != null && ConfigParser.TIME_SETS.size() > 0) {
            for (int i = 0; i < ConfigParser.TIME_SETS.size(); i++) {
                int key = ConfigParser.TIME_SETS.keyAt(i);
                long timeout = ConfigParser.TIME_SETS.get(key).longValue();
                pw.println("        key" + key + ", timeout: " + timeout);
            }
        }
    }

    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        pw.println("Dump HardKeyService:");
        synchronized (this.mLock) {
            dumpCallbackMap(pw);
            dumpLongPressCallbackMap(pw);
            dumpKeycodeMap(pw);
            dumpKeyLongPressMap(pw);
            dumpSceneCbkMap(pw);
            dumpSceneCbkLongPress(pw);
            dumpPassThroughKeymap(pw);
            dumpLongPressTimeout(pw);
        }
    }

    private void checkPermission() {
        if (this.bIsSkipCheck) {
            Log.d(TAG, "bIsSkipCheck is true");
            return;
        }
        int callingUid = Binder.getCallingUid();
        int callingPid = Binder.getCallingPid();
        if (this.mContext.checkCallingOrSelfPermission("ts.permission.QUERY_KEYEVENT") == 0) {
            return;
        }
        throw new SecurityException("Permission Denial: callingPid[" + callingPid + "]callingUid[" + callingUid + "],requires: ts.permission.QUERY_KEYEVENT");
    }
}
