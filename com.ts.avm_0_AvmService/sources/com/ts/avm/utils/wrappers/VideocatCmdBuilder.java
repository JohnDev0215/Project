package com.ts.avm.utils.wrappers;

/* JADX INFO: loaded from: classes.dex */
public class VideocatCmdBuilder implements Builder {
    private final VideoCatCmd mVideoCatCmd = new VideoCatCmd();

    public VideocatCmdBuilder setHeader(int header) {
        this.mVideoCatCmd.setHeader(header);
        return this;
    }

    public VideocatCmdBuilder setPayLoadSize(int payLoadSize) {
        this.mVideoCatCmd.setPayLoadSize(payLoadSize);
        return this;
    }

    public VideocatCmdBuilder setSrcId(int srcId) {
        this.mVideoCatCmd.setSrcId(srcId);
        return this;
    }

    public VideocatCmdBuilder setTargetId(int targetId) {
        this.mVideoCatCmd.setTargetId(targetId);
        return this;
    }

    public VideocatCmdBuilder setTransferId(int transferId) {
        this.mVideoCatCmd.setTransferId(transferId);
        return this;
    }

    public VideocatCmdBuilder setId(int id) {
        this.mVideoCatCmd.setId(id);
        return this;
    }

    public VideocatCmdBuilder setType(int type) {
        this.mVideoCatCmd.setType(type);
        return this;
    }

    public VideocatCmdBuilder setGetOrSet(int getOrSet) {
        this.mVideoCatCmd.setGetOrSet(getOrSet);
        return this;
    }

    public VideocatCmdBuilder setPriority(int priority) {
        this.mVideoCatCmd.setPriority(priority);
        return this;
    }

    public VideocatCmdBuilder setFlags(int flags) {
        this.mVideoCatCmd.setFlags(flags);
        return this;
    }

    public VideocatCmdBuilder setValue(int[] value) {
        this.mVideoCatCmd.setValue(value);
        return this;
    }

    @Override // com.ts.avm.utils.wrappers.Builder
    public VideoCatCmd build() {
        return this.mVideoCatCmd;
    }
}
