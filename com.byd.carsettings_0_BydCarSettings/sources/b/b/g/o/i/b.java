package b.b.g.o.i;

import b.b.g.o.d;
import com.byd.carsettings.R;
import com.byd.carsettings.sound.model.DiracLevel;
import com.byd.carsettings.sound.model.SpeakerLayoutMode;
import com.byd.mvp.common.CarModel$Brand;

/* JADX INFO: compiled from: DiracSoundResSelector.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends c {

    /* JADX INFO: compiled from: DiracSoundResSelector.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1352a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f1353b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final /* synthetic */ int[] f1354c;

        static {
            int[] iArr = new int[CarModel$Brand.values().length];
            f1354c = iArr;
            try {
                iArr[CarModel$Brand.DYNASTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1354c[CarModel$Brand.DENZA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[SpeakerLayoutMode.values().length];
            f1353b = iArr2;
            try {
                iArr2[SpeakerLayoutMode.SEAT_ROWS_2.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1353b[SpeakerLayoutMode.SEAT_ROWS_2_PICKUP_TRUCK.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1353b[SpeakerLayoutMode.SEAT_ROWS_3_SUV.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1353b[SpeakerLayoutMode.SEAT_ROWS_3_MPV.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[DiracLevel.values().length];
            f1352a = iArr3;
            try {
                iArr3[DiracLevel.DIRAC_SIMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1352a[DiracLevel.DIRAC_PERFORMANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1352a[DiracLevel.DIRAC_LIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public static int m() {
        int iD = d.d();
        boolean zL = c.l();
        return iD != 180 ? (iD == 182 || iD == 208) ? zL ? R.drawable.bg_dirac_sound_balance_5 : R.drawable.bg_dirac_sound_balance_5_r : zL ? R.drawable.bg_dirac_sound_balance : R.drawable.bg_dirac_sound_balance_r : zL ? R.drawable.bg_dirac_sound_balance_6 : R.drawable.bg_dirac_sound_balance_6_r;
    }

    public static int n() {
        SpeakerLayoutMode speakerLayoutMode;
        b.b.g.o.h.a aVarA = c.a();
        if (aVarA != null && (speakerLayoutMode = aVarA.f1344c) != null) {
            int i = a.f1353b[speakerLayoutMode.ordinal()];
        }
        return R.layout.dialog_dirac_sound_field;
    }
}
