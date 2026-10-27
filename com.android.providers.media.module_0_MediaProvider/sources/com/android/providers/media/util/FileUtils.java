package com.android.providers.media.util;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class FileUtils {
    public static final long DEFAULT_DURATION_PENDING = 604800000;
    public static final long DEFAULT_DURATION_TRASHED = 2592000000L;
    public static final String PREFIX_PENDING = "pending";
    public static final String PREFIX_TRASHED = "trashed";
    private static final Pattern PATTERN_DCF_STRICT = Pattern.compile("([A-Z0-9_]{4})([0-9]{4})");
    private static final Pattern PATTERN_DCF_RELAXED = Pattern.compile("((?:IMG|MVIMG|VID)_[0-9]{8}_[0-9]{6})(?:~([0-9]+))?");
    public static final Pattern PATTERN_DOWNLOADS_FILE = Pattern.compile("(?i)^/storage/[^/]+/(?:[0-9]+/)?(?:Android/sandbox/[^/]+/)?Download/.+");
    public static final Pattern PATTERN_DOWNLOADS_DIRECTORY = Pattern.compile("(?i)^/storage/[^/]+/(?:[0-9]+/)?(?:Android/sandbox/[^/]+/)?Download/?");
    public static final Pattern PATTERN_EXPIRES_FILE = Pattern.compile("(?i)^\\.(pending|trashed)-(\\d+)-([^/]+)$");
    public static final Pattern PATTERN_PENDING_FILEPATH_FOR_SQL = Pattern.compile(".*/\\.pending-(\\d+)-([^/]+)$");
    public static final Pattern PATTERN_OWNED_PATH = Pattern.compile("(?i)^/storage/[^/]+/(?:[0-9]+/)?Android/(?:data|media|obb|sandbox)/([^/]+)(/?.*)?");
    public static final Pattern PATTERN_DATA_OR_OBB_PATH = Pattern.compile("(?i)^/storage/[^/]+/(?:[0-9]+/)?Android/(?:data|obb)/?$");
    public static final String[] DEFAULT_FOLDER_NAMES = {Environment.DIRECTORY_MUSIC, Environment.DIRECTORY_PODCASTS, Environment.DIRECTORY_RINGTONES, Environment.DIRECTORY_ALARMS, Environment.DIRECTORY_NOTIFICATIONS, Environment.DIRECTORY_PICTURES, Environment.DIRECTORY_MOVIES, Environment.DIRECTORY_DOWNLOADS, Environment.DIRECTORY_DCIM, Environment.DIRECTORY_DOCUMENTS, Environment.DIRECTORY_AUDIOBOOKS};
    private static final Pattern PATTERN_RELATIVE_PATH = Pattern.compile("(?i)^/storage/(?:emulated/[0-9]+/|[^/]+/)(Android/sandbox/([^/]+)/)?");
    private static final Pattern PATTERN_VOLUME_NAME = Pattern.compile("(?i)^/storage/([^/]+)");
    private static final String CAMERA_RELATIVE_PATH = String.format("%s/%s/", Environment.DIRECTORY_DCIM, "Camera");

    private static boolean isValidFatFilenameChar(char c) {
        return ((c >= 0 && c <= 31) || c == '\"' || c == '*' || c == '/' || c == ':' || c == '<' || c == '\\' || c == '|' || c == 127 || c == '>' || c == '?') ? false : true;
    }

    public static ParcelFileDescriptor openSafely(File file, int i) throws FileNotFoundException {
        try {
            FileDescriptor fileDescriptorOpen = Os.open(file.getAbsolutePath(), translateModePfdToPosix(i) | OsConstants.O_CLOEXEC | OsConstants.O_NOFOLLOW, OsConstants.S_IRWXU | OsConstants.S_IRWXG);
            try {
                return ParcelFileDescriptor.dup(fileDescriptorOpen);
            } finally {
                closeQuietly(fileDescriptorOpen);
            }
        } catch (ErrnoException | IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    public static void closeQuietly(AutoCloseable autoCloseable) {
        android.os.FileUtils.closeQuietly(autoCloseable);
    }

    public static void closeQuietly(FileDescriptor fileDescriptor) {
        if (fileDescriptor == null) {
            return;
        }
        try {
            Os.close(fileDescriptor);
        } catch (ErrnoException unused) {
        }
    }

    public static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        return android.os.FileUtils.copy(inputStream, outputStream);
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

    public static boolean deleteOlderFiles(File file, int i, long j) {
        if (i < 0 || j < 0) {
            throw new IllegalArgumentException("Constraints must be positive or 0");
        }
        File[] fileArrListFiles = file.listFiles();
        boolean z = false;
        if (fileArrListFiles == null) {
            return false;
        }
        Arrays.sort(fileArrListFiles, new Comparator<File>() { // from class: com.android.providers.media.util.FileUtils.1
            @Override // java.util.Comparator
            public int compare(File file2, File file3) {
                return Long.compare(file3.lastModified(), file2.lastModified());
            }
        });
        while (i < fileArrListFiles.length) {
            File file2 = fileArrListFiles[i];
            if (System.currentTimeMillis() - file2.lastModified() > j && file2.delete()) {
                Log.d(Logging.TAG, "Deleted old file " + file2);
                z = true;
            }
            i++;
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int translateModeStringToPosix(java.lang.String r5) {
        /*
            r0 = 0
        L1:
            int r1 = r5.length()
            java.lang.String r2 = "Bad mode: "
            r3 = 116(0x74, float:1.63E-43)
            r4 = 97
            if (r0 >= r1) goto L36
            char r1 = r5.charAt(r0)
            if (r1 == r4) goto L33
            r4 = 114(0x72, float:1.6E-43)
            if (r1 == r4) goto L33
            if (r1 == r3) goto L33
            r3 = 119(0x77, float:1.67E-43)
            if (r1 != r3) goto L1e
            goto L33
        L1e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        L33:
            int r0 = r0 + 1
            goto L1
        L36:
            java.lang.String r0 = "rw"
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto L44
            int r0 = android.system.OsConstants.O_RDWR
            int r1 = android.system.OsConstants.O_CREAT
        L42:
            r0 = r0 | r1
            goto L5b
        L44:
            java.lang.String r0 = "w"
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto L51
            int r0 = android.system.OsConstants.O_WRONLY
            int r1 = android.system.OsConstants.O_CREAT
            goto L42
        L51:
            java.lang.String r0 = "r"
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto L6f
            int r0 = android.system.OsConstants.O_RDONLY
        L5b:
            int r1 = r5.indexOf(r3)
            r2 = -1
            if (r1 == r2) goto L65
            int r1 = android.system.OsConstants.O_TRUNC
            r0 = r0 | r1
        L65:
            int r5 = r5.indexOf(r4)
            if (r5 == r2) goto L6e
            int r5 = android.system.OsConstants.O_APPEND
            r0 = r0 | r5
        L6e:
            return r0
        L6f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.util.FileUtils.translateModeStringToPosix(java.lang.String):int");
    }

    public static String translateModePosixToString(int i) {
        String str;
        if ((OsConstants.O_ACCMODE & i) == OsConstants.O_RDWR) {
            str = "rw";
        } else if ((OsConstants.O_ACCMODE & i) == OsConstants.O_WRONLY) {
            str = "w";
        } else {
            if ((OsConstants.O_ACCMODE & i) != OsConstants.O_RDONLY) {
                throw new IllegalArgumentException("Bad mode: " + i);
            }
            str = "r";
        }
        int i2 = OsConstants.O_TRUNC;
        if ((i & i2) == i2) {
            str = str + "t";
        }
        int i3 = OsConstants.O_APPEND;
        if ((i & i3) != i3) {
            return str;
        }
        return str + "a";
    }

    public static int translateModePosixToPfd(int i) {
        int i2;
        if ((OsConstants.O_ACCMODE & i) == OsConstants.O_RDWR) {
            i2 = 805306368;
        } else if ((OsConstants.O_ACCMODE & i) == OsConstants.O_WRONLY) {
            i2 = 536870912;
        } else {
            if ((OsConstants.O_ACCMODE & i) != OsConstants.O_RDONLY) {
                throw new IllegalArgumentException("Bad mode: " + i);
            }
            i2 = 268435456;
        }
        int i3 = OsConstants.O_CREAT;
        if ((i & i3) == i3) {
            i2 |= 134217728;
        }
        int i4 = OsConstants.O_TRUNC;
        if ((i & i4) == i4) {
            i2 |= 67108864;
        }
        int i5 = OsConstants.O_APPEND;
        return (i & i5) == i5 ? i2 | 33554432 : i2;
    }

    public static int translateModePfdToPosix(int i) {
        int i2;
        if ((i & 805306368) == 805306368) {
            i2 = OsConstants.O_RDWR;
        } else if ((i & 536870912) == 536870912) {
            i2 = OsConstants.O_WRONLY;
        } else if ((i & 268435456) == 268435456) {
            i2 = OsConstants.O_RDONLY;
        } else {
            throw new IllegalArgumentException("Bad mode: " + i);
        }
        if ((i & 134217728) == 134217728) {
            i2 |= OsConstants.O_CREAT;
        }
        if ((i & 67108864) == 67108864) {
            i2 |= OsConstants.O_TRUNC;
        }
        return (i & 33554432) == 33554432 ? i2 | OsConstants.O_APPEND : i2;
    }

    public static int translateModeAccessToPosix(int i) {
        if (i == OsConstants.F_OK) {
            return OsConstants.O_RDONLY;
        }
        if (((OsConstants.R_OK | OsConstants.W_OK) & i) == (OsConstants.R_OK | OsConstants.W_OK)) {
            return OsConstants.O_RDWR;
        }
        int i2 = OsConstants.R_OK;
        if ((i & i2) == i2) {
            return OsConstants.O_RDONLY;
        }
        int i3 = OsConstants.W_OK;
        if ((i & i3) == i3) {
            return OsConstants.O_WRONLY;
        }
        throw new IllegalArgumentException("Bad mode: " + i);
    }

    public static boolean contains(File[] fileArr, File file) {
        for (File file2 : fileArr) {
            if (contains(file2, file)) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(Collection<File> collection, File file) {
        Iterator<File> it = collection.iterator();
        while (it.hasNext()) {
            if (contains(it.next(), file)) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(File file, File file2) {
        if (file == null || file2 == null) {
            return false;
        }
        return contains(file.getAbsolutePath(), file2.getAbsolutePath());
    }

    public static boolean contains(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        return str2.startsWith(str);
    }

    public static void writeString(File file, Optional<String> optional) throws IOException {
        if (optional.isPresent()) {
            Files.write(file.toPath(), optional.get().getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
        } else {
            file.delete();
        }
    }

    public static Optional<String> readString(File file) throws IOException {
        try {
            return Optional.of(new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8));
        } catch (NoSuchFileException unused) {
            return Optional.empty();
        }
    }

    public static void walkFileTreeContents(final Path path, final Consumer<Path> consumer) {
        try {
            Files.walkFileTree(path, new FileVisitor<Path>() { // from class: com.android.providers.media.util.FileUtils.2
                @Override // java.nio.file.FileVisitor
                public FileVisitResult preVisitDirectory(Path path2, BasicFileAttributes basicFileAttributes) {
                    return FileVisitResult.CONTINUE;
                }

                @Override // java.nio.file.FileVisitor
                public FileVisitResult visitFile(Path path2, BasicFileAttributes basicFileAttributes) {
                    if (!Objects.equals(path, path2)) {
                        consumer.accept(path2);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override // java.nio.file.FileVisitor
                public FileVisitResult visitFileFailed(Path path2, IOException iOException) {
                    Log.w(Logging.TAG, "Failed to visit " + path2, iOException);
                    return FileVisitResult.CONTINUE;
                }

                @Override // java.nio.file.FileVisitor
                public FileVisitResult postVisitDirectory(Path path2, IOException iOException) {
                    if (!Objects.equals(path, path2)) {
                        consumer.accept(path2);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            Log.w(Logging.TAG, "Failed to walk " + path, e);
        }
    }

    @Deprecated
    public static void deleteContents(File file) {
        walkFileTreeContents(file.toPath(), new Consumer() { // from class: com.android.providers.media.util.-$$Lambda$FileUtils$buCH4Sait9e-8fTExOmDin6zXfs
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Path) obj).toFile().delete();
            }
        });
    }

    public static boolean isValidFatFilename(String str) {
        return str != null && str.equals(buildValidFatFilename(str));
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

    public static String trimFilename(String str, int i) {
        StringBuilder sb = new StringBuilder(str);
        trimFilename(sb, i);
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

    private static File buildUniqueFileWithExtension(File file, String str, String str2) throws FileNotFoundException {
        Iterator<String> itBuildUniqueNameIterator = buildUniqueNameIterator(file, str);
        while (itBuildUniqueNameIterator.hasNext()) {
            File fileBuildFile = buildFile(file, itBuildUniqueNameIterator.next(), str2);
            if (!fileBuildFile.exists()) {
                return fileBuildFile;
            }
        }
        throw new FileNotFoundException("Failed to create unique file");
    }

    private static boolean isDcim(File file) {
        while (file != null) {
            if (Objects.equals("DCIM", file.getName())) {
                return true;
            }
            file = file.getParentFile();
        }
        return false;
    }

    private static Iterator<String> buildUniqueNameIterator(File file, final String str) {
        if (isDcim(file)) {
            final Matcher matcher = PATTERN_DCF_STRICT.matcher(str);
            if (matcher.matches()) {
                final String strGroup = matcher.group(1);
                return new Iterator<String>() { // from class: com.android.providers.media.util.FileUtils.3
                    int i;

                    {
                        this.i = Integer.parseInt(matcher.group(2));
                    }

                    @Override // java.util.Iterator
                    public String next() {
                        String str2 = String.format("%s%04d", strGroup, Integer.valueOf(this.i));
                        this.i++;
                        return str2;
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.i <= 9999;
                    }
                };
            }
            final Matcher matcher2 = PATTERN_DCF_RELAXED.matcher(str);
            if (matcher2.matches()) {
                final String strGroup2 = matcher2.group(1);
                return new Iterator<String>() { // from class: com.android.providers.media.util.FileUtils.4
                    int i;

                    {
                        this.i = TextUtils.isEmpty(matcher2.group(2)) ? 1 : Integer.parseInt(matcher2.group(2));
                    }

                    @Override // java.util.Iterator
                    public String next() {
                        int i = this.i;
                        String str2 = i == 1 ? strGroup2 : String.format("%s~%d", strGroup2, Integer.valueOf(i));
                        this.i++;
                        return str2;
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.i <= 99;
                    }
                };
            }
        }
        return new Iterator<String>() { // from class: com.android.providers.media.util.FileUtils.5
            int i = 0;

            @Override // java.util.Iterator
            public String next() {
                String str2;
                if (this.i == 0) {
                    str2 = str;
                } else {
                    str2 = str + " (" + this.i + ")";
                }
                this.i++;
                return str2;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i < 32;
            }
        };
    }

    public static File buildUniqueFile(File file, String str, String str2) throws FileNotFoundException {
        String[] strArrSplitFileName = splitFileName(str, str2);
        return buildUniqueFileWithExtension(file, strArrSplitFileName[0], strArrSplitFileName[1]);
    }

    public static File buildNonUniqueFile(File file, String str, String str2) {
        String[] strArrSplitFileName = splitFileName(str, str2);
        return buildFile(file, strArrSplitFileName[0], strArrSplitFileName[1]);
    }

    public static File buildUniqueFile(File file, String str) throws FileNotFoundException {
        String strSubstring;
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf >= 0) {
            String strSubstring2 = str.substring(0, iLastIndexOf);
            strSubstring = str.substring(iLastIndexOf + 1);
            str = strSubstring2;
        } else {
            strSubstring = null;
        }
        return buildUniqueFileWithExtension(file, str, strSubstring);
    }

    public static String[] splitFileName(String str, String str2) {
        String strSubstring;
        String strSubstring2;
        String mimeTypeFromExtension;
        int iLastIndexOf = str2.lastIndexOf(46);
        if (iLastIndexOf > 0) {
            strSubstring = str2.substring(0, iLastIndexOf);
            strSubstring2 = str2.substring(iLastIndexOf + 1);
            mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(strSubstring2.toLowerCase(Locale.ROOT));
        } else {
            strSubstring = str2;
            strSubstring2 = null;
            mimeTypeFromExtension = null;
        }
        if (mimeTypeFromExtension == null) {
            mimeTypeFromExtension = "application/octet-stream";
        }
        String extensionFromMimeType = "application/octet-stream".equalsIgnoreCase(str) ? null : MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
        if (MimeUtils.equalIgnoreCase(str, mimeTypeFromExtension) || MimeUtils.equalIgnoreCase(strSubstring2, extensionFromMimeType)) {
            str2 = strSubstring;
        } else {
            strSubstring2 = extensionFromMimeType;
        }
        if (strSubstring2 == null) {
            strSubstring2 = "";
        }
        return new String[]{str2, strSubstring2};
    }

    private static File buildFile(File file, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return new File(file, str);
        }
        return new File(file, str + "." + str2);
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

    public static String extractFileName(String str) {
        if (str == null) {
            return null;
        }
        String strExtractDisplayName = extractDisplayName(str);
        int iLastIndexOf = strExtractDisplayName.lastIndexOf(46);
        return iLastIndexOf == -1 ? strExtractDisplayName : strExtractDisplayName.substring(0, iLastIndexOf);
    }

    public static String extractFileExtension(String str) {
        String strExtractDisplayName;
        int iLastIndexOf;
        if (str == null || (iLastIndexOf = (strExtractDisplayName = extractDisplayName(str)).lastIndexOf(46)) == -1) {
            return null;
        }
        return strExtractDisplayName.substring(iLastIndexOf + 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Collection<java.io.File> getVolumeScanPaths(android.content.Context r4, java.lang.String r5) throws java.io.FileNotFoundException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r1 = r5.hashCode()
            r2 = -1820761141(0xffffffff937963cb, float:-3.147742E-27)
            r3 = 1
            if (r1 == r2) goto L1f
            r2 = 570410685(0x21ffc6bd, float:1.7332078E-18)
            if (r1 == r2) goto L15
            goto L29
        L15:
            java.lang.String r1 = "internal"
            boolean r1 = r5.equals(r1)
            if (r1 == 0) goto L29
            r1 = 0
            goto L2a
        L1f:
            java.lang.String r1 = "external"
            boolean r1 = r5.equals(r1)
            if (r1 == 0) goto L29
            r1 = r3
            goto L2a
        L29:
            r1 = -1
        L2a:
            if (r1 == 0) goto L5e
            if (r1 == r3) goto L3c
            java.io.File r1 = getVolumePath(r4, r5)
            if (r1 == 0) goto L65
            java.io.File r4 = getVolumePath(r4, r5)
            r0.add(r4)
            goto L65
        L3c:
            java.util.Set r5 = android.provider.MediaStore.getExternalVolumeNames(r4)
            java.util.Iterator r5 = r5.iterator()
        L44:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L65
            java.lang.Object r1 = r5.next()
            java.lang.String r1 = (java.lang.String) r1
            java.io.File r2 = getVolumePath(r4, r1)
            if (r2 == 0) goto L44
            java.io.File r1 = getVolumePath(r4, r1)
            r0.add(r1)
            goto L44
        L5e:
            java.util.Collection r4 = android.os.Environment.getInternalMediaDirectories()
            r0.addAll(r4)
        L65:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.util.FileUtils.getVolumeScanPaths(android.content.Context, java.lang.String):java.util.Collection");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.io.File getVolumePath(android.content.Context r4, java.lang.String r5) throws java.io.FileNotFoundException {
        /*
            java.lang.String r0 = "MediaProvider"
            int r1 = r5.hashCode()
            r2 = -1820761141(0xffffffff937963cb, float:-3.147742E-27)
            r3 = 1
            if (r1 == r2) goto L1c
            r2 = 570410685(0x21ffc6bd, float:1.7332078E-18)
            if (r1 == r2) goto L12
            goto L26
        L12:
            java.lang.String r1 = "internal"
            boolean r1 = r5.equals(r1)
            if (r1 == 0) goto L26
            r1 = 0
            goto L27
        L1c:
            java.lang.String r1 = "external"
            boolean r1 = r5.equals(r1)
            if (r1 == 0) goto L26
            r1 = r3
            goto L27
        L26:
            r1 = -1
        L27:
            if (r1 == 0) goto L6f
            if (r1 == r3) goto L6f
            r1 = 0
            android.net.Uri r2 = android.provider.MediaStore.Files.getContentUri(r5)     // Catch: java.lang.IllegalStateException -> L41
            java.lang.Class<android.os.storage.StorageManager> r3 = android.os.storage.StorageManager.class
            java.lang.Object r4 = r4.getSystemService(r3)     // Catch: java.lang.IllegalStateException -> L41
            android.os.storage.StorageManager r4 = (android.os.storage.StorageManager) r4     // Catch: java.lang.IllegalStateException -> L41
            android.os.storage.StorageVolume r4 = r4.getStorageVolume(r2)     // Catch: java.lang.IllegalStateException -> L41
            java.io.File r4 = r4.getDirectory()     // Catch: java.lang.IllegalStateException -> L41
            goto L57
        L41:
            r4 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to get volume "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r0, r2, r4)
            r4 = r1
        L57:
            if (r4 == 0) goto L5a
            return r4
        L5a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r2 = "Failed to get volume has no associated path "
            r4.append(r2)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.w(r0, r4)
            return r1
        L6f:
            java.io.FileNotFoundException r4 = new java.io.FileNotFoundException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            java.lang.String r5 = " has no associated path"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.util.FileUtils.getVolumePath(android.content.Context, java.lang.String):java.io.File");
    }

    public static Uri getContentUriForPath(String str) {
        Objects.requireNonNull(str);
        return MediaStore.Files.getContentUri(extractVolumeName(str));
    }

    public static String getVolumeName(Context context, File file) {
        return contains(Environment.getStorageDirectory(), file) ? ((StorageManager) context.getSystemService(StorageManager.class)).getStorageVolume(file).getMediaStoreVolumeName() : "internal";
    }

    public static boolean isDownload(String str) {
        return PATTERN_DOWNLOADS_FILE.matcher(str).matches();
    }

    public static boolean isDownloadDir(String str) {
        return PATTERN_DOWNLOADS_DIRECTORY.matcher(str).matches();
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

    public static String extractPathOwnerPackageName(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = PATTERN_OWNED_PATH.matcher(str);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    public static boolean isDataOrObbPath(String str) {
        if (str == null) {
            return false;
        }
        return PATTERN_DATA_OR_OBB_PATH.matcher(str).matches();
    }

    public static String extractTopLevelDir(String str) {
        String strExtractRelativePath = extractRelativePath(str);
        if (strExtractRelativePath == null) {
            return null;
        }
        String[] strArrSplit = strExtractRelativePath.split("/");
        if (strArrSplit.length > 0) {
            return strArrSplit[0];
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

    public static void computeDateExpires(ContentValues contentValues) {
        contentValues.remove("date_expires");
        Object obj = contentValues.get("is_pending");
        if (obj != null) {
            if (DatabaseUtils.parseBoolean(obj, false)) {
                contentValues.put("date_expires", Long.valueOf((System.currentTimeMillis() + DEFAULT_DURATION_PENDING) / 1000));
            } else {
                contentValues.putNull("date_expires");
            }
        }
        Object obj2 = contentValues.get("is_trashed");
        if (obj2 != null) {
            if (DatabaseUtils.parseBoolean(obj2, false)) {
                contentValues.put("date_expires", Long.valueOf((System.currentTimeMillis() + DEFAULT_DURATION_TRASHED) / 1000));
            } else {
                contentValues.putNull("date_expires");
            }
        }
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
            contentValues.put("is_pending", Integer.valueOf(matcher.group(1).equals(PREFIX_PENDING) ? 1 : 0));
            contentValues.put("is_trashed", Integer.valueOf(matcher.group(1).equals(PREFIX_TRASHED) ? 1 : 0));
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
            asString = String.format(".%s-%d-%s", PREFIX_PENDING, Long.valueOf(DatabaseUtils.getAsLong(contentValues, "date_expires", (System.currentTimeMillis() + DEFAULT_DURATION_PENDING) / 1000)), asString);
        } else if (DatabaseUtils.getAsBoolean(contentValues, "is_trashed", false)) {
            asString = String.format(".%s-%d-%s", PREFIX_TRASHED, Long.valueOf(DatabaseUtils.getAsLong(contentValues, "date_expires", (System.currentTimeMillis() + DEFAULT_DURATION_TRASHED) / 1000)), asString);
        }
        contentValues.put("_data", buildPath(file, contentValues.getAsString("relative_path"), asString).getAbsolutePath());
    }

    public static void sanitizeValues(ContentValues contentValues, boolean z) {
        String[] strArrSplit = contentValues.getAsString("relative_path").split("/");
        for (int i = 0; i < strArrSplit.length; i++) {
            strArrSplit[i] = sanitizeDisplayName(strArrSplit[i], z);
        }
        contentValues.put("relative_path", String.join("/", strArrSplit) + "/");
        contentValues.put("_display_name", sanitizeDisplayName(contentValues.getAsString("_display_name"), z));
    }

    public static String getAbsoluteSanitizedPath(String str) {
        String[] strArrSanitizePath = sanitizePath(str);
        if (strArrSanitizePath.length == 0) {
            return null;
        }
        return "/" + String.join("/", (CharSequence[]) Arrays.copyOfRange(strArrSanitizePath, 1, strArrSanitizePath.length));
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

    public static int clearAppCacheDirectories() {
        Log.i(Logging.TAG, "Clearing cache for all apps");
        int i = 0;
        for (File file : buildPath(Environment.getExternalStorageDirectory(), "Android", "data").listFiles()) {
            try {
                File file2 = new File(file, "cache");
                if (file2.isDirectory()) {
                    deleteContents(file2);
                }
            } catch (Exception e) {
                Log.e(Logging.TAG, "Couldn't delete all app cache dirs!", e);
                i = OsConstants.EIO;
            }
        }
        return i;
    }
}
