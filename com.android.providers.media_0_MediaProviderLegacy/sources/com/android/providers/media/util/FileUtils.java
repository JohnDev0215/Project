package com.android.providers.media.util;

import android.content.ContentValues;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class FileUtils {
    private static final String CAMERA_RELATIVE_PATH;
    public static final String[] DEFAULT_FOLDER_NAMES;
    public static final Pattern PATTERN_DOWNLOADS_FILE;
    public static final Pattern PATTERN_EXPIRES_FILE;
    public static final Pattern PATTERN_OWNED_PATH;
    private static final Pattern PATTERN_RELATIVE_PATH;
    private static final Pattern PATTERN_VOLUME_NAME;

    private static boolean isValidFatFilenameChar(char c) {
        return ((c >= 0 && c <= 31) || c == '\"' || c == '*' || c == '/' || c == ':' || c == '<' || c == '\\' || c == '|' || c == 127 || c == '>' || c == '?') ? false : true;
    }

    public static File buildPath(File file, String... strArr) {
        for (String str : strArr) {
            if (file == null) {
                file = new File(str);
            } else {
                file = new File(file, str);
            }
        }
        return file;
    }

    public static String buildValidFatFilename(String str) {
        if (TextUtils.isEmpty(str) || ".".equals(str) || "..".equals(str)) {
            return "(invalid)";
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (isValidFatFilenameChar(cCharAt)) {
                sb.append(cCharAt);
            } else {
                sb.append('_');
            }
        }
        trimFilename(sb, 255);
        return sb.toString();
    }

    private static void trimFilename(StringBuilder sb, int i) {
        byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
        if (bytes.length > i) {
            int i2 = i - 3;
            while (bytes.length > i2) {
                sb.deleteCharAt(sb.length() / 2);
                bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
            }
            sb.insert(sb.length() / 2, "...");
        }
    }

    static {
        Pattern.compile("([A-Z0-9_]{4})([0-9]{4})");
        Pattern.compile("((?:IMG|MVIMG|VID)_[0-9]{8}_[0-9]{6})(?:~([0-9]+))?");
        PATTERN_DOWNLOADS_FILE = Pattern.compile("(?i)^/storage/[^/]+/(?:[0-9]+/)?(?:Android/sandbox/[^/]+/)?Download/.+");
        Pattern.compile("(?i)^/storage/[^/]+/(?:[0-9]+/)?(?:Android/sandbox/[^/]+/)?Download/?");
        PATTERN_EXPIRES_FILE = Pattern.compile("(?i)^\\.(pending|trashed)-(\\d+)-([^/]+)$");
        Pattern.compile(".*/\\.pending-(\\d+)-([^/]+)$");
        PATTERN_OWNED_PATH = Pattern.compile("(?i)^/storage/[^/]+/(?:[0-9]+/)?Android/(?:data|media|obb|sandbox)/([^/]+)(/?.*)?");
        Pattern.compile("(?i)^/storage/[^/]+/(?:[0-9]+/)?Android/(?:data|obb)/?$");
        DEFAULT_FOLDER_NAMES = new String[]{Environment.DIRECTORY_MUSIC, Environment.DIRECTORY_PODCASTS, Environment.DIRECTORY_RINGTONES, Environment.DIRECTORY_ALARMS, Environment.DIRECTORY_NOTIFICATIONS, Environment.DIRECTORY_PICTURES, Environment.DIRECTORY_MOVIES, Environment.DIRECTORY_DOWNLOADS, Environment.DIRECTORY_DCIM, Environment.DIRECTORY_DOCUMENTS, Environment.DIRECTORY_AUDIOBOOKS};
        PATTERN_RELATIVE_PATH = Pattern.compile("(?i)^/storage/(?:emulated/[0-9]+/|[^/]+/)(Android/sandbox/([^/]+)/)?");
        PATTERN_VOLUME_NAME = Pattern.compile("(?i)^/storage/([^/]+)");
        CAMERA_RELATIVE_PATH = String.format("%s/%s/", Environment.DIRECTORY_DCIM, "Camera");
    }

    public static String extractDisplayName(String str) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(47) == -1) {
            return str;
        }
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        return str.substring(str.lastIndexOf(47) + 1);
    }

    private static String normalizeUuid(String str) {
        if (str != null) {
            return str.toLowerCase(Locale.ROOT);
        }
        return null;
    }

    public static String extractVolumePath(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = PATTERN_RELATIVE_PATH.matcher(str);
        if (matcher.find()) {
            return str.substring(0, matcher.end());
        }
        return null;
    }

    public static String extractVolumeName(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = PATTERN_VOLUME_NAME.matcher(str);
        if (!matcher.find()) {
            return "internal";
        }
        String strGroup = matcher.group(1);
        return strGroup.equals("emulated") ? "external_primary" : normalizeUuid(strGroup);
    }

    public static String extractRelativePath(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = PATTERN_RELATIVE_PATH.matcher(str);
        if (!matcher.find()) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(47);
        return (iLastIndexOf == -1 || iLastIndexOf < matcher.end()) ? "/" : str.substring(matcher.end(), iLastIndexOf + 1);
    }

    public static String extractRelativePathForDirectory(String str) {
        if (str != null && !str.equals("/storage/emulated") && !str.equals("/storage/emulated/")) {
            if (!str.endsWith("/")) {
                str = str + "/";
            }
            Matcher matcher = PATTERN_RELATIVE_PATH.matcher(str);
            if (matcher.find()) {
                return matcher.end() == str.length() ? "/" : str.substring(matcher.end());
            }
        }
        return null;
    }

    public static boolean isDefaultDirectoryName(String str) {
        for (String str2 : DEFAULT_FOLDER_NAMES) {
            if (str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public static void computeValuesFromData(ContentValues contentValues, boolean z) {
        contentValues.remove("volume_name");
        contentValues.remove("relative_path");
        contentValues.remove("is_trashed");
        contentValues.remove("date_expires");
        contentValues.remove("_display_name");
        contentValues.remove("bucket_id");
        contentValues.remove("bucket_display_name");
        String asString = contentValues.getAsString("_data");
        if (TextUtils.isEmpty(asString)) {
            return;
        }
        File file = new File(asString);
        File file2 = new File(asString.toLowerCase(Locale.ROOT));
        contentValues.put("volume_name", extractVolumeName(asString));
        contentValues.put("relative_path", extractRelativePath(asString));
        String strExtractDisplayName = extractDisplayName(asString);
        Matcher matcher = PATTERN_EXPIRES_FILE.matcher(strExtractDisplayName);
        if (matcher.matches()) {
            contentValues.put("is_pending", Integer.valueOf(matcher.group(1).equals("pending") ? 1 : 0));
            contentValues.put("is_trashed", Integer.valueOf(matcher.group(1).equals("trashed") ? 1 : 0));
            contentValues.put("date_expires", Long.valueOf(Long.parseLong(matcher.group(2))));
            contentValues.put("_display_name", matcher.group(3));
        } else {
            if (!z) {
                contentValues.put("is_pending", (Integer) 0);
            }
            contentValues.put("is_trashed", (Integer) 0);
            contentValues.putNull("date_expires");
            contentValues.put("_display_name", strExtractDisplayName);
        }
        String parent = file2.getParent();
        if (parent != null) {
            contentValues.put("bucket_id", Integer.valueOf(parent.hashCode()));
            if ("/".equals(contentValues.getAsString("relative_path"))) {
                return;
            }
            contentValues.put("bucket_display_name", file.getParentFile().getName());
        }
    }

    public static void computeDataFromValues(ContentValues contentValues, File file, boolean z) {
        contentValues.remove("_data");
        String asString = contentValues.getAsString("_display_name");
        if (!z && DatabaseUtils.getAsBoolean(contentValues, "is_pending", false)) {
            asString = String.format(".%s-%d-%s", "pending", Long.valueOf(DatabaseUtils.getAsLong(contentValues, "date_expires", (System.currentTimeMillis() + 604800000) / 1000)), asString);
        } else if (DatabaseUtils.getAsBoolean(contentValues, "is_trashed", false)) {
            asString = String.format(".%s-%d-%s", "trashed", Long.valueOf(DatabaseUtils.getAsLong(contentValues, "date_expires", (System.currentTimeMillis() + 2592000000L) / 1000)), asString);
        }
        contentValues.put("_data", buildPath(file, contentValues.getAsString("relative_path"), asString).getAbsolutePath());
    }

    public static String[] sanitizePath(String str) {
        if (str == null) {
            return new String[0];
        }
        String[] strArrSplit = str.split("/");
        if (strArrSplit.length == 0) {
            return new String[]{""};
        }
        for (int i = 0; i < strArrSplit.length; i++) {
            strArrSplit[i] = sanitizeDisplayName(strArrSplit[i]);
        }
        return strArrSplit;
    }

    public static String sanitizeDisplayName(String str) {
        return sanitizeDisplayName(str, false);
    }

    public static String sanitizeDisplayName(String str, boolean z) {
        if (str == null) {
            return null;
        }
        if (z && str.startsWith(".")) {
            return "_" + str;
        }
        return buildValidFatFilename(str);
    }

    public static boolean isDirectoryHidden(File file) {
        String name = file.getName();
        if (name.startsWith(".")) {
            return true;
        }
        File file2 = new File(file, ".nomedia");
        if (!file2.exists()) {
            return false;
        }
        String[] strArrSanitizePath = sanitizePath(extractRelativePath(file.getAbsolutePath()));
        if ((strArrSanitizePath.length == 1 && TextUtils.isEmpty(strArrSanitizePath[0])) && isDefaultDirectoryName(name)) {
            file2.delete();
            return false;
        }
        if (CAMERA_RELATIVE_PATH.equalsIgnoreCase(extractRelativePathForDirectory(file.getAbsolutePath()))) {
            file2.delete();
            return false;
        }
        Logging.logPersistent("Observed non-standard " + file2);
        return true;
    }

    public static boolean isFileHidden(File file) {
        String name = file.getName();
        return !PATTERN_EXPIRES_FILE.matcher(name).matches() && name.startsWith(".");
    }
}
