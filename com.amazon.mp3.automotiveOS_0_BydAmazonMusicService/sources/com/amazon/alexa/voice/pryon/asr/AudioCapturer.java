package com.amazon.alexa.voice.pryon.asr;

import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.NoiseSuppressor;
import android.util.Log;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class AudioCapturer {
    private static final String TAG = "AudioCapturer";
    private short[] audioBuffer;
    private AudioRecord audioRecord;
    private final int bufferSize;
    private final int bytesBufferSize;
    private AcousticEchoCanceler acousticEchoCanceler = null;
    private NoiseSuppressor noiseSuppressor = null;

    public AudioCapturer(int i, boolean z, boolean z2) {
        this.bufferSize = i;
        this.bytesBufferSize = i * 2;
        this.audioBuffer = new short[i];
        try {
            AudioRecord audioRecordBuildAudioRecord = buildAudioRecord();
            this.audioRecord = audioRecordBuildAudioRecord;
            if (z) {
                requestAcousticEchoCanceler(audioRecordBuildAudioRecord.getAudioSessionId());
            }
            if (z2) {
                requestNoiseSuppressor(this.audioRecord.getAudioSessionId());
            }
            try {
                this.audioRecord.startRecording();
            } catch (IllegalStateException e) {
                String str = "AudioRecord startRecording() failed. " + logCurrentAudioRecord();
                this.audioRecord.release();
                throw new AudioRecordException(str + logTestAudioRecord(44100), e);
            }
        } catch (IllegalArgumentException e2) {
            throw new AudioRecordException("Unable to instantiate test Audio Record object with audioSource: 1, sampleRate: 16000, channel: 16, encoding: 2, bufferSize: " + this.bytesBufferSize, e2);
        }
    }

    private AudioRecord buildAudioRecord() {
        Log.i(TAG, "AudioCapturer AudioRecord is constructed.");
        return new AudioRecord(1, 16000, 16, 2, this.bytesBufferSize);
    }

    private void requestAcousticEchoCanceler(int i) {
        if (AcousticEchoCanceler.isAvailable()) {
            AcousticEchoCanceler acousticEchoCancelerCreate = AcousticEchoCanceler.create(i);
            this.acousticEchoCanceler = acousticEchoCancelerCreate;
            acousticEchoCancelerCreate.setEnabled(true);
            return;
        }
        Log.i(TAG, "AcousticEchoCanceller is not available");
    }

    private void requestNoiseSuppressor(int i) {
        if (NoiseSuppressor.isAvailable()) {
            NoiseSuppressor noiseSuppressorCreate = NoiseSuppressor.create(i);
            this.noiseSuppressor = noiseSuppressorCreate;
            if (noiseSuppressorCreate != null) {
                noiseSuppressorCreate.setEnabled(true);
                return;
            } else {
                Log.i(TAG, "NoiseSuppressor.create() returned null.");
                return;
            }
        }
        Log.i(TAG, "NoiseSuppressor is not available");
    }

    private void releaseAcousticEchoCanceler() {
        AcousticEchoCanceler acousticEchoCanceler = this.acousticEchoCanceler;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.setEnabled(false);
            this.acousticEchoCanceler.release();
            this.acousticEchoCanceler = null;
        }
    }

    private void releaseNoiseSuppressor() {
        NoiseSuppressor noiseSuppressor = this.noiseSuppressor;
        if (noiseSuppressor != null) {
            noiseSuppressor.setEnabled(false);
            this.noiseSuppressor.release();
            this.noiseSuppressor = null;
        }
    }

    public void release() {
        Log.i(TAG, "AudioCapturer AudioRecord.stop() and AudioRecord.release() are called.");
        releaseAcousticEchoCanceler();
        releaseNoiseSuppressor();
        this.audioRecord.stop();
        this.audioRecord.release();
        this.audioRecord = null;
    }

    public short[] read() throws IOException {
        int i = this.audioRecord.read(this.audioBuffer, 0, this.bufferSize);
        if (this.bufferSize != i) {
            throw new IOException("Buffer underrun -- wanted " + this.bufferSize + " many shorts, but got " + i);
        }
        return this.audioBuffer;
    }

    private String logCurrentAudioRecord() {
        return checkAudioRecord(this.audioRecord, this.bytesBufferSize);
    }

    private String logTestAudioRecord(int i) {
        AudioRecord audioRecord;
        String str = "Testing if device supports sampling rate of " + i + ". ";
        try {
            audioRecord = new AudioRecord(1, i, 16, 2, this.bytesBufferSize);
        } catch (IllegalArgumentException unused) {
            str = str + "Unable to instantiate test Audio Record object with " + buildAudioRecordData(1, i, 16, 2, this.bytesBufferSize);
            audioRecord = null;
        }
        if (audioRecord == null) {
            return str;
        }
        String str2 = str + checkAudioRecord(audioRecord, this.bytesBufferSize);
        audioRecord.release();
        return str2;
    }

    private String checkAudioRecord(AudioRecord audioRecord, int i) {
        String str = buildAudioRecordData(audioRecord.getAudioSource(), audioRecord.getSampleRate(), audioRecord.getChannelConfiguration(), audioRecord.getAudioFormat(), i) + ", minRequiredBufferSize: " + AudioRecord.getMinBufferSize(audioRecord.getSampleRate(), audioRecord.getChannelConfiguration(), audioRecord.getAudioFormat()) + ", ";
        if (audioRecord.getState() == 1) {
            return str + "initialized: true} ";
        }
        return str + "initialized: false} ";
    }

    private String buildAudioRecordData(int i, int i2, int i3, int i4, int i5) {
        return "AudioRecord {audioSource: " + i + ", sampleRate: " + i2 + ", channel: " + i3 + ", encoding: " + i4 + ", bufferSize: " + i5;
    }
}
