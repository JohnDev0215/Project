package com.amazon.music.destination;

/* JADX INFO: loaded from: classes4.dex */
public class Target {
    private final String targetId;
    private final TargetType targetType;

    public Target(TargetType targetType, String str) {
        this.targetType = targetType;
        this.targetId = str;
    }

    public TargetType getTargetType() {
        return this.targetType;
    }

    public String getTargetId() {
        return this.targetId;
    }
}
