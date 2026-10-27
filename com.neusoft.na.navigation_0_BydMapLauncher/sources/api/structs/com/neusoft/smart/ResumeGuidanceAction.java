package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ResumeGuidanceAction {
    ACCEPT(0),
    REJECT(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ResumeGuidanceAction(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ResumeGuidanceAction getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ACCEPT;
        }
        if (iIntValue == 1) {
            return REJECT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ResumeGuidanceAction.class.getName());
    }

    public static ResumeGuidanceAction valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("REJECT")) {
            return REJECT;
        }
        if (str.equals("ACCEPT")) {
            return ACCEPT;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ResumeGuidanceAction.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
