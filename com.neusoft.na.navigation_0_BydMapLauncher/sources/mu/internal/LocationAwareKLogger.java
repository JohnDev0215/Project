package mu.internal;

import android.car.Car;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

/* JADX INFO: compiled from: LocationAwareKLogger.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010\n\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J'\u0010\n\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\"\u0010\n\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\"\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J1\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u001aJ&\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0018\u0010\u001b\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0012\u0010\u001b\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010\u001b\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010\u001b\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J'\u0010\u001b\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u001c\u0010\u001b\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\"\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\"\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u001c\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J1\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u001aJ&\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0011\u0010\u001c\u001a\n \u001d*\u0004\u0018\u00010\u00070\u0007H\u0096\u0001J\u0018\u0010\u001e\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0012\u0010\u001e\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010\u001e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010\u001e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J'\u0010\u001e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u001c\u0010\u001e\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\"\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\"\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u001c\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J1\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u001aJ&\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\t\u0010\u001f\u001a\u00020 H\u0096\u0001J\u0019\u0010\u001f\u001a\u00020 2\u000e\u0010!\u001a\n \u001d*\u0004\u0018\u00010\u00190\u0019H\u0096\u0001J\t\u0010\"\u001a\u00020 H\u0096\u0001J\u0019\u0010\"\u001a\u00020 2\u000e\u0010!\u001a\n \u001d*\u0004\u0018\u00010\u00190\u0019H\u0096\u0001J\t\u0010#\u001a\u00020 H\u0096\u0001J\u0019\u0010#\u001a\u00020 2\u000e\u0010!\u001a\n \u001d*\u0004\u0018\u00010\u00190\u0019H\u0096\u0001J\t\u0010$\u001a\u00020 H\u0096\u0001J\u0019\u0010$\u001a\u00020 2\u000e\u0010!\u001a\n \u001d*\u0004\u0018\u00010\u00190\u0019H\u0096\u0001J\t\u0010%\u001a\u00020 H\u0096\u0001J\u0019\u0010%\u001a\u00020 2\u000e\u0010!\u001a\n \u001d*\u0004\u0018\u00010\u00190\u0019H\u0096\u0001J\u0018\u0010&\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0012\u0010&\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010&\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010&\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J'\u0010&\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u001c\u0010&\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\"\u0010&\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\"\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u001c\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J1\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u001aJ&\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0018\u0010'\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0012\u0010'\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010'\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010'\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J'\u0010'\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u001c\u0010'\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\"\u0010'\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\"\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u001c\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J1\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u001aJ&\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006("}, d2 = {"Lmu/internal/LocationAwareKLogger;", "Lmu/KLogger;", "Lorg/slf4j/Logger;", "underlyingLogger", "Lorg/slf4j/spi/LocationAwareLogger;", "(Lorg/slf4j/spi/LocationAwareLogger;)V", "fqcn", "", "getUnderlyingLogger", "()Lorg/slf4j/spi/LocationAwareLogger;", "debug", "", NotificationCompat.CATEGORY_MESSAGE, "Lkotlin/Function0;", "", "format", "arg", "arg1", "arg2", "argArray", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", "t", "", "marker", "Lorg/slf4j/Marker;", "(Lorg/slf4j/Marker;Ljava/lang/String;[Ljava/lang/Object;)V", "error", "getName", "kotlin.jvm.PlatformType", Car.INFO_SERVICE, "isDebugEnabled", "", "p0", "isErrorEnabled", "isInfoEnabled", "isTraceEnabled", "isWarnEnabled", "trace", "warn", "kotlin-logging"}, k = 1, mv = {1, 1, 13})
public final class LocationAwareKLogger implements KLogger, Logger {
    private final String fqcn;
    private final LocationAwareLogger underlyingLogger;

    @Override // org.slf4j.Logger
    public String getName() {
        return this.underlyingLogger.getName();
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled() {
        return this.underlyingLogger.isDebugEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled(Marker p0) {
        return this.underlyingLogger.isDebugEnabled(p0);
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled() {
        return this.underlyingLogger.isErrorEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled(Marker p0) {
        return this.underlyingLogger.isErrorEnabled(p0);
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled() {
        return this.underlyingLogger.isInfoEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled(Marker p0) {
        return this.underlyingLogger.isInfoEnabled(p0);
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled() {
        return this.underlyingLogger.isTraceEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled(Marker p0) {
        return this.underlyingLogger.isTraceEnabled(p0);
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled() {
        return this.underlyingLogger.isWarnEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled(Marker p0) {
        return this.underlyingLogger.isWarnEnabled(p0);
    }

    public LocationAwareKLogger(LocationAwareLogger underlyingLogger) {
        Intrinsics.checkParameterIsNotNull(underlyingLogger, "underlyingLogger");
        this.underlyingLogger = underlyingLogger;
        String name = LocationAwareKLogger.class.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "LocationAwareKLogger::class.java.name");
        this.fqcn = name;
    }

    @Override // mu.KLogger
    public LocationAwareLogger getUnderlyingLogger() {
        return this.underlyingLogger;
    }

    @Override // org.slf4j.Logger
    public void trace(String msg) {
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 0, msg, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String format, Object arg) {
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 0, MessageFormatter.format(format, arg).getMessage(), new Object[]{arg}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String format, Object arg1, Object arg2) {
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 0, MessageFormatter.format(format, arg1, arg2).getMessage(), new Object[]{arg1, arg2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String format, Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 0, MessageFormatter.arrayFormat(format, argArray).getMessage(), argArray, null);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String msg, Throwable t) {
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 0, msg, null, t);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String msg) {
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 0, msg, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String format, Object arg) {
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 0, MessageFormatter.format(format, arg).getMessage(), new Object[]{arg}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 0, MessageFormatter.format(format, arg1, arg2).getMessage(), new Object[]{arg1, arg2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String format, Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 0, MessageFormatter.arrayFormat(format, argArray).getMessage(), argArray, null);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String msg, Throwable t) {
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 0, msg, null, t);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String msg) {
        if (getUnderlyingLogger().isDebugEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 10, msg, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String format, Object arg) {
        if (getUnderlyingLogger().isDebugEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 10, MessageFormatter.format(format, arg).getMessage(), new Object[]{arg}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String format, Object arg1, Object arg2) {
        if (getUnderlyingLogger().isDebugEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 10, MessageFormatter.format(format, arg1, arg2).getMessage(), new Object[]{arg1, arg2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String format, Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (getUnderlyingLogger().isDebugEnabled()) {
            FormattingTuple formattingTupleArrayFormat = MessageFormatter.arrayFormat(format, argArray);
            getUnderlyingLogger().log(null, this.fqcn, 10, formattingTupleArrayFormat.getMessage(), formattingTupleArrayFormat.getArgArray(), formattingTupleArrayFormat.getThrowable());
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String msg, Throwable t) {
        if (getUnderlyingLogger().isDebugEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 10, msg, null, t);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String msg) {
        if (getUnderlyingLogger().isDebugEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 10, msg, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String format, Object arg) {
        if (getUnderlyingLogger().isDebugEnabled()) {
            FormattingTuple formattingTuple = MessageFormatter.format(format, arg);
            getUnderlyingLogger().log(marker, this.fqcn, 10, formattingTuple.getMessage(), formattingTuple.getArgArray(), formattingTuple.getThrowable());
        }
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        if (getUnderlyingLogger().isDebugEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 10, MessageFormatter.format(format, arg1, arg2).getMessage(), new Object[]{arg1, arg2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String format, Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (getUnderlyingLogger().isDebugEnabled()) {
            FormattingTuple formattingTupleArrayFormat = MessageFormatter.arrayFormat(format, argArray);
            getUnderlyingLogger().log(marker, this.fqcn, 10, formattingTupleArrayFormat.getMessage(), argArray, formattingTupleArrayFormat.getThrowable());
        }
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String msg, Throwable t) {
        if (getUnderlyingLogger().isDebugEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 10, msg, null, t);
        }
    }

    @Override // org.slf4j.Logger
    public void info(String msg) {
        if (getUnderlyingLogger().isInfoEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 20, msg, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void info(String format, Object arg) {
        if (getUnderlyingLogger().isInfoEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 20, MessageFormatter.format(format, arg).getMessage(), new Object[]{arg}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void info(String format, Object arg1, Object arg2) {
        if (getUnderlyingLogger().isInfoEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 20, MessageFormatter.format(format, arg1, arg2).getMessage(), new Object[]{arg1, arg2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void info(String format, Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (getUnderlyingLogger().isInfoEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 20, MessageFormatter.arrayFormat(format, argArray).getMessage(), argArray, null);
        }
    }

    @Override // org.slf4j.Logger
    public void info(String msg, Throwable t) {
        if (getUnderlyingLogger().isInfoEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 20, msg, null, t);
        }
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String msg) {
        if (getUnderlyingLogger().isInfoEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 20, msg, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String format, Object arg) {
        if (getUnderlyingLogger().isInfoEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 20, MessageFormatter.format(format, arg).getMessage(), new Object[]{arg}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        if (getUnderlyingLogger().isInfoEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 20, MessageFormatter.format(format, arg1, arg2).getMessage(), new Object[]{arg1, arg2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String format, Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (getUnderlyingLogger().isInfoEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 20, MessageFormatter.arrayFormat(format, argArray).getMessage(), argArray, null);
        }
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String msg, Throwable t) {
        if (getUnderlyingLogger().isInfoEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 20, msg, null, t);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String msg) {
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 30, msg, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String format, Object arg) {
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 30, MessageFormatter.format(format, arg).getMessage(), new Object[]{arg}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String format, Object arg1, Object arg2) {
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 30, MessageFormatter.format(format, arg1, arg2).getMessage(), new Object[]{arg1, arg2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String format, Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 30, MessageFormatter.arrayFormat(format, argArray).getMessage(), argArray, null);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String msg, Throwable t) {
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 30, msg, null, t);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String msg) {
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 30, msg, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String format, Object arg) {
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 30, MessageFormatter.format(format, arg).getMessage(), new Object[]{arg}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 30, MessageFormatter.format(format, arg1, arg2).getMessage(), new Object[]{arg1, arg2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String format, Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 30, MessageFormatter.arrayFormat(format, argArray).getMessage(), argArray, null);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String msg, Throwable t) {
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 30, msg, null, t);
        }
    }

    @Override // org.slf4j.Logger
    public void error(String msg) {
        if (getUnderlyingLogger().isErrorEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 40, msg, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void error(String format, Object arg) {
        if (getUnderlyingLogger().isErrorEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 40, MessageFormatter.format(format, arg).getMessage(), new Object[]{arg}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void error(String format, Object arg1, Object arg2) {
        if (getUnderlyingLogger().isErrorEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 40, MessageFormatter.format(format, arg1, arg2).getMessage(), new Object[]{arg1, arg2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void error(String format, Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (getUnderlyingLogger().isErrorEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 40, MessageFormatter.arrayFormat(format, argArray).getMessage(), argArray, null);
        }
    }

    @Override // org.slf4j.Logger
    public void error(String msg, Throwable t) {
        if (getUnderlyingLogger().isErrorEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 40, msg, null, t);
        }
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String msg) {
        if (getUnderlyingLogger().isErrorEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 40, msg, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String format, Object arg) {
        if (getUnderlyingLogger().isErrorEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 40, MessageFormatter.format(format, arg).getMessage(), new Object[]{arg}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        if (getUnderlyingLogger().isErrorEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 40, MessageFormatter.format(format, arg1, arg2).getMessage(), new Object[]{arg1, arg2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String format, Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (getUnderlyingLogger().isErrorEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 40, MessageFormatter.arrayFormat(format, argArray).getMessage(), argArray, null);
        }
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String msg, Throwable t) {
        if (getUnderlyingLogger().isErrorEnabled()) {
            getUnderlyingLogger().log(marker, this.fqcn, 40, msg, null, t);
        }
    }

    @Override // mu.KLogger
    public void trace(Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isTraceEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            trace(strValueOf);
        }
    }

    @Override // mu.KLogger
    public void debug(Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isDebugEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            debug(strValueOf);
        }
    }

    @Override // mu.KLogger
    public void info(Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isInfoEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            info(strValueOf);
        }
    }

    @Override // mu.KLogger
    public void warn(Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isWarnEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            warn(strValueOf);
        }
    }

    @Override // mu.KLogger
    public void error(Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isErrorEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            error(strValueOf);
        }
    }

    @Override // mu.KLogger
    public void trace(Throwable t, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isTraceEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            trace(strValueOf, t);
        }
    }

    @Override // mu.KLogger
    public void debug(Throwable t, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isDebugEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            debug(strValueOf, t);
        }
    }

    @Override // mu.KLogger
    public void info(Throwable t, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isInfoEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            info(strValueOf, t);
        }
    }

    @Override // mu.KLogger
    public void warn(Throwable t, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isWarnEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            warn(strValueOf, t);
        }
    }

    @Override // mu.KLogger
    public void error(Throwable t, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isErrorEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            error(strValueOf, t);
        }
    }

    @Override // mu.KLogger
    public void trace(Marker marker, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isTraceEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            trace(marker, strValueOf);
        }
    }

    @Override // mu.KLogger
    public void debug(Marker marker, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isDebugEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            debug(marker, strValueOf);
        }
    }

    @Override // mu.KLogger
    public void info(Marker marker, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isInfoEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            info(marker, strValueOf);
        }
    }

    @Override // mu.KLogger
    public void warn(Marker marker, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isWarnEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            warn(marker, strValueOf);
        }
    }

    @Override // mu.KLogger
    public void error(Marker marker, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isErrorEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            error(marker, strValueOf);
        }
    }

    @Override // mu.KLogger
    public void trace(Marker marker, Throwable t, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isTraceEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            trace(marker, strValueOf, t);
        }
    }

    @Override // mu.KLogger
    public void debug(Marker marker, Throwable t, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isDebugEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            debug(marker, strValueOf, t);
        }
    }

    @Override // mu.KLogger
    public void info(Marker marker, Throwable t, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isInfoEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            info(marker, strValueOf, t);
        }
    }

    @Override // mu.KLogger
    public void warn(Marker marker, Throwable t, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isWarnEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            warn(marker, strValueOf, t);
        }
    }

    @Override // mu.KLogger
    public void error(Marker marker, Throwable t, Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (isErrorEnabled()) {
            try {
                strValueOf = String.valueOf(msg.invoke());
            } catch (Exception e) {
                strValueOf = "Log message invocation failed: " + e;
            }
            error(marker, strValueOf, t);
        }
    }
}
