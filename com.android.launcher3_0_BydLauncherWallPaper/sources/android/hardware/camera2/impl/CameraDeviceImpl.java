package android.hardware.camera2.impl;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraOfflineSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraDeviceUser;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.impl.CameraOfflineSessionImpl;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.utils.SubmitInfo;
import android.hardware.camera2.utils.SurfaceUtils;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import android.util.SparseArray;
import android.view.Surface;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes.dex */
public class CameraDeviceImpl extends CameraDevice implements IBinder.DeathRecipient {
    private static final long NANO_PER_SECOND = 1000000000;
    private static final int REQUEST_ID_NONE = -1;
    private final String TAG;
    private final int mAppTargetSdkVersion;
    private final String mCameraId;
    private final CameraCharacteristics mCharacteristics;
    private CameraCaptureSessionCore mCurrentSession;
    private final CameraDevice.StateCallback mDeviceCallback;
    private final Executor mDeviceExecutor;
    private CameraOfflineSessionImpl mOfflineSessionImpl;
    private ExecutorService mOfflineSwitchService;
    private ICameraDeviceUserWrapper mRemoteDevice;
    private int[] mRepeatingRequestTypes;
    private volatile StateCallbackKK mSessionStateCallback;
    private final int mTotalPartialCount;
    private final boolean DEBUG = false;
    final Object mInterfaceLock = new Object();
    private final CameraDeviceCallbacks mCallbacks = new CameraDeviceCallbacks();
    private final AtomicBoolean mClosing = new AtomicBoolean();
    private boolean mInError = false;
    private boolean mIdle = true;
    private SparseArray<CaptureCallbackHolder> mCaptureCallbackMap = new SparseArray<>();
    private int mRepeatingRequestId = -1;
    private AbstractMap.SimpleEntry<Integer, InputConfiguration> mConfiguredInput = new AbstractMap.SimpleEntry<>(-1, null);
    private final SparseArray<OutputConfiguration> mConfiguredOutputs = new SparseArray<>();
    private final HashSet<Integer> mOfflineSupport = new HashSet<>();
    private final List<RequestLastFrameNumbersHolder> mRequestLastFrameNumbersList = new ArrayList();
    private FrameNumberTracker mFrameNumberTracker = new FrameNumberTracker();
    private int mNextSessionId = 0;
    private final Runnable mCallOnOpened = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice == null) {
                    return;
                }
                StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
                if (stateCallbackKK != null) {
                    stateCallbackKK.onOpened(CameraDeviceImpl.this);
                }
                CameraDeviceImpl.this.mDeviceCallback.onOpened(CameraDeviceImpl.this);
            }
        }
    };
    private final Runnable mCallOnUnconfigured = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.2
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice == null) {
                    return;
                }
                StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
                if (stateCallbackKK != null) {
                    stateCallbackKK.onUnconfigured(CameraDeviceImpl.this);
                }
            }
        }
    };
    private final Runnable mCallOnActive = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.3
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice == null) {
                    return;
                }
                StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
                if (stateCallbackKK != null) {
                    stateCallbackKK.onActive(CameraDeviceImpl.this);
                }
            }
        }
    };
    private final Runnable mCallOnBusy = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.4
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice == null) {
                    return;
                }
                StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
                if (stateCallbackKK != null) {
                    stateCallbackKK.onBusy(CameraDeviceImpl.this);
                }
            }
        }
    };
    private final Runnable mCallOnClosed = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.5
        private boolean mClosedOnce = false;

        @Override // java.lang.Runnable
        public void run() {
            StateCallbackKK stateCallbackKK;
            if (this.mClosedOnce) {
                throw new AssertionError("Don't post #onClosed more than once");
            }
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
            }
            if (stateCallbackKK != null) {
                stateCallbackKK.onClosed(CameraDeviceImpl.this);
            }
            CameraDeviceImpl.this.mDeviceCallback.onClosed(CameraDeviceImpl.this);
            this.mClosedOnce = true;
        }
    };
    private final Runnable mCallOnIdle = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.6
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice == null) {
                    return;
                }
                StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
                if (stateCallbackKK != null) {
                    stateCallbackKK.onIdle(CameraDeviceImpl.this);
                }
            }
        }
    };
    private final Runnable mCallOnDisconnected = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.7
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice == null) {
                    return;
                }
                StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
                if (stateCallbackKK != null) {
                    stateCallbackKK.onDisconnected(CameraDeviceImpl.this);
                }
                CameraDeviceImpl.this.mDeviceCallback.onDisconnected(CameraDeviceImpl.this);
            }
        }
    };

    public static abstract class StateCallbackKK extends CameraDevice.StateCallback {
        public void onActive(CameraDevice cameraDevice) {
        }

        public void onBusy(CameraDevice cameraDevice) {
        }

        public void onIdle(CameraDevice cameraDevice) {
        }

        public void onRequestQueueEmpty() {
        }

        public void onSurfacePrepared(Surface surface) {
        }

        public void onUnconfigured(CameraDevice cameraDevice) {
        }
    }

    public CameraDeviceImpl(String str, CameraDevice.StateCallback stateCallback, Executor executor, CameraCharacteristics cameraCharacteristics, int i) {
        if (str == null || stateCallback == null || executor == null || cameraCharacteristics == null) {
            throw new IllegalArgumentException("Null argument given");
        }
        this.mCameraId = str;
        this.mDeviceCallback = stateCallback;
        this.mDeviceExecutor = executor;
        this.mCharacteristics = cameraCharacteristics;
        this.mAppTargetSdkVersion = i;
        String str2 = String.format("CameraDevice-JV-%s", str);
        this.TAG = str2.length() > 23 ? str2.substring(0, 23) : str2;
        Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT);
        if (num == null) {
            this.mTotalPartialCount = 1;
        } else {
            this.mTotalPartialCount = num.intValue();
        }
    }

    public CameraDeviceCallbacks getCallbacks() {
        return this.mCallbacks;
    }

    public void setRemoteDevice(ICameraDeviceUser iCameraDeviceUser) throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            if (this.mInError) {
                return;
            }
            this.mRemoteDevice = new ICameraDeviceUserWrapper(iCameraDeviceUser);
            IBinder iBinderAsBinder = iCameraDeviceUser.asBinder();
            if (iBinderAsBinder != null) {
                try {
                    iBinderAsBinder.linkToDeath(this, 0);
                } catch (RemoteException unused) {
                    this.mDeviceExecutor.execute(this.mCallOnDisconnected);
                    throw new CameraAccessException(2, "The camera device has encountered a serious error");
                }
            }
            this.mDeviceExecutor.execute(this.mCallOnOpened);
            this.mDeviceExecutor.execute(this.mCallOnUnconfigured);
        }
    }

    public void setRemoteFailure(ServiceSpecificException serviceSpecificException) {
        final boolean z;
        int i = serviceSpecificException.errorCode;
        final int i2 = 4;
        if (i == 4) {
            z = false;
        } else if (i == 10) {
            z = true;
        } else {
            if (i == 6) {
                i2 = 3;
            } else if (i == 7) {
                z = true;
                i2 = 1;
            } else if (i != 8) {
                Log.e(this.TAG, "Unexpected failure in opening camera device: " + serviceSpecificException.errorCode + serviceSpecificException.getMessage());
            } else {
                i2 = 2;
            }
            z = true;
        }
        synchronized (this.mInterfaceLock) {
            this.mInError = true;
            this.mDeviceExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.8
                @Override // java.lang.Runnable
                public void run() {
                    if (z) {
                        CameraDeviceImpl.this.mDeviceCallback.onError(CameraDeviceImpl.this, i2);
                    } else {
                        CameraDeviceImpl.this.mDeviceCallback.onDisconnected(CameraDeviceImpl.this);
                    }
                }
            });
        }
    }

    @Override // android.hardware.camera2.CameraDevice
    public String getId() {
        return this.mCameraId;
    }

    public void configureOutputs(List<Surface> list) throws CameraAccessException {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Surface> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new OutputConfiguration(it.next()));
        }
        configureStreamsChecked(null, arrayList, 0, null);
    }

    public boolean configureStreamsChecked(InputConfiguration inputConfiguration, List<OutputConfiguration> list, int i, CaptureRequest captureRequest) throws CameraAccessException {
        int[] iArrEndConfigure;
        if (list == null) {
            list = new ArrayList<>();
        }
        if (list.size() == 0 && inputConfiguration != null) {
            throw new IllegalArgumentException("cannot configure an input stream without any output streams");
        }
        checkInputConfiguration(inputConfiguration);
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            HashSet hashSet = new HashSet(list);
            ArrayList<Integer> arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.mConfiguredOutputs.size(); i2++) {
                int iKeyAt = this.mConfiguredOutputs.keyAt(i2);
                OutputConfiguration outputConfigurationValueAt = this.mConfiguredOutputs.valueAt(i2);
                if (!list.contains(outputConfigurationValueAt) || outputConfigurationValueAt.isDeferredConfiguration()) {
                    arrayList.add(Integer.valueOf(iKeyAt));
                } else {
                    hashSet.remove(outputConfigurationValueAt);
                }
            }
            this.mDeviceExecutor.execute(this.mCallOnBusy);
            stopRepeating();
            try {
                try {
                    waitUntilIdle();
                    this.mRemoteDevice.beginConfigure();
                    InputConfiguration value = this.mConfiguredInput.getValue();
                    if (inputConfiguration != value && (inputConfiguration == null || !inputConfiguration.equals(value))) {
                        if (value != null) {
                            this.mRemoteDevice.deleteStream(this.mConfiguredInput.getKey().intValue());
                            this.mConfiguredInput = new AbstractMap.SimpleEntry<>(-1, null);
                        }
                        if (inputConfiguration != null) {
                            this.mConfiguredInput = new AbstractMap.SimpleEntry<>(Integer.valueOf(this.mRemoteDevice.createInputStream(inputConfiguration.getWidth(), inputConfiguration.getHeight(), inputConfiguration.getFormat())), inputConfiguration);
                        }
                    }
                    for (Integer num : arrayList) {
                        this.mRemoteDevice.deleteStream(num.intValue());
                        this.mConfiguredOutputs.delete(num.intValue());
                    }
                    for (OutputConfiguration outputConfiguration : list) {
                        if (hashSet.contains(outputConfiguration)) {
                            this.mConfiguredOutputs.put(this.mRemoteDevice.createStream(outputConfiguration), outputConfiguration);
                        }
                    }
                    if (captureRequest != null) {
                        iArrEndConfigure = this.mRemoteDevice.endConfigure(i, captureRequest.getNativeCopy());
                    } else {
                        iArrEndConfigure = this.mRemoteDevice.endConfigure(i, null);
                    }
                    this.mOfflineSupport.clear();
                    if (iArrEndConfigure != null && iArrEndConfigure.length > 0) {
                        for (int i3 : iArrEndConfigure) {
                            this.mOfflineSupport.add(Integer.valueOf(i3));
                        }
                    }
                    if (list.size() > 0) {
                        this.mDeviceExecutor.execute(this.mCallOnIdle);
                    } else {
                        this.mDeviceExecutor.execute(this.mCallOnUnconfigured);
                    }
                } catch (CameraAccessException e) {
                    if (e.getReason() == 4) {
                        throw new IllegalStateException("The camera is currently busy. You must wait until the previous operation completes.", e);
                    }
                    throw e;
                } catch (IllegalArgumentException e2) {
                    Log.w(this.TAG, "Stream configuration failed due to: " + e2.getMessage());
                    this.mDeviceExecutor.execute(this.mCallOnUnconfigured);
                    return false;
                }
            } catch (Throwable th) {
                this.mDeviceExecutor.execute(this.mCallOnUnconfigured);
                throw th;
            }
        }
        return true;
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createCaptureSession(List<Surface> list, CameraCaptureSession.StateCallback stateCallback, Handler handler) throws CameraAccessException {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Surface> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new OutputConfiguration(it.next()));
        }
        createCaptureSessionInternal(null, arrayList, stateCallback, checkAndWrapHandler(handler), 0, null);
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createCaptureSessionByOutputConfigurations(List<OutputConfiguration> list, CameraCaptureSession.StateCallback stateCallback, Handler handler) throws CameraAccessException {
        createCaptureSessionInternal(null, new ArrayList(list), stateCallback, checkAndWrapHandler(handler), 0, null);
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createReprocessableCaptureSession(InputConfiguration inputConfiguration, List<Surface> list, CameraCaptureSession.StateCallback stateCallback, Handler handler) throws CameraAccessException {
        if (inputConfiguration == null) {
            throw new IllegalArgumentException("inputConfig cannot be null when creating a reprocessable capture session");
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Surface> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new OutputConfiguration(it.next()));
        }
        createCaptureSessionInternal(inputConfiguration, arrayList, stateCallback, checkAndWrapHandler(handler), 0, null);
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createReprocessableCaptureSessionByConfigurations(InputConfiguration inputConfiguration, List<OutputConfiguration> list, CameraCaptureSession.StateCallback stateCallback, Handler handler) throws CameraAccessException {
        if (inputConfiguration == null) {
            throw new IllegalArgumentException("inputConfig cannot be null when creating a reprocessable capture session");
        }
        if (list == null) {
            throw new IllegalArgumentException("Output configurations cannot be null when creating a reprocessable capture session");
        }
        ArrayList arrayList = new ArrayList();
        Iterator<OutputConfiguration> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new OutputConfiguration(it.next()));
        }
        createCaptureSessionInternal(inputConfiguration, arrayList, stateCallback, checkAndWrapHandler(handler), 0, null);
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createConstrainedHighSpeedCaptureSession(List<Surface> list, CameraCaptureSession.StateCallback stateCallback, Handler handler) throws CameraAccessException {
        if (list == null || list.size() == 0 || list.size() > 2) {
            throw new IllegalArgumentException("Output surface list must not be null and the size must be no more than 2");
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Surface> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new OutputConfiguration(it.next()));
        }
        createCaptureSessionInternal(null, arrayList, stateCallback, checkAndWrapHandler(handler), 1, null);
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createCustomCaptureSession(InputConfiguration inputConfiguration, List<OutputConfiguration> list, int i, CameraCaptureSession.StateCallback stateCallback, Handler handler) throws CameraAccessException {
        ArrayList arrayList = new ArrayList();
        Iterator<OutputConfiguration> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new OutputConfiguration(it.next()));
        }
        createCaptureSessionInternal(inputConfiguration, arrayList, stateCallback, checkAndWrapHandler(handler), i, null);
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createCaptureSession(SessionConfiguration sessionConfiguration) throws CameraAccessException {
        if (sessionConfiguration == null) {
            throw new IllegalArgumentException("Invalid session configuration");
        }
        List<OutputConfiguration> outputConfigurations = sessionConfiguration.getOutputConfigurations();
        if (outputConfigurations == null) {
            throw new IllegalArgumentException("Invalid output configurations");
        }
        if (sessionConfiguration.getExecutor() == null) {
            throw new IllegalArgumentException("Invalid executor");
        }
        createCaptureSessionInternal(sessionConfiguration.getInputConfiguration(), outputConfigurations, sessionConfiguration.getStateCallback(), sessionConfiguration.getExecutor(), sessionConfiguration.getSessionType(), sessionConfiguration.getSessionParameters());
    }

    private void createCaptureSessionInternal(InputConfiguration inputConfiguration, List<OutputConfiguration> list, CameraCaptureSession.StateCallback stateCallback, Executor executor, int i, CaptureRequest captureRequest) throws CameraAccessException {
        boolean z;
        Surface inputSurface;
        CameraCaptureSessionCore cameraCaptureSessionImpl;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            boolean z2 = i == 1;
            if (z2 && inputConfiguration != null) {
                throw new IllegalArgumentException("Constrained high speed session doesn't support input configuration yet.");
            }
            CameraCaptureSessionCore cameraCaptureSessionCore = this.mCurrentSession;
            if (cameraCaptureSessionCore != null) {
                cameraCaptureSessionCore.replaceSessionClose();
            }
            try {
                boolean zConfigureStreamsChecked = configureStreamsChecked(inputConfiguration, list, i, captureRequest);
                inputSurface = (!zConfigureStreamsChecked || inputConfiguration == null) ? null : this.mRemoteDevice.getInputSurface();
                z = zConfigureStreamsChecked;
                e = null;
            } catch (CameraAccessException e) {
                e = e;
                z = false;
                inputSurface = null;
            }
            if (z2) {
                ArrayList arrayList = new ArrayList(list.size());
                Iterator<OutputConfiguration> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().getSurface());
                }
                SurfaceUtils.checkConstrainedHighSpeedSurfaces(arrayList, null, (StreamConfigurationMap) getCharacteristics().get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP));
                int i2 = this.mNextSessionId;
                this.mNextSessionId = i2 + 1;
                cameraCaptureSessionImpl = new CameraConstrainedHighSpeedCaptureSessionImpl(i2, stateCallback, executor, this, this.mDeviceExecutor, z, this.mCharacteristics);
            } else {
                int i3 = this.mNextSessionId;
                this.mNextSessionId = i3 + 1;
                cameraCaptureSessionImpl = new CameraCaptureSessionImpl(i3, inputSurface, stateCallback, executor, this, this.mDeviceExecutor, z);
            }
            this.mCurrentSession = cameraCaptureSessionImpl;
            if (e != null) {
                throw e;
            }
            this.mSessionStateCallback = cameraCaptureSessionImpl.getDeviceStateCallback();
        }
    }

    @Override // android.hardware.camera2.CameraDevice
    public boolean isSessionConfigurationSupported(SessionConfiguration sessionConfiguration) throws UnsupportedOperationException, CameraAccessException, IllegalArgumentException {
        boolean zIsSessionConfigurationSupported;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            zIsSessionConfigurationSupported = this.mRemoteDevice.isSessionConfigurationSupported(sessionConfiguration);
        }
        return zIsSessionConfigurationSupported;
    }

    public void setSessionListener(StateCallbackKK stateCallbackKK) {
        synchronized (this.mInterfaceLock) {
            this.mSessionStateCallback = stateCallbackKK;
        }
    }

    private void overrideEnableZsl(CameraMetadataNative cameraMetadataNative, boolean z) {
        if (((Boolean) cameraMetadataNative.get(CaptureRequest.CONTROL_ENABLE_ZSL)) == null) {
            return;
        }
        cameraMetadataNative.set(CaptureRequest.CONTROL_ENABLE_ZSL, Boolean.valueOf(z));
    }

    @Override // android.hardware.camera2.CameraDevice
    public CaptureRequest.Builder createCaptureRequest(int i, Set<String> set) throws CameraAccessException {
        CaptureRequest.Builder builder;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                if (it.next() == getId()) {
                    throw new IllegalStateException("Physical id matches the logical id!");
                }
            }
            CameraMetadataNative cameraMetadataNativeCreateDefaultRequest = this.mRemoteDevice.createDefaultRequest(i);
            if (this.mAppTargetSdkVersion < 26 || i != 2) {
                overrideEnableZsl(cameraMetadataNativeCreateDefaultRequest, false);
            }
            builder = new CaptureRequest.Builder(cameraMetadataNativeCreateDefaultRequest, false, -1, getId(), set);
        }
        return builder;
    }

    @Override // android.hardware.camera2.CameraDevice
    public CaptureRequest.Builder createCaptureRequest(int i) throws CameraAccessException {
        CaptureRequest.Builder builder;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            CameraMetadataNative cameraMetadataNativeCreateDefaultRequest = this.mRemoteDevice.createDefaultRequest(i);
            if (this.mAppTargetSdkVersion < 26 || i != 2) {
                overrideEnableZsl(cameraMetadataNativeCreateDefaultRequest, false);
            }
            builder = new CaptureRequest.Builder(cameraMetadataNativeCreateDefaultRequest, false, -1, getId(), null);
        }
        return builder;
    }

    @Override // android.hardware.camera2.CameraDevice
    public CaptureRequest.Builder createReprocessCaptureRequest(TotalCaptureResult totalCaptureResult) throws CameraAccessException {
        CaptureRequest.Builder builder;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            builder = new CaptureRequest.Builder(new CameraMetadataNative(totalCaptureResult.getNativeCopy()), true, totalCaptureResult.getSessionId(), getId(), null);
        }
        return builder;
    }

    public void prepare(Surface surface) throws CameraAccessException {
        int iKeyAt;
        if (surface == null) {
            throw new IllegalArgumentException("Surface is null");
        }
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            int i = 0;
            while (true) {
                if (i >= this.mConfiguredOutputs.size()) {
                    iKeyAt = -1;
                    break;
                } else {
                    if (this.mConfiguredOutputs.valueAt(i).getSurfaces().contains(surface)) {
                        iKeyAt = this.mConfiguredOutputs.keyAt(i);
                        break;
                    }
                    i++;
                }
            }
            if (iKeyAt == -1) {
                throw new IllegalArgumentException("Surface is not part of this session");
            }
            this.mRemoteDevice.prepare(iKeyAt);
        }
    }

    public void prepare(int i, Surface surface) throws CameraAccessException {
        int iKeyAt;
        if (surface == null) {
            throw new IllegalArgumentException("Surface is null");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("Invalid maxCount given: " + i);
        }
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            int i2 = 0;
            while (true) {
                if (i2 >= this.mConfiguredOutputs.size()) {
                    iKeyAt = -1;
                    break;
                } else {
                    if (surface == this.mConfiguredOutputs.valueAt(i2).getSurface()) {
                        iKeyAt = this.mConfiguredOutputs.keyAt(i2);
                        break;
                    }
                    i2++;
                }
            }
            if (iKeyAt == -1) {
                throw new IllegalArgumentException("Surface is not part of this session");
            }
            this.mRemoteDevice.prepare2(i, iKeyAt);
        }
    }

    public void updateOutputConfiguration(OutputConfiguration outputConfiguration) throws CameraAccessException {
        int iKeyAt;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            int i = 0;
            while (true) {
                if (i >= this.mConfiguredOutputs.size()) {
                    iKeyAt = -1;
                    break;
                } else {
                    if (outputConfiguration.getSurface() == this.mConfiguredOutputs.valueAt(i).getSurface()) {
                        iKeyAt = this.mConfiguredOutputs.keyAt(i);
                        break;
                    }
                    i++;
                }
            }
            if (iKeyAt == -1) {
                throw new IllegalArgumentException("Invalid output configuration");
            }
            this.mRemoteDevice.updateOutputConfiguration(iKeyAt, outputConfiguration);
            this.mConfiguredOutputs.put(iKeyAt, outputConfiguration);
        }
    }

    public CameraOfflineSession switchToOffline(Collection<Surface> collection, Executor executor, CameraOfflineSession.CameraOfflineSessionCallback cameraOfflineSessionCallback) throws CameraAccessException {
        CameraOfflineSessionImpl cameraOfflineSessionImpl;
        int iKeyAt;
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Invalid offline surfaces!");
        }
        HashSet hashSet = new HashSet();
        SparseArray sparseArray = new SparseArray();
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            if (this.mOfflineSessionImpl != null) {
                throw new IllegalStateException("Switch to offline mode already in progress");
            }
            for (Surface surface : collection) {
                int i = 0;
                while (true) {
                    if (i >= this.mConfiguredOutputs.size()) {
                        iKeyAt = -1;
                        break;
                    }
                    if (surface == this.mConfiguredOutputs.valueAt(i).getSurface()) {
                        iKeyAt = this.mConfiguredOutputs.keyAt(i);
                        sparseArray.append(iKeyAt, this.mConfiguredOutputs.valueAt(i));
                        break;
                    }
                    i++;
                }
                if (iKeyAt == -1) {
                    throw new IllegalArgumentException("Offline surface is not part of this session");
                }
                if (!this.mOfflineSupport.contains(Integer.valueOf(iKeyAt))) {
                    throw new IllegalArgumentException("Surface: " + surface + " does not  support offline mode");
                }
                hashSet.add(Integer.valueOf(iKeyAt));
            }
            stopRepeating();
            cameraOfflineSessionImpl = new CameraOfflineSessionImpl(this.mCameraId, this.mCharacteristics, executor, cameraOfflineSessionCallback, sparseArray, this.mConfiguredInput, this.mConfiguredOutputs, this.mFrameNumberTracker, this.mCaptureCallbackMap, this.mRequestLastFrameNumbersList);
            this.mOfflineSessionImpl = cameraOfflineSessionImpl;
            this.mOfflineSwitchService = Executors.newSingleThreadExecutor();
            this.mConfiguredOutputs.clear();
            this.mConfiguredInput = new AbstractMap.SimpleEntry<>(-1, null);
            this.mIdle = true;
            this.mCaptureCallbackMap = new SparseArray<>();
            this.mFrameNumberTracker = new FrameNumberTracker();
            this.mCurrentSession.closeWithoutDraining();
            this.mCurrentSession = null;
        }
        this.mOfflineSwitchService.execute(new AnonymousClass9(hashSet));
        return cameraOfflineSessionImpl;
    }

    /* JADX INFO: renamed from: android.hardware.camera2.impl.CameraDeviceImpl$9, reason: invalid class name */
    class AnonymousClass9 implements Runnable {
        final /* synthetic */ HashSet val$offlineStreamIds;

        AnonymousClass9(HashSet hashSet) {
            this.val$offlineStreamIds = hashSet;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    ICameraDeviceUserWrapper iCameraDeviceUserWrapper = CameraDeviceImpl.this.mRemoteDevice;
                    CameraOfflineSessionImpl.CameraDeviceCallbacks callbacks = CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks();
                    HashSet hashSet = this.val$offlineStreamIds;
                    CameraDeviceImpl.this.mOfflineSessionImpl.setRemoteSession(iCameraDeviceUserWrapper.switchToOffline(callbacks, Arrays.stream((Integer[]) hashSet.toArray(new Integer[hashSet.size()])).mapToInt(new ToIntFunction() { // from class: android.hardware.camera2.impl.-$$Lambda$CameraDeviceImpl$9$uBsCIBAbAW1p84hPJ6ULJah0SPE
                        @Override // java.util.function.ToIntFunction
                        public final int applyAsInt(Object obj) {
                            return ((Integer) obj).intValue();
                        }
                    }).toArray()));
                } catch (CameraAccessException unused) {
                    CameraDeviceImpl.this.mOfflineSessionImpl.notifyFailedSwitch();
                }
            } finally {
                CameraDeviceImpl.this.mOfflineSessionImpl = null;
            }
        }
    }

    public boolean supportsOfflineProcessing(Surface surface) {
        int iKeyAt;
        boolean zContains;
        if (surface == null) {
            throw new IllegalArgumentException("Surface is null");
        }
        synchronized (this.mInterfaceLock) {
            int i = 0;
            while (true) {
                if (i >= this.mConfiguredOutputs.size()) {
                    iKeyAt = -1;
                    break;
                }
                if (surface == this.mConfiguredOutputs.valueAt(i).getSurface()) {
                    iKeyAt = this.mConfiguredOutputs.keyAt(i);
                    break;
                }
                i++;
            }
            if (iKeyAt == -1) {
                throw new IllegalArgumentException("Surface is not part of this session");
            }
            zContains = this.mOfflineSupport.contains(Integer.valueOf(iKeyAt));
        }
        return zContains;
    }

    public void tearDown(Surface surface) throws CameraAccessException {
        int iKeyAt;
        if (surface == null) {
            throw new IllegalArgumentException("Surface is null");
        }
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            int i = 0;
            while (true) {
                if (i >= this.mConfiguredOutputs.size()) {
                    iKeyAt = -1;
                    break;
                } else {
                    if (surface == this.mConfiguredOutputs.valueAt(i).getSurface()) {
                        iKeyAt = this.mConfiguredOutputs.keyAt(i);
                        break;
                    }
                    i++;
                }
            }
            if (iKeyAt == -1) {
                throw new IllegalArgumentException("Surface is not part of this session");
            }
            this.mRemoteDevice.tearDown(iKeyAt);
        }
    }

    public void finalizeOutputConfigs(List<OutputConfiguration> list) throws CameraAccessException {
        int iKeyAt;
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("deferred config is null or empty");
        }
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            for (OutputConfiguration outputConfiguration : list) {
                int i = 0;
                while (true) {
                    if (i >= this.mConfiguredOutputs.size()) {
                        iKeyAt = -1;
                        break;
                    } else {
                        if (outputConfiguration.equals(this.mConfiguredOutputs.valueAt(i))) {
                            iKeyAt = this.mConfiguredOutputs.keyAt(i);
                            break;
                        }
                        i++;
                    }
                }
                if (iKeyAt == -1) {
                    throw new IllegalArgumentException("Deferred config is not part of this session");
                }
                if (outputConfiguration.getSurfaces().size() == 0) {
                    throw new IllegalArgumentException("The final config for stream " + iKeyAt + " must have at least 1 surface");
                }
                this.mRemoteDevice.finalizeOutputConfigurations(iKeyAt, outputConfiguration);
                this.mConfiguredOutputs.put(iKeyAt, outputConfiguration);
            }
        }
    }

    public int capture(CaptureRequest captureRequest, CaptureCallback captureCallback, Executor executor) throws CameraAccessException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(captureRequest);
        return submitCaptureRequest(arrayList, captureCallback, executor, false);
    }

    public int captureBurst(List<CaptureRequest> list, CaptureCallback captureCallback, Executor executor) throws CameraAccessException {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("At least one request must be given");
        }
        return submitCaptureRequest(list, captureCallback, executor, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkEarlyTriggerSequenceCompleteLocked(final int i, long j, int[] iArr) {
        if (j == -1) {
            int iIndexOfKey = this.mCaptureCallbackMap.indexOfKey(i);
            final CaptureCallbackHolder captureCallbackHolderValueAt = iIndexOfKey >= 0 ? this.mCaptureCallbackMap.valueAt(iIndexOfKey) : null;
            if (captureCallbackHolderValueAt != null) {
                this.mCaptureCallbackMap.removeAt(iIndexOfKey);
            }
            if (captureCallbackHolderValueAt != null) {
                Runnable runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.10
                    @Override // java.lang.Runnable
                    public void run() {
                        if (CameraDeviceImpl.this.isClosed()) {
                            return;
                        }
                        captureCallbackHolderValueAt.getCallback().onCaptureSequenceAborted(CameraDeviceImpl.this, i);
                    }
                };
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    captureCallbackHolderValueAt.getExecutor().execute(runnable);
                    return;
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }
            Log.w(this.TAG, String.format("did not register callback to request %d", Integer.valueOf(i)));
            return;
        }
        this.mRequestLastFrameNumbersList.add(new RequestLastFrameNumbersHolder(i, j, iArr));
        checkAndFireSequenceComplete();
    }

    private int[] getRequestTypes(CaptureRequest[] captureRequestArr) {
        int[] iArr = new int[captureRequestArr.length];
        for (int i = 0; i < captureRequestArr.length; i++) {
            iArr[i] = captureRequestArr[i].getRequestType();
        }
        return iArr;
    }

    private int submitCaptureRequest(List<CaptureRequest> list, CaptureCallback captureCallback, Executor executor, boolean z) throws CameraAccessException {
        int requestId;
        Executor executorCheckExecutor = checkExecutor(executor, captureCallback);
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            for (CaptureRequest captureRequest : list) {
                if (captureRequest.getTargets().isEmpty()) {
                    throw new IllegalArgumentException("Each request must have at least one Surface target");
                }
                Iterator<Surface> it = captureRequest.getTargets().iterator();
                while (it.hasNext()) {
                    if (it.next() == null) {
                        throw new IllegalArgumentException("Null Surface targets are not allowed");
                    }
                }
            }
            if (z) {
                stopRepeating();
            }
            CaptureRequest[] captureRequestArr = (CaptureRequest[]) list.toArray(new CaptureRequest[list.size()]);
            for (CaptureRequest captureRequest2 : captureRequestArr) {
                captureRequest2.convertSurfaceToStreamId(this.mConfiguredOutputs);
            }
            SubmitInfo submitInfoSubmitRequestList = this.mRemoteDevice.submitRequestList(captureRequestArr, z);
            for (CaptureRequest captureRequest3 : captureRequestArr) {
                captureRequest3.recoverStreamIdToSurface();
            }
            if (captureCallback != null) {
                this.mCaptureCallbackMap.put(submitInfoSubmitRequestList.getRequestId(), new CaptureCallbackHolder(captureCallback, list, executorCheckExecutor, z, this.mNextSessionId - 1));
            }
            if (z) {
                int i = this.mRepeatingRequestId;
                if (i != -1) {
                    checkEarlyTriggerSequenceCompleteLocked(i, submitInfoSubmitRequestList.getLastFrameNumber(), this.mRepeatingRequestTypes);
                }
                this.mRepeatingRequestId = submitInfoSubmitRequestList.getRequestId();
                this.mRepeatingRequestTypes = getRequestTypes(captureRequestArr);
            } else {
                this.mRequestLastFrameNumbersList.add(new RequestLastFrameNumbersHolder(list, submitInfoSubmitRequestList));
            }
            if (this.mIdle) {
                this.mDeviceExecutor.execute(this.mCallOnActive);
            }
            this.mIdle = false;
            requestId = submitInfoSubmitRequestList.getRequestId();
        }
        return requestId;
    }

    public int setRepeatingRequest(CaptureRequest captureRequest, CaptureCallback captureCallback, Executor executor) throws CameraAccessException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(captureRequest);
        return submitCaptureRequest(arrayList, captureCallback, executor, true);
    }

    public int setRepeatingBurst(List<CaptureRequest> list, CaptureCallback captureCallback, Executor executor) throws CameraAccessException {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("At least one request must be given");
        }
        return submitCaptureRequest(list, captureCallback, executor, true);
    }

    public void stopRepeating() throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            int i = this.mRepeatingRequestId;
            if (i != -1) {
                this.mRepeatingRequestId = -1;
                int[] iArr = this.mRepeatingRequestTypes;
                this.mRepeatingRequestTypes = null;
                try {
                    checkEarlyTriggerSequenceCompleteLocked(i, this.mRemoteDevice.cancelRequest(i), iArr);
                } catch (IllegalArgumentException unused) {
                }
            }
        }
    }

    private void waitUntilIdle() throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            if (this.mRepeatingRequestId != -1) {
                throw new IllegalStateException("Active repeating request ongoing");
            }
            this.mRemoteDevice.waitUntilIdle();
        }
    }

    public void flush() throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            this.mDeviceExecutor.execute(this.mCallOnBusy);
            if (this.mIdle) {
                this.mDeviceExecutor.execute(this.mCallOnIdle);
                return;
            }
            long jFlush = this.mRemoteDevice.flush();
            int i = this.mRepeatingRequestId;
            if (i != -1) {
                checkEarlyTriggerSequenceCompleteLocked(i, jFlush, this.mRepeatingRequestTypes);
                this.mRepeatingRequestId = -1;
                this.mRepeatingRequestTypes = null;
            }
        }
    }

    @Override // android.hardware.camera2.CameraDevice, java.lang.AutoCloseable
    public void close() {
        synchronized (this.mInterfaceLock) {
            if (this.mClosing.getAndSet(true)) {
                return;
            }
            ExecutorService executorService = this.mOfflineSwitchService;
            if (executorService != null) {
                executorService.shutdownNow();
                this.mOfflineSwitchService = null;
            }
            ICameraDeviceUserWrapper iCameraDeviceUserWrapper = this.mRemoteDevice;
            if (iCameraDeviceUserWrapper != null) {
                iCameraDeviceUserWrapper.disconnect();
                this.mRemoteDevice.unlinkToDeath(this, 0);
            }
            if (this.mRemoteDevice != null || this.mInError) {
                this.mDeviceExecutor.execute(this.mCallOnClosed);
            }
            this.mRemoteDevice = null;
        }
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    private void checkInputConfiguration(InputConfiguration inputConfiguration) {
        if (inputConfiguration != null) {
            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.mCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            boolean z = false;
            for (int i : streamConfigurationMap.getInputFormats()) {
                if (i == inputConfiguration.getFormat()) {
                    z = true;
                }
            }
            if (!z) {
                throw new IllegalArgumentException("input format " + inputConfiguration.getFormat() + " is not valid");
            }
            boolean z2 = false;
            for (Size size : streamConfigurationMap.getInputSizes(inputConfiguration.getFormat())) {
                if (inputConfiguration.getWidth() == size.getWidth() && inputConfiguration.getHeight() == size.getHeight()) {
                    z2 = true;
                }
            }
            if (!z2) {
                throw new IllegalArgumentException("input size " + inputConfiguration.getWidth() + "x" + inputConfiguration.getHeight() + " is not valid");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndFireSequenceComplete() {
        long completedFrameNumber = this.mFrameNumberTracker.getCompletedFrameNumber();
        long completedReprocessFrameNumber = this.mFrameNumberTracker.getCompletedReprocessFrameNumber();
        long completedZslStillFrameNumber = this.mFrameNumberTracker.getCompletedZslStillFrameNumber();
        Iterator<RequestLastFrameNumbersHolder> it = this.mRequestLastFrameNumbersList.iterator();
        while (it.hasNext()) {
            final RequestLastFrameNumbersHolder next = it.next();
            final int requestId = next.getRequestId();
            if (this.mRemoteDevice == null) {
                Log.w(this.TAG, "Camera closed while checking sequences");
                return;
            }
            if (!next.isSequenceCompleted()) {
                long lastRegularFrameNumber = next.getLastRegularFrameNumber();
                long lastReprocessFrameNumber = next.getLastReprocessFrameNumber();
                long lastZslStillFrameNumber = next.getLastZslStillFrameNumber();
                if (lastRegularFrameNumber <= completedFrameNumber && lastReprocessFrameNumber <= completedReprocessFrameNumber && lastZslStillFrameNumber <= completedZslStillFrameNumber) {
                    next.markSequenceCompleted();
                }
                int iIndexOfKey = this.mCaptureCallbackMap.indexOfKey(requestId);
                final CaptureCallbackHolder captureCallbackHolderValueAt = iIndexOfKey >= 0 ? this.mCaptureCallbackMap.valueAt(iIndexOfKey) : null;
                if (captureCallbackHolderValueAt != null && next.isSequenceCompleted()) {
                    Runnable runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.11
                        @Override // java.lang.Runnable
                        public void run() {
                            if (CameraDeviceImpl.this.isClosed()) {
                                return;
                            }
                            captureCallbackHolderValueAt.getCallback().onCaptureSequenceCompleted(CameraDeviceImpl.this, requestId, next.getLastFrameNumber());
                        }
                    };
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        captureCallbackHolderValueAt.getExecutor().execute(runnable);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            }
            if (next.isSequenceCompleted() && next.isInflightCompleted()) {
                int iIndexOfKey2 = this.mCaptureCallbackMap.indexOfKey(requestId);
                if (iIndexOfKey2 >= 0) {
                    this.mCaptureCallbackMap.removeAt(iIndexOfKey2);
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeCompletedCallbackHolderLocked(long j, long j2, long j3) {
        Iterator<RequestLastFrameNumbersHolder> it = this.mRequestLastFrameNumbersList.iterator();
        while (it.hasNext()) {
            RequestLastFrameNumbersHolder next = it.next();
            int requestId = next.getRequestId();
            if (this.mRemoteDevice == null) {
                Log.w(this.TAG, "Camera closed while removing completed callback holders");
                return;
            }
            long lastRegularFrameNumber = next.getLastRegularFrameNumber();
            long lastReprocessFrameNumber = next.getLastReprocessFrameNumber();
            long lastZslStillFrameNumber = next.getLastZslStillFrameNumber();
            if (lastRegularFrameNumber <= j && lastReprocessFrameNumber <= j2 && lastZslStillFrameNumber <= j3) {
                if (next.isSequenceCompleted()) {
                    int iIndexOfKey = this.mCaptureCallbackMap.indexOfKey(requestId);
                    if (iIndexOfKey >= 0) {
                        this.mCaptureCallbackMap.removeAt(iIndexOfKey);
                    }
                    it.remove();
                } else {
                    next.markInflightCompleted();
                }
            }
        }
    }

    public void onDeviceError(int i, CaptureResultExtras captureResultExtras) {
        synchronized (this.mInterfaceLock) {
            if (this.mRemoteDevice == null) {
                return;
            }
            CameraOfflineSessionImpl cameraOfflineSessionImpl = this.mOfflineSessionImpl;
            if (cameraOfflineSessionImpl != null) {
                cameraOfflineSessionImpl.getCallbacks().onDeviceError(i, captureResultExtras);
                return;
            }
            if (i == 0) {
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mDeviceExecutor.execute(this.mCallOnDisconnected);
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            } else if (i == 1) {
                scheduleNotifyError(4);
            } else if (i == 3 || i == 4 || i == 5) {
                onCaptureErrorLocked(i, captureResultExtras);
            } else if (i == 6) {
                scheduleNotifyError(3);
            } else {
                Log.e(this.TAG, "Unknown error from camera device: " + i);
                scheduleNotifyError(5);
            }
        }
    }

    private void scheduleNotifyError(int i) {
        this.mInError = true;
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            this.mDeviceExecutor.execute(PooledLambda.obtainRunnable(new BiConsumer() { // from class: android.hardware.camera2.impl.-$$Lambda$CameraDeviceImpl$KBQCqQRdhVVn7uHI9Xdha6OqnsU
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((CameraDeviceImpl) obj).notifyError(((Integer) obj2).intValue());
                }
            }, this, Integer.valueOf(i)).recycleOnUse());
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyError(int i) {
        if (isClosed()) {
            return;
        }
        this.mDeviceCallback.onError(this, i);
    }

    private void onCaptureErrorLocked(int i, CaptureResultExtras captureResultExtras) {
        long jClearCallingIdentity;
        int requestId = captureResultExtras.getRequestId();
        int subsequenceId = captureResultExtras.getSubsequenceId();
        final long frameNumber = captureResultExtras.getFrameNumber();
        String errorPhysicalCameraId = captureResultExtras.getErrorPhysicalCameraId();
        final CaptureCallbackHolder captureCallbackHolder = this.mCaptureCallbackMap.get(requestId);
        int i2 = 0;
        if (captureCallbackHolder == null) {
            Log.e(this.TAG, String.format("Receive capture error on unknown request ID %d", Integer.valueOf(requestId)));
            return;
        }
        final CaptureRequest request = captureCallbackHolder.getRequest(subsequenceId);
        if (i == 5) {
            OutputConfiguration outputConfiguration = this.mConfiguredOutputs.get(captureResultExtras.getErrorStreamId());
            if (outputConfiguration == null) {
                Log.v(this.TAG, String.format("Stream %d has been removed. Skipping buffer lost callback", Integer.valueOf(captureResultExtras.getErrorStreamId())));
                return;
            }
            for (final Surface surface : outputConfiguration.getSurfaces()) {
                if (request.containsTarget(surface)) {
                    Runnable runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.12
                        @Override // java.lang.Runnable
                        public void run() {
                            if (CameraDeviceImpl.this.isClosed()) {
                                return;
                            }
                            captureCallbackHolder.getCallback().onCaptureBufferLost(CameraDeviceImpl.this, request, surface, frameNumber);
                        }
                    };
                    jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        captureCallbackHolder.getExecutor().execute(runnable);
                    } finally {
                    }
                }
            }
            return;
        }
        boolean z = i == 4;
        CameraCaptureSessionCore cameraCaptureSessionCore = this.mCurrentSession;
        if (cameraCaptureSessionCore != null && cameraCaptureSessionCore.isAborting()) {
            i2 = 1;
        }
        final CaptureFailure captureFailure = new CaptureFailure(request, i2, z, requestId, frameNumber, errorPhysicalCameraId);
        Runnable runnable2 = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.13
            @Override // java.lang.Runnable
            public void run() {
                if (CameraDeviceImpl.this.isClosed()) {
                    return;
                }
                captureCallbackHolder.getCallback().onCaptureFailed(CameraDeviceImpl.this, request, captureFailure);
            }
        };
        this.mFrameNumberTracker.updateTracker(frameNumber, true, request.getRequestType());
        checkAndFireSequenceComplete();
        jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            captureCallbackHolder.getExecutor().execute(runnable2);
        } finally {
        }
    }

    public void onDeviceIdle() {
        synchronized (this.mInterfaceLock) {
            if (this.mRemoteDevice == null) {
                return;
            }
            CameraOfflineSessionImpl cameraOfflineSessionImpl = this.mOfflineSessionImpl;
            if (cameraOfflineSessionImpl != null) {
                cameraOfflineSessionImpl.getCallbacks().onDeviceIdle();
                return;
            }
            removeCompletedCallbackHolderLocked(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE);
            if (!this.mIdle) {
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mDeviceExecutor.execute(this.mCallOnIdle);
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                } catch (Throwable th) {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    throw th;
                }
            }
            this.mIdle = true;
        }
    }

    public class CameraDeviceCallbacks extends ICameraDeviceCallbacks.Stub {
        @Override // android.hardware.camera2.ICameraDeviceCallbacks.Stub, android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public CameraDeviceCallbacks() {
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onDeviceError(int i, CaptureResultExtras captureResultExtras) {
            CameraDeviceImpl.this.onDeviceError(i, captureResultExtras);
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onRepeatingRequestError(long j, int i) {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice != null && CameraDeviceImpl.this.mRepeatingRequestId != -1) {
                    if (CameraDeviceImpl.this.mOfflineSessionImpl != null) {
                        CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks().onRepeatingRequestError(j, i);
                        return;
                    }
                    CameraDeviceImpl cameraDeviceImpl = CameraDeviceImpl.this;
                    cameraDeviceImpl.checkEarlyTriggerSequenceCompleteLocked(cameraDeviceImpl.mRepeatingRequestId, j, CameraDeviceImpl.this.mRepeatingRequestTypes);
                    if (CameraDeviceImpl.this.mRepeatingRequestId == i) {
                        CameraDeviceImpl.this.mRepeatingRequestId = -1;
                        CameraDeviceImpl.this.mRepeatingRequestTypes = null;
                    }
                }
            }
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onDeviceIdle() {
            CameraDeviceImpl.this.onDeviceIdle();
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onCaptureStarted(final CaptureResultExtras captureResultExtras, final long j) throws Throwable {
            int requestId = captureResultExtras.getRequestId();
            final long frameNumber = captureResultExtras.getFrameNumber();
            long lastCompletedRegularFrameNumber = captureResultExtras.getLastCompletedRegularFrameNumber();
            long lastCompletedReprocessFrameNumber = captureResultExtras.getLastCompletedReprocessFrameNumber();
            long lastCompletedZslFrameNumber = captureResultExtras.getLastCompletedZslFrameNumber();
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                try {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (CameraDeviceImpl.this.mRemoteDevice == null) {
                        return;
                    }
                    if (CameraDeviceImpl.this.mOfflineSessionImpl != null) {
                        CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks().onCaptureStarted(captureResultExtras, j);
                        return;
                    }
                    CameraDeviceImpl.this.removeCompletedCallbackHolderLocked(lastCompletedRegularFrameNumber, lastCompletedReprocessFrameNumber, lastCompletedZslFrameNumber);
                    final CaptureCallbackHolder captureCallbackHolder = (CaptureCallbackHolder) CameraDeviceImpl.this.mCaptureCallbackMap.get(requestId);
                    if (captureCallbackHolder == null) {
                        return;
                    }
                    if (CameraDeviceImpl.this.isClosed()) {
                        return;
                    }
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        try {
                            captureCallbackHolder.getExecutor().execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.CameraDeviceCallbacks.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (CameraDeviceImpl.this.isClosed()) {
                                        return;
                                    }
                                    int subsequenceId = captureResultExtras.getSubsequenceId();
                                    CaptureRequest request = captureCallbackHolder.getRequest(subsequenceId);
                                    if (captureCallbackHolder.hasBatchedOutputs()) {
                                        Range range = (Range) request.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
                                        for (int i = 0; i < captureCallbackHolder.getRequestCount(); i++) {
                                            long j2 = subsequenceId - i;
                                            captureCallbackHolder.getCallback().onCaptureStarted(CameraDeviceImpl.this, captureCallbackHolder.getRequest(i), j - ((1000000000 * j2) / ((long) ((Integer) range.getUpper()).intValue())), frameNumber - j2);
                                        }
                                        return;
                                    }
                                    captureCallbackHolder.getCallback().onCaptureStarted(CameraDeviceImpl.this, captureCallbackHolder.getRequest(captureResultExtras.getSubsequenceId()), j, frameNumber);
                                }
                            });
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        } catch (Throwable th2) {
                            th = th2;
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    throw th;
                }
            }
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onResultReceived(CameraMetadataNative cameraMetadataNative, final CaptureResultExtras captureResultExtras, PhysicalCaptureResultInfo[] physicalCaptureResultInfoArr) throws RemoteException {
            CaptureCallbackHolder captureCallbackHolder;
            CaptureResult captureResult;
            Runnable runnable;
            int requestId = captureResultExtras.getRequestId();
            long frameNumber = captureResultExtras.getFrameNumber();
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice == null) {
                    return;
                }
                if (CameraDeviceImpl.this.mOfflineSessionImpl != null) {
                    CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks().onResultReceived(cameraMetadataNative, captureResultExtras, physicalCaptureResultInfoArr);
                    return;
                }
                cameraMetadataNative.set(CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE, (Size) CameraDeviceImpl.this.getCharacteristics().get(CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE));
                final CaptureCallbackHolder captureCallbackHolder2 = (CaptureCallbackHolder) CameraDeviceImpl.this.mCaptureCallbackMap.get(requestId);
                final CaptureRequest request = captureCallbackHolder2.getRequest(captureResultExtras.getSubsequenceId());
                boolean z = captureResultExtras.getPartialResultCount() < CameraDeviceImpl.this.mTotalPartialCount;
                int requestType = request.getRequestType();
                if (captureCallbackHolder2 == null) {
                    CameraDeviceImpl.this.mFrameNumberTracker.updateTracker(frameNumber, null, z, requestType);
                    return;
                }
                if (CameraDeviceImpl.this.isClosed()) {
                    CameraDeviceImpl.this.mFrameNumberTracker.updateTracker(frameNumber, null, z, requestType);
                    return;
                }
                final CameraMetadataNative cameraMetadataNative2 = captureCallbackHolder2.hasBatchedOutputs() ? new CameraMetadataNative(cameraMetadataNative) : null;
                if (!z) {
                    final List<CaptureResult> listPopPartialResults = CameraDeviceImpl.this.mFrameNumberTracker.popPartialResults(frameNumber);
                    final long jLongValue = ((Long) cameraMetadataNative.get(CaptureResult.SENSOR_TIMESTAMP)).longValue();
                    final Range range = (Range) request.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
                    final int subsequenceId = captureResultExtras.getSubsequenceId();
                    final TotalCaptureResult totalCaptureResult = new TotalCaptureResult(cameraMetadataNative, request, captureResultExtras, listPopPartialResults, captureCallbackHolder2.getSessionId(), physicalCaptureResultInfoArr);
                    captureCallbackHolder = captureCallbackHolder2;
                    captureResult = totalCaptureResult;
                    runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.CameraDeviceCallbacks.3
                        @Override // java.lang.Runnable
                        public void run() {
                            if (CameraDeviceImpl.this.isClosed()) {
                                return;
                            }
                            if (captureCallbackHolder2.hasBatchedOutputs()) {
                                for (int i = 0; i < captureCallbackHolder2.getRequestCount(); i++) {
                                    cameraMetadataNative2.set(CaptureResult.SENSOR_TIMESTAMP, Long.valueOf(jLongValue - ((((long) (subsequenceId - i)) * 1000000000) / ((long) ((Integer) range.getUpper()).intValue()))));
                                    captureCallbackHolder2.getCallback().onCaptureCompleted(CameraDeviceImpl.this, captureCallbackHolder2.getRequest(i), new TotalCaptureResult(new CameraMetadataNative(cameraMetadataNative2), captureCallbackHolder2.getRequest(i), captureResultExtras, listPopPartialResults, captureCallbackHolder2.getSessionId(), new PhysicalCaptureResultInfo[0]));
                                }
                                return;
                            }
                            captureCallbackHolder2.getCallback().onCaptureCompleted(CameraDeviceImpl.this, request, totalCaptureResult);
                        }
                    };
                } else {
                    final CaptureResult captureResult2 = new CaptureResult(cameraMetadataNative, request, captureResultExtras);
                    captureResult = captureResult2;
                    runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.CameraDeviceCallbacks.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (CameraDeviceImpl.this.isClosed()) {
                                return;
                            }
                            if (captureCallbackHolder2.hasBatchedOutputs()) {
                                for (int i = 0; i < captureCallbackHolder2.getRequestCount(); i++) {
                                    captureCallbackHolder2.getCallback().onCaptureProgressed(CameraDeviceImpl.this, captureCallbackHolder2.getRequest(i), new CaptureResult(new CameraMetadataNative(cameraMetadataNative2), captureCallbackHolder2.getRequest(i), captureResultExtras));
                                }
                                return;
                            }
                            captureCallbackHolder2.getCallback().onCaptureProgressed(CameraDeviceImpl.this, request, captureResult2);
                        }
                    };
                    captureCallbackHolder = captureCallbackHolder2;
                }
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    captureCallbackHolder.getExecutor().execute(runnable);
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    CameraDeviceImpl.this.mFrameNumberTracker.updateTracker(frameNumber, captureResult, z, requestType);
                    if (!z) {
                        CameraDeviceImpl.this.checkAndFireSequenceComplete();
                    }
                } catch (Throwable th) {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    throw th;
                }
            }
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onPrepared(int i) {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mOfflineSessionImpl != null) {
                    CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks().onPrepared(i);
                    return;
                }
                OutputConfiguration outputConfiguration = (OutputConfiguration) CameraDeviceImpl.this.mConfiguredOutputs.get(i);
                StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
                if (stateCallbackKK == null) {
                    return;
                }
                if (outputConfiguration == null) {
                    Log.w(CameraDeviceImpl.this.TAG, "onPrepared invoked for unknown output Surface");
                    return;
                }
                Iterator<Surface> it = outputConfiguration.getSurfaces().iterator();
                while (it.hasNext()) {
                    stateCallbackKK.onSurfacePrepared(it.next());
                }
            }
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onRequestQueueEmpty() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mOfflineSessionImpl != null) {
                    CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks().onRequestQueueEmpty();
                    return;
                }
                StateCallbackKK stateCallbackKK = CameraDeviceImpl.this.mSessionStateCallback;
                if (stateCallbackKK == null) {
                    return;
                }
                stateCallbackKK.onRequestQueueEmpty();
            }
        }
    }

    private static class CameraHandlerExecutor implements Executor {
        private final Handler mHandler;

        public CameraHandlerExecutor(Handler handler) {
            this.mHandler = (Handler) Objects.requireNonNull(handler);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    static Executor checkExecutor(Executor executor) {
        return executor == null ? checkAndWrapHandler(null) : executor;
    }

    public static <T> Executor checkExecutor(Executor executor, T t) {
        return t != null ? checkExecutor(executor) : executor;
    }

    public static Executor checkAndWrapHandler(Handler handler) {
        return new CameraHandlerExecutor(checkHandler(handler));
    }

    static Handler checkHandler(Handler handler) {
        if (handler != null) {
            return handler;
        }
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper == null) {
            throw new IllegalArgumentException("No handler given, and current thread has no looper!");
        }
        return new Handler(looperMyLooper);
    }

    static <T> Handler checkHandler(Handler handler, T t) {
        return t != null ? checkHandler(handler) : handler;
    }

    private void checkIfCameraClosedOrInError() throws CameraAccessException {
        if (this.mRemoteDevice == null) {
            throw new IllegalStateException("CameraDevice was already closed");
        }
        if (this.mInError) {
            throw new CameraAccessException(3, "The camera device has encountered a serious error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isClosed() {
        return this.mClosing.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CameraCharacteristics getCharacteristics() {
        return this.mCharacteristics;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        Log.w(this.TAG, "CameraDevice " + this.mCameraId + " died unexpectedly");
        if (this.mRemoteDevice == null) {
            return;
        }
        this.mInError = true;
        Runnable runnable = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.14
            @Override // java.lang.Runnable
            public void run() {
                if (CameraDeviceImpl.this.isClosed()) {
                    return;
                }
                CameraDeviceImpl.this.mDeviceCallback.onError(CameraDeviceImpl.this, 5);
            }
        };
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            this.mDeviceExecutor.execute(runnable);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // android.hardware.camera2.CameraDevice
    public void setCameraAudioRestriction(int i) throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            this.mRemoteDevice.setCameraAudioRestriction(i);
        }
    }

    @Override // android.hardware.camera2.CameraDevice
    public int getCameraAudioRestriction() throws CameraAccessException {
        int globalAudioRestriction;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            globalAudioRestriction = this.mRemoteDevice.getGlobalAudioRestriction();
        }
        return globalAudioRestriction;
    }
}
