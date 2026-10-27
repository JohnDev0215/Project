package com.android.car.settings.system;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import java.io.File;

/* JADX INFO: loaded from: classes5.dex */
public class ThirdPartyLicensesActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<File> {
    private static final String DEFAULT_LICENSE_PATH = "/system/etc/NOTICE.html.gz";
    private static final String FILE_PROVIDER_AUTHORITY = "com.android.settings.files";
    private static final String HTML_VIEWER_PACKAGE = "com.android.htmlviewer";
    private static final int LOADER_ID_LICENSE_HTML_LOADER = 0;
    private static final Logger LOG = new Logger((Class<?>) ThirdPartyLicensesActivity.class);
    private static final String PROPERTY_LICENSE_PATH = "ro.config.license_path";

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String licenseHtmlPath = SystemProperties.get(PROPERTY_LICENSE_PATH, DEFAULT_LICENSE_PATH);
        if (isFilePathValid(licenseHtmlPath)) {
            showSelectedFile(licenseHtmlPath);
        } else {
            showHtmlFromDefaultXmlFiles();
        }
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<File> onCreateLoader(int id, Bundle args) {
        return new LicenseHtmlLoader(this);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<File> loader, File generatedHtmlFile) {
        showGeneratedHtmlFile(generatedHtmlFile);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<File> loader) {
    }

    private void showSelectedFile(String path) {
        if (TextUtils.isEmpty(path)) {
            LOG.e("The system property for the license file is empty");
            showErrorAndFinish();
            return;
        }
        File file = new File(path);
        if (!isFileValid(file)) {
            LOG.e("License file " + path + " does not exist");
            showErrorAndFinish();
            return;
        }
        showHtmlFromUri(Uri.fromFile(file));
    }

    private void showErrorAndFinish() {
        finish();
    }

    private void showHtmlFromUri(Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        intent.putExtra("android.intent.extra.TITLE", getString(R.string.settings_license_activity_title));
        if ("content".equals(uri.getScheme())) {
            intent.addFlags(1);
        }
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setPackage(HTML_VIEWER_PACKAGE);
        try {
            startActivity(intent);
            finish();
        } catch (ActivityNotFoundException e) {
            LOG.e("Failed to find viewer", e);
            showErrorAndFinish();
        }
    }

    private void showHtmlFromDefaultXmlFiles() {
        LoaderManager.getInstance(this).initLoader(0, Bundle.EMPTY, this);
    }

    private void showGeneratedHtmlFile(File generatedHtmlFile) {
        if (generatedHtmlFile != null) {
            LOG.i("File size: " + generatedHtmlFile.length());
            showHtmlFromUri(getUriFromGeneratedHtmlFile(generatedHtmlFile));
            return;
        }
        LOG.e("Failed to generate.");
        showErrorAndFinish();
    }

    private Uri getUriFromGeneratedHtmlFile(File generatedHtmlFile) {
        return FileProvider.getUriForFile(this, FILE_PROVIDER_AUTHORITY, generatedHtmlFile);
    }

    private boolean isFilePathValid(String path) {
        return !TextUtils.isEmpty(path) && isFileValid(new File(path));
    }

    private boolean isFileValid(File file) {
        return file.exists() && file.length() != 0;
    }
}
