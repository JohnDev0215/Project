package com.byd.music.view.jingyun.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class AudioVisualConverter {
    private static final int MAX_SIZE = 180;
    private static final String TAG = AudioVisualConverter.class.getSimpleName();

    public byte[] converter(byte[] bArr) {
        if (bArr == null || bArr.length < 180) {
            return null;
        }
        return genWaveDate(adornData(pickAltData(readyData(bArr))));
    }

    private List<Byte> readyData(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf((byte) (128 - Math.abs((int) b))));
        }
        return arrayList;
    }

    public byte[] readyDataByte(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) (128 - Math.abs((int) bArr[i]));
        }
        return bArr2;
    }

    public byte[] converterFft(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        byte[] bArr3 = readyFftDataByte(bArr);
        for (int i = 0; i < bArr3.length / 2; i++) {
            int i2 = i * 2;
            bArr2[i2] = bArr3[i];
            if (i != (bArr3.length / 2) - 1) {
                bArr2[i2 + 1] = (byte) (((double) bArr2[i2]) * Math.random());
            }
        }
        return bArr2;
    }

    public byte[] readyFftDataByte(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) Math.abs((int) bArr[i]);
        }
        return bArr2;
    }

    public int[] getVoiceSizes(byte[] bArr) {
        byte[] bArr2 = readyFftDataByte(bArr);
        int length = bArr2.length / 8;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 8;
            byte b = 0;
            for (int i3 = i2; i3 < i2 + 8; i3++) {
                if (bArr2[i3] > b) {
                    b = bArr2[i3];
                }
            }
            iArr[i] = b;
        }
        return iArr;
    }

    public int getVoiceSize(byte[] bArr) {
        int[] voiceSizes = getVoiceSizes(readyFftDataByte(bArr));
        int i = 0;
        int i2 = 0;
        while (i < 3) {
            if (i == 0 || i == 1 || i == 2) {
                int i3 = voiceSizes[i];
                i2 += i3;
                i++;
            } else {
                i++;
            }
        }
        return i2 / 3;
    }

    private Map<Integer, Byte> pickAltData(List<Byte> list) {
        int i;
        HashMap map = new HashMap();
        int i2 = 0;
        int i3 = 0;
        byte bByteValue = 0;
        while (true) {
            if (i2 >= 32) {
                break;
            }
            Byte b = list.get(i2);
            if (b.byteValue() > bByteValue) {
                bByteValue = b.byteValue();
                i3 = i2;
            }
            i2++;
        }
        map.put(Integer.valueOf(i3), Byte.valueOf(bByteValue));
        byte bByteValue2 = 0;
        byte bByteValue3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (i = 32; i < list.size(); i++) {
            Byte b2 = list.get(i);
            if (b2.byteValue() > bByteValue2) {
                bByteValue2 = b2.byteValue();
                i4 = i;
            } else if (b2.byteValue() < bByteValue3) {
                if (i4 - i5 > 26) {
                    map.put(Integer.valueOf(i4), Byte.valueOf(bByteValue2));
                    i5 = i4;
                }
                bByteValue2 = 0;
            }
            bByteValue3 = b2.byteValue();
        }
        return map;
    }

    private Map<Integer, Byte> adornData(Map<Integer, Byte> map) {
        HashMap map2 = new HashMap();
        byte bByteValue = 0;
        for (Integer num : map.keySet()) {
            Byte b = map.get(num);
            if (b.byteValue() > bByteValue) {
                bByteValue = b.byteValue();
                num.intValue();
            }
        }
        for (Map.Entry<Integer, Byte> entry : map.entrySet()) {
            Integer key = entry.getKey();
            double dByteValue = entry.getValue().byteValue();
            byte bRandom = (byte) (Math.random() * dByteValue);
            byte bRandom2 = (byte) (dByteValue * Math.random());
            byte bRandom3 = (byte) (((double) bRandom) * Math.random());
            byte bRandom4 = (byte) (((double) bRandom2) * Math.random());
            if (key.intValue() - 2 >= 0) {
                map2.put(Integer.valueOf(key.intValue() - 2), Byte.valueOf(bRandom3));
            }
            if (key.intValue() - 1 >= 0) {
                map2.put(Integer.valueOf(key.intValue() - 1), Byte.valueOf(bRandom));
            }
            if (key.intValue() + 1 < 180) {
                map2.put(Integer.valueOf(key.intValue() + 1), Byte.valueOf(bRandom2));
            }
            if (key.intValue() + 2 < 180) {
                map2.put(Integer.valueOf(key.intValue() + 2), Byte.valueOf(bRandom4));
            }
        }
        map.putAll(map2);
        return map;
    }

    private byte[] genWaveDate(Map<Integer, Byte> map) {
        byte[] bArr = new byte[180];
        for (int i = 0; i < 180; i++) {
            Byte b = map.get(Integer.valueOf(i));
            bArr[i] = b == null ? (byte) 0 : b.byteValue();
        }
        return bArr;
    }
}
