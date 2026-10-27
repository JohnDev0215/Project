package dagger.spi.shaded.androidx.room.compiler.processing.javac;

import dagger.spi.shaded.androidx.room.compiler.processing.XMessager;
import javax.annotation.processing.Messager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavacProcessingEnvMessager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/javac/JavacProcessingEnvMessager;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMessager;", "Ljavax/annotation/processing/Messager;", "delegate", "Ljavax/annotation/processing/Messager;", "getDelegate", "()Ljavax/annotation/processing/Messager;", "<init>", "(Ljavax/annotation/processing/Messager;)V", "Companion", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class JavacProcessingEnvMessager extends XMessager {
    private final Messager delegate;

    public JavacProcessingEnvMessager(Messager delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }
}
