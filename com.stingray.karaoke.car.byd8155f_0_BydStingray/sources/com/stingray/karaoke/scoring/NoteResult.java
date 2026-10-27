package com.stingray.karaoke.scoring;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/karaoke/scoring/NoteResult;", "", "<init>", "(Ljava/lang/String;I)V", "HIT", "MISS", "UNKNOWN", "scoring_baseEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class NoteResult {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ NoteResult[] $VALUES;
    public static final NoteResult HIT = new NoteResult("HIT", 0);
    public static final NoteResult MISS = new NoteResult("MISS", 1);
    public static final NoteResult UNKNOWN = new NoteResult("UNKNOWN", 2);

    private static final /* synthetic */ NoteResult[] $values() {
        return new NoteResult[]{HIT, MISS, UNKNOWN};
    }

    static {
        NoteResult[] noteResultArr$values = $values();
        $VALUES = noteResultArr$values;
        $ENTRIES = r.e(noteResultArr$values);
    }

    private NoteResult(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static NoteResult valueOf(String str) {
        return (NoteResult) Enum.valueOf(NoteResult.class, str);
    }

    public static NoteResult[] values() {
        return (NoteResult[]) $VALUES.clone();
    }
}
