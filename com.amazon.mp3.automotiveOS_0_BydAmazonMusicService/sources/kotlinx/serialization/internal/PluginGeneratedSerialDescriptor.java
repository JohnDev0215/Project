package kotlinx.serialization.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;

/* JADX INFO: compiled from: PluginGeneratedSerialDescriptor.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0018\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0006\b\u0011\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0019\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d\u0012\u0006\u0010 \u001a\u00020\u0005¢\u0006\u0004\bJ\u0010KJ\u0014\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0002J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\bJ\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0005H\u0016J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\f\u001a\u00020\u0005H\u0016J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0005H\u0016J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0004H\u0016J\u0013\u0010\u0016\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0005H\u0016J\b\u0010\u0018\u001a\u00020\u0004H\u0016R\u001a\u0010\u0019\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010!R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\"\u0010)\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010(0%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u001e\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\"\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u00101R%\u00107\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u00106R!\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00010%8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b8\u00104\u001a\u0004\b9\u0010:R\u001b\u0010>\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b<\u00104\u001a\u0004\b=\u0010#R\u0014\u0010B\u001a\u00020?8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b@\u0010AR\u001a\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010DR\u001a\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00040F8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010H¨\u0006L"}, d2 = {"Lkotlinx/serialization/internal/PluginGeneratedSerialDescriptor;", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "Lkotlinx/serialization/internal/CachedNames;", "", "", "", "buildIndices", "name", "", "isOptional", "", "addElement", "index", "getElementDescriptor", "isElementOptional", "", "", "getElementAnnotations", "getElementName", "getElementIndex", "", "other", "equals", "hashCode", "toString", "serialName", "Ljava/lang/String;", "getSerialName", "()Ljava/lang/String;", "Lkotlinx/serialization/internal/GeneratedSerializer;", "generatedSerializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "elementsCount", "I", "getElementsCount", "()I", "added", "", "names", "[Ljava/lang/String;", "", "propertiesAnnotations", "[Ljava/util/List;", "classAnnotations", "Ljava/util/List;", "", "elementsOptionality", "[Z", "indices", "Ljava/util/Map;", "Lkotlinx/serialization/KSerializer;", "childSerializers$delegate", "Lkotlin/Lazy;", "getChildSerializers", "()[Lkotlinx/serialization/KSerializer;", "childSerializers", "typeParameterDescriptors$delegate", "getTypeParameterDescriptors$kotlinx_serialization_core", "()[Lkotlinx/serialization/descriptors/SerialDescriptor;", "typeParameterDescriptors", "_hashCode$delegate", "get_hashCode", "_hashCode", "Lkotlinx/serialization/descriptors/SerialKind;", "getKind", "()Lkotlinx/serialization/descriptors/SerialKind;", "kind", "getAnnotations", "()Ljava/util/List;", "annotations", "", "getSerialNames", "()Ljava/util/Set;", "serialNames", "<init>", "(Ljava/lang/String;Lkotlinx/serialization/internal/GeneratedSerializer;I)V", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
@PublishedApi
public class PluginGeneratedSerialDescriptor implements SerialDescriptor, CachedNames {

    /* JADX INFO: renamed from: _hashCode$delegate, reason: from kotlin metadata */
    private final Lazy _hashCode;
    private int added;

    /* JADX INFO: renamed from: childSerializers$delegate, reason: from kotlin metadata */
    private final Lazy childSerializers;
    private List<Annotation> classAnnotations;
    private final int elementsCount;
    private final boolean[] elementsOptionality;
    private final GeneratedSerializer<?> generatedSerializer;
    private Map<String, Integer> indices;
    private final String[] names;
    private final List<Annotation>[] propertiesAnnotations;
    private final String serialName;

    /* JADX INFO: renamed from: typeParameterDescriptors$delegate, reason: from kotlin metadata */
    private final Lazy typeParameterDescriptors;

    public PluginGeneratedSerialDescriptor(String serialName, GeneratedSerializer<?> generatedSerializer, int i) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        this.serialName = serialName;
        this.generatedSerializer = generatedSerializer;
        this.elementsCount = i;
        this.added = -1;
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = "[UNINITIALIZED]";
        }
        this.names = strArr;
        int i3 = this.elementsCount;
        this.propertiesAnnotations = new List[i3];
        this.elementsOptionality = new boolean[i3];
        this.indices = MapsKt__MapsKt.emptyMap();
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this.childSerializers = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<KSerializer<?>[]>() { // from class: kotlinx.serialization.internal.PluginGeneratedSerialDescriptor$childSerializers$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final KSerializer<?>[] invoke() {
                KSerializer<?>[] kSerializerArrChildSerializers;
                GeneratedSerializer generatedSerializer2 = this.this$0.generatedSerializer;
                return (generatedSerializer2 == null || (kSerializerArrChildSerializers = generatedSerializer2.childSerializers()) == null) ? PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY : kSerializerArrChildSerializers;
            }
        });
        this.typeParameterDescriptors = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<SerialDescriptor[]>() { // from class: kotlinx.serialization.internal.PluginGeneratedSerialDescriptor$typeParameterDescriptors$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final SerialDescriptor[] invoke() {
                ArrayList arrayList;
                KSerializer<?>[] kSerializerArrTypeParametersSerializers;
                GeneratedSerializer generatedSerializer2 = this.this$0.generatedSerializer;
                if (generatedSerializer2 == null || (kSerializerArrTypeParametersSerializers = generatedSerializer2.typeParametersSerializers()) == null) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(kSerializerArrTypeParametersSerializers.length);
                    for (KSerializer<?> kSerializer : kSerializerArrTypeParametersSerializers) {
                        arrayList.add(kSerializer.getDescriptor());
                    }
                }
                return Platform_commonKt.compactArray(arrayList);
            }
        });
        this._hashCode = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<Integer>() { // from class: kotlinx.serialization.internal.PluginGeneratedSerialDescriptor$_hashCode$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = this.this$0;
                return Integer.valueOf(PluginGeneratedSerialDescriptorKt.hashCodeImpl(pluginGeneratedSerialDescriptor, pluginGeneratedSerialDescriptor.getTypeParameterDescriptors$kotlinx_serialization_core()));
            }
        });
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    /* JADX INFO: renamed from: isInline */
    public boolean getIsInline() {
        return SerialDescriptor.DefaultImpls.isInline(this);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isNullable() {
        return SerialDescriptor.DefaultImpls.isNullable(this);
    }

    public /* synthetic */ PluginGeneratedSerialDescriptor(String str, GeneratedSerializer generatedSerializer, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? null : generatedSerializer, i);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getSerialName() {
        return this.serialName;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public final int getElementsCount() {
        return this.elementsCount;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialKind getKind() {
        return StructureKind.CLASS.INSTANCE;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public List<Annotation> getAnnotations() {
        List<Annotation> list = this.classAnnotations;
        return list == null ? CollectionsKt__CollectionsKt.emptyList() : list;
    }

    @Override // kotlinx.serialization.internal.CachedNames
    public Set<String> getSerialNames() {
        return this.indices.keySet();
    }

    private final KSerializer<?>[] getChildSerializers() {
        return (KSerializer[]) this.childSerializers.getValue();
    }

    public final SerialDescriptor[] getTypeParameterDescriptors$kotlinx_serialization_core() {
        return (SerialDescriptor[]) this.typeParameterDescriptors.getValue();
    }

    private final int get_hashCode() {
        return ((Number) this._hashCode.getValue()).intValue();
    }

    public static /* synthetic */ void addElement$default(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addElement");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        pluginGeneratedSerialDescriptor.addElement(str, z);
    }

    public final void addElement(String name, boolean isOptional) {
        Intrinsics.checkNotNullParameter(name, "name");
        String[] strArr = this.names;
        int i = this.added + 1;
        this.added = i;
        strArr[i] = name;
        this.elementsOptionality[i] = isOptional;
        this.propertiesAnnotations[i] = null;
        if (i == this.elementsCount - 1) {
            this.indices = buildIndices();
        }
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public SerialDescriptor getElementDescriptor(int index) {
        return getChildSerializers()[index].getDescriptor();
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public boolean isElementOptional(int index) {
        return this.elementsOptionality[index];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public List<Annotation> getElementAnnotations(int index) {
        List<Annotation> list = this.propertiesAnnotations[index];
        return list == null ? CollectionsKt__CollectionsKt.emptyList() : list;
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getElementName(int index) {
        return this.names[index];
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public int getElementIndex(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Integer num = this.indices.get(name);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    private final Map<String, Integer> buildIndices() {
        HashMap map = new HashMap();
        int length = this.names.length;
        for (int i = 0; i < length; i++) {
            map.put(this.names[i], Integer.valueOf(i));
        }
        return map;
    }

    public int hashCode() {
        return get_hashCode();
    }

    public String toString() {
        return CollectionsKt___CollectionsKt.joinToString$default(RangesKt___RangesKt.until(0, this.elementsCount), ", ", getSerialName() + '(', ")", 0, null, new Function1<Integer, CharSequence>() { // from class: kotlinx.serialization.internal.PluginGeneratedSerialDescriptor.toString.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final CharSequence invoke(int i) {
                return PluginGeneratedSerialDescriptor.this.getElementName(i) + ": " + PluginGeneratedSerialDescriptor.this.getElementDescriptor(i).getSerialName();
            }
        }, 24, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof PluginGeneratedSerialDescriptor) {
            SerialDescriptor serialDescriptor = (SerialDescriptor) other;
            if (Intrinsics.areEqual(getSerialName(), serialDescriptor.getSerialName()) && Arrays.equals(getTypeParameterDescriptors$kotlinx_serialization_core(), ((PluginGeneratedSerialDescriptor) other).getTypeParameterDescriptors$kotlinx_serialization_core()) && getElementsCount() == serialDescriptor.getElementsCount()) {
                int elementsCount = getElementsCount();
                for (int i = 0; i < elementsCount; i++) {
                    if (Intrinsics.areEqual(getElementDescriptor(i).getSerialName(), serialDescriptor.getElementDescriptor(i).getSerialName()) && Intrinsics.areEqual(getElementDescriptor(i).getKind(), serialDescriptor.getElementDescriptor(i).getKind())) {
                    }
                }
                return true;
            }
        }
        return false;
    }
}
