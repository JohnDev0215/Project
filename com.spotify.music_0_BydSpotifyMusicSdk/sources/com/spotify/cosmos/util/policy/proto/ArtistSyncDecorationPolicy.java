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
public final class ArtistSyncDecorationPolicy extends i implements ArtistSyncDecorationPolicyOrBuilder {
    private static final ArtistSyncDecorationPolicy DEFAULT_INSTANCE;
    public static final int INFERRED_OFFLINE_FIELD_NUMBER = 1;
    public static final int OFFLINE_STATE_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int SYNC_PROGRESS_FIELD_NUMBER = 3;
    private boolean inferredOffline_;
    private boolean offlineState_;
    private boolean syncProgress_;

    /* JADX INFO: renamed from: com.spotify.cosmos.util.policy.proto.ArtistSyncDecorationPolicy$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements ArtistSyncDecorationPolicyOrBuilder {
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

        public Builder clearInferredOffline() {
            copyOnWrite();
            ((ArtistSyncDecorationPolicy) this.instance).clearInferredOffline();
            return this;
        }

        public Builder clearOfflineState() {
            copyOnWrite();
            ((ArtistSyncDecorationPolicy) this.instance).clearOfflineState();
            return this;
        }

        public Builder clearSyncProgress() {
            copyOnWrite();
            ((ArtistSyncDecorationPolicy) this.instance).clearSyncProgress();
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

        @Override // com.spotify.cosmos.util.policy.proto.ArtistSyncDecorationPolicyOrBuilder
        public boolean getInferredOffline() {
            return ((ArtistSyncDecorationPolicy) this.instance).getInferredOffline();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ArtistSyncDecorationPolicyOrBuilder
        public boolean getOfflineState() {
            return ((ArtistSyncDecorationPolicy) this.instance).getOfflineState();
        }

        @Override // com.spotify.cosmos.util.policy.proto.ArtistSyncDecorationPolicyOrBuilder
        public boolean getSyncProgress() {
            return ((ArtistSyncDecorationPolicy) this.instance).getSyncProgress();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setInferredOffline(boolean z) {
            copyOnWrite();
            ((ArtistSyncDecorationPolicy) this.instance).setInferredOffline(z);
            return this;
        }

        public Builder setOfflineState(boolean z) {
            copyOnWrite();
            ((ArtistSyncDecorationPolicy) this.instance).setOfflineState(z);
            return this;
        }

        public Builder setSyncProgress(boolean z) {
            copyOnWrite();
            ((ArtistSyncDecorationPolicy) this.instance).setSyncProgress(z);
            return this;
        }

        private Builder() {
            super(ArtistSyncDecorationPolicy.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m211mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m212mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m213mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        ArtistSyncDecorationPolicy artistSyncDecorationPolicy = new ArtistSyncDecorationPolicy();
        DEFAULT_INSTANCE = artistSyncDecorationPolicy;
        i.registerDefaultInstance(ArtistSyncDecorationPolicy.class, artistSyncDecorationPolicy);
    }

    private ArtistSyncDecorationPolicy() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearInferredOffline() {
        this.inferredOffline_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOfflineState() {
        this.offlineState_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSyncProgress() {
        this.syncProgress_ = false;
    }

    public static ArtistSyncDecorationPolicy getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static ArtistSyncDecorationPolicy parseDelimitedFrom(InputStream inputStream) {
        return (ArtistSyncDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ArtistSyncDecorationPolicy parseFrom(ByteBuffer byteBuffer) {
        return (ArtistSyncDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setInferredOffline(boolean z) {
        this.inferredOffline_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOfflineState(boolean z) {
        this.offlineState_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSyncProgress(boolean z) {
        this.syncProgress_ = z;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0007", new Object[]{"inferredOffline_", "offlineState_", "syncProgress_"});
            case 3:
                return new ArtistSyncDecorationPolicy();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ArtistSyncDecorationPolicy.class) {
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

    @Override // com.spotify.cosmos.util.policy.proto.ArtistSyncDecorationPolicyOrBuilder
    public boolean getInferredOffline() {
        return this.inferredOffline_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ArtistSyncDecorationPolicyOrBuilder
    public boolean getOfflineState() {
        return this.offlineState_;
    }

    @Override // com.spotify.cosmos.util.policy.proto.ArtistSyncDecorationPolicyOrBuilder
    public boolean getSyncProgress() {
        return this.syncProgress_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(ArtistSyncDecorationPolicy artistSyncDecorationPolicy) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(artistSyncDecorationPolicy);
    }

    public static ArtistSyncDecorationPolicy parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (ArtistSyncDecorationPolicy) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ArtistSyncDecorationPolicy parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (ArtistSyncDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static ArtistSyncDecorationPolicy parseFrom(ByteString byteString) {
        return (ArtistSyncDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ArtistSyncDecorationPolicy parseFrom(ByteString byteString, rl0 rl0Var) {
        return (ArtistSyncDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static ArtistSyncDecorationPolicy parseFrom(byte[] bArr) {
        return (ArtistSyncDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ArtistSyncDecorationPolicy parseFrom(byte[] bArr, rl0 rl0Var) {
        return (ArtistSyncDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static ArtistSyncDecorationPolicy parseFrom(InputStream inputStream) {
        return (ArtistSyncDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ArtistSyncDecorationPolicy parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (ArtistSyncDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ArtistSyncDecorationPolicy parseFrom(au auVar) {
        return (ArtistSyncDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static ArtistSyncDecorationPolicy parseFrom(au auVar, rl0 rl0Var) {
        return (ArtistSyncDecorationPolicy) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
