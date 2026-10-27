package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.auto.common.MoreElements;
import dagger.spi.shaded.auto.common.MoreTypes;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ElementExt.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a(\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\u001a\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u0000\u001a\u001f\u0010\u0017\u001a\u00020\u000b*\u00020\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0019\u001a\u0014\u0010\u001a\u001a\u00020\u0014*\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u0000\"\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\u00078@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001b"}, d2 = {"NONNULL_ANNOTATIONS", "", "", "[Ljava/lang/String;", "NULLABLE_ANNOTATIONS", "nullability", "Ldagger/spi/shaded/androidx/room/compiler/processing/XNullability;", "Ljavax/lang/model/element/Element;", "getNullability", "(Ljavax/lang/model/element/Element;)Landroidx/room/compiler/processing/XNullability;", "suspendOverrides", "", "overrider", "Ljavax/lang/model/element/ExecutableElement;", "overridden", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "Ljavax/lang/model/element/TypeElement;", "typeUtils", "Ljavax/lang/model/util/Types;", "enclosingType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeElement;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "hasAnyOf", "annotations", "(Ljavax/lang/model/element/Element;[Ljava/lang/String;)Z", "requireEnclosingType", "room-compiler-processing"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class ElementExtKt {
    private static final String[] NONNULL_ANNOTATIONS = {"androidx.annotation.NonNull", "org.jetbrains.annotations.NotNull"};
    private static final String[] NULLABLE_ANNOTATIONS = {"androidx.annotation.Nullable", "org.jetbrains.annotations.Nullable"};

    private static final boolean hasAnyOf(Element element, String[] strArr) {
        boolean z;
        List annotationMirrors = element.getAnnotationMirrors();
        Intrinsics.checkNotNullExpressionValue(annotationMirrors, "annotationMirrors");
        if (!(annotationMirrors instanceof Collection) || !annotationMirrors.isEmpty()) {
            Iterator it = annotationMirrors.iterator();
            while (it.hasNext()) {
                TypeElement typeElementAsType = MoreElements.asType(((AnnotationMirror) it.next()).getAnnotationType().asElement());
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = false;
                        break;
                    }
                    if (typeElementAsType.getQualifiedName().contentEquals(strArr[i])) {
                        z = true;
                        break;
                    }
                    i++;
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final XNullability getNullability(Element element) {
        Intrinsics.checkNotNullParameter(element, "<this>");
        if (element.asType().getKind().isPrimitive() || hasAnyOf(element, NONNULL_ANNOTATIONS)) {
            return XNullability.NONNULL;
        }
        if (hasAnyOf(element, NULLABLE_ANNOTATIONS)) {
            return XNullability.NULLABLE;
        }
        return XNullability.UNKNOWN;
    }

    public static final JavacTypeElement requireEnclosingType(Element element, JavacProcessingEnv env) {
        Intrinsics.checkNotNullParameter(element, "<this>");
        Intrinsics.checkNotNullParameter(env, "env");
        JavacTypeElement javacTypeElementEnclosingType = enclosingType(element, env);
        if (javacTypeElementEnclosingType != null) {
            return javacTypeElementEnclosingType;
        }
        throw new IllegalStateException(("Cannot find required enclosing type for " + element).toString());
    }

    public static final JavacTypeElement enclosingType(Element element, JavacProcessingEnv env) {
        Intrinsics.checkNotNullParameter(element, "<this>");
        Intrinsics.checkNotNullParameter(env, "env");
        if (!MoreElements.isType(element.getEnclosingElement())) {
            return null;
        }
        TypeElement typeElementAsType = MoreElements.asType(element.getEnclosingElement());
        Intrinsics.checkNotNullExpressionValue(typeElementAsType, "asType(enclosingElement)");
        return env.wrapTypeElement(typeElementAsType);
    }

    public static final boolean suspendOverrides(ExecutableElement overrider, ExecutableElement overridden, TypeElement owner, Types typeUtils) {
        Intrinsics.checkNotNullParameter(overrider, "overrider");
        Intrinsics.checkNotNullParameter(overridden, "overridden");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(typeUtils, "typeUtils");
        if (!Intrinsics.areEqual(overrider.getSimpleName(), overridden.getSimpleName()) || Intrinsics.areEqual(overrider.getEnclosingElement(), overridden.getEnclosingElement()) || overridden.getModifiers().contains(Modifier.STATIC) || overridden.getModifiers().contains(Modifier.PRIVATE)) {
            return false;
        }
        TypeElement enclosingElement = overridden.getEnclosingElement();
        TypeElement typeElement = enclosingElement instanceof TypeElement ? enclosingElement : null;
        if (typeElement == null || !typeUtils.isSubtype(typeUtils.erasure(owner.asType()), typeUtils.erasure(typeElement.asType()))) {
            return false;
        }
        DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(owner.asType());
        Element element = (Element) overrider;
        ExecutableType executableTypeAsExecutable = MoreTypes.asExecutable(typeUtils.asMemberOf(declaredTypeAsDeclared, element));
        ExecutableType executableTypeAsExecutable2 = MoreTypes.asExecutable(typeUtils.asMemberOf(declaredTypeAsDeclared, element));
        if (executableTypeAsExecutable.getParameterTypes().size() != executableTypeAsExecutable2.getParameterTypes().size()) {
            return false;
        }
        TypeName typeName = TypeName.get(Continuation.class);
        List parameterTypes = executableTypeAsExecutable.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "overriderExecutable.parameterTypes");
        TypeName typeName2 = TypeName.get((TypeMirror) CollectionsKt___CollectionsKt.last(parameterTypes));
        ParameterizedTypeName parameterizedTypeName = typeName2 instanceof ParameterizedTypeName ? (ParameterizedTypeName) typeName2 : null;
        ClassName className = parameterizedTypeName != null ? parameterizedTypeName.rawType : null;
        if (!Intrinsics.areEqual(className, typeName)) {
            throw new IllegalStateException(("Expected " + className + " to be " + typeName).toString());
        }
        List parameterTypes2 = executableTypeAsExecutable2.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes2, "overriddenExecutable.parameterTypes");
        TypeName typeName3 = TypeName.get((TypeMirror) CollectionsKt___CollectionsKt.last(parameterTypes2));
        ParameterizedTypeName parameterizedTypeName2 = typeName3 instanceof ParameterizedTypeName ? (ParameterizedTypeName) typeName3 : null;
        ClassName className2 = parameterizedTypeName2 != null ? parameterizedTypeName2.rawType : null;
        if (!Intrinsics.areEqual(className2, typeName)) {
            throw new IllegalStateException(("Expected " + className2 + " to be " + typeName).toString());
        }
        List parameterTypes3 = executableTypeAsExecutable.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes3, "overriderExecutable.parameterTypes");
        List typeArguments = MoreTypes.asDeclared((TypeMirror) CollectionsKt___CollectionsKt.last(parameterTypes3)).getTypeArguments();
        Intrinsics.checkNotNullExpressionValue(typeArguments, "asDeclared(overriderExec…           .typeArguments");
        Object objSingle = CollectionsKt___CollectionsKt.single((List<? extends Object>) typeArguments);
        Intrinsics.checkNotNullExpressionValue(objSingle, "asDeclared(overriderExec…  .typeArguments.single()");
        TypeMirror typeMirrorExtendsBound = TypeMirrorExtKt.extendsBound((TypeMirror) objSingle);
        List parameterTypes4 = executableTypeAsExecutable.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes4, "overriderExecutable.parameterTypes");
        List typeArguments2 = MoreTypes.asDeclared((TypeMirror) CollectionsKt___CollectionsKt.last(parameterTypes4)).getTypeArguments();
        Intrinsics.checkNotNullExpressionValue(typeArguments2, "asDeclared(overriderExec…           .typeArguments");
        Object objSingle2 = CollectionsKt___CollectionsKt.single((List<? extends Object>) typeArguments2);
        Intrinsics.checkNotNullExpressionValue(objSingle2, "asDeclared(overriderExec…  .typeArguments.single()");
        if (!typeUtils.isSameType(typeUtils.erasure(typeMirrorExtendsBound), typeUtils.erasure(TypeMirrorExtKt.extendsBound((TypeMirror) objSingle2)))) {
            return false;
        }
        if (executableTypeAsExecutable2.getParameterTypes().size() >= 2) {
            List parameterTypes5 = executableTypeAsExecutable.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(parameterTypes5, "overriderExecutable.parameterTypes");
            List parameterTypes6 = executableTypeAsExecutable2.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(parameterTypes6, "overriddenExecutable.parameterTypes");
            for (Pair pair : CollectionsKt___CollectionsKt.dropLast(CollectionsKt___CollectionsKt.zip(parameterTypes5, parameterTypes6), 1)) {
                if (!typeUtils.isSameType(typeUtils.erasure((TypeMirror) pair.component1()), typeUtils.erasure((TypeMirror) pair.component2()))) {
                    return false;
                }
            }
        }
        return true;
    }
}
