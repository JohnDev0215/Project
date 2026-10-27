package com.byd.engineermode.utils;

import android.content.Context;
import android.text.TextUtils;
import butterknife.R;
import com.byd.engineermode.DiagnoseApplication;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class StringArrayToList {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static StringArrayToList f2486a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ArrayList<String> f2487b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static ArrayList<String> f2488c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static ArrayList<String> f2489d = new ArrayList<>();
    private static ArrayList<String> e = new ArrayList<>();
    Context f = DiagnoseApplication.e();

    public enum Audio {
        Audio_MIC1_MID(0),
        Audio_MIC2_MID(1),
        Audio_MIC3_MID(2),
        Audio_MIC4_MID(3),
        Audio_MIC5_MID(4),
        Audio_MIC6_MID(5),
        Audio_A2B_MID(6),
        Audio_Amplifier_MID(7);

        private int id;

        Audio(int i) {
            this.id = i;
        }

        public static Audio find(int i) {
            for (Audio audio : values()) {
                if (audio.ordinal() == i) {
                    return audio;
                }
            }
            return null;
        }

        public int id() {
            return this.id;
        }
    }

    public enum Communicate {
        Communicate_WHUD_MID(0),
        Communicate_USB_Assembly_Power_Supply_MID(1),
        Communicate_USB_Karaoke_MID(2),
        Communicate_ADAS_MID(3),
        Communicate_HID_Left_MID(4),
        Communicate_HID_Right_MID(5),
        Communicate_HUD_MID(6),
        Communicate_4G_5G_TBOX_MID(7),
        Communicate_UAV_MID(8);

        private int id;

        Communicate(int i) {
            this.id = i;
        }

        public static Communicate find(int i) {
            for (Communicate communicate : values()) {
                if (communicate.ordinal() == i) {
                    return communicate;
                }
            }
            return null;
        }

        public byte id() {
            return (byte) this.id;
        }
    }

    public enum Radio {
        Radio_WIFI_MID(0),
        Radio_BT_MID(1),
        Radio_FM_AM_DAB_MID(2),
        Radio_GPS_STATUS_MID(3);

        private int id;

        Radio(int i) {
            this.id = i;
        }

        public static Radio find(int i) {
            for (Radio radio : values()) {
                if (radio.ordinal() == i) {
                    return radio;
                }
            }
            return null;
        }

        public int id() {
            return this.id;
        }
    }

    public enum Video {
        Video_DVR_MID(0),
        Video_DMS_MID(1),
        Video_OMS_MID(2),
        Video_IMS_MID(3),
        Video_FACE_ID_MID(4),
        Video_AVM_COAX_L(5),
        Video_AVM_COAX_B(6),
        Video_AVM_COAX_F(7),
        Video_AVM_COAX_R(8),
        Video_Instrument_MID(9),
        Video_IVI_MID(10),
        Video_Armres_MID(11),
        Video_RSE_MID(12),
        Video_StreamMedia_MID(13),
        Video_HUD_MID(14),
        Video_DMSPower_MID(15),
        Video_OMSPower_MID(16),
        Video_IMSPower_MID(17);

        private int id;

        Video(int i) {
            this.id = i;
        }

        public static Video find(int i) {
            for (Video video : values()) {
                if (video.ordinal() == i) {
                    return video;
                }
            }
            return null;
        }

        public int id() {
            return this.id;
        }
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2494a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f2495b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        static final /* synthetic */ int[] f2496c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        static final /* synthetic */ int[] f2497d;

        static {
            int[] iArr = new int[Communicate.values().length];
            f2497d = iArr;
            try {
                iArr[Communicate.Communicate_WHUD_MID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2497d[Communicate.Communicate_USB_Assembly_Power_Supply_MID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2497d[Communicate.Communicate_USB_Karaoke_MID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2497d[Communicate.Communicate_ADAS_MID.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2497d[Communicate.Communicate_HID_Left_MID.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2497d[Communicate.Communicate_HID_Right_MID.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2497d[Communicate.Communicate_HUD_MID.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2497d[Communicate.Communicate_4G_5G_TBOX_MID.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2497d[Communicate.Communicate_UAV_MID.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr2 = new int[Radio.values().length];
            f2496c = iArr2;
            try {
                iArr2[Radio.Radio_WIFI_MID.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f2496c[Radio.Radio_BT_MID.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f2496c[Radio.Radio_FM_AM_DAB_MID.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f2496c[Radio.Radio_GPS_STATUS_MID.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            int[] iArr3 = new int[Audio.values().length];
            f2495b = iArr3;
            try {
                iArr3[Audio.Audio_MIC1_MID.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f2495b[Audio.Audio_MIC2_MID.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f2495b[Audio.Audio_MIC3_MID.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f2495b[Audio.Audio_MIC4_MID.ordinal()] = 4;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f2495b[Audio.Audio_MIC5_MID.ordinal()] = 5;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f2495b[Audio.Audio_MIC6_MID.ordinal()] = 6;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f2495b[Audio.Audio_A2B_MID.ordinal()] = 7;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f2495b[Audio.Audio_Amplifier_MID.ordinal()] = 8;
            } catch (NoSuchFieldError unused21) {
            }
            int[] iArr4 = new int[Video.values().length];
            f2494a = iArr4;
            try {
                iArr4[Video.Video_DVR_MID.ordinal()] = 1;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f2494a[Video.Video_DMS_MID.ordinal()] = 2;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f2494a[Video.Video_OMS_MID.ordinal()] = 3;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f2494a[Video.Video_IMS_MID.ordinal()] = 4;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f2494a[Video.Video_FACE_ID_MID.ordinal()] = 5;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f2494a[Video.Video_AVM_COAX_L.ordinal()] = 6;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                f2494a[Video.Video_AVM_COAX_B.ordinal()] = 7;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                f2494a[Video.Video_AVM_COAX_F.ordinal()] = 8;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                f2494a[Video.Video_AVM_COAX_R.ordinal()] = 9;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                f2494a[Video.Video_Instrument_MID.ordinal()] = 10;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                f2494a[Video.Video_IVI_MID.ordinal()] = 11;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                f2494a[Video.Video_Armres_MID.ordinal()] = 12;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                f2494a[Video.Video_RSE_MID.ordinal()] = 13;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                f2494a[Video.Video_StreamMedia_MID.ordinal()] = 14;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                f2494a[Video.Video_HUD_MID.ordinal()] = 15;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                f2494a[Video.Video_DMSPower_MID.ordinal()] = 16;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                f2494a[Video.Video_OMSPower_MID.ordinal()] = 17;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                f2494a[Video.Video_IMSPower_MID.ordinal()] = 18;
            } catch (NoSuchFieldError unused39) {
            }
        }
    }

    private StringArrayToList() {
    }

    public static StringArrayToList h() {
        if (f2486a == null) {
            f2486a = new StringArrayToList();
        }
        return f2486a;
    }

    private boolean i(String str) {
        int length = str.length();
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (Character.isDigit(str.charAt(length)));
        return false;
    }

    public String a(long j) {
        String[] stringArray = this.f.getResources().getStringArray(R.array.functions);
        int iIntValue = Long.valueOf(j).intValue() - 1;
        if (iIntValue < 0 || iIntValue >= stringArray.length) {
            return null;
        }
        return stringArray[iIntValue];
    }

    public String b(String str, int i) {
        if (TextUtils.isEmpty(str) || !i(str)) {
            return null;
        }
        int i2 = Integer.parseInt(str);
        ArrayList<String> arrayList = new ArrayList<>();
        if (i == 0) {
            arrayList = f(R.array.video);
        } else if (i == 1) {
            arrayList = f(R.array.audio);
        } else if (i == 2) {
            arrayList = f(R.array.radio);
        } else if (i == 3) {
            arrayList = f(R.array.communicate);
        }
        if (arrayList == null || arrayList.size() <= 0 || i2 >= arrayList.size() || i2 < 0) {
            return null;
        }
        return arrayList.get(i2);
    }

    public String c(String str) {
        if (TextUtils.isEmpty(str) || !i(str)) {
            return str;
        }
        int i = Integer.parseInt(str);
        String[] stringArray = new String[0];
        if (i == 0) {
            stringArray = this.f.getResources().getStringArray(R.array.video);
        } else if (i == 1) {
            stringArray = this.f.getResources().getStringArray(R.array.audio);
        } else if (i == 2) {
            stringArray = this.f.getResources().getStringArray(R.array.radio);
        } else if (i == 3) {
            stringArray = this.f.getResources().getStringArray(R.array.communicate);
        }
        return (stringArray == null || stringArray.length <= 0) ? str : stringArray[0];
    }

    public String d(String str, int i, int i2) {
        if (TextUtils.isEmpty(str) || !i(str)) {
            return str;
        }
        int i3 = Integer.parseInt(str);
        String[] stringArray = new String[0];
        if (i2 == 0) {
            switch (a.f2494a[Video.find(i).ordinal()]) {
                case 1:
                    stringArray = this.f.getResources().getStringArray(R.array.dvr_supplier);
                    break;
                case 2:
                    stringArray = this.f.getResources().getStringArray(R.array.dms_supplier);
                    break;
                case 3:
                    stringArray = this.f.getResources().getStringArray(R.array.oms_supplier);
                    break;
                case 4:
                    stringArray = this.f.getResources().getStringArray(R.array.ims_supplier);
                    break;
                case 5:
                    stringArray = this.f.getResources().getStringArray(R.array.face_is_supplier);
                    break;
                case 6:
                    stringArray = this.f.getResources().getStringArray(R.array.avm_supplier);
                    break;
                case 7:
                    stringArray = this.f.getResources().getStringArray(R.array.avm_supplier);
                    break;
                case 8:
                    stringArray = this.f.getResources().getStringArray(R.array.avm_supplier);
                    break;
                case 9:
                    stringArray = this.f.getResources().getStringArray(R.array.avm_supplier);
                    break;
                case 10:
                    stringArray = this.f.getResources().getStringArray(R.array.cluster_supplier);
                    break;
                case 11:
                    stringArray = this.f.getResources().getStringArray(R.array.ivi_screen_supplier);
                    break;
                case 13:
                    stringArray = this.f.getResources().getStringArray(R.array.rse_screen_supplier);
                    break;
                case 14:
                    stringArray = this.f.getResources().getStringArray(R.array.stream_media_supplier);
                    break;
                case 15:
                    stringArray = this.f.getResources().getStringArray(R.array.hud_supplier);
                    break;
            }
        } else if (i2 == 1) {
            int i4 = a.f2495b[Audio.find(i).ordinal()];
        } else if (i2 == 2) {
            int i5 = a.f2496c[Radio.find(i).ordinal()];
        } else if (i2 == 3) {
            int i6 = a.f2497d[Communicate.find(i).ordinal()];
        }
        return (stringArray == null || stringArray.length <= 0 || i3 >= stringArray.length || i3 < 0) ? str : stringArray[i3];
    }

    public String e(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith("TYDD")) {
                return this.f.getResources().getStringArray(R.array.tbox_supplier)[0];
            }
            if (str.startsWith("DESAI")) {
                return this.f.getResources().getStringArray(R.array.tbox_supplier)[1];
            }
        }
        return null;
    }

    public ArrayList<String> f(int i) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] stringArray = this.f.getResources().getStringArray(i);
        if (stringArray != null && stringArray.length > 2) {
            for (int i2 = 1; i2 < stringArray.length; i2++) {
                ArrayList<String> arrayList2 = f2487b;
                if (arrayList2 != null && !arrayList2.contains(stringArray[i2])) {
                    arrayList.add(stringArray[i2]);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x008e, code lost:
    
        if (com.byd.engineermode.utils.d.a().b() != false) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009e, code lost:
    
        if (com.byd.engineermode.utils.d.a().b() != false) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ae, code lost:
    
        if (com.byd.engineermode.utils.d.a().b() != false) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00be, code lost:
    
        if (com.byd.engineermode.utils.d.a().b() != false) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ed, code lost:
    
        if (com.byd.engineermode.utils.d.a().b() != false) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0102, code lost:
    
        if (com.byd.engineermode.utils.d.a().b() != false) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x011b, code lost:
    
        if (com.byd.engineermode.utils.d.a().b() != false) goto L167;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:139:0x0264. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0023. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:65:0x012d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02c0 A[PHI: r2
  0x02c0: PHI (r2v7 android.hardware.bydauto.tools.DiagnoseDeviceStatus) = 
  (r2v2 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v3 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v4 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v5 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v6 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v8 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
 binds: [B:154:0x02bc, B:152:0x02b9, B:149:0x02aa, B:146:0x029b, B:144:0x0279, B:142:0x0275] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02f5 A[PHI: r2
  0x02f5: PHI (r2v26 android.hardware.bydauto.tools.DiagnoseDeviceStatus) = 
  (r2v0 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v0 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v0 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v0 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v0 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v3 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v4 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v5 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v8 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v0 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v15 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v17 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v19 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v21 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v0 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v31 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v33 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v35 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v43 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v44 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v45 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v46 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
  (r2v48 android.hardware.bydauto.tools.DiagnoseDeviceStatus)
 binds: [B:137:0x0256, B:139:0x0264, B:166:0x02f0, B:165:0x02ea, B:160:0x02ce, B:152:0x02b9, B:149:0x02aa, B:146:0x029b, B:142:0x0275, B:65:0x012d, B:108:0x01e9, B:98:0x01bd, B:88:0x0191, B:78:0x0164, B:5:0x0023, B:61:0x011b, B:55:0x0102, B:51:0x00ed, B:41:0x00be, B:38:0x00ae, B:35:0x009e, B:32:0x008e, B:15:0x004e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.byd.engineermode.utils.f g(int r10, int r11) {
        /*
            Method dump skipped, instruction units count: 904
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.engineermode.utils.StringArrayToList.g(int, int):com.byd.engineermode.utils.f");
    }
}
