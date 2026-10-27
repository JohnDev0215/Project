package com.byd.data.common.entiry.cloud.event;

import java.io.Serializable;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CanBean implements Serializable, Comparable<CanBean> {
    public short canId;
    public byte netWorkIndex;
    public LinkedList<byte[]> queue;
    public byte size;
    public boolean subTag;
    public byte subVal;
    public LinkedList<Integer> timeQueue;
    protected byte type = 1;

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(CanBean canBean) {
        short s = this.canId;
        short s2 = canBean.canId;
        if (s != s2) {
            return s - s2;
        }
        if (this.subTag || canBean.subTag) {
            return this.subVal - canBean.subVal;
        }
        return 0;
    }

    public byte b() {
        return this.type;
    }

    public boolean d() {
        return (this.type & 1) == 1;
    }

    public void e(byte b2) {
        this.type = b2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        CanBean canBean = (CanBean) obj;
        if (this.canId != canBean.canId) {
            return false;
        }
        return !(canBean.subTag || this.subTag) || this.subVal == canBean.subVal;
    }

    public int hashCode() {
        return this.canId | (this.subTag ? this.subVal << 12 : 0);
    }

    public String toString() {
        return "CanBean{canId=" + ((int) this.canId) + ", subTag=" + this.subTag + ", subVal=" + ((int) this.subVal) + ", type=" + ((int) this.type) + '}';
    }
}
