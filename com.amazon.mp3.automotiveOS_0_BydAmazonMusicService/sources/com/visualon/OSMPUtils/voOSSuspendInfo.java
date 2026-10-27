package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSSuspendInfo {
    boolean bSuspend = false;
    boolean bPaused = false;
    long suspendTimeStamp = -1;
    int APILevel = 2;

    public int getAPILevel() {
        return this.APILevel;
    }

    public void setAPILevel(int i) {
        this.APILevel = i;
    }

    public boolean isSuspend() {
        return this.bSuspend;
    }

    public void setSuspendStatus(boolean z) {
        this.bSuspend = z;
    }

    public boolean isPaused() {
        return this.bPaused;
    }

    public void setPausedStatus(boolean z) {
        this.bPaused = z;
    }

    public long getSuspendTimeStamp() {
        return this.suspendTimeStamp;
    }

    public void setSuspendTimeStamp(long j) {
        this.suspendTimeStamp = j;
    }
}
