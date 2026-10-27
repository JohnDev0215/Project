package com.ts.androidauto.app.proxy.navigation;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GuidanceLineInfo {
    private List<Integer> mAngles;
    private int mHighlightAngle;
    private int mStatus;

    public GuidanceLineInfo() {
    }

    public GuidanceLineInfo(int status, List<Integer> angles, int highlightAngle) {
        this.mStatus = status;
        this.mAngles = angles;
        this.mHighlightAngle = highlightAngle;
    }

    public int getmStatus() {
        return this.mStatus;
    }

    public void setmStatus(int status) {
        this.mStatus = status;
    }

    public List<Integer> getmAngles() {
        return this.mAngles;
    }

    public void setmAngles(List<Integer> angles) {
        this.mAngles = angles;
    }

    public int getmHighlightAngle() {
        return this.mHighlightAngle;
    }

    public void setmHighlightAngle(int highlightAngle) {
        this.mHighlightAngle = highlightAngle;
    }

    public String toString() {
        return "GuidanceLineInfo{mStatus=" + this.mStatus + ", mAngles=" + this.mAngles + ", mHighlightAngle=" + this.mHighlightAngle + '}';
    }
}
