package com.byd.card.db;

import com.byd.card.bean.Folder;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface FolderDao {
    void deleteFolder(Folder... folder);

    void deleteFolderList(List<Folder> folderList);

    Folder getFolderById(int folderId);

    Folder getFolderByTitle(String title);

    List<Folder> getFolders();

    int getMaxFolderId();

    void insertFolder(Folder... folder);

    void insertFolderList(List<Folder> folderList);

    void updateFolder(Folder... folder);

    void updateFolderList(List<Folder> folderList);
}
