package dagger.spi.shaded.auto.common;

import dagger.spi.shaded.auto.common.Overrides;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.SimpleElementVisitor8;
import javax.lang.model.util.Types;

/* JADX INFO: loaded from: classes7.dex */
public final class MoreElements {
    public static PackageElement getPackage(Element element) {
        while (element.getKind() != ElementKind.PACKAGE) {
            element = element.getEnclosingElement();
        }
        return (PackageElement) element;
    }

    private static final class TypeElementVisitor extends CastingElementVisitor<TypeElement> {
        private static final TypeElementVisitor INSTANCE = new TypeElementVisitor();

        TypeElementVisitor() {
            super("type element");
        }
    }

    public static boolean isType(Element element) {
        return element.getKind().isClass() || element.getKind().isInterface();
    }

    public static TypeElement asType(Element element) {
        return (TypeElement) element.accept(TypeElementVisitor.INSTANCE, (Object) null);
    }

    public static boolean overrides(ExecutableElement executableElement, ExecutableElement executableElement2, TypeElement typeElement, Types types) {
        return new Overrides.ExplicitOverrides(types).overrides(executableElement, executableElement2, typeElement);
    }

    /* JADX INFO: renamed from: dagger.spi.shaded.auto.common.MoreElements$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$auto$common$Visibility;

        static {
            int[] iArr = new int[Visibility.values().length];
            $SwitchMap$com$google$auto$common$Visibility = iArr;
            try {
                iArr[Visibility.PRIVATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$auto$common$Visibility[Visibility.DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static boolean methodVisibleFromPackage(ExecutableElement executableElement, PackageElement packageElement) {
        int i = AnonymousClass2.$SwitchMap$com$google$auto$common$Visibility[Visibility.ofElement(executableElement).ordinal()];
        if (i == 1) {
            return false;
        }
        if (i != 2) {
            return true;
        }
        return getPackage(executableElement).equals(packageElement);
    }

    private static abstract class CastingElementVisitor<T> extends SimpleElementVisitor8<T, Void> {
        private final String label;

        CastingElementVisitor(String str) {
            this.label = str;
        }
    }

    private MoreElements() {
    }
}
