package com.ecarx.xui.adaptapi.hvac;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IHvacReminder {
    public static final int REMINDER_MASSAGE_ON = 2;
    public static final int REMINDER_ROW2_LOCKED = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ReminderType {
    }

    int getHvacReminderType();
}
