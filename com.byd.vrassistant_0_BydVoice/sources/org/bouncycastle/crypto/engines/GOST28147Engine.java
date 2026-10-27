package org.bouncycastle.crypto.engines;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import java.util.Hashtable;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* JADX INFO: loaded from: classes4.dex */
public class GOST28147Engine implements BlockCipher {
    protected static final int BLOCK_SIZE = 8;
    private boolean forEncryption;
    private static byte[] Sbox_Default = {4, 10, 9, 2, Ascii.CR, 8, 0, Ascii.SO, 6, 11, 1, 12, 7, Ascii.SI, 5, 3, Ascii.SO, 11, 4, 12, 6, Ascii.CR, Ascii.SI, 10, 2, 3, 8, 1, 0, 7, 5, 9, 5, 8, 1, Ascii.CR, 10, 3, 4, 2, Ascii.SO, Ascii.SI, 12, 7, 6, 0, 9, 11, 7, Ascii.CR, 10, 1, 0, 8, 9, Ascii.SI, Ascii.SO, 4, 6, 12, 11, 2, 5, 3, 6, 12, 7, 1, 5, Ascii.SI, Ascii.CR, 8, 4, 10, 9, Ascii.SO, 0, 3, 11, 2, 4, 11, 10, 0, 7, 2, 1, Ascii.CR, 3, 6, 8, 5, 9, 12, Ascii.SI, Ascii.SO, Ascii.CR, 11, 4, 1, 3, Ascii.SI, 5, 9, 0, 10, Ascii.SO, 7, 6, 8, 2, 12, 1, Ascii.SI, Ascii.CR, 0, 5, 7, 10, 4, 9, 2, 3, Ascii.SO, 6, 11, 8, 12};
    private static byte[] ESbox_Test = {4, 2, Ascii.SI, 5, 9, 1, 0, 8, Ascii.SO, 3, 11, 12, Ascii.CR, 7, 10, 6, 12, 9, Ascii.SI, Ascii.SO, 8, 1, 3, 10, 2, 7, 4, Ascii.CR, 6, 0, 11, 5, Ascii.CR, 8, Ascii.SO, 12, 7, 3, 9, 10, 1, 5, 2, 4, 6, Ascii.SI, 0, 11, Ascii.SO, 9, 11, 2, 5, Ascii.SI, 7, 1, 0, Ascii.CR, 12, 6, 10, 4, 3, 8, 3, Ascii.SO, 5, 9, 6, 8, 0, Ascii.CR, 10, 11, 7, 12, 2, 1, Ascii.SI, 4, 8, Ascii.SI, 6, 11, 1, 9, 12, 5, Ascii.CR, 3, 7, 10, 0, Ascii.SO, 2, 4, 9, 11, 12, 0, 3, 6, 7, 5, 4, 8, Ascii.SO, Ascii.SI, 1, 10, 2, Ascii.CR, 12, 6, 5, 2, 11, 0, 9, Ascii.CR, 3, Ascii.SO, 7, 10, Ascii.SI, 4, 1, 8};
    private static byte[] ESbox_A = {9, 6, 3, 2, 8, 11, 1, 7, 10, 4, Ascii.SO, Ascii.SI, 12, 0, Ascii.CR, 5, 3, 7, Ascii.SO, 9, 8, 10, Ascii.SI, 0, 5, 2, 6, 12, 11, 4, Ascii.CR, 1, Ascii.SO, 4, 6, 2, 11, 3, Ascii.CR, 8, 12, Ascii.SI, 5, 10, 0, 7, 1, 9, Ascii.SO, 7, 10, 12, Ascii.CR, 1, 3, 9, 0, 2, 11, 4, Ascii.SI, 8, 5, 6, 11, 5, 1, 9, 8, Ascii.CR, Ascii.SI, 0, Ascii.SO, 4, 2, 3, 12, 7, 10, 6, 3, 10, Ascii.CR, 12, 1, 2, 0, 11, 7, 5, 9, 4, 8, Ascii.SI, Ascii.SO, 6, 1, Ascii.CR, 2, 9, 7, 10, 6, 0, 8, 12, 4, 5, Ascii.SI, 3, 11, Ascii.SO, 11, 10, Ascii.SI, 5, 0, 12, Ascii.SO, 8, 6, 2, 3, 9, 1, 7, Ascii.CR, 4};
    private static byte[] ESbox_B = {8, 4, 11, 1, 3, 5, 0, 9, 2, Ascii.SO, 10, 12, Ascii.CR, 6, 7, Ascii.SI, 0, 1, 2, 10, 4, Ascii.CR, 5, 12, 9, 7, 3, Ascii.SI, 11, 8, 6, Ascii.SO, Ascii.SO, 12, 0, 10, 9, 2, Ascii.CR, 11, 7, 5, 8, Ascii.SI, 3, 6, 1, 4, 7, 5, 0, Ascii.CR, 11, 6, 1, 2, 3, 10, 12, Ascii.SI, 4, Ascii.SO, 9, 8, 2, 7, 12, Ascii.SI, 9, 5, 10, 11, 1, 4, 0, Ascii.CR, 6, 8, Ascii.SO, 3, 8, 3, 2, 6, 4, Ascii.CR, Ascii.SO, 11, 12, 1, 7, Ascii.SI, 10, 0, 9, 5, 5, 2, 10, 11, 9, 1, 12, 3, 7, 4, Ascii.CR, 0, 6, Ascii.SI, 8, Ascii.SO, 0, 4, 11, Ascii.SO, 8, 3, 7, 1, 10, 2, 9, 6, Ascii.SI, Ascii.CR, 5, 12};
    private static byte[] ESbox_C = {1, 11, 12, 2, 9, Ascii.CR, 0, Ascii.SI, 4, 5, 8, Ascii.SO, 10, 7, 6, 3, 0, 1, 7, Ascii.CR, 11, 4, 5, 2, 8, Ascii.SO, Ascii.SI, 12, 9, 10, 6, 3, 8, 2, 5, 0, 4, 9, Ascii.SI, 10, 3, 7, 12, Ascii.CR, 6, Ascii.SO, 1, 11, 3, 6, 0, 1, 5, Ascii.CR, 10, 8, 11, 2, 9, 7, Ascii.SO, Ascii.SI, 12, 4, 8, Ascii.CR, 11, 0, 4, 5, 1, 2, 9, 3, 12, Ascii.SO, 6, Ascii.SI, 10, 7, 12, 9, 11, 1, 8, Ascii.SO, 2, 4, 7, 3, 6, 5, 10, 0, Ascii.SI, Ascii.CR, 10, 9, 6, 8, Ascii.CR, Ascii.SO, 2, 0, Ascii.SI, 3, 5, 11, 4, 1, 12, 7, 7, 4, 0, 5, 10, 2, Ascii.SI, Ascii.SO, 12, 6, 1, 11, Ascii.CR, 9, 3, 8};
    private static byte[] ESbox_D = {Ascii.SI, 12, 2, 10, 6, 4, 5, 0, 7, 9, Ascii.SO, Ascii.CR, 1, 11, 8, 3, 11, 6, 3, 4, 12, Ascii.SI, Ascii.SO, 2, 7, Ascii.CR, 8, 0, 5, 10, 9, 1, 1, 12, 11, 0, Ascii.SI, Ascii.SO, 6, 5, 10, Ascii.CR, 4, 8, 9, 3, 7, 2, 1, 5, Ascii.SO, 12, 10, 7, 0, Ascii.CR, 6, 2, 11, 4, 9, 3, Ascii.SI, 8, 0, 12, 8, 9, Ascii.CR, 2, 10, 11, 7, 3, 6, 5, 4, Ascii.SO, Ascii.SI, 1, 8, 0, Ascii.SI, 3, 2, 5, Ascii.SO, 11, 1, 10, 4, 7, 12, 9, Ascii.CR, 6, 3, 0, 6, Ascii.SI, 1, Ascii.SO, 9, 2, Ascii.CR, 8, 12, 4, 11, 10, 5, 7, 1, 10, 6, 8, Ascii.SI, 11, 0, 4, 12, 3, 5, 9, 7, Ascii.CR, 2, Ascii.SO};
    private static byte[] DSbox_Test = {4, 10, 9, 2, Ascii.CR, 8, 0, Ascii.SO, 6, 11, 1, 12, 7, Ascii.SI, 5, 3, Ascii.SO, 11, 4, 12, 6, Ascii.CR, Ascii.SI, 10, 2, 3, 8, 1, 0, 7, 5, 9, 5, 8, 1, Ascii.CR, 10, 3, 4, 2, Ascii.SO, Ascii.SI, 12, 7, 6, 0, 9, 11, 7, Ascii.CR, 10, 1, 0, 8, 9, Ascii.SI, Ascii.SO, 4, 6, 12, 11, 2, 5, 3, 6, 12, 7, 1, 5, Ascii.SI, Ascii.CR, 8, 4, 10, 9, Ascii.SO, 0, 3, 11, 2, 4, 11, 10, 0, 7, 2, 1, Ascii.CR, 3, 6, 8, 5, 9, 12, Ascii.SI, Ascii.SO, Ascii.CR, 11, 4, 1, 3, Ascii.SI, 5, 9, 0, 10, Ascii.SO, 7, 6, 8, 2, 12, 1, Ascii.SI, Ascii.CR, 0, 5, 7, 10, 4, 9, 2, 3, Ascii.SO, 6, 11, 8, 12};
    private static byte[] DSbox_A = {10, 4, 5, 6, 8, 1, 3, 7, Ascii.CR, 12, Ascii.SO, 0, 9, 2, 11, Ascii.SI, 5, Ascii.SI, 4, 0, 2, Ascii.CR, 11, 9, 1, 7, 6, 3, 12, Ascii.SO, 10, 8, 7, Ascii.SI, 12, Ascii.SO, 9, 4, 1, 0, 3, 11, 5, 2, 6, 10, 8, Ascii.CR, 4, 10, 7, 12, 0, Ascii.SI, 2, 8, Ascii.SO, 1, 6, 5, Ascii.CR, 11, 9, 3, 7, 6, 4, 11, 9, 12, 2, 10, 1, 8, 0, Ascii.SO, Ascii.SI, Ascii.CR, 3, 5, 7, 6, 2, 4, Ascii.CR, 9, Ascii.SI, 0, 10, 1, 5, 11, 8, Ascii.SO, 12, 3, Ascii.CR, Ascii.SO, 4, 1, 7, 0, 5, 10, 3, 12, 8, Ascii.SI, 6, 2, 9, 11, 1, 3, 10, 9, 5, 11, 4, Ascii.SI, 8, 6, 7, Ascii.SO, Ascii.CR, 0, 2, 12};
    private static Hashtable sBoxes = new Hashtable();
    private int[] workingKey = null;
    private byte[] S = Sbox_Default;

