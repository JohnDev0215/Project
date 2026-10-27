package android.view.contentcapture;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.graphics.Insets;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.LocalLog;
import android.util.Log;
import android.util.TimeUtils;
import android.view.autofill.AutofillId;
import android.view.contentcapture.IContentCaptureDirectManager;
import android.view.contentcapture.ViewNode;
import com.android.internal.os.IResultReceiver;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public final class MainContentCaptureSession extends ContentCaptureSession {
    public static final String EXTRA_BINDER = "binder";
    public static final String EXTRA_ENABLED_STATE = "enabled";
    private static final boolean FORCE_FLUSH = true;
    private static final int MSG_FLUSH = 1;
    private static final String TAG = "MainContentCaptureSession";
    private IBinder mApplicationToken;
    private ComponentName mComponentName;
    private final Context mContext;
    private IContentCaptureDirectManager mDirectServiceInterface;
    private IBinder.DeathRecipient mDirectServiceVulture;
    private ArrayList<ContentCaptureEvent> mEvents;
    private final LocalLog mFlushHistory;
    private final Handler mHandler;
    private final ContentCaptureManager mManager;
    private long mNextFlush;
    private final SessionStateReceiver mSessionStateReceiver;
    private final IContentCaptureManager mSystemServerInterface;
    private final AtomicBoolean mDisabled = new AtomicBoolean(false);
    private int mState = 0;
    private boolean mNextFlushForTextChanged = false;

    @Override // android.view.contentcapture.ContentCaptureSession
    MainContentCaptureSession getMainCaptureSession() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class SessionStateReceiver extends IResultReceiver.Stub {
        private final WeakReference<MainContentCaptureSession> mMainSession;

        SessionStateReceiver(MainContentCaptureSession mainContentCaptureSession) {
            this.mMainSession = new WeakReference<>(mainContentCaptureSession);
        }

        @Override // com.android.internal.os.IResultReceiver
        public void send(final int i, Bundle bundle) {
            final IBinder binder;
            final MainContentCaptureSession mainContentCaptureSession = this.mMainSession.get();
            if (mainContentCaptureSession == null) {
                Log.w(MainContentCaptureSession.TAG, "received result after mina session released");
                return;
            }
            if (bundle == null) {
                binder = null;
            } else {
                if (bundle.getBoolean("enabled")) {
                    mainContentCaptureSession.mDisabled.set(i == 2);
                    return;
                }
                binder = bundle.getBinder(MainContentCaptureSession.EXTRA_BINDER);
                if (binder == null) {
                    Log.wtf(MainContentCaptureSession.TAG, "No binder extra result");
                    mainContentCaptureSession.mHandler.post(new Runnable() { // from class: android.view.contentcapture.-$$Lambda$MainContentCaptureSession$SessionStateReceiver$8PfhpDxL4uce1ah63EZf3_bDJxc
                        @Override // java.lang.Runnable
                        public final void run() {
                            mainContentCaptureSession.resetSession(260);
                        }
                    });
                    return;
                }
            }
            mainContentCaptureSession.mHandler.post(new Runnable() { // from class: android.view.contentcapture.-$$Lambda$MainContentCaptureSession$SessionStateReceiver$1SZEhAz_3j1HcL0mDZd_J4DqTSw
                @Override // java.lang.Runnable
                public final void run() {
                    mainContentCaptureSession.onSessionStarted(i, binder);
                }
            });
        }
    }

    protected MainContentCaptureSession(Context context, ContentCaptureManager contentCaptureManager, Handler handler, IContentCaptureManager iContentCaptureManager) {
        this.mContext = context;
        this.mManager = contentCaptureManager;
        this.mHandler = handler;
        this.mSystemServerInterface = iContentCaptureManager;
        int i = contentCaptureManager.mOptions.logHistorySize;
        this.mFlushHistory = i > 0 ? new LocalLog(i) : null;
        this.mSessionStateReceiver = new SessionStateReceiver(this);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    ContentCaptureSession newChild(ContentCaptureContext contentCaptureContext) {
        ChildContentCaptureSession childContentCaptureSession = new ChildContentCaptureSession(this, contentCaptureContext);
        notifyChildSessionStarted(this.mId, childContentCaptureSession.mId, contentCaptureContext);
        return childContentCaptureSession;
    }

    void start(IBinder iBinder, ComponentName componentName, int i) {
        if (isContentCaptureEnabled()) {
            if (ContentCaptureHelper.sVerbose) {
                Log.v(TAG, "start(): token=" + iBinder + ", comp=" + ComponentName.flattenToShortString(componentName));
            }
            if (hasStarted()) {
                if (ContentCaptureHelper.sDebug) {
                    Log.d(TAG, "ignoring handleStartSession(" + iBinder + "/" + ComponentName.flattenToShortString(componentName) + " while on state " + getStateAsString(this.mState));
                    return;
                }
                return;
            }
            this.mState = 1;
            this.mApplicationToken = iBinder;
            this.mComponentName = componentName;
            if (ContentCaptureHelper.sVerbose) {
                Log.v(TAG, "handleStartSession(): token=" + iBinder + ", act=" + getDebugState() + ", id=" + this.mId);
            }
            try {
                this.mSystemServerInterface.startSession(this.mApplicationToken, componentName, this.mId, i, this.mSessionStateReceiver);
            } catch (RemoteException e) {
                Log.w(TAG, "Error starting session for " + componentName.flattenToShortString() + ": " + e);
            }
        }
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void onDestroy() {
        this.mHandler.removeMessages(1);
        this.mHandler.post(new Runnable() { // from class: android.view.contentcapture.-$$Lambda$MainContentCaptureSession$xv6G_6xWQNqoCPFZHD8mXRxc6kc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDestroy$0$MainContentCaptureSession();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSessionStarted(int i, IBinder iBinder) {
        if (iBinder != null) {
            this.mDirectServiceInterface = IContentCaptureDirectManager.Stub.asInterface(iBinder);
            IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() { // from class: android.view.contentcapture.-$$Lambda$MainContentCaptureSession$-_Bo0GyMC9jrhpIngHmXUJsDSTM
                @Override // android.os.IBinder.DeathRecipient
                public final void binderDied() {
                    this.f$0.lambda$onSessionStarted$1$MainContentCaptureSession();
                }
            };
            this.mDirectServiceVulture = deathRecipient;
            try {
                iBinder.linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                Log.w(TAG, "Failed to link to death on " + iBinder + ": " + e);
            }
        }
        if ((i & 4) != 0) {
            resetSession(i);
        } else {
            this.mState = i;
            this.mDisabled.set(false);
            lambda$scheduleFlush$2$MainContentCaptureSession(7);
        }
        if (ContentCaptureHelper.sVerbose) {
            String str = TAG;
            StringBuilder sbAppend = new StringBuilder().append("handleSessionStarted() result: id=").append(this.mId).append(" resultCode=").append(i).append(", state=").append(getStateAsString(this.mState)).append(", disabled=").append(this.mDisabled.get()).append(", binder=").append(iBinder).append(", events=");
            ArrayList<ContentCaptureEvent> arrayList = this.mEvents;
            Log.v(str, sbAppend.append(arrayList != null ? arrayList.size() : 0).toString());
        }
    }

    public /* synthetic */ void lambda$onSessionStarted$1$MainContentCaptureSession() {
        Log.w(TAG, "Keeping session " + this.mId + " when service died");
        this.mState = 1024;
        this.mDisabled.set(true);
    }

    private void sendEvent(ContentCaptureEvent contentCaptureEvent) {
        sendEvent(contentCaptureEvent, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0107  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void sendEvent(android.view.contentcapture.ContentCaptureEvent r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 458
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.contentcapture.MainContentCaptureSession.sendEvent(android.view.contentcapture.ContentCaptureEvent, boolean):void");
    }

    private boolean hasStarted() {
        return this.mState != 0;
    }

    private void scheduleFlush(final int i, boolean z) {
        int i2;
        if (ContentCaptureHelper.sVerbose) {
            Log.v(TAG, "handleScheduleFlush(" + getDebugState(i) + ", checkExisting=" + z);
        }
        if (!hasStarted()) {
            if (ContentCaptureHelper.sVerbose) {
                Log.v(TAG, "handleScheduleFlush(): session not started yet");
                return;
            }
            return;
        }
        if (this.mDisabled.get()) {
            String str = TAG;
            StringBuilder sbAppend = new StringBuilder().append("handleScheduleFlush(").append(getDebugState(i)).append("): should not be called when disabled. events=");
            ArrayList<ContentCaptureEvent> arrayList = this.mEvents;
            Log.e(str, sbAppend.append(arrayList == null ? null : Integer.valueOf(arrayList.size())).toString());
            return;
        }
        if (z && this.mHandler.hasMessages(1)) {
            this.mHandler.removeMessages(1);
        }
        if (i == 6) {
            i2 = this.mManager.mOptions.textChangeFlushingFrequencyMs;
        } else {
            if (i != 5 && ContentCaptureHelper.sDebug) {
                Log.d(TAG, "handleScheduleFlush(" + getDebugState(i) + "): not a timeout reason because mDirectServiceInterface is not ready yet");
            }
            i2 = this.mManager.mOptions.idleFlushingFrequencyMs;
        }
        long j = i2;
        this.mNextFlush = System.currentTimeMillis() + j;
        if (ContentCaptureHelper.sVerbose) {
            Log.v(TAG, "handleScheduleFlush(): scheduled to flush in " + i2 + "ms: " + TimeUtils.logTimeOfDay(this.mNextFlush));
        }
        this.mHandler.postDelayed(new Runnable() { // from class: android.view.contentcapture.-$$Lambda$MainContentCaptureSession$eKmGDfEo2kq0f3uDp6OYG0dnUuk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$scheduleFlush$2$MainContentCaptureSession(i);
            }
        }, 1, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: flushIfNeeded, reason: merged with bridge method [inline-methods] */
    public void lambda$scheduleFlush$2$MainContentCaptureSession(int i) {
        ArrayList<ContentCaptureEvent> arrayList = this.mEvents;
        if (arrayList == null || arrayList.isEmpty()) {
            if (ContentCaptureHelper.sVerbose) {
                Log.v(TAG, "Nothing to flush");
                return;
            }
            return;
        }
        flush(i);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void flush(int i) {
        if (this.mEvents == null) {
            return;
        }
        if (this.mDisabled.get()) {
            Log.e(TAG, "handleForceFlush(" + getDebugState(i) + "): should not be when disabled");
            return;
        }
        if (this.mDirectServiceInterface == null) {
            if (ContentCaptureHelper.sVerbose) {
                Log.v(TAG, "handleForceFlush(" + getDebugState(i) + "): hold your horses, client not ready: " + this.mEvents);
            }
            if (this.mHandler.hasMessages(1)) {
                return;
            }
            scheduleFlush(i, false);
            return;
        }
        this.mNextFlushForTextChanged = false;
        int size = this.mEvents.size();
        String flushReasonAsString = getFlushReasonAsString(i);
        if (ContentCaptureHelper.sDebug) {
            Log.d(TAG, "Flushing " + size + " event(s) for " + getDebugState(i));
        }
        if (this.mFlushHistory != null) {
            this.mFlushHistory.log("r=" + flushReasonAsString + " s=" + size + " m=" + this.mManager.mOptions.maxBufferSize + " i=" + this.mManager.mOptions.idleFlushingFrequencyMs);
        }
        try {
            this.mHandler.removeMessages(1);
            this.mDirectServiceInterface.sendEvents(clearEvents(), i, this.mManager.mOptions);
        } catch (RemoteException e) {
            Log.w(TAG, "Error sending " + size + " for " + getDebugState() + ": " + e);
        }
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    public void updateContentCaptureContext(ContentCaptureContext contentCaptureContext) {
        notifyContextUpdated(this.mId, contentCaptureContext);
    }

    private ParceledListSlice<ContentCaptureEvent> clearEvents() {
        List listEmptyList = this.mEvents;
        if (listEmptyList == null) {
            listEmptyList = Collections.emptyList();
        }
        this.mEvents = null;
        return new ParceledListSlice<>(listEmptyList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: destroySession, reason: merged with bridge method [inline-methods] */
    public void lambda$onDestroy$0$MainContentCaptureSession() {
        if (ContentCaptureHelper.sDebug) {
            String str = TAG;
            StringBuilder sbAppend = new StringBuilder().append("Destroying session (ctx=").append(this.mContext).append(", id=").append(this.mId).append(") with ");
            ArrayList<ContentCaptureEvent> arrayList = this.mEvents;
            Log.d(str, sbAppend.append(arrayList == null ? 0 : arrayList.size()).append(" event(s) for ").append(getDebugState()).toString());
        }
        try {
            this.mSystemServerInterface.finishSession(this.mId);
        } catch (RemoteException e) {
            Log.e(TAG, "Error destroying system-service session " + this.mId + " for " + getDebugState() + ": " + e);
        }
        IContentCaptureDirectManager iContentCaptureDirectManager = this.mDirectServiceInterface;
        if (iContentCaptureDirectManager != null) {
            iContentCaptureDirectManager.asBinder().unlinkToDeath(this.mDirectServiceVulture, 0);
        }
        this.mDirectServiceInterface = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetSession(int i) {
        if (ContentCaptureHelper.sVerbose) {
            Log.v(TAG, "handleResetSession(" + getActivityName() + "): from " + getStateAsString(this.mState) + " to " + getStateAsString(i));
        }
        this.mState = i;
        this.mDisabled.set((i & 4) != 0);
        this.mApplicationToken = null;
        this.mComponentName = null;
        this.mEvents = null;
        IContentCaptureDirectManager iContentCaptureDirectManager = this.mDirectServiceInterface;
        if (iContentCaptureDirectManager != null) {
            iContentCaptureDirectManager.asBinder().unlinkToDeath(this.mDirectServiceVulture, 0);
        }
        this.mDirectServiceInterface = null;
        this.mHandler.removeMessages(1);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void internalNotifyViewAppeared(ViewNode.ViewStructureImpl viewStructureImpl) {
        notifyViewAppeared(this.mId, viewStructureImpl);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void internalNotifyViewDisappeared(AutofillId autofillId) {
        notifyViewDisappeared(this.mId, autofillId);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void internalNotifyViewTextChanged(AutofillId autofillId, CharSequence charSequence) {
        notifyViewTextChanged(this.mId, autofillId, charSequence);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void internalNotifyViewInsetsChanged(Insets insets) {
        notifyViewInsetsChanged(this.mId, insets);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    public void internalNotifyViewTreeEvent(boolean z) {
        notifyViewTreeEvent(this.mId, z);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    public void internalNotifySessionResumed() {
        notifySessionResumed(this.mId);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    public void internalNotifySessionPaused() {
        notifySessionPaused(this.mId);
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    boolean isContentCaptureEnabled() {
        return super.isContentCaptureEnabled() && this.mManager.isContentCaptureEnabled();
    }

    boolean isDisabled() {
        return this.mDisabled.get();
    }

    boolean setDisabled(boolean z) {
        return this.mDisabled.compareAndSet(!z, z);
    }

    void notifyChildSessionStarted(int i, int i2, ContentCaptureContext contentCaptureContext) {
        sendEvent(new ContentCaptureEvent(i2, -1).setParentSessionId(i).setClientContext(contentCaptureContext), true);
    }

    void notifyChildSessionFinished(int i, int i2) {
        sendEvent(new ContentCaptureEvent(i2, -2).setParentSessionId(i), true);
    }

    void notifyViewAppeared(int i, ViewNode.ViewStructureImpl viewStructureImpl) {
        sendEvent(new ContentCaptureEvent(i, 1).setViewNode(viewStructureImpl.mNode));
    }

    public void notifyViewDisappeared(int i, AutofillId autofillId) {
        sendEvent(new ContentCaptureEvent(i, 2).setAutofillId(autofillId));
    }

    void notifyViewTextChanged(int i, AutofillId autofillId, CharSequence charSequence) {
        sendEvent(new ContentCaptureEvent(i, 3).setAutofillId(autofillId).setText(charSequence));
    }

    public void notifyViewInsetsChanged(int i, Insets insets) {
        sendEvent(new ContentCaptureEvent(i, 9).setInsets(insets));
    }

    public void notifyViewTreeEvent(int i, boolean z) {
        sendEvent(new ContentCaptureEvent(i, z ? 4 : 5), true);
    }

    void notifySessionResumed(int i) {
        sendEvent(new ContentCaptureEvent(i, 7), true);
    }

    void notifySessionPaused(int i) {
        sendEvent(new ContentCaptureEvent(i, 8), true);
    }

    void notifyContextUpdated(int i, ContentCaptureContext contentCaptureContext) {
        sendEvent(new ContentCaptureEvent(i, 6).setClientContext(contentCaptureContext));
    }

    @Override // android.view.contentcapture.ContentCaptureSession
    void dump(String str, PrintWriter printWriter) {
        super.dump(str, printWriter);
        printWriter.print(str);
        printWriter.print("mContext: ");
        printWriter.println(this.mContext);
        printWriter.print(str);
        printWriter.print("user: ");
        printWriter.println(this.mContext.getUserId());
        if (this.mDirectServiceInterface != null) {
            printWriter.print(str);
            printWriter.print("mDirectServiceInterface: ");
            printWriter.println(this.mDirectServiceInterface);
        }
        printWriter.print(str);
        printWriter.print("mDisabled: ");
        printWriter.println(this.mDisabled.get());
        printWriter.print(str);
        printWriter.print("isEnabled(): ");
        printWriter.println(isContentCaptureEnabled());
        printWriter.print(str);
        printWriter.print("state: ");
        printWriter.println(getStateAsString(this.mState));
        if (this.mApplicationToken != null) {
            printWriter.print(str);
            printWriter.print("app token: ");
            printWriter.println(this.mApplicationToken);
        }
        if (this.mComponentName != null) {
            printWriter.print(str);
            printWriter.print("component name: ");
            printWriter.println(this.mComponentName.flattenToShortString());
        }
        ArrayList<ContentCaptureEvent> arrayList = this.mEvents;
        if (arrayList != null && !arrayList.isEmpty()) {
            int size = this.mEvents.size();
            printWriter.print(str);
            printWriter.print("buffered events: ");
            printWriter.print(size);
            printWriter.print('/');
            printWriter.println(this.mManager.mOptions.maxBufferSize);
            if (ContentCaptureHelper.sVerbose && size > 0) {
                String str2 = str + "  ";
                for (int i = 0; i < size; i++) {
                    ContentCaptureEvent contentCaptureEvent = this.mEvents.get(i);
                    printWriter.print(str2);
                    printWriter.print(i);
                    printWriter.print(": ");
                    contentCaptureEvent.dump(printWriter);
                    printWriter.println();
                }
            }
            printWriter.print(str);
            printWriter.print("mNextFlushForTextChanged: ");
            printWriter.println(this.mNextFlushForTextChanged);
            printWriter.print(str);
            printWriter.print("flush frequency: ");
            if (this.mNextFlushForTextChanged) {
                printWriter.println(this.mManager.mOptions.textChangeFlushingFrequencyMs);
            } else {
                printWriter.println(this.mManager.mOptions.idleFlushingFrequencyMs);
            }
            printWriter.print(str);
            printWriter.print("next flush: ");
            TimeUtils.formatDuration(this.mNextFlush - System.currentTimeMillis(), printWriter);
            printWriter.print(" (");
            printWriter.print(TimeUtils.logTimeOfDay(this.mNextFlush));
            printWriter.println(")");
        }
        if (this.mFlushHistory != null) {
            printWriter.print(str);
            printWriter.println("flush history:");
            this.mFlushHistory.reverseDump(null, printWriter, null);
            printWriter.println();
        } else {
            printWriter.print(str);
            printWriter.println("not logging flush history");
        }
        super.dump(str, printWriter);
    }

    private String getActivityName() {
        if (this.mComponentName == null) {
            return "pkg:" + this.mContext.getPackageName();
        }
        return "act:" + this.mComponentName.flattenToShortString();
    }

    private String getDebugState() {
        return getActivityName() + " [state=" + getStateAsString(this.mState) + ", disabled=" + this.mDisabled.get() + "]";
    }

    private String getDebugState(int i) {
        return getDebugState() + ", reason=" + getFlushReasonAsString(i);
    }
}
