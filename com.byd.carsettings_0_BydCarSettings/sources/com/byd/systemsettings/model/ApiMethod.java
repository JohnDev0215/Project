package com.byd.systemsettings.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ApiMethod {
    public String methodName;
    public Object obj;
    public Object[] paramArr;
    public Class<?>[] paramClassArr;

    public ApiMethod(Object obj, String str, Object[] objArr) {
        this.obj = obj;
        this.methodName = str;
        if (objArr == null || objArr.length <= 0) {
            return;
        }
        this.paramArr = objArr;
        this.paramClassArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            this.paramClassArr[i] = objArr[i].getClass();
        }
    }

    public String toString() {
        return "ApiMethod{obj=" + this.obj + ", methodName='" + this.methodName + "', paramClassArr=" + Arrays.toString(this.paramClassArr) + ", paramArr=" + Arrays.toString(this.paramArr) + '}';
    }
}
