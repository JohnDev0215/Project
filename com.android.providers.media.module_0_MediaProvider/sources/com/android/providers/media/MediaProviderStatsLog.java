package com.android.providers.media;

import android.util.StatsEvent;
import android.util.StatsLog;

/* JADX INFO: loaded from: classes.dex */
public class MediaProviderStatsLog {
    public static final byte ANNOTATION_ID_EXCLUSIVE_STATE = 4;
    public static final byte ANNOTATION_ID_IS_UID = 1;
    public static final byte ANNOTATION_ID_PRIMARY_FIELD = 3;
    public static final byte ANNOTATION_ID_PRIMARY_FIELD_FIRST_UID = 5;
    public static final byte ANNOTATION_ID_STATE_NESTED = 8;
    public static final byte ANNOTATION_ID_TRIGGER_STATE_RESET = 7;
    public static final byte ANNOTATION_ID_TRUNCATE_TIMESTAMP = 2;
    public static final int GENERAL_EXTERNAL_STORAGE_ACCESS_STATS = 10085;
    public static final int MEDIA_CONTENT_DELETED = 234;
    public static final int MEDIA_CONTENT_DELETED__VOLUME_TYPE__EXTERNAL_OTHER = 3;
    public static final int MEDIA_CONTENT_DELETED__VOLUME_TYPE__EXTERNAL_PRIMARY = 2;
    public static final int MEDIA_CONTENT_DELETED__VOLUME_TYPE__INTERNAL = 1;
    public static final int MEDIA_CONTENT_DELETED__VOLUME_TYPE__UNKNOWN = 0;
    public static final int MEDIA_PROVIDER_IDLE_MAINTENANCE_FINISHED = 237;
    public static final int MEDIA_PROVIDER_IDLE_MAINTENANCE_FINISHED__VOLUME_TYPE__EXTERNAL_OTHER = 3;
    public static final int MEDIA_PROVIDER_IDLE_MAINTENANCE_FINISHED__VOLUME_TYPE__EXTERNAL_PRIMARY = 2;
    public static final int MEDIA_PROVIDER_IDLE_MAINTENANCE_FINISHED__VOLUME_TYPE__INTERNAL = 1;
    public static final int MEDIA_PROVIDER_IDLE_MAINTENANCE_FINISHED__VOLUME_TYPE__UNKNOWN = 0;
    public static final int MEDIA_PROVIDER_PERMISSION_REQUESTED = 235;
    public static final int MEDIA_PROVIDER_PERMISSION_REQUESTED__RESULT__AUTO_DENIED = 5;
    public static final int MEDIA_PROVIDER_PERMISSION_REQUESTED__RESULT__AUTO_GRANTED = 2;
    public static final int MEDIA_PROVIDER_PERMISSION_REQUESTED__RESULT__UNKNOWN = 0;
    public static final int MEDIA_PROVIDER_PERMISSION_REQUESTED__RESULT__USER_DENIED = 3;
    public static final int MEDIA_PROVIDER_PERMISSION_REQUESTED__RESULT__USER_DENIED_WITH_PREJUDICE = 4;
    public static final int MEDIA_PROVIDER_PERMISSION_REQUESTED__RESULT__USER_GRANTED = 1;
    public static final int MEDIA_PROVIDER_PERMISSION_REQUESTED__VOLUME_TYPE__EXTERNAL_OTHER = 3;
    public static final int MEDIA_PROVIDER_PERMISSION_REQUESTED__VOLUME_TYPE__EXTERNAL_PRIMARY = 2;
    public static final int MEDIA_PROVIDER_PERMISSION_REQUESTED__VOLUME_TYPE__INTERNAL = 1;
    public static final int MEDIA_PROVIDER_PERMISSION_REQUESTED__VOLUME_TYPE__UNKNOWN = 0;
    public static final int MEDIA_PROVIDER_SCAN_OCCURRED = 233;
    public static final int MEDIA_PROVIDER_SCAN_OCCURRED__REASON__DEMAND = 2;
    public static final int MEDIA_PROVIDER_SCAN_OCCURRED__REASON__IDLE = 3;
    public static final int MEDIA_PROVIDER_SCAN_OCCURRED__REASON__MOUNTED = 1;
    public static final int MEDIA_PROVIDER_SCAN_OCCURRED__REASON__UNKNOWN = 0;
    public static final int MEDIA_PROVIDER_SCAN_OCCURRED__VOLUME_TYPE__EXTERNAL_OTHER = 3;
    public static final int MEDIA_PROVIDER_SCAN_OCCURRED__VOLUME_TYPE__EXTERNAL_PRIMARY = 2;
    public static final int MEDIA_PROVIDER_SCAN_OCCURRED__VOLUME_TYPE__INTERNAL = 1;
    public static final int MEDIA_PROVIDER_SCAN_OCCURRED__VOLUME_TYPE__UNKNOWN = 0;
    public static final int MEDIA_PROVIDER_SCHEMA_CHANGED = 236;
    public static final int MEDIA_PROVIDER_SCHEMA_CHANGED__VOLUME_TYPE__EXTERNAL_OTHER = 3;
    public static final int MEDIA_PROVIDER_SCHEMA_CHANGED__VOLUME_TYPE__EXTERNAL_PRIMARY = 2;
    public static final int MEDIA_PROVIDER_SCHEMA_CHANGED__VOLUME_TYPE__INTERNAL = 1;
    public static final int MEDIA_PROVIDER_SCHEMA_CHANGED__VOLUME_TYPE__UNKNOWN = 0;

