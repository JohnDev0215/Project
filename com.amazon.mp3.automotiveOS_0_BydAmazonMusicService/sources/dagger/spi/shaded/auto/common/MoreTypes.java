package dagger.spi.shaded.auto.common;

import com.google.common.base.Equivalence;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleTypeVisitor8;

/* JADX INFO: loaded from: classes7.dex */
public final class MoreTypes {

    private static final class TypeEquivalence extends Equivalence<TypeMirror> {
        private static final TypeEquivalence INSTANCE = new TypeEquivalence();

        public String toString() {
            return "MoreTypes.equivalence()";
        }

        private TypeEquivalence() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(TypeMirror typeMirror, TypeMirror typeMirror2) {
            return MoreTypes.equal(typeMirror, typeMirror2, ImmutableSet.of());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public int doHash(TypeMirror typeMirror) {
            return MoreTypes.hash(typeMirror, ImmutableSet.of());
        }
    }

    public static Equivalence<TypeMirror> equivalence() {
        return TypeEquivalence.INSTANCE;
    }

    private static final class EqualVisitorParam {
        TypeMirror type;
        Set<Object> visiting;

        private EqualVisitorParam() {
        }
    }

    private static final class EqualVisitor extends SimpleTypeVisitor8<Boolean, EqualVisitorParam> {
        private static final EqualVisitor INSTANCE = new EqualVisitor();

        private EqualVisitor() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean equal(TypeMirror typeMirror, TypeMirror typeMirror2, Set<Object> set) {
        if (typeMirror == typeMirror2) {
            return true;
        }
        if (typeMirror == null || typeMirror2 == null) {
            return false;
        }
        if (typeMirror.equals(typeMirror2) && !(typeMirror instanceof ExecutableType)) {
            return true;
        }
        EqualVisitorParam equalVisitorParam = new EqualVisitorParam();
        equalVisitorParam.type = typeMirror2;
        equalVisitorParam.visiting = set;
        return ((Boolean) typeMirror.accept(EqualVisitor.INSTANCE, equalVisitorParam)).booleanValue();
    }

    private static final class HashVisitor extends SimpleTypeVisitor8<Integer, Set<Element>> {
        private static final HashVisitor INSTANCE = new HashVisitor();

        private HashVisitor() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int hash(TypeMirror typeMirror, Set<Element> set) {
        if (typeMirror == null) {
            return 0;
        }
        return ((Integer) typeMirror.accept(HashVisitor.INSTANCE, set)).intValue();
    }

    public static Element asElement(TypeMirror typeMirror) {
        return (Element) typeMirror.accept(AsElementVisitor.INSTANCE, (Object) null);
    }

    private static final class AsElementVisitor extends SimpleTypeVisitor8<Element, Void> {
        private static final AsElementVisitor INSTANCE = new AsElementVisitor();

        private AsElementVisitor() {
        }
    }

    public static TypeElement asTypeElement(TypeMirror typeMirror) {
        return MoreElements.asType(asElement(typeMirror));
    }

    public static ArrayType asArray(TypeMirror typeMirror) {
        return (ArrayType) typeMirror.accept(ArrayTypeVisitor.INSTANCE, (Object) null);
    }

    private static final class ArrayTypeVisitor extends CastingTypeVisitor<ArrayType> {
        private static final ArrayTypeVisitor INSTANCE = new ArrayTypeVisitor();

        ArrayTypeVisitor() {
            super("array");
        }
    }

    public static DeclaredType asDeclared(TypeMirror typeMirror) {
        return (DeclaredType) typeMirror.accept(DeclaredTypeVisitor.INSTANCE, (Object) null);
    }

    private static final class DeclaredTypeVisitor extends CastingTypeVisitor<DeclaredType> {
        private static final DeclaredTypeVisitor INSTANCE = new DeclaredTypeVisitor();

        DeclaredTypeVisitor() {
            super("declared type");
        }
    }

    public static ExecutableType asExecutable(TypeMirror typeMirror) {
        return (ExecutableType) typeMirror.accept(ExecutableTypeVisitor.INSTANCE, (Object) null);
    }

    private static final class ExecutableTypeVisitor extends CastingTypeVisitor<ExecutableType> {
        private static final ExecutableTypeVisitor INSTANCE = new ExecutableTypeVisitor();

        ExecutableTypeVisitor() {
            super("executable type");
        }
    }

    private static abstract class CastingTypeVisitor<T> extends SimpleTypeVisitor8<T, Void> {
        private final String label;

        CastingTypeVisitor(String str) {
            this.label = str;
        }
    }

    private MoreTypes() {
    }
}
