package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes.dex */
final class RawMessageInfo implements MessageInfo {
    private final MessageLite defaultInstance;
    private final int flags;
    private final String info;
    private final Object[] objects;

    public RawMessageInfo(MessageLite messageLite, String str, Object[] objArr) {
        char cCharAt;
        this.defaultInstance = messageLite;
        this.info = str;
        this.objects = objArr;
        int iCharAt = str.charAt(0);
        if (iCharAt >= 55296) {
            int i = iCharAt & 8191;
            int i2 = 13;
            int i3 = 1;
            while (true) {
                int i4 = i3 + 1;
                cCharAt = str.charAt(i3);
                if (cCharAt < 55296) {
                    break;
                }
                i |= (cCharAt & 8191) << i2;
                i2 += 13;
                i3 = i4;
            }
            iCharAt = i | (cCharAt << i2);
        }
        this.flags = iCharAt;
    }

    @Override // androidx.datastore.preferences.protobuf.MessageInfo
    public MessageLite getDefaultInstance() {
        return this.defaultInstance;
    }

    public Object[] getObjects() {
        return this.objects;
    }

    public String getStringInfo() {
        return this.info;
    }

    @Override // androidx.datastore.preferences.protobuf.MessageInfo
    public ProtoSyntax getSyntax() {
        return (this.flags & 1) == 1 ? ProtoSyntax.PROTO2 : ProtoSyntax.PROTO3;
    }

    @Override // androidx.datastore.preferences.protobuf.MessageInfo
    public boolean isMessageSetWireFormat() {
        return (this.flags & 2) == 2;
    }
}
