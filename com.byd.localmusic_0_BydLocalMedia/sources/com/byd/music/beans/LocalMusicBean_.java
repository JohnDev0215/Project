package com.byd.music.beans;

import com.byd.music.beans.LocalMusicBeanCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX INFO: loaded from: classes5.dex */
public final class LocalMusicBean_ implements EntityInfo<LocalMusicBean> {
    public static final Property<LocalMusicBean>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "LocalMusicBean";
    public static final int __ENTITY_ID = 3;
    public static final String __ENTITY_NAME = "LocalMusicBean";
    public static final Property<LocalMusicBean> __ID_PROPERTY;
    public static final Property<LocalMusicBean> pinyin;
    public static final Class<LocalMusicBean> __ENTITY_CLASS = LocalMusicBean.class;
    public static final CursorFactory<LocalMusicBean> __CURSOR_FACTORY = new LocalMusicBeanCursor.Factory();
    static final LocalMusicBeanIdGetter __ID_GETTER = new LocalMusicBeanIdGetter();
    public static final LocalMusicBean_ __INSTANCE = new LocalMusicBean_();
    public static final Property<LocalMusicBean> id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<LocalMusicBean> musicId = new Property<>(__INSTANCE, 1, 2, Integer.TYPE, "musicId");
    public static final Property<LocalMusicBean> title = new Property<>(__INSTANCE, 2, 3, String.class, "title");
    public static final Property<LocalMusicBean> uri = new Property<>(__INSTANCE, 3, 4, String.class, "uri");
    public static final Property<LocalMusicBean> duration = new Property<>(__INSTANCE, 4, 5, Integer.TYPE, "duration");
    public static final Property<LocalMusicBean> createTime = new Property<>(__INSTANCE, 5, 6, Long.TYPE, "createTime");
    public static final Property<LocalMusicBean> playTime = new Property<>(__INSTANCE, 6, 7, Long.TYPE, "playTime");
    public static final Property<LocalMusicBean> artist = new Property<>(__INSTANCE, 7, 8, String.class, "artist");
    public static final Property<LocalMusicBean> artistId = new Property<>(__INSTANCE, 8, 9, Long.TYPE, "artistId");
    public static final Property<LocalMusicBean> album = new Property<>(__INSTANCE, 9, 10, String.class, "album");
    public static final Property<LocalMusicBean> albumId = new Property<>(__INSTANCE, 10, 11, Long.TYPE, "albumId");
    public static final Property<LocalMusicBean> playing = new Property<>(__INSTANCE, 11, 12, Boolean.TYPE, "playing");
    public static final Property<LocalMusicBean> isCollect = new Property<>(__INSTANCE, 12, 13, Boolean.TYPE, "isCollect");
    public static final Property<LocalMusicBean> isLocal = new Property<>(__INSTANCE, 13, 14, Boolean.TYPE, "isLocal");
    public static final Property<LocalMusicBean> storageType = new Property<>(__INSTANCE, 14, 15, Integer.TYPE, "storageType");
    public static final Property<LocalMusicBean> state = new Property<>(__INSTANCE, 15, 16, Integer.TYPE, "state");
    public static final Property<LocalMusicBean> progress = new Property<>(__INSTANCE, 16, 17, Integer.TYPE, "progress");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "LocalMusicBean";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 3;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "LocalMusicBean";
    }

    static {
        Property<LocalMusicBean> property = new Property<>(__INSTANCE, 17, 18, String.class, "pinyin");
        pinyin = property;
        Property<LocalMusicBean> property2 = id;
        __ALL_PROPERTIES = new Property[]{property2, musicId, title, uri, duration, createTime, playTime, artist, artistId, album, albumId, playing, isCollect, isLocal, storageType, state, progress, property};
        __ID_PROPERTY = property2;
    }

    @Override // io.objectbox.EntityInfo
    public Class<LocalMusicBean> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<LocalMusicBean>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<LocalMusicBean> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<LocalMusicBean> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<LocalMusicBean> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    static final class LocalMusicBeanIdGetter implements IdGetter<LocalMusicBean> {
        LocalMusicBeanIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(LocalMusicBean localMusicBean) {
            return localMusicBean.getId();
        }
    }
}
