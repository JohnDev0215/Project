package com.byd.music.beans;

import com.byd.music.beans.PlayStateDBBeanCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX INFO: loaded from: classes5.dex */
public final class PlayStateDBBean_ implements EntityInfo<PlayStateDBBean> {
    public static final Property<PlayStateDBBean>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "PlayStateDBBean";
    public static final int __ENTITY_ID = 7;
    public static final String __ENTITY_NAME = "PlayStateDBBean";
    public static final Property<PlayStateDBBean> __ID_PROPERTY;
    public static final Property<PlayStateDBBean> timeStamp;
    public static final Class<PlayStateDBBean> __ENTITY_CLASS = PlayStateDBBean.class;
    public static final CursorFactory<PlayStateDBBean> __CURSOR_FACTORY = new PlayStateDBBeanCursor.Factory();
    static final PlayStateDBBeanIdGetter __ID_GETTER = new PlayStateDBBeanIdGetter();
    public static final PlayStateDBBean_ __INSTANCE = new PlayStateDBBean_();
    public static final Property<PlayStateDBBean> id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<PlayStateDBBean> playState = new Property<>(__INSTANCE, 1, 2, Boolean.TYPE, "playState");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "PlayStateDBBean";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 7;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "PlayStateDBBean";
    }

    static {
        Property<PlayStateDBBean> property = new Property<>(__INSTANCE, 2, 3, Long.TYPE, "timeStamp");
        timeStamp = property;
        Property<PlayStateDBBean> property2 = id;
        __ALL_PROPERTIES = new Property[]{property2, playState, property};
        __ID_PROPERTY = property2;
    }

    @Override // io.objectbox.EntityInfo
    public Class<PlayStateDBBean> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<PlayStateDBBean>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<PlayStateDBBean> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<PlayStateDBBean> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<PlayStateDBBean> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    static final class PlayStateDBBeanIdGetter implements IdGetter<PlayStateDBBean> {
        PlayStateDBBeanIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(PlayStateDBBean playStateDBBean) {
            return playStateDBBean.getId();
        }
    }
}
