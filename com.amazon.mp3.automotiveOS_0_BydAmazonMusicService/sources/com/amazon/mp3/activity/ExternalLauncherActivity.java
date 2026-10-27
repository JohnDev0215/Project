package com.amazon.mp3.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.fragment.DownloadsFragment;
import com.amazon.mp3.library.fragment.RecentActivityTabFragmentHost;
import com.amazon.mp3.library.provider.ExternalProvider;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.service.metrics.UxMetricsLogger;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ExternalLauncherActivity extends Activity {
    private static final String TAG = "ExternalLauncherActivity";

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseActivity.setLowMemory(getClass(), false);
        handleNewIntent(getIntent());
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleNewIntent(intent);
    }

    private void handleNewIntent(final Intent intent) {
        if (intent == null) {
            finish();
            return;
        }
        String action = intent.getAction();
        if ("com.amazon.mp3.otter.action.SHOW_LIBRARY".equals(action)) {
            Intent startIntent = MusicHomeActivity.getStartIntent(this);
            startIntent.addFlags(268451840);
            startActivity(startIntent);
            Log.warning(TAG, "ExternalLibraryActivity should not be handling this intent anymore!");
        } else if ("com.amazon.music.downloads.notification.VIEW_DOWNLOADS".equals(action)) {
            startActivity(MusicHomeActivity.getStartIntent(this).putExtra("com.amazon.mp3.fragment.extra", DownloadsFragment.class.getSimpleName()));
        } else if ("com.amazon.music.downloads.notification.VIEW_DOWNLOADED_TAB".equals(action)) {
            Intent startIntent2 = MusicHomeActivity.getStartIntent(this);
            startIntent2.putExtra("com.amazon.mp3.fragment.extra", RecentActivityTabFragmentHost.class.getSimpleName());
            startIntent2.putExtra("com.amazon.mp3.library.fragment.EXTRA_TAB_POSITION_TO_OPEN", RecentActivityTabFragmentHost.getRecentActivityDownloadedPosition());
            startActivity(startIntent2);
        } else if ("android.intent.action.VIEW".equals(action)) {
            UxMetricsLogger.registerUserAction(UxMetricsLogger.UserAction.LAUNCHER_LIBRARY_SEARCH);
            Uri data = intent.getData();
            if (data != null) {
                List<String> pathSegments = data.getPathSegments();
                Uri uriManageInavlidUriFromExternalApps = null;
                if (pathSegments.size() > 0 && ("nowplaying".equals(pathSegments.get(0)) || "nowplaying".equals(pathSegments.get(1)))) {
                    uriManageInavlidUriFromExternalApps = Uri.parse("content://" + MediaProvider.AUTHORITY + "/nowplaying");
                } else if (pathSegments.size() == 2) {
                    uriManageInavlidUriFromExternalApps = manageInavlidUriFromExternalApps(Uri.parse(ExternalProvider.decodeKey(pathSegments.get(1))));
                    Cursor cursorQuery = MediaProvider.getInstance().query(uriManageInavlidUriFromExternalApps, null, null, null, null);
                    if (cursorQuery == null || cursorQuery.getCount() == 0) {
                        ExternalProvider.deleteRecentKey(getApplicationContext(), uriManageInavlidUriFromExternalApps.toString());
                    }
                    DbUtil.closeCursor(cursorQuery);
                }
                if (uriManageInavlidUriFromExternalApps != null) {
                    Log.debug(TAG, "Firing intent %s", uriManageInavlidUriFromExternalApps.toString());
                    if (checkShowItemDeletedDialog(uriManageInavlidUriFromExternalApps)) {
                        finish();
                        return;
                    }
                    if (CirrusMediaSource.matchCloud(uriManageInavlidUriFromExternalApps) && !AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
                        finish();
                        return;
                    }
                    Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(this, uriManageInavlidUriFromExternalApps);
                    intentForContentUri.setAction("android.intent.action.VIEW");
                    intentForContentUri.addFlags(intent.getFlags());
                    intentForContentUri.putExtra("com.amazon.mp3.launch.EXTERNAL", true);
                    startActivity(intentForContentUri);
                }
            }
        }
        finish();
    }

    private boolean checkShowItemDeletedDialog(Uri uri) {
        Cursor cursorQuery = getContentResolver().query(uri, new String[]{"_id"}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() > 0) {
                    DbUtil.closeCursor(cursorQuery);
                    return false;
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        startActivity(new Intent(this, (Class<?>) RecentItemDeletedActivity.class));
        return true;
    }

    private Uri manageInavlidUriFromExternalApps(Uri targetUri) {
        int iIndexOf = targetUri.toString().indexOf("tracks");
        if (iIndexOf == -1) {
            return targetUri;
        }
        return Uri.parse(targetUri.toString().substring(0, iIndexOf) + "albums");
    }
}
