package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes6.dex */
final class CachedContent {
    private final TreeSet<SimpleCacheSpan> cachedSpans;
    public final int id;
    public final String key;
    private long length;

    public CachedContent(DataInputStream dataInputStream) throws IOException {
        this(dataInputStream.readInt(), dataInputStream.readUTF(), dataInputStream.readLong());
    }

    public CachedContent(int i, String str, long j) {
        this.id = i;
        this.key = str;
        this.length = j;
        this.cachedSpans = new TreeSet<>();
    }

    public void writeToStream(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.id);
        dataOutputStream.writeUTF(this.key);
        dataOutputStream.writeLong(this.length);
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long j) {
        this.length = j;
    }

    public void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.cachedSpans.add(simpleCacheSpan);
    }

    public TreeSet<SimpleCacheSpan> getSpans() {
        return this.cachedSpans;
    }

    public SimpleCacheSpan getSpan(long j) {
        SimpleCacheSpan simpleCacheSpanCreateLookup = SimpleCacheSpan.createLookup(this.key, j);
        SimpleCacheSpan simpleCacheSpanFloor = this.cachedSpans.floor(simpleCacheSpanCreateLookup);
        if (simpleCacheSpanFloor != null && simpleCacheSpanFloor.position + simpleCacheSpanFloor.length > j) {
            return simpleCacheSpanFloor;
        }
        SimpleCacheSpan simpleCacheSpanCeiling = this.cachedSpans.ceiling(simpleCacheSpanCreateLookup);
        if (simpleCacheSpanCeiling == null) {
            return SimpleCacheSpan.createOpenHole(this.key, j);
        }
        return SimpleCacheSpan.createClosedHole(this.key, j, simpleCacheSpanCeiling.position - j);
    }

    public long getCachedBytes(long j, long j2) {
        SimpleCacheSpan span = getSpan(j);
        if (span.isHoleSpan()) {
            return -Math.min(span.isOpenEnded() ? Long.MAX_VALUE : span.length, j2);
        }
        long j3 = j + j2;
        long jMax = span.position + span.length;
        if (jMax < j3) {
            for (SimpleCacheSpan simpleCacheSpan : this.cachedSpans.tailSet(span, false)) {
                long j4 = simpleCacheSpan.position;
                if (j4 > jMax) {
                    break;
                }
                jMax = Math.max(jMax, j4 + simpleCacheSpan.length);
                if (jMax >= j3) {
                    break;
                }
            }
        }
        return Math.min(jMax - j, j2);
    }

    public SimpleCacheSpan touch(SimpleCacheSpan simpleCacheSpan) throws Cache.CacheException {
        Assertions.checkState(this.cachedSpans.remove(simpleCacheSpan));
        SimpleCacheSpan simpleCacheSpanCopyWithUpdatedLastAccessTime = simpleCacheSpan.copyWithUpdatedLastAccessTime(this.id);
        if (!simpleCacheSpan.file.renameTo(simpleCacheSpanCopyWithUpdatedLastAccessTime.file)) {
            throw new Cache.CacheException("Renaming of " + simpleCacheSpan.file + " to " + simpleCacheSpanCopyWithUpdatedLastAccessTime.file + " failed.");
        }
        this.cachedSpans.add(simpleCacheSpanCopyWithUpdatedLastAccessTime);
        return simpleCacheSpanCopyWithUpdatedLastAccessTime;
    }

    public boolean isEmpty() {
        return this.cachedSpans.isEmpty();
    }

    public boolean removeSpan(CacheSpan cacheSpan) {
        if (!this.cachedSpans.remove(cacheSpan)) {
            return false;
        }
        cacheSpan.file.delete();
        return true;
    }

    public int headerHashCode() {
        int iHashCode = ((this.id * 31) + this.key.hashCode()) * 31;
        long j = this.length;
        return iHashCode + ((int) (j ^ (j >>> 32)));
    }
}
