package com.byd.music.beans;

import com.byd.music.beans.RadioDBBeanCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX INFO: loaded from: classes5.dex */
public final class RadioDBBean_ implements EntityInfo<RadioDBBean> {
    public static final Property<RadioDBBean> RDS_RT;
    public static final Property<RadioDBBean>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "RadioDBBean";
    public static final int __ENTITY_ID = 10;
    public static final String __ENTITY_NAME = "RadioDBBean";
    public static final Property<RadioDBBean> __ID_PROPERTY;
    public static final Class<RadioDBBean> __ENTITY_CLASS = RadioDBBean.class;
    public static final CursorFactory<RadioDBBean> __CURSOR_FACTORY = new RadioDBBeanCursor.Factory();
    static final RadioDBBeanIdGetter __ID_GETTER = new RadioDBBeanIdGetter();
    public static final RadioDBBean_ __INSTANCE = new RadioDBBean_();
    public static final Property<RadioDBBean> id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<RadioDBBean> type = new Property<>(__INSTANCE, 1, 2, Integer.TYPE, "type");
    public static final Property<RadioDBBean> radioFrequency = new Property<>(__INSTANCE, 2, 3, Integer.TYPE, "radioFrequency");
    public static final Property<RadioDBBean> radioName = new Property<>(__INSTANCE, 3, 10, String.class, "radioName");
    public static final Property<RadioDBBean> DAB_SID = new Property<>(__INSTANCE, 4, 8, Long.TYPE, "DAB_SID");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "RadioDBBean";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 10;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "RadioDBBean";
    }

    static {
        Property<RadioDBBean> property = new Property<>(__INSTANCE, 5, 9, String.class, "RDS_RT");
        RDS_RT = property;
        Property<RadioDBBean> property2 = id;
        __ALL_PROPERTIES = new Property[]{property2, type, radioFrequency, radioName, DAB_SID, property};
        __ID_PROPERTY = property2;
    }

    @Override // io.objectbox.EntityInfo
    public Class<RadioDBBean> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<RadioDBBean>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<RadioDBBean> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<RadioDBBean> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<RadioDBBean> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    static final class RadioDBBeanIdGetter implements IdGetter<RadioDBBean> {
        RadioDBBeanIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(RadioDBBean radioDBBean) {
            return radioDBBean.getId();
        }
    }
}
