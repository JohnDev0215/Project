package com.android.car.storagemonitoring;

import android.car.storagemonitoring.LifetimeWriteInfo;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes3.dex */
public class SysfsLifetimeWriteInfoProvider implements LifetimeWriteInfoProvider {
    private static final String DEFAULT_PATH = "/sys/fs/";
    private static final String FILENAME = "lifetime_write_kbytes";
    private final File mWriteInfosPath;
    private static final String TAG = SysfsLifetimeWriteInfoProvider.class.getSimpleName();
    private static final String[] KNOWN_FILESYSTEMS = {"ext4", "f2fs"};

    public SysfsLifetimeWriteInfoProvider() {
        this(new File(DEFAULT_PATH));
    }

    SysfsLifetimeWriteInfoProvider(File writeInfosPath) {
        this.mWriteInfosPath = writeInfosPath;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LifetimeWriteInfo tryParse(File dir) {
        File writefile = new File(dir, FILENAME);
        if (!writefile.exists() || !writefile.isFile()) {
            Log.d(TAG, writefile + " not a valid source of lifetime writes");
            return null;
        }
        try {
            List<String> datalines = Files.readAllLines(writefile.toPath());
            if (datalines == null || datalines.size() != 1) {
                Log.e(TAG, "unable to read valid write info from " + writefile);
                return null;
            }
            String data = datalines.get(0);
            try {
                long writtenBytes = Long.parseLong(data) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                if (writtenBytes < 0) {
                    Log.e(TAG, "file at location " + writefile + " contained a negative data amount " + data + ". Ignoring.");
                    return null;
                }
                return new LifetimeWriteInfo(dir.getName(), dir.getParentFile().getName(), writtenBytes);
            } catch (NumberFormatException e) {
                Log.e(TAG, "unable to read valid write info from " + writefile, e);
                return null;
            }
        } catch (IOException e2) {
            Log.e(TAG, "unable to read write info from " + writefile, e2);
            return null;
        }
    }

    @Override // com.android.car.storagemonitoring.LifetimeWriteInfoProvider
    public LifetimeWriteInfo[] load() {
        final List<LifetimeWriteInfo> writeInfos = new ArrayList<>();
        for (String fstype : KNOWN_FILESYSTEMS) {
            File fspath = new File(this.mWriteInfosPath, fstype);
            if (fspath.exists() && fspath.isDirectory()) {
                File[] files = fspath.listFiles(new FileFilter() { // from class: com.android.car.storagemonitoring.-$$Lambda$k1LMnpJLlrYtcSsQvSbPW-daMgg
                    @Override // java.io.FileFilter
                    public final boolean accept(File file) {
                        return file.isDirectory();
                    }
                });
                if (files == null) {
                    Log.e(TAG, "there are no directories at location " + fspath.getAbsolutePath());
                } else {
                    Stream streamFilter = Arrays.stream(files).map(new Function() { // from class: com.android.car.storagemonitoring.-$$Lambda$SysfsLifetimeWriteInfoProvider$nuOyF8hsWCsfSJ-JZlm84PkspOs
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return this.f$0.tryParse((File) obj);
                        }
                    }).filter(new Predicate() { // from class: com.android.car.storagemonitoring.-$$Lambda$aO5UVK-KB6f5le1IcqRmf5rrPZs
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return Objects.nonNull((LifetimeWriteInfo) obj);
                        }
                    });
                    Objects.requireNonNull(writeInfos);
                    streamFilter.forEach(new Consumer() { // from class: com.android.car.storagemonitoring.-$$Lambda$LstD_2z5GcY5aUyJTlDFH9mLppY
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            writeInfos.add((LifetimeWriteInfo) obj);
                        }
                    });
                }
            }
        }
        return (LifetimeWriteInfo[]) writeInfos.toArray(new LifetimeWriteInfo[0]);
    }
}
