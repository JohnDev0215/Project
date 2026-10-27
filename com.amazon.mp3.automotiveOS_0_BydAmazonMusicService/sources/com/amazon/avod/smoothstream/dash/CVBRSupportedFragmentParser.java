package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.EventMessage;
import com.amazon.avod.content.smoothstream.Fragment;
import com.amazon.avod.content.smoothstream.Mp4FragmentParser;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.memory.DirectBuffer;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class CVBRSupportedFragmentParser implements Mp4FragmentParser {
    private final int mDashParserFlags;
    private final Mp4FragmentJni mFragmentJni;
    private final Collection<Mp4FragmentParser.EventMessageListener> mListeners;
    private final boolean mShouldSupportSignedCompositionTimeOffset;
    private final Object mSyncObj;

    public CVBRSupportedFragmentParser(@Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this(new Mp4FragmentJni(playbackNativeLibrariesLoader), smoothStreamingPlaybackConfig);
    }

    CVBRSupportedFragmentParser(@Nonnull Mp4FragmentJni mp4FragmentJni, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mSyncObj = new Object();
        this.mListeners = new CopyOnWriteArrayList();
        this.mFragmentJni = (Mp4FragmentJni) Preconditions.checkNotNull(mp4FragmentJni, "mp4FragmentJni");
        this.mDashParserFlags = ((SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "config")).getDashParserFlags();
        this.mShouldSupportSignedCompositionTimeOffset = smoothStreamingPlaybackConfig.shouldSupportSignedCompositionTimeOffset();
    }

    @Override // com.amazon.avod.content.smoothstream.Mp4FragmentParser
    public void addEventMessageListener(@Nonnull Mp4FragmentParser.EventMessageListener eventMessageListener) {
        this.mListeners.add((Mp4FragmentParser.EventMessageListener) Preconditions.checkNotNull(eventMessageListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER));
    }

    @Override // com.amazon.avod.content.smoothstream.Mp4FragmentParser
    public void removeEventMessageListener(@Nonnull Mp4FragmentParser.EventMessageListener eventMessageListener) {
        this.mListeners.remove(Preconditions.checkNotNull(eventMessageListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER));
    }

    @Override // com.amazon.avod.content.smoothstream.Mp4FragmentParser
    @Nonnull
    public Fragment parse(byte[] bArr, int i, int i2, boolean z, @Nonnull DirectBuffer directBuffer) throws ContentException {
        return parseInternal(bArr, i, i2, z, directBuffer);
    }

    @Override // com.amazon.avod.content.smoothstream.Mp4FragmentParser
    @Nonnull
    public Fragment parse(ByteBuffer byteBuffer, boolean z) throws ContentException {
        Fragment fragmentHandleEmsg;
        synchronized (this.mSyncObj) {
            fragmentHandleEmsg = handleEmsg(this.mFragmentJni.parse(byteBuffer, z, this.mDashParserFlags, this.mShouldSupportSignedCompositionTimeOffset));
        }
        return fragmentHandleEmsg;
    }

    @Override // com.amazon.avod.content.smoothstream.Mp4FragmentParser
    public void release() {
        synchronized (this.mSyncObj) {
            this.mFragmentJni.release();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.Mp4FragmentParser
    public boolean validate(byte[] bArr, int i, int i2, boolean z, boolean z2, @Nonnull DirectBuffer directBuffer) throws ContentException {
        parseInternal(bArr, i, i2, z2, directBuffer);
        return true;
    }

    @Override // com.amazon.avod.content.smoothstream.Mp4FragmentParser
    public boolean validate(ByteBuffer byteBuffer, boolean z) throws ContentException {
        synchronized (this.mSyncObj) {
            handleEmsg(this.mFragmentJni.parse(byteBuffer, z, this.mDashParserFlags, this.mShouldSupportSignedCompositionTimeOffset));
        }
        return true;
    }

    private Fragment parseInternal(byte[] bArr, int i, int i2, boolean z, @Nonnull DirectBuffer directBuffer) throws ContentException {
        Fragment fragmentHandleEmsg;
        synchronized (this.mSyncObj) {
            directBuffer.ensureCapacity(i2 - i);
            directBuffer.getByteBufferForWriting().put(bArr, i, i2);
            fragmentHandleEmsg = handleEmsg(this.mFragmentJni.parse(directBuffer, z, this.mDashParserFlags, this.mShouldSupportSignedCompositionTimeOffset));
        }
        return fragmentHandleEmsg;
    }

    private Fragment handleEmsg(Fragment fragment) {
        Collection<EventMessage> eventMessages = fragment.getEventMessages();
        if (!eventMessages.isEmpty()) {
            Iterator<Mp4FragmentParser.EventMessageListener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onEventMessage(eventMessages);
            }
        }
        return fragment;
    }
}
