package ts.car.service.voiceactuator;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IVoiceActuatorResultCallback {
    void onActionMuiltyResultCallback(List<VoiceData> list);

    void onActionResultCallback(VoiceData voiceData);
}
