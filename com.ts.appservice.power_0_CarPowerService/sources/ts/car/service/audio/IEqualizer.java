package ts.car.service.audio;

/* JADX INFO: loaded from: classes.dex */
public interface IEqualizer {
    public static final int INVALID_BAND = -1;
    public static final int PRESET_CLASSICAL = 1;
    public static final int PRESET_DANCE = 8;
    public static final int PRESET_FLAT = 5;
    public static final int PRESET_FOLK = 9;
    public static final int PRESET_HEAVY_METAL = 6;
    public static final int PRESET_HIP_POP = 7;
    public static final int PRESET_JAZZ = 3;
    public static final int PRESET_NORMAL = 0;
    public static final int PRESET_POP = 2;
    public static final int PRESET_ROCK = 4;

    int getBand(int i);

    int[] getBandFreqRange(int i);

    int getBandLevel(int i);

    int[] getBandLevelRange(int i);

    int getCenterFreq(int i);

    int getCurrentPreset();

    int getNumberOfBands();

    int getNumberOfPresets();

    String getPresetName(int i);

    boolean isBandSupported();

    boolean isPresetSupported();

    void setBandLevel(int i, int i2);

    void usePreset(int i);
}
