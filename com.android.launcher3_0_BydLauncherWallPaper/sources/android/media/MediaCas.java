package android.media;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.cas.V1_0.HidlCasPluginDescriptor;
import android.hardware.cas.V1_0.ICas;
import android.hardware.cas.V1_0.IMediaCasService;
import android.hardware.cas.V1_2.ICas;
import android.hardware.cas.V1_2.ICasListener;
import android.media.MediaCasException;
import android.media.tv.tunerresourcemanager.CasSessionRequest;
import android.media.tv.tunerresourcemanager.ResourceClientProfile;
import android.media.tv.tunerresourcemanager.TunerResourceManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.Singleton;
import com.android.internal.util.FrameworkStatsLog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaCas implements AutoCloseable {
    public static final int PLUGIN_STATUS_PHYSICAL_MODULE_CHANGED = 0;
    public static final int PLUGIN_STATUS_SESSION_NUMBER_CHANGED = 1;
    public static final int SCRAMBLING_MODE_AES128 = 9;
    public static final int SCRAMBLING_MODE_AES_ECB = 10;
    public static final int SCRAMBLING_MODE_AES_SCTE52 = 11;
    public static final int SCRAMBLING_MODE_DVB_CISSA_V1 = 6;
    public static final int SCRAMBLING_MODE_DVB_CSA1 = 1;
    public static final int SCRAMBLING_MODE_DVB_CSA2 = 2;
    public static final int SCRAMBLING_MODE_DVB_CSA3_ENHANCE = 5;
    public static final int SCRAMBLING_MODE_DVB_CSA3_MINIMAL = 4;
    public static final int SCRAMBLING_MODE_DVB_CSA3_STANDARD = 3;
    public static final int SCRAMBLING_MODE_DVB_IDSA = 7;
    public static final int SCRAMBLING_MODE_MULTI2 = 8;
    public static final int SCRAMBLING_MODE_RESERVED = 0;
    public static final int SCRAMBLING_MODE_TDES_ECB = 12;
    public static final int SCRAMBLING_MODE_TDES_SCTE52 = 13;
    public static final int SESSION_USAGE_LIVE = 0;
    public static final int SESSION_USAGE_PLAYBACK = 1;
    public static final int SESSION_USAGE_RECORD = 2;
    public static final int SESSION_USAGE_TIMESHIFT = 3;
    private static final String TAG = "MediaCas";
    private static final Singleton<IMediaCasService> sService = new Singleton<IMediaCasService>() { // from class: android.media.MediaCas.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.util.Singleton
        public IMediaCasService create() {
            try {
                Log.d(MediaCas.TAG, "Trying to get cas@1.2 service");
                android.hardware.cas.V1_2.IMediaCasService service = android.hardware.cas.V1_2.IMediaCasService.getService(true);
                if (service != null) {
                    return service;
                }
            } catch (Exception unused) {
                Log.d(MediaCas.TAG, "Failed to get cas@1.2 service");
            }
            try {
                Log.d(MediaCas.TAG, "Trying to get cas@1.1 service");
                android.hardware.cas.V1_1.IMediaCasService service2 = android.hardware.cas.V1_1.IMediaCasService.getService(true);
                if (service2 != null) {
                    return service2;
                }
            } catch (Exception unused2) {
                Log.d(MediaCas.TAG, "Failed to get cas@1.1 service");
            }
            try {
                Log.d(MediaCas.TAG, "Trying to get cas@1.0 service");
                return IMediaCasService.getService(true);
            } catch (Exception unused3) {
                Log.d(MediaCas.TAG, "Failed to get cas@1.0 service");
                return null;
            }
        }
    };
    private final ICasListener.Stub mBinder;
    private int mCasSystemId;
    private int mClientId;
    private EventHandler mEventHandler;
    private HandlerThread mHandlerThread;
    private ICas mICas;
    private android.hardware.cas.V1_1.ICas mICasV11;
    private android.hardware.cas.V1_2.ICas mICasV12;
    private EventListener mListener;
    private int mPriorityHint;
    private final TunerResourceManager.ResourcesReclaimListener mResourceListener;
    private final Map<Session, Integer> mSessionMap;
    private TunerResourceManager mTunerResourceManager;
    private String mTvInputServiceSessionId;
    private int mUserId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PluginStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScramblingMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SessionUsage {
    }

    static IMediaCasService getService() {
        return sService.get();
    }

    private void validateInternalStates() {
        if (this.mICas == null) {
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanupAndRethrowIllegalState() {
        this.mICas = null;
        this.mICasV11 = null;
        this.mICasV12 = null;
        throw new IllegalStateException();
    }

    private class EventHandler extends Handler {
        private static final String DATA_KEY = "data";
        private static final int MSG_CAS_EVENT = 0;
        private static final int MSG_CAS_RESOURCE_LOST = 3;
        private static final int MSG_CAS_SESSION_EVENT = 1;
        private static final int MSG_CAS_STATUS_EVENT = 2;
        private static final String SESSION_KEY = "sessionId";

        public EventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0) {
                MediaCas.this.mListener.onEvent(MediaCas.this, message.arg1, message.arg2, MediaCas.this.toBytes((ArrayList) message.obj));
                return;
            }
            if (message.what == 1) {
                Bundle data = message.getData();
                ArrayList<Byte> byteArray = MediaCas.this.toByteArray(data.getByteArray("sessionId"));
                EventListener eventListener = MediaCas.this.mListener;
                MediaCas mediaCas = MediaCas.this;
                eventListener.onSessionEvent(mediaCas, mediaCas.createFromSessionId(byteArray), message.arg1, message.arg2, data.getByteArray("data"));
                return;
            }
            if (message.what == 2) {
                if (message.arg1 == 1 && MediaCas.this.mTunerResourceManager != null) {
                    MediaCas.this.mTunerResourceManager.updateCasInfo(MediaCas.this.mCasSystemId, message.arg2);
                }
                MediaCas.this.mListener.onPluginStatusUpdate(MediaCas.this, message.arg1, message.arg2);
                return;
            }
            if (message.what == 3) {
                MediaCas.this.mListener.onResourceLost(MediaCas.this);
            }
        }
    }

    public static class PluginDescriptor {
        private final int mCASystemId;
        private final String mName;

        private PluginDescriptor() {
            this.mCASystemId = 65535;
            this.mName = null;
        }

        PluginDescriptor(HidlCasPluginDescriptor hidlCasPluginDescriptor) {
            this.mCASystemId = hidlCasPluginDescriptor.caSystemId;
            this.mName = hidlCasPluginDescriptor.name;
        }

        public int getSystemId() {
            return this.mCASystemId;
        }

        public String getName() {
            return this.mName;
        }

        public String toString() {
            return "PluginDescriptor {" + this.mCASystemId + ", " + this.mName + "}";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<Byte> toByteArray(byte[] bArr, int i, int i2) {
        ArrayList<Byte> arrayList = new ArrayList<>(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.add(Byte.valueOf(bArr[i + i3]));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<Byte> toByteArray(byte[] bArr) {
        if (bArr == null) {
            return new ArrayList<>();
        }
        return toByteArray(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] toBytes(ArrayList<Byte> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = arrayList.get(i).byteValue();
        }
        return bArr;
    }

    public final class Session implements AutoCloseable {
        boolean mIsClosed = false;
        final ArrayList<Byte> mSessionId;

        Session(ArrayList<Byte> arrayList) {
            this.mSessionId = new ArrayList<>(arrayList);
        }

        private void validateSessionInternalStates() {
            if (MediaCas.this.mICas == null) {
                throw new IllegalStateException();
            }
            if (this.mIsClosed) {
                MediaCasStateException.throwExceptionIfNeeded(3);
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof Session) {
                return this.mSessionId.equals(((Session) obj).mSessionId);
            }
            return false;
        }

        public void setPrivateData(byte[] bArr) throws MediaCasException {
            validateSessionInternalStates();
            try {
                MediaCasException.throwExceptionIfNeeded(MediaCas.this.mICas.setSessionPrivateData(this.mSessionId, MediaCas.this.toByteArray(bArr, 0, bArr.length)));
            } catch (RemoteException unused) {
                MediaCas.this.cleanupAndRethrowIllegalState();
            }
        }

        public void processEcm(byte[] bArr, int i, int i2) throws MediaCasException {
            validateSessionInternalStates();
            try {
                MediaCasException.throwExceptionIfNeeded(MediaCas.this.mICas.processEcm(this.mSessionId, MediaCas.this.toByteArray(bArr, i, i2)));
            } catch (RemoteException unused) {
                MediaCas.this.cleanupAndRethrowIllegalState();
            }
        }

        public void processEcm(byte[] bArr) throws MediaCasException {
            processEcm(bArr, 0, bArr.length);
        }

        public void sendSessionEvent(int i, int i2, byte[] bArr) throws MediaCasException {
            validateSessionInternalStates();
            if (MediaCas.this.mICasV11 == null) {
                Log.d(MediaCas.TAG, "Send Session Event isn't supported by cas@1.0 interface");
                throw new MediaCasException.UnsupportedCasException("Send Session Event is not supported");
            }
            try {
                MediaCasException.throwExceptionIfNeeded(MediaCas.this.mICasV11.sendSessionEvent(this.mSessionId, i, i2, MediaCas.this.toByteArray(bArr)));
            } catch (RemoteException unused) {
                MediaCas.this.cleanupAndRethrowIllegalState();
            }
        }

        public byte[] getSessionId() {
            validateSessionInternalStates();
            return MediaCas.this.toBytes(this.mSessionId);
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            validateSessionInternalStates();
            try {
                MediaCasStateException.throwExceptionIfNeeded(MediaCas.this.mICas.closeSession(this.mSessionId));
                this.mIsClosed = true;
                MediaCas.this.removeSessionFromResourceMap(this);
            } catch (RemoteException unused) {
                MediaCas.this.cleanupAndRethrowIllegalState();
            }
        }
    }

    Session createFromSessionId(ArrayList<Byte> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        return new Session(arrayList);
    }

    public static boolean isSystemIdSupported(int i) {
        IMediaCasService service = getService();
        if (service == null) {
            return false;
        }
        try {
            return service.isSystemIdSupported(i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public static PluginDescriptor[] enumeratePlugins() {
        IMediaCasService service = getService();
        if (service != null) {
            try {
                ArrayList<HidlCasPluginDescriptor> arrayListEnumeratePlugins = service.enumeratePlugins();
                if (arrayListEnumeratePlugins.size() == 0) {
                    return null;
                }
                int size = arrayListEnumeratePlugins.size();
                PluginDescriptor[] pluginDescriptorArr = new PluginDescriptor[size];
                for (int i = 0; i < size; i++) {
                    pluginDescriptorArr[i] = new PluginDescriptor(arrayListEnumeratePlugins.get(i));
                }
                return pluginDescriptorArr;
            } catch (RemoteException unused) {
            }
        }
        return null;
    }

    public MediaCas(int i) throws MediaCasException.UnsupportedCasException {
        this.mTunerResourceManager = null;
        this.mSessionMap = new HashMap();
        ICasListener.Stub stub = new ICasListener.Stub() { // from class: android.media.MediaCas.2
            @Override // android.hardware.cas.V1_0.ICasListener
            public void onEvent(int i2, int i3, ArrayList<Byte> arrayList) throws RemoteException {
                MediaCas.this.mEventHandler.sendMessage(MediaCas.this.mEventHandler.obtainMessage(0, i2, i3, arrayList));
            }

            @Override // android.hardware.cas.V1_1.ICasListener
            public void onSessionEvent(ArrayList<Byte> arrayList, int i2, int i3, ArrayList<Byte> arrayList2) throws RemoteException {
                Message messageObtainMessage = MediaCas.this.mEventHandler.obtainMessage();
                messageObtainMessage.what = 1;
                messageObtainMessage.arg1 = i2;
                messageObtainMessage.arg2 = i3;
                Bundle bundle = new Bundle();
                bundle.putByteArray(TextToSpeech.Engine.KEY_PARAM_SESSION_ID, MediaCas.this.toBytes(arrayList));
                bundle.putByteArray("data", MediaCas.this.toBytes(arrayList2));
                messageObtainMessage.setData(bundle);
                MediaCas.this.mEventHandler.sendMessage(messageObtainMessage);
            }

            @Override // android.hardware.cas.V1_2.ICasListener
            public void onStatusUpdate(byte b, int i2) throws RemoteException {
                MediaCas.this.mEventHandler.sendMessage(MediaCas.this.mEventHandler.obtainMessage(2, b, i2));
            }
        };
        this.mBinder = stub;
        this.mResourceListener = new TunerResourceManager.ResourcesReclaimListener() { // from class: android.media.MediaCas.3
            @Override // android.media.tv.tunerresourcemanager.TunerResourceManager.ResourcesReclaimListener
            public void onReclaimResources() {
                synchronized (MediaCas.this.mSessionMap) {
                    Iterator it = new ArrayList(MediaCas.this.mSessionMap.keySet()).iterator();
                    while (it.hasNext()) {
                        ((Session) it.next()).close();
                    }
                }
                MediaCas.this.mEventHandler.sendMessage(MediaCas.this.mEventHandler.obtainMessage(3));
            }
        };
        try {
            try {
                this.mCasSystemId = i;
                this.mUserId = ActivityManager.getCurrentUser();
                IMediaCasService service = getService();
                android.hardware.cas.V1_2.IMediaCasService iMediaCasServiceCastFrom = android.hardware.cas.V1_2.IMediaCasService.castFrom((IHwInterface) service);
                if (iMediaCasServiceCastFrom == null) {
                    android.hardware.cas.V1_1.IMediaCasService iMediaCasServiceCastFrom2 = android.hardware.cas.V1_1.IMediaCasService.castFrom((IHwInterface) service);
                    if (iMediaCasServiceCastFrom2 == null) {
                        Log.d(TAG, "Used cas@1_0 interface to create plugin");
                        this.mICas = service.createPlugin(i, stub);
                    } else {
                        Log.d(TAG, "Used cas@1.1 interface to create plugin");
                        android.hardware.cas.V1_1.ICas iCasCreatePluginExt = iMediaCasServiceCastFrom2.createPluginExt(i, stub);
                        this.mICasV11 = iCasCreatePluginExt;
                        this.mICas = iCasCreatePluginExt;
                    }
                } else {
                    Log.d(TAG, "Used cas@1.2 interface to create plugin");
                    android.hardware.cas.V1_2.ICas iCasCastFrom = android.hardware.cas.V1_2.ICas.castFrom((IHwInterface) iMediaCasServiceCastFrom.createPluginExt(i, stub));
                    this.mICasV12 = iCasCastFrom;
                    this.mICasV11 = iCasCastFrom;
                    this.mICas = iCasCastFrom;
                }
                if (this.mICas == null) {
                    throw new MediaCasException.UnsupportedCasException("Unsupported CA_system_id " + i);
                }
            } catch (Exception e) {
                Log.e(TAG, "Failed to create plugin: " + e);
                this.mICas = null;
                throw new MediaCasException.UnsupportedCasException("Unsupported CA_system_id " + i);
            }
        } catch (Throwable th) {
            if (this.mICas == null) {
                throw new MediaCasException.UnsupportedCasException("Unsupported CA_system_id " + i);
            }
            throw th;
        }
    }

    public MediaCas(Context context, int i, String str, int i2) throws MediaCasException.UnsupportedCasException {
        this(i);
        Objects.requireNonNull(context, "context must not be null");
        TunerResourceManager tunerResourceManager = (TunerResourceManager) context.getSystemService(Context.TV_TUNER_RESOURCE_MGR_SERVICE);
        this.mTunerResourceManager = tunerResourceManager;
        if (tunerResourceManager != null) {
            int[] iArr = new int[1];
            this.mTunerResourceManager.registerClientProfile(new ResourceClientProfile(str, i2), context.getMainExecutor(), this.mResourceListener, iArr);
            this.mClientId = iArr[0];
        }
    }

    IHwBinder getBinder() {
        validateInternalStates();
        return this.mICas.asBinder();
    }

    public interface EventListener {
        void onEvent(MediaCas mediaCas, int i, int i2, byte[] bArr);

        default void onSessionEvent(MediaCas mediaCas, Session session, int i, int i2, byte[] bArr) {
            Log.d(MediaCas.TAG, "Received MediaCas Session event");
        }

        default void onPluginStatusUpdate(MediaCas mediaCas, int i, int i2) {
            Log.d(MediaCas.TAG, "Received MediaCas Plugin Status event");
        }

        default void onResourceLost(MediaCas mediaCas) {
            Log.d(MediaCas.TAG, "Received MediaCas Resource Reclaim event");
        }
    }

    public void setEventListener(EventListener eventListener, Handler handler) {
        this.mListener = eventListener;
        if (eventListener == null) {
            this.mEventHandler = null;
            return;
        }
        Looper looper = handler != null ? handler.getLooper() : null;
        if (looper == null && (looper = Looper.myLooper()) == null && (looper = Looper.getMainLooper()) == null) {
            HandlerThread handlerThread = this.mHandlerThread;
            if (handlerThread == null || !handlerThread.isAlive()) {
                HandlerThread handlerThread2 = new HandlerThread("MediaCasEventThread", -2);
                this.mHandlerThread = handlerThread2;
                handlerThread2.start();
            }
            looper = this.mHandlerThread.getLooper();
        }
        this.mEventHandler = new EventHandler(looper);
    }

    public void setPrivateData(byte[] bArr) throws MediaCasException {
        validateInternalStates();
        try {
            MediaCasException.throwExceptionIfNeeded(this.mICas.setPrivateData(toByteArray(bArr, 0, bArr.length)));
        } catch (RemoteException unused) {
            cleanupAndRethrowIllegalState();
        }
    }

    private class OpenSessionCallback implements ICas.openSessionCallback {
        public Session mSession;
        public int mStatus;

        private OpenSessionCallback() {
        }

        @Override // android.hardware.cas.V1_0.ICas.openSessionCallback
        public void onValues(int i, ArrayList<Byte> arrayList) {
            this.mStatus = i;
            this.mSession = MediaCas.this.createFromSessionId(arrayList);
        }
    }

    private class OpenSession_1_2_Callback implements ICas.openSession_1_2Callback {
        public Session mSession;
        public int mStatus;

        private OpenSession_1_2_Callback() {
        }

        @Override // android.hardware.cas.V1_2.ICas.openSession_1_2Callback
        public void onValues(int i, ArrayList<Byte> arrayList) {
            this.mStatus = i;
            this.mSession = MediaCas.this.createFromSessionId(arrayList);
        }
    }

    private int getSessionResourceHandle() throws MediaCasException {
        validateInternalStates();
        int[] iArr = {-1};
        if (this.mTunerResourceManager != null && !this.mTunerResourceManager.requestCasSession(new CasSessionRequest(this.mClientId, this.mCasSystemId), iArr)) {
            throw new MediaCasException.InsufficientResourceException("insufficient resource to Open Session");
        }
        return iArr[0];
    }

    private void addSessionToResourceMap(Session session, int i) {
        if (i != -1) {
            synchronized (this.mSessionMap) {
                this.mSessionMap.put(session, Integer.valueOf(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeSessionFromResourceMap(Session session) {
        synchronized (this.mSessionMap) {
            if (this.mSessionMap.get(session) != null) {
                this.mTunerResourceManager.releaseCasSession(this.mSessionMap.get(session).intValue(), this.mClientId);
                this.mSessionMap.remove(session);
            }
        }
    }

    public Session openSession() throws MediaCasException {
        int sessionResourceHandle = getSessionResourceHandle();
        try {
            OpenSessionCallback openSessionCallback = new OpenSessionCallback();
            this.mICas.openSession(openSessionCallback);
            MediaCasException.throwExceptionIfNeeded(openSessionCallback.mStatus);
            addSessionToResourceMap(openSessionCallback.mSession, sessionResourceHandle);
            Log.d(TAG, "Write Stats Log for succeed to Open Session.");
            FrameworkStatsLog.write(280, this.mUserId, this.mCasSystemId, 1);
            return openSessionCallback.mSession;
        } catch (RemoteException unused) {
            this.cleanupAndRethrowIllegalState();
            Log.d(TAG, "Write Stats Log for fail to Open Session.");
            FrameworkStatsLog.write(280, this.mUserId, this.mCasSystemId, 2);
            return null;
        }
    }

    public Session openSession(int i, int i2) throws MediaCasException {
        int sessionResourceHandle = getSessionResourceHandle();
        if (this.mICasV12 == null) {
            Log.d(TAG, "Open Session with scrambling mode is only supported by cas@1.2+ interface");
            throw new MediaCasException.UnsupportedCasException("Open Session with scrambling mode is not supported");
        }
        try {
            OpenSession_1_2_Callback openSession_1_2_Callback = new OpenSession_1_2_Callback();
            this.mICasV12.openSession_1_2(i, i2, openSession_1_2_Callback);
            MediaCasException.throwExceptionIfNeeded(openSession_1_2_Callback.mStatus);
            addSessionToResourceMap(openSession_1_2_Callback.mSession, sessionResourceHandle);
            Log.d(TAG, "Write Stats Log for succeed to Open Session.");
            FrameworkStatsLog.write(280, this.mUserId, this.mCasSystemId, 1);
            return openSession_1_2_Callback.mSession;
        } catch (RemoteException unused) {
            this.cleanupAndRethrowIllegalState();
            Log.d(TAG, "Write Stats Log for fail to Open Session.");
            FrameworkStatsLog.write(280, this.mUserId, this.mCasSystemId, 2);
            return null;
        }
    }

    public void processEmm(byte[] bArr, int i, int i2) throws MediaCasException {
        validateInternalStates();
        try {
            MediaCasException.throwExceptionIfNeeded(this.mICas.processEmm(toByteArray(bArr, i, i2)));
        } catch (RemoteException unused) {
            cleanupAndRethrowIllegalState();
        }
    }

    public void processEmm(byte[] bArr) throws MediaCasException {
        processEmm(bArr, 0, bArr.length);
    }

    public void sendEvent(int i, int i2, byte[] bArr) throws MediaCasException {
        validateInternalStates();
        try {
            MediaCasException.throwExceptionIfNeeded(this.mICas.sendEvent(i, i2, toByteArray(bArr)));
        } catch (RemoteException unused) {
            cleanupAndRethrowIllegalState();
        }
    }

    public void provision(String str) throws MediaCasException {
        validateInternalStates();
        try {
            MediaCasException.throwExceptionIfNeeded(this.mICas.provision(str));
        } catch (RemoteException unused) {
            cleanupAndRethrowIllegalState();
        }
    }

    public void refreshEntitlements(int i, byte[] bArr) throws MediaCasException {
        validateInternalStates();
        try {
            MediaCasException.throwExceptionIfNeeded(this.mICas.refreshEntitlements(i, toByteArray(bArr)));
        } catch (RemoteException unused) {
            cleanupAndRethrowIllegalState();
        }
    }

    public void forceResourceLost() {
        TunerResourceManager.ResourcesReclaimListener resourcesReclaimListener = this.mResourceListener;
        if (resourcesReclaimListener != null) {
            resourcesReclaimListener.onReclaimResources();
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        android.hardware.cas.V1_0.ICas iCas = this.mICas;
        if (iCas != null) {
            try {
                iCas.release();
            } catch (RemoteException unused) {
            } catch (Throwable th) {
                this.mICas = null;
                throw th;
            }
            this.mICas = null;
        }
        TunerResourceManager tunerResourceManager = this.mTunerResourceManager;
        if (tunerResourceManager != null) {
            tunerResourceManager.unregisterClientProfile(this.mClientId);
            this.mTunerResourceManager = null;
        }
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mHandlerThread = null;
        }
    }

    protected void finalize() {
        close();
    }
}
