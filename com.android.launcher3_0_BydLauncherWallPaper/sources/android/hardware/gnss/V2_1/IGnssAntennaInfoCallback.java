package android.hardware.gnss.V2_1;

import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import com.android.internal.midi.MidiConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public interface IGnssAntennaInfoCallback extends IBase {
    public static final String kInterfaceName = "android.hardware.gnss@2.1::IGnssAntennaInfoCallback";

    @Override // android.hidl.base.V1_0.IBase, android.os.IHwInterface
    IHwBinder asBinder();

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain() throws RemoteException;

    void gnssAntennaInfoCb(ArrayList<GnssAntennaInfo> arrayList) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<String> interfaceChain() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    String interfaceDescriptor() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void notifySyspropsChanged() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void ping() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    static IGnssAntennaInfoCallback asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface(kInterfaceName);
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IGnssAntennaInfoCallback)) {
            return (IGnssAntennaInfoCallback) iHwInterfaceQueryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals(kInterfaceName)) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static IGnssAntennaInfoCallback castFrom(IHwInterface iHwInterface) {
        if (iHwInterface == null) {
            return null;
        }
        return asInterface(iHwInterface.asBinder());
    }

    static IGnssAntennaInfoCallback getService(String str, boolean z) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, str, z));
    }

    static IGnssAntennaInfoCallback getService(boolean z) throws RemoteException {
        return getService("default", z);
    }

    static IGnssAntennaInfoCallback getService(String str) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, str));
    }

    static IGnssAntennaInfoCallback getService() throws RemoteException {
        return getService("default");
    }

    public static final class Row {
        public ArrayList<Double> row = new ArrayList<>();

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && obj.getClass() == Row.class && HidlSupport.deepEquals(this.row, ((Row) obj).row);
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.row)));
        }

        public final String toString() {
            return "{.row = " + this.row + "}";
        }

        public final void readFromParcel(HwParcel hwParcel) {
            readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(16L), 0L);
        }

        public static final ArrayList<Row> readVectorFromParcel(HwParcel hwParcel) {
            ArrayList<Row> arrayList = new ArrayList<>();
            HwBlob buffer = hwParcel.readBuffer(16L);
            int int32 = buffer.getInt32(8L);
            HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 16, buffer.handle(), 0L, true);
            arrayList.clear();
            for (int i = 0; i < int32; i++) {
                Row row = new Row();
                row.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 16);
                arrayList.add(row);
            }
            return arrayList;
        }

        public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
            long j2 = j + 0;
            int int32 = hwBlob.getInt32(8 + j2);
            HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 8, hwBlob.handle(), j2 + 0, true);
            this.row.clear();
            for (int i = 0; i < int32; i++) {
                this.row.add(Double.valueOf(embeddedBuffer.getDouble(i * 8)));
            }
        }

        public final void writeToParcel(HwParcel hwParcel) {
            HwBlob hwBlob = new HwBlob(16);
            writeEmbeddedToBlob(hwBlob, 0L);
            hwParcel.writeBuffer(hwBlob);
        }

        public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<Row> arrayList) {
            HwBlob hwBlob = new HwBlob(16);
            int size = arrayList.size();
            hwBlob.putInt32(8L, size);
            hwBlob.putBool(12L, false);
            HwBlob hwBlob2 = new HwBlob(size * 16);
            for (int i = 0; i < size; i++) {
                arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 16);
            }
            hwBlob.putBlob(0L, hwBlob2);
            hwParcel.writeBuffer(hwBlob);
        }

        public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
            int size = this.row.size();
            long j2 = j + 0;
            hwBlob.putInt32(8 + j2, size);
            hwBlob.putBool(12 + j2, false);
            HwBlob hwBlob2 = new HwBlob(size * 8);
            for (int i = 0; i < size; i++) {
                hwBlob2.putDouble(i * 8, this.row.get(i).doubleValue());
            }
            hwBlob.putBlob(j2 + 0, hwBlob2);
        }
    }

    public static final class Coord {
        public double x = 0.0d;
        public double xUncertainty = 0.0d;
        public double y = 0.0d;
        public double yUncertainty = 0.0d;
        public double z = 0.0d;
        public double zUncertainty = 0.0d;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != Coord.class) {
                return false;
            }
            Coord coord = (Coord) obj;
            return this.x == coord.x && this.xUncertainty == coord.xUncertainty && this.y == coord.y && this.yUncertainty == coord.yUncertainty && this.z == coord.z && this.zUncertainty == coord.zUncertainty;
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.x))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.xUncertainty))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.y))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.yUncertainty))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.z))), Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.zUncertainty))));
        }

        public final String toString() {
            return "{.x = " + this.x + ", .xUncertainty = " + this.xUncertainty + ", .y = " + this.y + ", .yUncertainty = " + this.yUncertainty + ", .z = " + this.z + ", .zUncertainty = " + this.zUncertainty + "}";
        }

        public final void readFromParcel(HwParcel hwParcel) {
            readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(48L), 0L);
        }

        public static final ArrayList<Coord> readVectorFromParcel(HwParcel hwParcel) {
            ArrayList<Coord> arrayList = new ArrayList<>();
            HwBlob buffer = hwParcel.readBuffer(16L);
            int int32 = buffer.getInt32(8L);
            HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 48, buffer.handle(), 0L, true);
            arrayList.clear();
            for (int i = 0; i < int32; i++) {
                Coord coord = new Coord();
                coord.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 48);
                arrayList.add(coord);
            }
            return arrayList;
        }

        public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
            this.x = hwBlob.getDouble(0 + j);
            this.xUncertainty = hwBlob.getDouble(8 + j);
            this.y = hwBlob.getDouble(16 + j);
            this.yUncertainty = hwBlob.getDouble(24 + j);
            this.z = hwBlob.getDouble(32 + j);
            this.zUncertainty = hwBlob.getDouble(j + 40);
        }

        public final void writeToParcel(HwParcel hwParcel) {
            HwBlob hwBlob = new HwBlob(48);
            writeEmbeddedToBlob(hwBlob, 0L);
            hwParcel.writeBuffer(hwBlob);
        }

        public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<Coord> arrayList) {
            HwBlob hwBlob = new HwBlob(16);
            int size = arrayList.size();
            hwBlob.putInt32(8L, size);
            hwBlob.putBool(12L, false);
            HwBlob hwBlob2 = new HwBlob(size * 48);
            for (int i = 0; i < size; i++) {
                arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 48);
            }
            hwBlob.putBlob(0L, hwBlob2);
            hwParcel.writeBuffer(hwBlob);
        }

        public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
            hwBlob.putDouble(0 + j, this.x);
            hwBlob.putDouble(8 + j, this.xUncertainty);
            hwBlob.putDouble(16 + j, this.y);
            hwBlob.putDouble(24 + j, this.yUncertainty);
            hwBlob.putDouble(32 + j, this.z);
            hwBlob.putDouble(j + 40, this.zUncertainty);
        }
    }

    public static final class GnssAntennaInfo {
        public double carrierFrequencyMHz = 0.0d;
        public Coord phaseCenterOffsetCoordinateMillimeters = new Coord();
        public ArrayList<Row> phaseCenterVariationCorrectionMillimeters = new ArrayList<>();
        public ArrayList<Row> phaseCenterVariationCorrectionUncertaintyMillimeters = new ArrayList<>();
        public ArrayList<Row> signalGainCorrectionDbi = new ArrayList<>();
        public ArrayList<Row> signalGainCorrectionUncertaintyDbi = new ArrayList<>();

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != GnssAntennaInfo.class) {
                return false;
            }
            GnssAntennaInfo gnssAntennaInfo = (GnssAntennaInfo) obj;
            return this.carrierFrequencyMHz == gnssAntennaInfo.carrierFrequencyMHz && HidlSupport.deepEquals(this.phaseCenterOffsetCoordinateMillimeters, gnssAntennaInfo.phaseCenterOffsetCoordinateMillimeters) && HidlSupport.deepEquals(this.phaseCenterVariationCorrectionMillimeters, gnssAntennaInfo.phaseCenterVariationCorrectionMillimeters) && HidlSupport.deepEquals(this.phaseCenterVariationCorrectionUncertaintyMillimeters, gnssAntennaInfo.phaseCenterVariationCorrectionUncertaintyMillimeters) && HidlSupport.deepEquals(this.signalGainCorrectionDbi, gnssAntennaInfo.signalGainCorrectionDbi) && HidlSupport.deepEquals(this.signalGainCorrectionUncertaintyDbi, gnssAntennaInfo.signalGainCorrectionUncertaintyDbi);
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Double.valueOf(this.carrierFrequencyMHz))), Integer.valueOf(HidlSupport.deepHashCode(this.phaseCenterOffsetCoordinateMillimeters)), Integer.valueOf(HidlSupport.deepHashCode(this.phaseCenterVariationCorrectionMillimeters)), Integer.valueOf(HidlSupport.deepHashCode(this.phaseCenterVariationCorrectionUncertaintyMillimeters)), Integer.valueOf(HidlSupport.deepHashCode(this.signalGainCorrectionDbi)), Integer.valueOf(HidlSupport.deepHashCode(this.signalGainCorrectionUncertaintyDbi)));
        }

        public final String toString() {
            return "{.carrierFrequencyMHz = " + this.carrierFrequencyMHz + ", .phaseCenterOffsetCoordinateMillimeters = " + this.phaseCenterOffsetCoordinateMillimeters + ", .phaseCenterVariationCorrectionMillimeters = " + this.phaseCenterVariationCorrectionMillimeters + ", .phaseCenterVariationCorrectionUncertaintyMillimeters = " + this.phaseCenterVariationCorrectionUncertaintyMillimeters + ", .signalGainCorrectionDbi = " + this.signalGainCorrectionDbi + ", .signalGainCorrectionUncertaintyDbi = " + this.signalGainCorrectionUncertaintyDbi + "}";
        }

        public final void readFromParcel(HwParcel hwParcel) {
            readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(120L), 0L);
        }

        public static final ArrayList<GnssAntennaInfo> readVectorFromParcel(HwParcel hwParcel) {
            ArrayList<GnssAntennaInfo> arrayList = new ArrayList<>();
            HwBlob buffer = hwParcel.readBuffer(16L);
            int int32 = buffer.getInt32(8L);
            HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 120, buffer.handle(), 0L, true);
            arrayList.clear();
            for (int i = 0; i < int32; i++) {
                GnssAntennaInfo gnssAntennaInfo = new GnssAntennaInfo();
                gnssAntennaInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 120);
                arrayList.add(gnssAntennaInfo);
            }
            return arrayList;
        }

        public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
            this.carrierFrequencyMHz = hwBlob.getDouble(j + 0);
            this.phaseCenterOffsetCoordinateMillimeters.readEmbeddedFromParcel(hwParcel, hwBlob, j + 8);
            long j2 = j + 56;
            int int32 = hwBlob.getInt32(j2 + 8);
            HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 16, hwBlob.handle(), j2 + 0, true);
            this.phaseCenterVariationCorrectionMillimeters.clear();
            for (int i = 0; i < int32; i++) {
                Row row = new Row();
                row.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 16);
                this.phaseCenterVariationCorrectionMillimeters.add(row);
            }
            long j3 = j + 72;
            int int322 = hwBlob.getInt32(j3 + 8);
            HwBlob embeddedBuffer2 = hwParcel.readEmbeddedBuffer(int322 * 16, hwBlob.handle(), j3 + 0, true);
            this.phaseCenterVariationCorrectionUncertaintyMillimeters.clear();
            for (int i2 = 0; i2 < int322; i2++) {
                Row row2 = new Row();
                row2.readEmbeddedFromParcel(hwParcel, embeddedBuffer2, i2 * 16);
                this.phaseCenterVariationCorrectionUncertaintyMillimeters.add(row2);
            }
            long j4 = j + 88;
            int int323 = hwBlob.getInt32(j4 + 8);
            HwBlob embeddedBuffer3 = hwParcel.readEmbeddedBuffer(int323 * 16, hwBlob.handle(), j4 + 0, true);
            this.signalGainCorrectionDbi.clear();
            for (int i3 = 0; i3 < int323; i3++) {
                Row row3 = new Row();
                row3.readEmbeddedFromParcel(hwParcel, embeddedBuffer3, i3 * 16);
                this.signalGainCorrectionDbi.add(row3);
            }
            long j5 = j + 104;
            int int324 = hwBlob.getInt32(8 + j5);
            HwBlob embeddedBuffer4 = hwParcel.readEmbeddedBuffer(int324 * 16, hwBlob.handle(), j5 + 0, true);
            this.signalGainCorrectionUncertaintyDbi.clear();
            for (int i4 = 0; i4 < int324; i4++) {
                Row row4 = new Row();
                row4.readEmbeddedFromParcel(hwParcel, embeddedBuffer4, i4 * 16);
                this.signalGainCorrectionUncertaintyDbi.add(row4);
            }
        }

        public final void writeToParcel(HwParcel hwParcel) {
            HwBlob hwBlob = new HwBlob(120);
            writeEmbeddedToBlob(hwBlob, 0L);
            hwParcel.writeBuffer(hwBlob);
        }

        public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<GnssAntennaInfo> arrayList) {
            HwBlob hwBlob = new HwBlob(16);
            int size = arrayList.size();
            hwBlob.putInt32(8L, size);
            hwBlob.putBool(12L, false);
            HwBlob hwBlob2 = new HwBlob(size * 120);
            for (int i = 0; i < size; i++) {
                arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 120);
            }
            hwBlob.putBlob(0L, hwBlob2);
            hwParcel.writeBuffer(hwBlob);
        }

        public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
            hwBlob.putDouble(j + 0, this.carrierFrequencyMHz);
            this.phaseCenterOffsetCoordinateMillimeters.writeEmbeddedToBlob(hwBlob, j + 8);
            int size = this.phaseCenterVariationCorrectionMillimeters.size();
            long j2 = j + 56;
            hwBlob.putInt32(j2 + 8, size);
            hwBlob.putBool(j2 + 12, false);
            HwBlob hwBlob2 = new HwBlob(size * 16);
            for (int i = 0; i < size; i++) {
                this.phaseCenterVariationCorrectionMillimeters.get(i).writeEmbeddedToBlob(hwBlob2, i * 16);
            }
            hwBlob.putBlob(j2 + 0, hwBlob2);
            int size2 = this.phaseCenterVariationCorrectionUncertaintyMillimeters.size();
            long j3 = j + 72;
            hwBlob.putInt32(j3 + 8, size2);
            hwBlob.putBool(j3 + 12, false);
            HwBlob hwBlob3 = new HwBlob(size2 * 16);
            for (int i2 = 0; i2 < size2; i2++) {
                this.phaseCenterVariationCorrectionUncertaintyMillimeters.get(i2).writeEmbeddedToBlob(hwBlob3, i2 * 16);
            }
            hwBlob.putBlob(j3 + 0, hwBlob3);
            int size3 = this.signalGainCorrectionDbi.size();
            long j4 = j + 88;
            hwBlob.putInt32(j4 + 8, size3);
            hwBlob.putBool(j4 + 12, false);
            HwBlob hwBlob4 = new HwBlob(size3 * 16);
            for (int i3 = 0; i3 < size3; i3++) {
                this.signalGainCorrectionDbi.get(i3).writeEmbeddedToBlob(hwBlob4, i3 * 16);
            }
            hwBlob.putBlob(j4 + 0, hwBlob4);
            int size4 = this.signalGainCorrectionUncertaintyDbi.size();
            long j5 = j + 104;
            hwBlob.putInt32(8 + j5, size4);
            hwBlob.putBool(j5 + 12, false);
            HwBlob hwBlob5 = new HwBlob(size4 * 16);
            for (int i4 = 0; i4 < size4; i4++) {
                this.signalGainCorrectionUncertaintyDbi.get(i4).writeEmbeddedToBlob(hwBlob5, i4 * 16);
            }
            hwBlob.putBlob(j5 + 0, hwBlob5);
        }
    }

    public static final class Proxy implements IGnssAntennaInfoCallback {
        private IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            this.mRemote = (IHwBinder) Objects.requireNonNull(iHwBinder);
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase, android.os.IHwInterface
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of android.hardware.gnss@2.1::IGnssAntennaInfoCallback]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback
        public void gnssAntennaInfoCb(ArrayList<GnssAntennaInfo> arrayList) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IGnssAntennaInfoCallback.kInterfaceName);
            GnssAntennaInfo.writeVectorToParcel(hwParcel, arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(1, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256067662, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            hwParcel.writeNativeHandle(nativeHandle);
            hwParcel.writeStringVector(arrayList);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256131655, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public String interfaceDescriptor() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public ArrayList<byte[]> getHashChain() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256398152, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                ArrayList<byte[]> arrayList = new ArrayList<>();
                HwBlob buffer = hwParcel2.readBuffer(16L);
                int int32 = buffer.getInt32(8L);
                HwBlob embeddedBuffer = hwParcel2.readEmbeddedBuffer(int32 * 32, buffer.handle(), 0L, true);
                arrayList.clear();
                for (int i = 0; i < int32; i++) {
                    byte[] bArr = new byte[32];
                    embeddedBuffer.copyToInt8Array(i * 32, bArr, 32);
                    arrayList.add(bArr);
                }
                return arrayList;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public void setHALInstrumentation() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256462420, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException {
            return this.mRemote.linkToDeath(deathRecipient, j);
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public void ping() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256921159, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public DebugInfo getDebugInfo() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(257049926, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                DebugInfo debugInfo = new DebugInfo();
                debugInfo.readFromParcel(hwParcel2);
                return debugInfo;
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public void notifySyspropsChanged() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(257120595, hwParcel, hwParcel2, 1);
                hwParcel.releaseTemporaryStorage();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(deathRecipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IGnssAntennaInfoCallback {
        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase, android.os.IHwInterface
        public IHwBinder asBinder() {
            return this;
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) {
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IGnssAntennaInfoCallback.kInterfaceName;
        }

        @Override // android.os.IHwBinder, android.hardware.cas.V1_0.ICas, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) {
            return true;
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        @Override // android.os.IHwBinder, android.hardware.cas.V1_0.ICas, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) {
            return true;
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IGnssAntennaInfoCallback.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{11, -61, -19, -105, -53, -61, -10, -85, -56, -100, 104, -12, -23, -12, -47, 36, -7, -9, 35, 67, 25, -105, -36, -120, -62, 24, 108, -12, -46, -83, 71, -18}, new byte[]{-20, 127, -41, -98, MidiConstants.STATUS_CHANNEL_PRESSURE, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, MidiConstants.STATUS_SONG_SELECT, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo debugInfo = new DebugInfo();
            debugInfo.pid = HidlSupport.getPidIfSharable();
            debugInfo.ptr = 0L;
            debugInfo.arch = 0;
            return debugInfo;
        }

        @Override // android.hardware.gnss.V2_1.IGnssAntennaInfoCallback, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        @Override // android.os.IHwBinder
        public IHwInterface queryLocalInterface(String str) {
            if (IGnssAntennaInfoCallback.kInterfaceName.equals(str)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String str) throws RemoteException {
            registerService(str);
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        @Override // android.os.HwBinder
        public void onTransact(int i, HwParcel hwParcel, HwParcel hwParcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    hwParcel.enforceInterface(IGnssAntennaInfoCallback.kInterfaceName);
                    gnssAntennaInfoCb(GnssAntennaInfo.readVectorFromParcel(hwParcel));
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 256067662:
                    hwParcel.enforceInterface(IBase.kInterfaceName);
                    ArrayList<String> arrayListInterfaceChain = interfaceChain();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeStringVector(arrayListInterfaceChain);
                    hwParcel2.send();
                    return;
                case 256131655:
                    hwParcel.enforceInterface(IBase.kInterfaceName);
                    debug(hwParcel.readNativeHandle(), hwParcel.readStringVector());
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 256136003:
                    hwParcel.enforceInterface(IBase.kInterfaceName);
                    String strInterfaceDescriptor = interfaceDescriptor();
                    hwParcel2.writeStatus(0);
                    hwParcel2.writeString(strInterfaceDescriptor);
                    hwParcel2.send();
                    return;
                case 256398152:
                    hwParcel.enforceInterface(IBase.kInterfaceName);
                    ArrayList<byte[]> hashChain = getHashChain();
                    hwParcel2.writeStatus(0);
                    HwBlob hwBlob = new HwBlob(16);
                    int size = hashChain.size();
                    hwBlob.putInt32(8L, size);
                    hwBlob.putBool(12L, false);
                    HwBlob hwBlob2 = new HwBlob(size * 32);
                    for (int i3 = 0; i3 < size; i3++) {
                        long j = i3 * 32;
                        byte[] bArr = hashChain.get(i3);
                        if (bArr == null || bArr.length != 32) {
                            throw new IllegalArgumentException("Array element is not of the expected length");
                        }
                        hwBlob2.putInt8Array(j, bArr);
                    }
                    hwBlob.putBlob(0L, hwBlob2);
                    hwParcel2.writeBuffer(hwBlob);
                    hwParcel2.send();
                    return;
                case 256462420:
                    hwParcel.enforceInterface(IBase.kInterfaceName);
                    setHALInstrumentation();
                    return;
                case 256921159:
                    hwParcel.enforceInterface(IBase.kInterfaceName);
                    ping();
                    hwParcel2.writeStatus(0);
                    hwParcel2.send();
                    return;
                case 257049926:
                    hwParcel.enforceInterface(IBase.kInterfaceName);
                    DebugInfo debugInfo = getDebugInfo();
                    hwParcel2.writeStatus(0);
                    debugInfo.writeToParcel(hwParcel2);
                    hwParcel2.send();
                    return;
                case 257120595:
                    hwParcel.enforceInterface(IBase.kInterfaceName);
                    notifySyspropsChanged();
                    return;
                default:
                    return;
            }
        }
    }
}
