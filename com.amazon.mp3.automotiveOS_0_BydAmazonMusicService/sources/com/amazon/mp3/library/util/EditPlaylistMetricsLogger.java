package com.amazon.mp3.library.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;

/* JADX INFO: loaded from: classes3.dex */
public class EditPlaylistMetricsLogger {
    private final Context mContext;

    public EditPlaylistMetricsLogger(final Context context) {
        this.mContext = context;
    }

    public void sendOnlinePlaylistEdited() {
        sendOnlinePlaylistEdited(null, null);
    }

    public void sendOnlinePlaylistEdited(final String entityIdType, final String entityId) {
        sendFlexEvent("onlinePlaylistEdited", null, entityIdType, entityId);
    }

    public void sendOfflinePlaylistEdited(final String entityType, final String entityIdType, final String entityId) {
        if (!ConnectivityUtil.hasAnyInternetConnection(this.mContext)) {
            setPlaylistEditedOffline(true);
        }
        sendFlexEvent("offlinePlaylistEdited", entityType, entityIdType, entityId);
    }

    public void sendPlaylistDownloadDisabled(final String entityType, final String entityIdType, final String entityId) {
        if (!ConnectivityUtil.hasAnyInternetConnection(this.mContext)) {
            setPlaylistEditedOffline(true);
        }
        sendFlexEvent("playlistDownloadDisabled", entityType, entityIdType, entityId);
    }

    public synchronized void sendCloudAndOfflinePlaylistEdited() {
        if (getPlaylistEditedOffline()) {
            setPlaylistEditedOffline(false);
            sendFlexEvent("cloudAndOfflinePlaylistEdited");
        }
    }

    public synchronized void setPlaylistEditedOffline(final boolean editedOffline) {
        SharedPreferences.Editor editorEdit = getSharedPreference(this.mContext).edit();
        editorEdit.putBoolean("playlistEditedOffline", editedOffline);
        editorEdit.apply();
    }

    private synchronized boolean getPlaylistEditedOffline() {
        return getSharedPreference(this.mContext).getBoolean("playlistEditedOffline", false);
    }

    private void sendFlexEvent(final String eventName) {
        sendFlexEvent(eventName, null, null, null);
    }

    private void sendFlexEvent(final String eventName, final String entityType, final String entityIdType, final String entityId) {
        MetricsLogger.sendEvent(FlexEvent.builder(eventName).withFlexStr1(getDeviceId()).withFlexStr2(entityType).withFlexStr3(entityIdType).withFlexStr4(entityId).build());
    }

    private String getDeviceId() {
        return AccountCredentialStorage.get(this.mContext).getDeviceId();
    }

    private SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences("com.amazon.mp3_EditPlaylist", 4);
    }
}
