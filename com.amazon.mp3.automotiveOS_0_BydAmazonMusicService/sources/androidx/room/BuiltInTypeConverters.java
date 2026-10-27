package androidx.room;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes2.dex */
@Target({})
@Retention(RetentionPolicy.CLASS)
public @interface BuiltInTypeConverters {

    public enum State {
        ENABLED,
        DISABLED,
        INHERITED
    }

    State enums() default State.INHERITED;

    State uuid() default State.INHERITED;
}
