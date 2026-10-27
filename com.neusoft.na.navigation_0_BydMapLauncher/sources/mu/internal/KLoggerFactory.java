package mu.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mu.KLoggable;
import mu.KLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

/* JADX INFO: compiled from: KLoggerFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0082\bJ\u001e\u0010\u0007\u001a\u00020\b2\u000e\b\b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0080\b¢\u0006\u0002\b\fJ\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0080\b¢\u0006\u0002\b\fJ\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0080\b¢\u0006\u0002\b\fJ\u0011\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\b¨\u0006\u0010"}, d2 = {"Lmu/internal/KLoggerFactory;", "", "()V", "jLogger", "Lorg/slf4j/Logger;", "name", "", "logger", "Lmu/KLogger;", "func", "Lkotlin/Function0;", "", "logger$kotlin_logging", "loggable", "Lmu/KLoggable;", "wrapJLogger", "kotlin-logging"}, k = 1, mv = {1, 1, 13})
public final class KLoggerFactory {
    public static final KLoggerFactory INSTANCE = new KLoggerFactory();

    private KLoggerFactory() {
    }

    public final KLogger logger$kotlin_logging(KLoggable loggable) {
        Intrinsics.checkParameterIsNotNull(loggable, "loggable");
        KLoggerNameResolver kLoggerNameResolver = KLoggerNameResolver.INSTANCE;
        Class<?> cls = loggable.getClass();
        if (cls.getEnclosingClass() != null) {
            try {
                Field field = cls.getEnclosingClass().getField(cls.getSimpleName());
                if (Modifier.isStatic(field.getModifiers()) && Intrinsics.areEqual(field.getType(), cls)) {
                    Class<?> enclosingClass = cls.getEnclosingClass();
                    Intrinsics.checkExpressionValueIsNotNull(enclosingClass, "clazz.enclosingClass");
                    cls = enclosingClass;
                }
            } catch (Exception unused) {
            }
        }
        String name = cls.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "unwrapCompanionClass(forClass).name");
        Logger logger = LoggerFactory.getLogger(name);
        Intrinsics.checkExpressionValueIsNotNull(logger, "LoggerFactory.getLogger(name)");
        if (logger instanceof LocationAwareLogger) {
            return new LocationAwareKLogger((LocationAwareLogger) logger);
        }
        return new LocationIgnorantKLogger(logger);
    }

    public final KLogger logger$kotlin_logging(Function0<Unit> func) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        KLoggerNameResolver kLoggerNameResolver = KLoggerNameResolver.INSTANCE;
        String slicedName = func.getClass().getName();
        String str = slicedName;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "Kt$", false, 2, (Object) null)) {
            slicedName = StringsKt.substringBefore$default(slicedName, "Kt$", (String) null, 2, (Object) null);
        } else if (StringsKt.contains$default((CharSequence) str, (CharSequence) "$", false, 2, (Object) null)) {
            slicedName = StringsKt.substringBefore$default(slicedName, "$", (String) null, 2, (Object) null);
        }
        Intrinsics.checkExpressionValueIsNotNull(slicedName, "slicedName");
        Logger logger = LoggerFactory.getLogger(slicedName);
        Intrinsics.checkExpressionValueIsNotNull(logger, "LoggerFactory.getLogger(name)");
        if (logger instanceof LocationAwareLogger) {
            return new LocationAwareKLogger((LocationAwareLogger) logger);
        }
        return new LocationIgnorantKLogger(logger);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Logger jLogger(String name) {
        Logger logger = LoggerFactory.getLogger(name);
        Intrinsics.checkExpressionValueIsNotNull(logger, "LoggerFactory.getLogger(name)");
        return logger;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KLogger wrapJLogger(Logger jLogger) {
        if (jLogger instanceof LocationAwareLogger) {
            return new LocationAwareKLogger((LocationAwareLogger) jLogger);
        }
        return new LocationIgnorantKLogger(jLogger);
    }

    public final KLogger logger$kotlin_logging(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Logger logger = LoggerFactory.getLogger(name);
        Intrinsics.checkExpressionValueIsNotNull(logger, "LoggerFactory.getLogger(name)");
        if (logger instanceof LocationAwareLogger) {
            return new LocationAwareKLogger((LocationAwareLogger) logger);
        }
        return new LocationIgnorantKLogger(logger);
    }
}
