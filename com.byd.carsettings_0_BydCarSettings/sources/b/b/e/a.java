package b.b.e;

import com.byd.bt.model.BTMajorType;
import com.byd.bt.model.PhoneType;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: BtImgUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: b.b.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BtImgUtil.java */
    public static /* synthetic */ class C0031a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f623a;

        static {
            int[] iArr = new int[BTMajorType.values().length];
            f623a = iArr;
            try {
                iArr[BTMajorType.MISC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f623a[BTMajorType.COMPUTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f623a[BTMajorType.PHONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f623a[BTMajorType.NETWORKING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f623a[BTMajorType.AUDIO_VIDEO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f623a[BTMajorType.PERIPHERAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f623a[BTMajorType.IMAGING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f623a[BTMajorType.WEARABLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f623a[BTMajorType.TOY.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f623a[BTMajorType.HEALTH.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public static int a(BTMajorType bTMajorType, PhoneType phoneType, boolean z) {
        int i = C0031a.f623a[bTMajorType.ordinal()];
        int iB = i != 3 ? i != 6 ? 0 : R.drawable.icon_public_phone_battery_48 : b(phoneType, z);
        return iB == 0 ? R.drawable.icon_public_phone_battery_48 : iB;
    }

    public static int b(PhoneType phoneType, boolean z) {
        return (phoneType == PhoneType.CAR_PLAY && z) ? R.drawable.icon_light_public_carplay : R.drawable.icon_public_phone_48;
    }
}
