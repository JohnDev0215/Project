package com.spotify.connectivity.product_state.esperanto.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import p.a2;
import p.au;
import p.ds1;
import p.h01;
import p.i01;
import p.r1;
import p.rl0;
import p.v51;
import p.vj1;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class DelOverridesValuesRequest extends i implements DelOverridesValuesRequestOrBuilder {
    private static final DelOverridesValuesRequest DEFAULT_INSTANCE;
    public static final int KEYS_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private v51 keys_ = i.emptyProtobufList();

    /* JADX INFO: renamed from: com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesRequest$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements DelOverridesValuesRequestOrBuilder {
        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public Builder addAllKeys(Iterable<String> iterable) {
            copyOnWrite();
            ((DelOverridesValuesRequest) this.instance).addAllKeys(iterable);
            return this;
        }

        public Builder addKeys(String str) {
            copyOnWrite();
            ((DelOverridesValuesRequest) this.instance).addKeys(str);
            return this;
        }

        public Builder addKeysBytes(ByteString byteString) {
            copyOnWrite();
            ((DelOverridesValuesRequest) this.instance).addKeysBytes(byteString);
            return this;
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

        public Builder clearKeys() {
            copyOnWrite();
            ((DelOverridesValuesRequest) this.instance).clearKeys();
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

        @Override // com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesRequestOrBuilder
        public String getKeys(int i) {
            return ((DelOverridesValuesRequest) this.instance).getKeys(i);
        }

        @Override // com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesRequestOrBuilder
        public ByteString getKeysBytes(int i) {
            return ((DelOverridesValuesRequest) this.instance).getKeysBytes(i);
        }

        @Override // com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesRequestOrBuilder
        public int getKeysCount() {
            return ((DelOverridesValuesRequest) this.instance).getKeysCount();
        }

        @Override // com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesRequestOrBuilder
        public List<String> getKeysList() {
            return Collections.unmodifiableList(((DelOverridesValuesRequest) this.instance).getKeysList());
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setKeys(int i, String str) {
            copyOnWrite();
            ((DelOverridesValuesRequest) this.instance).setKeys(i, str);
            return this;
        }

        private Builder() {
            super(DelOverridesValuesRequest.DEFAULT_INSTANCE);
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
        public /* bridge */ /* synthetic */ vj1 m164mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m165mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m166mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        DelOverridesValuesRequest delOverridesValuesRequest = new DelOverridesValuesRequest();
        DEFAULT_INSTANCE = delOverridesValuesRequest;
        i.registerDefaultInstance(DelOverridesValuesRequest.class, delOverridesValuesRequest);
    }

    private DelOverridesValuesRequest() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllKeys(Iterable<String> iterable) {
        ensureKeysIsMutable();
        a.addAll((Iterable) iterable, (List) this.keys_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addKeys(String str) {
        str.getClass();
        ensureKeysIsMutable();
        this.keys_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addKeysBytes(ByteString byteString) {
        a.checkByteStringIsUtf8(byteString);
        ensureKeysIsMutable();
        this.keys_.add(byteString.s());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearKeys() {
        this.keys_ = i.emptyProtobufList();
    }

    private void ensureKeysIsMutable() {
        v51 v51Var = this.keys_;
        if (((a2) v51Var).c) {
            return;
        }
        this.keys_ = i.mutableCopy(v51Var);
    }

    public static DelOverridesValuesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static DelOverridesValuesRequest parseDelimitedFrom(InputStream inputStream) {
        return (DelOverridesValuesRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DelOverridesValuesRequest parseFrom(ByteBuffer byteBuffer) {
        return (DelOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setKeys(int i, String str) {
        str.getClass();
        ensureKeysIsMutable();
        this.keys_.set(i, str);
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001Ț", new Object[]{"keys_"});
            case 3:
                return new DelOverridesValuesRequest();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (DelOverridesValuesRequest.class) {
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

    @Override // com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesRequestOrBuilder
    public String getKeys(int i) {
        return (String) this.keys_.get(i);
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesRequestOrBuilder
    public ByteString getKeysBytes(int i) {
        return ByteString.d((String) this.keys_.get(i));
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesRequestOrBuilder
    public int getKeysCount() {
        return this.keys_.size();
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesRequestOrBuilder
    public List<String> getKeysList() {
        return this.keys_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(DelOverridesValuesRequest delOverridesValuesRequest) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(delOverridesValuesRequest);
    }

    public static DelOverridesValuesRequest parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (DelOverridesValuesRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static DelOverridesValuesRequest parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (DelOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static DelOverridesValuesRequest parseFrom(ByteString byteString) {
        return (DelOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DelOverridesValuesRequest parseFrom(ByteString byteString, rl0 rl0Var) {
        return (DelOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static DelOverridesValuesRequest parseFrom(byte[] bArr) {
        return (DelOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DelOverridesValuesRequest parseFrom(byte[] bArr, rl0 rl0Var) {
        return (DelOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static DelOverridesValuesRequest parseFrom(InputStream inputStream) {
        return (DelOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DelOverridesValuesRequest parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (DelOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static DelOverridesValuesRequest parseFrom(au auVar) {
        return (DelOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static DelOverridesValuesRequest parseFrom(au auVar, rl0 rl0Var) {
        return (DelOverridesValuesRequest) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
