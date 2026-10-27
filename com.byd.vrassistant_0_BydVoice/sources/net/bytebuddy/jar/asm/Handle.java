package net.bytebuddy.jar.asm;

import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public final class Handle {
    final String desc;
    final boolean itf;
    final String name;
    final String owner;
    final int tag;

    @Deprecated
    public Handle(int i, String str, String str2, String str3) {
        this(i, str, str2, str3, i == 9);
    }

    public Handle(int i, String str, String str2, String str3, boolean z) {
        this.tag = i;
        this.owner = str;
        this.name = str2;
        this.desc = str3;
        this.itf = z;
    }

    public int getTag() {
        return this.tag;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean isInterface() {
        return this.itf;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Handle)) {
            return false;
        }
        Handle handle = (Handle) obj;
        return this.tag == handle.tag && this.itf == handle.itf && this.owner.equals(handle.owner) && this.name.equals(handle.name) && this.desc.equals(handle.desc);
    }

    public int hashCode() {
        return this.tag + (this.itf ? 64 : 0) + (this.owner.hashCode() * this.name.hashCode() * this.desc.hashCode());
    }

    public String toString() {
        return this.owner + TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH + this.name + this.desc + " (" + this.tag + (this.itf ? " itf" : "") + ')';
    }
}
