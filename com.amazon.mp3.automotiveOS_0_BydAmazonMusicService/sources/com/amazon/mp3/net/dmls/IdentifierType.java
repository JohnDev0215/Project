package com.amazon.mp3.net.dmls;

/* JADX INFO: loaded from: classes3.dex */
public enum IdentifierType {
    UNDEFINED("Undefined"),
    ASIN("ASIN"),
    DMID("DMID"),
    COID("COID");

    private String mName;

    IdentifierType(String name) {
        this.mName = name;
    }

    public String getName() {
        return this.mName;
    }

    public static IdentifierType fromString(String s) {
        for (IdentifierType identifierType : values()) {
            if (identifierType.mName.equals(s)) {
                return identifierType;
            }
        }
        return UNDEFINED;
    }
}
