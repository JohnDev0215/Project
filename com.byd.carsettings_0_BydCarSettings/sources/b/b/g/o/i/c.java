package b.b.g.o.i;

import android.content.Context;
import androidx.fragment.app.Fragment;
import b.b.g.o.d;
import b.b.g.q.f.b.f;
import b.b.g.q.f.b.k;
import b.b.g.q.f.b.l;
import b.b.g.q.f.b.m;
import b.b.g.q.f.b.n;
import b.b.g.q.f.d.e;
import b.b.g.q.f.d.g;
import b.b.g.q.f.d.h;
import b.b.g.q.f.d.i;
import com.byd.carsettings.R;
import com.byd.carsettings.sound.model.DiracLevel;
import com.byd.carsettings.sound.model.DiracMode;
import com.byd.carsettings.sound.model.Manufactor;
import com.byd.carsettings.sound.model.NonBrandLevel;
import com.byd.carsettings.ui.dialog.CustomViewDialog;

/* JADX INFO: compiled from: SoundResSelector.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: compiled from: SoundResSelector.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1355a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f1356b;

        static {
            int[] iArr = new int[DiracLevel.values().length];
            f1356b = iArr;
            try {
                iArr[DiracLevel.DIRAC_SIMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1356b[DiracLevel.DIRAC_PERFORMANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1356b[DiracLevel.DIRAC_LIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Manufactor.values().length];
            f1355a = iArr2;
            try {
                iArr2[Manufactor.NON_BRAND.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1355a[Manufactor.DIRAC.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1355a[Manufactor.DYNAUDIO.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1355a[Manufactor.BS.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1355a[Manufactor.HARMAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static b.b.g.o.h.a a() {
        return d.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x004c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int b(com.byd.carsettings.sound.model.DiracMode r4) {
        /*
            b.b.g.o.h.a r0 = b.b.g.o.d.a()
            boolean r1 = b.b.g.o.d.g(r0)
            r2 = 2131232014(0x7f08050e, float:1.8080125E38)
            if (r1 == 0) goto L5d
            b.b.g.o.h.b r0 = r0.f1345d
            com.byd.carsettings.sound.model.DiracLevel r0 = (com.byd.carsettings.sound.model.DiracLevel) r0
            int[] r1 = b.b.g.o.i.c.a.f1356b
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            r3 = 2131232012(0x7f08050c, float:1.8080121E38)
            if (r0 == r1) goto L26
            r1 = 2
            if (r0 == r1) goto L30
            r1 = 3
            if (r0 == r1) goto L48
            goto L5d
        L26:
            com.byd.carsettings.sound.model.DiracMode r0 = com.byd.carsettings.sound.model.DiracMode.SIMPLE_OPEN
            if (r4 != r0) goto L2b
            return r2
        L2b:
            com.byd.carsettings.sound.model.DiracMode r0 = com.byd.carsettings.sound.model.DiracMode.SIMPLE_CLOSE
            if (r4 != r0) goto L30
            return r3
        L30:
            com.byd.carsettings.sound.model.DiracMode r0 = com.byd.carsettings.sound.model.DiracMode.PERFORMANCE_DYNAMIC
            if (r4 != r0) goto L38
            r4 = 2131232017(0x7f080511, float:1.8080131E38)
            return r4
        L38:
            com.byd.carsettings.sound.model.DiracMode r0 = com.byd.carsettings.sound.model.DiracMode.PERFORMANCE_NICAM
            if (r4 != r0) goto L40
            r4 = 2131232018(0x7f080512, float:1.8080133E38)
            return r4
        L40:
            com.byd.carsettings.sound.model.DiracMode r0 = com.byd.carsettings.sound.model.DiracMode.PERFORMANCE_CLOSE
            if (r4 != r0) goto L48
            r4 = 2131232016(0x7f080510, float:1.808013E38)
            return r4
        L48:
            com.byd.carsettings.sound.model.DiracMode r0 = com.byd.carsettings.sound.model.DiracMode.LIVE_HIFI
            if (r4 != r0) goto L50
            r4 = 2131232013(0x7f08050d, float:1.8080123E38)
            return r4
        L50:
            com.byd.carsettings.sound.model.DiracMode r0 = com.byd.carsettings.sound.model.DiracMode.LIVE_STAGE
            if (r4 != r0) goto L58
            r4 = 2131232015(0x7f08050f, float:1.8080127E38)
            return r4
        L58:
            com.byd.carsettings.sound.model.DiracMode r0 = com.byd.carsettings.sound.model.DiracMode.LIVE_CLOSE
            if (r4 != r0) goto L5d
            return r3
        L5d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.g.o.i.c.b(com.byd.carsettings.sound.model.DiracMode):int");
    }

    public static i c(Context context, Fragment fragment, DiracLevel diracLevel) {
        if (diracLevel == null) {
            return null;
        }
        int i = a.f1356b[diracLevel.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? new b.b.g.q.f.d.b(context) : new b.b.g.q.f.d.b(context) : new b.b.g.q.f.d.d(context) : new e(context);
    }

    public static b.b.m.e.a d(Context context, CustomViewDialog customViewDialog) {
        Manufactor manufactor;
        b.b.g.o.h.a aVarA = d.a();
        if (aVarA != null && (manufactor = aVarA.f1342a) != null) {
            int i = a.f1355a[manufactor.ordinal()];
            if (i == 1) {
                return new m(context, customViewDialog);
            }
            if (i == 3) {
                return new k(context, customViewDialog);
            }
            if (i == 4) {
                return new b.b.g.q.f.b.e(context, customViewDialog);
            }
        }
        return new k(context, customViewDialog);
    }

    public static i e(Context context, Fragment fragment, NonBrandLevel nonBrandLevel) {
        return new h(context, nonBrandLevel);
    }

    public static int f() {
        return R.drawable.img_carsetting_non_brand_bg;
    }

    public static int g() {
        boolean zH = d.h();
        int iD = d.d();
        return (iD == -1 || iD == 143) ? zH ? R.drawable.bg_dyna_sound_focus_rows_2_pa : R.drawable.bg_dyna_sound_focus_rows_2_r_pa : (iD == 182 || iD == 208) ? zH ? R.drawable.bg_dyna_sound_focus_rows_2_uke : R.drawable.bg_dyna_sound_focus_rows_2_r_uke : zH ? R.drawable.bg_dyna_sound_focus_rows_2 : R.drawable.bg_dyna_sound_focus_rows_2_r;
    }

    public static int h() {
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

    public static b.b.m.e.a i(Context context, CustomViewDialog customViewDialog) {
        Manufactor manufactor;
        b.b.g.o.h.a aVarA = d.a();
        if (aVarA != null && (manufactor = aVarA.f1342a) != null) {
            int i = a.f1355a[manufactor.ordinal()];
            if (i == 1) {
                return new n(context, customViewDialog);
            }
            if (i == 3) {
                return new l(context, customViewDialog);
            }
            if (i == 4) {
                return new f(context, customViewDialog);
            }
        }
        return new l(context, customViewDialog);
    }

    public static int j() {
        Manufactor manufactor;
        b.b.g.o.h.a aVarA = d.a();
        b.b.m.c.a.a("SoundResSelector", "getSoundManufactorPagerImg", "config: " + aVarA);
        if (aVarA == null || (manufactor = aVarA.f1342a) == null) {
            return R.drawable.img_carsetting_dynaudio;
        }
        int i = a.f1355a[manufactor.ordinal()];
        return i != 1 ? i != 2 ? i != 4 ? i != 5 ? R.drawable.img_carsetting_dynaudio : R.drawable.img_carsetting_harman : R.drawable.img_sound_bs : b(DiracMode.LIVE_CLOSE) : f();
    }

    public static i k(Context context, Fragment fragment) {
        b.b.g.o.h.a aVarA = d.a();
        b.b.m.c.a.a("SoundResSelector", "getSoundManufactorPager", "config: " + aVarA);
        if (aVarA != null && aVarA.f1342a != null) {
            b.b.g.o.h.b bVar = aVarA.f1345d;
            b.b.m.c.a.a("SoundResSelector", "getSoundManufactorPager", "ampLevel: " + bVar);
            int i = a.f1355a[aVarA.f1342a.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            return new b.b.g.q.f.d.a(context, fragment);
                        }
                        if (i == 5) {
                            return new g(context);
                        }
                    }
                }
                return new b.b.g.q.f.d.f(context, fragment);
            }
            if (bVar != null && (bVar instanceof NonBrandLevel)) {
                return e(context, fragment, (NonBrandLevel) bVar);
            }
            if (bVar != null && (bVar instanceof DiracLevel)) {
                return c(context, fragment, (DiracLevel) bVar);
            }
            return new b.b.g.q.f.d.f(context, fragment);
        }
        return new b.b.g.q.f.d.f(context, fragment);
    }

    public static boolean l() {
        return d.h();
    }
}
