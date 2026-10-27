package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleTypeVisitor8;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TypeMirrorExt.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"extendsBound", "Ljavax/lang/model/type/TypeMirror;", "room-compiler-processing"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class TypeMirrorExtKt {
    public static final TypeMirror extendsBound(TypeMirror typeMirror) {
        Intrinsics.checkNotNullParameter(typeMirror, "<this>");
        return (TypeMirror) typeMirror.accept(new SimpleTypeVisitor8<TypeMirror, Void>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.TypeMirrorExtKt.extendsBound.1
        }, (Object) null);
    }
}