    static {
        addSBox("Default", Sbox_Default);
        addSBox("E-TEST", ESbox_Test);
        addSBox("E-A", ESbox_A);
        addSBox("E-B", ESbox_B);
        addSBox("E-C", ESbox_C);
        addSBox("E-D", ESbox_D);
        addSBox("D-TEST", DSbox_Test);
        addSBox("D-A", DSbox_A);
    }

    private void GOST28147Func(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3;
        int i4;
        int iBytesToint = bytesToint(bArr, i);
        int iBytesToint2 = bytesToint(bArr, i + 4);
        int i5 = 7;
        if (this.forEncryption) {
            for (int i6 = 0; i6 < 3; i6++) {
                int i7 = 0;
                while (i7 < 8) {
                    int iGOST28147_mainStep = iBytesToint2 ^ GOST28147_mainStep(iBytesToint, iArr[i7]);
                    i7++;
                    int i8 = iBytesToint;
                    iBytesToint = iGOST28147_mainStep;
                    iBytesToint2 = i8;
                }
            }
            i3 = iBytesToint2;
            i4 = iBytesToint;
            while (i5 > 0) {
                int iGOST28147_mainStep2 = i3 ^ GOST28147_mainStep(i4, iArr[i5]);
                i5--;
                i3 = i4;
                i4 = iGOST28147_mainStep2;
            }
        } else {
            int i9 = 0;
            while (i9 < 8) {
                int iGOST28147_mainStep3 = iBytesToint2 ^ GOST28147_mainStep(iBytesToint, iArr[i9]);
                i9++;
                int i10 = iBytesToint;
                iBytesToint = iGOST28147_mainStep3;
                iBytesToint2 = i10;
            }
            i3 = iBytesToint2;
            i4 = iBytesToint;
            for (int i11 = 0; i11 < 3; i11++) {
                int i12 = 7;
                while (i12 >= 0 && (i11 != 2 || i12 != 0)) {
                    int iGOST28147_mainStep4 = i3 ^ GOST28147_mainStep(i4, iArr[i12]);
                    i12--;
                    i3 = i4;
                    i4 = iGOST28147_mainStep4;
                }
            }
        }
        int iGOST28147_mainStep5 = GOST28147_mainStep(i4, iArr[0]) ^ i3;
        intTobytes(i4, bArr2, i2);
        intTobytes(iGOST28147_mainStep5, bArr2, i2 + 4);
    }

