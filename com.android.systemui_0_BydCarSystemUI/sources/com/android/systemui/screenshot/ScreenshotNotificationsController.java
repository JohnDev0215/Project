package com.android.systemui.screenshot;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.os.UserHandle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.core.app.NotificationCompat;
import com.android.systemui.SystemUI;
import com.android.systemui.screenshot.GlobalScreenshot;
import com.android.systemui.util.NotificationChannels;
import com.ts.avm.sdk.impl.AvmManagerImpl;
import java.util.Iterator;
import javax.inject.Inject;

/* JADX INFO: loaded from: classes.dex */
public class ScreenshotNotificationsController {
    private static final String TAG = "ScreenshotNotificationManager";
    private final Context mContext;
    private int mIconSize;
    private Notification.Builder mNotificationBuilder;
    private final NotificationManager mNotificationManager;
    private final Notification.BigPictureStyle mNotificationStyle;
    private int mPreviewHeight;
    private int mPreviewWidth;
    private Notification.Builder mPublicNotificationBuilder;
    private final Resources mResources;

    @Inject
    ScreenshotNotificationsController(Context context, WindowManager windowManager) {
        int dimensionPixelSize;
        this.mContext = context;
        Resources resources = context.getResources();
        this.mResources = resources;
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
        this.mIconSize = resources.getDimensionPixelSize(R.dimen.notification_large_icon_height);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        try {
            dimensionPixelSize = resources.getDimensionPixelSize(com.android.systemui.R.dimen.notification_panel_width);
        } catch (Resources.NotFoundException unused) {
            dimensionPixelSize = 0;
        }
        this.mPreviewWidth = dimensionPixelSize <= 0 ? displayMetrics.widthPixels : dimensionPixelSize;
        this.mPreviewHeight = this.mResources.getDimensionPixelSize(com.android.systemui.R.dimen.notification_max_height);
        this.mNotificationStyle = new Notification.BigPictureStyle();
    }

    public void reset() {
        this.mPublicNotificationBuilder = new Notification.Builder(this.mContext, NotificationChannels.SCREENSHOTS_HEADSUP);
        this.mNotificationBuilder = new Notification.Builder(this.mContext, NotificationChannels.SCREENSHOTS_HEADSUP);
    }

    public void setImage(Bitmap image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.25f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Matrix matrix = new Matrix();
        matrix.setTranslate((this.mPreviewWidth - width) / 2.0f, (this.mPreviewHeight - height) / 2.0f);
        this.mNotificationStyle.bigPicture(generateAdjustedHwBitmap(image, this.mPreviewWidth, this.mPreviewHeight, matrix, paint, 1090519039).createAshmemBitmap());
        float fMin = this.mIconSize / Math.min(width, height);
        matrix.setScale(fMin, fMin);
        int i = this.mIconSize;
        matrix.postTranslate((i - (width * fMin)) / 2.0f, (i - (fMin * height)) / 2.0f);
        int i2 = this.mIconSize;
        this.mNotificationBuilder.setLargeIcon(generateAdjustedHwBitmap(image, i2, i2, matrix, paint, 1090519039).createAshmemBitmap());
        this.mNotificationStyle.bigLargeIcon((Bitmap) null);
    }

