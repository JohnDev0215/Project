package com.android.server.telecom;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.telecom.Log;
import android.telecom.PhoneAccountHandle;
import com.android.internal.telecom.IInCallAdapter;
import com.android.internal.util.FunctionalUtils;
import com.android.server.telecom.TelecomSystem;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class InCallAdapter extends IInCallAdapter.Stub {
    private final CallIdMapper mCallIdMapper;
    private final CallsManager mCallsManager;
    private final TelecomSystem.SyncRoot mLock;
    private final String mOwnerPackageAbbreviation;
    private final String mOwnerPackageName;

    public InCallAdapter(CallsManager callsManager, CallIdMapper callIdMapper, TelecomSystem.SyncRoot syncRoot, String str) {
        this.mCallsManager = callsManager;
        this.mCallIdMapper = callIdMapper;
        this.mLock = syncRoot;
        this.mOwnerPackageName = str;
        this.mOwnerPackageAbbreviation = Log.getPackageAbbreviation(str);
    }

    public void answerCall(String str, int i) {
        try {
            Log.startSession("ICA.aC", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Log.d(this, "answerCall(%s,%d)", new Object[]{str, Integer.valueOf(i)});
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        this.mCallsManager.answerCall(call, i);
                    } else {
                        Log.w(this, "answerCall, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void deflectCall(String str, Uri uri) {
        try {
            Log.startSession("ICA.defC", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Log.i(this, "deflectCall - %s, %s ", new Object[]{str, Log.pii(uri)});
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        this.mCallsManager.deflectCall(call, uri);
                    } else {
                        Log.w(this, "deflectCall, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void rejectCall(String str, boolean z, String str2) {
        try {
            Log.startSession("ICA.rC", this.mOwnerPackageAbbreviation);
            int callingUid = Binder.getCallingUid();
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    if (!this.mCallsManager.isReplyWithSmsAllowed(callingUid)) {
                        str2 = null;
                        z = false;
                    }
                    Log.d(this, "rejectCall(%s,%b,%s)", new Object[]{str, Boolean.valueOf(z), str2});
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        this.mCallsManager.rejectCall(call, z, str2);
                    } else {
                        Log.w(this, "setRingback, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void rejectCallWithReason(String str, int i) {
        try {
            Log.startSession("ICA.rC", this.mOwnerPackageAbbreviation);
            Binder.getCallingUid();
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Log.d(this, "rejectCallWithReason(%s,%d)", new Object[]{str, Integer.valueOf(i)});
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        this.mCallsManager.rejectCall(call, i);
                    } else {
                        Log.w(this, "rejectCallWithReason, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void transferCall(String str, Uri uri, boolean z) {
        try {
            Log.startSession("ICA.tC", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Log.i(this, "transferCall - %s, %s, %b", new Object[]{str, Log.pii(uri), Boolean.valueOf(z)});
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        this.mCallsManager.transferCall(call, uri, z);
                    } else {
                        Log.w(this, "transferCall, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void consultativeTransfer(String str, String str2) {
        try {
            Log.startSession("ICA.cT", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Log.i(this, "consultativeTransfer - %s, %s", new Object[]{str, str2});
                    Call call = this.mCallIdMapper.getCall(str);
                    Call call2 = this.mCallIdMapper.getCall(str2);
                    if (call != null && call2 != null) {
                        this.mCallsManager.transferCall(call, call2);
                    } else {
                        Log.w(this, "consultativeTransfer, unknown call id: %s or %s", new Object[]{str, str2});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void playDtmfTone(String str, char c) {
        try {
            Log.startSession("ICA.pDT", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Log.d(this, "playDtmfTone(%s,%c)", new Object[]{str, Character.valueOf(c)});
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        this.mCallsManager.playDtmfTone(call, c);
                    } else {
                        Log.w(this, "playDtmfTone, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void stopDtmfTone(String str) {
        try {
            Log.startSession("ICA.sDT", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Log.d(this, "stopDtmfTone(%s)", new Object[]{str});
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        this.mCallsManager.stopDtmfTone(call);
                    } else {
                        Log.w(this, "stopDtmfTone, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void postDialContinue(String str, boolean z) {
        try {
            Log.startSession("ICA.pDC", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Log.d(this, "postDialContinue(%s)", new Object[]{str});
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        this.mCallsManager.postDialContinue(call, z);
                    } else {
                        Log.w(this, "postDialContinue, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void disconnectCall(String str) {
        try {
            Log.startSession("ICA.dC", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Log.v(this, "disconnectCall: %s", new Object[]{str});
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        this.mCallsManager.lambda$disconnectOtherCalls$17(call);
                    } else {
                        Log.w(this, "disconnectCall, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void holdCall(String str) {
        try {
            Log.startSession("ICA.hC", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        this.mCallsManager.holdCall(call);
                    } else {
                        Log.w(this, "holdCall, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void unholdCall(String str) {
        try {
            Log.startSession("ICA.uC", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        this.mCallsManager.unholdCall(call);
                    } else {
                        Log.w(this, "unholdCall, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void phoneAccountSelected(String str, PhoneAccountHandle phoneAccountHandle, boolean z) {
        try {
            Log.startSession("ICA.pAS", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        this.mCallsManager.phoneAccountSelected(call, phoneAccountHandle, z);
                    } else {
                        Log.w(this, "phoneAccountSelected, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void mute(boolean z) {
        try {
            Log.startSession("ICA.m", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    this.mCallsManager.mute(z);
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void setAudioRoute(int i, String str) {
        try {
            Log.startSession("ICA.sAR", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    this.mCallsManager.setAudioRoute(i, str);
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void enterBackgroundAudioProcessing(final String str) {
        try {
            Log.startSession("ICA.enBAP", this.mOwnerPackageAbbreviation);
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: com.android.server.telecom.-$$Lambda$InCallAdapter$bCHhZ2uge4YbQ36fxtyR3gnrZ_s
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$enterBackgroundAudioProcessing$0$InCallAdapter(str);
                }
            });
        } finally {
            Log.endSession();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$enterBackgroundAudioProcessing$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$enterBackgroundAudioProcessing$0$InCallAdapter(String str) throws Exception {
        synchronized (this.mLock) {
            Call call = this.mCallIdMapper.getCall(str);
            if (call != null) {
                this.mCallsManager.enterBackgroundAudioProcessing(call, this.mOwnerPackageName);
            } else {
                Log.w(this, "enterBackgroundAudioProcessing, unknown call id: %s", new Object[]{str});
            }
        }
    }

    public void exitBackgroundAudioProcessing(final String str, final boolean z) {
        try {
            Log.startSession("ICA.exBAP", this.mOwnerPackageAbbreviation);
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: com.android.server.telecom.-$$Lambda$InCallAdapter$jq--Ft3WPUukTIwa59HXRoRans4
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$exitBackgroundAudioProcessing$1$InCallAdapter(str, z);
                }
            });
        } finally {
            Log.endSession();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$exitBackgroundAudioProcessing$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$exitBackgroundAudioProcessing$1$InCallAdapter(String str, boolean z) throws Exception {
        synchronized (this.mLock) {
            Call call = this.mCallIdMapper.getCall(str);
            if (call != null) {
                this.mCallsManager.exitBackgroundAudioProcessing(call, z);
            } else {
                Log.w(this, "exitBackgroundAudioProcessing, unknown call id: %s", new Object[]{str});
            }
        }
    }

    public void conference(String str, String str2) {
        try {
            Log.startSession("ICA.c", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    Call call2 = this.mCallIdMapper.getCall(str2);
                    if (call != null && call2 != null) {
                        this.mCallsManager.conference(call, call2);
                    } else {
                        Log.w(this, "conference, unknown call id: %s or %s", new Object[]{str, str2});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void splitFromConference(String str) {
        try {
            Log.startSession("ICA.sFC", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.splitFromConference();
                    } else {
                        Log.w(this, "splitFromConference, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void mergeConference(String str) {
        try {
            Log.startSession("ICA.mC", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.mergeConference();
                    } else {
                        Log.w(this, "mergeConference, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void swapConference(String str) {
        try {
            Log.startSession("ICA.sC", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.swapConference();
                    } else {
                        Log.w(this, "swapConference, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void addConferenceParticipants(String str, List<Uri> list) {
        try {
            Log.startSession("ICA.aCP", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.addConferenceParticipants(list);
                    } else {
                        Log.w(this, "addConferenceParticipants, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void pullExternalCall(String str) {
        try {
            Log.startSession("ICA.pEC", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.pullExternalCall();
                    } else {
                        Log.w(this, "pullExternalCall, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void sendCallEvent(String str, String str2, int i, Bundle bundle) {
        try {
            Log.startSession("ICA.sCE", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.sendCallEvent(str2, i, bundle);
                    } else {
                        Log.w(this, "sendCallEvent, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void putExtras(String str, Bundle bundle) {
        try {
            Log.startSession("ICA.pE", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.putExtras(2, bundle);
                    } else {
                        Log.w(this, "putExtras, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void removeExtras(String str, List<String> list) {
        try {
            Log.startSession("ICA.rE", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.removeExtras(2, list);
                    } else {
                        Log.w(this, "removeExtra, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void turnOnProximitySensor() {
        try {
            Log.startSession("ICA.tOnPS", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    this.mCallsManager.turnOnProximitySensor();
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void turnOffProximitySensor(boolean z) {
        try {
            Log.startSession("ICA.tOffPS", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    this.mCallsManager.turnOffProximitySensor(z);
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void sendRttRequest(String str) {
        try {
            Log.startSession("ICA.sRR");
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.sendRttRequest();
                    } else {
                        Log.w(this, "stopRtt(): call %s not found", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void respondToRttRequest(String str, int i, boolean z) {
        try {
            Log.startSession("ICA.rTRR");
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.handleRttRequestResponse(i, z);
                    } else {
                        Log.w(this, "respondToRttRequest(): call %s not found", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void stopRtt(String str) {
        try {
            Log.startSession("ICA.sRTT");
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.stopRtt();
                    } else {
                        Log.w(this, "stopRtt(): call %s not found", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void setRttMode(String str, int i) {
        try {
            Log.startSession("ICA.sRM");
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }

    public void handoverTo(String str, PhoneAccountHandle phoneAccountHandle, int i, Bundle bundle) {
        try {
            Log.startSession("ICA.hT", this.mOwnerPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (this.mLock) {
                    Call call = this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.handoverTo(phoneAccountHandle, i, bundle);
                    } else {
                        Log.w(this, "handoverTo, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Log.endSession();
        }
    }
}
