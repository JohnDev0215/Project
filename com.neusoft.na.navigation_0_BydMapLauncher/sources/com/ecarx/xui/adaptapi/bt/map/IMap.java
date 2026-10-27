package com.ecarx.xui.adaptapi.bt.map;

/* JADX INFO: loaded from: classes2.dex */
public interface IMap {
    int getMapCurrentState(String str);

    int getMapRegisterState(String str);

    boolean isMapNotificationRegistered(String str);

    boolean isMapServiceReady();

    boolean registerMapCallback(IMapCallback iMapCallback);

    boolean reqMapChangeReadStatus(String str, int i, String str2, boolean z);

    void reqMapCleanDatabase();

    void reqMapDatabaseAvailable();

    void reqMapDeleteDatabaseByAddress(String str);

    boolean reqMapDeleteMessage(String str, int i, String str2);

    boolean reqMapDownloadAllMessages(String str, int i, boolean z);

    boolean reqMapDownloadAllMessagesToDatabase(String str, int i, boolean z);

    boolean reqMapDownloadInterrupt(String str);

    boolean reqMapDownloadSingleMessage(String str, int i, String str2);

    boolean reqMapDownloadSingleMessageToDatabase(String str, int i, String str2);

    boolean reqMapRegisterNotification(String str, boolean z);

    boolean reqMapSendMessage(String str, String str2, String str3);

    void reqMapUnregisterNotification(String str);

    boolean setMapDownloadNotify(int i);

    boolean unregisterMapCallback(IMapCallback iMapCallback);
}
