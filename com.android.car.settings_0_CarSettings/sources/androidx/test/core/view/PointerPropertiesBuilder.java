package androidx.test.core.view;

import android.view.MotionEvent;

/* JADX INFO: loaded from: classes5.dex */
public class PointerPropertiesBuilder {
    private int id;
    private int toolType;

    private PointerPropertiesBuilder() {
    }

    public PointerPropertiesBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public PointerPropertiesBuilder setToolType(int toolType) {
        this.toolType = toolType;
        return this;
    }

    public MotionEvent.PointerProperties build() {
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = this.id;
        pointerProperties.toolType = this.toolType;
        return pointerProperties;
    }

    public static PointerPropertiesBuilder newBuilder() {
        return new PointerPropertiesBuilder();
    }
}
