package spi;

import com.byd.car.locale.ccTLD;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class kg {

    @NotNull
    public static final kg a = new kg();

    private kg() {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final short a() {
        if (Intrinsics.areEqual(e(), "en")) {
            return Intrinsics.areEqual(d(), "AU") ? (short) 33 : (short) 1;
        }
        if (f()) {
            return (short) 2;
        }
        if (Intrinsics.areEqual(e(), ccTLD.SPAIN)) {
            String strD = d();
            switch (strD.hashCode()) {
                case 1692:
                    if (!strD.equals("51")) {
                        return (short) 3;
                    }
                    break;
                case 2206:
                    if (!strD.equals("EC")) {
                        return (short) 3;
                    }
                    break;
                case 2222:
                    strD.equals("ES");
                    return (short) 3;
                case 2718:
                    if (!strD.equals("US")) {
                        return (short) 3;
                    }
                    break;
                case 52471:
                    if (!strD.equals("502")) {
                        return (short) 3;
                    }
                    break;
                case 52475:
                    if (!strD.equals("506")) {
                        return (short) 3;
                    }
                    break;
                case 52753:
                    if (!strD.equals("595")) {
                        return (short) 3;
                    }
                    break;
                case 1515390:
                    if (!strD.equals("1890")) {
                        return (short) 3;
                    }
                    break;
                default:
                    return (short) 3;
            }
            return (short) 4;
        }
        if (Intrinsics.areEqual(e(), ccTLD.FRANCE)) {
            String strD2 = d();
            return (!Intrinsics.areEqual(strD2, "FR") && Intrinsics.areEqual(strD2, "CA")) ? (short) 34 : (short) 5;
        }
        if (Intrinsics.areEqual(e(), "hi")) {
            return (short) 6;
        }
        if (Intrinsics.areEqual(e(), "nb")) {
            return (short) 7;
        }
        if (Intrinsics.areEqual(e(), ccTLD.PORTUGAL)) {
            String strD3 = d();
            return (!Intrinsics.areEqual(strD3, "PT") && Intrinsics.areEqual(strD3, "BR")) ? (short) 8 : (short) 9;
        }
        if (Intrinsics.areEqual(e(), ccTLD.THAILAND)) {
            return (short) 10;
        }
        if (Intrinsics.areEqual(e(), ccTLD.UZBEKISTAN)) {
            return (short) 11;
        }
        if (!Intrinsics.areEqual(e(), "zh")) {
            if (g()) {
                return (short) 14;
            }
            return Intrinsics.areEqual(e(), "ja") ? (short) 15 : (short) -1;
        }
        String strD4 = d();
        int iHashCode = strD4.hashCode();
        if (iHashCode == 2155) {
            strD4.equals("CN");
        } else if (iHashCode != 2307) {
            if (iHashCode == 2691 && strD4.equals("TW")) {
                return (short) 13;
            }
        } else if (strD4.equals("HK")) {
            return (short) 13;
        }
        return (short) 12;
    }

    public final short b() {
        if (Intrinsics.areEqual(e(), ccTLD.RUSSIA)) {
            return (short) 16;
        }
        if (Intrinsics.areEqual(e(), ccTLD.GERMANY)) {
            return (short) 17;
        }
        if (Intrinsics.areEqual(e(), ccTLD.FINLAND)) {
            return (short) 18;
        }
        if (Intrinsics.areEqual(e(), ccTLD.NETHERLANDS)) {
            return (short) 19;
        }
        if (Intrinsics.areEqual(e(), ccTLD.EL_SALVADOR)) {
            return (short) 20;
        }
        if (Intrinsics.areEqual(e(), "da")) {
            return (short) 21;
        }
        if (Intrinsics.areEqual(e(), ccTLD.ITALY)) {
            return (short) 22;
        }
        if (Intrinsics.areEqual(e(), "ko")) {
            return (short) 23;
        }
        if (Intrinsics.areEqual(e(), ccTLD.ICELAND)) {
            return (short) 24;
        }
        if (Intrinsics.areEqual(e(), ccTLD.TURKEY)) {
            return (short) 25;
        }
        if (Intrinsics.areEqual(e(), ccTLD.HUNGARY)) {
            return (short) 26;
        }
        if (Intrinsics.areEqual(e(), ccTLD.SLOVAKIA)) {
            return (short) 27;
        }
        if (Intrinsics.areEqual(e(), "cs")) {
            return (short) 28;
        }
        if (Intrinsics.areEqual(e(), ccTLD.POLAND)) {
            return (short) 29;
        }
        if (Intrinsics.areEqual(e(), "vi")) {
            return (short) 30;
        }
        return Intrinsics.areEqual(e(), ccTLD.INDIA) ? (short) 31 : (short) -1;
    }

    public final short c() {
        if (Intrinsics.areEqual(e(), "ms")) {
            return (short) 32;
        }
        if (Intrinsics.areEqual(e(), ccTLD.CANADA)) {
            return (short) 35;
        }
        if (Intrinsics.areEqual(e(), ccTLD.CROATIA)) {
            return (short) 36;
        }
        if (Intrinsics.areEqual(e(), ccTLD.ROMANIA)) {
            return (short) 37;
        }
        if (Intrinsics.areEqual(e(), "el")) {
            return (short) 38;
        }
        if (Intrinsics.areEqual(e(), "uk")) {
            return (short) 39;
        }
        if (Intrinsics.areEqual(e(), "kk")) {
            return (short) 40;
        }
        return Intrinsics.areEqual(e(), ccTLD.BULGARIA) ? (short) 41 : (short) -1;
    }

    @NotNull
    public final String d() {
        String country = Locale.getDefault().getCountry();
        Intrinsics.checkNotNullExpressionValue(country, "getDefault().country");
        return country;
    }

    @NotNull
    public final String e() {
        String language = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "getDefault().language");
        return language;
    }

    public final boolean f() {
        return Intrinsics.areEqual(e(), ccTLD.ARGENTINA);
    }

    public final boolean g() {
        return Intrinsics.areEqual(e(), "iw");
    }
}
