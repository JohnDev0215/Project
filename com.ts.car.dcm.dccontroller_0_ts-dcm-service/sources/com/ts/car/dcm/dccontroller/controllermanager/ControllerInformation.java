package com.ts.car.dcm.dccontroller.controllermanager;

/* JADX INFO: loaded from: classes.dex */
public class ControllerInformation {
    public static final int CLASS_INDEX = 1;
    public static final int ENABLE_INDEX = 2;
    public static final int NAME_INDEX = 0;
    public static final int VERSION_INDEX = 3;
    private String mClassName;
    private boolean mEnable;
    private String mJarName;
    private boolean mNeedUpdated;
    private String mVersion;

    public String getJarName() {
        return this.mJarName;
    }

    public String getClassName() {
        return this.mClassName;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public boolean isNeedUpdated() {
        return this.mNeedUpdated;
    }

    public ControllerInformation(String jarName, String className, boolean enable, String version, boolean updated) {
        this.mJarName = jarName;
        this.mClassName = className;
        this.mEnable = enable;
        this.mVersion = version;
        this.mNeedUpdated = updated;
    }

    public boolean isAvailable() {
        String str;
        String str2;
        return (!this.mEnable || (str = this.mClassName) == null || str.isEmpty() || (str2 = this.mJarName) == null || str2.isEmpty()) ? false : true;
    }

    public String toString() {
        return "ConnControllerInformation{mJarName='" + this.mJarName + "', mClassName='" + this.mClassName + "', mEnable=" + this.mEnable + '}';
    }
}
