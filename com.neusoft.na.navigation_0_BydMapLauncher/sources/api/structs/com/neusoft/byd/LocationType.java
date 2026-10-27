package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum LocationType {
    CurrentLocation(1),
    Destination(2),
    SpecifyLocation(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    LocationType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static LocationType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 1) {
            return CurrentLocation;
        }
        if (iIntValue == 2) {
            return Destination;
        }
        if (iIntValue == 3) {
            return SpecifyLocation;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + LocationType.class.getName());
    }

    public static LocationType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "CurrentLocation":
                return CurrentLocation;
            case "SpecifyLocation":
                return SpecifyLocation;
            case "Destination":
                return Destination;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + LocationType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
