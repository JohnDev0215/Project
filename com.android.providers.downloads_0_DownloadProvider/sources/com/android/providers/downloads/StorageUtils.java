package com.android.providers.downloads;

import android.system.ErrnoException;
import android.system.Os;
import android.system.StructStat;
import com.google.android.collect.Lists;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class StorageUtils {
    static List<ConcreteFile> listFilesRecursive(File file, String str, int i) {
        File[] fileArrListFiles;
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        LinkedList linkedList = new LinkedList();
        linkedList.add(file);
        while (!linkedList.isEmpty()) {
            File file2 = (File) linkedList.removeFirst();
            if (!Objects.equals(file2.getName(), str) && (fileArrListFiles = file2.listFiles()) != null) {
                for (File file3 : fileArrListFiles) {
                    if (file3.isDirectory()) {
                        linkedList.add(file3);
                    } else if (file3.isFile()) {
                        try {
                            ConcreteFile concreteFile = new ConcreteFile(file3);
                            if (i == -1 || concreteFile.stat.st_uid == i) {
                                arrayListNewArrayList.add(concreteFile);
                            }
                        } catch (ErrnoException unused) {
                        }
                    }
                }
            }
        }
        return arrayListNewArrayList;
    }

    static class ConcreteFile {
        public final File file;
        public final StructStat stat;

        public ConcreteFile(File file) throws ErrnoException {
            this.file = file;
            this.stat = Os.lstat(file.getAbsolutePath());
        }

        public int hashCode() {
            StructStat structStat = this.stat;
            long j = structStat.st_dev;
            int i = (((int) (j ^ (j >>> 32))) + 31) * 31;
            long j2 = structStat.st_ino;
            return i + ((int) ((j2 >>> 32) ^ j2));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ConcreteFile)) {
                return false;
            }
            StructStat structStat = ((ConcreteFile) obj).stat;
            long j = structStat.st_dev;
            StructStat structStat2 = this.stat;
            return j == structStat2.st_dev && structStat.st_ino == structStat2.st_ino;
        }
    }
}
