package com.spotify.localfiles.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import com.spotify.localfiles.proto.LocalFile;
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
public final class QueryResult extends i implements QueryResultOrBuilder {
    private static final QueryResult DEFAULT_INSTANCE;
    public static final int FILES_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER;
    private v51 files_ = i.emptyProtobufList();

    /* JADX INFO: renamed from: com.spotify.localfiles.proto.QueryResult$1, reason: invalid class name */
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

    public static final class Builder extends h01 implements QueryResultOrBuilder {
        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public Builder addAllFiles(Iterable<? extends LocalFile> iterable) {
            copyOnWrite();
            ((QueryResult) this.instance).addAllFiles(iterable);
            return this;
        }

        public Builder addFiles(LocalFile localFile) {
            copyOnWrite();
            ((QueryResult) this.instance).addFiles(localFile);
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

        public Builder clearFiles() {
            copyOnWrite();
            ((QueryResult) this.instance).clearFiles();
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

        @Override // com.spotify.localfiles.proto.QueryResultOrBuilder
        public LocalFile getFiles(int i) {
            return ((QueryResult) this.instance).getFiles(i);
        }

        @Override // com.spotify.localfiles.proto.QueryResultOrBuilder
        public int getFilesCount() {
            return ((QueryResult) this.instance).getFilesCount();
        }

        @Override // com.spotify.localfiles.proto.QueryResultOrBuilder
        public List<LocalFile> getFilesList() {
            return Collections.unmodifiableList(((QueryResult) this.instance).getFilesList());
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder removeFiles(int i) {
            copyOnWrite();
            ((QueryResult) this.instance).removeFiles(i);
            return this;
        }

        public Builder setFiles(int i, LocalFile localFile) {
            copyOnWrite();
            ((QueryResult) this.instance).setFiles(i, localFile);
            return this;
        }

        private Builder() {
            super(QueryResult.DEFAULT_INSTANCE);
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ r1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        public Builder addFiles(int i, LocalFile localFile) {
            copyOnWrite();
            ((QueryResult) this.instance).addFiles(i, localFile);
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ vj1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public Builder setFiles(int i, LocalFile.Builder builder) {
            copyOnWrite();
            ((QueryResult) this.instance).setFiles(i, (LocalFile) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString) {
            return super.mergeFrom(byteString);
        }

        public Builder addFiles(LocalFile.Builder builder) {
            copyOnWrite();
            ((QueryResult) this.instance).addFiles((LocalFile) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString, rl0 rl0Var) {
            return super.mergeFrom(byteString, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream) {
            return super.mergeFrom(inputStream);
        }

        public Builder addFiles(int i, LocalFile.Builder builder) {
            copyOnWrite();
            ((QueryResult) this.instance).addFiles(i, (LocalFile) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
            return super.mergeFrom(inputStream, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(au auVar) {
            return super.mergeFrom(auVar);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m324mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m325mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m326mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        QueryResult queryResult = new QueryResult();
        DEFAULT_INSTANCE = queryResult;
        i.registerDefaultInstance(QueryResult.class, queryResult);
    }

    private QueryResult() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllFiles(Iterable<? extends LocalFile> iterable) {
        ensureFilesIsMutable();
        a.addAll((Iterable) iterable, (List) this.files_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFiles(LocalFile localFile) {
        localFile.getClass();
        ensureFilesIsMutable();
        this.files_.add(localFile);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFiles() {
        this.files_ = i.emptyProtobufList();
    }

    private void ensureFilesIsMutable() {
        v51 v51Var = this.files_;
        if (((a2) v51Var).c) {
            return;
        }
        this.files_ = i.mutableCopy(v51Var);
    }

    public static QueryResult getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static QueryResult parseDelimitedFrom(InputStream inputStream) {
        return (QueryResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static QueryResult parseFrom(ByteBuffer byteBuffer) {
        return (QueryResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeFiles(int i) {
        ensureFilesIsMutable();
        this.files_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFiles(int i, LocalFile localFile) {
        localFile.getClass();
        ensureFilesIsMutable();
        this.files_.set(i, localFile);
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"files_", LocalFile.class});
            case 3:
                return new QueryResult();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (QueryResult.class) {
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

    @Override // com.spotify.localfiles.proto.QueryResultOrBuilder
    public LocalFile getFiles(int i) {
        return (LocalFile) this.files_.get(i);
    }

    @Override // com.spotify.localfiles.proto.QueryResultOrBuilder
    public int getFilesCount() {
        return this.files_.size();
    }

    @Override // com.spotify.localfiles.proto.QueryResultOrBuilder
    public List<LocalFile> getFilesList() {
        return this.files_;
    }

    public LocalFileOrBuilder getFilesOrBuilder(int i) {
        return (LocalFileOrBuilder) this.files_.get(i);
    }

    public List<? extends LocalFileOrBuilder> getFilesOrBuilderList() {
        return this.files_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(QueryResult queryResult) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(queryResult);
    }

    public static QueryResult parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (QueryResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static QueryResult parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (QueryResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static QueryResult parseFrom(ByteString byteString) {
        return (QueryResult) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFiles(int i, LocalFile localFile) {
        localFile.getClass();
        ensureFilesIsMutable();
        this.files_.add(i, localFile);
    }

    public static QueryResult parseFrom(ByteString byteString, rl0 rl0Var) {
        return (QueryResult) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static QueryResult parseFrom(byte[] bArr) {
        return (QueryResult) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static QueryResult parseFrom(byte[] bArr, rl0 rl0Var) {
        return (QueryResult) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static QueryResult parseFrom(InputStream inputStream) {
        return (QueryResult) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static QueryResult parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (QueryResult) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static QueryResult parseFrom(au auVar) {
        return (QueryResult) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static QueryResult parseFrom(au auVar, rl0 rl0Var) {
        return (QueryResult) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
