package com.byd.music.db;

import com.byd.music.beans.SearchHistoryBean;
import com.byd.music.beans.SearchHistoryBean_;
import com.byd.music.utils.LogUtil;
import io.objectbox.Box;
import io.objectbox.android.ObjectBoxLiveData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SearchHistoryDB {
    private static final int saveNum = 30;

    private static Box<SearchHistoryBean> getBox() {
        return BoxManager.get().getBoxStore().boxFor(SearchHistoryBean.class);
    }

    public static ObjectBoxLiveData<SearchHistoryBean> loadNewHistory() {
        LogUtil.d("ObjectBox", "" + getBox().query().orderDesc(SearchHistoryBean_.id).build().find().size());
        return new ObjectBoxLiveData<>(getBox().query().orderDesc(SearchHistoryBean_.id).build());
    }

    public static void clearSearchHistory() {
        getBox().removeAll();
    }

    public static void insert(SearchHistoryBean searchHistoryBean) {
        List<SearchHistoryBean> listFind = getBox().query().order(SearchHistoryBean_.id).build().find();
        int size = listFind.size();
        if (size > 30) {
            getBox().remove(new ArrayList(listFind.subList(0, size - 30)));
        }
        Iterator<SearchHistoryBean> it = listFind.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SearchHistoryBean next = it.next();
            if (next.getTitle().equals(searchHistoryBean.getTitle())) {
                getBox().remove(next);
                break;
            }
        }
        getBox().put(searchHistoryBean);
    }

    public static boolean hasHistory() {
        return getBox().query().order(SearchHistoryBean_.id).build().find().size() > 0;
    }

    public static int getBeanType(String str) {
        SearchHistoryBean searchHistoryBeanFindUnique = getBox().query(SearchHistoryBean_.title.equal(str)).build().findUnique();
        if (searchHistoryBeanFindUnique != null) {
            return searchHistoryBeanFindUnique.getType();
        }
        return 1;
    }
}
