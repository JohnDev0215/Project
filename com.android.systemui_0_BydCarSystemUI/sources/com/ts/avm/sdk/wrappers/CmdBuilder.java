package com.ts.avm.sdk.wrappers;

import com.ts.avm.bean.CmdBean;

/* JADX INFO: loaded from: classes2.dex */
public class CmdBuilder implements Builder {
    private final CmdBean mCmd = new CmdBean();

    public CmdBuilder setHeader(int i) {
        this.mCmd.setHeader(i);
        return this;
    }

    public CmdBuilder setPayLoadSize(int i) {
        this.mCmd.setPayLoadSize(i);
        return this;
    }

    public CmdBuilder setSrcId(int i) {
        this.mCmd.setSrcId(i);
        return this;
    }

    public CmdBuilder setTargetId(int i) {
        this.mCmd.setTargetId(i);
        return this;
    }

    public CmdBuilder setTransferId(int i) {
        this.mCmd.setTransferId(i);
        return this;
    }

    public CmdBuilder setId(int i) {
        this.mCmd.setId(i);
        return this;
    }

    public CmdBuilder setType(int i) {
        this.mCmd.setType(i);
        return this;
    }

    public CmdBuilder setGetOrSet(int i) {
        this.mCmd.setGetOrSet(i);
        return this;
    }

    public CmdBuilder setPriority(int i) {
        this.mCmd.setPriority(i);
        return this;
    }

    public CmdBuilder setFlags(int i) {
        this.mCmd.setFlags(i);
        return this;
    }

    public CmdBuilder setValue(int[] iArr) {
        this.mCmd.setValue(iArr);
        return this;
    }

    @Override // com.ts.avm.sdk.wrappers.Builder
    public CmdBean build() {
        return this.mCmd;
    }
}
