package com.amazon.avod.content.guice;

import android.content.Context;
import com.amazon.avod.content.ContentStore;
import com.amazon.avod.content.smoothstream.storage.AllocatorNativeLibrary;
import com.amazon.avod.content.smoothstream.storage.CompositeSmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.storage.DynamicManifestContentStore;
import com.amazon.avod.content.smoothstream.storage.FileBackedContentStore;
import com.amazon.avod.content.smoothstream.storage.MemoryAllocator;
import com.amazon.avod.content.smoothstream.storage.MemoryBackedContentStore;
import com.amazon.avod.content.smoothstream.storage.NativeMemoryAllocatorJni;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.storage.singlefile.SingleFileContentStore;
import com.amazon.avod.media.framework.MediaComponent;
import com.amazon.avod.media.framework.libraries.LoadableNativeLibrary;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.network.MultiNetworkHistoryManager;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.framework.platform.FileSystem;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public class ContentModule_Dagger {
    ContentStore provideContentStore(CompositeSmoothStreamingContentStore compositeSmoothStreamingContentStore) {
        return compositeSmoothStreamingContentStore;
    }

    NetworkHistoryManager provideNetworkHistoryManager(MultiNetworkHistoryManager multiNetworkHistoryManager) {
        return multiNetworkHistoryManager;
    }

    SmoothStreamingContentStore provideSmoothStreamingContentStore(CompositeSmoothStreamingContentStore compositeSmoothStreamingContentStore) {
        return compositeSmoothStreamingContentStore;
    }

    MediaComponent providesMediaComponent(FileBackedContentStore fileBackedContentStore) {
        return fileBackedContentStore;
    }

    LoadableNativeLibrary providesMemoryAllocatorNativeLibrary() {
        return new AllocatorNativeLibrary();
    }

    MemoryAllocator provideMemoryAllocator(PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        return new NativeMemoryAllocatorJni(playbackNativeLibrariesLoader);
    }

    FileBackedContentStore provideFileBackedComponentContentStore(Context context, FileSystem fileSystem, ExecutorService executorService) {
        return new FileBackedContentStore(context, fileSystem, executorService);
    }

    SingleFileContentStore provideSingleFileComponentContentStore(FileBackedContentStore fileBackedContentStore) {
        return new SingleFileContentStore(fileBackedContentStore);
    }

    MemoryBackedContentStore provideMemoryBackedComponentContentStore(MemoryAllocator memoryAllocator, FileBackedContentStore fileBackedContentStore) {
        return new MemoryBackedContentStore(memoryAllocator, fileBackedContentStore);
    }

    DynamicManifestContentStore provideManifestComponentContentStore() {
        return new DynamicManifestContentStore();
    }
}
