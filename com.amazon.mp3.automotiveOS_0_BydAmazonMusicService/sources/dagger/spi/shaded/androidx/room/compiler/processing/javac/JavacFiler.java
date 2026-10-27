package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import dagger.spi.shaded.androidx.room.compiler.processing.XFiler;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;
import javax.annotation.processing.Filer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacFiler.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacFiler;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XFiler;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv;", "processingEnv", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv;", "Ljavax/annotation/processing/Filer;", "delegate", "Ljavax/annotation/processing/Filer;", "getDelegate", "()Ljavax/annotation/processing/Filer;", "<init>", "(Landroidx/room/compiler/processing/XProcessingEnv;Ljavax/annotation/processing/Filer;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JavacFiler implements XFiler {
    private final Filer delegate;
    private final XProcessingEnv processingEnv;

    public JavacFiler(XProcessingEnv processingEnv, Filer delegate) {
        Intrinsics.checkNotNullParameter(processingEnv, "processingEnv");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.processingEnv = processingEnv;
        this.delegate = delegate;
    }

    public final Filer getDelegate() {
        return this.delegate;
    }
}
