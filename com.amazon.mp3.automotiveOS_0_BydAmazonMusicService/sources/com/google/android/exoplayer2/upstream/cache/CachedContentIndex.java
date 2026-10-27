package com.google.android.exoplayer2.upstream.cache;

import android.util.SparseArray;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes6.dex */
class CachedContentIndex {
    private final AtomicFile atomicFile;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private boolean changed;
    private final Cipher cipher;
    private final boolean encrypt;
    private final SparseArray<String> idToKey;
    private final HashMap<String, CachedContent> keyToContent;
    private final SecretKeySpec secretKeySpec;

    public CachedContentIndex(File file, byte[] bArr, boolean z) {
        this.encrypt = z;
        if (bArr != null) {
            Assertions.checkArgument(bArr.length == 16);
            try {
                this.cipher = getCipher();
                this.secretKeySpec = new SecretKeySpec(bArr, "AES");
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                throw new IllegalStateException(e);
            }
        } else {
            this.cipher = null;
            this.secretKeySpec = null;
        }
        this.keyToContent = new HashMap<>();
        this.idToKey = new SparseArray<>();
        this.atomicFile = new AtomicFile(new File(file, "cached_content_index.exi"));
    }

    public void load() {
        Assertions.checkState(!this.changed);
        if (readFile()) {
            return;
        }
        this.atomicFile.delete();
        this.keyToContent.clear();
        this.idToKey.clear();
    }

    public void store() throws Throwable {
        if (this.changed) {
            writeFile();
            this.changed = false;
        }
    }

    public CachedContent add(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        return cachedContent == null ? addNew(str, -1L) : cachedContent;
    }

    public CachedContent get(String str) {
        return this.keyToContent.get(str);
    }

    public Collection<CachedContent> getAll() {
        return this.keyToContent.values();
    }

    public int assignIdForKey(String str) {
        return add(str).id;
    }

    public String getKeyForId(int i) {
        return this.idToKey.get(i);
    }

    public void removeEmpty(String str) {
        CachedContent cachedContentRemove = this.keyToContent.remove(str);
        if (cachedContentRemove != null) {
            Assertions.checkState(cachedContentRemove.isEmpty());
            this.idToKey.remove(cachedContentRemove.id);
            this.changed = true;
        }
    }

    public void removeEmpty() {
        LinkedList linkedList = new LinkedList();
        for (CachedContent cachedContent : this.keyToContent.values()) {
            if (cachedContent.isEmpty()) {
                linkedList.add(cachedContent.key);
            }
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            removeEmpty((String) it.next());
        }
    }

    public Set<String> getKeys() {
        return this.keyToContent.keySet();
    }

    public void setContentLength(String str, long j) {
        CachedContent cachedContent = get(str);
        if (cachedContent == null) {
            addNew(str, j);
        } else if (cachedContent.getLength() != j) {
            cachedContent.setLength(j);
            this.changed = true;
        }
    }

