package com.audio.iflytek;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.audio.usbmic.UsbMicDevice;
import com.micphone.utils.Config;
import com.micphone.utils.ReflectUtil;
import d.c.a.a.a.a;
import d.c.a.a.b.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class iFlyTekRecord {
    private static final String PROPERTY_KEY = "service.thunder.mic.dumpdata";
    private static final int RECORD_EVENT_CONNECT = 1;
    private static final int RECORD_EVENT_SOURCE_ACTIVE = 2;
    private static final String TAG = "iFlyTecRecord";
    private EventHandler mHandler;
    a mRecordClient;
    private boolean mIsConnect = false;
    private boolean mActiveSource = false;
    private boolean mRecordStart = false;
    private boolean bufferState = true;
    private boolean mDumpPcm = false;
    private String pcmFileName = "/sdcard/record-raw.pcm";
    private FileOutputStream os = null;
    private d.c.a.a.c.a listener = new d.c.a.a.c.a() { // from class: com.audio.iflytek.iFlyTekRecord.1
        @Override // d.c.a.a.c.a
        public void onInitCallback(boolean z, int i) {
            Log.d(iFlyTekRecord.TAG, "b: " + z + " i:" + i);
            iFlyTekRecord.this.mIsConnect = z;
            if (iFlyTekRecord.this.mHandler != null) {
                iFlyTekRecord.this.mHandler.obtainMessage(1, Boolean.valueOf(z)).sendToTarget();
            }
        }
    };
    public a.AbstractBinderC0126a recordListener = new a.AbstractBinderC0126a() { // from class: com.audio.iflytek.iFlyTekRecord.2
        @Override // d.c.a.a.a.a
        public byte[] getRecordBuffer(int i) throws RemoteException {
            byte[] bArr = new byte[i];
            if (UsbMicDevice.loopbackRead(bArr) < 0) {
                Arrays.fill(bArr, (byte) 0);
            } else {
                if (iFlyTekRecord.this.os != null) {
                    try {
                        iFlyTekRecord.this.os.write(bArr, 0, i);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (!iFlyTekRecord.this.bufferState) {
                    iFlyTekRecord.this.setParam("record_buffer_state", "0");
                    iFlyTekRecord.this.bufferState = true;
                }
            }
            return bArr;
        }
    };

    private class EventHandler extends Handler {
        EventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                Log.e(iFlyTekRecord.TAG, "connect mIsConnect " + iFlyTekRecord.this.mIsConnect + " mActiveSource " + iFlyTekRecord.this.mActiveSource);
                if (iFlyTekRecord.this.mIsConnect && iFlyTekRecord.this.mActiveSource) {
                    iFlyTekRecord.this.startRecord();
                    return;
                } else {
                    if (iFlyTekRecord.this.mIsConnect) {
                        return;
                    }
                    iFlyTekRecord.this.stopRecord();
                    return;
                }
            }
            if (i != 2) {
                return;
            }
            Log.e(iFlyTekRecord.TAG, "RECORD_EVENT_SOURCE_ACTIVE " + message.arg1 + " mIsConnect " + iFlyTekRecord.this.mIsConnect + " mActiveSource " + iFlyTekRecord.this.mActiveSource);
            UsbMicDevice.activeSource(3, message.arg1);
            if (iFlyTekRecord.this.mIsConnect && iFlyTekRecord.this.mActiveSource) {
                iFlyTekRecord.this.startRecord();
            } else {
                if (iFlyTekRecord.this.mActiveSource) {
                    return;
                }
                iFlyTekRecord.this.stopRecord();
            }
        }
    }

    public iFlyTekRecord(Context context) {
        if (Config.getProductType().contains("xunfeipoc")) {
            this.mHandler = new EventHandler(Looper.getMainLooper());
            d.c.a.a.b.a aVarI = d.c.a.a.b.a.i();
            this.mRecordClient = aVarI;
            aVarI.k(context, this.listener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRecord() {
        if (this.mIsConnect && this.mRecordClient != null && !this.mRecordStart) {
            boolean zEquals = ReflectUtil.getProperty(PROPERTY_KEY, "enable").equals("1");
            this.mDumpPcm = zEquals;
            if (zEquals) {
                Log.e(TAG, "enable microphone data dump");
                File file = new File(this.pcmFileName);
                if (file.exists()) {
                    file.delete();
                }
                try {
                    file.createNewFile();
                    this.os = new FileOutputStream(file, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Log.e(TAG, "mRecordClient.startRecord");
            this.mRecordClient.m(2, this.recordListener);
        }
        this.mRecordStart = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopRecord() {
        if (this.mIsConnect && this.mRecordClient != null) {
            FileOutputStream fileOutputStream = this.os;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.os = null;
            }
            Log.e(TAG, "mRecordClient.stopRecord");
            this.mRecordClient.o();
        }
        this.mRecordStart = false;
    }

    public void activeSource(int i) {
        if (i == 1) {
            this.mActiveSource = true;
        } else {
            this.mActiveSource = false;
        }
        EventHandler eventHandler = this.mHandler;
        if (eventHandler != null) {
            eventHandler.obtainMessage(2, i, 0).sendToTarget();
        }
    }

    public void release() {
        EventHandler eventHandler = this.mHandler;
        if (eventHandler != null) {
            eventHandler.removeCallbacksAndMessages(null);
        }
        stopRecord();
        this.mIsConnect = false;
        this.mActiveSource = false;
        this.mHandler = null;
    }

    public void setParam(String str, String str2) {
        d.c.a.a.b.a aVar = this.mRecordClient;
        if (aVar != null) {
            aVar.l(str, str2);
        }
    }
}
