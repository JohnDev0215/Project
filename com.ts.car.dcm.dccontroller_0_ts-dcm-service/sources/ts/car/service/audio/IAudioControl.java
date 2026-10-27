package ts.car.service.audio;

/* JADX INFO: loaded from: classes.dex */
public interface IAudioControl {
    public static final int AUDIO_POWER_MODE_NORMAL = 0;
    public static final int AUDIO_POWER_MODE_VOLTAGE_WARNING = 1;
    public static final int SURROUND_MODE_ALL_PASSENGERS = 4;
    public static final int SURROUND_MODE_CENTER = 1;
    public static final int SURROUND_MODE_COPILOT = 3;
    public static final int SURROUND_MODE_DRIVER = 2;
    public static final int SURROUND_MODE_OFF = 0;
    public static final int VSC_MODE_CLOSE = 0;
    public static final int VSC_MODE_GENERAL = 2;
    public static final int VSC_MODE_HIGHER = 3;
    public static final int VSC_MODE_LOWER = 1;

    int getAudioPowerMode();

    int getAudioPowerModeCount();

    int getSurroundMode();

    int getSurroundModeCount();

    float[] getVscGainRange();

    int getVscMode();

    int getVscModeCount();

    boolean isRingtoneMute();

    void registerVscModeChangeCallback(IVscModeCallback iVscModeCallback);

    void setAudioPowerMode(int i);

    void setRingtoneMute(boolean z);

    void setSurroundMode(int i);

    void setVscGain(float f);

    void setVscMode(int i);

    void unregisterVscModeChangeCallback(IVscModeCallback iVscModeCallback);
}
