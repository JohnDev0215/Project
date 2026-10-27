package g5;

import com.byd.uereport.R;
import java.io.File;

/* JADX INFO: compiled from: VoiceResource.java */
/* JADX INFO: loaded from: classes2.dex */
public class p {
    public static String a() {
        String str = "/product/media/audio/ue/" + g.a() + "/";
        if (new File(str).exists()) {
            return str;
        }
        a.i("the current language is not exist, play default(en) res.");
        return "/product/media/audio/ue/default/";
    }

    public static int b(int i8) {
        if (i8 == 41) {
            return R.raw.line_00013;
        }
        if (i8 == 42) {
            return R.raw.line_00014;
        }
        if (i8 == 203) {
            return R.raw.line_00002;
        }
        switch (i8) {
            case 7:
                return R.raw.line_00006;
            case 8:
                return R.raw.line_00007;
            case 9:
                return R.raw.line_00008;
            case 10:
                return R.raw.line_00009;
            default:
                switch (i8) {
                    case 36:
                        return R.raw.line_00010;
                    case 37:
                        return R.raw.line_00011;
                    case 38:
                        return R.raw.line_00012;
                    default:
                        switch (i8) {
                            case 101:
                                return R.raw.line_00001;
                            case 102:
                                return R.raw.line_00004;
                            case 103:
                                return R.raw.line_00002;
                            case 104:
                                return R.raw.line_00015;
                            default:
                                switch (i8) {
                                    case 106:
                                        return R.raw.line_00036;
                                    case 107:
                                        return R.raw.line_00037;
                                    case 108:
                                        return R.raw.line_00038;
                                    case 109:
                                        return R.raw.line_00039;
                                    case 110:
                                        return R.raw.line_00040;
                                    case 111:
                                        return R.raw.line_00035;
                                    default:
                                        switch (i8) {
                                            case 120:
                                                return R.raw.line_00060;
                                            case 121:
                                                return R.raw.line_00062;
                                            case 122:
                                                return R.raw.line_00063;
                                            case 123:
                                                return R.raw.line_00064;
                                            case 124:
                                                return R.raw.line_00065;
                                            case 125:
                                                return R.raw.line_00068;
                                            case 126:
                                                return R.raw.line_00066;
                                            case 127:
                                                return R.raw.line_00067;
                                            case 128:
                                                return R.raw.line_00061;
                                            case 129:
                                                return R.raw.line_00097;
                                            case 130:
                                                return R.raw.line_00020;
                                            case 131:
                                                return R.raw.line_00033;
                                            case 132:
                                                return R.raw.line_00034;
                                            default:
                                                switch (i8) {
                                                    case 135:
                                                        return R.raw.line_00095;
                                                    case 136:
                                                        return R.raw.line_00096;
                                                    case 137:
                                                        return R.raw.line_00098;
                                                    default:
                                                        switch (i8) {
                                                            case 140:
                                                                return R.raw.line_00053;
                                                            case 141:
                                                                return R.raw.line_00054;
                                                            case 142:
                                                                return R.raw.line_00055;
                                                            case 143:
                                                                return R.raw.line_00056;
                                                            case 144:
                                                                return R.raw.line_00057;
                                                            case 145:
                                                                return R.raw.line_00058;
                                                            case 146:
                                                                return R.raw.line_00059;
                                                            default:
                                                                return -1;
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public static int c(int i8) {
        switch (i8) {
            case 71:
                return R.raw.line_00071;
            case 72:
                return R.raw.line_00072;
            case 73:
                return R.raw.line_00073;
            case 74:
                return R.raw.line_00074;
            case 75:
                return R.raw.line_00075;
            case 76:
                return R.raw.line_00076;
            case 77:
                return R.raw.line_00077;
            case 78:
                return R.raw.line_00078;
            case 79:
                return R.raw.line_00079;
            case 80:
                return R.raw.line_00080;
            case 81:
                return R.raw.line_00081;
            case 82:
                return R.raw.line_00082;
            case 83:
                return R.raw.line_00083;
            case 84:
                return R.raw.line_00084;
            case 85:
                return R.raw.line_00085;
            case 86:
                return R.raw.line_00086;
            case 87:
                return R.raw.line_00087;
            case 88:
                return R.raw.line_00088;
            case 89:
                return R.raw.line_00089;
            case 90:
                return R.raw.line_00090;
            case 91:
                return R.raw.line_00091;
            case 92:
                return R.raw.line_00092;
            case 93:
                return R.raw.line_00093;
            case 94:
                return R.raw.line_00094;
            default:
                return -1;
        }
    }

    public static String d(int i8) {
        if (i8 == 41) {
            return a() + "line_00013.wav";
        }
        if (i8 == 42) {
            return a() + "line_00014.wav";
        }
        if (i8 != 203) {
            switch (i8) {
                case 7:
                    return a() + "line_00006.wav";
                case 8:
                    return a() + "line_00007.wav";
                case 9:
                    return a() + "line_00008.wav";
                case 10:
                    return a() + "line_00009.wav";
                default:
                    switch (i8) {
                        case 36:
                            return a() + "line_00010.wav";
                        case 37:
                            return a() + "line_00011.wav";
                        case 38:
                            return a() + "line_00012.wav";
                        default:
                            switch (i8) {
                                case 101:
                                    return a() + "line_00001.wav";
                                case 102:
                                    return a() + "line_00004.wav";
                                case 103:
                                    break;
                                case 104:
                                    return a() + "line_00015.wav";
                                default:
                                    switch (i8) {
                                        case 106:
                                            return a() + "line_00036.wav";
                                        case 107:
                                            return a() + "line_00037.wav";
                                        case 108:
                                            return a() + "line_00038.wav";
                                        case 109:
                                            return a() + "line_00039.wav";
                                        case 110:
                                            return a() + "line_00040.wav";
                                        case 111:
                                            return a() + "line_00035.wav";
                                        default:
                                            switch (i8) {
                                                case 120:
                                                    return a() + "line_00060.wav";
                                                case 121:
                                                    return a() + "line_00062.wav";
                                                case 122:
                                                    return a() + "line_00063.wav";
                                                case 123:
                                                    return a() + "line_00064.wav";
                                                case 124:
                                                    return a() + "line_00065.wav";
                                                case 125:
                                                    return a() + "line_00068.wav";
                                                case 126:
                                                    return a() + "line_00066.wav";
                                                case 127:
                                                    return a() + "line_00067.wav";
                                                case 128:
                                                    return a() + "line_00061.wav";
                                                case 129:
                                                    return a() + "line_00097.wav";
                                                case 130:
                                                    return a() + "line_00020.wav";
                                                case 131:
                                                    return a() + "line_00033.wav";
                                                case 132:
                                                    return a() + "line_00034.wav";
                                                default:
                                                    switch (i8) {
                                                        case 135:
                                                            return a() + "line_00095.wav";
                                                        case 136:
                                                            return a() + "line_00096.wav";
                                                        case 137:
                                                            return a() + "line_00098.wav";
                                                        default:
                                                            switch (i8) {
                                                                case 140:
                                                                    return a() + "line_00053.wav";
                                                                case 141:
                                                                    return a() + "line_00054.wav";
                                                                case 142:
                                                                    return a() + "line_00055.wav";
                                                                case 143:
                                                                    return a() + "line_00056.wav";
                                                                case 144:
                                                                    return a() + "line_00057.wav";
                                                                case 145:
                                                                    return a() + "line_00058.wav";
                                                                case 146:
                                                                    return a() + "line_00059.wav";
                                                                default:
                                                                    return null;
                                                            }
                                                    }
                                            }
                                    }
                            }
                            break;
                    }
                    break;
            }
        }
        return a() + "line_00002.wav";
    }

    public static boolean e() {
        return new File("/product/media/audio/ue/default").exists();
    }
}
