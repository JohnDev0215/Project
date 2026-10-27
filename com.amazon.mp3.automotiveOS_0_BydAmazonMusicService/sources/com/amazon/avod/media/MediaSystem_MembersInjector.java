package com.amazon.avod.media;

import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.media.aloysius.AloysiusInitializer;
import com.amazon.avod.media.framework.MediaComponent;
import com.amazon.avod.media.framework.libraries.LoadableNativeLibrary;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import dagger.MembersInjector;
import java.util.Set;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaSystem_MembersInjector implements MembersInjector<MediaSystem> {
    public static void injectMBaseDrmSystem(MediaSystem mediaSystem, Provider<BaseDrmSystem> provider) {
        mediaSystem.mBaseDrmSystem = provider;
    }

    public static void injectMEventReporterFactory(MediaSystem mediaSystem, EventReporterFactory eventReporterFactory) {
        mediaSystem.mEventReporterFactory = eventReporterFactory;
    }

    public static void injectMLibrariesLoader(MediaSystem mediaSystem, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        mediaSystem.mLibrariesLoader = playbackNativeLibrariesLoader;
    }

    public static void injectMFileSystem(MediaSystem mediaSystem, FileSystem fileSystem) {
        mediaSystem.mFileSystem = fileSystem;
    }

    public static void injectMProfiler(MediaSystem mediaSystem, MediaProfiler mediaProfiler) {
        mediaSystem.mProfiler = mediaProfiler;
    }

    public static void injectMLibraries(MediaSystem mediaSystem, Set<LoadableNativeLibrary> set) {
        mediaSystem.mLibraries = set;
    }

    public static void injectMRendererSchemeController(MediaSystem mediaSystem, RendererSchemeController rendererSchemeController) {
        mediaSystem.mRendererSchemeController = rendererSchemeController;
    }

    public static void injectMPlaybackMediaEventReportersFactory(MediaSystem mediaSystem, PlaybackMediaEventReporters.Factory factory) {
        mediaSystem.mPlaybackMediaEventReportersFactory = factory;
    }

    public static void injectMAloysiusInitializer(MediaSystem mediaSystem, AloysiusInitializer aloysiusInitializer) {
        mediaSystem.mAloysiusInitializer = aloysiusInitializer;
    }

    public static void injectMComponentProvider(MediaSystem mediaSystem, Provider<Set<MediaComponent>> provider) {
        mediaSystem.mComponentProvider = provider;
    }
}
