package kotlin.coroutines.jvm.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.JvmName;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.TYPE})
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0081\u0002\u0018\u00002\u00020\u00012\u00020\u0002R\u0013\u0010\u0006\u001a\u00020\u000386@\u0000¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0013\u0010\n\u001a\u00020\u000786@\u0000¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0013\u0010\f\u001a\u00020\u000786@\u0000¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\u0010\u001a\u00020\r86@\u0000¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0012\u001a\u00020\r86@\u0000¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\u0014\u001a\u00020\r86@\u0000¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0013\u0010\u0018\u001a\u00020\u001586@\u0000¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u001a\u001a\u00020\u000386@\u0000¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005¨\u0006\u001b"}, d2 = {"Lkotlin/coroutines/jvm/internal/DebugMetadata;", "Ljava/lang/annotation/Annotation;", "Lkotlin/Any;", "Lkotlin/Array;", "n", "()[Ljava/lang/String;", "localNames", "Lkotlin/IntArray;", "l", "()[I", "lineNumbers", "i", "indexToLabel", "Lkotlin/String;", "m", "()Ljava/lang/String;", "methodName", "c", "className", "f", "sourceFile", "Lkotlin/Int;", "v", "()I", "version", "s", "spilled", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@Retention(RetentionPolicy.RUNTIME)
public @interface DebugMetadata {
    @JvmName(name = "c")
    String c() default "";

    @JvmName(name = "f")
    String f() default "";

    @JvmName(name = "i")
    int[] i() default {};

    @JvmName(name = "l")
    int[] l() default {};

    @JvmName(name = "m")
    String m() default "";

    @JvmName(name = "n")
    String[] n() default {};

    @JvmName(name = "s")
    String[] s() default {};

    @JvmName(name = "v")
    int v() default 1;
}