    public void showSavingScreenshotNotification() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.mPublicNotificationBuilder.setContentTitle(this.mResources.getString(com.android.systemui.R.string.screenshot_saving_title)).setSmallIcon(com.android.systemui.R.drawable.stat_notify_image).setCategory("progress").setWhen(jCurrentTimeMillis).setShowWhen(true).setColor(this.mResources.getColor(R.color.system_notification_accent_color));
        SystemUI.overrideNotificationAppName(this.mContext, this.mPublicNotificationBuilder, true);
        this.mNotificationBuilder.setContentTitle(this.mResources.getString(com.android.systemui.R.string.screenshot_saving_title)).setSmallIcon(com.android.systemui.R.drawable.stat_notify_image).setWhen(jCurrentTimeMillis).setShowWhen(true).setColor(this.mResources.getColor(R.color.system_notification_accent_color)).setStyle(this.mNotificationStyle).setPublicVersion(this.mPublicNotificationBuilder.build());
        this.mNotificationBuilder.setFlag(32, true);
        SystemUI.overrideNotificationAppName(this.mContext, this.mNotificationBuilder, true);
        this.mNotificationManager.notify(1, this.mNotificationBuilder.build());
    }

    public void showScreenshotActionsNotification(GlobalScreenshot.SavedImageData actionData) {
        this.mNotificationBuilder.addAction(actionData.shareAction);
        this.mNotificationBuilder.addAction(actionData.editAction);
        this.mNotificationBuilder.addAction(actionData.deleteAction);
        Iterator<Notification.Action> it = actionData.smartActions.iterator();
        while (it.hasNext()) {
            this.mNotificationBuilder.addAction(it.next());
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(actionData.uri, "image/png");
        intent.setFlags(268435457);
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.mPublicNotificationBuilder.setContentTitle(this.mResources.getString(com.android.systemui.R.string.screenshot_saved_title)).setContentText(this.mResources.getString(com.android.systemui.R.string.screenshot_saved_text)).setContentIntent(PendingIntent.getActivity(this.mContext, 0, intent, AvmManagerImpl.AVM_CALIBRATION_CODE)).setWhen(jCurrentTimeMillis).setAutoCancel(true).setColor(this.mContext.getColor(R.color.system_notification_accent_color));
        this.mNotificationBuilder.setContentTitle(this.mResources.getString(com.android.systemui.R.string.screenshot_saved_title)).setContentText(this.mResources.getString(com.android.systemui.R.string.screenshot_saved_text)).setContentIntent(PendingIntent.getActivity(this.mContext, 0, intent, AvmManagerImpl.AVM_CALIBRATION_CODE)).setWhen(jCurrentTimeMillis).setAutoCancel(true).setColor(this.mContext.getColor(R.color.system_notification_accent_color)).setPublicVersion(this.mPublicNotificationBuilder.build()).setFlag(32, false);
        this.mNotificationManager.notify(1, this.mNotificationBuilder.build());
    }

    public void notifyScreenshotError(int msgResId) {
        Resources resources = this.mContext.getResources();
        String string = resources.getString(msgResId);
        Notification.Builder color = new Notification.Builder(this.mContext, NotificationChannels.ALERTS).setTicker(resources.getString(com.android.systemui.R.string.screenshot_failed_title)).setContentTitle(resources.getString(com.android.systemui.R.string.screenshot_failed_title)).setContentText(string).setSmallIcon(com.android.systemui.R.drawable.stat_notify_image_error).setWhen(System.currentTimeMillis()).setVisibility(1).setCategory(NotificationCompat.CATEGORY_ERROR).setAutoCancel(true).setColor(this.mContext.getColor(R.color.system_notification_accent_color));
        Intent intentCreateAdminSupportIntent = ((DevicePolicyManager) this.mContext.getSystemService("device_policy")).createAdminSupportIntent("policy_disable_screen_capture");
        if (intentCreateAdminSupportIntent != null) {
            color.setContentIntent(PendingIntent.getActivityAsUser(this.mContext, 0, intentCreateAdminSupportIntent, AvmManagerImpl.AVM_CALIBRATION_CODE, null, UserHandle.CURRENT));
        }
        SystemUI.overrideNotificationAppName(this.mContext, color, true);
        this.mNotificationManager.notify(1, new Notification.BigTextStyle(color).bigText(string).build());
    }

    public void cancelNotification() {
        this.mNotificationManager.cancel(1);
    }

    private Bitmap generateAdjustedHwBitmap(Bitmap bitmap, int width, int height, Matrix matrix, Paint paint, int color) {
        Picture picture = new Picture();
        Canvas canvasBeginRecording = picture.beginRecording(width, height);
        canvasBeginRecording.drawColor(color);
        canvasBeginRecording.drawBitmap(bitmap, matrix, paint);
        picture.endRecording();
        return Bitmap.createBitmap(picture);
    }

    static void cancelScreenshotNotification(Context context) {
        ((NotificationManager) context.getSystemService("notification")).cancel(1);
    }
}
