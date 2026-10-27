package com.android.launcher3.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;

/* JADX INFO: loaded from: classes4.dex */
public class SecureSettingsObserver extends ContentObserver {
    public static final String NOTIFICATION_BADGING = "notification_badging";
    private final int mDefaultValue;
    private final String mKeySetting;
    private final OnChangeListener mOnChangeListener;
    private final ContentResolver mResolver;

    public interface OnChangeListener {
        void onSettingsChanged(boolean isEnabled);
    }

    public SecureSettingsObserver(ContentResolver resolver, OnChangeListener listener, String keySetting, int defaultValue) {
        super(new Handler());
        this.mResolver = resolver;
        this.mOnChangeListener = listener;
        this.mKeySetting = keySetting;
        this.mDefaultValue = defaultValue;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean selfChange) {
        this.mOnChangeListener.onSettingsChanged(getValue());
    }

    public boolean getValue() {
        return Settings.Secure.getInt(this.mResolver, this.mKeySetting, this.mDefaultValue) == 1;
    }

    public void register() {
        this.mResolver.registerContentObserver(Settings.Secure.getUriFor(this.mKeySetting), false, this);
    }

    public ContentResolver getResolver() {
        return this.mResolver;
    }

    public void dispatchOnChange() {
        onChange(true);
    }

    public void unregister() {
        this.mResolver.unregisterContentObserver(this);
    }

    public static SecureSettingsObserver newNotificationSettingsObserver(Context context, OnChangeListener listener) {
        return new SecureSettingsObserver(context.getContentResolver(), listener, "notification_badging", 1);
    }
}
