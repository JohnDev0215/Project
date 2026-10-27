package api.structs.com.neusoft.changan;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum SoundType {
    SOUND_MALE(0),
    SOUND_FEMALE(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    SoundType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static SoundType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return SOUND_MALE;
        }
        if (iIntValue == 1) {
            return SOUND_FEMALE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + SoundType.class.getName());
    }

    public static SoundType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("SOUND_MALE")) {
            return SOUND_MALE;
        }
        if (str.equals("SOUND_FEMALE")) {
            return SOUND_FEMALE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + SoundType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
