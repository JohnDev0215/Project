package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.R$string;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class h8 {

    @NotNull
    public static final h8 a = new h8();

    private h8() {
    }

    @NotNull
    public final String a(@NotNull String encryptedData, @NotNull Context context) {
        Object objM63constructorimpl;
        Intrinsics.checkNotNullParameter(encryptedData, "encryptedData");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            Cipher cipher = Cipher.getInstance(vm.a.e(context, R$string.encryption_transformation));
            cipher.init(2, c(context));
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            Intrinsics.checkNotNullExpressionValue(decryptedBytes, "decryptedBytes");
            objM63constructorimpl = Result.m63constructorimpl(new String(decryptedBytes, Charsets.UTF_8));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM66exceptionOrNullimpl = Result.m66exceptionOrNullimpl(objM63constructorimpl);
        if (thM66exceptionOrNullimpl != null) {
            ct.b(d.g("EncryptionUtils decrypt error msg:", thM66exceptionOrNullimpl.getMessage(), " encryptedData:", encryptedData), new Object[0]);
        }
        if (Result.m69isFailureimpl(objM63constructorimpl)) {
            objM63constructorimpl = null;
        }
        String str = (String) objM63constructorimpl;
        return str == null ? encryptedData : str;
    }

    @NotNull
    public final String b(@NotNull String data, @NotNull Context context) {
        Object objM63constructorimpl;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            Cipher cipher = Cipher.getInstance(vm.a.e(context, R$string.encryption_transformation));
            cipher.init(1, c(context));
            byte[] bytes = data.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            objM63constructorimpl = Result.m63constructorimpl(Base64.getEncoder().encodeToString(cipher.doFinal(bytes)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM66exceptionOrNullimpl = Result.m66exceptionOrNullimpl(objM63constructorimpl);
        if (thM66exceptionOrNullimpl != null) {
            ct.b(d.g("EncryptionUtils encrypt error msg:", thM66exceptionOrNullimpl.getMessage(), " data:", data), new Object[0]);
        }
        if (Result.m69isFailureimpl(objM63constructorimpl)) {
            objM63constructorimpl = null;
        }
        String str = (String) objM63constructorimpl;
        return str == null ? data : str;
    }

    public final SecretKeySpec c(Context context) {
        vm vmVar = vm.a;
        byte[] bytes = vmVar.e(context, R$string.encryption_key).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return new SecretKeySpec(bytes, vmVar.e(context, R$string.encryption_algorithm));
    }
}
