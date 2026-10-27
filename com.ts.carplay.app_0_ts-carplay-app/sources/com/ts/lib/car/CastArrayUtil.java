package com.ts.lib.car;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class CastArrayUtil {
    private CastArrayUtil() {
    }

    public static byte[] toWrap(byte[] original) {
        int length = original.length;
        Byte[] dest = new Byte[length];
        for (int i = 0; i < length; i++) {
            dest[i] = Byte.valueOf(original[i]);
        }
        return original;
    }

    public static Byte[] toWrap(Byte[] original) {
        return original;
    }

    public static Float[] toWrap(float[] original) {
        int length = original.length;
        Float[] dest = new Float[length];
        for (int i = 0; i < length; i++) {
            dest[i] = Float.valueOf(original[i]);
        }
        return dest;
    }

    public static Float[] toWrap(Float[] original) {
        return original;
    }

    public static Double[] toWrap(double[] original) {
        int length = original.length;
        Double[] dest = new Double[length];
        for (int i = 0; i < length; i++) {
            dest[i] = Double.valueOf(original[i]);
        }
        return dest;
    }

    public static Double[] toWrap(Double[] original) {
        return original;
    }

    public static Boolean[] toWrap(boolean[] original) {
        int length = original.length;
        Boolean[] dest = new Boolean[length];
        for (int i = 0; i < length; i++) {
            dest[i] = Boolean.valueOf(original[i]);
        }
        return dest;
    }

    public static Boolean[] toWrap(Boolean[] original) {
        return original;
    }

    public static Long[] toWrap(long[] original) {
        int length = original.length;
        Long[] dest = new Long[length];
        for (int i = 0; i < length; i++) {
            dest[i] = Long.valueOf(original[i]);
        }
        return dest;
    }

    public static Long[] toWrap(Long[] original) {
        return original;
    }

    public static Character[] toWrap(char[] original) {
        int length = original.length;
        Character[] dest = new Character[length];
        for (int i = 0; i < length; i++) {
            dest[i] = Character.valueOf(original[i]);
        }
        return dest;
    }

    public static Character[] toWrap(Character[] original) {
        return original;
    }

    public static Integer[] toWrap(int[] original) {
        int length = original.length;
        Integer[] dest = new Integer[length];
        for (int i = 0; i < length; i++) {
            dest[i] = Integer.valueOf(original[i]);
        }
        return dest;
    }

    public static Integer[] toWrap(Integer[] original) {
        return original;
    }

    public static Short[] toWrap(short[] original) {
        int len = original.length;
        Short[] dest = new Short[len];
        for (int i = 0; i < len; i++) {
            dest[i] = Short.valueOf(original[i]);
        }
        return dest;
    }

    public static Short[] toWrap(Short[] original) {
        return original;
    }

    public static Byte toWrap(byte original) {
        return Byte.valueOf(original);
    }

    public static Byte toWrap(Byte original) {
        return original;
    }

    public static Float toWrap(float original) {
        return Float.valueOf(original);
    }

    public static Float toWrap(Float original) {
        return original;
    }

    public static Double toWrap(double original) {
        return Double.valueOf(original);
    }

    public static Double toWrap(Double original) {
        return original;
    }

    public static Boolean toWrap(boolean original) {
        return Boolean.valueOf(original);
    }

    public static Boolean toWrap(Boolean original) {
        return original;
    }

    public static Long toWrap(long original) {
        return Long.valueOf(original);
    }

    public static Long toWrap(Long original) {
        return original;
    }

    public static Character toWrap(char original) {
        return Character.valueOf(original);
    }

    public static Character toWrap(Character original) {
        return original;
    }

    public static Integer toWrap(int original) {
        return Integer.valueOf(original);
    }

    public static Integer toWrap(Integer original) {
        return original;
    }

    public static Short toWrap(short original) {
        return Short.valueOf(original);
    }

    public static Short toWrap(Short original) {
        return original;
    }

    public static String toWrap(String original) {
        return original;
    }

    public static byte[] toPrimitive(Byte[] original) {
        int length = original.length;
        byte[] dest = new byte[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i].byteValue();
        }
        return dest;
    }

    public static byte[] toPrimitive(byte[] original) {
        return original;
    }

    public static float[] toPrimitive(Float[] original) {
        int length = original.length;
        float[] dest = new float[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i].floatValue();
        }
        return dest;
    }

    public static float[] toPrimitive(float[] original) {
        return original;
    }

    public static double[] toPrimitive(Double[] original) {
        int length = original.length;
        double[] dest = new double[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i].doubleValue();
        }
        return dest;
    }

    public static double[] toPrimitive(double[] original) {
        return original;
    }

    public static boolean[] toPrimitive(Boolean[] original) {
        int length = original.length;
        boolean[] dest = new boolean[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i].booleanValue();
        }
        return dest;
    }

    public static boolean[] toPrimitive(boolean[] original) {
        return original;
    }

    public static long[] toPrimitive(Long[] original) {
        int length = original.length;
        long[] dest = new long[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i].longValue();
        }
        return dest;
    }

    public static long[] toPrimitive(long[] original) {
        return original;
    }

    public static char[] toPrimitive(Character[] original) {
        int length = original.length;
        char[] dest = new char[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i].charValue();
        }
        return dest;
    }

    public static char[] toPrimitive(char[] original) {
        return original;
    }

    public static int[] toPrimitive(Integer[] original) {
        int length = original.length;
        int[] dest = new int[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i].intValue();
        }
        return dest;
    }

    public static int[] toPrimitive(int[] original) {
        return original;
    }

    public static short[] toPrimitive(Short[] original) {
        int len = original.length;
        short[] dest = new short[len];
        for (int i = 0; i < len; i++) {
            dest[i] = original[i].shortValue();
        }
        return dest;
    }

    public static short[] toPrimitive(short[] original) {
        return original;
    }

    public static byte toPrimitive(Byte original) {
        return original.byteValue();
    }

    public static byte toPrimitive(byte original) {
        return original;
    }

    public static float toPrimitive(Float original) {
        return original.floatValue();
    }

    public static float toPrimitive(float original) {
        return original;
    }

    public static double toPrimitive(Double original) {
        return original.doubleValue();
    }

    public static double toPrimitive(double original) {
        return original;
    }

    public static boolean toPrimitive(Boolean original) {
        return original.booleanValue();
    }

    public static boolean toPrimitive(boolean original) {
        return original;
    }

    public static long toPrimitive(Long original) {
        return original.longValue();
    }

    public static long toPrimitive(long original) {
        return original;
    }

    public static char toPrimitive(Character original) {
        return original.charValue();
    }

    public static char toPrimitive(char original) {
        return original;
    }

    public static int toPrimitive(Integer original) {
        return original.intValue();
    }

    public static int toPrimitive(int original) {
        return original;
    }

    public static short toPrimitive(Short original) {
        return original.shortValue();
    }

    public static short toPrimitive(short original) {
        return original;
    }

    public static String toPrimitive(String original) {
        return original;
    }

    public static String parseString(int msg) {
        return msg + "";
    }

    public static String parseString(int[] msg) {
        if (msg == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i : msg) {
            result.append(i + ",");
        }
        return result.toString();
    }

    public static String parseString(boolean msg) {
        return msg + "";
    }

    public static String parseString(float msg) {
        return msg + "";
    }

    public static String parseString(float[] msg) {
        if (msg == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (float i : msg) {
            result.append(i + ",");
        }
        return result.toString();
    }

    public static String parseString(String msg) {
        return msg;
    }

    public static String parseString(double msg) {
        return msg + "";
    }

    public static String parseString(double[] msg) {
        if (msg == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (double i : msg) {
            result.append(i + ",");
        }
        return result.toString();
    }

    public static String parseString(byte[] msg) {
        if (msg == null) {
            return "";
        }
        StringBuilder data = new StringBuilder();
        for (byte i : msg) {
            data.append(((int) i) + ",");
        }
        return data.toString();
    }

    public static String parseString(Integer msg) {
        return msg + "";
    }

    public static String parseString(Integer[] msg) {
        if (msg == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (Integer i : msg) {
            result.append(i + ",");
        }
        return result.toString();
    }

    public static String parseString(Boolean msg) {
        return msg + "";
    }

    public static String parseString(Float msg) {
        return msg + "";
    }

    public static String parseString(Float[] msg) {
        if (msg == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (Float i : msg) {
            result.append(i + ",");
        }
        return result.toString();
    }

    public static String parseString(Double msg) {
        return msg + "";
    }

    public static String parseString(Double[] msg) {
        if (msg == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (Double i : msg) {
            result.append(i + ",");
        }
        return result.toString();
    }

    public static String parseString(Byte[] msg) {
        if (msg == null) {
            return "";
        }
        StringBuilder data = new StringBuilder();
        for (Byte b : msg) {
            byte i = b.byteValue();
            data.append(((int) i) + ",");
        }
        return data.toString();
    }

    public static Byte[] longToBytes(long primitive) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(primitive);
        byte[] bytes = buffer.array();
        Byte[] result = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = Byte.valueOf(bytes[i]);
        }
        return result;
    }
}
