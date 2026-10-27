package com.amazon.avod.playback.sye.events;

import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.playback.sye.SyeDomainHolder;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.ISyeMetrics;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class BitstreamEvent implements MediaEvent {
    private final Asset mAsset;
    private final Update mUpdate;

    BitstreamEvent(@Nonnull Asset asset, @Nonnull Update update) {
        this.mAsset = (Asset) Preconditions.checkNotNull(asset, "asset");
        this.mUpdate = (Update) Preconditions.checkNotNull(update, "update");
    }

    static class Asset {
        private final String mConsumptionId;
        private final List<Type> mTypes;

        enum Type {
            Video,
            Audio,
            TimedText,
            DRMLicense
        }

        Asset(@Nonnull List<Type> list, @Nonnull String str) {
            this.mTypes = (List) Preconditions.checkNotNull(list, "types");
            this.mConsumptionId = (String) Preconditions.checkNotNull(str, "consumptionId");
        }
    }

    static class Update {
        private final Integer mBitrate;
        private final Bytes mBytes;
        private final Integer mDuration;
        private final Frames mFrames;
        private final Packets mPackets;

        @Nullable
        private final Resolution mResolution;
        private final Integer mRoundtrip;
        private final Server mServer;
        private final String mUrl;

        Update(@Nonnull String str, @Nonnull Server server, @Nonnull Integer num, @Nonnull Integer num2, @Nonnull Bytes bytes, @Nonnull Packets packets, @Nonnull Frames frames, int i, @Nullable Resolution resolution) {
            this.mUrl = (String) Preconditions.checkNotNull(str, ImagesContract.URL);
            this.mServer = (Server) Preconditions.checkNotNull(server, "server");
            this.mBitrate = (Integer) Preconditions.checkNotNull(num, "bitrate");
            this.mRoundtrip = (Integer) Preconditions.checkNotNull(num2, "roundtrip");
            this.mBytes = (Bytes) Preconditions.checkNotNull(bytes, "bytes");
            this.mPackets = (Packets) Preconditions.checkNotNull(packets, "packets");
            this.mFrames = (Frames) Preconditions.checkNotNull(frames, "frames");
            this.mDuration = Integer.valueOf(i);
            this.mResolution = resolution;
        }

        static class Bytes {
            private final Long mReceived;

            Bytes(@Nonnull Long l) {
                this.mReceived = (Long) Preconditions.checkNotNull(l, "received");
            }
        }

        static class Packets {
            private final Integer mLost;
            private final Integer mReceived;
            private final Integer mRetransmitted;

            Packets(@Nonnull Integer num, @Nonnull Integer num2, @Nonnull Integer num3) {
                this.mReceived = (Integer) Preconditions.checkNotNull(num, "received");
                this.mLost = (Integer) Preconditions.checkNotNull(num2, "lost");
                this.mRetransmitted = (Integer) Preconditions.checkNotNull(num3, "retransmitted");
            }
        }

        static class Frames {
            private final Integer mDamagedAudio;
            private final Integer mDamagedVideo;
            private final Integer mDecrypted;
            private final Integer mDivergentAudioTime;
            private final Integer mDivergentVideoTime;
            private final Integer mReceived;

            Frames(@Nonnull Integer num, @Nonnull Integer num2, @Nonnull Integer num3, @Nonnull Integer num4, @Nonnull Integer num5, @Nonnull Integer num6) {
                this.mReceived = (Integer) Preconditions.checkNotNull(num, "received");
                this.mDamagedVideo = (Integer) Preconditions.checkNotNull(num2, "damagedVideo");
                this.mDamagedAudio = (Integer) Preconditions.checkNotNull(num3, "damagedAudio");
                this.mDecrypted = (Integer) Preconditions.checkNotNull(num4, "decrypted");
                this.mDivergentVideoTime = (Integer) Preconditions.checkNotNull(num5, "divergentVideoTime");
                this.mDivergentAudioTime = (Integer) Preconditions.checkNotNull(num6, "divergentAudioTime");
            }
        }

        static class Server {
            private final String mHostname;
            private final String mIpv4;
            private final String mIpv6;

            Server(@Nonnull String str, @Nonnull String str2, @Nullable String str3) {
                this.mHostname = (String) Preconditions.checkNotNull(str, "hostname");
                this.mIpv4 = (String) Preconditions.checkNotNull(str2, "ipv4");
                this.mIpv6 = str3;
            }
        }

        static class Resolution {
            private final int mHeight;
            private final int mWidth;

            Resolution(int i, int i2) {
                this.mWidth = i;
                this.mHeight = i2;
            }
        }
    }

    @Override // com.amazon.avod.media.events.model.MediaEvent
    @Nonnull
    public MediaEvent.MediaEventType getMediaEventType() {
        return MediaEvent.MediaEventType.Bitstream;
    }

    public static class Factory {
        private long mBytesReceived = 0;
        private int mPktReceived = 0;
        private int mPktLost = 0;
        private int mPktRetransmitted = 0;
        private int mFramesReceived = 0;
        private int mDamagedVideo = 0;
        private int mDivergentVideoTime = 0;
        private int mDivergentAudioTime = 0;

        @Nonnull
        public BitstreamEvent fromMetrics(@Nonnull ISyeMetrics iSyeMetrics, @Nonnull SyeDomainHolder syeDomainHolder, int i, int i2, @Nullable VideoResolution videoResolution) throws IOException {
            int iMax = Math.max(iSyeMetrics.getRoundtripMillis(), 0);
            long jMax = Math.max(iSyeMetrics.getBytesReceived() - this.mBytesReceived, 0L);
            int iMax2 = Math.max((int) (iSyeMetrics.getPacketsReceived() - ((long) this.mPktReceived)), 0);
            int iMax3 = Math.max((int) (iSyeMetrics.getPacketsLost() - ((long) this.mPktLost)), 0);
            int iMax4 = Math.max((int) (iSyeMetrics.getPacketsRetransmitReceived() - ((long) this.mPktRetransmitted)), 0);
            int iMax5 = Math.max((int) (iSyeMetrics.getVideoFramesReceived() - ((long) this.mFramesReceived)), 0);
            int iMax6 = Math.max(iSyeMetrics.getVideoFramesDamaged() - this.mDamagedVideo, 0);
            int iMax7 = Math.max(iSyeMetrics.getVideoDivergenceMillis() - this.mDivergentVideoTime, 0);
            int iMax8 = Math.max(iSyeMetrics.getAudioDropoutMillis() - this.mDivergentAudioTime, 0);
            this.mBytesReceived += jMax;
            this.mPktReceived += iMax2;
            this.mPktLost += iMax3;
            this.mPktRetransmitted += iMax4;
            this.mFramesReceived += iMax5;
            this.mDamagedVideo += iMax6;
            this.mDivergentVideoTime += iMax7;
            this.mDivergentAudioTime += iMax8;
            Asset asset = new Asset(Arrays.asList(Asset.Type.Audio, Asset.Type.Video), syeDomainHolder.getConsumptionId());
            URL url = new URL(syeDomainHolder.getSyeDomain().getBaseUrl());
            return new BitstreamEvent(asset, new Update(syeDomainHolder.getSyeDomain().getBaseUrl(), new Update.Server(url.getHost(), InetAddress.getByName(url.getHost()).getHostAddress(), null), Integer.valueOf(i), Integer.valueOf(iMax), new Update.Bytes(Long.valueOf(jMax)), new Update.Packets(Integer.valueOf(iMax2), Integer.valueOf(iMax3), Integer.valueOf(iMax4)), new Update.Frames(Integer.valueOf(iMax5), Integer.valueOf(iMax6), 0, 0, Integer.valueOf(iMax7), Integer.valueOf(iMax8)), i2, videoResolution != null ? new Update.Resolution(videoResolution.getWidth(), videoResolution.getHeight()) : null));
        }
    }
}
