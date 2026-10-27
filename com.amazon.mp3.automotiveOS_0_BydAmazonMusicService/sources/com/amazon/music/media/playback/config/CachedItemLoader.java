package com.amazon.music.media.playback.config;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class CachedItemLoader<KEY, VALUE> implements ItemLoader<KEY, VALUE> {
    private final LruCache<KEY, VALUE> cache;
    private final ItemLoader<? super KEY, ? extends VALUE> sourceLoader;
    private final Object lock = new Object();
    private final Map<KEY, Integer> howManyAreWaiting = new HashMap();
    private final Map<KEY, Object> waitingResults = new HashMap();

    public CachedItemLoader(@NonNull ItemLoader<? super KEY, ? extends VALUE> itemLoader, int i) {
        this.sourceLoader = (ItemLoader) Validate.notNull(itemLoader);
        this.cache = new LruCache<>(i);
    }

    public ItemLoader<? super KEY, ? extends VALUE> getSource() {
        return this.sourceLoader;
    }

    @Override // com.amazon.music.media.playback.config.ItemLoader
    @Nullable
    public VALUE getIfCached(@NonNull KEY key) {
        synchronized (this.lock) {
            VALUE value = this.cache.get(key);
            return value != null ? value : this.sourceLoader.getIfCached(key);
        }
    }

    @Override // com.amazon.music.media.playback.config.ItemLoader
    @NonNull
    public VALUE load(@NonNull KEY key) throws IOException {
        synchronized (this.lock) {
            VALUE value = this.cache.get(key);
            if (value != null) {
                return value;
            }
            Integer num = this.howManyAreWaiting.get(key);
            boolean z = num == null;
            this.howManyAreWaiting.put(key, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
            if (z) {
                return selfLoadItem(key);
            }
            return waitForItem(key);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7, types: [VALUE, java.lang.Object] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private VALUE selfLoadItem(KEY r6) throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            com.amazon.music.media.playback.config.ItemLoader<? super KEY, ? extends VALUE> r1 = r5.sourceLoader     // Catch: java.lang.Throwable -> L3d java.lang.RuntimeException -> L3f java.io.IOException -> L49
            java.lang.Object r0 = r1.load(r6)     // Catch: java.lang.Throwable -> L3d java.lang.RuntimeException -> L3f java.io.IOException -> L49
            androidx.collection.LruCache<KEY, VALUE> r1 = r5.cache     // Catch: java.lang.Throwable -> L3d java.lang.RuntimeException -> L3f java.io.IOException -> L49
            r1.put(r6, r0)     // Catch: java.lang.Throwable -> L3d java.lang.RuntimeException -> L3f java.io.IOException -> L49
            java.lang.Object r1 = r5.lock
            monitor-enter(r1)
            java.util.Map<KEY, java.lang.Integer> r2 = r5.howManyAreWaiting     // Catch: java.lang.Throwable -> L3a
            java.lang.Object r2 = r2.get(r6)     // Catch: java.lang.Throwable -> L3a
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch: java.lang.Throwable -> L3a
            int r2 = r2.intValue()     // Catch: java.lang.Throwable -> L3a
            int r2 = r2 + (-1)
            if (r2 != 0) goto L25
            java.util.Map<KEY, java.lang.Integer> r2 = r5.howManyAreWaiting     // Catch: java.lang.Throwable -> L3a
            r2.remove(r6)     // Catch: java.lang.Throwable -> L3a
            goto L33
        L25:
            java.util.Map<KEY, java.lang.Object> r3 = r5.waitingResults     // Catch: java.lang.Throwable -> L3a
            r3.put(r6, r0)     // Catch: java.lang.Throwable -> L3a
            java.util.Map<KEY, java.lang.Integer> r3 = r5.howManyAreWaiting     // Catch: java.lang.Throwable -> L3a
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L3a
            r3.put(r6, r2)     // Catch: java.lang.Throwable -> L3a
        L33:
            java.lang.Object r6 = r5.lock     // Catch: java.lang.Throwable -> L3a
            r6.notifyAll()     // Catch: java.lang.Throwable -> L3a
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L3a
            return r0
        L3a:
            r6 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L3a
            throw r6
        L3d:
            r1 = move-exception
            goto L4b
        L3f:
            r1 = move-exception
            java.io.IOException r2 = new java.io.IOException     // Catch: java.lang.Throwable -> L3d
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L3d
            throw r2     // Catch: java.lang.Throwable -> L46
        L46:
            r1 = move-exception
            r0 = r2
            goto L4b
        L49:
            r0 = move-exception
            throw r0     // Catch: java.lang.Throwable -> L3d
        L4b:
            java.lang.Object r2 = r5.lock
            monitor-enter(r2)
            java.util.Map<KEY, java.lang.Integer> r3 = r5.howManyAreWaiting     // Catch: java.lang.Throwable -> L79
            java.lang.Object r3 = r3.get(r6)     // Catch: java.lang.Throwable -> L79
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch: java.lang.Throwable -> L79
            int r3 = r3.intValue()     // Catch: java.lang.Throwable -> L79
            int r3 = r3 + (-1)
            if (r3 != 0) goto L64
            java.util.Map<KEY, java.lang.Integer> r0 = r5.howManyAreWaiting     // Catch: java.lang.Throwable -> L79
            r0.remove(r6)     // Catch: java.lang.Throwable -> L79
            goto L72
        L64:
            java.util.Map<KEY, java.lang.Object> r4 = r5.waitingResults     // Catch: java.lang.Throwable -> L79
            r4.put(r6, r0)     // Catch: java.lang.Throwable -> L79
            java.util.Map<KEY, java.lang.Integer> r0 = r5.howManyAreWaiting     // Catch: java.lang.Throwable -> L79
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L79
            r0.put(r6, r3)     // Catch: java.lang.Throwable -> L79
        L72:
            java.lang.Object r6 = r5.lock     // Catch: java.lang.Throwable -> L79
            r6.notifyAll()     // Catch: java.lang.Throwable -> L79
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L79
            throw r1
        L79:
            r6 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L79
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.playback.config.CachedItemLoader.selfLoadItem(java.lang.Object):java.lang.Object");
    }

    private VALUE waitForItem(KEY key) throws IOException {
        VALUE value;
        while (true) {
            synchronized (this.lock) {
                value = (VALUE) this.waitingResults.get(key);
                if (value != null) {
                    break;
                }
                try {
                    this.lock.wait();
                } catch (InterruptedException unused) {
                    value = (VALUE) new InterruptedIOException();
                }
            }
        }
        int iIntValue = this.howManyAreWaiting.get(key).intValue() - 1;
        if (iIntValue == 0) {
            this.howManyAreWaiting.remove(key);
            this.waitingResults.remove(key);
        } else {
            this.howManyAreWaiting.put(key, Integer.valueOf(iIntValue));
        }
        if (value instanceof IOException) {
            throw ((IOException) value);
        }
        return value;
    }
}
