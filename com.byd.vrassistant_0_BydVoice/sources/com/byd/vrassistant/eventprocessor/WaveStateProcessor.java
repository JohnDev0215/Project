package com.byd.vrassistant.eventprocessor;

import android.util.Log;
import com.byd.vrassistant.utils.InterrupterTimer;
import com.byd.vrassistant.view.ViewStateManger;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class WaveStateProcessor {
    private static final String TAG = "NEU_WaveStateProcessor";
    boolean areaChanged;
    DialogEventState lastAnimState;
    String lastWakeArea;
    WaveState lastWaveState;
    OnWaveStateAreaUpdateListener onWaveStateAreaUpdate;
    Runnable listenStateTask = new Runnable() { // from class: com.byd.vrassistant.eventprocessor.WaveStateProcessor$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.m131xb7c0afc3();
        }
    };
    Runnable speakStateTask = new Runnable() { // from class: com.byd.vrassistant.eventprocessor.WaveStateProcessor$$ExternalSyntheticLambda1
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.m132xe1150504();
        }
    };
    InterrupterTimer spk2lsnTimer = new InterrupterTimer(this.listenStateTask);
    InterrupterTimer lsn2spkTimer = new InterrupterTimer(this.speakStateTask);

    public interface OnWaveStateAreaUpdateListener {
        void onWaveStateUpdate(WaveState waveState);
    }

    public WaveStateProcessor(OnWaveStateAreaUpdateListener onWaveStateAreaUpdateListener) {
        this.onWaveStateAreaUpdate = onWaveStateAreaUpdateListener;
    }

    public void setWakeArea(String str) {
        if (Objects.equals(this.lastWakeArea, str)) {
            return;
        }
        this.areaChanged = true;
        this.lastWakeArea = str;
    }

    public void setDialogEventState(DialogEventState dialogEventState) {
        if (this.lastAnimState == dialogEventState || !ViewStateManger.get().isInUiSession()) {
            return;
        }
        Log.i(TAG, "setDialogState: " + dialogEventState);
        switch (AnonymousClass1.$SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[dialogEventState.ordinal()]) {
            case 1:
                setWaveState(WaveState.LISTENING);
                break;
            case 2:
                setWaveState(WaveState.NONE);
                break;
            case 3:
            case 4:
                if (this.lastWaveState == WaveState.SPEAK2LISTEN) {
                    this.spk2lsnTimer.interruptTimer();
                    setWaveState(WaveState.SPEAKING);
                } else if (this.lastWaveState == WaveState.LISTENING) {
                    setWaveState(WaveState.LISTEN2SPEAK);
                    this.lsn2spkTimer.startTimer(340L);
                }
                break;
            case 5:
            case 6:
                if (this.lastWaveState == WaveState.LISTEN2SPEAK || this.lastWaveState == WaveState.NONE) {
                    this.lsn2spkTimer.interruptTimer();
                    setWaveState(WaveState.LISTENING);
                } else if (this.lastWaveState == WaveState.SPEAKING) {
                    setWaveState(WaveState.SPEAK2LISTEN);
                    this.spk2lsnTimer.startTimer(510L);
                }
                break;
        }
        this.lastAnimState = dialogEventState;
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.eventprocessor.WaveStateProcessor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState;

        static {
            int[] iArr = new int[DialogEventState.values().length];
            $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState = iArr;
            try {
                iArr[DialogEventState.CONVERSATION_BEGIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[DialogEventState.CONVERSATION_END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[DialogEventState.CAPTURING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[DialogEventState.CAPTURING_EX.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[DialogEventState.PROCESSING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[DialogEventState.PROCESSING_EX.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void setWaveState(WaveState waveState) {
        Log.i(TAG, "setWaveState: " + waveState);
        if (this.lastWaveState != waveState || this.areaChanged) {
            this.areaChanged = false;
            this.onWaveStateAreaUpdate.onWaveStateUpdate(waveState);
            this.lastWaveState = waveState;
        }
    }

    /* JADX INFO: renamed from: lambda$new$0$com-byd-vrassistant-eventprocessor-WaveStateProcessor, reason: not valid java name */
    /* synthetic */ void m131xb7c0afc3() {
        setWaveState(WaveState.LISTENING);
    }

    /* JADX INFO: renamed from: lambda$new$1$com-byd-vrassistant-eventprocessor-WaveStateProcessor, reason: not valid java name */
    /* synthetic */ void m132xe1150504() {
        setWaveState(WaveState.SPEAKING);
    }
}
