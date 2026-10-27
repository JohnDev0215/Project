package com.byd.vrassistant.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import com.byd.vrassistant.base.ImageViewLifecycle;
import com.byd.vrassistant.bean.WaveStateWithArea;
import com.byd.vrassistant.eventprocessor.WaveState;
import com.byd.vrassistant.ui.R;

/* JADX INFO: loaded from: classes2.dex */
public class IvWave extends ImageViewLifecycle {
    private static final String TAG = "NEU_IvWave";

    @Override // com.byd.vrassistant.base.ImageViewLifecycle
    protected void initView() {
    }

    @Override // com.byd.vrassistant.base.ImageViewLifecycle
    protected void initViewModel() {
    }

    public IvWave(Context context) {
        super(context);
    }

    public IvWave(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public IvWave(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.view.IvWave$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$vrassistant$eventprocessor$WaveState;

        static {
            int[] iArr = new int[WaveState.values().length];
            $SwitchMap$com$byd$vrassistant$eventprocessor$WaveState = iArr;
            try {
                iArr[WaveState.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$WaveState[WaveState.LISTEN2SPEAK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$WaveState[WaveState.SPEAKING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$WaveState[WaveState.SPEAK2LISTEN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$vrassistant$eventprocessor$WaveState[WaveState.LISTENING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public void setWaveState(WaveStateWithArea waveStateWithArea) {
        boolean z = waveStateWithArea.getArea().contains("FRONT") || waveStateWithArea.getArea().contains("ROW_1");
        int i = AnonymousClass1.$SwitchMap$com$byd$vrassistant$eventprocessor$WaveState[waveStateWithArea.getWaveState().ordinal()];
        if (i == 1) {
            setImageDrawable(null);
            return;
        }
        if (i == 2) {
            getWaveLsn2Spk(z).start();
            return;
        }
        if (i == 3) {
            getWaveSpeak(z).start();
        } else if (i == 4) {
            getWaveSpk2Lsn(z).start();
        } else {
            if (i != 5) {
                return;
            }
            getWaveListen(z).start();
        }
    }

    public AnimationDrawable getWaveListen(boolean z) {
        Log.i(TAG, "getWaveListen: isFront=" + z);
        setImageResource(z ? R.drawable.anim_listening_blue : R.drawable.anim_listening_pink);
        return (AnimationDrawable) getDrawable();
    }

    public AnimationDrawable getWaveLsn2Spk(boolean z) {
        Log.i(TAG, "getWaveLsn2Spk: isFront=" + z);
        setImageResource(z ? R.drawable.anim_listen_to_speak_blue : R.drawable.anim_listen_to_speak_pink);
        return (AnimationDrawable) getDrawable();
    }

    public AnimationDrawable getWaveSpeak(boolean z) {
        Log.i(TAG, "getWaveSpeak: isFront=" + z);
        setImageResource(z ? R.drawable.anim_speaking_blue : R.drawable.anim_speaking_pink);
        return (AnimationDrawable) getDrawable();
    }

    public AnimationDrawable getWaveSpk2Lsn(boolean z) {
        Log.i(TAG, "getWaveSpk2Lsn: isFront=" + z);
        setImageResource(z ? R.drawable.anim_speak_to_listen_blue : R.drawable.anim_speak_to_listen_pink);
        return (AnimationDrawable) getDrawable();
    }
}
