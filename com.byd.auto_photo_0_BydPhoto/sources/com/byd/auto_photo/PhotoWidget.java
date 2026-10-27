package com.byd.auto_photo;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class PhotoWidget extends AppWidgetProvider {
    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
        Intent intent = new Intent();
        String packageName = context.getApplicationContext().getPackageName();
        intent.setClassName(packageName, packageName + ".PhotoWidgetService");
        context.startService(intent);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        Intent intent = new Intent();
        String packageName = context.getApplicationContext().getPackageName();
        intent.setClassName(packageName, packageName + ".PhotoWidgetService");
        context.startService(intent);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        Intent intent = new Intent();
        String packageName = context.getApplicationContext().getPackageName();
        intent.setClassName(packageName, packageName + ".PhotoWidgetService");
        context.startService(intent);
    }
}
