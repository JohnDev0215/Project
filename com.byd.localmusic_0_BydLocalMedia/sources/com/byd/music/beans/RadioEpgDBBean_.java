package com.byd.music.beans;

import com.byd.music.beans.RadioEpgDBBeanCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX INFO: loaded from: classes5.dex */
public final class RadioEpgDBBean_ implements EntityInfo<RadioEpgDBBean> {
    public static final Property<RadioEpgDBBean>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "RadioEpgDBBean";
    public static final int __ENTITY_ID = 9;
    public static final String __ENTITY_NAME = "RadioEpgDBBean";
    public static final Property<RadioEpgDBBean> __ID_PROPERTY;
    public static final Property<RadioEpgDBBean> desc;
    public static final Class<RadioEpgDBBean> __ENTITY_CLASS = RadioEpgDBBean.class;
    public static final CursorFactory<RadioEpgDBBean> __CURSOR_FACTORY = new RadioEpgDBBeanCursor.Factory();
    static final RadioEpgDBBeanIdGetter __ID_GETTER = new RadioEpgDBBeanIdGetter();
    public static final RadioEpgDBBean_ __INSTANCE = new RadioEpgDBBean_();
    public static final Property<RadioEpgDBBean> id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<RadioEpgDBBean> dabSid = new Property<>(__INSTANCE, 1, 2, Long.TYPE, "dabSid");
    public static final Property<RadioEpgDBBean> epgStartTime = new Property<>(__INSTANCE, 2, 3, Long.TYPE, "epgStartTime");
    public static final Property<RadioEpgDBBean> epgEndTime = new Property<>(__INSTANCE, 3, 4, Long.TYPE, "epgEndTime");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "RadioEpgDBBean";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 9;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "RadioEpgDBBean";
    }

    static {
        Property<RadioEpgDBBean> property = new Property<>(__INSTANCE, 4, 5, String.class, "desc");
        desc = property;
        Property<RadioEpgDBBean> property2 = id;
        __ALL_PROPERTIES = new Property[]{property2, dabSid, epgStartTime, epgEndTime, property};
        __ID_PROPERTY = property2;
    }

    @Override // io.objectbox.EntityInfo
    public Class<RadioEpgDBBean> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<RadioEpgDBBean>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<RadioEpgDBBean> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<RadioEpgDBBean> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<RadioEpgDBBean> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    static final class RadioEpgDBBeanIdGetter implements IdGetter<RadioEpgDBBean> {
        RadioEpgDBBeanIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(RadioEpgDBBean radioEpgDBBean) {
            return radioEpgDBBean.getId();
        }
    }
}
