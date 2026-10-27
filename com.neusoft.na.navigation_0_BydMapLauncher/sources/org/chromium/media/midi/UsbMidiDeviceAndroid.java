package org.chromium.media.midi;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.os.Handler;
import android.util.SparseArray;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media::midi")
class UsbMidiDeviceAndroid {
    static final int MIDI_SUBCLASS = 3;
    static final int REQUEST_GET_DESCRIPTOR = 6;
    static final int STRING_DESCRIPTOR_TYPE = 3;
    private final UsbDeviceConnection mConnection;
    private UsbDevice mUsbDevice;
    private final SparseArray<UsbEndpoint> mEndpointMap = new SparseArray<>();
    private final Map<UsbEndpoint, UsbRequest> mRequestMap = new HashMap();
    private final Handler mHandler = new Handler();
    private boolean mIsClosed = false;
    private boolean mHasInputThread = false;
    private long mNativePointer = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeOnData(long j, int i, byte[] bArr);

    UsbMidiDeviceAndroid(UsbManager usbManager, UsbDevice usbDevice) {
        this.mConnection = usbManager.openDevice(usbDevice);
        this.mUsbDevice = usbDevice;
        for (int i = 0; i < usbDevice.getInterfaceCount(); i++) {
            UsbInterface usbInterface = usbDevice.getInterface(i);
            if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 3) {
                this.mConnection.claimInterface(usbInterface, true);
                for (int i2 = 0; i2 < usbInterface.getEndpointCount(); i2++) {
                    UsbEndpoint endpoint = usbInterface.getEndpoint(i2);
                    if (endpoint.getDirection() == 0) {
                        this.mEndpointMap.put(endpoint.getEndpointNumber(), endpoint);
                    }
                }
            }
        }
        startListen(usbDevice);
    }

    /* JADX WARN: Type inference failed for: r10v2, types: [org.chromium.media.midi.UsbMidiDeviceAndroid$1] */
    private void startListen(UsbDevice usbDevice) {
        final HashMap map = new HashMap();
        for (int i = 0; i < usbDevice.getInterfaceCount(); i++) {
            UsbInterface usbInterface = usbDevice.getInterface(i);
            if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 3) {
                for (int i2 = 0; i2 < usbInterface.getEndpointCount(); i2++) {
                    UsbEndpoint endpoint = usbInterface.getEndpoint(i2);
                    if (endpoint.getDirection() == 128) {
                        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(endpoint.getMaxPacketSize());
                        UsbRequest usbRequest = new UsbRequest();
                        usbRequest.initialize(this.mConnection, endpoint);
                        usbRequest.queue(byteBufferAllocate, byteBufferAllocate.remaining());
                        map.put(endpoint, byteBufferAllocate);
                    }
                }
            }
        }
        if (map.isEmpty()) {
            return;
        }
        this.mHasInputThread = true;
        new Thread() { // from class: org.chromium.media.midi.UsbMidiDeviceAndroid.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (true) {
                    UsbRequest usbRequestRequestWait = UsbMidiDeviceAndroid.this.mConnection.requestWait();
                    if (usbRequestRequestWait == null) {
                        return;
                    }
                    UsbEndpoint endpoint2 = usbRequestRequestWait.getEndpoint();
                    if (endpoint2.getDirection() == 128) {
                        ByteBuffer byteBuffer = (ByteBuffer) map.get(endpoint2);
                        int inputDataLength = UsbMidiDeviceAndroid.getInputDataLength(byteBuffer);
                        if (inputDataLength > 0) {
                            byteBuffer.rewind();
                            byte[] bArr = new byte[inputDataLength];
                            byteBuffer.get(bArr, 0, inputDataLength);
                            UsbMidiDeviceAndroid.this.postOnDataEvent(endpoint2.getEndpointNumber(), bArr);
                        }
                        byteBuffer.rewind();
                        usbRequestRequestWait.queue(byteBuffer, byteBuffer.capacity());
                    }
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postOnDataEvent(final int i, final byte[] bArr) {
        this.mHandler.post(new Runnable() { // from class: org.chromium.media.midi.UsbMidiDeviceAndroid.2
            @Override // java.lang.Runnable
            public void run() {
                if (UsbMidiDeviceAndroid.this.mIsClosed) {
                    return;
                }
                UsbMidiDeviceAndroid.nativeOnData(UsbMidiDeviceAndroid.this.mNativePointer, i, bArr);
            }
        });
    }

    UsbDevice getUsbDevice() {
        return this.mUsbDevice;
    }

    boolean isClosed() {
        return this.mIsClosed;
    }

    void registerSelf(long j) {
        this.mNativePointer = j;
    }

    void send(int i, byte[] bArr) {
        UsbEndpoint usbEndpoint;
        if (this.mIsClosed || (usbEndpoint = this.mEndpointMap.get(i)) == null) {
            return;
        }
        if (shouldUseBulkTransfer()) {
            this.mConnection.bulkTransfer(usbEndpoint, bArr, bArr.length, 100);
            return;
        }
        UsbRequest usbRequest = this.mRequestMap.get(usbEndpoint);
        if (usbRequest == null) {
            usbRequest = new UsbRequest();
            usbRequest.initialize(this.mConnection, usbEndpoint);
            this.mRequestMap.put(usbEndpoint, usbRequest);
        }
        usbRequest.queue(ByteBuffer.wrap(bArr), bArr.length);
    }

    private boolean shouldUseBulkTransfer() {
        return this.mHasInputThread;
    }

    byte[] getDescriptors() {
        UsbDeviceConnection usbDeviceConnection = this.mConnection;
        return usbDeviceConnection == null ? new byte[0] : usbDeviceConnection.getRawDescriptors();
    }

    byte[] getStringDescriptor(int i) {
        UsbDeviceConnection usbDeviceConnection = this.mConnection;
        if (usbDeviceConnection == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[255];
        int iControlTransfer = usbDeviceConnection.controlTransfer(128, 6, i | 768, 0, bArr, 255, 0);
        return iControlTransfer < 0 ? new byte[0] : Arrays.copyOf(bArr, iControlTransfer);
    }

    void close() {
        this.mEndpointMap.clear();
        Iterator<UsbRequest> it = this.mRequestMap.values().iterator();
        while (it.hasNext()) {
            it.next().close();
        }
        this.mRequestMap.clear();
        this.mConnection.close();
        this.mNativePointer = 0L;
        this.mIsClosed = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getInputDataLength(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        for (int i = 0; i < iPosition; i += 4) {
            if (byteBuffer.get(i) == 0) {
                return i;
            }
        }
        return iPosition;
    }
}
