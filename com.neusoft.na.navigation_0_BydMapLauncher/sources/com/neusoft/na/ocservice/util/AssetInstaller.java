package com.neusoft.na.ocservice.util;

import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: AssetInstaller.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007H\u0002J\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\nJ\b\u0010\u0016\u001a\u00020\u0012H\u0002J\u0016\u0010\u0017\u001a\u00020\u0005*\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/neusoft/na/ocservice/util/AssetInstaller;", "", "assetManager", "Landroid/content/res/AssetManager;", "mSrcFolder", "", "mTargetDir", "Ljava/io/File;", "(Landroid/content/res/AssetManager;Ljava/lang/String;Ljava/io/File;)V", "isOutdated", "", "()Z", "mHashFile", "mListFile", "copyAssetFile", "assetFileName", "destination", "createDir", "", "dir", "install", "force", "removeAssets", "readTextAndClose", "Ljava/io/InputStream;", "charset", "Ljava/nio/charset/Charset;", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class AssetInstaller {
    private static final Logger logger = LoggerFactory.getLogger("AssetInstaller");
    private final AssetManager assetManager;
    private final String mHashFile;
    private final String mListFile;
    private final String mSrcFolder;
    private final File mTargetDir;

    public AssetInstaller(AssetManager assetManager, String mSrcFolder, File mTargetDir) {
        Intrinsics.checkNotNullParameter(assetManager, "assetManager");
        Intrinsics.checkNotNullParameter(mSrcFolder, "mSrcFolder");
        Intrinsics.checkNotNullParameter(mTargetDir, "mTargetDir");
        this.assetManager = assetManager;
        this.mSrcFolder = mSrcFolder;
        this.mTargetDir = mTargetDir;
        this.mHashFile = mSrcFolder + ".hash";
        this.mListFile = mSrcFolder + ".list";
    }

    public final boolean isOutdated() {
        try {
            InputStream inputStreamOpen = this.assetManager.open(this.mHashFile);
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "assetManager.open(mHashFile)");
            String string = StringsKt.trim((CharSequence) readTextAndClose$default(this, inputStreamOpen, null, 1, null)).toString();
            String string2 = StringsKt.trim((CharSequence) readTextAndClose$default(this, new FileInputStream(new File(this.mTargetDir, this.mHashFile)), null, 1, null)).toString();
            if (string.length() > 0) {
                if (Intrinsics.areEqual(string, string2)) {
                    return false;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return true;
    }

    public final boolean install(boolean force) throws IOException {
        boolean zCopyAssetFile = true;
        if (!force && !isOutdated()) {
            return true;
        }
        removeAssets();
        InputStream inputStreamOpen = this.assetManager.open(this.mListFile);
        Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "assetManager.open(mListFile)");
        List listSplit$default = StringsKt.split$default((CharSequence) readTextAndClose$default(this, inputStreamOpen, null, 1, null), new String[]{"\n"}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            arrayList.add(StringsKt.trim((CharSequence) it.next()).toString());
        }
        ArrayList<String> arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            if (((String) next).length() > 0) {
                arrayList2.add(next);
            }
        }
        String str = new File(this.mSrcFolder).getName() + IOUtils.DIR_SEPARATOR_UNIX;
        for (String str2 : arrayList2) {
            if (StringsKt.startsWith$default(str2, str, false, 2, (Object) null)) {
                String strSubstring = str2.substring(str.length());
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                zCopyAssetFile = copyAssetFile(this.mSrcFolder + IOUtils.DIR_SEPARATOR_UNIX + strSubstring, new File(this.mTargetDir, strSubstring));
            } else {
                logger.warn("invalid file in list file: " + str2);
                zCopyAssetFile = false;
            }
            if (!zCopyAssetFile) {
                break;
            }
        }
        return zCopyAssetFile ? zCopyAssetFile & copyAssetFile(this.mHashFile, new File(this.mTargetDir, this.mHashFile)) : zCopyAssetFile;
    }

    private final boolean copyAssetFile(String assetFileName, File destination) {
        logger.debug("extract asset file '" + assetFileName + "' to " + destination);
        try {
            File parentFile = destination.getParentFile();
            Intrinsics.checkNotNullExpressionValue(parentFile, "destination.parentFile");
            createDir(parentFile);
            FileOutputStream fileOutputStreamOpen = this.assetManager.open(assetFileName);
            try {
                InputStream input = fileOutputStreamOpen;
                fileOutputStreamOpen = new FileOutputStream(destination);
                try {
                    Intrinsics.checkNotNullExpressionValue(input, "input");
                    ByteStreamsKt.copyTo$default(input, fileOutputStreamOpen, 0, 2, null);
                    CloseableKt.closeFinally(fileOutputStreamOpen, null);
                    CloseableKt.closeFinally(fileOutputStreamOpen, null);
                    return true;
                } finally {
                }
            } finally {
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    private final void removeAssets() {
        try {
            FilesKt.deleteRecursively(this.mTargetDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final void createDir(File dir) {
        if (dir.exists()) {
            return;
        }
        dir.mkdirs();
    }

    static /* synthetic */ String readTextAndClose$default(AssetInstaller assetInstaller, InputStream inputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return assetInstaller.readTextAndClose(inputStream, charset);
    }

    private final String readTextAndClose(InputStream inputStream, Charset charset) {
        Reader inputStreamReader = new InputStreamReader(inputStream, charset);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            String text = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            return text;
        } finally {
        }
    }
}
