package b.b.g.o.i;

import b.b.g.o.d;
import com.byd.carsettings.R;
import com.byd.carsettings.sound.model.SpeakerLayoutMode;
import com.byd.mvp.common.CarModel$Brand;

/* JADX INFO: compiled from: BSSoundResSelector.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends c {

    /* JADX INFO: renamed from: b.b.g.o.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BSSoundResSelector.java */
    public static /* synthetic */ class C0062a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1350a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f1351b;

        static {
            int[] iArr = new int[CarModel$Brand.values().length];
            f1351b = iArr;
            try {
                iArr[CarModel$Brand.DYNASTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1351b[CarModel$Brand.DENZA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[SpeakerLayoutMode.values().length];
            f1350a = iArr2;
            try {
                iArr2[SpeakerLayoutMode.SEAT_ROWS_2.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1350a[SpeakerLayoutMode.SEAT_ROWS_2_PICKUP_TRUCK.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1350a[SpeakerLayoutMode.SEAT_ROWS_3_SUV.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1350a[SpeakerLayoutMode.SEAT_ROWS_3_MPV.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static int m() {
        CarModel$Brand carModel$BrandC = d.c();
        return (carModel$BrandC != null && C0062a.f1351b[carModel$BrandC.ordinal()] == 2) ? R.drawable.bg_dyna_sound_charact_custom_denza : R.drawable.bg_dyna_sound_charact_custom;
    }

    public static int n(SpeakerLayoutMode speakerLayoutMode) {
        if (speakerLayoutMode == null) {
            return R.layout.dialog_bs_sound_field_suv_rows_3;
        }
        int i = C0062a.f1350a[speakerLayoutMode.ordinal()];
        return i != 1 ? i != 2 ? i != 4 ? R.layout.dialog_bs_sound_field_suv_rows_3 : R.layout.dialog_bs_sound_field_mpv_rows_3 : R.layout.dialog_bs_sound_field_pa : R.layout.dialog_bs_sound_field;
    }

    public static int o(int i) {
        return (i == 182 || i == 208) ? R.drawable.bg_dyna_sound_focus_all_uke : R.drawable.bg_dyna_sound_focus_all;
    }

    public static int p(int i) {
        boolean zH = d.h();
        int iD = d.d();
        if (i == 0) {
            return o(iD);
        }
        if (i == 1) {
            return t(iD, zH);
        }
        if (i != 2) {
            return 0;
        }
        return u(iD);
    }

    public static int q(int i) {
        boolean zH = d.h();
        if (i == 0) {
            return R.drawable.bg_dyna_sound_focus_all_pickup;
        }
        if (i == 1) {
            return zH ? R.drawable.bg_dyna_sound_focus_left_1st_pickup : R.drawable.bg_dyna_sound_focus_right_1st_pickup;
        }
        if (i != 3) {
            return 0;
        }
        return R.drawable.bg_dyna_sound_focus_last_pickup;
    }

    public static int r(int i) {
        boolean zH = d.h();
        if (i == 0) {
            return zH ? R.drawable.bg_dyna_sound_focus_all_rows_3 : R.drawable.bg_dyna_sound_focus_all_rows_3_mpv_r;
        }
        if (i == 1) {
            return zH ? R.drawable.bg_dyna_sound_focus_left_1st_rows_3 : R.drawable.bg_dyna_sound_focus_right_1st_rows_3_mpv_r;
        }
        if (i != 2) {
            return 0;
        }
        return zH ? R.drawable.bg_dyna_sound_focus_last_rows_3 : R.drawable.bg_dyna_sound_focus_last_rows_3_mpv_r;
    }

    public static int s(int i) {
        boolean zH = d.h();
        b.b.m.c.a.a("BSSoundResSelector", "getFocusBgImgIdRows3SUV", "leftRudder: " + zH + ", index: " + i);
        if (i == 0) {
            return zH ? R.drawable.bg_dyna_sound_focus_all_rows_3 : R.drawable.bg_dyna_sound_focus_all_rows_3_r;
        }
        if (i == 1) {
            return zH ? R.drawable.bg_dyna_sound_focus_left_1st_rows_3 : R.drawable.bg_dyna_sound_focus_right_1st_rows_3_r;
        }
        if (i != 2) {
            return 0;
        }
        return zH ? R.drawable.bg_dyna_sound_focus_last_rows_3 : R.drawable.bg_dyna_sound_focus_last_rows_3_r;
    }

    public static int t(int i, boolean z) {
        return (i == 182 || i == 208) ? z ? R.drawable.bg_dyna_sound_focus_left_1st_uke : R.drawable.bg_dyna_sound_focus_right_1st_uke : z ? R.drawable.bg_dyna_sound_focus_left_1st : R.drawable.bg_dyna_sound_focus_right_1st;
    }

    public static int u(int i) {
        return (i == 182 || i == 208) ? R.drawable.bg_dyna_sound_focus_last_uke : R.drawable.bg_dyna_sound_focus_last;
    }

    public static int v(SpeakerLayoutMode speakerLayoutMode, int i) {
        b.b.m.c.a.a("BSSoundResSelector", "getSoundFocusBgImgId", "mode: " + speakerLayoutMode + ", index: " + i);
        if (speakerLayoutMode == null) {
            return s(i);
        }
        int i2 = C0062a.f1350a[speakerLayoutMode.ordinal()];
        if (i2 == 1) {
            return p(i);
        }
        if (i2 == 2) {
            return q(i);
        }
        if (i2 == 3) {
            return s(i);
        }
        if (i2 != 4) {
            return 0;
        }
        return r(i);
    }
}
