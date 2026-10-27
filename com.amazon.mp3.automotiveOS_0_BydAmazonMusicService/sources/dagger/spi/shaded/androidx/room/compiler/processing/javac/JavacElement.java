package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import dagger.spi.shaded.androidx.room.compiler.processing.XElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XEquality;
import javax.lang.model.element.Element;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\f\b \u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u001f\u0010 J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0096\u0002J\b\u0010\n\u001a\u00020\tH\u0016R\u001a\u0010\f\u001a\u00020\u000b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R%\u0010\u001a\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00030\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\u001e\u001a\u0004\u0018\u00010\u00048VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0017\u001a\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XEquality;", "", "", "toString", "other", "", "equals", "", "hashCode", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnv;", "getEnv$room_compiler_processing", "()Landroidx/room/compiler/processing/javac/JavacProcessingEnv;", "Ljavax/lang/model/element/Element;", "element", "Ljavax/lang/model/element/Element;", "getElement", "()Ljavax/lang/model/element/Element;", "", "equalityItems$delegate", "Lkotlin/Lazy;", "getEqualityItems", "()[Ljava/lang/Object;", "equalityItems", "docComment$delegate", "getDocComment", "()Ljava/lang/String;", "docComment", "<init>", "(Landroidx/room/compiler/processing/javac/JavacProcessingEnv;Ljavax/lang/model/element/Element;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public abstract class JavacElement implements XElement, XEquality {

    /* JADX INFO: renamed from: docComment$delegate, reason: from kotlin metadata */
    private final Lazy docComment;
    private final Element element;
    private final JavacProcessingEnv env;

    /* JADX INFO: renamed from: equalityItems$delegate, reason: from kotlin metadata */
    private final Lazy equalityItems;

    public JavacElement(JavacProcessingEnv env, Element element) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(element, "element");
        this.env = env;
        this.element = element;
        this.equalityItems = LazyKt__LazyJVMKt.lazy(new Function0<Element[]>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacElement$equalityItems$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Element[] invoke() {
                return new Element[]{this.this$0.getElement()};
            }
        });
        this.docComment = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacElement$docComment$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getEnv().getElementUtils().getDocComment(this.this$0.getElement());
            }
        });
    }

    /* JADX INFO: renamed from: getEnv$room_compiler_processing, reason: from getter */
    public final JavacProcessingEnv getEnv() {
        return this.env;
    }

    public Element getElement() {
        return this.element;
    }

    public String toString() {
        return getElement().toString();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XEquality
    public final Object[] getEqualityItems() {
        return (Object[]) this.equalityItems.getValue();
    }

    public boolean equals(Object other) {
        return XEquality.INSTANCE.equals(this, other);
    }

    public int hashCode() {
        return XEquality.INSTANCE.hashCode(getEqualityItems());
    }
}
