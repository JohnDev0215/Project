package com.byd.music.db;

import com.byd.music.beans.RadioEpgDBBean;
import com.byd.music.beans.RadioEpgDBBean_;
import io.objectbox.Box;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RadioEpgDB {
    private static Box<RadioEpgDBBean> getBox() {
        return BoxManager.get().getBoxStore().boxFor(RadioEpgDBBean.class);
    }

    public static RadioEpgDBBean query(long j) {
        return getBox().query().equal(RadioEpgDBBean_.id, j).build().findUnique();
    }

    public static List<RadioEpgDBBean> queryAll() {
        return getBox().query().build().find();
    }

    public static void insert(RadioEpgDBBean radioEpgDBBean) {
        getBox().put(radioEpgDBBean);
    }

    public static void insertAll(List<RadioEpgDBBean> list) {
        getBox().put(list);
    }

    public static void delete(long j) {
        getBox().remove(query(j));
    }

    public static void delete(RadioEpgDBBean radioEpgDBBean) {
        getBox().remove(radioEpgDBBean);
    }

    public static void deleteAll() {
        getBox().removeAll();
    }
}
