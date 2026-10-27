package b.b.g.o;

import android.content.Context;
import android.provider.Settings;
import b.b.g.o.c;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: GeneralSoundEffect.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f1318a = {0, 1, 2, 3, 4};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Map<Integer, Integer[]> f1319b;

    /* JADX INFO: renamed from: b.b.g.o.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GeneralSoundEffect.java */
    public static class C0059b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f1320a = new b();

        public static b a() {
            return f1320a;
        }
    }

    static {
        HashMap map = new HashMap(9);
        f1319b = map;
        map.put(1, new Integer[]{0, 0, 0, 0, 0});
        f1319b.put(2, new Integer[]{-2, 0, 3, 5, 0});
        f1319b.put(3, new Integer[]{3, 0, 0, 3, 5});
        f1319b.put(4, new Integer[]{-2, 1, 3, 1, 0});
        f1319b.put(5, new Integer[]{4, 2, 0, 2, 1});
        f1319b.put(6, new Integer[]{-3, 0, -2, -1, 3});
        f1319b.put(7, new Integer[]{0, 0, 3, 0, 0});
        f1319b.put(8, new Integer[]{6, 1, 0, 3, 6});
        f1319b.put(9, new Integer[]{4, 2, 6, 2, 1});
        f1319b.put(10, new Integer[]{4, 2, 2, 1, 1});
    }

    public static int a(int i) {
        b.b.m.c.a.a("GeneralSoundEffect", "effectToIndex, effect: " + i);
        switch (i) {
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 7;
            case 9:
                return 8;
            case 10:
                return 9;
            default:
                return 0;
        }
    }

    public static int b(int i) {
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    i2 = 4;
                    if (i != 4) {
                        return 0;
                    }
                }
            }
        }
        return i2;
    }

    public static int j(int i) {
        b.b.m.c.a.a("GeneralSoundEffect", "indexToEffect, index: " + i);
        switch (i) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 8;
            case 8:
                return 9;
            case 9:
                return 10;
            default:
                return 1;
        }
    }

    public final Map<Integer, Integer> c(Context context) {
        String[] strArrSplit;
        if (context == null) {
            return null;
        }
        String string = Settings.System.getString(context.getContentResolver(), "general_custom_freq_bands");
        b.b.m.c.a.d("GeneralSoundEffect", "getCustomFreqBands", "freqBands： " + string);
        HashMap map = new HashMap();
        if (string != null && string.length() > 0 && (strArrSplit = string.split("#")) != null && strArrSplit.length > 0) {
            for (String str : strArrSplit) {
                String[] strArrSplit2 = str.split("_");
                if (strArrSplit2 != null && strArrSplit2.length > 1) {
                    map.put(Integer.valueOf(Integer.parseInt(strArrSplit2[0])), Integer.valueOf(Integer.parseInt(strArrSplit2[1])));
                }
            }
        }
        b.b.m.c.a.d("GeneralSoundEffect", "getCustomFreqBands", "map： " + map);
        return map;
    }

    public final int d(Context context) {
        int i;
        if (context == null) {
            return 0;
        }
        try {
            i = Settings.System.getInt(context.getContentResolver(), "general_sound_effect_type");
        } catch (Settings.SettingNotFoundException e2) {
            e2.printStackTrace();
            i = 1;
        }
        b.b.m.c.a.d("GeneralSoundEffect", "getEffect", "effect： " + i);
        return i;
    }

    public final Map<Integer, Integer> e(int i) {
        Integer[] numArrG = g(i);
        HashMap map = new HashMap();
        if (numArrG != null && numArrG.length > 0) {
            for (int i2 = 0; i2 < numArrG.length; i2++) {
                map.put(Integer.valueOf(b(i2)), numArrG[i2]);
            }
        }
        return map;
    }

    public Map<Integer, Integer> f(Context context, boolean z) {
        if (context == null) {
            return null;
        }
        Map<Integer, Integer> mapC = c(context);
        b.b.m.c.a.a("GeneralSoundEffect", "getSoundEffectCustomFreqBands: " + mapC);
        if (mapC == null || mapC.isEmpty()) {
            mapC = e(1);
        }
        return z ? t(mapC) : mapC;
    }

    public Integer[] g(int i) {
        return f1319b.get(Integer.valueOf(i));
    }

    public Map<Integer, Integer> h(Context context, int i, boolean z) {
        int iJ = j(i);
        Map<Integer, Integer> mapE = iJ != 1 ? e(iJ) : f(context, false);
        b.b.m.c.a.a("GeneralSoundEffect", "getSoundEffectFreqBands", "transform: " + z + ", map: " + mapE);
        return z ? t(mapE) : mapE;
    }

    public int i(Context context) {
        if (context == null) {
            return 0;
        }
        return a(d(context));
    }

    public void k() {
        c.d.a().c(0, 0);
    }

    public int l(Context context) {
        k();
        m(context, 1);
        o(context, e(1));
        return a(1);
    }

    public void m(Context context, int i) {
        p(context, j(i));
    }

    public void n(Context context, Map<Integer, Integer> map) {
        o(context, u(map));
    }

    public final void o(Context context, Map<Integer, Integer> map) {
        if (context == null || map == null) {
            return;
        }
        b.b.m.c.a.d("GeneralSoundEffect", "setCustomFreqBands", "map： " + map);
        StringBuilder sb = new StringBuilder();
        for (Integer num : map.keySet()) {
            sb.append(num);
            sb.append("_");
            sb.append(map.get(num));
            sb.append("#");
        }
        b.b.m.c.a.a("GeneralSoundEffect", "setCustomFreqBands", "str: " + sb.toString().trim());
        Settings.System.putString(context.getContentResolver(), "general_custom_freq_bands", sb.toString());
    }

    public final void p(Context context, int i) {
        if (context == null) {
            return;
        }
        b.b.m.c.a.d("GeneralSoundEffect", "setEffect", "effect： " + i);
        Settings.System.putInt(context.getContentResolver(), "general_sound_effect_type", i);
    }

    public int q(b.b.g.o.h.c cVar) {
        c.d.a().R(0, cVar.a().intValue(), cVar.b() - 10);
        return 0;
    }

    public void r(Context context, int i) {
        m(context, i);
        Map<Integer, Integer> mapH = h(context, i, false);
        b.b.m.c.a.a("GeneralSoundEffect", "setSoundEffect map: " + mapH);
        c cVarA = c.d.a();
        if (mapH == null || mapH.size() <= 0) {
            return;
        }
        for (Integer num : mapH.keySet()) {
            cVarA.R(0, num.intValue(), mapH.get(num).intValue());
        }
    }

    public void s(Context context, int i, int i2) {
        if (context == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append("_");
        sb.append(i2);
        b.b.m.c.a.d("GeneralSoundEffect", "setSoundField", "soundField： " + sb.toString());
        Settings.System.putString(context.getContentResolver(), "general_custom_sound_field", sb.toString());
    }

    public final Map<Integer, Integer> t(Map<Integer, Integer> map) {
        for (Integer num : map.keySet()) {
            map.put(num, Integer.valueOf(map.get(num).intValue() + 10));
        }
        return map;
    }

    public final Map<Integer, Integer> u(Map<Integer, Integer> map) {
        HashMap map2 = new HashMap();
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            map2.put(it.next(), Integer.valueOf(map.get(r2).intValue() - 10));
        }
        return map2;
    }

    public b() {
    }
}
