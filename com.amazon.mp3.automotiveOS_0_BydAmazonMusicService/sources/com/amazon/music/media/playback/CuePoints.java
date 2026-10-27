package com.amazon.music.media.playback;

/* JADX INFO: loaded from: classes4.dex */
public class CuePoints {
    private Integer endOffSetInMills;
    private Integer startOffSetInMills;

    public CuePoints(Integer num, Integer num2) {
        this.startOffSetInMills = num;
        this.endOffSetInMills = num2;
    }

    public Integer getStartOffSetInMills() {
        return this.startOffSetInMills;
    }

    public Integer getEndOffSetInMills() {
        return this.endOffSetInMills;
    }

    public static String createOffsetAsin(String str, CuePoints cuePoints) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("?");
        sb.append("startOffsetMS=" + cuePoints.getStartOffSetInMills());
        sb.append("&");
        sb.append("endOffsetMS=" + cuePoints.getEndOffSetInMills());
        return sb.toString();
    }
}
