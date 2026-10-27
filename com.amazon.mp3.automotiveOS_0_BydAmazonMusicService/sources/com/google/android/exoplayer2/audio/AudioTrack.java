package com.google.android.exoplayer2.audio;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTimestamp;
import android.os.ConditionVariable;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.util.AmazonQuirks;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.wearable.WearableStatusCodes;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.LinkedList;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes6.dex */
public final class AudioTrack {
    private static final String TAG = "AudioTrack";
    public static boolean enablePreV21AudioSessionWorkaround = false;
    public static boolean failOnSpuriousAudioTimestamp = false;
    private static final boolean isLatencyQuirkEnabled = AmazonQuirks.isLatencyQuirkEnabled();
    private AudioAttributes audioAttributes;
    private final AudioCapabilities audioCapabilities;
    private AudioProcessor[] audioProcessors;
    private int audioSessionId;
    private boolean audioTimestampSet;
    private android.media.AudioTrack audioTrack;
    private final AudioTrackUtil audioTrackUtil;
    private ByteBuffer avSyncHeader;
    private final AudioProcessor[] availableAudioProcessors;
    private int bufferSize;
    private long bufferSizeUs;
    private int bytesUntilNextAvSync;
    private int channelConfig;
    private final ChannelMappingAudioProcessor channelMappingAudioProcessor;
    private int drainingAudioProcessorIndex;
    private PlaybackParameters drainingPlaybackParameters;
    private int encoding;
    private int framesPerEncodedSample;
    private Method getLatencyMethod;
    private boolean handledEndOfStream;
    private boolean hasData;
    private ByteBuffer inputBuffer;
    private android.media.AudioTrack keepSessionIdAudioTrack;
    private long lastFeedElapsedRealtimeMs;
    private long lastPlayheadSampleTimeUs;
    private long lastTimestampSampleTimeUs;
    private long latencyUs;
    private final Listener listener;
    private int nextPlayheadOffsetIndex;
    private ByteBuffer outputBuffer;
    private ByteBuffer[] outputBuffers;
    private int outputEncoding;
    private int outputPcmFrameSize;
    private boolean passthrough;
    private int pcmFrameSize;
    private PlaybackParameters playbackParameters;
    private final LinkedList<PlaybackParametersCheckpoint> playbackParametersCheckpoints;
    private long playbackParametersOffsetUs;
    private long playbackParametersPositionUs;
    private int playheadOffsetCount;
    private final long[] playheadOffsets;
    private boolean playing;
    private byte[] preV21OutputBuffer;
    private int preV21OutputBufferOffset;
    private long resumeSystemTimeUs;
    private int sampleRate;
    private long smoothedPlayheadOffsetUs;
    private final SonicAudioProcessor sonicAudioProcessor;
    private int startMediaTimeState;
    private long startMediaTimeUs;
    private long submittedEncodedFrames;
    private long submittedPcmBytes;
    private boolean tunneling;
    private float volume;
    private long writtenEncodedFrames;
    private long writtenPcmBytes;
    private final Logger log = new Logger(Logger.Module.Audio, TAG);
    private final boolean isLegacyPassthroughQuirckEnabled = AmazonQuirks.isDolbyPassthroughQuirkEnabled();
    private final ConditionVariable releasingConditionVariable = new ConditionVariable(true);

    public interface Listener {
        void onAudioSessionId(int i);

        void onPositionDiscontinuity();

        void onUnderrun(int i, long j, long j2);
    }

    public static final class ConfigurationException extends Exception {
        public ConfigurationException(Throwable th) {
            super(th);
        }

        public ConfigurationException(String str) {
            super(str);
        }
    }

    public static final class InitializationException extends Exception {
        public final int audioTrackState;

        public InitializationException(int i, int i2, int i3, int i4) {
            super("AudioTrack init failed: " + i + ", Config(" + i2 + ", " + i3 + ", " + i4 + ")");
            this.audioTrackState = i;
        }
    }

    public static final class WriteException extends Exception {
        public final int errorCode;

        public WriteException(int i) {
            super("AudioTrack write failed: " + i);
            this.errorCode = i;
        }
    }

    public static final class InvalidAudioTrackTimestampException extends RuntimeException {
        public InvalidAudioTrackTimestampException(String str) {
            super(str);
        }
    }

