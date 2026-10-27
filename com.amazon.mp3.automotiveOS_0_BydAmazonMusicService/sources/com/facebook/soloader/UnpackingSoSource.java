package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import android.os.StrictMode;
import android.util.Log;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SyncFailedException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
public abstract class UnpackingSoSource extends DirectorySoSource {

    @Nullable
    private String[] mAbis;
    protected final Context mContext;

    @Nullable
    protected String mCorruptedLib;

    @Nullable
    protected FileLocker mInstanceLock;
    private final Map<String, Object> mLibsBeingLoaded;

    protected interface InputDso extends Closeable {
        int available() throws IOException;

        Dso getDso();

        String getFileName();

        void write(DataOutput dataOutput, byte[] bArr) throws IOException;
    }

    protected String getSoNameFromFileName(String str) {
        return str;
    }

    protected abstract Unpacker makeUnpacker(byte b) throws IOException;

    protected UnpackingSoSource(Context context, String str) {
        super(getSoStorePath(context, str), 1);
        this.mLibsBeingLoaded = new HashMap();
        this.mContext = context;
    }

    public static File getSoStorePath(Context context, String str) {
        return new File(context.getApplicationInfo().dataDir + "/" + str);
    }

    public void setSoSourceAbis(String[] strArr) {
        this.mAbis = strArr;
    }

    public static class Dso {
        public final String hash;
        public final String name;

        public Dso(String str, String str2) {
            this.name = str;
            this.hash = str2;
        }
    }

    public static final class DsoManifest {
        public final Dso[] dsos;

        public DsoManifest(Dso[] dsoArr) {
            this.dsos = dsoArr;
        }

        static final DsoManifest read(DataInput dataInput) throws IOException {
            if (dataInput.readByte() != 1) {
                throw new RuntimeException("wrong dso manifest version");
            }
            int i = dataInput.readInt();
            if (i < 0) {
                throw new RuntimeException("illegal number of shared libraries");
            }
            Dso[] dsoArr = new Dso[i];
            for (int i2 = 0; i2 < i; i2++) {
                dsoArr[i2] = new Dso(dataInput.readUTF(), dataInput.readUTF());
            }
            return new DsoManifest(dsoArr);
        }

        public final void write(DataOutput dataOutput) throws IOException {
            dataOutput.writeByte(1);
            dataOutput.writeInt(this.dsos.length);
            int i = 0;
            while (true) {
                Dso[] dsoArr = this.dsos;
                if (i >= dsoArr.length) {
                    return;
                }
                dataOutput.writeUTF(dsoArr[i].name);
                dataOutput.writeUTF(this.dsos[i].hash);
                i++;
            }
        }
    }

    public static class InputDsoStream implements InputDso {
        private final InputStream content;
        private final Dso dso;

        public InputDsoStream(Dso dso, InputStream inputStream) {
            this.dso = dso;
            this.content = inputStream;
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public void write(DataOutput dataOutput, byte[] bArr) throws IOException {
            SysUtil.copyBytes(dataOutput, this.content, Integer.MAX_VALUE, bArr);
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public Dso getDso() {
            return this.dso;
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public String getFileName() {
            return this.dso.name;
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public int available() throws IOException {
            return this.content.available();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.content.close();
        }
    }

    protected static abstract class InputDsoIterator implements Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public abstract boolean hasNext();

        @Nullable
        public abstract InputDso next() throws IOException;

        protected InputDsoIterator() {
        }
    }

    protected static abstract class Unpacker implements Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public abstract DsoManifest getDsoManifest() throws IOException;

        public abstract InputDsoIterator openDsoIterator() throws IOException;

        protected Unpacker() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeState(File file, byte b) throws IOException {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                randomAccessFile.seek(0L);
                randomAccessFile.write(b);
                randomAccessFile.setLength(randomAccessFile.getFilePointer());
                randomAccessFile.getFD().sync();
                randomAccessFile.close();
            } finally {
            }
        } catch (SyncFailedException e) {
            Log.w("fb-UnpackingSoSource", "state file sync failed", e);
        }
    }

