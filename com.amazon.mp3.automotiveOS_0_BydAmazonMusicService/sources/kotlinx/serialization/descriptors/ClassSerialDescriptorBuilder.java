package kotlinx.serialization.descriptors;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SerialDescriptors.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\r\u001a\u00020\u0002¢\u0006\u0004\b$\u0010%J0\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\n\u001a\u00020\tR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R.\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\b\u0010\u0011\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u00188\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u0013R\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00188\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u0011\u001a\u0004\b\u001f\u0010\u0013R&\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00188\u0000X\u0080\u0004¢\u0006\f\n\u0004\b \u0010\u0011\u001a\u0004\b!\u0010\u0013R \u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0\u00188\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\"\u0010\u0011\u001a\u0004\b#\u0010\u0013¨\u0006&"}, d2 = {"Lkotlinx/serialization/descriptors/ClassSerialDescriptorBuilder;", "", "", "elementName", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "", "", "annotations", "", "isOptional", "", "element", "serialName", "Ljava/lang/String;", "getSerialName", "()Ljava/lang/String;", "Ljava/util/List;", "getAnnotations", "()Ljava/util/List;", "setAnnotations", "(Ljava/util/List;)V", "getAnnotations$annotations", "()V", "", "elementNames", "getElementNames$kotlinx_serialization_core", "", "uniqueNames", "Ljava/util/Set;", "elementDescriptors", "getElementDescriptors$kotlinx_serialization_core", "elementAnnotations", "getElementAnnotations$kotlinx_serialization_core", "elementOptionality", "getElementOptionality$kotlinx_serialization_core", "<init>", "(Ljava/lang/String;)V", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
public final class ClassSerialDescriptorBuilder {
    private List<? extends Annotation> annotations;
    private final List<List<Annotation>> elementAnnotations;
    private final List<SerialDescriptor> elementDescriptors;
    private final List<String> elementNames;
    private final List<Boolean> elementOptionality;
    private final String serialName;
    private final Set<String> uniqueNames;

    public ClassSerialDescriptorBuilder(String serialName) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        this.serialName = serialName;
        this.annotations = CollectionsKt__CollectionsKt.emptyList();
        this.elementNames = new ArrayList();
        this.uniqueNames = new HashSet();
        this.elementDescriptors = new ArrayList();
        this.elementAnnotations = new ArrayList();
        this.elementOptionality = new ArrayList();
    }

    public final List<Annotation> getAnnotations() {
        return this.annotations;
    }

    public final void setAnnotations(List<? extends Annotation> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.annotations = list;
    }

    public final List<String> getElementNames$kotlinx_serialization_core() {
        return this.elementNames;
    }

    public final List<SerialDescriptor> getElementDescriptors$kotlinx_serialization_core() {
        return this.elementDescriptors;
    }

    public final List<List<Annotation>> getElementAnnotations$kotlinx_serialization_core() {
        return this.elementAnnotations;
    }

    public final List<Boolean> getElementOptionality$kotlinx_serialization_core() {
        return this.elementOptionality;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void element$default(ClassSerialDescriptorBuilder classSerialDescriptorBuilder, String str, SerialDescriptor serialDescriptor, List list, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt__CollectionsKt.emptyList();
        }
        if ((i & 8) != 0) {
            z = false;
        }
        classSerialDescriptorBuilder.element(str, serialDescriptor, list, z);
    }

    public final void element(String elementName, SerialDescriptor descriptor, List<? extends Annotation> annotations, boolean isOptional) {
        Intrinsics.checkNotNullParameter(elementName, "elementName");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        if (!this.uniqueNames.add(elementName)) {
            throw new IllegalArgumentException(("Element with name '" + elementName + "' is already registered").toString());
        }
        this.elementNames.add(elementName);
        this.elementDescriptors.add(descriptor);
        this.elementAnnotations.add(annotations);
        this.elementOptionality.add(Boolean.valueOf(isOptional));
    }
}
