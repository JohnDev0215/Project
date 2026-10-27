package dagger.spi.shaded.auto.common;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import dagger.spi.shaded.auto.common.Overrides;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.SimpleTypeVisitor8;
import javax.lang.model.util.Types;

/* JADX INFO: loaded from: classes7.dex */
abstract class Overrides {
    Overrides() {
    }

    static class ExplicitOverrides extends Overrides {
        private final Types typeUtils;

        ExplicitOverrides(Types types) {
            this.typeUtils = types;
        }

        public boolean overrides(ExecutableElement executableElement, ExecutableElement executableElement2, TypeElement typeElement) {
            if (!executableElement.getSimpleName().equals(executableElement2.getSimpleName()) || executableElement.getEnclosingElement().equals(executableElement2.getEnclosingElement()) || executableElement2.getModifiers().contains(Modifier.STATIC)) {
                return false;
            }
            Visibility visibilityOfElement = Visibility.ofElement(executableElement2);
            Visibility visibilityOfElement2 = Visibility.ofElement(executableElement);
            if (visibilityOfElement.equals(Visibility.PRIVATE) || visibilityOfElement2.compareTo(visibilityOfElement) < 0 || !isSubsignature(executableElement, executableElement2, typeElement) || !MoreElements.methodVisibleFromPackage(executableElement2, MoreElements.getPackage(executableElement)) || !MoreElements.isType(executableElement2.getEnclosingElement())) {
                return false;
            }
            TypeElement typeElementAsType = MoreElements.asType(executableElement2.getEnclosingElement());
            Types types = this.typeUtils;
            if (!types.isSubtype(types.erasure(typeElement.asType()), this.typeUtils.erasure(typeElementAsType.asType()))) {
                return false;
            }
            if (typeElement.getKind().isClass()) {
                if (typeElementAsType.getKind().isClass()) {
                    ExecutableElement executableElementMethodFromSuperclasses = methodFromSuperclasses(typeElement, executableElement2);
                    return (executableElementMethodFromSuperclasses == null || executableElement2.getEnclosingElement().equals(executableElementMethodFromSuperclasses.getEnclosingElement())) ? false : true;
                }
                if (!typeElementAsType.getKind().isInterface()) {
                    return false;
                }
                if (!executableElement.getModifiers().contains(Modifier.ABSTRACT)) {
                    return true;
                }
                ExecutableElement executableElementMethodFromSuperinterfaces = methodFromSuperinterfaces(typeElement, executableElement2);
                return (executableElementMethodFromSuperinterfaces == null || executableElement2.getEnclosingElement().equals(executableElementMethodFromSuperinterfaces.getEnclosingElement())) ? false : true;
            }
            TypeElement typeElementAsType2 = MoreElements.asType(executableElement.getEnclosingElement());
            if (!typeElement.getKind().isInterface()) {
                return false;
            }
            Types types2 = this.typeUtils;
            return types2.isSubtype(types2.erasure(typeElementAsType2.asType()), this.typeUtils.erasure(typeElementAsType.asType()));
        }

