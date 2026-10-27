package android.content.pm;

import android.os.Environment;
import android.os.FileUtils;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public class PackagePartitions {
    public static final int PARTITION_ODM = 2;
    public static final int PARTITION_OEM = 3;
    public static final int PARTITION_PRODUCT = 4;
    public static final int PARTITION_SYSTEM = 0;
    public static final int PARTITION_SYSTEM_EXT = 5;
    public static final int PARTITION_VENDOR = 1;
    private static final ArrayList<SystemPartition> SYSTEM_PARTITIONS;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PartitionType {
    }

    static {
        boolean z = true;
        boolean z2 = true;
        SYSTEM_PARTITIONS = new ArrayList<>(Arrays.asList(new SystemPartition(Environment.getRootDirectory(), 0, true, false), new SystemPartition(Environment.getVendorDirectory(), 1, true, true), new SystemPartition(Environment.getOdmDirectory(), 2, true, z), new SystemPartition(Environment.getOemDirectory(), 3, false, z), new SystemPartition(Environment.getProductDirectory(), 4, z2, z), new SystemPartition(Environment.getSystemExtDirectory(), 5, z2, z)));
    }

    public static <T> ArrayList<T> getOrderedPartitions(Function<SystemPartition, T> function) {
        ArrayList<T> arrayList = new ArrayList<>();
        int size = SYSTEM_PARTITIONS.size();
        for (int i = 0; i < size; i++) {
            T tApply = function.apply(SYSTEM_PARTITIONS.get(i));
            if (tApply != null) {
                arrayList.add(tApply);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File canonicalize(File file) {
        try {
            return file.getCanonicalFile();
        } catch (IOException unused) {
            return file;
        }
    }

    public static class SystemPartition {
        private final DeferredCanonicalFile mAppFolder;
        private final DeferredCanonicalFile mFolder;
        private final DeferredCanonicalFile mOverlayFolder;
        private final DeferredCanonicalFile mPrivAppFolder;
        public final int type;

        private SystemPartition(File file, int i, boolean z, boolean z2) {
            DeferredCanonicalFile deferredCanonicalFile;
            this.type = i;
            byte b = 0;
            byte b2 = 0;
            this.mFolder = new DeferredCanonicalFile(file);
            this.mAppFolder = new DeferredCanonicalFile(file, "app");
            if (z) {
                deferredCanonicalFile = new DeferredCanonicalFile(file, "priv-app");
            } else {
                deferredCanonicalFile = null;
            }
            this.mPrivAppFolder = deferredCanonicalFile;
            this.mOverlayFolder = z2 ? new DeferredCanonicalFile(file, "overlay") : null;
        }

        public SystemPartition(SystemPartition systemPartition) {
            this.type = systemPartition.type;
            this.mFolder = new DeferredCanonicalFile(systemPartition.mFolder.getFile());
            this.mAppFolder = systemPartition.mAppFolder;
            this.mPrivAppFolder = systemPartition.mPrivAppFolder;
            this.mOverlayFolder = systemPartition.mOverlayFolder;
        }

        public SystemPartition(File file, SystemPartition systemPartition) {
            this(file, systemPartition.type, systemPartition.mPrivAppFolder != null, systemPartition.mOverlayFolder != null);
        }

        public File getFolder() {
            return this.mFolder.getFile();
        }

        public File getAppFolder() {
            DeferredCanonicalFile deferredCanonicalFile = this.mAppFolder;
            if (deferredCanonicalFile == null) {
                return null;
            }
            return deferredCanonicalFile.getFile();
        }

        public File getPrivAppFolder() {
            DeferredCanonicalFile deferredCanonicalFile = this.mPrivAppFolder;
            if (deferredCanonicalFile == null) {
                return null;
            }
            return deferredCanonicalFile.getFile();
        }

        public File getOverlayFolder() {
            DeferredCanonicalFile deferredCanonicalFile = this.mOverlayFolder;
            if (deferredCanonicalFile == null) {
                return null;
            }
            return deferredCanonicalFile.getFile();
        }

        public boolean containsPath(String str) {
            return containsFile(new File(str));
        }

        public boolean containsFile(File file) {
            return FileUtils.contains(this.mFolder.getFile(), PackagePartitions.canonicalize(file));
        }

        public boolean containsPrivApp(File file) {
            DeferredCanonicalFile deferredCanonicalFile = this.mPrivAppFolder;
            return deferredCanonicalFile != null && FileUtils.contains(deferredCanonicalFile.getFile(), PackagePartitions.canonicalize(file));
        }

        public boolean containsApp(File file) {
            DeferredCanonicalFile deferredCanonicalFile = this.mAppFolder;
            return deferredCanonicalFile != null && FileUtils.contains(deferredCanonicalFile.getFile(), PackagePartitions.canonicalize(file));
        }

        public boolean containsOverlay(File file) {
            DeferredCanonicalFile deferredCanonicalFile = this.mOverlayFolder;
            return deferredCanonicalFile != null && FileUtils.contains(deferredCanonicalFile.getFile(), PackagePartitions.canonicalize(file));
        }
    }

    private static class DeferredCanonicalFile {
        private File mFile;
        private boolean mIsCanonical;

        private DeferredCanonicalFile(File file) {
            this.mIsCanonical = false;
            this.mFile = file;
        }

        private DeferredCanonicalFile(File file, String str) {
            this.mIsCanonical = false;
            this.mFile = new File(file, str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File getFile() {
            if (!this.mIsCanonical) {
                this.mFile = PackagePartitions.canonicalize(this.mFile);
                this.mIsCanonical = true;
            }
            return this.mFile;
        }
    }
}
