package com.amazon.music.signin;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class SignInTaskCache {
    private final Context context;
    private final Lock readLock;
    private final Lock writeLock;

    public SignInTaskCache(@Nonnull Context context) {
        this.context = (Context) Validate.notNull(context);
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.writeLock = reentrantReadWriteLock.writeLock();
        this.readLock = reentrantReadWriteLock.readLock();
    }

    public synchronized void saveSignInEvent() {
        try {
            this.writeLock.lock();
            SharedPreferences.Editor editorEdit = this.context.getSharedPreferences("SignInTaskCache", 0).edit();
            editorEdit.putBoolean("signedIn", true);
            editorEdit.apply();
        } finally {
            this.writeLock.unlock();
        }
    }

    public synchronized boolean isSignedIn() {
        try {
            this.readLock.lock();
        } finally {
            this.readLock.unlock();
        }
        return this.context.getSharedPreferences("SignInTaskCache", 0).getBoolean("signedIn", false);
    }

    public synchronized void saveHasSeenMusicTOUEvent() {
        try {
            this.writeLock.lock();
            SharedPreferences.Editor editorEdit = this.context.getSharedPreferences("SignInTaskCache", 0).edit();
            editorEdit.putBoolean("hasSeenMusicTOU", true);
            editorEdit.apply();
        } finally {
            this.writeLock.unlock();
        }
    }

    public synchronized boolean hasSeenMusicTOU() {
        try {
            this.readLock.lock();
        } finally {
            this.readLock.unlock();
        }
        return this.context.getSharedPreferences("SignInTaskCache", 0).getBoolean("hasSeenMusicTOU", false);
    }

    public synchronized void saveIsUserSSO(boolean z) {
        try {
            this.writeLock.lock();
            SharedPreferences.Editor editorEdit = this.context.getSharedPreferences("SignInTaskCache", 0).edit();
            editorEdit.putBoolean("isUserSSO", z);
            editorEdit.apply();
        } finally {
            this.writeLock.unlock();
        }
    }

    public synchronized boolean isUserSSO() {
        try {
            this.readLock.lock();
        } finally {
            this.readLock.unlock();
        }
        return this.context.getSharedPreferences("SignInTaskCache", 0).getBoolean("isUserSSO", false);
    }

    public synchronized void saveSignOutEvent() {
        try {
            this.writeLock.lock();
            SharedPreferences.Editor editorEdit = this.context.getSharedPreferences("SignInTaskCache", 0).edit();
            editorEdit.remove("signedIn");
            editorEdit.apply();
        } finally {
            this.writeLock.unlock();
        }
    }

    synchronized void cacheLastExecutedTask(@Nonnull String str) {
        try {
            this.writeLock.lock();
            SharedPreferences.Editor editorEdit = this.context.getSharedPreferences("SignInTaskCache", 0).edit();
            editorEdit.putString("lastSignInTaskExecuted", str);
            editorEdit.apply();
        } finally {
            this.writeLock.unlock();
        }
    }

    synchronized boolean atLeastOneTaskExecuted() {
        return getLastExecutedTaskName() != null;
    }

    public synchronized boolean isLastExecutedTask(@Nonnull String str) {
        return str.equals(getLastExecutedTaskName());
    }

    public synchronized String getLastExecutedTaskName() {
        try {
            this.readLock.lock();
        } finally {
            this.readLock.unlock();
        }
        return this.context.getSharedPreferences("SignInTaskCache", 0).getString("lastSignInTaskExecuted", null);
    }

    public synchronized void clear() {
        try {
            this.writeLock.lock();
            SharedPreferences.Editor editorEdit = this.context.getSharedPreferences("SignInTaskCache", 0).edit();
            editorEdit.remove("lastSignInTaskExecuted");
            editorEdit.apply();
        } finally {
            this.writeLock.unlock();
        }
    }
}
