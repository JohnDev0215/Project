package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.zzj;
import java.util.HashMap;
import p.oz2;

/* JADX INFO: loaded from: classes.dex */
public class AssetPackException extends zzj {
    private final int zza;

    /* JADX WARN: Illegal instructions before constructor call */
    public AssetPackException(int i) {
        String string;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        HashMap map = oz2.a;
        Integer numValueOf = Integer.valueOf(i);
        if (map.containsKey(numValueOf)) {
            String str = (String) map.get(numValueOf);
            String str2 = (String) oz2.b.get(numValueOf);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 113 + String.valueOf(str2).length());
            sb.append(str);
            sb.append(" (https://developer.android.com/reference/com/google/android/play/core/assetpacks/model/AssetPackErrorCode.html#");
            sb.append(str2);
            sb.append(")");
            string = sb.toString();
        } else {
            string = "";
        }
        objArr[1] = string;
        super(String.format("Asset Pack Download Error(%d): %s", objArr));
        if (i == 0) {
            throw new IllegalArgumentException("errorCode should not be 0.");
        }
        this.zza = i;
    }
}
