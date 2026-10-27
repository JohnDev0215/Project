package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import p.n03;
import p.s31;
import p.v13;

/* JADX INFO: loaded from: classes.dex */
public final class d extends s31 {
    public final TreeMap c = new TreeMap();

    public d(File file, File file2) throws IOException {
        ArrayList<File> arrayListA = q.a(file, file2);
        if (arrayListA.isEmpty()) {
            throw new zzck(String.format("Virtualized slice archive empty for %s, %s", file, file2));
        }
        long length = 0;
        for (File file3 : arrayListA) {
            this.c.put(Long.valueOf(length), file3);
            length += file3.length();
        }
    }

    public final FileInputStream E(long j, Long l) {
        FileInputStream fileInputStream = new FileInputStream((File) this.c.get(l));
        if (fileInputStream.skip(j - l.longValue()) == j - l.longValue()) {
            return fileInputStream;
        }
        throw new zzck(String.format("Virtualized slice archive corrupt, could not skip in file with key %s", l));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // p.s31
    public final long i() {
        Map.Entry entryLastEntry = this.c.lastEntry();
        return ((File) entryLastEntry.getValue()).length() + ((Long) entryLastEntry.getKey()).longValue();
    }

    @Override // p.s31
    public final InputStream q(long j, long j2) {
        if (j < 0 || j2 < 0) {
            throw new zzck(String.format("Invalid input parameters %s, %s", Long.valueOf(j), Long.valueOf(j2)));
        }
        long j3 = j + j2;
        if (j3 > i()) {
            throw new zzck(String.format("Trying to access archive out of bounds. Archive ends at: %s. Tried accessing: %s", Long.valueOf(i()), Long.valueOf(j3)));
        }
        TreeMap treeMap = this.c;
        Long l = (Long) treeMap.floorKey(Long.valueOf(j));
        Long l2 = (Long) treeMap.floorKey(Long.valueOf(j3));
        if (l.equals(l2)) {
            return new n03(E(j, l), j2);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(E(j, l));
        Collection collectionValues = treeMap.subMap(l, false, l2, false).values();
        if (!collectionValues.isEmpty()) {
            arrayList.add(new v13(Collections.enumeration(collectionValues)));
        }
        arrayList.add(new n03(new FileInputStream((File) treeMap.get(l2)), j2 - (l2.longValue() - j)));
        return new SequenceInputStream(Collections.enumeration(arrayList));
    }
}
