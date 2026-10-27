package org.greenrobot.eventbus.util;

import android.content.res.Resources;
import android.util.Log;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorDialogConfig {
    public int defaultDialogIconId;
    public final int defaultErrorMsgId;
    public Class<?> defaultEventTypeOnDialogClosed;
    public final int defaultTitleId;
    public EventBus eventBus;
    public boolean logExceptions = true;
    public final ExceptionToResourceMapping mapping = new ExceptionToResourceMapping();
    public final Resources resources;
    public String tagForLoggingExceptions;

    public ErrorDialogConfig(Resources resources, int i, int i2) {
        this.resources = resources;
        this.defaultTitleId = i;
        this.defaultErrorMsgId = i2;
    }

    public ErrorDialogConfig addMapping(Class<? extends Throwable> cls, int i) {
        this.mapping.addMapping(cls, i);
        return this;
    }

    public void disableExceptionLogging() {
        this.logExceptions = false;
    }

    public EventBus getEventBus() {
        EventBus eventBus = this.eventBus;
        return eventBus != null ? eventBus : EventBus.getDefault();
    }

    public int getMessageIdForThrowable(Throwable th) {
        Integer numMapThrowable = this.mapping.mapThrowable(th);
        if (numMapThrowable != null) {
            return numMapThrowable.intValue();
        }
        Log.d(EventBus.TAG, "No specific message ressource ID found for " + th);
        return this.defaultErrorMsgId;
    }

    public void setDefaultDialogIconId(int i) {
        this.defaultDialogIconId = i;
    }

    public void setDefaultEventTypeOnDialogClosed(Class<?> cls) {
        this.defaultEventTypeOnDialogClosed = cls;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void setTagForLoggingExceptions(String str) {
        this.tagForLoggingExceptions = str;
    }
}
