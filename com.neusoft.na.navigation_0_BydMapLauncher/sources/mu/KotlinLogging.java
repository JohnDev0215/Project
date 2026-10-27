package mu;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mu.internal.KLoggerFactory;
import mu.internal.KLoggerNameResolver;
import mu.internal.LocationAwareKLogger;
import mu.internal.LocationIgnorantKLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

/* JADX INFO: compiled from: KotlinLogging.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lmu/KotlinLogging;", "", "()V", "logger", "Lmu/KLogger;", "func", "Lkotlin/Function0;", "", "name", "", "kotlin-logging"}, k = 1, mv = {1, 1, 13})
public final class KotlinLogging {
    public static final KotlinLogging INSTANCE = new KotlinLogging();

    private KotlinLogging() {
    }

    public final KLogger logger(Function0<Unit> func) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        KLoggerFactory kLoggerFactory = KLoggerFactory.INSTANCE;
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

    public final KLogger logger(String name) {
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
