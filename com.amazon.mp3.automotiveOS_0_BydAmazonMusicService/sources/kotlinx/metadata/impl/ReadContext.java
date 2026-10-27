package kotlinx.metadata.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.impl.extensions.MetadataExtensions;
import kotlinx.metadata.internal.metadata.ProtoBuf$TypeParameter;
import kotlinx.metadata.internal.metadata.deserialization.NameResolver;
import kotlinx.metadata.internal.metadata.deserialization.TypeTable;
import kotlinx.metadata.internal.metadata.deserialization.VersionRequirementTable;

/* JADX INFO: compiled from: readers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0000\u0012\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0004\b*\u0010+J\u0011\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0086\u0002J\u0012\u0010\u0007\u001a\u00060\u0004j\u0002`\u00062\u0006\u0010\u0003\u001a\u00020\u0002J\u0017\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0014\u0010\u000e\u001a\u00020\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001a\u001a\u00020\u00198\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u001d\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\u000b8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R \u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R \u0010(\u001a\b\u0012\u0004\u0012\u00020'0\u000b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b(\u0010!\u001a\u0004\b)\u0010#¨\u0006,"}, d2 = {"Lkotlinx/metadata/impl/ReadContext;", "", "", "index", "", "get", "Lkotlinx/metadata/ClassName;", "className", "name", "getTypeParameterId", "(I)Ljava/lang/Integer;", "", "Lkotlinx/metadata/internal/metadata/ProtoBuf$TypeParameter;", "typeParameters", "withTypeParameters", "Lkotlinx/metadata/internal/metadata/deserialization/NameResolver;", "strings", "Lkotlinx/metadata/internal/metadata/deserialization/NameResolver;", "getStrings", "()Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "Lkotlinx/metadata/internal/metadata/deserialization/TypeTable;", "types", "Lkotlinx/metadata/internal/metadata/deserialization/TypeTable;", "getTypes", "()Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirementTable;", "versionRequirements", "Lkotlinx/metadata/internal/metadata/deserialization/VersionRequirementTable;", "getVersionRequirements$kotlinx_metadata", "()Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirementTable;", "parent", "Lkotlinx/metadata/impl/ReadContext;", "contextExtensions", "Ljava/util/List;", "getContextExtensions", "()Ljava/util/List;", "", "typeParameterNameToId", "Ljava/util/Map;", "Lkotlinx/metadata/impl/extensions/MetadataExtensions;", "extensions", "getExtensions$kotlinx_metadata", "<init>", "(Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirementTable;Lkotlinx/metadata/impl/ReadContext;Ljava/util/List;)V", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
public final class ReadContext {
    private final List<Object> contextExtensions;
    private final List<MetadataExtensions> extensions;
    private final ReadContext parent;
    private final NameResolver strings;
    private final Map<Integer, Integer> typeParameterNameToId;
    private final TypeTable types;
    private final VersionRequirementTable versionRequirements;

    public ReadContext(NameResolver strings, TypeTable types, VersionRequirementTable versionRequirements, ReadContext readContext, List<Object> contextExtensions) {
        Intrinsics.checkNotNullParameter(strings, "strings");
        Intrinsics.checkNotNullParameter(types, "types");
        Intrinsics.checkNotNullParameter(versionRequirements, "versionRequirements");
        Intrinsics.checkNotNullParameter(contextExtensions, "contextExtensions");
        this.strings = strings;
        this.types = types;
        this.versionRequirements = versionRequirements;
        this.parent = readContext;
        this.contextExtensions = contextExtensions;
        this.typeParameterNameToId = new LinkedHashMap();
        this.extensions = MetadataExtensions.INSTANCE.getINSTANCES();
    }

    public final NameResolver getStrings() {
        return this.strings;
    }

    public final TypeTable getTypes() {
        return this.types;
    }

    /* JADX INFO: renamed from: getVersionRequirements$kotlinx_metadata, reason: from getter */
    public final VersionRequirementTable getVersionRequirements() {
        return this.versionRequirements;
    }

    public /* synthetic */ ReadContext(NameResolver nameResolver, TypeTable typeTable, VersionRequirementTable versionRequirementTable, ReadContext readContext, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(nameResolver, typeTable, versionRequirementTable, (i & 8) != 0 ? null : readContext, (i & 16) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }

    public final List<MetadataExtensions> getExtensions$kotlinx_metadata() {
        return this.extensions;
    }

    public final String get(int index) {
        return this.strings.getString(index);
    }

    public final String className(int index) {
        return ReadUtilsKt.getClassName(this.strings, index);
    }

    public final Integer getTypeParameterId(int name) {
        Integer num = this.typeParameterNameToId.get(Integer.valueOf(name));
        if (num != null) {
            return num;
        }
        ReadContext readContext = this.parent;
        if (readContext != null) {
            return readContext.getTypeParameterId(name);
        }
        return null;
    }

    public final ReadContext withTypeParameters(List<ProtoBuf$TypeParameter> typeParameters) {
        Intrinsics.checkNotNullParameter(typeParameters, "typeParameters");
        ReadContext readContext = new ReadContext(this.strings, this.types, this.versionRequirements, this, this.contextExtensions);
        for (ProtoBuf$TypeParameter protoBuf$TypeParameter : typeParameters) {
            readContext.typeParameterNameToId.put(Integer.valueOf(protoBuf$TypeParameter.getName()), Integer.valueOf(protoBuf$TypeParameter.getId()));
        }
        return readContext;
    }
}
