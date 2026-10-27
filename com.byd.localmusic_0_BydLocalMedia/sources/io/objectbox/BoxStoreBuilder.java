package io.objectbox;

import io.objectbox.exception.DbException;
import io.objectbox.flatbuffers.FlatBufferBuilder;
import io.objectbox.ideasonly.ModelUpdate;
import io.objectbox.model.FlatStoreOptions;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.greenrobot.essentials.io.IoUtils;

/* JADX INFO: loaded from: classes5.dex */
public class BoxStoreBuilder {
    public static final int DEFAULT_MAX_DB_SIZE_KBYTE = 1048576;
    public static final String DEFAULT_NAME = "objectbox";

    /* JADX INFO: renamed from: android, reason: collision with root package name */
    private boolean f4android;
    private File baseDirectory;

    @Nullable
    Object context;
    int debugFlags;
    boolean debugRelations;
    File directory;
    final List<EntityInfo<?>> entityInfoList;
    TxCallback<?> failedReadTxAttemptCallback;
    int fileMode;
    private Factory<InputStream> initialDbFileFactory;
    long maxDataSizeInKByte;
    int maxReaders;
    long maxSizeInKByte;
    final byte[] model;
    ModelUpdate modelUpdate;
    private String name;
    boolean noReaderThreadLocals;
    int queryAttempts;
    boolean readOnly;

    @Nullable
    Object relinker;
    boolean skipReadSchema;
    boolean usePreviousCommit;
    short validateOnOpenMode;
    long validateOnOpenPageLimit;

    private static String dbName(@Nullable String str) {
        return str != null ? str : DEFAULT_NAME;
    }

    public static BoxStoreBuilder createDebugWithoutModel() {
        BoxStoreBuilder boxStoreBuilder = new BoxStoreBuilder();
        boxStoreBuilder.skipReadSchema = true;
        return boxStoreBuilder;
    }

    private BoxStoreBuilder() {
        this.maxSizeInKByte = 1048576L;
        this.entityInfoList = new ArrayList();
        this.model = null;
    }

    public BoxStoreBuilder(byte[] bArr) {
        this.maxSizeInKByte = 1048576L;
        this.entityInfoList = new ArrayList();
        if (bArr == null) {
            throw new IllegalArgumentException("Model may not be null");
        }
        this.model = Arrays.copyOf(bArr, bArr.length);
    }

    public BoxStoreBuilder name(String str) {
        if (this.directory != null) {
            throw new IllegalArgumentException("Already has directory, cannot assign name");
        }
        if (str.contains("/") || str.contains("\\")) {
            throw new IllegalArgumentException("Name may not contain (back) slashes. Use baseDirectory() or directory() to configure alternative directories");
        }
        this.name = str;
        return this;
    }

    public BoxStoreBuilder directory(File file) {
        if (this.name != null) {
            throw new IllegalArgumentException("Already has name, cannot assign directory");
        }
        if (!this.f4android && this.baseDirectory != null) {
            throw new IllegalArgumentException("Already has base directory, cannot assign directory");
        }
        this.directory = file;
        return this;
    }

    public BoxStoreBuilder baseDirectory(File file) {
        if (this.directory != null) {
            throw new IllegalArgumentException("Already has directory, cannot assign base directory");
        }
        this.baseDirectory = file;
        return this;
    }

