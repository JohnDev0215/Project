package com.amazon.mp3.library.db;

import android.database.ContentObserver;
import android.database.Cursor;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Handler;
import com.amazon.mp3.AmazonApplication;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes3.dex */
public class MultiWindowedCursor extends VirtualizedCursor {
    private static final String TAG = MultiWindowedCursor.class.getSimpleName();
    private final AtomicBoolean mAllRowsCached;
    private final int mColumnNum;
    private final int[] mColumnTypes;
    private ContentObserver mContentObserver;
    private int mCount;
    private final Cursor mCursor;
    private final ReentrantLock mCursorLock;
    private final Thread mFillThread;
    private final Handler mHandler;
    private final ContentObserver mInternalContentObserver;
    private CursorWindow mOnDemandWindow;
    private CursorWindow mOnDemandWindowSwap;
    private final List<CursorWindow> mWindows;

    @Override // com.amazon.mp3.library.db.VirtualizedCursor
    public boolean rowCached(int position) {
        return true;
    }

    public MultiWindowedCursor(Cursor cursor) {
        WindowFillThread windowFillThread = new WindowFillThread();
        this.mFillThread = windowFillThread;
        this.mCursorLock = new ReentrantLock(true);
        this.mAllRowsCached = new AtomicBoolean(false);
        this.mHandler = AmazonApplication.getDefaultHandler();
        this.mWindows = new ArrayList();
        ContentObserver contentObserver = new ContentObserver(AmazonApplication.getBackgroundHandler()) { // from class: com.amazon.mp3.library.db.MultiWindowedCursor.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                if (MultiWindowedCursor.this.mContentObserver != null) {
                    MultiWindowedCursor.this.mContentObserver.onChange(selfChange);
                }
                MultiWindowedCursor.this.killThread(false);
            }

