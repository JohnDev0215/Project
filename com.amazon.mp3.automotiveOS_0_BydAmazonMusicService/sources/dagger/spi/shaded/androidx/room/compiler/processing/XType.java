package dagger.spi.shaded.androidx.room.compiler.processing;

import com.squareup.javapoet.TypeName;
import kotlin.Metadata;

/* JADX INFO: compiled from: XType.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&R\u0014\u0010\u0007\u001a\u00020\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000f\u001a\u00020\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "", "", "isError", "Lcom/squareup/javapoet/TypeName;", "getTypeName", "()Lcom/squareup/javapoet/TypeName;", "typeName", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "getNullability", "()Landroidx/room/compiler/processing/XNullability;", "nullability", "Ldagger/spi/shaded/androidx/room/compiler/processing/XRawType;", "getRawType", "()Landroidx/room/compiler/processing/XRawType;", "rawType", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", "getTypeElement", "()Landroidx/room/compiler/processing/XTypeElement;", "typeElement", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public interface XType {
    XNullability getNullability();

    XTypeElement getTypeElement();

    TypeName getTypeName();

    boolean isError();
}
