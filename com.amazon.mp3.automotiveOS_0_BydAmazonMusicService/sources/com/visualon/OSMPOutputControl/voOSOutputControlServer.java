package com.visualon.OSMPOutputControl;

import android.content.Context;
import android.os.Handler;
import com.visualon.OSMPOutputControl.voOSDisplayManager;
import com.visualon.OSMPOutputControl.voOSHDMIStateCheck;
import com.visualon.OSMPUtils.voLog;
import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public class voOSOutputControlServer {
    private static final String TAG = "@@@voOSOutputControlServer";
    private static voOSHDMIStateCheck m_HDMIStateCheck;
    private final Context mContext;
    private voOSDisplayManager mDisplay;
    private Handler mEventHandler;
    private onOutputControlListener mListener = null;
    private voOSHDMIStateCheck.onHDMIStateChangeListener mHDMICheckState = new voOSHDMIStateCheck.onHDMIStateChangeListener() { // from class: com.visualon.OSMPOutputControl.voOSOutputControlServer.1
        /* JADX WARN: Removed duplicated region for block: B:27:0x005b  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x006c  */
        /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
        @Override // com.visualon.OSMPOutputControl.voOSHDMIStateCheck.onHDMIStateChangeListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onHDMIStateChangeEvent(int r6, java.lang.Object r7) {
            /*
                r5 = this;
                com.visualon.OSMPOutputControl.voOSHDMIStateCheck r0 = com.visualon.OSMPOutputControl.voOSOutputControlServer.access$000()
                java.lang.String r1 = "@@@voOSOutputControlServer"
                r2 = 0
                if (r0 != 0) goto L17
                boolean r6 = com.visualon.OSMPUtils.voLog.enablePrintLog()
                if (r6 == 0) goto L16
                java.lang.Object[] r6 = new java.lang.Object[r2]
                java.lang.String r7 = "m_HDMIStateCheck is null, do not use onHDMIStateChangeEvent "
                com.visualon.OSMPUtils.voLog.e(r1, r7, r6)
            L16:
                return
            L17:
                r0 = 1
                if (r6 == 0) goto L34
                if (r6 == r0) goto L32
                r3 = 2048(0x800, float:2.87E-42)
                if (r6 == r3) goto L21
                goto L30
            L21:
                if (r7 == 0) goto L30
                boolean r6 = r7 instanceof java.lang.Integer
                if (r6 == 0) goto L30
                java.lang.Integer r7 = (java.lang.Integer) r7
                int r6 = r7.intValue()
                if (r6 != r0) goto L30
                goto L32
            L30:
                r6 = r2
                goto L46
            L32:
                r6 = r0
                goto L46
            L34:
                com.visualon.OSMPOutputControl.voOSOutputControlServer r6 = com.visualon.OSMPOutputControl.voOSOutputControlServer.this
                com.visualon.OSMPOutputControl.voOSOutputControlServer$onOutputControlListener r6 = com.visualon.OSMPOutputControl.voOSOutputControlServer.access$100(r6)
                if (r6 == 0) goto L30
                com.visualon.OSMPOutputControl.voOSOutputControlServer r6 = com.visualon.OSMPOutputControl.voOSOutputControlServer.this
                com.visualon.OSMPOutputControl.voOSOutputControlServer$onOutputControlListener r6 = com.visualon.OSMPOutputControl.voOSOutputControlServer.access$100(r6)
                r6.onHdmiDisconnect()
                goto L30
            L46:
                r7 = 2
                int[] r7 = new int[r7]
                com.visualon.OSMPUtils.voOSType$VOOSMP_OUTPUT_CONTROL_TYPE r3 = com.visualon.OSMPUtils.voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_HDCP
                int r3 = r3.getValue()
                r7[r2] = r3
                r7[r0] = r6
                com.visualon.OSMPOutputControl.voOSOutputControlServer r6 = com.visualon.OSMPOutputControl.voOSOutputControlServer.this
                com.visualon.OSMPOutputControl.voOSOutputControlServer$onOutputControlListener r6 = com.visualon.OSMPOutputControl.voOSOutputControlServer.access$100(r6)
                if (r6 == 0) goto L66
                com.visualon.OSMPOutputControl.voOSOutputControlServer r6 = com.visualon.OSMPOutputControl.voOSOutputControlServer.this
                com.visualon.OSMPOutputControl.voOSOutputControlServer$onOutputControlListener r6 = com.visualon.OSMPOutputControl.voOSOutputControlServer.access$100(r6)
                r3 = 99
                r6.onSetParam(r3, r7)
            L66:
                boolean r6 = com.visualon.OSMPUtils.voLog.enablePrintLog()
                if (r6 == 0) goto L7b
                java.lang.Object[] r6 = new java.lang.Object[r0]
                r7 = r7[r0]
                java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
                r6[r2] = r7
                java.lang.String r7 = "VOOSMP_PID_OUTPUT_CONTROL_UPDATE_CONNECT_STATUS, HDMIState param is %d"
                com.visualon.OSMPUtils.voLog.i(r1, r7, r6)
            L7b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPOutputControl.voOSOutputControlServer.AnonymousClass1.onHDMIStateChangeEvent(int, java.lang.Object):void");
        }
    };
    private voOSDisplayManager.voOSExternalDisplayListener mMiracastListener = new voOSDisplayManager.voOSExternalDisplayListener() { // from class: com.visualon.OSMPOutputControl.voOSOutputControlServer.2
        @Override // com.visualon.OSMPOutputControl.voOSDisplayManager.voOSExternalDisplayListener
        public void onMiracastOpened() {
            voOSType.VOOSMP_OUTPUT_CONTROL_TYPE voosmp_output_control_type = voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_ANTI_MIRRORING;
            int[] iArr = {voosmp_output_control_type.getValue(), 1};
            if (voOSOutputControlServer.this.mListener != null) {
                voOSOutputControlServer.this.mListener.onSetParam(99L, iArr);
            }
            if (voLog.enablePrintLog()) {
                voLog.i(voOSOutputControlServer.TAG, "VOOSMP_PID_OUTPUT_CONTROL_UPDATE_CONNECT_STATUS, type is %s, value is %d", voosmp_output_control_type.name(), Integer.valueOf(iArr[1]));
            }
        }

        @Override // com.visualon.OSMPOutputControl.voOSDisplayManager.voOSExternalDisplayListener
        public void onMiracastClosed() {
            voOSType.VOOSMP_OUTPUT_CONTROL_TYPE voosmp_output_control_type = voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_ANTI_MIRRORING;
            int[] iArr = {voosmp_output_control_type.getValue(), 0};
            if (voOSOutputControlServer.this.mListener != null) {
                voOSOutputControlServer.this.mListener.onSetParam(99L, iArr);
            }
            if (voLog.enablePrintLog()) {
                voLog.i(voOSOutputControlServer.TAG, "VOOSMP_PID_OUTPUT_CONTROL_UPDATE_CONNECT_STATUS, type is %s, value is %d", voosmp_output_control_type.name(), Integer.valueOf(iArr[1]));
            }
        }

        @Override // com.visualon.OSMPOutputControl.voOSDisplayManager.voOSExternalDisplayListener
        public void onExternalDisplayConnected(voOSType.VOOSMP_OUTPUT_CONTROL_TYPE voosmp_output_control_type) {
            if (voosmp_output_control_type == voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_HDMI) {
                int[] iArr = {voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_HDCP.getValue(), 1};
                if (voOSOutputControlServer.this.mListener != null) {
                    voOSOutputControlServer.this.mListener.onSetParam(99L, iArr);
                }
                if (voLog.enablePrintLog()) {
                    voLog.i(voOSOutputControlServer.TAG, "VOOSMP_PID_OUTPUT_CONTROL_UPDATE_CONNECT_STATUS, HDMIState param is %d", Integer.valueOf(iArr[1]));
                }
            }
        }

        @Override // com.visualon.OSMPOutputControl.voOSDisplayManager.voOSExternalDisplayListener
        public void onExternalDisplayDisconnected(voOSType.VOOSMP_OUTPUT_CONTROL_TYPE voosmp_output_control_type) {
            if (voosmp_output_control_type == voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_HDMI) {
                int[] iArr = {voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_HDCP.getValue(), 0};
                if (voOSOutputControlServer.this.mListener != null) {
                    voOSOutputControlServer.this.mListener.onHdmiDisconnect();
                    voOSOutputControlServer.this.mListener.onSetParam(99L, iArr);
                }
                if (voLog.enablePrintLog()) {
                    voLog.i(voOSOutputControlServer.TAG, "VOOSMP_PID_OUTPUT_CONTROL_UPDATE_CONNECT_STATUS, HDMIState param is %d", Integer.valueOf(iArr[1]));
                }
            }
        }
    };

    public interface onOutputControlListener {
        void onHdmiDisconnect();

        int onSetParam(long j, Object obj);
    }

    public void setOutputControlListener(onOutputControlListener onoutputcontrollistener) {
        this.mListener = onoutputcontrollistener;
    }

    public voOSOutputControlServer(Context context, Handler handler) {
        this.mContext = context;
        this.mEventHandler = handler;
    }

    public void startDisplayListener() {
        if (this.mDisplay == null) {
            voOSDisplayManager voosdisplaymanager = new voOSDisplayManager(this.mContext);
            this.mDisplay = voosdisplaymanager;
            voosdisplaymanager.setHandler(this.mEventHandler);
            this.mDisplay.setOnExternalDisplayListener(this.mMiracastListener);
            this.mDisplay.start();
        }
    }

    public void closeMiracast() {
        voOSDisplayManager voosdisplaymanager = this.mDisplay;
        if (voosdisplaymanager != null) {
            voosdisplaymanager.closeMiracast();
        }
    }

    public void stopDisplayListener() {
        voOSHDMIStateCheck vooshdmistatecheck = m_HDMIStateCheck;
        if (vooshdmistatecheck != null) {
            vooshdmistatecheck.Release();
            m_HDMIStateCheck = null;
        }
        voOSDisplayManager voosdisplaymanager = this.mDisplay;
        if (voosdisplaymanager != null) {
            voosdisplaymanager.stop();
            this.mDisplay = null;
        }
    }
}
