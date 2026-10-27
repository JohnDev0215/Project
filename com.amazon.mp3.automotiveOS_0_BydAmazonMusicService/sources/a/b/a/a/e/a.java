package a.b.a.a.e;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(21)
public final class a {
    public final int[] c;
    public final boolean d;
    public final int e;
    public static final C0006a b = new C0006a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f45a = new a(new int[]{2}, 2, 1);

    /* JADX INFO: renamed from: a.b.a.a.e.a$a, reason: collision with other inner class name */
    public static final class C0006a {
        @SuppressLint({"InlinedApi"})
        public final a a(Intent intent) {
            return intent != null ? new a(intent.getIntArrayExtra("android.media.extra.ENCODINGS"), intent.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 0), intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 1)) : a.f45a;
        }

        public final a a() {
            return a.f45a;
        }
    }

    public static final class b extends Lambda implements Function1<Integer, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f46a = new b();

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public String invoke(Integer num) {
            int iIntValue = num.intValue();
            a aVar = a.f45a;
            if (iIntValue == 0) {
                return "ENCODING_INVALID";
            }
            switch (iIntValue) {
                case 2:
                    return "ENCODING_PCM_16BIT";
                case 3:
                    return "ENCODING_PCM_8BIT";
                case 4:
                    return "ENCODING_PCM_FLOAT";
                case 5:
                    return "ENCODING_AC3";
                case 6:
                    return "ENCODING_E_AC3";
                case 7:
                    return "ENCODING_DTS";
                case 8:
                    return "ENCODING_DTS_HD";
                case 9:
                    return "ENCODING_MP3";
                case 10:
                    return "ENCODING_AAC_LC";
                case 11:
                    return "ENCODING_AAC_HE_V1";
                case 12:
                    return "ENCODING_AAC_HE_V2";
                case 13:
                    return "ENCODING_IEC61937";
                case 14:
                    return "ENCODING_DOLBY_TRUEHD";
                case 15:
                    return "ENCODING_AAC_ELD";
                case 16:
                    return "ENCODING_AAC_XHE";
                case 17:
                    return "ENCODING_AC4";
                default:
                    return "invalid encoding " + iIntValue;
            }
        }
    }

    public a(int[] iArr, int i, int i2) {
        int[] iArrSortedArray;
        this.e = i;
        this.c = (iArr == null || (iArrSortedArray = ArraysKt___ArraysKt.sortedArray(iArr)) == null) ? new int[0] : iArrSortedArray;
        this.d = i2 == 1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (Arrays.equals(this.c, aVar.c) && this.e == aVar.e && this.d == aVar.d) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.e + (Arrays.hashCode(this.c) * 31);
    }

    public String toString() {
        return "AudioCapabilities[HDMIConnected=" + this.d + ",maxChannelCount=" + this.e + ",supportedEncodings=" + ArraysKt___ArraysKt.joinToString$default(this.c, (CharSequence) " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) b.f46a, 30, (Object) null) + "]";
    }
}
