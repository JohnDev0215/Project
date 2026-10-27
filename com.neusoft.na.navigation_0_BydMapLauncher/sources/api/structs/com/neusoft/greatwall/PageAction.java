package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum PageAction {
    PAGE_ACTION_ENTER(0),
    PAGE_ACTION_EXIT(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    PageAction(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static PageAction getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return PAGE_ACTION_ENTER;
        }
        if (iIntValue == 1) {
            return PAGE_ACTION_EXIT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + PageAction.class.getName());
    }

    public static PageAction valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("PAGE_ACTION_EXIT")) {
            return PAGE_ACTION_EXIT;
        }
        if (str.equals("PAGE_ACTION_ENTER")) {
            return PAGE_ACTION_ENTER;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + PageAction.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