    public long getContentLength(String str) {
        CachedContent cachedContent = get(str);
        if (cachedContent == null) {
            return -1L;
        }
        return cachedContent.getLength();
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x009a: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:49:0x009a */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean readFile() throws java.lang.Throwable {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L89 java.io.FileNotFoundException -> La1
            com.google.android.exoplayer2.util.AtomicFile r3 = r8.atomicFile     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L89 java.io.FileNotFoundException -> La1
            java.io.InputStream r3 = r3.openRead()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L89 java.io.FileNotFoundException -> La1
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L89 java.io.FileNotFoundException -> La1
            java.io.DataInputStream r3 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L89 java.io.FileNotFoundException -> La1
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L89 java.io.FileNotFoundException -> La1
            int r1 = r3.readInt()     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            r4 = 1
            if (r1 == r4) goto L1d
            com.google.android.exoplayer2.util.Util.closeQuietly(r3)
            return r0
        L1d:
            int r1 = r3.readInt()     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            r1 = r1 & r4
            if (r1 == 0) goto L56
            javax.crypto.Cipher r1 = r8.cipher     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            if (r1 != 0) goto L2c
            com.google.android.exoplayer2.util.Util.closeQuietly(r3)
            return r0
        L2c:
            r1 = 16
            byte[] r1 = new byte[r1]     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            r3.readFully(r1)     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            javax.crypto.spec.IvParameterSpec r5 = new javax.crypto.spec.IvParameterSpec     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            r5.<init>(r1)     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            javax.crypto.Cipher r1 = r8.cipher     // Catch: java.security.InvalidAlgorithmParameterException -> L4d java.security.InvalidKeyException -> L4f java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            r6 = 2
            javax.crypto.spec.SecretKeySpec r7 = r8.secretKeySpec     // Catch: java.security.InvalidAlgorithmParameterException -> L4d java.security.InvalidKeyException -> L4f java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            r1.init(r6, r7, r5)     // Catch: java.security.InvalidAlgorithmParameterException -> L4d java.security.InvalidKeyException -> L4f java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            java.io.DataInputStream r1 = new java.io.DataInputStream     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            javax.crypto.CipherInputStream r5 = new javax.crypto.CipherInputStream     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            javax.crypto.Cipher r6 = r8.cipher     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            r5.<init>(r2, r6)     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            r1.<init>(r5)     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            goto L5d
        L4d:
            r1 = move-exception
            goto L50
        L4f:
            r1 = move-exception
        L50:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            r2.<init>(r1)     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            throw r2     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
        L56:
            javax.crypto.Cipher r1 = r8.cipher     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
            if (r1 == 0) goto L5c
            r8.changed = r4     // Catch: java.io.IOException -> L83 java.io.FileNotFoundException -> L85 java.lang.Throwable -> L99
        L5c:
            r1 = r3
        L5d:
            int r2 = r1.readInt()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L89 java.io.FileNotFoundException -> La1
            r3 = r0
            r5 = r3
        L63:
            if (r3 >= r2) goto L75
            com.google.android.exoplayer2.upstream.cache.CachedContent r6 = new com.google.android.exoplayer2.upstream.cache.CachedContent     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L89 java.io.FileNotFoundException -> La1
            r6.<init>(r1)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L89 java.io.FileNotFoundException -> La1
            r8.add(r6)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L89 java.io.FileNotFoundException -> La1
            int r6 = r6.headerHashCode()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L89 java.io.FileNotFoundException -> La1
            int r5 = r5 + r6
            int r3 = r3 + 1
            goto L63
        L75:
            int r2 = r1.readInt()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L89 java.io.FileNotFoundException -> La1
            if (r2 == r5) goto L7f
            com.google.android.exoplayer2.util.Util.closeQuietly(r1)
            return r0
        L7f:
            com.google.android.exoplayer2.util.Util.closeQuietly(r1)
            return r4
        L83:
            r1 = move-exception
            goto L8c
        L85:
            r1 = r3
            goto La1
        L87:
            r0 = move-exception
            goto L9b
        L89:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L8c:
            java.lang.String r2 = "CachedContentIndex"
            java.lang.String r4 = "Error reading cache content index file."
            android.util.Log.e(r2, r4, r1)     // Catch: java.lang.Throwable -> L99
            if (r3 == 0) goto L98
            com.google.android.exoplayer2.util.Util.closeQuietly(r3)
        L98:
            return r0
        L99:
            r0 = move-exception
            r1 = r3
        L9b:
            if (r1 == 0) goto La0
            com.google.android.exoplayer2.util.Util.closeQuietly(r1)
        La0:
            throw r0
        La1:
            if (r1 == 0) goto La6
            com.google.android.exoplayer2.util.Util.closeQuietly(r1)
        La6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CachedContentIndex.readFile():boolean");
    }

    private void writeFile() throws Throwable {
        DataOutputStream dataOutputStream = null;
        try {
            try {
                OutputStream outputStreamStartWrite = this.atomicFile.startWrite();
                ReusableBufferedOutputStream reusableBufferedOutputStream = this.bufferedOutputStream;
                if (reusableBufferedOutputStream == null) {
                    this.bufferedOutputStream = new ReusableBufferedOutputStream(outputStreamStartWrite);
                } else {
                    reusableBufferedOutputStream.reset(outputStreamStartWrite);
                }
                DataOutputStream dataOutputStream2 = new DataOutputStream(this.bufferedOutputStream);
                try {
                    dataOutputStream2.writeInt(1);
                    int iHeaderHashCode = 0;
                    boolean z = this.encrypt && this.cipher != null;
                    dataOutputStream2.writeInt(z ? 1 : 0);
                    if (z) {
                        byte[] bArr = new byte[16];
                        new Random().nextBytes(bArr);
                        dataOutputStream2.write(bArr);
                        try {
                            this.cipher.init(1, this.secretKeySpec, new IvParameterSpec(bArr));
                            dataOutputStream2.flush();
                            dataOutputStream2 = new DataOutputStream(new CipherOutputStream(this.bufferedOutputStream, this.cipher));
                        } catch (InvalidAlgorithmParameterException e) {
                            e = e;
                            throw new IllegalStateException(e);
                        } catch (InvalidKeyException e2) {
                            e = e2;
                            throw new IllegalStateException(e);
                        }
                    }
                    dataOutputStream2.writeInt(this.keyToContent.size());
                    for (CachedContent cachedContent : this.keyToContent.values()) {
                        cachedContent.writeToStream(dataOutputStream2);
                        iHeaderHashCode += cachedContent.headerHashCode();
                    }
                    dataOutputStream2.writeInt(iHeaderHashCode);
                    this.atomicFile.endWrite(dataOutputStream2);
                    Util.closeQuietly((Closeable) null);
                } catch (IOException e3) {
                    e = e3;
                    throw new Cache.CacheException(e);
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    Util.closeQuietly(dataOutputStream);
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void add(CachedContent cachedContent) {
        this.keyToContent.put(cachedContent.key, cachedContent);
        this.idToKey.put(cachedContent.id, cachedContent.key);
    }

    void addNew(CachedContent cachedContent) {
        add(cachedContent);
        this.changed = true;
    }

    private CachedContent addNew(String str, long j) {
        CachedContent cachedContent = new CachedContent(getNewId(this.idToKey), str, j);
        addNew(cachedContent);
        return cachedContent;
    }

    private static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (Util.SDK_INT == 18) {
            try {
                return Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
            } catch (Throwable unused) {
            }
        }
        return Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    public static int getNewId(SparseArray<String> sparseArray) {
        int size = sparseArray.size();
        int i = 0;
        int iKeyAt = size == 0 ? 0 : sparseArray.keyAt(size - 1) + 1;
        if (iKeyAt >= 0) {
            return iKeyAt;
        }
        while (i < size && i == sparseArray.keyAt(i)) {
            i++;
        }
        return i;
    }
}
