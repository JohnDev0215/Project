package com.byd.vrassistant.viewmodel;

import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.byd.vrassistant.bean.AnimState;
import com.byd.vrassistant.bean.WaveStateWithArea;
import com.byd.vrassistant.eventprocessor.AnimStateProcessor;
import com.byd.vrassistant.eventprocessor.DialogEventState;
import com.byd.vrassistant.eventprocessor.WaveState;
import com.byd.vrassistant.eventprocessor.WaveStateProcessor;
import com.byd.vrassistant.livedata.SafeMutableLiveData;

/* JADX INFO: loaded from: classes2.dex */
public class AnimViewModel extends ViewModel {
    private static final String TAG = "AnimViewModel";
    WaveState lastWaveState;
    SafeMutableLiveData<WaveStateWithArea> waveStateLd = new SafeMutableLiveData<>();
    SafeMutableLiveData<AnimState> animStateLd = new SafeMutableLiveData<>();
    String area = "";
    AnimStateProcessor animStateProcessor = new AnimStateProcessor(new AnimStateProcessor.OnAnimStateUpdateListener() { // from class: com.byd.vrassistant.viewmodel.AnimViewModel$$ExternalSyntheticLambda0
        @Override // com.byd.vrassistant.eventprocessor.AnimStateProcessor.OnAnimStateUpdateListener
        public final void onAnimStateUpdate(AnimState animState) {
            this.f$0.setAnimState(animState);
        }
    });
    WaveStateProcessor waveStateProcessor = new WaveStateProcessor(new WaveStateProcessor.OnWaveStateAreaUpdateListener() { // from class: com.byd.vrassistant.viewmodel.AnimViewModel$$ExternalSyntheticLambda1
        @Override // com.byd.vrassistant.eventprocessor.WaveStateProcessor.OnWaveStateAreaUpdateListener
        public final void onWaveStateUpdate(WaveState waveState) {
            this.f$0.updateWaveState(waveState);
        }
    });

    public LiveData<WaveStateWithArea> getWaveStateLd() {
        return this.waveStateLd;
    }

    public void updateWaveState(WaveState waveState) {
        this.lastWaveState = waveState;
        if (TextUtils.isEmpty(this.area)) {
            Log.i(TAG, "updateWaveState: area is null or empty");
        } else {
            this.waveStateLd.setValue(WaveStateWithArea.get(waveState, this.area));
        }
    }

    public void updateWakeArea(String str) {
        this.area = str;
        this.waveStateProcessor.setWakeArea(str);
        if (this.waveStateLd.getValue() != null) {
            WaveStateWithArea waveStateWithArea = new WaveStateWithArea();
            waveStateWithArea.setArea(str);
            waveStateWithArea.setWaveState(this.waveStateLd.getValue().getWaveState());
            this.waveStateLd.setValue(waveStateWithArea);
        }
    }

    public LiveData<AnimState> getAnimStateLd() {
        return this.animStateLd;
    }

    public void setAnimState(AnimState animState) {
        this.animStateLd.setValue(animState);
    }

    public void setDialogEventState(DialogEventState dialogEventState) {
        if (dialogEventState == null) {
            return;
        }
        this.animStateProcessor.setDialogEventState(dialogEventState);
        this.waveStateProcessor.setDialogEventState(dialogEventState);
    }
}
