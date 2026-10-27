package com.stingray.karaoke.car.utils;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/stingray/karaoke/car/utils/MainLayerLevel;", "", "<init>", "(Ljava/lang/String;I)V", "BACKGROUND", "MAIN", "MINI_PLAYER", "PLAYER", "DIALOG", "TOAST", "DEV_SETTINGS_DISPLAY_INFO", "DEV_SETTINGS_LOG_BOX", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MainLayerLevel {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ MainLayerLevel[] $VALUES;
    public static final MainLayerLevel BACKGROUND = new MainLayerLevel("BACKGROUND", 0);
    public static final MainLayerLevel MAIN = new MainLayerLevel("MAIN", 1);
    public static final MainLayerLevel MINI_PLAYER = new MainLayerLevel("MINI_PLAYER", 2);
    public static final MainLayerLevel PLAYER = new MainLayerLevel("PLAYER", 3);
    public static final MainLayerLevel DIALOG = new MainLayerLevel("DIALOG", 4);
    public static final MainLayerLevel TOAST = new MainLayerLevel("TOAST", 5);
    public static final MainLayerLevel DEV_SETTINGS_DISPLAY_INFO = new MainLayerLevel("DEV_SETTINGS_DISPLAY_INFO", 6);
    public static final MainLayerLevel DEV_SETTINGS_LOG_BOX = new MainLayerLevel("DEV_SETTINGS_LOG_BOX", 7);

    private static final /* synthetic */ MainLayerLevel[] $values() {
        return new MainLayerLevel[]{BACKGROUND, MAIN, MINI_PLAYER, PLAYER, DIALOG, TOAST, DEV_SETTINGS_DISPLAY_INFO, DEV_SETTINGS_LOG_BOX};
    }

    static {
        MainLayerLevel[] mainLayerLevelArr$values = $values();
        $VALUES = mainLayerLevelArr$values;
        $ENTRIES = r.e(mainLayerLevelArr$values);
    }

    private MainLayerLevel(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static MainLayerLevel valueOf(String str) {
        return (MainLayerLevel) Enum.valueOf(MainLayerLevel.class, str);
    }

    public static MainLayerLevel[] values() {
        return (MainLayerLevel[]) $VALUES.clone();
    }
}
