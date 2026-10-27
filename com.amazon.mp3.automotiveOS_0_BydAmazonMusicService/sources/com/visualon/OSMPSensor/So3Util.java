package com.visualon.OSMPSensor;

/* JADX INFO: loaded from: classes7.dex */
public class So3Util {
    private static final double M_SQRT1_2 = 0.7071067811865476d;
    private static final double ONE_20TH = 0.1666666716337204d;
    private static final double ONE_6TH = 0.1666666716337204d;
    private static Vector3d temp31 = new Vector3d();
    private static Vector3d sO3FromTwoVecN = new Vector3d();
    private static Vector3d sO3FromTwoVecA = new Vector3d();
    private static Vector3d sO3FromTwoVecB = new Vector3d();
    private static Vector3d sO3FromTwoVecRotationAxis = new Vector3d();
    private static Matrix3x3d sO3FromTwoVec33R1 = new Matrix3x3d();
    private static Matrix3x3d sO3FromTwoVec33R2 = new Matrix3x3d();
    private static Vector3d muFromSO3R2 = new Vector3d();
    private static Vector3d rotationPiAboutAxisTemp = new Vector3d();

    public static void sO3FromTwoVec(Vector3d vector3d, Vector3d vector3d2, Matrix3x3d matrix3x3d) {
        Vector3d.cross(vector3d, vector3d2, sO3FromTwoVecN);
        if (sO3FromTwoVecN.length() == 0.0d) {
            if (Vector3d.dot(vector3d, vector3d2) >= 0.0d) {
                matrix3x3d.setIdentity();
                return;
            } else {
                Vector3d.ortho(vector3d, sO3FromTwoVecRotationAxis);
                rotationPiAboutAxis(sO3FromTwoVecRotationAxis, matrix3x3d);
                return;
            }
        }
        sO3FromTwoVecA.set(vector3d);
        sO3FromTwoVecB.set(vector3d2);
        sO3FromTwoVecN.normalize();
        sO3FromTwoVecA.normalize();
        sO3FromTwoVecB.normalize();
        Matrix3x3d matrix3x3d2 = sO3FromTwoVec33R1;
        matrix3x3d2.setColumn(0, sO3FromTwoVecA);
        matrix3x3d2.setColumn(1, sO3FromTwoVecN);
        Vector3d.cross(sO3FromTwoVecN, sO3FromTwoVecA, temp31);
        matrix3x3d2.setColumn(2, temp31);
        Matrix3x3d matrix3x3d3 = sO3FromTwoVec33R2;
        matrix3x3d3.setColumn(0, sO3FromTwoVecB);
        matrix3x3d3.setColumn(1, sO3FromTwoVecN);
        Vector3d.cross(sO3FromTwoVecN, sO3FromTwoVecB, temp31);
        matrix3x3d3.setColumn(2, temp31);
        matrix3x3d2.transpose();
        Matrix3x3d.mult(matrix3x3d3, matrix3x3d2, matrix3x3d);
    }

    private static void rotationPiAboutAxis(Vector3d vector3d, Matrix3x3d matrix3x3d) {
        rotationPiAboutAxisTemp.set(vector3d);
        Vector3d vector3d2 = rotationPiAboutAxisTemp;
        vector3d2.scale(3.141592653589793d / vector3d2.length());
        rodriguesSo3Exp(rotationPiAboutAxisTemp, 0.0d, 0.20264236728467558d, matrix3x3d);
    }

    public static void sO3FromMu(Vector3d vector3d, Matrix3x3d matrix3x3d) {
        double dCos;
        double d;
        double d2;
        double dDot = Vector3d.dot(vector3d, vector3d);
        double dSqrt = Math.sqrt(dDot);
        double d3 = 0.5d;
        if (dDot < 1.0E-8d) {
            d2 = dDot * 0.1666666716337204d;
        } else {
            if (dDot >= 1.0E-6d) {
                double d4 = 1.0d / dSqrt;
                double dSin = Math.sin(dSqrt) * d4;
                dCos = d4 * d4 * (1.0d - Math.cos(dSqrt));
                d = dSin;
                rodriguesSo3Exp(vector3d, d, dCos, matrix3x3d);
            }
            d3 = 0.5d - (0.0416666679084301d * dDot);
            double d5 = dDot * 0.1666666716337204d;
            d2 = d5 * (1.0d - d5);
        }
        d = 1.0d - d2;
        dCos = d3;
        rodriguesSo3Exp(vector3d, d, dCos, matrix3x3d);
    }

