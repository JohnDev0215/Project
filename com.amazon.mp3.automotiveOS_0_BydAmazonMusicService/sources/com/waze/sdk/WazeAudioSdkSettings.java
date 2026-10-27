package com.waze.sdk;

import android.app.PendingIntent;
import com.waze.sdk.WazeSdkSettings;

/* JADX INFO: loaded from: classes7.dex */
public class WazeAudioSdkSettings extends WazeSdkSettings {
    private WazeAudioSdkSettings(Builder builder) {
        super(builder);
    }

    public static class Builder extends WazeSdkSettings.Builder<Builder> {
        @Override // com.waze.sdk.WazeSdkSettings.Builder
        public /* bridge */ /* synthetic */ WazeSdkSettings.Builder setOpenMeIntent(PendingIntent pendingIntent) {
            return super.setOpenMeIntent(pendingIntent);
        }

        @Override // com.waze.sdk.WazeSdkSettings.Builder
        public /* bridge */ /* synthetic */ WazeSdkSettings.Builder setThemeColor(int i) {
            return super.setThemeColor(i);
        }

        public WazeAudioSdkSettings build() {
            return new WazeAudioSdkSettings(this);
        }
    }
}
