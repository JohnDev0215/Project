package com.amazon.music.page.api.model;

/* JADX INFO: loaded from: classes4.dex */
public enum RankMovement {
    UP("UP"),
    DOWN("DOWN");

    private String value;

    RankMovement(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static RankMovement fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        for (RankMovement rankMovement : values()) {
            if (rankMovement.toString().equals(str)) {
                return rankMovement;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
