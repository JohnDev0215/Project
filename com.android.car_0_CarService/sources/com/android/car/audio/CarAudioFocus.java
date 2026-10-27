package com.android.car.audio;

import android.content.pm.PackageManager;
import android.media.AudioFocusInfo;
import android.media.AudioManager;
import android.media.audiopolicy.AudioPolicy;
import android.util.LocalLog;
import android.util.Log;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class CarAudioFocus extends AudioPolicy.AudioPolicyFocusListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FOCUS_EVENT_LOGGER_QUEUE_SIZE = 25;
    private static final String TAG = "CarAudioFocus";
    private final AudioManager mAudioManager;
    private AudioPolicy mAudioPolicy;
    private AudioFocusInfo mDelayedRequest;
    private final boolean mEnabledDelayedFocusRequest;
    private final FocusInteraction mFocusInteraction;
    private final PackageManager mPackageManager;
    private final HashMap<String, FocusEntry> mFocusHolders = new HashMap<>();
    private final HashMap<String, FocusEntry> mFocusLosers = new HashMap<>();
    private final Object mLock = new Object();
    private final LocalLog mFocusEventLogger = new LocalLog(25);

    CarAudioFocus(AudioManager audioManager, PackageManager packageManager, FocusInteraction focusInteraction, boolean enableDelayedFocusRequest) {
        this.mAudioManager = audioManager;
        this.mPackageManager = packageManager;
        this.mFocusInteraction = focusInteraction;
        this.mEnabledDelayedFocusRequest = enableDelayedFocusRequest;
    }

    public void setOwningPolicy(AudioPolicy parentPolicy) {
        this.mAudioPolicy = parentPolicy;
    }

    private void sendFocusLossLocked(AudioFocusInfo loser, int lossType) {
        int result = this.mAudioManager.dispatchAudioFocusChange(loser, lossType, this.mAudioPolicy);
        if (result != 1) {
            Log.e(TAG, "Failure to signal loss of audio focus with error: " + result);
        }
        logFocusEvent("sendFocusLoss for client " + loser.getClientId() + " with loss type " + focusEventToString(lossType) + " resulted in " + focusRequestResponseToString(result));
    }

    private int evaluateFocusRequestLocked(AudioFocusInfo afi) {
        int lossType;
        Log.i(TAG, "Evaluating " + focusEventToString(afi.getGainRequest()) + " request for client " + afi.getClientId() + " with usage " + afi.getAttributes().usageToString());
        boolean permanent = afi.getGainRequest() == 1;
        int i = 3;
        boolean allowDucking = afi.getGainRequest() == 3;
        int requestedContext = CarAudioContext.getContextForUsage(afi.getAttributes().getSystemUsage());
        boolean allowDelayedFocus = this.mEnabledDelayedFocusRequest && canReceiveDelayedFocus(afi);
        if (this.mDelayedRequest != null && afi.getClientId().equals(this.mDelayedRequest.getClientId())) {
            int delayedRequestedContext = CarAudioContext.getContextForUsage(this.mDelayedRequest.getAttributes().getSystemUsage());
            if (delayedRequestedContext != requestedContext) {
                Log.e(TAG, String.format("Client %s has already delayed requested focus for %s - cannot request focus for %s on same listener.", this.mDelayedRequest.getClientId(), this.mDelayedRequest.getAttributes().usageToString(), afi.getAttributes().usageToString()));
                return 0;
            }
        }
        Log.i(TAG, "Scanning focus holders...");
        ArrayList<FocusEntry> losers = new ArrayList<>();
        Iterator<FocusEntry> it = this.mFocusHolders.values().iterator();
        boolean delayFocusForCurrentRequest = false;
        FocusEntry replacedCurrentEntry = null;
        while (true) {
            boolean delayFocusForCurrentRequest2 = it.hasNext();
            int requestedContext2 = 4;
            int i2 = 7;
            if (delayFocusForCurrentRequest2) {
                FocusEntry entry = it.next();
                Log.d(TAG, "Evaluating focus holder: " + entry.getClientId());
                if (requestedContext == 7 && entry.getAudioFocusInfo().getGainRequest() == 4) {
                    return 0;
                }
                if (afi.getClientId().equals(entry.getAudioFocusInfo().getClientId())) {
                    if (entry.getAudioContext() == requestedContext) {
                        Log.i(TAG, "Replacing accepted request from same client");
                        replacedCurrentEntry = entry;
                    } else {
                        Log.e(TAG, String.format("Client %s has already requested focus for %s - cannot request focus for %s on same listener.", entry.getClientId(), entry.getAudioFocusInfo().getAttributes().usageToString(), afi.getAttributes().usageToString()));
                        return 0;
                    }
                } else {
                    FocusEntry replacedCurrentEntry2 = replacedCurrentEntry;
                    int interactionResult = this.mFocusInteraction.evaluateRequest(requestedContext, entry, losers, allowDucking, allowDelayedFocus);
                    if (interactionResult == 0) {
                        return interactionResult;
                    }
                    if (interactionResult == 2) {
                        delayFocusForCurrentRequest = true;
                    }
                    replacedCurrentEntry = replacedCurrentEntry2;
                }
            } else {
                FocusEntry replacedCurrentEntry3 = replacedCurrentEntry;
                Log.i(TAG, "Scanning those who've already lost focus...");
                ArrayList<FocusEntry> blocked = new ArrayList<>();
                FocusEntry replacedBlockedEntry = null;
                boolean delayFocusForCurrentRequest3 = delayFocusForCurrentRequest;
                for (FocusEntry entry2 : this.mFocusLosers.values()) {
                    Log.i(TAG, entry2.getAudioFocusInfo().getClientId());
                    if (requestedContext == i2 && entry2.getAudioFocusInfo().getGainRequest() == requestedContext2) {
                        return 0;
                    }
                    if (!afi.getClientId().equals(entry2.getAudioFocusInfo().getClientId())) {
                        int requestedContext3 = requestedContext;
                        int interactionResult2 = this.mFocusInteraction.evaluateRequest(requestedContext, entry2, blocked, allowDucking, allowDelayedFocus);
                        if (interactionResult2 == 0) {
                            return interactionResult2;
                        }
                        if (interactionResult2 == 2) {
                            delayFocusForCurrentRequest3 = true;
                        }
                        requestedContext = requestedContext3;
                        i = 3;
                        requestedContext2 = 4;
                        i2 = 7;
                    } else if (entry2.getAudioContext() == requestedContext) {
                        Log.i(TAG, "Replacing pending request from same client");
                        replacedBlockedEntry = entry2;
                        requestedContext2 = 4;
                    } else {
                        Object[] objArr = new Object[i];
                        objArr[0] = entry2.getClientId();
                        objArr[1] = entry2.getAudioFocusInfo().getAttributes().usageToString();
                        objArr[2] = afi.getAttributes().usageToString();
                        Log.e(TAG, String.format("Client %s has already requested focus for %s - cannot request focus for %s on same listener.", objArr));
                        return 0;
                    }
                }
                FocusEntry newEntry = new FocusEntry(afi, requestedContext, this.mPackageManager);
                ArrayList<FocusEntry> permanentlyLost = new ArrayList<>();
                if (replacedCurrentEntry3 != null) {
                    this.mFocusHolders.remove(replacedCurrentEntry3.getClientId());
                    permanentlyLost.add(replacedCurrentEntry3);
                }
                if (replacedBlockedEntry != null) {
                    this.mFocusLosers.remove(replacedBlockedEntry.getClientId());
                    permanentlyLost.add(replacedBlockedEntry);
                }
                for (FocusEntry entry3 : blocked) {
                    if (permanent) {
                        sendFocusLossLocked(entry3.getAudioFocusInfo(), -1);
                        entry3.setDucked(false);
                        this.mFocusLosers.remove(entry3.getAudioFocusInfo().getClientId());
                        permanentlyLost.add(entry3);
                    } else {
                        if (!allowDucking && entry3.isDucked()) {
                            Log.i(TAG, "Converting duckable loss to non-duckable for " + entry3.getClientId());
                            sendFocusLossLocked(entry3.getAudioFocusInfo(), -2);
                            entry3.setDucked(false);
                        }
                        entry3.addBlocker(newEntry);
                    }
                }
                for (FocusEntry entry4 : losers) {
                    if (permanent) {
                        lossType = -1;
                    } else if (allowDucking && entry4.receivesDuckEvents()) {
                        lossType = -3;
                        entry4.setDucked(true);
                    } else {
                        lossType = -2;
                    }
                    sendFocusLossLocked(entry4.getAudioFocusInfo(), lossType);
                    FocusEntry replacedCurrentEntry4 = replacedCurrentEntry3;
                    this.mFocusHolders.remove(entry4.getAudioFocusInfo().getClientId());
                    if (permanent) {
                        permanentlyLost.add(entry4);
                    } else {
                        this.mFocusLosers.put(entry4.getAudioFocusInfo().getClientId(), entry4);
                        entry4.addBlocker(newEntry);
                    }
                    replacedCurrentEntry3 = replacedCurrentEntry4;
                }
                for (FocusEntry entry5 : permanentlyLost) {
                    Log.d(TAG, "Cleaning up entry " + entry5.getClientId());
                    removeBlockerAndRestoreUnblockedWaitersLocked(entry5);
                }
                if (delayFocusForCurrentRequest3) {
                    swapDelayedAudioFocusRequestLocked(afi);
                    return 2;
                }
                this.mFocusHolders.put(afi.getClientId(), newEntry);
                Log.i(TAG, "AUDIOFOCUS_REQUEST_GRANTED");
                return 1;
            }
        }
    }

    public void onAudioFocusRequest(AudioFocusInfo afi, int requestResult) {
        AudioPolicy policy;
        int response;
        synchronized (this.mLock) {
            policy = this.mAudioPolicy;
            response = evaluateFocusRequestLocked(afi);
        }
        this.mAudioManager.setFocusRequestResult(afi, response, policy);
        logFocusEvent("onAudioFocusRequest for client " + afi.getClientId() + " with gain type " + focusEventToString(afi.getGainRequest()) + " resulted in " + focusRequestResponseToString(response));
    }

    private void swapDelayedAudioFocusRequestLocked(AudioFocusInfo afi) {
        if (this.mDelayedRequest != null && !afi.getClientId().equals(this.mDelayedRequest.getClientId())) {
            sendFocusLossLocked(this.mDelayedRequest, -1);
        }
        this.mDelayedRequest = afi;
    }

    private boolean canReceiveDelayedFocus(AudioFocusInfo afi) {
        return afi.getGainRequest() == 1 && (afi.getFlags() & 1) == 1;
    }

    public void onAudioFocusAbandon(AudioFocusInfo afi) {
        logFocusEvent("onAudioFocusAbandon for client " + afi.getClientId());
        synchronized (this.mLock) {
            FocusEntry deadEntry = removeFocusEntryLocked(afi);
            if (deadEntry != null) {
                removeBlockerAndRestoreUnblockedWaitersLocked(deadEntry);
            } else {
                removeDelayedAudioFocusRequestLocked(afi);
            }
        }
    }

    private void removeDelayedAudioFocusRequestLocked(AudioFocusInfo afi) {
        if (this.mDelayedRequest != null && afi.getClientId().equals(this.mDelayedRequest.getClientId())) {
            this.mDelayedRequest = null;
        }
    }

    private FocusEntry removeFocusEntryLocked(AudioFocusInfo afi) {
        Log.i(TAG, "removeFocusEntry " + afi.getClientId());
        FocusEntry deadEntry = this.mFocusHolders.remove(afi.getClientId());
        if (deadEntry == null && (deadEntry = this.mFocusLosers.remove(afi.getClientId())) == null) {
            Log.w(TAG, "Audio focus abandoned by unrecognized client id: " + afi.getClientId());
        }
        return deadEntry;
    }

    private void removeBlockerAndRestoreUnblockedWaitersLocked(FocusEntry deadEntry) {
        attemptToGainFocusForDelayedAudioFocusRequest();
        removeBlockerAndRestoreUnblockedFocusLosersLocked(deadEntry);
    }

    private void attemptToGainFocusForDelayedAudioFocusRequest() {
        AudioFocusInfo audioFocusInfo;
        if (!this.mEnabledDelayedFocusRequest || (audioFocusInfo = this.mDelayedRequest) == null) {
            return;
        }
        int delayedFocusRequestResults = evaluateFocusRequestLocked(audioFocusInfo);
        if (delayedFocusRequestResults == 1) {
            FocusEntry focusEntry = this.mFocusHolders.get(this.mDelayedRequest.getClientId());
            this.mDelayedRequest = null;
            if (dispatchFocusGainedLocked(focusEntry.getAudioFocusInfo()) == 0) {
                Log.e(TAG, "Failure to signal gain of audio focus gain for delayed focus clientId " + focusEntry.getClientId());
                this.mFocusHolders.remove(focusEntry.getClientId());
                removeBlockerFromBlockedFocusLosersLocked(focusEntry);
                sendFocusLossLocked(focusEntry.getAudioFocusInfo(), -1);
                logFocusEvent("Did not gained delayed audio focus for " + focusEntry.getClientId());
            }
        }
    }

    private void removeBlockerFromBlockedFocusLosersLocked(FocusEntry deadEntry) {
        for (FocusEntry entry : this.mFocusLosers.values()) {
            entry.removeBlocker(deadEntry);
        }
    }

    private void removeBlockerAndRestoreUnblockedFocusLosersLocked(FocusEntry deadEntry) {
        Iterator<FocusEntry> it = this.mFocusLosers.values().iterator();
        while (it.hasNext()) {
            FocusEntry entry = it.next();
            entry.removeBlocker(deadEntry);
            if (entry.isUnblocked()) {
                Log.i(TAG, "Restoring unblocked entry " + entry.getClientId());
                it.remove();
                this.mFocusHolders.put(entry.getClientId(), entry);
                dispatchFocusGainedLocked(entry.getAudioFocusInfo());
            }
        }
    }

    private int dispatchFocusGainedLocked(AudioFocusInfo afi) {
        int result = this.mAudioManager.dispatchAudioFocusChange(afi, afi.getGainRequest(), this.mAudioPolicy);
        if (result != 1) {
            Log.e(TAG, "Failure to signal gain of audio focus with error: " + result);
        }
        logFocusEvent("dispatchFocusGainedLocked for client " + afi.getClientId() + " with gain type " + focusEventToString(afi.getGainRequest()) + " resulted in " + focusRequestResponseToString(result));
        return result;
    }

    ArrayList<AudioFocusInfo> getAudioFocusLosersForUid(int uid) {
        return getAudioFocusListForUid(uid, this.mFocusLosers);
    }

    ArrayList<AudioFocusInfo> getAudioFocusHoldersForUid(int uid) {
        return getAudioFocusListForUid(uid, this.mFocusHolders);
    }

    private ArrayList<AudioFocusInfo> getAudioFocusListForUid(int uid, HashMap<String, FocusEntry> mapToQuery) {
        ArrayList<AudioFocusInfo> matchingInfoList = new ArrayList<>();
        synchronized (this.mLock) {
            for (String clientId : mapToQuery.keySet()) {
                AudioFocusInfo afi = mapToQuery.get(clientId).getAudioFocusInfo();
                if (afi.getClientUid() == uid) {
                    matchingInfoList.add(afi);
                }
            }
        }
        return matchingInfoList;
    }

    void removeAudioFocusInfoAndTransientlyLoseFocus(AudioFocusInfo afi) {
        synchronized (this.mLock) {
            FocusEntry deadEntry = removeFocusEntryLocked(afi);
            if (deadEntry != null) {
                sendFocusLossLocked(deadEntry.getAudioFocusInfo(), -2);
                removeBlockerAndRestoreUnblockedWaitersLocked(deadEntry);
            }
        }
    }

    int reevaluateAndRegainAudioFocus(AudioFocusInfo afi) {
        int results;
        synchronized (this.mLock) {
            results = evaluateFocusRequestLocked(afi);
            if (results == 1) {
                results = dispatchFocusGainedLocked(afi);
            }
        }
        return results;
    }

    public void dump(String indent, PrintWriter writer) {
        synchronized (this.mLock) {
            writer.printf("%s*CarAudioFocus*\n", indent);
            String innerIndent = indent + "\t";
            String focusIndent = innerIndent + "\t";
            this.mFocusInteraction.dump(innerIndent, writer);
            writer.printf("%sCurrent Focus Holders:\n", innerIndent);
            for (String clientId : this.mFocusHolders.keySet()) {
                this.mFocusHolders.get(clientId).dump(focusIndent, writer);
            }
            writer.printf("%sTransient Focus Losers:\n", innerIndent);
            for (String clientId2 : this.mFocusLosers.keySet()) {
                this.mFocusLosers.get(clientId2).dump(focusIndent, writer);
            }
            Object[] objArr = new Object[2];
            objArr[0] = innerIndent;
            objArr[1] = this.mDelayedRequest == null ? "None" : this.mDelayedRequest.getClientId();
            writer.printf("%sQueued Delayed Focus: %s\n", objArr);
            writer.printf("%sFocus Events:\n", innerIndent);
            this.mFocusEventLogger.dump(innerIndent + "\t", writer);
        }
    }

    private static String focusEventToString(int focusEvent) {
        if (focusEvent == -3) {
            return "LOSS_TRANSIENT_CAN_DUCK";
        }
        if (focusEvent == -2) {
            return "LOSS_TRANSIENT";
        }
        if (focusEvent == -1) {
            return "LOSS";
        }
        if (focusEvent == 1) {
            return "GAIN";
        }
        if (focusEvent == 2) {
            return "GAIN_TRANSIENT";
        }
        if (focusEvent == 3) {
            return "GAIN_TRANSIENT_MAY_DUCK";
        }
        if (focusEvent == 4) {
            return "GAIN_TRANSIENT_EXCLUSIVE";
        }
        return "unknown event " + focusEvent;
    }

    private static String focusRequestResponseToString(int response) {
        if (response == 1) {
            return "REQUEST_GRANTED";
        }
        if (response == 0) {
            return "REQUEST_FAILED";
        }
        return "REQUEST_DELAYED";
    }

    private void logFocusEvent(String log) {
        this.mFocusEventLogger.log(log);
        Log.i(TAG, log);
    }

    public FocusInteraction getFocusInteraction() {
        return this.mFocusInteraction;
    }
}
