package com.android.server.telecom;

import android.app.AppOpsManager;
import android.content.Context;
import android.net.Uri;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import android.telecom.Connection;
import android.telecom.Log;
import android.telecom.VideoProfile;
import android.text.TextUtils;
import android.view.Surface;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telecom.IVideoCallback;
import com.android.internal.telecom.IVideoProvider;
import com.android.server.telecom.TelecomSystem;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class VideoProviderProxy extends Connection.VideoProvider {
    private Call mCall;
    private final IVideoProvider mConectionServiceVideoProvider;
    private CurrentUserProxy mCurrentUserProxy;
    private IBinder.DeathRecipient mDeathRecipient;
    private final Set<Listener> mListeners;
    private final TelecomSystem.SyncRoot mLock;
    private final VideoCallListenerBinder mVideoCallListenerBinder;

    public interface Listener {
        void onSessionModifyRequestReceived(Call call, VideoProfile videoProfile);

        void onSetCamera(Call call, String str);
    }

    @Override // android.telecom.Connection.VideoProvider
    public void onSetCamera(String str) {
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [android.os.IBinder, com.android.server.telecom.VideoProviderProxy$VideoCallListenerBinder] */
    public VideoProviderProxy(TelecomSystem.SyncRoot syncRoot, IVideoProvider iVideoProvider, Call call, CurrentUserProxy currentUserProxy) throws RemoteException {
        super(Looper.getMainLooper());
        this.mListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
        this.mDeathRecipient = new IBinder.DeathRecipient() { // from class: com.android.server.telecom.VideoProviderProxy.1
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                VideoProviderProxy.this.mConectionServiceVideoProvider.asBinder().unlinkToDeath(this, 0);
            }
        };
        this.mLock = syncRoot;
        this.mConectionServiceVideoProvider = iVideoProvider;
        iVideoProvider.asBinder().linkToDeath(this.mDeathRecipient, 0);
        ?? videoCallListenerBinder = new VideoCallListenerBinder();
        this.mVideoCallListenerBinder = videoCallListenerBinder;
        this.mConectionServiceVideoProvider.addVideoCallback((IBinder) videoCallListenerBinder);
        this.mCall = call;
        this.mCurrentUserProxy = currentUserProxy;
    }

    public void clearVideoCallback() {
        try {
            this.mConectionServiceVideoProvider.removeVideoCallback(this.mVideoCallListenerBinder);
        } catch (RemoteException unused) {
        }
    }

    @VisibleForTesting
    public VideoCallListenerBinder getVideoCallListenerBinder() {
        return this.mVideoCallListenerBinder;
    }

    public final class VideoCallListenerBinder extends IVideoCallback.Stub {
        public VideoCallListenerBinder() {
        }

        public void receiveSessionModifyRequest(VideoProfile videoProfile) {
            try {
                Log.startSession("VPP.rSMR");
                synchronized (VideoProviderProxy.this.mLock) {
                    VideoProviderProxy.this.logFromVideoProvider("receiveSessionModifyRequest: " + videoProfile);
                    Log.addEvent(VideoProviderProxy.this.mCall, "RECEIVE_VIDEO_REQUEST", VideoProfile.videoStateToString(videoProfile.getVideoState()));
                    VideoProviderProxy.this.mCall.getAnalytics().addVideoEvent(2, videoProfile.getVideoState());
                    if ((!VideoProviderProxy.this.mCall.isVideoCallingSupportedByPhoneAccount() || !VideoProviderProxy.this.mCall.isLocallyVideoCapable()) && VideoProfile.isVideo(videoProfile.getVideoState())) {
                        Log.addEvent(VideoProviderProxy.this.mCall, "SEND_VIDEO_RESPONSE", "video not supported");
                        try {
                            VideoProviderProxy.this.mConectionServiceVideoProvider.sendSessionModifyResponse(new VideoProfile(0));
                        } catch (RemoteException unused) {
                        }
                    } else {
                        Iterator it = VideoProviderProxy.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((Listener) it.next()).onSessionModifyRequestReceived(VideoProviderProxy.this.mCall, videoProfile);
                        }
                        VideoProviderProxy.this.receiveSessionModifyRequest(videoProfile);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public void receiveSessionModifyResponse(int i, VideoProfile videoProfile, VideoProfile videoProfile2) {
            VideoProviderProxy.this.logFromVideoProvider("receiveSessionModifyResponse: status=" + i + " requestProfile=" + videoProfile + " responseProfile=" + videoProfile2);
            StringBuilder sb = new StringBuilder();
            sb.append("Status Code : ");
            sb.append(i);
            sb.append(" Video State: ");
            sb.append(videoProfile2 != null ? Integer.valueOf(videoProfile2.getVideoState()) : "null");
            Log.addEvent(VideoProviderProxy.this.mCall, "RECEIVE_VIDEO_RESPONSE", sb.toString());
            synchronized (VideoProviderProxy.this.mLock) {
                if (i == 1) {
                    VideoProviderProxy.this.mCall.getAnalytics().addVideoEvent(3, videoProfile2 == null ? 0 : videoProfile2.getVideoState());
                    VideoProviderProxy.this.receiveSessionModifyResponse(i, videoProfile, videoProfile2);
                } else {
                    VideoProviderProxy.this.receiveSessionModifyResponse(i, videoProfile, videoProfile2);
                }
            }
        }

        public void handleCallSessionEvent(int i) {
            synchronized (VideoProviderProxy.this.mLock) {
                VideoProviderProxy.this.logFromVideoProvider("handleCallSessionEvent: " + Connection.VideoProvider.sessionEventToString(i));
                VideoProviderProxy.this.handleCallSessionEvent(i);
            }
        }

        public void changePeerDimensions(int i, int i2) {
            synchronized (VideoProviderProxy.this.mLock) {
                VideoProviderProxy.this.logFromVideoProvider("changePeerDimensions: width=" + i + " height=" + i2);
                VideoProviderProxy.this.changePeerDimensions(i, i2);
            }
        }

        public void changeVideoQuality(int i) {
            synchronized (VideoProviderProxy.this.mLock) {
                VideoProviderProxy.this.logFromVideoProvider("changeVideoQuality: " + i);
                VideoProviderProxy.this.changeVideoQuality(i);
            }
        }

        public void changeCallDataUsage(long j) {
            synchronized (VideoProviderProxy.this.mLock) {
                VideoProviderProxy.this.logFromVideoProvider("changeCallDataUsage: " + j);
                VideoProviderProxy.this.setCallDataUsage(j);
                VideoProviderProxy.this.mCall.setCallDataUsage(j);
            }
        }

        public void changeCameraCapabilities(VideoProfile.CameraCapabilities cameraCapabilities) {
            synchronized (VideoProviderProxy.this.mLock) {
                VideoProviderProxy.this.logFromVideoProvider("changeCameraCapabilities: " + cameraCapabilities);
                VideoProviderProxy.this.changeCameraCapabilities(cameraCapabilities);
            }
        }
    }

    public void onSetCamera(String str, String str2, int i, int i2, int i3) {
        synchronized (this.mLock) {
            logFromInCall("setCamera: " + str + " callingPackage=" + str2 + "; callingUid=" + i);
            if (!TextUtils.isEmpty(str) && !canUseCamera(this.mCall.getContext(), str2, i, i2)) {
                Log.i(this, "onSetCamera: camera permission denied; package=%s, uid=%d, pid=%d, targetSdkVersion=%d", new Object[]{str2, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
                if (i3 > 25) {
                    handleCallSessionEvent(7);
                } else {
                    handleCallSessionEvent(5);
                }
                return;
            }
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onSetCamera(this.mCall, str);
            }
            try {
                this.mConectionServiceVideoProvider.setCamera(str, str2, i3);
            } catch (RemoteException unused) {
                handleCallSessionEvent(5);
            }
        }
    }

    @Override // android.telecom.Connection.VideoProvider
    public void onSetPreviewSurface(Surface surface) {
        synchronized (this.mLock) {
            logFromInCall("setPreviewSurface");
            try {
                this.mConectionServiceVideoProvider.setPreviewSurface(surface);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.telecom.Connection.VideoProvider
    public void onSetDisplaySurface(Surface surface) {
        synchronized (this.mLock) {
            logFromInCall("setDisplaySurface");
            try {
                this.mConectionServiceVideoProvider.setDisplaySurface(surface);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.telecom.Connection.VideoProvider
    public void onSetDeviceOrientation(int i) {
        synchronized (this.mLock) {
            logFromInCall("setDeviceOrientation: " + i);
            try {
                this.mConectionServiceVideoProvider.setDeviceOrientation(i);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.telecom.Connection.VideoProvider
    public void onSetZoom(float f) {
        synchronized (this.mLock) {
            logFromInCall("setZoom: " + f);
            try {
                this.mConectionServiceVideoProvider.setZoom(f);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.telecom.Connection.VideoProvider
    public void onSendSessionModifyRequest(VideoProfile videoProfile, VideoProfile videoProfile2) {
        synchronized (this.mLock) {
            logFromInCall("sendSessionModifyRequest: from=" + videoProfile + " to=" + videoProfile2);
            Log.addEvent(this.mCall, "SEND_VIDEO_REQUEST", VideoProfile.videoStateToString(videoProfile2.getVideoState()));
            if (!VideoProfile.isVideo(videoProfile.getVideoState()) && VideoProfile.isVideo(videoProfile2.getVideoState())) {
                this.mCall.maybeEnableSpeakerForVideoUpgrade(videoProfile2.getVideoState());
            }
            this.mCall.getAnalytics().addVideoEvent(0, videoProfile2.getVideoState());
            try {
                this.mConectionServiceVideoProvider.sendSessionModifyRequest(videoProfile, videoProfile2);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.telecom.Connection.VideoProvider
    public void onSendSessionModifyResponse(VideoProfile videoProfile) {
        synchronized (this.mLock) {
            logFromInCall("sendSessionModifyResponse: " + videoProfile);
            Log.addEvent(this.mCall, "SEND_VIDEO_RESPONSE", VideoProfile.videoStateToString(videoProfile.getVideoState()));
            this.mCall.getAnalytics().addVideoEvent(1, videoProfile.getVideoState());
            try {
                this.mConectionServiceVideoProvider.sendSessionModifyResponse(videoProfile);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.telecom.Connection.VideoProvider
    public void onRequestCameraCapabilities() {
        synchronized (this.mLock) {
            logFromInCall("requestCameraCapabilities");
            try {
                this.mConectionServiceVideoProvider.requestCameraCapabilities();
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.telecom.Connection.VideoProvider
    public void onRequestConnectionDataUsage() {
        synchronized (this.mLock) {
            logFromInCall("requestCallDataUsage");
            try {
                this.mConectionServiceVideoProvider.requestCallDataUsage();
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.telecom.Connection.VideoProvider
    public void onSetPauseImage(Uri uri) {
        synchronized (this.mLock) {
            logFromInCall("setPauseImage: " + uri);
            try {
                this.mConectionServiceVideoProvider.setPauseImage(uri);
            } catch (RemoteException unused) {
            }
        }
    }

    public void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    private void logFromInCall(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("IC->VP (callId=");
        Call call = this.mCall;
        sb.append(call == null ? "?" : call.getId());
        sb.append("): ");
        sb.append(str);
        Log.i(this, sb.toString(), new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logFromVideoProvider(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("VP->IC (callId=");
        Call call = this.mCall;
        sb.append(call == null ? "?" : call.getId());
        sb.append("): ");
        sb.append(str);
        Log.i(this, sb.toString(), new Object[0]);
    }

    private boolean canUseCamera(Context context, String str, int i, int i2) {
        UserHandle userHandleForUid = UserHandle.getUserHandleForUid(i);
        UserHandle currentUserHandle = this.mCurrentUserProxy.getCurrentUserHandle();
        if (currentUserHandle != null && !currentUserHandle.equals(userHandleForUid)) {
            Log.w(this, "canUseCamera attempt to user camera by background user.", new Object[0]);
            return false;
        }
        try {
            context.enforcePermission("android.permission.CAMERA", i2, i, "Camera permission required.");
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            if (appOpsManager == null) {
                return false;
            }
            try {
                return appOpsManager.noteOp(26, i, str) == 0;
            } catch (SecurityException e) {
                Log.w(this, "canUseCamera got appOpps Exception " + e.toString(), new Object[0]);
                return false;
            }
        } catch (SecurityException unused) {
            return false;
        }
    }
}
