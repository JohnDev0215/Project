package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.content.smoothstream.EventMessage;
import com.amazon.avod.content.smoothstream.Fragment;
import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.media.framework.memory.DirectBuffer;
import com.amazon.avod.media.framework.resources.SurgingResourcePool;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.sampling.SampleEncryptionInfo;
import com.amazon.avod.util.Base64;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DashFragment implements Fragment {
    private final int mBehaviorFlags;
    private String mCodecPrivateData;
    private final DirectBuffer mDirectBuffer;
    private Collection<EventMessage> mEventMessages;
    private final ByteBuffer mFragmentBuffer;
    private final Mp4FragmentJni mFragmentJni;
    private Long mFragmentPresentationTime;
    private long mIDRCompositionTimeOffset = 0;
    private final boolean mIsAudio;
    private ProtectionHeader mProtectionHeader;
    private Integer mSampleCount;
    private final boolean mShouldSupportSignedCompositionTimeOffset;

    public DashFragment(@Nonnull Mp4FragmentJni mp4FragmentJni, @Nonnull ByteBuffer byteBuffer, boolean z, int i, @Nullable DirectBuffer directBuffer, boolean z2) {
        this.mFragmentJni = (Mp4FragmentJni) Preconditions.checkNotNull(mp4FragmentJni, "fragmentJni");
        this.mFragmentBuffer = (ByteBuffer) Preconditions.checkNotNull(byteBuffer, "fragmentBuffer");
        this.mIsAudio = z;
        this.mBehaviorFlags = i;
        this.mDirectBuffer = directBuffer;
        this.mShouldSupportSignedCompositionTimeOffset = z2;
    }

    public DashFragment initialize() {
        if (this.mShouldSupportSignedCompositionTimeOffset && !this.mIsAudio) {
            try {
                this.mIDRCompositionTimeOffset = this.mFragmentJni.getPresentationTime(this.mFragmentBuffer, 0, this.mBehaviorFlags);
            } catch (Exception e) {
                DLog.warnf("Unable to getPresentationTime for %s : %s", this.mFragmentJni, e.getMessage());
            }
        }
        return this;
    }

    @Override // com.amazon.avod.content.smoothstream.Fragment
    public int getSampleCount() {
        if (this.mSampleCount == null) {
            this.mSampleCount = Integer.valueOf(this.mFragmentJni.getSampleCount(this.mFragmentBuffer, this.mBehaviorFlags));
        }
        return this.mSampleCount.intValue();
    }

    @Override // com.amazon.avod.content.smoothstream.Fragment
    public long getSampleSize(int i) {
        return this.mFragmentJni.getSampleSize(this.mFragmentBuffer, i, this.mBehaviorFlags);
    }

    @Override // com.amazon.avod.content.smoothstream.Fragment
    public long getSampleDuration(int i) {
        return this.mFragmentJni.getSampleDuration(this.mFragmentBuffer, i, this.mBehaviorFlags);
    }

    @Override // com.amazon.avod.content.smoothstream.Fragment
    public long getFragmentPresentationTime() {
        if (this.mFragmentPresentationTime == null) {
            this.mFragmentPresentationTime = Long.valueOf(this.mFragmentJni.getFragmentPresentationTime(this.mFragmentBuffer, this.mBehaviorFlags));
        }
        return this.mFragmentPresentationTime.longValue();
    }

    @Override // com.amazon.avod.content.smoothstream.Fragment
    public long getPresentationTime(int i) {
        long presentationTime = this.mFragmentJni.getPresentationTime(this.mFragmentBuffer, i, this.mBehaviorFlags);
        if (!this.mShouldSupportSignedCompositionTimeOffset) {
            return presentationTime;
        }
        long j = this.mIDRCompositionTimeOffset;
        return (j <= 0 || presentationTime < j) ? presentationTime : presentationTime - j;
    }

    @Override // com.amazon.avod.content.smoothstream.Fragment
    public ByteBuffer getSampleData(int i) {
        return this.mFragmentJni.getSampleData(this.mFragmentBuffer, i, this.mBehaviorFlags);
    }

    @Override // com.amazon.avod.content.smoothstream.Fragment
    public SampleEncryptionInfo getEncryptionInfo(int i) {
        return this.mFragmentJni.getEncryptionInfo(this.mFragmentBuffer, i, this.mBehaviorFlags);
    }

    @Override // com.amazon.avod.content.smoothstream.Fragment
    @Nullable
    public ProtectionHeader getPssh(boolean z) {
        if (this.mProtectionHeader == null) {
            ByteBuffer pssh = this.mFragmentJni.getPssh(this.mFragmentBuffer, z, this.mBehaviorFlags);
            if (pssh == null) {
                this.mProtectionHeader = null;
            } else {
                int iLimit = pssh.limit();
                byte[] bArr = new byte[iLimit];
                pssh.get(bArr, 0, iLimit);
                this.mProtectionHeader = new DashProtectionHeader(Base64.encodeBytes(bArr), z ? DrmScheme.PLAYREADY : DrmScheme.WIDEVINE);
            }
        }
        return this.mProtectionHeader;
    }

    @Override // com.amazon.avod.content.smoothstream.Fragment
    @Nonnull
    public Collection<EventMessage> getEventMessages() {
        if (this.mEventMessages == null) {
            this.mEventMessages = this.mFragmentJni.getEventMessages(this.mFragmentBuffer, this.mBehaviorFlags);
        }
        return this.mEventMessages;
    }

    @Override // com.amazon.avod.content.smoothstream.Fragment
    @Nullable
    public String getCodecPrivateData() {
        if (this.mCodecPrivateData == null) {
            this.mCodecPrivateData = this.mFragmentJni.getCodecPrivateData(this.mFragmentBuffer, this.mIsAudio, this.mBehaviorFlags);
        }
        return this.mCodecPrivateData;
    }

    @Override // com.amazon.avod.content.smoothstream.Fragment
    public void release(@Nullable SurgingResourcePool<DirectBuffer> surgingResourcePool) {
        DirectBuffer directBuffer;
        this.mFragmentJni.releaseFragment();
        if (surgingResourcePool == null || (directBuffer = this.mDirectBuffer) == null) {
            return;
        }
        surgingResourcePool.releaseResource(directBuffer);
    }

    @Override // com.amazon.avod.content.smoothstream.Fragment
    @Nullable
    public DirectBuffer getDirectBuffer() {
        return this.mDirectBuffer;
    }
}
