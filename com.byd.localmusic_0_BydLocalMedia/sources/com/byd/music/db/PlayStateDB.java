package com.byd.music.db;

import com.byd.music.beans.PlayStateDBBean;
import com.byd.music.beans.PlayStateDBBean_;
import io.objectbox.Box;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class PlayStateDB {
    private static Box<PlayStateDBBean> getBox() {
        return BoxManager.get().getBoxStore().boxFor(PlayStateDBBean.class);
    }

    public static PlayStateDBBean query(long j) {
        return getBox().query().equal(PlayStateDBBean_.playState, j).build().findUnique();
    }

    public static List<PlayStateDBBean> queryAll() {
        return getBox().query().build().find();
    }

    public static void insert(PlayStateDBBean playStateDBBean) {
        getBox().put(playStateDBBean);
    }

    public static void insertAll(List<PlayStateDBBean> list) {
        getBox().put(list);
    }

    public static void delete(long j) {
        getBox().remove(query(j));
    }

    public static void delete(PlayStateDBBean playStateDBBean) {
        getBox().remove(playStateDBBean);
    }

    public static void deleteAll() {
        getBox().removeAll();
    }
}
