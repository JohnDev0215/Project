package b.b.g.r;

import com.byd.carsettings.sound.model.SpeakerLayoutMode;

/* JADX INFO: compiled from: SoundUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w {
    public static int a(int i) {
        b.b.m.c.a.a("SoundUtils", "bsEffectToIndex, type: " + i);
        if (i == 18) {
            return 3;
        }
        if (i != 20) {
            return i != 21 ? 0 : 2;
        }
        return 1;
    }

    public static int b(int i) {
        b.b.m.c.a.a("SoundUtils", "bsIndexToEffect, index: " + i);
        if (i == 1) {
            return 20;
        }
        if (i != 2) {
            return i != 3 ? 19 : 18;
        }
        return 21;
    }

    public static int c(b.b.g.o.h.a aVar, int i) {
        b.b.m.c.a.a("SoundUtils", "bsSoundFieldIndexToType, config: " + aVar + ",  index: " + i);
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? 1 : 6;
        }
        return 4;
    }

    public static int d(b.b.g.o.h.a aVar, int i) {
        b.b.m.c.a.a("SoundUtils", "bsSoundFieldTypeToIndex, config: " + aVar + ", type: " + i);
        if (i == 2) {
            return 1;
        }
        if (i != 4) {
            return i != 6 ? 0 : 3;
        }
        return 2;
    }

    public static int e(int i) {
        b.b.m.c.a.a("SoundUtils", "diracSoundEffectToIndex, effect: " + i);
        switch (i) {
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 9;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 7;
            case 9:
                return 8;
            case 10:
                return 4;
            default:
                return 0;
        }
    }

    public static int f(int i) {
        b.b.m.c.a.a("SoundUtils", "dynaEffectToIndex, type: " + i);
        switch (i) {
            case 18:
                return 4;
            case 19:
            default:
                return 0;
            case 20:
                return 1;
            case 21:
                return 2;
            case 22:
                return 3;
        }
    }

    public static int g(int i) {
        b.b.m.c.a.a("SoundUtils", "dynaIndexToEffect, index: " + i);
        if (i == 1) {
            return 20;
        }
        if (i == 2) {
            return 21;
        }
        if (i != 3) {
            return i != 4 ? 19 : 18;
        }
        return 22;
    }

    public static int h(b.b.g.o.h.a aVar, int i) {
        b.b.m.c.a.a("SoundUtils", "dynaSoundFieldIndexToType, config: " + aVar + ",  index: " + i);
        if (aVar == null || !SpeakerLayoutMode.SEAT_ROWS_3_MPV.equals(aVar.f1344c)) {
            if (i == 1) {
                return 2;
            }
            if (i == 2) {
                return 3;
            }
            if (i == 3) {
                return 4;
            }
            if (i != 4) {
                return i != 5 ? 1 : 6;
            }
            return 5;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        if (i == 3) {
            return 5;
        }
        if (i != 4) {
            return i != 5 ? 1 : 6;
        }
        return 4;
    }

    public static int i(b.b.g.o.h.a aVar, int i) {
        b.b.m.c.a.a("SoundUtils", "dynaSoundFieldTypeToIndex, config: " + aVar + ", type: " + i);
        if (aVar == null || !SpeakerLayoutMode.SEAT_ROWS_3_MPV.equals(aVar.f1344c)) {
            if (i == 2) {
                return 1;
            }
            if (i == 3) {
                return 2;
            }
            if (i == 4) {
                return 3;
            }
            if (i != 5) {
                return i != 6 ? 0 : 5;
            }
            return 4;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        if (i == 4) {
            return 4;
        }
        if (i != 5) {
            return i != 6 ? 0 : 5;
        }
        return 3;
    }

    public static int j(int i) {
        if (i != 1) {
            return i != 2 ? 17 : 19;
        }
        return 18;
    }

    public static int k(int i) {
        if (i != 18) {
            return i != 19 ? 0 : 2;
        }
        return 1;
    }
}
