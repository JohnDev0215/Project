package dagger.spi.shaded.androidx.room.compiler.processing.compat;

import dagger.spi.shaded.androidx.room.compiler.processing.XAnnotation;
import dagger.spi.shaded.androidx.room.compiler.processing.XFiler;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacAnnotation;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacFiler;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.ksp.KspAnnotation;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XConverters.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ\f\u0010\u0004\u001a\u00020\u0003*\u00020\u0002H\u0007J\f\u0010\u0004\u001a\u00020\u0006*\u00020\u0005H\u0007J\f\u0010\u0004\u001a\u00020\b*\u00020\u0007H\u0007J\f\u0010\t\u001a\u00020\u0002*\u00020\u0007H\u0007¨\u0006\f"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/compat/XConverters;", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv;", "Ljavax/annotation/processing/ProcessingEnvironment;", "toJavac", "Ldagger/spi/shaded/androidx/room/compiler/processing/XFiler;", "Ljavax/annotation/processing/Filer;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XAnnotation;", "Ljavax/lang/model/element/AnnotationMirror;", "getProcessingEnv", "<init>", "()V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class XConverters {
    public static final XConverters INSTANCE = new XConverters();

    private XConverters() {
    }

    @JvmStatic
    public static final ProcessingEnvironment toJavac(XProcessingEnv xProcessingEnv) {
        Intrinsics.checkNotNullParameter(xProcessingEnv, "<this>");
        return ((JavacProcessingEnv) xProcessingEnv).getDelegate();
    }

    @JvmStatic
    public static final Filer toJavac(XFiler xFiler) {
        Intrinsics.checkNotNullParameter(xFiler, "<this>");
        return ((JavacFiler) xFiler).getDelegate();
    }

    @JvmStatic
    public static final AnnotationMirror toJavac(XAnnotation xAnnotation) {
        Intrinsics.checkNotNullParameter(xAnnotation, "<this>");
        return ((JavacAnnotation) xAnnotation).getMirror();
    }

    @Deprecated(message = "This will be removed in a future version of XProcessing.")
    @JvmStatic
    public static final XProcessingEnv getProcessingEnv(XAnnotation xAnnotation) {
        Intrinsics.checkNotNullParameter(xAnnotation, "<this>");
        if (xAnnotation instanceof JavacAnnotation) {
            return ((JavacAnnotation) xAnnotation).getEnv();
        }
        if (xAnnotation instanceof KspAnnotation) {
            return ((KspAnnotation) xAnnotation).getEnv();
        }
        throw new IllegalStateException(("Unexpected annotation type: " + xAnnotation).toString());
    }
}
