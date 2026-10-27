package com.ecarx.xui.adaptapi.audio.audiofx;

import com.ecarx.xui.adaptapi.FunctionStatus;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IWarnVolume {
    public static final int MODE_LEVEL = 2;
    public static final int MODE_VOLUME = 1;
    public static final int WARN_VOLUME_LEVEL_HIGH = 3;
    public static final int WARN_VOLUME_LEVEL_LOW = 1;
    public static final int WARN_VOLUME_LEVEL_NORMAL = 2;
    public static final int WARN_VOLUME_LEVEL_OFF = 0;
    public static final int WARN_VOLUME_TYPE_RADAR = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface VolumeSetMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WarnVolumeLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WarnVolumeType {
    }

    int getMaxWarnVolume(int i);

    int getMinWarnVolume(int i);

    int[] getSupportWarnVolumeLevel(int i);

    int getWarnVolume(int i);

    int getWarnVolumeLevel(int i);

    int getWarnVolumeSetMode(int i);

    FunctionStatus isWarnVolumeTypeSupported(int i);

    boolean setWarnVolume(int i, int i2);

    boolean setWarnVolumeLevel(int i, int i2);
}
