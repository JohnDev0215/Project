package api.structs.com.neusoft.onecore.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum VisibilityMode {
    INVALID(0),
    INVISIBLE(32768),
    FIRST_FRAME(33280),
    BACKGROUND(33792),
    SUPPRESS(34816),
    VISIBLE(36863);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    VisibilityMode(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static VisibilityMode getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return INVALID;
        }
        if (iIntValue == 32768) {
            return INVISIBLE;
        }
        if (iIntValue == 33280) {
            return FIRST_FRAME;
        }
        if (iIntValue == 33792) {
            return BACKGROUND;
        }
        if (iIntValue == 34816) {
            return SUPPRESS;
        }
        if (iIntValue == 36863) {
            return VISIBLE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + VisibilityMode.class.getName());
    }

    public static VisibilityMode valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "FIRST_FRAME":
                return FIRST_FRAME;
            case "INVALID":
                return INVALID;
            case "SUPPRESS":
                return SUPPRESS;
            case "BACKGROUND":
                return BACKGROUND;
            case "INVISIBLE":
                return INVISIBLE;
            case "VISIBLE":
                return VISIBLE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + VisibilityMode.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
