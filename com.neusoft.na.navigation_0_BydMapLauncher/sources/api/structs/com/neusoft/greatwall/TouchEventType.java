package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum TouchEventType {
    TOUCH_EVENT_CLICK(0),
    TOUCH_EVENT_LONG_CLICK(1),
    TOUCH_EVENT_SCROLL(2),
    TOUCH_EVENT_OTHER(10);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    TouchEventType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static TouchEventType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return TOUCH_EVENT_CLICK;
        }
        if (iIntValue == 1) {
            return TOUCH_EVENT_LONG_CLICK;
        }
        if (iIntValue == 2) {
            return TOUCH_EVENT_SCROLL;
        }
        if (iIntValue == 10) {
            return TOUCH_EVENT_OTHER;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + TouchEventType.class.getName());
    }

    public static TouchEventType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "TOUCH_EVENT_LONG_CLICK":
                return TOUCH_EVENT_LONG_CLICK;
            case "TOUCH_EVENT_CLICK":
                return TOUCH_EVENT_CLICK;
            case "TOUCH_EVENT_OTHER":
                return TOUCH_EVENT_OTHER;
            case "TOUCH_EVENT_SCROLL":
                return TOUCH_EVENT_SCROLL;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + TouchEventType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