    private int GOST28147_mainStep(int i, int i2) {
        int i3 = i2 + i;
        byte[] bArr = this.S;
        int i4 = (bArr[((i3 >> 0) & 15) + 0] << 0) + (bArr[((i3 >> 4) & 15) + 16] << 4) + (bArr[((i3 >> 8) & 15) + 32] << 8) + (bArr[((i3 >> 12) & 15) + 48] << 12) + (bArr[((i3 >> 16) & 15) + 64] << 16) + (bArr[((i3 >> 20) & 15) + 80] << Ascii.DC4) + (bArr[((i3 >> 24) & 15) + 96] << Ascii.CAN) + (bArr[((i3 >> 28) & 15) + 112] << Ascii.FS);
        return (i4 << 11) | (i4 >>> 21);
    }

    private static void addSBox(String str, byte[] bArr) {
        sBoxes.put(Strings.toUpperCase(str), bArr);
    }

    private int bytesToint(byte[] bArr, int i) {
        return ((bArr[i + 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) + ((bArr[i + 2] << 16) & 16711680) + ((bArr[i + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[i] & 255);
    }

    private int[] generateWorkingKey(boolean z, byte[] bArr) {
        this.forEncryption = z;
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        int[] iArr = new int[8];
        for (int i = 0; i != 8; i++) {
            iArr[i] = bytesToint(bArr, i * 4);
        }
        return iArr;
    }

    public static byte[] getSBox(String str) {
        byte[] bArr = (byte[]) sBoxes.get(Strings.toUpperCase(str));
        if (bArr != null) {
            return Arrays.clone(bArr);
        }
        throw new IllegalArgumentException("Unknown S-Box - possible types: \"Default\", \"E-Test\", \"E-A\", \"E-B\", \"E-C\", \"E-D\", \"D-Test\", \"D-A\".");
    }

    private void intTobytes(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >>> 24);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2] = (byte) i;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "GOST28147";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithSBox)) {
            if (cipherParameters instanceof KeyParameter) {
                this.workingKey = generateWorkingKey(z, ((KeyParameter) cipherParameters).getKey());
                return;
            } else {
                if (cipherParameters != null) {
                    throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + cipherParameters.getClass().getName());
                }
                return;
            }
        }
        ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
        byte[] sBox = parametersWithSBox.getSBox();
        if (sBox.length != Sbox_Default.length) {
            throw new IllegalArgumentException("invalid S-box passed to GOST28147 init");
        }
        this.S = Arrays.clone(sBox);
        if (parametersWithSBox.getParameters() != null) {
            this.workingKey = generateWorkingKey(z, ((KeyParameter) parametersWithSBox.getParameters()).getKey());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = this.workingKey;
        if (iArr == null) {
            throw new IllegalStateException("GOST28147 engine not initialised");
        }
        if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 8 > bArr2.length) {
            throw new DataLengthException("output buffer too short");
        }
        GOST28147Func(iArr, bArr, i, bArr2, i2);
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
