package com.spotify.cosmos.util.policy.proto;

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
public final class ArtistDecorationPolicy extends i implements ArtistDecorationPolicyOrBuilder {
    private static final ArtistDecorationPolicy DEFAULT_INSTANCE;
    public static final int IS_VARIOUS_ARTISTS_FIELD_NUMBER = 3;
    public static final int LINK_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int PORTRAITS_FIELD_NUMBER = 4;
    private boolean isVariousArtists_;
    private boolean link_;
    private boolean name_;
    private boolean portraits_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.policy.proto.ArtistDecorationPolicy$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements ArtistDecorationPolicyOrBuilder {
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

        public Builder clearIsVariousArtists() {
            copyOnWrite();
            ((ArtistDecorationPolicy) this.instance).clearIsVariousArtists();
            return this;
        }

        public Builder clearLink() {
            copyOnWrite();
            ((ArtistDecorationPolicy) this.instance).clearLink();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((ArtistDecorationPolicy) this.instance).clearName();
            return this;
        }

        public Builder clearPortraits() {
            copyOnWrite();
            ((ArtistDecorationPolicy) this.instance).clearPortraits();
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

        @Override // com.spotify.cosmos.util.policy.proto.ArtistDecorationPolicyOrBuilder
        public boolean getIsVariousArtists() {
            return ((ArtistDecorationPolicy) this.instance).getIsVariousArtists();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ArtistDecorationPolicyOrBuilder
        public boolean getLink() {
            return ((ArtistDecorationPolicy) this.instance).getLink();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ArtistDecorationPolicyOrBuilder
        public boolean getName() {
            return ((ArtistDecorationPolicy) this.instance).getName();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ArtistDecorationPolicyOrBuilder
        public boolean getPortraits() {
            return ((ArtistDecorationPolicy) this.instance).getPortraits();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setIsVariousArtists(boolean z) {
            copyOnWrite();
            ((ArtistDecorationPolicy) this.instance).setIsVariousArtists(z);
            return this;
        }

        public Builder setLink(boolean z) {
            copyOnWrite();
            ((ArtistDecorationPolicy) this.instance).setLink(z);
            return this;
        }

        public Builder setName(boolean z) {
            copyOnWrite();
            ((ArtistDecorationPolicy) this.instance).setName(z);
            return this;
        }

        public Builder setPortraits(boolean z) {
            copyOnWrite();
            ((ArtistDecorationPolicy) this.instance).setPortraits(z);
            return this;
        }

        private Builder() {
            super(ArtistDecorationPolicy.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m208mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m209mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m210mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        ArtistDecorationPolicy artistDecorationPolicy = new ArtistDecorationPolicy();
        DEFAULT_INSTANCE = artistDecorationPolicy;
        i.registerDefaultInstance(ArtistDecorationPolicy.class, artistDecorationPolicy);
    }

    private ArtistDecorationPolicy() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIsVariousArtists() {
        this.isVariousArtists_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLink() {
        this.link_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPortraits() {
        this.portraits_ = false;
    }

    public static ArtistDecorationPolicy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static ArtistDecorationPolicy parseDelimitedFrom(InputStream inputStream) {
        return (ArtistDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ArtistDecorationPolicy parseFrom(ByteBuffer byteBuffer) {
        return (ArtistDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsVariousArtists(boolean z) {
        this.isVariousArtists_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLink(boolean z) {
        this.link_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(boolean z) {
        this.name_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPortraits(boolean z) {
        this.portraits_ = z;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0007\u0004\u0007", new Object[]{"link_", "name_", "isVariousArtists_", "portraits_"});
            case 3:
                return new ArtistDecorationPolicy();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ArtistDecorationPolicy.class) {
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

    @Override // com.spotify.cosmos.util.policy.proto.ArtistDecorationPolicyOrBuilder
    public boolean getIsVariousArtists() {
        return this.isVariousArtists_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ArtistDecorationPolicyOrBuilder
    public boolean getLink() {
        return this.link_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ArtistDecorationPolicyOrBuilder
    public boolean getName() {
        return this.name_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ArtistDecorationPolicyOrBuilder
    public boolean getPortraits() {
        return this.portraits_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(ArtistDecorationPolicy artistDecorationPolicy) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(artistDecorationPolicy);
    }

    public static ArtistDecorationPolicy parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (ArtistDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ArtistDecorationPolicy parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (ArtistDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static ArtistDecorationPolicy parseFrom(ByteString byteString) {
        return (ArtistDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ArtistDecorationPolicy parseFrom(ByteString byteString, rl0 rl0Var) {
        return (ArtistDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static ArtistDecorationPolicy parseFrom(byte[] bArr) {
        return (ArtistDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ArtistDecorationPolicy parseFrom(byte[] bArr, rl0 rl0Var) {
        return (ArtistDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static ArtistDecorationPolicy parseFrom(InputStream inputStream) {
        return (ArtistDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ArtistDecorationPolicy parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (ArtistDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ArtistDecorationPolicy parseFrom(au auVar) {
        return (ArtistDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static ArtistDecorationPolicy parseFrom(au auVar, rl0 rl0Var) {
        return (ArtistDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
