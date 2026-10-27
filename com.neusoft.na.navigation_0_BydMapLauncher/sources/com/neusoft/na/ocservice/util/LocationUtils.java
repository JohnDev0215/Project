package com.neusoft.na.ocservice.util;

import android.content.Context;
import android.util.Log;
import com.neusoft.na.common.FileUtils;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocationUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/neusoft/na/ocservice/util/LocationUtils;", "", "()V", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LocationUtils {
    private static final float BYD_LATITUDE_DEFAULT = 38.89512f;
    private static final float BYD_LONGITUDE_DEFAULT = -77.03637f;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "LocationUtils";

    /* JADX INFO: compiled from: LocationUtils.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\t2\u0006\u0010\n\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\u000bJ\u001d\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\t2\u0006\u0010\n\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\u000bJ\u001d\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0003¢\u0006\u0002\u0010\u0010J\u001b\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\t2\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/neusoft/na/ocservice/util/LocationUtils$Companion;", "", "()V", "BYD_LATITUDE_DEFAULT", "", "BYD_LONGITUDE_DEFAULT", "TAG", "", "getByd6125FPitch", "", "areaKey", "(Ljava/lang/String;)[Ljava/lang/Float;", "getByd8155FPitch", "getBydPitch", "context", "Landroid/content/Context;", "(Landroid/content/Context;)[Ljava/lang/Float;", "getPitch", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Float[] getPitch(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return (CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) ? getBydPitch(context) : new Float[2];
        }

        private final Float[] getBydPitch(Context context) throws Throwable {
            Log.i(LocationUtils.TAG, "[getBydPitch]");
            Float[] byd8155FPitch = new Float[2];
            InputStream inputStreamOpen = context.getAssets().open(FileUtils.getInstance(context).getDbuIniPath());
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.assets.open(File…ance(context).dbuIniPath)");
            Map<String, Object> ini = com.neusoft.na.common.Tool.readIni(inputStreamOpen);
            Intrinsics.checkNotNullExpressionValue(ini, "readIni(inputStream)");
            Map map = (Map) ini.get("nds");
            Intrinsics.checkNotNull(map);
            File file = new File(com.neusoft.na.common.Tool.getFileDir((String) map.get("database")), "Area.txt");
            Log.i(LocationUtils.TAG, "areaFile == " + file.getAbsolutePath());
            if (file.exists()) {
                String text$default = FilesKt.readText$default(file, null, 1, null);
                Log.i(LocationUtils.TAG, "areaKey == " + text$default);
                if (CheckBoardKt.isByd()) {
                    byd8155FPitch = getByd6125FPitch(text$default);
                } else if (CheckBoardKt.isByd8155f()) {
                    byd8155FPitch = getByd8155FPitch(text$default);
                }
            } else {
                byd8155FPitch[0] = Float.valueOf(LocationUtils.BYD_LATITUDE_DEFAULT);
                byd8155FPitch[1] = Float.valueOf(LocationUtils.BYD_LONGITUDE_DEFAULT);
            }
            Log.i(LocationUtils.TAG, "result == " + byd8155FPitch[0] + " , " + byd8155FPitch[1]);
            return byd8155FPitch;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:20:0x007a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final java.lang.Float[] getByd6125FPitch(java.lang.String r8) {
            /*
                r7 = this;
                java.lang.String r0 = "LocationUtils"
                java.lang.String r1 = "[getByd6125FPitch]"
                android.util.Log.i(r0, r1)
                r0 = 2
                java.lang.Float[] r0 = new java.lang.Float[r0]
                int r1 = r8.hashCode()
                r2 = 2066(0x812, float:2.895E-42)
                r3 = -1030090081(0xffffffffc29a129f, float:-77.03637)
                r4 = 1109103770(0x421b949a, float:38.89512)
                r5 = 1
                r6 = 0
                if (r1 == r2) goto L72
                switch(r1) {
                    case 2098: goto L56;
                    case 2099: goto L3a;
                    case 2100: goto L1e;
                    default: goto L1d;
                }
            L1d:
                goto L7a
            L1e:
                java.lang.String r1 = "B6"
                boolean r8 = r8.equals(r1)
                if (r8 != 0) goto L27
                goto L7a
            L27:
                r8 = -1043242417(0xffffffffc1d1624f, float:-26.173002)
                java.lang.Float r8 = java.lang.Float.valueOf(r8)
                r0[r6] = r8
                r8 = 1105221440(0x41e05740, float:28.042603)
                java.lang.Float r8 = java.lang.Float.valueOf(r8)
                r0[r5] = r8
                goto L93
            L3a:
                java.lang.String r1 = "B5"
                boolean r8 = r8.equals(r1)
                if (r8 != 0) goto L43
                goto L7a
            L43:
                r8 = 1106263409(0x41f03d71, float:30.03)
                java.lang.Float r8 = java.lang.Float.valueOf(r8)
                r0[r6] = r8
                r8 = 1106850611(0x41f93333, float:31.15)
                java.lang.Float r8 = java.lang.Float.valueOf(r8)
                r0[r5] = r8
                goto L93
            L56:
                java.lang.String r1 = "B4"
                boolean r8 = r8.equals(r1)
                if (r8 != 0) goto L5f
                goto L7a
            L5f:
                r8 = 1103462664(0x41c58108, float:24.688004)
                java.lang.Float r8 = java.lang.Float.valueOf(r8)
                r0[r6] = r8
                r8 = 1111155653(0x423ae3c5, float:46.72243)
                java.lang.Float r8 = java.lang.Float.valueOf(r8)
                r0[r5] = r8
                goto L93
            L72:
                java.lang.String r1 = "A3"
                boolean r8 = r8.equals(r1)
                if (r8 != 0) goto L87
            L7a:
                java.lang.Float r8 = java.lang.Float.valueOf(r4)
                r0[r6] = r8
                java.lang.Float r8 = java.lang.Float.valueOf(r3)
                r0[r5] = r8
                goto L93
            L87:
                java.lang.Float r8 = java.lang.Float.valueOf(r4)
                r0[r6] = r8
                java.lang.Float r8 = java.lang.Float.valueOf(r3)
                r0[r5] = r8
            L93:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.util.LocationUtils.Companion.getByd6125FPitch(java.lang.String):java.lang.Float[]");
        }

        /* JADX WARN: Removed duplicated region for block: B:43:0x00e4  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final java.lang.Float[] getByd8155FPitch(java.lang.String r6) {
            /*
                Method dump skipped, instruction units count: 266
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.util.LocationUtils.Companion.getByd8155FPitch(java.lang.String):java.lang.Float[]");
        }
    }
}
