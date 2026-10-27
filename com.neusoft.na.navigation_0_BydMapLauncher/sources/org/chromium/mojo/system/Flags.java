package org.chromium.mojo.system;

import org.chromium.mojo.system.Flags;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Flags<F extends Flags<F>> {
    private int mFlags;
    private boolean mImmutable = false;

    protected Flags(int i) {
        this.mFlags = i;
    }

    public int getFlags() {
        return this.mFlags;
    }

    protected F setFlag(int i, boolean z) {
        if (this.mImmutable) {
            throw new UnsupportedOperationException("Flags is immutable.");
        }
        if (z) {
            this.mFlags = i | this.mFlags;
        } else {
            this.mFlags = (~i) & this.mFlags;
        }
        return this;
    }

    protected F immutable() {
        this.mImmutable = true;
        return this;
    }

    public int hashCode() {
        return this.mFlags;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.mFlags == ((Flags) obj).mFlags;
    }
}
