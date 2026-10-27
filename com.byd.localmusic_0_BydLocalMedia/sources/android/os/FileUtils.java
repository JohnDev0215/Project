package android.os;

import android.app.backup.FullBackup;
import android.media.MediaMetrics;
import android.provider.DocumentsContract;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import android.webkit.MimeTypeMap;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.SizedInputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import libcore.io.IoUtils;
import libcore.util.EmptyArray;

/* JADX INFO: loaded from: classes2.dex */
public final class FileUtils {
    private static final long COPY_CHECKPOINT_BYTES = 524288;
    public static final int S_IRGRP = 32;
    public static final int S_IROTH = 4;
    public static final int S_IRUSR = 256;
    public static final int S_IRWXG = 56;
    public static final int S_IRWXO = 7;
    public static final int S_IRWXU = 448;
    public static final int S_IWGRP = 16;
    public static final int S_IWOTH = 2;
    public static final int S_IWUSR = 128;
    public static final int S_IXGRP = 8;
    public static final int S_IXOTH = 1;
    public static final int S_IXUSR = 64;
    private static final String TAG = "FileUtils";
    private static boolean sEnableCopyOptimizations = true;

    public interface ProgressListener {
        void onProgress(long j);
    }

    private static boolean isValidExtFilenameChar(char c) {
        return (c == 0 || c == '/') ? false : true;
    }

    private static boolean isValidFatFilenameChar(char c) {
        return ((c >= 0 && c <= 31) || c == '\"' || c == '*' || c == '/' || c == ':' || c == '<' || c == '\\' || c == '|' || c == 127 || c == '>' || c == '?') ? false : true;
    }

    public static long roundStorageSize(long j) {
        long j2 = 1;
        long j3 = 1;
        while (true) {
            long j4 = j2 * j3;
            if (j4 >= j) {
                return j4;
            }
            j2 <<= 1;
            if (j2 > 512) {
                j3 *= 1000;
                j2 = 1;
            }
        }
    }

    private FileUtils() {
    }

    private static class NoImagePreloadHolder {
        public static final Pattern SAFE_FILENAME_PATTERN = Pattern.compile("[\\w%+,./=_-]+");

        private NoImagePreloadHolder() {
        }
    }

    public static int setPermissions(File file, int i, int i2, int i3) {
        return setPermissions(file.getAbsolutePath(), i, i2, i3);
    }

    public static int setPermissions(String str, int i, int i2, int i3) {
        try {
            Os.chmod(str, i);
            if (i2 < 0 && i3 < 0) {
                return 0;
            }
            try {
                Os.chown(str, i2, i3);
                return 0;
            } catch (ErrnoException e) {
                Slog.w(TAG, "Failed to chown(" + str + "): " + e);
                return e.errno;
            }
        } catch (ErrnoException e2) {
            Slog.w(TAG, "Failed to chmod(" + str + "): " + e2);
            return e2.errno;
        }
    }

    public static int setPermissions(FileDescriptor fileDescriptor, int i, int i2, int i3) {
        try {
            Os.fchmod(fileDescriptor, i);
            if (i2 < 0 && i3 < 0) {
                return 0;
            }
            try {
                Os.fchown(fileDescriptor, i2, i3);
                return 0;
            } catch (ErrnoException e) {
                Slog.w(TAG, "Failed to fchown(): " + e);
                return e.errno;
            }
        } catch (ErrnoException e2) {
            Slog.w(TAG, "Failed to fchmod(): " + e2);
            return e2.errno;
        }
    }

    public static void copyPermissions(File file, File file2) throws IOException {
        try {
            StructStat structStatStat = Os.stat(file.getAbsolutePath());
            Os.chmod(file2.getAbsolutePath(), structStatStat.st_mode);
            Os.chown(file2.getAbsolutePath(), structStatStat.st_uid, structStatStat.st_gid);
        } catch (ErrnoException e) {
            throw e.rethrowAsIOException();
        }
    }

    @Deprecated
    public static int getUid(String str) {
        try {
            return Os.stat(str).st_uid;
        } catch (ErrnoException unused) {
            return -1;
        }
    }

