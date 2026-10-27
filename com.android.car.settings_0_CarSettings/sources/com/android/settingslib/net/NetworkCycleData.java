package com.android.settingslib.net;

/* JADX INFO: loaded from: classes6.dex */
public class NetworkCycleData {
    private long mEndTime;
    private long mStartTime;
    private long mTotalUsage;

    protected NetworkCycleData() {
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public long getTotalUsage() {
        return this.mTotalUsage;
    }

    public static class Builder {
        private NetworkCycleData mObject = new NetworkCycleData();

        public Builder setStartTime(long start) {
            getObject().mStartTime = start;
            return this;
        }

        public Builder setEndTime(long end) {
            getObject().mEndTime = end;
            return this;
        }

        public Builder setTotalUsage(long total) {
            getObject().mTotalUsage = total;
            return this;
        }

        protected NetworkCycleData getObject() {
            return this.mObject;
        }

        public NetworkCycleData build() {
            return getObject();
        }
    }
}
