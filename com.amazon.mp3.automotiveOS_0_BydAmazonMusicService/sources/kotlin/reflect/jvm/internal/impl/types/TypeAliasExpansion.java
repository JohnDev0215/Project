package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

/* JADX INFO: compiled from: TypeAliasExpansion.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class TypeAliasExpansion {
    public static final Companion Companion = new Companion(null);
    private final List<TypeProjection> arguments;
    private final TypeAliasDescriptor descriptor;
    private final Map<TypeParameterDescriptor, TypeProjection> mapping;
    private final TypeAliasExpansion parent;

    public /* synthetic */ TypeAliasExpansion(TypeAliasExpansion typeAliasExpansion, TypeAliasDescriptor typeAliasDescriptor, List list, Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeAliasExpansion, typeAliasDescriptor, list, map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TypeAliasExpansion(TypeAliasExpansion typeAliasExpansion, TypeAliasDescriptor typeAliasDescriptor, List<? extends TypeProjection> list, Map<TypeParameterDescriptor, ? extends TypeProjection> map) {
        this.parent = typeAliasExpansion;
        this.descriptor = typeAliasDescriptor;
        this.arguments = list;
        this.mapping = map;
    }

    public final TypeAliasDescriptor getDescriptor() {
        return this.descriptor;
    }

    public final List<TypeProjection> getArguments() {
        return this.arguments;
    }

    public final TypeProjection getReplacement(TypeConstructor constructor) {
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = constructor.mo4084getDeclarationDescriptor();
        if (classifierDescriptorMo4084getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return this.mapping.get(classifierDescriptorMo4084getDeclarationDescriptor);
        }
        return null;
    }

    public final boolean isRecursion(TypeAliasDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (!Intrinsics.areEqual(this.descriptor, descriptor)) {
            TypeAliasExpansion typeAliasExpansion = this.parent;
            if (!(typeAliasExpansion == null ? false : typeAliasExpansion.isRecursion(descriptor))) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: compiled from: TypeAliasExpansion.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TypeAliasExpansion create(TypeAliasExpansion typeAliasExpansion, TypeAliasDescriptor typeAliasDescriptor, List<? extends TypeProjection> arguments) {
            Intrinsics.checkNotNullParameter(typeAliasDescriptor, "typeAliasDescriptor");
            Intrinsics.checkNotNullParameter(arguments, "arguments");
            List<TypeParameterDescriptor> parameters = typeAliasDescriptor.getTypeConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "typeAliasDescriptor.typeConstructor.parameters");
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters, 10));
            Iterator<T> it = parameters.iterator();
            while (it.hasNext()) {
                arrayList.add(((TypeParameterDescriptor) it.next()).getOriginal());
            }
            return new TypeAliasExpansion(typeAliasExpansion, typeAliasDescriptor, arguments, MapsKt__MapsKt.toMap(CollectionsKt___CollectionsKt.zip(arrayList, arguments)), null);
        }
    }
}
