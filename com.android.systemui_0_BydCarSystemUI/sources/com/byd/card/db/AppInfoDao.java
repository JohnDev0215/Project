package com.byd.card.db;

import com.byd.card.bean.AppInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface AppInfoDao {
    void delete(AppInfo... appInfos);

    void deleteList(List<AppInfo> appInfos);

    List<AppInfo> getAllApp();

    List<AppInfo> getAllAppExceptPkg();

    AppInfo getAppInfoByPackage(String packageName);

    List<AppInfo> getListForExact(String inputText);

    List<AppInfo> getListForSearch(String inputText);

    List<AppInfo> getListForStart(String inputText);

    List<AppInfo> getRecentList(int recent);

    void insert(AppInfo... appInfos);

    void insertList(List<AppInfo> appInfos);

    void update(AppInfo... appInfos);

    void updateList(List<AppInfo> appInfos);

    void updates(long time, int id);
}
