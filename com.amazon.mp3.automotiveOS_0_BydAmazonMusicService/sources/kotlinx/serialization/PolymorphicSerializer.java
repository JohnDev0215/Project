package kotlinx.serialization;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KClass;
import kotlin.text.Typography;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.ContextAwareKt;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;

/* JADX INFO: compiled from: PolymorphicSerializer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0015\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0015\u0010\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001b\u0010\u0014\u001a\u00020\u000f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lkotlinx/serialization/PolymorphicSerializer;", "", "T", "Lkotlinx/serialization/internal/AbstractPolymorphicSerializer;", "", "toString", "Lkotlin/reflect/KClass;", "baseClass", "Lkotlin/reflect/KClass;", "getBaseClass", "()Lkotlin/reflect/KClass;", "", "", "_annotations", "Ljava/util/List;", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor$delegate", "Lkotlin/Lazy;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "<init>", "(Lkotlin/reflect/KClass;)V", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
public final class PolymorphicSerializer<T> extends AbstractPolymorphicSerializer<T> {
    private List<? extends Annotation> _annotations;
    private final KClass<T> baseClass;

    /* JADX INFO: renamed from: descriptor$delegate, reason: from kotlin metadata */
    private final Lazy descriptor;

    public PolymorphicSerializer(KClass<T> baseClass) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        this.baseClass = baseClass;
        this._annotations = CollectionsKt__CollectionsKt.emptyList();
        this.descriptor = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<SerialDescriptor>(this) { // from class: kotlinx.serialization.PolymorphicSerializer$descriptor$2
            final /* synthetic */ PolymorphicSerializer<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final SerialDescriptor invoke() {
                final PolymorphicSerializer<T> polymorphicSerializer = this.this$0;
                return ContextAwareKt.withContext(SerialDescriptorsKt.buildSerialDescriptor("kotlinx.serialization.Polymorphic", PolymorphicKind.OPEN.INSTANCE, new SerialDescriptor[0], new Function1<ClassSerialDescriptorBuilder, Unit>() { // from class: kotlinx.serialization.PolymorphicSerializer$descriptor$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
                        invoke2(classSerialDescriptorBuilder);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ClassSerialDescriptorBuilder buildSerialDescriptor) {
                        Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
                        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE).getDescriptor(), null, false, 12, null);
                        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "value", SerialDescriptorsKt.buildSerialDescriptor$default("kotlinx.serialization.Polymorphic<" + polymorphicSerializer.getBaseClass().getSimpleName() + Typography.greater, SerialKind.CONTEXTUAL.INSTANCE, new SerialDescriptor[0], null, 8, null), null, false, 12, null);
                        buildSerialDescriptor.setAnnotations(((PolymorphicSerializer) polymorphicSerializer)._annotations);
                    }
                }), this.this$0.getBaseClass());
            }
        });
    }

    @Override // kotlinx.serialization.internal.AbstractPolymorphicSerializer
    public KClass<T> getBaseClass() {
        return this.baseClass;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return (SerialDescriptor) this.descriptor.getValue();
    }

    public String toString() {
        return "kotlinx.serialization.PolymorphicSerializer(baseClass: " + getBaseClass() + ')';
    }
}
