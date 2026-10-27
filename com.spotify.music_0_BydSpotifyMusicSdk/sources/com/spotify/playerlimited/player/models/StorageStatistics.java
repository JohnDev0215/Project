package com.spotify.playerlimited.player.models;

import p.c71;
import p.u4;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class StorageStatistics {
    public final int a;

    public StorageStatistics(@z61(name = "current_locked_size") int i) {
        this.a = i;
    }

    public final StorageStatistics copy(@z61(name = "current_locked_size") int i) {
        return new StorageStatistics(i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StorageStatistics) && this.a == ((StorageStatistics) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    public final String toString() {
        return u4.k(new StringBuilder("StorageStatistics{currentLockedSize="), this.a, '}');
    }
}
