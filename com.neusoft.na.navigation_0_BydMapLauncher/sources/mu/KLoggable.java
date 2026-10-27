package mu;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import mu.internal.KLoggerFactory;
import mu.internal.KLoggerNameResolver;
import mu.internal.LocationAwareKLogger;
import mu.internal.LocationIgnorantKLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

/* JADX INFO: compiled from: KLogging.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"Lmu/KLoggable;", "", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "name", "", "kotlin-logging"}, k = 1, mv = {1, 1, 13})
public interface KLoggable {
    KLogger getLogger();

    KLogger logger();

    KLogger logger(String name);

    /* JADX INFO: compiled from: KLogging.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public static final class DefaultImpls {
        public static KLogger logger(KLoggable kLoggable) {
            KLoggerFactory kLoggerFactory = KLoggerFactory.INSTANCE;
            KLoggerNameResolver kLoggerNameResolver = KLoggerNameResolver.INSTANCE;
            Class<?> cls = kLoggable.getClass();
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

        public static KLogger logger(KLoggable kLoggable, String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            KLoggerFactory kLoggerFactory = KLoggerFactory.INSTANCE;
            Logger logger = LoggerFactory.getLogger(name);
            Intrinsics.checkExpressionValueIsNotNull(logger, "LoggerFactory.getLogger(name)");
            if (logger instanceof LocationAwareLogger) {
                return new LocationAwareKLogger((LocationAwareLogger) logger);
            }
            return new LocationIgnorantKLogger(logger);
        }
    }
}
