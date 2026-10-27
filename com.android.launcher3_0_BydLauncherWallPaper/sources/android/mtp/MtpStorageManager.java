package android.mtp;

import android.media.MediaFile;
import android.os.FileObserver;
import android.os.storage.StorageVolume;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class MtpStorageManager {
    private static final int IN_IGNORED = 32768;
    private static final int IN_ISDIR = 1073741824;
    private static final int IN_ONLYDIR = 16777216;
    private static final int IN_Q_OVERFLOW = 16384;
    private static final String TAG = "MtpStorageManager";
    public static boolean sDebug = false;
    private MtpNotifier mMtpNotifier;
    private Set<String> mSubdirectories;
    private HashMap<Integer, MtpObject> mObjects = new HashMap<>();
    private HashMap<Integer, MtpObject> mRoots = new HashMap<>();
    private int mNextObjectId = 1;
    private int mNextStorageId = 1;
    private volatile boolean mCheckConsistency = false;
    private Thread mConsistencyThread = new Thread(new Runnable() { // from class: android.mtp.-$$Lambda$MtpStorageManager$46HwfrMQ0OcBrH6QyK_PpzurkWo
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$MtpStorageManager();
        }
    });

    public static abstract class MtpNotifier {
        public abstract void sendObjectAdded(int i);

        public abstract void sendObjectInfoChanged(int i);

        public abstract void sendObjectRemoved(int i);
    }

    private enum MtpObjectState {
        NORMAL,
        FROZEN,
        FROZEN_ADDED,
        FROZEN_REMOVED,
        FROZEN_ONESHOT_ADD,
        FROZEN_ONESHOT_DEL
    }

    private enum MtpOperation {
        NONE,
        ADD,
        RENAME,
        COPY,
        DELETE
    }

    private class MtpObjectObserver extends FileObserver {
        MtpObject mObject;

        @Override // android.os.FileObserver
        public void finalize() {
        }

        MtpObjectObserver(MtpObject mtpObject) {
            super(mtpObject.getPath().toString(), 16778184);
            this.mObject = mtpObject;
        }

        @Override // android.os.FileObserver
        public void onEvent(int i, String str) {
            synchronized (MtpStorageManager.this) {
                if ((i & 16384) != 0) {
                    Log.e(MtpStorageManager.TAG, "Received Inotify overflow event!");
                }
                MtpObject child = this.mObject.getChild(str);
                if ((i & 128) != 0 || (i & 256) != 0) {
                    if (MtpStorageManager.sDebug) {
                        Log.i(MtpStorageManager.TAG, "Got inotify added event for " + str + " " + i);
                    }
                    MtpStorageManager.this.handleAddedObject(this.mObject, str, (i & 1073741824) != 0);
                } else if ((i & 64) == 0 && (i & 512) == 0) {
                    if ((32768 & i) != 0) {
                        if (MtpStorageManager.sDebug) {
                            Log.i(MtpStorageManager.TAG, "inotify for " + this.mObject.getPath() + " deleted");
                        }
                        if (this.mObject.mObserver != null) {
                            this.mObject.mObserver.stopWatching();
                        }
                        this.mObject.mObserver = null;
                    } else if ((i & 8) == 0) {
                        Log.w(MtpStorageManager.TAG, "Got unrecognized event " + str + " " + i);
                    } else {
                        if (MtpStorageManager.sDebug) {
                            Log.i(MtpStorageManager.TAG, "inotify for " + this.mObject.getPath() + " CLOSE_WRITE: " + str);
                        }
                        MtpStorageManager.this.handleChangedObject(this.mObject, str);
                    }
                } else if (child == null) {
                    Log.w(MtpStorageManager.TAG, "Object was null in event " + str);
                } else {
                    if (MtpStorageManager.sDebug) {
                        Log.i(MtpStorageManager.TAG, "Got inotify removed event for " + str + " " + i);
                    }
                    MtpStorageManager.this.handleRemovedObject(child);
                }
            }
        }
    }

    public static class MtpObject {
        private HashMap<String, MtpObject> mChildren;
        private int mId;
        private boolean mIsDir;
        private String mName;
        private MtpObject mParent;
        private MtpStorage mStorage;
        private FileObserver mObserver = null;
        private boolean mVisited = false;
        private MtpObjectState mState = MtpObjectState.NORMAL;
        private MtpOperation mOp = MtpOperation.NONE;

        MtpObject(String str, int i, MtpStorage mtpStorage, MtpObject mtpObject, boolean z) {
            this.mId = i;
            this.mName = str;
            this.mStorage = (MtpStorage) Preconditions.checkNotNull(mtpStorage);
            this.mParent = mtpObject;
            this.mIsDir = z;
            this.mChildren = this.mIsDir ? new HashMap<>() : null;
        }

        public String getName() {
            return this.mName;
        }

        public int getId() {
            return this.mId;
        }

        public boolean isDir() {
            return this.mIsDir;
        }

        public int getFormat() {
            if (this.mIsDir) {
                return 12289;
            }
            return MediaFile.getFormatCode(this.mName, null);
        }

        public int getStorageId() {
            return getRoot().getId();
        }

        public long getModifiedTime() {
            return getPath().toFile().lastModified() / 1000;
        }

        public MtpObject getParent() {
            return this.mParent;
        }

        public MtpObject getRoot() {
            return isRoot() ? this : this.mParent.getRoot();
        }

        public long getSize() {
            if (this.mIsDir) {
                return 0L;
            }
            return getPath().toFile().length();
        }

        public Path getPath() {
            return isRoot() ? Paths.get(this.mName, new String[0]) : this.mParent.getPath().resolve(this.mName);
        }

        public boolean isRoot() {
            return this.mParent == null;
        }

        public String getVolumeName() {
            return this.mStorage.getVolumeName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setName(String str) {
            this.mName = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setId(int i) {
            this.mId = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isVisited() {
            return this.mVisited;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setParent(MtpObject mtpObject) {
            this.mParent = mtpObject;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDir(boolean z) {
            if (z != this.mIsDir) {
                this.mIsDir = z;
                this.mChildren = z ? new HashMap<>() : null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVisited(boolean z) {
            this.mVisited = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MtpObjectState getState() {
            return this.mState;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setState(MtpObjectState mtpObjectState) {
            this.mState = mtpObjectState;
            if (mtpObjectState == MtpObjectState.NORMAL) {
                this.mOp = MtpOperation.NONE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MtpOperation getOperation() {
            return this.mOp;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOperation(MtpOperation mtpOperation) {
            this.mOp = mtpOperation;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FileObserver getObserver() {
            return this.mObserver;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setObserver(FileObserver fileObserver) {
            this.mObserver = fileObserver;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addChild(MtpObject mtpObject) {
            this.mChildren.put(mtpObject.getName(), mtpObject);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MtpObject getChild(String str) {
            return this.mChildren.get(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Collection<MtpObject> getChildren() {
            return this.mChildren.values();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean exists() {
            return getPath().toFile().exists();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MtpObject copy(boolean z) {
            MtpObject mtpObject = new MtpObject(this.mName, this.mId, this.mStorage, this.mParent, this.mIsDir);
            mtpObject.mIsDir = this.mIsDir;
            mtpObject.mVisited = this.mVisited;
            mtpObject.mState = this.mState;
            mtpObject.mChildren = this.mIsDir ? new HashMap<>() : null;
            if (z && this.mIsDir) {
                Iterator<MtpObject> it = this.mChildren.values().iterator();
                while (it.hasNext()) {
                    MtpObject mtpObjectCopy = it.next().copy(true);
                    mtpObjectCopy.setParent(mtpObject);
                    mtpObject.addChild(mtpObjectCopy);
                }
            }
            return mtpObject;
        }
    }

    public MtpStorageManager(MtpNotifier mtpNotifier, Set<String> set) {
        this.mMtpNotifier = mtpNotifier;
        this.mSubdirectories = set;
        if (this.mCheckConsistency) {
            this.mConsistencyThread.start();
        }
    }

    public /* synthetic */ void lambda$new$0$MtpStorageManager() {
        while (this.mCheckConsistency) {
            try {
                Thread.sleep(15000L);
                if (checkConsistency()) {
                    Log.v(TAG, "Cache is consistent");
                } else {
                    Log.w(TAG, "Cache is not consistent");
                }
            } catch (InterruptedException unused) {
                return;
            }
        }
    }

    public synchronized void close() {
        for (MtpObject mtpObject : this.mObjects.values()) {
            if (mtpObject.getObserver() != null) {
                mtpObject.getObserver().stopWatching();
                mtpObject.setObserver(null);
            }
        }
        for (MtpObject mtpObject2 : this.mRoots.values()) {
            if (mtpObject2.getObserver() != null) {
                mtpObject2.getObserver().stopWatching();
                mtpObject2.setObserver(null);
            }
        }
        if (this.mCheckConsistency) {
            this.mCheckConsistency = false;
            this.mConsistencyThread.interrupt();
            try {
                this.mConsistencyThread.join();
            } catch (InterruptedException unused) {
            }
        }
    }

    public synchronized void setSubdirectories(Set<String> set) {
        this.mSubdirectories = set;
    }

    public synchronized MtpStorage addMtpStorage(StorageVolume storageVolume) {
        MtpStorage mtpStorage;
        int nextStorageId = ((getNextStorageId() & 65535) << 16) + 1;
        mtpStorage = new MtpStorage(storageVolume, nextStorageId);
        this.mRoots.put(Integer.valueOf(nextStorageId), new MtpObject(mtpStorage.getPath(), nextStorageId, mtpStorage, null, true));
        return mtpStorage;
    }

    public synchronized void removeMtpStorage(MtpStorage mtpStorage) {
        removeObjectFromCache(getStorageRoot(mtpStorage.getStorageId()), true, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized boolean isSpecialSubDir(android.mtp.MtpStorageManager.MtpObject r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            android.mtp.MtpStorageManager$MtpObject r0 = r2.getParent()     // Catch: java.lang.Throwable -> L1e
            boolean r0 = r0.isRoot()     // Catch: java.lang.Throwable -> L1e
            if (r0 == 0) goto L1b
            java.util.Set<java.lang.String> r0 = r1.mSubdirectories     // Catch: java.lang.Throwable -> L1e
            if (r0 == 0) goto L1b
            java.lang.String r2 = r2.getName()     // Catch: java.lang.Throwable -> L1e
            boolean r2 = r0.contains(r2)     // Catch: java.lang.Throwable -> L1e
            if (r2 != 0) goto L1b
            r2 = 1
            goto L1c
        L1b:
            r2 = 0
        L1c:
            monitor-exit(r1)
            return r2
        L1e:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.mtp.MtpStorageManager.isSpecialSubDir(android.mtp.MtpStorageManager$MtpObject):boolean");
    }

    public synchronized MtpObject getByPath(String str) {
        MtpObject child = null;
        for (MtpObject mtpObject : this.mRoots.values()) {
            if (str.startsWith(mtpObject.getName())) {
                str = str.substring(mtpObject.getName().length());
                child = mtpObject;
            }
        }
        for (String str2 : str.split("/")) {
            if (child != null && child.isDir()) {
                if (!"".equals(str2)) {
                    if (!child.isVisited()) {
                        getChildren(child);
                    }
                    child = child.getChild(str2);
                }
            }
            return null;
        }
        return child;
    }

    public synchronized MtpObject getObject(int i) {
        if (i == 0 || i == -1) {
            Log.w(TAG, "Can't get root storages with getObject()");
            return null;
        }
        if (!this.mObjects.containsKey(Integer.valueOf(i))) {
            Log.w(TAG, "Id " + i + " doesn't exist");
            return null;
        }
        return this.mObjects.get(Integer.valueOf(i));
    }

    public MtpObject getStorageRoot(int i) {
        if (!this.mRoots.containsKey(Integer.valueOf(i))) {
            Log.w(TAG, "StorageId " + i + " doesn't exist");
            return null;
        }
        return this.mRoots.get(Integer.valueOf(i));
    }

    private int getNextObjectId() {
        int i = this.mNextObjectId;
        this.mNextObjectId = (int) (((long) i) + 1);
        return i;
    }

    private int getNextStorageId() {
        int i = this.mNextStorageId;
        this.mNextStorageId = i + 1;
        return i;
    }

    public synchronized List<MtpObject> getObjects(int i, int i2, int i3) {
        boolean objects = true;
        boolean z = i == 0;
        ArrayList arrayList = new ArrayList();
        if (i == -1) {
            i = 0;
        }
        if (i3 == -1 && i == 0) {
            Iterator<MtpObject> it = this.mRoots.values().iterator();
            while (it.hasNext()) {
                objects &= getObjects(arrayList, it.next(), i2, z);
            }
            if (!objects) {
                arrayList = null;
            }
            return arrayList;
        }
        MtpObject storageRoot = i == 0 ? getStorageRoot(i3) : getObject(i);
        if (storageRoot == null) {
            return null;
        }
        if (!getObjects(arrayList, storageRoot, i2, z)) {
            arrayList = null;
        }
        return arrayList;
    }

    private synchronized boolean getObjects(List<MtpObject> list, MtpObject mtpObject, int i, boolean z) {
        Collection<MtpObject> children = getChildren(mtpObject);
        if (children == null) {
            return false;
        }
        for (MtpObject mtpObject2 : children) {
            if (i == 0 || mtpObject2.getFormat() == i) {
                list.add(mtpObject2);
            }
        }
        boolean z2 = true;
        if (z) {
            boolean objects = true;
            for (MtpObject mtpObject3 : children) {
                if (mtpObject3.isDir()) {
                    objects &= getObjects(list, mtpObject3, i, true);
                }
            }
            z2 = objects;
        }
        return z2;
    }

    private synchronized Collection<MtpObject> getChildren(MtpObject mtpObject) {
        if (mtpObject != null) {
            if (mtpObject.isDir()) {
                if (!mtpObject.isVisited()) {
                    Path path = mtpObject.getPath();
                    if (mtpObject.getObserver() != null) {
                        Log.e(TAG, "Observer is not null!");
                    }
                    mtpObject.setObserver(new MtpObjectObserver(mtpObject));
                    mtpObject.getObserver().startWatching();
                    try {
                        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path);
                        try {
                            for (Path path2 : directoryStreamNewDirectoryStream) {
                                addObjectToCache(mtpObject, path2.getFileName().toString(), path2.toFile().isDirectory());
                            }
                            if (directoryStreamNewDirectoryStream != null) {
                                directoryStreamNewDirectoryStream.close();
                            }
                            mtpObject.setVisited(true);
                        } catch (Throwable th) {
                            if (directoryStreamNewDirectoryStream != null) {
                                try {
                                    directoryStreamNewDirectoryStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            }
                            throw th;
                        }
                    } catch (IOException | DirectoryIteratorException e) {
                        Log.e(TAG, e.toString());
                        mtpObject.getObserver().stopWatching();
                        mtpObject.setObserver(null);
                        return null;
                    }
                }
                return mtpObject.getChildren();
            }
        }
        Log.w(TAG, "Can't find children of " + (mtpObject == null ? "null" : Integer.valueOf(mtpObject.getId())));
        return null;
    }

    private synchronized MtpObject addObjectToCache(MtpObject mtpObject, String str, boolean z) {
        if (!mtpObject.isRoot() && getObject(mtpObject.getId()) != mtpObject) {
            return null;
        }
        if (mtpObject.getChild(str) != null) {
            return null;
        }
        if (this.mSubdirectories != null && mtpObject.isRoot() && !this.mSubdirectories.contains(str)) {
            return null;
        }
        MtpObject mtpObject2 = new MtpObject(str, getNextObjectId(), mtpObject.mStorage, mtpObject, z);
        this.mObjects.put(Integer.valueOf(mtpObject2.getId()), mtpObject2);
        mtpObject.addChild(mtpObject2);
        return mtpObject2;
    }

    private synchronized boolean removeObjectFromCache(MtpObject mtpObject, boolean z, boolean z2) {
        boolean z3;
        z3 = mtpObject.isRoot() || mtpObject.getParent().mChildren.remove(mtpObject.getName(), mtpObject);
        if (!z3 && sDebug) {
            Log.w(TAG, "Failed to remove from parent " + mtpObject.getPath());
        }
        if (mtpObject.isRoot()) {
            z3 = this.mRoots.remove(Integer.valueOf(mtpObject.getId()), mtpObject) && z3;
        } else if (z) {
            if (this.mObjects.remove(Integer.valueOf(mtpObject.getId()), mtpObject) && z3) {
            }
        }
        if (!z3 && sDebug) {
            Log.w(TAG, "Failed to remove from global cache " + mtpObject.getPath());
        }
        if (mtpObject.getObserver() != null) {
            mtpObject.getObserver().stopWatching();
            mtpObject.setObserver(null);
        }
        if (mtpObject.isDir() && z2) {
            Iterator it = new ArrayList(mtpObject.getChildren()).iterator();
            while (it.hasNext()) {
                z3 = removeObjectFromCache((MtpObject) it.next(), z, true) && z3;
            }
        }
        return z3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void handleAddedObject(MtpObject mtpObject, String str, boolean z) {
        MtpOperation operation = MtpOperation.NONE;
        MtpObject child = mtpObject.getChild(str);
        if (child != null) {
            MtpObjectState state = child.getState();
            operation = child.getOperation();
            if (child.isDir() != z && state != MtpObjectState.FROZEN_REMOVED) {
                Log.d(TAG, "Inconsistent directory info! " + child.getPath());
            }
            child.setDir(z);
            int i = AnonymousClass1.$SwitchMap$android$mtp$MtpStorageManager$MtpObjectState[state.ordinal()];
            if (i == 1 || i == 2) {
                child.setState(MtpObjectState.FROZEN_ADDED);
            } else if (i == 3) {
                child.setState(MtpObjectState.NORMAL);
            } else if (i == 4 || i == 5) {
                return;
            } else {
                Log.w(TAG, "Unexpected state in add " + str + " " + state);
            }
            if (sDebug) {
                Log.i(TAG, state + " transitioned to " + child.getState() + " in op " + operation);
            }
        } else {
            child = addObjectToCache(mtpObject, str, z);
            if (child != null) {
                this.mMtpNotifier.sendObjectAdded(child.getId());
            } else {
                if (sDebug) {
                    Log.w(TAG, "object " + str + " already exists");
                }
                return;
            }
        }
        if (z) {
            if (operation == MtpOperation.RENAME) {
                return;
            }
            if (operation == MtpOperation.COPY && !child.isVisited()) {
                return;
            }
            if (child.getObserver() == null) {
                child.setObserver(new MtpObjectObserver(child));
                child.getObserver().startWatching();
                child.setVisited(true);
                try {
                    DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(child.getPath());
                    try {
                        for (Path path : directoryStreamNewDirectoryStream) {
                            if (sDebug) {
                                Log.i(TAG, "Manually handling event for " + path.getFileName().toString());
                            }
                            handleAddedObject(child, path.getFileName().toString(), path.toFile().isDirectory());
                        }
                        if (directoryStreamNewDirectoryStream != null) {
                            directoryStreamNewDirectoryStream.close();
                        }
                    } catch (Throwable th) {
                        if (directoryStreamNewDirectoryStream != null) {
                            try {
                                directoryStreamNewDirectoryStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (IOException | DirectoryIteratorException e) {
                    Log.e(TAG, e.toString());
                    child.getObserver().stopWatching();
                    child.setObserver(null);
                }
            } else {
                Log.e(TAG, "Observer is not null!");
            }
        }
    }

    /* JADX INFO: renamed from: android.mtp.MtpStorageManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$mtp$MtpStorageManager$MtpObjectState;

        static {
            int[] iArr = new int[MtpObjectState.values().length];
            $SwitchMap$android$mtp$MtpStorageManager$MtpObjectState = iArr;
            try {
                iArr[MtpObjectState.FROZEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$mtp$MtpStorageManager$MtpObjectState[MtpObjectState.FROZEN_REMOVED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$mtp$MtpStorageManager$MtpObjectState[MtpObjectState.FROZEN_ONESHOT_ADD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$mtp$MtpStorageManager$MtpObjectState[MtpObjectState.NORMAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$mtp$MtpStorageManager$MtpObjectState[MtpObjectState.FROZEN_ADDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$mtp$MtpStorageManager$MtpObjectState[MtpObjectState.FROZEN_ONESHOT_DEL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void handleRemovedObject(MtpObject mtpObject) {
        MtpObjectState state = mtpObject.getState();
        MtpOperation operation = mtpObject.getOperation();
        int i = AnonymousClass1.$SwitchMap$android$mtp$MtpStorageManager$MtpObjectState[state.ordinal()];
        boolean z = true;
        if (i == 1) {
            mtpObject.setState(MtpObjectState.FROZEN_REMOVED);
        } else if (i != 4) {
            if (i == 5) {
                mtpObject.setState(MtpObjectState.FROZEN_REMOVED);
            } else if (i == 6) {
                if (operation == MtpOperation.RENAME) {
                    z = false;
                }
                removeObjectFromCache(mtpObject, z, false);
            } else {
                Log.e(TAG, "Got unexpected object remove for " + mtpObject.getName());
            }
        } else if (removeObjectFromCache(mtpObject, true, true)) {
            this.mMtpNotifier.sendObjectRemoved(mtpObject.getId());
        }
        if (sDebug) {
            Log.i(TAG, state + " transitioned to " + mtpObject.getState() + " in op " + operation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void handleChangedObject(MtpObject mtpObject, String str) {
        MtpOperation mtpOperation = MtpOperation.NONE;
        MtpObject child = mtpObject.getChild(str);
        if (child != null) {
            if (!child.isDir() && child.getSize() > 0) {
                child.getState();
                child.getOperation();
                this.mMtpNotifier.sendObjectInfoChanged(child.getId());
                if (sDebug) {
                    Log.d(TAG, "sendObjectInfoChanged: id=" + child.getId() + ",size=" + child.getSize());
                }
            }
        } else if (sDebug) {
            Log.w(TAG, "object " + str + " null");
        }
    }

    public void flushEvents() {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException unused) {
        }
    }

    public synchronized void dump() {
        Iterator<Integer> it = this.mObjects.keySet().iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            MtpObject mtpObject = this.mObjects.get(Integer.valueOf(iIntValue));
            Log.i(TAG, iIntValue + " | " + (mtpObject.getParent() == null ? Integer.valueOf(mtpObject.getParent().getId()) : "null") + " | " + mtpObject.getName() + " | " + (mtpObject.isDir() ? "dir" : "obj") + " | " + (mtpObject.isVisited() ? "v" : "nv") + " | " + mtpObject.getState());
        }
    }

    public synchronized boolean checkConsistency() {
        boolean z;
        ArrayList<MtpObject> arrayList = new ArrayList();
        arrayList.addAll(this.mRoots.values());
        arrayList.addAll(this.mObjects.values());
        z = true;
        for (MtpObject mtpObject : arrayList) {
            if (!mtpObject.exists()) {
                Log.w(TAG, "Object doesn't exist " + mtpObject.getPath() + " " + mtpObject.getId());
                z = false;
            }
            if (mtpObject.getState() != MtpObjectState.NORMAL) {
                Log.w(TAG, "Object " + mtpObject.getPath() + " in state " + mtpObject.getState());
                z = false;
            }
            if (mtpObject.getOperation() != MtpOperation.NONE) {
                Log.w(TAG, "Object " + mtpObject.getPath() + " in operation " + mtpObject.getOperation());
                z = false;
            }
            if (!mtpObject.isRoot() && this.mObjects.get(Integer.valueOf(mtpObject.getId())) != mtpObject) {
                Log.w(TAG, "Object " + mtpObject.getPath() + " is not in map correctly");
                z = false;
            }
            if (mtpObject.getParent() != null) {
                if (mtpObject.getParent().isRoot() && mtpObject.getParent() != this.mRoots.get(Integer.valueOf(mtpObject.getParent().getId()))) {
                    Log.w(TAG, "Root parent is not in root mapping " + mtpObject.getPath());
                    z = false;
                }
                if (!mtpObject.getParent().isRoot() && mtpObject.getParent() != this.mObjects.get(Integer.valueOf(mtpObject.getParent().getId()))) {
                    Log.w(TAG, "Parent is not in object mapping " + mtpObject.getPath());
                    z = false;
                }
                if (mtpObject.getParent().getChild(mtpObject.getName()) != mtpObject) {
                    Log.w(TAG, "Child does not exist in parent " + mtpObject.getPath());
                    z = false;
                }
            }
            if (mtpObject.isDir()) {
                if (mtpObject.isVisited() == (mtpObject.getObserver() == null)) {
                    Log.w(TAG, mtpObject.getPath() + " is " + (mtpObject.isVisited() ? "" : "not ") + " visited but observer is " + mtpObject.getObserver());
                    z = false;
                }
                if (!mtpObject.isVisited() && mtpObject.getChildren().size() > 0) {
                    Log.w(TAG, mtpObject.getPath() + " is not visited but has children");
                    z = false;
                }
                try {
                    DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(mtpObject.getPath());
                    try {
                        HashSet hashSet = new HashSet();
                        for (Path path : directoryStreamNewDirectoryStream) {
                            if (mtpObject.isVisited() && mtpObject.getChild(path.getFileName().toString()) == null && (this.mSubdirectories == null || !mtpObject.isRoot() || this.mSubdirectories.contains(path.getFileName().toString()))) {
                                Log.w(TAG, "File exists in fs but not in children " + path);
                                z = false;
                            }
                            hashSet.add(path.toString());
                        }
                        for (MtpObject mtpObject2 : mtpObject.getChildren()) {
                            if (!hashSet.contains(mtpObject2.getPath().toString())) {
                                Log.w(TAG, "File in children doesn't exist in fs " + mtpObject2.getPath());
                                z = false;
                            }
                            if (mtpObject2 != this.mObjects.get(Integer.valueOf(mtpObject2.getId()))) {
                                Log.w(TAG, "Child is not in object map " + mtpObject2.getPath());
                                z = false;
                            }
                        }
                        if (directoryStreamNewDirectoryStream != null) {
                            directoryStreamNewDirectoryStream.close();
                        }
                    } catch (Throwable th) {
                        if (directoryStreamNewDirectoryStream != null) {
                            try {
                                directoryStreamNewDirectoryStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (IOException | DirectoryIteratorException e) {
                    Log.w(TAG, e.toString());
                    z = false;
                }
            }
        }
        return z;
    }

    public synchronized int beginSendObject(MtpObject mtpObject, String str, int i) {
        Set<String> set;
        if (sDebug) {
            Log.v(TAG, "beginSendObject " + str);
        }
        if (!mtpObject.isDir()) {
            return -1;
        }
        if (mtpObject.isRoot() && (set = this.mSubdirectories) != null && !set.contains(str)) {
            return -1;
        }
        getChildren(mtpObject);
        MtpObject mtpObjectAddObjectToCache = addObjectToCache(mtpObject, str, i == 12289);
        if (mtpObjectAddObjectToCache == null) {
            return -1;
        }
        mtpObjectAddObjectToCache.setState(MtpObjectState.FROZEN);
        mtpObjectAddObjectToCache.setOperation(MtpOperation.ADD);
        return mtpObjectAddObjectToCache.getId();
    }

    public synchronized boolean endSendObject(MtpObject mtpObject, boolean z) {
        if (sDebug) {
            Log.v(TAG, "endSendObject " + z);
        }
        return generalEndAddObject(mtpObject, z, true);
    }

    public synchronized boolean beginRenameObject(MtpObject mtpObject, String str) {
        if (sDebug) {
            Log.v(TAG, "beginRenameObject " + mtpObject.getName() + " " + str);
        }
        if (mtpObject.isRoot()) {
            return false;
        }
        if (isSpecialSubDir(mtpObject)) {
            return false;
        }
        if (mtpObject.getParent().getChild(str) != null) {
            return false;
        }
        MtpObject mtpObjectCopy = mtpObject.copy(false);
        mtpObject.setName(str);
        mtpObject.getParent().addChild(mtpObject);
        mtpObjectCopy.getParent().addChild(mtpObjectCopy);
        return generalBeginRenameObject(mtpObjectCopy, mtpObject);
    }

    public synchronized boolean endRenameObject(MtpObject mtpObject, String str, boolean z) {
        MtpObject child;
        if (sDebug) {
            Log.v(TAG, "endRenameObject " + z);
        }
        MtpObject parent = mtpObject.getParent();
        child = parent.getChild(str);
        if (z) {
            child = mtpObject;
            mtpObject = child;
        } else {
            MtpObjectState state = child.getState();
            child.setName(mtpObject.getName());
            child.setState(mtpObject.getState());
            mtpObject.setName(str);
            mtpObject.setState(state);
            parent.addChild(child);
            parent.addChild(mtpObject);
        }
        return generalEndRenameObject(mtpObject, child, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean beginRemoveObject(android.mtp.MtpStorageManager.MtpObject r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = android.mtp.MtpStorageManager.sDebug     // Catch: java.lang.Throwable -> L3a
            if (r0 == 0) goto L21
            java.lang.String r0 = android.mtp.MtpStorageManager.TAG     // Catch: java.lang.Throwable -> L3a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3a
            r1.<init>()     // Catch: java.lang.Throwable -> L3a
            java.lang.String r2 = "beginRemoveObject "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3a
            java.lang.String r2 = r4.getName()     // Catch: java.lang.Throwable -> L3a
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3a
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L3a
            android.util.Log.v(r0, r1)     // Catch: java.lang.Throwable -> L3a
        L21:
            boolean r0 = r4.isRoot()     // Catch: java.lang.Throwable -> L3a
            if (r0 != 0) goto L37
            boolean r0 = r3.isSpecialSubDir(r4)     // Catch: java.lang.Throwable -> L3a
            if (r0 != 0) goto L37
            android.mtp.MtpStorageManager$MtpOperation r0 = android.mtp.MtpStorageManager.MtpOperation.DELETE     // Catch: java.lang.Throwable -> L3a
            boolean r4 = r3.generalBeginRemoveObject(r4, r0)     // Catch: java.lang.Throwable -> L3a
            if (r4 == 0) goto L37
            r4 = 1
            goto L38
        L37:
            r4 = 0
        L38:
            monitor-exit(r3)
            return r4
        L3a:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.mtp.MtpStorageManager.beginRemoveObject(android.mtp.MtpStorageManager$MtpObject):boolean");
    }

    public synchronized boolean endRemoveObject(MtpObject mtpObject, boolean z) {
        boolean z2;
        boolean z3;
        if (sDebug) {
            Log.v(TAG, "endRemoveObject " + z);
        }
        z2 = false;
        if (mtpObject.isDir()) {
            loop0: while (true) {
                z3 = true;
                for (MtpObject mtpObject2 : new ArrayList(mtpObject.getChildren())) {
                    if (mtpObject2.getOperation() == MtpOperation.DELETE) {
                        if (!endRemoveObject(mtpObject2, z) || !z3) {
                            z3 = false;
                        }
                    }
                }
            }
        } else {
            z3 = true;
        }
        if (generalEndRemoveObject(mtpObject, z, true) && z3) {
            z2 = true;
        }
        return z2;
    }

    public synchronized boolean beginMoveObject(MtpObject mtpObject, MtpObject mtpObject2) {
        if (sDebug) {
            Log.v(TAG, "beginMoveObject " + mtpObject2.getPath());
        }
        boolean z = false;
        if (mtpObject.isRoot()) {
            return false;
        }
        if (isSpecialSubDir(mtpObject)) {
            return false;
        }
        getChildren(mtpObject2);
        if (mtpObject2.getChild(mtpObject.getName()) != null) {
            return false;
        }
        if (mtpObject.getStorageId() != mtpObject2.getStorageId()) {
            MtpObject mtpObjectCopy = mtpObject.copy(true);
            mtpObjectCopy.setParent(mtpObject2);
            mtpObject2.addChild(mtpObjectCopy);
            if (generalBeginRemoveObject(mtpObject, MtpOperation.RENAME) && generalBeginCopyObject(mtpObjectCopy, false)) {
                z = true;
            }
            return z;
        }
        MtpObject mtpObjectCopy2 = mtpObject.copy(false);
        mtpObject.setParent(mtpObject2);
        mtpObjectCopy2.getParent().addChild(mtpObjectCopy2);
        mtpObject.getParent().addChild(mtpObject);
        return generalBeginRenameObject(mtpObjectCopy2, mtpObject);
    }

    public synchronized boolean endMoveObject(MtpObject mtpObject, MtpObject mtpObject2, String str, boolean z) {
        if (sDebug) {
            Log.v(TAG, "endMoveObject " + z);
        }
        MtpObject child = mtpObject.getChild(str);
        MtpObject child2 = mtpObject2.getChild(str);
        boolean z2 = false;
        if (child != null && child2 != null) {
            if (mtpObject.getStorageId() != child2.getStorageId()) {
                boolean zEndRemoveObject = endRemoveObject(child, z);
                if (generalEndCopyObject(child2, z, true) && zEndRemoveObject) {
                    z2 = true;
                }
                return z2;
            }
            if (!z) {
                MtpObjectState state = child.getState();
                child.setParent(child2.getParent());
                child.setState(child2.getState());
                child2.setParent(mtpObject);
                child2.setState(state);
                child.getParent().addChild(child);
                mtpObject.addChild(child2);
                child = child2;
                child2 = child;
            }
            return generalEndRenameObject(child, child2, z);
        }
        return false;
    }

    public synchronized int beginCopyObject(MtpObject mtpObject, MtpObject mtpObject2) {
        Set<String> set;
        if (sDebug) {
            Log.v(TAG, "beginCopyObject " + mtpObject.getName() + " to " + mtpObject2.getPath());
        }
        String name = mtpObject.getName();
        if (!mtpObject2.isDir()) {
            return -1;
        }
        if (mtpObject2.isRoot() && (set = this.mSubdirectories) != null && !set.contains(name)) {
            return -1;
        }
        getChildren(mtpObject2);
        if (mtpObject2.getChild(name) != null) {
            return -1;
        }
        MtpObject mtpObjectCopy = mtpObject.copy(mtpObject.isDir());
        mtpObject2.addChild(mtpObjectCopy);
        mtpObjectCopy.setParent(mtpObject2);
        if (generalBeginCopyObject(mtpObjectCopy, true)) {
            return mtpObjectCopy.getId();
        }
        return -1;
    }

    public synchronized boolean endCopyObject(MtpObject mtpObject, boolean z) {
        if (sDebug) {
            Log.v(TAG, "endCopyObject " + mtpObject.getName() + " " + z);
        }
        return generalEndCopyObject(mtpObject, z, false);
    }

    private synchronized boolean generalEndAddObject(MtpObject mtpObject, boolean z, boolean z2) {
        int i = AnonymousClass1.$SwitchMap$android$mtp$MtpStorageManager$MtpObjectState[mtpObject.getState().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 5) {
                    return false;
                }
                mtpObject.setState(MtpObjectState.NORMAL);
                if (!z) {
                    MtpObject parent = mtpObject.getParent();
                    if (!removeObjectFromCache(mtpObject, z2, false)) {
                        return false;
                    }
                    handleAddedObject(parent, mtpObject.getName(), mtpObject.isDir());
                }
            } else {
                if (!removeObjectFromCache(mtpObject, z2, false)) {
                    return false;
                }
                if (z) {
                    this.mMtpNotifier.sendObjectRemoved(mtpObject.getId());
                }
            }
        } else if (z) {
            mtpObject.setState(MtpObjectState.FROZEN_ONESHOT_ADD);
        } else if (!removeObjectFromCache(mtpObject, z2, false)) {
            return false;
        }
        return true;
    }

    private synchronized boolean generalEndRemoveObject(MtpObject mtpObject, boolean z, boolean z2) {
        int i = AnonymousClass1.$SwitchMap$android$mtp$MtpStorageManager$MtpObjectState[mtpObject.getState().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 5) {
                    return false;
                }
                mtpObject.setState(MtpObjectState.NORMAL);
                if (z) {
                    MtpObject parent = mtpObject.getParent();
                    if (!removeObjectFromCache(mtpObject, z2, false)) {
                        return false;
                    }
                    handleAddedObject(parent, mtpObject.getName(), mtpObject.isDir());
                }
            } else {
                if (!removeObjectFromCache(mtpObject, z2, false)) {
                    return false;
                }
                if (!z) {
                    this.mMtpNotifier.sendObjectRemoved(mtpObject.getId());
                }
            }
        } else if (z) {
            mtpObject.setState(MtpObjectState.FROZEN_ONESHOT_DEL);
        } else {
            mtpObject.setState(MtpObjectState.NORMAL);
        }
        return true;
    }

    private synchronized boolean generalBeginRenameObject(MtpObject mtpObject, MtpObject mtpObject2) {
        mtpObject.setState(MtpObjectState.FROZEN);
        mtpObject2.setState(MtpObjectState.FROZEN);
        mtpObject.setOperation(MtpOperation.RENAME);
        mtpObject2.setOperation(MtpOperation.RENAME);
        return true;
    }

    private synchronized boolean generalEndRenameObject(MtpObject mtpObject, MtpObject mtpObject2, boolean z) {
        return generalEndAddObject(mtpObject2, z, z) && generalEndRemoveObject(mtpObject, z, !z);
    }

    private synchronized boolean generalBeginRemoveObject(MtpObject mtpObject, MtpOperation mtpOperation) {
        mtpObject.setState(MtpObjectState.FROZEN);
        mtpObject.setOperation(mtpOperation);
        if (mtpObject.isDir()) {
            Iterator it = mtpObject.getChildren().iterator();
            while (it.hasNext()) {
                generalBeginRemoveObject((MtpObject) it.next(), mtpOperation);
            }
        }
        return true;
    }

    private synchronized boolean generalBeginCopyObject(MtpObject mtpObject, boolean z) {
        mtpObject.setState(MtpObjectState.FROZEN);
        mtpObject.setOperation(MtpOperation.COPY);
        if (z) {
            mtpObject.setId(getNextObjectId());
            this.mObjects.put(Integer.valueOf(mtpObject.getId()), mtpObject);
        }
        if (mtpObject.isDir()) {
            Iterator it = mtpObject.getChildren().iterator();
            while (it.hasNext()) {
                if (!generalBeginCopyObject((MtpObject) it.next(), z)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a A[Catch: all -> 0x005b, TryCatch #0 {, blocks: (B:5:0x0005, B:6:0x0012, B:8:0x001a, B:10:0x0028, B:12:0x002e, B:14:0x003c, B:25:0x0050), top: B:34:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized boolean generalEndCopyObject(android.mtp.MtpStorageManager.MtpObject r8, boolean r9, boolean r10) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 == 0) goto L12
            if (r10 == 0) goto L12
            java.util.HashMap<java.lang.Integer, android.mtp.MtpStorageManager$MtpObject> r0 = r7.mObjects     // Catch: java.lang.Throwable -> L5b
            int r1 = r8.getId()     // Catch: java.lang.Throwable -> L5b
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L5b
            r0.put(r1, r8)     // Catch: java.lang.Throwable -> L5b
        L12:
            boolean r0 = r8.isDir()     // Catch: java.lang.Throwable -> L5b
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L47
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L5b
            java.util.Collection r3 = android.mtp.MtpStorageManager.MtpObject.access$1000(r8)     // Catch: java.lang.Throwable -> L5b
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L5b
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L5b
        L27:
            r3 = r2
        L28:
            boolean r4 = r0.hasNext()     // Catch: java.lang.Throwable -> L5b
            if (r4 == 0) goto L48
            java.lang.Object r4 = r0.next()     // Catch: java.lang.Throwable -> L5b
            android.mtp.MtpStorageManager$MtpObject r4 = (android.mtp.MtpStorageManager.MtpObject) r4     // Catch: java.lang.Throwable -> L5b
            android.mtp.MtpStorageManager$MtpOperation r5 = android.mtp.MtpStorageManager.MtpObject.access$1500(r4)     // Catch: java.lang.Throwable -> L5b
            android.mtp.MtpStorageManager$MtpOperation r6 = android.mtp.MtpStorageManager.MtpOperation.COPY     // Catch: java.lang.Throwable -> L5b
            if (r5 != r6) goto L28
            boolean r4 = r7.generalEndCopyObject(r4, r9, r10)     // Catch: java.lang.Throwable -> L5b
            if (r4 == 0) goto L45
            if (r3 == 0) goto L45
            goto L27
        L45:
            r3 = r1
            goto L28
        L47:
            r3 = r2
        L48:
            if (r9 != 0) goto L4f
            if (r10 != 0) goto L4d
            goto L4f
        L4d:
            r10 = r1
            goto L50
        L4f:
            r10 = r2
        L50:
            boolean r8 = r7.generalEndAddObject(r8, r9, r10)     // Catch: java.lang.Throwable -> L5b
            if (r8 == 0) goto L59
            if (r3 == 0) goto L59
            r1 = r2
        L59:
            monitor-exit(r7)
            return r1
        L5b:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.mtp.MtpStorageManager.generalEndCopyObject(android.mtp.MtpStorageManager$MtpObject, boolean, boolean):boolean");
    }
}
