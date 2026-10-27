package com.amazon.mp3.library.item;

import android.os.Environment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.IoUtil;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class DeluxeContentUtil {
    public static final String EXTRA_ASIN;
    public static final String EXTRA_EXTENSION_KEY;
    public static final String EXTRA_LUID;
    public static final String EXTRA_PATH_KEY;
    public static final String EXTRA_REQUEST_KEY;
    public static final String EXTRA_TITLE_KEY;
    public static final String EXTRA_URI_KEY;
    private static final Set<String> SUPPORTED_BOOKLET_EXTENSIONS;
    private static final Set<String> SUPPORTED_DELUXE_EXTENSIONS;
    private static final Set<String> SUPPORTED_VIDEO_EXTENSIONS;
    public static final String TEMP_BOOKLET_DIR;
    private static String sNonDeluxeSelection;

    public enum ContentType {
        AUDIO,
        VIDEO,
        DOCUMENT
    }

    static {
        String name = DeluxeContentUtil.class.getName();
        EXTRA_REQUEST_KEY = name + ".EXTRA_REQUEST_KEY";
        EXTRA_TITLE_KEY = name + ".EXTRA_TITLE_KEY";
        EXTRA_EXTENSION_KEY = name + ".EXTRA_EXTENSION_KEY";
        EXTRA_URI_KEY = name + ".EXTRA_URI_KEY";
        EXTRA_PATH_KEY = name + ".EXTRA_PATH_KEY";
        EXTRA_ASIN = name + ".EXTRA_ASIN";
        EXTRA_LUID = name + ".EXTRA_LUID";
        TEMP_BOOKLET_DIR = String.format(Locale.US, "%s/%s/%s/.booklets/", Environment.getExternalStorageDirectory().getPath(), AmazonApplication.getExternalStorageAppDirectory(), AmazonApplication.getExternalStorageTempDirectory());
        HashSet hashSet = new HashSet();
        Collections.addAll(hashSet, "mp4");
        Set<String> setUnmodifiableSet = Collections.unmodifiableSet(hashSet);
        SUPPORTED_VIDEO_EXTENSIONS = setUnmodifiableSet;
        HashSet hashSet2 = new HashSet();
        Collections.addAll(hashSet2, "pdf");
        SUPPORTED_BOOKLET_EXTENSIONS = Collections.unmodifiableSet(hashSet2);
        HashSet hashSet3 = new HashSet();
        hashSet3.addAll(setUnmodifiableSet);
        hashSet3.addAll(hashSet2);
        SUPPORTED_DELUXE_EXTENSIONS = Collections.unmodifiableSet(hashSet3);
        sNonDeluxeSelection = constructNonDeluxeSelection();
    }

    private static String constructNonDeluxeSelection() {
        StringBuilder sb = new StringBuilder();
        int size = SUPPORTED_DELUXE_EXTENSIONS.size();
        sb.append(" (");
        sb.append("extension != ? ");
        for (int i = 1; i < size; i++) {
            sb.append("AND extension != ? ");
        }
        sb.append("OR extension is NULL)");
        return sb.toString();
    }

    public static boolean isDeluxeContent(String extension) {
        if (extension == null) {
            return false;
        }
        return SUPPORTED_DELUXE_EXTENSIONS.contains(extension.toLowerCase(Locale.US));
    }

    public static boolean isSupportedVideo(String extension) {
        return SUPPORTED_VIDEO_EXTENSIONS.contains(extension.toLowerCase(Locale.US));
    }

    public static String getNonDeluxeSelection() {
        return sNonDeluxeSelection;
    }

    public static String[] getNonDeluxeSelectionArgs() {
        Set<String> set = SUPPORTED_DELUXE_EXTENSIONS;
        return (String[]) set.toArray(new String[set.size()]);
    }

    public static ContentType getMediaType(String extension) {
        if (isDeluxeContent(extension)) {
            return isSupportedVideo(extension) ? ContentType.VIDEO : ContentType.DOCUMENT;
        }
        return ContentType.AUDIO;
    }

    public static void clearDiskCache() {
        IoUtil.deleteFilesInDirectory(TEMP_BOOKLET_DIR);
    }
}
