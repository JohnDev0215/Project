package com.neusoft.optimus.utility.offlineconfig;

import android.content.Context;
import android.util.Log;
import com.neusoft.alfus.RpcManager;
import com.neusoft.optimus.utility.log.LogTool;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class OfflineConfig {
    private static final int OpeCode = 1562;
    private static final byte SubOpeCode = 21;
    private static final byte VehicleSubOpeCode = 37;
    private static final String cTag = "OfflineConfig";
    private static byte[] f101bytes;
    private static byte[] f110bytes;
    private static OfflineConfig offlineConfig;
    private static byte[] vehicleConfigList;

    public static class CAR_MODE_CONFIG {
        public static final int ABS = 276;
        public static final int AC = 265;
        public static final int ACCM = 287;
        public static final int ACU = 259;
        public static final int AFS = 275;
        public static final int AVAS = 292;
        public static final int AVM = 299;
        public static final int BCM = 257;
        public static final int BMS = 282;
        public static final int BSD = 293;
        public static final int BSG = 281;
        public static final int DCDC = 283;
        public static final int DDM = 297;
        public static final int DMS = 290;
        public static final int DVR = 288;
        public static final int EGSM = 280;
        public static final int EMS = 255;
        public static final int EPB = 271;
        public static final int EPS = 262;
        public static final int ESC = 260;
        public static final int ESCL = 270;
        public static final int F110_OFFSET = 255;
        public static final int FCS = 272;
        public static final int FRS = 264;
        public static final int GW = 289;
        public static final int HUD = 298;
        public static final int HVSM = 291;
        public static final int IMMO = 277;
        public static final int IPK = 256;
        public static final int IPU = 286;
        public static final int MMI = 266;
        public static final int OBC = 285;
        public static final int PAS = 267;
        public static final int PCU = 295;
        public static final int PDM = 296;
        public static final int PEPS = 261;
        public static final int PTC = 279;
        public static final int PTG = 273;
        public static final int SAS = 263;
        public static final int SCU = 274;
        public static final int TBOX = 269;
        public static final int TCU = 258;
        public static final int TPMS = 268;
        public static final int TSU = 294;
        public static final int VCU = 284;
        public static final int WD4 = 278;
    }

    public static class E01Config {
        public static final int ABS = 18;
        public static final int AC = 13;
        public static final int ACAutoLevel = 240;
        public static final int ACCM = 37;
        public static final int ACDry = 242;
        public static final int ACEco = 238;
        public static final int ACKeyBeep = 236;
        public static final int ACPre = 117;
        public static final int ACU = 3;
        public static final int ACWindLevel = 246;
        public static final int ACWindMode = 245;
        public static final int ADAS_Aeb = 249;
        public static final int ADAS_EmergencyLka = 254;
        public static final int ADAS_Icc = 248;
        public static final int ADAS_Ihbc = 252;
        public static final int ADAS_Lka = 253;
        public static final int ADAS_RRadaAssist = 255;
        public static final int ADAS_RoadSign = 251;
        public static final int ADAS_SpeedRemind = 250;
        public static final int ADAS_Type = 256;
        public static final int ADB = 19;
        public static final int AGS = 45;
        public static final int ALCM = 54;
        public static final int AMPType = 104;
        public static final int AVAS = 36;
        public static final int AVM = 44;
        public static final int AirCon = 116;
        public static final int AirPurify = 244;
        public static final int Alc = 270;
        public static final int AntiPinch = 208;
        public static final int Apa = 127;
        public static final int Aqs = 118;
        public static final int AutoHold = 221;
        public static final int AutoUnlock = 213;
        public static final int BCM = 5;
        public static final int BLC_L = 57;
        public static final int BLC_R = 58;
        public static final int BMS = 28;
        public static final int BMSL = 51;
        public static final int BSG = 29;
        public static final int BackDoorHeight = 212;
        public static final int BackDoorUnlock = 267;
        public static final int BackLight = 201;
        public static final int BackWarning = 272;
        public static final int BackingSideAid = 271;
        public static final int BehindDefrost = 275;
        public static final int BreachAmbient = 231;
        public static final int BsgStartStop = 225;
        public static final int CHM = 53;
        public static final int CabinClean = 243;
        public static final int CloseUnlock = 269;
        public static final int Co2 = 239;
        public static final int ComfortType = 154;
        public static final int CupHolder = 110;
        public static final int CustomSwitch = 257;
        public static final int DCDC = 27;
        public static final int DDM = 42;
        public static final int DMM = 52;
        public static final int DMS = 33;
        public static final int DSCU = 20;
        public static final int DVR = 35;
        public static final int DaytimeLight = 260;
        public static final int DoorOpenAlert = 216;
        public static final int DoorOpenWarning = 273;
        public static final int DriveMode = 219;
        public static final int DvrForm = 128;
        public static final int ECS = 49;
        public static final int EGSM = 30;
        public static final int EMS = 7;
        public static final int EPB = 23;
        public static final int EPS = 0;
        public static final int ESC = 2;
        public static final int ESCL = 8;
        public static final int EcoComfortSport = 136;
        public static final int EcoType = 153;
        public static final int EscOff = 258;
        public static final int EtcAl01 = 140;
        public static final int EtcAl02 = 141;
        public static final int EtcAl03 = 142;
        public static final int EtcAl04 = 143;
        public static final int EtcAl05 = 144;
        public static final int EtcAl06 = 145;
        public static final int EtcAl07 = 146;
        public static final int EtcAl08 = 147;
        public static final int EtcAl09 = 148;
        public static final int EtcAl10 = 149;
        public static final int EtcAl11 = 150;
        public static final int EtcAl12 = 151;
        public static final int EtcLamp = 131;
        public static final int EtcLampType = 130;
        public static final int FCS = 22;
        public static final int FRS = 14;
        public static final int FRadar = 220;
        public static final int FollowMeHome = 203;
        public static final int Fragrance = 122;
        public static final int GW = 32;
        public static final int GateCtrl = 224;
        public static final int HUD = 43;
        public static final int HVSM = 34;
        public static final int Hdc = 222;
        public static final int Heat = 241;
        public static final int HudMode = 226;
        public static final int IB = 46;
        public static final int IKey = 115;
        public static final int IMMO = 17;
        public static final int IPK = 6;
        public static final int IPU = 24;
        public static final int Ion = 119;
        public static final int IpkNavi = 109;
        public static final int KeyForgetAlert = 218;
        public static final int Language = 103;
        public static final int LeaveAutoLock = 268;
        public static final int LockCloseRollshade = 210;
        public static final int LockCloseWindow = 207;
        public static final int MMI = 12;
        public static final int MirrorAutoFold = 259;
        public static final int MirrorTilt = 263;
        public static final int NozzleHeat = 132;
        public static final int OBC = 25;
        public static final int Offload = 138;
        public static final int PAS = 11;
        public static final int PCU = 40;
        public static final int PDM = 41;
        public static final int PEPS = 1;
        public static final int PTC = 31;
        public static final int PTG = 21;
        public static final int ParkingAC = 237;
        public static final int ParkingAmbient = 235;
        public static final int Pm25 = 121;
        public static final int PmType = 157;
        public static final int PowerNotOffAlert = 217;
        public static final int Project = 100;
        public static final int RCP2 = 55;
        public static final int RCP3 = 56;
        public static final int RML = 48;
        public static final int RSRS = 38;
        public static final int RadarType = 158;
        public static final int Rader = 125;
        public static final int RainCloseWindow = 206;
        public static final int RearCamera = 124;
        public static final int RearCtrl = 120;
        public static final int RearSeat = 106;
        public static final int RemoteKey = 264;
        public static final int RemoteKeyUnlock = 214;
        public static final int ReversingWiper = 262;
        public static final int RightBlind = 126;
        public static final int RollshadeVoice = 112;
        public static final int SAS = 15;
        public static final int SUM = 47;
        public static final int SaveKey = 247;
        public static final int ScreenKeyBackLight = 200;
        public static final int SeatComfort = 265;
        public static final int SeatHeat = 227;
        public static final int SeatHeatFun = 152;
        public static final int SeatMassage = 228;
        public static final int SeatMemory = 229;
        public static final int SeatType = 135;
        public static final int Snow = 137;
        public static final int Sound = 105;
        public static final int SpeedAlertAmbient = 233;
        public static final int SpeedAmbient = 232;
        public static final int SpeedAutoLock = 215;
        public static final int Sport = 139;
        public static final int SportType = 155;
        public static final int SteplessAmbient = 230;
        public static final int Sunroof = 156;
        public static final int SunroofRollShade = 223;
        public static final int SunroofVoice = 111;
        public static final int Sva = 123;
        public static final int TACM = 50;
        public static final int TBOX = 9;
        public static final int TCU = 4;
        public static final int TPMS = 10;
        public static final int TSU = 39;
        public static final int TrunkUnLock = 211;
        public static final int TrunkUnLockRange = 266;
        public static final int TurnLight = 202;
        public static final int UIType = 102;
        public static final int Usb = 107;
        public static final int UsbPort = 274;
        public static final int VCU = 26;
        public static final int VehicleType = 101;
        public static final int VoicePrint = 114;
        public static final int WD4 = 16;
        public static final int Wcm = 108;
        public static final int WelcomeAmbient = 234;
        public static final int WelcomeLight = 261;
        public static final int WelcomeLightEffect = 204;
        public static final int WheelHeat = 134;
        public static final int WheelPosition = 276;
        public static final int WheelType = 129;
        public static final int WindowBreath = 209;
        public static final int WindowClamp = 113;
        public static final int WindowHeat = 133;
        public static final int WiperSlow = 205;
    }

    public static class Type {
        public static final int AMBIENT_LIGHT_0 = 90;
        public static final int AMBIENT_LIGHT_1 = 91;
        public static final int AMBIENT_LIGHT_2 = 92;
        public static final int AMBIENT_LIGHT_3 = 93;
        public static final int AMBIENT_LIGHT_4 = 94;
        public static final int AMBIENT_LIGHT_5 = 95;
        public static final int APA = 21;
        public static final int AQS = 8;
        public static final int Air_Conditioning_Configuration = 19;
        public static final int DRL = 18;
        public static final int Face_recognition = 26;
        public static final int INSTRUMENT_NAVI = 5;
        public static final int ION = 13;
        public static final int OUTSIDE_REARVIEW = 7;
        public static final int PM2POINT5 = 12;
        public static final int POWER_AMPLIFIER = 4;
        public static final int PSC = 30;
        public static final int RADER = 6;
        public static final int REAR_CAMERA = 3;
        public static final int REAR_SEAT = 0;
        public static final int Rear_control_panel = 14;
        public static final int Right_Blind = 24;
        public static final int Rollshade_Motor = 15;
        public static final int SUNROOF = 10;
        public static final int SURROUND_CAMERA = 2;
        public static final int Seat_animation = 33;
        public static final int Seat_function_configuration = 23;
        public static final int Skylight_animation = 32;
        public static final int Smart_car_fragrance = 22;
        public static final int Temperature_Controlled_Cup_Holder = 9;
        public static final int Voiceprint_recognition = 27;
        public static final int WCM = 11;
        public static final int WIfI = 1;
        public static final int Welcome_Lamp = 16;
        public static final int Window_Clamp = 17;
        public static final int blind_spot_monitor = 20;
        public static final int cv_car_mode = 45;
        public static final int drv_video_format = 42;
        public static final int eco_comfort_sport = 37;
        public static final int key_controls_the_glass_lift = 25;
        public static final int leave_lock = 34;
        public static final int offload_mode = 39;
        public static final int rear_speaker = 44;
        public static final int rear_view_mirror_tilts = 31;
        public static final int remote_unlock = 36;
        public static final int reverse_wiper_combined = 28;
        public static final int seat_format = 41;
        public static final int snow_mode = 38;
        public static final int sport_mode = 40;
        public static final int sx_vehicle_mode = 46;
        public static final int ui_display_config = 43;
        public static final int usb_port_num = 35;
        public static final int wisdom_key = 29;
    }

    public static int getOfflineConfig(int i) {
        return -1;
    }

    public static int getVehicleOfflineConfig(int i) {
        return -1;
    }

    public static boolean isNeedOfflinConfig() {
        return true;
    }

    public OfflineConfig(Context context) {
    }

    public static OfflineConfig getInstance(Context context) {
        if (offlineConfig == null) {
            offlineConfig = new OfflineConfig(context);
        }
        return offlineConfig;
    }

    public static int getOfflineConfig(Context context, int i) {
        if (f101bytes == null) {
            byte[] bArr = new byte[25];
            bArr[0] = 21;
            f101bytes = RpcManager.getInstance().getMessage(1562, bArr);
            Log.i(cTag, "request 101 Code:" + Arrays.toString(f101bytes));
            if (f101bytes == null) {
                Log.i(cTag, "request 101 Code, bug i get null");
                return 0;
            }
        }
        return get101(f101bytes, i);
    }

    private static int get101(byte[] bArr, int i) {
        int i2;
        int i3;
        int i4;
        if (bArr != null) {
            byte b = bArr[1];
            byte b2 = bArr[2];
            byte b3 = bArr[3];
            byte b4 = bArr[4];
            byte b5 = bArr[5];
            byte b6 = bArr[6];
            byte b7 = bArr[7];
            byte b8 = bArr[8];
            byte b9 = bArr[9];
            byte b10 = bArr[10];
            byte b11 = bArr[11];
            switch (i) {
                case 0:
                    i2 = b >> 6;
                    return i2 & 3;
                case 1:
                    i2 = b >> 4;
                    return i2 & 3;
                case 2:
                    i2 = b >> 2;
                    return i2 & 3;
                case 3:
                    i2 = b >> 0;
                    return i2 & 3;
                case 4:
                    i2 = b2 >> 6;
                    return i2 & 3;
                case 5:
                    i2 = b2 >> 4;
                    return i2 & 3;
                case 6:
                    return (b2 >> 0) & 15;
                case 7:
                    i2 = b3 >> 6;
                    return i2 & 3;
                case 8:
                    i2 = b3 >> 4;
                    return i2 & 3;
                case 9:
                    i2 = b3 >> 2;
                    return i2 & 3;
                case 10:
                    i2 = b3 >> 0;
                    return i2 & 3;
                case 11:
                    i2 = b4 >> 2;
                    return i2 & 3;
                case 12:
                    i2 = b4 >> 0;
                    return i2 & 3;
                case 13:
                    i2 = b4 >> 6;
                    return i2 & 3;
                case 14:
                    i2 = b4 >> 4;
                    return i2 & 3;
                case 15:
                    i2 = b5 >> 6;
                    return i2 & 3;
                case 16:
                    return 1 & b6;
                case 17:
                    i2 = b6 >> 1;
                    return i2 & 3;
                case 18:
                    i2 = b6 >> 3;
                    return i2 & 3;
                case 19:
                    i3 = b6 >> 5;
                    return i3 & 7;
                case 20:
                    i4 = b7 >> 7;
                    return 1 & i4;
                case 21:
                    i4 = b7 >> 6;
                    return 1 & i4;
                case 22:
                    i4 = b7 >> 5;
                    return 1 & i4;
                case 23:
                    i2 = b7 >> 3;
                    return i2 & 3;
                case 24:
                    i4 = b7 >> 2;
                    return 1 & i4;
                case 25:
                    i4 = b7 >> 1;
                    return 1 & i4;
                case 26:
                    return 1 & b7;
                case 27:
                    i4 = b8 >> 7;
                    return 1 & i4;
                case 28:
                    i4 = b8 >> 6;
                    return 1 & i4;
                case 29:
                    i4 = b8 >> 5;
                    return 1 & i4;
                case 30:
                    i4 = b8 >> 4;
                    return 1 & i4;
                case 31:
                    i4 = b8 >> 3;
                    return 1 & i4;
                case 32:
                    i4 = b8 >> 2;
                    return 1 & i4;
                case 33:
                    i4 = b8 >> 1;
                    return 1 & i4;
                case 34:
                    return 1 & b8;
                case 35:
                    i4 = b9 >> 7;
                    return 1 & i4;
                case 36:
                    i4 = b9 >> 6;
                    return 1 & i4;
                case 37:
                    i4 = b9 >> 5;
                    return 1 & i4;
                case 38:
                    i4 = b9 >> 4;
                    return 1 & i4;
                case 39:
                    i4 = b9 >> 3;
                    return 1 & i4;
                case 40:
                    i4 = b9 >> 2;
                    return 1 & i4;
                case 41:
                    return 1 & b9;
                case 42:
                    i3 = b10 >> 5;
                    return i3 & 7;
                case 43:
                    i2 = b10 >> 3;
                    return i2 & 3;
                case 44:
                    return b10 & 7;
                case 45:
                    return b11 >> 4;
                case 46:
                    return b11 & 15;
                default:
                    switch (i) {
                        case 90:
                            return 1 & b5;
                        case 91:
                            i4 = b5 >> 1;
                            break;
                        case 92:
                            i4 = b5 >> 2;
                            break;
                        case 93:
                            i4 = b5 >> 3;
                            break;
                        case 94:
                            i4 = b5 >> 4;
                            break;
                        case 95:
                            i4 = b5 >> 5;
                            break;
                    }
                    return 1 & i4;
            }
        }
        return -1;
    }

    public static int getVehicleOfflineConfig(Context context, int i) {
        if (f110bytes == null) {
            byte[] bArr = new byte[25];
            bArr[0] = VehicleSubOpeCode;
            f110bytes = RpcManager.getInstance().getMessage(1562, bArr);
            Log.i(cTag, "request 110 Code" + Arrays.toString(f110bytes));
            if (f110bytes == null) {
                Log.i(cTag, "request 110 Code, bug i get null");
                return 0;
            }
        }
        return get110(f110bytes, i);
    }

    private static int get110(byte[] bArr, int i) {
        int i2;
        if (bArr != null) {
            byte b = bArr[1];
            byte b2 = bArr[2];
            byte b3 = bArr[3];
            byte b4 = bArr[4];
            byte b5 = bArr[5];
            byte b6 = bArr[6];
            switch (i) {
                case 255:
                    i2 = b >> 0;
                    break;
                case 256:
                    i2 = b >> 1;
                    break;
                case 257:
                    i2 = b >> 2;
                    break;
                case 258:
                    i2 = b >> 3;
                    break;
                case 259:
                    i2 = b >> 4;
                    break;
                case 260:
                    i2 = b >> 5;
                    break;
                case 261:
                    i2 = b >> 6;
                    break;
                case 262:
                    i2 = b >> 7;
                    break;
                case 263:
                    i2 = b2 >> 0;
                    break;
                case 264:
                    i2 = b2 >> 1;
                    break;
                case 265:
                    i2 = b2 >> 2;
                    break;
                case 266:
                    i2 = b2 >> 3;
                    break;
                case 267:
                    i2 = b2 >> 4;
                    break;
                case 268:
                    i2 = b2 >> 5;
                    break;
                case 269:
                    i2 = b2 >> 6;
                    break;
                case 270:
                    i2 = b2 >> 7;
                    break;
                case 271:
                    i2 = b3 >> 0;
                    break;
                case 272:
                    i2 = b3 >> 1;
                    break;
                case 273:
                    i2 = b3 >> 2;
                    break;
                case 274:
                    i2 = b3 >> 3;
                    break;
                case 275:
                    i2 = b3 >> 4;
                    break;
                case 276:
                    i2 = b3 >> 5;
                    break;
                case 277:
                    i2 = b3 >> 6;
                    break;
                case 278:
                    i2 = b3 >> 7;
                    break;
                case 279:
                    i2 = b4 >> 0;
                    break;
                case 280:
                    i2 = b4 >> 1;
                    break;
                case 281:
                    i2 = b4 >> 2;
                    break;
                case 282:
                    i2 = b4 >> 3;
                    break;
                case 283:
                    i2 = b4 >> 4;
                    break;
                case 284:
                    i2 = b4 >> 5;
                    break;
                case 285:
                    i2 = b4 >> 6;
                    break;
                case 286:
                    i2 = b4 >> 7;
                    break;
                case 287:
                    i2 = b5 >> 0;
                    break;
                case 288:
                    return get110(bArr, 299) == 1 ? 1 : 0;
                case 289:
                    i2 = b5 >> 6;
                    break;
                case 290:
                    i2 = b5 >> 5;
                    break;
                case 291:
                    i2 = b5 >> 4;
                    break;
                case 292:
                    i2 = b5 >> 2;
                    break;
                case 293:
                    i2 = b6 >> 7;
                    break;
                case 294:
                    i2 = b6 >> 6;
                    break;
                case 295:
                    i2 = b6 >> 5;
                    break;
                case 296:
                    i2 = b6 >> 4;
                    break;
                case 297:
                    i2 = b6 >> 3;
                    break;
                case 298:
                    i2 = b6 >> 2;
                    break;
                case 299:
                    i2 = b6 >> 1;
                    break;
            }
            return 1 & i2;
        }
        return -1;
    }

    private static int getE01F110Cfg(int i) {
        if (f110bytes == null) {
            byte[] bArr = new byte[25];
            bArr[0] = VehicleSubOpeCode;
            f110bytes = RpcManager.getInstance().getMessage(1562, bArr);
            Log.i(cTag, "request 110 Code" + Arrays.toString(f110bytes));
            if (f110bytes == null) {
                Log.i(cTag, "request 110 Code, bug i get null");
                return -1;
            }
        }
        switch (i) {
            case 0:
                return cov2int(f110bytes, 0, 7);
            case 1:
                return cov2int(f110bytes, 0, 6);
            case 2:
                return cov2int(f110bytes, 0, 5);
            case 3:
                return cov2int(f110bytes, 0, 4);
            case 4:
                return cov2int(f110bytes, 0, 3);
            case 5:
                return cov2int(f110bytes, 0, 2);
            case 6:
                return cov2int(f110bytes, 0, 1);
            case 7:
                return cov2int(f110bytes, 0, 0);
            case 8:
                return cov2int(f110bytes, 1, 7);
            case 9:
                return cov2int(f110bytes, 1, 6);
            case 10:
                return cov2int(f110bytes, 1, 5);
            case 11:
                return cov2int(f110bytes, 1, 4);
            case 12:
                return cov2int(f110bytes, 1, 3);
            case 13:
                return cov2int(f110bytes, 1, 2);
            case 14:
                return cov2int(f110bytes, 1, 1);
            case 15:
                return cov2int(f110bytes, 1, 0);
            case 16:
                return cov2int(f110bytes, 2, 7);
            case 17:
                return cov2int(f110bytes, 2, 6);
            case 18:
                return cov2int(f110bytes, 2, 5);
            case 19:
                return cov2int(f110bytes, 2, 4);
            case 20:
                return cov2int(f110bytes, 2, 3);
            case 21:
                return cov2int(f110bytes, 2, 2);
            case 22:
                return cov2int(f110bytes, 2, 1);
            case 23:
                return cov2int(f110bytes, 2, 0);
            case 24:
                return cov2int(f110bytes, 3, 7);
            case 25:
                return cov2int(f110bytes, 3, 6);
            case 26:
                return cov2int(f110bytes, 3, 5);
            case 27:
                return cov2int(f110bytes, 3, 4);
            case 28:
                return cov2int(f110bytes, 3, 3);
            case 29:
                return cov2int(f110bytes, 3, 2);
            case 30:
                return cov2int(f110bytes, 3, 1);
            case 31:
                return cov2int(f110bytes, 3, 0);
            case 32:
                return cov2int(f110bytes, 4, 6);
            case 33:
                return cov2int(f110bytes, 4, 5);
            case 34:
                return cov2int(f110bytes, 4, 4);
            case 35:
                return cov2int(f110bytes, 4, 3);
            case 36:
                return cov2int(f110bytes, 4, 2);
            case 37:
                return cov2int(f110bytes, 4, 0);
            case 38:
                return cov2int(f110bytes, 5, 7);
            case 39:
                return cov2int(f110bytes, 5, 6);
            case 40:
                return cov2int(f110bytes, 5, 5);
            case 41:
                return cov2int(f110bytes, 5, 4);
            case 42:
                return cov2int(f110bytes, 5, 3);
            case 43:
                return cov2int(f110bytes, 5, 2);
            case 44:
                return cov2int(f110bytes, 5, 1);
            case 45:
                return cov2int(f110bytes, 5, 0);
            case 46:
                return cov2int(f110bytes, 6, 7);
            case 47:
                return cov2int(f110bytes, 6, 6);
            case 48:
                return cov2int(f110bytes, 6, 5);
            case 49:
                return cov2int(f110bytes, 6, 4);
            case 50:
                return cov2int(f110bytes, 6, 3);
            case 51:
                return cov2int(f110bytes, 6, 2);
            case 52:
                return cov2int(f110bytes, 6, 1);
            case 53:
                return cov2int(f110bytes, 6, 0);
            case 54:
                return cov2int(f110bytes, 7, 7);
            case 55:
                return cov2int(f110bytes, 7, 6);
            case 56:
                return cov2int(f110bytes, 7, 5);
            case 57:
                return cov2int(f110bytes, 7, 4);
            case 58:
                return cov2int(f110bytes, 7, 3);
            default:
                return -1;
        }
    }

    private static int getE01F101Cfg(int i) {
        if (f101bytes == null) {
            byte[] bArr = new byte[25];
            bArr[0] = 21;
            f101bytes = RpcManager.getInstance().getMessage(1562, bArr);
            Log.i(cTag, "request 101 Code:" + Arrays.toString(f101bytes));
            if (f101bytes == null) {
                Log.i(cTag, "request 101 Code, bug i get null");
                return -1;
            }
        }
        switch (i) {
            case 100:
                return cov2int(f101bytes, 0, 7, 8);
            case 101:
                return cov2int(f101bytes, 1, 7, 8);
            case 102:
                return cov2int(f101bytes, 2, 7, 2);
            case 103:
                return cov2int(f101bytes, 2, 5, 6);
            case 104:
                return cov2int(f101bytes, 3, 7, 2);
            case 105:
                return cov2int(f101bytes, 3, 5, 3);
            case 106:
                return cov2int(f101bytes, 3, 2, 1);
            case 107:
                return cov2int(f101bytes, 3, 1, 1);
            case 108:
                return cov2int(f101bytes, 3, 0, 1);
            case 109:
                return cov2int(f101bytes, 4, 7, 1);
            case 110:
                return cov2int(f101bytes, 4, 6, 1);
            case 111:
                return cov2int(f101bytes, 4, 5, 1);
            case 112:
                return cov2int(f101bytes, 4, 4, 1);
            case 113:
                return cov2int(f101bytes, 4, 3, 2);
            case 114:
                return cov2int(f101bytes, 4, 1, 1);
            case 115:
                return cov2int(f101bytes, 4, 0, 1);
            case 116:
                return cov2int(f101bytes, 5, 7, 3);
            case 117:
                return cov2int(f101bytes, 5, 4, 1);
            case 118:
                return cov2int(f101bytes, 5, 3, 1);
            case 119:
                return cov2int(f101bytes, 5, 2, 1);
            case 120:
                return cov2int(f101bytes, 5, 1, 1);
            case 121:
                return cov2int(f101bytes, 5, 0, 1);
            case 122:
                return cov2int(f101bytes, 6, 7, 1);
            case 123:
                return cov2int(f101bytes, 6, 6, 1);
            case 124:
                return cov2int(f101bytes, 6, 5, 2);
            case 125:
                return cov2int(f101bytes, 6, 3, 4);
            case 126:
                return cov2int(f101bytes, 7, 7, 1);
            case 127:
                return cov2int(f101bytes, 7, 6, 1);
            case 128:
                return cov2int(f101bytes, 7, 5, 3);
            case 129:
                return cov2int(f101bytes, 7, 2, 2);
            case 130:
                return cov2int(f101bytes, 7, 0, 1);
            case 131:
            case 136:
            default:
                return -1;
            case 132:
                return cov2int(f101bytes, 9, 3);
            case 133:
                return cov2int(f101bytes, 9, 2);
            case 134:
                return cov2int(f101bytes, 9, 1);
            case 135:
                return cov2int(f101bytes, 9, 0);
            case 137:
                return cov2int(f101bytes, 10, 3);
            case 138:
                return cov2int(f101bytes, 10, 2);
            case 139:
                return cov2int(f101bytes, 10, 1);
            case 140:
                return cov2int(f101bytes, 8, 7);
            case 141:
                return cov2int(f101bytes, 8, 6);
            case 142:
                return cov2int(f101bytes, 8, 5);
            case 143:
                return cov2int(f101bytes, 8, 4);
            case 144:
                return cov2int(f101bytes, 8, 3);
            case 145:
                return cov2int(f101bytes, 8, 2);
            case 146:
                return cov2int(f101bytes, 8, 1);
            case 147:
                return cov2int(f101bytes, 8, 0);
            case 148:
                return cov2int(f101bytes, 9, 7);
            case 149:
                return cov2int(f101bytes, 9, 6);
            case 150:
                return cov2int(f101bytes, 9, 5);
            case 151:
                return cov2int(f101bytes, 9, 4);
            case 152:
                return cov2int(f101bytes, 10, 7);
            case 153:
                return cov2int(f101bytes, 10, 6);
            case 154:
                return cov2int(f101bytes, 10, 5);
            case 155:
                return cov2int(f101bytes, 10, 4);
            case 156:
                return cov2int(f101bytes, 11, 7, 2);
            case 157:
                return cov2int(f101bytes, 11, 5, 2);
            case 158:
                return cov2int(f101bytes, 11, 3, 2);
        }
    }

    private static int getE01F01VehicleCfg(int i) {
        int iCov2int;
        int iCov2int2;
        AbsVehicleConfig offCfg_FE5AB;
        if (vehicleConfigList == null) {
            int e01OfflineConfig = getE01OfflineConfig(100);
            int e01OfflineConfig2 = getE01OfflineConfig(101);
            if (e01OfflineConfig == 2) {
                LogTool.d(cTag, "current vehicleType is FE-5AB");
                offCfg_FE5AB = new OffCfg_FE5AB();
            } else if (e01OfflineConfig == 21) {
                LogTool.d(cTag, "current vehicleType is SX11-RA");
                offCfg_FE5AB = new OffCfg_SX11RA();
            } else if (e01OfflineConfig == 31) {
                LogTool.d(cTag, "current vehicleType is 测试专用-SX11QJ");
                offCfg_FE5AB = new OffCfg_SX11QJ();
            } else if (e01OfflineConfig == 4) {
                LogTool.d(cTag, "current vehicleType is GE13");
                offCfg_FE5AB = new OffCfg_GE13();
            } else if (e01OfflineConfig == 5) {
                LogTool.d(cTag, "current vehicleType is KC-2A");
                offCfg_FE5AB = new OffCfg_KC2A();
            } else {
                switch (e01OfflineConfig) {
                    case 8:
                        LogTool.d(cTag, "current vehicleType is VF12");
                        offCfg_FE5AB = new OffCfg_VF12();
                        break;
                    case 9:
                        LogTool.d(cTag, "current vehicleType is SX12");
                        offCfg_FE5AB = new OffCfg_SX12();
                        break;
                    case 10:
                        LogTool.d(cTag, "current vehicleType is SX11");
                        offCfg_FE5AB = new OffCfg_SX11();
                        break;
                    case 11:
                        LogTool.d(cTag, "current vehicleType is SX11AD");
                        offCfg_FE5AB = new OffCfg_SX11AD();
                        break;
                    default:
                        switch (e01OfflineConfig) {
                            case 17:
                                LogTool.d(cTag, "current vehicleType is fe6db");
                                offCfg_FE5AB = new OffCfg_FE6DB();
                                break;
                            case 18:
                            case 19:
                                LogTool.d(cTag, "current vehicleType is VF11");
                                offCfg_FE5AB = new OffCfg_VF11();
                                break;
                            default:
                                LogTool.d(cTag, "current vehicleType is default");
                                offCfg_FE5AB = new OffCfg_FE6DB();
                                break;
                        }
                        break;
                }
            }
            vehicleConfigList = offCfg_FE5AB.getVehicleCfg(e01OfflineConfig2);
        }
        switch (i) {
            case 200:
                return cov2int(vehicleConfigList, 0, 7, 2);
            case 201:
                return cov2int(vehicleConfigList, 0, 5);
            case 202:
                return cov2int(vehicleConfigList, 0, 4);
            case 203:
                return cov2int(vehicleConfigList, 0, 3);
            case 204:
                return cov2int(vehicleConfigList, 0, 2);
            case 205:
                return cov2int(vehicleConfigList, 0, 1);
            case 206:
                return cov2int(vehicleConfigList, 0, 0);
            case 207:
                return cov2int(vehicleConfigList, 1, 7);
            case 208:
                return cov2int(vehicleConfigList, 1, 6);
            case 209:
                return cov2int(vehicleConfigList, 1, 5);
            case 210:
                return cov2int(vehicleConfigList, 1, 4);
            case 211:
                return cov2int(vehicleConfigList, 1, 3);
            case 212:
                return cov2int(vehicleConfigList, 1, 2, 2);
            case 213:
                return cov2int(vehicleConfigList, 1, 0);
            case 214:
                return cov2int(vehicleConfigList, 2, 7);
            case 215:
                return cov2int(vehicleConfigList, 2, 6);
            case 216:
                return cov2int(vehicleConfigList, 2, 5);
            case 217:
                return cov2int(vehicleConfigList, 2, 4);
            case 218:
                return cov2int(vehicleConfigList, 2, 3);
            case 219:
                return cov2int(vehicleConfigList, 2, 2);
            case 220:
                return cov2int(vehicleConfigList, 2, 1);
            case 221:
                return cov2int(vehicleConfigList, 2, 0);
            case 222:
                return cov2int(vehicleConfigList, 3, 7);
            case 223:
                return cov2int(vehicleConfigList, 3, 6);
            case 224:
                return cov2int(vehicleConfigList, 3, 5);
            case 225:
                return cov2int(vehicleConfigList, 3, 4);
            case 226:
                return cov2int(vehicleConfigList, 3, 3, 2);
            case 227:
                iCov2int = cov2int(vehicleConfigList, 3, 1, 2) << 1;
                iCov2int2 = cov2int(vehicleConfigList, 4, 7, 1);
                break;
            case 228:
                return cov2int(vehicleConfigList, 4, 6);
            case 229:
                return cov2int(vehicleConfigList, 4, 5, 2);
            case 230:
                return cov2int(vehicleConfigList, 4, 3);
            case 231:
                return cov2int(vehicleConfigList, 4, 2);
            case 232:
                return cov2int(vehicleConfigList, 4, 1);
            case 233:
                return cov2int(vehicleConfigList, 4, 0);
            case 234:
                return cov2int(vehicleConfigList, 5, 7);
            case 235:
                return cov2int(vehicleConfigList, 5, 6);
            case 236:
                return cov2int(vehicleConfigList, 5, 5);
            case 237:
                return cov2int(vehicleConfigList, 5, 4);
            case 238:
                return cov2int(vehicleConfigList, 5, 3);
            case 239:
                return cov2int(vehicleConfigList, 5, 2);
            case 240:
                return cov2int(vehicleConfigList, 5, 1);
            case 241:
                return cov2int(vehicleConfigList, 5, 0);
            case 242:
                return cov2int(vehicleConfigList, 6, 7);
            case 243:
                return cov2int(vehicleConfigList, 6, 6);
            case 244:
                return cov2int(vehicleConfigList, 6, 5);
            case 245:
                return cov2int(vehicleConfigList, 6, 4);
            case 246:
                return cov2int(vehicleConfigList, 6, 3);
            case 247:
                return cov2int(vehicleConfigList, 6, 2, 2);
            case 248:
                return cov2int(vehicleConfigList, 6, 0);
            case 249:
                return cov2int(vehicleConfigList, 7, 7);
            case 250:
                return cov2int(vehicleConfigList, 7, 6);
            case 251:
                return cov2int(vehicleConfigList, 7, 5);
            case 252:
                return cov2int(vehicleConfigList, 7, 4);
            case 253:
                return cov2int(vehicleConfigList, 7, 3);
            case 254:
                return cov2int(vehicleConfigList, 7, 2);
            case 255:
                return cov2int(vehicleConfigList, 7, 1);
            case 256:
                iCov2int = cov2int(vehicleConfigList, 7, 0) << 1;
                iCov2int2 = cov2int(vehicleConfigList, 8, 7);
                break;
            case 257:
                return cov2int(vehicleConfigList, 8, 6);
            case 258:
                return cov2int(vehicleConfigList, 8, 5);
            case 259:
                return cov2int(vehicleConfigList, 8, 4);
            case 260:
                return cov2int(vehicleConfigList, 8, 3);
            case 261:
                return cov2int(vehicleConfigList, 8, 2);
            case 262:
                return cov2int(vehicleConfigList, 8, 1);
            case 263:
                return cov2int(vehicleConfigList, 8, 0);
            case 264:
                return cov2int(vehicleConfigList, 9, 7);
            case 265:
                return cov2int(vehicleConfigList, 9, 6);
            case 266:
                return cov2int(vehicleConfigList, 9, 5);
            case 267:
                return cov2int(vehicleConfigList, 9, 4);
            case 268:
                return cov2int(vehicleConfigList, 9, 3);
            case 269:
                return cov2int(vehicleConfigList, 9, 2);
            case 270:
                return cov2int(vehicleConfigList, 9, 1);
            case 271:
                return cov2int(vehicleConfigList, 9, 0);
            case 272:
                return cov2int(vehicleConfigList, 10, 7);
            case 273:
                return cov2int(vehicleConfigList, 10, 6);
            case 274:
                return cov2int(vehicleConfigList, 10, 5, 2);
            case 275:
                return cov2int(vehicleConfigList, 10, 3);
            case 276:
                return cov2int(vehicleConfigList, 10, 2);
            default:
                return -1;
        }
        return iCov2int | iCov2int2;
    }

    private static int cov2int(byte[] bArr, int i, int i2) {
        return cov2int(bArr, i, i2, 1);
    }

    private static int cov2int(byte[] bArr, int i, int i2, int i3) {
        int i4;
        LogTool.d(cTag, "cov2int list=" + Arrays.toString(bArr));
        LogTool.d(cTag, "cov2int byteIndex=" + i + " bitIndex=" + i2 + " length=" + i3);
        int i5 = bArr[i + 1];
        if (i3 > 1) {
            i4 = 1;
            for (int i6 = 1; i6 < i3; i6++) {
                i4 = (i4 << 1) | 1;
            }
        } else {
            i4 = 1;
        }
        return (i5 >> ((i2 + 1) - i3)) & i4;
    }

    public static int getE01OfflineConfig(int i) {
        int e01F01VehicleCfg;
        LogTool.d(cTag, "getE01OfflineConfig type =" + i);
        if (i < 100) {
            e01F01VehicleCfg = getE01F110Cfg(i);
        } else if (i < 200) {
            e01F01VehicleCfg = getE01F101Cfg(i);
        } else {
            e01F01VehicleCfg = getE01F01VehicleCfg(i);
        }
        LogTool.d(cTag, "getE01OfflineConfig result =" + e01F01VehicleCfg);
        return e01F01VehicleCfg;
    }
}
