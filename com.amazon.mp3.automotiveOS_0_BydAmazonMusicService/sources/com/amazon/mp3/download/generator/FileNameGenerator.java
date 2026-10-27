package com.amazon.mp3.download.generator;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.io.File;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class FileNameGenerator {
    public static final String DIR_SEPARATOR = File.separator;
    private static final String TAG = "FileNameGenerator";

    public static String generatePath(Context context, String album, String artist, String albumArtist, String asin, String title, String discNum, String trackNum, String extension, boolean isDownloadablePrimeContent, boolean isPrimePlaylistTrack, @NonNull final StorageLocationFileManager storageLocationFileManager) {
        return generatePath(context, album, artist, albumArtist, asin, title, discNum, trackNum, extension, isDownloadablePrimeContent, isPrimePlaylistTrack, generateRootPathDir(storageLocationFileManager));
    }

    public static String generateDirectoryPath(@NonNull final StorageLocationFileManager storageLocationFileManager) {
        File fileGenerateRootPathDir = generateRootPathDir(storageLocationFileManager);
        return fileGenerateRootPathDir == null ? "" : fileGenerateRootPathDir.getAbsolutePath();
    }

    public static String generatePathForStorageLocation(Context context, String album, String artist, String albumArtist, String asin, String title, String discNum, String trackNum, String extension, boolean isDownloadablePrimeContent, boolean isPrimePlaylistTrack, @NonNull final StorageLocationFileManager storageLocationFileManager, @NonNull final StorageLocation storageLocation) {
        return generatePath(context, album, artist, albumArtist, asin, title, discNum, trackNum, extension, isDownloadablePrimeContent, isPrimePlaylistTrack, generateRootPathDir(storageLocationFileManager, storageLocation));
    }

    public static String generateHarleyPath(final String trackNum, final String asin, final String title, final String luid, final String extension, final boolean isOwned, @NonNull final StorageLocationFileManager storageLocationFileManager) {
        File fileGenerateRootPathDir = generateRootPathDir(storageLocationFileManager);
        if (!isOwned) {
            if (asin == null) {
                return null;
            }
            return fileGenerateRootPathDir + DIR_SEPARATOR + String.format("asin://%s", asin);
        }
        if (trackNum == null || title == null || luid == null || extension == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(fileGenerateRootPathDir);
        String str = DIR_SEPARATOR;
        sb.append(str);
        sb.append("Purchased");
        sb.append(str);
        sb.append(String.format("%02d", Integer.valueOf(trackNum)));
        sb.append(" - ");
        sb.append(title);
        sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
        sb.append(luid);
        sb.append(".");
        sb.append(extension);
        return sb.toString();
    }

    @NonNull
    private static String generatePath(final Context context, final String album, final String artist, final String albumArtist, final String asin, final String title, final String discNum, final String trackNum, final String extension, final boolean isDownloadablePrimeContent, final boolean isPrimePlaylistTrack, final File rootFile) {
        if (isDownloadablePrimeContent) {
            FileUtil.createEmptyFile(rootFile, ".nomedia");
        }
        return rootFile + DIR_SEPARATOR + buildFilePathAndName(context, isDownloadablePrimeContent, isPrimePlaylistTrack, artist, albumArtist, album, asin, discNum, trackNum, title, extension);
    }

    private static File generateRootPathDir(@NonNull final StorageLocationFileManager storageLocationFileManager) {
        return storageLocationFileManager.getPreferredStorageDirectory(StorageLocationFileManager.Visibility.PRIVATE);
    }

    private static File generateRootPathDir(final StorageLocationFileManager storageLocationFileManager, final StorageLocation storageLocation) {
        if (storageLocationFileManager == null) {
            return null;
        }
        StorageLocationFileManager.Visibility visibility = StorageLocationFileManager.Visibility.PRIVATE;
        try {
            return storageLocationFileManager.getStorageDirectory(storageLocation, visibility);
        } catch (Exception e) {
            Log.warning(TAG, "Unable to get a storage directory for " + storageLocation + ", " + visibility + ": " + e.getMessage());
            return storageLocationFileManager.getPreferredStorageDirectory(visibility);
        }
    }

    private static String buildFilePathAndName(Context context, boolean isPrimeDownloadableContent, boolean isPrimePlaylistTrack, String artist, String albumArtist, String album, String asin, String discNum, String trackNum, String title, String extension) {
        String pathSafeString = getPathSafeString(artist);
        String pathSafeString2 = getPathSafeString(albumArtist);
        String pathSafeString3 = getPathSafeString(album);
        String pathSafeString4 = getPathSafeString(asin);
        String pathSafeString5 = getPathSafeString(discNum);
        String pathSafeString6 = getPathSafeString(trackNum);
        String pathSafeString7 = getPathSafeString(title);
        String pathSafeString8 = getPathSafeString(extension);
        StringBuilder sb = new StringBuilder();
        if (isPrimePlaylistTrack) {
            sb.append("PrimePlaylist");
            sb.append(DIR_SEPARATOR);
        }
        sb.append(formatArtist(pathSafeString, pathSafeString2));
        String str = DIR_SEPARATOR;
        sb.append(str);
        sb.append(formatAlbum(pathSafeString3));
        sb.append(str);
        if (isPrimeDownloadableContent) {
            sb.append(AccountDetailUtil.get(context).getHomeMarketPlaceId());
            sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
            sb.append(pathSafeString4);
            sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
        } else {
            if (pathSafeString4 != null) {
                sb.append(pathSafeString4);
                sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
            }
            if (pathSafeString5 != null) {
                sb.append(formatDiscNum(pathSafeString5));
                sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
            }
            if (pathSafeString6 != null) {
                sb.append(formatTrackNum(pathSafeString6));
                sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
            }
            sb.append("-_");
            sb.append(formatTitle(pathSafeString7));
        }
        if (isPrimeDownloadableContent) {
            pathSafeString8 = "m4a";
        } else if (TextUtils.isEmpty(pathSafeString8)) {
            pathSafeString8 = HlsSegmentFormat.MP3;
        }
        sb.append('.');
        sb.append(pathSafeString8);
        String strTrimPath = trimPath(sb.toString());
        if (strTrimPath.startsWith(str)) {
            strTrimPath = strTrimPath.replaceFirst(str, "");
        }
        Log.debug(TAG, "downloading file to %s", strTrimPath);
        return strTrimPath;
    }

    private static String formatAlbum(String album) {
        return sanitizeMetadata(album, "Album");
    }

    private static String formatArtist(String artist, String albumArtist) {
        if (!TextUtils.isEmpty(albumArtist)) {
            artist = albumArtist;
        }
        return sanitizeMetadata(artist, "Artist");
    }

    private static String formatTitle(String title) {
        return sanitizeMetadata(title, "Title");
    }

    private static String formatDiscNum(String discNum) {
        if (discNum == null) {
            return discNum;
        }
        try {
            return String.format(Locale.US, "(disc_%d)", Integer.valueOf(Integer.parseInt(discNum)));
        } catch (NumberFormatException e) {
            Log.debug(TAG, "Could not parse int", e);
            return discNum;
        }
    }

    private static String formatTrackNum(String trackNum) {
        if (trackNum == null) {
            return trackNum;
        }
        try {
            return String.format(Locale.US, "%1$02d", Integer.valueOf(Integer.parseInt(trackNum)));
        } catch (NumberFormatException e) {
            Log.debug(TAG, "Could not parse int", e);
            return trackNum;
        }
    }

    private static String sanitizeMetadata(String value, String metadataKey) {
        if (!TextUtils.isEmpty(value)) {
            return value;
        }
        return "(Unknown " + metadataKey + ")";
    }

    private static String getPathSafeString(String input) {
        if (TextUtils.isEmpty(input)) {
            return null;
        }
        return FileUtil.replaceInvalidPathChars(input);
    }

    public static boolean isPreWidevineDRMFileName(final String fileName) {
        return fileName.endsWith("m4a") && fileName.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX).length >= 2;
    }

    private static String trimPath(String path) {
        String strTrimFilename;
        if (path.endsWith("/")) {
            strTrimFilename = "";
        } else {
            String strSubstring = path.substring(0, path.lastIndexOf(47) + 1);
            strTrimFilename = path.substring(path.lastIndexOf(47) + 1, path.length());
            path = strSubstring;
        }
        String strTrimDirectory = trimDirectory(path);
        if (strTrimFilename != null) {
            strTrimFilename = trimFilename(strTrimFilename);
        }
        return strTrimDirectory + strTrimFilename;
    }

    @VisibleForTesting
    static String trimDirectory(String directory) {
        int length = directory.length() - 100;
        if (length <= 0) {
            return directory;
        }
        String[] strArrSplit = directory.split("/");
        StringBuilder sb = new StringBuilder(100);
        int length2 = strArrSplit.length;
        do {
            length2--;
            if (length2 < 0) {
                break;
            }
            String str = strArrSplit[length2];
            int length3 = str.length() - 1;
            if (length <= length3) {
                length3 = length;
            }
            sb.insert(0, str.substring(0, str.length() - length3));
            sb.insert(0, JsonPointer.SEPARATOR);
            length -= length3;
        } while (length > 0);
        for (int i = length2 - 1; i >= 0; i--) {
            sb.insert(0, strArrSplit[i]);
            sb.insert(0, JsonPointer.SEPARATOR);
        }
        sb.append(JsonPointer.SEPARATOR);
        return sb.toString();
    }

    private static String trimFilename(String filename) {
        String strSubstring;
        int length = (filename.length() - 64) - 5;
        if (length <= 0) {
            return filename;
        }
        int iLastIndexOf = filename.lastIndexOf(46);
        if (iLastIndexOf >= 0) {
            strSubstring = filename.substring(iLastIndexOf);
            filename = filename.substring(0, iLastIndexOf);
        } else {
            strSubstring = "";
        }
        return filename.substring(0, filename.length() - length) + strSubstring;
    }
}
