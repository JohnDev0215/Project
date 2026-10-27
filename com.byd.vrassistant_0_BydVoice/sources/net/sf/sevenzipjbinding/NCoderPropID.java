package net.sf.sevenzipjbinding;

import cerence.ark.assistant.controller.enumeration.TemperatureUnitEnum;

/* JADX INFO: loaded from: classes3.dex */
public enum NCoderPropID {
    kBlockSize(Integer.TYPE, TemperatureUnitEnum.C),
    kDictionarySize(Integer.TYPE, "D"),
    kUsedMemorySize(Integer.TYPE, "MEM"),
    kOrder(Integer.TYPE, "O"),
    kPosStateBits(Integer.TYPE, "PB"),
    kLitContextBits(Integer.TYPE, "LC"),
    kLitPosBits(Integer.TYPE, "LP"),
    kEndMarker(Boolean.TYPE, "eos"),
    kNumPasses(Integer.TYPE, "Pass"),
    kNumFastBytes(Integer.TYPE, "fb"),
    kMatchFinderCycles(Integer.TYPE, "mc"),
    kAlgorithm(Integer.TYPE, "a"),
    kMatchFinder(String.class, "mf"),
    kNumThreads(Integer.TYPE, "mt"),
    kDefaultProp(Integer.TYPE, "");

    private final Class<?> clazz;
    private final String option;

    NCoderPropID(Class cls, String str) {
        this.clazz = cls;
        this.option = str;
    }
}
