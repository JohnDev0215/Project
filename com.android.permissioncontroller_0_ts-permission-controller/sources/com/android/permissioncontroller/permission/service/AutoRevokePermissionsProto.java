package com.android.permissioncontroller.permission.service;

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
public final class AutoRevokePermissionsProto {

    public interface AutoRevokePermissionsDumpProtoOrBuilder extends MessageLiteOrBuilder {
        TeamFoodSettingsProto getTeamfoodSettings();

        PerUserProto getUsers(int i);

        int getUsersCount();

        List<PerUserProto> getUsersList();

        boolean hasTeamfoodSettings();
    }

    public interface AutoRevokedPackageProtoOrBuilder extends MessageLiteOrBuilder {
        String getPackageName();

        ByteString getPackageNameBytes();

        String getRevokedGroups(int i);

        ByteString getRevokedGroupsBytes(int i);

        int getRevokedGroupsCount();

        List<String> getRevokedGroupsList();

        boolean hasPackageName();
    }

    public interface PackageProtoOrBuilder extends MessageLiteOrBuilder {
        long getFirstInstallTime();

        PermissionGroupProto getGroups(int i);

        int getGroupsCount();

        List<PermissionGroupProto> getGroupsList();

        String getImplementedServices(int i);

        ByteString getImplementedServicesBytes(int i);

        int getImplementedServicesCount();

        List<String> getImplementedServicesList();

        long getLastTimeVisible();

        String getPackageName();

        ByteString getPackageNameBytes();

        int getUid();

        boolean hasFirstInstallTime();

        boolean hasLastTimeVisible();

        boolean hasPackageName();

        boolean hasUid();
    }

    public interface PerUserProtoOrBuilder extends MessageLiteOrBuilder {
        PackageProto getPackages(int i);

        int getPackagesCount();

        List<PackageProto> getPackagesList();

        int getUserId();

        boolean hasUserId();
    }

    public interface PermissionGroupProtoOrBuilder extends MessageLiteOrBuilder {
        String getGroupName();

        ByteString getGroupNameBytes();

        boolean getIsAnyGrantedIncludingAppop();

        boolean getIsAutoRevoked();

        boolean getIsFixed();

        boolean getIsGrantedByDefault();

        boolean getIsGrantedByRole();

        boolean getIsUserSensitive();

        boolean hasGroupName();

        boolean hasIsAnyGrantedIncludingAppop();

        boolean hasIsAutoRevoked();

        boolean hasIsFixed();

        boolean hasIsGrantedByDefault();

        boolean hasIsGrantedByRole();

        boolean hasIsUserSensitive();
    }

    public interface TeamFoodSettingsProtoOrBuilder extends MessageLiteOrBuilder {
        long getCheckFrequencyMillis();

        boolean getEnabledForPreRApps();

        long getUnusedThresholdMillis();

        boolean hasCheckFrequencyMillis();

        boolean hasEnabledForPreRApps();

        boolean hasUnusedThresholdMillis();
    }

