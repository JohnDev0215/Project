package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.activity.GrantSDCardPermissionMainPageActivity;
import com.amazon.mp3.activity.GrantStoragePermissionActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.AccessibilityFocusDialogBuilder;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.fragment.DownloadSettingDialogFragment;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.local.DeleteListener;
import com.amazon.mp3.library.provider.source.local.DeleteUtil;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.task.DeleteTrackTask;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.notification.NotificationInfo;

/* JADX INFO: loaded from: classes3.dex */
public class ContextMenuDownloadComponent {
    private static final String TAG = "ContextMenuDownloadComponent";

    public static void startDownload(final FragmentActivity activity, final String requestId, final Uri uri, final NotificationInfo notificationInfo) {
        download(activity, requestId, uri, notificationInfo, false);
    }

    public static void startDownload(final FragmentActivity activity, final String requestId, final Uri uri, final NotificationInfo notificationInfo, final boolean redownload) {
        download(activity, requestId, uri, notificationInfo, redownload);
    }

    private static void download(final FragmentActivity activity, final String requestId, final Uri uri, final NotificationInfo notificationInfo, final boolean redownload) {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            NetworkErrorDialog.create(activity, new NetworkErrorDialog.INetworkErrorDialogHandler() { // from class: com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent.1
                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onConnectionErrorDialogCancel() {
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onDialogDismiss() {
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onRetryConnection() {
                    ContextMenuDownloadComponent.startDownload(activity, requestId, uri, notificationInfo, redownload);
                }
            }).show();
            return;
        }
        if (DownloadSettingDialogFragment.shouldShowDownloadSettingsDialogFragment(activity)) {
            DownloadSettingDialogFragment.showSettingsDialog(activity, requestId, uri, notificationInfo, false);
            return;
        }
        final DeviceAuthorizationManager deviceAuthorizationManager = DeviceAuthorizationManager.getInstance();
        if (deviceAuthorizationManager.shouldRefreshPrimeAuthorization()) {
            final HandlerThread handlerThread = new HandlerThread("PrimeDeviceAuthBackgroundHandlerThread");
            handlerThread.start();
            new Handler(handlerThread.getLooper()).post(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent.2
                @Override // java.lang.Runnable
                public void run() {
                    deviceAuthorizationManager.refresh();
                    if (!deviceAuthorizationManager.isPrimeAuthorized()) {
                        deviceAuthorizationManager.primeAuthorizeDevice();
                    }
                    if (redownload) {
                        MusicDownloader.getInstance(activity).redownload(requestId, uri, notificationInfo, false, true);
                    } else {
                        MusicDownloader.getInstance(activity).download(requestId, uri, notificationInfo, false, true);
                    }
                    handlerThread.quit();
                }
            });
        } else if (redownload) {
            MusicDownloader.getInstance(activity).redownload(requestId, uri, notificationInfo, false, true);
        } else {
            MusicDownloader.getInstance(activity).download(requestId, uri, notificationInfo, false, true);
        }
    }

    public static void cancelDownload(final Activity activity, final String requestId) {
        MusicDownloader.getInstance(activity.getApplicationContext()).cancel(requestId);
    }

    public static void deleteCloudContent(final Activity activity, int id, final Uri tracksUri, final ConfirmDeleteDialogHolder<?> holder, final boolean deleteFromDevice, final Uri uriToDelete) {
        deleteCloudContent(activity, id, tracksUri, holder, deleteFromDevice, uriToDelete, null);
    }

    public static void deleteCloudContent(final Activity activity, int id, final Uri tracksUri, final ConfirmDeleteDialogHolder<?> holder, final boolean deleteFromDevice, final Uri uriToDelete, final RemoveLibraryContentProvider.OnRemoveLibraryContentListener onRemoveListener) {
        if (deleteFromDevice) {
            GrantStoragePermissionActivity.requestIfNotGranted(activity, MusicDownloader.PERMISSIONS_FOR_DOWNLOAD, new GrantPermissionActivity.PermissionResultReceiver() { // from class: com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent.3
                @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                protected void onGranted() {
                    ContextMenuDownloadComponent.buildConfirmDeleteDialog(activity, tracksUri, holder, deleteFromDevice, uriToDelete, onRemoveListener).show();
                }

                @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                protected void onDenied(String[] permissions) {
                    Log.error(ContextMenuDownloadComponent.TAG, "Do not have adequate permissions to download");
                }
            });
        } else {
            buildConfirmDeleteDialog(activity, tracksUri, holder, deleteFromDevice, uriToDelete, onRemoveListener).show();
        }
    }

