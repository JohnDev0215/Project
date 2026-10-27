package b.b.g.o;

import com.byd.carsettings.R;
import com.byd.carsettings.sound.model.SpeakerLayoutMode;
import com.byd.mvp.common.CarModel$Brand;

/* JADX INFO: compiled from: SoundResSelector.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: compiled from: SoundResSelector.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1339a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f1340b;

        static {
            int[] iArr = new int[CarModel$Brand.values().length];
            f1340b = iArr;
            try {
                iArr[CarModel$Brand.DYNASTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1340b[CarModel$Brand.DENZA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[SpeakerLayoutMode.values().length];
            f1339a = iArr2;
            try {
                iArr2[SpeakerLayoutMode.SEAT_ROWS_2.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1339a[SpeakerLayoutMode.SEAT_ROWS_2_PICKUP_TRUCK.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1339a[SpeakerLayoutMode.SEAT_ROWS_3_SUV.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1339a[SpeakerLayoutMode.SEAT_ROWS_3_MPV.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static int a() {
        CarModel$Brand carModel$BrandC = d.c();
        return (carModel$BrandC != null && a.f1340b[carModel$BrandC.ordinal()] == 2) ? R.drawable.bg_dyna_sound_charact_custom_denza : R.drawable.bg_dyna_sound_charact_custom;
    }

    public static int b() {
        boolean zH = d.h();
        CarModel$Brand carModel$BrandC = d.c();
        if (carModel$BrandC == null) {
            return zH ? R.drawable.bg_dyna_sound_charact_dynamic : R.drawable.bg_dyna_sound_charact_dynamic_r;
        }
        int i = a.f1340b[carModel$BrandC.ordinal()];
        return i != 1 ? i != 2 ? zH ? R.drawable.bg_dyna_sound_charact_dynamic : R.drawable.bg_dyna_sound_charact_dynamic_r : zH ? R.drawable.bg_dyna_sound_charact_dynamic_denza : R.drawable.bg_dyna_sound_charact_dynamic_r_denza : zH ? R.drawable.bg_dyna_sound_charact_dynamic : R.drawable.bg_dyna_sound_charact_dynamic_r;
    }

    public static int c(int i) {
        return (i == 182 || i == 208) ? R.drawable.bg_dyna_sound_focus_all_uke : R.drawable.bg_dyna_sound_focus_all;
    }

    public static int d(SpeakerLayoutMode speakerLayoutMode, int i) {
        if (speakerLayoutMode == null) {
            return g(i);
        }
        int i2 = a.f1339a[speakerLayoutMode.ordinal()];
        if (i2 == 1) {
            return e(i);
        }
        if (i2 == 2) {
            return f(i);
        }
        if (i2 == 3) {
            return h(i);
        }
        if (i2 != 4) {
            return 0;
        }
        return g(i);
    }

    public static int e(int i) {
        boolean zH = d.h();
        int iD = d.d();
        switch (i) {
            case R.id.rbtn_all_dyna_sound_field /* 2131297773 */:
                return c(iD);
            case R.id.rbtn_last_row_dyna_sound_field /* 2131297822 */:
                return k(iD);
            case R.id.rbtn_left_1st_dyna_sound_field /* 2131297826 */:
                return j(iD, zH);
            case R.id.rbtn_right_1st_dyna_sound_field /* 2131297845 */:
                return i(iD, zH);
            case R.id.rbtn_surround_dyna_sound_field /* 2131297856 */:
                return l(iD);
            default:
                return 0;
        }
    }

    public static int f(int i) {
        boolean zH = d.h();
        switch (i) {
            case R.id.rbtn_all_dyna_sound_field /* 2131297773 */:
                return R.drawable.bg_dyna_sound_focus_all_pickup;
            case R.id.rbtn_last_row_dyna_sound_field /* 2131297822 */:
                return R.drawable.bg_dyna_sound_focus_last_pickup;
            case R.id.rbtn_left_1st_dyna_sound_field /* 2131297826 */:
                return zH ? R.drawable.bg_dyna_sound_focus_left_1st_pickup : R.drawable.bg_dyna_sound_focus_right_1st_pickup;
            case R.id.rbtn_right_1st_dyna_sound_field /* 2131297845 */:
                return zH ? R.drawable.bg_dyna_sound_focus_right_1st_pickup : R.drawable.bg_dyna_sound_focus_left_1st_pickup;
            case R.id.rbtn_surround_dyna_sound_field /* 2131297856 */:
                return R.drawable.bg_dyna_sound_focus_surround_pickup;
            default:
                return 0;
        }
    }

    public static int g(int i) {
        boolean zH = d.h();
        switch (i) {
            case R.id.rbtn_all_dyna_sound_field_rows_3 /* 2131297774 */:
                return zH ? R.drawable.bg_dyna_sound_focus_all_rows_3 : R.drawable.bg_dyna_sound_focus_all_rows_3_mpv_r;
            case R.id.rbtn_last_row_dyna_sound_field_rows_3 /* 2131297823 */:
                return zH ? R.drawable.bg_dyna_sound_focus_last_rows_3 : R.drawable.bg_dyna_sound_focus_last_rows_3_mpv_r;
            case R.id.rbtn_left_1st_dyna_sound_field_rows_3 /* 2131297827 */:
                return zH ? R.drawable.bg_dyna_sound_focus_left_1st_rows_3 : R.drawable.bg_dyna_sound_focus_right_1st_rows_3_mpv_r;
            case R.id.rbtn_middle_dyna_sound_field_rows_3 /* 2131297831 */:
                return zH ? R.drawable.bg_dyna_sound_focus_second_rows_3 : R.drawable.bg_dyna_sound_focus_second_rows_3_mpv_r;
            case R.id.rbtn_right_1st_dyna_sound_field_rows_3 /* 2131297846 */:
                return zH ? R.drawable.bg_dyna_sound_focus_right_1st_rows_3 : R.drawable.bg_dyna_sound_focus_left_1st_rows_3_mpv_r;
            default:
                return 0;
        }
    }

    public static int h(int i) {
        boolean zH = d.h();
        switch (i) {
            case R.id.rbtn_all_dyna_sound_field_rows_3 /* 2131297774 */:
                return zH ? R.drawable.bg_dyna_sound_focus_all_rows_3 : R.drawable.bg_dyna_sound_focus_all_rows_3_r;
            case R.id.rbtn_last_row_dyna_sound_field_rows_3 /* 2131297823 */:
                return zH ? R.drawable.bg_dyna_sound_focus_last_rows_3 : R.drawable.bg_dyna_sound_focus_last_rows_3_r;
            case R.id.rbtn_left_1st_dyna_sound_field_rows_3 /* 2131297827 */:
                return zH ? R.drawable.bg_dyna_sound_focus_left_1st_rows_3 : R.drawable.bg_dyna_sound_focus_right_1st_rows_3_r;
            case R.id.rbtn_right_1st_dyna_sound_field_rows_3 /* 2131297846 */:
                return zH ? R.drawable.bg_dyna_sound_focus_right_1st_rows_3 : R.drawable.bg_dyna_sound_focus_left_1st_rows_3_r;
            case R.id.rbtn_surround_dyna_sound_field_rows_3 /* 2131297857 */:
                return zH ? R.drawable.bg_dyna_sound_focus_surround_rows_3 : R.drawable.bg_dyna_sound_focus_surround_rows_3_r;
            default:
                return 0;
        }
    }

    public static int i(int i, boolean z) {
        return (i == 182 || i == 208) ? z ? R.drawable.bg_dyna_sound_focus_right_1st_uke : R.drawable.bg_dyna_sound_focus_left_1st_uke : z ? R.drawable.bg_dyna_sound_focus_right_1st : R.drawable.bg_dyna_sound_focus_left_1st;
    }

    public static int j(int i, boolean z) {
        return (i == 182 || i == 208) ? z ? R.drawable.bg_dyna_sound_focus_left_1st_uke : R.drawable.bg_dyna_sound_focus_right_1st_uke : z ? R.drawable.bg_dyna_sound_focus_left_1st : R.drawable.bg_dyna_sound_focus_right_1st;
    }

    public static int k(int i) {
        return (i == 182 || i == 208) ? R.drawable.bg_dyna_sound_focus_last_uke : R.drawable.bg_dyna_sound_focus_last;
    }

    public static int l(int i) {
        return (i == 182 || i == 208) ? R.drawable.bg_dyna_sound_focus_surround_uke : R.drawable.bg_dyna_sound_focus_surround;
    }

    public static int m(SpeakerLayoutMode speakerLayoutMode) {
        if (speakerLayoutMode == null) {
            return R.layout.dialog_dyna_sound_field_rows_3;
        }
        int i = a.f1339a[speakerLayoutMode.ordinal()];
        return i != 1 ? i != 2 ? i != 4 ? R.layout.dialog_dyna_sound_field_rows_3 : R.layout.dialog_mpv_dyna_sound_field_rows_3 : R.layout.dialog_pa_dyna_sound_field : R.layout.dialog_dyna_sound_field;
    }

    public static int n() {
        boolean zH = d.h();
        CarModel$Brand carModel$BrandC = d.c();
        if (carModel$BrandC == null) {
            return zH ? R.drawable.bg_dyna_sound_charact_original : R.drawable.bg_dyna_sound_charact_original_r;
        }
        int i = a.f1340b[carModel$BrandC.ordinal()];
        return i != 1 ? i != 2 ? zH ? R.drawable.bg_dyna_sound_charact_original : R.drawable.bg_dyna_sound_charact_original_r : zH ? R.drawable.bg_dyna_sound_charact_original_denza : R.drawable.bg_dyna_sound_charact_original_r_denza : zH ? R.drawable.bg_dyna_sound_charact_original : R.drawable.bg_dyna_sound_charact_original_r;
    }

    public static int o() {
        boolean zH = d.h();
        int iD = d.d();
        return (iD == -1 || iD == 143) ? zH ? R.drawable.bg_dyna_sound_focus_rows_2_pa : R.drawable.bg_dyna_sound_focus_rows_2_r_pa : (iD == 182 || iD == 208) ? zH ? R.drawable.bg_dyna_sound_focus_rows_2_uke : R.drawable.bg_dyna_sound_focus_rows_2_r_uke : zH ? R.drawable.bg_dyna_sound_focus_rows_2 : R.drawable.bg_dyna_sound_focus_rows_2_r;
    }

    public static int p() {
        boolean zH = d.h();
        int iD = d.d();
        if (iD == 60 || iD == 123) {
            return zH ? R.drawable.bg_dyna_sound_focus_rows_3 : R.drawable.bg_dyna_sound_focus_rows_3_mr_r;
        }
        if (iD != 182) {
            if (iD == 196) {
                return zH ? R.drawable.bg_dyna_sound_focus_rows_3_stebu : R.drawable.bg_dyna_sound_focus_rows_3_stebu_r;
            }
            if (iD != 208) {
                return zH ? R.drawable.bg_dyna_sound_focus_rows_3 : R.drawable.bg_dyna_sound_focus_rows_3_r;
            }
        }
        return zH ? R.drawable.bg_dyna_sound_focus_rows_3 : R.drawable.bg_dyna_sound_focus_rows_3_r;
    }

    public static int q() {
        boolean zH = d.h();
        CarModel$Brand carModel$BrandC = d.c();
        if (carModel$BrandC == null) {
            return zH ? R.drawable.bg_dyna_sound_charact_soft : R.drawable.bg_dyna_sound_charact_soft_r;
        }
        int i = a.f1340b[carModel$BrandC.ordinal()];
        return i != 1 ? i != 2 ? zH ? R.drawable.bg_dyna_sound_charact_soft : R.drawable.bg_dyna_sound_charact_soft_r : zH ? R.drawable.bg_dyna_sound_charact_soft_denza : R.drawable.bg_dyna_sound_charact_soft_r_denza : zH ? R.drawable.bg_dyna_sound_charact_soft : R.drawable.bg_dyna_sound_charact_soft_r;
    }

    public static int r() {
        boolean zH = d.h();
        CarModel$Brand carModel$BrandC = d.c();
        if (carModel$BrandC == null) {
            return zH ? R.drawable.bg_dyna_sound_charact_speech : R.drawable.bg_dyna_sound_charact_speech_r;
        }
        int i = a.f1340b[carModel$BrandC.ordinal()];
        return i != 1 ? i != 2 ? zH ? R.drawable.bg_dyna_sound_charact_speech : R.drawable.bg_dyna_sound_charact_speech_r : zH ? R.drawable.bg_dyna_sound_charact_speech_denza : R.drawable.bg_dyna_sound_charact_speech_r_denza : zH ? R.drawable.bg_dyna_sound_charact_speech : R.drawable.bg_dyna_sound_charact_speech_r;
    }
}
