package kotlinx.metadata.jvm;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.InconsistentKotlinMetadataException;
import kotlinx.metadata.KmClassVisitor;
import kotlinx.metadata.impl.ReadersKt;
import kotlinx.metadata.internal.metadata.ProtoBuf$Class;
import kotlinx.metadata.internal.metadata.ProtoBuf$Function;
import kotlinx.metadata.internal.metadata.ProtoBuf$Package;
import kotlinx.metadata.internal.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlinx.metadata.internal.metadata.jvm.deserialization.JvmNameResolver;
import kotlinx.metadata.internal.metadata.jvm.deserialization.JvmProtoBufUtil;

/* JADX INFO: compiled from: KotlinClassMetadata.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \t2\u00020\u0001:\u0007\n\t\u000b\f\r\u000e\u000fB\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0006\u0010\u0011\u0012\u0013\u0014\u0015¨\u0006\u0016"}, d2 = {"Lkotlinx/metadata/jvm/KotlinClassMetadata;", "", "Lkotlinx/metadata/jvm/KotlinClassHeader;", "header", "Lkotlinx/metadata/jvm/KotlinClassHeader;", "getHeader", "()Lkotlinx/metadata/jvm/KotlinClassHeader;", "<init>", "(Lkotlinx/metadata/jvm/KotlinClassHeader;)V", "Companion", "Class", "FileFacade", "MultiFileClassFacade", "MultiFileClassPart", "SyntheticClass", "Unknown", "Lkotlinx/metadata/jvm/KotlinClassMetadata$Class;", "Lkotlinx/metadata/jvm/KotlinClassMetadata$FileFacade;", "Lkotlinx/metadata/jvm/KotlinClassMetadata$MultiFileClassFacade;", "Lkotlinx/metadata/jvm/KotlinClassMetadata$MultiFileClassPart;", "Lkotlinx/metadata/jvm/KotlinClassMetadata$SyntheticClass;", "Lkotlinx/metadata/jvm/KotlinClassMetadata$Unknown;", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
public abstract class KotlinClassMetadata {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final KotlinClassHeader header;

    public /* synthetic */ KotlinClassMetadata(KotlinClassHeader kotlinClassHeader, DefaultConstructorMarker defaultConstructorMarker) {
        this(kotlinClassHeader);
    }

    private KotlinClassMetadata(KotlinClassHeader kotlinClassHeader) {
        this.header = kotlinClassHeader;
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    @Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R'\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Lkotlinx/metadata/jvm/KotlinClassMetadata$Class;", "Lkotlinx/metadata/jvm/KotlinClassMetadata;", "Lkotlinx/metadata/KmClassVisitor;", "v", "", "accept", "Lkotlin/Pair;", "Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmNameResolver;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Class;", "classData$delegate", "Lkotlin/Lazy;", "getClassData", "()Lkotlin/Pair;", "classData", "Lkotlinx/metadata/jvm/KotlinClassHeader;", "header", "<init>", "(Lkotlinx/metadata/jvm/KotlinClassHeader;)V", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
    public static final class Class extends KotlinClassMetadata {

        /* JADX INFO: renamed from: classData$delegate, reason: from kotlin metadata */
        private final Lazy classData;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Class(final KotlinClassHeader header) {
            super(header, null);
            Intrinsics.checkNotNullParameter(header, "header");
            this.classData = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<Pair<? extends JvmNameResolver, ? extends ProtoBuf$Class>>() { // from class: kotlinx.metadata.jvm.KotlinClassMetadata$Class$classData$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Pair<? extends JvmNameResolver, ? extends ProtoBuf$Class> invoke() {
                    String[] data1 = header.getData1();
                    if (!(!(data1.length == 0))) {
                        data1 = null;
                    }
                    if (data1 == null) {
                        throw new InconsistentKotlinMetadataException("data1 must not be empty", null, 2, null);
                    }
                    return JvmProtoBufUtil.readClassDataFrom(data1, header.getData2());
                }
            });
        }

        private final Pair<JvmNameResolver, ProtoBuf$Class> getClassData() {
            return (Pair) this.classData.getValue();
        }

        public final void accept(KmClassVisitor v) {
            Intrinsics.checkNotNullParameter(v, "v");
            Pair<JvmNameResolver, ProtoBuf$Class> classData = getClassData();
            ReadersKt.accept$default(classData.component2(), v, classData.component1(), null, 4, null);
        }
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    @Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rR'\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lkotlinx/metadata/jvm/KotlinClassMetadata$FileFacade;", "Lkotlinx/metadata/jvm/KotlinClassMetadata;", "Lkotlin/Pair;", "Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmNameResolver;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Package;", "packageData$delegate", "Lkotlin/Lazy;", "getPackageData", "()Lkotlin/Pair;", "packageData", "Lkotlinx/metadata/jvm/KotlinClassHeader;", "header", "<init>", "(Lkotlinx/metadata/jvm/KotlinClassHeader;)V", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
    public static final class FileFacade extends KotlinClassMetadata {

        /* JADX INFO: renamed from: packageData$delegate, reason: from kotlin metadata */
        private final Lazy packageData;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FileFacade(final KotlinClassHeader header) {
            super(header, null);
            Intrinsics.checkNotNullParameter(header, "header");
            this.packageData = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<Pair<? extends JvmNameResolver, ? extends ProtoBuf$Package>>() { // from class: kotlinx.metadata.jvm.KotlinClassMetadata$FileFacade$packageData$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Pair<? extends JvmNameResolver, ? extends ProtoBuf$Package> invoke() {
                    String[] data1 = header.getData1();
                    if (!(!(data1.length == 0))) {
                        data1 = null;
                    }
                    if (data1 == null) {
                        throw new InconsistentKotlinMetadataException("data1 must not be empty", null, 2, null);
                    }
                    return JvmProtoBufUtil.readPackageDataFrom(data1, header.getData2());
                }
            });
        }
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    @Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rR)\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lkotlinx/metadata/jvm/KotlinClassMetadata$SyntheticClass;", "Lkotlinx/metadata/jvm/KotlinClassMetadata;", "Lkotlin/Pair;", "Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmNameResolver;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Function;", "functionData$delegate", "Lkotlin/Lazy;", "getFunctionData", "()Lkotlin/Pair;", "functionData", "Lkotlinx/metadata/jvm/KotlinClassHeader;", "header", "<init>", "(Lkotlinx/metadata/jvm/KotlinClassHeader;)V", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
    public static final class SyntheticClass extends KotlinClassMetadata {

        /* JADX INFO: renamed from: functionData$delegate, reason: from kotlin metadata */
        private final Lazy functionData;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SyntheticClass(final KotlinClassHeader header) {
            super(header, null);
            Intrinsics.checkNotNullParameter(header, "header");
            this.functionData = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<Pair<? extends JvmNameResolver, ? extends ProtoBuf$Function>>() { // from class: kotlinx.metadata.jvm.KotlinClassMetadata$SyntheticClass$functionData$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Pair<? extends JvmNameResolver, ? extends ProtoBuf$Function> invoke() {
                    String[] data1 = header.getData1();
                    if (!(!(data1.length == 0))) {
                        data1 = null;
                    }
                    if (data1 != null) {
                        return JvmProtoBufUtil.readFunctionDataFrom(data1, header.getData2());
                    }
                    return null;
                }
            });
        }
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lkotlinx/metadata/jvm/KotlinClassMetadata$MultiFileClassFacade;", "Lkotlinx/metadata/jvm/KotlinClassMetadata;", "", "", "partClassNames", "Ljava/util/List;", "getPartClassNames", "()Ljava/util/List;", "Lkotlinx/metadata/jvm/KotlinClassHeader;", "header", "<init>", "(Lkotlinx/metadata/jvm/KotlinClassHeader;)V", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
    public static final class MultiFileClassFacade extends KotlinClassMetadata {
        private final List<String> partClassNames;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MultiFileClassFacade(KotlinClassHeader header) {
            super(header, null);
            Intrinsics.checkNotNullParameter(header, "header");
            this.partClassNames = ArraysKt___ArraysJvmKt.asList(header.getData1());
        }
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    @Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rR'\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lkotlinx/metadata/jvm/KotlinClassMetadata$MultiFileClassPart;", "Lkotlinx/metadata/jvm/KotlinClassMetadata;", "Lkotlin/Pair;", "Lkotlinx/metadata/internal/metadata/jvm/deserialization/JvmNameResolver;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Package;", "packageData$delegate", "Lkotlin/Lazy;", "getPackageData", "()Lkotlin/Pair;", "packageData", "Lkotlinx/metadata/jvm/KotlinClassHeader;", "header", "<init>", "(Lkotlinx/metadata/jvm/KotlinClassHeader;)V", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
    public static final class MultiFileClassPart extends KotlinClassMetadata {

        /* JADX INFO: renamed from: packageData$delegate, reason: from kotlin metadata */
        private final Lazy packageData;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MultiFileClassPart(final KotlinClassHeader header) {
            super(header, null);
            Intrinsics.checkNotNullParameter(header, "header");
            this.packageData = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<Pair<? extends JvmNameResolver, ? extends ProtoBuf$Package>>() { // from class: kotlinx.metadata.jvm.KotlinClassMetadata$MultiFileClassPart$packageData$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Pair<? extends JvmNameResolver, ? extends ProtoBuf$Package> invoke() {
                    String[] data1 = header.getData1();
                    if (!(!(data1.length == 0))) {
                        data1 = null;
                    }
                    if (data1 == null) {
                        throw new InconsistentKotlinMetadataException("data1 must not be empty", null, 2, null);
                    }
                    return JvmProtoBufUtil.readPackageDataFrom(data1, header.getData2());
                }
            });
        }
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlinx/metadata/jvm/KotlinClassMetadata$Unknown;", "Lkotlinx/metadata/jvm/KotlinClassMetadata;", "header", "Lkotlinx/metadata/jvm/KotlinClassHeader;", "(Lkotlinx/metadata/jvm/KotlinClassHeader;)V", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Unknown extends KotlinClassMetadata {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Unknown(KotlinClassHeader header) {
            super(header, null);
            Intrinsics.checkNotNullParameter(header, "header");
        }
    }

    /* JADX INFO: compiled from: KotlinClassMetadata.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¨\u0006\b"}, d2 = {"Lkotlinx/metadata/jvm/KotlinClassMetadata$Companion;", "", "Lkotlinx/metadata/jvm/KotlinClassHeader;", "header", "Lkotlinx/metadata/jvm/KotlinClassMetadata;", "read", "<init>", "()V", "kotlinx-metadata-jvm"}, k = 1, mv = {1, 7, 1})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final KotlinClassMetadata read(KotlinClassHeader header) {
            Intrinsics.checkNotNullParameter(header, "header");
            if (!new JvmMetadataVersion(header.getMetadataVersion(), (header.getExtraInt() & 8) != 0).isCompatible()) {
                return null;
            }
            try {
                int kind = header.getKind();
                if (kind == 1) {
                    return new Class(header);
                }
                if (kind == 2) {
                    return new FileFacade(header);
                }
                if (kind == 3) {
                    return new SyntheticClass(header);
                }
                if (kind == 4) {
                    return new MultiFileClassFacade(header);
                }
                if (kind == 5) {
                    return new MultiFileClassPart(header);
                }
                return new Unknown(header);
            } catch (InconsistentKotlinMetadataException e) {
                throw e;
            } catch (Throwable th) {
                throw new InconsistentKotlinMetadataException("Exception occurred when reading Kotlin metadata", th);
            }
        }
    }
}
