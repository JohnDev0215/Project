package com.visualon.OSMPUtils;

import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.util.Xml;
import androidx.core.internal.view.SupportMenu;
import com.amazon.music.destination.parser.ParserUtil;
import com.visualon.OSMPUtils.voOSType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes7.dex */
public class voUnifyDecoderManager {
    private static final String TAG = "@@@voUnifyDecoderManager";
    private Context mContext;
    private voOSDeviceData mDeviceDatas;
    private List<voOSDeviceData> mWhiteListDeviceDatas;
    private int m_RenderType;
    private boolean m_bEnableSEIPostProcessVideo;
    private boolean mbAdaptivePlayback;
    private boolean m_bVerimatrixPostProcessVideo = false;
    private voVideoViewController mVideoViewCtrl = null;
    private onUnifyDecoderListener mListener = null;
    private boolean mCardBoard = false;
    private boolean mSphericalVideo = false;
    private boolean mCubeVideo = false;
    private boolean m_bDisableForceOpenGL = false;
    final int DEFAULT_VIDEO_CODEC_TYPE = SupportMenu.USER_MASK;
    final int DEFAULT_AUDIO_CODEC_TYPE = SupportMenu.USER_MASK;

    public interface onUnifyDecoderListener {
        void onUnifyDecoderEvent(int i, int i2, int i3, Object obj);

        int onUnifyDecoderSetParam(long j, Object obj);
    }

    public voUnifyDecoderManager() {
        reset();
    }