            @Override // android.database.ContentObserver
            public void onChange(final boolean selfChange, final Uri uri) {
                if (MultiWindowedCursor.this.mContentObserver != null) {
                    MultiWindowedCursor.this.mContentObserver.onChange(selfChange, uri);
                }
                MultiWindowedCursor.this.killThread(false);
            }
        };
        this.mInternalContentObserver = contentObserver;
        this.mCursor = cursor;
        cursor.registerContentObserver(contentObserver);
        this.mCount = cursor.getCount();
        cursor.moveToFirst();
        int columnCount = cursor.getColumnCount();
        this.mColumnNum = columnCount;
        this.mColumnTypes = new int[columnCount];
        CursorWindow cursorWindow = new CursorWindow(true);
        this.mOnDemandWindow = cursorWindow;
        cursorWindow.acquireReference();
        CursorWindow cursorWindow2 = new CursorWindow(true);
        this.mOnDemandWindowSwap = cursorWindow2;
        cursorWindow2.acquireReference();
        this.mOnDemandWindow.acquireReference();
        fillWindowFromCursor(this.mOnDemandWindow, 100, cursor, 0);
        setWindow(this.mOnDemandWindow);
        windowFillThread.start();
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getCount() {
        return this.mCount;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public String[] getColumnNames() {
        return this.mCursor.getColumnNames();
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getColumnIndex(String columnName) {
        return this.mCursor.getColumnIndex(columnName);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void deactivate() {
        killThread(false);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        killThread(true);
        super.close();
        setWindow(null);
        synchronized (this.mWindows) {
            Iterator<CursorWindow> it = this.mWindows.iterator();
            while (it.hasNext()) {
                it.next().close();
            }
            this.mWindows.clear();
        }
        this.mOnDemandWindow.close();
        this.mOnDemandWindowSwap.close();
        this.mCursor.unregisterContentObserver(this.mInternalContentObserver);
        this.mCursor.close();
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void registerContentObserver(ContentObserver observer) {
        this.mContentObserver = observer;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void unregisterContentObserver(ContentObserver observer) {
        this.mContentObserver = null;
    }

    @Override // android.database.AbstractCursor, android.database.CrossProcessCursor
    public void fillWindow(int position, CursorWindow window) {
        fillWindowFromCursor(window, this.mCursor, position);
    }

    @Override // android.database.AbstractCursor, android.database.CrossProcessCursor
    public boolean onMove(int oldPosition, int newPosition) {
        if (windowContains(getWindow(), newPosition)) {
            return true;
        }
        CursorWindow cursorWindow = null;
        synchronized (this.mWindows) {
            for (CursorWindow cursorWindow2 : this.mWindows) {
                if (windowContains(cursorWindow2, newPosition)) {
                    cursorWindow = cursorWindow2;
                }
            }
        }
        if (cursorWindow != null && windowContains(cursorWindow, newPosition)) {
            cursorWindow.acquireReference();
            setWindow(cursorWindow);
        } else {
            this.mOnDemandWindowSwap.clear();
            this.mOnDemandWindowSwap.acquireReference();
            int iMax = Math.max(0, newPosition - 50);
            this.mCursorLock.lock();
            try {
                fillWindowFromCursor(this.mOnDemandWindowSwap, 100, this.mCursor, iMax);
                this.mCursorLock.unlock();
                CursorWindow cursorWindow3 = this.mOnDemandWindow;
                CursorWindow cursorWindow4 = this.mOnDemandWindowSwap;
                this.mOnDemandWindow = cursorWindow4;
                this.mOnDemandWindowSwap = cursorWindow3;
                setWindow(cursorWindow4);
            } catch (Throwable th) {
                this.mCursorLock.unlock();
                throw th;
            }
        }
        return true;
    }

    @Override // com.amazon.mp3.library.db.VirtualizedCursor
    public boolean allRowsCached() {
        return this.mAllRowsCached.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void killThread(boolean wait) {
        this.mFillThread.interrupt();
        if (wait) {
            try {
                this.mFillThread.join();
            } catch (InterruptedException unused) {
            }
        }
    }

    private boolean windowContains(CursorWindow window, int position) {
        return window != null && position >= window.getStartPosition() && position < window.getStartPosition() + window.getNumRows();
    }

    private class WindowFillThread extends Thread {
        WindowFillThread() {
            super(WindowFillThread.class.getSimpleName());
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            MultiWindowedCursor multiWindowedCursor = MultiWindowedCursor.this;
            multiWindowedCursor.mCount = multiWindowedCursor.mCursor.getCount();
            int i = 0;
            while (true) {
                if (isInterrupted() || i >= MultiWindowedCursor.this.mCount) {
                    break;
                }
                CursorWindow cursorWindow = new CursorWindow(true);
                MultiWindowedCursor multiWindowedCursor2 = MultiWindowedCursor.this;
                multiWindowedCursor2.fillWindowFromCursor(cursorWindow, multiWindowedCursor2.mCursor, i);
                int numRows = cursorWindow.getNumRows();
                if (numRows > 0) {
                    synchronized (MultiWindowedCursor.this.mWindows) {
                        MultiWindowedCursor.this.mWindows.add(cursorWindow);
                    }
                    i += numRows;
                } else {
                    cursorWindow.close();
                }
            }
            if (i >= MultiWindowedCursor.this.mCount) {
                MultiWindowedCursor.this.mAllRowsCached.set(true);
                MultiWindowedCursor.this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.library.db.MultiWindowedCursor.WindowFillThread.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MultiWindowedCursor.this.onAllRowsCached();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean fillWindowFromCursor(CursorWindow window, Cursor cursor, int startPosition) {
        return fillWindowFromCursor(window, 0, cursor, startPosition);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x00d0, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean fillWindowFromCursor(final android.database.CursorWindow r9, final int r10, final android.database.Cursor r11, final int r12) {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.db.MultiWindowedCursor.fillWindowFromCursor(android.database.CursorWindow, int, android.database.Cursor, int):boolean");
    }

    private static boolean isWindowOutOfMemory(CursorWindow window, int failedColumnType) {
        if (failedColumnType == 0 || failedColumnType == 1 || failedColumnType == 2) {
            return false;
        }
        if (!window.allocRow()) {
            return true;
        }
        window.freeLastRow();
        return false;
    }
}
