package com.bugsnag.android;

import android.util.JsonReader;
import com.bugsnag.android.JsonStream;
import com.bugsnag.android.JsonStream.Streamable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: SynchronizedStreamableStore.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\t\u001a\u00028\u00002\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/bugsnag/android/SynchronizedStreamableStore;", "T", "Lcom/bugsnag/android/JsonStream$Streamable;", "", "file", "Ljava/io/File;", "(Ljava/io/File;)V", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "load", "loadCallback", "Lkotlin/Function1;", "Landroid/util/JsonReader;", "(Lkotlin/jvm/functions/Function1;)Lcom/bugsnag/android/JsonStream$Streamable;", "persist", "", "streamable", "(Lcom/bugsnag/android/JsonStream$Streamable;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class SynchronizedStreamableStore<T extends JsonStream.Streamable> {
    private final File file;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public SynchronizedStreamableStore(File file) {
        this.file = file;
    }

    public final void persist(T streamable) throws IOException {
        ReentrantReadWriteLock.WriteLock writeLock = this.lock.writeLock();
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(writeLock, "lock.writeLock()");
        writeLock.lock();
        try {
            Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream(this.file), Charsets.UTF_8);
            BufferedWriter bufferedWriter = outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192);
            try {
                streamable.toStream(new JsonStream(bufferedWriter));
                CloseableKt.closeFinally(bufferedWriter, null);
            } finally {
            }
        } finally {
            writeLock.unlock();
        }
    }

    public final T load(Function1<? super JsonReader, ? extends T> loadCallback) throws IOException {
        ReentrantReadWriteLock.ReadLock lock = this.lock.readLock();
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(lock, "lock.readLock()");
        lock.lock();
        try {
            Reader inputStreamReader = new InputStreamReader(new FileInputStream(this.file), Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                T tInvoke = loadCallback.invoke(new JsonReader(bufferedReader));
                CloseableKt.closeFinally(bufferedReader, null);
                return tInvoke;
            } finally {
            }
        } finally {
            lock.unlock();
        }
    }
}
