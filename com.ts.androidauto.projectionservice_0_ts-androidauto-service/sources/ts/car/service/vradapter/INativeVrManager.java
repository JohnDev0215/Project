package ts.car.service.vradapter;

import java.util.List;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface INativeVrManager extends IPlatformService {
    int getAudioEvent();

    int getNativeVrState();

    String getPrivacyPolicyUrl();

    List<String> getRandomSemantic();

    String getVersion();

    String getWakeupWord();

    void openAccountPage();

    boolean registerListener(INativeVrListener iNativeVrListener);

    void setPreferMap(int i);

    void setWakeupWord(String str);

    void speak(String str);

    void startNativeVr();

    void stopNativeVr();

    void stopSpeak(String str);

    boolean unregisterListener(INativeVrListener iNativeVrListener);
}
