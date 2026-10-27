package androidx.constraintlayout.motion.widget;

/* JADX INFO: compiled from: DesignTool.java */
/* JADX INFO: loaded from: classes.dex */
public interface ProxyInterface {
    int designAccess(int i, String str, Object obj, float[] fArr, int i2, float[] fArr2, int i3);

    float getKeyFramePosition(Object obj, int i, float f2, float f3);

    Object getKeyframeAtLocation(Object obj, float f2, float f3);

    Boolean getPositionKeyframe(Object obj, Object obj2, float f2, float f3, String[] strArr, float[] fArr);

    long getTransitionTimeMs();

    void setAttributes(int i, String str, Object obj, Object obj2);

    void setKeyFrame(Object obj, int i, String str, Object obj2);

    boolean setKeyFramePosition(Object obj, int i, int i2, float f2, float f3);

    void setToolPosition(float f2);
}
