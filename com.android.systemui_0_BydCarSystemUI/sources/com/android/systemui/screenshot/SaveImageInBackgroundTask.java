package com.android.systemui.screenshot;

import android.app.ActivityTaskManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Slog;
import com.android.systemui.R;
import com.android.systemui.SystemUIFactory;
import com.android.systemui.screenshot.GlobalScreenshot;
import com.byd.card.provider.ShortcutProvider;
import com.ts.avm.sdk.impl.AvmManagerImpl;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes.dex */
class SaveImageInBackgroundTask extends AsyncTask<Void, Void, Void> {
    private static final String SCREENSHOT_FILE_NAME_TEMPLATE = "Screenshot_%s.png";
    private static final String SCREENSHOT_ID_TEMPLATE = "Screenshot_%s";
    private static final String SCREENSHOT_SHARE_SUBJECT_TEMPLATE = "Screenshot (%s)";
    private static final String TAG = "SaveImageInBackgroundTask";
    private final Context mContext;
    private final String mImageFileName;
    private final long mImageTime;
    private final GlobalScreenshot.SaveImageInBackgroundData mParams;
    private final String mScreenshotId;
    private final ScreenshotSmartActions mScreenshotSmartActions;
    private final boolean mSmartActionsEnabled;
    private final ScreenshotNotificationSmartActionsProvider mSmartActionsProvider;
    private final Random mRandom = new Random();
    private final GlobalScreenshot.SavedImageData mImageData = new GlobalScreenshot.SavedImageData();

