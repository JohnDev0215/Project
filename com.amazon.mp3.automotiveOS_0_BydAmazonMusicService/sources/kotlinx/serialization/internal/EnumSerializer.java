package kotlinx.serialization.internal;

import java.lang.Enum;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: Enums.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\f\b\u0001\u0018\u0000*\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0004H\u0016R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001b\u0010\u001c\u001a\u00020\u00068VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001f"}, d2 = {"Lkotlinx/serialization/internal/EnumSerializer;", "", "T", "Lkotlinx/serialization/KSerializer;", "", "serialName", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "createUnmarkedDescriptor", "Lkotlinx/serialization/encoding/Encoder;", "encoder", "value", "", "serialize", "(Lkotlinx/serialization/encoding/Encoder;Ljava/lang/Enum;)V", "Lkotlinx/serialization/encoding/Decoder;", "decoder", "deserialize", "(Lkotlinx/serialization/encoding/Decoder;)Ljava/lang/Enum;", "toString", "", "values", "[Ljava/lang/Enum;", "overriddenDescriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor$delegate", "Lkotlin/Lazy;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "<init>", "(Ljava/lang/String;[Ljava/lang/Enum;)V", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
@PublishedApi
public final class EnumSerializer<T extends Enum<T>> implements KSerializer<T> {

    /* JADX INFO: renamed from: descriptor$delegate, reason: from kotlin metadata */
    private final Lazy descriptor;
    private SerialDescriptor overriddenDescriptor;
    private final T[] values;

    public EnumSerializer(final String serialName, T[] values) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(values, "values");
        this.values = values;
        this.descriptor = LazyKt__LazyJVMKt.lazy(new Function0<SerialDescriptor>(this) { // from class: kotlinx.serialization.internal.EnumSerializer$descriptor$2
            final /* synthetic */ EnumSerializer<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final SerialDescriptor invoke() {
                SerialDescriptor serialDescriptor = ((EnumSerializer) this.this$0).overriddenDescriptor;
                return serialDescriptor == null ? this.this$0.createUnmarkedDescriptor(serialName) : serialDescriptor;
            }
        });
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return (SerialDescriptor) this.descriptor.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SerialDescriptor createUnmarkedDescriptor(String serialName) {
        EnumDescriptor enumDescriptor = new EnumDescriptor(serialName, this.values.length);
        for (T t : this.values) {
            PluginGeneratedSerialDescriptor.addElement$default(enumDescriptor, t.name(), false, 2, null);
        }
        return enumDescriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, T value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        int iIndexOf = ArraysKt___ArraysKt.indexOf(this.values, value);
        if (iIndexOf == -1) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            sb.append(" is not a valid enum ");
            sb.append(getDescriptor().getSerialName());
            sb.append(", must be one of ");
            String string = Arrays.toString(this.values);
            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
            sb.append(string);
            throw new SerializationException(sb.toString());
        }
        encoder.encodeEnum(getDescriptor(), iIndexOf);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public T deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        int iDecodeEnum = decoder.decodeEnum(getDescriptor());
        boolean z = false;
        if (iDecodeEnum >= 0 && iDecodeEnum < this.values.length) {
            z = true;
        }
        if (!z) {
            throw new SerializationException(iDecodeEnum + " is not among valid " + getDescriptor().getSerialName() + " enum values, values size is " + this.values.length);
        }
        return this.values[iDecodeEnum];
    }

    public String toString() {
        return "kotlinx.serialization.internal.EnumSerializer<" + getDescriptor().getSerialName() + Typography.greater;
    }
}
