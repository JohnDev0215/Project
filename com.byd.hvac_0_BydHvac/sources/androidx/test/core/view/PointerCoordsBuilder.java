package androidx.test.core.view;

import android.view.MotionEvent;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class PointerCoordsBuilder {
    private float orientation;
    private float toolMajor;
    private float toolMinor;
    private float touchMajor;
    private float touchMinor;
    private float x = 0.0f;
    private float y = 0.0f;
    private float pressure = 1.0f;
    private float size = 1.0f;

    private PointerCoordsBuilder() {
    }

    public static PointerCoordsBuilder newBuilder() {
        return new PointerCoordsBuilder();
    }

    public MotionEvent.PointerCoords build() {
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.x = this.x;
        pointerCoords.y = this.y;
        pointerCoords.pressure = this.pressure;
        pointerCoords.size = this.size;
        pointerCoords.touchMajor = this.touchMajor;
        pointerCoords.touchMinor = this.touchMinor;
        pointerCoords.toolMajor = this.toolMajor;
        pointerCoords.toolMinor = this.toolMinor;
        pointerCoords.orientation = this.orientation;
        return pointerCoords;
    }

    public PointerCoordsBuilder setCoords(float f, float f2) {
        this.x = f;
        this.y = f2;
        return this;
    }

    public PointerCoordsBuilder setOrientation(float f) {
        this.orientation = f;
        return this;
    }

    public PointerCoordsBuilder setPressure(float f) {
        this.pressure = f;
        return this;
    }

    public PointerCoordsBuilder setSize(float f) {
        this.size = f;
        return this;
    }

    public PointerCoordsBuilder setTool(float f, float f2) {
        this.toolMajor = f;
        this.toolMinor = f2;
        return this;
    }

    public PointerCoordsBuilder setTouch(float f, float f2) {
        this.touchMajor = f;
        this.touchMinor = f2;
        return this;
    }
}
