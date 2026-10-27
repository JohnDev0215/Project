package org.chromium.media.midi;

import android.content.Context;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.os.Handler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media::midi")
class MidiManagerAndroid {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final MidiManager mManager;
    private final long mNativeManagerPointer;
    private boolean mIsInitializing = true;
    private final List<MidiDeviceAndroid> mDevices = new ArrayList();
    private final Set<MidiDeviceInfo> mPendingDevices = new HashSet();
    private final Handler mHandler = new Handler(ThreadUtils.getUiThreadLooper());

    static native void nativeOnAttached(long j, MidiDeviceAndroid midiDeviceAndroid);

    static native void nativeOnDetached(long j, MidiDeviceAndroid midiDeviceAndroid);

    static native void nativeOnInitialized(long j, MidiDeviceAndroid[] midiDeviceAndroidArr);

    static MidiManagerAndroid create(Context context, long j) {
        return new MidiManagerAndroid(context, j);
    }

    MidiManagerAndroid(Context context, long j) {
        this.mManager = (MidiManager) context.getSystemService("midi");
        this.mNativeManagerPointer = j;
    }

    void initialize() {
        this.mManager.registerDeviceCallback(new MidiManager.DeviceCallback() { // from class: org.chromium.media.midi.MidiManagerAndroid.1
            @Override // android.media.midi.MidiManager.DeviceCallback
            public void onDeviceAdded(MidiDeviceInfo midiDeviceInfo) {
                MidiManagerAndroid.this.onDeviceAdded(midiDeviceInfo);
            }

            @Override // android.media.midi.MidiManager.DeviceCallback
            public void onDeviceRemoved(MidiDeviceInfo midiDeviceInfo) {
                MidiManagerAndroid.this.onDeviceRemoved(midiDeviceInfo);
            }
        }, this.mHandler);
        for (MidiDeviceInfo midiDeviceInfo : this.mManager.getDevices()) {
            this.mPendingDevices.add(midiDeviceInfo);
            openDevice(midiDeviceInfo);
        }
        this.mHandler.post(new Runnable() { // from class: org.chromium.media.midi.MidiManagerAndroid.2
            @Override // java.lang.Runnable
            public void run() {
                if (MidiManagerAndroid.this.mPendingDevices.isEmpty() && MidiManagerAndroid.this.mIsInitializing) {
                    MidiManagerAndroid.nativeOnInitialized(MidiManagerAndroid.this.mNativeManagerPointer, (MidiDeviceAndroid[]) MidiManagerAndroid.this.mDevices.toArray(new MidiDeviceAndroid[0]));
                    MidiManagerAndroid.this.mIsInitializing = false;
                }
            }
        });
    }

    private void openDevice(final MidiDeviceInfo midiDeviceInfo) {
        this.mManager.openDevice(midiDeviceInfo, new MidiManager.OnDeviceOpenedListener() { // from class: org.chromium.media.midi.MidiManagerAndroid.3
            @Override // android.media.midi.MidiManager.OnDeviceOpenedListener
            public void onDeviceOpened(MidiDevice midiDevice) {
                MidiManagerAndroid.this.onDeviceOpened(midiDevice, midiDeviceInfo);
            }
        }, this.mHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeviceAdded(MidiDeviceInfo midiDeviceInfo) {
        if (this.mIsInitializing) {
            this.mPendingDevices.add(midiDeviceInfo);
        }
        openDevice(midiDeviceInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeviceRemoved(MidiDeviceInfo midiDeviceInfo) {
        for (MidiDeviceAndroid midiDeviceAndroid : this.mDevices) {
            if (midiDeviceAndroid.isOpen() && midiDeviceAndroid.getInfo().getId() == midiDeviceInfo.getId()) {
                midiDeviceAndroid.close();
                nativeOnDetached(this.mNativeManagerPointer, midiDeviceAndroid);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeviceOpened(MidiDevice midiDevice, MidiDeviceInfo midiDeviceInfo) {
        this.mPendingDevices.remove(midiDeviceInfo);
        if (midiDevice != null) {
            MidiDeviceAndroid midiDeviceAndroid = new MidiDeviceAndroid(midiDevice);
            this.mDevices.add(midiDeviceAndroid);
            if (!this.mIsInitializing) {
                nativeOnAttached(this.mNativeManagerPointer, midiDeviceAndroid);
            }
        }
        if (this.mIsInitializing && this.mPendingDevices.isEmpty()) {
            nativeOnInitialized(this.mNativeManagerPointer, (MidiDeviceAndroid[]) this.mDevices.toArray(new MidiDeviceAndroid[0]));
            this.mIsInitializing = false;
        }
    }
}
