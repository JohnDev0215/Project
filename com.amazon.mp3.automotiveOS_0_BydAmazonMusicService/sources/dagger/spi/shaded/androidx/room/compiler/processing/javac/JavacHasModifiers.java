package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacHasModifiers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0002H\u0016R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacHasModifiers;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XHasModifiers;", "", "isPublic", "isProtected", "isPrivate", "isStatic", "Ljavax/lang/model/element/Element;", "element", "Ljavax/lang/model/element/Element;", "<init>", "(Ljavax/lang/model/element/Element;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JavacHasModifiers implements XHasModifiers {
    private final Element element;

    public JavacHasModifiers(Element element) {
        Intrinsics.checkNotNullParameter(element, "element");
        this.element = element;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers
    public boolean isPublic() {
        return this.element.getModifiers().contains(Modifier.PUBLIC);
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers
    public boolean isProtected() {
        return this.element.getModifiers().contains(Modifier.PROTECTED);
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers
    public boolean isPrivate() {
        return this.element.getModifiers().contains(Modifier.PRIVATE);
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XHasModifiers
    public boolean isStatic() {
        return this.element.getModifiers().contains(Modifier.STATIC);
    }
}
