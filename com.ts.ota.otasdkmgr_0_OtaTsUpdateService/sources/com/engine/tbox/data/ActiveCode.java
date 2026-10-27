package com.engine.tbox.data;

import android.text.TextUtils;
import com.engine.tbox.core.utils.TypeUtils;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class ActiveCode {
    private static final int ACTIVE_CODE_LENGTH = 128;
    public static final int MAX_PROVIDER_TYPE_VAL = 3;
    public static final int MIN_PROVIDER_TYPE_VAL = 1;
    private static Map<NationalRegion, Byte> nationalMap;
    private ActiveState mActiveState;
    private long mActiveTime;
    private long mExpireTime;
    private NationalRegion mNational;
    private String mPackageName;
    private Region mRegion;
    private ProviderType mType = ProviderType.INVALID_TYPE;
    private boolean mGetVirtualVinResult = false;
    private ProviderName mProviderName = ProviderName.NeuSoft;

    public enum ActiveState {
        ATCIVE_INVALID,
        ATCIVE_SUCCESS,
        ATCIVE_FAILED
    }

    public enum ProviderName {
        InValidName,
        TeleNav,
        Cerence,
        Spotify,
        NeuSoft
    }

    public enum ProviderType {
        INVALID_TYPE,
        MAP,
        VR,
        MUSIC
    }

    public ActiveCode() {
        if (nationalMap == null) {
            nationalMap = new EnumMap(NationalRegion.class);
            for (NationalRegion national : NationalRegion.values()) {
                byte num = national.toByte();
                nationalMap.put(national, Byte.valueOf(num));
            }
        }
    }

    public enum Region {
        INVALID_REGION(0),
        Europe(1),
        America(2),
        AsiaPacific(3),
        MiddleEastAfrica(4);

        private final int mVal;

        Region(int val) {
            this.mVal = val;
        }

        public byte toByte() {
            return (byte) this.mVal;
        }
    }

    public ProviderType getProviderType() {
        return this.mType;
    }

    public void setProviderType(ProviderType type) {
        this.mType = type;
    }

    public boolean isGetVirtualVinSuccess() {
        return this.mGetVirtualVinResult;
    }

    public void setGetVirtualVinResult(boolean result) {
        this.mGetVirtualVinResult = result;
    }

    public ProviderName getProviderName() {
        return this.mProviderName;
    }

    public void setProviderName(ProviderName providerName) {
        this.mProviderName = providerName;
    }

    public ActiveState getActiveState() {
        return this.mActiveState;
    }

    public void setActiveState(ActiveState activeState) {
        this.mActiveState = activeState;
    }

    public long getActiveTime() {
        return this.mActiveTime;
    }

    public void setActiveTime(long activeTime) {
        this.mActiveTime = activeTime;
    }

    public long getExpireTime() {
        return this.mExpireTime;
    }

    public void setExpireTime(long expireTime) {
        this.mExpireTime = expireTime;
    }

    public NationalRegion getNational() {
        return this.mNational;
    }

    public void setNational(NationalRegion national) {
        this.mNational = national;
    }

    public Region getRegion() {
        return this.mRegion;
    }

    public void setRegion(Region region) {
        this.mRegion = region;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setPackageName(String packageName) {
        this.mPackageName = packageName;
    }

    public boolean checkValid() {
        return !TextUtils.isEmpty(this.mPackageName);
    }

    public byte[] toByteArray() {
        String str = this.mPackageName;
        int len = str == null ? 0 : str.length();
        byte[] array = new byte[128];
        int pos = 0 + 1;
        array[0] = providerTypeToByte(this.mType);
        int pos2 = pos + 1;
        array[pos] = (byte) (this.mGetVirtualVinResult ? 1 : 2);
        int pos3 = pos2 + 1;
        array[pos2] = providerNameToByte(this.mProviderName);
        int pos4 = pos3 + 1;
        array[pos3] = activeStateToByte(this.mActiveState);
        byte[] data = TypeUtils.longToBytes(this.mActiveTime);
        System.arraycopy(data, 0, array, pos4, 8);
        int pos5 = pos4 + 8;
        byte[] data2 = TypeUtils.longToBytes(this.mExpireTime);
        System.arraycopy(data2, 0, array, pos5, 8);
        int pos6 = pos5 + 8;
        int pos7 = pos6 + 1;
        array[pos6] = this.mNational.toByte();
        int pos8 = pos7 + 1;
        array[pos7] = this.mRegion.toByte();
        if (TextUtils.isEmpty(this.mPackageName)) {
            int i = pos8 + 1;
            array[pos8] = 0;
        } else {
            array[pos8] = (byte) this.mPackageName.length();
            System.arraycopy(this.mPackageName.getBytes(StandardCharsets.UTF_8), 0, array, pos8 + 1, len);
        }
        return array;
    }

    public static byte providerTypeToByte(ProviderType type) {
        int i = AnonymousClass1.$SwitchMap$com$engine$tbox$data$ActiveCode$ProviderType[type.ordinal()];
        if (i == 1) {
            return (byte) 1;
        }
        if (i != 2) {
            return i != 3 ? (byte) 0 : (byte) 3;
        }
        return (byte) 2;
    }

    public static ProviderType byteToProviderType(byte type) {
        if (type == 1) {
            return ProviderType.MAP;
        }
        if (type == 2) {
            return ProviderType.VR;
        }
        if (type == 3) {
            return ProviderType.MUSIC;
        }
        return ProviderType.INVALID_TYPE;
    }

    private static byte providerNameToByte(ProviderName name) {
        int i = AnonymousClass1.$SwitchMap$com$engine$tbox$data$ActiveCode$ProviderName[name.ordinal()];
        if (i == 1) {
            return (byte) 1;
        }
        if (i == 2) {
            return (byte) 2;
        }
        if (i != 3) {
            return i != 4 ? (byte) 0 : (byte) 4;
        }
        return (byte) 3;
    }

    public static ProviderName byteToProviderName(byte name) {
        if (name == 1) {
            return ProviderName.TeleNav;
        }
        if (name == 2) {
            return ProviderName.Cerence;
        }
        if (name == 3) {
            return ProviderName.Spotify;
        }
        if (name == 4) {
            return ProviderName.NeuSoft;
        }
        return ProviderName.InValidName;
    }

    /* JADX INFO: renamed from: com.engine.tbox.data.ActiveCode$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$engine$tbox$data$ActiveCode$ActiveState;
        static final /* synthetic */ int[] $SwitchMap$com$engine$tbox$data$ActiveCode$ProviderName;
        static final /* synthetic */ int[] $SwitchMap$com$engine$tbox$data$ActiveCode$ProviderType;

        static {
            int[] iArr = new int[ActiveState.values().length];
            $SwitchMap$com$engine$tbox$data$ActiveCode$ActiveState = iArr;
            try {
                iArr[ActiveState.ATCIVE_INVALID.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$engine$tbox$data$ActiveCode$ActiveState[ActiveState.ATCIVE_SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$engine$tbox$data$ActiveCode$ActiveState[ActiveState.ATCIVE_FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            int[] iArr2 = new int[ProviderName.values().length];
            $SwitchMap$com$engine$tbox$data$ActiveCode$ProviderName = iArr2;
            try {
                iArr2[ProviderName.TeleNav.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$engine$tbox$data$ActiveCode$ProviderName[ProviderName.Cerence.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$engine$tbox$data$ActiveCode$ProviderName[ProviderName.Spotify.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$engine$tbox$data$ActiveCode$ProviderName[ProviderName.NeuSoft.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$engine$tbox$data$ActiveCode$ProviderName[ProviderName.InValidName.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            int[] iArr3 = new int[ProviderType.values().length];
            $SwitchMap$com$engine$tbox$data$ActiveCode$ProviderType = iArr3;
            try {
                iArr3[ProviderType.MAP.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$engine$tbox$data$ActiveCode$ProviderType[ProviderType.VR.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$engine$tbox$data$ActiveCode$ProviderType[ProviderType.MUSIC.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$engine$tbox$data$ActiveCode$ProviderType[ProviderType.INVALID_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    private static byte activeStateToByte(ActiveState state) {
        int i = AnonymousClass1.$SwitchMap$com$engine$tbox$data$ActiveCode$ActiveState[state.ordinal()];
        if (i != 1) {
            return i != 2 ? (byte) 2 : (byte) 1;
        }
        return (byte) 0;
    }

    public static ActiveState byteToActiveState(byte state) {
        if (state == 0) {
            return ActiveState.ATCIVE_INVALID;
        }
        if (state == 1) {
            return ActiveState.ATCIVE_SUCCESS;
        }
        return ActiveState.ATCIVE_FAILED;
    }

    public static Region byteToRegion(byte state) {
        if (state == 1) {
            return Region.Europe;
        }
        if (state == 2) {
            return Region.America;
        }
        if (state == 3) {
            return Region.AsiaPacific;
        }
        if (state == 4) {
            return Region.MiddleEastAfrica;
        }
        return Region.INVALID_REGION;
    }

    public static NationalRegion byteToNation(byte data) {
        Map<NationalRegion, Byte> map = nationalMap;
        if (map != null) {
            for (Map.Entry<NationalRegion, Byte> entry : map.entrySet()) {
                if (entry.getValue().equals(Byte.valueOf(data))) {
                    return entry.getKey();
                }
            }
        }
        return NationalRegion.INVALID;
    }

    public String toString() {
        return "ActiveCode{mType=" + this.mType + ", mGetVirtualVinResult=" + this.mGetVirtualVinResult + ", mProviderName=" + this.mProviderName + ", mActiveState=" + this.mActiveState + ", mActiveTime=" + this.mActiveTime + ", mExpireTime=" + this.mExpireTime + ", mNational=" + this.mNational + ", mRegion=" + this.mRegion + ", mPackageName='" + this.mPackageName + "'}";
    }
}
