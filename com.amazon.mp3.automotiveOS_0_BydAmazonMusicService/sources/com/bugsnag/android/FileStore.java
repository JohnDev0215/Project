package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.destination.parser.ParserUtil;
import com.bugsnag.android.JsonStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes5.dex */
abstract class FileStore {
    private final Comparator<File> comparator;
    private final Delegate delegate;
    protected final Logger logger;
    private final int maxStoreCount;
    private final File storageDir;
    private final Lock lock = new ReentrantLock();
    private final Collection<File> queuedFiles = new ConcurrentSkipListSet();

    interface Delegate {
        void onErrorIOFailure(Exception exc, File file, String str);
    }

    @NonNull
    abstract String getFilename(Object obj);

    FileStore(@NonNull File file, int i, Comparator<File> comparator, Logger logger, Delegate delegate) {
        this.maxStoreCount = i;
        this.comparator = comparator;
        this.logger = logger;
        this.delegate = delegate;
        this.storageDir = file;
        isStorageDirValid(file);
    }

    private boolean isStorageDirValid(@NonNull File file) {
        try {
            file.mkdirs();
            return true;
        } catch (Exception e) {
            this.logger.e("Could not prepare file storage directory", e);
            return false;
        }
    }

    void enqueueContentForDelivery(String str, String str2) throws Throwable {
        Logger logger;
        StringBuilder sb;
        if (!isStorageDirValid(this.storageDir)) {
            return;
        }
        discardOldestFileIfNeeded();
        this.lock.lock();
        BufferedWriter bufferedWriter = null;
        String absolutePath = new File(this.storageDir, str2).getAbsolutePath();
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absolutePath), ParserUtil.UTF_8));
                try {
                    bufferedWriter2.write(str);
                    try {
                        bufferedWriter2.close();
                    } catch (Exception e) {
                        e = e;
                        logger = this.logger;
                        sb = new StringBuilder();
                        sb.append("Failed to close unsent payload writer: ");
                        sb.append(str2);
                        logger.w(sb.toString(), e);
                    }
                } catch (Exception e2) {
                    e = e2;
                    bufferedWriter = bufferedWriter2;
                    File file = new File(absolutePath);
                    Delegate delegate = this.delegate;
                    if (delegate != null) {
                        delegate.onErrorIOFailure(e, file, "NDK Crash report copy");
                    }
                    IOUtils.deleteFile(file, this.logger);
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Exception e3) {
                            e = e3;
                            logger = this.logger;
                            sb = new StringBuilder();
                            sb.append("Failed to close unsent payload writer: ");
                            sb.append(str2);
                            logger.w(sb.toString(), e);
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Exception e4) {
                            this.logger.w("Failed to close unsent payload writer: " + str2, e4);
                        }
                    }
                    this.lock.unlock();
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
            this.lock.unlock();
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v2 */
    @Nullable
    String write(@NonNull JsonStream.Streamable streamable) throws Throwable {
        JsonStream jsonStream;
        ?? r1 = 0;
        if (!isStorageDirValid(this.storageDir) || this.maxStoreCount == 0) {
            return null;
        }
        discardOldestFileIfNeeded();
        String absolutePath = new File(this.storageDir, getFilename(streamable)).getAbsolutePath();
        Lock lock = this.lock;
        lock.lock();
        try {
            try {
                jsonStream = new JsonStream(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absolutePath), ParserUtil.UTF_8)));
            } catch (FileNotFoundException e) {
                e = e;
                jsonStream = null;
            } catch (Exception e2) {
                e = e2;
                jsonStream = null;
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(r1);
                this.lock.unlock();
                throw th;
            }
            try {
                jsonStream.value(streamable);
                this.logger.i("Saved unsent payload to disk: '" + absolutePath + '\'');
                IOUtils.closeQuietly(jsonStream);
                this.lock.unlock();
                return absolutePath;
            } catch (FileNotFoundException e3) {
                e = e3;
                this.logger.w("Ignoring FileNotFoundException - unable to create file", e);
                IOUtils.closeQuietly(jsonStream);
                this.lock.unlock();
                return null;
            } catch (Exception e4) {
                e = e4;
                File file = new File(absolutePath);
                Delegate delegate = this.delegate;
                if (delegate != null) {
                    delegate.onErrorIOFailure(e, file, "Crash report serialization");
                }
                IOUtils.deleteFile(file, this.logger);
                IOUtils.closeQuietly(jsonStream);
                this.lock.unlock();
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            r1 = lock;
            IOUtils.closeQuietly(r1);
            this.lock.unlock();
            throw th;
        }
    }

    void discardOldestFileIfNeeded() {
        File[] fileArrListFiles;
        if (!isStorageDirValid(this.storageDir) || (fileArrListFiles = this.storageDir.listFiles()) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(fileArrListFiles));
        if (arrayList.size() >= this.maxStoreCount) {
            Collections.sort(arrayList, this.comparator);
            int i = 0;
            while (i < arrayList.size() && arrayList.size() >= this.maxStoreCount) {
                File file = (File) arrayList.get(i);
                if (!this.queuedFiles.contains(file)) {
                    this.logger.w("Discarding oldest error as stored error limit reached: '" + file.getPath() + '\'');
                    deleteStoredFiles(Collections.singleton(file));
                    arrayList.remove(i);
                    i += -1;
                }
                i++;
            }
        }
    }

    List<File> findStoredFiles() {
        File[] fileArrListFiles;
        this.lock.lock();
        try {
            ArrayList arrayList = new ArrayList();
            if (isStorageDirValid(this.storageDir) && (fileArrListFiles = this.storageDir.listFiles()) != null) {
                for (File file : fileArrListFiles) {
                    if (file.length() == 0) {
                        if (!file.delete()) {
                            file.deleteOnExit();
                        }
                    } else if (file.isFile() && !this.queuedFiles.contains(file)) {
                        arrayList.add(file);
                    }
                }
            }
            this.queuedFiles.addAll(arrayList);
            return arrayList;
        } finally {
            this.lock.unlock();
        }
    }

    void cancelQueuedFiles(Collection<File> collection) {
        this.lock.lock();
        if (collection != null) {
            try {
                this.queuedFiles.removeAll(collection);
            } finally {
                this.lock.unlock();
            }
        }
    }

    void deleteStoredFiles(Collection<File> collection) {
        this.lock.lock();
        if (collection != null) {
            try {
                this.queuedFiles.removeAll(collection);
                for (File file : collection) {
                    if (!file.delete()) {
                        file.deleteOnExit();
                    }
                }
            } finally {
                this.lock.unlock();
            }
        }
    }
}
