package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.ArrayList;
import java.util.Set;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: DescriptorRenderer.kt */
/* JADX INFO: loaded from: classes7.dex */
public enum DescriptorRendererModifier {
    VISIBILITY(true),
    MODALITY(true),
    OVERRIDE(true),
    ANNOTATIONS(false),
    INNER(true),
    MEMBER_KIND(true),
    DATA(true),
    INLINE(true),
    EXPECT(true),
    ACTUAL(true),
    CONST(true),
    LATEINIT(true),
    FUN(true),
    VALUE(true);


    @JvmField
    public static final Set<DescriptorRendererModifier> ALL;

    @JvmField
    public static final Set<DescriptorRendererModifier> ALL_EXCEPT_ANNOTATIONS;
    public static final Companion Companion = new Companion(null);
    private final boolean includeByDefault;

    DescriptorRendererModifier(boolean z) {
        this.includeByDefault = z;
    }

    public final boolean getIncludeByDefault() {
        return this.includeByDefault;
    }

    static {
        int i = 0;
        DescriptorRendererModifier[] descriptorRendererModifierArrValues = values();
        ArrayList arrayList = new ArrayList();
        int length = descriptorRendererModifierArrValues.length;
        while (i < length) {
            DescriptorRendererModifier descriptorRendererModifier = descriptorRendererModifierArrValues[i];
            i++;
            if (descriptorRendererModifier.getIncludeByDefault()) {
                arrayList.add(descriptorRendererModifier);
            }
        }
        ALL_EXCEPT_ANNOTATIONS = CollectionsKt___CollectionsKt.toSet(arrayList);
        ALL = ArraysKt___ArraysKt.toSet(values());
    }

    /* JADX INFO: compiled from: DescriptorRenderer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
