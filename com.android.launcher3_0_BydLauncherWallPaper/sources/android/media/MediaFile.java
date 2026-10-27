package android.media;

import android.bluetooth.BluetoothHidDevice;
import android.hardware.gnss.visibility_control.V1_0.IGnssVisibilityControlCallback;
import android.mtp.MtpConstants;
import com.android.internal.midi.MidiConstants;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.widget.MessagingMessage;
import com.google.android.mms.ContentType;
import java.util.HashMap;
import java.util.Locale;
import libcore.content.type.MimeMap;

/* JADX INFO: loaded from: classes2.dex */
public class MediaFile {

    @Deprecated
    private static final int FIRST_AUDIO_FILE_TYPE = 1;

    @Deprecated
    private static final int LAST_AUDIO_FILE_TYPE = 10;

    @Deprecated
    private static final HashMap<String, MediaFileType> sFileTypeMap = new HashMap<>();

    @Deprecated
    private static final HashMap<String, Integer> sFileTypeToFormatMap = new HashMap<>();
    private static final HashMap<String, Integer> sMimeTypeToFormatMap = new HashMap<>();
    private static final HashMap<Integer, String> sFormatToMimeTypeMap = new HashMap<>();

    @Deprecated
    static void addFileType(String str, int i, String str2) {
    }

    @Deprecated
    public static MediaFileType getFileType(String str) {
        return null;
    }

    @Deprecated
    public static int getFileTypeForMimeType(String str) {
        return 0;
    }

    @Deprecated
    public static boolean isAudioFileType(int i) {
        return false;
    }

    @Deprecated
    public static boolean isDrmFileType(int i) {
        return false;
    }

    @Deprecated
    public static boolean isImageFileType(int i) {
        return false;
    }

    @Deprecated
    public static boolean isPlayListFileType(int i) {
        return false;
    }

    @Deprecated
    public static boolean isVideoFileType(int i) {
        return false;
    }

    @Deprecated
    public static class MediaFileType {
        public final int fileType;
        public final String mimeType;

        MediaFileType(int i, String str) {
            this.fileType = i;
            this.mimeType = str;
        }
    }

