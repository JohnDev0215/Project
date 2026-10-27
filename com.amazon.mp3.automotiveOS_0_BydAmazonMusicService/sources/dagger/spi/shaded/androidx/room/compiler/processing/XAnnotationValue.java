package dagger.spi.shaded.androidx.room.compiler.processing;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XAnnotationValue.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0002H&J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0002H&J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\u0002H&J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\u0002H&J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0002H&J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00000\u0013H\u0016R\u0014\u0010\u0017\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u00018&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001d\u001a\u00020\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001eÀ\u0006\u0001"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XAnnotationValue;", "", "", "hasTypeValue", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "asType", "hasAnnotationValue", "Ldagger/spi/shaded/androidx/room/compiler/processing/XAnnotation;", "asAnnotation", "hasEnumValue", "Ldagger/spi/shaded/androidx/room/compiler/processing/XEnumEntry;", "asEnum", "hasStringValue", "", "asString", "hasCharValue", "", "asChar", "hasListValue", "", "asAnnotationValueList", "getName", "()Ljava/lang/String;", "name", "getValue", "()Ljava/lang/Object;", "value", "getValueType", "()Landroidx/room/compiler/processing/XType;", "valueType", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public interface XAnnotationValue {
    String getName();

    Object getValue();

    boolean hasAnnotationValue();

    boolean hasCharValue();

    boolean hasEnumValue();

    boolean hasListValue();

    boolean hasStringValue();

    boolean hasTypeValue();

    default XType asType() {
        Object value = getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.room.compiler.processing.XType");
        return (XType) value;
    }

    default XAnnotation asAnnotation() {
        Object value = getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.room.compiler.processing.XAnnotation");
        return (XAnnotation) value;
    }

    default XEnumEntry asEnum() {
        Object value = getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.room.compiler.processing.XEnumEntry");
        return (XEnumEntry) value;
    }

    default String asString() {
        Object value = getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.String");
        return (String) value;
    }

    default char asChar() {
        Object value = getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Char");
        return ((Character) value).charValue();
    }

    default List<XAnnotationValue> asAnnotationValueList() {
        Object value = getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.collections.List<androidx.room.compiler.processing.XAnnotationValue>");
        return (List) value;
    }
}