    private void reset() {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "reset vars..", new Object[0]);
        }
        this.mbAdaptivePlayback = false;
        this.m_bEnableSEIPostProcessVideo = false;
        this.mContext = null;
        this.m_RenderType = 1;
        this.mVideoViewCtrl = null;
        this.mWhiteListDeviceDatas = null;
    }

    public void enableCardBoardVideo(boolean z) {
        this.mCardBoard = z;
    }

    public void enableSphericalVideo(boolean z) {
        this.mSphericalVideo = z;
    }

    public void enableCubemapVideo(boolean z) {
        this.mCubeVideo = z;
    }

    public enum VARSKEY {
        VARSKEY_ADAPTIVE_PLAYBACK(0),
        VARSKEY_ENABLE_SEI_POST_PROCESS_VIDEO(1),
        VARSKEY_CONTEXT(2),
        VARSKEY_RENDER_TYPE(3),
        VARSKEY_VIEW_CONTROL(4),
        VARSKEY_VERIMATRIX(5),
        VARSKEY_DISABLE_FORCE_OPENGL(6),
        VARSKEY_MAX(Integer.MAX_VALUE);

        private int value;

        VARSKEY(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static VARSKEY valueOf(int i) {
            if (i < values().length) {
                return values()[i];
            }
            return VARSKEY_MAX;
        }
    }

    public void setListener(onUnifyDecoderListener onunifydecoderlistener) {
        this.mListener = onunifydecoderlistener;
        if (onunifydecoderlistener == null) {
            reset();
        }
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "set listener: " + this.mListener, new Object[0]);
        }
    }

    /* JADX INFO: renamed from: com.visualon.OSMPUtils.voUnifyDecoderManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$visualon$OSMPUtils$voUnifyDecoderManager$VARSKEY;

        static {
            int[] iArr = new int[VARSKEY.values().length];
            $SwitchMap$com$visualon$OSMPUtils$voUnifyDecoderManager$VARSKEY = iArr;
            try {
                iArr[VARSKEY.VARSKEY_ADAPTIVE_PLAYBACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$visualon$OSMPUtils$voUnifyDecoderManager$VARSKEY[VARSKEY.VARSKEY_ENABLE_SEI_POST_PROCESS_VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$visualon$OSMPUtils$voUnifyDecoderManager$VARSKEY[VARSKEY.VARSKEY_CONTEXT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$visualon$OSMPUtils$voUnifyDecoderManager$VARSKEY[VARSKEY.VARSKEY_RENDER_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$visualon$OSMPUtils$voUnifyDecoderManager$VARSKEY[VARSKEY.VARSKEY_VIEW_CONTROL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$visualon$OSMPUtils$voUnifyDecoderManager$VARSKEY[VARSKEY.VARSKEY_VERIMATRIX.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$visualon$OSMPUtils$voUnifyDecoderManager$VARSKEY[VARSKEY.VARSKEY_DISABLE_FORCE_OPENGL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public int setParam(VARSKEY varskey, Object obj) {
        switch (AnonymousClass1.$SwitchMap$com$visualon$OSMPUtils$voUnifyDecoderManager$VARSKEY[varskey.ordinal()]) {
            case 1:
                this.mbAdaptivePlayback = ((Boolean) obj).booleanValue();
                break;
            case 2:
                this.m_bEnableSEIPostProcessVideo = ((Boolean) obj).booleanValue();
                break;
            case 3:
                this.mContext = (Context) obj;
                break;
            case 4:
                this.m_RenderType = ((Integer) obj).intValue();
                break;
            case 5:
                this.mVideoViewCtrl = (voVideoViewController) obj;
                break;
            case 6:
                this.m_bVerimatrixPostProcessVideo = ((Boolean) obj).booleanValue();
                break;
            case 7:
                this.m_bDisableForceOpenGL = ((Boolean) obj).booleanValue();
                break;
        }
        return 0;
    }

    public int setSmoothBAWhiteListFile(String str) {
        File file = new File(str);
        if (!file.exists()) {
            if (!voLog.enablePrintLog()) {
                return voOSType.VOOSMP_ERR_ParamID;
            }
            voLog.d(TAG, "setSmoothBAWhiteListFile fail," + str + " doesn't exist.", new Object[0]);
            return voOSType.VOOSMP_ERR_ParamID;
        }
        try {
            readWhiteListDeviceData(new FileInputStream(file));
            return 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return voOSType.VOOSMP_ERR_Unknown;
        }
    }

    private int readWhiteListDeviceData(InputStream inputStream) {
        this.mWhiteListDeviceDatas = readXML(inputStream);
        return 0;
    }

    private List<voOSDeviceData> readXML(InputStream inputStream) {
        XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
        try {
            xmlPullParserNewPullParser.setInput(inputStream, ParserUtil.UTF_8);
            ArrayList arrayList = null;
            voOSDeviceData voosdevicedata = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 0) {
                    arrayList = new ArrayList();
                } else if (eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (name.equalsIgnoreCase("item")) {
                        voosdevicedata = new voOSDeviceData();
                    } else if (voosdevicedata != null) {
                        if (name.equalsIgnoreCase("model")) {
                            voosdevicedata.setModel(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("release")) {
                            voosdevicedata.setVersion(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("platform")) {
                            voosdevicedata.setPlatform(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("codecsolution")) {
                            voosdevicedata.setRenderType(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("textureview")) {
                            voosdevicedata.setTextureView(xmlPullParserNewPullParser.nextText());
                        }
                    }
                } else if (eventType == 3 && xmlPullParserNewPullParser.getName().equalsIgnoreCase("item") && voosdevicedata != null) {
                    arrayList.add(voosdevicedata);
                    if (voLog.enablePrintLog()) {
                        voLog.i(TAG, "Parse Device Data, model %s, platform is %s, release is %s, rendertype is %s, textureview is %s.", voosdevicedata.getModel(), voosdevicedata.getPlatform(), voosdevicedata.getVersion(), voosdevicedata.getRenderType(), voosdevicedata.getTextureView());
                    }
                    voosdevicedata = null;
                }
            }
            inputStream.close();
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int setCodeType(int i, int i2) {
        if (i == 2293760) {
            if (voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_AUDIO_IOMX.getValue() == i2 && !Build.VERSION.RELEASE.startsWith("6.0")) {
                i2 = voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_AUDIO_MEDIACODEC.getValue();
            }
            setUnifyDecoder(voOSType.VOOSMP_PID_SELECT_AUDIO_DECODER, SupportMenu.USER_MASK, i2, 0);
        } else if (i == 2359296) {
            int value = voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_NONE.getValue() == i2 ? voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_SW.getValue() : i2;
            voOSType.VOOSMP_AV_DECODER_TYPE voosmp_av_decoder_type = voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_HARDWARE_AUTO_SELECTED;
            if (voosmp_av_decoder_type.getValue() == value) {
                value = voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_MEDIACODEC.getValue();
            }
            if (voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_SW.getValue() != value) {
                getCurrentDeviceDatas();
                matchCurrentDeviceToWhiteList();
                if (!isDeviceHardwareSupport()) {
                    this.mDeviceDatas = null;
                    this.mListener.onUnifyDecoderEvent(-2147483632, -1, -1, null);
                    return 0;
                }
                voOSDeviceData voosdevicedata = this.mDeviceDatas;
                if (voosdevicedata != null && voosdevicedata.getRenderType() != null && voosmp_av_decoder_type.getValue() == i2) {
                    if (this.mDeviceDatas.getRenderType().equalsIgnoreCase("iomx")) {
                        voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_IOMX.getValue();
                    } else if (this.mDeviceDatas.getRenderType().equalsIgnoreCase("mediacodec")) {
                        voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_MEDIACODEC.getValue();
                    }
                }
                value = voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_MEDIACODEC.getValue();
            }
            if (voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_IOMX.getValue() == value && this.mContext.getSharedPreferences("IOMX", 0).getString("checkIOMX", "none").equals("false")) {
                return voOSType.VOOSMP_ERR_Implement;
            }
            setDecoderAndRender(value);
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int setDecoderAndRender(int r9) {
        /*
            r8 = this;
            int r0 = r8.m_RenderType
            com.visualon.OSMPUtils.voOSType$VOOSMP_AV_DECODER_TYPE r1 = com.visualon.OSMPUtils.voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_IOMX
            int r1 = r1.getValue()
            r2 = 26
            r3 = 6
            r4 = 3
            r5 = 5
            if (r1 != r9) goto L11
            r0 = r5
            goto L3b
        L11:
            boolean r1 = r8.mCardBoard
            if (r1 != 0) goto L3a
            boolean r1 = r8.mSphericalVideo
            if (r1 != 0) goto L3a
            boolean r1 = r8.mCubeVideo
            if (r1 == 0) goto L1e
            goto L3a
        L1e:
            if (r0 == r4) goto L32
            com.visualon.OSMPUtils.voOSType$VOOSMP_AV_DECODER_TYPE r1 = com.visualon.OSMPUtils.voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_MEDIACODEC
            int r1 = r1.getValue()
            if (r1 != r9) goto L32
            boolean r1 = r8.m_bVerimatrixPostProcessVideo
            if (r1 != 0) goto L32
            boolean r1 = r8.m_bEnableSEIPostProcessVideo
            if (r1 != 0) goto L32
            r0 = r3
            goto L3b
        L32:
            int r1 = android.os.Build.VERSION.SDK_INT
            if (r1 < r2) goto L3b
            boolean r1 = r8.m_bDisableForceOpenGL
            if (r1 != 0) goto L3b
        L3a:
            r0 = r4
        L3b:
            r1 = 1
            r4 = 0
            if (r3 == r0) goto L4c
            if (r5 != r0) goto L42
            goto L4c
        L42:
            com.visualon.OSMPUtils.voOSType$VOOSMP_AV_DECODER_TYPE r3 = com.visualon.OSMPUtils.voOSType.VOOSMP_AV_DECODER_TYPE.VOOSMP_DEC_VIDEO_SW
            int r3 = r3.getValue()
            if (r3 == r9) goto L5c
            r3 = r1
            goto L5d
        L4c:
            boolean r3 = r8.mbAdaptivePlayback
            if (r3 == 0) goto L53
            r8.checkDeviceAdaptivePlaybackSupport()
        L53:
            boolean r3 = r8.mbAdaptivePlayback
            if (r3 == 0) goto L5c
            com.visualon.OSMPUtils.voVideoViewController r3 = r8.mVideoViewCtrl
            r3.addSecondTextureView()
        L5c:
            r3 = r4
        L5d:
            if (r1 != r0) goto L84
            java.lang.String r1 = android.os.Build.MODEL
            java.lang.String r1 = r1.toLowerCase()
            java.lang.String r6 = "vtab1008"
            int r1 = r1.compareToIgnoreCase(r6)
            r6 = 3
            if (r1 != 0) goto L7b
            com.visualon.OSMPUtils.voUnifyDecoderManager$onUnifyDecoderListener r1 = r8.mListener
            r2 = 23
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1.onUnifyDecoderSetParam(r6, r2)
            goto L84
        L7b:
            com.visualon.OSMPUtils.voUnifyDecoderManager$onUnifyDecoderListener r1 = r8.mListener
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1.onUnifyDecoderSetParam(r6, r2)
        L84:
            boolean r1 = r8.mSphericalVideo
            if (r1 != 0) goto L90
            boolean r1 = r8.mCardBoard
            if (r1 != 0) goto L90
            boolean r1 = r8.mCubeVideo
            if (r1 == 0) goto L9d
        L90:
            if (r5 != r0) goto L9d
            com.visualon.OSMPUtils.voUnifyDecoderManager$onUnifyDecoderListener r9 = r8.mListener
            r0 = -2147483632(0xffffffff80000010, float:-2.2E-44)
            r1 = 0
            r2 = -1
            r9.onUnifyDecoderEvent(r0, r2, r2, r1)
            return r4
        L9d:
            r1 = 2359296(0x240000, float:3.306078E-39)
            r2 = 65535(0xffff, float:9.1834E-41)
            r8.setUnifyDecoder(r1, r2, r9, r3)
            com.visualon.OSMPUtils.voVideoViewController r9 = r8.mVideoViewCtrl
            r9.setRenderType(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPUtils.voUnifyDecoderManager.setDecoderAndRender(int):int");
    }

    private void checkDeviceAdaptivePlaybackSupport() {
        voOSDeviceData voosdevicedata = this.mDeviceDatas;
        if (voosdevicedata == null || voosdevicedata.getTextureView() == null || !this.mDeviceDatas.getTextureView().equalsIgnoreCase("no")) {
            return;
        }
        this.mbAdaptivePlayback = false;
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "Current device doesn't support textureview.", new Object[0]);
        }
    }

    public boolean isDeviceHardwareSupport() {
        voOSDeviceData voosdevicedata = this.mDeviceDatas;
        return voosdevicedata == null || voosdevicedata.getRenderType() == null || !this.mDeviceDatas.getRenderType().equalsIgnoreCase("none");
    }

    private static class SystemPropertyUtils {
        private static volatile Method getter;

        private SystemPropertyUtils() {
        }

        public static String get(String str, String str2) {
            try {
                if (getter == null) {
                    synchronized (SystemPropertyUtils.class) {
                        if (getter == null) {
                            getter = SystemProperties.class.getDeclaredMethod("get", String.class, String.class);
                        }
                    }
                }
                return (String) getter.invoke(null, str, str2);
            } catch (Throwable th) {
                th.printStackTrace();
                return str2;
            }
        }
    }

    private void getCurrentDeviceDatas() {
        if (this.mDeviceDatas != null) {
            return;
        }
        String str = Build.MODEL;
        String str2 = Build.VERSION.RELEASE;
        String str3 = SystemPropertyUtils.get("ro.board.platform", "");
        voLog.i(TAG, "Get platform via SystemProperties : " + str3, new Object[0]);
        voOSDeviceData voosdevicedata = new voOSDeviceData();
        this.mDeviceDatas = voosdevicedata;
        voosdevicedata.setModel(str);
        this.mDeviceDatas.setVersion(str2);
        this.mDeviceDatas.setPlatform(str3);
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "Current device info, model is %s, release is %s, platform is %s.", str, str2, str3);
        }
    }

    private void matchCurrentDeviceToWhiteList() {
        if (this.mWhiteListDeviceDatas == null) {
            try {
                readWhiteListDeviceData(this.mContext.getResources().getAssets().open("device.xml"));
            } catch (IOException unused) {
                voLog.w(TAG, "device.xml not found.", new Object[0]);
            }
        }
        voOSDeviceData voosdevicedata = this.mDeviceDatas;
        if (voosdevicedata == null || this.mWhiteListDeviceDatas == null || voosdevicedata.getRenderType() != null || this.mDeviceDatas.getTextureView() != null || this.mDeviceDatas.getModel() == null || this.mDeviceDatas.getVersion() == null || this.mDeviceDatas.getPlatform() == null) {
            return;
        }
        for (int i = 0; i < this.mWhiteListDeviceDatas.size(); i++) {
            voOSDeviceData voosdevicedata2 = this.mWhiteListDeviceDatas.get(i);
            if (this.mDeviceDatas.getModel().equalsIgnoreCase(voosdevicedata2.getModel()) && this.mDeviceDatas.getPlatform().equalsIgnoreCase(voosdevicedata2.getPlatform()) && this.mDeviceDatas.getVersion().startsWith(voosdevicedata2.getVersion())) {
                this.mDeviceDatas = voosdevicedata2;
                return;
            }
        }
    }

    private int setUnifyDecoder(int i, int i2, int i3, int i4) {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "setUnifyDecoder pid:%08x, codectype:%08x, dectype:%08x outputMode：%d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        }
        if (2359296 == i) {
            i3 = (i3 << 16) | i4;
        }
        return this.mListener.onUnifyDecoderSetParam(i | i2, Integer.valueOf(i3));
    }
}
