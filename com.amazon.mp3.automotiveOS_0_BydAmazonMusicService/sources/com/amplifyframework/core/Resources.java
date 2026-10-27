package com.amplifyframework.core;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.RawRes;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public final class Resources {
    private Resources() {
    }

    @RawRes
    public static int getRawResourceId(Context context, String str) throws ResourceLoadingException {
        try {
            return context.getResources().getIdentifier(str, "raw", context.getPackageName());
        } catch (Exception e) {
            throw new ResourceLoadingException("No such resource with identifier " + str, e);
        }
    }

    public static JSONObject readJsonResource(Context context, String str) throws ResourceLoadingException {
        return readJsonResourceFromId(context, getRawResourceId(context, str));
    }

    public static JSONObject readJsonResourceFromId(Context context, @RawRes int i) throws ResourceLoadingException {
        try {
            Scanner scanner = new Scanner(context.getResources().openRawResource(i));
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            scanner.close();
            try {
                return new JSONObject(sb.toString());
            } catch (JSONException e) {
                throw new ResourceLoadingException("Failed to read the resource with ID " + i + ".", e);
            }
        } catch (Resources.NotFoundException e2) {
            throw new ResourceLoadingException("No such resource with ID = " + i, e2);
        }
    }

    public static final class ResourceLoadingException extends Exception {
        private static final long serialVersionUID = 1;

        public ResourceLoadingException(String str, Throwable th) {
            super(str, th);
        }
    }
}
