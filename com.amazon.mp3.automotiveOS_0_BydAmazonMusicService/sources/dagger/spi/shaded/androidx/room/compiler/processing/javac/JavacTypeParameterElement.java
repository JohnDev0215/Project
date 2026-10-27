package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import dagger.spi.shaded.androidx.room.compiler.processing.XElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XEquality;
import dagger.spi.shaded.androidx.room.compiler.processing.XNullability;
import dagger.spi.shaded.androidx.room.compiler.processing.XType;
import dagger.spi.shaded.androidx.room.compiler.processing.XTypeParameterElement;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.kotlin.KmType;
import dagger.spi.shaded.auto.common.MoreTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacTypeParameterElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B)\u0012\u0006\u0010%\u001a\u00020$\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b&\u0010'R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\t\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0015\u001a\u00020\u00108VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R!\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001f\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010#\u001a\u00020 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006("}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacTypeParameterElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeParameterElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XElement;", "enclosingElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/XElement;", "getEnclosingElement", "()Landroidx/room/compiler/processing/XElement;", "Ljavax/lang/model/element/TypeParameterElement;", "element", "Ljavax/lang/model/element/TypeParameterElement;", "getElement", "()Ljavax/lang/model/element/TypeParameterElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "kotlinType", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/kotlin/KmType;", "Lcom/squareup/javapoet/TypeVariableName;", "typeVariableName$delegate", "Lkotlin/Lazy;", "getTypeVariableName", "()Lcom/squareup/javapoet/TypeVariableName;", "typeVariableName", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "bounds$delegate", "getBounds", "()Ljava/util/List;", "bounds", "", "getName", "()Ljava/lang/String;", "name", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMemberContainer;", "getClosestMemberContainer", "()Landroidx/room/compiler/processing/XMemberContainer;", "closestMemberContainer", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Landroidx/room/compiler/processing/XElement;Ljavax/lang/model/element/TypeParameterElement;Landroidx/room/compiler/processing/javac/kotlin/KmType;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JavacTypeParameterElement extends JavacElement implements XTypeParameterElement {

    /* JADX INFO: renamed from: bounds$delegate, reason: from kotlin metadata */
    private final Lazy bounds;
    private final TypeParameterElement element;
    private final XElement enclosingElement;
    private final KmType kotlinType;

    /* JADX INFO: renamed from: typeVariableName$delegate, reason: from kotlin metadata */
    private final Lazy typeVariableName;

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public XElement getEnclosingElement() {
        return this.enclosingElement;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacElement
    public TypeParameterElement getElement() {
        return this.element;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavacTypeParameterElement(final JavacProcessingEnv env, XElement enclosingElement, TypeParameterElement element, KmType kmType) {
        super(env, (Element) element);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(enclosingElement, "enclosingElement");
        Intrinsics.checkNotNullParameter(element, "element");
        this.enclosingElement = enclosingElement;
        this.element = element;
        this.kotlinType = kmType;
        this.typeVariableName = LazyKt__LazyJVMKt.lazy(new Function0<TypeVariableName>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeParameterElement$typeVariableName$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TypeVariableName invoke() {
                String name = this.this$0.getName();
                List<XType> bounds = this.this$0.getBounds();
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(bounds, 10));
                Iterator<T> it = bounds.iterator();
                while (it.hasNext()) {
                    arrayList.add(((XType) it.next()).getTypeName());
                }
                Object[] array = arrayList.toArray(new TypeName[0]);
                Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                TypeName[] typeNameArr = (TypeName[]) array;
                return TypeVariableName.get(name, (TypeName[]) Arrays.copyOf(typeNameArr, typeNameArr.length));
            }
        });
        this.bounds = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends JavacType>>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacTypeParameterElement$bounds$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends JavacType> invoke() {
                XEquality javacArrayType;
                XEquality javacArrayType2;
                List<TypeMirror> bounds = this.this$0.getElement().getBounds();
                Intrinsics.checkNotNullExpressionValue(bounds, "element.bounds");
                JavacProcessingEnv javacProcessingEnv = env;
                JavacTypeParameterElement javacTypeParameterElement = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(bounds, 10));
                for (TypeMirror it : bounds) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    KmType kmType2 = javacTypeParameterElement.kotlinType;
                    KmType extendsBound = kmType2 != null ? kmType2.getExtendsBound() : null;
                    XNullability xNullability = XNullability.UNKNOWN;
                    TypeKind kind = it.getKind();
                    int i = kind == null ? -1 : JavacProcessingEnv.WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
                    if (i != 1) {
                        if (i != 2) {
                            if (extendsBound != null) {
                                javacArrayType2 = new DefaultJavacType(javacProcessingEnv, it, extendsBound);
                            } else if (xNullability != null) {
                                javacArrayType2 = new DefaultJavacType(javacProcessingEnv, it, xNullability);
                            } else {
                                javacArrayType2 = new DefaultJavacType(javacProcessingEnv, it);
                            }
                        } else if (extendsBound != null) {
                            DeclaredType declaredTypeAsDeclared = MoreTypes.asDeclared(it);
                            Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared, "asDeclared(typeMirror)");
                            javacArrayType2 = new JavacDeclaredType(javacProcessingEnv, declaredTypeAsDeclared, extendsBound);
                        } else {
                            if (xNullability != null) {
                                DeclaredType declaredTypeAsDeclared2 = MoreTypes.asDeclared(it);
                                Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared2, "asDeclared(typeMirror)");
                                javacArrayType = new JavacDeclaredType(javacProcessingEnv, declaredTypeAsDeclared2, xNullability);
                            } else {
                                DeclaredType declaredTypeAsDeclared3 = MoreTypes.asDeclared(it);
                                Intrinsics.checkNotNullExpressionValue(declaredTypeAsDeclared3, "asDeclared(typeMirror)");
                                javacArrayType = new JavacDeclaredType(javacProcessingEnv, declaredTypeAsDeclared3);
                            }
                            javacArrayType2 = javacArrayType;
                        }
                    } else if (extendsBound != null) {
                        ArrayType arrayTypeAsArray = MoreTypes.asArray(it);
                        Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray, "asArray(typeMirror)");
                        javacArrayType2 = new JavacArrayType(javacProcessingEnv, arrayTypeAsArray, extendsBound);
                    } else {
                        if (xNullability != null) {
                            ArrayType arrayTypeAsArray2 = MoreTypes.asArray(it);
                            Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray2, "asArray(typeMirror)");
                            javacArrayType = new JavacArrayType(javacProcessingEnv, arrayTypeAsArray2, xNullability, null);
                        } else {
                            ArrayType arrayTypeAsArray3 = MoreTypes.asArray(it);
                            Intrinsics.checkNotNullExpressionValue(arrayTypeAsArray3, "asArray(typeMirror)");
                            javacArrayType = new JavacArrayType(javacProcessingEnv, arrayTypeAsArray3);
                        }
                        javacArrayType2 = javacArrayType;
                    }
                    arrayList.add(javacArrayType2);
                }
                return arrayList;
            }
        });
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public String getName() {
        return getElement().getSimpleName().toString();
    }

    public List<XType> getBounds() {
        return (List) this.bounds.getValue();
    }
}
