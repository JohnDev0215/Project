package kotlin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.JvmName;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.TYPE})
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0087\u0002\u0018\u00002\u00020\u00012\u00020\u0002R\u0013\u0010\u0006\u001a\u00020\u000386@\u0000¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0013\u0010\n\u001a\u00020\u000786@\u0000¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0016\u0010\f\u001a\u00020\u000786@\u0000X\u0087\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\u0010\u001a\u00020\r86@\u0000¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0014\u001a\u00020\u001186@\u0000¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0016\u001a\u00020\u001186@\u0000¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0016\u0010\u0018\u001a\u00020\r86@\u0000X\u0087\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000fR\u0013\u0010\u001a\u001a\u00020\u000386@\u0000¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005¨\u0006\u001b"}, d2 = {"Lkotlin/Metadata;", "Ljava/lang/annotation/Annotation;", "Lkotlin/Any;", "Lkotlin/IntArray;", "bv", "()[I", "bytecodeVersion", "Lkotlin/Int;", "k", "()I", "kind", "xi", "extraInt", "Lkotlin/String;", "xs", "()Ljava/lang/String;", "extraString", "Lkotlin/Array;", "d2", "()[Ljava/lang/String;", "data2", "d1", "data1", "pn", "packageName", "mv", "metadataVersion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS})
@Retention(RetentionPolicy.RUNTIME)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
public @interface Metadata {
    @JvmName(name = "bv")
    int[] bv() default {};

    @JvmName(name = "d1")
    String[] d1() default {};

    @JvmName(name = "d2")
    String[] d2() default {};

    @JvmName(name = "k")
    int k() default 1;

    @JvmName(name = "mv")
    int[] mv() default {};

    @JvmName(name = "pn")
    String pn() default "";

    @JvmName(name = "xi")
    int xi() default 0;

    @JvmName(name = "xs")
    String xs() default "";
}
