package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum DesktopMode {
    WALLPAPER_DESKTOP(0),
    MAP_DESKTOP(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    DesktopMode(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static DesktopMode getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return WALLPAPER_DESKTOP;
        }
        if (iIntValue == 1) {
            return MAP_DESKTOP;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + DesktopMode.class.getName());
    }

    public static DesktopMode valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("WALLPAPER_DESKTOP")) {
            return WALLPAPER_DESKTOP;
        }
        if (str.equals("MAP_DESKTOP")) {
            return MAP_DESKTOP;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + DesktopMode.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
