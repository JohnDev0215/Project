package api.structs.com.neusoft.ecarx;

import cerence.ark.assistant.controller.enumeration.ZoomMapTypeEnum;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ZoomInOutType {
    ZOOM_OUT(0),
    ZOOM_IN(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ZoomInOutType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ZoomInOutType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ZOOM_OUT;
        }
        if (iIntValue == 1) {
            return ZOOM_IN;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ZoomInOutType.class.getName());
    }

    public static ZoomInOutType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals(ZoomMapTypeEnum.ZOOM_IN)) {
            return ZOOM_IN;
        }
        if (str.equals(ZoomMapTypeEnum.ZOOM_OUT)) {
            return ZOOM_OUT;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ZoomInOutType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
