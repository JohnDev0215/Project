package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum EventType {
    EVENT_RESERVE(0),
    EVENT_REQUEST_FOCUS(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    EventType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static EventType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return EVENT_RESERVE;
        }
        if (iIntValue == 1) {
            return EVENT_REQUEST_FOCUS;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + EventType.class.getName());
    }

    public static EventType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("EVENT_REQUEST_FOCUS")) {
            return EVENT_REQUEST_FOCUS;
        }
        if (str.equals("EVENT_RESERVE")) {
            return EVENT_RESERVE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + EventType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
