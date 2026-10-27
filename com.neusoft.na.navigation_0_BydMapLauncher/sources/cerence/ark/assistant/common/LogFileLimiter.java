package cerence.ark.assistant.common;

import android.text.TextUtils;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class LogFileLimiter {
    private static final String TAG = "LogFileLimiter";
    private final Pattern mFilenamePattern;
    private final String mLogDir;
    private final long mMaximumSize;
    private long mCurrentSize = 0;
    private final LinkedList<LogFile> mLogFileList = new LinkedList<>();

    public LogFileLimiter(String str, Pattern pattern, long j) {
        this.mLogDir = str;
        this.mFilenamePattern = pattern;
        this.mMaximumSize = j;
    }

    static class FileComparator implements Comparator<LogFile> {
        FileComparator() {
        }

        @Override // java.util.Comparator
        public int compare(LogFile logFile, LogFile logFile2) {
            return Long.compare(logFile.getFile().lastModified(), logFile2.getFile().lastModified());
        }
    }

    static class LogFile {
        private final File mFile;
        private final long mSize;

        LogFile(File file, long j) {
            this.mFile = file;
            this.mSize = j;
        }

        File getFile() {
            return this.mFile;
        }

        long getSize() {
            return this.mSize;
        }
    }

    public synchronized void scanLogFiles() {
        if (this.mMaximumSize < 0) {
            Logger.warn(TAG, "Maximum size value is negative, ignore scanning.");
            return;
        }
        if (TextUtils.isEmpty(this.mLogDir)) {
            Logger.error(TAG, "Log dir: log dir is empty");
            return;
        }
        File file = new File(this.mLogDir);
        if (!file.exists()) {
            Logger.error(TAG, "Log dir:" + this.mLogDir + " does not exist");
            return;
        }
        if (!file.isDirectory()) {
            Logger.error(TAG, "Log dir:" + this.mLogDir + " is not directory");
            return;
        }
        this.mCurrentSize = 0L;
        this.mLogFileList.clear();
        searchLogFiles(file);
        Collections.sort(this.mLogFileList, new FileComparator());
        Logger.debug(TAG, "Found " + this.mLogFileList.size() + " log files, total size is " + this.mCurrentSize);
        deleteFilesIfNeeded();
    }

    private void searchLogFiles(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isFile()) {
                String name = file2.getName();
                Pattern pattern = this.mFilenamePattern;
                if (pattern == null || pattern.matcher(name).matches()) {
                    long length = file2.length();
                    this.mLogFileList.add(new LogFile(file2, length));
                    this.mCurrentSize += length;
                }
            } else if (file2.isDirectory()) {
                searchLogFiles(file2);
            } else {
                Logger.warn(TAG, "File " + file2.getAbsolutePath() + " is neither file nor directory");
            }
        }
    }

    public synchronized void addNewFile(File file) {
        if (this.mMaximumSize >= 0 && file != null) {
            String name = file.getName();
            Pattern pattern = this.mFilenamePattern;
            boolean z = pattern == null || pattern.matcher(name).matches();
            if (file.isFile() && z) {
                long length = file.length();
                this.mLogFileList.addLast(new LogFile(file, length));
                this.mCurrentSize += length;
            }
            deleteFilesIfNeeded();
        }
    }

    private void deleteFilesIfNeeded() {
        if (this.mMaximumSize < 0) {
            return;
        }
        while (true) {
            if (this.mCurrentSize <= this.mMaximumSize || this.mLogFileList.isEmpty()) {
                break;
            }
            LogFile first = this.mLogFileList.getFirst();
            File file = first.getFile();
            if (file.exists()) {
                if (file.delete()) {
                    this.mLogFileList.removeFirst();
                    this.mCurrentSize -= first.getSize();
                } else {
                    Logger.error(TAG, "trying to delete file " + file.getAbsolutePath() + "failed.");
                    break;
                }
            } else {
                Logger.warn(TAG, "log file " + file.getAbsolutePath() + " does not exist");
                this.mLogFileList.removeFirst();
                this.mCurrentSize -= first.getSize();
            }
        }
        if (this.mLogFileList.isEmpty()) {
            this.mCurrentSize = 0L;
        }
    }
}
