package com.google.protobuf;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.TextFormat;
import com.google.protobuf.WireFormat;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class Descriptors {
    private static final Logger logger = Logger.getLogger(Descriptors.class.getName());
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Descriptor[] EMPTY_DESCRIPTORS = new Descriptor[0];
    private static final FieldDescriptor[] EMPTY_FIELD_DESCRIPTORS = new FieldDescriptor[0];
    private static final EnumDescriptor[] EMPTY_ENUM_DESCRIPTORS = new EnumDescriptor[0];
    private static final ServiceDescriptor[] EMPTY_SERVICE_DESCRIPTORS = new ServiceDescriptor[0];
    private static final OneofDescriptor[] EMPTY_ONEOF_DESCRIPTORS = new OneofDescriptor[0];

    private interface NumberGetter<T> {
        int getNumber(T t);
    }

    public static final class FileDescriptor extends GenericDescriptor {
        private final FileDescriptor[] dependencies;
        private final EnumDescriptor[] enumTypes;
        private final FieldDescriptor[] extensions;
        private final Descriptor[] messageTypes;
        private final DescriptorPool pool;
        private DescriptorProtos.FileDescriptorProto proto;
        private final FileDescriptor[] publicDependencies;
        private final ServiceDescriptor[] services;

        @Deprecated
        public interface InternalDescriptorAssigner {
            ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor);
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.FileDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.proto.getName();
        }

        public String getPackage() {
            return this.proto.getPackage();
        }

        public DescriptorProtos.FileOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<Descriptor> getMessageTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
        }

        public List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public List<ServiceDescriptor> getServices() {
            return Collections.unmodifiableList(Arrays.asList(this.services));
        }

        public List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public List<FileDescriptor> getDependencies() {
            return Collections.unmodifiableList(Arrays.asList(this.dependencies));
        }

        public List<FileDescriptor> getPublicDependencies() {
            return Collections.unmodifiableList(Arrays.asList(this.publicDependencies));
        }

        public enum Syntax {
            UNKNOWN("unknown"),
            PROTO2("proto2"),
            PROTO3("proto3");

            private final String name;

            Syntax(String name) {
                this.name = name;
            }
        }

        public Syntax getSyntax() {
            if (Syntax.PROTO3.name.equals(this.proto.getSyntax())) {
                return Syntax.PROTO3;
            }
            return Syntax.PROTO2;
        }

        public Descriptor findMessageTypeByName(String name) {
            if (name.indexOf(46) != -1) {
                return null;
            }
            String packageName = getPackage();
            if (!packageName.isEmpty()) {
                name = packageName + '.' + name;
            }
            GenericDescriptor result = this.pool.findSymbol(name);
            if ((result instanceof Descriptor) && result.getFile() == this) {
                return (Descriptor) result;
            }
            return null;
        }

        public EnumDescriptor findEnumTypeByName(String name) {
            if (name.indexOf(46) != -1) {
                return null;
            }
            String packageName = getPackage();
            if (!packageName.isEmpty()) {
                name = packageName + '.' + name;
            }
            GenericDescriptor result = this.pool.findSymbol(name);
            if ((result instanceof EnumDescriptor) && result.getFile() == this) {
                return (EnumDescriptor) result;
            }
            return null;
        }

        public ServiceDescriptor findServiceByName(String name) {
            if (name.indexOf(46) != -1) {
                return null;
            }
            String packageName = getPackage();
            if (!packageName.isEmpty()) {
                name = packageName + '.' + name;
            }
            GenericDescriptor result = this.pool.findSymbol(name);
            if ((result instanceof ServiceDescriptor) && result.getFile() == this) {
                return (ServiceDescriptor) result;
            }
            return null;
        }

        public FieldDescriptor findExtensionByName(String name) {
            if (name.indexOf(46) != -1) {
                return null;
            }
            String packageName = getPackage();
            if (!packageName.isEmpty()) {
                name = packageName + '.' + name;
            }
            GenericDescriptor result = this.pool.findSymbol(name);
            if ((result instanceof FieldDescriptor) && result.getFile() == this) {
                return (FieldDescriptor) result;
            }
            return null;
        }

        public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies) throws DescriptorValidationException {
            return buildFrom(proto, dependencies, false);
        }

        public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies, boolean allowUnknownDependencies) throws DescriptorValidationException {
            DescriptorPool pool = new DescriptorPool(dependencies, allowUnknownDependencies);
            FileDescriptor result = new FileDescriptor(proto, dependencies, pool, allowUnknownDependencies);
            result.crossLink();
            return result;
        }

        private static byte[] latin1Cat(String[] strings) {
            if (strings.length == 1) {
                return strings[0].getBytes(Internal.ISO_8859_1);
            }
            StringBuilder descriptorData = new StringBuilder();
            for (String part : strings) {
                descriptorData.append(part);
            }
            return descriptorData.toString().getBytes(Internal.ISO_8859_1);
        }

        private static FileDescriptor[] findDescriptors(Class<?> descriptorOuterClass, String[] dependencyClassNames, String[] dependencyFileNames) {
            List<FileDescriptor> descriptors = new ArrayList<>();
            for (int i = 0; i < dependencyClassNames.length; i++) {
                try {
                    Class<?> clazz = descriptorOuterClass.getClassLoader().loadClass(dependencyClassNames[i]);
                    descriptors.add((FileDescriptor) clazz.getField("descriptor").get(null));
                } catch (Exception e) {
                    Descriptors.logger.warning("Descriptors for \"" + dependencyFileNames[i] + "\" can not be found.");
                }
            }
            return (FileDescriptor[]) descriptors.toArray(new FileDescriptor[0]);
        }

        @Deprecated
        public static void internalBuildGeneratedFileFrom(String[] descriptorDataParts, FileDescriptor[] dependencies, InternalDescriptorAssigner descriptorAssigner) {
            byte[] descriptorBytes = latin1Cat(descriptorDataParts);
            try {
                DescriptorProtos.FileDescriptorProto proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes);
                try {
                    FileDescriptor result = buildFrom(proto, dependencies, true);
                    ExtensionRegistry registry = descriptorAssigner.assignDescriptors(result);
                    if (registry != null) {
                        try {
                            result.setProto(DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes, registry));
                        } catch (InvalidProtocolBufferException e) {
                            throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
                        }
                    }
                } catch (DescriptorValidationException e2) {
                    throw new IllegalArgumentException("Invalid embedded descriptor for \"" + proto.getName() + "\".", e2);
                }
            } catch (InvalidProtocolBufferException e3) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e3);
            }
        }

        public static FileDescriptor internalBuildGeneratedFileFrom(String[] descriptorDataParts, FileDescriptor[] dependencies) {
            byte[] descriptorBytes = latin1Cat(descriptorDataParts);
            try {
                DescriptorProtos.FileDescriptorProto proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes);
                try {
                    return buildFrom(proto, dependencies, true);
                } catch (DescriptorValidationException e) {
                    throw new IllegalArgumentException("Invalid embedded descriptor for \"" + proto.getName() + "\".", e);
                }
            } catch (InvalidProtocolBufferException e2) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e2);
            }
        }

        @Deprecated
        public static void internalBuildGeneratedFileFrom(String[] descriptorDataParts, Class<?> descriptorOuterClass, String[] dependencyClassNames, String[] dependencyFileNames, InternalDescriptorAssigner descriptorAssigner) {
            FileDescriptor[] dependencies = findDescriptors(descriptorOuterClass, dependencyClassNames, dependencyFileNames);
            internalBuildGeneratedFileFrom(descriptorDataParts, dependencies, descriptorAssigner);
        }

        public static FileDescriptor internalBuildGeneratedFileFrom(String[] descriptorDataParts, Class<?> descriptorOuterClass, String[] dependencyClassNames, String[] dependencyFileNames) {
            FileDescriptor[] dependencies = findDescriptors(descriptorOuterClass, dependencyClassNames, dependencyFileNames);
            return internalBuildGeneratedFileFrom(descriptorDataParts, dependencies);
        }

        public static void internalUpdateFileDescriptor(FileDescriptor descriptor, ExtensionRegistry registry) {
            ByteString bytes = descriptor.proto.toByteString();
            try {
                DescriptorProtos.FileDescriptorProto proto = DescriptorProtos.FileDescriptorProto.parseFrom(bytes, registry);
                descriptor.setProto(proto);
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
            }
        }

        /* JADX WARN: Illegal instructions before constructor call */
        private FileDescriptor(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies, DescriptorPool pool, boolean allowUnknownDependencies) throws DescriptorValidationException {
            Descriptor[] descriptorArr;
            EnumDescriptor[] enumDescriptorArr;
            ServiceDescriptor[] serviceDescriptorArr;
            FieldDescriptor[] fieldDescriptorArr;
            AnonymousClass1 anonymousClass1 = null;
            super(anonymousClass1);
            this.pool = pool;
            this.proto = proto;
            this.dependencies = (FileDescriptor[]) dependencies.clone();
            HashMap<String, FileDescriptor> nameToFileMap = new HashMap<>();
            for (FileDescriptor file : dependencies) {
                nameToFileMap.put(file.getName(), file);
            }
            List<FileDescriptor> publicDependencies = new ArrayList<>();
            for (int i = 0; i < proto.getPublicDependencyCount(); i++) {
                int index = proto.getPublicDependency(i);
                if (index < 0 || index >= proto.getDependencyCount()) {
                    throw new DescriptorValidationException(this, "Invalid public dependency index.", anonymousClass1);
                }
                String name = proto.getDependency(index);
                FileDescriptor file2 = nameToFileMap.get(name);
                if (file2 == null) {
                    if (!allowUnknownDependencies) {
                        throw new DescriptorValidationException(this, "Invalid public dependency: " + name, anonymousClass1);
                    }
                } else {
                    publicDependencies.add(file2);
                }
            }
            int i2 = publicDependencies.size();
            FileDescriptor[] fileDescriptorArr = new FileDescriptor[i2];
            this.publicDependencies = fileDescriptorArr;
            publicDependencies.toArray(fileDescriptorArr);
            pool.addPackage(getPackage(), this);
            if (proto.getMessageTypeCount() <= 0) {
                descriptorArr = Descriptors.EMPTY_DESCRIPTORS;
            } else {
                descriptorArr = new Descriptor[proto.getMessageTypeCount()];
            }
            this.messageTypes = descriptorArr;
            for (int i3 = 0; i3 < proto.getMessageTypeCount(); i3++) {
                this.messageTypes[i3] = new Descriptor(proto.getMessageType(i3), this, null, i3, null);
            }
            if (proto.getEnumTypeCount() <= 0) {
                enumDescriptorArr = Descriptors.EMPTY_ENUM_DESCRIPTORS;
            } else {
                enumDescriptorArr = new EnumDescriptor[proto.getEnumTypeCount()];
            }
            this.enumTypes = enumDescriptorArr;
            for (int i4 = 0; i4 < proto.getEnumTypeCount(); i4++) {
                this.enumTypes[i4] = new EnumDescriptor(proto.getEnumType(i4), this, null, i4, null);
            }
            if (proto.getServiceCount() <= 0) {
                serviceDescriptorArr = Descriptors.EMPTY_SERVICE_DESCRIPTORS;
            } else {
                serviceDescriptorArr = new ServiceDescriptor[proto.getServiceCount()];
            }
            this.services = serviceDescriptorArr;
            for (int i5 = 0; i5 < proto.getServiceCount(); i5++) {
                this.services[i5] = new ServiceDescriptor(proto.getService(i5), this, i5, anonymousClass1);
            }
            if (proto.getExtensionCount() <= 0) {
                fieldDescriptorArr = Descriptors.EMPTY_FIELD_DESCRIPTORS;
            } else {
                fieldDescriptorArr = new FieldDescriptor[proto.getExtensionCount()];
            }
            this.extensions = fieldDescriptorArr;
            for (int i6 = 0; i6 < proto.getExtensionCount(); i6++) {
                this.extensions[i6] = new FieldDescriptor(proto.getExtension(i6), this, null, i6, true, null);
            }
        }

        FileDescriptor(String packageName, Descriptor message) throws DescriptorValidationException {
            super(null);
            this.pool = new DescriptorPool(new FileDescriptor[0], true);
            this.proto = DescriptorProtos.FileDescriptorProto.newBuilder().setName(message.getFullName() + ".placeholder.proto").setPackage(packageName).addMessageType(message.toProto()).build();
            this.dependencies = new FileDescriptor[0];
            this.publicDependencies = new FileDescriptor[0];
            this.messageTypes = new Descriptor[]{message};
            this.enumTypes = Descriptors.EMPTY_ENUM_DESCRIPTORS;
            this.services = Descriptors.EMPTY_SERVICE_DESCRIPTORS;
            this.extensions = Descriptors.EMPTY_FIELD_DESCRIPTORS;
            this.pool.addPackage(packageName, this);
            this.pool.addSymbol(message);
        }

        private void crossLink() throws DescriptorValidationException {
            for (Descriptor messageType : this.messageTypes) {
                messageType.crossLink();
            }
            for (ServiceDescriptor service : this.services) {
                service.crossLink();
            }
            for (FieldDescriptor extension : this.extensions) {
                extension.crossLink();
            }
        }

        private void setProto(DescriptorProtos.FileDescriptorProto proto) {
            this.proto = proto;
            int i = 0;
            while (true) {
                Descriptor[] descriptorArr = this.messageTypes;
                if (i >= descriptorArr.length) {
                    break;
                }
                descriptorArr[i].setProto(proto.getMessageType(i));
                i++;
            }
            int i2 = 0;
            while (true) {
                EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                if (i2 >= enumDescriptorArr.length) {
                    break;
                }
                enumDescriptorArr[i2].setProto(proto.getEnumType(i2));
                i2++;
            }
            int i3 = 0;
            while (true) {
                ServiceDescriptor[] serviceDescriptorArr = this.services;
                if (i3 >= serviceDescriptorArr.length) {
                    break;
                }
                serviceDescriptorArr[i3].setProto(proto.getService(i3));
                i3++;
            }
            int i4 = 0;
            while (true) {
                FieldDescriptor[] fieldDescriptorArr = this.extensions;
                if (i4 >= fieldDescriptorArr.length) {
                    return;
                }
                fieldDescriptorArr[i4].setProto(proto.getExtension(i4));
                i4++;
            }
        }

        boolean supportsUnknownEnumValue() {
            return getSyntax() == Syntax.PROTO3;
        }
    }

    public static final class Descriptor extends GenericDescriptor {
        private final Descriptor containingType;
        private final EnumDescriptor[] enumTypes;
        private final int[] extensionRangeLowerBounds;
        private final int[] extensionRangeUpperBounds;
        private final FieldDescriptor[] extensions;
        private final FieldDescriptor[] fields;
        private final FieldDescriptor[] fieldsSortedByNumber;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private final Descriptor[] nestedTypes;
        private final OneofDescriptor[] oneofs;
        private DescriptorProtos.DescriptorProto proto;
        private final int realOneofCount;

        /* synthetic */ Descriptor(DescriptorProtos.DescriptorProto x0, FileDescriptor x1, Descriptor x2, int x3, AnonymousClass1 x4) throws DescriptorValidationException {
            this(x0, x1, x2, x3);
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.DescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public DescriptorProtos.MessageOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<FieldDescriptor> getFields() {
            return Collections.unmodifiableList(Arrays.asList(this.fields));
        }

        public List<OneofDescriptor> getOneofs() {
            return Collections.unmodifiableList(Arrays.asList(this.oneofs));
        }

        public List<OneofDescriptor> getRealOneofs() {
            return Collections.unmodifiableList(Arrays.asList(this.oneofs).subList(0, this.realOneofCount));
        }

        public List<FieldDescriptor> getExtensions() {
            return Collections.unmodifiableList(Arrays.asList(this.extensions));
        }

        public List<Descriptor> getNestedTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
        }

        public List<EnumDescriptor> getEnumTypes() {
            return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
        }

        public boolean isExtensionNumber(int number) {
            int index = Arrays.binarySearch(this.extensionRangeLowerBounds, number);
            if (index < 0) {
                index = (~index) - 1;
            }
            return index >= 0 && number < this.extensionRangeUpperBounds[index];
        }

        public boolean isReservedNumber(int number) {
            for (DescriptorProtos.DescriptorProto.ReservedRange range : this.proto.getReservedRangeList()) {
                if (range.getStart() <= number && number < range.getEnd()) {
                    return true;
                }
            }
            return false;
        }

        public boolean isReservedName(String name) {
            Internal.checkNotNull(name);
            for (String reservedName : this.proto.getReservedNameList()) {
                if (reservedName.equals(name)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isExtendable() {
            return !this.proto.getExtensionRangeList().isEmpty();
        }

        public FieldDescriptor findFieldByName(String name) {
            GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
            if (result instanceof FieldDescriptor) {
                return (FieldDescriptor) result;
            }
            return null;
        }

        public FieldDescriptor findFieldByNumber(int number) {
            FieldDescriptor[] fieldDescriptorArr = this.fieldsSortedByNumber;
            return (FieldDescriptor) Descriptors.binarySearch(fieldDescriptorArr, fieldDescriptorArr.length, FieldDescriptor.NUMBER_GETTER, number);
        }

        public Descriptor findNestedTypeByName(String name) {
            GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
            if (result instanceof Descriptor) {
                return (Descriptor) result;
            }
            return null;
        }

        public EnumDescriptor findEnumTypeByName(String name) {
            GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
            if (result instanceof EnumDescriptor) {
                return (EnumDescriptor) result;
            }
            return null;
        }

        Descriptor(String fullname) throws DescriptorValidationException {
            super(null);
            String name = fullname;
            String packageName = "";
            int pos = fullname.lastIndexOf(46);
            if (pos != -1) {
                name = fullname.substring(pos + 1);
                packageName = fullname.substring(0, pos);
            }
            this.index = 0;
            this.proto = DescriptorProtos.DescriptorProto.newBuilder().setName(name).addExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange.newBuilder().setStart(1).setEnd(536870912).build()).build();
            this.fullName = fullname;
            this.containingType = null;
            this.nestedTypes = Descriptors.EMPTY_DESCRIPTORS;
            this.enumTypes = Descriptors.EMPTY_ENUM_DESCRIPTORS;
            this.fields = Descriptors.EMPTY_FIELD_DESCRIPTORS;
            this.fieldsSortedByNumber = Descriptors.EMPTY_FIELD_DESCRIPTORS;
            this.extensions = Descriptors.EMPTY_FIELD_DESCRIPTORS;
            this.oneofs = Descriptors.EMPTY_ONEOF_DESCRIPTORS;
            this.realOneofCount = 0;
            this.file = new FileDescriptor(packageName, this);
            this.extensionRangeLowerBounds = new int[]{1};
            this.extensionRangeUpperBounds = new int[]{536870912};
        }

        /* JADX WARN: Illegal instructions before constructor call */
        private Descriptor(DescriptorProtos.DescriptorProto proto, FileDescriptor file, Descriptor parent, int index) throws DescriptorValidationException {
            OneofDescriptor[] oneofDescriptorArr;
            Descriptor[] descriptorArr;
            EnumDescriptor[] enumDescriptorArr;
            FieldDescriptor[] fieldDescriptorArr;
            FieldDescriptor[] fieldDescriptorArr2;
            int i;
            AnonymousClass1 anonymousClass1 = null;
            super(anonymousClass1);
            this.index = index;
            this.proto = proto;
            this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
            this.file = file;
            this.containingType = parent;
            if (proto.getOneofDeclCount() <= 0) {
                oneofDescriptorArr = Descriptors.EMPTY_ONEOF_DESCRIPTORS;
            } else {
                oneofDescriptorArr = new OneofDescriptor[proto.getOneofDeclCount()];
            }
            this.oneofs = oneofDescriptorArr;
            for (int i2 = 0; i2 < proto.getOneofDeclCount(); i2++) {
                this.oneofs[i2] = new OneofDescriptor(proto.getOneofDecl(i2), file, this, i2, null);
            }
            if (proto.getNestedTypeCount() <= 0) {
                descriptorArr = Descriptors.EMPTY_DESCRIPTORS;
            } else {
                descriptorArr = new Descriptor[proto.getNestedTypeCount()];
            }
            this.nestedTypes = descriptorArr;
            for (int i3 = 0; i3 < proto.getNestedTypeCount(); i3++) {
                this.nestedTypes[i3] = new Descriptor(proto.getNestedType(i3), file, this, i3);
            }
            if (proto.getEnumTypeCount() <= 0) {
                enumDescriptorArr = Descriptors.EMPTY_ENUM_DESCRIPTORS;
            } else {
                enumDescriptorArr = new EnumDescriptor[proto.getEnumTypeCount()];
            }
            this.enumTypes = enumDescriptorArr;
            for (int i4 = 0; i4 < proto.getEnumTypeCount(); i4++) {
                this.enumTypes[i4] = new EnumDescriptor(proto.getEnumType(i4), file, this, i4, null);
            }
            if (proto.getFieldCount() <= 0) {
                fieldDescriptorArr = Descriptors.EMPTY_FIELD_DESCRIPTORS;
            } else {
                fieldDescriptorArr = new FieldDescriptor[proto.getFieldCount()];
            }
            this.fields = fieldDescriptorArr;
            for (int i5 = 0; i5 < proto.getFieldCount(); i5++) {
                this.fields[i5] = new FieldDescriptor(proto.getField(i5), file, this, i5, false, null);
            }
            this.fieldsSortedByNumber = proto.getFieldCount() > 0 ? (FieldDescriptor[]) this.fields.clone() : Descriptors.EMPTY_FIELD_DESCRIPTORS;
            if (proto.getExtensionCount() <= 0) {
                fieldDescriptorArr2 = Descriptors.EMPTY_FIELD_DESCRIPTORS;
            } else {
                fieldDescriptorArr2 = new FieldDescriptor[proto.getExtensionCount()];
            }
            this.extensions = fieldDescriptorArr2;
            for (int i6 = 0; i6 < proto.getExtensionCount(); i6++) {
                this.extensions[i6] = new FieldDescriptor(proto.getExtension(i6), file, this, i6, true, null);
            }
            int i7 = 0;
            while (true) {
                if (i7 >= proto.getOneofDeclCount()) {
                    break;
                }
                OneofDescriptor[] oneofDescriptorArr2 = this.oneofs;
                oneofDescriptorArr2[i7].fields = new FieldDescriptor[oneofDescriptorArr2[i7].getFieldCount()];
                this.oneofs[i7].fieldCount = 0;
                i7++;
            }
            for (int i8 = 0; i8 < proto.getFieldCount(); i8++) {
                OneofDescriptor oneofDescriptor = this.fields[i8].getContainingOneof();
                if (oneofDescriptor != null) {
                    oneofDescriptor.fields[OneofDescriptor.access$2608(oneofDescriptor)] = this.fields[i8];
                }
            }
            int syntheticOneofCount = 0;
            for (OneofDescriptor oneof : this.oneofs) {
                if (oneof.isSynthetic()) {
                    syntheticOneofCount++;
                } else if (syntheticOneofCount > 0) {
                    throw new DescriptorValidationException(this, "Synthetic oneofs must come last.", anonymousClass1);
                }
            }
            this.realOneofCount = this.oneofs.length - syntheticOneofCount;
            file.pool.addSymbol(this);
            if (proto.getExtensionRangeCount() <= 0) {
                this.extensionRangeLowerBounds = Descriptors.EMPTY_INT_ARRAY;
                this.extensionRangeUpperBounds = Descriptors.EMPTY_INT_ARRAY;
                return;
            }
            this.extensionRangeLowerBounds = new int[proto.getExtensionRangeCount()];
            this.extensionRangeUpperBounds = new int[proto.getExtensionRangeCount()];
            int i9 = 0;
            for (DescriptorProtos.DescriptorProto.ExtensionRange range : proto.getExtensionRangeList()) {
                this.extensionRangeLowerBounds[i9] = range.getStart();
                this.extensionRangeUpperBounds[i9] = range.getEnd();
                i9++;
            }
            Arrays.sort(this.extensionRangeLowerBounds);
            Arrays.sort(this.extensionRangeUpperBounds);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            for (Descriptor nestedType : this.nestedTypes) {
                nestedType.crossLink();
            }
            for (FieldDescriptor field : this.fields) {
                field.crossLink();
            }
            Arrays.sort(this.fieldsSortedByNumber);
            validateNoDuplicateFieldNumbers();
            for (FieldDescriptor extension : this.extensions) {
                extension.crossLink();
            }
        }

        private void validateNoDuplicateFieldNumbers() throws DescriptorValidationException {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                FieldDescriptor[] fieldDescriptorArr = this.fieldsSortedByNumber;
                if (i2 < fieldDescriptorArr.length) {
                    FieldDescriptor old = fieldDescriptorArr[i];
                    FieldDescriptor field = fieldDescriptorArr[i + 1];
                    if (old.getNumber() != field.getNumber()) {
                        i++;
                    } else {
                        throw new DescriptorValidationException(field, "Field number " + field.getNumber() + " has already been used in \"" + field.getContainingType().getFullName() + "\" by field \"" + old.getName() + "\".", (AnonymousClass1) null);
                    }
                } else {
                    return;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.DescriptorProto proto) {
            this.proto = proto;
            int i = 0;
            while (true) {
                Descriptor[] descriptorArr = this.nestedTypes;
                if (i >= descriptorArr.length) {
                    break;
                }
                descriptorArr[i].setProto(proto.getNestedType(i));
                i++;
            }
            int i2 = 0;
            while (true) {
                OneofDescriptor[] oneofDescriptorArr = this.oneofs;
                if (i2 >= oneofDescriptorArr.length) {
                    break;
                }
                oneofDescriptorArr[i2].setProto(proto.getOneofDecl(i2));
                i2++;
            }
            int i3 = 0;
            while (true) {
                EnumDescriptor[] enumDescriptorArr = this.enumTypes;
                if (i3 >= enumDescriptorArr.length) {
                    break;
                }
                enumDescriptorArr[i3].setProto(proto.getEnumType(i3));
                i3++;
            }
            int i4 = 0;
            while (true) {
                FieldDescriptor[] fieldDescriptorArr = this.fields;
                if (i4 >= fieldDescriptorArr.length) {
                    break;
                }
                fieldDescriptorArr[i4].setProto(proto.getField(i4));
                i4++;
            }
            int i5 = 0;
            while (true) {
                FieldDescriptor[] fieldDescriptorArr2 = this.extensions;
                if (i5 >= fieldDescriptorArr2.length) {
                    return;
                }
                fieldDescriptorArr2[i5].setProto(proto.getExtension(i5));
                i5++;
            }
        }
    }

    public static final class FieldDescriptor extends GenericDescriptor implements Comparable<FieldDescriptor>, FieldSet.FieldDescriptorLite<FieldDescriptor> {
        private static final NumberGetter<FieldDescriptor> NUMBER_GETTER = new NumberGetter<FieldDescriptor>() { // from class: com.google.protobuf.Descriptors.FieldDescriptor.1
            @Override // com.google.protobuf.Descriptors.NumberGetter
            public int getNumber(FieldDescriptor fieldDescriptor) {
                return fieldDescriptor.getNumber();
            }
        };
        private static final WireFormat.FieldType[] table = WireFormat.FieldType.values();
        private OneofDescriptor containingOneof;
        private Descriptor containingType;
        private Object defaultValue;
        private EnumDescriptor enumType;
        private final Descriptor extensionScope;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private final boolean isProto3Optional;
        private String jsonName;
        private Descriptor messageType;
        private DescriptorProtos.FieldDescriptorProto proto;
        private Type type;

        /* synthetic */ FieldDescriptor(DescriptorProtos.FieldDescriptorProto x0, FileDescriptor x1, Descriptor x2, int x3, boolean x4, AnonymousClass1 x5) throws DescriptorValidationException {
            this(x0, x1, x2, x3, x4);
        }

        static {
            if (Type.types.length != DescriptorProtos.FieldDescriptorProto.Type.values().length) {
                throw new RuntimeException("descriptor.proto has a new declared type but Descriptors.java wasn't updated.");
            }
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.FieldDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public int getNumber() {
            return this.proto.getNumber();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public String getJsonName() {
            String result = this.jsonName;
            if (result != null) {
                return result;
            }
            if (this.proto.hasJsonName()) {
                String jsonName = this.proto.getJsonName();
                this.jsonName = jsonName;
                return jsonName;
            }
            String strFieldNameToJsonName = fieldNameToJsonName(this.proto.getName());
            this.jsonName = strFieldNameToJsonName;
            return strFieldNameToJsonName;
        }

        public JavaType getJavaType() {
            return this.type.getJavaType();
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.JavaType getLiteJavaType() {
            return getLiteType().getJavaType();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        public Type getType() {
            return this.type;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.FieldType getLiteType() {
            return table[this.type.ordinal()];
        }

        public boolean needsUtf8Check() {
            if (this.type != Type.STRING) {
                return false;
            }
            if (getContainingType().getOptions().getMapEntry() || getFile().getSyntax() == FileDescriptor.Syntax.PROTO3) {
                return true;
            }
            return getFile().getOptions().getJavaStringCheckUtf8();
        }

        public boolean isMapField() {
            return getType() == Type.MESSAGE && isRepeated() && getMessageType().getOptions().getMapEntry();
        }

        public boolean isRequired() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REQUIRED;
        }

        public boolean isOptional() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isRepeated() {
            return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REPEATED;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isPacked() {
            if (!isPackable()) {
                return false;
            }
            if (getFile().getSyntax() == FileDescriptor.Syntax.PROTO2) {
                return getOptions().getPacked();
            }
            return !getOptions().hasPacked() || getOptions().getPacked();
        }

        public boolean isPackable() {
            return isRepeated() && getLiteType().isPackable();
        }

        public boolean hasDefaultValue() {
            return this.proto.hasDefaultValue();
        }

        public Object getDefaultValue() {
            if (getJavaType() == JavaType.MESSAGE) {
                throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
            }
            return this.defaultValue;
        }

        public DescriptorProtos.FieldOptions getOptions() {
            return this.proto.getOptions();
        }

        public boolean isExtension() {
            return this.proto.hasExtendee();
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public OneofDescriptor getContainingOneof() {
            return this.containingOneof;
        }

        public OneofDescriptor getRealContainingOneof() {
            OneofDescriptor oneofDescriptor = this.containingOneof;
            if (oneofDescriptor == null || oneofDescriptor.isSynthetic()) {
                return null;
            }
            return this.containingOneof;
        }

        public boolean hasOptionalKeyword() {
            return this.isProto3Optional || (this.file.getSyntax() == FileDescriptor.Syntax.PROTO2 && isOptional() && getContainingOneof() == null);
        }

        public boolean hasPresence() {
            if (isRepeated()) {
                return false;
            }
            return getType() == Type.MESSAGE || getType() == Type.GROUP || getContainingOneof() != null || this.file.getSyntax() == FileDescriptor.Syntax.PROTO2;
        }

        public Descriptor getExtensionScope() {
            if (!isExtension()) {
                throw new UnsupportedOperationException(String.format("This field is not an extension. (%s)", this.fullName));
            }
            return this.extensionScope;
        }

        public Descriptor getMessageType() {
            if (getJavaType() != JavaType.MESSAGE) {
                throw new UnsupportedOperationException(String.format("This field is not of message type. (%s)", this.fullName));
            }
            return this.messageType;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public EnumDescriptor getEnumType() {
            if (getJavaType() != JavaType.ENUM) {
                throw new UnsupportedOperationException(String.format("This field is not of enum type. (%s)", this.fullName));
            }
            return this.enumType;
        }

        @Override // java.lang.Comparable
        public int compareTo(FieldDescriptor other) {
            if (other.containingType != this.containingType) {
                throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
            }
            return getNumber() - other.getNumber();
        }

        public String toString() {
            return getFullName();
        }

        public enum Type {
            DOUBLE(JavaType.DOUBLE),
            FLOAT(JavaType.FLOAT),
            INT64(JavaType.LONG),
            UINT64(JavaType.LONG),
            INT32(JavaType.INT),
            FIXED64(JavaType.LONG),
            FIXED32(JavaType.INT),
            BOOL(JavaType.BOOLEAN),
            STRING(JavaType.STRING),
            GROUP(JavaType.MESSAGE),
            MESSAGE(JavaType.MESSAGE),
            BYTES(JavaType.BYTE_STRING),
            UINT32(JavaType.INT),
            ENUM(JavaType.ENUM),
            SFIXED32(JavaType.INT),
            SFIXED64(JavaType.LONG),
            SINT32(JavaType.INT),
            SINT64(JavaType.LONG);

            private static final Type[] types = values();
            private final JavaType javaType;

            Type(JavaType javaType) {
                this.javaType = javaType;
            }

            public DescriptorProtos.FieldDescriptorProto.Type toProto() {
                return DescriptorProtos.FieldDescriptorProto.Type.forNumber(ordinal() + 1);
            }

            public JavaType getJavaType() {
                return this.javaType;
            }

            public static Type valueOf(DescriptorProtos.FieldDescriptorProto.Type type) {
                return types[type.getNumber() - 1];
            }
        }

        public enum JavaType {
            INT(0),
            LONG(0L),
            FLOAT(Float.valueOf(0.0f)),
            DOUBLE(Double.valueOf(0.0d)),
            BOOLEAN(false),
            STRING(""),
            BYTE_STRING(ByteString.EMPTY),
            ENUM(null),
            MESSAGE(null);

            private final Object defaultDefault;

            JavaType(Object defaultDefault) {
                this.defaultDefault = defaultDefault;
            }
        }

        private static String fieldNameToJsonName(String name) {
            int length = name.length();
            StringBuilder result = new StringBuilder(length);
            boolean isNextUpperCase = false;
            for (int i = 0; i < length; i++) {
                char ch = name.charAt(i);
                if (ch == '_') {
                    isNextUpperCase = true;
                } else if (isNextUpperCase) {
                    if ('a' <= ch && ch <= 'z') {
                        ch = (char) ((ch - 'a') + 65);
                    }
                    result.append(ch);
                    isNextUpperCase = false;
                } else {
                    result.append(ch);
                }
            }
            return result.toString();
        }

        /* JADX WARN: Illegal instructions before constructor call */
        private FieldDescriptor(DescriptorProtos.FieldDescriptorProto proto, FileDescriptor file, Descriptor parent, int index, boolean isExtension) throws DescriptorValidationException {
            AnonymousClass1 anonymousClass1 = null;
            super(anonymousClass1);
            this.index = index;
            this.proto = proto;
            this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
            this.file = file;
            if (proto.hasType()) {
                this.type = Type.valueOf(proto.getType());
            }
            this.isProto3Optional = proto.getProto3Optional();
            if (getNumber() <= 0) {
                throw new DescriptorValidationException(this, "Field numbers must be positive integers.", anonymousClass1);
            }
            if (isExtension) {
                if (!proto.hasExtendee()) {
                    throw new DescriptorValidationException(this, "FieldDescriptorProto.extendee not set for extension field.", anonymousClass1);
                }
                this.containingType = null;
                if (parent != null) {
                    this.extensionScope = parent;
                } else {
                    this.extensionScope = null;
                }
                if (proto.hasOneofIndex()) {
                    throw new DescriptorValidationException(this, "FieldDescriptorProto.oneof_index set for extension field.", anonymousClass1);
                }
                this.containingOneof = null;
            } else {
                if (proto.hasExtendee()) {
                    throw new DescriptorValidationException(this, "FieldDescriptorProto.extendee set for non-extension field.", anonymousClass1);
                }
                this.containingType = parent;
                if (proto.hasOneofIndex()) {
                    if (proto.getOneofIndex() < 0 || proto.getOneofIndex() >= parent.toProto().getOneofDeclCount()) {
                        throw new DescriptorValidationException(this, "FieldDescriptorProto.oneof_index is out of range for type " + parent.getName(), anonymousClass1);
                    }
                    OneofDescriptor oneofDescriptor = parent.getOneofs().get(proto.getOneofIndex());
                    this.containingOneof = oneofDescriptor;
                    OneofDescriptor.access$2608(oneofDescriptor);
                } else {
                    this.containingOneof = null;
                }
                this.extensionScope = null;
            }
            file.pool.addSymbol(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public void crossLink() throws DescriptorValidationException {
            AnonymousClass1 anonymousClass1 = null;
            if (this.proto.hasExtendee()) {
                GenericDescriptor extendee = this.file.pool.lookupSymbol(this.proto.getExtendee(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
                if (!(extendee instanceof Descriptor)) {
                    throw new DescriptorValidationException(this, '\"' + this.proto.getExtendee() + "\" is not a message type.", anonymousClass1);
                }
                this.containingType = (Descriptor) extendee;
                if (!getContainingType().isExtensionNumber(getNumber())) {
                    throw new DescriptorValidationException(this, '\"' + getContainingType().getFullName() + "\" does not declare " + getNumber() + " as an extension number.", anonymousClass1);
                }
            }
            if (this.proto.hasTypeName()) {
                GenericDescriptor typeDescriptor = this.file.pool.lookupSymbol(this.proto.getTypeName(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
                if (!this.proto.hasType()) {
                    if (typeDescriptor instanceof Descriptor) {
                        this.type = Type.MESSAGE;
                    } else if (typeDescriptor instanceof EnumDescriptor) {
                        this.type = Type.ENUM;
                    } else {
                        throw new DescriptorValidationException(this, '\"' + this.proto.getTypeName() + "\" is not a type.", anonymousClass1);
                    }
                }
                if (getJavaType() == JavaType.MESSAGE) {
                    if (!(typeDescriptor instanceof Descriptor)) {
                        throw new DescriptorValidationException(this, '\"' + this.proto.getTypeName() + "\" is not a message type.", anonymousClass1);
                    }
                    this.messageType = (Descriptor) typeDescriptor;
                    if (this.proto.hasDefaultValue()) {
                        throw new DescriptorValidationException(this, "Messages can't have default values.", anonymousClass1);
                    }
                } else if (getJavaType() == JavaType.ENUM) {
                    if (!(typeDescriptor instanceof EnumDescriptor)) {
                        throw new DescriptorValidationException(this, '\"' + this.proto.getTypeName() + "\" is not an enum type.", anonymousClass1);
                    }
                    this.enumType = (EnumDescriptor) typeDescriptor;
                } else {
                    throw new DescriptorValidationException(this, "Field with primitive type has type_name.", anonymousClass1);
                }
            } else if (getJavaType() == JavaType.MESSAGE || getJavaType() == JavaType.ENUM) {
                throw new DescriptorValidationException(this, "Field with message or enum type missing type_name.", anonymousClass1);
            }
            if (this.proto.getOptions().getPacked() && !isPackable()) {
                throw new DescriptorValidationException(this, "[packed = true] can only be specified for repeated primitive fields.", anonymousClass1);
            }
            if (this.proto.hasDefaultValue()) {
                if (isRepeated()) {
                    throw new DescriptorValidationException(this, "Repeated fields cannot have default values.", anonymousClass1);
                }
                try {
                    switch (AnonymousClass1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[getType().ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                            this.defaultValue = Integer.valueOf(TextFormat.parseInt32(this.proto.getDefaultValue()));
                            break;
                        case 4:
                        case 5:
                            this.defaultValue = Integer.valueOf(TextFormat.parseUInt32(this.proto.getDefaultValue()));
                            break;
                        case 6:
                        case 7:
                        case 8:
                            this.defaultValue = Long.valueOf(TextFormat.parseInt64(this.proto.getDefaultValue()));
                            break;
                        case 9:
                        case 10:
                            this.defaultValue = Long.valueOf(TextFormat.parseUInt64(this.proto.getDefaultValue()));
                            break;
                        case 11:
                            if (this.proto.getDefaultValue().equals("inf")) {
                                this.defaultValue = Float.valueOf(Float.POSITIVE_INFINITY);
                            } else if (this.proto.getDefaultValue().equals("-inf")) {
                                this.defaultValue = Float.valueOf(Float.NEGATIVE_INFINITY);
                            } else if (this.proto.getDefaultValue().equals("nan")) {
                                this.defaultValue = Float.valueOf(Float.NaN);
                            } else {
                                this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
                            }
                            break;
                        case 12:
                            if (this.proto.getDefaultValue().equals("inf")) {
                                this.defaultValue = Double.valueOf(Double.POSITIVE_INFINITY);
                            } else if (this.proto.getDefaultValue().equals("-inf")) {
                                this.defaultValue = Double.valueOf(Double.NEGATIVE_INFINITY);
                            } else if (this.proto.getDefaultValue().equals("nan")) {
                                this.defaultValue = Double.valueOf(Double.NaN);
                            } else {
                                this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
                            }
                            break;
                        case 13:
                            this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
                            break;
                        case 14:
                            this.defaultValue = this.proto.getDefaultValue();
                            break;
                        case 15:
                            try {
                                this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
                            } catch (TextFormat.InvalidEscapeSequenceException e) {
                                throw new DescriptorValidationException(this, "Couldn't parse default value: " + e.getMessage(), e, anonymousClass1);
                            }
                            break;
                        case 16:
                            EnumValueDescriptor enumValueDescriptorFindValueByName = this.enumType.findValueByName(this.proto.getDefaultValue());
                            this.defaultValue = enumValueDescriptorFindValueByName;
                            if (enumValueDescriptorFindValueByName == null) {
                                throw new DescriptorValidationException(this, "Unknown enum default value: \"" + this.proto.getDefaultValue() + '\"', anonymousClass1);
                            }
                            break;
                            break;
                        case 17:
                        case 18:
                            throw new DescriptorValidationException(this, "Message type had default value.", anonymousClass1);
                    }
                } catch (NumberFormatException e2) {
                    throw new DescriptorValidationException(this, "Could not parse default value: \"" + this.proto.getDefaultValue() + '\"', e2, anonymousClass1);
                }
            } else if (isRepeated()) {
                this.defaultValue = Collections.emptyList();
            } else {
                int i = AnonymousClass1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[getJavaType().ordinal()];
                if (i == 1) {
                    this.defaultValue = this.enumType.getValues().get(0);
                } else if (i != 2) {
                    this.defaultValue = getJavaType().defaultDefault;
                } else {
                    this.defaultValue = null;
                }
            }
            Descriptor descriptor = this.containingType;
            if (descriptor != null && descriptor.getOptions().getMessageSetWireFormat()) {
                if (isExtension()) {
                    if (!isOptional() || getType() != Type.MESSAGE) {
                        throw new DescriptorValidationException(this, "Extensions of MessageSets must be optional messages.", anonymousClass1);
                    }
                    return;
                }
                throw new DescriptorValidationException(this, "MessageSets cannot have fields, only extensions.", anonymousClass1);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.FieldDescriptorProto proto) {
            this.proto = proto;
        }

        @Override // com.google.protobuf.FieldSet.FieldDescriptorLite
        public MessageLite.Builder internalMergeFrom(MessageLite.Builder to, MessageLite from) {
            return ((Message.Builder) to).mergeFrom((Message) from);
        }
    }

    /* JADX INFO: renamed from: com.google.protobuf.Descriptors$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type;

        static {
            int[] iArr = new int[FieldDescriptor.JavaType.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType = iArr;
            try {
                iArr[FieldDescriptor.JavaType.ENUM.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[FieldDescriptor.JavaType.MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            int[] iArr2 = new int[FieldDescriptor.Type.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = iArr2;
            try {
                iArr2[FieldDescriptor.Type.INT32.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.SINT32.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.SFIXED32.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.UINT32.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.INT64.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.SINT64.ordinal()] = 7;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.SFIXED64.ordinal()] = 8;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.UINT64.ordinal()] = 9;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.FIXED64.ordinal()] = 10;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.FLOAT.ordinal()] = 11;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.DOUBLE.ordinal()] = 12;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.BOOL.ordinal()] = 13;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.STRING.ordinal()] = 14;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.ENUM.ordinal()] = 16;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.MESSAGE.ordinal()] = 17;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[FieldDescriptor.Type.GROUP.ordinal()] = 18;
            } catch (NoSuchFieldError e20) {
            }
        }
    }

    public static final class EnumDescriptor extends GenericDescriptor implements Internal.EnumLiteMap<EnumValueDescriptor> {
        private ReferenceQueue<EnumValueDescriptor> cleanupQueue;
        private final Descriptor containingType;
        private final int distinctNumbers;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos.EnumDescriptorProto proto;
        private Map<Integer, WeakReference<EnumValueDescriptor>> unknownValues;
        private final EnumValueDescriptor[] values;
        private final EnumValueDescriptor[] valuesSortedByNumber;

        /* synthetic */ EnumDescriptor(DescriptorProtos.EnumDescriptorProto x0, FileDescriptor x1, Descriptor x2, int x3, AnonymousClass1 x4) throws DescriptorValidationException {
            this(x0, x1, x2, x3);
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.EnumDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public DescriptorProtos.EnumOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<EnumValueDescriptor> getValues() {
            return Collections.unmodifiableList(Arrays.asList(this.values));
        }

        public EnumValueDescriptor findValueByName(String name) {
            GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
            if (result instanceof EnumValueDescriptor) {
                return (EnumValueDescriptor) result;
            }
            return null;
        }

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public EnumValueDescriptor findValueByNumber(int number) {
            return (EnumValueDescriptor) Descriptors.binarySearch(this.valuesSortedByNumber, this.distinctNumbers, EnumValueDescriptor.NUMBER_GETTER, number);
        }

        private static class UnknownEnumValueReference extends WeakReference<EnumValueDescriptor> {
            private final int number;

            /* synthetic */ UnknownEnumValueReference(int x0, EnumValueDescriptor x1, AnonymousClass1 x2) {
                this(x0, x1);
            }

            private UnknownEnumValueReference(int number, EnumValueDescriptor descriptor) {
                super(descriptor);
                this.number = number;
            }
        }

        public EnumValueDescriptor findValueByNumberCreatingIfUnknown(int number) {
            EnumValueDescriptor result;
            EnumValueDescriptor result2 = findValueByNumber(number);
            if (result2 != null) {
                return result2;
            }
            synchronized (this) {
                if (this.cleanupQueue == null) {
                    this.cleanupQueue = new ReferenceQueue<>();
                    this.unknownValues = new HashMap();
                } else {
                    while (true) {
                        UnknownEnumValueReference toClean = (UnknownEnumValueReference) this.cleanupQueue.poll();
                        if (toClean == null) {
                            break;
                        }
                        this.unknownValues.remove(Integer.valueOf(toClean.number));
                    }
                }
                WeakReference<EnumValueDescriptor> reference = this.unknownValues.get(Integer.valueOf(number));
                AnonymousClass1 anonymousClass1 = null;
                result = reference == null ? null : reference.get();
                if (result == null) {
                    result = new EnumValueDescriptor(this, Integer.valueOf(number), anonymousClass1);
                    this.unknownValues.put(Integer.valueOf(number), new UnknownEnumValueReference(number, result, anonymousClass1));
                }
            }
            return result;
        }

        int getUnknownEnumValueDescriptorCount() {
            return this.unknownValues.size();
        }

        /* JADX WARN: Illegal instructions before constructor call */
        private EnumDescriptor(DescriptorProtos.EnumDescriptorProto proto, FileDescriptor file, Descriptor parent, int index) throws DescriptorValidationException {
            AnonymousClass1 anonymousClass1 = null;
            super(anonymousClass1);
            this.unknownValues = null;
            this.cleanupQueue = null;
            this.index = index;
            this.proto = proto;
            this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
            this.file = file;
            this.containingType = parent;
            if (proto.getValueCount() == 0) {
                throw new DescriptorValidationException(this, "Enums must contain at least one value.", anonymousClass1);
            }
            this.values = new EnumValueDescriptor[proto.getValueCount()];
            for (int i = 0; i < proto.getValueCount(); i++) {
                this.values[i] = new EnumValueDescriptor(proto.getValue(i), file, this, i, null);
            }
            EnumValueDescriptor[] enumValueDescriptorArr = (EnumValueDescriptor[]) this.values.clone();
            this.valuesSortedByNumber = enumValueDescriptorArr;
            Arrays.sort(enumValueDescriptorArr, EnumValueDescriptor.BY_NUMBER);
            int j = 0;
            for (int i2 = 1; i2 < proto.getValueCount(); i2++) {
                EnumValueDescriptor[] enumValueDescriptorArr2 = this.valuesSortedByNumber;
                EnumValueDescriptor oldValue = enumValueDescriptorArr2[j];
                EnumValueDescriptor newValue = enumValueDescriptorArr2[i2];
                if (oldValue.getNumber() != newValue.getNumber()) {
                    j++;
                    this.valuesSortedByNumber[j] = newValue;
                }
            }
            int i3 = j + 1;
            this.distinctNumbers = i3;
            Arrays.fill(this.valuesSortedByNumber, i3, proto.getValueCount(), (Object) null);
            file.pool.addSymbol(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.EnumDescriptorProto proto) {
            this.proto = proto;
            int i = 0;
            while (true) {
                EnumValueDescriptor[] enumValueDescriptorArr = this.values;
                if (i >= enumValueDescriptorArr.length) {
                    return;
                }
                enumValueDescriptorArr[i].setProto(proto.getValue(i));
                i++;
            }
        }
    }

    public static final class EnumValueDescriptor extends GenericDescriptor implements Internal.EnumLite {
        static final Comparator<EnumValueDescriptor> BY_NUMBER = new Comparator<EnumValueDescriptor>() { // from class: com.google.protobuf.Descriptors.EnumValueDescriptor.1
            @Override // java.util.Comparator
            public int compare(EnumValueDescriptor o1, EnumValueDescriptor o2) {
                return Integer.valueOf(o1.getNumber()).compareTo(Integer.valueOf(o2.getNumber()));
            }
        };
        static final NumberGetter<EnumValueDescriptor> NUMBER_GETTER = new NumberGetter<EnumValueDescriptor>() { // from class: com.google.protobuf.Descriptors.EnumValueDescriptor.2
            @Override // com.google.protobuf.Descriptors.NumberGetter
            public int getNumber(EnumValueDescriptor enumValueDescriptor) {
                return enumValueDescriptor.getNumber();
            }
        };
        private final String fullName;
        private final int index;
        private DescriptorProtos.EnumValueDescriptorProto proto;
        private final EnumDescriptor type;

        /* synthetic */ EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto x0, FileDescriptor x1, EnumDescriptor x2, int x3, AnonymousClass1 x4) throws DescriptorValidationException {
            this(x0, x1, x2, x3);
        }

        /* synthetic */ EnumValueDescriptor(EnumDescriptor x0, Integer x1, AnonymousClass1 x2) {
            this(x0, x1);
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.EnumValueDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.proto.getNumber();
        }

        public String toString() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.type.file;
        }

        public EnumDescriptor getType() {
            return this.type;
        }

        public DescriptorProtos.EnumValueOptions getOptions() {
            return this.proto.getOptions();
        }

        private EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto proto, FileDescriptor file, EnumDescriptor parent, int index) throws DescriptorValidationException {
            super(null);
            this.index = index;
            this.proto = proto;
            this.type = parent;
            this.fullName = parent.getFullName() + '.' + proto.getName();
            file.pool.addSymbol(this);
        }

        private EnumValueDescriptor(EnumDescriptor parent, Integer number) {
            super(null);
            String name = "UNKNOWN_ENUM_VALUE_" + parent.getName() + "_" + number;
            DescriptorProtos.EnumValueDescriptorProto proto = DescriptorProtos.EnumValueDescriptorProto.newBuilder().setName(name).setNumber(number.intValue()).build();
            this.index = -1;
            this.proto = proto;
            this.type = parent;
            this.fullName = parent.getFullName() + '.' + proto.getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.EnumValueDescriptorProto proto) {
            this.proto = proto;
        }
    }

    public static final class ServiceDescriptor extends GenericDescriptor {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private MethodDescriptor[] methods;
        private DescriptorProtos.ServiceDescriptorProto proto;

        /* synthetic */ ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto x0, FileDescriptor x1, int x2, AnonymousClass1 x3) throws DescriptorValidationException {
            this(x0, x1, x2);
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.ServiceDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        public DescriptorProtos.ServiceOptions getOptions() {
            return this.proto.getOptions();
        }

        public List<MethodDescriptor> getMethods() {
            return Collections.unmodifiableList(Arrays.asList(this.methods));
        }

        public MethodDescriptor findMethodByName(String name) {
            GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
            if (result instanceof MethodDescriptor) {
                return (MethodDescriptor) result;
            }
            return null;
        }

        private ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto proto, FileDescriptor file, int index) throws DescriptorValidationException {
            super(null);
            this.index = index;
            this.proto = proto;
            this.fullName = Descriptors.computeFullName(file, null, proto.getName());
            this.file = file;
            this.methods = new MethodDescriptor[proto.getMethodCount()];
            for (int i = 0; i < proto.getMethodCount(); i++) {
                this.methods[i] = new MethodDescriptor(proto.getMethod(i), file, this, i, null);
            }
            file.pool.addSymbol(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            for (MethodDescriptor method : this.methods) {
                method.crossLink();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.ServiceDescriptorProto proto) {
            this.proto = proto;
            int i = 0;
            while (true) {
                MethodDescriptor[] methodDescriptorArr = this.methods;
                if (i >= methodDescriptorArr.length) {
                    return;
                }
                methodDescriptorArr[i].setProto(proto.getMethod(i));
                i++;
            }
        }
    }

    public static final class MethodDescriptor extends GenericDescriptor {
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private Descriptor inputType;
        private Descriptor outputType;
        private DescriptorProtos.MethodDescriptorProto proto;
        private final ServiceDescriptor service;

        /* synthetic */ MethodDescriptor(DescriptorProtos.MethodDescriptorProto x0, FileDescriptor x1, ServiceDescriptor x2, int x3, AnonymousClass1 x4) throws DescriptorValidationException {
            this(x0, x1, x2, x3);
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.MethodDescriptorProto toProto() {
            return this.proto;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        public ServiceDescriptor getService() {
            return this.service;
        }

        public Descriptor getInputType() {
            return this.inputType;
        }

        public Descriptor getOutputType() {
            return this.outputType;
        }

        public boolean isClientStreaming() {
            return this.proto.getClientStreaming();
        }

        public boolean isServerStreaming() {
            return this.proto.getServerStreaming();
        }

        public DescriptorProtos.MethodOptions getOptions() {
            return this.proto.getOptions();
        }

        private MethodDescriptor(DescriptorProtos.MethodDescriptorProto proto, FileDescriptor file, ServiceDescriptor parent, int index) throws DescriptorValidationException {
            super(null);
            this.index = index;
            this.proto = proto;
            this.file = file;
            this.service = parent;
            this.fullName = parent.getFullName() + '.' + proto.getName();
            file.pool.addSymbol(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void crossLink() throws DescriptorValidationException {
            GenericDescriptor input = getFile().pool.lookupSymbol(this.proto.getInputType(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
            AnonymousClass1 anonymousClass1 = null;
            if (!(input instanceof Descriptor)) {
                throw new DescriptorValidationException(this, '\"' + this.proto.getInputType() + "\" is not a message type.", anonymousClass1);
            }
            this.inputType = (Descriptor) input;
            GenericDescriptor output = getFile().pool.lookupSymbol(this.proto.getOutputType(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
            if (!(output instanceof Descriptor)) {
                throw new DescriptorValidationException(this, '\"' + this.proto.getOutputType() + "\" is not a message type.", anonymousClass1);
            }
            this.outputType = (Descriptor) output;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.MethodDescriptorProto proto) {
            this.proto = proto;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String computeFullName(FileDescriptor file, Descriptor parent, String name) {
        if (parent != null) {
            return parent.getFullName() + '.' + name;
        }
        String packageName = file.getPackage();
        if (!packageName.isEmpty()) {
            return packageName + '.' + name;
        }
        return name;
    }

    public static abstract class GenericDescriptor {
        public abstract FileDescriptor getFile();

        public abstract String getFullName();

        public abstract String getName();

        public abstract Message toProto();

        /* synthetic */ GenericDescriptor(AnonymousClass1 x0) {
            this();
        }

        private GenericDescriptor() {
        }
    }

    public static class DescriptorValidationException extends Exception {
        private static final long serialVersionUID = 5750205775490483148L;
        private final String description;
        private final String name;
        private final Message proto;

        /* synthetic */ DescriptorValidationException(FileDescriptor x0, String x1, AnonymousClass1 x2) {
            this(x0, x1);
        }

        /* synthetic */ DescriptorValidationException(GenericDescriptor x0, String x1, AnonymousClass1 x2) {
            this(x0, x1);
        }

        /* synthetic */ DescriptorValidationException(GenericDescriptor x0, String x1, Throwable x2, AnonymousClass1 x3) {
            this(x0, x1, x2);
        }

        public String getProblemSymbolName() {
            return this.name;
        }

        public Message getProblemProto() {
            return this.proto;
        }

        public String getDescription() {
            return this.description;
        }

        private DescriptorValidationException(GenericDescriptor problemDescriptor, String description) {
            super(problemDescriptor.getFullName() + ": " + description);
            this.name = problemDescriptor.getFullName();
            this.proto = problemDescriptor.toProto();
            this.description = description;
        }

        private DescriptorValidationException(GenericDescriptor problemDescriptor, String description, Throwable cause) {
            this(problemDescriptor, description);
            initCause(cause);
        }

        private DescriptorValidationException(FileDescriptor problemDescriptor, String description) {
            super(problemDescriptor.getName() + ": " + description);
            this.name = problemDescriptor.getName();
            this.proto = problemDescriptor.toProto();
            this.description = description;
        }
    }

    private static final class DescriptorPool {
        private final boolean allowUnknownDependencies;
        private final Set<FileDescriptor> dependencies;
        private final Map<String, GenericDescriptor> descriptorsByName = new HashMap();

        enum SearchFilter {
            TYPES_ONLY,
            AGGREGATES_ONLY,
            ALL_SYMBOLS
        }

        DescriptorPool(FileDescriptor[] dependencies, boolean allowUnknownDependencies) {
            this.dependencies = Collections.newSetFromMap(new IdentityHashMap(dependencies.length));
            this.allowUnknownDependencies = allowUnknownDependencies;
            for (FileDescriptor dependency : dependencies) {
                this.dependencies.add(dependency);
                importPublicDependencies(dependency);
            }
            for (FileDescriptor dependency2 : this.dependencies) {
                try {
                    addPackage(dependency2.getPackage(), dependency2);
                } catch (DescriptorValidationException e) {
                    throw new AssertionError(e);
                }
            }
        }

        private void importPublicDependencies(FileDescriptor file) {
            for (FileDescriptor dependency : file.getPublicDependencies()) {
                if (this.dependencies.add(dependency)) {
                    importPublicDependencies(dependency);
                }
            }
        }

        GenericDescriptor findSymbol(String fullName) {
            return findSymbol(fullName, SearchFilter.ALL_SYMBOLS);
        }

        GenericDescriptor findSymbol(String fullName, SearchFilter filter) {
            GenericDescriptor result = this.descriptorsByName.get(fullName);
            if (result != null && (filter == SearchFilter.ALL_SYMBOLS || ((filter == SearchFilter.TYPES_ONLY && isType(result)) || (filter == SearchFilter.AGGREGATES_ONLY && isAggregate(result))))) {
                return result;
            }
            for (FileDescriptor dependency : this.dependencies) {
                GenericDescriptor result2 = dependency.pool.descriptorsByName.get(fullName);
                if (result2 != null && (filter == SearchFilter.ALL_SYMBOLS || ((filter == SearchFilter.TYPES_ONLY && isType(result2)) || (filter == SearchFilter.AGGREGATES_ONLY && isAggregate(result2))))) {
                    return result2;
                }
            }
            return null;
        }

        boolean isType(GenericDescriptor descriptor) {
            return (descriptor instanceof Descriptor) || (descriptor instanceof EnumDescriptor);
        }

        boolean isAggregate(GenericDescriptor descriptor) {
            return (descriptor instanceof Descriptor) || (descriptor instanceof EnumDescriptor) || (descriptor instanceof PackageDescriptor) || (descriptor instanceof ServiceDescriptor);
        }

        GenericDescriptor lookupSymbol(String name, GenericDescriptor relativeTo, SearchFilter filter) throws DescriptorValidationException {
            String firstPart;
            String fullname;
            GenericDescriptor result;
            GenericDescriptor result2;
            if (name.startsWith(".")) {
                fullname = name.substring(1);
                result = findSymbol(fullname, filter);
            } else {
                int firstPartLength = name.indexOf(46);
                if (firstPartLength == -1) {
                    firstPart = name;
                } else {
                    firstPart = name.substring(0, firstPartLength);
                }
                StringBuilder scopeToTry = new StringBuilder(relativeTo.getFullName());
                while (true) {
                    int dotpos = scopeToTry.lastIndexOf(".");
                    if (dotpos == -1) {
                        fullname = name;
                        GenericDescriptor result3 = findSymbol(name, filter);
                        result = result3;
                        break;
                    }
                    scopeToTry.setLength(dotpos + 1);
                    scopeToTry.append(firstPart);
                    GenericDescriptor result4 = findSymbol(scopeToTry.toString(), SearchFilter.AGGREGATES_ONLY);
                    if (result4 != null) {
                        if (firstPartLength == -1) {
                            result2 = result4;
                        } else {
                            scopeToTry.setLength(dotpos + 1);
                            scopeToTry.append(name);
                            result2 = findSymbol(scopeToTry.toString(), filter);
                        }
                        String fullname2 = scopeToTry.toString();
                        result = result2;
                        fullname = fullname2;
                    } else {
                        scopeToTry.setLength(dotpos);
                    }
                }
            }
            if (result == null) {
                if (this.allowUnknownDependencies && filter == SearchFilter.TYPES_ONLY) {
                    Descriptors.logger.warning("The descriptor for message type \"" + name + "\" cannot be found and a placeholder is created for it");
                    GenericDescriptor result5 = new Descriptor(fullname);
                    this.dependencies.add(result5.getFile());
                    return result5;
                }
                throw new DescriptorValidationException(relativeTo, '\"' + name + "\" is not defined.", (AnonymousClass1) null);
            }
            return result;
        }

        void addSymbol(GenericDescriptor descriptor) throws DescriptorValidationException {
            validateSymbolName(descriptor);
            String fullName = descriptor.getFullName();
            GenericDescriptor old = this.descriptorsByName.put(fullName, descriptor);
            if (old != null) {
                this.descriptorsByName.put(fullName, old);
                AnonymousClass1 anonymousClass1 = null;
                if (descriptor.getFile() == old.getFile()) {
                    int dotpos = fullName.lastIndexOf(46);
                    if (dotpos == -1) {
                        throw new DescriptorValidationException(descriptor, '\"' + fullName + "\" is already defined.", anonymousClass1);
                    }
                    throw new DescriptorValidationException(descriptor, '\"' + fullName.substring(dotpos + 1) + "\" is already defined in \"" + fullName.substring(0, dotpos) + "\".", anonymousClass1);
                }
                throw new DescriptorValidationException(descriptor, '\"' + fullName + "\" is already defined in file \"" + old.getFile().getName() + "\".", anonymousClass1);
            }
        }

        private static final class PackageDescriptor extends GenericDescriptor {
            private final FileDescriptor file;
            private final String fullName;
            private final String name;

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public Message toProto() {
                return this.file.toProto();
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public String getName() {
                return this.name;
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public String getFullName() {
                return this.fullName;
            }

            @Override // com.google.protobuf.Descriptors.GenericDescriptor
            public FileDescriptor getFile() {
                return this.file;
            }

            PackageDescriptor(String name, String fullName, FileDescriptor file) {
                super(null);
                this.file = file;
                this.fullName = fullName;
                this.name = name;
            }
        }

        void addPackage(String fullName, FileDescriptor file) throws DescriptorValidationException {
            String name;
            int dotpos = fullName.lastIndexOf(46);
            if (dotpos == -1) {
                name = fullName;
            } else {
                addPackage(fullName.substring(0, dotpos), file);
                name = fullName.substring(dotpos + 1);
            }
            GenericDescriptor old = this.descriptorsByName.put(fullName, new PackageDescriptor(name, fullName, file));
            if (old != null) {
                this.descriptorsByName.put(fullName, old);
                if (!(old instanceof PackageDescriptor)) {
                    throw new DescriptorValidationException(file, '\"' + name + "\" is already defined (as something other than a package) in file \"" + old.getFile().getName() + "\".", (AnonymousClass1) null);
                }
            }
        }

        static void validateSymbolName(GenericDescriptor descriptor) throws DescriptorValidationException {
            String name = descriptor.getName();
            AnonymousClass1 anonymousClass1 = null;
            if (name.length() == 0) {
                throw new DescriptorValidationException(descriptor, "Missing name.", anonymousClass1);
            }
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);
                if (('a' > c || c > 'z') && (('A' > c || c > 'Z') && c != '_' && ('0' > c || c > '9' || i <= 0))) {
                    throw new DescriptorValidationException(descriptor, '\"' + name + "\" is not a valid identifier.", anonymousClass1);
                }
            }
        }
    }

    public static final class OneofDescriptor extends GenericDescriptor {
        private Descriptor containingType;
        private int fieldCount;
        private FieldDescriptor[] fields;
        private final FileDescriptor file;
        private final String fullName;
        private final int index;
        private DescriptorProtos.OneofDescriptorProto proto;

        /* synthetic */ OneofDescriptor(DescriptorProtos.OneofDescriptorProto x0, FileDescriptor x1, Descriptor x2, int x3, AnonymousClass1 x4) {
            this(x0, x1, x2, x3);
        }

        static /* synthetic */ int access$2608(OneofDescriptor x0) {
            int i = x0.fieldCount;
            x0.fieldCount = i + 1;
            return i;
        }

        public int getIndex() {
            return this.index;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getName() {
            return this.proto.getName();
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public FileDescriptor getFile() {
            return this.file;
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public String getFullName() {
            return this.fullName;
        }

        public Descriptor getContainingType() {
            return this.containingType;
        }

        public int getFieldCount() {
            return this.fieldCount;
        }

        public DescriptorProtos.OneofOptions getOptions() {
            return this.proto.getOptions();
        }

        public boolean isSynthetic() {
            FieldDescriptor[] fieldDescriptorArr = this.fields;
            return fieldDescriptorArr.length == 1 && fieldDescriptorArr[0].isProto3Optional;
        }

        public List<FieldDescriptor> getFields() {
            return Collections.unmodifiableList(Arrays.asList(this.fields));
        }

        public FieldDescriptor getField(int index) {
            return this.fields[index];
        }

        @Override // com.google.protobuf.Descriptors.GenericDescriptor
        public DescriptorProtos.OneofDescriptorProto toProto() {
            return this.proto;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProto(DescriptorProtos.OneofDescriptorProto proto) {
            this.proto = proto;
        }

        private OneofDescriptor(DescriptorProtos.OneofDescriptorProto proto, FileDescriptor file, Descriptor parent, int index) {
            super(null);
            this.proto = proto;
            this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
            this.file = file;
            this.index = index;
            this.containingType = parent;
            this.fieldCount = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T binarySearch(T[] array, int size, NumberGetter<T> getter, int number) {
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            T midValue = array[mid];
            int midValueNumber = getter.getNumber(midValue);
            if (number < midValueNumber) {
                right = mid - 1;
            } else if (number > midValueNumber) {
                left = mid + 1;
            } else {
                return midValue;
            }
        }
        return null;
    }
}