    private void deleteUnmentionedFiles(Dso[] dsoArr) throws IOException {
        String[] list = this.soDirectory.list();
        if (list == null) {
            throw new IOException("unable to list directory " + this.soDirectory);
        }
        for (String str : list) {
            if (!str.equals("dso_state") && !str.equals("dso_lock") && !str.equals("dso_instance_lock") && !str.equals("dso_deps") && !str.equals("dso_manifest")) {
                boolean z = false;
                for (int i = 0; !z && i < dsoArr.length; i++) {
                    if (dsoArr[i].name.equals(getSoNameFromFileName(str))) {
                        z = true;
                    }
                }
                if (!z) {
                    File file = new File(this.soDirectory, str);
                    Log.v("fb-UnpackingSoSource", "deleting unaccounted-for file " + file);
                    SysUtil.dumbDeleteRecursive(file);
                }
            }
        }
    }

    private void extractDso(InputDso inputDso, byte[] bArr) throws IOException {
        Log.i("fb-UnpackingSoSource", "extracting DSO " + inputDso.getDso().name);
        try {
            if (!this.soDirectory.setWritable(true)) {
                throw new IOException("cannot make directory writable for us: " + this.soDirectory);
            }
            extractDsoImpl(inputDso, bArr);
        } finally {
            if (!this.soDirectory.setWritable(false)) {
                Log.w("fb-UnpackingSoSource", "error removing " + this.soDirectory.getCanonicalPath() + " write permission");
            }
        }
    }

