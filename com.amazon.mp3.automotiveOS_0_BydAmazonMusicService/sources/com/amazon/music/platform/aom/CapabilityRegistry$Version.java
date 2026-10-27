package com.amazon.music.platform.aom;

import kotlin.Metadata;

/* JADX INFO: compiled from: CapabilityRegistry.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"com/amazon/music/platform/aom/CapabilityRegistry$Version", "", "Lcom/amazon/music/platform/aom/CapabilityRegistry$Version;", "", "value", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "VERSION_1_2", "VERSION_1_0", "VERSION_0_2", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public enum CapabilityRegistry$Version {
    VERSION_1_2("1.2"),
    VERSION_1_0("1.0"),
    VERSION_0_2("0.2");

    private final String value;

    CapabilityRegistry$Version(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
