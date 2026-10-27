package android.util;

import android.os.FileUtils;
import android.os.SystemClock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.Consumer;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes3.dex */
public class AtomicFile {
    private static final String LOG_TAG = "AtomicFile";
    private final File mBaseName;
    private final String mCommitTag;
    private final File mLegacyBackupName;
    private final File mNewName;
    private long mStartTime;

    public AtomicFile(File file) {
        this(file, null);
    }

    public AtomicFile(File file, String str) {
        this.mBaseName = file;
        this.mNewName = new File(file.getPath() + ".new");
        this.mLegacyBackupName = new File(file.getPath() + ".bak");
        this.mCommitTag = str;
    }

    public File getBaseFile() {
        return this.mBaseName;
    }

    public void delete() {
        this.mBaseName.delete();
        this.mNewName.delete();
        this.mLegacyBackupName.delete();
    }

    public FileOutputStream startWrite() throws IOException {
        return startWrite(this.mCommitTag != null ? SystemClock.uptimeMillis() : 0L);
    }

    public FileOutputStream startWrite(long j) throws IOException {
        this.mStartTime = j;
        if (this.mLegacyBackupName.exists()) {
            rename(this.mLegacyBackupName, this.mBaseName);
        }
        try {
            return new FileOutputStream(this.mNewName);
        } catch (FileNotFoundException unused) {
            File parentFile = this.mNewName.getParentFile();
            if (!parentFile.mkdirs()) {
                throw new IOException("Failed to create directory for " + this.mNewName);
            }
            FileUtils.setPermissions(parentFile.getPath(), 505, -1, -1);
            try {
                return new FileOutputStream(this.mNewName);
            } catch (FileNotFoundException e) {
                throw new IOException("Failed to create new file " + this.mNewName, e);
            }
        }
    }

    public void finishWrite(FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) {
            return;
        }
        if (!FileUtils.sync(fileOutputStream)) {
            Log.e(LOG_TAG, "Failed to sync file output stream");
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Failed to close file output stream", e);
        }
        rename(this.mNewName, this.mBaseName);
        String str = this.mCommitTag;
        if (str != null) {
            com.android.internal.logging.EventLogTags.writeCommitSysConfigFile(str, SystemClock.uptimeMillis() - this.mStartTime);
        }
    }

    public void failWrite(FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) {
            return;
        }
        if (!FileUtils.sync(fileOutputStream)) {
            Log.e(LOG_TAG, "Failed to sync file output stream");
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Failed to close file output stream", e);
        }
        if (this.mNewName.delete()) {
            return;
        }
        Log.e(LOG_TAG, "Failed to delete new file " + this.mNewName);
    }

    @Deprecated
    public void truncate() throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.mBaseName);
            FileUtils.sync(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException unused) {
            throw new IOException("Couldn't append " + this.mBaseName);
        } catch (IOException unused2) {
        }
    }

    @Deprecated
    public FileOutputStream openAppend() throws IOException {
        try {
            return new FileOutputStream(this.mBaseName, true);
        } catch (FileNotFoundException unused) {
            throw new IOException("Couldn't append " + this.mBaseName);
        }
    }

    public FileInputStream openRead() throws FileNotFoundException {
        if (this.mLegacyBackupName.exists()) {
            rename(this.mLegacyBackupName, this.mBaseName);
        }
        if (this.mNewName.exists() && this.mBaseName.exists() && !this.mNewName.delete()) {
            Log.e(LOG_TAG, "Failed to delete outdated new file " + this.mNewName);
        }
        return new FileInputStream(this.mBaseName);
    }

    public boolean exists() {
        return this.mBaseName.exists() || this.mLegacyBackupName.exists();
    }

    public long getLastModifiedTime() {
        if (this.mLegacyBackupName.exists()) {
            return this.mLegacyBackupName.lastModified();
        }
        return this.mBaseName.lastModified();
    }

    public byte[] readFully() throws IOException {
        FileInputStream fileInputStreamOpenRead = openRead();
        try {
            byte[] bArr = new byte[fileInputStreamOpenRead.available()];
            int i = 0;
            while (true) {
                int i2 = fileInputStreamOpenRead.read(bArr, i, bArr.length - i);
                if (i2 <= 0) {
                    return bArr;
                }
                i += i2;
                int iAvailable = fileInputStreamOpenRead.available();
                if (iAvailable > bArr.length - i) {
                    byte[] bArr2 = new byte[iAvailable + i];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    bArr = bArr2;
                }
            }
        } finally {
            fileInputStreamOpenRead.close();
        }
    }

    public void write(Consumer<FileOutputStream> consumer) {
        FileOutputStream fileOutputStreamStartWrite;
        try {
            fileOutputStreamStartWrite = startWrite();
            try {
                consumer.accept(fileOutputStreamStartWrite);
                finishWrite(fileOutputStreamStartWrite);
            } catch (Throwable th) {
                th = th;
                try {
                    failWrite(fileOutputStreamStartWrite);
                    throw ExceptionUtils.propagate(th);
                } finally {
                    IoUtils.closeQuietly(fileOutputStreamStartWrite);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStreamStartWrite = null;
        }
    }

    private static void rename(File file, File file2) {
        if (file2.isDirectory() && !file2.delete()) {
            Log.e(LOG_TAG, "Failed to delete file which is a directory " + file2);
        }
        if (file.renameTo(file2)) {
            return;
        }
        Log.e(LOG_TAG, "Failed to rename " + file + " to " + file2);
    }
}
