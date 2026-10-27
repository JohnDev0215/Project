package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import com.fasterxml.uuid.Generators;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes5.dex */
final class TimeBasedUuid implements Comparable<TimeBasedUuid> {
    private final UUID delegate;

    private TimeBasedUuid(UUID uuid) {
        this.delegate = uuid;
    }

    static TimeBasedUuid create() {
        UUID uuidGenerate = Generators.timeBasedGenerator().generate();
        validateVersion(uuidGenerate);
        return new TimeBasedUuid(uuidGenerate);
    }

    static TimeBasedUuid fromString(@NonNull String str) {
        Objects.requireNonNull(str);
        UUID uuidFromString = UUID.fromString(str);
        validateVersion(uuidFromString);
        return new TimeBasedUuid(uuidFromString);
    }

    private static void validateVersion(UUID uuid) {
        if (1 != uuid.version()) {
            throw new IllegalStateException("Found UUID that is not a V1, time-based, UUID.");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TimeBasedUuid.class != obj.getClass()) {
            return false;
        }
        return this.delegate.equals(((TimeBasedUuid) obj).delegate);
    }

    public int hashCode() {
        return this.delegate.hashCode();
    }

    public String toString() {
        return this.delegate.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull TimeBasedUuid timeBasedUuid) {
        Objects.requireNonNull(timeBasedUuid);
        return (int) Math.signum(this.delegate.timestamp() - timeBasedUuid.delegate.timestamp());
    }
}
