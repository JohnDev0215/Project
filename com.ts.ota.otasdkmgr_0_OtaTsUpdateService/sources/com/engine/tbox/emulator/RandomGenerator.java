package com.engine.tbox.emulator;

import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class RandomGenerator {
    private static final int CHAR_START = 65;
    private static final int MAX_CHAR_SIZE = 26;
    private static final int MAX_NUM_SIZE = 10;
    private static final int NUM_START = 48;
    private static final int SHORT_MAX = 255;
    private static Random random = new Random();

    public static void generatorChar(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (random.nextInt(26) + CHAR_START);
        }
    }

    public static void generatorNumberString(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (random.nextInt(10) + NUM_START);
        }
    }

    public static void generatorNumber(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) random.nextInt(255);
        }
    }
}
