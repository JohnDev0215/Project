package com.ts.avm.sdk.wrappers;

import com.ts.avm.bean.CmdBean;

/* JADX INFO: loaded from: classes.dex */
public class CmdBuilder implements Builder {
    private final CmdBean mCmd = new CmdBean();

    public CmdBuilder setHeader(int header) {
        this.mCmd.setHeader(header);
        return this;
    }

    public CmdBuilder setPayLoadSize(int payLoadSize) {
        this.mCmd.setPayLoadSize(payLoadSize);
        return this;
    }

    public CmdBuilder setSrcId(int srcId) {
        this.mCmd.setSrcId(srcId);
        return this;
    }

    public CmdBuilder setTargetId(int targetId) {
        this.mCmd.setTargetId(targetId);
        return this;
    }

    public CmdBuilder setTransferId(int transferId) {
        this.mCmd.setTransferId(transferId);
        return this;
    }

    public CmdBuilder setId(int id) {
        this.mCmd.setId(id);
        return this;
    }

    public CmdBuilder setType(int type) {
        this.mCmd.setType(type);
        return this;
    }

    public CmdBuilder setGetOrSet(int getOrSet) {
        this.mCmd.setGetOrSet(getOrSet);
        return this;
    }

    public CmdBuilder setPriority(int priority) {
        this.mCmd.setPriority(priority);
        return this;
    }

    public CmdBuilder setFlags(int flags) {
        this.mCmd.setFlags(flags);
        return this;
    }

    public CmdBuilder setValue(int[] value) {
        this.mCmd.setValue(value);
        return this;
    }

    @Override // com.ts.avm.sdk.wrappers.Builder
    public CmdBean build() {
        return this.mCmd;
    }
}