    public AudioTrack(AudioCapabilities audioCapabilities, AudioProcessor[] audioProcessorArr, Listener listener) {
        this.audioCapabilities = audioCapabilities;
        this.listener = listener;
        if (Util.SDK_INT >= 18) {
            try {
                this.getLatencyMethod = android.media.AudioTrack.class.getMethod("getLatency", null);
            } catch (Throwable th) {
                this.log.w("getLatencyMethod not found. " + th.getMessage());
            }
        }
        int i = Util.SDK_INT;
        if (i >= 19) {
            this.audioTrackUtil = new AudioTrackUtilV19(this.getLatencyMethod);
        } else {
            this.audioTrackUtil = new AudioTrackUtil(this.getLatencyMethod);
        }
        ChannelMappingAudioProcessor channelMappingAudioProcessor = new ChannelMappingAudioProcessor();
        this.channelMappingAudioProcessor = channelMappingAudioProcessor;
        SonicAudioProcessor sonicAudioProcessor = new SonicAudioProcessor();
        this.sonicAudioProcessor = sonicAudioProcessor;
        AudioProcessor[] audioProcessorArr2 = new AudioProcessor[audioProcessorArr.length + 3];
        this.availableAudioProcessors = audioProcessorArr2;
        audioProcessorArr2[0] = new ResamplingAudioProcessor();
        audioProcessorArr2[1] = channelMappingAudioProcessor;
        System.arraycopy(audioProcessorArr, 0, audioProcessorArr2, 2, audioProcessorArr.length);
        audioProcessorArr2[audioProcessorArr.length + 2] = sonicAudioProcessor;
        Logger logger = this.log;
        StringBuilder sb = new StringBuilder();
        sb.append("Amazon legacy corrections: Latency:");
        boolean z = isLatencyQuirkEnabled;
        String str = DebugKt.DEBUG_PROPERTY_VALUE_ON;
        sb.append(z ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        sb.append("; Dolby:");
        sb.append(this.isLegacyPassthroughQuirckEnabled ? str : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        sb.append(". On Sdk: ");
        sb.append(i);
        logger.i(sb.toString());
        this.playheadOffsets = new long[10];
        this.volume = 1.0f;
        this.startMediaTimeState = 0;
        this.audioAttributes = AudioAttributes.DEFAULT;
        this.audioSessionId = 0;
        this.playbackParameters = PlaybackParameters.DEFAULT;
        this.drainingAudioProcessorIndex = -1;
        this.audioProcessors = new AudioProcessor[0];
        this.outputBuffers = new ByteBuffer[0];
        this.playbackParametersCheckpoints = new LinkedList<>();
    }

    public boolean applyDolbyPassthroughQuirk() {
        return this.passthrough && this.isLegacyPassthroughQuirckEnabled;
    }

    public boolean isPassthroughSupported(String str) {
        AudioCapabilities audioCapabilities = this.audioCapabilities;
        return audioCapabilities != null && audioCapabilities.supportsEncoding(getEncodingForMimeType(str));
    }

    public long getCurrentPositionUs(boolean z) {
        long positionUs;
        if (!hasCurrentPositionUs()) {
            return Long.MIN_VALUE;
        }
        if (this.audioTrack.getPlayState() == 3 && !applyDolbyPassthroughQuirk()) {
            maybeSampleSyncParams();
        }
        long jNanoTime = System.nanoTime() / 1000;
        if (applyDolbyPassthroughQuirk()) {
            boolean zUpdateTimestamp = this.audioTrackUtil.updateTimestamp();
            this.audioTimestampSet = zUpdateTimestamp;
            positionUs = zUpdateTimestamp ? this.audioTrackUtil.getTimestampNanoTime() / 1000 : 0L;
            if (this.log.allowVerbose()) {
                this.log.v("positionUs = " + positionUs);
            }
        } else if (this.audioTimestampSet) {
            long timestampNanoTime = jNanoTime - (this.audioTrackUtil.getTimestampNanoTime() / 1000);
            long jDurationUsToFrames = durationUsToFrames(timestampNanoTime);
            long timestampFramePosition = this.audioTrackUtil.getTimestampFramePosition() + jDurationUsToFrames;
            long jFramesToDurationUs = framesToDurationUs(timestampFramePosition);
            if (this.log.allowVerbose()) {
                this.log.v("systemClockUs = " + jNanoTime + ", elapsedSinceTimestampUs = " + timestampNanoTime + ", elapsedSinceTimestampFrames = " + jDurationUsToFrames + ", elapsedFrames = " + timestampFramePosition + ", positionUs = " + jFramesToDurationUs);
            }
            positionUs = jFramesToDurationUs;
        } else {
            if (this.playheadOffsetCount == 0) {
                positionUs = this.audioTrackUtil.getPositionUs();
                if (this.log.allowVerbose()) {
                    this.log.v("positionUs = " + positionUs);
                }
            } else {
                long j = this.smoothedPlayheadOffsetUs + jNanoTime;
                if (this.log.allowVerbose()) {
                    this.log.v("startMediaTimeUs = " + this.startMediaTimeUs + " smoothedPlayheadOffsetUs = " + this.smoothedPlayheadOffsetUs + " systemClockUs = " + jNanoTime + " positionUs = " + j);
                }
                positionUs = j;
            }
            if (!z) {
                positionUs -= this.latencyUs;
            }
        }
        if (this.log.allowVerbose()) {
            this.log.v("positionUs = " + positionUs);
        }
        return this.startMediaTimeUs + applySpeedup(Math.min(positionUs, framesToDurationUs(getWrittenFrames())));
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void configure(java.lang.String r10, int r11, int r12, int r13, int r14, int[] r15) throws com.google.android.exoplayer2.audio.AudioTrack.ConfigurationException {
        /*
            Method dump skipped, instruction units count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.AudioTrack.configure(java.lang.String, int, int, int, int, int[]):void");
    }

    private void resetAudioProcessors() {
        ArrayList arrayList = new ArrayList();
        for (AudioProcessor audioProcessor : this.availableAudioProcessors) {
            if (audioProcessor.isActive()) {
                arrayList.add(audioProcessor);
            } else {
                audioProcessor.flush();
            }
        }
        int size = arrayList.size();
        this.audioProcessors = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[size]);
        this.outputBuffers = new ByteBuffer[size];
        for (int i = 0; i < size; i++) {
            AudioProcessor audioProcessor2 = this.audioProcessors[i];
            audioProcessor2.flush();
            this.outputBuffers[i] = audioProcessor2.getOutput();
        }
    }

    private void initialize() throws InitializationException {
        this.releasingConditionVariable.block();
        android.media.AudioTrack audioTrackInitializeAudioTrack = initializeAudioTrack();
        this.audioTrack = audioTrackInitializeAudioTrack;
        int audioSessionId = audioTrackInitializeAudioTrack.getAudioSessionId();
        if (enablePreV21AudioSessionWorkaround && Util.SDK_INT < 21) {
            android.media.AudioTrack audioTrack = this.keepSessionIdAudioTrack;
            if (audioTrack != null && audioSessionId != audioTrack.getAudioSessionId()) {
                releaseKeepSessionIdAudioTrack();
            }
            if (this.keepSessionIdAudioTrack == null) {
                this.keepSessionIdAudioTrack = initializeKeepSessionIdAudioTrack(audioSessionId);
            }
        }
        if (this.audioSessionId != audioSessionId) {
            this.audioSessionId = audioSessionId;
            this.listener.onAudioSessionId(audioSessionId);
        }
        this.audioTrackUtil.reconfigure(this.audioTrack, needsPassthroughWorkarounds());
        setVolumeInternal();
        this.hasData = false;
    }

    public void play() {
        this.playing = true;
        if (isInitialized()) {
            this.resumeSystemTimeUs = System.nanoTime() / 1000;
            this.audioTrackUtil.play();
            this.log.i("calling play");
            this.audioTrack.play();
        }
    }

    public void handleDiscontinuity() {
        if (this.startMediaTimeState == 1) {
            this.startMediaTimeState = 2;
        }
    }

    public boolean handleBuffer(ByteBuffer byteBuffer, long j) throws InitializationException, WriteException {
        int i;
        ByteBuffer byteBuffer2 = this.inputBuffer;
        Assertions.checkArgument(byteBuffer2 == null || byteBuffer == byteBuffer2);
        if (!isInitialized()) {
            initialize();
            if (this.playing) {
                play();
            }
        }
        if (needsPassthroughWorkarounds() && !applyDolbyPassthroughQuirk()) {
            if (this.audioTrack.getPlayState() == 2) {
                this.hasData = false;
                return false;
            }
            if (this.audioTrack.getPlayState() == 1 && this.audioTrackUtil.getPlaybackHeadPosition() != 0) {
                return false;
            }
        }
        boolean z = this.hasData;
        boolean zHasPendingData = hasPendingData();
        this.hasData = zHasPendingData;
        if (z && !zHasPendingData && this.audioTrack.getPlayState() != 1) {
            this.listener.onUnderrun(this.bufferSize, C.usToMs(this.bufferSizeUs), SystemClock.elapsedRealtime() - this.lastFeedElapsedRealtimeMs);
        }
        if (this.inputBuffer == null) {
            if (!byteBuffer.hasRemaining()) {
                return true;
            }
            if (this.passthrough && this.framesPerEncodedSample == 0) {
                this.framesPerEncodedSample = getFramesPerEncodedSample(this.outputEncoding, byteBuffer);
            }
            if (this.drainingPlaybackParameters != null) {
                if (!drainAudioProcessorsToEndOfStream()) {
                    return false;
                }
                this.playbackParametersCheckpoints.add(new PlaybackParametersCheckpoint(this.drainingPlaybackParameters, Math.max(0L, j), framesToDurationUs(getWrittenFrames())));
                this.drainingPlaybackParameters = null;
                resetAudioProcessors();
            }
            if (this.startMediaTimeState == 0) {
                this.startMediaTimeUs = Math.max(0L, j);
                this.log.i("Setting StartMediaTimeUs = " + this.startMediaTimeUs);
                this.startMediaTimeState = 1;
            } else {
                long jFramesToDurationUs = this.startMediaTimeUs + framesToDurationUs(getSubmittedFrames());
                if (this.startMediaTimeState != 1 || Math.abs(jFramesToDurationUs - j) <= 200000) {
                    i = 2;
                } else {
                    this.log.w("Discontinuity detected [expected " + jFramesToDurationUs + ", got " + j + "]");
                    i = 2;
                    this.startMediaTimeState = 2;
                }
                if (this.startMediaTimeState == i) {
                    this.startMediaTimeUs += j - jFramesToDurationUs;
                    this.startMediaTimeState = 1;
                    this.listener.onPositionDiscontinuity();
                }
            }
            if (this.passthrough) {
                this.submittedEncodedFrames += (long) this.framesPerEncodedSample;
            } else {
                this.submittedPcmBytes += (long) byteBuffer.remaining();
            }
            this.inputBuffer = byteBuffer;
        }
        if (this.passthrough) {
            writeBuffer(this.inputBuffer, j);
        } else {
            processBuffers(j);
        }
        if (this.inputBuffer.hasRemaining()) {
            return false;
        }
        this.inputBuffer = null;
        return true;
    }

    private void processBuffers(long j) throws WriteException {
        ByteBuffer byteBuffer;
        int length = this.audioProcessors.length;
        int i = length;
        while (i >= 0) {
            if (i > 0) {
                byteBuffer = this.outputBuffers[i - 1];
            } else {
                byteBuffer = this.inputBuffer;
                if (byteBuffer == null) {
                    byteBuffer = AudioProcessor.EMPTY_BUFFER;
                }
            }
            if (i == length) {
                writeBuffer(byteBuffer, j);
            } else {
                AudioProcessor audioProcessor = this.audioProcessors[i];
                audioProcessor.queueInput(byteBuffer);
                ByteBuffer output = audioProcessor.getOutput();
                this.outputBuffers[i] = output;
                if (output.hasRemaining()) {
                    i++;
                }
            }
            if (byteBuffer.hasRemaining()) {
                return;
            } else {
                i--;
            }
        }
    }

    private boolean writeBuffer(ByteBuffer byteBuffer, long j) throws WriteException {
        int iWriteNonBlockingV21;
        if (this.log.allowDebug()) {
            this.log.d("writeBuffer : offset = " + byteBuffer.position() + " limit = " + byteBuffer.limit() + " presentationTimeUs = " + j);
        }
        if (!byteBuffer.hasRemaining()) {
            return true;
        }
        ByteBuffer byteBuffer2 = this.outputBuffer;
        if (byteBuffer2 != null) {
            Assertions.checkArgument(byteBuffer2 == byteBuffer);
        } else {
            this.outputBuffer = byteBuffer;
            if (Util.SDK_INT < 21 || applyDolbyPassthroughQuirk()) {
                int iRemaining = byteBuffer.remaining();
                byte[] bArr = this.preV21OutputBuffer;
                if (bArr == null || bArr.length < iRemaining) {
                    this.preV21OutputBuffer = new byte[iRemaining];
                }
                int iPosition = byteBuffer.position();
                byteBuffer.get(this.preV21OutputBuffer, 0, iRemaining);
                byteBuffer.position(iPosition);
                this.preV21OutputBufferOffset = 0;
            }
        }
        int iRemaining2 = byteBuffer.remaining();
        if (applyDolbyPassthroughQuirk()) {
            iWriteNonBlockingV21 = this.audioTrack.write(this.preV21OutputBuffer, this.preV21OutputBufferOffset, iRemaining2);
            if (iWriteNonBlockingV21 > 0) {
                this.preV21OutputBufferOffset += iWriteNonBlockingV21;
                byteBuffer.position(byteBuffer.position() + iWriteNonBlockingV21);
            }
        } else if (Util.SDK_INT < 21) {
            int playbackHeadPosition = this.bufferSize - ((int) (this.writtenPcmBytes - (this.audioTrackUtil.getPlaybackHeadPosition() * ((long) this.outputPcmFrameSize))));
            if (playbackHeadPosition > 0) {
                iWriteNonBlockingV21 = this.audioTrack.write(this.preV21OutputBuffer, this.preV21OutputBufferOffset, Math.min(iRemaining2, playbackHeadPosition));
                if (iWriteNonBlockingV21 > 0) {
                    this.preV21OutputBufferOffset += iWriteNonBlockingV21;
                    byteBuffer.position(byteBuffer.position() + iWriteNonBlockingV21);
                }
            } else {
                iWriteNonBlockingV21 = 0;
            }
        } else if (this.tunneling) {
            Assertions.checkState(j != -9223372036854775807L);
            iWriteNonBlockingV21 = writeNonBlockingWithAvSyncV21(this.audioTrack, byteBuffer, iRemaining2, j);
        } else {
            iWriteNonBlockingV21 = writeNonBlockingV21(this.audioTrack, byteBuffer, iRemaining2);
        }
        this.lastFeedElapsedRealtimeMs = SystemClock.elapsedRealtime();
        if (iWriteNonBlockingV21 < 0) {
            throw new WriteException(iWriteNonBlockingV21);
        }
        boolean z = this.passthrough;
        if (!z) {
            this.writtenPcmBytes += (long) iWriteNonBlockingV21;
        }
        if (iWriteNonBlockingV21 != iRemaining2) {
            return false;
        }
        if (z) {
            this.writtenEncodedFrames += (long) this.framesPerEncodedSample;
        }
        this.outputBuffer = null;
        return true;
    }

    public void playToEndOfStream() throws WriteException {
        if (!this.handledEndOfStream && isInitialized() && drainAudioProcessorsToEndOfStream()) {
            if (applyDolbyPassthroughQuirk()) {
                this.audioTrack.stop();
            } else {
                this.audioTrackUtil.handleEndOfStream(getWrittenFrames());
            }
            this.bytesUntilNextAvSync = 0;
            this.handledEndOfStream = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0038  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0032 -> B:9:0x0012). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean drainAudioProcessorsToEndOfStream() throws com.google.android.exoplayer2.audio.AudioTrack.WriteException {
        /*
            r9 = this;
            int r0 = r9.drainingAudioProcessorIndex
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 != r1) goto L14
            boolean r0 = r9.passthrough
            if (r0 == 0) goto Lf
            com.google.android.exoplayer2.audio.AudioProcessor[] r0 = r9.audioProcessors
            int r0 = r0.length
            goto L10
        Lf:
            r0 = r3
        L10:
            r9.drainingAudioProcessorIndex = r0
        L12:
            r0 = r2
            goto L15
        L14:
            r0 = r3
        L15:
            int r4 = r9.drainingAudioProcessorIndex
            com.google.android.exoplayer2.audio.AudioProcessor[] r5 = r9.audioProcessors
            int r6 = r5.length
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 >= r6) goto L38
            r4 = r5[r4]
            if (r0 == 0) goto L28
            r4.queueEndOfStream()
        L28:
            r9.processBuffers(r7)
            boolean r0 = r4.isEnded()
            if (r0 != 0) goto L32
            return r3
        L32:
            int r0 = r9.drainingAudioProcessorIndex
            int r0 = r0 + r2
            r9.drainingAudioProcessorIndex = r0
            goto L12
        L38:
            java.nio.ByteBuffer r0 = r9.outputBuffer
            if (r0 == 0) goto L44
            r9.writeBuffer(r0, r7)
            java.nio.ByteBuffer r0 = r9.outputBuffer
            if (r0 == 0) goto L44
            return r3
        L44:
            r9.drainingAudioProcessorIndex = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.AudioTrack.drainAudioProcessorsToEndOfStream():boolean");
    }

    public boolean isEnded() {
        return !isInitialized() || (this.handledEndOfStream && (applyDolbyPassthroughQuirk() || !hasPendingData()));
    }

    public boolean hasPendingData() {
        if (!isInitialized()) {
            return false;
        }
        boolean z = applyDolbyPassthroughQuirk() || getWrittenFrames() > this.audioTrackUtil.getPlaybackHeadPosition() || overrideHasPendingData();
        if (this.log.allowVerbose()) {
            this.log.v("hasPendingData = " + z);
        }
        return z;
    }

    public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (this.passthrough) {
            PlaybackParameters playbackParameters2 = PlaybackParameters.DEFAULT;
            this.playbackParameters = playbackParameters2;
            return playbackParameters2;
        }
        PlaybackParameters playbackParameters3 = new PlaybackParameters(this.sonicAudioProcessor.setSpeed(playbackParameters.speed), this.sonicAudioProcessor.setPitch(playbackParameters.pitch));
        PlaybackParameters playbackParameters4 = this.drainingPlaybackParameters;
        if (playbackParameters4 == null) {
            playbackParameters4 = !this.playbackParametersCheckpoints.isEmpty() ? this.playbackParametersCheckpoints.getLast().playbackParameters : this.playbackParameters;
        }
        if (!playbackParameters3.equals(playbackParameters4)) {
            if (isInitialized()) {
                this.drainingPlaybackParameters = playbackParameters3;
            } else {
                this.playbackParameters = playbackParameters3;
            }
        }
        return this.playbackParameters;
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    public void setAudioAttributes(AudioAttributes audioAttributes) {
        if (this.audioAttributes.equals(audioAttributes)) {
            return;
        }
        this.audioAttributes = audioAttributes;
        if (this.tunneling) {
            return;
        }
        reset();
        this.audioSessionId = 0;
    }

    public void setAudioSessionId(int i) {
        if (this.audioSessionId != i) {
            this.audioSessionId = i;
            reset();
        }
    }

    public void enableTunnelingV21(int i) {
        Assertions.checkState(Util.SDK_INT >= 21);
        if (this.tunneling && this.audioSessionId == i) {
            return;
        }
        this.tunneling = true;
        this.audioSessionId = i;
        reset();
    }

    public void disableTunneling() {
        if (this.tunneling) {
            this.tunneling = false;
            this.audioSessionId = 0;
            reset();
        }
    }

    public void setVolume(float f) {
        if (this.volume != f) {
            this.log.i("setVolume: volume = " + f);
            this.volume = f;
            setVolumeInternal();
        }
    }

    private void setVolumeInternal() {
        if (isInitialized()) {
            if (Util.SDK_INT >= 21) {
                setVolumeInternalV21(this.audioTrack, this.volume);
            } else {
                setVolumeInternalV3(this.audioTrack, this.volume);
            }
        }
    }

    public void pause() {
        this.playing = false;
        if (isInitialized()) {
            this.log.i("calling pause");
            resetSyncParams();
            this.audioTrackUtil.pause();
        }
    }

    public void reset() {
        this.log.i("reset()");
        if (isInitialized()) {
            this.submittedPcmBytes = 0L;
            this.submittedEncodedFrames = 0L;
            this.writtenPcmBytes = 0L;
            this.writtenEncodedFrames = 0L;
            this.framesPerEncodedSample = 0;
            PlaybackParameters playbackParameters = this.drainingPlaybackParameters;
            if (playbackParameters != null) {
                this.playbackParameters = playbackParameters;
                this.drainingPlaybackParameters = null;
            } else if (!this.playbackParametersCheckpoints.isEmpty()) {
                this.playbackParameters = this.playbackParametersCheckpoints.getLast().playbackParameters;
            }
            this.playbackParametersCheckpoints.clear();
            this.playbackParametersOffsetUs = 0L;
            this.playbackParametersPositionUs = 0L;
            this.inputBuffer = null;
            this.outputBuffer = null;
            int i = 0;
            while (true) {
                AudioProcessor[] audioProcessorArr = this.audioProcessors;
                if (i >= audioProcessorArr.length) {
                    break;
                }
                AudioProcessor audioProcessor = audioProcessorArr[i];
                audioProcessor.flush();
                this.outputBuffers[i] = audioProcessor.getOutput();
                i++;
            }
            this.handledEndOfStream = false;
            this.drainingAudioProcessorIndex = -1;
            this.avSyncHeader = null;
            this.bytesUntilNextAvSync = 0;
            this.startMediaTimeState = 0;
            this.latencyUs = 0L;
            resetSyncParams();
            if (this.audioTrack.getPlayState() == 3) {
                this.log.i("calling pause");
                this.audioTrack.pause();
            }
            final android.media.AudioTrack audioTrack = this.audioTrack;
            this.audioTrack = null;
            this.audioTrackUtil.reconfigure(null, false);
            this.releasingConditionVariable.close();
            new Thread() { // from class: com.google.android.exoplayer2.audio.AudioTrack.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        AudioTrack.this.log.i("calling flush");
                        audioTrack.flush();
                        AudioTrack.this.log.i("calling release");
                        audioTrack.release();
                    } finally {
                        AudioTrack.this.releasingConditionVariable.open();
                    }
                }
            }.start();
        }
    }

    public void release() {
        reset();
        releaseKeepSessionIdAudioTrack();
        for (AudioProcessor audioProcessor : this.availableAudioProcessors) {
            audioProcessor.reset();
        }
        this.audioSessionId = 0;
        this.playing = false;
    }

    private void releaseKeepSessionIdAudioTrack() {
        final android.media.AudioTrack audioTrack = this.keepSessionIdAudioTrack;
        if (audioTrack == null) {
            return;
        }
        this.keepSessionIdAudioTrack = null;
        new Thread() { // from class: com.google.android.exoplayer2.audio.AudioTrack.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                AudioTrack.this.log.i("calling release");
                audioTrack.release();
            }
        }.start();
    }

    private boolean hasCurrentPositionUs() {
        return isInitialized() && this.startMediaTimeState != 0;
    }

    private long applySpeedup(long j) {
        long j2;
        long jScaleLargeTimestamp;
        while (!this.playbackParametersCheckpoints.isEmpty() && j >= this.playbackParametersCheckpoints.getFirst().positionUs) {
            PlaybackParametersCheckpoint playbackParametersCheckpointRemove = this.playbackParametersCheckpoints.remove();
            this.playbackParameters = playbackParametersCheckpointRemove.playbackParameters;
            this.playbackParametersPositionUs = playbackParametersCheckpointRemove.positionUs;
            this.playbackParametersOffsetUs = playbackParametersCheckpointRemove.mediaTimeUs - this.startMediaTimeUs;
        }
        if (this.playbackParameters.speed == 1.0f) {
            return (j + this.playbackParametersOffsetUs) - this.playbackParametersPositionUs;
        }
        if (this.playbackParametersCheckpoints.isEmpty() && this.sonicAudioProcessor.getOutputByteCount() >= 1024) {
            j2 = this.playbackParametersOffsetUs;
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(j - this.playbackParametersPositionUs, this.sonicAudioProcessor.getInputByteCount(), this.sonicAudioProcessor.getOutputByteCount());
        } else {
            j2 = this.playbackParametersOffsetUs;
            jScaleLargeTimestamp = (long) (((double) this.playbackParameters.speed) * (j - this.playbackParametersPositionUs));
        }
        return j2 + jScaleLargeTimestamp;
    }

    private void maybeSampleSyncParams() {
        long positionUs = this.audioTrackUtil.getPositionUs();
        if (positionUs == 0) {
            return;
        }
        if (this.log.allowVerbose()) {
            this.log.v("playbackPositionUs = " + positionUs);
        }
        long jNanoTime = System.nanoTime() / 1000;
        if (jNanoTime - this.lastPlayheadSampleTimeUs >= 30000) {
            long[] jArr = this.playheadOffsets;
            int i = this.nextPlayheadOffsetIndex;
            jArr[i] = positionUs - jNanoTime;
            this.nextPlayheadOffsetIndex = (i + 1) % 10;
            int i2 = this.playheadOffsetCount;
            if (i2 < 10) {
                this.playheadOffsetCount = i2 + 1;
            }
            this.lastPlayheadSampleTimeUs = jNanoTime;
            this.smoothedPlayheadOffsetUs = 0L;
            int i3 = 0;
            while (true) {
                int i4 = this.playheadOffsetCount;
                if (i3 >= i4) {
                    break;
                }
                this.smoothedPlayheadOffsetUs += this.playheadOffsets[i3] / ((long) i4);
                i3++;
            }
        }
        if (!needsPassthroughWorkarounds() && jNanoTime - this.lastTimestampSampleTimeUs >= 500000) {
            boolean zUpdateTimestamp = this.audioTrackUtil.updateTimestamp();
            this.audioTimestampSet = zUpdateTimestamp;
            if (zUpdateTimestamp) {
                long timestampNanoTime = this.audioTrackUtil.getTimestampNanoTime() / 1000;
                if (this.log.allowVerbose()) {
                    this.log.v("audioTimestampUs = " + timestampNanoTime);
                }
                long timestampFramePosition = this.audioTrackUtil.getTimestampFramePosition();
                if (timestampNanoTime < this.resumeSystemTimeUs) {
                    this.audioTimestampSet = false;
                    this.log.w("The timestamp corresponds to a time before the track was most recently resumed: " + timestampNanoTime + ", " + this.resumeSystemTimeUs);
                } else if (Math.abs(timestampNanoTime - jNanoTime) > 5000000) {
                    String str = "Spurious audio timestamp (system clock mismatch): " + timestampFramePosition + ", " + timestampNanoTime + ", " + jNanoTime + ", " + positionUs + ", " + getSubmittedFrames() + ", " + getWrittenFrames();
                    if (failOnSpuriousAudioTimestamp) {
                        throw new InvalidAudioTrackTimestampException(str);
                    }
                    this.log.w(str);
                    this.audioTimestampSet = false;
                } else if (Math.abs(framesToDurationUs(timestampFramePosition) - positionUs) > 5000000) {
                    String str2 = "Spurious audio timestamp (frame position mismatch): " + timestampFramePosition + ", " + timestampNanoTime + ", " + jNanoTime + ", " + positionUs + ", " + getSubmittedFrames() + ", " + getWrittenFrames();
                    if (failOnSpuriousAudioTimestamp) {
                        throw new InvalidAudioTrackTimestampException(str2);
                    }
                    this.log.w(str2);
                    this.audioTimestampSet = false;
                }
            }
            if (isLatencyQuirkEnabled) {
                this.latencyUs = AmazonQuirks.getAudioHWLatency();
            } else {
                Method method = this.getLatencyMethod;
                if (method != null && !this.passthrough) {
                    try {
                        long jIntValue = (((long) ((Integer) method.invoke(this.audioTrack, null)).intValue()) * 1000) - this.bufferSizeUs;
                        this.latencyUs = jIntValue;
                        long jMax = Math.max(jIntValue, 0L);
                        this.latencyUs = jMax;
                        if (jMax > 5000000) {
                            this.log.w("Ignoring impossibly large audio latency: " + this.latencyUs);
                            this.latencyUs = 0L;
                        }
                    } catch (Exception unused) {
                        this.getLatencyMethod = null;
                    }
                }
            }
            this.lastTimestampSampleTimeUs = jNanoTime;
        }
    }

    private boolean isInitialized() {
        return this.audioTrack != null;
    }

    private long framesToDurationUs(long j) {
        return (j * 1000000) / ((long) this.sampleRate);
    }

    private long durationUsToFrames(long j) {
        return (j * ((long) this.sampleRate)) / 1000000;
    }

    private long getSubmittedFrames() {
        return this.passthrough ? this.submittedEncodedFrames : this.submittedPcmBytes / ((long) this.pcmFrameSize);
    }

    private long getWrittenFrames() {
        return this.passthrough ? this.writtenEncodedFrames : this.writtenPcmBytes / ((long) this.outputPcmFrameSize);
    }

    private void resetSyncParams() {
        this.smoothedPlayheadOffsetUs = 0L;
        this.playheadOffsetCount = 0;
        this.nextPlayheadOffsetIndex = 0;
        this.lastPlayheadSampleTimeUs = 0L;
        this.audioTimestampSet = false;
        this.lastTimestampSampleTimeUs = 0L;
    }

    private boolean needsPassthroughWorkarounds() {
        int i;
        return Util.SDK_INT < 23 && ((i = this.outputEncoding) == 5 || i == 6);
    }

    private boolean overrideHasPendingData() {
        if (needsPassthroughWorkarounds() && this.audioTrack.getPlayState() == 2 && this.audioTrack.getPlaybackHeadPosition() == 0) {
            return true;
        }
        return AmazonQuirks.isLatencyQuirkEnabled() && this.audioTrack.getPlayState() == 3 && (System.nanoTime() / 1000) - this.resumeSystemTimeUs < 1000000;
    }

    private android.media.AudioTrack initializeAudioTrack() throws InitializationException {
        android.media.AudioTrack audioTrack;
        this.log.i("initializeAudioTrack");
        if (Util.SDK_INT >= 21) {
            audioTrack = createAudioTrackV21();
        } else {
            int streamTypeForAudioUsage = Util.getStreamTypeForAudioUsage(this.audioAttributes.usage);
            if (this.audioSessionId == 0) {
                if (applyDolbyPassthroughQuirk()) {
                    audioTrack = new DolbyPassthroughAudioTrack(streamTypeForAudioUsage, this.sampleRate, this.channelConfig, this.outputEncoding, this.bufferSize, 1);
                } else {
                    audioTrack = new android.media.AudioTrack(streamTypeForAudioUsage, this.sampleRate, this.channelConfig, this.outputEncoding, this.bufferSize, 1);
                }
            } else if (applyDolbyPassthroughQuirk()) {
                audioTrack = new DolbyPassthroughAudioTrack(streamTypeForAudioUsage, this.sampleRate, this.channelConfig, this.outputEncoding, this.bufferSize, 1, this.audioSessionId);
            } else {
                audioTrack = new android.media.AudioTrack(streamTypeForAudioUsage, this.sampleRate, this.channelConfig, this.outputEncoding, this.bufferSize, 1, this.audioSessionId);
            }
        }
        int state = audioTrack.getState();
        if (state == 1) {
            return audioTrack;
        }
        try {
            audioTrack.release();
        } catch (Exception unused) {
        }
        throw new InitializationException(state, this.sampleRate, this.channelConfig, this.bufferSize);
    }

    @TargetApi(21)
    private android.media.AudioTrack createAudioTrackV21() {
        android.media.AudioAttributes audioAttributesV21;
        if (this.tunneling) {
            audioAttributesV21 = new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build();
        } else {
            audioAttributesV21 = this.audioAttributes.getAudioAttributesV21();
        }
        android.media.AudioAttributes audioAttributes = audioAttributesV21;
        AudioFormat audioFormatBuild = new AudioFormat.Builder().setChannelMask(this.channelConfig).setEncoding(this.outputEncoding).setSampleRate(this.sampleRate).build();
        int i = this.audioSessionId;
        if (i == 0) {
            i = 0;
        }
        int i2 = i;
        if (applyDolbyPassthroughQuirk()) {
            return new DolbyPassthroughAudioTrack(audioAttributes, audioFormatBuild, this.bufferSize, 1, i2);
        }
        return new android.media.AudioTrack(audioAttributes, audioFormatBuild, this.bufferSize, 1, i2);
    }

    private android.media.AudioTrack initializeKeepSessionIdAudioTrack(int i) {
        return new android.media.AudioTrack(3, WearableStatusCodes.TARGET_NODE_NOT_CONNECTED, 4, 2, 2, 0, i);
    }

    private static int getEncodingForMimeType(String str) {
        str.hashCode();
        switch (str) {
            case "audio/vnd.dts":
                return 7;
            case "audio/ac3":
                return 5;
            case "audio/ec3":
            case "audio/eac3":
                return 6;
            case "audio/vnd.dts.hd":
                return 8;
            default:
                return 0;
        }
    }

    private static int getFramesPerEncodedSample(int i, ByteBuffer byteBuffer) {
        if (i == 7 || i == 8) {
            return DtsUtil.parseDtsAudioSampleCount(byteBuffer);
        }
        if (i == 5) {
            return Ac3Util.getAc3SyncframeAudioSampleCount();
        }
        if (i == 6) {
            return Ac3Util.parseEAc3SyncframeAudioSampleCount(byteBuffer);
        }
        throw new IllegalStateException("Unexpected audio encoding: " + i);
    }

    @TargetApi(21)
    private static int writeNonBlockingV21(android.media.AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
        return audioTrack.write(byteBuffer, i, 1);
    }

    @TargetApi(21)
    private int writeNonBlockingWithAvSyncV21(android.media.AudioTrack audioTrack, ByteBuffer byteBuffer, int i, long j) {
        if (this.avSyncHeader == null) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16);
            this.avSyncHeader = byteBufferAllocate;
            byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
            this.avSyncHeader.putInt(1431633921);
        }
        if (this.bytesUntilNextAvSync == 0) {
            this.avSyncHeader.putInt(4, i);
            this.avSyncHeader.putLong(8, j * 1000);
            this.avSyncHeader.position(0);
            this.bytesUntilNextAvSync = i;
        }
        int iRemaining = this.avSyncHeader.remaining();
        if (iRemaining > 0) {
            int iWrite = audioTrack.write(this.avSyncHeader, iRemaining, 1);
            if (iWrite < 0) {
                this.bytesUntilNextAvSync = 0;
                return iWrite;
            }
            if (iWrite < iRemaining) {
                return 0;
            }
        }
        int iWriteNonBlockingV21 = writeNonBlockingV21(audioTrack, byteBuffer, i);
        if (iWriteNonBlockingV21 < 0) {
            this.bytesUntilNextAvSync = 0;
            return iWriteNonBlockingV21;
        }
        this.bytesUntilNextAvSync -= iWriteNonBlockingV21;
        return iWriteNonBlockingV21;
    }

    @TargetApi(21)
    private static void setVolumeInternalV21(android.media.AudioTrack audioTrack, float f) {
        audioTrack.setVolume(f);
    }

    private static void setVolumeInternalV3(android.media.AudioTrack audioTrack, float f) {
        audioTrack.setStereoVolume(f, f);
    }

    private static class AudioTrackUtil {
        protected android.media.AudioTrack audioTrack;
        private long endPlaybackHeadPosition;
        private final Method getLatencyMethod;
        private long lastRawPlaybackHeadPosition;
        private final Logger log = new Logger(Logger.Module.Audio, AudioTrack.TAG);
        private boolean needsPassthroughWorkaround;
        private long passthroughWorkaroundPauseOffset;
        private long rawPlaybackHeadWrapCount;
        private long resumeTime;
        private int sampleRate;
        private long stopPlaybackHeadPosition;
        private long stopTimestampUs;

        public boolean updateTimestamp() {
            return false;
        }

        public AudioTrackUtil(Method method) {
            this.getLatencyMethod = method;
        }

        private int getAudioSWLatencies() {
            Method method = this.getLatencyMethod;
            if (method == null) {
                return 0;
            }
            try {
                return ((Integer) method.invoke(this.audioTrack, null)).intValue() * (this.sampleRate / 1000);
            } catch (Exception unused) {
                return 0;
            }
        }

        public void play() {
            this.resumeTime = SystemClock.uptimeMillis();
        }

        public void reconfigure(android.media.AudioTrack audioTrack, boolean z) {
            this.audioTrack = audioTrack;
            this.needsPassthroughWorkaround = z;
            this.stopTimestampUs = -9223372036854775807L;
            this.lastRawPlaybackHeadPosition = 0L;
            this.rawPlaybackHeadWrapCount = 0L;
            this.passthroughWorkaroundPauseOffset = 0L;
            if (audioTrack != null) {
                this.sampleRate = audioTrack.getSampleRate();
            }
        }

        public void handleEndOfStream(long j) {
            this.stopPlaybackHeadPosition = getPlaybackHeadPosition();
            this.stopTimestampUs = SystemClock.elapsedRealtime() * 1000;
            this.endPlaybackHeadPosition = j;
            this.log.i("calling stop");
            this.audioTrack.stop();
        }

        public void pause() {
            if (this.stopTimestampUs != -9223372036854775807L) {
                return;
            }
            this.log.i("calling pause");
            this.audioTrack.pause();
        }

        public long getPlaybackHeadPosition() {
            long j;
            if (this.stopTimestampUs != -9223372036854775807L) {
                return Math.min(this.endPlaybackHeadPosition, this.stopPlaybackHeadPosition + ((((SystemClock.elapsedRealtime() * 1000) - this.stopTimestampUs) * ((long) this.sampleRate)) / 1000000));
            }
            int playState = this.audioTrack.getPlayState();
            if (playState == 1) {
                return 0L;
            }
            if (AudioTrack.isLatencyQuirkEnabled) {
                int playbackHeadPosition = this.audioTrack.getPlaybackHeadPosition();
                int playState2 = this.audioTrack.getPlayState();
                if (playState2 == 3 || (playState2 == 2 && playbackHeadPosition != 0)) {
                    playbackHeadPosition += getAudioSWLatencies();
                }
                if (playbackHeadPosition < 0 && SystemClock.uptimeMillis() - this.resumeTime < 1000) {
                    playbackHeadPosition = 0;
                    this.log.i("php is negative during latency stabilization phase ...resetting to 0");
                }
                j = ((long) playbackHeadPosition) & 4294967295L;
                long j2 = this.lastRawPlaybackHeadPosition;
                if (j2 > j && j2 > 2147483647L && j2 - j >= 2147483647L) {
                    this.log.i("The playback head position wrapped around");
                    this.rawPlaybackHeadWrapCount++;
                }
            } else {
                long playbackHeadPosition2 = ((long) this.audioTrack.getPlaybackHeadPosition()) & 4294967295L;
                if (this.log.allowVerbose()) {
                    this.log.v("rawPlaybackHeadPosition = " + playbackHeadPosition2);
                }
                if (this.needsPassthroughWorkaround) {
                    if (playState == 2 && playbackHeadPosition2 == 0) {
                        this.passthroughWorkaroundPauseOffset = this.lastRawPlaybackHeadPosition;
                    }
                    playbackHeadPosition2 += this.passthroughWorkaroundPauseOffset;
                }
                j = playbackHeadPosition2;
                if (this.lastRawPlaybackHeadPosition > j) {
                    this.rawPlaybackHeadWrapCount++;
                }
            }
            this.lastRawPlaybackHeadPosition = j;
            return j + (this.rawPlaybackHeadWrapCount << 32);
        }

        public long getPositionUs() {
            return (getPlaybackHeadPosition() * 1000000) / ((long) this.sampleRate);
        }

        public long getTimestampNanoTime() {
            throw new UnsupportedOperationException();
        }

        public long getTimestampFramePosition() {
            throw new UnsupportedOperationException();
        }
    }

    @TargetApi(19)
    private static class AudioTrackUtilV19 extends AudioTrackUtil {
        private final AudioTimestamp audioTimestamp;
        private long lastRawTimestampFramePosition;
        private long lastTimestampFramePosition;
        private long rawTimestampFramePositionWrapCount;

        public AudioTrackUtilV19(Method method) {
            super(method);
            this.audioTimestamp = new AudioTimestamp();
        }

        @Override // com.google.android.exoplayer2.audio.AudioTrack.AudioTrackUtil
        public void reconfigure(android.media.AudioTrack audioTrack, boolean z) {
            super.reconfigure(audioTrack, z);
            this.rawTimestampFramePositionWrapCount = 0L;
            this.lastRawTimestampFramePosition = 0L;
            this.lastTimestampFramePosition = 0L;
        }

        @Override // com.google.android.exoplayer2.audio.AudioTrack.AudioTrackUtil
        public boolean updateTimestamp() {
            boolean timestamp = this.audioTrack.getTimestamp(this.audioTimestamp);
            if (timestamp) {
                long j = this.audioTimestamp.framePosition;
                if (this.lastRawTimestampFramePosition > j) {
                    this.rawTimestampFramePositionWrapCount++;
                }
                this.lastRawTimestampFramePosition = j;
                this.lastTimestampFramePosition = j + (this.rawTimestampFramePositionWrapCount << 32);
            }
            return timestamp;
        }

        @Override // com.google.android.exoplayer2.audio.AudioTrack.AudioTrackUtil
        public long getTimestampNanoTime() {
            return this.audioTimestamp.nanoTime;
        }

        @Override // com.google.android.exoplayer2.audio.AudioTrack.AudioTrackUtil
        public long getTimestampFramePosition() {
            return this.lastTimestampFramePosition;
        }
    }

    private static final class PlaybackParametersCheckpoint {
        private final long mediaTimeUs;
        private final PlaybackParameters playbackParameters;
        private final long positionUs;

        private PlaybackParametersCheckpoint(PlaybackParameters playbackParameters, long j, long j2) {
            this.playbackParameters = playbackParameters;
            this.mediaTimeUs = j;
            this.positionUs = j2;
        }
    }
}
