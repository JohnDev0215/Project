package com.android.permissioncontroller;

import com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public final class PermissionControllerProto {

    public interface PermissionControllerDumpProtoOrBuilder extends MessageLiteOrBuilder {
        AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto getAutoRevoke();

        String getLogs(int i);

        ByteString getLogsBytes(int i);

        int getLogsCount();

        List<String> getLogsList();

        boolean hasAutoRevoke();
    }

    private PermissionControllerProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class PermissionControllerDumpProto extends GeneratedMessageLite<PermissionControllerDumpProto, Builder> implements PermissionControllerDumpProtoOrBuilder {
        public static final int AUTOREVOKE_FIELD_NUMBER = 1;
        private static final PermissionControllerDumpProto DEFAULT_INSTANCE;
        public static final int LOGS_FIELD_NUMBER = 3;
        private static volatile Parser<PermissionControllerDumpProto> PARSER;
        private AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto autoRevoke_;
        private int bitField0_;
        private Internal.ProtobufList<String> logs_ = GeneratedMessageLite.emptyProtobufList();

        private PermissionControllerDumpProto() {
        }

        @Override // com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProtoOrBuilder
        public boolean hasAutoRevoke() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProtoOrBuilder
        public AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto getAutoRevoke() {
            AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto autoRevokePermissionsDumpProto = this.autoRevoke_;
            return autoRevokePermissionsDumpProto == null ? AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto.getDefaultInstance() : autoRevokePermissionsDumpProto;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAutoRevoke(AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto value) {
            if (value == null) {
                throw null;
            }
            this.autoRevoke_ = value;
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAutoRevoke(AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto.Builder builderForValue) {
            this.autoRevoke_ = builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAutoRevoke(AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto value) {
            if (value == null) {
                throw null;
            }
            AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto autoRevokePermissionsDumpProto = this.autoRevoke_;
            if (autoRevokePermissionsDumpProto != null && autoRevokePermissionsDumpProto != AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto.getDefaultInstance()) {
                this.autoRevoke_ = AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto.newBuilder(this.autoRevoke_).mergeFrom(value).buildPartial();
            } else {
                this.autoRevoke_ = value;
            }
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAutoRevoke() {
            this.autoRevoke_ = null;
            this.bitField0_ &= -2;
        }

        @Override // com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProtoOrBuilder
        public List<String> getLogsList() {
            return this.logs_;
        }

        @Override // com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProtoOrBuilder
        public int getLogsCount() {
            return this.logs_.size();
        }

        @Override // com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProtoOrBuilder
        public String getLogs(int index) {
            return this.logs_.get(index);
        }

        @Override // com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProtoOrBuilder
        public ByteString getLogsBytes(int index) {
            return ByteString.copyFromUtf8(this.logs_.get(index));
        }

        private void ensureLogsIsMutable() {
            if (!this.logs_.isModifiable()) {
                this.logs_ = GeneratedMessageLite.mutableCopy(this.logs_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLogs(int index, String value) {
            if (value == null) {
                throw null;
            }
            ensureLogsIsMutable();
            this.logs_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLogs(String value) {
            if (value == null) {
                throw null;
            }
            ensureLogsIsMutable();
            this.logs_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllLogs(Iterable<String> values) {
            ensureLogsIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.logs_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLogs() {
            this.logs_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addLogsBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            ensureLogsIsMutable();
            this.logs_.add(value.toStringUtf8());
        }

        public static PermissionControllerDumpProto parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (PermissionControllerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PermissionControllerDumpProto parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PermissionControllerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PermissionControllerDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PermissionControllerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PermissionControllerDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PermissionControllerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PermissionControllerDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PermissionControllerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PermissionControllerDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PermissionControllerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PermissionControllerDumpProto parseFrom(InputStream input) throws IOException {
            return (PermissionControllerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PermissionControllerDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PermissionControllerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PermissionControllerDumpProto parseDelimitedFrom(InputStream input) throws IOException {
            return (PermissionControllerDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PermissionControllerDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PermissionControllerDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PermissionControllerDumpProto parseFrom(CodedInputStream input) throws IOException {
            return (PermissionControllerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PermissionControllerDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PermissionControllerDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(PermissionControllerDumpProto prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PermissionControllerDumpProto, Builder> implements PermissionControllerDumpProtoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PermissionControllerDumpProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProtoOrBuilder
            public boolean hasAutoRevoke() {
                return ((PermissionControllerDumpProto) this.instance).hasAutoRevoke();
            }

            @Override // com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProtoOrBuilder
            public AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto getAutoRevoke() {
                return ((PermissionControllerDumpProto) this.instance).getAutoRevoke();
            }

            public Builder setAutoRevoke(AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto value) {
                copyOnWrite();
                ((PermissionControllerDumpProto) this.instance).setAutoRevoke(value);
                return this;
            }

            public Builder setAutoRevoke(AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto.Builder builderForValue) {
                copyOnWrite();
                ((PermissionControllerDumpProto) this.instance).setAutoRevoke(builderForValue);
                return this;
            }

            public Builder mergeAutoRevoke(AutoRevokePermissionsProto.AutoRevokePermissionsDumpProto value) {
                copyOnWrite();
                ((PermissionControllerDumpProto) this.instance).mergeAutoRevoke(value);
                return this;
            }

            public Builder clearAutoRevoke() {
                copyOnWrite();
                ((PermissionControllerDumpProto) this.instance).clearAutoRevoke();
                return this;
            }

            @Override // com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProtoOrBuilder
            public List<String> getLogsList() {
                return Collections.unmodifiableList(((PermissionControllerDumpProto) this.instance).getLogsList());
            }

            @Override // com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProtoOrBuilder
            public int getLogsCount() {
                return ((PermissionControllerDumpProto) this.instance).getLogsCount();
            }

            @Override // com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProtoOrBuilder
            public String getLogs(int index) {
                return ((PermissionControllerDumpProto) this.instance).getLogs(index);
            }

            @Override // com.android.permissioncontroller.PermissionControllerProto.PermissionControllerDumpProtoOrBuilder
            public ByteString getLogsBytes(int index) {
                return ((PermissionControllerDumpProto) this.instance).getLogsBytes(index);
            }

            public Builder setLogs(int index, String value) {
                copyOnWrite();
                ((PermissionControllerDumpProto) this.instance).setLogs(index, value);
                return this;
            }

            public Builder addLogs(String value) {
                copyOnWrite();
                ((PermissionControllerDumpProto) this.instance).addLogs(value);
                return this;
            }

            public Builder addAllLogs(Iterable<String> values) {
                copyOnWrite();
                ((PermissionControllerDumpProto) this.instance).addAllLogs(values);
                return this;
            }

            public Builder clearLogs() {
                copyOnWrite();
                ((PermissionControllerDumpProto) this.instance).clearLogs();
                return this;
            }

            public Builder addLogsBytes(ByteString value) {
                copyOnWrite();
                ((PermissionControllerDumpProto) this.instance).addLogsBytes(value);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new PermissionControllerDumpProto();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "autoRevoke_", "logs_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0003\u0002\u0000\u0001\u0000\u0001\t\u0000\u0003\u001a", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PermissionControllerDumpProto> parser = PARSER;
                    if (parser == null) {
                        synchronized (PermissionControllerDumpProto.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                            break;
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            PermissionControllerDumpProto defaultInstance = new PermissionControllerDumpProto();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(PermissionControllerDumpProto.class, defaultInstance);
        }

        public static PermissionControllerDumpProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PermissionControllerDumpProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.PermissionControllerProto$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }
}
