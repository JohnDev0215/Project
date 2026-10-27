package com.spotify.connectivity.esperanto.proto;

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
public final class PutRulesRequest extends i implements PutRulesRequestOrBuilder {
    public static final int ALLOW_SYNC_OVER_CELLULAR_FIELD_NUMBER = 1;
    private static final PutRulesRequest DEFAULT_INSTANCE;
    private static volatile ds1 PARSER;
    private boolean allowSyncOverCellular_;
    private int bitField0_;

    /* JADX INFO: renamed from: com.spotify.connectivity.esperanto.proto.PutRulesRequest$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements PutRulesRequestOrBuilder {
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

        public Builder clearAllowSyncOverCellular() {
            copyOnWrite();
            ((PutRulesRequest) this.instance).clearAllowSyncOverCellular();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // com.spotify.connectivity.esperanto.proto.PutRulesRequestOrBuilder
        public boolean getAllowSyncOverCellular() {
            return ((PutRulesRequest) this.instance).getAllowSyncOverCellular();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.esperanto.proto.PutRulesRequestOrBuilder
        public boolean hasAllowSyncOverCellular() {
            return ((PutRulesRequest) this.instance).hasAllowSyncOverCellular();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setAllowSyncOverCellular(boolean z) {
            copyOnWrite();
            ((PutRulesRequest) this.instance).setAllowSyncOverCellular(z);
            return this;
        }

        private Builder() {
            super(PutRulesRequest.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m154mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m155mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m156mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        PutRulesRequest putRulesRequest = new PutRulesRequest();
        DEFAULT_INSTANCE = putRulesRequest;
        i.registerDefaultInstance(PutRulesRequest.class, putRulesRequest);
    }

    private PutRulesRequest() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAllowSyncOverCellular() {
        this.bitField0_ &= -2;
        this.allowSyncOverCellular_ = false;
    }

    public static PutRulesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static PutRulesRequest parseDelimitedFrom(InputStream inputStream) {
        return (PutRulesRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static PutRulesRequest parseFrom(ByteBuffer byteBuffer) {
        return (PutRulesRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAllowSyncOverCellular(boolean z) {
        this.bitField0_ |= 1;
        this.allowSyncOverCellular_ = z;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဇ\u0000", new Object[]{"bitField0_", "allowSyncOverCellular_"});
            case 3:
                return new PutRulesRequest();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (PutRulesRequest.class) {
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

    @Override // com.spotify.connectivity.esperanto.proto.PutRulesRequestOrBuilder
    public boolean getAllowSyncOverCellular() {
        return this.allowSyncOverCellular_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.connectivity.esperanto.proto.PutRulesRequestOrBuilder
    public boolean hasAllowSyncOverCellular() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(PutRulesRequest putRulesRequest) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(putRulesRequest);
    }

    public static PutRulesRequest parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (PutRulesRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static PutRulesRequest parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (PutRulesRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static PutRulesRequest parseFrom(ByteString byteString) {
        return (PutRulesRequest) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static PutRulesRequest parseFrom(ByteString byteString, rl0 rl0Var) {
        return (PutRulesRequest) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static PutRulesRequest parseFrom(byte[] bArr) {
        return (PutRulesRequest) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static PutRulesRequest parseFrom(byte[] bArr, rl0 rl0Var) {
        return (PutRulesRequest) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static PutRulesRequest parseFrom(InputStream inputStream) {
        return (PutRulesRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static PutRulesRequest parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (PutRulesRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static PutRulesRequest parseFrom(au auVar) {
        return (PutRulesRequest) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static PutRulesRequest parseFrom(au auVar, rl0 rl0Var) {
        return (PutRulesRequest) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
