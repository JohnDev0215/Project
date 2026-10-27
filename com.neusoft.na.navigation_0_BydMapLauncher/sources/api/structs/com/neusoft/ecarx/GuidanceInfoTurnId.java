package api.structs.com.neusoft.ecarx;

import com.ecarx.xui.adaptapi.hvac.specific.Define;
import com.ecarx.xui.adaptapi.wpc.specific.WPCModel;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum GuidanceInfoTurnId {
    INVALID(-1),
    TURN_LEFT(102),
    TURN_RIGHT(103),
    TURN_SLIGHT_LEFT(104),
    TURN_SLIGHT_RIGHT(105),
    TURN_HARD_LEFT(106),
    TURN_HARD_RIGHT(107),
    UTURN_LEFT(108),
    TURN_STRAIGHT_ON(109),
    TURN_VIA_WAYPOINT(110),
    TURN_RING_IN_RIGHTSIDE(111),
    TURN_RING_OUT_RIGHTSIDE(112),
    TURN_TOLL_GATE(114),
    TURN_DEST(115),
    TURN_TUNNEL(116),
    TURN_RING_IN_LEFTSIDE(117),
    TURN_RING_OUT_LEFTSIDE(118),
    UTURN_RIGHT(119),
    TURN_RING_LEFT_RIGHTSIDE(141),
    TURN_RING_RIGHT_RIGHTSIDE(142),
    TURN_RING_BACK_RIGHTSIDE(143),
    TURN_RING_LEFT_LEFTSIDE(144),
    TURN_RING_RIGHT_LEFTSIDE(145),
    TURN_RING_BACK_LEFTSIDE(146),
    FRONTLEFT_HL_FRONTRIGHT(147),
    FRONTLEFT_FRONTRIGHT_HL(148),
    STRAIGHT_HL_FRONTRIGHT(149),
    FRONTLEFT_STRAIGHT_HL(150),
    FRONTLEFT_HL_STRAIGHT(151),
    STRAIGHT_FRONTRIGHT_HL(152),
    FRONTLEFT_HL_STRAIGHT_FRONTRIGHT(157),
    FRONTLEFT_STRAIGHT_HL_FRONTRIGHT(158),
    FRONTLEFT_STRAIGHT_FRONTRIGHT_HL(159),
    STRAIGHT_HL_FRONTRIGHT_FRONTRIGHT(160),
    FRONTLEFT_FRONTLEFT_STRAIGHT_HL(161),
    STRAIGHT_FRONTRIGHT_HL_FRONTRIGHT(162),
    STRAIGHT_FRONTRIGHT_FRONTRIGHT_HL(163),
    FRONTLEFT_FRONTLEFT_HL_STRAIGHT(164),
    FRONTLEFT_HL_FRONTLEFTL_STRAIGHT(165),
    STRAIGHT_HL_STRAIGHT(166),
    STRAIGHT_STRAIGHT_HL(167),
    STRAIGHT_HL_STRAIGHT_STRAIGHT(168),
    STRAIGHT_STRAIGHT_HL_STRAIGHT(169),
    STRAIGHT_STRAIGHT_STRAIGHT_HL(170),
    FRONTLEFT_HL_FRONTLEFT(171),
    FRONTLEFT_FRONTLEFT_HL(172),
    FRONTLEFT_HL_FRONTLEFT_FRONTLEFT(173),
    FRONTLEFT_FRONTLEFT_HL_FRONTLEFT(174),
    FRONTLEFT_FRONTLEFT_FRONTLEFT_HL(175),
    FRONTRIGHT_HL_FRONTRIGHT(176),
    FRONTRIGHT_FRONTRIGHT_HL(177),
    FRONTRIGHT_HL_FRONTRIGHT_FRONTRIGHT(178),
    FRONTRIGHT_FRONTRIGHT_HL_FRONTRIGHT(179),
    FRONTRIGHT_FRONTRIGHT_FRONTRIGHT_HL(180),
    TURN_FERRY(184),
    ROUND_ABOUT_ANTICLOCKWISE_315_DEGREE(185),
    ROUND_ABOUT_ANTICLOCKWISE_225_DEGREE(186),
    ROUND_ABOUT_ANTICLOCKWISE_45_DEGREE(187),
    ROUND_ABOUT_ANTICLOCKWISE_135_DEGREE(188),
    ROUND_ABOUT_CLOCKWISE_45_DEGREE(189),
    ROUND_ABOUT_CLOCKWISE_135_DEGREE(190),
    ROUND_ABOUT_CLOCKWISE_315_DEGREE(191),
    ROUND_ABOUT_CLOCKWISE_225_DEGREE(192);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    GuidanceInfoTurnId(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static GuidanceInfoTurnId getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == -1) {
            return INVALID;
        }
        switch (iIntValue) {
            case 102:
                return TURN_LEFT;
            case 103:
                return TURN_RIGHT;
            case 104:
                return TURN_SLIGHT_LEFT;
            case 105:
                return TURN_SLIGHT_RIGHT;
            case 106:
                return TURN_HARD_LEFT;
            case 107:
                return TURN_HARD_RIGHT;
            case 108:
                return UTURN_LEFT;
            case 109:
                return TURN_STRAIGHT_ON;
            case 110:
                return TURN_VIA_WAYPOINT;
            case 111:
                return TURN_RING_IN_RIGHTSIDE;
            case 112:
                return TURN_RING_OUT_RIGHTSIDE;
            default:
                switch (iIntValue) {
                    case 114:
                        return TURN_TOLL_GATE;
                    case 115:
                        return TURN_DEST;
                    case 116:
                        return TURN_TUNNEL;
                    case 117:
                        return TURN_RING_IN_LEFTSIDE;
                    case 118:
                        return TURN_RING_OUT_LEFTSIDE;
                    case 119:
                        return UTURN_RIGHT;
                    default:
                        switch (iIntValue) {
                            case 141:
                                return TURN_RING_LEFT_RIGHTSIDE;
                            case 142:
                                return TURN_RING_RIGHT_RIGHTSIDE;
                            case 143:
                                return TURN_RING_BACK_RIGHTSIDE;
                            case 144:
                                return TURN_RING_LEFT_LEFTSIDE;
                            case 145:
                                return TURN_RING_RIGHT_LEFTSIDE;
                            case 146:
                                return TURN_RING_BACK_LEFTSIDE;
                            case 147:
                                return FRONTLEFT_HL_FRONTRIGHT;
                            case 148:
                                return FRONTLEFT_FRONTRIGHT_HL;
                            case 149:
                                return STRAIGHT_HL_FRONTRIGHT;
                            case 150:
                                return FRONTLEFT_STRAIGHT_HL;
                            case 151:
                                return FRONTLEFT_HL_STRAIGHT;
                            case 152:
                                return STRAIGHT_FRONTRIGHT_HL;
                            default:
                                switch (iIntValue) {
                                    case 157:
                                        return FRONTLEFT_HL_STRAIGHT_FRONTRIGHT;
                                    case 158:
                                        return FRONTLEFT_STRAIGHT_HL_FRONTRIGHT;
                                    case 159:
                                        return FRONTLEFT_STRAIGHT_FRONTRIGHT_HL;
                                    case 160:
                                        return STRAIGHT_HL_FRONTRIGHT_FRONTRIGHT;
                                    case 161:
                                        return FRONTLEFT_FRONTLEFT_STRAIGHT_HL;
                                    case 162:
                                        return STRAIGHT_FRONTRIGHT_HL_FRONTRIGHT;
                                    case 163:
                                        return STRAIGHT_FRONTRIGHT_FRONTRIGHT_HL;
                                    case 164:
                                        return FRONTLEFT_FRONTLEFT_HL_STRAIGHT;
                                    case 165:
                                        return FRONTLEFT_HL_FRONTLEFTL_STRAIGHT;
                                    case 166:
                                        return STRAIGHT_HL_STRAIGHT;
                                    case 167:
                                        return STRAIGHT_STRAIGHT_HL;
                                    case 168:
                                        return STRAIGHT_HL_STRAIGHT_STRAIGHT;
                                    case 169:
                                        return STRAIGHT_STRAIGHT_HL_STRAIGHT;
                                    case 170:
                                        return STRAIGHT_STRAIGHT_STRAIGHT_HL;
                                    case 171:
                                        return FRONTLEFT_HL_FRONTLEFT;
                                    case 172:
                                        return FRONTLEFT_FRONTLEFT_HL;
                                    case 173:
                                        return FRONTLEFT_HL_FRONTLEFT_FRONTLEFT;
                                    case 174:
                                        return FRONTLEFT_FRONTLEFT_HL_FRONTLEFT;
                                    case 175:
                                        return FRONTLEFT_FRONTLEFT_FRONTLEFT_HL;
                                    case 176:
                                        return FRONTRIGHT_HL_FRONTRIGHT;
                                    case 177:
                                        return FRONTRIGHT_FRONTRIGHT_HL;
                                    case 178:
                                        return FRONTRIGHT_HL_FRONTRIGHT_FRONTRIGHT;
                                    case 179:
                                        return FRONTRIGHT_FRONTRIGHT_HL_FRONTRIGHT;
                                    case 180:
                                        return FRONTRIGHT_FRONTRIGHT_FRONTRIGHT_HL;
                                    default:
                                        switch (iIntValue) {
                                            case 184:
                                                return TURN_FERRY;
                                            case 185:
                                                return ROUND_ABOUT_ANTICLOCKWISE_315_DEGREE;
                                            case 186:
                                                return ROUND_ABOUT_ANTICLOCKWISE_225_DEGREE;
                                            case 187:
                                                return ROUND_ABOUT_ANTICLOCKWISE_45_DEGREE;
                                            case 188:
                                                return ROUND_ABOUT_ANTICLOCKWISE_135_DEGREE;
                                            case 189:
                                                return ROUND_ABOUT_CLOCKWISE_45_DEGREE;
                                            case 190:
                                                return ROUND_ABOUT_CLOCKWISE_135_DEGREE;
                                            case 191:
                                                return ROUND_ABOUT_CLOCKWISE_315_DEGREE;
                                            case 192:
                                                return ROUND_ABOUT_CLOCKWISE_225_DEGREE;
                                            default:
                                                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + GuidanceInfoTurnId.class.getName());
                                        }
                                }
                        }
                }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static GuidanceInfoTurnId valueOfSerializedName(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2053383622:
                if (str.equals("FRONTLEFT_STRAIGHT_FRONTRIGHT_HL")) {
                    b = 0;
                }
                break;
            case -1943765082:
                if (str.equals("FRONTLEFT_HL_STRAIGHT")) {
                    b = 1;
                }
                break;
            case -1784752829:
                if (str.equals("TURN_RING_RIGHT_RIGHTSIDE")) {
                    b = 2;
                }
                break;
            case -1730265982:
                if (str.equals("TURN_STRAIGHT_ON")) {
                    b = 3;
                }
                break;
            case -1653139765:
                if (str.equals("FRONTLEFT_FRONTLEFT_STRAIGHT_HL")) {
                    b = 4;
                }
                break;
            case -1625951284:
                if (str.equals("FRONTLEFT_HL_STRAIGHT_FRONTRIGHT")) {
                    b = 5;
                }
                break;
            case -1618936136:
                if (str.equals("FRONTLEFT_STRAIGHT_HL_FRONTRIGHT")) {
                    b = 6;
                }
                break;
            case -1617199657:
                if (str.equals("INVALID")) {
                    b = 7;
                }
                break;
            case -1603535580:
                if (str.equals("TURN_DEST")) {
                    b = 8;
                }
                break;
            case -1603297655:
                if (str.equals("TURN_LEFT")) {
                    b = 9;
                }
                break;
            case -1575570044:
                if (str.equals("FRONTRIGHT_FRONTRIGHT_HL")) {
                    b = 10;
                }
                break;
            case -1484922895:
                if (str.equals("FRONTLEFT_FRONTLEFT_FRONTLEFT_HL")) {
                    b = 11;
                }
                break;
            case -1380163004:
                if (str.equals("ROUND_ABOUT_CLOCKWISE_315_DEGREE")) {
                    b = 12;
                }
                break;
            case -1166744563:
                if (str.equals("TURN_TOLL_GATE")) {
                    b = 13;
                }
                break;
            case -1159078327:
                if (str.equals("TURN_RING_LEFT_LEFTSIDE")) {
                    b = 14;
                }
                break;
            case -1157247501:
                if (str.equals("FRONTLEFT_FRONTLEFT_HL_FRONTLEFT")) {
                    b = 15;
                }
                break;
            case -1141122558:
                if (str.equals("FRONTRIGHT_HL_FRONTRIGHT")) {
                    b = 16;
                }
                break;
            case -1130472726:
                if (str.equals("ROUND_ABOUT_ANTICLOCKWISE_135_DEGREE")) {
                    b = 17;
                }
                break;
            case -1127076396:
                if (str.equals("UTURN_LEFT")) {
                    b = 18;
                }
                break;
            case -1058586642:
                if (str.equals("TURN_RING_RIGHT_LEFTSIDE")) {
                    b = 19;
                }
                break;
            case -1042930615:
                if (str.equals("STRAIGHT_HL_STRAIGHT_STRAIGHT")) {
                    b = 20;
                }
                break;
            case -1024948681:
                if (str.equals("FRONTLEFT_FRONTLEFT_HL_STRAIGHT")) {
                    b = 21;
                }
                break;
            case -918648156:
                if (str.equals("STRAIGHT_STRAIGHT_HL")) {
                    b = 22;
                }
                break;
            case -844372310:
                if (str.equals("TURN_HARD_RIGHT")) {
                    b = 23;
                }
                break;
            case -605027768:
                if (str.equals("TURN_RING_LEFT_RIGHTSIDE")) {
                    b = 24;
                }
                break;
            case -573968945:
                if (str.equals("UTURN_RIGHT")) {
                    b = 25;
                }
                break;
            case -522523816:
                if (str.equals("ROUND_ABOUT_ANTICLOCKWISE_45_DEGREE")) {
                    b = 26;
                }
                break;
            case -471920981:
                if (str.equals("STRAIGHT_FRONTRIGHT_HL")) {
                    b = 27;
                }
                break;
            case -386085452:
                if (str.equals("TURN_VIA_WAYPOINT")) {
                    b = 28;
                }
                break;
            case -383273231:
                if (str.equals("FRONTLEFT_HL_FRONTLEFTL_STRAIGHT")) {
                    b = 29;
                }
                break;
            case -307077624:
                if (str.equals("ROUND_ABOUT_CLOCKWISE_135_DEGREE")) {
                    b = 30;
                }
                break;
            case -290457072:
                if (str.equals("STRAIGHT_HL_STRAIGHT")) {
                    b = 31;
                }
                break;
            case -264568917:
                if (str.equals("TURN_RING_IN_LEFTSIDE")) {
                    b = 32;
                }
                break;
            case -171838103:
                if (str.equals("TURN_RING_BACK_LEFTSIDE")) {
                    b = 33;
                }
                break;
            case -163019071:
                if (str.equals("FRONTLEFT_FRONTRIGHT_HL")) {
                    b = 34;
                }
                break;
            case -65351896:
                if (str.equals("TURN_RING_BACK_RIGHTSIDE")) {
                    b = 35;
                }
                break;
            case -37473495:
                if (str.equals("STRAIGHT_HL_FRONTRIGHT")) {
                    b = 36;
                }
                break;
            case 96539746:
                if (str.equals("FRONTLEFT_FRONTLEFT_HL")) {
                    b = 37;
                }
                break;
            case 271428415:
                if (str.equals("FRONTLEFT_HL_FRONTRIGHT")) {
                    b = 38;
                }
                break;
            case 301643137:
                if (str.equals("TURN_SLIGHT_LEFT")) {
                    b = WPCModel.cSignalId0x27;
                }
                break;
            case 390652592:
                if (str.equals("FRONTRIGHT_FRONTRIGHT_FRONTRIGHT_HL")) {
                    b = 40;
                }
                break;
            case 424215140:
                if (str.equals("FRONTLEFT_HL_FRONTLEFT")) {
                    b = 41;
                }
                break;
            case 432382325:
                if (str.equals("FRONTLEFT_HL_FRONTLEFT_FRONTLEFT")) {
                    b = 42;
                }
                break;
            case 480468232:
                if (str.equals("ROUND_ABOUT_ANTICLOCKWISE_225_DEGREE")) {
                    b = 43;
                }
                break;
            case 718915568:
                if (str.equals("FRONTRIGHT_HL_FRONTRIGHT_FRONTRIGHT")) {
                    b = 44;
                }
                break;
            case 766663618:
                if (str.equals("TURN_SLIGHT_RIGHT")) {
                    b = Define.cSubCodeSetADS;
                }
                break;
            case 825100078:
                if (str.equals("FRONTRIGHT_FRONTRIGHT_HL_FRONTRIGHT")) {
                    b = Define.cSubCodeSetFraganceSwitch;
                }
                break;
            case 1219505561:
                if (str.equals("TURN_HARD_LEFT")) {
                    b = Define.cSubCodeSetFraganceType;
                }
                break;
            case 1269664457:
                if (str.equals("STRAIGHT_STRAIGHT_STRAIGHT_HL")) {
                    b = Define.cSubCodeSetFraganceLevel;
                }
                break;
            case 1303863334:
                if (str.equals("ROUND_ABOUT_CLOCKWISE_225_DEGREE")) {
                    b = Define.cSubCodeSetAcComfortLevel;
                }
                break;
            case 1305152634:
                if (str.equals("ROUND_ABOUT_CLOCKWISE_45_DEGREE")) {
                    b = 50;
                }
                break;
            case 1354960166:
                if (str.equals("TURN_RING_IN_RIGHTSIDE")) {
                    b = Define.cSubCodeSetRightWindSeat;
                }
                break;
            case 1368257130:
                if (str.equals("TURN_TUNNEL")) {
                    b = 52;
                }
                break;
            case 1397745845:
                if (str.equals("TURN_RING_OUT_RIGHTSIDE")) {
                    b = 53;
                }
                break;
            case 1460607785:
                if (str.equals("STRAIGHT_FRONTRIGHT_FRONTRIGHT_HL")) {
                    b = Define.cSubCodeSetAcSync;
                }
                break;
            case 1676473916:
                if (str.equals("TURN_RING_OUT_LEFTSIDE")) {
                    b = Define.cSubCodeSetPowerOn;
                }
                break;
            case 1723011130:
                if (str.equals("FRONTLEFT_STRAIGHT_HL")) {
                    b = Define.cSubCodeSetThirdPanel;
                }
                break;
            case 1788870761:
                if (str.equals("STRAIGHT_HL_FRONTRIGHT_FRONTRIGHT")) {
                    b = 57;
                }
                break;
            case 1831850680:
                if (str.equals("TURN_FERRY")) {
                    b = 58;
                }
                break;
            case 1843041210:
                if (str.equals("TURN_RIGHT")) {
                    b = 59;
                }
                break;
            case 1895055271:
                if (str.equals("STRAIGHT_FRONTRIGHT_HL_FRONTRIGHT")) {
                    b = Define.cSubCodeSetAcZISOpen;
                }
                break;
            case 1897855541:
                if (str.equals("STRAIGHT_STRAIGHT_HL_STRAIGHT")) {
                    b = Define.cSubCodeSetAcPTCHeating;
                }
                break;
            case 2091409190:
                if (str.equals("ROUND_ABOUT_ANTICLOCKWISE_315_DEGREE")) {
                    b = Define.cSubCodeSetAcEco;
                }
                break;
        }
        switch (b) {
            case 0:
                return FRONTLEFT_STRAIGHT_FRONTRIGHT_HL;
            case 1:
                return FRONTLEFT_HL_STRAIGHT;
            case 2:
                return TURN_RING_RIGHT_RIGHTSIDE;
            case 3:
                return TURN_STRAIGHT_ON;
            case 4:
                return FRONTLEFT_FRONTLEFT_STRAIGHT_HL;
            case 5:
                return FRONTLEFT_HL_STRAIGHT_FRONTRIGHT;
            case 6:
                return FRONTLEFT_STRAIGHT_HL_FRONTRIGHT;
            case 7:
                return INVALID;
            case 8:
                return TURN_DEST;
            case 9:
                return TURN_LEFT;
            case 10:
                return FRONTRIGHT_FRONTRIGHT_HL;
            case 11:
                return FRONTLEFT_FRONTLEFT_FRONTLEFT_HL;
            case 12:
                return ROUND_ABOUT_CLOCKWISE_315_DEGREE;
            case 13:
                return TURN_TOLL_GATE;
            case 14:
                return TURN_RING_LEFT_LEFTSIDE;
            case 15:
                return FRONTLEFT_FRONTLEFT_HL_FRONTLEFT;
            case 16:
                return FRONTRIGHT_HL_FRONTRIGHT;
            case 17:
                return ROUND_ABOUT_ANTICLOCKWISE_135_DEGREE;
            case 18:
                return UTURN_LEFT;
            case 19:
                return TURN_RING_RIGHT_LEFTSIDE;
            case 20:
                return STRAIGHT_HL_STRAIGHT_STRAIGHT;
            case 21:
                return FRONTLEFT_FRONTLEFT_HL_STRAIGHT;
            case 22:
                return STRAIGHT_STRAIGHT_HL;
            case 23:
                return TURN_HARD_RIGHT;
            case 24:
                return TURN_RING_LEFT_RIGHTSIDE;
            case 25:
                return UTURN_RIGHT;
            case 26:
                return ROUND_ABOUT_ANTICLOCKWISE_45_DEGREE;
            case 27:
                return STRAIGHT_FRONTRIGHT_HL;
            case 28:
                return TURN_VIA_WAYPOINT;
            case 29:
                return FRONTLEFT_HL_FRONTLEFTL_STRAIGHT;
            case 30:
                return ROUND_ABOUT_CLOCKWISE_135_DEGREE;
            case 31:
                return STRAIGHT_HL_STRAIGHT;
            case 32:
                return TURN_RING_IN_LEFTSIDE;
            case 33:
                return TURN_RING_BACK_LEFTSIDE;
            case 34:
                return FRONTLEFT_FRONTRIGHT_HL;
            case 35:
                return TURN_RING_BACK_RIGHTSIDE;
            case 36:
                return STRAIGHT_HL_FRONTRIGHT;
            case 37:
                return FRONTLEFT_FRONTLEFT_HL;
            case 38:
                return FRONTLEFT_HL_FRONTRIGHT;
            case 39:
                return TURN_SLIGHT_LEFT;
            case 40:
                return FRONTRIGHT_FRONTRIGHT_FRONTRIGHT_HL;
            case 41:
                return FRONTLEFT_HL_FRONTLEFT;
            case 42:
                return FRONTLEFT_HL_FRONTLEFT_FRONTLEFT;
            case 43:
                return ROUND_ABOUT_ANTICLOCKWISE_225_DEGREE;
            case 44:
                return FRONTRIGHT_HL_FRONTRIGHT_FRONTRIGHT;
            case 45:
                return TURN_SLIGHT_RIGHT;
            case 46:
                return FRONTRIGHT_FRONTRIGHT_HL_FRONTRIGHT;
            case 47:
                return TURN_HARD_LEFT;
            case 48:
                return STRAIGHT_STRAIGHT_STRAIGHT_HL;
            case 49:
                return ROUND_ABOUT_CLOCKWISE_225_DEGREE;
            case 50:
                return ROUND_ABOUT_CLOCKWISE_45_DEGREE;
            case 51:
                return TURN_RING_IN_RIGHTSIDE;
            case 52:
                return TURN_TUNNEL;
            case 53:
                return TURN_RING_OUT_RIGHTSIDE;
            case 54:
                return STRAIGHT_FRONTRIGHT_FRONTRIGHT_HL;
            case 55:
                return TURN_RING_OUT_LEFTSIDE;
            case 56:
                return FRONTLEFT_STRAIGHT_HL;
            case 57:
                return STRAIGHT_HL_FRONTRIGHT_FRONTRIGHT;
            case 58:
                return TURN_FERRY;
            case 59:
                return TURN_RIGHT;
            case 60:
                return STRAIGHT_FRONTRIGHT_HL_FRONTRIGHT;
            case 61:
                return STRAIGHT_STRAIGHT_HL_STRAIGHT;
            case 62:
                return ROUND_ABOUT_ANTICLOCKWISE_315_DEGREE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + GuidanceInfoTurnId.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
