package android.hardware.camera2.impl;

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.MarshalRegistry;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.marshal.impl.MarshalQueryableArray;
import android.hardware.camera2.marshal.impl.MarshalQueryableBlackLevelPattern;
import android.hardware.camera2.marshal.impl.MarshalQueryableBoolean;
import android.hardware.camera2.marshal.impl.MarshalQueryableColorSpaceTransform;
import android.hardware.camera2.marshal.impl.MarshalQueryableEnum;
import android.hardware.camera2.marshal.impl.MarshalQueryableHighSpeedVideoConfiguration;
import android.hardware.camera2.marshal.impl.MarshalQueryableMeteringRectangle;
import android.hardware.camera2.marshal.impl.MarshalQueryableNativeByteToInteger;
import android.hardware.camera2.marshal.impl.MarshalQueryablePair;
import android.hardware.camera2.marshal.impl.MarshalQueryableParcelable;
import android.hardware.camera2.marshal.impl.MarshalQueryablePrimitive;
import android.hardware.camera2.marshal.impl.MarshalQueryableRange;
import android.hardware.camera2.marshal.impl.MarshalQueryableRecommendedStreamConfiguration;
import android.hardware.camera2.marshal.impl.MarshalQueryableRect;
import android.hardware.camera2.marshal.impl.MarshalQueryableReprocessFormatsMap;
import android.hardware.camera2.marshal.impl.MarshalQueryableRggbChannelVector;
import android.hardware.camera2.marshal.impl.MarshalQueryableSize;
import android.hardware.camera2.marshal.impl.MarshalQueryableSizeF;
import android.hardware.camera2.marshal.impl.MarshalQueryableStreamConfiguration;
import android.hardware.camera2.marshal.impl.MarshalQueryableStreamConfigurationDuration;
import android.hardware.camera2.marshal.impl.MarshalQueryableString;
import android.hardware.camera2.params.Capability;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.params.HighSpeedVideoConfiguration;
import android.hardware.camera2.params.LensShadingMap;
import android.hardware.camera2.params.MandatoryStreamCombination;
import android.hardware.camera2.params.OisSample;
import android.hardware.camera2.params.RecommendedStreamConfiguration;
import android.hardware.camera2.params.ReprocessFormatsMap;
import android.hardware.camera2.params.StreamConfiguration;
import android.hardware.camera2.params.StreamConfigurationDuration;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.params.TonemapCurve;
import android.hardware.camera2.utils.TypeReference;
import android.location.Location;
import android.location.LocationManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ServiceSpecificException;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import dalvik.annotation.optimization.FastNative;
import dalvik.system.VMRuntime;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class CameraMetadataNative implements Parcelable {
    private static final String CELLID_PROCESS = "CELLID";
    public static final Parcelable.Creator<CameraMetadataNative> CREATOR = new Parcelable.Creator<CameraMetadataNative>() { // from class: android.hardware.camera2.impl.CameraMetadataNative.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraMetadataNative createFromParcel(Parcel parcel) {
            CameraMetadataNative cameraMetadataNative = new CameraMetadataNative();
            cameraMetadataNative.readFromParcel(parcel);
            return cameraMetadataNative;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraMetadataNative[] newArray(int i) {
            return new CameraMetadataNative[i];
        }
    };
    private static final boolean DEBUG = false;
    private static final int FACE_LANDMARK_SIZE = 6;
    private static final String GPS_PROCESS = "GPS";
    public static final int NATIVE_JPEG_FORMAT = 33;
    public static final int NUM_TYPES = 6;
    private static final String TAG = "CameraMetadataJV";
    public static final int TYPE_BYTE = 0;
    public static final int TYPE_DOUBLE = 4;
    public static final int TYPE_FLOAT = 2;
    public static final int TYPE_INT32 = 1;
    public static final int TYPE_INT64 = 3;
    public static final int TYPE_RATIONAL = 5;
    private static final HashMap<Key<?>, GetCommand> sGetCommandMap;
    private static final HashMap<Key<?>, SetCommand> sSetCommandMap;
    private long mMetadataPtr;
    private int mCameraId = -1;
    private boolean mHasMandatoryConcurrentStreams = false;
    private Size mDisplaySize = new Size(0, 0);
    private long mBufferSize = 0;

    @FastNative
    private static native long nativeAllocate();

    @FastNative
    private static native long nativeAllocateCopy(long j) throws NullPointerException;

    @FastNative
    private static native synchronized void nativeClose(long j);

    private static native synchronized void nativeDump(long j) throws IOException;

    @FastNative
    private static native synchronized ArrayList nativeGetAllVendorKeys(long j, Class cls);

    @FastNative
    private static native synchronized long nativeGetBufferSize(long j);

    @FastNative
    private static native synchronized int nativeGetEntryCount(long j);

    @FastNative
    private static native int nativeGetTagFromKey(String str, long j) throws IllegalArgumentException;

    @FastNative
    private static native synchronized int nativeGetTagFromKeyLocal(long j, String str) throws IllegalArgumentException;

    @FastNative
    private static native int nativeGetTypeFromTag(int i, long j) throws IllegalArgumentException;

    @FastNative
    private static native synchronized int nativeGetTypeFromTagLocal(long j, int i) throws IllegalArgumentException;

    @FastNative
    private static native synchronized boolean nativeIsEmpty(long j);

    @FastNative
    private static native synchronized void nativeReadFromParcel(Parcel parcel, long j);

    @FastNative
    private static native synchronized byte[] nativeReadValues(int i, long j);

    private static native int nativeSetupGlobalVendorTagDescriptor();

    @FastNative
    private static native synchronized void nativeSwap(long j, long j2) throws NullPointerException;

    @FastNative
    private static native synchronized void nativeWriteToParcel(Parcel parcel, long j);

    @FastNative
    private static native synchronized void nativeWriteValues(int i, byte[] bArr, long j);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static class Key<T> {
        private final String mFallbackName;
        private boolean mHasTag;
        private final int mHash;
        private final String mName;
        private int mTag;
        private final Class<T> mType;
        private final TypeReference<T> mTypeReference;
        private long mVendorId;

        public Key(String str, Class<T> cls, long j) {
            this.mVendorId = Long.MAX_VALUE;
            Objects.requireNonNull(str, "Key needs a valid name");
            Objects.requireNonNull(cls, "Type needs to be non-null");
            this.mName = str;
            this.mFallbackName = null;
            this.mType = cls;
            this.mVendorId = j;
            TypeReference<T> typeReferenceCreateSpecializedTypeReference = TypeReference.createSpecializedTypeReference((Class) cls);
            this.mTypeReference = typeReferenceCreateSpecializedTypeReference;
            this.mHash = str.hashCode() ^ typeReferenceCreateSpecializedTypeReference.hashCode();
        }

        public Key(String str, String str2, Class<T> cls) {
            this.mVendorId = Long.MAX_VALUE;
            Objects.requireNonNull(str, "Key needs a valid name");
            Objects.requireNonNull(cls, "Type needs to be non-null");
            this.mName = str;
            this.mFallbackName = str2;
            this.mType = cls;
            TypeReference<T> typeReferenceCreateSpecializedTypeReference = TypeReference.createSpecializedTypeReference((Class) cls);
            this.mTypeReference = typeReferenceCreateSpecializedTypeReference;
            this.mHash = str.hashCode() ^ typeReferenceCreateSpecializedTypeReference.hashCode();
        }

        public Key(String str, Class<T> cls) {
            this.mVendorId = Long.MAX_VALUE;
            Objects.requireNonNull(str, "Key needs a valid name");
            Objects.requireNonNull(cls, "Type needs to be non-null");
            this.mName = str;
            this.mFallbackName = null;
            this.mType = cls;
            TypeReference<T> typeReferenceCreateSpecializedTypeReference = TypeReference.createSpecializedTypeReference((Class) cls);
            this.mTypeReference = typeReferenceCreateSpecializedTypeReference;
            this.mHash = str.hashCode() ^ typeReferenceCreateSpecializedTypeReference.hashCode();
        }

        public Key(String str, TypeReference<T> typeReference) {
            this.mVendorId = Long.MAX_VALUE;
            Objects.requireNonNull(str, "Key needs a valid name");
            Objects.requireNonNull(typeReference, "TypeReference needs to be non-null");
            this.mName = str;
            this.mFallbackName = null;
            this.mType = typeReference.getRawType();
            this.mTypeReference = typeReference;
            this.mHash = str.hashCode() ^ typeReference.hashCode();
        }

        public final String getName() {
            return this.mName;
        }

        public final int hashCode() {
            return this.mHash;
        }

        public final boolean equals(Object obj) {
            Key<T> nativeKey;
            if (this == obj) {
                return true;
            }
            if (obj != null && hashCode() == obj.hashCode()) {
                if (obj instanceof CaptureResult.Key) {
                    nativeKey = ((CaptureResult.Key) obj).getNativeKey();
                } else if (obj instanceof CaptureRequest.Key) {
                    nativeKey = ((CaptureRequest.Key) obj).getNativeKey();
                } else if (obj instanceof CameraCharacteristics.Key) {
                    nativeKey = ((CameraCharacteristics.Key) obj).getNativeKey();
                } else if (obj instanceof Key) {
                    nativeKey = (Key) obj;
                }
                return this.mName.equals(nativeKey.mName) && this.mTypeReference.equals(nativeKey.mTypeReference);
            }
            return false;
        }

        public final int getTag() {
            if (!this.mHasTag) {
                this.mTag = CameraMetadataNative.getTag(this.mName, this.mVendorId);
                this.mHasTag = true;
            }
            return this.mTag;
        }

        public final boolean hasTag() {
            return this.mHasTag;
        }

        public final void cacheTag(int i) {
            this.mHasTag = true;
            this.mTag = i;
        }

        public final Class<T> getType() {
            return this.mType;
        }

        public final long getVendorId() {
            return this.mVendorId;
        }

        public final TypeReference<T> getTypeReference() {
            return this.mTypeReference;
        }
    }

    private static String translateLocationProviderToProcess(String str) {
        if (str == null) {
            return null;
        }
        str.hashCode();
        if (str.equals(LocationManager.GPS_PROVIDER)) {
            return GPS_PROCESS;
        }
        if (str.equals(LocationManager.NETWORK_PROVIDER)) {
            return CELLID_PROCESS;
        }
        return null;
    }

    private static String translateProcessToLocationProvider(String str) {
        if (str == null) {
            return null;
        }
        str.hashCode();
        if (str.equals(GPS_PROCESS)) {
            return LocationManager.GPS_PROVIDER;
        }
        if (str.equals(CELLID_PROCESS)) {
            return LocationManager.NETWORK_PROVIDER;
        }
        return null;
    }

    public CameraMetadataNative() {
        long jNativeAllocate = nativeAllocate();
        this.mMetadataPtr = jNativeAllocate;
        if (jNativeAllocate == 0) {
            throw new OutOfMemoryError("Failed to allocate native CameraMetadata");
        }
        updateNativeAllocation();
    }

    public CameraMetadataNative(CameraMetadataNative cameraMetadataNative) {
        long jNativeAllocateCopy = nativeAllocateCopy(cameraMetadataNative.mMetadataPtr);
        this.mMetadataPtr = jNativeAllocateCopy;
        if (jNativeAllocateCopy == 0) {
            throw new OutOfMemoryError("Failed to allocate native CameraMetadata");
        }
        updateNativeAllocation();
    }

    public static CameraMetadataNative move(CameraMetadataNative cameraMetadataNative) {
        CameraMetadataNative cameraMetadataNative2 = new CameraMetadataNative();
        cameraMetadataNative2.swap(cameraMetadataNative);
        return cameraMetadataNative2;
    }

    static {
        HashMap<Key<?>, GetCommand> map = new HashMap<>();
        sGetCommandMap = map;
        map.put(CameraCharacteristics.SCALER_AVAILABLE_FORMATS.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getAvailableFormats();
            }
        });
        map.put(CaptureResult.STATISTICS_FACES.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getFaces();
            }
        });
        map.put(CaptureResult.STATISTICS_FACE_RECTANGLES.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.4
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getFaceRectangles();
            }
        });
        map.put(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.5
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getStreamConfigurationMap();
            }
        });
        map.put(CameraCharacteristics.SCALER_MANDATORY_STREAM_COMBINATIONS.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.6
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getMandatoryStreamCombinations();
            }
        });
        map.put(CameraCharacteristics.SCALER_MANDATORY_CONCURRENT_STREAM_COMBINATIONS.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.7
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getMandatoryConcurrentStreamCombinations();
            }
        });
        map.put(CameraCharacteristics.CONTROL_MAX_REGIONS_AE.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.8
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getMaxRegions(key);
            }
        });
        map.put(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.9
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getMaxRegions(key);
            }
        });
        map.put(CameraCharacteristics.CONTROL_MAX_REGIONS_AF.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.10
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getMaxRegions(key);
            }
        });
        map.put(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_RAW.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.11
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getMaxNumOutputs(key);
            }
        });
        map.put(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.12
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getMaxNumOutputs(key);
            }
        });
        map.put(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC_STALLING.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.13
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getMaxNumOutputs(key);
            }
        });
        map.put(CaptureRequest.TONEMAP_CURVE.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.14
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getTonemapCurve();
            }
        });
        map.put(CaptureResult.JPEG_GPS_LOCATION.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.15
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getGpsLocation();
            }
        });
        map.put(CaptureResult.STATISTICS_LENS_SHADING_CORRECTION_MAP.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.16
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getLensShadingMap();
            }
        });
        map.put(CaptureResult.STATISTICS_OIS_SAMPLES.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.17
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getOisSamples();
            }
        });
        map.put(CameraCharacteristics.CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_CAPABILITIES.getNativeKey(), new GetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.18
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.camera2.impl.GetCommand
            public <T> T getValue(CameraMetadataNative cameraMetadataNative, Key<T> key) {
                return (T) cameraMetadataNative.getExtendedSceneModeCapabilities();
            }
        });
        HashMap<Key<?>, SetCommand> map2 = new HashMap<>();
        sSetCommandMap = map2;
        map2.put(CameraCharacteristics.SCALER_AVAILABLE_FORMATS.getNativeKey(), new SetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.19
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.camera2.impl.SetCommand
            public <T> void setValue(CameraMetadataNative cameraMetadataNative, T t) {
                cameraMetadataNative.setAvailableFormats((int[]) t);
            }
        });
        map2.put(CaptureResult.STATISTICS_FACE_RECTANGLES.getNativeKey(), new SetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.20
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.camera2.impl.SetCommand
            public <T> void setValue(CameraMetadataNative cameraMetadataNative, T t) {
                cameraMetadataNative.setFaceRectangles((Rect[]) t);
            }
        });
        map2.put(CaptureResult.STATISTICS_FACES.getNativeKey(), new SetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.21
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.camera2.impl.SetCommand
            public <T> void setValue(CameraMetadataNative cameraMetadataNative, T t) {
                cameraMetadataNative.setFaces((Face[]) t);
            }
        });
        map2.put(CaptureRequest.TONEMAP_CURVE.getNativeKey(), new SetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.22
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.camera2.impl.SetCommand
            public <T> void setValue(CameraMetadataNative cameraMetadataNative, T t) {
                cameraMetadataNative.setTonemapCurve((TonemapCurve) t);
            }
        });
        map2.put(CaptureResult.JPEG_GPS_LOCATION.getNativeKey(), new SetCommand() { // from class: android.hardware.camera2.impl.CameraMetadataNative.23
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.camera2.impl.SetCommand
            public <T> void setValue(CameraMetadataNative cameraMetadataNative, T t) {
                cameraMetadataNative.setGpsLocation((Location) t);
            }
        });
        registerAllMarshalers();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        nativeWriteToParcel(parcel, this.mMetadataPtr);
    }

    public <T> T get(CameraCharacteristics.Key<T> key) {
        return (T) get(key.getNativeKey());
    }

    public <T> T get(CaptureResult.Key<T> key) {
        return (T) get(key.getNativeKey());
    }

    public <T> T get(CaptureRequest.Key<T> key) {
        return (T) get(key.getNativeKey());
    }

    public <T> T get(Key<T> key) {
        Objects.requireNonNull(key, "key must not be null");
        GetCommand getCommand = sGetCommandMap.get(key);
        if (getCommand != null) {
            return (T) getCommand.getValue(this, key);
        }
        return (T) getBase(key);
    }

    public void readFromParcel(Parcel parcel) {
        nativeReadFromParcel(parcel, this.mMetadataPtr);
        updateNativeAllocation();
    }

    public static void setupGlobalVendorTagDescriptor() throws ServiceSpecificException {
        int iNativeSetupGlobalVendorTagDescriptor = nativeSetupGlobalVendorTagDescriptor();
        if (iNativeSetupGlobalVendorTagDescriptor != 0) {
            throw new ServiceSpecificException(iNativeSetupGlobalVendorTagDescriptor, "Failure to set up global vendor tags");
        }
    }

    public <T> void set(Key<T> key, T t) {
        SetCommand setCommand = sSetCommandMap.get(key);
        if (setCommand != null) {
            setCommand.setValue(this, t);
        } else {
            setBase(key, t);
        }
    }

    public <T> void set(CaptureRequest.Key<T> key, T t) {
        set(key.getNativeKey(), t);
    }

    public <T> void set(CaptureResult.Key<T> key, T t) {
        set(key.getNativeKey(), t);
    }

    public <T> void set(CameraCharacteristics.Key<T> key, T t) {
        set(key.getNativeKey(), t);
    }

    private void close() {
        nativeClose(this.mMetadataPtr);
        this.mMetadataPtr = 0L;
        if (this.mBufferSize > 0) {
            VMRuntime.getRuntime().registerNativeFree(this.mBufferSize);
        }
        this.mBufferSize = 0L;
    }

    private <T> T getBase(CameraCharacteristics.Key<T> key) {
        return (T) getBase(key.getNativeKey());
    }

    private <T> T getBase(CaptureResult.Key<T> key) {
        return (T) getBase(key.getNativeKey());
    }

    private <T> T getBase(CaptureRequest.Key<T> key) {
        return (T) getBase(key.getNativeKey());
    }

    private <T> T getBase(Key<T> key) {
        int iNativeGetTagFromKeyLocal;
        byte[] values;
        if (key.hasTag()) {
            iNativeGetTagFromKeyLocal = key.getTag();
        } else {
            iNativeGetTagFromKeyLocal = nativeGetTagFromKeyLocal(this.mMetadataPtr, key.getName());
            key.cacheTag(iNativeGetTagFromKeyLocal);
        }
        byte[] values2 = readValues(iNativeGetTagFromKeyLocal);
        if (values2 == null) {
            if (((Key) key).mFallbackName == null || (values = readValues((iNativeGetTagFromKeyLocal = nativeGetTagFromKeyLocal(this.mMetadataPtr, ((Key) key).mFallbackName)))) == null) {
                return null;
            }
            values2 = values;
        }
        return (T) getMarshalerForKey(key, nativeGetTypeFromTagLocal(this.mMetadataPtr, iNativeGetTagFromKeyLocal)).unmarshal(ByteBuffer.wrap(values2).order(ByteOrder.nativeOrder()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] getAvailableFormats() {
        int[] iArr = (int[]) getBase(CameraCharacteristics.SCALER_AVAILABLE_FORMATS);
        if (iArr != null) {
            for (int i = 0; i < iArr.length; i++) {
                if (iArr[i] == 33) {
                    iArr[i] = 256;
                }
            }
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setFaces(Face[] faceArr) {
        int[] iArr;
        if (faceArr == null) {
            return false;
        }
        int length = faceArr.length;
        boolean z = true;
        for (Face face : faceArr) {
            if (face == null) {
                length--;
                Log.w(TAG, "setFaces - null face detected, skipping");
            } else if (face.getId() == -1) {
                z = false;
            }
        }
        Rect[] rectArr = new Rect[length];
        byte[] bArr = new byte[length];
        int[] iArr2 = null;
        if (z) {
            iArr2 = new int[length];
            iArr = new int[length * 6];
        } else {
            iArr = null;
        }
        int i = 0;
        for (Face face2 : faceArr) {
            if (face2 != null) {
                rectArr[i] = face2.getBounds();
                bArr[i] = (byte) face2.getScore();
                if (z) {
                    iArr2[i] = face2.getId();
                    int i2 = i * 6;
                    iArr[i2 + 0] = face2.getLeftEyePosition().x;
                    iArr[i2 + 1] = face2.getLeftEyePosition().y;
                    iArr[i2 + 2] = face2.getRightEyePosition().x;
                    iArr[i2 + 3] = face2.getRightEyePosition().y;
                    iArr[i2 + 4] = face2.getMouthPosition().x;
                    iArr[i2 + 5] = face2.getMouthPosition().y;
                }
                i++;
            }
        }
        set(CaptureResult.STATISTICS_FACE_RECTANGLES, rectArr);
        set(CaptureResult.STATISTICS_FACE_IDS, iArr2);
        set(CaptureResult.STATISTICS_FACE_LANDMARKS, iArr);
        set(CaptureResult.STATISTICS_FACE_SCORES, bArr);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Face[] getFaces() {
        Integer num = (Integer) get(CaptureResult.STATISTICS_FACE_DETECT_MODE);
        byte[] bArr = (byte[]) get(CaptureResult.STATISTICS_FACE_SCORES);
        Rect[] rectArr = (Rect[]) get(CaptureResult.STATISTICS_FACE_RECTANGLES);
        int[] iArr = (int[]) get(CaptureResult.STATISTICS_FACE_IDS);
        int[] iArr2 = (int[]) get(CaptureResult.STATISTICS_FACE_LANDMARKS);
        int i = 0;
        Integer num2 = 1;
        if (areValuesAllNull(num, bArr, rectArr, iArr, iArr2)) {
            return null;
        }
        if (num == null) {
            Log.w(TAG, "Face detect mode metadata is null, assuming the mode is SIMPLE");
            num = num2;
        } else if (num.intValue() > 2) {
            num = 2;
        } else {
            if (num.intValue() == 0) {
                return new Face[0];
            }
            if (num.intValue() != 1 && num.intValue() != 2) {
                Log.w(TAG, "Unknown face detect mode: " + num);
                return new Face[0];
            }
        }
        if (bArr == null || rectArr == null) {
            Log.w(TAG, "Expect face scores and rectangles to be non-null");
            return new Face[0];
        }
        if (bArr.length != rectArr.length) {
            Log.w(TAG, String.format("Face score size(%d) doesn match face rectangle size(%d)!", Integer.valueOf(bArr.length), Integer.valueOf(rectArr.length)));
        }
        int iMin = Math.min(bArr.length, rectArr.length);
        if (num.intValue() != 2) {
            num2 = num;
        } else if (iArr == null || iArr2 == null) {
            Log.w(TAG, "Expect face ids and landmarks to be non-null for FULL mode,fallback to SIMPLE mode");
        } else {
            if (iArr.length != iMin || iArr2.length != iMin * 6) {
                Log.w(TAG, String.format("Face id size(%d), or face landmark size(%d) don'tmatch face number(%d)!", Integer.valueOf(iArr.length), Integer.valueOf(iArr2.length * 6), Integer.valueOf(iMin)));
            }
            iMin = Math.min(Math.min(iMin, iArr.length), iArr2.length / 6);
            num2 = num;
        }
        ArrayList arrayList = new ArrayList();
        if (num2.intValue() == 1) {
            while (i < iMin) {
                if (bArr[i] <= 100 && bArr[i] >= 1) {
                    arrayList.add(new Face(rectArr[i], bArr[i]));
                }
                i++;
            }
        } else {
            while (i < iMin) {
                if (bArr[i] <= 100 && bArr[i] >= 1 && iArr[i] >= 0) {
                    int i2 = i * 6;
                    arrayList.add(new Face(rectArr[i], bArr[i], iArr[i], new Point(iArr2[i2], iArr2[i2 + 1]), new Point(iArr2[i2 + 2], iArr2[i2 + 3]), new Point(iArr2[i2 + 4], iArr2[i2 + 5])));
                }
                i++;
            }
        }
        Face[] faceArr = new Face[arrayList.size()];
        arrayList.toArray(faceArr);
        return faceArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect[] getFaceRectangles() {
        Rect[] rectArr = (Rect[]) getBase(CaptureResult.STATISTICS_FACE_RECTANGLES);
        if (rectArr == null) {
            return null;
        }
        Rect[] rectArr2 = new Rect[rectArr.length];
        for (int i = 0; i < rectArr.length; i++) {
            rectArr2[i] = new Rect(rectArr[i].left, rectArr[i].top, rectArr[i].right - rectArr[i].left, rectArr[i].bottom - rectArr[i].top);
        }
        return rectArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LensShadingMap getLensShadingMap() {
        float[] fArr = (float[]) getBase(CaptureResult.STATISTICS_LENS_SHADING_MAP);
        Size size = (Size) get(CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE);
        if (fArr == null) {
            return null;
        }
        if (size == null) {
            Log.w(TAG, "getLensShadingMap - Lens shading map size was null.");
            return null;
        }
        return new LensShadingMap(fArr, size.getHeight(), size.getWidth());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Location getGpsLocation() {
        String str = (String) get(CaptureResult.JPEG_GPS_PROCESSING_METHOD);
        double[] dArr = (double[]) get(CaptureResult.JPEG_GPS_COORDINATES);
        Long l = (Long) get(CaptureResult.JPEG_GPS_TIMESTAMP);
        if (areValuesAllNull(str, dArr, l)) {
            return null;
        }
        Location location = new Location(translateProcessToLocationProvider(str));
        if (l != null) {
            location.setTime(l.longValue() * 1000);
        } else {
            Log.w(TAG, "getGpsLocation - No timestamp for GPS location.");
        }
        if (dArr != null) {
            location.setLatitude(dArr[0]);
            location.setLongitude(dArr[1]);
            location.setAltitude(dArr[2]);
        } else {
            Log.w(TAG, "getGpsLocation - No coordinates for GPS location");
        }
        return location;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setGpsLocation(Location location) {
        if (location == null) {
            return false;
        }
        double[] dArr = {location.getLatitude(), location.getLongitude(), location.getAltitude()};
        String strTranslateLocationProviderToProcess = translateLocationProviderToProcess(location.getProvider());
        set(CaptureRequest.JPEG_GPS_TIMESTAMP, Long.valueOf(location.getTime() / 1000));
        set(CaptureRequest.JPEG_GPS_COORDINATES, dArr);
        if (strTranslateLocationProviderToProcess == null) {
            Log.w(TAG, "setGpsLocation - No process method, Location is not from a GPS or NETWORKprovider");
        } else {
            setBase(CaptureRequest.JPEG_GPS_PROCESSING_METHOD, strTranslateLocationProviderToProcess);
        }
        return true;
    }

    private void parseRecommendedConfigurations(RecommendedStreamConfiguration[] recommendedStreamConfigurationArr, StreamConfigurationMap streamConfigurationMap, boolean z, ArrayList<ArrayList<StreamConfiguration>> arrayList, ArrayList<ArrayList<StreamConfigurationDuration>> arrayList2, ArrayList<ArrayList<StreamConfigurationDuration>> arrayList3, boolean[] zArr) {
        int iImageFormatToPublic;
        char c;
        int i;
        Size size;
        int i2;
        int i3;
        int i4;
        int i5;
        StreamConfigurationDuration streamConfigurationDuration;
        StreamConfigurationDuration streamConfigurationDuration2;
        StreamConfigurationDuration streamConfigurationDuration3;
        RecommendedStreamConfiguration[] recommendedStreamConfigurationArr2 = recommendedStreamConfigurationArr;
        StreamConfigurationMap streamConfigurationMap2 = streamConfigurationMap;
        arrayList.ensureCapacity(32);
        arrayList2.ensureCapacity(32);
        arrayList3.ensureCapacity(32);
        boolean z2 = false;
        for (int i6 = 0; i6 < 32; i6++) {
            arrayList.add(new ArrayList<>());
            arrayList2.add(new ArrayList<>());
            arrayList3.add(new ArrayList<>());
        }
        int length = recommendedStreamConfigurationArr2.length;
        int i7 = 0;
        while (i7 < length) {
            RecommendedStreamConfiguration recommendedStreamConfiguration = recommendedStreamConfigurationArr2[i7];
            int width = recommendedStreamConfiguration.getWidth();
            int height = recommendedStreamConfiguration.getHeight();
            int format = recommendedStreamConfiguration.getFormat();
            if (z) {
                iImageFormatToPublic = StreamConfigurationMap.depthFormatToPublic(format);
            } else {
                iImageFormatToPublic = StreamConfigurationMap.imageFormatToPublic(format);
            }
            int i8 = iImageFormatToPublic;
            Size size2 = new Size(width, height);
            int usecaseBitmap = recommendedStreamConfiguration.getUsecaseBitmap();
            if (!recommendedStreamConfiguration.isInput()) {
                StreamConfiguration streamConfiguration = new StreamConfiguration(format, width, height, z2);
                long outputMinFrameDuration = streamConfigurationMap2.getOutputMinFrameDuration(i8, size2);
                if (outputMinFrameDuration > 0) {
                    i = usecaseBitmap;
                    size = size2;
                    i2 = i8;
                    i3 = format;
                    i4 = width;
                    i5 = height;
                    streamConfigurationDuration = new StreamConfigurationDuration(format, width, height, outputMinFrameDuration);
                } else {
                    i = usecaseBitmap;
                    size = size2;
                    i2 = i8;
                    i3 = format;
                    i4 = width;
                    i5 = height;
                    streamConfigurationDuration = null;
                }
                long outputStallDuration = streamConfigurationMap2.getOutputStallDuration(i2, size);
                if (outputStallDuration > 0) {
                    streamConfigurationDuration2 = streamConfigurationDuration;
                    streamConfigurationDuration3 = new StreamConfigurationDuration(i3, i4, i5, outputStallDuration);
                } else {
                    streamConfigurationDuration2 = streamConfigurationDuration;
                    streamConfigurationDuration3 = null;
                }
                c = ' ';
                for (int i9 = 0; i9 < 32; i9++) {
                    if ((i & (1 << i9)) != 0) {
                        arrayList.get(i9).add(streamConfiguration);
                        if (outputMinFrameDuration > 0) {
                            arrayList2.get(i9).add(streamConfigurationDuration2);
                        }
                        if (outputStallDuration > 0) {
                            arrayList3.get(i9).add(streamConfigurationDuration3);
                        }
                        if (zArr != null && !zArr[i9] && i2 == 34) {
                            zArr[i9] = true;
                        }
                    }
                }
            } else {
                c = ' ';
                if (usecaseBitmap != 16) {
                    throw new IllegalArgumentException("Recommended input stream configurations should only be advertised in the ZSL use case!");
                }
                arrayList.get(4).add(new StreamConfiguration(format, width, height, true));
            }
            i7++;
            recommendedStreamConfigurationArr2 = recommendedStreamConfigurationArr;
            streamConfigurationMap2 = streamConfigurationMap;
            z2 = false;
        }
    }

    private class StreamConfigurationData {
        StreamConfigurationDuration[] minDurationArray;
        StreamConfigurationDuration[] stallDurationArray;
        StreamConfiguration[] streamConfigurationArray;

        private StreamConfigurationData() {
            this.streamConfigurationArray = null;
            this.minDurationArray = null;
            this.stallDurationArray = null;
        }
    }

    public void initializeStreamConfigurationData(ArrayList<StreamConfiguration> arrayList, ArrayList<StreamConfigurationDuration> arrayList2, ArrayList<StreamConfigurationDuration> arrayList3, StreamConfigurationData streamConfigurationData) {
        if (streamConfigurationData == null || arrayList == null) {
            return;
        }
        streamConfigurationData.streamConfigurationArray = new StreamConfiguration[arrayList.size()];
        streamConfigurationData.streamConfigurationArray = (StreamConfiguration[]) arrayList.toArray(streamConfigurationData.streamConfigurationArray);
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            streamConfigurationData.minDurationArray = new StreamConfigurationDuration[arrayList2.size()];
            streamConfigurationData.minDurationArray = (StreamConfigurationDuration[]) arrayList2.toArray(streamConfigurationData.minDurationArray);
        } else {
            streamConfigurationData.minDurationArray = new StreamConfigurationDuration[0];
        }
        if (arrayList3 != null && !arrayList3.isEmpty()) {
            streamConfigurationData.stallDurationArray = new StreamConfigurationDuration[arrayList3.size()];
            streamConfigurationData.stallDurationArray = (StreamConfigurationDuration[]) arrayList3.toArray(streamConfigurationData.stallDurationArray);
        } else {
            streamConfigurationData.stallDurationArray = new StreamConfigurationDuration[0];
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x01b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.ArrayList<android.hardware.camera2.params.RecommendedStreamConfigurationMap> getRecommendedStreamConfigurations() {
        /*
            Method dump skipped, instruction units count: 505
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.camera2.impl.CameraMetadataNative.getRecommendedStreamConfigurations():java.util.ArrayList");
    }

    private boolean isBurstSupported() {
        for (int i : (int[]) getBase(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES)) {
            if (i == 6) {
                return true;
            }
        }
        return false;
    }

    private MandatoryStreamCombination[] getMandatoryStreamCombinationsHelper(boolean z) {
        List<MandatoryStreamCombination> availableMandatoryStreamCombinations;
        int[] iArr = (int[]) getBase(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        ArrayList arrayList = new ArrayList();
        arrayList.ensureCapacity(iArr.length);
        for (int i : iArr) {
            arrayList.add(new Integer(i));
        }
        MandatoryStreamCombination.Builder builder = new MandatoryStreamCombination.Builder(this.mCameraId, ((Integer) getBase(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue(), this.mDisplaySize, arrayList, getStreamConfigurationMap());
        if (z) {
            availableMandatoryStreamCombinations = builder.getAvailableMandatoryConcurrentStreamCombinations();
        } else {
            availableMandatoryStreamCombinations = builder.getAvailableMandatoryStreamCombinations();
        }
        if (availableMandatoryStreamCombinations == null || availableMandatoryStreamCombinations.isEmpty()) {
            return null;
        }
        return (MandatoryStreamCombination[]) availableMandatoryStreamCombinations.toArray(new MandatoryStreamCombination[availableMandatoryStreamCombinations.size()]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MandatoryStreamCombination[] getMandatoryConcurrentStreamCombinations() {
        if (this.mHasMandatoryConcurrentStreams) {
            return getMandatoryStreamCombinationsHelper(true);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MandatoryStreamCombination[] getMandatoryStreamCombinations() {
        return getMandatoryStreamCombinationsHelper(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StreamConfigurationMap getStreamConfigurationMap() {
        return new StreamConfigurationMap((StreamConfiguration[]) getBase(CameraCharacteristics.SCALER_AVAILABLE_STREAM_CONFIGURATIONS), (StreamConfigurationDuration[]) getBase(CameraCharacteristics.SCALER_AVAILABLE_MIN_FRAME_DURATIONS), (StreamConfigurationDuration[]) getBase(CameraCharacteristics.SCALER_AVAILABLE_STALL_DURATIONS), (StreamConfiguration[]) getBase(CameraCharacteristics.DEPTH_AVAILABLE_DEPTH_STREAM_CONFIGURATIONS), (StreamConfigurationDuration[]) getBase(CameraCharacteristics.DEPTH_AVAILABLE_DEPTH_MIN_FRAME_DURATIONS), (StreamConfigurationDuration[]) getBase(CameraCharacteristics.DEPTH_AVAILABLE_DEPTH_STALL_DURATIONS), (StreamConfiguration[]) getBase(CameraCharacteristics.DEPTH_AVAILABLE_DYNAMIC_DEPTH_STREAM_CONFIGURATIONS), (StreamConfigurationDuration[]) getBase(CameraCharacteristics.DEPTH_AVAILABLE_DYNAMIC_DEPTH_MIN_FRAME_DURATIONS), (StreamConfigurationDuration[]) getBase(CameraCharacteristics.DEPTH_AVAILABLE_DYNAMIC_DEPTH_STALL_DURATIONS), (StreamConfiguration[]) getBase(CameraCharacteristics.HEIC_AVAILABLE_HEIC_STREAM_CONFIGURATIONS), (StreamConfigurationDuration[]) getBase(CameraCharacteristics.HEIC_AVAILABLE_HEIC_MIN_FRAME_DURATIONS), (StreamConfigurationDuration[]) getBase(CameraCharacteristics.HEIC_AVAILABLE_HEIC_STALL_DURATIONS), (HighSpeedVideoConfiguration[]) getBase(CameraCharacteristics.CONTROL_AVAILABLE_HIGH_SPEED_VIDEO_CONFIGURATIONS), (ReprocessFormatsMap) getBase(CameraCharacteristics.SCALER_AVAILABLE_INPUT_OUTPUT_FORMATS_MAP), isBurstSupported());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> Integer getMaxRegions(Key<T> key) {
        int[] iArr = (int[]) getBase(CameraCharacteristics.CONTROL_MAX_REGIONS);
        if (iArr == null) {
            return null;
        }
        if (key.equals(CameraCharacteristics.CONTROL_MAX_REGIONS_AE)) {
            return Integer.valueOf(iArr[0]);
        }
        if (key.equals(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB)) {
            return Integer.valueOf(iArr[1]);
        }
        if (key.equals(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)) {
            return Integer.valueOf(iArr[2]);
        }
        throw new AssertionError("Invalid key " + key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> Integer getMaxNumOutputs(Key<T> key) {
        int[] iArr = (int[]) getBase(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_STREAMS);
        if (iArr == null) {
            return null;
        }
        if (key.equals(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_RAW)) {
            return Integer.valueOf(iArr[0]);
        }
        if (key.equals(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC)) {
            return Integer.valueOf(iArr[1]);
        }
        if (key.equals(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC_STALLING)) {
            return Integer.valueOf(iArr[2]);
        }
        throw new AssertionError("Invalid key " + key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> TonemapCurve getTonemapCurve() {
        float[] fArr = (float[]) getBase(CaptureRequest.TONEMAP_CURVE_RED);
        float[] fArr2 = (float[]) getBase(CaptureRequest.TONEMAP_CURVE_GREEN);
        float[] fArr3 = (float[]) getBase(CaptureRequest.TONEMAP_CURVE_BLUE);
        if (areValuesAllNull(fArr, fArr2, fArr3)) {
            return null;
        }
        if (fArr == null || fArr2 == null || fArr3 == null) {
            Log.w(TAG, "getTonemapCurve - missing tone curve components");
            return null;
        }
        return new TonemapCurve(fArr, fArr2, fArr3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OisSample[] getOisSamples() {
        long[] jArr = (long[]) getBase(CaptureResult.STATISTICS_OIS_TIMESTAMPS);
        float[] fArr = (float[]) getBase(CaptureResult.STATISTICS_OIS_X_SHIFTS);
        float[] fArr2 = (float[]) getBase(CaptureResult.STATISTICS_OIS_Y_SHIFTS);
        if (jArr == null) {
            if (fArr != null) {
                throw new AssertionError("timestamps is null but xShifts is not");
            }
            if (fArr2 == null) {
                return null;
            }
            throw new AssertionError("timestamps is null but yShifts is not");
        }
        if (fArr == null) {
            throw new AssertionError("timestamps is not null but xShifts is");
        }
        if (fArr2 == null) {
            throw new AssertionError("timestamps is not null but yShifts is");
        }
        if (fArr.length != jArr.length) {
            throw new AssertionError(String.format("timestamps has %d entries but xShifts has %d", Integer.valueOf(jArr.length), Integer.valueOf(fArr.length)));
        }
        if (fArr2.length != jArr.length) {
            throw new AssertionError(String.format("timestamps has %d entries but yShifts has %d", Integer.valueOf(jArr.length), Integer.valueOf(fArr2.length)));
        }
        OisSample[] oisSampleArr = new OisSample[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            oisSampleArr[i] = new OisSample(jArr[i], fArr[i], fArr2[i]);
        }
        return oisSampleArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Capability[] getExtendedSceneModeCapabilities() {
        int length;
        int[] iArr = (int[]) getBase(CameraCharacteristics.CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_MAX_SIZES);
        float[] fArr = (float[]) getBase(CameraCharacteristics.CONTROL_AVAILABLE_EXTENDED_SCENE_MODE_ZOOM_RATIO_RANGES);
        Range range = (Range) getBase(CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE);
        float fFloatValue = ((Float) getBase(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue();
        if (iArr == null) {
            return null;
        }
        if (iArr.length % 3 != 0) {
            throw new AssertionError("availableExtendedSceneModeMaxSizes must be tuples of [mode, width, height]");
        }
        int length2 = iArr.length / 3;
        if (fArr == null) {
            length = 0;
        } else {
            if (fArr.length % 2 != 0) {
                throw new AssertionError("availableExtendedSceneModeZoomRanges must be tuples of [minZoom, maxZoom]");
            }
            length = fArr.length / 2;
            if (length2 - length != 1) {
                throw new AssertionError("Number of extended scene mode zoom ranges must be 1 less than number of supported modes");
            }
        }
        float fFloatValue2 = 1.0f;
        if (range != null) {
            fFloatValue2 = ((Float) range.getLower()).floatValue();
            fFloatValue = ((Float) range.getUpper()).floatValue();
        }
        Capability[] capabilityArr = new Capability[length2];
        int i = 0;
        for (int i2 = 0; i2 < length2; i2++) {
            int i3 = i2 * 3;
            int i4 = iArr[i3];
            int i5 = iArr[i3 + 1];
            int i6 = iArr[i3 + 2];
            if (i4 != 0 && i < length) {
                int i7 = i * 2;
                capabilityArr[i2] = new Capability(i4, i5, i6, fArr[i7], fArr[i7 + 1]);
                i++;
            } else {
                capabilityArr[i2] = new Capability(i4, i5, i6, fFloatValue2, fFloatValue);
            }
        }
        return capabilityArr;
    }

    private <T> void setBase(CameraCharacteristics.Key<T> key, T t) {
        setBase(key.getNativeKey(), t);
    }

    private <T> void setBase(CaptureResult.Key<T> key, T t) {
        setBase(key.getNativeKey(), t);
    }

    private <T> void setBase(CaptureRequest.Key<T> key, T t) {
        setBase(key.getNativeKey(), t);
    }

    private <T> void setBase(Key<T> key, T t) {
        int iNativeGetTagFromKeyLocal;
        if (key.hasTag()) {
            iNativeGetTagFromKeyLocal = key.getTag();
        } else {
            iNativeGetTagFromKeyLocal = nativeGetTagFromKeyLocal(this.mMetadataPtr, key.getName());
            key.cacheTag(iNativeGetTagFromKeyLocal);
        }
        if (t == null) {
            writeValues(iNativeGetTagFromKeyLocal, null);
            return;
        }
        Marshaler marshalerForKey = getMarshalerForKey(key, nativeGetTypeFromTagLocal(this.mMetadataPtr, iNativeGetTagFromKeyLocal));
        byte[] bArr = new byte[marshalerForKey.calculateMarshalSize(t)];
        marshalerForKey.marshal(t, ByteBuffer.wrap(bArr).order(ByteOrder.nativeOrder()));
        writeValues(iNativeGetTagFromKeyLocal, bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setAvailableFormats(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = iArr[i];
            if (iArr[i] == 256) {
                iArr2[i] = 33;
            }
        }
        setBase(CameraCharacteristics.SCALER_AVAILABLE_FORMATS, iArr2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setFaceRectangles(Rect[] rectArr) {
        if (rectArr == null) {
            return false;
        }
        int length = rectArr.length;
        Rect[] rectArr2 = new Rect[length];
        for (int i = 0; i < length; i++) {
            rectArr2[i] = new Rect(rectArr[i].left, rectArr[i].top, rectArr[i].right + rectArr[i].left, rectArr[i].bottom + rectArr[i].top);
        }
        setBase(CaptureResult.STATISTICS_FACE_RECTANGLES, rectArr2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> boolean setTonemapCurve(TonemapCurve tonemapCurve) {
        if (tonemapCurve == null) {
            return false;
        }
        float[][] fArr = new float[3][];
        for (int i = 0; i <= 2; i++) {
            fArr[i] = new float[tonemapCurve.getPointCount(i) * 2];
            tonemapCurve.copyColorCurve(i, fArr[i], 0);
        }
        setBase(CaptureRequest.TONEMAP_CURVE_RED, fArr[0]);
        setBase(CaptureRequest.TONEMAP_CURVE_GREEN, fArr[1]);
        setBase(CaptureRequest.TONEMAP_CURVE_BLUE, fArr[2]);
        return true;
    }

    private void updateNativeAllocation() {
        long jNativeGetBufferSize = nativeGetBufferSize(this.mMetadataPtr);
        long j = this.mBufferSize;
        if (jNativeGetBufferSize != j) {
            if (j > 0) {
                VMRuntime.getRuntime().registerNativeFree(this.mBufferSize);
            }
            this.mBufferSize = jNativeGetBufferSize;
            if (jNativeGetBufferSize > 0) {
                VMRuntime.getRuntime().registerNativeAllocation(this.mBufferSize);
            }
        }
    }

    public void setCameraId(int i) {
        this.mCameraId = i;
    }

    public void setHasMandatoryConcurrentStreams(boolean z) {
        this.mHasMandatoryConcurrentStreams = z;
    }

    public void setDisplaySize(Size size) {
        this.mDisplaySize = size;
    }

    public void swap(CameraMetadataNative cameraMetadataNative) {
        nativeSwap(this.mMetadataPtr, cameraMetadataNative.mMetadataPtr);
        this.mCameraId = cameraMetadataNative.mCameraId;
        this.mHasMandatoryConcurrentStreams = cameraMetadataNative.mHasMandatoryConcurrentStreams;
        this.mDisplaySize = cameraMetadataNative.mDisplaySize;
        updateNativeAllocation();
        cameraMetadataNative.updateNativeAllocation();
    }

    public int getEntryCount() {
        return nativeGetEntryCount(this.mMetadataPtr);
    }

    public boolean isEmpty() {
        return nativeIsEmpty(this.mMetadataPtr);
    }

    public long getMetadataPtr() {
        return this.mMetadataPtr;
    }

    public <K> ArrayList<K> getAllVendorKeys(Class<K> cls) {
        Objects.requireNonNull(cls);
        return nativeGetAllVendorKeys(this.mMetadataPtr, cls);
    }

    public static int getTag(String str) {
        return nativeGetTagFromKey(str, Long.MAX_VALUE);
    }

    public static int getTag(String str, long j) {
        return nativeGetTagFromKey(str, j);
    }

    public static int getNativeType(int i, long j) {
        return nativeGetTypeFromTag(i, j);
    }

    public void writeValues(int i, byte[] bArr) {
        nativeWriteValues(i, bArr, this.mMetadataPtr);
    }

    public byte[] readValues(int i) {
        return nativeReadValues(i, this.mMetadataPtr);
    }

    public void dumpToLog() {
        try {
            nativeDump(this.mMetadataPtr);
        } catch (IOException e) {
            Log.wtf(TAG, "Dump logging failed", e);
        }
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    private static <T> Marshaler<T> getMarshalerForKey(Key<T> key, int i) {
        return MarshalRegistry.getMarshaler(key.getTypeReference(), i);
    }

    private static void registerAllMarshalers() {
        MarshalQueryable[] marshalQueryableArr = {new MarshalQueryablePrimitive(), new MarshalQueryableEnum(), new MarshalQueryableArray(), new MarshalQueryableBoolean(), new MarshalQueryableNativeByteToInteger(), new MarshalQueryableRect(), new MarshalQueryableSize(), new MarshalQueryableSizeF(), new MarshalQueryableString(), new MarshalQueryableReprocessFormatsMap(), new MarshalQueryableRange(), new MarshalQueryablePair(), new MarshalQueryableMeteringRectangle(), new MarshalQueryableColorSpaceTransform(), new MarshalQueryableStreamConfiguration(), new MarshalQueryableStreamConfigurationDuration(), new MarshalQueryableRggbChannelVector(), new MarshalQueryableBlackLevelPattern(), new MarshalQueryableHighSpeedVideoConfiguration(), new MarshalQueryableRecommendedStreamConfiguration(), new MarshalQueryableParcelable()};
        for (int i = 0; i < 21; i++) {
            MarshalRegistry.registerMarshalQueryable(marshalQueryableArr[i]);
        }
    }

    private static boolean areValuesAllNull(Object... objArr) {
        for (Object obj : objArr) {
            if (obj != null) {
                return false;
            }
        }
        return true;
    }
}
