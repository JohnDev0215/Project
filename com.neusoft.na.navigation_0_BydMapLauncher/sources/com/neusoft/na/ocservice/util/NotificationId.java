package com.neusoft.na.ocservice.util;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationHelper.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/neusoft/na/ocservice/util/NotificationId;", "", "builder", "Landroidx/core/app/NotificationCompat$Builder;", "id", "", "(Landroidx/core/app/NotificationCompat$Builder;I)V", "getBuilder", "()Landroidx/core/app/NotificationCompat$Builder;", "getId", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final /* data */ class NotificationId {
    private final NotificationCompat.Builder builder;
    private final int id;

    public static /* synthetic */ NotificationId copy$default(NotificationId notificationId, NotificationCompat.Builder builder, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            builder = notificationId.builder;
        }
        if ((i2 & 2) != 0) {
            i = notificationId.id;
        }
        return notificationId.copy(builder, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final NotificationCompat.Builder getBuilder() {
        return this.builder;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public final NotificationId copy(NotificationCompat.Builder builder, int id) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        return new NotificationId(builder, id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NotificationId)) {
            return false;
        }
        NotificationId notificationId = (NotificationId) other;
        return Intrinsics.areEqual(this.builder, notificationId.builder) && this.id == notificationId.id;
    }

    public int hashCode() {
        return (this.builder.hashCode() * 31) + Integer.hashCode(this.id);
    }

    public String toString() {
        return "NotificationId(builder=" + this.builder + ", id=" + this.id + ')';
    }

    public NotificationId(NotificationCompat.Builder builder, int i) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
        this.id = i;
    }

    public final NotificationCompat.Builder getBuilder() {
        return this.builder;
    }

    public final int getId() {
        return this.id;
    }
}
