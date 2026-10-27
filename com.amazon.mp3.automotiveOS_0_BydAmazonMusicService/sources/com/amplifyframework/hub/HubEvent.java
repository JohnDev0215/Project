package com.amplifyframework.hub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes5.dex */
public final class HubEvent<T> {
    private final T data;
    private final String name;
    private final UUID uuid = UUID.randomUUID();

    public interface Data<T> {
        HubEvent<T> toHubEvent();
    }

    private HubEvent(@NonNull String str, @Nullable T t) {
        this.name = str;
        this.data = t;
    }

    @NonNull
    public static HubEvent<?> create(@NonNull String str) {
        return new HubEvent<>(str, null);
    }

    @NonNull
    public static <E extends Enum<E>> HubEvent<?> create(@NonNull E e) {
        Objects.requireNonNull(e);
        return new HubEvent<>(e.toString(), null);
    }

    @NonNull
    public static <T> HubEvent<T> create(@NonNull String str, @NonNull T t) {
        return new HubEvent<>(str, t);
    }

    @NonNull
    public static <T, E extends Enum<E>> HubEvent<T> create(@NonNull E e, @NonNull T t) {
        Objects.requireNonNull(e);
        Objects.requireNonNull(t);
        return new HubEvent<>(e.toString(), t);
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @Nullable
    public T getData() {
        return this.data;
    }

    @NonNull
    public UUID getId() {
        return this.uuid;
    }

    @NonNull
    public String toString() {
        return "HubEvent{name='" + this.name + "', data=" + this.data + ", uuid=" + this.uuid + '}';
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || HubEvent.class != obj.getClass()) {
            return false;
        }
        HubEvent hubEvent = (HubEvent) obj;
        if (ObjectsCompat.equals(this.name, hubEvent.name) && ObjectsCompat.equals(this.data, hubEvent.data)) {
            return ObjectsCompat.equals(this.uuid, hubEvent.uuid);
        }
        return false;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        T t = this.data;
        int iHashCode2 = (iHashCode + (t != null ? t.hashCode() : 0)) * 31;
        UUID uuid = this.uuid;
        return iHashCode2 + (uuid != null ? uuid.hashCode() : 0);
    }
}
