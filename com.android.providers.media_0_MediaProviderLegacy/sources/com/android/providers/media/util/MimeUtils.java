package com.android.providers.media.util;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class MimeUtils {
    public static boolean startsWithIgnoreCase(String str, String str2) {
        if (str == null || str2 == null || str2.length() > str.length()) {
            return false;
        }
        return str.regionMatches(true, 0, str2, 0, str2.length());
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
