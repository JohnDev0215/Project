package ipc.fdbus;

import ipc.fdbus.FdbusParcelable;

/* JADX INFO: loaded from: classes.dex */
public class FdbusSimpleBuilder extends FdbusMsgBuilder {
    public FdbusSimpleBuilder(Object msg) {
        super(msg);
    }

    @Override // ipc.fdbus.FdbusMsgBuilder
    public boolean build(boolean to_text) {
        FdbusSerializer serializer = new FdbusSerializer();
        if (this.mMsg instanceof FdbusParcelable) {
            FdbusParcelable msg = (FdbusParcelable) this.mMsg;
            serializer.in(msg);
        } else if (this.mMsg instanceof FdbusParcelable[]) {
            FdbusParcelable[] msg2 = (FdbusParcelable[]) this.mMsg;
            serializer.in(msg2);
        } else if (this.mMsg instanceof String) {
            String msg3 = (String) this.mMsg;
            serializer.inS(msg3);
        } else if (this.mMsg instanceof String[]) {
            String[] msg4 = (String[]) this.mMsg;
            serializer.inS(msg4);
        } else {
            return false;
        }
        this.mStream = serializer.export();
        if (to_text) {
            FdbusParcelable.TextFormatter fmter = new FdbusParcelable.TextFormatter();
            fmter.format(this.mMsg);
            this.mText = fmter.stream();
            return true;
        }
        return true;
    }
}
