package dagger.spi.shaded.androidx.room.compiler.processing;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u001d\u0010\u0003\u001a\u00020\u0001*\u00020\u0000\u0082\u0002\u0010\n\u000e\b\u0000\u0012\u0002\u0018\u0000\u001a\u0006\u0010\u0000\"\u00020\u0002\u001a\u001d\u0010\u0005\u001a\u00020\u0001*\u00020\u0000\u0082\u0002\u0010\n\u000e\b\u0000\u0012\u0002\u0018\u0000\u001a\u0006\u0010\u0000\"\u00020\u0004\u001a\u001d\u0010\u0007\u001a\u00020\u0001*\u00020\u0000\u0082\u0002\u0010\n\u000e\b\u0000\u0012\u0002\u0018\u0000\u001a\u0006\u0010\u0000\"\u00020\u0006\u001a\u001d\u0010\t\u001a\u00020\u0001*\u00020\u0000\u0082\u0002\u0010\n\u000e\b\u0000\u0012\u0002\u0018\u0000\u001a\u0006\u0010\u0000\"\u00020\b\u001a\u001d\u0010\u000b\u001a\u00020\u0001*\u00020\u0000\u0082\u0002\u0010\n\u000e\b\u0000\u0012\u0002\u0018\u0000\u001a\u0006\u0010\u0000\"\u00020\n\u001a\u001d\u0010\r\u001a\u00020\u0001*\u00020\u0000\u0082\u0002\u0010\n\u000e\b\u0000\u0012\u0002\u0018\u0000\u001a\u0006\u0010\u0000\"\u00020\f¨\u0006\u000e"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XElement;", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", "isTypeElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/XVariableElement;", "isVariableElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/XFieldElement;", "isField", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMethodElement;", "isMethod", "Ldagger/spi/shaded/androidx/room/compiler/processing/XExecutableParameterElement;", "isMethodParameter", "", "isConstructor", "room-compiler-processing"}, k = 2, mv = {1, 7, 1})
public final class XElementKt {
    public static final boolean isConstructor(XElement xElement) {
        Intrinsics.checkNotNullParameter(xElement, "<this>");
        return false;
    }

    public static final boolean isTypeElement(XElement xElement) {
        Intrinsics.checkNotNullParameter(xElement, "<this>");
        return xElement instanceof XTypeElement;
    }

    public static final boolean isVariableElement(XElement xElement) {
        Intrinsics.checkNotNullParameter(xElement, "<this>");
        return xElement instanceof XVariableElement;
    }

    public static final boolean isField(XElement xElement) {
        Intrinsics.checkNotNullParameter(xElement, "<this>");
        return xElement instanceof XFieldElement;
    }

    public static final boolean isMethod(XElement xElement) {
        Intrinsics.checkNotNullParameter(xElement, "<this>");
        return xElement instanceof XMethodElement;
    }

    public static final boolean isMethodParameter(XElement xElement) {
        Intrinsics.checkNotNullParameter(xElement, "<this>");
        return xElement instanceof XExecutableParameterElement;
    }
}
