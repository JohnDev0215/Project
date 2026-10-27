package com.amazon.identity.auth.device.utils;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.device.ga;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ResourceHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f620a = "com.amazon.identity.auth.device.utils.ResourceHelper";

    /* JADX INFO: compiled from: DCP */
    public static class ResourceNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ResourceNotFoundException(String str) {
            super(str);
        }
    }

    public static int a(Context context, String str, String str2) {
        int identifier = context.getResources().getIdentifier(str2, str, context.getPackageName());
        if (identifier != 0) {
            return identifier;
        }
        Log.w(ga.a(f620a), String.format("The %s resource %s has not been found", str, str2));
        throw new ResourceNotFoundException(String.format("%s Resource %s not found", str, str2));
    }
}
