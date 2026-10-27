package mu;

import android.car.Car;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.slf4j.Logger;
import org.slf4j.Marker;

/* JADX INFO: compiled from: KLogger.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\"\u0010\u0005\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J(\u0010\u0005\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J2\u0010\u0005\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\u0018\u0010\u000f\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\"\u0010\u000f\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J(\u0010\u000f\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J2\u0010\u000f\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\u0018\u0010\u0010\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\"\u0010\u0010\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J(\u0010\u0010\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J2\u0010\u0010\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\u0018\u0010\u0011\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\"\u0010\u0011\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J(\u0010\u0011\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J2\u0010\u0011\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\u0018\u0010\u0012\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J\"\u0010\u0012\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J(\u0010\u0012\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&J2\u0010\u0012\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH&R\u0012\u0010\u0002\u001a\u00020\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0013"}, d2 = {"Lmu/KLogger;", "Lorg/slf4j/Logger;", "underlyingLogger", "getUnderlyingLogger", "()Lorg/slf4j/Logger;", "debug", "", NotificationCompat.CATEGORY_MESSAGE, "Lkotlin/Function0;", "", "t", "", "marker", "Lorg/slf4j/Marker;", "Lmu/Marker;", "error", Car.INFO_SERVICE, "trace", "warn", "kotlin-logging"}, k = 1, mv = {1, 1, 13})
public interface KLogger extends Logger {
    void debug(Throwable t, Function0<? extends Object> msg);

    void debug(Function0<? extends Object> msg);

    void debug(Marker marker, Throwable t, Function0<? extends Object> msg);

    void debug(Marker marker, Function0<? extends Object> msg);

    void error(Throwable t, Function0<? extends Object> msg);

    void error(Function0<? extends Object> msg);

    void error(Marker marker, Throwable t, Function0<? extends Object> msg);

    void error(Marker marker, Function0<? extends Object> msg);

    Logger getUnderlyingLogger();

    void info(Throwable t, Function0<? extends Object> msg);

    void info(Function0<? extends Object> msg);

    void info(Marker marker, Throwable t, Function0<? extends Object> msg);

    void info(Marker marker, Function0<? extends Object> msg);

    void trace(Throwable t, Function0<? extends Object> msg);

    void trace(Function0<? extends Object> msg);

    void trace(Marker marker, Throwable t, Function0<? extends Object> msg);

    void trace(Marker marker, Function0<? extends Object> msg);

    void warn(Throwable t, Function0<? extends Object> msg);

    void warn(Function0<? extends Object> msg);

    void warn(Marker marker, Throwable t, Function0<? extends Object> msg);

    void warn(Marker marker, Function0<? extends Object> msg);
}
