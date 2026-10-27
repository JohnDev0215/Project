package androidx.webkit.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes.dex */
public class AssetHelper {
    public static final String DEFAULT_MIME_TYPE = "text/plain";
    private static final String TAG = "AssetHelper";
    private Context mContext;

    public AssetHelper(Context context) {
        this.mContext = context;
    }

    private static InputStream handleSvgzStream(String str, InputStream inputStream) {
        if (inputStream == null || !str.endsWith(".svgz")) {
            return inputStream;
        }
        try {
            return new GZIPInputStream(inputStream);
        } catch (IOException e) {
            Log.e(TAG, "Error decompressing " + str + " - " + e.getMessage());
            return null;
        }
    }

    private static String removeLeadingSlash(String str) {
        return (str.length() <= 1 || str.charAt(0) != '/') ? str : str.substring(1);
    }

    private int getFieldId(String str, String str2) {
        return this.mContext.getResources().getIdentifier(str2, str, this.mContext.getPackageName());
    }

    private int getValueType(int i) {
        TypedValue typedValue = new TypedValue();
        this.mContext.getResources().getValue(i, typedValue, true);
        return typedValue.type;
    }

    public InputStream openResource(String str) {
        String strRemoveLeadingSlash = removeLeadingSlash(str);
        String[] strArrSplit = strRemoveLeadingSlash.split("/");
        if (strArrSplit.length != 2) {
            Log.e(TAG, "Incorrect resource path: " + strRemoveLeadingSlash);
            return null;
        }
        try {
            int fieldId = getFieldId(strArrSplit[0], strArrSplit[1].split("\\.")[0]);
            int valueType = getValueType(fieldId);
            if (valueType == 3) {
                return handleSvgzStream(strRemoveLeadingSlash, this.mContext.getResources().openRawResource(fieldId));
            }
            Log.e(TAG, String.format("Expected %s resource to be of TYPE_STRING but was %d", strRemoveLeadingSlash, Integer.valueOf(valueType)));
            return null;
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Resource not found from the path: " + strRemoveLeadingSlash, e);
            return null;
        }
    }

    public InputStream openAsset(String str) {
        String strRemoveLeadingSlash = removeLeadingSlash(str);
        try {
            return handleSvgzStream(strRemoveLeadingSlash, this.mContext.getAssets().open(strRemoveLeadingSlash, 2));
        } catch (IOException unused) {
            Log.e(TAG, "Unable to open asset path: " + strRemoveLeadingSlash);
            return null;
        }
    }

    public static InputStream openFile(File file) {
        try {
            return handleSvgzStream(file.getPath(), new FileInputStream(file));
        } catch (IOException e) {
            Log.e(TAG, "Error opening the requested file " + file, e);
            return null;
        }
    }

    public static boolean isCanonicalChildOf(File file, File file2) {
        try {
            String canonicalPath = file.getCanonicalPath();
            String canonicalPath2 = file2.getCanonicalPath();
            if (!canonicalPath.endsWith("/")) {
                canonicalPath = canonicalPath + "/";
            }
            return canonicalPath2.startsWith(canonicalPath);
        } catch (IOException e) {
            Log.e(TAG, "Error getting the canonical path of file", e);
            return false;
        }
    }

    public static String getCanonicalPath(File file) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        return !canonicalPath.endsWith("/") ? canonicalPath + "/" : canonicalPath;
    }

    public static File getDataDir(Context context) {
        return context.getDataDir();
    }

    public static String guessMimeType(String str) {
        String strGuessContentTypeFromName = URLConnection.guessContentTypeFromName(str);
        return strGuessContentTypeFromName == null ? DEFAULT_MIME_TYPE : strGuessContentTypeFromName;
    }
}
