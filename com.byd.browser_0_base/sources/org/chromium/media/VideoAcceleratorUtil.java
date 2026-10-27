package org.chromium.media;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class VideoAcceleratorUtil {
    public static final String[] a = {"video/x-vnd.on2.vp8", "video/x-vnd.on2.vp9", "video/av01", "video/avc"};
    public static final String[] b = {"video/x-vnd.on2.vp8", "video/x-vnd.on2.vp9", "video/av01", "video/avc", "video/hevc", "video/dolby-vision"};

    public static class SupportedProfileAdapter {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public boolean h;
        public boolean i;
        public String j;
        public boolean k;

        @CalledByNative
        public int getMaxFramerateDenominator() {
            return this.g;
        }

        @CalledByNative
        public int getMaxFramerateNumerator() {
            return this.f;
        }

        @CalledByNative
        public int getMaxHeight() {
            return this.c;
        }

        @CalledByNative
        public int getMaxWidth() {
            return this.b;
        }

        @CalledByNative
        public int getMinHeight() {
            return this.e;
        }

        @CalledByNative
        public int getMinWidth() {
            return this.d;
        }

        @CalledByNative
        public String getName() {
            return this.j;
        }

        @CalledByNative
        public int getProfile() {
            return this.a;
        }

        @CalledByNative
        public boolean isSoftwareCodec() {
            return this.k;
        }

        @CalledByNative
        public boolean supportsCbr() {
            return this.h;
        }

        @CalledByNative
        public boolean supportsVbr() {
            return this.i;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0146  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.media.VideoAcceleratorUtil.SupportedProfileAdapter[] getSupportedDecoderProfiles() {
        /*
            Method dump skipped, instruction units count: 504
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.VideoAcceleratorUtil.getSupportedDecoderProfiles():org.chromium.media.VideoAcceleratorUtil$SupportedProfileAdapter[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0059 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.media.VideoAcceleratorUtil.SupportedProfileAdapter[] getSupportedEncoderProfiles() {
        /*
            Method dump skipped, instruction units count: 554
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.VideoAcceleratorUtil.getSupportedEncoderProfiles():org.chromium.media.VideoAcceleratorUtil$SupportedProfileAdapter[]");
    }
}
