package com.byd.vrassistant.manager;

import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioRecord;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.framework.audio.AudioChannel;
import cerence.ark.assistant.framework.audio.AudioFormat;
import cerence.ark.assistant.framework.audio.recorder.IAudioRecorder;
import cerence.ark.assistant.library.Utils;
import com.byd.vrassistant.eventbus.AutoRecorderEvent;
import com.byd.vrassistant.utils.RLog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class CustomAudioRecorder implements IAudioRecorder {
    private static final String TAG = "NEU_CustomRecorder";
    private static int sampleRateInHz = 16000;
    private byte[] mAudioBuffer;
    private byte[] mAudioBuffer_inter;
    private byte[] mAudioBuffer_non_inter;
    private AudioDeviceInfo[] mAudioDeviceInfo;
    private AudioRecord mAudioRecord;
    FileInputStream pcmStream = null;

    protected int onGetAudioSource() {
        return 6;
    }

    public CustomAudioRecorder() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onMessage(AutoRecorderEvent autoRecorderEvent) {
        synchronized (this) {
            if (autoRecorderEvent.event == 0) {
                try {
                    try {
                        try {
                            FileInputStream fileInputStream = this.pcmStream;
                            if (fileInputStream != null && fileInputStream.available() > 0) {
                                wait();
                            }
                            this.pcmStream = new FileInputStream(autoRecorderEvent.filename);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            this.pcmStream = null;
                        }
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        this.pcmStream = null;
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                    this.pcmStream = null;
                }
            } else if (autoRecorderEvent.event == 1) {
                FileInputStream fileInputStream2 = this.pcmStream;
                try {
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                            notifyAll();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            notifyAll();
                        }
                        this.pcmStream = null;
                    }
                } catch (Throwable th) {
                    notifyAll();
                    this.pcmStream = null;
                    throw th;
                }
            }
        }
    }

    @Override // cerence.ark.assistant.framework.audio.recorder.IAudioRecorder
    public boolean onInitialize(AudioChannel audioChannel, AudioFormat audioFormat, int i) {
        boolean z = false;
        if (!Utils.checkSelfPermission("android.permission.RECORD_AUDIO")) {
            RLog.i(TAG, "Initialize audio recorder failed, the android.permission.RECORD_AUDIO was denied.");
            return false;
        }
        RLog.i(TAG, String.format("channelCount  = %s", 12));
        RLog.i(TAG, String.format("audioFormatInt  = %s", 2));
        int minBufferSize = AudioRecord.getMinBufferSize(sampleRateInHz, 12, 2);
        RLog.i(TAG, String.format("minBufferSize  = %s", Integer.valueOf(minBufferSize)));
        if (minBufferSize < 0) {
            RLog.i(TAG, String.format("getMinBufferSize error = %s", Integer.valueOf(minBufferSize)));
            minBufferSize = (sampleRateInHz / 1000) * (audioFormat.getValue() / 8) * 12 * 600;
        }
        try {
            AudioRecord audioRecordBuild = new AudioRecord.Builder().setAudioFormat(new AudioFormat.Builder().setChannelIndexMask(255).setSampleRate(sampleRateInHz).setEncoding(2).build()).setAudioSource(6).setBufferSizeInBytes(minBufferSize * 4).build();
            this.mAudioRecord = audioRecordBuild;
            int state = audioRecordBuild.getState();
            if (state == 1) {
                this.mAudioRecord.startRecording();
                RLog.i(TAG, "init audio record success");
                z = true;
            } else {
                RLog.i(TAG, String.format("audio record init error = %s", Integer.valueOf(state)));
            }
        } catch (Exception e) {
            RLog.i(TAG, "init audio record exception", e);
        }
        return z;
    }

    protected byte[] onGetAudioEx(int i) {
        int i2;
        synchronized (this) {
            if (this.mAudioRecord == null) {
                RLog.i(TAG, "onGetAudioEx: audio recorder is null");
                return null;
            }
            byte[] bArr = this.mAudioBuffer_inter;
            if (bArr == null || bArr.length != i) {
                this.mAudioBuffer_inter = new byte[i];
            }
            byte[] bArr2 = this.mAudioBuffer_non_inter;
            if (bArr2 == null || bArr2.length != i) {
                this.mAudioBuffer_non_inter = new byte[i];
            }
            if (this.pcmStream != null) {
                try {
                    Arrays.fill(this.mAudioBuffer_inter, (byte) 0);
                    i2 = this.pcmStream.read(this.mAudioBuffer_inter, 0, i);
                } catch (IOException | InterruptedException e) {
                    e = e;
                    i2 = 0;
                }
                try {
                    Thread.sleep(16L);
                    for (int i3 = 0; i3 < i2; i3++) {
                        byte b = this.mAudioBuffer_inter[i3];
                    }
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                } catch (InterruptedException e3) {
                    e = e3;
                    e.printStackTrace();
                }
            } else {
                i2 = 0;
            }
            if (i2 == i) {
                return this.mAudioBuffer_inter;
            }
            if (i2 > 0) {
                Logger.warn(TAG, String.format("just read partial data, bytesRead = %s", Integer.valueOf(i2)));
                byte[] bArr3 = new byte[i2];
                System.arraycopy(this.mAudioBuffer_inter, 0, bArr3, 0, i2);
                return bArr3;
            }
            FileInputStream fileInputStream = this.pcmStream;
            if (fileInputStream != null) {
                try {
                    try {
                        fileInputStream.close();
                        this.pcmStream = null;
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        this.pcmStream = null;
                    }
                    notifyAll();
                } catch (Throwable th) {
                    this.pcmStream = null;
                    notifyAll();
                    throw th;
                }
            }
            return null;
        }
    }

    @Override // cerence.ark.assistant.framework.audio.recorder.IAudioRecorder
    public byte[] onGetAudio(int i) {
        if (VoiceAssistantManager.get().isAutoBatchStart()) {
            return onGetAudioEx(i);
        }
        AudioRecord audioRecord = this.mAudioRecord;
        if (audioRecord == null) {
            RLog.i(TAG, "onGetAudio: audio recorder is null");
            return null;
        }
        byte[] bArr = this.mAudioBuffer;
        if (bArr == null || bArr.length != i) {
            this.mAudioBuffer = new byte[i];
        }
        int i2 = audioRecord.read(this.mAudioBuffer, 0, i);
        if (i2 == i) {
            return this.mAudioBuffer;
        }
        if (i2 <= 0) {
            RLog.i(TAG, String.format("read audio error, bytesRead = %s", Integer.valueOf(i2)));
            return null;
        }
        RLog.i(TAG, String.format("just read partial data, bytesRead = %s", Integer.valueOf(i2)));
        byte[] bArr2 = new byte[i2];
        System.arraycopy(this.mAudioBuffer, 0, bArr2, 0, i2);
        return bArr2;
    }

    @Override // cerence.ark.assistant.framework.audio.recorder.IAudioRecorder
    public void onRelease() {
        RLog.i(TAG, String.format("%s: onRelease", TAG));
        if (isInitAudioRecord()) {
            this.mAudioRecord.release();
            this.mAudioRecord = null;
        }
        this.mAudioBuffer = null;
        this.mAudioBuffer_inter = null;
        this.mAudioBuffer_non_inter = null;
    }

    private boolean isInitAudioRecord() {
        AudioRecord audioRecord = this.mAudioRecord;
        return audioRecord != null && audioRecord.getState() == 1;
    }
}
