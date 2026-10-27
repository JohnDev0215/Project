package com.fasterxml.uuid;

import com.fasterxml.uuid.impl.TimeBasedGenerator;
import java.io.IOException;
import java.util.Random;

/* JADX INFO: loaded from: classes6.dex */
public class Generators {
    protected static UUIDTimer _sharedTimer;

    public static TimeBasedGenerator timeBasedGenerator() {
        return timeBasedGenerator(null);
    }

    public static TimeBasedGenerator timeBasedGenerator(EthernetAddress ethernetAddress) {
        return timeBasedGenerator(ethernetAddress, null);
    }

    public static TimeBasedGenerator timeBasedGenerator(EthernetAddress ethernetAddress, UUIDTimer uUIDTimer) {
        if (uUIDTimer == null) {
            uUIDTimer = sharedTimer();
        }
        return new TimeBasedGenerator(ethernetAddress, uUIDTimer);
    }

    private static synchronized UUIDTimer sharedTimer() {
        if (_sharedTimer == null) {
            try {
                _sharedTimer = new UUIDTimer(new Random(System.currentTimeMillis()), null);
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to create UUIDTimer with specified synchronizer: " + e.getMessage(), e);
            }
        }
        return _sharedTimer;
    }
}
