package android.gamedriver;

import com.android.framework.protobuf.AbstractMessageLite;
import com.android.framework.protobuf.ByteString;
import com.android.framework.protobuf.CodedInputStream;
import com.android.framework.protobuf.ExtensionRegistryLite;
import com.android.framework.protobuf.GeneratedMessageLite;
import com.android.framework.protobuf.Internal;
import com.android.framework.protobuf.InvalidProtocolBufferException;
import com.android.framework.protobuf.MessageLiteOrBuilder;
import com.android.framework.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class GameDriverProto {

    public interface BlacklistOrBuilder extends MessageLiteOrBuilder {
        String getPackageNames(int i);

        ByteString getPackageNamesBytes(int i);

        int getPackageNamesCount();

        List<String> getPackageNamesList();

        long getVersionCode();

        boolean hasVersionCode();
    }

    public interface BlacklistsOrBuilder extends MessageLiteOrBuilder {
        Blacklist getBlacklists(int i);

        int getBlacklistsCount();

        List<Blacklist> getBlacklistsList();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private GameDriverProto() {
    }

    public static final class Blacklist extends GeneratedMessageLite<Blacklist, Builder> implements BlacklistOrBuilder {
        private static final Blacklist DEFAULT_INSTANCE;
        public static final int PACKAGE_NAMES_FIELD_NUMBER = 2;
        private static volatile Parser<Blacklist> PARSER = null;
        public static final int VERSION_CODE_FIELD_NUMBER = 1;
        private int bitField0_;
        private Internal.ProtobufList<String> packageNames_ = GeneratedMessageLite.emptyProtobufList();
        private long versionCode_;

        private Blacklist() {
        }

        @Override // android.gamedriver.GameDriverProto.BlacklistOrBuilder
        public boolean hasVersionCode() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // android.gamedriver.GameDriverProto.BlacklistOrBuilder
        public long getVersionCode() {
            return this.versionCode_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersionCode(long j) {
            this.bitField0_ |= 1;
            this.versionCode_ = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersionCode() {
            this.bitField0_ &= -2;
            this.versionCode_ = 0L;
        }

        @Override // android.gamedriver.GameDriverProto.BlacklistOrBuilder
        public List<String> getPackageNamesList() {
            return this.packageNames_;
        }

        @Override // android.gamedriver.GameDriverProto.BlacklistOrBuilder
        public int getPackageNamesCount() {
            return this.packageNames_.size();
        }

        @Override // android.gamedriver.GameDriverProto.BlacklistOrBuilder
        public String getPackageNames(int i) {
            return this.packageNames_.get(i);
        }

        @Override // android.gamedriver.GameDriverProto.BlacklistOrBuilder
        public ByteString getPackageNamesBytes(int i) {
            return ByteString.copyFromUtf8(this.packageNames_.get(i));
        }

        private void ensurePackageNamesIsMutable() {
            if (this.packageNames_.isModifiable()) {
                return;
            }
            this.packageNames_ = GeneratedMessageLite.mutableCopy(this.packageNames_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackageNames(int i, String str) {
            Objects.requireNonNull(str);
            ensurePackageNamesIsMutable();
            this.packageNames_.set(i, str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPackageNames(String str) {
            Objects.requireNonNull(str);
            ensurePackageNamesIsMutable();
            this.packageNames_.add(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllPackageNames(Iterable<String> iterable) {
            ensurePackageNamesIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.packageNames_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPackageNames() {
            this.packageNames_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPackageNamesBytes(ByteString byteString) {
            Objects.requireNonNull(byteString);
            ensurePackageNamesIsMutable();
            this.packageNames_.add(byteString.toStringUtf8());
        }

        public static Blacklist parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Blacklist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Blacklist parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Blacklist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Blacklist parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Blacklist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Blacklist parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Blacklist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Blacklist parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Blacklist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Blacklist parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Blacklist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Blacklist parseFrom(InputStream inputStream) throws IOException {
            return (Blacklist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Blacklist parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Blacklist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Blacklist parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Blacklist) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Blacklist parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Blacklist) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Blacklist parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Blacklist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Blacklist parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Blacklist) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Blacklist blacklist) {
            return DEFAULT_INSTANCE.createBuilder(blacklist);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Blacklist, Builder> implements BlacklistOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(Blacklist.DEFAULT_INSTANCE);
            }

            @Override // android.gamedriver.GameDriverProto.BlacklistOrBuilder
            public boolean hasVersionCode() {
                return ((Blacklist) this.instance).hasVersionCode();
            }

            @Override // android.gamedriver.GameDriverProto.BlacklistOrBuilder
            public long getVersionCode() {
                return ((Blacklist) this.instance).getVersionCode();
            }

            public Builder setVersionCode(long j) {
                copyOnWrite();
                ((Blacklist) this.instance).setVersionCode(j);
                return this;
            }

            public Builder clearVersionCode() {
                copyOnWrite();
                ((Blacklist) this.instance).clearVersionCode();
                return this;
            }

            @Override // android.gamedriver.GameDriverProto.BlacklistOrBuilder
            public List<String> getPackageNamesList() {
                return Collections.unmodifiableList(((Blacklist) this.instance).getPackageNamesList());
            }

            @Override // android.gamedriver.GameDriverProto.BlacklistOrBuilder
            public int getPackageNamesCount() {
                return ((Blacklist) this.instance).getPackageNamesCount();
            }

            @Override // android.gamedriver.GameDriverProto.BlacklistOrBuilder
            public String getPackageNames(int i) {
                return ((Blacklist) this.instance).getPackageNames(i);
            }

            @Override // android.gamedriver.GameDriverProto.BlacklistOrBuilder
            public ByteString getPackageNamesBytes(int i) {
                return ((Blacklist) this.instance).getPackageNamesBytes(i);
            }

            public Builder setPackageNames(int i, String str) {
                copyOnWrite();
                ((Blacklist) this.instance).setPackageNames(i, str);
                return this;
            }

            public Builder addPackageNames(String str) {
                copyOnWrite();
                ((Blacklist) this.instance).addPackageNames(str);
                return this;
            }

            public Builder addAllPackageNames(Iterable<String> iterable) {
                copyOnWrite();
                ((Blacklist) this.instance).addAllPackageNames(iterable);
                return this;
            }

            public Builder clearPackageNames() {
                copyOnWrite();
                ((Blacklist) this.instance).clearPackageNames();
                return this;
            }

            public Builder addPackageNamesBytes(ByteString byteString) {
                copyOnWrite();
                ((Blacklist) this.instance).addPackageNamesBytes(byteString);
                return this;
            }
        }

        @Override // com.android.framework.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Blacklist();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u0002\u0000\u0002\u001a", new Object[]{"bitField0_", "versionCode_", "packageNames_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Blacklist> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Blacklist.class) {
                            defaultInstanceBasedParser = PARSER;
                            if (defaultInstanceBasedParser == null) {
                                defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = defaultInstanceBasedParser;
                            }
                            break;
                        }
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            Blacklist blacklist = new Blacklist();
            DEFAULT_INSTANCE = blacklist;
            GeneratedMessageLite.registerDefaultInstance(Blacklist.class, blacklist);
        }

        public static Blacklist getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Blacklist> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: android.gamedriver.GameDriverProto$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class Blacklists extends GeneratedMessageLite<Blacklists, Builder> implements BlacklistsOrBuilder {
        public static final int BLACKLISTS_FIELD_NUMBER = 1;
        private static final Blacklists DEFAULT_INSTANCE;
        private static volatile Parser<Blacklists> PARSER;
        private Internal.ProtobufList<Blacklist> blacklists_ = emptyProtobufList();

        private Blacklists() {
        }

        @Override // android.gamedriver.GameDriverProto.BlacklistsOrBuilder
        public List<Blacklist> getBlacklistsList() {
            return this.blacklists_;
        }

        public List<? extends BlacklistOrBuilder> getBlacklistsOrBuilderList() {
            return this.blacklists_;
        }

        @Override // android.gamedriver.GameDriverProto.BlacklistsOrBuilder
        public int getBlacklistsCount() {
            return this.blacklists_.size();
        }

        @Override // android.gamedriver.GameDriverProto.BlacklistsOrBuilder
        public Blacklist getBlacklists(int i) {
            return this.blacklists_.get(i);
        }

        public BlacklistOrBuilder getBlacklistsOrBuilder(int i) {
            return this.blacklists_.get(i);
        }

        private void ensureBlacklistsIsMutable() {
            if (this.blacklists_.isModifiable()) {
                return;
            }
            this.blacklists_ = GeneratedMessageLite.mutableCopy(this.blacklists_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBlacklists(int i, Blacklist blacklist) {
            Objects.requireNonNull(blacklist);
            ensureBlacklistsIsMutable();
            this.blacklists_.set(i, blacklist);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBlacklists(int i, Blacklist.Builder builder) {
            ensureBlacklistsIsMutable();
            this.blacklists_.set(i, builder.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addBlacklists(Blacklist blacklist) {
            Objects.requireNonNull(blacklist);
            ensureBlacklistsIsMutable();
            this.blacklists_.add(blacklist);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addBlacklists(int i, Blacklist blacklist) {
            Objects.requireNonNull(blacklist);
            ensureBlacklistsIsMutable();
            this.blacklists_.add(i, blacklist);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addBlacklists(Blacklist.Builder builder) {
            ensureBlacklistsIsMutable();
            this.blacklists_.add(builder.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addBlacklists(int i, Blacklist.Builder builder) {
            ensureBlacklistsIsMutable();
            this.blacklists_.add(i, builder.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllBlacklists(Iterable<? extends Blacklist> iterable) {
            ensureBlacklistsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.blacklists_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBlacklists() {
            this.blacklists_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeBlacklists(int i) {
            ensureBlacklistsIsMutable();
            this.blacklists_.remove(i);
        }

        public static Blacklists parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Blacklists) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Blacklists parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Blacklists) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Blacklists parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Blacklists) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Blacklists parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Blacklists) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Blacklists parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Blacklists) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Blacklists parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Blacklists) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Blacklists parseFrom(InputStream inputStream) throws IOException {
            return (Blacklists) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Blacklists parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Blacklists) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Blacklists parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Blacklists) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Blacklists parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Blacklists) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Blacklists parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Blacklists) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Blacklists parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Blacklists) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Blacklists blacklists) {
            return DEFAULT_INSTANCE.createBuilder(blacklists);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Blacklists, Builder> implements BlacklistsOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(Blacklists.DEFAULT_INSTANCE);
            }

            @Override // android.gamedriver.GameDriverProto.BlacklistsOrBuilder
            public List<Blacklist> getBlacklistsList() {
                return Collections.unmodifiableList(((Blacklists) this.instance).getBlacklistsList());
            }

            @Override // android.gamedriver.GameDriverProto.BlacklistsOrBuilder
            public int getBlacklistsCount() {
                return ((Blacklists) this.instance).getBlacklistsCount();
            }

            @Override // android.gamedriver.GameDriverProto.BlacklistsOrBuilder
            public Blacklist getBlacklists(int i) {
                return ((Blacklists) this.instance).getBlacklists(i);
            }

            public Builder setBlacklists(int i, Blacklist blacklist) {
                copyOnWrite();
                ((Blacklists) this.instance).setBlacklists(i, blacklist);
                return this;
            }

            public Builder setBlacklists(int i, Blacklist.Builder builder) {
                copyOnWrite();
                ((Blacklists) this.instance).setBlacklists(i, builder);
                return this;
            }

            public Builder addBlacklists(Blacklist blacklist) {
                copyOnWrite();
                ((Blacklists) this.instance).addBlacklists(blacklist);
                return this;
            }

            public Builder addBlacklists(int i, Blacklist blacklist) {
                copyOnWrite();
                ((Blacklists) this.instance).addBlacklists(i, blacklist);
                return this;
            }

            public Builder addBlacklists(Blacklist.Builder builder) {
                copyOnWrite();
                ((Blacklists) this.instance).addBlacklists(builder);
                return this;
            }

            public Builder addBlacklists(int i, Blacklist.Builder builder) {
                copyOnWrite();
                ((Blacklists) this.instance).addBlacklists(i, builder);
                return this;
            }

            public Builder addAllBlacklists(Iterable<? extends Blacklist> iterable) {
                copyOnWrite();
                ((Blacklists) this.instance).addAllBlacklists(iterable);
                return this;
            }

            public Builder clearBlacklists() {
                copyOnWrite();
                ((Blacklists) this.instance).clearBlacklists();
                return this;
            }

            public Builder removeBlacklists(int i) {
                copyOnWrite();
                ((Blacklists) this.instance).removeBlacklists(i);
                return this;
            }
        }

        @Override // com.android.framework.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Blacklists();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"blacklists_", Blacklist.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Blacklists> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Blacklists.class) {
                            defaultInstanceBasedParser = PARSER;
                            if (defaultInstanceBasedParser == null) {
                                defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = defaultInstanceBasedParser;
                            }
                            break;
                        }
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            Blacklists blacklists = new Blacklists();
            DEFAULT_INSTANCE = blacklists;
            GeneratedMessageLite.registerDefaultInstance(Blacklists.class, blacklists);
        }

        public static Blacklists getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Blacklists> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
