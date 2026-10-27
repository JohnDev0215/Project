package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum KeyEvent {
    KEYCODE_BACK(0),
    KEYCODE_HOME(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    KeyEvent(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static KeyEvent getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return KEYCODE_BACK;
        }
        if (iIntValue == 1) {
            return KEYCODE_HOME;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + KeyEvent.class.getName());
    }

    public static KeyEvent valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("KEYCODE_BACK")) {
            return KEYCODE_BACK;
        }
        if (str.equals("KEYCODE_HOME")) {
            return KEYCODE_HOME;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + KeyEvent.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
