package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes5.dex */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface EqualsAndHashCode {

    @Target({})
    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnyAnnotation {
    }

    public enum CacheStrategy {
        NEVER,
        LAZY;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static CacheStrategy[] valuesCustom() {
            CacheStrategy[] cacheStrategyArrValuesCustom = values();
            int length = cacheStrategyArrValuesCustom.length;
            CacheStrategy[] cacheStrategyArr = new CacheStrategy[length];
            System.arraycopy(cacheStrategyArrValuesCustom, 0, cacheStrategyArr, 0, length);
            return cacheStrategyArr;
        }
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Exclude {
    }

    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Include {
        int rank() default 0;

        String replaces() default "";
    }

    CacheStrategy cacheStrategy() default CacheStrategy.NEVER;

    boolean callSuper() default false;

    boolean doNotUseGetters() default false;

    String[] exclude() default {};

    String[] of() default {};

    AnyAnnotation[] onParam() default {};

    boolean onlyExplicitlyIncluded() default false;
}
