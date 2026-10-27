package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class ConstantValueFactory {

    @NotNull
    public static final ConstantValueFactory INSTANCE = new ConstantValueFactory();

    private ConstantValueFactory() {
    }

    private final ArrayValue createArrayValue(List<?> list, final PrimitiveType primitiveType) {
        List list2 = CollectionsKt.toList(list);
        ArrayList arrayList = new ArrayList();
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            ConstantValue<?> constantValueCreateConstantValue = createConstantValue(it.next());
            if (constantValueCreateConstantValue != null) {
                arrayList.add(constantValueCreateConstantValue);
            }
        }
        return new ArrayValue(arrayList, new Function1<ModuleDescriptor, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory.createArrayValue.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KotlinType invoke(@NotNull ModuleDescriptor module) {
                Intrinsics.checkNotNullParameter(module, "module");
                SimpleType primitiveArrayKotlinType = module.getBuiltIns().getPrimitiveArrayKotlinType(primitiveType);
                Intrinsics.checkNotNullExpressionValue(primitiveArrayKotlinType, "module.builtIns.getPrimitiveArrayKotlinType(componentType)");
                return primitiveArrayKotlinType;
            }
        });
    }

    @NotNull
    public final ArrayValue createArrayValue(@NotNull List<? extends ConstantValue<?>> value, @NotNull final KotlinType type) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(type, "type");
        return new ArrayValue(value, new Function1<ModuleDescriptor, KotlinType>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory.createArrayValue.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KotlinType invoke(@NotNull ModuleDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return type;
            }
        });
    }

    @Nullable
    public final ConstantValue<?> createConstantValue(@Nullable Object obj) {
        List<?> list;
        PrimitiveType primitiveType;
        if (obj instanceof Byte) {
            return new ByteValue(((Number) obj).byteValue());
        }
        if (obj instanceof Short) {
            return new ShortValue(((Number) obj).shortValue());
        }
        if (obj instanceof Integer) {
            return new IntValue(((Number) obj).intValue());
        }
        if (obj instanceof Long) {
            return new LongValue(((Number) obj).longValue());
        }
        if (obj instanceof Character) {
            return new CharValue(((Character) obj).charValue());
        }
        if (obj instanceof Float) {
            return new FloatValue(((Number) obj).floatValue());
        }
        if (obj instanceof Double) {
            return new DoubleValue(((Number) obj).doubleValue());
        }
        if (obj instanceof Boolean) {
            return new BooleanValue(((Boolean) obj).booleanValue());
        }
        if (obj instanceof String) {
            return new StringValue((String) obj);
        }
        if (obj instanceof byte[]) {
            list = ArraysKt.toList((byte[]) obj);
            primitiveType = PrimitiveType.BYTE;
        } else if (obj instanceof short[]) {
            list = ArraysKt.toList((short[]) obj);
            primitiveType = PrimitiveType.SHORT;
        } else if (obj instanceof int[]) {
            list = ArraysKt.toList((int[]) obj);
            primitiveType = PrimitiveType.INT;
        } else if (obj instanceof long[]) {
            list = ArraysKt.toList((long[]) obj);
            primitiveType = PrimitiveType.LONG;
        } else if (obj instanceof char[]) {
            list = ArraysKt.toList((char[]) obj);
            primitiveType = PrimitiveType.CHAR;
        } else if (obj instanceof float[]) {
            list = ArraysKt.toList((float[]) obj);
            primitiveType = PrimitiveType.FLOAT;
        } else if (obj instanceof double[]) {
            list = ArraysKt.toList((double[]) obj);
            primitiveType = PrimitiveType.DOUBLE;
        } else {
            if (!(obj instanceof boolean[])) {
                if (obj == null) {
                    return new NullValue();
                }
                return null;
            }
            list = ArraysKt.toList((boolean[]) obj);
            primitiveType = PrimitiveType.BOOLEAN;
        }
        return createArrayValue(list, primitiveType);
    }
}
