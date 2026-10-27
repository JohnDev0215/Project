package com.amazon.alexa.mobilytics.event;

import android.text.TextUtils;
import com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DefaultMobilyticsEvent<T extends DefaultMobilyticsEvent<?>> {
    private String appComponent;
    private String eventName;
    protected transient long eventTimestamp;
    private String ownerIdentifier;
    private String subComponent;

    protected DefaultMobilyticsEvent(String str) {
        this.eventTimestamp = System.currentTimeMillis();
        Preconditions.checkArgument(!TextUtils.isEmpty(str));
        this.eventName = str;
    }

    protected DefaultMobilyticsEvent(String str, String str2, String str3, String str4) {
        this(str);
        Preconditions.checkArgument(!TextUtils.isEmpty(str2));
        this.appComponent = str2;
        this.subComponent = str3;
        this.ownerIdentifier = str4;
    }
}
