package com.ts.qnxapp.fdbusClientForAndroid;

import com.google.protobuf.AbstractMessageLite;
import ipc.fdbus.FdbusMsgBuilder;

/* JADX INFO: loaded from: classes.dex */
public class FdbusProtoBuilder extends FdbusMsgBuilder {
    public FdbusProtoBuilder(Object msg) {
        super(msg);
    }

    @Override // ipc.fdbus.FdbusMsgBuilder
    public boolean build(boolean to_text) {
        if (this.mMsg instanceof AbstractMessageLite) {
            AbstractMessageLite msg = (AbstractMessageLite) this.mMsg;
            this.mStream = msg.toByteArray();
            if (to_text) {
                this.mText = msg.toString();
                return true;
            }
            return true;
        }
        return false;
    }
}
