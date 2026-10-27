package com.amazon.avod.content.smoothstream.manifest;

import androidx.collection.SparseArrayCompat;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MultiPeriodStreamIndexSynchronizer {
    private final SparseArrayCompat<StreamIndexIdentity> mStreamIndexIdentities = new SparseArrayCompat<>();

    public void putIdentityAtIndex(@Nonnull StreamIndexIdentity streamIndexIdentity, int i) {
        Preconditions.checkNotNull(streamIndexIdentity, "id");
        this.mStreamIndexIdentities.put(i, streamIndexIdentity);
    }

    public StreamIndexIdentity getStreamIndexId(int i) {
        return this.mStreamIndexIdentities.get(i);
    }

    public boolean containsIndex(int i) {
        for (int i2 = 0; i2 < this.mStreamIndexIdentities.size(); i2++) {
            if (this.mStreamIndexIdentities.keyAt(i2) == i) {
                return true;
            }
        }
        return false;
    }
}
