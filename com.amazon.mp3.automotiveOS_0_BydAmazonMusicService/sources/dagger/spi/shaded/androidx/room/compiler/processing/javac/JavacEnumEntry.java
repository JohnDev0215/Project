package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import dagger.spi.shaded.androidx.room.compiler.processing.XEnumEntry;
import dagger.spi.shaded.androidx.room.compiler.processing.XEnumTypeElement;
import javax.lang.model.element.Element;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacEnumEntry.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u000b\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\r\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0007¨\u0006\u0014"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacEnumEntry;", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XEnumEntry;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XEnumTypeElement;", "enclosingElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/XEnumTypeElement;", "getEnclosingElement", "()Landroidx/room/compiler/processing/XEnumTypeElement;", "", "getName", "()Ljava/lang/String;", "name", "getClosestMemberContainer", "closestMemberContainer", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "Ljavax/lang/model/element/Element;", "entryElement", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/element/Element;Landroidx/room/compiler/processing/XEnumTypeElement;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JavacEnumEntry extends JavacElement implements XEnumEntry {
    private final XEnumTypeElement enclosingElement;

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public XEnumTypeElement getEnclosingElement() {
        return this.enclosingElement;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavacEnumEntry(JavacProcessingEnv env, Element entryElement, XEnumTypeElement enclosingElement) {
        super(env, entryElement);
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(entryElement, "entryElement");
        Intrinsics.checkNotNullParameter(enclosingElement, "enclosingElement");
        this.enclosingElement = enclosingElement;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    public String getName() {
        return getElement().getSimpleName().toString();
    }
}
