package com.spotify.eventsender.eventsender;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class SequenceNumberProvider$SequenceNumberType {
    public static final SequenceNumberProvider$SequenceNumberType c;
    public static final SequenceNumberProvider$SequenceNumberType e;
    public static final /* synthetic */ SequenceNumberProvider$SequenceNumberType[] k;

    static {
        SequenceNumberProvider$SequenceNumberType sequenceNumberProvider$SequenceNumberType = new SequenceNumberProvider$SequenceNumberType("PERSISTENT", 0);
        c = sequenceNumberProvider$SequenceNumberType;
        SequenceNumberProvider$SequenceNumberType sequenceNumberProvider$SequenceNumberType2 = new SequenceNumberProvider$SequenceNumberType("IN_MEMORY", 1);
        e = sequenceNumberProvider$SequenceNumberType2;
        k = new SequenceNumberProvider$SequenceNumberType[]{sequenceNumberProvider$SequenceNumberType, sequenceNumberProvider$SequenceNumberType2};
    }

    public static SequenceNumberProvider$SequenceNumberType valueOf(String str) {
        return (SequenceNumberProvider$SequenceNumberType) Enum.valueOf(SequenceNumberProvider$SequenceNumberType.class, str);
    }

    public static SequenceNumberProvider$SequenceNumberType[] values() {
        return (SequenceNumberProvider$SequenceNumberType[]) k.clone();
    }
}
