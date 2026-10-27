package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import com.spotify.extendedmetadata.proto.ExtensionKind;
import java.io.InputStream;
import java.nio.ByteBuffer;
import p.au;
import p.ds1;
import p.h01;
import p.i01;
import p.ol0;
import p.r1;
import p.rl0;
import p.vj1;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class Extension extends i implements ExtensionOrBuilder {
    public static final int DATA_FIELD_NUMBER = 2;
    private static final Extension DEFAULT_INSTANCE;
    public static final int EXTENSION_KIND_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private int bitField0_;
    private ByteString data_ = ByteString.c;
    private int extensionKind_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.Extension$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite$MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[3] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[4] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[5] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[6] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[0] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[1] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class Builder extends h01 implements ExtensionOrBuilder {
        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public /* bridge */ /* synthetic */ wj1 build() {
            return super.build();
        }

        @Override // p.h01, p.vj1
        public /* bridge */ /* synthetic */ wj1 buildPartial() {
            return super.buildPartial();
        }

        public /* bridge */ /* synthetic */ vj1 clear() {
            return super.clear();
        }

        public Builder clearData() {
            copyOnWrite();
            ((Extension) this.instance).clearData();
            return this;
        }

        public Builder clearExtensionKind() {
            copyOnWrite();
            ((Extension) this.instance).clearExtensionKind();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.cosmos.util.proto.ExtensionOrBuilder
        public ByteString getData() {
            return ((Extension) this.instance).getData();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.proto.ExtensionOrBuilder
        public ExtensionKind getExtensionKind() {
            return ((Extension) this.instance).getExtensionKind();
        }

        @Override // com.spotify.cosmos.util.proto.ExtensionOrBuilder
        public boolean hasData() {
            return ((Extension) this.instance).hasData();
        }

        @Override // com.spotify.cosmos.util.proto.ExtensionOrBuilder
        public boolean hasExtensionKind() {
            return ((Extension) this.instance).hasExtensionKind();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setData(ByteString byteString) {
            copyOnWrite();
            ((Extension) this.instance).setData(byteString);
            return this;
        }

        public Builder setExtensionKind(ExtensionKind extensionKind) {
            copyOnWrite();
            ((Extension) this.instance).setExtensionKind(extensionKind);
            return this;
        }

        private Builder() {
            super(Extension.DEFAULT_INSTANCE);
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ r1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ vj1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString) {
            return super.mergeFrom(byteString);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString, rl0 rl0Var) {
            return super.mergeFrom(byteString, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream) {
            return super.mergeFrom(inputStream);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
            return super.mergeFrom(inputStream, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(au auVar) {
            return super.mergeFrom(auVar);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m279mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m280mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m281mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        Extension extension = new Extension();
        DEFAULT_INSTANCE = extension;
        i.registerDefaultInstance(Extension.class, extension);
    }

    private Extension() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearData() {
        this.bitField0_ &= -3;
        this.data_ = getDefaultInstance().getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExtensionKind() {
        this.bitField0_ &= -2;
        this.extensionKind_ = 0;
    }

    public static Extension getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Extension parseDelimitedFrom(InputStream inputStream) {
        return (Extension) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Extension parseFrom(ByteBuffer byteBuffer) {
        return (Extension) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setData(ByteString byteString) {
        byteString.getClass();
        this.bitField0_ |= 2;
        this.data_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExtensionKind(ExtensionKind extensionKind) {
        this.extensionKind_ = extensionKind.getNumber();
        this.bitField0_ |= 1;
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ည\u0001", new Object[]{"bitField0_", "extensionKind_", ol0.a, "data_"});
            case 3:
                return new Extension();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Extension.class) {
                        try {
                            i01Var = PARSER;
                            if (i01Var == null) {
                                i01Var = new i01(DEFAULT_INSTANCE);
                                PARSER = i01Var;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return i01Var;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.spotify.cosmos.util.proto.ExtensionOrBuilder
    public ByteString getData() {
        return this.data_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.proto.ExtensionOrBuilder
    public ExtensionKind getExtensionKind() {
        ExtensionKind extensionKindA = ExtensionKind.a(this.extensionKind_);
        return extensionKindA == null ? ExtensionKind.UNKNOWN_EXTENSION : extensionKindA;
    }

    @Override // com.spotify.cosmos.util.proto.ExtensionOrBuilder
    public boolean hasData() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ExtensionOrBuilder
    public boolean hasExtensionKind() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(Extension extension) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(extension);
    }

    public static Extension parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (Extension) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static Extension parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (Extension) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static Extension parseFrom(ByteString byteString) {
        return (Extension) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Extension parseFrom(ByteString byteString, rl0 rl0Var) {
        return (Extension) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static Extension parseFrom(byte[] bArr) {
        return (Extension) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Extension parseFrom(byte[] bArr, rl0 rl0Var) {
        return (Extension) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static Extension parseFrom(InputStream inputStream) {
        return (Extension) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Extension parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (Extension) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static Extension parseFrom(au auVar) {
        return (Extension) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static Extension parseFrom(au auVar, rl0 rl0Var) {
        return (Extension) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
