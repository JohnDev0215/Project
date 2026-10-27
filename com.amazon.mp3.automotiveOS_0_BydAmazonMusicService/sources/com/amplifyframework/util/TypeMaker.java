package com.amplifyframework.util;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class TypeMaker {
    private TypeMaker() {
        throw new UnsupportedOperationException("No instances allowed.");
    }

    public static Type getParameterizedType(Type... typeArr) throws IllegalArgumentException {
        if (typeArr.length == 0) {
            throw new IllegalArgumentException("At least one Type must be passed as an argument");
        }
        if (typeArr.length == 1) {
            return typeArr[0];
        }
        if (typeArr.length == 2) {
            return TypeToken.getParameterized(typeArr[0], typeArr[1]).getType();
        }
        return TypeToken.getParameterized(typeArr[0], getParameterizedType((Type[]) Arrays.copyOfRange(typeArr, 1, typeArr.length))).getType();
    }
}