    public static void write(int i) {
        StatsEvent.Builder builderNewBuilder = StatsEvent.newBuilder();
        builderNewBuilder.setAtomId(i);
        builderNewBuilder.usePooledBuffer();
        StatsLog.write(builderNewBuilder.build());
    }

    public static void write(int i, int i2, int i3, int i4) {
        StatsEvent.Builder builderNewBuilder = StatsEvent.newBuilder();
        builderNewBuilder.setAtomId(i);
        builderNewBuilder.writeInt(i2);
        builderNewBuilder.writeInt(i3);
        if (234 == i) {
            builderNewBuilder.addBooleanAnnotation((byte) 1, true);
        }
        builderNewBuilder.writeInt(i4);
        builderNewBuilder.usePooledBuffer();
        StatsLog.write(builderNewBuilder.build());
    }

    public static void write(int i, int i2, int i3, int i4, int i5) {
        StatsEvent.Builder builderNewBuilder = StatsEvent.newBuilder();
        builderNewBuilder.setAtomId(i);
        builderNewBuilder.writeInt(i2);
        builderNewBuilder.writeInt(i3);
        if (235 == i) {
            builderNewBuilder.addBooleanAnnotation((byte) 1, true);
        }
        builderNewBuilder.writeInt(i4);
        builderNewBuilder.writeInt(i5);
        builderNewBuilder.usePooledBuffer();
        StatsLog.write(builderNewBuilder.build());
    }

    public static void write(int i, int i2, int i3, int i4, long j, float f) {
        StatsEvent.Builder builderNewBuilder = StatsEvent.newBuilder();
        builderNewBuilder.setAtomId(i);
        builderNewBuilder.writeInt(i2);
        builderNewBuilder.writeInt(i3);
        builderNewBuilder.writeInt(i4);
        builderNewBuilder.writeLong(j);
        builderNewBuilder.writeFloat(f);
        builderNewBuilder.usePooledBuffer();
        StatsLog.write(builderNewBuilder.build());
    }

    public static void write(int i, int i2, int i3, long j, float f, float f2, float f3, float f4) {
        StatsEvent.Builder builderNewBuilder = StatsEvent.newBuilder();
        builderNewBuilder.setAtomId(i);
        builderNewBuilder.writeInt(i2);
        builderNewBuilder.writeInt(i3);
        builderNewBuilder.writeLong(j);
        builderNewBuilder.writeFloat(f);
        builderNewBuilder.writeFloat(f2);
        builderNewBuilder.writeFloat(f3);
        builderNewBuilder.writeFloat(f4);
        builderNewBuilder.usePooledBuffer();
        StatsLog.write(builderNewBuilder.build());
    }

    public static void write(int i, int i2, long j, float f, float f2, float f3) {
        StatsEvent.Builder builderNewBuilder = StatsEvent.newBuilder();
        builderNewBuilder.setAtomId(i);
        builderNewBuilder.writeInt(i2);
        builderNewBuilder.writeLong(j);
        builderNewBuilder.writeFloat(f);
        builderNewBuilder.writeFloat(f2);
        builderNewBuilder.writeFloat(f3);
        builderNewBuilder.usePooledBuffer();
        StatsLog.write(builderNewBuilder.build());
    }
}
