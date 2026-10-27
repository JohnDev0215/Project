package com.byd.card.db;

import com.byd.card.drag.ApplicationInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ApplicationDao {
    void delete(ApplicationInfo... applicationInfos);

    void deleteList(List<ApplicationInfo> applicationInfos);

    int deleteShortcutByOrigin(String packageName);

    List<ApplicationInfo> getAllApps();

    ApplicationInfo getAppByActivity(String activity);

    ApplicationInfo getAppByPackageName(String packageName);

    ApplicationInfo getAppByPosition(int position);

    ApplicationInfo getAppByTitle(String title);

    List<ApplicationInfo> getExceptFolderAllApp();

    List<ApplicationInfo> getFolderListByName(String name);

    List<ApplicationInfo> getListByFolderId(long folderId);

    int getMaxPosition();

    List<ApplicationInfo> getShortcutList();

    void insert(ApplicationInfo... applicationInfos);

    void insertList(List<ApplicationInfo> applicationInfos);

    void update(ApplicationInfo... applicationInfos);

    void updateList(List<ApplicationInfo> applicationInfos);
}
