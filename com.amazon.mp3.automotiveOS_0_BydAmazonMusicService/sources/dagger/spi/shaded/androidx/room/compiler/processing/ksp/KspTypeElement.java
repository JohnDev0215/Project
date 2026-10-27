package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.google.devtools.ksp.symbol.ClassKind;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.squareup.javapoet.ClassName;
import dagger.spi.shaded.androidx.room.compiler.processing.XFieldElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers;
import dagger.spi.shaded.androidx.room.compiler.processing.XMemberContainer;
import dagger.spi.shaded.androidx.room.compiler.processing.XMethodElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XType;
import dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: KspTypeElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b0\u0018\u0000 <2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0004:\u0001<B\u0019\b\u0004\u0012\u0006\u00109\u001a\u000208\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b:\u0010;J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\bH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\bH\u0016R\u001a\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0018\u001a\u00020\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u001b\u001a\u00020\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0015\u001a\u0004\b\u001a\u0010\u0017R\u001d\u0010\u001f\u001a\u0004\u0018\u00010\u00028VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0015\u001a\u0004\b\u001d\u0010\u001eR\u001b\u0010\"\u001a\u00020\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0015\u001a\u0004\b!\u0010\u0017R\u001d\u0010'\u001a\u0004\u0018\u00010#8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u0015\u001a\u0004\b%\u0010&R\u001b\u0010,\u001a\u00020(8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u0015\u001a\u0004\b*\u0010+R!\u00100\u001a\b\u0012\u0004\u0012\u00020\u00040\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\u0015\u001a\u0004\b.\u0010/R!\u00103\u001a\b\u0012\u0004\u0012\u00020\u000b0\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\u0015\u001a\u0004\b2\u0010/R\u0016\u00107\u001a\u0004\u0018\u0001048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00106¨\u0006="}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspTypeElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XHasModifiers;", "", "", "isInterface", "isClass", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XFieldElement;", "getDeclaredFields", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMethodElement;", "getDeclaredMethods", "getSuperInterfaceElements", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "declaration", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "getDeclaration", "()Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "", "name$delegate", "Lkotlin/Lazy;", "getName", "()Ljava/lang/String;", "name", "packageName$delegate", "getPackageName", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "enclosingTypeElement$delegate", "getEnclosingTypeElement", "()Landroidx/room/compiler/processing/XTypeElement;", "enclosingTypeElement", "qualifiedName$delegate", "getQualifiedName", "qualifiedName", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "superClass$delegate", "getSuperClass", "()Landroidx/room/compiler/processing/XType;", "superClass", "Lcom/squareup/javapoet/ClassName;", "className$delegate", "getClassName", "()Lcom/squareup/javapoet/ClassName;", "className", "_declaredFields$delegate", "get_declaredFields", "()Ljava/util/List;", "_declaredFields", "_declaredMethods$delegate", "get_declaredMethods", "_declaredMethods", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMemberContainer;", "getEnclosingElement", "()Landroidx/room/compiler/processing/XMemberContainer;", "enclosingElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "env", "<init>", "(Landroidx/room/compiler/processing/ksp/KspProcessingEnv;Lcom/google/devtools/ksp/symbol/KSClassDeclaration;)V", "Companion", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public abstract class KspTypeElement extends KspElement implements XTypeElement, XHasModifiers, XMemberContainer {

    /* JADX INFO: renamed from: _declaredFields$delegate, reason: from kotlin metadata */
    private final Lazy _declaredFields;

    /* JADX INFO: renamed from: _declaredMethods$delegate, reason: from kotlin metadata */
    private final Lazy _declaredMethods;

    /* JADX INFO: renamed from: className$delegate, reason: from kotlin metadata */
    private final Lazy className;
    private final KSClassDeclaration declaration;

    /* JADX INFO: renamed from: enclosingTypeElement$delegate, reason: from kotlin metadata */
    private final Lazy enclosingTypeElement;

    /* JADX INFO: renamed from: name$delegate, reason: from kotlin metadata */
    private final Lazy name;

    /* JADX INFO: renamed from: packageName$delegate, reason: from kotlin metadata */
    private final Lazy packageName;

    /* JADX INFO: renamed from: qualifiedName$delegate, reason: from kotlin metadata */
    private final Lazy qualifiedName;

    /* JADX INFO: renamed from: superClass$delegate, reason: from kotlin metadata */
    private final Lazy superClass;

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspElement
    public KSClassDeclaration getDeclaration() {
        return this.declaration;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public String getName() {
        return (String) this.name.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public String getPackageName() {
        return (String) this.packageName.getValue();
    }

    public XTypeElement getEnclosingTypeElement() {
        return (XTypeElement) this.enclosingTypeElement.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public XMemberContainer getEnclosingElement() {
        return getEnclosingTypeElement();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public String getQualifiedName() {
        return (String) this.qualifiedName.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public XType getSuperClass() {
        return (XType) this.superClass.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement, dagger.spi.shaded.androidx.room.compiler.processing.XMemberContainer
    public ClassName getClassName() {
        return (ClassName) this.className.getValue();
    }

    private final List<Object> get_declaredFields() {
        return (List) this._declaredFields.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public boolean isInterface() {
        return getDeclaration().getClassKind() == ClassKind.INTERFACE;
    }

    public boolean isClass() {
        return getDeclaration().getClassKind() == ClassKind.CLASS;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public List<XFieldElement> getDeclaredFields() {
        return get_declaredFields();
    }

    private final List<XMethodElement> get_declaredMethods() {
        return (List) this._declaredMethods.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public List<XMethodElement> getDeclaredMethods() {
        return get_declaredMethods();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XTypeElement
    public List<XTypeElement> getSuperInterfaceElements() {
        Sequence sequenceFilter = SequencesKt___SequencesKt.filter(SequencesKt___SequencesKt.mapNotNull(getDeclaration().getSuperTypes(), new Function1<KSTypeReference, KSDeclaration>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspTypeElement.getSuperInterfaceElements.1
            @Override // kotlin.jvm.functions.Function1
            public final KSDeclaration invoke(KSTypeReference it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getResolved().getDeclaration();
            }
        }), new Function1<Object, Boolean>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspTypeElement$getSuperInterfaceElements$$inlined$filterIsInstance$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Object obj) {
                return Boolean.valueOf(obj instanceof KSClassDeclaration);
            }
        });
        Intrinsics.checkNotNull(sequenceFilter, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
        Sequence sequenceFilter2 = SequencesKt___SequencesKt.filter(sequenceFilter, new Function1<KSClassDeclaration, Boolean>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspTypeElement.getSuperInterfaceElements.2
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(KSClassDeclaration it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(it.getClassKind() == ClassKind.INTERFACE);
            }
        });
        ArrayList arrayList = new ArrayList();
        Iterator it = sequenceFilter2.iterator();
        while (it.hasNext()) {
            arrayList.add(getEnv().wrapClassDeclaration((KSClassDeclaration) it.next()));
        }
        return arrayList;
    }
}
