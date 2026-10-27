package com.amazon.avod.media.framework.libraries;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.util.DLog;
import com.google.common.collect.Maps;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackNativeLibrariesLoader {

    @Nullable
    public static final String NATIVE_LIBRARY_PATH_NOT_LOADED = null;
    private final ExecutorService mExecutor;
    private final PlaybackNativeLibrariesCopier mPlaybackNativeLibrariesCopier;
    private final CountDownLatch mLatch = new CountDownLatch(1);
    private final Object mMapSyncObject = new Object();
    private final Map<Type, LibraryListenerEntry> mListenerMap = Maps.newHashMap();

    public interface LibraryLoadedListener {
        void onLibraryLoaded(@Nullable String str);
    }

    private static class LibraryListenerEntry {
        public boolean mIsLoaded;
        public String mLibraryPath;
        public List<LibraryLoadedListener> mListeners;

        public LibraryListenerEntry(boolean z, @Nullable String str, LibraryLoadedListener libraryLoadedListener) {
            this.mIsLoaded = z;
            this.mLibraryPath = str;
            addListener(libraryLoadedListener);
        }

        public LibraryListenerEntry(boolean z) {
            this.mIsLoaded = z;
        }

        public void addListener(LibraryLoadedListener libraryLoadedListener) {
            if (libraryLoadedListener != null) {
                if (this.mListeners == null) {
                    this.mListeners = new ArrayList();
                }
                this.mListeners.add(libraryLoadedListener);
            }
        }

        public void notifyListeners() {
            List<LibraryLoadedListener> list = this.mListeners;
            if (list != null) {
                for (LibraryLoadedListener libraryLoadedListener : list) {
                    if (libraryLoadedListener != null) {
                        libraryLoadedListener.onLibraryLoaded(this.mLibraryPath);
                    }
                }
            }
        }
    }

    public PlaybackNativeLibrariesLoader(@Nonnull MediaSystemSharedContext mediaSystemSharedContext) {
        this.mExecutor = mediaSystemSharedContext.getExecutorService();
        this.mPlaybackNativeLibrariesCopier = new PlaybackNativeLibrariesCopier(mediaSystemSharedContext.getAppContext());
    }

    public void loadLibrariesAsync(@Nonnull final Set<LoadableNativeLibrary> set, @Nonnull final DeviceIdentity deviceIdentity, final boolean z) {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$loadLibrariesAsync$0(set, deviceIdentity, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadLibrariesAsync$0(Set set, DeviceIdentity deviceIdentity, boolean z) {
        initializeNativeLibraries(set);
        loadNativeLibraries(set, deviceIdentity, z);
    }

    public boolean waitForInitialization() {
        try {
            if (this.mLatch.await(20000L, TimeUnit.MILLISECONDS)) {
                return true;
            }
            DLog.errorf("PlaybackNativeLibrariesLoader: Loading native libraries has timed out. Potential dead-lock in one of the libraries that's being loaded in a static function.");
            return false;
        } catch (InterruptedException unused) {
            DLog.errorf("PlaybackNativeLibrariesLoader: Loading native libraries was interrupted");
            return false;
        }
    }

    private void initializeNativeLibraries(@Nonnull Set<LoadableNativeLibrary> set) {
        Iterator<LoadableNativeLibrary> it = set.iterator();
        while (it.hasNext()) {
            it.next().initialize();
        }
    }

    private void loadNativeLibraries(@Nonnull Set<LoadableNativeLibrary> set, @Nonnull DeviceIdentity deviceIdentity, boolean z) {
        if (z) {
            this.mPlaybackNativeLibrariesCopier.initialize(deviceIdentity);
        }
        for (final LoadableNativeLibrary loadableNativeLibrary : set) {
            List<String> libraryNames = loadableNativeLibrary.getLibraryNames();
            if (libraryNames == null || libraryNames.size() == 0) {
                DLog.errorf("PlaybackNativeLibrariesLoader: Native library list is null or empty");
            } else {
                for (String str : libraryNames) {
                    DLog.logf("PlaybackNativeLibrariesLoader: Loading library %s", str);
                    final String strLoadNativeLibrary = loadNativeLibrary(str, z);
                    if (strLoadNativeLibrary == NATIVE_LIBRARY_PATH_NOT_LOADED) {
                        DLog.warnf("PlaybackNativeLibrariesLoader: Failed to load Library: %s", str);
                        return;
                    }
                    this.mExecutor.submit(new Runnable() { // from class: com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$loadNativeLibraries$1(loadableNativeLibrary, strLoadNativeLibrary);
                        }
                    });
                }
            }
        }
        this.mLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadNativeLibraries$1(LoadableNativeLibrary loadableNativeLibrary, String str) {
        notifyListenerOnLoad(loadableNativeLibrary.getClass(), str);
    }

    public void addLibraryLoadedListener(Type type, LibraryLoadedListener libraryLoadedListener) {
        synchronized (this.mMapSyncObject) {
            if (!this.mListenerMap.containsKey(type)) {
                this.mListenerMap.put(type, new LibraryListenerEntry(false, NATIVE_LIBRARY_PATH_NOT_LOADED, libraryLoadedListener));
            } else {
                LibraryListenerEntry libraryListenerEntry = this.mListenerMap.get(type);
                libraryListenerEntry.addListener(libraryLoadedListener);
                if (libraryListenerEntry.mIsLoaded) {
                    libraryListenerEntry.notifyListeners();
                }
            }
        }
    }

    @Nullable
    private String loadNativeLibrary(@Nonnull String str, boolean z) {
        if (z) {
            for (String str2 : this.mPlaybackNativeLibrariesCopier.getInitializedLibraryPaths()) {
                if (str2 != null) {
                    if (loadNativeLibraryDirect(str2 + "/lib" + str + ".so")) {
                        return str2;
                    }
                }
            }
        }
        return loadNativeLibraryIndirect(str) ? "" : NATIVE_LIBRARY_PATH_NOT_LOADED;
    }

    @Deprecated
    private boolean loadNativeLibraryDirect(@Nonnull String str) {
        try {
            System.load(str);
            DLog.logf("PlaybackNativeLibrariesLoader: Success! Directly loaded native library %s", str);
            return true;
        } catch (SecurityException unused) {
            DLog.warnf("PlaybackNativeLibrariesLoader: Security exception. Directly loading native library %s.", str);
            return false;
        } catch (UnsatisfiedLinkError unused2) {
            DLog.warnf("PlaybackNativeLibrariesLoader: Unsatisfied link error. Directly loading native library %s.", str);
            return false;
        }
    }

    private boolean loadNativeLibraryIndirect(@Nonnull String str) {
        try {
            System.loadLibrary(str);
            DLog.logf("PlaybackNativeLibrariesLoader: Success! Indirectly loaded native library %s", str);
            return true;
        } catch (SecurityException e) {
            DLog.exceptionf(e, "PlaybackNativeLibrariesLoader: Security exception. Loading native library %s failed with %s", str, e.toString());
            return false;
        } catch (UnsatisfiedLinkError e2) {
            DLog.exceptionf(e2, "PlaybackNativeLibrariesLoader: Unsatisfied link error. Loading native library %s failed with %s", str, e2.toString());
            return false;
        }
    }

    private void notifyListenerOnLoad(@Nonnull Type type, @Nullable String str) {
        synchronized (this.mMapSyncObject) {
            if (this.mListenerMap.containsKey(type)) {
                LibraryListenerEntry libraryListenerEntry = this.mListenerMap.get(type);
                libraryListenerEntry.mIsLoaded = true;
                libraryListenerEntry.mLibraryPath = str;
                libraryListenerEntry.notifyListeners();
            } else {
                this.mListenerMap.put(type, new LibraryListenerEntry(true));
            }
        }
    }
}
