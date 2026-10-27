package com.android.server.telecom.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Message;
import android.telecom.Log;
import android.telecom.Logging.Session;
import android.util.SparseArray;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.IState;
import com.android.internal.util.State;
import com.android.internal.util.StateMachine;
import com.android.server.telecom.BluetoothHeadsetProxy;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.Timeouts;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothRouteManager extends StateMachine {
    private static final String LOG_TAG = BluetoothRouteManager.class.getSimpleName();
    private static final SparseArray<String> MESSAGE_CODE_TO_NAME = new SparseArray<String>() { // from class: com.android.server.telecom.bluetooth.BluetoothRouteManager.1
        {
            put(1, "NEW_DEVICE_CONNECTED");
            put(2, "LOST_DEVICE");
            put(100, "CONNECT_HFP");
            put(101, "DISCONNECT_HFP");
            put(102, "RETRY_HFP_CONNECTION");
            put(200, "BT_AUDIO_IS_ON");
            put(201, "BT_AUDIO_LOST");
            put(300, "CONNECTION_TIMEOUT");
            put(400, "GET_CURRENT_STATE");
            put(9001, "RUN_RUNNABLE");
        }
    };
    private final Map<String, AudioConnectedState> mAudioConnectedStates;
    private final Map<String, AudioConnectingState> mAudioConnectingStates;
    private final State mAudioOffState;
    private final Context mContext;
    private BluetoothDeviceManager mDeviceManager;
    private BluetoothDevice mHearingAidActiveDeviceCache;
    private BluetoothDevice mHfpActiveDeviceCache;
    private BluetoothStateListener mListener;
    private BluetoothDevice mMostRecentlyReportedActiveDevice;
    private final LinkedHashSet<String> mMostRecentlyUsedDevices;
    private final Timeouts.Adapter mTimeoutsAdapter;
    private final Set<State> statesToCleanUp;

    public interface BluetoothStateListener {
        void onBluetoothActiveDeviceGone();

        void onBluetoothActiveDevicePresent();

        void onBluetoothAudioConnected();

        void onBluetoothAudioDisconnected();

        void onBluetoothDeviceListChanged();

        void onUnexpectedBluetoothStateChange();
    }

    private final class AudioOffState extends State {
        public String getName() {
            return "AudioOff";
        }

        private AudioOffState() {
        }

        public void enter() {
            BluetoothDevice bluetoothAudioConnectedDevice = BluetoothRouteManager.this.getBluetoothAudioConnectedDevice();
            if (bluetoothAudioConnectedDevice != null) {
                Log.w(BluetoothRouteManager.LOG_TAG, "Entering AudioOff state but device %s appears to be connected. Switching to audio-on state for that device.", new Object[]{bluetoothAudioConnectedDevice});
                BluetoothRouteManager.this.transitionToActualState();
            }
            BluetoothRouteManager.this.cleanupStatesForDisconnectedDevices();
            if (BluetoothRouteManager.this.mListener != null) {
                BluetoothRouteManager.this.mListener.onBluetoothAudioDisconnected();
            }
        }

        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 9001) {
                ((Runnable) message.obj).run();
                return true;
            }
            SomeArgs someArgs = (SomeArgs) message.obj;
            try {
                if (i == 1) {
                    BluetoothRouteManager.this.addDevice((String) someArgs.arg2);
                } else if (i == 2) {
                    BluetoothRouteManager.this.removeDevice((String) someArgs.arg2);
                } else if (i == 100) {
                    String strConnectBtAudio = BluetoothRouteManager.this.connectBtAudio((String) someArgs.arg2);
                    if (strConnectBtAudio != null) {
                        BluetoothRouteManager.this.transitionTo(BluetoothRouteManager.this.getConnectingStateForAddress(strConnectBtAudio, "AudioOff/CONNECT_HFP"));
                    } else {
                        Log.w(BluetoothRouteManager.LOG_TAG, "Tried to connect to %s but failed to connect to any HFP device.", new Object[]{(String) someArgs.arg2});
                    }
                } else if (i == 102) {
                    Log.i(BluetoothRouteManager.LOG_TAG, "Retrying HFP connection to %s", new Object[]{(String) someArgs.arg2});
                    String strConnectBtAudio2 = BluetoothRouteManager.this.connectBtAudio((String) someArgs.arg2, someArgs.argi1);
                    if (strConnectBtAudio2 != null) {
                        BluetoothRouteManager.this.transitionTo(BluetoothRouteManager.this.getConnectingStateForAddress(strConnectBtAudio2, "AudioOff/RETRY_HFP_CONNECTION"));
                    } else {
                        Log.i(BluetoothRouteManager.LOG_TAG, "Retry failed.", new Object[0]);
                    }
                } else if (i == 400) {
                    ((BlockingQueue) someArgs.arg3).offer(this);
                } else {
                    if (i != 200) {
                        if (i == 201) {
                            Log.i(BluetoothRouteManager.LOG_TAG, "Received HFP off for device %s while HFP off.", new Object[]{(String) someArgs.arg2});
                            BluetoothRouteManager.this.mListener.onUnexpectedBluetoothStateChange();
                        }
                        return true;
                    }
                    String str = (String) someArgs.arg2;
                    Log.w(BluetoothRouteManager.LOG_TAG, "HFP audio unexpectedly turned on from device %s", new Object[]{str});
                    BluetoothRouteManager.this.transitionTo(BluetoothRouteManager.this.getConnectedStateForAddress(str, "AudioOff/BT_AUDIO_IS_ON"));
                }
                return true;
            } finally {
                someArgs.recycle();
            }
        }
    }

    private final class AudioConnectingState extends State {
        private final String mDeviceAddress;

        AudioConnectingState(String str) {
            this.mDeviceAddress = str;
        }

        public String getName() {
            return "Connecting:" + this.mDeviceAddress;
        }

        public void enter() {
            SomeArgs someArgsObtain = SomeArgs.obtain();
            someArgsObtain.arg1 = Log.createSubsession();
            BluetoothRouteManager bluetoothRouteManager = BluetoothRouteManager.this;
            bluetoothRouteManager.sendMessageDelayed(300, someArgsObtain, bluetoothRouteManager.mTimeoutsAdapter.getBluetoothPendingTimeoutMillis(BluetoothRouteManager.this.mContext.getContentResolver()));
            BluetoothRouteManager.this.mListener.onBluetoothAudioConnected();
        }

        public void exit() {
            BluetoothRouteManager.this.removeMessages(300);
        }

        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 9001) {
                ((Runnable) message.obj).run();
                return true;
            }
            SomeArgs someArgs = (SomeArgs) message.obj;
            Object obj = someArgs.arg2;
            String str = (String) obj;
            try {
                if (i == 1) {
                    BluetoothRouteManager.this.addDevice(str);
                } else if (i == 2) {
                    BluetoothRouteManager.this.removeDevice((String) obj);
                    if (Objects.equals(str, this.mDeviceAddress)) {
                        BluetoothRouteManager.this.transitionToActualState();
                    }
                } else if (i != 200) {
                    if (i != 201) {
                        if (i == 300) {
                            Log.i(BluetoothRouteManager.LOG_TAG, "Connection with device %s timed out.", new Object[]{this.mDeviceAddress});
                            BluetoothRouteManager.this.transitionToActualState();
                        } else if (i != 400) {
                            switch (i) {
                                case 100:
                                    if (!Objects.equals(this.mDeviceAddress, str)) {
                                        String strConnectBtAudio = BluetoothRouteManager.this.connectBtAudio(str);
                                        if (strConnectBtAudio != null) {
                                            BluetoothRouteManager.this.transitionTo(BluetoothRouteManager.this.getConnectingStateForAddress(strConnectBtAudio, "AudioConnecting/CONNECT_HFP"));
                                        } else {
                                            Log.w(BluetoothRouteManager.LOG_TAG, "Tried to connect to %s but failed to connect to any HFP device.", new Object[]{(String) someArgs.arg2});
                                        }
                                    }
                                    break;
                                case 101:
                                    BluetoothRouteManager.this.mDeviceManager.disconnectAudio();
                                    break;
                                case 102:
                                    if (Objects.equals(str, this.mDeviceAddress)) {
                                        Log.d(BluetoothRouteManager.LOG_TAG, "Retry message came through while connecting.", new Object[0]);
                                    } else {
                                        String strConnectBtAudio2 = BluetoothRouteManager.this.connectBtAudio(str, someArgs.argi1);
                                        if (strConnectBtAudio2 != null) {
                                            BluetoothRouteManager.this.transitionTo(BluetoothRouteManager.this.getConnectingStateForAddress(strConnectBtAudio2, "AudioConnecting/RETRY_HFP_CONNECTION"));
                                        } else {
                                            Log.i(BluetoothRouteManager.LOG_TAG, "Retry failed.", new Object[0]);
                                        }
                                    }
                                    break;
                                default:
                                    return true;
                            }
                        } else {
                            ((BlockingQueue) someArgs.arg3).offer(this);
                        }
                    } else if (Objects.equals(this.mDeviceAddress, str) || str == null) {
                        Log.i(BluetoothRouteManager.LOG_TAG, "Connection with device %s failed.", new Object[]{this.mDeviceAddress});
                        BluetoothRouteManager.this.transitionToActualState();
                    } else {
                        Log.w(BluetoothRouteManager.LOG_TAG, "Got HFP lost message for device %s while connecting to %s.", new Object[]{str, this.mDeviceAddress});
                        BluetoothRouteManager.this.mListener.onUnexpectedBluetoothStateChange();
                    }
                } else if (Objects.equals(this.mDeviceAddress, str)) {
                    Log.i(BluetoothRouteManager.LOG_TAG, "HFP connection success for device %s.", new Object[]{this.mDeviceAddress});
                    BluetoothRouteManager.this.transitionTo((IState) BluetoothRouteManager.this.mAudioConnectedStates.get(this.mDeviceAddress));
                } else {
                    Log.w(BluetoothRouteManager.LOG_TAG, "In connecting state for device %s but %s is now connected", new Object[]{this.mDeviceAddress, str});
                    BluetoothRouteManager.this.transitionTo(BluetoothRouteManager.this.getConnectedStateForAddress(str, "AudioConnecting/BT_AUDIO_IS_ON"));
                }
                return true;
            } finally {
                someArgs.recycle();
            }
        }
    }

    private final class AudioConnectedState extends State {
        private final String mDeviceAddress;

        AudioConnectedState(String str) {
            this.mDeviceAddress = str;
        }

        public String getName() {
            return "Connected:" + this.mDeviceAddress;
        }

        public void enter() {
            BluetoothRouteManager.this.removeMessages(102);
            BluetoothRouteManager.this.mMostRecentlyUsedDevices.remove(this.mDeviceAddress);
            BluetoothRouteManager.this.mMostRecentlyUsedDevices.add(this.mDeviceAddress);
            BluetoothRouteManager.this.mListener.onBluetoothAudioConnected();
        }

        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 9001) {
                ((Runnable) message.obj).run();
                return true;
            }
            SomeArgs someArgs = (SomeArgs) message.obj;
            Object obj = someArgs.arg2;
            String str = (String) obj;
            try {
                if (i == 1) {
                    BluetoothRouteManager.this.addDevice(str);
                } else if (i == 2) {
                    BluetoothRouteManager.this.removeDevice((String) obj);
                    if (Objects.equals(str, this.mDeviceAddress)) {
                        BluetoothRouteManager.this.transitionToActualState();
                    }
                } else if (i != 200) {
                    if (i != 201) {
                        if (i == 300) {
                            Log.w(BluetoothRouteManager.LOG_TAG, "Received CONNECTION_TIMEOUT while connected.", new Object[0]);
                        } else if (i != 400) {
                            switch (i) {
                                case 100:
                                    if (!Objects.equals(this.mDeviceAddress, str)) {
                                        if (BluetoothRouteManager.this.connectBtAudio(str) != null) {
                                            BluetoothRouteManager.this.transitionTo(BluetoothRouteManager.this.getConnectingStateForAddress(str, "AudioConnected/CONNECT_HFP"));
                                        } else {
                                            Log.w(BluetoothRouteManager.LOG_TAG, "Tried to connect to %s but failed to connect to any HFP device.", new Object[]{(String) someArgs.arg2});
                                        }
                                    }
                                    break;
                                case 101:
                                    BluetoothRouteManager.this.mDeviceManager.disconnectAudio();
                                    break;
                                case 102:
                                    if (Objects.equals(str, this.mDeviceAddress)) {
                                        Log.d(BluetoothRouteManager.LOG_TAG, "Retry message came through while connected.", new Object[0]);
                                    } else {
                                        String strConnectBtAudio = BluetoothRouteManager.this.connectBtAudio(str, someArgs.argi1);
                                        if (strConnectBtAudio != null) {
                                            BluetoothRouteManager.this.transitionTo(BluetoothRouteManager.this.getConnectingStateForAddress(strConnectBtAudio, "AudioConnected/RETRY_HFP_CONNECTION"));
                                        } else {
                                            Log.i(BluetoothRouteManager.LOG_TAG, "Retry failed.", new Object[0]);
                                        }
                                    }
                                    break;
                                default:
                                    return true;
                            }
                        } else {
                            ((BlockingQueue) someArgs.arg3).offer(this);
                        }
                    } else if (Objects.equals(this.mDeviceAddress, str) || str == null) {
                        Log.i(BluetoothRouteManager.LOG_TAG, "HFP connection with device %s lost.", new Object[]{this.mDeviceAddress});
                        BluetoothRouteManager.this.transitionToActualState();
                    } else {
                        Log.w(BluetoothRouteManager.LOG_TAG, "Got HFP lost message for device %s while connected to %s.", new Object[]{str, this.mDeviceAddress});
                        BluetoothRouteManager.this.mListener.onUnexpectedBluetoothStateChange();
                    }
                } else if (Objects.equals(this.mDeviceAddress, str)) {
                    Log.i(BluetoothRouteManager.LOG_TAG, "Received redundant BT_AUDIO_IS_ON for %s", new Object[]{this.mDeviceAddress});
                } else {
                    Log.w(BluetoothRouteManager.LOG_TAG, "In connected state for device %s but %s is now connected", new Object[]{this.mDeviceAddress, str});
                    BluetoothRouteManager.this.transitionTo(BluetoothRouteManager.this.getConnectedStateForAddress(str, "AudioConnected/BT_AUDIO_IS_ON"));
                }
                return true;
            } finally {
                someArgs.recycle();
            }
        }
    }

    public BluetoothRouteManager(Context context, TelecomSystem.SyncRoot syncRoot, BluetoothDeviceManager bluetoothDeviceManager, Timeouts.Adapter adapter) {
        super(BluetoothRouteManager.class.getSimpleName());
        this.mAudioConnectingStates = new HashMap();
        this.mAudioConnectedStates = new HashMap();
        this.statesToCleanUp = new HashSet();
        this.mMostRecentlyUsedDevices = new LinkedHashSet<>();
        this.mHfpActiveDeviceCache = null;
        this.mHearingAidActiveDeviceCache = null;
        this.mMostRecentlyReportedActiveDevice = null;
        this.mContext = context;
        this.mDeviceManager = bluetoothDeviceManager;
        bluetoothDeviceManager.setBluetoothRouteManager(this);
        this.mTimeoutsAdapter = adapter;
        AudioOffState audioOffState = new AudioOffState();
        this.mAudioOffState = audioOffState;
        addState(audioOffState);
        setInitialState(this.mAudioOffState);
        start();
    }

    protected void onPreHandleMessage(Message message) {
        String str = LOG_TAG;
        Object obj = message.obj;
        if (obj != null && (obj instanceof SomeArgs)) {
            Log.continueSession((Session) ((SomeArgs) obj).arg1, "BRM.pM_" + message.what);
            Log.i(str, "Message received: %s.", new Object[]{MESSAGE_CODE_TO_NAME.get(message.what)});
            return;
        }
        if (message.what == 9001 && (message.obj instanceof Runnable)) {
            Log.i(str, "Running runnable for testing", new Object[0]);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Message sent must be of type nonnull SomeArgs, but got ");
        Object obj2 = message.obj;
        sb.append(obj2 == null ? "null" : obj2.getClass().getSimpleName());
        Log.w(str, sb.toString(), new Object[0]);
        Log.w(str, "The message was of code %d = %s", new Object[]{Integer.valueOf(message.what), MESSAGE_CODE_TO_NAME.get(message.what)});
    }

    protected void onPostHandleMessage(Message message) {
        Log.endSession();
    }

    public boolean isBluetoothAvailable() {
        return this.mDeviceManager.getNumConnectedDevices() > 0;
    }

    public boolean isBluetoothAudioConnectedOrPending() {
        String str = LOG_TAG;
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = Log.createSubsession();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        someArgsObtain.arg3 = linkedBlockingQueue;
        sendMessage(400, someArgsObtain);
        try {
            State state = (IState) linkedBlockingQueue.poll(1000L, TimeUnit.MILLISECONDS);
            if (state != null) {
                return state != this.mAudioOffState;
            }
            Log.w(str, "Failed to get a state from the state machine in time -- Handler stuck?", new Object[0]);
            return false;
        } catch (InterruptedException unused) {
            Log.w(str, "isBluetoothAudioConnectedOrPending -- interrupted getting state", new Object[0]);
            return false;
        }
    }

    public void connectBluetoothAudio(String str) {
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = Log.createSubsession();
        someArgsObtain.arg2 = str;
        sendMessage(100, someArgsObtain);
    }

    public void disconnectBluetoothAudio() {
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = Log.createSubsession();
        sendMessage(101, someArgsObtain);
    }

    public void disconnectSco() {
        this.mDeviceManager.disconnectSco();
    }

    public void cacheHearingAidDevice() {
        this.mDeviceManager.cacheHearingAidDevice();
    }

    public void restoreHearingAidDevice() {
        this.mDeviceManager.restoreHearingAidDevice();
    }

    public void setListener(BluetoothStateListener bluetoothStateListener) {
        this.mListener = bluetoothStateListener;
    }

    public void onDeviceAdded(String str) {
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = Log.createSubsession();
        someArgsObtain.arg2 = str;
        sendMessage(1, someArgsObtain);
        this.mListener.onBluetoothDeviceListChanged();
    }

    public void onDeviceLost(String str) {
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = Log.createSubsession();
        someArgsObtain.arg2 = str;
        sendMessage(2, someArgsObtain);
        this.mListener.onBluetoothDeviceListChanged();
    }

    public void onActiveDeviceChanged(BluetoothDevice bluetoothDevice, boolean z) {
        boolean z2 = (this.mHearingAidActiveDeviceCache == null && this.mHfpActiveDeviceCache == null) ? false : true;
        if (z) {
            this.mHearingAidActiveDeviceCache = bluetoothDevice;
        } else {
            this.mHfpActiveDeviceCache = bluetoothDevice;
        }
        if (bluetoothDevice != null) {
            this.mMostRecentlyReportedActiveDevice = bluetoothDevice;
        }
        boolean z3 = (this.mHearingAidActiveDeviceCache == null && this.mHfpActiveDeviceCache == null) ? false : true;
        if (z2 && !z3) {
            this.mListener.onBluetoothActiveDeviceGone();
        } else {
            if (z2 || !z3) {
                return;
            }
            this.mListener.onBluetoothActiveDevicePresent();
        }
    }

    public boolean hasBtActiveDevice() {
        return (this.mHearingAidActiveDeviceCache == null && this.mHfpActiveDeviceCache == null) ? false : true;
    }

    public Collection<BluetoothDevice> getConnectedDevices() {
        return this.mDeviceManager.getUniqueConnectedDevices();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String connectBtAudio(String str) {
        return connectBtAudio(str, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String connectBtAudio(final String str, int i) {
        Collection<BluetoothDevice> connectedDevices = this.mDeviceManager.getConnectedDevices();
        Optional<BluetoothDevice> optionalFindAny = connectedDevices.stream().filter(new Predicate() { // from class: com.android.server.telecom.bluetooth.-$$Lambda$BluetoothRouteManager$eKf5MaJzkN_GNAzkxGjlkk_1EOI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.equals(((BluetoothDevice) obj).getAddress(), str);
            }
        }).findAny();
        String activeDeviceAddress = optionalFindAny.isPresent() ? str : getActiveDeviceAddress();
        if (activeDeviceAddress == null) {
            Log.i(this, "No device specified and BT stack has no active device. Using arbitrary device", new Object[0]);
            if (connectedDevices.size() > 0) {
                activeDeviceAddress = connectedDevices.iterator().next().getAddress();
            } else {
                Log.i(this, "No devices available at all. Not connecting.", new Object[0]);
                return null;
            }
        }
        if (!optionalFindAny.isPresent()) {
            Log.i(this, "No device with address %s available. Using %s instead.", new Object[]{str, activeDeviceAddress});
        }
        BluetoothDevice bluetoothAudioConnectedDevice = getBluetoothAudioConnectedDevice();
        if (bluetoothAudioConnectedDevice != null && bluetoothAudioConnectedDevice.getAddress().equals(activeDeviceAddress)) {
            Log.i(this, "trying to connect to already connected device -- skipping connection and going into the actual connected state.", new Object[0]);
            transitionToActualState();
            return null;
        }
        if (this.mDeviceManager.connectAudio(activeDeviceAddress)) {
            return activeDeviceAddress;
        }
        boolean z = i < 2;
        String str2 = LOG_TAG;
        Object[] objArr = new Object[2];
        objArr[0] = activeDeviceAddress;
        objArr[1] = z ? "retry" : "not retry";
        Log.w(str2, "Could not connect to %s. Will %s", objArr);
        if (z) {
            SomeArgs someArgsObtain = SomeArgs.obtain();
            someArgsObtain.arg1 = Log.createSubsession();
            someArgsObtain.arg2 = activeDeviceAddress;
            someArgsObtain.argi1 = i + 1;
            sendMessageDelayed(102, someArgsObtain, this.mTimeoutsAdapter.getRetryBluetoothConnectAudioBackoffMillis(this.mContext.getContentResolver()));
        }
        return null;
    }

    private String getActiveDeviceAddress() {
        BluetoothDevice bluetoothDevice = this.mHfpActiveDeviceCache;
        if (bluetoothDevice != null) {
            return bluetoothDevice.getAddress();
        }
        BluetoothDevice bluetoothDevice2 = this.mHearingAidActiveDeviceCache;
        if (bluetoothDevice2 != null) {
            return bluetoothDevice2.getAddress();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionToActualState() {
        BluetoothDevice bluetoothAudioConnectedDevice = getBluetoothAudioConnectedDevice();
        if (bluetoothAudioConnectedDevice != null) {
            Log.i(LOG_TAG, "Device %s is already connected; going to AudioConnected.", new Object[]{bluetoothAudioConnectedDevice});
            transitionTo(getConnectedStateForAddress(bluetoothAudioConnectedDevice.getAddress(), "transitionToActualState"));
        } else {
            transitionTo(this.mAudioOffState);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    @com.android.internal.annotations.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.bluetooth.BluetoothDevice getBluetoothAudioConnectedDevice() {
        /*
            r6 = this;
            com.android.server.telecom.bluetooth.BluetoothDeviceManager r0 = r6.mDeviceManager
            com.android.server.telecom.BluetoothHeadsetProxy r0 = r0.getHeadsetService()
            com.android.server.telecom.bluetooth.BluetoothDeviceManager r1 = r6.mDeviceManager
            android.bluetooth.BluetoothHearingAid r1 = r1.getHearingAidService()
            r2 = 0
            r3 = 0
            if (r0 != 0) goto L1a
            if (r1 != 0) goto L1a
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r1 = "getBluetoothAudioConnectedDevice: no service available."
            android.telecom.Log.i(r6, r1, r0)
            return r3
        L1a:
            if (r0 == 0) goto L28
            android.bluetooth.BluetoothDevice r4 = r0.getActiveDevice()
            int r0 = r0.getAudioState(r4)
            r5 = 10
            if (r0 != r5) goto L29
        L28:
            r4 = r3
        L29:
            if (r1 == 0) goto L42
            java.util.List r0 = r1.getActiveDevices()
            java.util.Iterator r0 = r0.iterator()
        L33:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L42
            java.lang.Object r1 = r0.next()
            android.bluetooth.BluetoothDevice r1 = (android.bluetooth.BluetoothDevice) r1
            if (r1 == 0) goto L33
            r3 = r1
        L42:
            if (r4 == 0) goto L51
            if (r3 == 0) goto L50
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r1 = "Both HFP and hearing aid are reporting active devices. Going with the most recently reported active device: %s"
            android.telecom.Log.i(r6, r1, r0)
            android.bluetooth.BluetoothDevice r6 = r6.mMostRecentlyReportedActiveDevice
            return r6
        L50:
            return r4
        L51:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.bluetooth.BluetoothRouteManager.getBluetoothAudioConnectedDevice():android.bluetooth.BluetoothDevice");
    }

    @VisibleForTesting
    public boolean isInbandRingingEnabled() {
        BluetoothHeadsetProxy headsetService = this.mDeviceManager.getHeadsetService();
        if (headsetService == null) {
            Log.i(this, "isInbandRingingEnabled: no headset service available.", new Object[0]);
            return false;
        }
        return headsetService.isInbandRingingEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean addDevice(String str) {
        if (this.mAudioConnectingStates.containsKey(str)) {
            Log.i(this, "Attempting to add device %s twice.", new Object[]{str});
            return false;
        }
        AudioConnectedState audioConnectedState = new AudioConnectedState(str);
        AudioConnectingState audioConnectingState = new AudioConnectingState(str);
        this.mAudioConnectingStates.put(str, audioConnectingState);
        this.mAudioConnectedStates.put(str, audioConnectedState);
        addState(audioConnectedState);
        addState(audioConnectingState);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeDevice(String str) {
        if (!this.mAudioConnectingStates.containsKey(str)) {
            Log.i(this, "Attempting to remove already-removed device %s", new Object[]{str});
            return false;
        }
        this.statesToCleanUp.add(this.mAudioConnectingStates.remove(str));
        this.statesToCleanUp.add(this.mAudioConnectedStates.remove(str));
        this.mMostRecentlyUsedDevices.remove(str);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AudioConnectingState getConnectingStateForAddress(String str, String str2) {
        if (!this.mAudioConnectingStates.containsKey(str)) {
            Log.w(LOG_TAG, "Device being connected to does not have a corresponding state: %s", new Object[]{str2});
            addDevice(str);
        }
        return this.mAudioConnectingStates.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AudioConnectedState getConnectedStateForAddress(String str, String str2) {
        if (!this.mAudioConnectedStates.containsKey(str)) {
            Log.w(LOG_TAG, "Device already connected to does not have a corresponding state: %s", new Object[]{str2});
            addDevice(str);
        }
        return this.mAudioConnectedStates.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanupStatesForDisconnectedDevices() {
        for (State state : this.statesToCleanUp) {
            if (state != null) {
                removeState(state);
            }
        }
        this.statesToCleanUp.clear();
    }

    @VisibleForTesting
    public void setInitialStateForTesting(final String str, final BluetoothDevice bluetoothDevice) {
        sendMessage(9001, new Runnable() { // from class: com.android.server.telecom.bluetooth.-$$Lambda$BluetoothRouteManager$DMRE70gayY3ytI0bdpRnooQqKN8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setInitialStateForTesting$1$BluetoothRouteManager(str, bluetoothDevice);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0035  */
    /* JADX INFO: renamed from: lambda$setInitialStateForTesting$1, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void lambda$setInitialStateForTesting$1$BluetoothRouteManager(java.lang.String r6, android.bluetooth.BluetoothDevice r7) {
        /*
            r5 = this;
            int r0 = r6.hashCode()
            r1 = 1040541145(0x3e0565d9, float:0.13027133)
            r2 = 0
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L2b
            r1 = 1217813208(0x48965ad8, float:307926.75)
            if (r0 == r1) goto L21
            r1 = 1424757481(0x54ec12e9, float:8.1114363E12)
            if (r0 == r1) goto L17
            goto L35
        L17:
            java.lang.String r0 = "Connected"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L35
            r0 = r3
            goto L36
        L21:
            java.lang.String r0 = "Connecting"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L35
            r0 = r4
            goto L36
        L2b:
            java.lang.String r0 = "AudioOff"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L35
            r0 = r2
            goto L36
        L35:
            r0 = -1
        L36:
            if (r0 == 0) goto L57
            java.lang.String r1 = "setInitialStateForTesting"
            if (r0 == r4) goto L4b
            if (r0 == r3) goto L3f
            goto L5c
        L3f:
            java.lang.String r7 = r7.getAddress()
            com.android.server.telecom.bluetooth.BluetoothRouteManager$AudioConnectedState r7 = r5.getConnectedStateForAddress(r7, r1)
            r5.transitionTo(r7)
            goto L5c
        L4b:
            java.lang.String r7 = r7.getAddress()
            com.android.server.telecom.bluetooth.BluetoothRouteManager$AudioConnectingState r7 = r5.getConnectingStateForAddress(r7, r1)
            r5.transitionTo(r7)
            goto L5c
        L57:
            com.android.internal.util.State r7 = r5.mAudioOffState
            r5.transitionTo(r7)
        L5c:
            java.lang.String r5 = com.android.server.telecom.bluetooth.BluetoothRouteManager.LOG_TAG
            java.lang.Object[] r7 = new java.lang.Object[r4]
            r7[r2] = r6
            java.lang.String r6 = "transition for testing done: %s"
            android.telecom.Log.i(r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.bluetooth.BluetoothRouteManager.lambda$setInitialStateForTesting$1$BluetoothRouteManager(java.lang.String, android.bluetooth.BluetoothDevice):void");
    }

    @VisibleForTesting
    public void setActiveDeviceCacheForTesting(BluetoothDevice bluetoothDevice, boolean z) {
        if (z) {
            this.mHearingAidActiveDeviceCache = bluetoothDevice;
        } else {
            this.mHfpActiveDeviceCache = bluetoothDevice;
        }
    }
}
