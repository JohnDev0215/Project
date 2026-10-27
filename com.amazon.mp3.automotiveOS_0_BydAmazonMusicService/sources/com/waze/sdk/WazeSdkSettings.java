package com.waze.sdk;

import android.app.PendingIntent;
import android.os.Bundle;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes7.dex */
public class WazeSdkSettings {
    final PendingIntent openMeIntent;
    final Integer themeColor;

    WazeSdkSettings(Builder builder) {
        this.openMeIntent = builder.openMeIntent;
        this.themeColor = builder.themeColor;
    }

    Bundle toBundle() {
        Bundle bundle = new Bundle();
        PendingIntent pendingIntent = this.openMeIntent;
        if (pendingIntent != null) {
            bundle.putParcelable("openMeIntent", pendingIntent);
        }
        Integer num = this.themeColor;
        if (num != null) {
            bundle.putInt("themeColor", num.intValue());
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static abstract class Builder<T extends Builder> {
        PendingIntent openMeIntent;
        Integer themeColor;

        Builder() {
        }

        public T setOpenMeIntent(PendingIntent pendingIntent) {
            this.openMeIntent = pendingIntent;
            return this;
        }

        public T setThemeColor(int i) {
            this.themeColor = Integer.valueOf(i);
            return this;
        }
    }
}
