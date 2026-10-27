package com.byd.vrassistant.utils;

import cerence.ark.assistant.controller.enumeration.RouteOptionEnum;
import com.byd.vrassistant.Constant;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import okio.Utf8;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* JADX INFO: loaded from: classes2.dex */
public class NavUtil {
    private static final String NAV_NEUSOFT = "com.neusoft.na.navigation";
    private static final String NAV_TELENAV = "com.telenav.app.arp";
    private static final String NAV_ZAMP = "net.zmap.znav.byd8155";
    private static final String TAG = "NEU_NavCodeUtil";

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static String codeToPackage(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    b = 0;
                }
                break;
            case 1629:
                if (str.equals("30")) {
                    b = 1;
                }
                break;
            case 1630:
                if (str.equals("31")) {
                    b = 2;
                }
                break;
            case 1631:
                if (str.equals("32")) {
                    b = 3;
                }
                break;
            case 1632:
                if (str.equals("33")) {
                    b = 4;
                }
                break;
            case 1633:
                if (str.equals("34")) {
                    b = 5;
                }
                break;
            case 1635:
                if (str.equals("36")) {
                    b = 6;
                }
                break;
            case 1638:
                if (str.equals("39")) {
                    b = 7;
                }
                break;
            case 1660:
                if (str.equals("40")) {
                    b = 8;
                }
                break;
            case 1661:
                if (str.equals("41")) {
                    b = 9;
                }
                break;
            case 1663:
                if (str.equals("43")) {
                    b = 10;
                }
                break;
            case 1664:
                if (str.equals("44")) {
                    b = 11;
                }
                break;
            case 1665:
                if (str.equals("45")) {
                    b = 12;
                }
                break;
            case 1666:
                if (str.equals("46")) {
                    b = Ascii.CR;
                }
                break;
            case 1667:
                if (str.equals("47")) {
                    b = Ascii.SO;
                }
                break;
            case 1668:
                if (str.equals("48")) {
                    b = Ascii.SI;
                }
                break;
            case 1669:
                if (str.equals("49")) {
                    b = 16;
                }
                break;
            case 1692:
                if (str.equals("51")) {
                    b = 17;
                }
                break;
            case 1693:
                if (str.equals("52")) {
                    b = Ascii.DC2;
                }
                break;
            case 1696:
                if (str.equals("55")) {
                    b = 19;
                }
                break;
            case 1697:
                if (str.equals("56")) {
                    b = Ascii.DC4;
                }
                break;
            case 1698:
                if (str.equals("57")) {
                    b = Ascii.NAK;
                }
                break;
            case 1722:
                if (str.equals("60")) {
                    b = Ascii.SYN;
                }
                break;
            case 1723:
                if (str.equals("61")) {
                    b = Ascii.ETB;
                }
                break;
            case 1724:
                if (str.equals("62")) {
                    b = Ascii.CAN;
                }
                break;
            case 1725:
                if (str.equals("63")) {
                    b = Ascii.EM;
                }
                break;
            case 1726:
                if (str.equals("64")) {
                    b = Ascii.SUB;
                }
                break;
            case 1727:
                if (str.equals("65")) {
                    b = Ascii.ESC;
                }
                break;
            case 1728:
                if (str.equals("66")) {
                    b = Ascii.FS;
                }
                break;
            case 1785:
                if (str.equals("81")) {
                    b = Ascii.GS;
                }
                break;
            case 1786:
                if (str.equals("82")) {
                    b = Ascii.RS;
                }
                break;
            case 1788:
                if (str.equals("84")) {
                    b = Ascii.US;
                }
                break;
            case 1815:
                if (str.equals("90")) {
                    b = 32;
                }
                break;
            case 1817:
                if (str.equals("92")) {
                    b = 33;
                }
                break;
            case 50703:
                if (str.equals("351")) {
                    b = 34;
                }
                break;
            case 50704:
                if (str.equals("352")) {
                    b = 35;
                }
                break;
            case 50705:
                if (str.equals("353")) {
                    b = 36;
                }
                break;
            case 50706:
                if (str.equals("354")) {
                    b = 37;
                }
                break;
            case 50707:
                if (str.equals("355")) {
                    b = 38;
                }
                break;
            case 50708:
                if (str.equals("356")) {
                    b = 39;
                }
                break;
            case 50709:
                if (str.equals("357")) {
                    b = 40;
                }
                break;
            case 50710:
                if (str.equals("358")) {
                    b = 41;
                }
                break;
            case 50711:
                if (str.equals("359")) {
                    b = ClassDefinitionUtils.OPS_aload_0;
                }
                break;
            case 50764:
                if (str.equals("370")) {
                    b = 43;
                }
                break;
            case 50765:
                if (str.equals("371")) {
                    b = 44;
                }
                break;
            case 50766:
                if (str.equals("372")) {
                    b = 45;
                }
                break;
            case 50769:
                if (str.equals("375")) {
                    b = 46;
                }
                break;
            case 50771:
                if (str.equals("377")) {
                    b = 47;
                }
                break;
            case 50773:
                if (str.equals("379")) {
                    b = TarConstants.LF_NORMAL;
                }
                break;
            case 50797:
                if (str.equals("382")) {
                    b = TarConstants.LF_LINK;
                }
                break;
            case 50800:
                if (str.equals("385")) {
                    b = TarConstants.LF_SYMLINK;
                }
                break;
            case 50801:
                if (str.equals("386")) {
                    b = TarConstants.LF_CHR;
                }
                break;
            case 51570:
                if (str.equals("420")) {
                    b = TarConstants.LF_BLK;
                }
                break;
            case 51571:
                if (str.equals("421")) {
                    b = TarConstants.LF_DIR;
                }
                break;
            case 51573:
                if (str.equals("423")) {
                    b = TarConstants.LF_FIFO;
                }
                break;
            case 52471:
                if (str.equals("502")) {
                    b = TarConstants.LF_CONTIG;
                }
                break;
            case 52475:
                if (str.equals("506")) {
                    b = 56;
                }
                break;
            case 52476:
                if (str.equals("507")) {
                    b = 57;
                }
                break;
            case 52749:
                if (str.equals("591")) {
                    b = 58;
                }
                break;
            case 52751:
                if (str.equals("593")) {
                    b = 59;
                }
                break;
            case 52753:
                if (str.equals("595")) {
                    b = 60;
                }
                break;
            case 52756:
                if (str.equals("598")) {
                    b = 61;
                }
                break;
            case 56537:
                if (str.equals("977")) {
                    b = 62;
                }
                break;
            case 56599:
                if (str.equals("997")) {
                    b = Utf8.REPLACEMENT_BYTE;
                }
                break;
            case 1515334:
                if (str.equals("1876")) {
                    b = SignedBytes.MAX_POWER_OF_TWO;
                }
                break;
        }
        switch (b) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
                RLog.i(TAG, "com.neusoft.na.navigation");
                return "com.neusoft.na.navigation";
            case 29:
                RLog.i(TAG, "net.zmap.znav.byd8155");
                return "net.zmap.znav.byd8155";
            case 30:
                RLog.i(TAG, "NAV_KOR");
                return Constant.KOR_NAVIPACKAGE;
            default:
                return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:1023:0x0ddb  */
    /* JADX WARN: Removed duplicated region for block: B:570:0x07db  */
    /* JADX WARN: Removed duplicated region for block: B:985:0x0d43  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int mappingRule(java.lang.String r23) {
        /*
            Method dump skipped, instruction units count: 6746
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.utils.NavUtil.mappingRule(java.lang.String):int");
    }

    public static String getDefaultRouteOption(String str) {
        RLog.i(TAG, "getDefaultRouteOption: packageName==" + str);
        if ("com.telenav.app.arp".equals(str) || "com.neusoft.na.navigation".equals(str)) {
            return RouteOptionEnum.AVOID_HIGHWAY;
        }
        "net.zmap.znav.byd8155".equals(str);
        return RouteOptionEnum.AVOID_HIGHWAY;
    }
}
