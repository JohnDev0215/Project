package com.android.providers.media.util;

import android.webkit.MimeTypeMap;
import java.io.File;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class MimeUtils {
    public static boolean equalIgnoreCase(String str, String str2) {
        return str != null && str.equalsIgnoreCase(str2);
    }

    public static boolean startsWithIgnoreCase(String str, String str2) {
        if (str == null || str2 == null || str2.length() > str.length()) {
            return false;
        }
        return str.regionMatches(true, 0, str2, 0, str2.length());
    }

    public static String resolveMimeType(File file) {
        String strExtractFileExtension = FileUtils.extractFileExtension(file.getPath());
        if (strExtractFileExtension == null) {
            return "application/octet-stream";
        }
        if (strExtractFileExtension.equalsIgnoreCase("ape")) {
            return "audio/wav";
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(strExtractFileExtension.toLowerCase(Locale.ROOT));
        return mimeTypeFromExtension == null ? "application/octet-stream" : mimeTypeFromExtension;
    }

    public static int resolveMediaType(String str) {
        if (isPlaylistMimeType(str)) {
            return 4;
        }
        if (isSubtitleMimeType(str)) {
            return 5;
        }
        if (isAudioMimeType(str)) {
            return 2;
        }
        if (isVideoMimeType(str)) {
            return 3;
        }
        if (isImageMimeType(str)) {
            return 1;
        }
        return isDocumentMimeType(str) ? 6 : 0;
    }

    public static int resolveFormatCode(String str) {
        int iResolveMediaType = resolveMediaType(str);
        if (iResolveMediaType == 1) {
            return 14336;
        }
        if (iResolveMediaType != 2) {
            return iResolveMediaType != 3 ? 12288 : 47488;
        }
        return 47360;
    }

    public static String extractPrimaryType(String str) {
        int iIndexOf = str.indexOf(47);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException();
        }
        return str.substring(0, iIndexOf);
    }

    public static boolean isAudioMimeType(String str) {
        if (str == null) {
            return false;
        }
        return startsWithIgnoreCase(str, "audio/");
    }

    public static boolean isVideoMimeType(String str) {
        if (str == null) {
            return false;
        }
        return startsWithIgnoreCase(str, "video/");
    }

    public static boolean isImageMimeType(String str) {
        if (str == null) {
            return false;
        }
        return startsWithIgnoreCase(str, "image/");
    }

    public static boolean isPlaylistMimeType(String str) {
        if (str == null) {
            return false;
        }
        switch (str.toLowerCase(Locale.ROOT)) {
        }
        return false;
    }

    public static boolean isSubtitleMimeType(String str) {
        if (str == null) {
            return false;
        }
        switch (str.toLowerCase(Locale.ROOT)) {
        }
        return false;
    }

    public static boolean isDocumentMimeType(String str) {
        if (str == null) {
            return false;
        }
        if (startsWithIgnoreCase(str, "text/")) {
            return true;
        }
        switch (str.toLowerCase(Locale.ROOT)) {
            case "application/epub+zip":
            case "application/msword":
            case "application/pdf":
            case "application/rtf":
            case "application/vnd.ms-excel":
            case "application/vnd.ms-excel.addin.macroenabled.12":
            case "application/vnd.ms-excel.sheet.binary.macroenabled.12":
            case "application/vnd.ms-excel.sheet.macroenabled.12":
            case "application/vnd.ms-excel.template.macroenabled.12":
            case "application/vnd.ms-powerpoint":
            case "application/vnd.ms-powerpoint.addin.macroenabled.12":
            case "application/vnd.ms-powerpoint.presentation.macroenabled.12":
            case "application/vnd.ms-powerpoint.slideshow.macroenabled.12":
            case "application/vnd.ms-powerpoint.template.macroenabled.12":
            case "application/vnd.ms-word.document.macroenabled.12":
            case "application/vnd.ms-word.template.macroenabled.12":
            case "application/vnd.oasis.opendocument.chart":
            case "application/vnd.oasis.opendocument.database":
            case "application/vnd.oasis.opendocument.formula":
            case "application/vnd.oasis.opendocument.graphics":
            case "application/vnd.oasis.opendocument.graphics-template":
            case "application/vnd.oasis.opendocument.presentation":
            case "application/vnd.oasis.opendocument.presentation-template":
            case "application/vnd.oasis.opendocument.spreadsheet":
            case "application/vnd.oasis.opendocument.spreadsheet-template":
            case "application/vnd.oasis.opendocument.text":
            case "application/vnd.oasis.opendocument.text-master":
            case "application/vnd.oasis.opendocument.text-template":
            case "application/vnd.oasis.opendocument.text-web":
            case "application/vnd.openxmlformats-officedocument.presentationml.presentation":
            case "application/vnd.openxmlformats-officedocument.presentationml.slideshow":
            case "application/vnd.openxmlformats-officedocument.presentationml.template":
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.template":
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.template":
            case "application/vnd.stardivision.calc":
            case "application/vnd.stardivision.chart":
            case "application/vnd.stardivision.draw":
            case "application/vnd.stardivision.impress":
            case "application/vnd.stardivision.impress-packed":
            case "application/vnd.stardivision.mail":
            case "application/vnd.stardivision.math":
            case "application/vnd.stardivision.writer":
            case "application/vnd.stardivision.writer-global":
            case "application/vnd.sun.xml.calc":
            case "application/vnd.sun.xml.calc.template":
            case "application/vnd.sun.xml.draw":
            case "application/vnd.sun.xml.draw.template":
            case "application/vnd.sun.xml.impress":
            case "application/vnd.sun.xml.impress.template":
            case "application/vnd.sun.xml.math":
            case "application/vnd.sun.xml.writer":
            case "application/vnd.sun.xml.writer.global":
            case "application/vnd.sun.xml.writer.template":
            case "application/x-mspublisher":
                return true;
            default:
                return false;
        }
    }
}
