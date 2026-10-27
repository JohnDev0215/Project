package com.byd.systemsettings.btsetting;

import com.byd.bt.model.BTMajorType;
import com.byd.bt.model.PhoneType;
import com.byd.systemsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class BtImgUtil {

    /* JADX INFO: renamed from: com.byd.systemsettings.btsetting.BtImgUtil$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$byd$bt$model$BTMajorType;

        static {
            int[] iArr = new int[BTMajorType.values().length];
            $SwitchMap$com$byd$bt$model$BTMajorType = iArr;
            try {
                iArr[BTMajorType.MISC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTMajorType[BTMajorType.COMPUTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTMajorType[BTMajorType.PHONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTMajorType[BTMajorType.NETWORKING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTMajorType[BTMajorType.AUDIO_VIDEO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTMajorType[BTMajorType.PERIPHERAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTMajorType[BTMajorType.IMAGING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTMajorType[BTMajorType.WEARABLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTMajorType[BTMajorType.TOY.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BTMajorType[BTMajorType.HEALTH.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public static int getBtTypeImgId(BTMajorType bTMajorType, PhoneType phoneType, boolean z7) {
        int i8 = AnonymousClass1.$SwitchMap$com$byd$bt$model$BTMajorType[bTMajorType.ordinal()];
        int phoneTypeImgId = i8 != 3 ? i8 != 6 ? 0 : R.drawable.icon_public_phone_battery_48 : getPhoneTypeImgId(phoneType, z7);
        return phoneTypeImgId == 0 ? R.drawable.icon_public_phone_battery_48 : phoneTypeImgId;
    }

    public static int getPhoneTypeImgId(PhoneType phoneType, boolean z7) {
        return (phoneType == PhoneType.CAR_PLAY && z7) ? R.drawable.icon_light_public_carplay : R.drawable.icon_public_phone_48;
    }
}
