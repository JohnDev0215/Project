package s2;

import B2.N;
import F1.C0254m;
import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.util.Pair;
import d5.C1077F;
import d5.I;
import d5.d0;
import e2.C1200h;
import e2.C1208p;
import e2.L;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import h2.AbstractC1348e;
import java.util.List;
import java.util.Objects;
import n2.C1907g;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22095a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f22096b;
    public final String c;
    public final MediaCodecInfo.CodecCapabilities d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f22097e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f22098f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f22099g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f22100h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f22101i;

    public n(String str, String str2, String str3, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z6, boolean z10, boolean z11, boolean z12) {
        str.getClass();
        this.f22095a = str;
        this.f22096b = str2;
        this.c = str3;
        this.d = codecCapabilities;
        this.f22099g = z6;
        this.f22097e = z10;
        this.f22098f = z11;
        this.f22100h = z12;
        this.f22101i = L.k(str2);
    }

    public static boolean a(MediaCodecInfo.VideoCapabilities videoCapabilities, int i7, int i10, double d) {
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        Point point = new Point(AbstractC1343A.f(i7, widthAlignment) * widthAlignment, AbstractC1343A.f(i10, heightAlignment) * heightAlignment);
        int i11 = point.x;
        int i12 = point.y;
        return (d == -1.0d || d < 1.0d) ? videoCapabilities.isSizeSupported(i11, i12) : videoCapabilities.areSizeAndRateSupported(i11, i12, Math.floor(d));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static s2.n h(java.lang.String r10, java.lang.String r11, java.lang.String r12, android.media.MediaCodecInfo.CodecCapabilities r13, boolean r14, boolean r15, boolean r16, boolean r17) {
        /*
            r1 = r10
            r4 = r13
            s2.n r9 = new s2.n
            r0 = 0
            r2 = 1
            if (r4 == 0) goto L3b
            java.lang.String r3 = "adaptive-playback"
            boolean r3 = r13.isFeatureSupported(r3)
            if (r3 == 0) goto L3b
            int r3 = h2.AbstractC1343A.f15195a
            r5 = 22
            if (r3 > r5) goto L39
            java.lang.String r3 = h2.AbstractC1343A.d
            java.lang.String r5 = "ODROID-XU3"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L28
            java.lang.String r5 = "Nexus 10"
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L39
        L28:
            java.lang.String r3 = "OMX.Exynos.AVC.Decoder"
            boolean r3 = r3.equals(r10)
            if (r3 != 0) goto L3b
            java.lang.String r3 = "OMX.Exynos.AVC.Decoder.secure"
            boolean r3 = r3.equals(r10)
            if (r3 == 0) goto L39
            goto L3b
        L39:
            r6 = r2
            goto L3c
        L3b:
            r6 = r0
        L3c:
            if (r4 == 0) goto L44
            java.lang.String r3 = "tunneled-playback"
            boolean r3 = r13.isFeatureSupported(r3)
        L44:
            if (r17 != 0) goto L53
            if (r4 == 0) goto L51
            java.lang.String r3 = "secure-playback"
            boolean r3 = r13.isFeatureSupported(r3)
            if (r3 == 0) goto L51
            goto L53
        L51:
            r7 = r0
            goto L54
        L53:
            r7 = r2
        L54:
            int r3 = h2.AbstractC1343A.f15195a
            r5 = 35
            if (r3 < r5) goto L66
            if (r4 == 0) goto L66
            java.lang.String r3 = "detached-surface"
            boolean r3 = r13.isFeatureSupported(r3)
            if (r3 == 0) goto L66
            r8 = r2
            goto L67
        L66:
            r8 = r0
        L67:
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: s2.n.h(java.lang.String, java.lang.String, java.lang.String, android.media.MediaCodecInfo$CodecCapabilities, boolean, boolean, boolean, boolean):s2.n");
    }

    public final C1907g b(C1208p c1208p, C1208p c1208p2) {
        int i7 = !Objects.equals(c1208p.f14316n, c1208p2.f14316n) ? 8 : 0;
        if (this.f22101i) {
            if (c1208p.f14325x != c1208p2.f14325x) {
                i7 |= 1024;
            }
            if (!this.f22097e && (c1208p.f14322u != c1208p2.f14322u || c1208p.f14323v != c1208p2.f14323v)) {
                i7 |= 512;
            }
            C1200h c1200h = c1208p.B;
            boolean zE = C1200h.e(c1200h);
            C1200h c1200h2 = c1208p2.B;
            if ((!zE || !C1200h.e(c1200h2)) && !Objects.equals(c1200h, c1200h2)) {
                i7 |= 2048;
            }
            if (AbstractC1343A.d.startsWith("SM-T230") && "OMX.MARVELL.VIDEO.HW.CODA7542DECODER".equals(this.f22095a) && !c1208p.c(c1208p2)) {
                i7 |= 2;
            }
            if (i7 == 0) {
                return new C1907g(this.f22095a, c1208p, c1208p2, c1208p.c(c1208p2) ? 3 : 2, 0);
            }
        } else {
            if (c1208p.f14295C != c1208p2.f14295C) {
                i7 |= 4096;
            }
            if (c1208p.f14296D != c1208p2.f14296D) {
                i7 |= 8192;
            }
            if (c1208p.f14297E != c1208p2.f14297E) {
                i7 |= 16384;
            }
            String str = this.f22096b;
            if (i7 == 0 && "audio/mp4a-latm".equals(str)) {
                Pair pairD = v.d(c1208p);
                Pair pairD2 = v.d(c1208p2);
                if (pairD != null && pairD2 != null) {
                    int iIntValue = ((Integer) pairD.first).intValue();
                    int iIntValue2 = ((Integer) pairD2.first).intValue();
                    if (iIntValue == 42 && iIntValue2 == 42) {
                        return new C1907g(this.f22095a, c1208p, c1208p2, 3, 0);
                    }
                }
            }
            if (!c1208p.c(c1208p2)) {
                i7 |= 32;
            }
            if ("audio/opus".equals(str)) {
                i7 |= 2;
            }
            if (i7 == 0) {
                return new C1907g(this.f22095a, c1208p, c1208p2, 1, 0);
            }
        }
        return new C1907g(this.f22095a, c1208p, c1208p2, 0, i7);
    }

    public final boolean c(C1208p c1208p, boolean z6) {
        MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr;
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        String strA;
        Pair pairD = v.d(c1208p);
        String str = this.c;
        String str2 = c1208p.f14316n;
        if (str2 != null && str2.equals("video/mv-hevc") && str.equals("video/hevc")) {
            List list = c1208p.f14318q;
            int i7 = 0;
            loop0: while (true) {
                if (i7 >= list.size()) {
                    strA = null;
                    break;
                }
                byte[] bArr = (byte[]) list.get(i7);
                int length = bArr.length;
                if (length > 3) {
                    boolean[] zArr = new boolean[3];
                    C1077F c1077fU = I.u();
                    int i10 = 0;
                    while (i10 < bArr.length) {
                        int iB = i2.n.b(bArr, i10, bArr.length, zArr);
                        if (iB != bArr.length) {
                            c1077fU.a(Integer.valueOf(iB));
                        }
                        i10 = iB + 3;
                    }
                    d0 d0VarH = c1077fU.h();
                    for (int i11 = 0; i11 < d0VarH.size(); i11++) {
                        if (((Integer) d0VarH.get(i11)).intValue() + 3 < length) {
                            N n10 = new N(((Integer) d0VarH.get(i11)).intValue() + 3, length, bArr);
                            C0254m c0254mD = i2.n.d(n10);
                            if (c0254mD.f2343b == 33 && c0254mD.c == 0) {
                                n10.u(4);
                                int i12 = n10.i(3);
                                n10.t();
                                i2.h hVarE = i2.n.e(n10, true, i12, null);
                                strA = AbstractC1348e.a(hVarE.f15679a, hVarE.f15680b, hVarE.c, hVarE.d, hVarE.f15681e, hVarE.f15682f);
                                break loop0;
                            }
                        }
                    }
                }
                i7++;
            }
            pairD = strA == null ? null : AbstractC1348e.b(strA, strA.trim().split("\\.", -1), c1208p.B);
        }
        if (pairD == null) {
            return true;
        }
        int iIntValue = ((Integer) pairD.first).intValue();
        int iIntValue2 = ((Integer) pairD.second).intValue();
        boolean zEquals = "video/dolby-vision".equals(str2);
        int i13 = 8;
        String str3 = this.f22096b;
        if (zEquals) {
            if ("video/avc".equals(str3)) {
                iIntValue2 = 0;
                iIntValue = 8;
            } else if ("video/hevc".equals(str3)) {
                iIntValue2 = 0;
                iIntValue = 2;
            }
        }
        if (!this.f22101i && iIntValue != 42) {
            return true;
        }
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.d;
        if (codecCapabilities == null || (codecProfileLevelArr = codecCapabilities.profileLevels) == null) {
            codecProfileLevelArr = new MediaCodecInfo.CodecProfileLevel[0];
        }
        if (AbstractC1343A.f15195a <= 23 && "video/x-vnd.on2.vp9".equals(str3) && codecProfileLevelArr.length == 0) {
            int iIntValue3 = (codecCapabilities == null || (videoCapabilities = codecCapabilities.getVideoCapabilities()) == null) ? 0 : ((Integer) videoCapabilities.getBitrateRange().getUpper()).intValue();
            if (iIntValue3 >= 180000000) {
                i13 = 1024;
            } else if (iIntValue3 >= 120000000) {
                i13 = 512;
            } else if (iIntValue3 >= 60000000) {
                i13 = 256;
            } else if (iIntValue3 >= 30000000) {
                i13 = 128;
            } else if (iIntValue3 >= 18000000) {
                i13 = 64;
            } else if (iIntValue3 >= 12000000) {
                i13 = 32;
            } else if (iIntValue3 >= 7200000) {
                i13 = 16;
            } else if (iIntValue3 < 3600000) {
                i13 = iIntValue3 >= 1800000 ? 4 : iIntValue3 >= 800000 ? 2 : 1;
            }
            MediaCodecInfo.CodecProfileLevel codecProfileLevel = new MediaCodecInfo.CodecProfileLevel();
            codecProfileLevel.profile = 1;
            codecProfileLevel.level = i13;
            codecProfileLevelArr = new MediaCodecInfo.CodecProfileLevel[]{codecProfileLevel};
        }
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel2 : codecProfileLevelArr) {
            if (codecProfileLevel2.profile == iIntValue && (codecProfileLevel2.level >= iIntValue2 || !z6)) {
                if ("video/hevc".equals(str3) && 2 == iIntValue) {
                    String str4 = AbstractC1343A.f15196b;
                    if ("sailfish".equals(str4) || "marlin".equals(str4)) {
                    }
                }
                return true;
            }
        }
        g("codec.profileLevel, " + c1208p.f14314k + ", " + str);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d(e2.C1208p r8) {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s2.n.d(e2.p):boolean");
    }

    public final boolean e(C1208p c1208p) {
        if (this.f22101i) {
            return this.f22097e;
        }
        Pair pairD = v.d(c1208p);
        return pairD != null && ((Integer) pairD.first).intValue() == 42;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0089 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean f(int r12, int r13, double r14) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s2.n.f(int, int, double):boolean");
    }

    public final void g(String str) {
        StringBuilder sbJ = AbstractC1347d.j("NoSupport [", str, "] [");
        sbJ.append(this.f22095a);
        sbJ.append(", ");
        sbJ.append(this.f22096b);
        sbJ.append("] [");
        sbJ.append(AbstractC1343A.f15197e);
        sbJ.append("]");
        AbstractC1346c.n("MediaCodecInfo", sbJ.toString());
    }

    public final String toString() {
        return this.f22095a;
    }
}
