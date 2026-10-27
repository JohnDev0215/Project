package api.structs.com.neusoft.onecore.pos;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum LaneChangeEventType {
    NoChange(0),
    ChangedToTheLeft(1),
    ChangedToTheRight(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    LaneChangeEventType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static LaneChangeEventType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return NoChange;
        }
        if (iIntValue == 1) {
            return ChangedToTheLeft;
        }
        if (iIntValue == 2) {
            return ChangedToTheRight;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + LaneChangeEventType.class.getName());
    }

    public static LaneChangeEventType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "ChangedToTheLeft":
                return ChangedToTheLeft;
            case "ChangedToTheRight":
                return ChangedToTheRight;
            case "NoChange":
                return NoChange;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + LaneChangeEventType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
