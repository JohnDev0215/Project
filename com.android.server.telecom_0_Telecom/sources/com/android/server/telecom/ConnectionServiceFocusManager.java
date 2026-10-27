package com.android.server.telecom;

import android.content.ComponentName;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.telecom.Log;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import com.android.internal.annotations.VisibleForTesting;
import com.android.server.telecom.CallsManager;
import com.android.server.telecom.ConnectionServiceFocusManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionServiceFocusManager {
    private static final int[] PRIORITY_FOCUS_CALL_STATE = {5, 1, 3, 12};

    @VisibleForTesting
    public static final int RELEASE_FOCUS_TIMEOUT_MS = 5000;
    private final List<CallFocus> mCalls;
    private CallsManagerRequester mCallsManagerRequester;
    private ConnectionServiceFocus mCurrentFocus;
    private CallFocus mCurrentFocusCall;
    private FocusRequest mCurrentFocusRequest;
    private FocusManagerHandler mEventHandler;
    private final CallsManagerListenerBase mCallsManagerListener = new CallsManagerListenerBase() { // from class: com.android.server.telecom.ConnectionServiceFocusManager.1
        @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
        public void onCallAdded(Call call) {
            if (callShouldBeIgnored(call)) {
                return;
            }
            ConnectionServiceFocusManager.this.mEventHandler.obtainMessage(5, new MessageArgs(Log.createSubsession(), "CSFM.oCA", call)).sendToTarget();
        }

        @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
        public void onCallRemoved(Call call) {
            if (callShouldBeIgnored(call)) {
                return;
            }
            ConnectionServiceFocusManager.this.mEventHandler.obtainMessage(6, new MessageArgs(Log.createSubsession(), "CSFM.oCR", call)).sendToTarget();
        }

        @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
        public void onCallStateChanged(Call call, int i, int i2) {
            if (callShouldBeIgnored(call)) {
                return;
            }
            ConnectionServiceFocusManager.this.mEventHandler.obtainMessage(7, i, i2, new MessageArgs(Log.createSubsession(), "CSFM.oCSS", call)).sendToTarget();
        }

        @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
        public void onExternalCallChanged(Call call, boolean z) {
            if (z) {
                ConnectionServiceFocusManager.this.mEventHandler.obtainMessage(6, new MessageArgs(Log.createSubsession(), "CSFM.oECC", call)).sendToTarget();
            } else {
                ConnectionServiceFocusManager.this.mEventHandler.obtainMessage(5, new MessageArgs(Log.createSubsession(), "CSFM.oECC", call)).sendToTarget();
            }
        }

        boolean callShouldBeIgnored(Call call) {
            return call.isExternalCall();
        }
    };
    private final ConnectionServiceFocusListener mConnectionServiceFocusListener = new ConnectionServiceFocusListener() { // from class: com.android.server.telecom.ConnectionServiceFocusManager.2
        @Override // com.android.server.telecom.ConnectionServiceFocusManager.ConnectionServiceFocusListener
        public void onConnectionServiceReleased(ConnectionServiceFocus connectionServiceFocus) {
            ConnectionServiceFocusManager.this.mEventHandler.obtainMessage(2, new MessageArgs(Log.createSubsession(), "CSFM.oCSR", connectionServiceFocus)).sendToTarget();
        }

        @Override // com.android.server.telecom.ConnectionServiceFocusManager.ConnectionServiceFocusListener
        public void onConnectionServiceDeath(ConnectionServiceFocus connectionServiceFocus) {
            ConnectionServiceFocusManager.this.mEventHandler.obtainMessage(4, new MessageArgs(Log.createSubsession(), "CSFM.oCSD", connectionServiceFocus)).sendToTarget();
        }
    };

    public interface CallFocus {
        ConnectionServiceFocus getConnectionServiceWrapper();

        int getState();

        boolean isFocusable();
    }

    public interface CallsManagerRequester {
        void releaseConnectionService(ConnectionServiceFocus connectionServiceFocus);

        void setCallsManagerListener(CallsManager.CallsManagerListener callsManagerListener);
    }

    public interface ConnectionServiceFocus {
        void connectionServiceFocusGained();

        void connectionServiceFocusLost();

        ComponentName getComponentName();

        void setConnectionServiceFocusListener(ConnectionServiceFocusListener connectionServiceFocusListener);
    }

    public interface ConnectionServiceFocusListener {
        void onConnectionServiceDeath(ConnectionServiceFocus connectionServiceFocus);

        void onConnectionServiceReleased(ConnectionServiceFocus connectionServiceFocus);
    }

    public interface ConnectionServiceFocusManagerFactory {
        ConnectionServiceFocusManager create(CallsManagerRequester callsManagerRequester);
    }

    public interface RequestFocusCallback {
        void onRequestFocusDone(CallFocus callFocus);
    }

    public ConnectionServiceFocusManager(CallsManagerRequester callsManagerRequester) {
        this.mCallsManagerRequester = callsManagerRequester;
        callsManagerRequester.setCallsManagerListener(this.mCallsManagerListener);
        HandlerThread handlerThread = new HandlerThread("ConnectionSvrFocusMgr");
        handlerThread.start();
        this.mEventHandler = new FocusManagerHandler(handlerThread.getLooper());
        this.mCalls = new ArrayList();
    }

    public void requestFocus(CallFocus callFocus, RequestFocusCallback requestFocusCallback) {
        this.mEventHandler.obtainMessage(1, new MessageArgs(Log.createSubsession(), "CSFM.rF", new FocusRequest(callFocus, requestFocusCallback))).sendToTarget();
    }

    public CallFocus getCurrentFocusCall() {
        if (this.mEventHandler.getLooper().isCurrentThread()) {
            return this.mCurrentFocusCall;
        }
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(1);
        this.mEventHandler.post(new Runnable() { // from class: com.android.server.telecom.-$$Lambda$ConnectionServiceFocusManager$46kdmnYETSnCWRfRgkO7fAWDhnA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$getCurrentFocusCall$0$ConnectionServiceFocusManager(linkedBlockingQueue);
            }
        });
        try {
            Optional optional = (Optional) linkedBlockingQueue.poll(1000L, TimeUnit.MILLISECONDS);
            if (optional != null) {
                return (CallFocus) optional.orElse(null);
            }
            Log.w("ConnectionSvrFocusMgr", "Timed out waiting for synchronous current focus. Returning possibly inaccurate result", new Object[0]);
            return this.mCurrentFocusCall;
        } catch (InterruptedException unused) {
            Log.w("ConnectionSvrFocusMgr", "Interrupted when waiting for synchronous current focus. Returning possibly inaccurate result.", new Object[0]);
            return this.mCurrentFocusCall;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getCurrentFocusCall$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$getCurrentFocusCall$0$ConnectionServiceFocusManager(BlockingQueue blockingQueue) {
        CallFocus callFocus = this.mCurrentFocusCall;
        blockingQueue.offer(callFocus == null ? Optional.empty() : Optional.of(callFocus));
    }

    @VisibleForTesting
    public Handler getHandler() {
        return this.mEventHandler;
    }

    @VisibleForTesting
    public List<CallFocus> getAllCall() {
        return this.mCalls;
    }

    private void updateConnectionServiceFocus(ConnectionServiceFocus connectionServiceFocus) {
        if (Objects.equals(this.mCurrentFocus, connectionServiceFocus)) {
            return;
        }
        if (connectionServiceFocus != null) {
            connectionServiceFocus.setConnectionServiceFocusListener(this.mConnectionServiceFocusListener);
            connectionServiceFocus.connectionServiceFocusGained();
        }
        this.mCurrentFocus = connectionServiceFocus;
        Log.d(this, "updateConnectionServiceFocus connSvr = %s", new Object[]{connectionServiceFocus});
    }

    private void updateCurrentFocusCall() {
        int[] iArr = PRIORITY_FOCUS_CALL_STATE;
        this.mCurrentFocusCall = null;
        if (this.mCurrentFocus == null) {
            return;
        }
        List<CallFocus> list = (List) this.mCalls.stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$ConnectionServiceFocusManager$0zth9iEIa9iOXDDninc1Rf_ksfU
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$updateCurrentFocusCall$1$ConnectionServiceFocusManager((ConnectionServiceFocusManager.CallFocus) obj);
            }
        }).collect(Collectors.toList());
        for (int i : iArr) {
            for (CallFocus callFocus : list) {
                if (callFocus.getState() == i) {
                    this.mCurrentFocusCall = callFocus;
                    Log.d(this, "updateCurrentFocusCall %s", new Object[]{callFocus});
                    return;
                }
            }
        }
        Log.d(this, "updateCurrentFocusCall = null", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateCurrentFocusCall$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$updateCurrentFocusCall$1$ConnectionServiceFocusManager(CallFocus callFocus) {
        return this.mCurrentFocus.equals(callFocus.getConnectionServiceWrapper()) && callFocus.isFocusable();
    }

    private void onRequestFocusDone(FocusRequest focusRequest) {
        RequestFocusCallback requestFocusCallback = focusRequest.callback;
        if (requestFocusCallback != null) {
            requestFocusCallback.onRequestFocusDone(focusRequest.call);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRequestFocus(FocusRequest focusRequest) {
        Log.d(this, "handleRequestFocus req = %s", new Object[]{focusRequest});
        ConnectionServiceFocus connectionServiceFocus = this.mCurrentFocus;
        if (connectionServiceFocus == null || connectionServiceFocus.equals(focusRequest.call.getConnectionServiceWrapper())) {
            updateConnectionServiceFocus(focusRequest.call.getConnectionServiceWrapper());
            updateCurrentFocusCall();
            onRequestFocusDone(focusRequest);
        } else {
            this.mCurrentFocus.connectionServiceFocusLost();
            this.mCurrentFocusRequest = focusRequest;
            this.mEventHandler.sendMessageDelayed(this.mEventHandler.obtainMessage(3, new MessageArgs(Log.createSubsession(), "CSFM.hRF", focusRequest)), 5000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleReleasedFocus(ConnectionServiceFocus connectionServiceFocus) {
        Log.d(this, "handleReleasedFocus connSvr = %s", new Object[]{connectionServiceFocus});
        if (Objects.equals(this.mCurrentFocus, connectionServiceFocus)) {
            this.mEventHandler.removeMessages(3);
            FocusRequest focusRequest = this.mCurrentFocusRequest;
            updateConnectionServiceFocus(focusRequest != null ? focusRequest.call.getConnectionServiceWrapper() : null);
            updateCurrentFocusCall();
            FocusRequest focusRequest2 = this.mCurrentFocusRequest;
            if (focusRequest2 != null) {
                onRequestFocusDone(focusRequest2);
                this.mCurrentFocusRequest = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleReleasedFocusTimeout(FocusRequest focusRequest) {
        Log.d(this, "handleReleasedFocusTimeout req = %s", new Object[]{focusRequest});
        this.mCallsManagerRequester.releaseConnectionService(this.mCurrentFocus);
        updateConnectionServiceFocus(focusRequest.call.getConnectionServiceWrapper());
        updateCurrentFocusCall();
        onRequestFocusDone(focusRequest);
        this.mCurrentFocusRequest = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleConnectionServiceDeath(ConnectionServiceFocus connectionServiceFocus) {
        Log.d(this, "handleConnectionServiceDeath %s", new Object[]{connectionServiceFocus});
        if (Objects.equals(connectionServiceFocus, this.mCurrentFocus)) {
            updateConnectionServiceFocus(null);
            updateCurrentFocusCall();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAddedCall(CallFocus callFocus) {
        Log.d(this, "handleAddedCall %s", new Object[]{callFocus});
        if (!this.mCalls.contains(callFocus)) {
            this.mCalls.add(callFocus);
        }
        if (Objects.equals(this.mCurrentFocus, callFocus.getConnectionServiceWrapper())) {
            updateCurrentFocusCall();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRemovedCall(CallFocus callFocus) {
        Log.d(this, "handleRemovedCall %s", new Object[]{callFocus});
        this.mCalls.remove(callFocus);
        if (callFocus.equals(this.mCurrentFocusCall)) {
            updateCurrentFocusCall();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCallStateChanged(CallFocus callFocus, int i, int i2) {
        Log.d(this, "handleCallStateChanged %s, oldState = %d, newState = %d", new Object[]{callFocus, Integer.valueOf(i), Integer.valueOf(i2)});
        if (this.mCalls.contains(callFocus) && Objects.equals(this.mCurrentFocus, callFocus.getConnectionServiceWrapper())) {
            updateCurrentFocusCall();
        }
    }

    private final class FocusManagerHandler extends Handler {
        FocusManagerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Object obj = message.obj;
            Session session = ((MessageArgs) obj).logSession;
            String str = ((MessageArgs) obj).shortName;
            if (TextUtils.isEmpty(str)) {
                str = "hM";
            }
            Log.continueSession(session, str);
            Object obj2 = ((MessageArgs) message.obj).obj;
            try {
                switch (message.what) {
                    case 1:
                        ConnectionServiceFocusManager.this.handleRequestFocus((FocusRequest) obj2);
                        break;
                    case HeadsetMediaButton.LONG_PRESS /* 2 */:
                        ConnectionServiceFocusManager.this.handleReleasedFocus((ConnectionServiceFocus) obj2);
                        break;
                    case 3:
                        ConnectionServiceFocusManager.this.handleReleasedFocusTimeout((FocusRequest) obj2);
                        break;
                    case 4:
                        ConnectionServiceFocusManager.this.handleConnectionServiceDeath((ConnectionServiceFocus) obj2);
                        break;
                    case 5:
                        ConnectionServiceFocusManager.this.handleAddedCall((CallFocus) obj2);
                        break;
                    case 6:
                        ConnectionServiceFocusManager.this.handleRemovedCall((CallFocus) obj2);
                        break;
                    case 7:
                        ConnectionServiceFocusManager.this.handleCallStateChanged((CallFocus) obj2, message.arg1, message.arg2);
                        break;
                }
            } finally {
                Log.endSession();
            }
        }
    }

    private static final class FocusRequest {
        CallFocus call;
        RequestFocusCallback callback;

        FocusRequest(CallFocus callFocus, RequestFocusCallback requestFocusCallback) {
            this.call = callFocus;
            this.callback = requestFocusCallback;
        }
    }

    private static final class MessageArgs {
        Session logSession;
        Object obj;
        String shortName;

        MessageArgs(Session session, String str, Object obj) {
            this.logSession = session;
            this.shortName = str;
            this.obj = obj;
        }
    }
}
