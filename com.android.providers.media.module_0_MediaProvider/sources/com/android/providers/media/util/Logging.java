package com.android.providers.media.util;

import android.os.SystemProperties;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes.dex */
public class Logging {
    public static final boolean IS_DEBUGGABLE;
    private static final long PERSISTENT_AGE = 604800000;
    private static final int PERSISTENT_COUNT = 4;
    private static final int PERSISTENT_SIZE = 32768;
    private static SimpleDateFormat sDateFormat;
    private static Path sPersistentDir;
    public static final String TAG = "MediaProvider";
    public static final boolean LOGW = Log.isLoggable(TAG, 5);
    public static final boolean LOGD = Log.isLoggable(TAG, 3);
    public static final boolean LOGV = Log.isLoggable(TAG, 2);

    static {
        IS_DEBUGGABLE = SystemProperties.getInt("ro.debuggable", 0) == 1;
        sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static void initPersistent(File file) {
        sPersistentDir = file.toPath();
    }

    public static void logPersistent(String str) {
        Log.i(TAG, str);
        if (sPersistentDir == null) {
            return;
        }
        try {
            BufferedWriter bufferedWriterNewBufferedWriter = Files.newBufferedWriter(resolveCurrentPersistentFile(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            try {
                bufferedWriterNewBufferedWriter.write(sDateFormat.format(new Date()) + " " + str + "\n");
                if (bufferedWriterNewBufferedWriter != null) {
                    bufferedWriterNewBufferedWriter.close();
                }
            } finally {
            }
        } catch (IOException e) {
            Log.w(TAG, "Failed to persist: " + e);
        }
    }

    public static void trimPersistent() {
        Path path = sPersistentDir;
        if (path == null) {
            return;
        }
        FileUtils.deleteOlderFiles(path.toFile(), 4, 604800000L);
    }

    public static void dumpPersistent(final PrintWriter printWriter) {
        Path path = sPersistentDir;
        if (path == null) {
            return;
        }
        try {
            Stream<Path> list = Files.list(path);
            try {
                list.sorted().forEach(new Consumer() { // from class: com.android.providers.media.util.-$$Lambda$Logging$z4FyA-gO6TOSC48nfRWrJeXA4DE
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        Logging.dumpPersistentFile((Path) obj, printWriter);
                    }
                });
                if (list != null) {
                    list.close();
                }
            } finally {
            }
        } catch (IOException e) {
            printWriter.println(e.getMessage());
            printWriter.println();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dumpPersistentFile(Path path, final PrintWriter printWriter) {
        printWriter.println("Persistent logs in " + path + ":");
        try {
            Stream<String> streamLines = Files.lines(path);
            try {
                streamLines.forEach(new Consumer() { // from class: com.android.providers.media.util.-$$Lambda$Logging$Afk78HmaNS1LQ7AiVW5ao9H80aU
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        printWriter.println("  " + ((String) obj));
                    }
                });
                printWriter.println();
                if (streamLines != null) {
                    streamLines.close();
                }
            } finally {
            }
        } catch (IOException e) {
            printWriter.println("  " + e.getMessage());
            printWriter.println();
        }
    }

    private static Path resolveCurrentPersistentFile() throws IOException {
        Stream<Path> list = Files.list(sPersistentDir);
        try {
            Optional<Path> optionalMax = list.max(Comparator.naturalOrder());
            if (optionalMax.isPresent() && optionalMax.get().toFile().length() < 32768) {
                Path path = optionalMax.get();
                if (list != null) {
                    list.close();
                }
                return path;
            }
            Path pathResolve = sPersistentDir.resolve(String.valueOf(System.currentTimeMillis()));
            if (list != null) {
                list.close();
            }
            return pathResolve;
        } catch (Throwable th) {
            if (list != null) {
                try {
                    list.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
