package com.amazon.avod.content;

import com.amazon.avod.content.downloading.FragmentValidator;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.media.framework.MediaComponent;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface MediaComponentContentStore extends MediaComponent, SmoothStreamingContentStore {
    void deleteFragmentAtAllQualities(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI);

    SmoothStreamingURI getBestQualityAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) throws ContentException;

    boolean isAnyFragmentAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI);

    boolean isFragmentAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI);

    boolean isStatFileAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex);

    ByteBuffer loadFragment(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) throws ContentException;

    ByteBuffer loadStatFile(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex) throws ContentException;

    void releaseFragmentReferences(@Nonnull ContentSessionType contentSessionType, @Nonnull File file, @Nonnull PlayableContent playableContent, @Nonnull String str, @Nonnull SmoothStreamingURI smoothStreamingURI);

    void storeFragment(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, @Nullable String str2) throws ContentException;

    void storeStatFile(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex, InputStream inputStream, int i) throws ContentException;

    void validateAndStoreFragment(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, FragmentValidator fragmentValidator, @Nullable String str2) throws ContentException;
}
