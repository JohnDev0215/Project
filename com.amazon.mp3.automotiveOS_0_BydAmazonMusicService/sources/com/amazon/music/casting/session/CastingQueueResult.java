package com.amazon.music.casting.session;

/* JADX INFO: loaded from: classes4.dex */
public enum CastingQueueResult {
    SUCCESS("SUCCESS"),
    DEVICE_OFFLINE("DEVICE_OFFLINE"),
    UNKNOWN_FAILURE("UNKNOWN_FAILURE");

    private boolean isCreateQueueResult = false;
    private String name;
    private String queueId;

    CastingQueueResult(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public static CastingQueueResult fromString(String str) {
        for (CastingQueueResult castingQueueResult : values()) {
            if (castingQueueResult.name.equals(str)) {
                return castingQueueResult;
            }
        }
        return UNKNOWN_FAILURE;
    }

    public void setQueueId(String str) {
        this.queueId = str;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public void setIsCreateQueueResult(boolean z) {
        this.isCreateQueueResult = z;
    }

    public boolean getIsCreateQueueResult() {
        return this.isCreateQueueResult;
    }
}
