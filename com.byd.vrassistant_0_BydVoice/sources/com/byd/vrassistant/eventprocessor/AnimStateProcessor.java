package com.byd.vrassistant.eventprocessor;

import android.util.Log;
import com.byd.vrassistant.bean.AnimState;

/* JADX INFO: loaded from: classes.dex */
public class AnimStateProcessor {
    private static final String TAG = "NEU_AnimStateProcessor";
    AnimState lastAnimState;
    DialogEventState lastDialogState;
    OnAnimStateUpdateListener onAnimStateUpdateListener;

    public interface OnAnimStateUpdateListener {
        void onAnimStateUpdate(AnimState animState);
    }

    public AnimStateProcessor(OnAnimStateUpdateListener onAnimStateUpdateListener) {
        this.onAnimStateUpdateListener = onAnimStateUpdateListener;
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.eventprocessor.AnimStateProcessor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState;

        static {
            int[] iArr = new int[DialogEventState.values().length];
            $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState = iArr;
            try {
                iArr[DialogEventState.LISTENING_CMD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[DialogEventState.CAPTURING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[DialogEventState.CAPTURING_EX.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[DialogEventState.PROCESSING_EX.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[DialogEventState.RECOGNITION_RESTARTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[DialogEventState.BROADCAST_BEGIN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[DialogEventState.BROADCAST_END.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public void setDialogEventState(DialogEventState dialogEventState) {
        Log.i(TAG, "setDialogEventState: " + dialogEventState + ",lastAnimState=" + this.lastAnimState);
        switch (AnonymousClass1.$SwitchMap$com$byd$vrassistant$eventprocessor$DialogEventState[dialogEventState.ordinal()]) {
            case 1:
                if (this.lastAnimState == AnimState.BROADCAST) {
                    return;
                } else {
                    setAnimState(AnimState.CYCLE);
                }
                break;
            case 2:
            case 3:
                setAnimState(AnimState.IDENTIFY);
                break;
            case 4:
                if (this.lastAnimState == AnimState.IDENTIFY) {
                    setAnimState(AnimState.PROCESSING);
                }
                break;
            case 5:
                if (this.lastAnimState == AnimState.PROCESSING || this.lastAnimState == AnimState.IDENTIFY) {
                    setAnimState(AnimState.CYCLE);
                }
                break;
            case 6:
                setAnimState(AnimState.BROADCAST);
                break;
            case 7:
                if (this.lastAnimState == AnimState.BROADCAST) {
                    setAnimState(AnimState.CYCLE);
                }
                break;
        }
        this.lastDialogState = dialogEventState;
    }

    private void setAnimState(AnimState animState) {
        if (this.lastAnimState == animState) {
            return;
        }
        Log.i(TAG, "setAnimState: " + animState);
        this.onAnimStateUpdateListener.onAnimStateUpdate(animState);
        this.lastAnimState = animState;
    }
}
