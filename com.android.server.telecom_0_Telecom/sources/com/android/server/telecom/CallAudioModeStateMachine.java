package com.android.server.telecom;

import android.media.AudioManager;
import android.os.Looper;
import android.os.Message;
import android.telecom.Log;
import android.telecom.Logging.Runnable;
import android.telecom.Logging.Session;
import android.util.SparseArray;
import com.android.internal.util.IndentingPrintWriter;
import com.android.internal.util.State;
import com.android.internal.util.StateMachine;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class CallAudioModeStateMachine extends StateMachine {
    private final AudioManager mAudioManager;
    private final BaseState mAudioProcessingFocusState;
    private CallAudioManager mCallAudioManager;
    private boolean mIsInitialized;
    private int mMostRecentMode;
    private final BaseState mOtherFocusState;
    private final BaseState mRingingFocusState;
    private final BaseState mSimCallFocusState;
    private final SystemStateHelper mSystemStateHelper;
    private final BaseState mUnfocusedState;
    private final BaseState mVoipCallFocusState;
    private static final SparseArray<String> MESSAGE_CODE_TO_NAME = new SparseArray<String>() { // from class: com.android.server.telecom.CallAudioModeStateMachine.1
        {
            put(2, "ENTER_CALL_FOCUS_FOR_TESTING");
            put(3, "ENTER_COMMS_FOCUS_FOR_TESTING");
            put(4, "ENTER_RING_FOCUS_FOR_TESTING");
            put(6, "ENTER_AUDIO_PROCESSING_FOCUS_FOR_TESTING");
            put(5, "ENTER_TONE_OR_HOLD_FOCUS_FOR_TESTING");
            put(7, "ABANDON_FOCUS_FOR_TESTING");
            put(1001, "NO_MORE_ACTIVE_OR_DIALING_CALLS");
            put(1002, "NO_MORE_RINGING_CALLS");
            put(1003, "NO_MORE_HOLDING_CALLS");
            put(1004, "NO_MORE_AUDIO_PROCESSING_CALLS");
            put(2001, "NEW_ACTIVE_OR_DIALING_CALL");
            put(2002, "NEW_RINGING_CALL");
            put(2003, "NEW_HOLDING_CALL");
            put(2004, "NEW_AUDIO_PROCESSING_CALL");
            put(3001, "TONE_STARTED_PLAYING");
            put(3002, "TONE_STOPPED_PLAYING");
            put(4001, "FOREGROUND_VOIP_MODE_CHANGE");
            put(5001, "RINGER_MODE_CHANGE");
            put(6001, "AUDIO_OPERATIONS_COMPLETE");
            put(9001, "RUN_RUNNABLE");
        }
    };
    private static final String LOG_TAG = CallAudioModeStateMachine.class.getSimpleName();

    public static class Factory {
        public CallAudioModeStateMachine create(SystemStateHelper systemStateHelper, AudioManager audioManager) {
            return new CallAudioModeStateMachine(systemStateHelper, audioManager);
        }
    }

    public static class MessageArgs {
        public boolean foregroundCallIsVoip;
        public boolean hasActiveOrDialingCalls;
        public boolean hasAudioProcessingCalls;
        public boolean hasHoldingCalls;
        public boolean hasRingingCalls;
        public boolean isTonePlaying;
        public Session session;

        private MessageArgs(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, Session session) {
            this.hasActiveOrDialingCalls = z;
            this.hasRingingCalls = z2;
            this.hasHoldingCalls = z3;
            this.hasAudioProcessingCalls = z4;
            this.isTonePlaying = z5;
            this.foregroundCallIsVoip = z6;
            this.session = session;
        }

        public String toString() {
            return "MessageArgs{hasActiveCalls=" + this.hasActiveOrDialingCalls + ", hasRingingCalls=" + this.hasRingingCalls + ", hasHoldingCalls=" + this.hasHoldingCalls + ", hasAudioProcessingCalls=" + this.hasAudioProcessingCalls + ", isTonePlaying=" + this.isTonePlaying + ", foregroundCallIsVoip=" + this.foregroundCallIsVoip + ", session=" + this.session + '}';
        }

        public static class Builder {
            private boolean mForegroundCallIsVoip;
            private boolean mHasActiveOrDialingCalls;
            private boolean mHasAudioProcessingCalls;
            private boolean mHasHoldingCalls;
            private boolean mHasRingingCalls;
            private boolean mIsTonePlaying;
            private Session mSession;

            public Builder setHasActiveOrDialingCalls(boolean z) {
                this.mHasActiveOrDialingCalls = z;
                return this;
            }

            public Builder setHasRingingCalls(boolean z) {
                this.mHasRingingCalls = z;
                return this;
            }

            public Builder setHasHoldingCalls(boolean z) {
                this.mHasHoldingCalls = z;
                return this;
            }

            public Builder setHasAudioProcessingCalls(boolean z) {
                this.mHasAudioProcessingCalls = z;
                return this;
            }

            public Builder setIsTonePlaying(boolean z) {
                this.mIsTonePlaying = z;
                return this;
            }

            public Builder setForegroundCallIsVoip(boolean z) {
                this.mForegroundCallIsVoip = z;
                return this;
            }

            public Builder setSession(Session session) {
                this.mSession = session;
                return this;
            }

            public MessageArgs build() {
                return new MessageArgs(this.mHasActiveOrDialingCalls, this.mHasRingingCalls, this.mHasHoldingCalls, this.mHasAudioProcessingCalls, this.mIsTonePlaying, this.mForegroundCallIsVoip, this.mSession);
            }
        }
    }

    private class BaseState extends State {
        private BaseState() {
        }

        public boolean processMessage(Message message) {
            int i = message.what;
            if (i != 9001) {
                switch (i) {
                    case 1:
                        CallAudioModeStateMachine.this.mIsInitialized = true;
                        break;
                    case HeadsetMediaButton.LONG_PRESS /* 2 */:
                        CallAudioModeStateMachine callAudioModeStateMachine = CallAudioModeStateMachine.this;
                        callAudioModeStateMachine.transitionTo(callAudioModeStateMachine.mSimCallFocusState);
                        break;
                    case 3:
                        CallAudioModeStateMachine callAudioModeStateMachine2 = CallAudioModeStateMachine.this;
                        callAudioModeStateMachine2.transitionTo(callAudioModeStateMachine2.mVoipCallFocusState);
                        break;
                    case 4:
                        CallAudioModeStateMachine callAudioModeStateMachine3 = CallAudioModeStateMachine.this;
                        callAudioModeStateMachine3.transitionTo(callAudioModeStateMachine3.mRingingFocusState);
                        break;
                    case 5:
                        CallAudioModeStateMachine callAudioModeStateMachine4 = CallAudioModeStateMachine.this;
                        callAudioModeStateMachine4.transitionTo(callAudioModeStateMachine4.mOtherFocusState);
                        break;
                    case 6:
                        CallAudioModeStateMachine callAudioModeStateMachine5 = CallAudioModeStateMachine.this;
                        callAudioModeStateMachine5.transitionTo(callAudioModeStateMachine5.mAudioProcessingFocusState);
                        break;
                    case 7:
                        CallAudioModeStateMachine callAudioModeStateMachine6 = CallAudioModeStateMachine.this;
                        callAudioModeStateMachine6.transitionTo(callAudioModeStateMachine6.mUnfocusedState);
                        break;
                }
                return true;
            }
            ((Runnable) message.obj).run();
            return true;
        }
    }

    private class UnfocusedState extends BaseState {
        private UnfocusedState() {
            super();
        }

        public void enter() {
            if (CallAudioModeStateMachine.this.mIsInitialized) {
                CallAudioModeStateMachine.this.mCallAudioManager.setCallAudioRouteFocusState(1);
                CallAudioModeStateMachine.this.mAudioManager.setMode(0);
                CallAudioModeStateMachine.this.mMostRecentMode = 0;
            }
        }

        @Override // com.android.server.telecom.CallAudioModeStateMachine.BaseState
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            MessageArgs messageArgs = (MessageArgs) message.obj;
            int i = message.what;
            if (i == 3001) {
                Log.w(CallAudioModeStateMachine.LOG_TAG, "Tone started playing unexpectedly. Args are: \n" + messageArgs.toString(), new Object[0]);
                return true;
            }
            if (i == 6001) {
                Log.i(CallAudioModeStateMachine.LOG_TAG, "Abandoning audio focus: now UNFOCUSED", new Object[0]);
                CallAudioModeStateMachine.this.mAudioManager.abandonAudioFocusForCall();
                return true;
            }
            switch (i) {
                default:
                    switch (i) {
                        case 2001:
                            CallAudioModeStateMachine callAudioModeStateMachine = CallAudioModeStateMachine.this;
                            callAudioModeStateMachine.transitionTo(messageArgs.foregroundCallIsVoip ? callAudioModeStateMachine.mVoipCallFocusState : callAudioModeStateMachine.mSimCallFocusState);
                            break;
                        case 2002:
                            CallAudioModeStateMachine callAudioModeStateMachine2 = CallAudioModeStateMachine.this;
                            callAudioModeStateMachine2.transitionTo(callAudioModeStateMachine2.mRingingFocusState);
                            return true;
                        case 2003:
                            Log.w(CallAudioModeStateMachine.LOG_TAG, "Call was surprisingly put into hold from an unknown state. Args are: \n" + messageArgs.toString(), new Object[0]);
                            CallAudioModeStateMachine callAudioModeStateMachine3 = CallAudioModeStateMachine.this;
                            callAudioModeStateMachine3.transitionTo(callAudioModeStateMachine3.mOtherFocusState);
                            return true;
                        case 2004:
                            CallAudioModeStateMachine callAudioModeStateMachine4 = CallAudioModeStateMachine.this;
                            callAudioModeStateMachine4.transitionTo(callAudioModeStateMachine4.mAudioProcessingFocusState);
                            return true;
                        default:
                            return false;
                    }
                case 1001:
                case 1002:
                case 1003:
                case 1004:
                    return true;
            }
        }
    }

    private class AudioProcessingFocusState extends BaseState {
        private AudioProcessingFocusState() {
            super();
        }

        public void enter() {
            if (CallAudioModeStateMachine.this.mIsInitialized) {
                CallAudioModeStateMachine.this.mCallAudioManager.setCallAudioRouteFocusState(1);
                CallAudioModeStateMachine.this.mAudioManager.setMode(4);
                CallAudioModeStateMachine.this.mMostRecentMode = 4;
            }
        }

        @Override // com.android.server.telecom.CallAudioModeStateMachine.BaseState
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            MessageArgs messageArgs = (MessageArgs) message.obj;
            int i = message.what;
            if (i == 3001) {
                Log.w(CallAudioModeStateMachine.LOG_TAG, "Tone started playing unexpectedly. Args are: \n" + messageArgs.toString(), new Object[0]);
                return true;
            }
            if (i != 6001) {
                switch (i) {
                    case 1004:
                        BaseState baseStateCalculateProperStateFromArgs = CallAudioModeStateMachine.this.calculateProperStateFromArgs(messageArgs);
                        if (baseStateCalculateProperStateFromArgs == this) {
                            Log.w(CallAudioModeStateMachine.LOG_TAG, "Got spurious NO_MORE_AUDIO_PROCESSING_CALLS", new Object[0]);
                        }
                        CallAudioModeStateMachine.this.transitionTo(baseStateCalculateProperStateFromArgs);
                    case 1001:
                    case 1002:
                    case 1003:
                        return true;
                    default:
                        switch (i) {
                            case 2001:
                                CallAudioModeStateMachine callAudioModeStateMachine = CallAudioModeStateMachine.this;
                                callAudioModeStateMachine.transitionTo(messageArgs.foregroundCallIsVoip ? callAudioModeStateMachine.mVoipCallFocusState : callAudioModeStateMachine.mSimCallFocusState);
                                return true;
                            case 2002:
                                CallAudioModeStateMachine callAudioModeStateMachine2 = CallAudioModeStateMachine.this;
                                callAudioModeStateMachine2.transitionTo(callAudioModeStateMachine2.mRingingFocusState);
                                return true;
                            case 2003:
                                Log.w(CallAudioModeStateMachine.LOG_TAG, "Call was surprisingly put into hold from an unknown state. Args are: \n" + messageArgs.toString(), new Object[0]);
                                CallAudioModeStateMachine callAudioModeStateMachine3 = CallAudioModeStateMachine.this;
                                callAudioModeStateMachine3.transitionTo(callAudioModeStateMachine3.mOtherFocusState);
                                return true;
                            case 2004:
                                return true;
                            default:
                                return false;
                        }
                }
            } else {
                Log.i(CallAudioModeStateMachine.LOG_TAG, "Abandoning audio focus: now AUDIO_PROCESSING", new Object[0]);
                CallAudioModeStateMachine.this.mAudioManager.abandonAudioFocusForCall();
                return true;
            }
        }
    }

    private class RingingFocusState extends BaseState {
        private boolean mHasFocus;

        private RingingFocusState() {
            super();
            this.mHasFocus = false;
        }

        private void tryStartRinging() {
            if (!this.mHasFocus || !CallAudioModeStateMachine.this.mCallAudioManager.isRingtonePlaying()) {
                if (CallAudioModeStateMachine.this.mCallAudioManager.startRinging()) {
                    CallAudioModeStateMachine.this.mAudioManager.requestAudioFocusForCall(2, 2);
                    if (CallAudioModeStateMachine.this.mAudioManager.getMode() != 4) {
                        CallAudioModeStateMachine.this.mAudioManager.setMode(1);
                    }
                    CallAudioModeStateMachine.this.mCallAudioManager.setCallAudioRouteFocusState(3);
                    this.mHasFocus = true;
                    return;
                }
                Log.i(CallAudioModeStateMachine.LOG_TAG, "RINGING state, try start ringing but not acquiring audio focus", new Object[0]);
                return;
            }
            Log.i(CallAudioModeStateMachine.LOG_TAG, "RingingFocusState#tryStartRinging -- audio focus previously acquired and ringtone already playing -- skipping.", new Object[0]);
        }

        public void enter() {
            Log.i(CallAudioModeStateMachine.LOG_TAG, "Audio focus entering RINGING state", new Object[0]);
            tryStartRinging();
            CallAudioModeStateMachine.this.mCallAudioManager.stopCallWaiting();
        }

        public void exit() {
            CallAudioModeStateMachine.this.mCallAudioManager.stopRinging();
            this.mHasFocus = false;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.android.server.telecom.CallAudioModeStateMachine.BaseState
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            MessageArgs messageArgs = (MessageArgs) message.obj;
            int i = message.what;
            if (i == 5001) {
                Log.i(CallAudioModeStateMachine.LOG_TAG, "RINGING state, received RINGER_MODE_CHANGE", new Object[0]);
                tryStartRinging();
                return true;
            }
            if (i != 6001) {
                switch (i) {
                    case 1002:
                        BaseState baseStateCalculateProperStateFromArgs = CallAudioModeStateMachine.this.calculateProperStateFromArgs(messageArgs);
                        if (baseStateCalculateProperStateFromArgs == this) {
                            Log.w(CallAudioModeStateMachine.LOG_TAG, "Got spurious NO_MORE_RINGING_CALLS", new Object[0]);
                        }
                        CallAudioModeStateMachine.this.transitionTo(baseStateCalculateProperStateFromArgs);
                    case 1001:
                        return true;
                    default:
                        switch (i) {
                            case 2001:
                                CallAudioModeStateMachine callAudioModeStateMachine = CallAudioModeStateMachine.this;
                                callAudioModeStateMachine.transitionTo(messageArgs.foregroundCallIsVoip ? callAudioModeStateMachine.mVoipCallFocusState : callAudioModeStateMachine.mSimCallFocusState);
                                break;
                            case 2002:
                                return true;
                            case 2003:
                                Log.w(CallAudioModeStateMachine.LOG_TAG, "Call was surprisingly put into hold while ringing. Args are: " + messageArgs.toString(), new Object[0]);
                                CallAudioModeStateMachine callAudioModeStateMachine2 = CallAudioModeStateMachine.this;
                                callAudioModeStateMachine2.transitionTo(callAudioModeStateMachine2.mOtherFocusState);
                                return true;
                            case 2004:
                                if (messageArgs.hasRingingCalls) {
                                    Log.w(CallAudioModeStateMachine.LOG_TAG, "Got a audio processing call while there's still a call ringing", new Object[0]);
                                } else {
                                    CallAudioModeStateMachine callAudioModeStateMachine3 = CallAudioModeStateMachine.this;
                                    callAudioModeStateMachine3.transitionTo(callAudioModeStateMachine3.mAudioProcessingFocusState);
                                }
                                return true;
                            default:
                                return false;
                        }
                    case 1003:
                        return true;
                }
            } else {
                Log.w(CallAudioModeStateMachine.LOG_TAG, "Should not be seeing AUDIO_OPERATIONS_COMPLETE in a focused state", new Object[0]);
                return true;
            }
        }
    }

    private class SimCallFocusState extends BaseState {
        private SimCallFocusState() {
            super();
        }

        public void enter() {
            Log.i(CallAudioModeStateMachine.LOG_TAG, "Audio focus entering SIM CALL state", new Object[0]);
            if (!CallAudioModeStateMachine.this.mCallAudioManager.isBluetoothCall()) {
                CallAudioModeStateMachine.this.mAudioManager.requestAudioFocusForCall(0, 2);
                CallAudioModeStateMachine.this.mAudioManager.setMode(2);
            } else {
                Log.i(CallAudioModeStateMachine.LOG_TAG, "is bluetooth call, don't need request audio focus", new Object[0]);
            }
            CallAudioModeStateMachine.this.mMostRecentMode = 2;
            CallAudioModeStateMachine.this.mCallAudioManager.setCallAudioRouteFocusState(2);
        }

        @Override // com.android.server.telecom.CallAudioModeStateMachine.BaseState
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            MessageArgs messageArgs = (MessageArgs) message.obj;
            int i = message.what;
            if (i != 4001) {
                if (i == 6001) {
                    Log.w(CallAudioModeStateMachine.LOG_TAG, "Should not be seeing AUDIO_OPERATIONS_COMPLETE in a focused state", new Object[0]);
                    return true;
                }
                switch (i) {
                    case 1001:
                        CallAudioModeStateMachine callAudioModeStateMachine = CallAudioModeStateMachine.this;
                        callAudioModeStateMachine.transitionTo(callAudioModeStateMachine.calculateProperStateFromArgs(messageArgs));
                        return true;
                    case 1002:
                        if (messageArgs.isTonePlaying) {
                            CallAudioModeStateMachine.this.mCallAudioManager.stopCallWaiting();
                        }
                        if (!messageArgs.hasActiveOrDialingCalls) {
                            CallAudioModeStateMachine callAudioModeStateMachine2 = CallAudioModeStateMachine.this;
                            callAudioModeStateMachine2.transitionTo(callAudioModeStateMachine2.calculateProperStateFromArgs(messageArgs));
                        }
                        return true;
                    case 1003:
                        if (messageArgs.foregroundCallIsVoip) {
                            CallAudioModeStateMachine callAudioModeStateMachine3 = CallAudioModeStateMachine.this;
                            callAudioModeStateMachine3.transitionTo(callAudioModeStateMachine3.mVoipCallFocusState);
                        }
                        return true;
                    default:
                        switch (i) {
                            case 2001:
                                if (messageArgs.foregroundCallIsVoip) {
                                    CallAudioModeStateMachine callAudioModeStateMachine4 = CallAudioModeStateMachine.this;
                                    callAudioModeStateMachine4.transitionTo(callAudioModeStateMachine4.mVoipCallFocusState);
                                }
                                return true;
                            case 2003:
                                if (messageArgs.foregroundCallIsVoip) {
                                    CallAudioModeStateMachine callAudioModeStateMachine5 = CallAudioModeStateMachine.this;
                                    callAudioModeStateMachine5.transitionTo(callAudioModeStateMachine5.mVoipCallFocusState);
                                    break;
                                }
                            case 2002:
                                return true;
                            case 2004:
                                if (messageArgs.hasActiveOrDialingCalls) {
                                    Log.w(CallAudioModeStateMachine.LOG_TAG, "Got a audio processing call while there's still a call active", new Object[0]);
                                } else {
                                    CallAudioModeStateMachine callAudioModeStateMachine6 = CallAudioModeStateMachine.this;
                                    callAudioModeStateMachine6.transitionTo(callAudioModeStateMachine6.mAudioProcessingFocusState);
                                }
                                break;
                            default:
                                return false;
                        }
                        break;
                }
            }
            if (messageArgs.foregroundCallIsVoip) {
                CallAudioModeStateMachine callAudioModeStateMachine7 = CallAudioModeStateMachine.this;
                callAudioModeStateMachine7.transitionTo(callAudioModeStateMachine7.mVoipCallFocusState);
            }
            return true;
        }
    }

    private class VoipCallFocusState extends BaseState {
        private VoipCallFocusState() {
            super();
        }

        public void enter() {
            Log.i(CallAudioModeStateMachine.LOG_TAG, "Audio focus entering VOIP CALL state", new Object[0]);
            if (!CallAudioModeStateMachine.this.mCallAudioManager.isBluetoothCall()) {
                CallAudioModeStateMachine.this.mAudioManager.requestAudioFocusForCall(0, 2);
                CallAudioModeStateMachine.this.mAudioManager.setMode(3);
            } else {
                Log.i(CallAudioModeStateMachine.LOG_TAG, "is bluetooth call, don't need request audio focus", new Object[0]);
            }
            CallAudioModeStateMachine.this.mMostRecentMode = 3;
            CallAudioModeStateMachine.this.mCallAudioManager.setCallAudioRouteFocusState(2);
        }

        @Override // com.android.server.telecom.CallAudioModeStateMachine.BaseState
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            MessageArgs messageArgs = (MessageArgs) message.obj;
            int i = message.what;
            if (i != 4001) {
                if (i == 6001) {
                    Log.w(CallAudioModeStateMachine.LOG_TAG, "Should not be seeing AUDIO_OPERATIONS_COMPLETE in a focused state", new Object[0]);
                    return true;
                }
                switch (i) {
                    case 1001:
                        CallAudioModeStateMachine callAudioModeStateMachine = CallAudioModeStateMachine.this;
                        callAudioModeStateMachine.transitionTo(callAudioModeStateMachine.calculateProperStateFromArgs(messageArgs));
                        return true;
                    case 1002:
                        if (messageArgs.isTonePlaying) {
                            CallAudioModeStateMachine.this.mCallAudioManager.stopCallWaiting();
                        }
                        return true;
                    case 1003:
                        if (!messageArgs.foregroundCallIsVoip) {
                            CallAudioModeStateMachine callAudioModeStateMachine2 = CallAudioModeStateMachine.this;
                            callAudioModeStateMachine2.transitionTo(callAudioModeStateMachine2.mSimCallFocusState);
                        }
                        return true;
                    default:
                        switch (i) {
                            case 2001:
                                if (!messageArgs.foregroundCallIsVoip) {
                                    CallAudioModeStateMachine callAudioModeStateMachine3 = CallAudioModeStateMachine.this;
                                    callAudioModeStateMachine3.transitionTo(callAudioModeStateMachine3.mSimCallFocusState);
                                }
                                return true;
                            case 2002:
                                CallAudioModeStateMachine.this.mCallAudioManager.startCallWaiting("call already active");
                                return true;
                            case 2003:
                                if (!messageArgs.foregroundCallIsVoip) {
                                    CallAudioModeStateMachine callAudioModeStateMachine4 = CallAudioModeStateMachine.this;
                                    callAudioModeStateMachine4.transitionTo(callAudioModeStateMachine4.mSimCallFocusState);
                                }
                                return true;
                            case 2004:
                                if (messageArgs.hasActiveOrDialingCalls) {
                                    Log.w(CallAudioModeStateMachine.LOG_TAG, "Got a audio processing call while there's still a call active", new Object[0]);
                                } else {
                                    CallAudioModeStateMachine callAudioModeStateMachine5 = CallAudioModeStateMachine.this;
                                    callAudioModeStateMachine5.transitionTo(callAudioModeStateMachine5.mAudioProcessingFocusState);
                                }
                                break;
                            default:
                                return false;
                        }
                        break;
                }
            }
            if (!messageArgs.foregroundCallIsVoip) {
                CallAudioModeStateMachine callAudioModeStateMachine6 = CallAudioModeStateMachine.this;
                callAudioModeStateMachine6.transitionTo(callAudioModeStateMachine6.mSimCallFocusState);
            }
            return true;
        }
    }

    private class OtherFocusState extends BaseState {
        private OtherFocusState() {
            super();
        }

        public void enter() {
            Log.i(CallAudioModeStateMachine.LOG_TAG, "Audio focus entering TONE/HOLDING state", new Object[0]);
            if (!CallAudioModeStateMachine.this.mCallAudioManager.isBluetoothCall()) {
                CallAudioModeStateMachine.this.mAudioManager.requestAudioFocusForCall(0, 2);
                CallAudioModeStateMachine.this.mAudioManager.setMode(CallAudioModeStateMachine.this.mMostRecentMode);
            } else {
                Log.i(CallAudioModeStateMachine.LOG_TAG, "is bluetooth call, don't need request audio focus", new Object[0]);
            }
            CallAudioModeStateMachine.this.mCallAudioManager.setCallAudioRouteFocusState(2);
        }

        @Override // com.android.server.telecom.CallAudioModeStateMachine.BaseState
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            MessageArgs messageArgs = (MessageArgs) message.obj;
            int i = message.what;
            if (i == 1002) {
                CallAudioModeStateMachine.this.mCallAudioManager.stopCallWaiting();
                return true;
            }
            if (i == 1003) {
                if (messageArgs.hasActiveOrDialingCalls) {
                    CallAudioModeStateMachine callAudioModeStateMachine = CallAudioModeStateMachine.this;
                    callAudioModeStateMachine.transitionTo(messageArgs.foregroundCallIsVoip ? callAudioModeStateMachine.mVoipCallFocusState : callAudioModeStateMachine.mSimCallFocusState);
                } else if (messageArgs.hasRingingCalls) {
                    CallAudioModeStateMachine callAudioModeStateMachine2 = CallAudioModeStateMachine.this;
                    callAudioModeStateMachine2.transitionTo(callAudioModeStateMachine2.mRingingFocusState);
                } else if (!messageArgs.isTonePlaying) {
                    CallAudioModeStateMachine callAudioModeStateMachine3 = CallAudioModeStateMachine.this;
                    callAudioModeStateMachine3.transitionTo(callAudioModeStateMachine3.mUnfocusedState);
                }
                return true;
            }
            if (i == 3002) {
                CallAudioModeStateMachine callAudioModeStateMachine4 = CallAudioModeStateMachine.this;
                callAudioModeStateMachine4.transitionTo(callAudioModeStateMachine4.calculateProperStateFromArgs(messageArgs));
                return true;
            }
            if (i == 6001) {
                Log.w(CallAudioModeStateMachine.LOG_TAG, "Should not be seeing AUDIO_OPERATIONS_COMPLETE in a focused state", new Object[0]);
                return true;
            }
            switch (i) {
                case 2001:
                    CallAudioModeStateMachine callAudioModeStateMachine5 = CallAudioModeStateMachine.this;
                    callAudioModeStateMachine5.transitionTo(messageArgs.foregroundCallIsVoip ? callAudioModeStateMachine5.mVoipCallFocusState : callAudioModeStateMachine5.mSimCallFocusState);
                    return true;
                case 2002:
                    if (messageArgs.hasHoldingCalls && CallAudioModeStateMachine.this.mSystemStateHelper.isDeviceAtEar()) {
                        CallAudioModeStateMachine.this.mCallAudioManager.startCallWaiting("Device is at ear with held call");
                    } else {
                        CallAudioModeStateMachine callAudioModeStateMachine6 = CallAudioModeStateMachine.this;
                        callAudioModeStateMachine6.transitionTo(callAudioModeStateMachine6.mRingingFocusState);
                    }
                    return true;
                case 2003:
                    return true;
                default:
                    return false;
            }
        }
    }

    public CallAudioModeStateMachine(SystemStateHelper systemStateHelper, AudioManager audioManager) {
        super(CallAudioModeStateMachine.class.getSimpleName());
        this.mUnfocusedState = new UnfocusedState();
        this.mRingingFocusState = new RingingFocusState();
        this.mSimCallFocusState = new SimCallFocusState();
        this.mVoipCallFocusState = new VoipCallFocusState();
        this.mAudioProcessingFocusState = new AudioProcessingFocusState();
        this.mOtherFocusState = new OtherFocusState();
        this.mIsInitialized = false;
        this.mAudioManager = audioManager;
        this.mSystemStateHelper = systemStateHelper;
        this.mMostRecentMode = 0;
        createStates();
    }

    private void createStates() {
        addState(this.mUnfocusedState);
        addState(this.mRingingFocusState);
        addState(this.mSimCallFocusState);
        addState(this.mVoipCallFocusState);
        addState(this.mAudioProcessingFocusState);
        addState(this.mOtherFocusState);
        setInitialState(this.mUnfocusedState);
        start();
        MessageArgs.Builder builder = new MessageArgs.Builder();
        builder.setHasActiveOrDialingCalls(false);
        builder.setHasRingingCalls(false);
        builder.setHasHoldingCalls(false);
        builder.setIsTonePlaying(false);
        builder.setForegroundCallIsVoip(false);
        builder.setSession(Log.createSubsession());
        sendMessage(1, builder.build());
    }

    public void setCallAudioManager(CallAudioManager callAudioManager) {
        this.mCallAudioManager = callAudioManager;
    }

    public void sendMessageWithArgs(int i, MessageArgs messageArgs) {
        sendMessage(i, messageArgs);
    }

    protected void onPreHandleMessage(Message message) {
        Object obj = message.obj;
        if (obj != null && (obj instanceof MessageArgs)) {
            Log.continueSession(((MessageArgs) obj).session, "CAMSM.pM_" + message.what);
            Log.i(LOG_TAG, "Message received: %s.", new Object[]{MESSAGE_CODE_TO_NAME.get(message.what)});
            return;
        }
        if (message.what == 9001 && (message.obj instanceof Runnable)) {
            Log.i(LOG_TAG, "Running runnable for testing", new Object[0]);
            return;
        }
        String str = LOG_TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Message sent must be of type nonnull MessageArgs, but got ");
        Object obj2 = message.obj;
        sb.append(obj2 == null ? "null" : obj2.getClass().getSimpleName());
        Log.w(str, sb.toString(), new Object[0]);
        Log.w(LOG_TAG, "The message was of code %d = %s", new Object[]{Integer.valueOf(message.what), MESSAGE_CODE_TO_NAME.get(message.what)});
    }

    public void dumpPendingMessages(IndentingPrintWriter indentingPrintWriter) {
        Looper looper = getHandler().getLooper();
        Objects.requireNonNull(indentingPrintWriter);
        looper.dump(new $$Lambda$xzMjTXhTm1dp77OfnTdXjK_uqY(indentingPrintWriter), "");
    }

    protected void onPostHandleMessage(Message message) {
        Log.endSession();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BaseState calculateProperStateFromArgs(MessageArgs messageArgs) {
        if (messageArgs.hasActiveOrDialingCalls) {
            if (messageArgs.foregroundCallIsVoip) {
                return this.mVoipCallFocusState;
            }
            return this.mSimCallFocusState;
        }
        if (messageArgs.hasHoldingCalls) {
            return this.mOtherFocusState;
        }
        if (messageArgs.hasRingingCalls) {
            return this.mRingingFocusState;
        }
        if (messageArgs.isTonePlaying) {
            return this.mOtherFocusState;
        }
        if (messageArgs.hasAudioProcessingCalls) {
            return this.mAudioProcessingFocusState;
        }
        return this.mUnfocusedState;
    }
}
