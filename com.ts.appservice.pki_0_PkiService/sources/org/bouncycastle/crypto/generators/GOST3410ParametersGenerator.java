package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.GOST3410Parameters;
import org.bouncycastle.crypto.params.GOST3410ValidationParameters;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class GOST3410ParametersGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private SecureRandom init_random;
    private int size;
    private int typeproc;

    private int procedure_A(int i, int i2, BigInteger[] bigIntegerArr, int i3) {
        int i4;
        BigInteger bigInteger;
        BigInteger[] bigIntegerArr2;
        BigInteger[] bigIntegerArr3;
        BigInteger bigInteger2;
        int iNextInt = i;
        while (true) {
            if (iNextInt >= 0 && iNextInt <= 65536) {
                break;
            }
            iNextInt = this.init_random.nextInt() / 32768;
        }
        int iNextInt2 = i2;
        while (true) {
            i4 = 1;
            if (iNextInt2 >= 0 && iNextInt2 <= 65536 && iNextInt2 / 2 != 0) {
                break;
            }
            iNextInt2 = (this.init_random.nextInt() / 32768) + 1;
        }
        BigInteger bigInteger3 = new BigInteger(Integer.toString(iNextInt2));
        BigInteger bigInteger4 = new BigInteger("19381");
        BigInteger bigInteger5 = new BigInteger(Integer.toString(iNextInt));
        int i5 = 0;
        BigInteger[] bigIntegerArr4 = {bigInteger5};
        int[] iArr = {i3};
        int i6 = 0;
        int i7 = 0;
        while (iArr[i6] >= 17) {
            int length = iArr.length + 1;
            int[] iArr2 = new int[length];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            iArr = new int[length];
            System.arraycopy(iArr2, 0, iArr, 0, length);
            i7 = i6 + 1;
            iArr[i7] = iArr[i6] / 2;
            i6 = i7;
        }
        BigInteger[] bigIntegerArr5 = new BigInteger[i7 + 1];
        int i8 = 16;
        bigIntegerArr5[i7] = new BigInteger("8003", 16);
        int i9 = i7 - 1;
        int i10 = 0;
        while (true) {
            if (i10 >= i7) {
                bigInteger = bigIntegerArr4[i5];
                break;
            }
            int i11 = iArr[i9] / i8;
            while (true) {
                int length2 = bigIntegerArr4.length;
                BigInteger[] bigIntegerArr6 = new BigInteger[length2];
                System.arraycopy(bigIntegerArr4, i5, bigIntegerArr6, i5, bigIntegerArr4.length);
                bigIntegerArr2 = new BigInteger[i11 + 1];
                System.arraycopy(bigIntegerArr6, i5, bigIntegerArr2, i5, length2);
                int i12 = i5;
                while (i12 < i11) {
                    int i13 = i12 + 1;
                    bigIntegerArr2[i13] = bigIntegerArr2[i12].multiply(bigInteger4).add(bigInteger3).mod(TWO.pow(i8));
                    i12 = i13;
                }
                BigInteger bigInteger6 = new BigInteger("0");
                for (int i14 = i5; i14 < i11; i14++) {
                    bigInteger6 = bigInteger6.add(bigIntegerArr2[i14].multiply(TWO.pow(i14 * 16)));
                }
                bigIntegerArr2[i5] = bigIntegerArr2[i11];
                int i15 = i9 + 1;
                BigInteger bigIntegerAdd = TWO.pow(iArr[i9] - i4).divide(bigIntegerArr5[i15]).add(TWO.pow(iArr[i9] - i4).multiply(bigInteger6).divide(bigIntegerArr5[i15].multiply(TWO.pow(i11 * 16))));
                if (bigIntegerAdd.mod(TWO).compareTo(ONE) == 0) {
                    bigIntegerAdd = bigIntegerAdd.add(ONE);
                }
                int i16 = 0;
                while (true) {
                    bigIntegerArr3 = bigIntegerArr5;
                    long j = i16;
                    bigIntegerArr3[i9] = bigIntegerArr5[i15].multiply(bigIntegerAdd.add(BigInteger.valueOf(j))).add(ONE);
                    bigInteger2 = bigInteger3;
                    if (bigIntegerArr3[i9].compareTo(TWO.pow(iArr[i9])) == 1) {
                        break;
                    }
                    if (TWO.modPow(bigIntegerArr3[i15].multiply(bigIntegerAdd.add(BigInteger.valueOf(j))), bigIntegerArr3[i9]).compareTo(ONE) == 0 && TWO.modPow(bigIntegerAdd.add(BigInteger.valueOf(j)), bigIntegerArr3[i9]).compareTo(ONE) != 0) {
                        break;
                    }
                    i16 += 2;
                    bigIntegerArr5 = bigIntegerArr3;
                    bigInteger3 = bigInteger2;
                }
                i4 = 1;
                bigIntegerArr5 = bigIntegerArr3;
                bigIntegerArr4 = bigIntegerArr2;
                bigInteger3 = bigInteger2;
                i5 = 0;
                i8 = 16;
            }
            i9--;
            if (i9 < 0) {
                bigIntegerArr[0] = bigIntegerArr3[0];
                bigIntegerArr[1] = bigIntegerArr3[1];
                bigInteger = bigIntegerArr2[0];
                break;
            }
            i10++;
            bigIntegerArr5 = bigIntegerArr3;
            bigIntegerArr4 = bigIntegerArr2;
            bigInteger3 = bigInteger2;
            i5 = 0;
            i4 = 1;
            i8 = 16;
        }
        return bigInteger.intValue();
    }

    private long procedure_Aa(long j, long j2, BigInteger[] bigIntegerArr, int i) {
        int i2;
        BigInteger bigInteger;
        BigInteger[] bigIntegerArr2;
        BigInteger bigInteger2;
        long jNextInt = j;
        while (true) {
            if (jNextInt >= 0 && jNextInt <= 4294967296L) {
                break;
            }
            jNextInt = this.init_random.nextInt() * 2;
        }
        long jNextInt2 = j2;
        while (true) {
            i2 = 1;
            if (jNextInt2 >= 0 && jNextInt2 <= 4294967296L && jNextInt2 / 2 != 0) {
                break;
            }
            jNextInt2 = (this.init_random.nextInt() * 2) + 1;
        }
        BigInteger bigInteger3 = new BigInteger(Long.toString(jNextInt2));
        BigInteger bigInteger4 = new BigInteger("97781173");
        BigInteger bigInteger5 = new BigInteger(Long.toString(jNextInt));
        int i3 = 0;
        BigInteger[] bigIntegerArr3 = {bigInteger5};
        int[] iArr = {i};
        int i4 = 0;
        int i5 = 0;
        while (iArr[i4] >= 33) {
            int length = iArr.length + 1;
            int[] iArr2 = new int[length];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            iArr = new int[length];
            System.arraycopy(iArr2, 0, iArr, 0, length);
            i5 = i4 + 1;
            iArr[i5] = iArr[i4] / 2;
            i4 = i5;
        }
        BigInteger[] bigIntegerArr4 = new BigInteger[i5 + 1];
        bigIntegerArr4[i5] = new BigInteger("8000000B", 16);
        int i6 = i5 - 1;
        int i7 = 0;
        while (true) {
            if (i7 >= i5) {
                bigInteger = bigIntegerArr3[i3];
                break;
            }
            int i8 = 32;
            int i9 = iArr[i6] / 32;
            while (true) {
                int length2 = bigIntegerArr3.length;
                BigInteger[] bigIntegerArr5 = new BigInteger[length2];
                System.arraycopy(bigIntegerArr3, i3, bigIntegerArr5, i3, bigIntegerArr3.length);
                bigIntegerArr2 = new BigInteger[i9 + 1];
                System.arraycopy(bigIntegerArr5, i3, bigIntegerArr2, i3, length2);
                int i10 = i3;
                while (i10 < i9) {
                    int i11 = i10 + 1;
                    bigIntegerArr2[i11] = bigIntegerArr2[i10].multiply(bigInteger4).add(bigInteger3).mod(TWO.pow(i8));
                    i10 = i11;
                }
                BigInteger bigInteger6 = new BigInteger("0");
                for (int i12 = i3; i12 < i9; i12++) {
                    bigInteger6 = bigInteger6.add(bigIntegerArr2[i12].multiply(TWO.pow(i12 * 32)));
                }
                bigIntegerArr2[i3] = bigIntegerArr2[i9];
                int i13 = i6 + 1;
                BigInteger bigIntegerAdd = TWO.pow(iArr[i6] - i2).divide(bigIntegerArr4[i13]).add(TWO.pow(iArr[i6] - i2).multiply(bigInteger6).divide(bigIntegerArr4[i13].multiply(TWO.pow(i9 * 32))));
                if (bigIntegerAdd.mod(TWO).compareTo(ONE) == 0) {
                    bigIntegerAdd = bigIntegerAdd.add(ONE);
                }
                int i14 = 0;
                while (true) {
                    long j3 = i14;
                    bigIntegerArr4[i6] = bigIntegerArr4[i13].multiply(bigIntegerAdd.add(BigInteger.valueOf(j3))).add(ONE);
                    bigInteger2 = bigInteger3;
                    if (bigIntegerArr4[i6].compareTo(TWO.pow(iArr[i6])) == 1) {
                        break;
                    }
                    if (TWO.modPow(bigIntegerArr4[i13].multiply(bigIntegerAdd.add(BigInteger.valueOf(j3))), bigIntegerArr4[i6]).compareTo(ONE) == 0 && TWO.modPow(bigIntegerAdd.add(BigInteger.valueOf(j3)), bigIntegerArr4[i6]).compareTo(ONE) != 0) {
                        break;
                    }
                    i14 += 2;
                    bigInteger3 = bigInteger2;
                }
                bigInteger3 = bigInteger2;
                i2 = 1;
                bigIntegerArr3 = bigIntegerArr2;
                i3 = 0;
                i8 = 32;
            }
            i6--;
            if (i6 < 0) {
                bigIntegerArr[0] = bigIntegerArr4[0];
                bigIntegerArr[1] = bigIntegerArr4[1];
                bigInteger = bigIntegerArr2[0];
                break;
            }
            i7++;
            bigInteger3 = bigInteger2;
            bigIntegerArr3 = bigIntegerArr2;
            i3 = 0;
            i2 = 1;
        }
        return bigInteger.longValue();
    }

    private void procedure_B(int i, int i2, BigInteger[] bigIntegerArr) {
        int iNextInt = i;
        while (true) {
            if (iNextInt >= 0 && iNextInt <= 65536) {
                break;
            } else {
                iNextInt = this.init_random.nextInt() / 32768;
            }
        }
        int iNextInt2 = i2;
        while (true) {
            if (iNextInt2 >= 0 && iNextInt2 <= 65536 && iNextInt2 / 2 != 0) {
                break;
            } else {
                iNextInt2 = (this.init_random.nextInt() / 32768) + 1;
            }
        }
        BigInteger[] bigIntegerArr2 = new BigInteger[2];
        BigInteger bigInteger = new BigInteger(Integer.toString(iNextInt2));
        BigInteger bigInteger2 = new BigInteger("19381");
        int iProcedure_A = procedure_A(iNextInt, iNextInt2, bigIntegerArr2, 256);
        BigInteger bigInteger3 = bigIntegerArr2[0];
        int iProcedure_A2 = procedure_A(iProcedure_A, iNextInt2, bigIntegerArr2, 512);
        BigInteger bigInteger4 = bigIntegerArr2[0];
        BigInteger[] bigIntegerArr3 = new BigInteger[65];
        bigIntegerArr3[0] = new BigInteger(Integer.toString(iProcedure_A2));
        while (true) {
            int i3 = 0;
            while (i3 < 64) {
                int i4 = i3 + 1;
                bigIntegerArr3[i4] = bigIntegerArr3[i3].multiply(bigInteger2).add(bigInteger).mod(TWO.pow(16));
                i3 = i4;
            }
            BigInteger bigInteger5 = new BigInteger("0");
            for (int i5 = 0; i5 < 64; i5++) {
                bigInteger5 = bigInteger5.add(bigIntegerArr3[i5].multiply(TWO.pow(i5 * 16)));
            }
            bigIntegerArr3[0] = bigIntegerArr3[64];
            BigInteger bigIntegerAdd = TWO.pow(1023).divide(bigInteger3.multiply(bigInteger4)).add(TWO.pow(1023).multiply(bigInteger5).divide(bigInteger3.multiply(bigInteger4).multiply(TWO.pow(1024))));
            if (bigIntegerAdd.mod(TWO).compareTo(ONE) == 0) {
                bigIntegerAdd = bigIntegerAdd.add(ONE);
            }
            BigInteger bigInteger6 = bigIntegerAdd;
            int i6 = 0;
            while (true) {
                long j = i6;
                BigInteger bigIntegerAdd2 = bigInteger3.multiply(bigInteger4).multiply(bigInteger6.add(BigInteger.valueOf(j))).add(ONE);
                if (bigIntegerAdd2.compareTo(TWO.pow(1024)) == 1) {
                    break;
                }
                if (TWO.modPow(bigInteger3.multiply(bigInteger4).multiply(bigInteger6.add(BigInteger.valueOf(j))), bigIntegerAdd2).compareTo(ONE) == 0 && TWO.modPow(bigInteger3.multiply(bigInteger6.add(BigInteger.valueOf(j))), bigIntegerAdd2).compareTo(ONE) != 0) {
                    bigIntegerArr[0] = bigIntegerAdd2;
                    bigIntegerArr[1] = bigInteger3;
                    return;
                }
                i6 += 2;
            }
        }
    }

    private void procedure_Bb(long j, long j2, BigInteger[] bigIntegerArr) {
        long jNextInt = j;
        while (true) {
            if (jNextInt >= 0 && jNextInt <= 4294967296L) {
                break;
            } else {
                jNextInt = this.init_random.nextInt() * 2;
            }
        }
        long jNextInt2 = j2;
        while (true) {
            if (jNextInt2 >= 0 && jNextInt2 <= 4294967296L && jNextInt2 / 2 != 0) {
                break;
            } else {
                jNextInt2 = (this.init_random.nextInt() * 2) + 1;
            }
        }
        BigInteger[] bigIntegerArr2 = new BigInteger[2];
        BigInteger bigInteger = new BigInteger(Long.toString(jNextInt2));
        BigInteger bigInteger2 = new BigInteger("97781173");
        long j3 = jNextInt2;
        long jProcedure_Aa = procedure_Aa(jNextInt, j3, bigIntegerArr2, 256);
        BigInteger bigInteger3 = bigIntegerArr2[0];
        long jProcedure_Aa2 = procedure_Aa(jProcedure_Aa, j3, bigIntegerArr2, 512);
        BigInteger bigInteger4 = bigIntegerArr2[0];
        BigInteger[] bigIntegerArr3 = new BigInteger[33];
        bigIntegerArr3[0] = new BigInteger(Long.toString(jProcedure_Aa2));
        while (true) {
            int i = 0;
            while (i < 32) {
                int i2 = i + 1;
                bigIntegerArr3[i2] = bigIntegerArr3[i].multiply(bigInteger2).add(bigInteger).mod(TWO.pow(32));
                i = i2;
            }
            BigInteger bigInteger5 = new BigInteger("0");
            for (int i3 = 0; i3 < 32; i3++) {
                bigInteger5 = bigInteger5.add(bigIntegerArr3[i3].multiply(TWO.pow(i3 * 32)));
            }
            bigIntegerArr3[0] = bigIntegerArr3[32];
            BigInteger bigIntegerAdd = TWO.pow(1023).divide(bigInteger3.multiply(bigInteger4)).add(TWO.pow(1023).multiply(bigInteger5).divide(bigInteger3.multiply(bigInteger4).multiply(TWO.pow(1024))));
            if (bigIntegerAdd.mod(TWO).compareTo(ONE) == 0) {
                bigIntegerAdd = bigIntegerAdd.add(ONE);
            }
            int i4 = 0;
            while (true) {
                long j4 = i4;
                BigInteger bigIntegerAdd2 = bigInteger3.multiply(bigInteger4).multiply(bigIntegerAdd.add(BigInteger.valueOf(j4))).add(ONE);
                if (bigIntegerAdd2.compareTo(TWO.pow(1024)) == 1) {
                    break;
                }
                if (TWO.modPow(bigInteger3.multiply(bigInteger4).multiply(bigIntegerAdd.add(BigInteger.valueOf(j4))), bigIntegerAdd2).compareTo(ONE) == 0 && TWO.modPow(bigInteger3.multiply(bigIntegerAdd.add(BigInteger.valueOf(j4))), bigIntegerAdd2).compareTo(ONE) != 0) {
                    bigIntegerArr[0] = bigIntegerAdd2;
                    bigIntegerArr[1] = bigInteger3;
                    return;
                }
                i4 += 2;
            }
        }
    }

    private BigInteger procedure_C(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigIntegerSubtract = bigInteger.subtract(ONE);
        BigInteger bigIntegerDivide = bigIntegerSubtract.divide(bigInteger2);
        int iBitLength = bigInteger.bitLength();
        while (true) {
            BigInteger bigInteger3 = new BigInteger(iBitLength, this.init_random);
            if (bigInteger3.compareTo(ONE) > 0 && bigInteger3.compareTo(bigIntegerSubtract) < 0) {
                BigInteger bigIntegerModPow = bigInteger3.modPow(bigIntegerDivide, bigInteger);
                if (bigIntegerModPow.compareTo(ONE) != 0) {
                    return bigIntegerModPow;
                }
            }
        }
    }

    public GOST3410Parameters generateParameters() {
        BigInteger[] bigIntegerArr = new BigInteger[2];
        if (this.typeproc == 1) {
            int iNextInt = this.init_random.nextInt();
            int iNextInt2 = this.init_random.nextInt();
            int i = this.size;
            if (i == 512) {
                procedure_A(iNextInt, iNextInt2, bigIntegerArr, 512);
            } else {
                if (i != 1024) {
                    throw new IllegalArgumentException("Ooops! key size 512 or 1024 bit.");
                }
                procedure_B(iNextInt, iNextInt2, bigIntegerArr);
            }
            BigInteger bigInteger = bigIntegerArr[0];
            BigInteger bigInteger2 = bigIntegerArr[1];
            return new GOST3410Parameters(bigInteger, bigInteger2, procedure_C(bigInteger, bigInteger2), new GOST3410ValidationParameters(iNextInt, iNextInt2));
        }
        long jNextLong = this.init_random.nextLong();
        long jNextLong2 = this.init_random.nextLong();
        int i2 = this.size;
        if (i2 == 512) {
            procedure_Aa(jNextLong, jNextLong2, bigIntegerArr, 512);
        } else {
            if (i2 != 1024) {
                throw new IllegalStateException("Ooops! key size 512 or 1024 bit.");
            }
            procedure_Bb(jNextLong, jNextLong2, bigIntegerArr);
        }
        BigInteger bigInteger3 = bigIntegerArr[0];
        BigInteger bigInteger4 = bigIntegerArr[1];
        return new GOST3410Parameters(bigInteger3, bigInteger4, procedure_C(bigInteger3, bigInteger4), new GOST3410ValidationParameters(jNextLong, jNextLong2));
    }

    public void init(int i, int i2, SecureRandom secureRandom) {
        this.size = i;
        this.typeproc = i2;
        this.init_random = secureRandom;
    }
}
