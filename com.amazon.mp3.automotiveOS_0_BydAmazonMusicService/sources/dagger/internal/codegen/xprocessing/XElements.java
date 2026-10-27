package dagger.internal.codegen.xprocessing;

import com.google.common.base.Preconditions;
import dagger.spi.shaded.androidx.room.compiler.processing.XElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XElementKt;
import dagger.spi.shaded.androidx.room.compiler.processing.XEnumEntry;
import dagger.spi.shaded.androidx.room.compiler.processing.XExecutableElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XExecutableParameterElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XMethodElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XTypeParameterElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XVariableElement;
import javax.lang.model.util.SimpleTypeVisitor8;

/* JADX INFO: loaded from: classes7.dex */
public final class XElements {
    private static final SimpleTypeVisitor8<String, Void> JVM_DESCRIPTOR_TYPE_VISITOR = new SimpleTypeVisitor8<String, Void>() { // from class: dagger.internal.codegen.xprocessing.XElements.1
    };

    public static String getSimpleName(XElement element) {
        if (XElementKt.isTypeElement(element)) {
            return asTypeElement(element).getName();
        }
        if (XElementKt.isVariableElement(element)) {
            return asVariable(element).getName();
        }
        if (isEnumEntry(element)) {
            return asEnumEntry(element).getName();
        }
        if (XElementKt.isMethod(element)) {
            return asMethod(element).getJvmName();
        }
        if (XElementKt.isConstructor(element)) {
            return "<init>";
        }
        if (isTypeParameter(element)) {
            return asTypeParameter(element).getName();
        }
        throw new AssertionError("No simple name for: " + element);
    }

    public static boolean isTypeParameter(XElement element) {
        return element instanceof XTypeParameterElement;
    }

    public static XTypeParameterElement asTypeParameter(XElement element) {
        return (XTypeParameterElement) element;
    }

    public static boolean isEnumEntry(XElement element) {
        return element instanceof XEnumEntry;
    }

    public static boolean isExecutable(XElement element) {
        return XElementKt.isConstructor(element) || XElementKt.isMethod(element);
    }

    public static XExecutableElement asExecutable(XElement element) {
        Preconditions.checkState(isExecutable(element));
        return (XExecutableElement) element;
    }

    public static XTypeElement asTypeElement(XElement element) {
        Preconditions.checkState(XElementKt.isTypeElement(element));
        return (XTypeElement) element;
    }

    public static XExecutableParameterElement asMethodParameter(XElement element) {
        Preconditions.checkState(XElementKt.isMethodParameter(element));
        return (XExecutableParameterElement) element;
    }

    public static XEnumEntry asEnumEntry(XElement element) {
        return (XEnumEntry) element;
    }

    public static XVariableElement asVariable(XElement element) {
        Preconditions.checkState(XElementKt.isVariableElement(element));
        return (XVariableElement) element;
    }

    public static XMethodElement asMethod(XElement element) {
        Preconditions.checkState(XElementKt.isMethod(element));
        return (XMethodElement) element;
    }

    private XElements() {
    }
}
