package mu.internal;

import android.car.Car;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import org.slf4j.Logger;
import org.slf4j.Marker;

/* JADX INFO: compiled from: LocationIgnorantKLogger.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J\u0019\u0010\u0007\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0096\u0001J)\u0010\u0007\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001J9\u0010\u0007\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000b2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001JX\u0010\u0007\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r28\u0010\u000f\u001a(\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b \u000e*\u0014\u0012\u000e\b\u0001\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u00110\u0011\"\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001¢\u0006\u0002\u0010\u0012J)\u0010\u0007\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J\"\u0010\u0007\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J)\u0010\u0007\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0096\u0001J9\u0010\u0007\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001JI\u0010\u0007\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000b2\u000e\u0010\u0016\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001Jh\u0010\u0007\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r28\u0010\u0010\u001a(\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b \u000e*\u0014\u0012\u000e\b\u0001\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u00110\u0011\"\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001¢\u0006\u0002\u0010\u0017J9\u0010\u0007\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J\"\u0010\u0007\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00152\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J,\u0010\u0007\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J\u0018\u0010\u0019\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J\u0019\u0010\u0019\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0096\u0001J)\u0010\u0019\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001J9\u0010\u0019\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000b2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001JX\u0010\u0019\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r28\u0010\u000f\u001a(\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b \u000e*\u0014\u0012\u000e\b\u0001\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u00110\u0011\"\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001¢\u0006\u0002\u0010\u0012J)\u0010\u0019\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J\"\u0010\u0019\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J)\u0010\u0019\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0096\u0001J9\u0010\u0019\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001JI\u0010\u0019\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000b2\u000e\u0010\u0016\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001Jh\u0010\u0019\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r28\u0010\u0010\u001a(\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b \u000e*\u0014\u0012\u000e\b\u0001\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u00110\u0011\"\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001¢\u0006\u0002\u0010\u0017J9\u0010\u0019\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J\"\u0010\u0019\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00152\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J,\u0010\u0019\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J\u0011\u0010\u001a\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0096\u0001J\u0018\u0010\u001b\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J\u0019\u0010\u001b\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0096\u0001J)\u0010\u001b\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001J9\u0010\u001b\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000b2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001JX\u0010\u001b\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r28\u0010\u000f\u001a(\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b \u000e*\u0014\u0012\u000e\b\u0001\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u00110\u0011\"\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001¢\u0006\u0002\u0010\u0012J)\u0010\u001b\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J\"\u0010\u001b\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J)\u0010\u001b\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0096\u0001J9\u0010\u001b\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001JI\u0010\u001b\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000b2\u000e\u0010\u0016\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001Jh\u0010\u001b\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r28\u0010\u0010\u001a(\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b \u000e*\u0014\u0012\u000e\b\u0001\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u00110\u0011\"\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001¢\u0006\u0002\u0010\u0017J9\u0010\u001b\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J\"\u0010\u001b\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00152\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J,\u0010\u001b\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J\t\u0010\u001c\u001a\u00020\u001dH\u0096\u0001J\u0019\u0010\u001c\u001a\u00020\u001d2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u0015H\u0096\u0001J\t\u0010\u001e\u001a\u00020\u001dH\u0096\u0001J\u0019\u0010\u001e\u001a\u00020\u001d2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u0015H\u0096\u0001J\t\u0010\u001f\u001a\u00020\u001dH\u0096\u0001J\u0019\u0010\u001f\u001a\u00020\u001d2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u0015H\u0096\u0001J\t\u0010 \u001a\u00020\u001dH\u0096\u0001J\u0019\u0010 \u001a\u00020\u001d2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u0015H\u0096\u0001J\t\u0010!\u001a\u00020\u001dH\u0096\u0001J\u0019\u0010!\u001a\u00020\u001d2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u0015H\u0096\u0001J\u0018\u0010\"\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J\u0019\u0010\"\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0096\u0001J)\u0010\"\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001J9\u0010\"\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000b2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001JX\u0010\"\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r28\u0010\u000f\u001a(\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b \u000e*\u0014\u0012\u000e\b\u0001\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u00110\u0011\"\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001¢\u0006\u0002\u0010\u0012J)\u0010\"\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J\"\u0010\"\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J)\u0010\"\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0096\u0001J9\u0010\"\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001JI\u0010\"\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000b2\u000e\u0010\u0016\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001Jh\u0010\"\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r28\u0010\u0010\u001a(\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b \u000e*\u0014\u0012\u000e\b\u0001\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u00110\u0011\"\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001¢\u0006\u0002\u0010\u0017J9\u0010\"\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J\"\u0010\"\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00152\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J,\u0010\"\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J\u0018\u0010#\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J\u0019\u0010#\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0096\u0001J)\u0010#\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001J9\u0010#\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000b2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001JX\u0010#\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r28\u0010\u000f\u001a(\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b \u000e*\u0014\u0012\u000e\b\u0001\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u00110\u0011\"\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001¢\u0006\u0002\u0010\u0012J)\u0010#\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J\"\u0010#\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J)\u0010#\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\rH\u0096\u0001J9\u0010#\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001JI\u0010#\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000b2\u000e\u0010\u0016\u001a\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001Jh\u0010#\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r28\u0010\u0010\u001a(\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b \u000e*\u0014\u0012\u000e\b\u0001\u0012\n \u000e*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u00110\u0011\"\n \u000e*\u0004\u0018\u00010\u000b0\u000bH\u0096\u0001¢\u0006\u0002\u0010\u0017J9\u0010#\u001a\u00020\b2\u000e\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\u00150\u00152\u000e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r2\u000e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J\"\u0010#\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00152\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J,\u0010#\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016R\u0014\u0010\u0003\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006$"}, d2 = {"Lmu/internal/LocationIgnorantKLogger;", "Lmu/KLogger;", "Lorg/slf4j/Logger;", "underlyingLogger", "(Lorg/slf4j/Logger;)V", "getUnderlyingLogger", "()Lorg/slf4j/Logger;", "debug", "", NotificationCompat.CATEGORY_MESSAGE, "Lkotlin/Function0;", "", "p0", "", "kotlin.jvm.PlatformType", "p1", "p2", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", "", "t", "Lorg/slf4j/Marker;", "p3", "(Lorg/slf4j/Marker;Ljava/lang/String;[Ljava/lang/Object;)V", "marker", "error", "getName", Car.INFO_SERVICE, "isDebugEnabled", "", "isErrorEnabled", "isInfoEnabled", "isTraceEnabled", "isWarnEnabled", "trace", "warn", "kotlin-logging"}, k = 1, mv = {1, 1, 13})
public final class LocationIgnorantKLogger implements KLogger, Logger {
    private final Logger underlyingLogger;

    @Override // org.slf4j.Logger
    public void debug(String p0) {
        this.underlyingLogger.debug(p0);
    }

    @Override // org.slf4j.Logger
    public void debug(String p0, Object p1) {
        this.underlyingLogger.debug(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void debug(String p0, Object p1, Object p2) {
        this.underlyingLogger.debug(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void debug(String p0, Throwable p1) {
        this.underlyingLogger.debug(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void debug(String p0, Object... p1) {
        this.underlyingLogger.debug(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker p0, String p1) {
        this.underlyingLogger.debug(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker p0, String p1, Object p2) {
        this.underlyingLogger.debug(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker p0, String p1, Object p2, Object p3) {
        this.underlyingLogger.debug(p0, p1, p2, p3);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker p0, String p1, Throwable p2) {
        this.underlyingLogger.debug(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker p0, String p1, Object... p2) {
        this.underlyingLogger.debug(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void error(String p0) {
        this.underlyingLogger.error(p0);
    }

    @Override // org.slf4j.Logger
    public void error(String p0, Object p1) {
        this.underlyingLogger.error(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void error(String p0, Object p1, Object p2) {
        this.underlyingLogger.error(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void error(String p0, Throwable p1) {
        this.underlyingLogger.error(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void error(String p0, Object... p1) {
        this.underlyingLogger.error(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void error(Marker p0, String p1) {
        this.underlyingLogger.error(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void error(Marker p0, String p1, Object p2) {
        this.underlyingLogger.error(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void error(Marker p0, String p1, Object p2, Object p3) {
        this.underlyingLogger.error(p0, p1, p2, p3);
    }

    @Override // org.slf4j.Logger
    public void error(Marker p0, String p1, Throwable p2) {
        this.underlyingLogger.error(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void error(Marker p0, String p1, Object... p2) {
        this.underlyingLogger.error(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public String getName() {
        return this.underlyingLogger.getName();
    }

    @Override // org.slf4j.Logger
    public void info(String p0) {
        this.underlyingLogger.info(p0);
    }

    @Override // org.slf4j.Logger
    public void info(String p0, Object p1) {
        this.underlyingLogger.info(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void info(String p0, Object p1, Object p2) {
        this.underlyingLogger.info(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void info(String p0, Throwable p1) {
        this.underlyingLogger.info(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void info(String p0, Object... p1) {
        this.underlyingLogger.info(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void info(Marker p0, String p1) {
        this.underlyingLogger.info(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void info(Marker p0, String p1, Object p2) {
        this.underlyingLogger.info(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void info(Marker p0, String p1, Object p2, Object p3) {
        this.underlyingLogger.info(p0, p1, p2, p3);
    }

    @Override // org.slf4j.Logger
    public void info(Marker p0, String p1, Throwable p2) {
        this.underlyingLogger.info(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void info(Marker p0, String p1, Object... p2) {
        this.underlyingLogger.info(p0, p1, p2);
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

    @Override // org.slf4j.Logger
    public void trace(String p0) {
        this.underlyingLogger.trace(p0);
    }

    @Override // org.slf4j.Logger
    public void trace(String p0, Object p1) {
        this.underlyingLogger.trace(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void trace(String p0, Object p1, Object p2) {
        this.underlyingLogger.trace(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void trace(String p0, Throwable p1) {
        this.underlyingLogger.trace(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void trace(String p0, Object... p1) {
        this.underlyingLogger.trace(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker p0, String p1) {
        this.underlyingLogger.trace(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker p0, String p1, Object p2) {
        this.underlyingLogger.trace(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker p0, String p1, Object p2, Object p3) {
        this.underlyingLogger.trace(p0, p1, p2, p3);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker p0, String p1, Throwable p2) {
        this.underlyingLogger.trace(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker p0, String p1, Object... p2) {
        this.underlyingLogger.trace(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void warn(String p0) {
        this.underlyingLogger.warn(p0);
    }

    @Override // org.slf4j.Logger
    public void warn(String p0, Object p1) {
        this.underlyingLogger.warn(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void warn(String p0, Object p1, Object p2) {
        this.underlyingLogger.warn(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void warn(String p0, Throwable p1) {
        this.underlyingLogger.warn(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void warn(String p0, Object... p1) {
        this.underlyingLogger.warn(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker p0, String p1) {
        this.underlyingLogger.warn(p0, p1);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker p0, String p1, Object p2) {
        this.underlyingLogger.warn(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker p0, String p1, Object p2, Object p3) {
        this.underlyingLogger.warn(p0, p1, p2, p3);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker p0, String p1, Throwable p2) {
        this.underlyingLogger.warn(p0, p1, p2);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker p0, String p1, Object... p2) {
        this.underlyingLogger.warn(p0, p1, p2);
    }

    public LocationIgnorantKLogger(Logger underlyingLogger) {
        Intrinsics.checkParameterIsNotNull(underlyingLogger, "underlyingLogger");
        this.underlyingLogger = underlyingLogger;
    }

    @Override // mu.KLogger
    public Logger getUnderlyingLogger() {
        return this.underlyingLogger;
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