    public static void muFromSO3(Matrix3x3d matrix3x3d, Vector3d vector3d) {
        double d = (((matrix3x3d.get(0, 0) + matrix3x3d.get(1, 1)) + matrix3x3d.get(2, 2)) - 1.0d) * 0.5d;
        vector3d.set((matrix3x3d.get(2, 1) - matrix3x3d.get(1, 2)) / 2.0d, (matrix3x3d.get(0, 2) - matrix3x3d.get(2, 0)) / 2.0d, (matrix3x3d.get(1, 0) - matrix3x3d.get(0, 1)) / 2.0d);
        double length = vector3d.length();
        if (d > M_SQRT1_2) {
            if (length > 0.0d) {
                vector3d.scale(Math.asin(length) / length);
                return;
            }
            return;
        }
        if (d > -0.7071067811865476d) {
            vector3d.scale(Math.acos(d) / length);
            return;
        }
        double dAsin = 3.141592653589793d - Math.asin(length);
        double d2 = matrix3x3d.get(0, 0) - d;
        double d3 = matrix3x3d.get(1, 1) - d;
        double d4 = matrix3x3d.get(2, 2) - d;
        Vector3d vector3d2 = muFromSO3R2;
        double d5 = d2 * d2;
        double d6 = d3 * d3;
        if (d5 > d6 && d5 > d4 * d4) {
            vector3d2.set(d2, (matrix3x3d.get(1, 0) + matrix3x3d.get(0, 1)) / 2.0d, (matrix3x3d.get(0, 2) + matrix3x3d.get(2, 0)) / 2.0d);
        } else if (d6 > d4 * d4) {
            vector3d2.set((matrix3x3d.get(1, 0) + matrix3x3d.get(0, 1)) / 2.0d, d3, (matrix3x3d.get(2, 1) + matrix3x3d.get(1, 2)) / 2.0d);
        } else {
            vector3d2.set((matrix3x3d.get(0, 2) + matrix3x3d.get(2, 0)) / 2.0d, (matrix3x3d.get(2, 1) + matrix3x3d.get(1, 2)) / 2.0d, d4);
        }
        if (Vector3d.dot(vector3d2, vector3d) < 0.0d) {
            vector3d2.scale(-1.0d);
        }
        vector3d2.normalize();
        vector3d2.scale(dAsin);
        vector3d.set(vector3d2);
    }

    private static void rodriguesSo3Exp(Vector3d vector3d, double d, double d2, Matrix3x3d matrix3x3d) {
        double d3 = vector3d.x;
        double d4 = d3 * d3;
        double d5 = vector3d.y;
        double d6 = d5 * d5;
        double d7 = vector3d.z;
        double d8 = d7 * d7;
        matrix3x3d.set(0, 0, 1.0d - ((d6 + d8) * d2));
        matrix3x3d.set(1, 1, 1.0d - ((d8 + d4) * d2));
        matrix3x3d.set(2, 2, 1.0d - ((d4 + d6) * d2));
        double d9 = vector3d.z * d;
        double d10 = vector3d.x * vector3d.y * d2;
        matrix3x3d.set(0, 1, d10 - d9);
        matrix3x3d.set(1, 0, d10 + d9);
        double d11 = vector3d.y * d;
        double d12 = vector3d.x * vector3d.z * d2;
        matrix3x3d.set(0, 2, d12 + d11);
        matrix3x3d.set(2, 0, d12 - d11);
        double d13 = vector3d.x * d;
        double d14 = vector3d.y * vector3d.z * d2;
        matrix3x3d.set(1, 2, d14 - d13);
        matrix3x3d.set(2, 1, d14 + d13);
    }

    public static void generatorField(int i, Matrix3x3d matrix3x3d, Matrix3x3d matrix3x3d2) {
        matrix3x3d2.set(i, 0, 0.0d);
        int i2 = (i + 1) % 3;
        int i3 = (i + 2) % 3;
        matrix3x3d2.set(i2, 0, -matrix3x3d.get(i3, 0));
        matrix3x3d2.set(i3, 0, matrix3x3d.get(i2, 0));
    }
}