    private AutoRevokePermissionsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static final class PackageProto extends GeneratedMessageLite<PackageProto, Builder> implements PackageProtoOrBuilder {
        private static final PackageProto DEFAULT_INSTANCE;
        public static final int FIRST_INSTALL_TIME_FIELD_NUMBER = 5;
        public static final int GROUPS_FIELD_NUMBER = 6;
        public static final int IMPLEMENTED_SERVICES_FIELD_NUMBER = 7;
        public static final int LAST_TIME_VISIBLE_FIELD_NUMBER = 4;
        public static final int PACKAGE_NAME_FIELD_NUMBER = 2;
        private static volatile Parser<PackageProto> PARSER = null;
        public static final int UID_FIELD_NUMBER = 1;
        private int bitField0_;
        private long firstInstallTime_;
        private long lastTimeVisible_;
        private int uid_;
        private String packageName_ = "";
        private Internal.ProtobufList<PermissionGroupProto> groups_ = emptyProtobufList();
        private Internal.ProtobufList<String> implementedServices_ = GeneratedMessageLite.emptyProtobufList();

        private PackageProto() {
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUid(int value) {
            this.bitField0_ |= 1;
            this.uid_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUid() {
            this.bitField0_ &= -2;
            this.uid_ = 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public boolean hasPackageName() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public String getPackageName() {
            return this.packageName_;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public ByteString getPackageNameBytes() {
            return ByteString.copyFromUtf8(this.packageName_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackageName(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.packageName_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPackageName() {
            this.bitField0_ &= -3;
            this.packageName_ = getDefaultInstance().getPackageName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackageNameBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 2;
            this.packageName_ = value.toStringUtf8();
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public boolean hasLastTimeVisible() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public long getLastTimeVisible() {
            return this.lastTimeVisible_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLastTimeVisible(long value) {
            this.bitField0_ |= 4;
            this.lastTimeVisible_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLastTimeVisible() {
            this.bitField0_ &= -5;
            this.lastTimeVisible_ = 0L;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public boolean hasFirstInstallTime() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public long getFirstInstallTime() {
            return this.firstInstallTime_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirstInstallTime(long value) {
            this.bitField0_ |= 8;
            this.firstInstallTime_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFirstInstallTime() {
            this.bitField0_ &= -9;
            this.firstInstallTime_ = 0L;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public List<PermissionGroupProto> getGroupsList() {
            return this.groups_;
        }

        public List<? extends PermissionGroupProtoOrBuilder> getGroupsOrBuilderList() {
            return this.groups_;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public int getGroupsCount() {
            return this.groups_.size();
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public PermissionGroupProto getGroups(int index) {
            return this.groups_.get(index);
        }

        public PermissionGroupProtoOrBuilder getGroupsOrBuilder(int index) {
            return this.groups_.get(index);
        }

        private void ensureGroupsIsMutable() {
            if (!this.groups_.isModifiable()) {
                this.groups_ = GeneratedMessageLite.mutableCopy(this.groups_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGroups(int index, PermissionGroupProto value) {
            if (value == null) {
                throw null;
            }
            ensureGroupsIsMutable();
            this.groups_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGroups(int index, PermissionGroupProto.Builder builderForValue) {
            ensureGroupsIsMutable();
            this.groups_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addGroups(PermissionGroupProto value) {
            if (value == null) {
                throw null;
            }
            ensureGroupsIsMutable();
            this.groups_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addGroups(int index, PermissionGroupProto value) {
            if (value == null) {
                throw null;
            }
            ensureGroupsIsMutable();
            this.groups_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addGroups(PermissionGroupProto.Builder builderForValue) {
            ensureGroupsIsMutable();
            this.groups_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addGroups(int index, PermissionGroupProto.Builder builderForValue) {
            ensureGroupsIsMutable();
            this.groups_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllGroups(Iterable<? extends PermissionGroupProto> values) {
            ensureGroupsIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.groups_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGroups() {
            this.groups_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeGroups(int index) {
            ensureGroupsIsMutable();
            this.groups_.remove(index);
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public List<String> getImplementedServicesList() {
            return this.implementedServices_;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public int getImplementedServicesCount() {
            return this.implementedServices_.size();
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public String getImplementedServices(int index) {
            return this.implementedServices_.get(index);
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
        public ByteString getImplementedServicesBytes(int index) {
            return ByteString.copyFromUtf8(this.implementedServices_.get(index));
        }

        private void ensureImplementedServicesIsMutable() {
            if (!this.implementedServices_.isModifiable()) {
                this.implementedServices_ = GeneratedMessageLite.mutableCopy(this.implementedServices_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setImplementedServices(int index, String value) {
            if (value == null) {
                throw null;
            }
            ensureImplementedServicesIsMutable();
            this.implementedServices_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addImplementedServices(String value) {
            if (value == null) {
                throw null;
            }
            ensureImplementedServicesIsMutable();
            this.implementedServices_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllImplementedServices(Iterable<String> values) {
            ensureImplementedServicesIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.implementedServices_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearImplementedServices() {
            this.implementedServices_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addImplementedServicesBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            ensureImplementedServicesIsMutable();
            this.implementedServices_.add(value.toStringUtf8());
        }

        public static PackageProto parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PackageProto parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PackageProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PackageProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PackageProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PackageProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PackageProto parseFrom(InputStream input) throws IOException {
            return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PackageProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PackageProto parseDelimitedFrom(InputStream input) throws IOException {
            return (PackageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PackageProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PackageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PackageProto parseFrom(CodedInputStream input) throws IOException {
            return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PackageProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(PackageProto prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PackageProto, Builder> implements PackageProtoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PackageProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public boolean hasUid() {
                return ((PackageProto) this.instance).hasUid();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public int getUid() {
                return ((PackageProto) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((PackageProto) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((PackageProto) this.instance).clearUid();
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public boolean hasPackageName() {
                return ((PackageProto) this.instance).hasPackageName();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public String getPackageName() {
                return ((PackageProto) this.instance).getPackageName();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public ByteString getPackageNameBytes() {
                return ((PackageProto) this.instance).getPackageNameBytes();
            }

            public Builder setPackageName(String value) {
                copyOnWrite();
                ((PackageProto) this.instance).setPackageName(value);
                return this;
            }

            public Builder clearPackageName() {
                copyOnWrite();
                ((PackageProto) this.instance).clearPackageName();
                return this;
            }

            public Builder setPackageNameBytes(ByteString value) {
                copyOnWrite();
                ((PackageProto) this.instance).setPackageNameBytes(value);
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public boolean hasLastTimeVisible() {
                return ((PackageProto) this.instance).hasLastTimeVisible();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public long getLastTimeVisible() {
                return ((PackageProto) this.instance).getLastTimeVisible();
            }

            public Builder setLastTimeVisible(long value) {
                copyOnWrite();
                ((PackageProto) this.instance).setLastTimeVisible(value);
                return this;
            }

            public Builder clearLastTimeVisible() {
                copyOnWrite();
                ((PackageProto) this.instance).clearLastTimeVisible();
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public boolean hasFirstInstallTime() {
                return ((PackageProto) this.instance).hasFirstInstallTime();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public long getFirstInstallTime() {
                return ((PackageProto) this.instance).getFirstInstallTime();
            }

            public Builder setFirstInstallTime(long value) {
                copyOnWrite();
                ((PackageProto) this.instance).setFirstInstallTime(value);
                return this;
            }

            public Builder clearFirstInstallTime() {
                copyOnWrite();
                ((PackageProto) this.instance).clearFirstInstallTime();
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public List<PermissionGroupProto> getGroupsList() {
                return Collections.unmodifiableList(((PackageProto) this.instance).getGroupsList());
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public int getGroupsCount() {
                return ((PackageProto) this.instance).getGroupsCount();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public PermissionGroupProto getGroups(int index) {
                return ((PackageProto) this.instance).getGroups(index);
            }

            public Builder setGroups(int index, PermissionGroupProto value) {
                copyOnWrite();
                ((PackageProto) this.instance).setGroups(index, value);
                return this;
            }

            public Builder setGroups(int index, PermissionGroupProto.Builder builderForValue) {
                copyOnWrite();
                ((PackageProto) this.instance).setGroups(index, builderForValue);
                return this;
            }

            public Builder addGroups(PermissionGroupProto value) {
                copyOnWrite();
                ((PackageProto) this.instance).addGroups(value);
                return this;
            }

            public Builder addGroups(int index, PermissionGroupProto value) {
                copyOnWrite();
                ((PackageProto) this.instance).addGroups(index, value);
                return this;
            }

            public Builder addGroups(PermissionGroupProto.Builder builderForValue) {
                copyOnWrite();
                ((PackageProto) this.instance).addGroups(builderForValue);
                return this;
            }

            public Builder addGroups(int index, PermissionGroupProto.Builder builderForValue) {
                copyOnWrite();
                ((PackageProto) this.instance).addGroups(index, builderForValue);
                return this;
            }

            public Builder addAllGroups(Iterable<? extends PermissionGroupProto> values) {
                copyOnWrite();
                ((PackageProto) this.instance).addAllGroups(values);
                return this;
            }

            public Builder clearGroups() {
                copyOnWrite();
                ((PackageProto) this.instance).clearGroups();
                return this;
            }

            public Builder removeGroups(int index) {
                copyOnWrite();
                ((PackageProto) this.instance).removeGroups(index);
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public List<String> getImplementedServicesList() {
                return Collections.unmodifiableList(((PackageProto) this.instance).getImplementedServicesList());
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public int getImplementedServicesCount() {
                return ((PackageProto) this.instance).getImplementedServicesCount();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public String getImplementedServices(int index) {
                return ((PackageProto) this.instance).getImplementedServices(index);
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PackageProtoOrBuilder
            public ByteString getImplementedServicesBytes(int index) {
                return ((PackageProto) this.instance).getImplementedServicesBytes(index);
            }

            public Builder setImplementedServices(int index, String value) {
                copyOnWrite();
                ((PackageProto) this.instance).setImplementedServices(index, value);
                return this;
            }

            public Builder addImplementedServices(String value) {
                copyOnWrite();
                ((PackageProto) this.instance).addImplementedServices(value);
                return this;
            }

            public Builder addAllImplementedServices(Iterable<String> values) {
                copyOnWrite();
                ((PackageProto) this.instance).addAllImplementedServices(values);
                return this;
            }

            public Builder clearImplementedServices() {
                copyOnWrite();
                ((PackageProto) this.instance).clearImplementedServices();
                return this;
            }

            public Builder addImplementedServicesBytes(ByteString value) {
                copyOnWrite();
                ((PackageProto) this.instance).addImplementedServicesBytes(value);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new PackageProto();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "uid_", "packageName_", "lastTimeVisible_", "firstInstallTime_", "groups_", PermissionGroupProto.class, "implementedServices_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0007\u0006\u0000\u0002\u0000\u0001\u0004\u0000\u0002\b\u0001\u0004\u0002\u0002\u0005\u0002\u0003\u0006\u001b\u0007\u001a", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PackageProto> parser = PARSER;
                    if (parser == null) {
                        synchronized (PackageProto.class) {
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
            PackageProto defaultInstance = new PackageProto();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(PackageProto.class, defaultInstance);
        }

        public static PackageProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PackageProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto$1, reason: invalid class name */
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

    public static final class PermissionGroupProto extends GeneratedMessageLite<PermissionGroupProto, Builder> implements PermissionGroupProtoOrBuilder {
        private static final PermissionGroupProto DEFAULT_INSTANCE;
        public static final int GROUP_NAME_FIELD_NUMBER = 1;
        public static final int IS_ANY_GRANTED_INCLUDING_APPOP_FIELD_NUMBER = 3;
        public static final int IS_AUTO_REVOKED_FIELD_NUMBER = 7;
        public static final int IS_FIXED_FIELD_NUMBER = 2;
        public static final int IS_GRANTED_BY_DEFAULT_FIELD_NUMBER = 4;
        public static final int IS_GRANTED_BY_ROLE_FIELD_NUMBER = 5;
        public static final int IS_USER_SENSITIVE_FIELD_NUMBER = 6;
        private static volatile Parser<PermissionGroupProto> PARSER;
        private int bitField0_;
        private String groupName_ = "";
        private boolean isAnyGrantedIncludingAppop_;
        private boolean isAutoRevoked_;
        private boolean isFixed_;
        private boolean isGrantedByDefault_;
        private boolean isGrantedByRole_;
        private boolean isUserSensitive_;

        private PermissionGroupProto() {
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean hasGroupName() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public String getGroupName() {
            return this.groupName_;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public ByteString getGroupNameBytes() {
            return ByteString.copyFromUtf8(this.groupName_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGroupName(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.groupName_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGroupName() {
            this.bitField0_ &= -2;
            this.groupName_ = getDefaultInstance().getGroupName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGroupNameBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.groupName_ = value.toStringUtf8();
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean hasIsFixed() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean getIsFixed() {
            return this.isFixed_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsFixed(boolean value) {
            this.bitField0_ |= 2;
            this.isFixed_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsFixed() {
            this.bitField0_ &= -3;
            this.isFixed_ = false;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean hasIsAnyGrantedIncludingAppop() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean getIsAnyGrantedIncludingAppop() {
            return this.isAnyGrantedIncludingAppop_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsAnyGrantedIncludingAppop(boolean value) {
            this.bitField0_ |= 4;
            this.isAnyGrantedIncludingAppop_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsAnyGrantedIncludingAppop() {
            this.bitField0_ &= -5;
            this.isAnyGrantedIncludingAppop_ = false;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean hasIsGrantedByDefault() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean getIsGrantedByDefault() {
            return this.isGrantedByDefault_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsGrantedByDefault(boolean value) {
            this.bitField0_ |= 8;
            this.isGrantedByDefault_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsGrantedByDefault() {
            this.bitField0_ &= -9;
            this.isGrantedByDefault_ = false;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean hasIsGrantedByRole() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean getIsGrantedByRole() {
            return this.isGrantedByRole_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsGrantedByRole(boolean value) {
            this.bitField0_ |= 16;
            this.isGrantedByRole_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsGrantedByRole() {
            this.bitField0_ &= -17;
            this.isGrantedByRole_ = false;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean hasIsUserSensitive() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean getIsUserSensitive() {
            return this.isUserSensitive_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsUserSensitive(boolean value) {
            this.bitField0_ |= 32;
            this.isUserSensitive_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsUserSensitive() {
            this.bitField0_ &= -33;
            this.isUserSensitive_ = false;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean hasIsAutoRevoked() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
        public boolean getIsAutoRevoked() {
            return this.isAutoRevoked_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsAutoRevoked(boolean value) {
            this.bitField0_ |= 64;
            this.isAutoRevoked_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsAutoRevoked() {
            this.bitField0_ &= -65;
            this.isAutoRevoked_ = false;
        }

        public static PermissionGroupProto parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (PermissionGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PermissionGroupProto parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PermissionGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PermissionGroupProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PermissionGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PermissionGroupProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PermissionGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PermissionGroupProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PermissionGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PermissionGroupProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PermissionGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PermissionGroupProto parseFrom(InputStream input) throws IOException {
            return (PermissionGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PermissionGroupProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PermissionGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PermissionGroupProto parseDelimitedFrom(InputStream input) throws IOException {
            return (PermissionGroupProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PermissionGroupProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PermissionGroupProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PermissionGroupProto parseFrom(CodedInputStream input) throws IOException {
            return (PermissionGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PermissionGroupProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PermissionGroupProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(PermissionGroupProto prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PermissionGroupProto, Builder> implements PermissionGroupProtoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PermissionGroupProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean hasGroupName() {
                return ((PermissionGroupProto) this.instance).hasGroupName();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public String getGroupName() {
                return ((PermissionGroupProto) this.instance).getGroupName();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public ByteString getGroupNameBytes() {
                return ((PermissionGroupProto) this.instance).getGroupNameBytes();
            }

            public Builder setGroupName(String value) {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).setGroupName(value);
                return this;
            }

            public Builder clearGroupName() {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).clearGroupName();
                return this;
            }

            public Builder setGroupNameBytes(ByteString value) {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).setGroupNameBytes(value);
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean hasIsFixed() {
                return ((PermissionGroupProto) this.instance).hasIsFixed();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean getIsFixed() {
                return ((PermissionGroupProto) this.instance).getIsFixed();
            }

            public Builder setIsFixed(boolean value) {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).setIsFixed(value);
                return this;
            }

            public Builder clearIsFixed() {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).clearIsFixed();
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean hasIsAnyGrantedIncludingAppop() {
                return ((PermissionGroupProto) this.instance).hasIsAnyGrantedIncludingAppop();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean getIsAnyGrantedIncludingAppop() {
                return ((PermissionGroupProto) this.instance).getIsAnyGrantedIncludingAppop();
            }

            public Builder setIsAnyGrantedIncludingAppop(boolean value) {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).setIsAnyGrantedIncludingAppop(value);
                return this;
            }

            public Builder clearIsAnyGrantedIncludingAppop() {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).clearIsAnyGrantedIncludingAppop();
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean hasIsGrantedByDefault() {
                return ((PermissionGroupProto) this.instance).hasIsGrantedByDefault();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean getIsGrantedByDefault() {
                return ((PermissionGroupProto) this.instance).getIsGrantedByDefault();
            }

            public Builder setIsGrantedByDefault(boolean value) {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).setIsGrantedByDefault(value);
                return this;
            }

            public Builder clearIsGrantedByDefault() {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).clearIsGrantedByDefault();
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean hasIsGrantedByRole() {
                return ((PermissionGroupProto) this.instance).hasIsGrantedByRole();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean getIsGrantedByRole() {
                return ((PermissionGroupProto) this.instance).getIsGrantedByRole();
            }

            public Builder setIsGrantedByRole(boolean value) {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).setIsGrantedByRole(value);
                return this;
            }

            public Builder clearIsGrantedByRole() {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).clearIsGrantedByRole();
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean hasIsUserSensitive() {
                return ((PermissionGroupProto) this.instance).hasIsUserSensitive();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean getIsUserSensitive() {
                return ((PermissionGroupProto) this.instance).getIsUserSensitive();
            }

            public Builder setIsUserSensitive(boolean value) {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).setIsUserSensitive(value);
                return this;
            }

            public Builder clearIsUserSensitive() {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).clearIsUserSensitive();
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean hasIsAutoRevoked() {
                return ((PermissionGroupProto) this.instance).hasIsAutoRevoked();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PermissionGroupProtoOrBuilder
            public boolean getIsAutoRevoked() {
                return ((PermissionGroupProto) this.instance).getIsAutoRevoked();
            }

            public Builder setIsAutoRevoked(boolean value) {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).setIsAutoRevoked(value);
                return this;
            }

            public Builder clearIsAutoRevoked() {
                copyOnWrite();
                ((PermissionGroupProto) this.instance).clearIsAutoRevoked();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new PermissionGroupProto();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "groupName_", "isFixed_", "isAnyGrantedIncludingAppop_", "isGrantedByDefault_", "isGrantedByRole_", "isUserSensitive_", "isAutoRevoked_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001\b\u0000\u0002\u0007\u0001\u0003\u0007\u0002\u0004\u0007\u0003\u0005\u0007\u0004\u0006\u0007\u0005\u0007\u0007\u0006", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PermissionGroupProto> parser = PARSER;
                    if (parser == null) {
                        synchronized (PermissionGroupProto.class) {
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
            PermissionGroupProto defaultInstance = new PermissionGroupProto();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(PermissionGroupProto.class, defaultInstance);
        }

        public static PermissionGroupProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PermissionGroupProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class AutoRevokedPackageProto extends GeneratedMessageLite<AutoRevokedPackageProto, Builder> implements AutoRevokedPackageProtoOrBuilder {
        private static final AutoRevokedPackageProto DEFAULT_INSTANCE;
        public static final int PACKAGE_NAME_FIELD_NUMBER = 1;
        private static volatile Parser<AutoRevokedPackageProto> PARSER = null;
        public static final int REVOKED_GROUPS_FIELD_NUMBER = 2;
        private int bitField0_;
        private String packageName_ = "";
        private Internal.ProtobufList<String> revokedGroups_ = GeneratedMessageLite.emptyProtobufList();

        private AutoRevokedPackageProto() {
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
        public boolean hasPackageName() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
        public String getPackageName() {
            return this.packageName_;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
        public ByteString getPackageNameBytes() {
            return ByteString.copyFromUtf8(this.packageName_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackageName(String value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.packageName_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPackageName() {
            this.bitField0_ &= -2;
            this.packageName_ = getDefaultInstance().getPackageName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackageNameBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            this.bitField0_ |= 1;
            this.packageName_ = value.toStringUtf8();
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
        public List<String> getRevokedGroupsList() {
            return this.revokedGroups_;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
        public int getRevokedGroupsCount() {
            return this.revokedGroups_.size();
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
        public String getRevokedGroups(int index) {
            return this.revokedGroups_.get(index);
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
        public ByteString getRevokedGroupsBytes(int index) {
            return ByteString.copyFromUtf8(this.revokedGroups_.get(index));
        }

        private void ensureRevokedGroupsIsMutable() {
            if (!this.revokedGroups_.isModifiable()) {
                this.revokedGroups_ = GeneratedMessageLite.mutableCopy(this.revokedGroups_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRevokedGroups(int index, String value) {
            if (value == null) {
                throw null;
            }
            ensureRevokedGroupsIsMutable();
            this.revokedGroups_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addRevokedGroups(String value) {
            if (value == null) {
                throw null;
            }
            ensureRevokedGroupsIsMutable();
            this.revokedGroups_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllRevokedGroups(Iterable<String> values) {
            ensureRevokedGroupsIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.revokedGroups_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRevokedGroups() {
            this.revokedGroups_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addRevokedGroupsBytes(ByteString value) {
            if (value == null) {
                throw null;
            }
            ensureRevokedGroupsIsMutable();
            this.revokedGroups_.add(value.toStringUtf8());
        }

        public static AutoRevokedPackageProto parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (AutoRevokedPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AutoRevokedPackageProto parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AutoRevokedPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AutoRevokedPackageProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AutoRevokedPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AutoRevokedPackageProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AutoRevokedPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AutoRevokedPackageProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AutoRevokedPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AutoRevokedPackageProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AutoRevokedPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AutoRevokedPackageProto parseFrom(InputStream input) throws IOException {
            return (AutoRevokedPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AutoRevokedPackageProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AutoRevokedPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AutoRevokedPackageProto parseDelimitedFrom(InputStream input) throws IOException {
            return (AutoRevokedPackageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static AutoRevokedPackageProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AutoRevokedPackageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AutoRevokedPackageProto parseFrom(CodedInputStream input) throws IOException {
            return (AutoRevokedPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AutoRevokedPackageProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AutoRevokedPackageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(AutoRevokedPackageProto prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AutoRevokedPackageProto, Builder> implements AutoRevokedPackageProtoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(AutoRevokedPackageProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
            public boolean hasPackageName() {
                return ((AutoRevokedPackageProto) this.instance).hasPackageName();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
            public String getPackageName() {
                return ((AutoRevokedPackageProto) this.instance).getPackageName();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
            public ByteString getPackageNameBytes() {
                return ((AutoRevokedPackageProto) this.instance).getPackageNameBytes();
            }

            public Builder setPackageName(String value) {
                copyOnWrite();
                ((AutoRevokedPackageProto) this.instance).setPackageName(value);
                return this;
            }

            public Builder clearPackageName() {
                copyOnWrite();
                ((AutoRevokedPackageProto) this.instance).clearPackageName();
                return this;
            }

            public Builder setPackageNameBytes(ByteString value) {
                copyOnWrite();
                ((AutoRevokedPackageProto) this.instance).setPackageNameBytes(value);
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
            public List<String> getRevokedGroupsList() {
                return Collections.unmodifiableList(((AutoRevokedPackageProto) this.instance).getRevokedGroupsList());
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
            public int getRevokedGroupsCount() {
                return ((AutoRevokedPackageProto) this.instance).getRevokedGroupsCount();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
            public String getRevokedGroups(int index) {
                return ((AutoRevokedPackageProto) this.instance).getRevokedGroups(index);
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokedPackageProtoOrBuilder
            public ByteString getRevokedGroupsBytes(int index) {
                return ((AutoRevokedPackageProto) this.instance).getRevokedGroupsBytes(index);
            }

            public Builder setRevokedGroups(int index, String value) {
                copyOnWrite();
                ((AutoRevokedPackageProto) this.instance).setRevokedGroups(index, value);
                return this;
            }

            public Builder addRevokedGroups(String value) {
                copyOnWrite();
                ((AutoRevokedPackageProto) this.instance).addRevokedGroups(value);
                return this;
            }

            public Builder addAllRevokedGroups(Iterable<String> values) {
                copyOnWrite();
                ((AutoRevokedPackageProto) this.instance).addAllRevokedGroups(values);
                return this;
            }

            public Builder clearRevokedGroups() {
                copyOnWrite();
                ((AutoRevokedPackageProto) this.instance).clearRevokedGroups();
                return this;
            }

            public Builder addRevokedGroupsBytes(ByteString value) {
                copyOnWrite();
                ((AutoRevokedPackageProto) this.instance).addRevokedGroupsBytes(value);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new AutoRevokedPackageProto();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "packageName_", "revokedGroups_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\b\u0000\u0002\u001a", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<AutoRevokedPackageProto> parser = PARSER;
                    if (parser == null) {
                        synchronized (AutoRevokedPackageProto.class) {
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
            AutoRevokedPackageProto defaultInstance = new AutoRevokedPackageProto();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(AutoRevokedPackageProto.class, defaultInstance);
        }

        public static AutoRevokedPackageProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AutoRevokedPackageProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class PerUserProto extends GeneratedMessageLite<PerUserProto, Builder> implements PerUserProtoOrBuilder {
        private static final PerUserProto DEFAULT_INSTANCE;
        public static final int PACKAGES_FIELD_NUMBER = 2;
        private static volatile Parser<PerUserProto> PARSER = null;
        public static final int USER_ID_FIELD_NUMBER = 1;
        private int bitField0_;
        private Internal.ProtobufList<PackageProto> packages_ = emptyProtobufList();
        private int userId_;

        private PerUserProto() {
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PerUserProtoOrBuilder
        public boolean hasUserId() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PerUserProtoOrBuilder
        public int getUserId() {
            return this.userId_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUserId(int value) {
            this.bitField0_ |= 1;
            this.userId_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUserId() {
            this.bitField0_ &= -2;
            this.userId_ = 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PerUserProtoOrBuilder
        public List<PackageProto> getPackagesList() {
            return this.packages_;
        }

        public List<? extends PackageProtoOrBuilder> getPackagesOrBuilderList() {
            return this.packages_;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PerUserProtoOrBuilder
        public int getPackagesCount() {
            return this.packages_.size();
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PerUserProtoOrBuilder
        public PackageProto getPackages(int index) {
            return this.packages_.get(index);
        }

        public PackageProtoOrBuilder getPackagesOrBuilder(int index) {
            return this.packages_.get(index);
        }

        private void ensurePackagesIsMutable() {
            if (!this.packages_.isModifiable()) {
                this.packages_ = GeneratedMessageLite.mutableCopy(this.packages_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackages(int index, PackageProto value) {
            if (value == null) {
                throw null;
            }
            ensurePackagesIsMutable();
            this.packages_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackages(int index, PackageProto.Builder builderForValue) {
            ensurePackagesIsMutable();
            this.packages_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPackages(PackageProto value) {
            if (value == null) {
                throw null;
            }
            ensurePackagesIsMutable();
            this.packages_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPackages(int index, PackageProto value) {
            if (value == null) {
                throw null;
            }
            ensurePackagesIsMutable();
            this.packages_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPackages(PackageProto.Builder builderForValue) {
            ensurePackagesIsMutable();
            this.packages_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPackages(int index, PackageProto.Builder builderForValue) {
            ensurePackagesIsMutable();
            this.packages_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllPackages(Iterable<? extends PackageProto> values) {
            ensurePackagesIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.packages_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPackages() {
            this.packages_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removePackages(int index) {
            ensurePackagesIsMutable();
            this.packages_.remove(index);
        }

        public static PerUserProto parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (PerUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PerUserProto parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PerUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PerUserProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (PerUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PerUserProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PerUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PerUserProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (PerUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static PerUserProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (PerUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static PerUserProto parseFrom(InputStream input) throws IOException {
            return (PerUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PerUserProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PerUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PerUserProto parseDelimitedFrom(InputStream input) throws IOException {
            return (PerUserProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static PerUserProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PerUserProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static PerUserProto parseFrom(CodedInputStream input) throws IOException {
            return (PerUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static PerUserProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (PerUserProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(PerUserProto prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PerUserProto, Builder> implements PerUserProtoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(PerUserProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PerUserProtoOrBuilder
            public boolean hasUserId() {
                return ((PerUserProto) this.instance).hasUserId();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PerUserProtoOrBuilder
            public int getUserId() {
                return ((PerUserProto) this.instance).getUserId();
            }

            public Builder setUserId(int value) {
                copyOnWrite();
                ((PerUserProto) this.instance).setUserId(value);
                return this;
            }

            public Builder clearUserId() {
                copyOnWrite();
                ((PerUserProto) this.instance).clearUserId();
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PerUserProtoOrBuilder
            public List<PackageProto> getPackagesList() {
                return Collections.unmodifiableList(((PerUserProto) this.instance).getPackagesList());
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PerUserProtoOrBuilder
            public int getPackagesCount() {
                return ((PerUserProto) this.instance).getPackagesCount();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.PerUserProtoOrBuilder
            public PackageProto getPackages(int index) {
                return ((PerUserProto) this.instance).getPackages(index);
            }

            public Builder setPackages(int index, PackageProto value) {
                copyOnWrite();
                ((PerUserProto) this.instance).setPackages(index, value);
                return this;
            }

            public Builder setPackages(int index, PackageProto.Builder builderForValue) {
                copyOnWrite();
                ((PerUserProto) this.instance).setPackages(index, builderForValue);
                return this;
            }

            public Builder addPackages(PackageProto value) {
                copyOnWrite();
                ((PerUserProto) this.instance).addPackages(value);
                return this;
            }

            public Builder addPackages(int index, PackageProto value) {
                copyOnWrite();
                ((PerUserProto) this.instance).addPackages(index, value);
                return this;
            }

            public Builder addPackages(PackageProto.Builder builderForValue) {
                copyOnWrite();
                ((PerUserProto) this.instance).addPackages(builderForValue);
                return this;
            }

            public Builder addPackages(int index, PackageProto.Builder builderForValue) {
                copyOnWrite();
                ((PerUserProto) this.instance).addPackages(index, builderForValue);
                return this;
            }

            public Builder addAllPackages(Iterable<? extends PackageProto> values) {
                copyOnWrite();
                ((PerUserProto) this.instance).addAllPackages(values);
                return this;
            }

            public Builder clearPackages() {
                copyOnWrite();
                ((PerUserProto) this.instance).clearPackages();
                return this;
            }

            public Builder removePackages(int index) {
                copyOnWrite();
                ((PerUserProto) this.instance).removePackages(index);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new PerUserProto();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "userId_", "packages_", PackageProto.class};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u0004\u0000\u0002\u001b", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PerUserProto> parser = PARSER;
                    if (parser == null) {
                        synchronized (PerUserProto.class) {
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
            PerUserProto defaultInstance = new PerUserProto();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(PerUserProto.class, defaultInstance);
        }

        public static PerUserProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PerUserProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class TeamFoodSettingsProto extends GeneratedMessageLite<TeamFoodSettingsProto, Builder> implements TeamFoodSettingsProtoOrBuilder {
        public static final int CHECK_FREQUENCY_MILLIS_FIELD_NUMBER = 3;
        private static final TeamFoodSettingsProto DEFAULT_INSTANCE;
        public static final int ENABLED_FOR_PRE_R_APPS_FIELD_NUMBER = 1;
        private static volatile Parser<TeamFoodSettingsProto> PARSER = null;
        public static final int UNUSED_THRESHOLD_MILLIS_FIELD_NUMBER = 2;
        private int bitField0_;
        private long checkFrequencyMillis_;
        private boolean enabledForPreRApps_;
        private long unusedThresholdMillis_;

        private TeamFoodSettingsProto() {
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.TeamFoodSettingsProtoOrBuilder
        public boolean hasEnabledForPreRApps() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.TeamFoodSettingsProtoOrBuilder
        public boolean getEnabledForPreRApps() {
            return this.enabledForPreRApps_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEnabledForPreRApps(boolean value) {
            this.bitField0_ |= 1;
            this.enabledForPreRApps_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEnabledForPreRApps() {
            this.bitField0_ &= -2;
            this.enabledForPreRApps_ = false;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.TeamFoodSettingsProtoOrBuilder
        public boolean hasUnusedThresholdMillis() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.TeamFoodSettingsProtoOrBuilder
        public long getUnusedThresholdMillis() {
            return this.unusedThresholdMillis_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnusedThresholdMillis(long value) {
            this.bitField0_ |= 2;
            this.unusedThresholdMillis_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUnusedThresholdMillis() {
            this.bitField0_ &= -3;
            this.unusedThresholdMillis_ = 0L;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.TeamFoodSettingsProtoOrBuilder
        public boolean hasCheckFrequencyMillis() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.TeamFoodSettingsProtoOrBuilder
        public long getCheckFrequencyMillis() {
            return this.checkFrequencyMillis_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCheckFrequencyMillis(long value) {
            this.bitField0_ |= 4;
            this.checkFrequencyMillis_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCheckFrequencyMillis() {
            this.bitField0_ &= -5;
            this.checkFrequencyMillis_ = 0L;
        }

        public static TeamFoodSettingsProto parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (TeamFoodSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TeamFoodSettingsProto parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TeamFoodSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TeamFoodSettingsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (TeamFoodSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TeamFoodSettingsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TeamFoodSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TeamFoodSettingsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (TeamFoodSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static TeamFoodSettingsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (TeamFoodSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static TeamFoodSettingsProto parseFrom(InputStream input) throws IOException {
            return (TeamFoodSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TeamFoodSettingsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TeamFoodSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TeamFoodSettingsProto parseDelimitedFrom(InputStream input) throws IOException {
            return (TeamFoodSettingsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static TeamFoodSettingsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TeamFoodSettingsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static TeamFoodSettingsProto parseFrom(CodedInputStream input) throws IOException {
            return (TeamFoodSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static TeamFoodSettingsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (TeamFoodSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(TeamFoodSettingsProto prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<TeamFoodSettingsProto, Builder> implements TeamFoodSettingsProtoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(TeamFoodSettingsProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.TeamFoodSettingsProtoOrBuilder
            public boolean hasEnabledForPreRApps() {
                return ((TeamFoodSettingsProto) this.instance).hasEnabledForPreRApps();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.TeamFoodSettingsProtoOrBuilder
            public boolean getEnabledForPreRApps() {
                return ((TeamFoodSettingsProto) this.instance).getEnabledForPreRApps();
            }

            public Builder setEnabledForPreRApps(boolean value) {
                copyOnWrite();
                ((TeamFoodSettingsProto) this.instance).setEnabledForPreRApps(value);
                return this;
            }

            public Builder clearEnabledForPreRApps() {
                copyOnWrite();
                ((TeamFoodSettingsProto) this.instance).clearEnabledForPreRApps();
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.TeamFoodSettingsProtoOrBuilder
            public boolean hasUnusedThresholdMillis() {
                return ((TeamFoodSettingsProto) this.instance).hasUnusedThresholdMillis();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.TeamFoodSettingsProtoOrBuilder
            public long getUnusedThresholdMillis() {
                return ((TeamFoodSettingsProto) this.instance).getUnusedThresholdMillis();
            }

            public Builder setUnusedThresholdMillis(long value) {
                copyOnWrite();
                ((TeamFoodSettingsProto) this.instance).setUnusedThresholdMillis(value);
                return this;
            }

            public Builder clearUnusedThresholdMillis() {
                copyOnWrite();
                ((TeamFoodSettingsProto) this.instance).clearUnusedThresholdMillis();
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.TeamFoodSettingsProtoOrBuilder
            public boolean hasCheckFrequencyMillis() {
                return ((TeamFoodSettingsProto) this.instance).hasCheckFrequencyMillis();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.TeamFoodSettingsProtoOrBuilder
            public long getCheckFrequencyMillis() {
                return ((TeamFoodSettingsProto) this.instance).getCheckFrequencyMillis();
            }

            public Builder setCheckFrequencyMillis(long value) {
                copyOnWrite();
                ((TeamFoodSettingsProto) this.instance).setCheckFrequencyMillis(value);
                return this;
            }

            public Builder clearCheckFrequencyMillis() {
                copyOnWrite();
                ((TeamFoodSettingsProto) this.instance).clearCheckFrequencyMillis();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new TeamFoodSettingsProto();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "enabledForPreRApps_", "unusedThresholdMillis_", "checkFrequencyMillis_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0007\u0000\u0002\u0002\u0001\u0003\u0002\u0002", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<TeamFoodSettingsProto> parser = PARSER;
                    if (parser == null) {
                        synchronized (TeamFoodSettingsProto.class) {
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
            TeamFoodSettingsProto defaultInstance = new TeamFoodSettingsProto();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(TeamFoodSettingsProto.class, defaultInstance);
        }

        public static TeamFoodSettingsProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TeamFoodSettingsProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class AutoRevokePermissionsDumpProto extends GeneratedMessageLite<AutoRevokePermissionsDumpProto, Builder> implements AutoRevokePermissionsDumpProtoOrBuilder {
        private static final AutoRevokePermissionsDumpProto DEFAULT_INSTANCE;
        private static volatile Parser<AutoRevokePermissionsDumpProto> PARSER = null;
        public static final int TEAMFOOD_SETTINGS_FIELD_NUMBER = 2;
        public static final int USERS_FIELD_NUMBER = 1;
        private int bitField0_;
        private TeamFoodSettingsProto teamfoodSettings_;
        private Internal.ProtobufList<PerUserProto> users_ = emptyProtobufList();

        private AutoRevokePermissionsDumpProto() {
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokePermissionsDumpProtoOrBuilder
        public List<PerUserProto> getUsersList() {
            return this.users_;
        }

        public List<? extends PerUserProtoOrBuilder> getUsersOrBuilderList() {
            return this.users_;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokePermissionsDumpProtoOrBuilder
        public int getUsersCount() {
            return this.users_.size();
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokePermissionsDumpProtoOrBuilder
        public PerUserProto getUsers(int index) {
            return this.users_.get(index);
        }

        public PerUserProtoOrBuilder getUsersOrBuilder(int index) {
            return this.users_.get(index);
        }

        private void ensureUsersIsMutable() {
            if (!this.users_.isModifiable()) {
                this.users_ = GeneratedMessageLite.mutableCopy(this.users_);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUsers(int index, PerUserProto value) {
            if (value == null) {
                throw null;
            }
            ensureUsersIsMutable();
            this.users_.set(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUsers(int index, PerUserProto.Builder builderForValue) {
            ensureUsersIsMutable();
            this.users_.set(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addUsers(PerUserProto value) {
            if (value == null) {
                throw null;
            }
            ensureUsersIsMutable();
            this.users_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addUsers(int index, PerUserProto value) {
            if (value == null) {
                throw null;
            }
            ensureUsersIsMutable();
            this.users_.add(index, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addUsers(PerUserProto.Builder builderForValue) {
            ensureUsersIsMutable();
            this.users_.add(builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addUsers(int index, PerUserProto.Builder builderForValue) {
            ensureUsersIsMutable();
            this.users_.add(index, builderForValue.build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllUsers(Iterable<? extends PerUserProto> values) {
            ensureUsersIsMutable();
            AbstractMessageLite.addAll((Iterable) values, (List) this.users_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUsers() {
            this.users_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeUsers(int index) {
            ensureUsersIsMutable();
            this.users_.remove(index);
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokePermissionsDumpProtoOrBuilder
        public boolean hasTeamfoodSettings() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokePermissionsDumpProtoOrBuilder
        public TeamFoodSettingsProto getTeamfoodSettings() {
            TeamFoodSettingsProto teamFoodSettingsProto = this.teamfoodSettings_;
            return teamFoodSettingsProto == null ? TeamFoodSettingsProto.getDefaultInstance() : teamFoodSettingsProto;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTeamfoodSettings(TeamFoodSettingsProto value) {
            if (value == null) {
                throw null;
            }
            this.teamfoodSettings_ = value;
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTeamfoodSettings(TeamFoodSettingsProto.Builder builderForValue) {
            this.teamfoodSettings_ = builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeTeamfoodSettings(TeamFoodSettingsProto value) {
            if (value == null) {
                throw null;
            }
            TeamFoodSettingsProto teamFoodSettingsProto = this.teamfoodSettings_;
            if (teamFoodSettingsProto != null && teamFoodSettingsProto != TeamFoodSettingsProto.getDefaultInstance()) {
                this.teamfoodSettings_ = TeamFoodSettingsProto.newBuilder(this.teamfoodSettings_).mergeFrom(value).buildPartial();
            } else {
                this.teamfoodSettings_ = value;
            }
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTeamfoodSettings() {
            this.teamfoodSettings_ = null;
            this.bitField0_ &= -2;
        }

        public static AutoRevokePermissionsDumpProto parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (AutoRevokePermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AutoRevokePermissionsDumpProto parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AutoRevokePermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AutoRevokePermissionsDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (AutoRevokePermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AutoRevokePermissionsDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AutoRevokePermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AutoRevokePermissionsDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (AutoRevokePermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static AutoRevokePermissionsDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (AutoRevokePermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static AutoRevokePermissionsDumpProto parseFrom(InputStream input) throws IOException {
            return (AutoRevokePermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AutoRevokePermissionsDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AutoRevokePermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AutoRevokePermissionsDumpProto parseDelimitedFrom(InputStream input) throws IOException {
            return (AutoRevokePermissionsDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static AutoRevokePermissionsDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AutoRevokePermissionsDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static AutoRevokePermissionsDumpProto parseFrom(CodedInputStream input) throws IOException {
            return (AutoRevokePermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static AutoRevokePermissionsDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (AutoRevokePermissionsDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(AutoRevokePermissionsDumpProto prototype) {
            return DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AutoRevokePermissionsDumpProto, Builder> implements AutoRevokePermissionsDumpProtoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 x0) {
                this();
            }

            private Builder() {
                super(AutoRevokePermissionsDumpProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokePermissionsDumpProtoOrBuilder
            public List<PerUserProto> getUsersList() {
                return Collections.unmodifiableList(((AutoRevokePermissionsDumpProto) this.instance).getUsersList());
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokePermissionsDumpProtoOrBuilder
            public int getUsersCount() {
                return ((AutoRevokePermissionsDumpProto) this.instance).getUsersCount();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokePermissionsDumpProtoOrBuilder
            public PerUserProto getUsers(int index) {
                return ((AutoRevokePermissionsDumpProto) this.instance).getUsers(index);
            }

            public Builder setUsers(int index, PerUserProto value) {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).setUsers(index, value);
                return this;
            }

            public Builder setUsers(int index, PerUserProto.Builder builderForValue) {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).setUsers(index, builderForValue);
                return this;
            }

            public Builder addUsers(PerUserProto value) {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).addUsers(value);
                return this;
            }

            public Builder addUsers(int index, PerUserProto value) {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).addUsers(index, value);
                return this;
            }

            public Builder addUsers(PerUserProto.Builder builderForValue) {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).addUsers(builderForValue);
                return this;
            }

            public Builder addUsers(int index, PerUserProto.Builder builderForValue) {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).addUsers(index, builderForValue);
                return this;
            }

            public Builder addAllUsers(Iterable<? extends PerUserProto> values) {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).addAllUsers(values);
                return this;
            }

            public Builder clearUsers() {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).clearUsers();
                return this;
            }

            public Builder removeUsers(int index) {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).removeUsers(index);
                return this;
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokePermissionsDumpProtoOrBuilder
            public boolean hasTeamfoodSettings() {
                return ((AutoRevokePermissionsDumpProto) this.instance).hasTeamfoodSettings();
            }

            @Override // com.android.permissioncontroller.permission.service.AutoRevokePermissionsProto.AutoRevokePermissionsDumpProtoOrBuilder
            public TeamFoodSettingsProto getTeamfoodSettings() {
                return ((AutoRevokePermissionsDumpProto) this.instance).getTeamfoodSettings();
            }

            public Builder setTeamfoodSettings(TeamFoodSettingsProto value) {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).setTeamfoodSettings(value);
                return this;
            }

            public Builder setTeamfoodSettings(TeamFoodSettingsProto.Builder builderForValue) {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).setTeamfoodSettings(builderForValue);
                return this;
            }

            public Builder mergeTeamfoodSettings(TeamFoodSettingsProto value) {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).mergeTeamfoodSettings(value);
                return this;
            }

            public Builder clearTeamfoodSettings() {
                copyOnWrite();
                ((AutoRevokePermissionsDumpProto) this.instance).clearTeamfoodSettings();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new AutoRevokePermissionsDumpProto();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    Object[] objects = {"bitField0_", "users_", PerUserProto.class, "teamfoodSettings_"};
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\t\u0000", objects);
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<AutoRevokePermissionsDumpProto> parser = PARSER;
                    if (parser == null) {
                        synchronized (AutoRevokePermissionsDumpProto.class) {
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
            AutoRevokePermissionsDumpProto defaultInstance = new AutoRevokePermissionsDumpProto();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(AutoRevokePermissionsDumpProto.class, defaultInstance);
        }

        public static AutoRevokePermissionsDumpProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AutoRevokePermissionsDumpProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