        private boolean isSubsignature(ExecutableElement executableElement, ExecutableElement executableElement2, TypeElement typeElement) {
            DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(typeElement.asType());
            try {
                return this.typeUtils.isSubsignature(MoreTypes.asExecutable(this.typeUtils.asMemberOf(declaredTypeAsDeclared, executableElement)), MoreTypes.asExecutable(this.typeUtils.asMemberOf(declaredTypeAsDeclared, executableElement2)));
            } catch (IllegalArgumentException unused) {
                int size = executableElement.getParameters().size();
                if (executableElement2.getParameters().size() != size) {
                    return false;
                }
                ImmutableList<TypeMirror> immutableListErasedParameterTypes = erasedParameterTypes(executableElement, typeElement);
                ImmutableList<TypeMirror> immutableListErasedParameterTypes2 = erasedParameterTypes(executableElement2, typeElement);
                if (immutableListErasedParameterTypes == null || immutableListErasedParameterTypes2 == null) {
                    return false;
                }
                for (int i = 0; i < size; i++) {
                    if (!this.typeUtils.isSameType(immutableListErasedParameterTypes.get(i), immutableListErasedParameterTypes2.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }

        ImmutableList<TypeMirror> erasedParameterTypes(ExecutableElement executableElement, TypeElement typeElement) {
            if (executableElement.getParameters().isEmpty()) {
                return ImmutableList.of();
            }
            return new TypeSubstVisitor().erasedParameterTypes(executableElement, typeElement);
        }

        /* JADX INFO: Access modifiers changed from: private */
        class TypeSubstVisitor extends SimpleTypeVisitor8<TypeMirror, Void> {
            private final Map<TypeParameterElement, TypeMirror> typeBindings;

            private TypeSubstVisitor() {
                this.typeBindings = Maps.newLinkedHashMap();
            }

            ImmutableList<TypeMirror> erasedParameterTypes(ExecutableElement executableElement, TypeElement typeElement) {
                if (executableElement.getEnclosingElement().equals(typeElement)) {
                    ImmutableList.Builder builder = ImmutableList.builder();
                    Iterator it = executableElement.getParameters().iterator();
                    while (it.hasNext()) {
                        builder.add(ExplicitOverrides.this.typeUtils.erasure((TypeMirror) visit(((VariableElement) it.next()).asType())));
                    }
                    return builder.build();
                }
                ArrayList arrayListNewArrayList = Lists.newArrayList();
                if (typeElement.getSuperclass().getKind() == TypeKind.DECLARED) {
                    arrayListNewArrayList.add(typeElement.getSuperclass());
                }
                arrayListNewArrayList.addAll(typeElement.getInterfaces());
                Iterator it2 = arrayListNewArrayList.iterator();
                while (it2.hasNext()) {
                    DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared((TypeMirror) it2.next());
                    TypeElement typeElementAsType = MoreElements.asType(declaredTypeAsDeclared.asElement());
                    List typeArguments = declaredTypeAsDeclared.getTypeArguments();
                    List typeParameters = typeElementAsType.getTypeParameters();
                    if (typeArguments.isEmpty()) {
                        typeArguments = (List) typeParameters.stream().map(new Function() { // from class: dagger.spi.shaded.auto.common.Overrides$ExplicitOverrides$TypeSubstVisitor$$ExternalSyntheticLambda0
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return Overrides.ExplicitOverrides.TypeSubstVisitor.lambda$erasedParameterTypes$0((TypeParameterElement) obj);
                            }
                        }).collect(Collectors.toList());
                    }
                    Verify.verify(typeArguments.size() == typeParameters.size());
                    for (int i = 0; i < typeArguments.size(); i++) {
                        this.typeBindings.put((TypeParameterElement) typeParameters.get(i), (TypeMirror) typeArguments.get(i));
                    }
                    ImmutableList<TypeMirror> immutableListErasedParameterTypes = erasedParameterTypes(executableElement, typeElementAsType);
                    if (immutableListErasedParameterTypes != null) {
                        return immutableListErasedParameterTypes;
                    }
                }
                return null;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static /* synthetic */ TypeMirror lambda$erasedParameterTypes$0(TypeParameterElement typeParameterElement) {
                return (TypeMirror) typeParameterElement.getBounds().get(0);
            }
        }

        ExecutableElement methodFromSuperclasses(TypeElement typeElement, ExecutableElement executableElement) {
            while (typeElement != null) {
                ExecutableElement executableElementMethodInType = methodInType(typeElement, executableElement);
                if (executableElementMethodInType != null) {
                    return executableElementMethodInType;
                }
                typeElement = superclass(typeElement);
            }
            return null;
        }

        ExecutableElement methodFromSuperinterfaces(TypeElement typeElement, ExecutableElement executableElement) {
            TypeElement typeElementSuperclass;
            TypeElement typeElementAsType = MoreElements.asType(executableElement.getEnclosingElement());
            Preconditions.checkArgument(typeElementAsType.getKind().isInterface());
            TypeMirror typeMirrorErasure = this.typeUtils.erasure(typeElementAsType.asType());
            ImmutableList immutableListOf = ImmutableList.of(typeElement);
            while (!immutableListOf.isEmpty()) {
                ImmutableList.Builder builder = ImmutableList.builder();
                UnmodifiableIterator it = immutableListOf.iterator();
                while (it.hasNext()) {
                    TypeElement typeElement2 = (TypeElement) it.next();
                    if (this.typeUtils.isAssignable(this.typeUtils.erasure(typeElement2.asType()), typeMirrorErasure)) {
                        ExecutableElement executableElementMethodInType = methodInType(typeElement2, executableElement);
                        if (executableElementMethodInType != null) {
                            return executableElementMethodInType;
                        }
                        builder.addAll((Iterable) superinterfaces(typeElement2));
                    }
                    if (typeElement2.getKind().isClass() && (typeElementSuperclass = superclass(typeElement2)) != null) {
                        builder.add(typeElementSuperclass);
                    }
                }
                immutableListOf = builder.build();
            }
            return null;
        }

        private ExecutableElement methodInType(TypeElement typeElement, ExecutableElement executableElement) {
            int size = executableElement.getParameters().size();
            ImmutableList<TypeMirror> immutableListErasedParameterTypes = erasedParameterTypes(executableElement, typeElement);
            if (immutableListErasedParameterTypes == null) {
                return null;
            }
            for (ExecutableElement executableElement2 : ElementFilter.methodsIn(typeElement.getEnclosedElements())) {
                if (executableElement2.getSimpleName().equals(executableElement.getSimpleName()) && executableElement2.getParameters().size() == size) {
                    for (int i = 0; i < size; i++) {
                        if (!this.typeUtils.isSameType(immutableListErasedParameterTypes.get(i), this.typeUtils.erasure(((VariableElement) executableElement2.getParameters().get(i)).asType()))) {
                            break;
                        }
                    }
                    return executableElement2;
                }
            }
            return null;
        }

        private TypeElement superclass(TypeElement typeElement) {
            TypeMirror superclass = typeElement.getSuperclass();
            if (superclass.getKind() == TypeKind.DECLARED) {
                return MoreElements.asType(this.typeUtils.asElement(superclass));
            }
            return null;
        }

        private ImmutableList<TypeElement> superinterfaces(TypeElement typeElement) {
            ImmutableList.Builder builder = ImmutableList.builder();
            Iterator it = typeElement.getInterfaces().iterator();
            while (it.hasNext()) {
                builder.add(MoreElements.asType(this.typeUtils.asElement((TypeMirror) it.next())));
            }
            return builder.build();
        }
    }
}
