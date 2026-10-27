package b.b.g.o;

import com.byd.carsettings.sound.model.BSLevel;
import com.byd.carsettings.sound.model.DevialetLevel;
import com.byd.carsettings.sound.model.DiracLevel;
import com.byd.carsettings.sound.model.DynaLevel;
import com.byd.carsettings.sound.model.HarmanLevel;
import com.byd.carsettings.sound.model.Manufactor;
import com.byd.carsettings.sound.model.NonBrandLevel;
import com.byd.carsettings.sound.model.SpeakerLayoutMode;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.utils.AppConfig;

/* JADX INFO: compiled from: SoundConfigIdentifier.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1334a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f1335b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static b.b.g.o.h.a f1336c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static CarModel$Brand f1337d = CarModel$Brand.DYNASTY;

    /* JADX INFO: compiled from: SoundConfigIdentifier.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1338a;

        static {
            int[] iArr = new int[Manufactor.values().length];
            f1338a = iArr;
            try {
                iArr[Manufactor.NON_BRAND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1338a[Manufactor.DIRAC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1338a[Manufactor.DYNAUDIO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1338a[Manufactor.BS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1338a[Manufactor.HARMAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1338a[Manufactor.DEVIALET.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static b.b.g.o.h.a a() {
        return f1336c;
    }

    public static b.b.g.o.h.a b(int i, int i2) {
        f1335b = !AppConfig.d();
        f1337d = AppConfig.a();
        f1334a = i;
        b.b.g.o.h.a aVar = new b.b.g.o.h.a();
        aVar.f1344c = f(i);
        b.b.m.c.a.d("SoundConfigIdentifier", "--getAmpConfig--leftRudder: " + f1335b + ", modelCode: " + i + ", ampValue: " + i2 + ", mode: " + aVar.f1344c);
        aVar.f1343b = 0;
        j(aVar, i2);
        StringBuilder sb = new StringBuilder();
        sb.append("--getAmpConfig--1--config: ");
        sb.append(aVar);
        b.b.m.c.a.d("SoundConfigIdentifier", sb.toString());
        if (!Manufactor.supported(aVar.f1342a)) {
            aVar.f1342a = Manufactor.DYNAUDIO;
        }
        aVar.f1345d = k(i, aVar);
        b.b.m.c.a.d("SoundConfigIdentifier", "--getAmpConfig--2--config: " + aVar);
        f1336c = aVar;
        return aVar;
    }

    public static CarModel$Brand c() {
        return f1337d;
    }

    public static int d() {
        return f1334a;
    }

    public static NonBrandLevel e(int i) {
        return (i == 180 || i == 182 || i == 196 || i == 208) ? NonBrandLevel.NO_SOUND_EFFECTS : NonBrandLevel.NORMAL;
    }

    public static SpeakerLayoutMode f(int i) {
        if (i != -1) {
            if (i != 60 && i != 123) {
                if (i != 138) {
                    if (i != 143) {
                        if (i != 180) {
                            if (i != 182) {
                                if (i == 196) {
                                    return SpeakerLayoutMode.SEAT_ROWS_3_SUV;
                                }
                                if (i != 208) {
                                    return SpeakerLayoutMode.SEAT_ROWS_2;
                                }
                            }
                        }
                    }
                }
                return SpeakerLayoutMode.SEAT_ROWS_2;
            }
            return SpeakerLayoutMode.SEAT_ROWS_3_MPV;
        }
        return SpeakerLayoutMode.SEAT_ROWS_2_PICKUP_TRUCK;
    }

    public static boolean g(b.b.g.o.h.a aVar) {
        b.b.g.o.h.b bVar;
        return aVar != null && aVar.f1342a == Manufactor.DIRAC && (bVar = aVar.f1345d) != null && (bVar instanceof DiracLevel);
    }

    public static boolean h() {
        return f1335b;
    }

    public static void i(int i) {
        b.b.m.c.a.d("SoundConfigIdentifier", "onEepromAmpTypeChanged", "value: " + i);
        if (i == 1) {
            l(f1336c, i);
        }
    }

    public static void j(b.b.g.o.h.a aVar, int i) {
        if (aVar == null) {
        }
        switch (i) {
            case 0:
                aVar.f1342a = Manufactor.NON_BRAND;
                break;
            case 1:
                aVar.f1342a = Manufactor.DIRAC;
                break;
            case 2:
            case 3:
            case 8:
            case 10:
                aVar.f1342a = Manufactor.DYNAUDIO;
                break;
            case 4:
            case 5:
                aVar.f1342a = Manufactor.HARMAN;
                break;
            case 6:
                aVar.f1342a = Manufactor.DYNAUDIO;
                aVar.f1344c = SpeakerLayoutMode.SEAT_ROWS_3_MPV;
                break;
            case 7:
            case 11:
                aVar.f1342a = Manufactor.DEVIALET;
                break;
            case 9:
            case 12:
            case 13:
                aVar.f1342a = Manufactor.BS;
                break;
        }
    }

    public static b.b.g.o.h.b k(int i, b.b.g.o.h.a aVar) {
        Manufactor manufactor;
        if (aVar == null || (manufactor = aVar.f1342a) == null) {
            return null;
        }
        switch (a.f1338a[manufactor.ordinal()]) {
            case 1:
                return e(i);
            case 2:
                return DiracLevel.getDiracLevel(aVar.f1343b);
            case 3:
                return DynaLevel.getDynaLevel(aVar.f1343b);
            case 4:
                return BSLevel.getBSLevel(aVar.f1343b);
            case 5:
                return HarmanLevel.getHarmanLevel(aVar.f1343b);
            case 6:
                return DevialetLevel.getDevialetLevel(aVar.f1343b);
            default:
                return DynaLevel.getDynaLevel(aVar.f1343b);
        }
    }

    public static void l(b.b.g.o.h.a aVar, int i) {
        b.b.m.c.a.d("SoundConfigIdentifier", "updateAmpConfig", "value: " + i + ", config: " + aVar);
        if (aVar == null) {
            return;
        }
        aVar.f1342a = Manufactor.NON_BRAND;
        aVar.f1345d = k(f1334a, aVar);
        b.b.m.c.a.d("SoundConfigIdentifier", "updateAmpConfig--result--config: " + aVar);
    }
}