    public BoxStoreBuilder androidContext(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Context may not be null");
        }
        this.context = getApplicationContext(obj);
        File androidBaseDir = getAndroidBaseDir(obj);
        if (!androidBaseDir.exists()) {
            androidBaseDir.mkdir();
            if (!androidBaseDir.exists()) {
                throw new RuntimeException("Could not init Android base dir at " + androidBaseDir.getAbsolutePath());
            }
        }
        if (!androidBaseDir.isDirectory()) {
            throw new RuntimeException("Android base dir is not a dir: " + androidBaseDir.getAbsolutePath());
        }
        this.baseDirectory = androidBaseDir;
        this.f4android = true;
        return this;
    }

    private Object getApplicationContext(Object obj) {
        try {
            return obj.getClass().getMethod("getApplicationContext", new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException("context must be a valid Android Context", e);
        }
    }

    public BoxStoreBuilder androidReLinker(Object obj) {
        if (this.context == null) {
            throw new IllegalArgumentException("Set a Context using androidContext(context) first");
        }
        if (obj == null) {
            throw new NullPointerException("ReLinkerInstance may not be null");
        }
        this.relinker = obj;
        return this;
    }

    static File getAndroidDbDir(Object obj, @Nullable String str) {
        return new File(getAndroidBaseDir(obj), dbName(str));
    }

    static File getAndroidBaseDir(Object obj) {
        return new File(getAndroidFilesDir(obj), DEFAULT_NAME);
    }

    @Nonnull
    private static File getAndroidFilesDir(Object obj) {
        try {
            Method method = obj.getClass().getMethod("getFilesDir", new Class[0]);
            File file = (File) method.invoke(obj, new Object[0]);
            if (file == null) {
                System.err.println("getFilesDir() returned null - retrying once...");
                file = (File) method.invoke(obj, new Object[0]);
            }
            if (file == null) {
                throw new IllegalStateException("Android files dir is null");
            }
            if (file.exists()) {
                return file;
            }
            throw new IllegalStateException("Android files dir does not exist");
        } catch (Exception e) {
            throw new RuntimeException("Could not init with given Android context (must be sub class of android.content.Context)", e);
        }
    }

    public BoxStoreBuilder fileMode(int i) {
        this.fileMode = i;
        return this;
    }

    public BoxStoreBuilder maxReaders(int i) {
        this.maxReaders = i;
        return this;
    }

    public BoxStoreBuilder noReaderThreadLocals() {
        this.noReaderThreadLocals = true;
        return this;
    }

    public void entity(EntityInfo<?> entityInfo) {
        this.entityInfoList.add(entityInfo);
    }

    BoxStoreBuilder modelUpdate(ModelUpdate modelUpdate) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public BoxStoreBuilder maxSizeInKByte(long j) {
        if (j <= this.maxDataSizeInKByte) {
            throw new IllegalArgumentException("maxSizeInKByte must be larger than maxDataSizeInKByte.");
        }
        this.maxSizeInKByte = j;
        return this;
    }

    public BoxStoreBuilder maxDataSizeInKByte(long j) {
        if (j >= this.maxSizeInKByte) {
            throw new IllegalArgumentException("maxDataSizeInKByte must be smaller than maxSizeInKByte.");
        }
        this.maxDataSizeInKByte = j;
        return this;
    }

    public BoxStoreBuilder readOnly() {
        this.readOnly = true;
        return this;
    }

    public BoxStoreBuilder usePreviousCommit() {
        this.usePreviousCommit = true;
        return this;
    }

    public BoxStoreBuilder validateOnOpen(short s) {
        if (s < 1 || s > 5) {
            throw new IllegalArgumentException("Must be one of ValidateOnOpenMode");
        }
        this.validateOnOpenMode = s;
        return this;
    }

    public BoxStoreBuilder validateOnOpenPageLimit(long j) {
        short s = this.validateOnOpenMode;
        if (s != 2 && s != 3) {
            throw new IllegalStateException("Must call validateOnOpen(mode) with mode Regular or WithLeaves first");
        }
        if (j < 1) {
            throw new IllegalArgumentException("limit must be positive");
        }
        this.validateOnOpenPageLimit = j;
        return this;
    }

    @Deprecated
    public BoxStoreBuilder debugTransactions() {
        this.debugFlags |= 3;
        return this;
    }

    public BoxStoreBuilder debugFlags(int i) {
        this.debugFlags = i;
        return this;
    }

    public BoxStoreBuilder debugRelations() {
        this.debugRelations = true;
        return this;
    }

    public BoxStoreBuilder queryAttempts(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Query attempts must >= 1");
        }
        this.queryAttempts = i;
        return this;
    }

    public BoxStoreBuilder failedReadTxAttemptCallback(TxCallback<?> txCallback) {
        this.failedReadTxAttemptCallback = txCallback;
        return this;
    }

    static /* synthetic */ InputStream lambda$initialDbFile$0(File file) throws Exception {
        return new FileInputStream(file);
    }

    public BoxStoreBuilder initialDbFile(final File file) {
        return initialDbFile(new Factory() { // from class: io.objectbox.-$$Lambda$BoxStoreBuilder$tcz084OB2tIPv6nleegpU2ArJIA
            @Override // io.objectbox.Factory
            public final Object provide() {
                return BoxStoreBuilder.lambda$initialDbFile$0(file);
            }
        });
    }

    public BoxStoreBuilder initialDbFile(Factory<InputStream> factory) {
        this.initialDbFileFactory = factory;
        return this;
    }

    byte[] buildFlatStoreOptions(String str) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        flatBufferBuilder.forceDefaults(true);
        int iCreateString = flatBufferBuilder.createString(str);
        FlatStoreOptions.startFlatStoreOptions(flatBufferBuilder);
        FlatStoreOptions.addDirectoryPath(flatBufferBuilder, iCreateString);
        FlatStoreOptions.addMaxDbSizeInKbyte(flatBufferBuilder, this.maxSizeInKByte);
        FlatStoreOptions.addFileMode(flatBufferBuilder, this.fileMode);
        FlatStoreOptions.addMaxReaders(flatBufferBuilder, this.maxReaders);
        short s = this.validateOnOpenMode;
        if (s != 0) {
            FlatStoreOptions.addValidateOnOpen(flatBufferBuilder, s);
            long j = this.validateOnOpenPageLimit;
            if (j != 0) {
                FlatStoreOptions.addValidateOnOpenPageLimit(flatBufferBuilder, j);
            }
        }
        if (this.skipReadSchema) {
            FlatStoreOptions.addSkipReadSchema(flatBufferBuilder, true);
        }
        if (this.usePreviousCommit) {
            FlatStoreOptions.addUsePreviousCommit(flatBufferBuilder, true);
        }
        if (this.readOnly) {
            FlatStoreOptions.addReadOnly(flatBufferBuilder, true);
        }
        if (this.noReaderThreadLocals) {
            FlatStoreOptions.addNoReaderThreadLocals(flatBufferBuilder, true);
        }
        int i = this.debugFlags;
        if (i != 0) {
            FlatStoreOptions.addDebugFlags(flatBufferBuilder, i);
        }
        long j2 = this.maxDataSizeInKByte;
        if (j2 > 0) {
            FlatStoreOptions.addMaxDataSizeInKbyte(flatBufferBuilder, j2);
        }
        flatBufferBuilder.finish(FlatStoreOptions.endFlatStoreOptions(flatBufferBuilder));
        return flatBufferBuilder.sizedByteArray();
    }

    public BoxStore build() throws Throwable {
        if (this.directory == null) {
            String strDbName = dbName(this.name);
            this.name = strDbName;
            this.directory = getDbDir(this.baseDirectory, strDbName);
        }
        checkProvisionInitialDbFile();
        return new BoxStore(this);
    }

    private void checkProvisionInitialDbFile() throws Throwable {
        InputStream inputStreamProvide;
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        Exception e;
        BufferedOutputStream bufferedOutputStream2;
        if (this.initialDbFileFactory == null) {
            return;
        }
        File file = new File(BoxStore.getCanonicalPath(this.directory), "data.mdb");
        if (file.exists()) {
            return;
        }
        try {
            inputStreamProvide = this.initialDbFileFactory.provide();
            try {
                if (inputStreamProvide == null) {
                    throw new DbException("Factory did not provide a resource");
                }
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStreamProvide);
                try {
                    bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
                } catch (Exception e2) {
                    e = e2;
                    inputStreamProvide = bufferedInputStream;
                    Exception exc = e;
                    bufferedOutputStream = null;
                    e = exc;
                    try {
                        throw new DbException("Could not provision initial data file", e);
                    } catch (Throwable th2) {
                        th = th2;
                        IoUtils.safeClose(bufferedOutputStream);
                        IoUtils.safeClose(inputStreamProvide);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStreamProvide = bufferedInputStream;
                    Throwable th4 = th;
                    bufferedOutputStream = null;
                    th = th4;
                    IoUtils.safeClose(bufferedOutputStream);
                    IoUtils.safeClose(inputStreamProvide);
                    throw th;
                }
                try {
                    IoUtils.copyAllBytes(bufferedInputStream, bufferedOutputStream2);
                    IoUtils.safeClose(bufferedOutputStream2);
                    IoUtils.safeClose(bufferedInputStream);
                } catch (Exception e3) {
                    e = e3;
                    bufferedOutputStream = bufferedOutputStream2;
                    inputStreamProvide = bufferedInputStream;
                    throw new DbException("Could not provision initial data file", e);
                } catch (Throwable th5) {
                    th = th5;
                    bufferedOutputStream = bufferedOutputStream2;
                    inputStreamProvide = bufferedInputStream;
                    IoUtils.safeClose(bufferedOutputStream);
                    IoUtils.safeClose(inputStreamProvide);
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
            } catch (Throwable th6) {
                th = th6;
            }
        } catch (Exception e5) {
            inputStreamProvide = null;
            e = e5;
            bufferedOutputStream = null;
        } catch (Throwable th7) {
            inputStreamProvide = null;
            th = th7;
            bufferedOutputStream = null;
        }
    }

    static File getDbDir(@Nullable File file, @Nullable String str) {
        String strDbName = dbName(str);
        if (file != null) {
            return new File(file, strDbName);
        }
        return new File(strDbName);
    }

    public BoxStore buildDefault() throws Throwable {
        BoxStore boxStoreBuild = build();
        BoxStore.setDefault(boxStoreBuild);
        return boxStoreBuild;
    }
}
