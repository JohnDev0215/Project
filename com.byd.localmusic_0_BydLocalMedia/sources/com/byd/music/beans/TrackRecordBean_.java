package com.byd.music.beans;

import android.provider.Telephony;
import com.byd.music.beans.TrackRecordBeanCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX INFO: loaded from: classes5.dex */
public final class TrackRecordBean_ implements EntityInfo<TrackRecordBean> {
    public static final Property<TrackRecordBean>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "TrackRecordBean";
    public static final int __ENTITY_ID = 5;
    public static final String __ENTITY_NAME = "TrackRecordBean";
    public static final Property<TrackRecordBean> __ID_PROPERTY;
    public static final Property<TrackRecordBean> totalDuration;
    public static final Class<TrackRecordBean> __ENTITY_CLASS = TrackRecordBean.class;
    public static final CursorFactory<TrackRecordBean> __CURSOR_FACTORY = new TrackRecordBeanCursor.Factory();
    static final TrackRecordBeanIdGetter __ID_GETTER = new TrackRecordBeanIdGetter();
    public static final TrackRecordBean_ __INSTANCE = new TrackRecordBean_();
    public static final Property<TrackRecordBean> id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<TrackRecordBean> track_id = new Property<>(__INSTANCE, 1, 2, Long.TYPE, "track_id");
    public static final Property<TrackRecordBean> duration = new Property<>(__INSTANCE, 2, 3, Double.TYPE, "duration");
    public static final Property<TrackRecordBean> played_secs = new Property<>(__INSTANCE, 3, 4, Double.TYPE, "played_secs");
    public static final Property<TrackRecordBean> started_at = new Property<>(__INSTANCE, 4, 5, Long.TYPE, "started_at");
    public static final Property<TrackRecordBean> ended_at = new Property<>(__INSTANCE, 5, 6, Long.TYPE, "ended_at");
    public static final Property<TrackRecordBean> play_type = new Property<>(__INSTANCE, 6, 7, Integer.TYPE, "play_type");
    public static final Property<TrackRecordBean> rec_src = new Property<>(__INSTANCE, 7, 8, String.class, "rec_src");
    public static final Property<TrackRecordBean> rec_track = new Property<>(__INSTANCE, 8, 9, String.class, "rec_track");
    public static final Property<TrackRecordBean> play_source = new Property<>(__INSTANCE, 9, 10, String.class, "play_source");
    public static final Property<TrackRecordBean> album_title = new Property<>(__INSTANCE, 10, 11, String.class, "album_title");
    public static final Property<TrackRecordBean> track_title = new Property<>(__INSTANCE, 11, 12, String.class, "track_title");
    public static final Property<TrackRecordBean> imgUrl = new Property<>(__INSTANCE, 12, 13, String.class, "imgUrl");
    public static final Property<TrackRecordBean> album_id = new Property<>(__INSTANCE, 13, 14, Long.TYPE, "album_id");
    public static final Property<TrackRecordBean> content_type = new Property<>(__INSTANCE, 14, 15, Integer.TYPE, Telephony.RcsColumns.RcsFileTransferColumns.CONTENT_TYPE_COLUMN);

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "TrackRecordBean";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 5;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "TrackRecordBean";
    }

    static {
        Property<TrackRecordBean> property = new Property<>(__INSTANCE, 15, 16, Integer.TYPE, "totalDuration");
        totalDuration = property;
        Property<TrackRecordBean> property2 = id;
        __ALL_PROPERTIES = new Property[]{property2, track_id, duration, played_secs, started_at, ended_at, play_type, rec_src, rec_track, play_source, album_title, track_title, imgUrl, album_id, content_type, property};
        __ID_PROPERTY = property2;
    }

    @Override // io.objectbox.EntityInfo
    public Class<TrackRecordBean> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<TrackRecordBean>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<TrackRecordBean> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<TrackRecordBean> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<TrackRecordBean> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    static final class TrackRecordBeanIdGetter implements IdGetter<TrackRecordBean> {
        TrackRecordBeanIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(TrackRecordBean trackRecordBean) {
            return trackRecordBean.getId();
        }
    }
}
