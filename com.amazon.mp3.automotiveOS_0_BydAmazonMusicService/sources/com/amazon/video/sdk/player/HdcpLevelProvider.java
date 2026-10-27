package com.amazon.video.sdk.player;

/* JADX INFO: loaded from: classes5.dex */
public interface HdcpLevelProvider {
    HdcpLevel getCurrentHdcpLevel();

    public enum HdcpLevel {
        NO_HDCP_SUPPORT(0),
        HDCP_1_0(1),
        HDCP_2_0(2),
        HDCP_2_1(3),
        HDCP_2_2(4),
        LOCAL_DISPLAY(255);

        private final int mCode;

        HdcpLevel(int i) {
            this.mCode = i;
        }

        public int getHdcpLevelCode() {
            return this.mCode;
        }

        public static HdcpLevel fromInteger(int i) {
            for (HdcpLevel hdcpLevel : values()) {
                if (hdcpLevel.mCode == i) {
                    return hdcpLevel;
                }
            }
            return NO_HDCP_SUPPORT;
        }
    }
}
