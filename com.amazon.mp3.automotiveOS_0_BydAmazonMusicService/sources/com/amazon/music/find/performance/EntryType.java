package com.amazon.music.find.performance;

import kotlin.Metadata;

/* JADX INFO: compiled from: EntryType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/find/performance/EntryType;", "", "v", "", "(Ljava/lang/String;ILjava/lang/String;)V", "MARK", "MEASURE", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum EntryType {
    MARK("mark"),
    MEASURE("measure");

    private final String v;

    EntryType(String str) {
        this.v = str;
    }
}
