package ipc.fdbus;

/* JADX INFO: loaded from: classes.dex */
public class FdbusMsgBuilder {
    protected Object mMsg;
    protected byte[] mStream;
    protected String mText;

    public FdbusMsgBuilder(Object msg) {
        this.mStream = null;
        this.mText = null;
        this.mMsg = msg;
    }

    public FdbusMsgBuilder(byte[] stream, String text) {
        this.mStream = stream;
        this.mText = text;
        this.mMsg = null;
    }

    public boolean build(boolean to_text) {
        return true;
    }

    public byte[] toBuffer() {
        return this.mStream;
    }

    public String toString() {
        return this.mText;
    }
}