    public static void deleteDeviceContent(final Activity activity, int id, final Uri tracksUri, final ConfirmDeleteDialogHolder<?> holder) {
        deleteDeviceContent(activity, id, tracksUri, holder, null);
    }

    public static void deleteDeviceContent(final Activity activity, int id, final Uri tracksUri, final ConfirmDeleteDialogHolder<?> holder, final RemoveLibraryContentProvider.OnRemoveLibraryContentListener onRemoveListener) {
        GrantStoragePermissionActivity.requestIfNotGranted(activity, MusicDownloader.PERMISSIONS_FOR_DOWNLOAD, new GrantPermissionActivity.PermissionResultReceiver() { // from class: com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent.4
            @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
            protected void onGranted() {
                ContextMenuDownloadComponent.buildConfirmDeviceDeleteDialog(activity, tracksUri, holder, onRemoveListener).show();
            }

            @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
            protected void onDenied(String[] permissions) {
                Log.error(ContextMenuDownloadComponent.TAG, "Do not have adequate permissions to download");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Dialog buildConfirmDeleteDialog(final Activity activity, final Uri tracksUri, final ConfirmDeleteDialogHolder<?> holder, final boolean removeFromDeviceAfterCloud, final Uri uriToDelete, final RemoveLibraryContentProvider.OnRemoveLibraryContentListener onRemoveListener) {
        final String string = activity.getString(holder.mOnDeleteCompleteA11yMsgId);
        final String string2 = activity.getString(R.string.removal_fail_content_description);
        final DeleteListener deleteListener = new DeleteListener() { // from class: com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent.5
            @Override // com.amazon.mp3.library.provider.source.local.DeleteListener
            public void onComplete(DeleteListener.DeletionResult result) {
                if (result.getIsSuccess()) {
                    AccessibilityUtil.announceForAccessibility(activity, string);
                } else {
                    AccessibilityUtil.announceForAccessibility(activity, string2);
                }
            }
        };
        AccessibilityFocusDialogBuilder accessibilityFocusDialogBuilder = new AccessibilityFocusDialogBuilder(activity);
        accessibilityFocusDialogBuilder.setPositiveButton(holder.mPositiveBtnStringId, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                DeleteTrackTask deleteTrackTask;
                RemoveLibraryContentProvider.OnRemoveLibraryContentListener onRemoveLibraryContentListener = onRemoveListener;
                if (onRemoveLibraryContentListener != null) {
                    onRemoveLibraryContentListener.onRemoveContentConfirm();
                }
                BauhausToastUtils.showTextToast(activity, holder.mToastStringId, 1);
                String source = MediaProvider.getSource(tracksUri);
                if (MediaProvider.Albums.isAlbum(tracksUri)) {
                    deleteTrackTask = new DeleteTrackTask(activity, MediaProvider.Albums.Tracks.getContentUri(source, MediaProvider.Albums.getAlbumId(tracksUri)));
                } else if (MediaProvider.Artists.isArtist(tracksUri)) {
                    deleteTrackTask = new DeleteTrackTask(activity, MediaProvider.Artists.Tracks.getContentUri(source, MediaProvider.Artists.getArtistId(tracksUri)));
                } else {
                    deleteTrackTask = new DeleteTrackTask(activity, tracksUri);
                }
                deleteTrackTask.setOnCompleteListener(new DeleteTrackTask.OnCompleteListener() { // from class: com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent.6.1
                    @Override // com.amazon.mp3.task.DeleteTrackTask.OnCompleteListener
                    public void onComplete() {
                        AnonymousClass6 anonymousClass6 = AnonymousClass6.this;
                        AccessibilityUtil.announceForAccessibility(activity, string);
                        AnonymousClass6 anonymousClass62 = AnonymousClass6.this;
                        RemoveLibraryContentProvider.OnRemoveLibraryContentListener onRemoveLibraryContentListener2 = onRemoveListener;
                        if (onRemoveLibraryContentListener2 != null) {
                            onRemoveLibraryContentListener2.onRemoveContentComplete(tracksUri);
                        }
                    }
                });
                deleteTrackTask.execute(new Void[0]);
                if (removeFromDeviceAfterCloud) {
                    DeleteUtil.deleteTracksFromDevice(activity, uriToDelete, deleteListener);
                }
            }
        });
        accessibilityFocusDialogBuilder.setNegativeButton(holder.mNegativeBtnStringId, (DialogInterface.OnClickListener) null);
        accessibilityFocusDialogBuilder.setTitle(holder.mTitleStringId);
        accessibilityFocusDialogBuilder.setMessage(holder.mMessageString);
        return accessibilityFocusDialogBuilder.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Dialog buildConfirmDeviceDeleteDialog(final Activity activity, final Uri tracksUri, final ConfirmDeleteDialogHolder<?> holder, final RemoveLibraryContentProvider.OnRemoveLibraryContentListener onRemoveListener) {
        final String string = activity.getString(holder.mOnDeleteDeviceCompleteA11yMsgId);
        final String string2 = activity.getString(R.string.removal_fail_content_description);
        final DeleteListener deleteListener = new DeleteListener() { // from class: com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent.7
            @Override // com.amazon.mp3.library.provider.source.local.DeleteListener
            public void onComplete(DeleteListener.DeletionResult result) {
                if (result.getIsSuccess()) {
                    AccessibilityUtil.announceForAccessibility(activity, string);
                    RemoveLibraryContentProvider.OnRemoveLibraryContentListener onRemoveLibraryContentListener = onRemoveListener;
                    if (onRemoveLibraryContentListener != null) {
                        onRemoveLibraryContentListener.onRemoveContentComplete(tracksUri);
                        return;
                    }
                    return;
                }
                if (result.getIsSDCardPermissionError()) {
                    Intent intent = new Intent(activity, (Class<?>) GrantSDCardPermissionMainPageActivity.class);
                    intent.putExtra("com.amazon.mp3.tracksuri", result.getTracksUri().toString());
                    activity.startActivityForResult(intent, GrantSDCardPermissionMainPageActivity.SDCARD_PERMISSION_MAIN_PAGE_REQUEST_CODE);
                    return;
                }
                AccessibilityUtil.announceForAccessibility(activity, string2);
            }
        };
        AccessibilityFocusDialogBuilder accessibilityFocusDialogBuilder = new AccessibilityFocusDialogBuilder(activity);
        accessibilityFocusDialogBuilder.setPositiveButton(R.string.dmusic_button_yes, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                DeleteUtil.deleteTracksFromDevice(activity, tracksUri, deleteListener, true);
            }
        });
        accessibilityFocusDialogBuilder.setNegativeButton(R.string.dmusic_button_no, (DialogInterface.OnClickListener) null);
        accessibilityFocusDialogBuilder.setMessage(holder.mMessageString);
        accessibilityFocusDialogBuilder.setTitle(holder.mTitleStringId);
        return accessibilityFocusDialogBuilder.create();
    }

    public static class ConfirmDeleteDialogHolder<T extends AbstractItem> {
        public T mLibraryItem;
        public String mMessageString;
        public int mNegativeBtnStringId;
        public int mOnDeleteCompleteA11yMsgId;
        public int mOnDeleteDeviceCompleteA11yMsgId;
        public int mPositiveBtnStringId;
        public int mTitleStringId;
        public int mToastStringId;

        public ConfirmDeleteDialogHolder(T item, int titleId, String message, int toastId, int positiveId, int negativeId, int onDeleteCompleteA11yMsgId, int onDeleteDeviceCompleteA11yMsgId) {
            this.mLibraryItem = item;
            this.mTitleStringId = titleId;
            this.mMessageString = message;
            this.mToastStringId = toastId;
            this.mPositiveBtnStringId = positiveId;
            this.mNegativeBtnStringId = negativeId;
            this.mOnDeleteCompleteA11yMsgId = onDeleteCompleteA11yMsgId;
            this.mOnDeleteDeviceCompleteA11yMsgId = onDeleteDeviceCompleteA11yMsgId;
        }
    }
}