    private void extractDsoImpl(InputDso inputDso, byte[] bArr) throws IOException {
        RandomAccessFile randomAccessFile;
        File file = new File(this.soDirectory, inputDso.getFileName());
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                if (file.exists() && !file.setWritable(true)) {
                    Log.w("fb-UnpackingSoSource", "error adding write permission to: " + file);
                }
                try {
                    randomAccessFile = new RandomAccessFile(file, "rw");
                } catch (IOException e) {
                    Log.w("fb-UnpackingSoSource", "error overwriting " + file + " trying to delete and start over", e);
                    SysUtil.dumbDeleteRecursive(file);
                    randomAccessFile = new RandomAccessFile(file, "rw");
                }
                randomAccessFile2 = randomAccessFile;
                int iAvailable = inputDso.available();
                if (iAvailable > 1) {
                    SysUtil.fallocateIfSupported(randomAccessFile2.getFD(), iAvailable);
                }
                inputDso.write(randomAccessFile2, bArr);
                randomAccessFile2.setLength(randomAccessFile2.getFilePointer());
                if (!file.setExecutable(true, false)) {
                    throw new IOException("cannot make file executable: " + file);
                }
                if (!file.setWritable(false)) {
                    Log.w("fb-UnpackingSoSource", "error removing " + file + " write permission");
                }
                randomAccessFile2.close();
            } catch (IOException e2) {
                SysUtil.dumbDeleteRecursive(file);
                throw e2;
            }
        } catch (Throwable th) {
            if (!file.setWritable(false)) {
                Log.w("fb-UnpackingSoSource", "error removing " + file + " write permission");
            }
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            throw th;
        }
    }

    private void regenerate(byte b, DsoManifest dsoManifest, InputDsoIterator inputDsoIterator) throws IOException {
        DsoManifest dsoManifest2;
        Log.v("fb-UnpackingSoSource", "regenerating DSO store " + getClass().getName());
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(this.soDirectory, "dso_manifest"), "rw");
        try {
            if (b == 1) {
                try {
                    dsoManifest2 = DsoManifest.read(randomAccessFile);
                } catch (Exception e) {
                    Log.i("fb-UnpackingSoSource", "error reading existing DSO manifest", e);
                    dsoManifest2 = null;
                }
            } else {
                dsoManifest2 = null;
            }
            if (dsoManifest2 == null) {
                dsoManifest2 = new DsoManifest(new Dso[0]);
            }
            deleteUnmentionedFiles(dsoManifest.dsos);
            byte[] bArr = new byte[32768];
            while (inputDsoIterator.hasNext()) {
                InputDso next = inputDsoIterator.next();
                boolean z = true;
                int i = 0;
                while (z) {
                    try {
                        if (i >= dsoManifest2.dsos.length) {
                            break;
                        }
                        if (dsoManifest2.dsos[i].name.equals(next.getDso().name) && dsoManifest2.dsos[i].hash.equals(next.getDso().hash)) {
                            z = false;
                        }
                        i++;
                    } finally {
                    }
                }
                if (!new File(this.soDirectory, next.getFileName()).exists()) {
                    z = true;
                }
                if (z) {
                    extractDso(next, bArr);
                }
                next.close();
            }
            randomAccessFile.close();
            Log.v("fb-UnpackingSoSource", "Finished regenerating DSO store " + getClass().getName());
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    protected boolean depsChanged(byte[] bArr, byte[] bArr2) {
        return !Arrays.equals(bArr, bArr2);
    }

    protected boolean refreshLocked(FileLocker fileLocker, int i, byte[] bArr) throws IOException {
        byte b;
        File file = new File(this.soDirectory, "dso_state");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            b = randomAccessFile.readByte();
        } catch (EOFException unused) {
        } catch (Throwable th) {
            throw th;
        }
        if (b != 1) {
            Log.v("fb-UnpackingSoSource", "dso store " + this.soDirectory + " regeneration interrupted: wiping clean");
            b = 0;
        }
        randomAccessFile.close();
        File file2 = new File(this.soDirectory, "dso_deps");
        DsoManifest dsoManifest = null;
        randomAccessFile = new RandomAccessFile(file2, "rw");
        try {
            int length = (int) randomAccessFile.length();
            byte[] bArr2 = new byte[length];
            if (randomAccessFile.read(bArr2) != length) {
                Log.v("fb-UnpackingSoSource", "short read of so store deps file: marking unclean");
                b = 0;
            }
            if (depsChanged(bArr2, bArr)) {
                Log.v("fb-UnpackingSoSource", "deps mismatch on deps store: regenerating");
                b = 0;
            }
            if (b == 0 || (i & 2) != 0) {
                Log.v("fb-UnpackingSoSource", "so store dirty: regenerating");
                writeState(file, (byte) 0);
                Unpacker unpackerMakeUnpacker = makeUnpacker(b);
                try {
                    dsoManifest = unpackerMakeUnpacker.getDsoManifest();
                    InputDsoIterator inputDsoIteratorOpenDsoIterator = unpackerMakeUnpacker.openDsoIterator();
                    try {
                        regenerate(b, dsoManifest, inputDsoIteratorOpenDsoIterator);
                        if (inputDsoIteratorOpenDsoIterator != null) {
                            inputDsoIteratorOpenDsoIterator.close();
                        }
                        unpackerMakeUnpacker.close();
                    } finally {
                    }
                } finally {
                }
            }
            DsoManifest dsoManifest2 = dsoManifest;
            randomAccessFile.close();
            if (dsoManifest2 == null) {
                return false;
            }
            Runnable runnableCreateSyncer = createSyncer(fileLocker, bArr, file, file2, dsoManifest2, Boolean.FALSE);
            if ((i & 1) != 0) {
                new Thread(runnableCreateSyncer, "SoSync:" + this.soDirectory.getName()).start();
            } else {
                runnableCreateSyncer.run();
            }
            return true;
        } finally {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
    }

    private Runnable createSyncer(final FileLocker fileLocker, final byte[] bArr, final File file, final File file2, final DsoManifest dsoManifest, final Boolean bool) {
        return new Runnable() { // from class: com.facebook.soloader.UnpackingSoSource.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    try {
                        Log.v("fb-UnpackingSoSource", "starting syncer worker");
                        RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
                        try {
                            randomAccessFile.write(bArr);
                            randomAccessFile.setLength(randomAccessFile.getFilePointer());
                            randomAccessFile.close();
                            randomAccessFile = new RandomAccessFile(new File(UnpackingSoSource.this.soDirectory, "dso_manifest"), "rw");
                            try {
                                dsoManifest.write(randomAccessFile);
                                randomAccessFile.close();
                                SysUtil.fsyncRecursive(UnpackingSoSource.this.soDirectory);
                                UnpackingSoSource.writeState(file, (byte) 1);
                            } finally {
                                try {
                                    randomAccessFile.close();
                                } catch (Throwable th) {
                                    th.addSuppressed(th);
                                }
                            }
                        } finally {
                        }
                    } catch (IOException e) {
                        if (!bool.booleanValue()) {
                            throw new RuntimeException(e);
                        }
                    }
                } finally {
                    Log.v("fb-UnpackingSoSource", "releasing dso store lock for " + UnpackingSoSource.this.soDirectory + " (from syncer thread)");
                    fileLocker.close();
                }
            }
        };
    }

    protected byte[] getDepsBlock() throws IOException {
        Parcel parcelObtain = Parcel.obtain();
        Unpacker unpackerMakeUnpacker = makeUnpacker((byte) 1);
        try {
            Dso[] dsoArr = unpackerMakeUnpacker.getDsoManifest().dsos;
            parcelObtain.writeByte((byte) 1);
            parcelObtain.writeInt(dsoArr.length);
            for (int i = 0; i < dsoArr.length; i++) {
                parcelObtain.writeString(dsoArr[i].name);
                parcelObtain.writeString(dsoArr[i].hash);
            }
            unpackerMakeUnpacker.close();
            byte[] bArrMarshall = parcelObtain.marshall();
            parcelObtain.recycle();
            return bArrMarshall;
        } catch (Throwable th) {
            if (unpackerMakeUnpacker != null) {
                try {
                    unpackerMakeUnpacker.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Nullable
    protected FileLocker getOrCreateLock(File file, boolean z) throws IOException {
        return SysUtil.getOrCreateLockOnDir(this.soDirectory, file, z);
    }

    @Override // com.facebook.soloader.SoSource
    protected void prepare(int i) throws IOException {
        SysUtil.mkdirOrThrow(this.soDirectory);
        FileLocker orCreateLock = getOrCreateLock(new File(this.soDirectory, "dso_lock"), true);
        if (this.mInstanceLock == null) {
            this.mInstanceLock = getOrCreateLock(new File(this.soDirectory, "dso_instance_lock"), false);
        }
        boolean zCanWrite = this.soDirectory.canWrite();
        try {
            Log.v("fb-UnpackingSoSource", "locked dso store " + this.soDirectory);
            if (!zCanWrite) {
                this.soDirectory.setWritable(true);
            }
            if (refreshLocked(orCreateLock, i, getDepsBlock())) {
                orCreateLock = null;
            } else {
                Log.i("fb-UnpackingSoSource", "dso store is up-to-date: " + this.soDirectory);
            }
        } finally {
            if (!zCanWrite) {
                this.soDirectory.setWritable(false);
            }
            if (orCreateLock != null) {
                Log.v("fb-UnpackingSoSource", "releasing dso store lock for " + this.soDirectory);
                orCreateLock.close();
            } else {
                Log.v("fb-UnpackingSoSource", "not releasing dso store lock for " + this.soDirectory + " (syncer thread started)");
            }
        }
    }

    private Object getLibraryLock(String str) {
        Object obj;
        synchronized (this.mLibsBeingLoaded) {
            obj = this.mLibsBeingLoaded.get(str);
            if (obj == null) {
                obj = new Object();
                this.mLibsBeingLoaded.put(str, obj);
            }
        }
        return obj;
    }

    protected synchronized void prepare(String str) throws IOException {
        synchronized (getLibraryLock(str)) {
            this.mCorruptedLib = str;
            prepare(2);
        }
    }

    @Override // com.facebook.soloader.DirectorySoSource, com.facebook.soloader.SoSource
    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        int iLoadLibraryFrom;
        synchronized (getLibraryLock(str)) {
            iLoadLibraryFrom = loadLibraryFrom(str, i, this.soDirectory, threadPolicy);
        }
        return iLoadLibraryFrom;
    }
}
