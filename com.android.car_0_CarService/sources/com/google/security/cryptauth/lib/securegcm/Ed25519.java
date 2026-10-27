package com.google.security.cryptauth.lib.securegcm;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
public class Ed25519 {
    private static final int HEX_RADIX = 16;
    private static final int POINT_SIZE_BITS = 256;
    private static final int T = 3;
    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;
    private static final BigInteger Ed25519_P = new BigInteger("7FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFED", 16);
    private static final BigInteger Ed25519_D = new BigInteger("52036CEE2B6FFE738CC740797779E89800700A4D4141D8AB75EB4DCA135978A3", 16);
    private static final BigInteger Ed25519_K = new BigInteger("2406D9DC56DFFCE7198E80F2EEF3D13000E0149A8283B156EBD69B9426B2F159", 16);
    static final BigInteger[] IDENTITY_POINT = {BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO};

    private Ed25519() {
    }

    public static BigInteger[] scalarMultiplyAffinePoint(BigInteger[] p, BigInteger k) throws Ed25519Exception {
        return toAffine(scalarMultiplyExtendedPoint(toExtended(p), k));
    }

    public static BigInteger[] addAffinePoints(BigInteger[] p1, BigInteger[] p2) throws Ed25519Exception {
        return toAffine(addExtendedPoints(toExtended(p1), toExtended(p2)));
    }

    public static BigInteger[] subtractAffinePoints(BigInteger[] p1, BigInteger[] p2) throws Ed25519Exception {
        return toAffine(subtractExtendedPoints(toExtended(p1), toExtended(p2)));
    }

    public static void validateAffinePoint(BigInteger[] p) throws Ed25519Exception {
        checkPointIsInAffineRepresentation(p);
        BigInteger x = p[0];
        BigInteger y = p[1];
        if (x.signum() != 1 || y.signum() != 1) {
            throw new Ed25519Exception("Point encoding must use only positive integers");
        }
        if (x.compareTo(Ed25519_P) >= 0 || y.compareTo(Ed25519_P) >= 0) {
            throw new Ed25519Exception("Point lies outside of the expected field");
        }
        BigInteger xx = x.multiply(x);
        BigInteger yy = y.multiply(y);
        BigInteger lhs = xx.negate().add(yy).mod(Ed25519_P);
        BigInteger rhs = BigInteger.ONE.add(Ed25519_D.multiply(xx).multiply(yy)).mod(Ed25519_P);
        if (!lhs.equals(rhs)) {
            throw new Ed25519Exception("Point does not lie on the expected curve");
        }
    }

    static BigInteger[] scalarMultiplyExtendedPoint(BigInteger[] p, BigInteger k) throws Ed25519Exception {
        checkPointIsInExtendedRepresentation(p);
        if (k == null) {
            throw new Ed25519Exception("Can't multiply point by null");
        }
        if (k.bitLength() > 256) {
            throw new Ed25519Exception("Refuse to multiply point by scalar with more than 256 bits");
        }
        BigInteger[] q = IDENTITY_POINT;
        BigInteger[] r = IDENTITY_POINT;
        BigInteger[] doubleAccumulator = p;
        for (int i = 0; i < 256; i++) {
            if (k.testBit(i)) {
                q = addExtendedPoints(q, doubleAccumulator);
            } else {
                r = addExtendedPoints(q, doubleAccumulator);
            }
            if (i < 255) {
                doubleAccumulator = doubleExtendedPoint(doubleAccumulator);
            }
        }
        return addExtendedPoints(q, subtractExtendedPoints(r, r));
    }

    private static BigInteger[] doubleExtendedPoint(BigInteger[] p) throws Ed25519Exception {
        checkPointIsInExtendedRepresentation(p);
        BigInteger c = p[3].pow(2).multiply(Ed25519_K);
        BigInteger d = p[2].pow(2).shiftLeft(1);
        BigInteger e = p[1].multiply(p[0]).shiftLeft(2);
        BigInteger f = d.subtract(c);
        BigInteger g = d.add(c);
        BigInteger h = p[1].pow(2).add(p[0].pow(2)).shiftLeft(1);
        return new BigInteger[]{e.multiply(f).mod(Ed25519_P), g.multiply(h).mod(Ed25519_P), f.multiply(g).mod(Ed25519_P), e.multiply(h).mod(Ed25519_P)};
    }

    static BigInteger[] subtractExtendedPoints(BigInteger[] p1, BigInteger[] p2) throws Ed25519Exception {
        checkPointIsInExtendedRepresentation(p1);
        checkPointIsInExtendedRepresentation(p2);
        return addExtendedPoints(p1, new BigInteger[]{p2[0].negate(), p2[1], p2[2], p2[3].negate()});
    }

    static BigInteger[] addExtendedPoints(BigInteger[] p1, BigInteger[] p2) throws Ed25519Exception {
        checkPointIsInExtendedRepresentation(p1);
        checkPointIsInExtendedRepresentation(p2);
        BigInteger a = p1[1].subtract(p1[0]).multiply(p2[1].subtract(p2[0]));
        BigInteger b = p1[1].add(p1[0]).multiply(p2[1].add(p2[0]));
        BigInteger c = p1[3].multiply(Ed25519_K).multiply(p2[3]);
        BigInteger d = p1[2].add(p1[2]).multiply(p2[2]);
        BigInteger e = b.subtract(a);
        BigInteger f = d.subtract(c);
        BigInteger g = d.add(c);
        BigInteger h = b.add(a);
        return new BigInteger[]{e.multiply(f).mod(Ed25519_P), g.multiply(h).mod(Ed25519_P), f.multiply(g).mod(Ed25519_P), e.multiply(h).mod(Ed25519_P)};
    }

    static BigInteger[] toExtended(BigInteger[] p) throws Ed25519Exception {
        checkPointIsInAffineRepresentation(p);
        return new BigInteger[]{p[0], p[1], BigInteger.ONE, p[0].multiply(p[1]).mod(Ed25519_P)};
    }

    static BigInteger[] toAffine(BigInteger[] p) throws Ed25519Exception {
        checkPointIsInExtendedRepresentation(p);
        return new BigInteger[]{p[0].multiply(p[2].modInverse(Ed25519_P)).mod(Ed25519_P), p[1].multiply(p[2].modInverse(Ed25519_P)).mod(Ed25519_P)};
    }

    static void checkPointIsInExtendedRepresentation(BigInteger[] p) throws Ed25519Exception {
        if (p == null || p.length != 4 || p[0] == null || p[1] == null || p[2] == null || p[3] == null) {
            throw new Ed25519Exception("Point is not in extended representation");
        }
    }

    static void checkPointIsInAffineRepresentation(BigInteger[] p) throws Ed25519Exception {
        if (p == null || p.length != 2 || p[0] == null || p[1] == null) {
            throw new Ed25519Exception("Point is not in affine representation");
        }
    }

    public static class Ed25519Exception extends Exception {
        public Ed25519Exception(String message) {
            super(message);
        }

        public Ed25519Exception(Exception e) {
            super(e);
        }

        public Ed25519Exception(String message, Exception e) {
            super(message, e);
        }
    }
}
