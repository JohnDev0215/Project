package com.amazon.weblab.mobile.repository;

import com.amazon.music.destination.parser.ParserUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Calendar;
import org.json.JSONException;

/* JADX INFO: loaded from: classes5.dex */
class FileStorage implements IStorage {
    private static final Charset UTF8_CHARSET = Charset.forName(ParserUtil.UTF_8);
    protected File mDirectory;
    protected File mFile;
    protected final String mWeblabClientIdentifier;

    FileStorage(String str, String str2) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("directory cannot be null or empty");
        }
        if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException("directory cannot be null or empty");
        }
        File file = new File(str);
        this.mDirectory = file;
        if (!file.exists()) {
            throw new IllegalArgumentException("Directory " + str + " doesn't exist");
        }
        if (!this.mDirectory.isDirectory()) {
            throw new IllegalArgumentException(str + " is not a directory");
        }
        if (!this.mDirectory.canRead() || !this.mDirectory.canWrite()) {
            throw new IllegalArgumentException("Application needs a read and write access to " + str);
        }
        this.mWeblabClientIdentifier = str2;
        this.mFile = new File(str, str2);
    }

    private String readBackupFile() throws Throwable {
        BufferedInputStream bufferedInputStream;
        StringBuilder sb = new StringBuilder();
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(this.mFile));
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int i = bufferedInputStream.read(bArr, 0, 8192);
                if (i != -1) {
                    sb.append(new String(bArr, 0, i, UTF8_CHARSET));
                } else {
                    String string = sb.toString();
                    bufferedInputStream.close();
                    return string;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            throw th;
        }
    }

    private void writeBackupFile(String str) throws Throwable {
        File file;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            file = new File(this.mDirectory, this.mFile.getName() + Calendar.getInstance().getTime().hashCode() + ".tmp");
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedOutputStream.write(str.getBytes(UTF8_CHARSET));
            bufferedOutputStream.flush();
            if (!file.renameTo(this.mFile)) {
                throw new IOException("Cannot rename the temporary file to backup file.");
            }
            bufferedOutputStream.close();
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            throw th;
        }
    }

    @Override // com.amazon.weblab.mobile.repository.IStorage
    public boolean hasBackup() {
        return this.mFile.exists();
    }

    @Override // com.amazon.weblab.mobile.repository.IStorage
    public void writeBackup(StorageEntity storageEntity) throws Throwable {
        if (storageEntity == null) {
            throw new IllegalArgumentException("data can't be null");
        }
        try {
            writeBackupFile(new FileStorageJSONParser().putApplicationVersion(storageEntity.getApplicationVersion()).putSessionInfo(storageEntity.getSessionInfo()).putTreatmentAssignments(storageEntity.getTreatments().values()).toString());
        } catch (JSONException e) {
            throw new RuntimeException("Data could not be parsed into a JSON String", e);
        }
    }

    @Override // com.amazon.weblab.mobile.repository.IStorage
    public StorageEntity readBackup() throws IOException, RuntimeException {
        String backupFile;
        if (!hasBackup() || (backupFile = readBackupFile()) == null || backupFile.isEmpty()) {
            throw new IOException();
        }
        try {
            FileStorageJSONParser fileStorageJSONParser = new FileStorageJSONParser(backupFile);
            return new StorageEntity(fileStorageJSONParser.getApplicationVersion(), fileStorageJSONParser.getSessionInfo(), fileStorageJSONParser.getTreatmentAssignments(this.mWeblabClientIdentifier));
        } catch (JSONException e) {
            throw new RuntimeException("Stored data is in an unexpected format or it is corrupted", e);
        }
    }
}
