package com.android.internal.util;

import android.bluetooth.BluetoothHidDevice;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.hardware.gnss.visibility_control.V1_0.IGnssVisibilityControlCallback;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.android.internal.R;
import com.android.internal.midi.MidiConstants;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.widget.MessagingMessage;
import com.google.android.mms.ContentType;
import java.util.Locale;
import java.util.Objects;
import libcore.content.type.MimeMap;

/* JADX INFO: loaded from: classes4.dex */
public class MimeIconUtils {
    private static final ArrayMap<String, ContentResolver.MimeTypeInfo> sCache = new ArrayMap<>();

    private static ContentResolver.MimeTypeInfo buildTypeInfo(String str, int i, int i2, int i3) {
        Resources system = Resources.getSystem();
        String strGuessExtensionFromMimeType = MimeMap.getDefault().guessExtensionFromMimeType(str);
        String string = (TextUtils.isEmpty(strGuessExtensionFromMimeType) || i3 == -1) ? system.getString(i2) : system.getString(i3, strGuessExtensionFromMimeType.toUpperCase(Locale.US));
        return new ContentResolver.MimeTypeInfo(Icon.createWithResource(system, i), string, string);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static ContentResolver.MimeTypeInfo buildTypeInfo(String str) {
        byte b;
        switch (str.hashCode()) {
            case -2135180893:
                b = !str.equals("application/vnd.stardivision.calc") ? (byte) -1 : (byte) 83;
                break;
            case -2135135086:
                b = !str.equals("application/vnd.stardivision.draw") ? (byte) -1 : (byte) 70;
                break;
            case -2035614749:
                b = !str.equals("application/vnd.google-apps.spreadsheet") ? (byte) -1 : (byte) 87;
                break;
            case -1988437312:
                b = !str.equals("application/x-x509-ca-cert") ? (byte) -1 : (byte) 8;
                break;
            case -1917350260:
                b = !str.equals("text/x-literate-haskell") ? (byte) -1 : (byte) 28;
                break;
            case -1883861089:
                b = !str.equals("application/rss+xml") ? (byte) -1 : BluetoothHidDevice.ERROR_RSP_UNKNOWN;
                break;
            case -1808693885:
                b = !str.equals("text/x-pascal") ? (byte) -1 : (byte) 29;
                break;
            case -1777056778:
                b = !str.equals("application/vnd.oasis.opendocument.image") ? (byte) -1 : (byte) 69;
                break;
            case -1747277413:
                b = !str.equals("application/vnd.sun.xml.writer.template") ? (byte) -1 : (byte) 96;
                break;
            case -1719571662:
                b = !str.equals("application/vnd.oasis.opendocument.text") ? (byte) -1 : (byte) 88;
                break;
            case -1628346451:
                b = !str.equals("application/vnd.sun.xml.writer") ? (byte) -1 : (byte) 94;
                break;
            case -1590813831:
                b = !str.equals("application/vnd.sun.xml.calc.template") ? (byte) -1 : (byte) 85;
                break;
            case -1506009513:
                b = !str.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.template") ? (byte) -1 : (byte) 107;
                break;
            case -1386165903:
                b = !str.equals("application/x-kpresenter") ? (byte) -1 : (byte) 78;
                break;
            case -1348236371:
                b = !str.equals("application/x-deb") ? (byte) -1 : (byte) 57;
                break;
            case -1348228591:
                b = !str.equals("application/x-lha") ? (byte) -1 : (byte) 48;
                break;
            case -1348228026:
                b = !str.equals("application/x-lzh") ? (byte) -1 : (byte) 49;
                break;
            case -1348228010:
                b = !str.equals("application/x-lzx") ? (byte) -1 : (byte) 50;
                break;
            case -1348221103:
                b = !str.equals("application/x-tar") ? (byte) -1 : (byte) 52;
                break;
            case -1326989846:
                b = !str.equals("application/x-shockwave-flash") ? (byte) -1 : (byte) 101;
                break;
            case -1316922187:
                b = !str.equals("application/vnd.oasis.opendocument.text-template") ? (byte) -1 : (byte) 90;
                break;
            case -1296467268:
                b = !str.equals("application/atom+xml") ? (byte) -1 : (byte) 34;
                break;
            case -1294595255:
                b = !str.equals("inode/directory") ? (byte) -1 : (byte) 0;
                break;
            case -1248334925:
                b = !str.equals("application/pdf") ? (byte) -1 : (byte) 74;
                break;
            case -1248333084:
                b = !str.equals("application/rar") ? (byte) -1 : (byte) 42;
                break;
            case -1248326952:
                b = !str.equals("application/xml") ? (byte) -1 : (byte) 38;
                break;
            case -1248325150:
                b = !str.equals("application/zip") ? (byte) -1 : (byte) 43;
                break;
            case -1190438973:
                b = !str.equals("application/x-pkcs7-signature") ? (byte) -1 : (byte) 12;
                break;
            case -1143717099:
                b = !str.equals("application/x-pkcs7-certreqresp") ? (byte) -1 : (byte) 6;
                break;
            case -1082243251:
                b = !str.equals("text/html") ? (byte) -1 : (byte) 18;
                break;
            case -1073633483:
                b = !str.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation") ? (byte) -1 : (byte) 109;
                break;
            case -1071817359:
                b = !str.equals("application/vnd.ms-powerpoint") ? (byte) -1 : (byte) 108;
                break;
            case -1050893613:
                b = !str.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") ? (byte) -1 : (byte) 103;
                break;
            case -1033484950:
                b = !str.equals("application/vnd.sun.xml.draw.template") ? (byte) -1 : (byte) 72;
                break;
            case -1004747231:
                b = !str.equals("text/css") ? (byte) -1 : (byte) 17;
                break;
            case -1004727243:
                b = !str.equals("text/xml") ? (byte) -1 : (byte) 19;
                break;
            case -958424608:
                b = !str.equals("text/calendar") ? (byte) -1 : (byte) 61;
                break;
            case -951557661:
                b = !str.equals("application/vnd.google-apps.presentation") ? (byte) -1 : (byte) 80;
                break;
            case -779959281:
                b = !str.equals("application/vnd.sun.xml.calc") ? (byte) -1 : (byte) 84;
                break;
            case -779913474:
                b = !str.equals("application/vnd.sun.xml.draw") ? (byte) -1 : (byte) 71;
                break;
            case -723118015:
                b = !str.equals("application/x-javascript") ? (byte) -1 : (byte) 40;
                break;
            case -676675015:
                b = !str.equals("application/vnd.oasis.opendocument.text-web") ? (byte) -1 : (byte) 91;
                break;
            case -479218428:
                b = !str.equals("application/vnd.sun.xml.writer.global") ? (byte) -1 : (byte) 95;
                break;
            case -427343476:
                b = !str.equals("application/x-webarchive-xml") ? (byte) -1 : (byte) 54;
                break;
            case -396757341:
                b = !str.equals("application/vnd.sun.xml.impress.template") ? (byte) -1 : (byte) 77;
                break;
            case -366307023:
                b = !str.equals("application/vnd.ms-excel") ? (byte) -1 : (byte) 105;
                break;
            case -261480694:
                b = !str.equals("text/x-chdr") ? (byte) -1 : (byte) 22;
                break;
            case -261469704:
                b = !str.equals("text/x-csrc") ? (byte) -1 : (byte) 23;
                break;
            case -261439913:
                b = !str.equals("text/x-dsrc") ? (byte) -1 : (byte) 24;
                break;
            case -261278343:
                b = !str.equals("text/x-java") ? (byte) -1 : GsmAlphabet.GSM_EXTENDED_ESCAPE;
                break;
            case -228136375:
                b = !str.equals("application/x-pkcs7-mime") ? (byte) -1 : (byte) 11;
                break;
            case -221944004:
                b = !str.equals("application/x-font-ttf") ? (byte) -1 : (byte) 66;
                break;
            case -109382304:
                b = !str.equals("application/vnd.oasis.opendocument.spreadsheet-template") ? (byte) -1 : (byte) 82;
                break;
            case -43923783:
                b = !str.equals("application/gzip") ? (byte) -1 : (byte) 55;
                break;
            case -43840953:
                b = !str.equals("application/json") ? (byte) -1 : (byte) 36;
                break;
            case 26919318:
                b = !str.equals("application/x-iso9660-image") ? (byte) -1 : (byte) 47;
                break;
            case 81142075:
                b = !str.equals("application/vnd.android.package-archive") ? (byte) -1 : (byte) 2;
                break;
            case 163679941:
                b = !str.equals("application/pgp-signature") ? (byte) -1 : (byte) 4;
                break;
            case 180207563:
                b = !str.equals("application/x-stuffit") ? (byte) -1 : (byte) 51;
                break;
            case 245790645:
                b = !str.equals("application/vnd.google-apps.drawing") ? (byte) -1 : (byte) 73;
                break;
            case 262346941:
                b = !str.equals("text/x-vcalendar") ? (byte) -1 : (byte) 62;
                break;
            case 302189274:
                b = !str.equals(DocumentsContract.Document.MIME_TYPE_DIR) ? (byte) -1 : (byte) 1;
                break;
            case 302663708:
                b = !str.equals("application/ecmascript") ? (byte) -1 : (byte) 35;
                break;
            case 363965503:
                b = !str.equals("application/x-rar-compressed") ? (byte) -1 : (byte) 58;
                break;
            case 394650567:
                b = !str.equals("application/pgp-keys") ? (byte) -1 : (byte) 3;
                break;
            case 428819984:
                b = !str.equals("application/vnd.oasis.opendocument.graphics") ? (byte) -1 : (byte) 67;
                break;
            case 501428239:
                b = !str.equals(ContactsContract.Contacts.CONTENT_VCARD_TYPE) ? (byte) -1 : (byte) 59;
                break;
            case 571050671:
                b = !str.equals("application/vnd.stardivision.writer-global") ? (byte) -1 : (byte) 93;
                break;
            case 603849904:
                b = !str.equals(ContentType.APP_XHTML) ? (byte) -1 : (byte) 16;
                break;
            case 641141505:
                b = !str.equals("application/x-texinfo") ? (byte) -1 : (byte) 33;
                break;
            case 669516689:
                b = !str.equals("application/vnd.stardivision.impress") ? (byte) -1 : (byte) 75;
                break;
            case 694663701:
                b = !str.equals("application/vnd.openxmlformats-officedocument.presentationml.template") ? (byte) -1 : (byte) 110;
                break;
            case 717553764:
                b = !str.equals("application/vnd.google-apps.document") ? (byte) -1 : (byte) 99;
                break;
            case 822609188:
                b = !str.equals("text/vcard") ? (byte) -1 : (byte) 60;
                break;
            case 822849473:
                b = !str.equals("text/x-csh") ? (byte) -1 : (byte) 25;
                break;
            case 822865318:
                b = !str.equals("text/x-tcl") ? (byte) -1 : (byte) 30;
                break;
            case 822865392:
                b = !str.equals("text/x-tex") ? (byte) -1 : (byte) 31;
                break;
            case 859118878:
                b = !str.equals("application/x-abiword") ? (byte) -1 : (byte) 97;
                break;
            case 904647503:
                b = !str.equals("application/msword") ? (byte) -1 : (byte) 102;
                break;
            case 1043583697:
                b = !str.equals("application/x-pkcs7-certificates") ? (byte) -1 : (byte) 10;
                break;
            case 1060806194:
                b = !str.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.template") ? (byte) -1 : (byte) 104;
                break;
            case 1154415139:
                b = !str.equals("application/x-font") ? (byte) -1 : (byte) 63;
                break;
            case 1154449330:
                b = !str.equals("application/x-gtar") ? (byte) -1 : (byte) 46;
                break;
            case 1239557416:
                b = !str.equals("application/x-pkcs7-crl") ? (byte) -1 : (byte) 7;
                break;
            case 1255211837:
                b = !str.equals("text/x-haskell") ? (byte) -1 : (byte) 26;
                break;
            case 1283455191:
                b = !str.equals("application/x-apple-diskimage") ? (byte) -1 : (byte) 44;
                break;
            case 1305955842:
                b = !str.equals("application/x-debian-package") ? (byte) -1 : (byte) 45;
                break;
            case 1377360791:
                b = !str.equals("application/vnd.oasis.opendocument.graphics-template") ? (byte) -1 : (byte) 68;
                break;
            case 1383977381:
                b = !str.equals("application/vnd.sun.xml.impress") ? (byte) -1 : (byte) 76;
                break;
            case 1431987873:
                b = !str.equals("application/x-kword") ? (byte) -1 : (byte) 98;
                break;
            case 1432260414:
                b = !str.equals("application/x-latex") ? (byte) -1 : (byte) 32;
                break;
            case 1436962847:
                b = !str.equals("application/vnd.oasis.opendocument.presentation") ? (byte) -1 : (byte) 79;
                break;
            case 1440428940:
                b = !str.equals("application/javascript") ? (byte) -1 : (byte) 37;
                break;
            case 1454024983:
                b = !str.equals("application/x-7z-compressed") ? (byte) -1 : (byte) 56;
                break;
            case 1461751133:
                b = !str.equals("application/vnd.oasis.opendocument.text-master") ? (byte) -1 : (byte) 89;
                break;
            case 1502452311:
                b = !str.equals("application/font-woff") ? (byte) -1 : BluetoothHidDevice.SUBCLASS1_KEYBOARD;
                break;
            case 1536912403:
                b = !str.equals("application/x-object") ? (byte) -1 : MidiConstants.STATUS_CHANNEL_MASK;
                break;
            case 1573656544:
                b = !str.equals("application/x-pkcs12") ? (byte) -1 : (byte) 5;
                break;
            case 1577426419:
                b = !str.equals("application/vnd.openxmlformats-officedocument.presentationml.slideshow") ? (byte) -1 : (byte) 111;
                break;
            case 1637222218:
                b = !str.equals("application/x-kspread") ? (byte) -1 : (byte) 86;
                break;
            case 1643664935:
                b = !str.equals("application/vnd.oasis.opendocument.spreadsheet") ? (byte) -1 : (byte) 81;
                break;
            case 1673742401:
                b = !str.equals("application/vnd.stardivision.writer") ? (byte) -1 : (byte) 92;
                break;
            case 1709755138:
                b = !str.equals("application/x-font-woff") ? (byte) -1 : (byte) 65;
                break;
            case 1851895234:
                b = !str.equals("application/x-webarchive") ? (byte) -1 : (byte) 53;
                break;
            case 1868769095:
                b = !str.equals("application/x-quicktimeplayer") ? (byte) -1 : (byte) 100;
                break;
            case 1948418893:
                b = !str.equals("application/mac-binhex40") ? (byte) -1 : (byte) 41;
                break;
            case 1969663169:
                b = !str.equals("application/rdf+xml") ? (byte) -1 : IGnssVisibilityControlCallback.NfwRequestor.OTHER_CHIPSET_VENDOR;
                break;
            case 1993842850:
                b = !str.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ? (byte) -1 : (byte) 106;
                break;
            case 2041423923:
                b = !str.equals("application/x-x509-user-cert") ? (byte) -1 : (byte) 9;
                break;
            case 2062084266:
                b = !str.equals("text/x-c++hdr") ? (byte) -1 : IGnssVisibilityControlCallback.NfwRequestor.AUTOMOBILE_CLIENT;
                break;
            case 2062095256:
                b = !str.equals("text/x-c++src") ? (byte) -1 : (byte) 21;
                break;
            case 2132236175:
                b = !str.equals("text/javascript") ? (byte) -1 : (byte) 39;
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
            case 1:
                return buildTypeInfo(str, R.drawable.ic_doc_folder, R.string.mime_type_folder, -1);
            case 2:
                return buildTypeInfo(str, R.drawable.ic_doc_apk, R.string.mime_type_apk, -1);
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
                return buildTypeInfo(str, R.drawable.ic_doc_certificate, R.string.mime_type_generic, R.string.mime_type_generic_ext);
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
                return buildTypeInfo(str, R.drawable.ic_doc_codes, R.string.mime_type_document, R.string.mime_type_document_ext);
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
            case 56:
            case 57:
            case 58:
                return buildTypeInfo(str, R.drawable.ic_doc_compressed, R.string.mime_type_compressed, R.string.mime_type_compressed_ext);
            case 59:
            case 60:
                return buildTypeInfo(str, R.drawable.ic_doc_contact, R.string.mime_type_generic, R.string.mime_type_generic_ext);
            case 61:
            case 62:
                return buildTypeInfo(str, R.drawable.ic_doc_event, R.string.mime_type_generic, R.string.mime_type_generic_ext);
            case 63:
            case 64:
            case 65:
            case 66:
                return buildTypeInfo(str, R.drawable.ic_doc_font, R.string.mime_type_generic, R.string.mime_type_generic_ext);
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
                return buildTypeInfo(str, R.drawable.ic_doc_image, R.string.mime_type_image, R.string.mime_type_image_ext);
            case 74:
                return buildTypeInfo(str, R.drawable.ic_doc_pdf, R.string.mime_type_document, R.string.mime_type_document_ext);
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
                return buildTypeInfo(str, R.drawable.ic_doc_presentation, R.string.mime_type_presentation, R.string.mime_type_presentation_ext);
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
                return buildTypeInfo(str, R.drawable.ic_doc_spreadsheet, R.string.mime_type_spreadsheet, R.string.mime_type_spreadsheet_ext);
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
                return buildTypeInfo(str, R.drawable.ic_doc_document, R.string.mime_type_document, R.string.mime_type_document_ext);
            case 100:
            case 101:
                return buildTypeInfo(str, R.drawable.ic_doc_video, R.string.mime_type_video, R.string.mime_type_video_ext);
            case 102:
            case 103:
            case 104:
                return buildTypeInfo(str, R.drawable.ic_doc_word, R.string.mime_type_document, R.string.mime_type_document_ext);
            case 105:
            case 106:
            case 107:
                return buildTypeInfo(str, R.drawable.ic_doc_excel, R.string.mime_type_spreadsheet, R.string.mime_type_spreadsheet_ext);
            case 108:
            case 109:
            case 110:
            case 111:
                return buildTypeInfo(str, R.drawable.ic_doc_powerpoint, R.string.mime_type_presentation, R.string.mime_type_presentation_ext);
            default:
                return buildGenericTypeInfo(str);
        }
    }

    private static ContentResolver.MimeTypeInfo buildGenericTypeInfo(String str) {
        if (str.startsWith("audio/")) {
            return buildTypeInfo(str, R.drawable.ic_doc_audio, R.string.mime_type_audio, R.string.mime_type_audio_ext);
        }
        if (str.startsWith("video/")) {
            return buildTypeInfo(str, R.drawable.ic_doc_video, R.string.mime_type_video, R.string.mime_type_video_ext);
        }
        if (str.startsWith(MessagingMessage.IMAGE_MIME_TYPE_PREFIX)) {
            return buildTypeInfo(str, R.drawable.ic_doc_image, R.string.mime_type_image, R.string.mime_type_image_ext);
        }
        if (str.startsWith("text/")) {
            return buildTypeInfo(str, R.drawable.ic_doc_text, R.string.mime_type_document, R.string.mime_type_document_ext);
        }
        MimeMap mimeMap = MimeMap.getDefault();
        String strGuessMimeTypeFromExtension = mimeMap.guessMimeTypeFromExtension(mimeMap.guessExtensionFromMimeType(str));
        if (strGuessMimeTypeFromExtension != null && !Objects.equals(str, strGuessMimeTypeFromExtension)) {
            return buildTypeInfo(strGuessMimeTypeFromExtension);
        }
        return buildTypeInfo(str, R.drawable.ic_doc_generic, R.string.mime_type_generic, R.string.mime_type_generic_ext);
    }

    public static ContentResolver.MimeTypeInfo getTypeInfo(String str) {
        ContentResolver.MimeTypeInfo mimeTypeInfoBuildTypeInfo;
        String lowerCase = str.toLowerCase(Locale.US);
        synchronized (sCache) {
            mimeTypeInfoBuildTypeInfo = sCache.get(lowerCase);
            if (mimeTypeInfoBuildTypeInfo == null) {
                mimeTypeInfoBuildTypeInfo = buildTypeInfo(lowerCase);
                sCache.put(lowerCase, mimeTypeInfoBuildTypeInfo);
            }
        }
        return mimeTypeInfoBuildTypeInfo;
    }
}
