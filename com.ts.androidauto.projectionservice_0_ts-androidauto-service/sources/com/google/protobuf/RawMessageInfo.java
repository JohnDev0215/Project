package com.google.protobuf;

/* JADX INFO: loaded from: classes.dex */
final class RawMessageInfo implements MessageInfo {
    private final MessageLite defaultInstance;
    private final int flags;
    private final String info;
    private final Object[] objects;

    RawMessageInfo(MessageLite defaultInstance, String info, Object[] objects) {
        int value;
        this.defaultInstance = defaultInstance;
        this.info = info;
        this.objects = objects;
        try {
            value = info.charAt(0);
        } catch (StringIndexOutOfBoundsException e) {
            char[] infoChars = info.toCharArray();
            info = new String(infoChars);
            try {
                value = info.charAt(0);
            } catch (StringIndexOutOfBoundsException e2) {
                try {
                    char[] infoChars2 = new char[info.length()];
                    info.getChars(0, info.length(), infoChars2, 0);
                    info = new String(infoChars2);
                    value = info.charAt(0);
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e3) {
                    throw new IllegalStateException(String.format("Failed parsing '%s' with charArray.length of %d", info, Integer.valueOf(infoChars.length)), e3);
                }
            }
        }
        int position = 1;
        if (value < 55296) {
            this.flags = value;
            return;
        }
        int result = value & 8191;
        int shift = 13;
        while (true) {
            int position2 = position + 1;
            int value2 = info.charAt(position);
            if (value2 >= 55296) {
                result |= (value2 & 8191) << shift;
                shift += 13;
                position = position2;
            } else {
                this.flags = (value2 << shift) | result;
                return;
            }
        }
    }

    String getStringInfo() {
        return this.info;
    }

    Object[] getObjects() {
        return this.objects;
    }

    @Override // com.google.protobuf.MessageInfo
    public MessageLite getDefaultInstance() {
        return this.defaultInstance;
    }

    @Override // com.google.protobuf.MessageInfo
    public ProtoSyntax getSyntax() {
        return (this.flags & 1) == 1 ? ProtoSyntax.PROTO2 : ProtoSyntax.PROTO3;
    }

    @Override // com.google.protobuf.MessageInfo
    public boolean isMessageSetWireFormat() {
        return (this.flags & 2) == 2;
    }
}
