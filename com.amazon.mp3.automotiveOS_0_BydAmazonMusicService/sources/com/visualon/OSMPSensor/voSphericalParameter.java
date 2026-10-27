package com.visualon.OSMPSensor;

/* JADX INFO: loaded from: classes7.dex */
public class voSphericalParameter {
    private boolean m_bPortrait;
    private float m_fFov;
    private float[] m_fRotateMatrix;

    public voSphericalParameter(boolean z, float[] fArr, float f) {
        this.m_bPortrait = z;
        this.m_fRotateMatrix = fArr;
        this.m_fFov = f;
    }
}
