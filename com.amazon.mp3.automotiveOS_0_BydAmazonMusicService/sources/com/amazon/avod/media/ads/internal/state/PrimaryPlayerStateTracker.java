package com.amazon.avod.media.ads.internal.state;

/* JADX INFO: loaded from: classes2.dex */
public class PrimaryPlayerStateTracker {
    private PlayerState mPlayerCurrentState = PlayerState.UNINITIALIZED;

    public enum PlayerState {
        UNINITIALIZED,
        STARTED,
        RESTARTING,
        STOP_WHILE_RESTARTING,
        STOPPED
    }

    public enum PlayerStateTransitions {
        STARTING,
        RESTARTING,
        STOPPING
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.ads.internal.state.PrimaryPlayerStateTracker$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerState;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerStateTransitions;

        static {
            int[] iArr = new int[PlayerState.values().length];
            $SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerState = iArr;
            try {
                iArr[PlayerState.UNINITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerState[PlayerState.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerState[PlayerState.RESTARTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerState[PlayerState.STOP_WHILE_RESTARTING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerState[PlayerState.STOPPED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[PlayerStateTransitions.values().length];
            $SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerStateTransitions = iArr2;
            try {
                iArr2[PlayerStateTransitions.STARTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerStateTransitions[PlayerStateTransitions.STOPPING.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerStateTransitions[PlayerStateTransitions.RESTARTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public synchronized boolean isSessionTransition(PlayerStateTransitions playerStateTransitions) {
        boolean z;
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerState[this.mPlayerCurrentState.ordinal()];
        z = false;
        if (i != 1) {
            if (i == 2) {
                int i2 = AnonymousClass1.$SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerStateTransitions[playerStateTransitions.ordinal()];
                if (i2 == 2) {
                    this.mPlayerCurrentState = PlayerState.STOPPED;
                    z = true;
                } else if (i2 == 3) {
                    this.mPlayerCurrentState = PlayerState.RESTARTING;
                }
            } else if (i == 3) {
                int i3 = AnonymousClass1.$SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerStateTransitions[playerStateTransitions.ordinal()];
                if (i3 == 1) {
                    this.mPlayerCurrentState = PlayerState.STARTED;
                } else if (i3 == 2) {
                    this.mPlayerCurrentState = PlayerState.STOP_WHILE_RESTARTING;
                }
            } else if (i == 4) {
                int i4 = AnonymousClass1.$SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerStateTransitions[playerStateTransitions.ordinal()];
                if (i4 == 1) {
                    this.mPlayerCurrentState = PlayerState.STARTED;
                } else if (i4 == 2) {
                    this.mPlayerCurrentState = PlayerState.STOPPED;
                }
                z = true;
            } else {
                int i5 = AnonymousClass1.$SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerStateTransitions[playerStateTransitions.ordinal()];
            }
        } else if (AnonymousClass1.$SwitchMap$com$amazon$avod$media$ads$internal$state$PrimaryPlayerStateTracker$PlayerStateTransitions[playerStateTransitions.ordinal()] == 1) {
            this.mPlayerCurrentState = PlayerState.STARTED;
            z = true;
        }
        return z;
    }

    public PlayerState getPlayerCurrentState() {
        return this.mPlayerCurrentState;
    }
}