    SaveImageInBackgroundTask(Context context, ScreenshotSmartActions screenshotSmartActions, GlobalScreenshot.SaveImageInBackgroundData data) {
        this.mContext = context;
        this.mScreenshotSmartActions = screenshotSmartActions;
        this.mParams = data;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.mImageTime = jCurrentTimeMillis;
        this.mImageFileName = String.format(SCREENSHOT_FILE_NAME_TEMPLATE, new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date(jCurrentTimeMillis)));
        this.mScreenshotId = String.format(SCREENSHOT_ID_TEMPLATE, UUID.randomUUID());
        boolean z = DeviceConfig.getBoolean("systemui", "enable_screenshot_notification_smart_actions", true);
        this.mSmartActionsEnabled = z;
        if (z) {
            this.mSmartActionsProvider = SystemUIFactory.getInstance().createScreenshotNotificationSmartActionsProvider(context, THREAD_POOL_EXECUTOR, new Handler());
        } else {
            this.mSmartActionsProvider = new ScreenshotNotificationSmartActionsProvider();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(Void... paramsUnused) {
        ContentValues contentValues;
        Uri uriInsert;
        CompletableFuture<List<Notification.Action>> smartActionsFuture;
        OutputStream outputStreamOpenOutputStream;
        if (isCancelled()) {
            return null;
        }
        Thread.currentThread().setPriority(10);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Bitmap bitmap = this.mParams.image;
        this.mContext.getResources();
        try {
            contentValues = new ContentValues();
            contentValues.put("relative_path", Environment.DIRECTORY_PICTURES + File.separator + Environment.DIRECTORY_SCREENSHOTS);
            contentValues.put("_display_name", this.mImageFileName);
            contentValues.put("mime_type", "image/png");
            contentValues.put("date_added", Long.valueOf(this.mImageTime / 1000));
            contentValues.put("date_modified", Long.valueOf(this.mImageTime / 1000));
            contentValues.put("date_expires", Long.valueOf((this.mImageTime + 86400000) / 1000));
            contentValues.put("is_pending", (Integer) 1);
            uriInsert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            smartActionsFuture = this.mScreenshotSmartActions.getSmartActionsFuture(this.mScreenshotId, uriInsert, bitmap, this.mSmartActionsProvider, this.mSmartActionsEnabled, getUserHandle(this.mContext));
            try {
                outputStreamOpenOutputStream = contentResolver.openOutputStream(uriInsert);
                try {
                } finally {
                }
            } catch (Exception e) {
                contentResolver.delete(uriInsert, null);
                throw e;
            }
        } catch (Exception e2) {
            Slog.e(TAG, "unable to save screenshot", e2);
            this.mParams.clearImage();
            this.mParams.errorMsgResId = R.string.screenshot_failed_to_save_text;
            this.mImageData.reset();
            this.mParams.mActionsReadyListener.onActionsReady(this.mImageData);
            this.mParams.finisher.accept(null);
        }
        if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStreamOpenOutputStream)) {
            throw new IOException("Failed to compress");
        }
        if (outputStreamOpenOutputStream != null) {
            outputStreamOpenOutputStream.close();
        }
        ParcelFileDescriptor parcelFileDescriptorOpenFile = contentResolver.openFile(uriInsert, "rw", null);
        try {
            ExifInterface exifInterface = new ExifInterface(parcelFileDescriptorOpenFile.getFileDescriptor());
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_SOFTWARE, "Android " + Build.DISPLAY);
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_IMAGE_WIDTH, Integer.toString(bitmap.getWidth()));
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_IMAGE_LENGTH, Integer.toString(bitmap.getHeight()));
            ZonedDateTime zonedDateTimeOfInstant = ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.mImageTime), ZoneId.systemDefault());
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_DATETIME_ORIGINAL, DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss").format(zonedDateTimeOfInstant));
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, DateTimeFormatter.ofPattern("SSS").format(zonedDateTimeOfInstant));
            if (Objects.equals(zonedDateTimeOfInstant.getOffset(), ZoneOffset.UTC)) {
                exifInterface.setAttribute("OffsetTimeOriginal", "+00:00");
            } else {
                exifInterface.setAttribute("OffsetTimeOriginal", DateTimeFormatter.ofPattern("XXX").format(zonedDateTimeOfInstant));
            }
            exifInterface.saveAttributes();
            if (parcelFileDescriptorOpenFile != null) {
                parcelFileDescriptorOpenFile.close();
            }
            contentValues.clear();
            contentValues.put("is_pending", (Integer) 0);
            contentValues.putNull("date_expires");
            contentResolver.update(uriInsert, contentValues, null, null);
            ArrayList arrayList = new ArrayList();
            if (this.mSmartActionsEnabled) {
                arrayList.addAll(buildSmartActions(this.mScreenshotSmartActions.getSmartActions(this.mScreenshotId, smartActionsFuture, DeviceConfig.getInt("systemui", "screenshot_notification_smart_actions_timeout_ms", 1000), this.mSmartActionsProvider), this.mContext));
            }
            this.mImageData.uri = uriInsert;
            this.mImageData.smartActions = arrayList;
            GlobalScreenshot.SavedImageData savedImageData = this.mImageData;
            Context context = this.mContext;
            savedImageData.shareAction = createShareAction(context, context.getResources(), uriInsert);
            GlobalScreenshot.SavedImageData savedImageData2 = this.mImageData;
            Context context2 = this.mContext;
            savedImageData2.editAction = createEditAction(context2, context2.getResources(), uriInsert);
            GlobalScreenshot.SavedImageData savedImageData3 = this.mImageData;
            Context context3 = this.mContext;
            savedImageData3.deleteAction = createDeleteAction(context3, context3.getResources(), uriInsert);
            this.mParams.mActionsReadyListener.onActionsReady(this.mImageData);
            this.mParams.finisher.accept(this.mImageData.uri);
            this.mParams.image = null;
            this.mParams.errorMsgResId = 0;
            return null;
        } finally {
        }
        contentResolver.delete(uriInsert, null);
        throw e;
    }

    void setActionsReadyListener(GlobalScreenshot.ActionsReadyListener listener) {
        this.mParams.mActionsReadyListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onCancelled(Void params) {
        this.mImageData.reset();
        this.mParams.mActionsReadyListener.onActionsReady(this.mImageData);
        this.mParams.finisher.accept(null);
        this.mParams.clearImage();
    }

    Notification.Action createShareAction(Context context, Resources r, Uri uri) {
        String str = String.format(SCREENSHOT_SHARE_SUBJECT_TEMPLATE, DateFormat.getDateTimeInstance().format(new Date(this.mImageTime)));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/png");
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setClipData(new ClipData(new ClipDescription(ShortcutProvider.SCHEME, new String[]{"text/plain"}), new ClipData.Item(uri)));
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.addFlags(1);
        return new Notification.Action.Builder(Icon.createWithResource(r, R.drawable.ic_screenshot_share), r.getString(android.R.string.permdesc_handoverStatus), PendingIntent.getBroadcastAsUser(context, context.getUserId(), new Intent(context, (Class<?>) ActionProxyReceiver.class).putExtra("android:screenshot_action_intent", PendingIntent.getActivityAsUser(context, 0, Intent.createChooser(intent, null).addFlags(268468224).addFlags(1), 335544320, null, UserHandle.CURRENT)).putExtra("android:screenshot_disallow_enter_pip", true).putExtra("android:screenshot_id", this.mScreenshotId).putExtra("android:smart_actions_enabled", this.mSmartActionsEnabled).setAction("android.intent.action.SEND").addFlags(268435456), 335544320, UserHandle.SYSTEM)).build();
    }

    Notification.Action createEditAction(Context context, Resources r, Uri uri) {
        String string = context.getString(R.string.config_screenshotEditor);
        Intent intent = new Intent("android.intent.action.EDIT");
        if (!TextUtils.isEmpty(string)) {
            intent.setComponent(ComponentName.unflattenFromString(string));
        }
        intent.setType("image/png");
        intent.setData(uri);
        intent.addFlags(1);
        intent.addFlags(2);
        intent.addFlags(268468224);
        return new Notification.Action.Builder(Icon.createWithResource(r, R.drawable.ic_screenshot_edit), r.getString(android.R.string.permdesc_cameraOpenCloseListener), PendingIntent.getBroadcastAsUser(context, this.mContext.getUserId(), new Intent(context, (Class<?>) ActionProxyReceiver.class).putExtra("android:screenshot_action_intent", PendingIntent.getActivityAsUser(context, 0, intent, AvmManagerImpl.AVM_CALIBRATION_CODE, null, UserHandle.CURRENT)).putExtra("android:screenshot_id", this.mScreenshotId).putExtra("android:smart_actions_enabled", this.mSmartActionsEnabled).setAction("android.intent.action.EDIT").addFlags(268435456), 335544320, UserHandle.SYSTEM)).build();
    }

    Notification.Action createDeleteAction(Context context, Resources r, Uri uri) {
        return new Notification.Action.Builder(Icon.createWithResource(r, R.drawable.ic_screenshot_delete), r.getString(android.R.string.config_pointing_ui_package), PendingIntent.getBroadcast(context, this.mContext.getUserId(), new Intent(context, (Class<?>) DeleteScreenshotReceiver.class).putExtra("android:screenshot_uri_id", uri.toString()).putExtra("android:screenshot_id", this.mScreenshotId).putExtra("android:smart_actions_enabled", this.mSmartActionsEnabled).addFlags(268435456), 1409286144)).build();
    }

    private int getUserHandleOfForegroundApplication(Context context) {
        try {
            return ActivityTaskManager.getService().getLastResumedActivityUserId();
        } catch (RemoteException e) {
            Slog.w(TAG, "getUserHandleOfForegroundApplication: ", e);
            return context.getUserId();
        }
    }

    private UserHandle getUserHandle(Context context) {
        return UserManager.get(context).getUserInfo(getUserHandleOfForegroundApplication(context)).getUserHandle();
    }

    private List<Notification.Action> buildSmartActions(List<Notification.Action> actions, Context context) {
        ArrayList arrayList = new ArrayList();
        for (Notification.Action action : actions) {
            Bundle extras = action.getExtras();
            String string = extras.getString(ScreenshotNotificationSmartActionsProvider.ACTION_TYPE, ScreenshotNotificationSmartActionsProvider.DEFAULT_ACTION_TYPE);
            Intent intentAddFlags = new Intent(context, (Class<?>) SmartActionsReceiver.class).putExtra("android:screenshot_action_intent", action.actionIntent).addFlags(268435456);
            addIntentExtras(this.mScreenshotId, intentAddFlags, string, this.mSmartActionsEnabled);
            arrayList.add(new Notification.Action.Builder(action.getIcon(), action.title, PendingIntent.getBroadcast(context, this.mRandom.nextInt(), intentAddFlags, 335544320)).setContextual(true).addExtras(extras).build());
        }
        return arrayList;
    }

    private static void addIntentExtras(String screenshotId, Intent intent, String actionType, boolean smartActionsEnabled) {
        intent.putExtra("android:screenshot_action_type", actionType).putExtra("android:screenshot_id", screenshotId).putExtra("android:smart_actions_enabled", smartActionsEnabled);
    }
}