    public static boolean sync(FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) {
            return true;
        }
        try {
            fileOutputStream.getFD().sync();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    @Deprecated
    public static boolean copyFile(File file, File file2) {
        try {
            copyFileOrThrow(file, file2);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    @Deprecated
    public static void copyFileOrThrow(File file, File file2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            copyToFileOrThrow(fileInputStream, file2);
            fileInputStream.close();
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Deprecated
    public static boolean copyToFile(InputStream inputStream, File file) {
        try {
            copyToFileOrThrow(inputStream, file);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    @Deprecated
    public static void copyToFileOrThrow(InputStream inputStream, File file) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            copy(inputStream, fileOutputStream);
            try {
                Os.fsync(fileOutputStream.getFD());
                fileOutputStream.close();
            } catch (ErrnoException e) {
                throw e.rethrowAsIOException();
            }
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long copy(File file, File file2) throws IOException {
        return copy(file, file2, (CancellationSignal) null, (Executor) null, (ProgressListener) null);
    }

    public static long copy(File file, File file2, CancellationSignal cancellationSignal, Executor executor, ProgressListener progressListener) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                long jCopy = copy(fileInputStream, fileOutputStream, cancellationSignal, executor, progressListener);
                fileOutputStream.close();
                fileInputStream.close();
                return jCopy;
            } finally {
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        return copy(inputStream, outputStream, (CancellationSignal) null, (Executor) null, (ProgressListener) null);
    }

    public static long copy(InputStream inputStream, OutputStream outputStream, CancellationSignal cancellationSignal, Executor executor, ProgressListener progressListener) throws IOException {
        if (sEnableCopyOptimizations && (inputStream instanceof FileInputStream) && (outputStream instanceof FileOutputStream)) {
            return copy(((FileInputStream) inputStream).getFD(), ((FileOutputStream) outputStream).getFD(), cancellationSignal, executor, progressListener);
        }
        return copyInternalUserspace(inputStream, outputStream, cancellationSignal, executor, progressListener);
    }

    public static long copy(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2) throws IOException {
        return copy(fileDescriptor, fileDescriptor2, (CancellationSignal) null, (Executor) null, (ProgressListener) null);
    }

    public static long copy(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, CancellationSignal cancellationSignal, Executor executor, ProgressListener progressListener) throws IOException {
        return copy(fileDescriptor, fileDescriptor2, Long.MAX_VALUE, cancellationSignal, executor, progressListener);
    }

    public static long copy(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, long j, CancellationSignal cancellationSignal, Executor executor, ProgressListener progressListener) throws IOException {
        if (sEnableCopyOptimizations) {
            try {
                StructStat structStatFstat = Os.fstat(fileDescriptor);
                StructStat structStatFstat2 = Os.fstat(fileDescriptor2);
                if (OsConstants.S_ISREG(structStatFstat.st_mode) && OsConstants.S_ISREG(structStatFstat2.st_mode)) {
                    return copyInternalSendfile(fileDescriptor, fileDescriptor2, j, cancellationSignal, executor, progressListener);
                }
                if (OsConstants.S_ISFIFO(structStatFstat.st_mode) || OsConstants.S_ISFIFO(structStatFstat2.st_mode)) {
                    return copyInternalSplice(fileDescriptor, fileDescriptor2, j, cancellationSignal, executor, progressListener);
                }
            } catch (ErrnoException e) {
                throw e.rethrowAsIOException();
            }
        }
        return copyInternalUserspace(fileDescriptor, fileDescriptor2, j, cancellationSignal, executor, progressListener);
    }

    public static long copyInternalSplice(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, long j, CancellationSignal cancellationSignal, Executor executor, final ProgressListener progressListener) throws ErrnoException {
        long j2 = j;
        final long j3 = 0;
        long j4 = 0;
        while (true) {
            long jSplice = Os.splice(fileDescriptor, null, fileDescriptor2, null, Math.min(j2, 524288L), OsConstants.SPLICE_F_MOVE | OsConstants.SPLICE_F_MORE);
            if (jSplice == 0) {
                break;
            }
            j3 += jSplice;
            j4 += jSplice;
            j2 -= jSplice;
            if (j4 >= 524288) {
                if (cancellationSignal != null) {
                    cancellationSignal.throwIfCanceled();
                }
                if (executor != null && progressListener != null) {
                    executor.execute(new Runnable() { // from class: android.os.-$$Lambda$FileUtils$RlOy_0MlKMWkkCC1mk_jzWcLTKs
                        @Override // java.lang.Runnable
                        public final void run() {
                            progressListener.onProgress(j3);
                        }
                    });
                }
                j4 = 0;
            }
        }
        if (executor != null && progressListener != null) {
            executor.execute(new Runnable() { // from class: android.os.-$$Lambda$FileUtils$e0JoE-HjVf9vMX679eNxZixyUZ0
                @Override // java.lang.Runnable
                public final void run() {
                    progressListener.onProgress(j3);
                }
            });
        }
        return j3;
    }

    public static long copyInternalSendfile(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, long j, CancellationSignal cancellationSignal, Executor executor, final ProgressListener progressListener) throws ErrnoException {
        long j2 = j;
        final long j3 = 0;
        long j4 = 0;
        while (true) {
            long jSendfile = Os.sendfile(fileDescriptor2, fileDescriptor, null, Math.min(j2, 524288L));
            if (jSendfile == 0) {
                break;
            }
            j3 += jSendfile;
            j4 += jSendfile;
            j2 -= jSendfile;
            if (j4 >= 524288) {
                if (cancellationSignal != null) {
                    cancellationSignal.throwIfCanceled();
                }
                if (executor != null && progressListener != null) {
                    executor.execute(new Runnable() { // from class: android.os.-$$Lambda$FileUtils$QtbHtI8Y1rifwydngi6coGK5l2A
                        @Override // java.lang.Runnable
                        public final void run() {
                            progressListener.onProgress(j3);
                        }
                    });
                }
                j4 = 0;
            }
        }
        if (executor != null && progressListener != null) {
            executor.execute(new Runnable() { // from class: android.os.-$$Lambda$FileUtils$XQaJiyjsC2_MFNDbZFQcIhqPnNA
                @Override // java.lang.Runnable
                public final void run() {
                    progressListener.onProgress(j3);
                }
            });
        }
        return j3;
    }

    @Deprecated
    public static long copyInternalUserspace(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, ProgressListener progressListener, CancellationSignal cancellationSignal, long j) throws IOException {
        return copyInternalUserspace(fileDescriptor, fileDescriptor2, j, cancellationSignal, new Executor() { // from class: android.os.-$$Lambda$_14QHG018Z6p13d3hzJuGTWnNeo
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                runnable.run();
            }
        }, progressListener);
    }

    public static long copyInternalUserspace(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, long j, CancellationSignal cancellationSignal, Executor executor, ProgressListener progressListener) throws IOException {
        if (j != Long.MAX_VALUE) {
            return copyInternalUserspace(new SizedInputStream(new FileInputStream(fileDescriptor), j), new FileOutputStream(fileDescriptor2), cancellationSignal, executor, progressListener);
        }
        return copyInternalUserspace(new FileInputStream(fileDescriptor), new FileOutputStream(fileDescriptor2), cancellationSignal, executor, progressListener);
    }

    public static long copyInternalUserspace(InputStream inputStream, OutputStream outputStream, CancellationSignal cancellationSignal, Executor executor, final ProgressListener progressListener) throws IOException {
        byte[] bArr = new byte[8192];
        final long j = 0;
        long j2 = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                break;
            }
            outputStream.write(bArr, 0, i);
            long j3 = i;
            j += j3;
            j2 += j3;
            if (j2 >= 524288) {
                if (cancellationSignal != null) {
                    cancellationSignal.throwIfCanceled();
                }
                if (executor != null && progressListener != null) {
                    executor.execute(new Runnable() { // from class: android.os.-$$Lambda$FileUtils$TJeD9NeX5giO-5vlBrurGI-g4IY
                        @Override // java.lang.Runnable
                        public final void run() {
                            progressListener.onProgress(j);
                        }
                    });
                }
                j2 = 0;
            }
        }
        if (executor != null && progressListener != null) {
            executor.execute(new Runnable() { // from class: android.os.-$$Lambda$FileUtils$0SBPRWOXcbR9EMG_p-55sUuxJ_0
                @Override // java.lang.Runnable
                public final void run() {
                    progressListener.onProgress(j);
                }
            });
        }
        return j;
    }

    public static boolean isFilenameSafe(File file) {
        return NoImagePreloadHolder.SAFE_FILENAME_PATTERN.matcher(file.getPath()).matches();
    }

    public static String readTextFile(File file, int i, String str) throws IOException {
        int i2;
        boolean z;
        int i3;
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        try {
            long length = file.length();
            String str2 = "";
            if (i > 0 || (length > 0 && i == 0)) {
                if (length > 0 && (i == 0 || length < i)) {
                    i = (int) length;
                }
                byte[] bArr = new byte[i + 1];
                int i4 = bufferedInputStream.read(bArr);
                if (i4 > 0) {
                    if (i4 <= i) {
                        str2 = new String(bArr, 0, i4);
                    } else if (str == null) {
                        str2 = new String(bArr, 0, i);
                    } else {
                        str2 = new String(bArr, 0, i) + str;
                    }
                }
            } else if (i < 0) {
                byte[] bArr2 = null;
                byte[] bArr3 = null;
                boolean z2 = false;
                while (true) {
                    z = true;
                    if (bArr2 != null) {
                        z2 = true;
                    }
                    if (bArr2 == null) {
                        bArr2 = new byte[-i];
                    }
                    i3 = bufferedInputStream.read(bArr2);
                    if (i3 != bArr2.length) {
                        break;
                    }
                    byte[] bArr4 = bArr3;
                    bArr3 = bArr2;
                    bArr2 = bArr4;
                }
                if (bArr3 != null || i3 > 0) {
                    if (bArr3 == null) {
                        str2 = new String(bArr2, 0, i3);
                    } else {
                        if (i3 > 0) {
                            System.arraycopy(bArr3, i3, bArr3, 0, bArr3.length - i3);
                            System.arraycopy(bArr2, 0, bArr3, bArr3.length - i3, i3);
                        } else {
                            z = z2;
                        }
                        if (str == null || !z) {
                            str2 = new String(bArr3);
                        } else {
                            str2 = str + new String(bArr3);
                        }
                    }
                }
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr5 = new byte[1024];
                do {
                    i2 = bufferedInputStream.read(bArr5);
                    if (i2 > 0) {
                        byteArrayOutputStream.write(bArr5, 0, i2);
                    }
                } while (i2 == 1024);
                str2 = byteArrayOutputStream.toString();
            }
            return str2;
        } finally {
            bufferedInputStream.close();
            fileInputStream.close();
        }
    }

    public static void stringToFile(File file, String str) throws IOException {
        stringToFile(file.getAbsolutePath(), str);
    }

    public static void bytesToFile(String str, byte[] bArr) throws IOException {
        if (str.startsWith("/proc/")) {
            int iAllowThreadDiskWritesMask = StrictMode.allowThreadDiskWritesMask();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                try {
                    fileOutputStream.write(bArr);
                    fileOutputStream.close();
                    return;
                } finally {
                }
            } finally {
                StrictMode.setThreadPolicyMask(iAllowThreadDiskWritesMask);
            }
        }
        FileOutputStream fileOutputStream2 = new FileOutputStream(str);
        try {
            fileOutputStream2.write(bArr);
            fileOutputStream2.close();
        } catch (Throwable th) {
            try {
                fileOutputStream2.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static void stringToFile(String str, String str2) throws IOException {
        bytesToFile(str, str2.getBytes(StandardCharsets.UTF_8));
    }

    @Deprecated
    public static long checksumCrc32(File file) throws Throwable {
        CheckedInputStream checkedInputStream;
        CRC32 crc32 = new CRC32();
        CheckedInputStream checkedInputStream2 = null;
        try {
            checkedInputStream = new CheckedInputStream(new FileInputStream(file), crc32);
        } catch (Throwable th) {
            th = th;
        }
        try {
            while (checkedInputStream.read(new byte[128]) >= 0) {
            }
            long value = crc32.getValue();
            try {
                checkedInputStream.close();
            } catch (IOException unused) {
            }
            return value;
        } catch (Throwable th2) {
            th = th2;
            checkedInputStream2 = checkedInputStream;
            if (checkedInputStream2 != null) {
                try {
                    checkedInputStream2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    public static byte[] digest(File file, String str) throws NoSuchAlgorithmException, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] bArrDigest = digest(fileInputStream, str);
            fileInputStream.close();
            return bArrDigest;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static byte[] digest(InputStream inputStream, String str) throws NoSuchAlgorithmException, IOException {
        return digestInternalUserspace(inputStream, str);
    }

    public static byte[] digest(FileDescriptor fileDescriptor, String str) throws NoSuchAlgorithmException, IOException {
        return digestInternalUserspace(new FileInputStream(fileDescriptor), str);
    }

    private static byte[] digestInternalUserspace(InputStream inputStream, String str) throws NoSuchAlgorithmException, IOException {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigest);
        try {
            do {
            } while (digestInputStream.read(new byte[8192]) != -1);
            digestInputStream.close();
            return messageDigest.digest();
        } catch (Throwable th) {
            try {
                digestInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
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
        Arrays.sort(fileArrListFiles, new Comparator<File>() { // from class: android.os.FileUtils.1
            @Override // java.util.Comparator
            public int compare(File file2, File file3) {
                return Long.compare(file3.lastModified(), file2.lastModified());
            }
        });
        while (i < fileArrListFiles.length) {
            File file2 = fileArrListFiles[i];
            if (System.currentTimeMillis() - file2.lastModified() > j && file2.delete()) {
                Log.d(TAG, "Deleted old file " + file2);
                z = true;
            }
            i++;
        }
        return z;
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

    public static boolean deleteContentsAndDir(File file) {
        if (deleteContents(file)) {
            return file.delete();
        }
        return false;
    }

    public static boolean deleteContents(File file) {
        File[] fileArrListFiles = file.listFiles();
        boolean zDeleteContents = true;
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    zDeleteContents &= deleteContents(file2);
                }
                if (!file2.delete()) {
                    Log.w(TAG, "Failed to delete " + file2);
                    zDeleteContents = false;
                }
            }
        }
        return zDeleteContents;
    }

    public static boolean isValidExtFilename(String str) {
        return str != null && str.equals(buildValidExtFilename(str));
    }

    public static String buildValidExtFilename(String str) {
        if (TextUtils.isEmpty(str) || MediaMetrics.SEPARATOR.equals(str) || "..".equals(str)) {
            return "(invalid)";
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (isValidExtFilenameChar(cCharAt)) {
                sb.append(cCharAt);
            } else {
                sb.append('_');
            }
        }
        trimFilename(sb, 255);
        return sb.toString();
    }

    public static boolean isValidFatFilename(String str) {
        return str != null && str.equals(buildValidFatFilename(str));
    }

    public static String buildValidFatFilename(String str) {
        if (TextUtils.isEmpty(str) || MediaMetrics.SEPARATOR.equals(str) || "..".equals(str)) {
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
            sb.insert(sb.length() / 2, Session.TRUNCATE_STRING);
        }
    }

    public static String rewriteAfterRename(File file, File file2, String str) {
        File fileRewriteAfterRename;
        if (str == null || (fileRewriteAfterRename = rewriteAfterRename(file, file2, new File(str))) == null) {
            return null;
        }
        return fileRewriteAfterRename.getAbsolutePath();
    }

    public static String[] rewriteAfterRename(File file, File file2, String[] strArr) {
        if (strArr == null) {
            return null;
        }
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i] = rewriteAfterRename(file, file2, strArr[i]);
        }
        return strArr2;
    }

    public static File rewriteAfterRename(File file, File file2, File file3) {
        if (file3 == null || file == null || file2 == null || !contains(file, file3)) {
            return null;
        }
        return new File(file2, file3.getAbsolutePath().substring(file.getAbsolutePath().length()));
    }

    private static File buildUniqueFileWithExtension(File file, String str, String str2) throws FileNotFoundException {
        File fileBuildFile = buildFile(file, str, str2);
        int i = 0;
        while (fileBuildFile.exists()) {
            int i2 = i + 1;
            if (i >= 32) {
                throw new FileNotFoundException("Failed to create unique file");
            }
            i = i2;
            fileBuildFile = buildFile(file, str + " (" + i2 + ")", str2);
        }
        return fileBuildFile;
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
        if (!DocumentsContract.Document.MIME_TYPE_DIR.equals(str)) {
            int iLastIndexOf = str2.lastIndexOf(46);
            if (iLastIndexOf >= 0) {
                strSubstring = str2.substring(0, iLastIndexOf);
                strSubstring2 = str2.substring(iLastIndexOf + 1);
                mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(strSubstring2.toLowerCase());
            } else {
                strSubstring = str2;
                strSubstring2 = null;
                mimeTypeFromExtension = null;
            }
            if (mimeTypeFromExtension == null) {
                mimeTypeFromExtension = "application/octet-stream";
            }
            extensionFromMimeType = "application/octet-stream".equals(str) ? null : MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
            if (Objects.equals(str, mimeTypeFromExtension) || Objects.equals(strSubstring2, extensionFromMimeType)) {
                extensionFromMimeType = strSubstring2;
                str2 = strSubstring;
            }
        }
        if (extensionFromMimeType == null) {
            extensionFromMimeType = "";
        }
        return new String[]{str2, extensionFromMimeType};
    }

    private static File buildFile(File file, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return new File(file, str);
        }
        return new File(file, str + MediaMetrics.SEPARATOR + str2);
    }

    public static String[] listOrEmpty(File file) {
        if (file != null) {
            return ArrayUtils.defeatNullable(file.list());
        }
        return EmptyArray.STRING;
    }

    public static File[] listFilesOrEmpty(File file) {
        if (file != null) {
            return ArrayUtils.defeatNullable(file.listFiles());
        }
        return ArrayUtils.EMPTY_FILE;
    }

    public static File[] listFilesOrEmpty(File file, FilenameFilter filenameFilter) {
        if (file != null) {
            return ArrayUtils.defeatNullable(file.listFiles(filenameFilter));
        }
        return ArrayUtils.EMPTY_FILE;
    }

    public static File newFileOrNull(String str) {
        if (str != null) {
            return new File(str);
        }
        return null;
    }

    public static File createDir(File file, String str) {
        File file2 = new File(file, str);
        if (createDir(file2)) {
            return file2;
        }
        return null;
    }

    public static boolean createDir(File file) {
        if (file.exists()) {
            return file.isDirectory();
        }
        return file.mkdir();
    }

    @Deprecated
    public static void closeQuietly(AutoCloseable autoCloseable) {
        IoUtils.closeQuietly(autoCloseable);
    }

    @Deprecated
    public static void closeQuietly(FileDescriptor fileDescriptor) {
        IoUtils.closeQuietly(fileDescriptor);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006e  */
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
            if (r0 == 0) goto L45
            int r0 = android.system.OsConstants.O_RDWR
            int r1 = android.system.OsConstants.O_CREAT
        L43:
            r0 = r0 | r1
            goto L5e
        L45:
            java.lang.String r0 = "w"
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto L53
            int r0 = android.system.OsConstants.O_WRONLY
            int r1 = android.system.OsConstants.O_CREAT
            goto L43
        L53:
            java.lang.String r0 = "r"
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto L72
            int r0 = android.system.OsConstants.O_RDONLY
        L5e:
            int r1 = r5.indexOf(r3)
            r2 = -1
            if (r1 == r2) goto L68
            int r1 = android.system.OsConstants.O_TRUNC
            r0 = r0 | r1
        L68:
            int r5 = r5.indexOf(r4)
            if (r5 == r2) goto L71
            int r5 = android.system.OsConstants.O_APPEND
            r0 = r0 | r5
        L71:
            return r0
        L72:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.os.FileUtils.translateModeStringToPosix(java.lang.String):int");
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
        if ((OsConstants.O_TRUNC & i) == OsConstants.O_TRUNC) {
            str = str + "t";
        }
        if ((i & OsConstants.O_APPEND) != OsConstants.O_APPEND) {
            return str;
        }
        return str + FullBackup.APK_TREE_TOKEN;
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
        if ((OsConstants.O_CREAT & i) == OsConstants.O_CREAT) {
            i2 |= 134217728;
        }
        if ((OsConstants.O_TRUNC & i) == OsConstants.O_TRUNC) {
            i2 |= 67108864;
        }
        return (i & OsConstants.O_APPEND) == OsConstants.O_APPEND ? i2 | 33554432 : i2;
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
        if ((OsConstants.R_OK & i) == OsConstants.R_OK) {
            return OsConstants.O_RDONLY;
        }
        if ((OsConstants.W_OK & i) == OsConstants.W_OK) {
            return OsConstants.O_WRONLY;
        }
        throw new IllegalArgumentException("Bad mode: " + i);
    }

    public static class MemoryPipe extends Thread implements AutoCloseable {
        private final byte[] data;
        private final FileDescriptor[] pipe;
        private final boolean sink;

        private MemoryPipe(byte[] bArr, boolean z) throws IOException {
            try {
                this.pipe = Os.pipe();
                this.data = bArr;
                this.sink = z;
            } catch (ErrnoException e) {
                throw e.rethrowAsIOException();
            }
        }

        private MemoryPipe startInternal() {
            super.start();
            return this;
        }

        public static MemoryPipe createSource(byte[] bArr) throws IOException {
            return new MemoryPipe(bArr, false).startInternal();
        }

        public static MemoryPipe createSink(byte[] bArr) throws IOException {
            return new MemoryPipe(bArr, true).startInternal();
        }

        public FileDescriptor getFD() {
            return this.sink ? this.pipe[1] : this.pipe[0];
        }

        public FileDescriptor getInternalFD() {
            return this.sink ? this.pipe[0] : this.pipe[1];
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0042  */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r6 = this;
                java.io.FileDescriptor r0 = r6.getInternalFD()
                r1 = 0
            L5:
                r2 = 1
                byte[] r4 = r6.data     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3e
                int r4 = r4.length     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3e
                if (r1 >= r4) goto L27
                boolean r4 = r6.sink     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3e
                if (r4 == 0) goto L1b
                byte[] r4 = r6.data     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3e
                byte[] r5 = r6.data     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3e
                int r5 = r5.length     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3e
                int r5 = r5 - r1
                int r2 = android.system.Os.read(r0, r4, r1, r5)     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3e
                goto L25
            L1b:
                byte[] r4 = r6.data     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3e
                byte[] r5 = r6.data     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3e
                int r5 = r5.length     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3e
                int r5 = r5 - r1
                int r2 = android.system.Os.write(r0, r4, r1, r5)     // Catch: java.lang.Throwable -> L2c java.lang.Throwable -> L3e
            L25:
                int r1 = r1 + r2
                goto L5
            L27:
                boolean r1 = r6.sink
                if (r1 == 0) goto L4b
                goto L42
            L2c:
                r1 = move-exception
                boolean r4 = r6.sink
                if (r4 == 0) goto L3a
                java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS
                long r2 = r4.toMillis(r2)
                android.os.SystemClock.sleep(r2)
            L3a:
                libcore.io.IoUtils.closeQuietly(r0)
                throw r1
            L3e:
                boolean r1 = r6.sink
                if (r1 == 0) goto L4b
            L42:
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS
                long r1 = r1.toMillis(r2)
                android.os.SystemClock.sleep(r1)
            L4b:
                libcore.io.IoUtils.closeQuietly(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.os.FileUtils.MemoryPipe.run():void");
        }

        @Override // java.lang.AutoCloseable
        public void close() throws Exception {
            IoUtils.closeQuietly(getFD());
        }
    }
}
