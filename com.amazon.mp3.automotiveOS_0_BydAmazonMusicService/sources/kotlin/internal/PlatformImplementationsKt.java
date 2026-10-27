package kotlin.internal;

import com.google.firebase.perf.plugin.util.AsmString;
import com.visualon.OSMPUtils.voOSTypePrivate;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: PlatformImplementations.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0001\u001a\"\u0010\b\u001a\u0002H\t\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0083\b¢\u0006\u0002\u0010\f\u001a\b\u0010\r\u001a\u00020\u0005H\u0002\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "apiVersionIsAtLeast", "", "major", "", "minor", "patch", "castToBaseType", "T", "", "instance", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "getJavaVersion", "kotlin-stdlib"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class PlatformImplementationsKt {

    @JvmField
    public static final PlatformImplementations IMPLEMENTATIONS;

    /* JADX WARN: Removed duplicated region for block: B:30:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0094 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.internal.PlatformImplementationsKt.<clinit>():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final /* synthetic */ <T> T castToBaseType(Object obj) throws Throwable {
        try {
            Intrinsics.reifiedOperationMarker(1, "T");
            return obj;
        } catch (ClassCastException e) {
            ClassLoader classLoader = obj.getClass().getClassLoader();
            Intrinsics.reifiedOperationMarker(4, "T");
            Throwable thInitCause = new ClassCastException("Instance classloader: " + classLoader + ", base type classloader: " + Object.class.getClassLoader()).initCause(e);
            Intrinsics.checkNotNullExpressionValue(thInitCause, "ClassCastException(\"Inst…baseTypeCL\").initCause(e)");
            throw thInitCause;
        }
    }

    private static final int getJavaVersion() {
        String property = System.getProperty("java.specification.version");
        if (property == null) {
            return voOSTypePrivate.VOOSMP_PID_HDCP_POLICY;
        }
        int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) property, '.', 0, false, 6, (Object) null);
        if (iIndexOf$default < 0) {
            try {
                return Integer.parseInt(property) * 65536;
            } catch (NumberFormatException unused) {
                return voOSTypePrivate.VOOSMP_PID_HDCP_POLICY;
            }
        }
        int i = iIndexOf$default + 1;
        int iIndexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) property, '.', i, false, 4, (Object) null);
        if (iIndexOf$default2 < 0) {
            iIndexOf$default2 = property.length();
        }
        String strSubstring = property.substring(0, iIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        String strSubstring2 = property.substring(i, iIndexOf$default2);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
        try {
            return (Integer.parseInt(strSubstring) * 65536) + Integer.parseInt(strSubstring2);
        } catch (NumberFormatException unused2) {
            return voOSTypePrivate.VOOSMP_PID_HDCP_POLICY;
        }
    }

    @SinceKotlin(version = "1.2")
    @PublishedApi
    public static final boolean apiVersionIsAtLeast(int i, int i2, int i3) {
        return KotlinVersion.CURRENT.isAtLeast(i, i2, i3);
    }
}
