package defpackage;

import android.media.MediaCodecInfo;
import com.android.webview.R;
import java.util.ArrayList;
import java.util.List;
import org.chromium.media.CodecProfileLevelList$CodecProfileLevelAdapter;

/* JADX INFO: renamed from: dH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0653dH {
    public final List a = new ArrayList();

    public static int b(int i, int i2) {
        if (i == 1) {
            switch (i2) {
                case 1:
                    return 10;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    return 11;
                case 8:
                    return 12;
                case 16:
                    return 13;
                case 32:
                    return 20;
                case 64:
                    return 21;
                case 128:
                    return 22;
                case 256:
                    return 30;
                case 512:
                    return 31;
                case 1024:
                    return 32;
                case 2048:
                    return 40;
                case 4096:
                    return 41;
                case 8192:
                    return 42;
                case 16384:
                    return 50;
                case 32768:
                    return 51;
                case R.anim.abc_fade_in /* 65536 */:
                    return 52;
                default:
                    throw new C0597cH(null);
            }
        }
        if (i == 6) {
            if (i2 == 1) {
                return 0;
            }
            if (i2 == 2) {
                return 1;
            }
            if (i2 == 4) {
                return 2;
            }
            if (i2 == 8) {
                return 3;
            }
            throw new C0597cH(null);
        }
        if (i == 7) {
            if (i2 == 1) {
                return 10;
            }
            if (i2 == 2) {
                return 11;
            }
            switch (i2) {
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    return 20;
                case 8:
                    return 21;
                case 16:
                    return 30;
                case 32:
                    return 31;
                case 64:
                    return 40;
                case 128:
                    return 41;
                case 256:
                    return 50;
                case 512:
                    return 51;
                case 1024:
                    return 52;
                case 2048:
                    return 60;
                case 4096:
                    return 61;
                case 8192:
                    return 62;
                default:
                    throw new C0597cH(null);
            }
        }
        if (i != 8) {
            throw new C0597cH(null);
        }
        if (i2 == 1 || i2 == 2) {
            return 30;
        }
        switch (i2) {
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
            case 8:
                return 60;
            case 16:
            case 32:
                return 63;
            case 64:
            case 128:
                return 90;
            case 256:
            case 512:
                return 93;
            case 1024:
            case 2048:
                return 120;
            case 4096:
            case 8192:
                return 123;
            case 16384:
            case 32768:
                return 150;
            case R.anim.abc_fade_in /* 65536 */:
            case R.animator.design_appbar_state_list_animator /* 131072 */:
                return 153;
            case R.bool.abc_action_bar_embed_tabs /* 262144 */:
            case R.font.accent_font /* 524288 */:
                return 156;
            case R.raw.empty /* 1048576 */:
            case 2097152:
                return 180;
            case 4194304:
            case 8388608:
                return 183;
            case 16777216:
            case 33554432:
                return 186;
            default:
                throw new C0597cH(null);
        }
    }

    public static int c(int i, int i2) {
        if (i == 1) {
            if (i2 == 1) {
                return 0;
            }
            if (i2 == 2) {
                return 1;
            }
            if (i2 == 4) {
                return 2;
            }
            if (i2 == 8) {
                return 3;
            }
            if (i2 == 16) {
                return 4;
            }
            if (i2 == 32) {
                return 5;
            }
            if (i2 == 64) {
                return 6;
            }
            throw new C0597cH(null);
        }
        if (i == 6) {
            if (i2 == 1) {
                return 11;
            }
            throw new C0597cH(null);
        }
        if (i != 7) {
            if (i != 8) {
                throw new C0597cH(null);
            }
            if (i2 == 1) {
                return 16;
            }
            if (i2 == 2 || i2 == 4096) {
                return 17;
            }
            throw new C0597cH(null);
        }
        if (i2 == 1) {
            return 12;
        }
        if (i2 == 2) {
            return 13;
        }
        if (i2 == 4) {
            return 14;
        }
        if (i2 == 8) {
            return 15;
        }
        if (i2 == 4096) {
            return 14;
        }
        if (i2 == 8192) {
            return 15;
        }
        throw new C0597cH(null);
    }

    public boolean a(String str, MediaCodecInfo.CodecProfileLevel codecProfileLevel) {
        int i;
        try {
            if (str.endsWith("vp9")) {
                i = 7;
            } else if (str.endsWith("vp8")) {
                i = 6;
            } else if (str.endsWith("avc")) {
                i = 1;
            } else {
                if (!str.endsWith("hevc")) {
                    throw new C0597cH(null);
                }
                i = 8;
            }
            this.a.add(new CodecProfileLevelList$CodecProfileLevelAdapter(i, c(i, codecProfileLevel.profile), b(i, codecProfileLevel.level)));
            return true;
        } catch (C0597cH unused) {
            return false;
        }
    }
}