    static {
        addFileType(12297, "audio/mpeg");
        addFileType(12296, ContentType.AUDIO_X_WAV);
        addFileType(MtpConstants.FORMAT_WMA, "audio/x-ms-wma");
        addFileType(MtpConstants.FORMAT_OGG, ContentType.AUDIO_OGG2);
        addFileType(MtpConstants.FORMAT_AAC, ContentType.AUDIO_AAC);
        addFileType(MtpConstants.FORMAT_FLAC, MediaFormat.MIMETYPE_AUDIO_FLAC);
        addFileType(12295, "audio/x-aiff");
        addFileType(MtpConstants.FORMAT_MP2, "audio/mpeg");
        addFileType(12299, "video/mpeg");
        addFileType(MtpConstants.FORMAT_MP4_CONTAINER, ContentType.VIDEO_MP4);
        addFileType(MtpConstants.FORMAT_3GP_CONTAINER, "video/3gpp");
        addFileType(MtpConstants.FORMAT_3GP_CONTAINER, ContentType.VIDEO_3G2);
        addFileType(12298, "video/avi");
        addFileType(MtpConstants.FORMAT_WMV, "video/x-ms-wmv");
        addFileType(12300, "video/x-ms-asf");
        addFileType(MtpConstants.FORMAT_EXIF_JPEG, ContentType.IMAGE_JPEG);
        addFileType(MtpConstants.FORMAT_GIF, ContentType.IMAGE_GIF);
        addFileType(MtpConstants.FORMAT_PNG, ContentType.IMAGE_PNG);
        addFileType(MtpConstants.FORMAT_BMP, ContentType.IMAGE_X_MS_BMP);
        addFileType(MtpConstants.FORMAT_HEIF, "image/heif");
        addFileType(MtpConstants.FORMAT_DNG, "image/x-adobe-dng");
        addFileType(MtpConstants.FORMAT_TIFF, "image/tiff");
        addFileType(MtpConstants.FORMAT_TIFF, "image/x-canon-cr2");
        addFileType(MtpConstants.FORMAT_TIFF, "image/x-nikon-nrw");
        addFileType(MtpConstants.FORMAT_TIFF, "image/x-sony-arw");
        addFileType(MtpConstants.FORMAT_TIFF, "image/x-panasonic-rw2");
        addFileType(MtpConstants.FORMAT_TIFF, "image/x-olympus-orf");
        addFileType(MtpConstants.FORMAT_TIFF, "image/x-pentax-pef");
        addFileType(MtpConstants.FORMAT_TIFF, "image/x-samsung-srw");
        addFileType(MtpConstants.FORMAT_TIFF_EP, "image/tiff");
        addFileType(MtpConstants.FORMAT_TIFF_EP, "image/x-nikon-nef");
        addFileType(MtpConstants.FORMAT_JP2, "image/jp2");
        addFileType(MtpConstants.FORMAT_JPX, "image/jpx");
        addFileType(MtpConstants.FORMAT_M3U_PLAYLIST, "audio/x-mpegurl");
        addFileType(MtpConstants.FORMAT_PLS_PLAYLIST, "audio/x-scpls");
        addFileType(MtpConstants.FORMAT_WPL_PLAYLIST, "application/vnd.ms-wpl");
        addFileType(MtpConstants.FORMAT_ASX_PLAYLIST, "video/x-ms-asf");
        addFileType(12292, "text/plain");
        addFileType(12293, "text/html");
        addFileType(MtpConstants.FORMAT_XML_DOCUMENT, "text/xml");
        addFileType(MtpConstants.FORMAT_MS_WORD_DOCUMENT, "application/msword");
        addFileType(MtpConstants.FORMAT_MS_WORD_DOCUMENT, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        addFileType(MtpConstants.FORMAT_MS_EXCEL_SPREADSHEET, "application/vnd.ms-excel");
        addFileType(MtpConstants.FORMAT_MS_EXCEL_SPREADSHEET, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        addFileType(MtpConstants.FORMAT_MS_POWERPOINT_PRESENTATION, "application/vnd.ms-powerpoint");
        addFileType(MtpConstants.FORMAT_MS_POWERPOINT_PRESENTATION, "application/vnd.openxmlformats-officedocument.presentationml.presentation");
    }

    private static void addFileType(int i, String str) {
        HashMap<String, Integer> map = sMimeTypeToFormatMap;
        if (!map.containsKey(str)) {
            map.put(str, Integer.valueOf(i));
        }
        HashMap<Integer, String> map2 = sFormatToMimeTypeMap;
        if (map2.containsKey(Integer.valueOf(i))) {
            return;
        }
        map2.put(Integer.valueOf(i), str);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static boolean isDocumentMimeType(String str) {
        if (str == null) {
            return false;
        }
        String strNormalizeMimeType = normalizeMimeType(str);
        if (strNormalizeMimeType.startsWith("text/")) {
            return true;
        }
        String lowerCase = strNormalizeMimeType.toLowerCase(Locale.ROOT);
        lowerCase.hashCode();
        byte b = -1;
        switch (lowerCase.hashCode()) {
            case -2135180893:
                if (lowerCase.equals("application/vnd.stardivision.calc")) {
                    b = 0;
                }
                break;
            case -2135135086:
                if (lowerCase.equals("application/vnd.stardivision.draw")) {
                    b = 1;
                }
                break;
            case -2134883067:
                if (lowerCase.equals("application/vnd.stardivision.mail")) {
                    b = 2;
                }
                break;
            case -2134882730:
                if (lowerCase.equals("application/vnd.stardivision.math")) {
                    b = 3;
                }
                break;
            case -2008589971:
                if (lowerCase.equals("application/epub+zip")) {
                    b = 4;
                }
                break;
            case -1782746503:
                if (lowerCase.equals("application/vnd.oasis.opendocument.chart")) {
                    b = 5;
                }
                break;
            case -1765899696:
                if (lowerCase.equals("application/vnd.stardivision.chart")) {
                    b = 6;
                }
                break;
            case -1747277413:
                if (lowerCase.equals("application/vnd.sun.xml.writer.template")) {
                    b = 7;
                }
                break;
            case -1719571662:
                if (lowerCase.equals("application/vnd.oasis.opendocument.text")) {
                    b = 8;
                }
                break;
            case -1653115602:
                if (lowerCase.equals("application/vnd.ms-excel.sheet.macroenabled.12")) {
                    b = 9;
                }
                break;
            case -1628346451:
                if (lowerCase.equals("application/vnd.sun.xml.writer")) {
                    b = 10;
                }
                break;
            case -1590813831:
                if (lowerCase.equals("application/vnd.sun.xml.calc.template")) {
                    b = 11;
                }
                break;
            case -1506009513:
                if (lowerCase.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.template")) {
                    b = 12;
                }
                break;
            case -1316922187:
                if (lowerCase.equals("application/vnd.oasis.opendocument.text-template")) {
                    b = IGnssVisibilityControlCallback.NfwRequestor.OTHER_CHIPSET_VENDOR;
                }
                break;
            case -1293459259:
                if (lowerCase.equals("application/vnd.ms-word.document.macroenabled.12")) {
                    b = BluetoothHidDevice.ERROR_RSP_UNKNOWN;
                }
                break;
            case -1248334925:
                if (lowerCase.equals("application/pdf")) {
                    b = MidiConstants.STATUS_CHANNEL_MASK;
                }
                break;
            case -1248332507:
                if (lowerCase.equals("application/rtf")) {
                    b = 16;
                }
                break;
            case -1095881539:
                if (lowerCase.equals("application/vnd.ms-powerpoint.slideshow.macroenabled.12")) {
                    b = 17;
                }
                break;
            case -1073633483:
                if (lowerCase.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")) {
                    b = 18;
                }
                break;
            case -1071817359:
                if (lowerCase.equals("application/vnd.ms-powerpoint")) {
                    b = 19;
                }
                break;
            case -1050893613:
                if (lowerCase.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
                    b = IGnssVisibilityControlCallback.NfwRequestor.AUTOMOBILE_CLIENT;
                }
                break;
            case -1033484950:
                if (lowerCase.equals("application/vnd.sun.xml.draw.template")) {
                    b = 21;
                }
                break;
            case -943935167:
                if (lowerCase.equals("application/vnd.oasis.opendocument.formula")) {
                    b = 22;
                }
                break;
            case -878977693:
                if (lowerCase.equals("application/vnd.ms-excel.template.macroenabled.12")) {
                    b = 23;
                }
                break;
            case -779959281:
                if (lowerCase.equals("application/vnd.sun.xml.calc")) {
                    b = 24;
                }
                break;
            case -779913474:
                if (lowerCase.equals("application/vnd.sun.xml.draw")) {
                    b = 25;
                }
                break;
            case -779661118:
                if (lowerCase.equals("application/vnd.sun.xml.math")) {
                    b = 26;
                }
                break;
            case -676675015:
                if (lowerCase.equals("application/vnd.oasis.opendocument.text-web")) {
                    b = GsmAlphabet.GSM_EXTENDED_ESCAPE;
                }
                break;
            case -481744190:
                if (lowerCase.equals("application/x-mspublisher")) {
                    b = 28;
                }
                break;
            case -479218428:
                if (lowerCase.equals("application/vnd.sun.xml.writer.global")) {
                    b = 29;
                }
                break;
            case -396757341:
                if (lowerCase.equals("application/vnd.sun.xml.impress.template")) {
                    b = 30;
                }
                break;
            case -366307023:
                if (lowerCase.equals("application/vnd.ms-excel")) {
                    b = 31;
                }
                break;
            case -300783143:
                if (lowerCase.equals("application/vnd.ms-excel.sheet.binary.macroenabled.12")) {
                    b = 32;
                }
                break;
            case -291851672:
                if (lowerCase.equals("application/vnd.oasis.opendocument.presentation-template")) {
                    b = 33;
                }
                break;
            case -151068779:
                if (lowerCase.equals("application/vnd.ms-powerpoint.addin.macroenabled.12")) {
                    b = 34;
                }
                break;
            case -109382304:
                if (lowerCase.equals("application/vnd.oasis.opendocument.spreadsheet-template")) {
                    b = 35;
                }
                break;
            case 65167651:
                if (lowerCase.equals("application/vnd.ms-powerpoint.template.macroenabled.12")) {
                    b = 36;
                }
                break;
            case 428819984:
                if (lowerCase.equals("application/vnd.oasis.opendocument.graphics")) {
                    b = 37;
                }
                break;
            case 571050671:
                if (lowerCase.equals("application/vnd.stardivision.writer-global")) {
                    b = 38;
                }
                break;
            case 669516689:
                if (lowerCase.equals("application/vnd.stardivision.impress")) {
                    b = 39;
                }
                break;
            case 694663701:
                if (lowerCase.equals("application/vnd.openxmlformats-officedocument.presentationml.template")) {
                    b = 40;
                }
                break;
            case 776322612:
                if (lowerCase.equals("application/vnd.stardivision.impress-packed")) {
                    b = 41;
                }
                break;
            case 904647503:
                if (lowerCase.equals("application/msword")) {
                    b = 42;
                }
                break;
            case 1060806194:
                if (lowerCase.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.template")) {
                    b = 43;
                }
                break;
            case 1377360791:
                if (lowerCase.equals("application/vnd.oasis.opendocument.graphics-template")) {
                    b = 44;
                }
                break;
            case 1383644708:
                if (lowerCase.equals("application/vnd.ms-word.template.macroenabled.12")) {
                    b = 45;
                }
                break;
            case 1383977381:
                if (lowerCase.equals("application/vnd.sun.xml.impress")) {
                    b = 46;
                }
                break;
            case 1436962847:
                if (lowerCase.equals("application/vnd.oasis.opendocument.presentation")) {
                    b = 47;
                }
                break;
            case 1461751133:
                if (lowerCase.equals("application/vnd.oasis.opendocument.text-master")) {
                    b = 48;
                }
                break;
            case 1577426419:
                if (lowerCase.equals("application/vnd.openxmlformats-officedocument.presentationml.slideshow")) {
                    b = 49;
                }
                break;
            case 1643664935:
                if (lowerCase.equals("application/vnd.oasis.opendocument.spreadsheet")) {
                    b = 50;
                }
                break;
            case 1673742401:
                if (lowerCase.equals("application/vnd.stardivision.writer")) {
                    b = 51;
                }
                break;
            case 1750539587:
                if (lowerCase.equals("application/vnd.ms-powerpoint.presentation.macroenabled.12")) {
                    b = 52;
                }
                break;
            case 1993842850:
                if (lowerCase.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                    b = 53;
                }
                break;
            case 2094058325:
                if (lowerCase.equals("application/vnd.ms-excel.addin.macroenabled.12")) {
                    b = 54;
                }
                break;
            case 2117577984:
                if (lowerCase.equals("application/vnd.oasis.opendocument.database")) {
                    b = 55;
                }
                break;
        }
        switch (b) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
                return true;
            default:
                return false;
        }
    }

    public static boolean isExifMimeType(String str) {
        return isImageMimeType(str);
    }

    public static boolean isAudioMimeType(String str) {
        return normalizeMimeType(str).startsWith("audio/");
    }

    public static boolean isVideoMimeType(String str) {
        return normalizeMimeType(str).startsWith("video/");
    }

    public static boolean isImageMimeType(String str) {
        return normalizeMimeType(str).startsWith(MessagingMessage.IMAGE_MIME_TYPE_PREFIX);
    }

    public static boolean isPlayListMimeType(String str) {
        String strNormalizeMimeType = normalizeMimeType(str);
        strNormalizeMimeType.hashCode();
        switch (strNormalizeMimeType) {
            case "audio/x-scpls":
            case "application/x-mpegurl":
            case "application/vnd.apple.mpegurl":
            case "audio/mpegurl":
            case "audio/x-mpegurl":
            case "application/vnd.ms-wpl":
                return true;
            default:
                return false;
        }
    }

    public static boolean isDrmMimeType(String str) {
        return normalizeMimeType(str).equals("application/x-android-drm-fl");
    }

    public static String getFileTitle(String str) {
        int i;
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf >= 0 && (i = iLastIndexOf + 1) < str.length()) {
            str = str.substring(i);
        }
        int iLastIndexOf2 = str.lastIndexOf(46);
        return iLastIndexOf2 > 0 ? str.substring(0, iLastIndexOf2) : str;
    }

    public static String getFileExtension(String str) {
        int iLastIndexOf;
        if (str != null && (iLastIndexOf = str.lastIndexOf(46)) >= 0) {
            return str.substring(iLastIndexOf + 1);
        }
        return null;
    }

    public static String getMimeType(String str, int i) {
        String mimeTypeForFile = getMimeTypeForFile(str);
        return !"application/octet-stream".equals(mimeTypeForFile) ? mimeTypeForFile : getMimeTypeForFormatCode(i);
    }

    public static String getMimeTypeForFile(String str) {
        String strGuessMimeTypeFromExtension = MimeMap.getDefault().guessMimeTypeFromExtension(getFileExtension(str));
        return strGuessMimeTypeFromExtension != null ? strGuessMimeTypeFromExtension : "application/octet-stream";
    }

    public static String getMimeTypeForFormatCode(int i) {
        String str = sFormatToMimeTypeMap.get(Integer.valueOf(i));
        return str != null ? str : "application/octet-stream";
    }

    public static int getFormatCode(String str, String str2) {
        int formatCodeForMimeType = getFormatCodeForMimeType(str2);
        return formatCodeForMimeType != 12288 ? formatCodeForMimeType : getFormatCodeForFile(str);
    }

    public static int getFormatCodeForFile(String str) {
        return getFormatCodeForMimeType(getMimeTypeForFile(str));
    }

    public static int getFormatCodeForMimeType(String str) {
        if (str == null) {
            return 12288;
        }
        HashMap<String, Integer> map = sMimeTypeToFormatMap;
        Integer num = map.get(str);
        if (num != null) {
            return num.intValue();
        }
        String strNormalizeMimeType = normalizeMimeType(str);
        Integer num2 = map.get(strNormalizeMimeType);
        if (num2 != null) {
            return num2.intValue();
        }
        return strNormalizeMimeType.startsWith("audio/") ? MtpConstants.FORMAT_UNDEFINED_AUDIO : strNormalizeMimeType.startsWith("video/") ? MtpConstants.FORMAT_UNDEFINED_VIDEO : strNormalizeMimeType.startsWith(MessagingMessage.IMAGE_MIME_TYPE_PREFIX) ? 14336 : 12288;
    }

    private static String normalizeMimeType(String str) {
        String strGuessMimeTypeFromExtension;
        MimeMap mimeMap = MimeMap.getDefault();
        String strGuessExtensionFromMimeType = mimeMap.guessExtensionFromMimeType(str);
        return (strGuessExtensionFromMimeType == null || (strGuessMimeTypeFromExtension = mimeMap.guessMimeTypeFromExtension(strGuessExtensionFromMimeType)) == null) ? str != null ? str : "application/octet-stream" : strGuessMimeTypeFromExtension;
    }
}
