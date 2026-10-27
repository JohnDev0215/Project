package com.google.android.gms.common.images;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.core.model.ModelIdentifier;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.0.1 */
/* JADX INFO: loaded from: classes6.dex */
public final class Size {
    private final int zaa;
    private final int zab;

    public Size(int i, int i2) {
        this.zaa = i;
        this.zab = i2;
    }

    private static NumberFormatException zaa(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 16);
        sb.append("Invalid Size: \"");
        sb.append(str);
        sb.append(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
        throw new NumberFormatException(sb.toString());
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.zaa == size.zaa && this.zab == size.zab) {
                return true;
            }
        }
        return false;
    }

    public int getHeight() {
        return this.zab;
    }

    public int getWidth() {
        return this.zaa;
    }

    public int hashCode() {
        int i = this.zab;
        int i2 = this.zaa;
        return i ^ ((i2 >>> 16) | (i2 << 16));
    }

    @NonNull
    public String toString() {
        int i = this.zaa;
        int i2 = this.zab;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i);
        sb.append("x");
        sb.append(i2);
        return sb.toString();
    }

    @NonNull
    public static Size parseSize(@NonNull String str) throws NumberFormatException {
        if (str == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int iIndexOf = str.indexOf(42);
        if (iIndexOf < 0) {
            iIndexOf = str.indexOf(120);
        }
        if (iIndexOf < 0) {
            throw zaa(str);
        }
        try {
            return new Size(Integer.parseInt(str.substring(0, iIndexOf)), Integer.parseInt(str.substring(iIndexOf + 1)));
        } catch (NumberFormatException unused) {
            throw zaa(str);
        }
    }
}
