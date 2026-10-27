package com.byd.vrassistant.bean;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class NetShowResult {
    boolean show;
    boolean strongHint;

    public NetShowResult(boolean z, boolean z2) {
        this.show = z;
        this.strongHint = z2;
    }

    public boolean isShow() {
        return this.show;
    }

    public void setShow(boolean z) {
        this.show = z;
    }

    public boolean isStrongHint() {
        return this.strongHint;
    }

    public void setStrongHint(boolean z) {
        this.strongHint = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NetShowResult)) {
            return false;
        }
        NetShowResult netShowResult = (NetShowResult) obj;
        return this.show == netShowResult.show && this.strongHint == netShowResult.strongHint;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.show), Boolean.valueOf(this.strongHint));
    }

    public String toString() {
        return "NetShowResult{show=" + this.show + ", strongHint=" + this.strongHint + '}';
    }
}
