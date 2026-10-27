package com.spotify.cosmos.util.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import java.io.InputStream;
import java.nio.ByteBuffer;
import p.au;
import p.ds1;
import p.h01;
import p.i01;
import p.r1;
import p.rl0;
import p.vj1;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class ImageGroup extends i implements ImageGroupOrBuilder {
    private static final ImageGroup DEFAULT_INSTANCE;
    public static final int LARGE_LINK_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER = null;
    public static final int SMALL_LINK_FIELD_NUMBER = 2;
    public static final int STANDARD_LINK_FIELD_NUMBER = 1;
    public static final int XLARGE_LINK_FIELD_NUMBER = 4;
    private int bitField0_;
    private String standardLink_ = "";
    private String smallLink_ = "";
    private String largeLink_ = "";
    private String xlargeLink_ = "";

    /* JADX INFO: renamed from: com.spotify.cosmos.util.proto.ImageGroup$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements ImageGroupOrBuilder {
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

        public Builder clearLargeLink() {
            copyOnWrite();
            ((ImageGroup) this.instance).clearLargeLink();
            return this;
        }

        public Builder clearSmallLink() {
            copyOnWrite();
            ((ImageGroup) this.instance).clearSmallLink();
            return this;
        }

        public Builder clearStandardLink() {
            copyOnWrite();
            ((ImageGroup) this.instance).clearStandardLink();
            return this;
        }

        public Builder clearXlargeLink() {
            copyOnWrite();
            ((ImageGroup) this.instance).clearXlargeLink();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
        public String getLargeLink() {
            return ((ImageGroup) this.instance).getLargeLink();
        }

        @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
        public ByteString getLargeLinkBytes() {
            return ((ImageGroup) this.instance).getLargeLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
        public String getSmallLink() {
            return ((ImageGroup) this.instance).getSmallLink();
        }

        @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
        public ByteString getSmallLinkBytes() {
            return ((ImageGroup) this.instance).getSmallLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
        public String getStandardLink() {
            return ((ImageGroup) this.instance).getStandardLink();
        }

        @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
        public ByteString getStandardLinkBytes() {
            return ((ImageGroup) this.instance).getStandardLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
        public String getXlargeLink() {
            return ((ImageGroup) this.instance).getXlargeLink();
        }

        @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
        public ByteString getXlargeLinkBytes() {
            return ((ImageGroup) this.instance).getXlargeLinkBytes();
        }

        @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
        public boolean hasLargeLink() {
            return ((ImageGroup) this.instance).hasLargeLink();
        }

        @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
        public boolean hasSmallLink() {
            return ((ImageGroup) this.instance).hasSmallLink();
        }

        @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
        public boolean hasStandardLink() {
            return ((ImageGroup) this.instance).hasStandardLink();
        }

        @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
        public boolean hasXlargeLink() {
            return ((ImageGroup) this.instance).hasXlargeLink();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setLargeLink(String str) {
            copyOnWrite();
            ((ImageGroup) this.instance).setLargeLink(str);
            return this;
        }

        public Builder setLargeLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((ImageGroup) this.instance).setLargeLinkBytes(byteString);
            return this;
        }

        public Builder setSmallLink(String str) {
            copyOnWrite();
            ((ImageGroup) this.instance).setSmallLink(str);
            return this;
        }

        public Builder setSmallLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((ImageGroup) this.instance).setSmallLinkBytes(byteString);
            return this;
        }

        public Builder setStandardLink(String str) {
            copyOnWrite();
            ((ImageGroup) this.instance).setStandardLink(str);
            return this;
        }

        public Builder setStandardLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((ImageGroup) this.instance).setStandardLinkBytes(byteString);
            return this;
        }

        public Builder setXlargeLink(String str) {
            copyOnWrite();
            ((ImageGroup) this.instance).setXlargeLink(str);
            return this;
        }

        public Builder setXlargeLinkBytes(ByteString byteString) {
            copyOnWrite();
            ((ImageGroup) this.instance).setXlargeLinkBytes(byteString);
            return this;
        }

        private Builder() {
            super(ImageGroup.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m282mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m283mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m284mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        ImageGroup imageGroup = new ImageGroup();
        DEFAULT_INSTANCE = imageGroup;
        i.registerDefaultInstance(ImageGroup.class, imageGroup);
    }

    private ImageGroup() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLargeLink() {
        this.bitField0_ &= -5;
        this.largeLink_ = getDefaultInstance().getLargeLink();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSmallLink() {
        this.bitField0_ &= -3;
        this.smallLink_ = getDefaultInstance().getSmallLink();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStandardLink() {
        this.bitField0_ &= -2;
        this.standardLink_ = getDefaultInstance().getStandardLink();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearXlargeLink() {
        this.bitField0_ &= -9;
        this.xlargeLink_ = getDefaultInstance().getXlargeLink();
    }

    public static ImageGroup getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static ImageGroup parseDelimitedFrom(InputStream inputStream) {
        return (ImageGroup) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ImageGroup parseFrom(ByteBuffer byteBuffer) {
        return (ImageGroup) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLargeLink(String str) {
        str.getClass();
        this.bitField0_ |= 4;
        this.largeLink_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLargeLinkBytes(ByteString byteString) {
        this.largeLink_ = byteString.s();
        this.bitField0_ |= 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSmallLink(String str) {
        str.getClass();
        this.bitField0_ |= 2;
        this.smallLink_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSmallLinkBytes(ByteString byteString) {
        this.smallLink_ = byteString.s();
        this.bitField0_ |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStandardLink(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.standardLink_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStandardLinkBytes(ByteString byteString) {
        this.standardLink_ = byteString.s();
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setXlargeLink(String str) {
        str.getClass();
        this.bitField0_ |= 8;
        this.xlargeLink_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setXlargeLinkBytes(ByteString byteString) {
        this.xlargeLink_ = byteString.s();
        this.bitField0_ |= 8;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003", new Object[]{"bitField0_", "standardLink_", "smallLink_", "largeLink_", "xlargeLink_"});
            case 3:
                return new ImageGroup();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ImageGroup.class) {
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

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
    public String getLargeLink() {
        return this.largeLink_;
    }

    @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
    public ByteString getLargeLinkBytes() {
        return ByteString.d(this.largeLink_);
    }

    @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
    public String getSmallLink() {
        return this.smallLink_;
    }

    @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
    public ByteString getSmallLinkBytes() {
        return ByteString.d(this.smallLink_);
    }

    @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
    public String getStandardLink() {
        return this.standardLink_;
    }

    @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
    public ByteString getStandardLinkBytes() {
        return ByteString.d(this.standardLink_);
    }

    @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
    public String getXlargeLink() {
        return this.xlargeLink_;
    }

    @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
    public ByteString getXlargeLinkBytes() {
        return ByteString.d(this.xlargeLink_);
    }

    @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
    public boolean hasLargeLink() {
        return (this.bitField0_ & 4) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
    public boolean hasSmallLink() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
    public boolean hasStandardLink() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.spotify.cosmos.util.proto.ImageGroupOrBuilder
    public boolean hasXlargeLink() {
        return (this.bitField0_ & 8) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(ImageGroup imageGroup) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(imageGroup);
    }

    public static ImageGroup parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (ImageGroup) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ImageGroup parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (ImageGroup) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static ImageGroup parseFrom(ByteString byteString) {
        return (ImageGroup) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ImageGroup parseFrom(ByteString byteString, rl0 rl0Var) {
        return (ImageGroup) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static ImageGroup parseFrom(byte[] bArr) {
        return (ImageGroup) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ImageGroup parseFrom(byte[] bArr, rl0 rl0Var) {
        return (ImageGroup) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static ImageGroup parseFrom(InputStream inputStream) {
        return (ImageGroup) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ImageGroup parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (ImageGroup) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ImageGroup parseFrom(au auVar) {
        return (ImageGroup) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static ImageGroup parseFrom(au auVar, rl0 rl0Var) {
        return (ImageGroup) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
