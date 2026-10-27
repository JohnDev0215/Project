package androidx.test.core.view;

import android.view.MotionEvent;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class PointerPropertiesBuilder {
    private int id;
    private int toolType;

    private PointerPropertiesBuilder() {
    }

    public static PointerPropertiesBuilder newBuilder() {
        return new PointerPropertiesBuilder();
    }

    public MotionEvent.PointerProperties build() {
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = this.id;
        pointerProperties.toolType = this.toolType;
        return pointerProperties;
    }

    public PointerPropertiesBuilder setId(int i) {
        this.id = i;
        return this;
    }

    public PointerPropertiesBuilder setToolType(int i) {
        this.toolType = i;
        return this;
    }
}
