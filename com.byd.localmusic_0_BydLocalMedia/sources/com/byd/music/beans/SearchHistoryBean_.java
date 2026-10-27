package com.byd.music.beans;

import com.byd.music.beans.SearchHistoryBeanCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX INFO: loaded from: classes5.dex */
public final class SearchHistoryBean_ implements EntityInfo<SearchHistoryBean> {
    public static final Property<SearchHistoryBean>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "SearchHistoryBean";
    public static final int __ENTITY_ID = 4;
    public static final String __ENTITY_NAME = "SearchHistoryBean";
    public static final Property<SearchHistoryBean> __ID_PROPERTY;
    public static final Property<SearchHistoryBean> type;
    public static final Class<SearchHistoryBean> __ENTITY_CLASS = SearchHistoryBean.class;
    public static final CursorFactory<SearchHistoryBean> __CURSOR_FACTORY = new SearchHistoryBeanCursor.Factory();
    static final SearchHistoryBeanIdGetter __ID_GETTER = new SearchHistoryBeanIdGetter();
    public static final SearchHistoryBean_ __INSTANCE = new SearchHistoryBean_();
    public static final Property<SearchHistoryBean> id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<SearchHistoryBean> title = new Property<>(__INSTANCE, 1, 4, String.class, "title");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "SearchHistoryBean";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 4;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "SearchHistoryBean";
    }

    static {
        Property<SearchHistoryBean> property = new Property<>(__INSTANCE, 2, 3, Integer.TYPE, "type");
        type = property;
        Property<SearchHistoryBean> property2 = id;
        __ALL_PROPERTIES = new Property[]{property2, title, property};
        __ID_PROPERTY = property2;
    }

    @Override // io.objectbox.EntityInfo
    public Class<SearchHistoryBean> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<SearchHistoryBean>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<SearchHistoryBean> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<SearchHistoryBean> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<SearchHistoryBean> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    static final class SearchHistoryBeanIdGetter implements IdGetter<SearchHistoryBean> {
        SearchHistoryBeanIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(SearchHistoryBean searchHistoryBean) {
            return searchHistoryBean.getId();
        }
    }
}
