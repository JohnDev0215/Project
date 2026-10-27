package spi;

import android.content.Context;
import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.FragmentKt;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.logging.Logger;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import okio.SegmentedByteString;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@JvmName(name = "-Platform")
public final class b {
    public static long a;

    @NotNull
    public static final rp a(@NotNull File appendingSink) throws FileNotFoundException {
        Logger logger = fk.a;
        Intrinsics.checkNotNullParameter(appendingSink, "$this$appendingSink");
        FileOutputStream sink = new FileOutputStream(appendingSink, true);
        Intrinsics.checkNotNullParameter(sink, "$this$sink");
        return new mk(sink, new dt());
    }

    @NotNull
    public static final k2 b(@NotNull rp buffer) {
        Intrinsics.checkNotNullParameter(buffer, "$this$buffer");
        return new tl(buffer);
    }

    @NotNull
    public static final l2 c(@NotNull vr buffer) {
        Intrinsics.checkNotNullParameter(buffer, "$this$buffer");
        return new ul(buffer);
    }

    @NotNull
    public static final String d(@NotNull mn mnVar) throws IOException {
        Intrinsics.checkNotNullParameter(mnVar, "<this>");
        long jContentLength = mnVar.contentLength();
        l2 l2VarSource = mnVar.source();
        l2VarSource.request(Long.MAX_VALUE);
        g2 g2VarA = l2VarSource.a();
        if (jContentLength == 0) {
            return "";
        }
        g2 g2VarClone = g2VarA.clone();
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        return g2VarClone.m(UTF_8);
    }

    @NotNull
    public static final String e(@NotNull mm mmVar, @NotNull String token, @NotNull Context context) throws JSONException, IOException {
        String strM;
        Intrinsics.checkNotNullParameter(mmVar, "<this>");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(context, "context");
        int iD = sr.a.d(context);
        Intrinsics.checkNotNullParameter(mmVar, "<this>");
        if (mmVar instanceof gc) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            gc gcVar = (gc) mmVar;
            int size = gcVar.a.size();
            for (int i = 0; i < size; i++) {
                linkedHashMap.put(gcVar.a.get(i), gcVar.b.get(i));
            }
            Gson gson = ce.a;
            strM = gson != null ? gson.toJson(linkedHashMap) : "";
            ct.a(d.f("formDataToJson RequestBody type:", mmVar.getClass().getSimpleName()), new Object[0]);
            Intrinsics.checkNotNullExpressionValue(strM, "{\n            val map = …)\n            }\n        }");
        } else {
            g2 g2Var = new g2();
            mmVar.writeTo(g2Var);
            Charset charset = Charset.forName("UTF-8");
            mi miVarContentType = mmVar.contentType();
            if (miVarContentType != null) {
                charset = miVarContentType.a(charset);
            }
            Intrinsics.checkNotNullExpressionValue(charset, "charset");
            strM = g2Var.m(charset);
            ct.a(d.g("formDataToJson RequestBody type:", mmVar.getClass().getSimpleName(), " raw json:", x3.a(x3.a, strM)), new Object[0]);
        }
        JSONObject jSONObject = strM == null || strM.length() == 0 ? new JSONObject() : new JSONObject(strM);
        jSONObject.put("versionCode", iD);
        jSONObject.put("token", token);
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jb.toString()");
        return string;
    }

    public static final boolean f(@NotNull AssertionError isAndroidGetsocknameError) {
        Logger logger = fk.a;
        Intrinsics.checkNotNullParameter(isAndroidGetsocknameError, "$this$isAndroidGetsocknameError");
        if (isAndroidGetsocknameError.getCause() == null) {
            return false;
        }
        String message = isAndroidGetsocknameError.getMessage();
        return message != null ? StringsKt__StringsKt.contains$default(message, "getsockname failed", false, 2, (Object) null) : false;
    }

    public static final void g(@NotNull Fragment fragment, @IdRes int i) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        try {
            FragmentKt.findNavController(fragment).navigate(i);
        } catch (Exception e) {
            ct.b(d.f("fragment.navigateFg ", e.getMessage()), new Object[0]);
        }
    }

    public static final void h(@NotNull Fragment fragment, @NotNull NavDirections directions) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(directions, "directions");
        try {
            FragmentKt.findNavController(fragment).navigate(directions);
        } catch (Exception e) {
            ct.b(d.f("fragment.navigateFg ", e.getMessage()), new Object[0]);
        }
    }

    public static final int i(@NotNull SegmentedByteString segment, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(segment, "$this$segment");
        int[] binarySearch = segment.getDirectory();
        int i3 = i + 1;
        int length = segment.getSegments().length;
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        int i4 = length - 1;
        int i5 = 0;
        while (true) {
            if (i5 <= i4) {
                i2 = (i5 + i4) >>> 1;
                int i6 = binarySearch[i2];
                if (i6 >= i3) {
                    if (i6 <= i3) {
                        break;
                    }
                    i4 = i2 - 1;
                } else {
                    i5 = i2 + 1;
                }
            } else {
                i2 = (-i5) - 1;
                break;
            }
        }
        return i2 >= 0 ? i2 : ~i2;
    }

    @NotNull
    public static final rp j(@NotNull Socket sink) throws IOException {
        Logger logger = fk.a;
        Intrinsics.checkNotNullParameter(sink, "$this$sink");
        vp vpVar = new vp(sink);
        OutputStream outputStream = sink.getOutputStream();
        Intrinsics.checkNotNullExpressionValue(outputStream, "getOutputStream()");
        mk sink2 = new mk(outputStream, vpVar);
        Intrinsics.checkNotNullParameter(sink2, "sink");
        return new r0(vpVar, sink2);
    }

    public static rp k(File sink) throws FileNotFoundException {
        Logger logger = fk.a;
        Intrinsics.checkNotNullParameter(sink, "$this$sink");
        FileOutputStream sink2 = new FileOutputStream(sink, false);
        Intrinsics.checkNotNullParameter(sink2, "$this$sink");
        return new mk(sink2, new dt());
    }

    @NotNull
    public static final vr l(@NotNull File source) throws FileNotFoundException {
        Logger logger = fk.a;
        Intrinsics.checkNotNullParameter(source, "$this$source");
        FileInputStream source2 = new FileInputStream(source);
        Intrinsics.checkNotNullParameter(source2, "$this$source");
        return new uf(source2, new dt());
    }

    @NotNull
    public static final vr m(@NotNull Socket source) throws IOException {
        Logger logger = fk.a;
        Intrinsics.checkNotNullParameter(source, "$this$source");
        vp vpVar = new vp(source);
        InputStream inputStream = source.getInputStream();
        Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream()");
        uf source2 = new uf(inputStream, vpVar);
        Intrinsics.checkNotNullParameter(source2, "source");
        return new s0(vpVar, source2);
    }

    public static final int n(long j, long j2) {
        if (j == 0) {
            return 0;
        }
        if (j == j2) {
            return 100;
        }
        return (int) ((j * ((long) 100)) / j2);
    }
}
