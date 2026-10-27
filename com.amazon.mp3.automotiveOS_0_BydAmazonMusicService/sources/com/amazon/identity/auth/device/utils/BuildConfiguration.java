package com.amazon.identity.auth.device.utils;

import com.amazon.identity.auth.device.framework.InvalidEnumValueException;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'UserDebug' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class BuildConfiguration {
    private static final /* synthetic */ BuildConfiguration[] $VALUES;
    public static final BuildConfiguration Eng;
    public static final BuildConfiguration ReleaseDebug;
    public static final BuildConfiguration User;
    public static final BuildConfiguration UserDebug;
    private final KeyType mSignedWith;

    static {
        BuildConfiguration buildConfiguration = new BuildConfiguration("Eng", 0, KeyType.Debug);
        Eng = buildConfiguration;
        KeyType keyType = KeyType.ReleaseDebug;
        BuildConfiguration buildConfiguration2 = new BuildConfiguration("UserDebug", 1, keyType);
        UserDebug = buildConfiguration2;
        BuildConfiguration buildConfiguration3 = new BuildConfiguration("User", 2, KeyType.Release);
        User = buildConfiguration3;
        BuildConfiguration buildConfiguration4 = new BuildConfiguration("ReleaseDebug", 3, keyType);
        ReleaseDebug = buildConfiguration4;
        $VALUES = new BuildConfiguration[]{buildConfiguration, buildConfiguration2, buildConfiguration3, buildConfiguration4};
    }

    private BuildConfiguration(String str, int i, KeyType keyType) {
        this.mSignedWith = keyType;
    }

    public static BuildConfiguration fromString(String str) throws InvalidEnumValueException {
        if (str == null) {
            throw new InvalidEnumValueException("Null is not a valid BuildType");
        }
        BuildConfiguration[] buildConfigurationArrValues = values();
        for (int i = 0; i < 4; i++) {
            BuildConfiguration buildConfiguration = buildConfigurationArrValues[i];
            if (buildConfiguration.toString().equalsIgnoreCase(str)) {
                return buildConfiguration;
            }
        }
        throw new InvalidEnumValueException("Unknown build type: " + str);
    }

    public static BuildConfiguration valueOf(String str) {
        return (BuildConfiguration) Enum.valueOf(BuildConfiguration.class, str);
    }

    public static BuildConfiguration[] values() {
        return (BuildConfiguration[]) $VALUES.clone();
    }

    public boolean isSignedWith(KeyType keyType) {
        return this.mSignedWith == keyType;
    }
}
