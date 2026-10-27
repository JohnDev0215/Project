package com.android.car.settings.system;

import android.content.Context;
import com.android.car.settings.common.AsyncLoader;
import com.android.car.settings.common.Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class LicenseHtmlLoader extends AsyncLoader<File> {
    private static final String NOTICE_HTML_FILE_NAME = "NOTICE.html";
    private final Context mContext;
    private static final Logger LOG = new Logger((Class<?>) LicenseHtmlLoader.class);
    private static final String[] DEFAULT_LICENSE_XML_PATHS = {"/system/etc/NOTICE.xml.gz", "/vendor/etc/NOTICE.xml.gz", "/odm/etc/NOTICE.xml.gz", "/oem/etc/NOTICE.xml.gz"};

    public LicenseHtmlLoader(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public File loadInBackground() {
        return generateHtmlFromDefaultXmlFiles();
    }

    private File generateHtmlFromDefaultXmlFiles() {
        List<File> xmlFiles = getVaildXmlFiles();
        if (xmlFiles.isEmpty()) {
            LOG.e("No notice file exists.");
            return null;
        }
        File cachedHtmlFile = getCachedHtmlFile();
        if (!isCachedHtmlFileOutdated(xmlFiles, cachedHtmlFile) || generateHtmlFile(xmlFiles, cachedHtmlFile)) {
            return cachedHtmlFile;
        }
        return null;
    }

    private List<File> getVaildXmlFiles() {
        List<File> xmlFiles = new ArrayList<>();
        for (String xmlPath : DEFAULT_LICENSE_XML_PATHS) {
            File file = new File(xmlPath);
            if (file.exists() && file.length() != 0) {
                xmlFiles.add(file);
            }
        }
        return xmlFiles;
    }

    private File getCachedHtmlFile() {
        return new File(this.mContext.getCacheDir(), NOTICE_HTML_FILE_NAME);
    }

    private boolean isCachedHtmlFileOutdated(List<File> xmlFiles, File cachedHtmlFile) {
        if (!cachedHtmlFile.exists() || cachedHtmlFile.length() == 0) {
            return true;
        }
        for (File file : xmlFiles) {
            if (cachedHtmlFile.lastModified() < file.lastModified()) {
                return true;
            }
        }
        return false;
    }

    private boolean generateHtmlFile(List<File> xmlFiles, File htmlFile) {
        return LicenseHtmlGeneratorFromXml.generateHtml(xmlFiles, htmlFile);
    }
}
