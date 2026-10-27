package com.google.android.projection.protocol;

import android.util.Log;
import com.google.android.projection.proto.Protos;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;

/* JADX INFO: loaded from: classes.dex */
public class NavigationStatus implements CarServiceProvider, NavigationStatusCallbacks {
    private static final String TAG = "AHU.NAVIGATION";
    private final int mColorDepthBits;
    private final int mHeight;
    private final int mInstrumentClusterType;
    private final NavigationStatusListener mListener;
    private final int mMinIntervalMs;
    private long mNativeNavigationStatus;
    private final int mWidth;
    public static final int DISPLAY_DISTANCE_METERS = Protos.NavigationNextTurnDistanceEvent.DistanceUnits.METERS.getNumber();
    public static final int DISPLAY_DISTANCE_KILOMETERS = Protos.NavigationNextTurnDistanceEvent.DistanceUnits.KILOMETERS.getNumber();
    public static final int DISPLAY_DISTANCE_KILOMETERS_P1 = Protos.NavigationNextTurnDistanceEvent.DistanceUnits.KILOMETERS_P1.getNumber();
    public static final int DISPLAY_DISTANCE_MILES_P1 = Protos.NavigationNextTurnDistanceEvent.DistanceUnits.MILES_P1.getNumber();
    public static final int DISPLAY_DISTANCE_FEET = Protos.NavigationNextTurnDistanceEvent.DistanceUnits.FEET.getNumber();
    public static final int DISPLAY_DISTANCE_YARDS = Protos.NavigationNextTurnDistanceEvent.DistanceUnits.YARDS.getNumber();

    public interface NavigationStatusListener {
        void onNavigationCurrentPosition(Protos.NavigationCurrentPosition navigationCurrentPosition);

        void onNavigationState(Protos.NavigationState navigationState);

        void onNextTurn(String str, int i, int i2, byte[] bArr, int i3, int i4);

        void onNextTurnDistance(int i, int i2, int i3, int i4);

        void onStatus(int i);
    }

    private native int nativeInit(int i, long j, int i2, int i3, int i4, int i5, int i6);

    private native void nativeStart();

    private native void nativeStop();

    public NavigationStatus(NavigationStatusListener listener, int minIntervalMs, Protos.NavigationStatusService.InstrumentClusterType type, int height, int width, int colourDepthBits) {
        this.mListener = listener;
        this.mMinIntervalMs = minIntervalMs;
        this.mInstrumentClusterType = type.getNumber();
        this.mHeight = height;
        this.mWidth = width;
        this.mColorDepthBits = colourDepthBits;
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int id, long nativeGalReceiver) {
        if (nativeInit(id, nativeGalReceiver, this.mMinIntervalMs, this.mInstrumentClusterType, this.mHeight, this.mWidth, this.mColorDepthBits) == 0) {
            nativeStart();
            return true;
        }
        return false;
    }

    public void start() {
        nativeStart();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        nativeStop();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeNavigationStatus;
    }

    @Override // com.google.android.projection.protocol.NavigationStatusCallbacks
    public void navigationStatusCallback(int status) {
        this.mListener.onStatus(status);
    }

    @Override // com.google.android.projection.protocol.NavigationStatusCallbacks
    public void navigationNextTurnCallback(String road, int turnSide, int event, byte[] image, int turnAngle, int turnNumber) {
        this.mListener.onNextTurn(road, turnSide, event, image, turnAngle, turnNumber);
    }

    @Override // com.google.android.projection.protocol.NavigationStatusCallbacks
    public void navigationNextTurnDistanceCallback(int distanceMeters, int timeSeconds, int displayDistanceE3, int displayDistanceUnit) {
        this.mListener.onNextTurnDistance(distanceMeters, timeSeconds, displayDistanceE3, displayDistanceUnit);
    }

    @Override // com.google.android.projection.protocol.NavigationStatusCallbacks
    public void navigationStateCallback(byte[] state) {
        try {
            Protos.NavigationState navigationState = Protos.NavigationState.parseFrom(state, ExtensionRegistryLite.getEmptyRegistry());
            this.mListener.onNavigationState(navigationState);
        } catch (InvalidProtocolBufferException e) {
            Log.e("AHU.NAVIGATION", "Error parsing protocol buffer in callback", e);
            throw new IllegalArgumentException("Error parsing protocol buffer in callback: ", e);
        }
    }

    @Override // com.google.android.projection.protocol.NavigationStatusCallbacks
    public void navigationCurrentPositionCallback(byte[] position) {
        try {
            Protos.NavigationCurrentPosition navigationCurrentPosition = Protos.NavigationCurrentPosition.parseFrom(position, ExtensionRegistryLite.getEmptyRegistry());
            this.mListener.onNavigationCurrentPosition(navigationCurrentPosition);
        } catch (InvalidProtocolBufferException e) {
            Log.e("AHU.NAVIGATION", "Error parsing protocol buffer in callback", e);
            throw new IllegalArgumentException("Error parsing protocol buffer in callback", e);
        }
    }
}
