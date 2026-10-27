package kotlinx.metadata.impl;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.KmAnnotation;
import kotlinx.metadata.KmAnnotationArgument;
import kotlinx.metadata.internal.metadata.ProtoBuf$Annotation;
import kotlinx.metadata.internal.metadata.deserialization.Flags;
import kotlinx.metadata.internal.metadata.deserialization.NameResolver;

/* JADX INFO: compiled from: readUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u0012\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u0003\u001a\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\f2\u0006\u0010\t\u001a\u00020\u0003¨\u0006\r"}, d2 = {"getClassName", "", "Lkotlinx/metadata/ClassName;", "Lkotlinx/metadata/internal/metadata/deserialization/NameResolver;", "index", "", "readAnnotation", "Lkotlinx/metadata/KmAnnotation;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Annotation;", "strings", "readAnnotationArgument", "Lkotlinx/metadata/KmAnnotationArgument;", "Lkotlinx/metadata/internal/metadata/ProtoBuf$Annotation$Argument$Value;", "kotlinx-metadata"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class ReadUtilsKt {

    /* JADX INFO: compiled from: readUtils.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProtoBuf$Annotation.Argument.Value.Type.values().length];
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.BYTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.SHORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.LONG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.CHAR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.DOUBLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.BOOLEAN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.STRING.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.CLASS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.ENUM.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.ANNOTATION.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[ProtoBuf$Annotation.Argument.Value.Type.ARRAY.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final KmAnnotation readAnnotation(ProtoBuf$Annotation protoBuf$Annotation, NameResolver strings) {
        Intrinsics.checkNotNullParameter(protoBuf$Annotation, "<this>");
        Intrinsics.checkNotNullParameter(strings, "strings");
        String className = getClassName(strings, protoBuf$Annotation.getId());
        List<ProtoBuf$Annotation.Argument> argumentList = protoBuf$Annotation.getArgumentList();
        Intrinsics.checkNotNullExpressionValue(argumentList, "argumentList");
        ArrayList arrayList = new ArrayList();
        for (ProtoBuf$Annotation.Argument argument : argumentList) {
            ProtoBuf$Annotation.Argument.Value value = argument.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "argument.value");
            KmAnnotationArgument annotationArgument = readAnnotationArgument(value, strings);
            Pair pair = annotationArgument != null ? TuplesKt.to(strings.getString(argument.getNameId()), annotationArgument) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return new KmAnnotation(className, MapsKt__MapsKt.toMap(arrayList));
    }

    public static final KmAnnotationArgument readAnnotationArgument(ProtoBuf$Annotation.Argument.Value value, NameResolver strings) {
        Intrinsics.checkNotNullParameter(value, "<this>");
        Intrinsics.checkNotNullParameter(strings, "strings");
        Boolean bool = Flags.IS_UNSIGNED.get(value.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "Flags.IS_UNSIGNED[flags]");
        if (bool.booleanValue()) {
            ProtoBuf$Annotation.Argument.Value.Type type = value.getType();
            int i = type != null ? WhenMappings.$EnumSwitchMapping$0[type.ordinal()] : -1;
            if (i == 1) {
                return new KmAnnotationArgument.UByteValue(UByte.m2828constructorimpl((byte) value.getIntValue()), null);
            }
            if (i == 2) {
                return new KmAnnotationArgument.UShortValue(UShort.m3088constructorimpl((short) value.getIntValue()), null);
            }
            if (i == 3) {
                return new KmAnnotationArgument.UIntValue(UInt.m2904constructorimpl((int) value.getIntValue()), null);
            }
            if (i == 4) {
                return new KmAnnotationArgument.ULongValue(ULong.m2982constructorimpl(value.getIntValue()), null);
            }
            throw new IllegalStateException(("Cannot read value of unsigned type: " + value.getType()).toString());
        }
        ProtoBuf$Annotation.Argument.Value.Type type2 = value.getType();
        switch (type2 != null ? WhenMappings.$EnumSwitchMapping$0[type2.ordinal()] : -1) {
            case -1:
                return null;
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 1:
                return new KmAnnotationArgument.ByteValue((byte) value.getIntValue());
            case 2:
                return new KmAnnotationArgument.ShortValue((short) value.getIntValue());
            case 3:
                return new KmAnnotationArgument.IntValue((int) value.getIntValue());
            case 4:
                return new KmAnnotationArgument.LongValue(value.getIntValue());
            case 5:
                return new KmAnnotationArgument.CharValue((char) value.getIntValue());
            case 6:
                return new KmAnnotationArgument.FloatValue(value.getFloatValue());
            case 7:
                return new KmAnnotationArgument.DoubleValue(value.getDoubleValue());
            case 8:
                return new KmAnnotationArgument.BooleanValue(value.getIntValue() != 0);
            case 9:
                return new KmAnnotationArgument.StringValue(strings.getString(value.getStringValue()));
            case 10:
                return new KmAnnotationArgument.KClassValue(getClassName(strings, value.getClassId()), value.getArrayDimensionCount());
            case 11:
                return new KmAnnotationArgument.EnumValue(getClassName(strings, value.getClassId()), strings.getString(value.getEnumValueId()));
            case 12:
                ProtoBuf$Annotation annotation = value.getAnnotation();
                Intrinsics.checkNotNullExpressionValue(annotation, "annotation");
                return new KmAnnotationArgument.AnnotationValue(readAnnotation(annotation, strings));
            case 13:
                List<ProtoBuf$Annotation.Argument.Value> arrayElementList = value.getArrayElementList();
                Intrinsics.checkNotNullExpressionValue(arrayElementList, "arrayElementList");
                ArrayList arrayList = new ArrayList();
                for (ProtoBuf$Annotation.Argument.Value it : arrayElementList) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    KmAnnotationArgument annotationArgument = readAnnotationArgument(it, strings);
                    if (annotationArgument != null) {
                        arrayList.add(annotationArgument);
                    }
                }
                return new KmAnnotationArgument.ArrayValue(arrayList);
        }
    }

    public static final String getClassName(NameResolver nameResolver, int i) {
        Intrinsics.checkNotNullParameter(nameResolver, "<this>");
        String qualifiedClassName = nameResolver.getQualifiedClassName(i);
        if (!nameResolver.isLocalClassName(i)) {
            return qualifiedClassName;
        }
        return '.' + qualifiedClassName;
    }
}
