package kotlinx.metadata.impl.extensions;

import java.util.List;
import java.util.ServiceLoader;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.KmClassVisitor;
import kotlinx.metadata.KmConstructorVisitor;
import kotlinx.metadata.KmFunctionVisitor;
import kotlinx.metadata.KmPropertyVisitor;
import kotlinx.metadata.KmTypeAliasVisitor;
import kotlinx.metadata.KmTypeParameterVisitor;
import kotlinx.metadata.KmTypeVisitor;
import kotlinx.metadata.KmValueParameterVisitor;
import kotlinx.metadata.impl.ReadContext;
import kotlinx.metadata.internal.metadata.ProtoBuf$Class;
import kotlinx.metadata.internal.metadata.ProtoBuf$Constructor;
import kotlinx.metadata.internal.metadata.ProtoBuf$Function;
import kotlinx.metadata.internal.metadata.ProtoBuf$Property;
import kotlinx.metadata.internal.metadata.ProtoBuf$Type;
import kotlinx.metadata.internal.metadata.ProtoBuf$TypeAlias;
import kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter;
import kotlinx.metadata.internal.metadata.ProtoBuf$ValueParameter;

/* JADX INFO: compiled from: MetadataExtensions.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fJ \u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&J \u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H&J \u0010\u000f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u0006H&J \u0010\u0012\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u0006H&J \u0010\u0015\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u0006H&J \u0010\u0018\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\u0006H&J \u0010\u001b\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\u0006H&J \u0010\u001e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u001d2\u0006\u0010\u0007\u001a\u00020\u0006H&¨\u0006 "}, d2 = {"Lkotlinx/metadata/impl/extensions/MetadataExtensions;", "", "Lkotlinx/metadata/KmClassVisitor;", "v", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Class;", "proto", "Lkotlinx/metadata/impl/ReadContext;", "c", "", "readClassExtensions", "Lkotlinx/metadata/KmFunctionVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Function;", "readFunctionExtensions", "Lkotlinx/metadata/KmPropertyVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Property;", "readPropertyExtensions", "Lkotlinx/metadata/KmConstructorVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Constructor;", "readConstructorExtensions", "Lkotlinx/metadata/KmTypeParameterVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$TypeParameter;", "readTypeParameterExtensions", "Lkotlinx/metadata/KmTypeVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Type;", "readTypeExtensions", "Lkotlinx/metadata/KmTypeAliasVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$TypeAlias;", "readTypeAliasExtensions", "Lkotlinx/metadata/KmValueParameterVisitor;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$ValueParameter;", "readValueParameterExtensions", "Companion", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
public interface MetadataExtensions {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    void readClassExtensions(KmClassVisitor v, ProtoBuf$Class proto, ReadContext c);

    void readConstructorExtensions(KmConstructorVisitor v, ProtoBuf$Constructor proto, ReadContext c);

    void readFunctionExtensions(KmFunctionVisitor v, ProtoBuf$Function proto, ReadContext c);

    void readPropertyExtensions(KmPropertyVisitor v, ProtoBuf$Property proto, ReadContext c);

    void readTypeAliasExtensions(KmTypeAliasVisitor v, ProtoBuf$TypeAlias proto, ReadContext c);

    void readTypeExtensions(KmTypeVisitor v, ProtoBuf$Type proto, ReadContext c);

    void readTypeParameterExtensions(KmTypeParameterVisitor v, ProtoBuf$TypeParameter proto, ReadContext c);

    void readValueParameterExtensions(KmValueParameterVisitor v, ProtoBuf$ValueParameter proto, ReadContext c);

    /* JADX INFO: compiled from: MetadataExtensions.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lkotlinx/metadata/impl/extensions/MetadataExtensions$Companion;", "", "()V", "INSTANCES", "", "Lkotlinx/metadata/impl/extensions/MetadataExtensions;", "getINSTANCES", "()Ljava/util/List;", "INSTANCES$delegate", "Lkotlin/Lazy;", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* JADX INFO: renamed from: INSTANCES$delegate, reason: from kotlin metadata */
        private static final Lazy<List<MetadataExtensions>> INSTANCES = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends MetadataExtensions>>() { // from class: kotlinx.metadata.impl.extensions.MetadataExtensions$Companion$INSTANCES$2
            @Override // kotlin.jvm.functions.Function0
            public final List<? extends MetadataExtensions> invoke() {
                ServiceLoader serviceLoaderLoad = ServiceLoader.load(MetadataExtensions.class, MetadataExtensions.class.getClassLoader());
                Intrinsics.checkNotNullExpressionValue(serviceLoaderLoad, "load(MetadataExtensions:…::class.java.classLoader)");
                List<? extends MetadataExtensions> list = CollectionsKt___CollectionsKt.toList(serviceLoaderLoad);
                if (list.isEmpty()) {
                    throw new IllegalStateException("No MetadataExtensions instances found in the classpath. Please ensure that the META-INF/services/ is not stripped from your application and that the Java virtual machine is not running under a security manager".toString());
                }
                return list;
            }
        });

        private Companion() {
        }

        public final List<MetadataExtensions> getINSTANCES() {
            return INSTANCES.getValue();
        }
    }
}
