package spi;

import android.content.Context;
import android.hardware.bydauto_test.TestInstrumentManagerImplCallback;
import android.text.TextUtils;
import com.byd.overseaappstore.module.ability.data.req.TokenReq;
import com.byd.overseaappstore.module.ability.data.rsp.TokeWrapRsp;
import com.byd.overseaappstore.module.ability.util.SettingUtil;
import com.byd.overseaappstore.module.ability.util.SettingUtil$getToken$1;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Objects;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import spi.ag;
import spi.im;
import spi.mm;

/* JADX INFO: loaded from: classes.dex */
public final class ht implements ag {

    @NotNull
    public final Context a;

    @NotNull
    public final z3 b;

    public ht(@NotNull Context context, @NotNull z3 configRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        this.a = context;
        this.b = configRepository;
    }

    @Override // spi.ag
    @NotNull
    public final ln a(@NotNull ag.a chain) throws JSONException, IOException {
        String token;
        Object objM63constructorimpl;
        Object objM63constructorimpl2;
        Object objM63constructorimpl3;
        TokenReq tokenReq;
        TokenReq tokenReq2;
        Object objM63constructorimpl4;
        String strE;
        Object objM63constructorimpl5;
        String strE2;
        Intrinsics.checkNotNullParameter(chain, "chain");
        bm bmVar = (bm) chain;
        String strA = bmVar.f.d.a("CUSTOM_HEADER_KEY_DOWNLOAD");
        ct.a(d.f("interceptor download isDownloadRequest headerValue:", strA), new Object[0]);
        boolean zAreEqual = Intrinsics.areEqual(strA, "CUSTOM_HEADER_VALUE_DOWNLOAD");
        ct.a(hi.e("interceptor downloadReq:", zAreEqual), new Object[0]);
        if (zAreEqual) {
            return bmVar.b(bmVar.f);
        }
        SettingUtil settingUtil = SettingUtil.a;
        Context context = this.a;
        Objects.requireNonNull(settingUtil);
        Intrinsics.checkNotNullParameter(context, "context");
        String str = (String) BuildersKt__BuildersKt.runBlocking$default(null, new SettingUtil$getToken$1(context, null), 1, null);
        ln lnVarB = b(chain, str);
        String strB = bmVar.f.b.b();
        token = "";
        if (lnVarB.e != 200) {
            try {
                Result.Companion companion = Result.INSTANCE;
                mm mmVar = ((bm) chain).f.e;
                if (mmVar != null && (strE2 = b.e(mmVar, str, this.a)) != null) {
                    token = strE2;
                }
                objM63constructorimpl5 = Result.m63constructorimpl(token);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM63constructorimpl5 = Result.m63constructorimpl(ResultKt.createFailure(th));
            }
            ct.a(hi.f(xi.h("interceptor network error response.code is ", lnVarB.e, " url:", strB, " reqJson:"), (String) (Result.m69isFailureimpl(objM63constructorimpl5) ? null : objM63constructorimpl5), " "), new Object[0]);
            return lnVarB;
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            mn mnVar = lnVarB.h;
            objM63constructorimpl = Result.m63constructorimpl(mnVar != null ? b.d(mnVar) : "");
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m69isFailureimpl(objM63constructorimpl)) {
            objM63constructorimpl = null;
        }
        String str2 = (String) objM63constructorimpl;
        if (TextUtils.isEmpty(str2)) {
            ct.b(d.f("interceptor network error response.body is empty url:", strB), new Object[0]);
            return lnVarB;
        }
        try {
            Result.Companion companion5 = Result.INSTANCE;
            objM63constructorimpl2 = Result.m63constructorimpl(new JSONObject(str2));
        } catch (Throwable th3) {
            Result.Companion companion6 = Result.INSTANCE;
            objM63constructorimpl2 = Result.m63constructorimpl(ResultKt.createFailure(th3));
        }
        if (Result.m69isFailureimpl(objM63constructorimpl2)) {
            ct.b(d.f("interceptor network error response.body is not json url:", strB), new Object[0]);
            return lnVarB;
        }
        if (Result.m69isFailureimpl(objM63constructorimpl2)) {
            objM63constructorimpl2 = null;
        }
        JSONObject jSONObject = (JSONObject) objM63constructorimpl2;
        if (jSONObject == null) {
            ct.b(d.f("interceptor network error resultRsp.get() is null url:", strB), new Object[0]);
            return lnVarB;
        }
        try {
            Result.Companion companion7 = Result.INSTANCE;
            mm mmVar2 = ((bm) chain).f.e;
            if (mmVar2 == null || (strE = b.e(mmVar2, str, this.a)) == null) {
                strE = "";
            }
            objM63constructorimpl3 = Result.m63constructorimpl(strE);
        } catch (Throwable th4) {
            Result.Companion companion8 = Result.INSTANCE;
            objM63constructorimpl3 = Result.m63constructorimpl(ResultKt.createFailure(th4));
        }
        if (Result.m69isFailureimpl(objM63constructorimpl3)) {
            objM63constructorimpl3 = null;
        }
        String str3 = (String) objM63constructorimpl3;
        if (!jSONObject.has("code")) {
            StringBuilder sbL = d.l("interceptor network error no code key url:", strB, " reqJson:", str3, " responseJson:");
            sbL.append(str2);
            ct.b(sbL.toString(), new Object[0]);
            return lnVarB;
        }
        Object obj = jSONObject.get("code");
        if (Intrinsics.areEqual(obj, (Object) 0)) {
            StringBuilder sbL2 = d.l("interceptor network success url:", strB, " reqJson:", x3.a(x3.a, str3), " responseJson *** rspCode:");
            sbL2.append(obj);
            ct.a(sbL2.toString(), new Object[0]);
            return lnVarB;
        }
        if (!(Intrinsics.areEqual(obj, Integer.valueOf(TestInstrumentManagerImplCallback.SET_MAINTENANCE_INFO_TIME_STATE)) ? true : Intrinsics.areEqual(obj, Integer.valueOf(TestInstrumentManagerImplCallback.SET_INSTRUMENT_BRIGHTNESS)))) {
            StringBuilder sbL3 = d.l("interceptor network error other error url:", strB, " reqJson:", str3, " responseJson:");
            sbL3.append(str2);
            sbL3.append(" rspCode:");
            sbL3.append(obj);
            ct.a(sbL3.toString(), new Object[0]);
            return lnVarB;
        }
        StringBuilder sbL4 = d.l("interceptor network error once token error url:", strB, " reqJson:", str3, " responseJson:");
        sbL4.append(str2);
        sbL4.append(" rspCode:");
        sbL4.append(obj);
        ct.b(sbL4.toString(), new Object[0]);
        r3 r3Var = r3.a;
        if (r3Var.c() || r3Var.e() || r3Var.h() || r3Var.b()) {
            tokenReq = new TokenReq(225, 3, "Di3.0_3.5UI", "47");
        } else {
            if (Intrinsics.areEqual("flavor_8155f_pro", "flavor_6125f_master")) {
                int iF = this.b.f();
                this.b.c();
                tokenReq2 = new TokenReq(iF, 3, this.b.d(), this.b.b());
            } else if (Intrinsics.areEqual("flavor_8155f_pro", "flavor_6125f_pro")) {
                int iF2 = this.b.f();
                this.b.c();
                tokenReq2 = new TokenReq(iF2, 3, this.b.d(), this.b.b());
            } else if (r3Var.d()) {
                int iF3 = this.b.f();
                this.b.c();
                tokenReq2 = new TokenReq(iF3, 3, this.b.d(), this.b.b());
            } else if (r3Var.g()) {
                int iF4 = this.b.f();
                this.b.c();
                tokenReq2 = new TokenReq(iF4, 3, this.b.d(), this.b.b());
            } else if (r3Var.f()) {
                int iF5 = this.b.f();
                this.b.c();
                tokenReq2 = new TokenReq(iF5, 3, this.b.d(), this.b.b());
            } else if (r3Var.i()) {
                int iF6 = this.b.f();
                this.b.c();
                tokenReq2 = new TokenReq(iF6, 3, this.b.d(), this.b.b());
            } else if (r3Var.a()) {
                int iF7 = this.b.f();
                this.b.c();
                tokenReq2 = new TokenReq(iF7, 3, this.b.d(), this.b.b());
            } else {
                tokenReq = new TokenReq(225, 3, "Di3.0_3.5UI", "47");
            }
            tokenReq = tokenReq2;
        }
        ct.a("interceptor generateTokenReq apkType:flavor_8155f_pro tokenReq:" + tokenReq, new Object[0]);
        Gson gson = ce.a;
        String tokenReqJson = gson != null ? gson.toJson(tokenReq) : null;
        mm.a aVar = mm.Companion;
        Intrinsics.checkNotNullExpressionValue(tokenReqJson, "tokenReqJson");
        mm body = aVar.a(tokenReqJson, null);
        im imVar = bmVar.f;
        Objects.requireNonNull(imVar);
        im.a aVar2 = new im.a(imVar);
        aVar2.b("content-type", "application/json");
        String strA2 = gi.a("appmarketapi" + tokenReqJson + "1234567887654321");
        Intrinsics.checkNotNullExpressionValue(strA2, "encrypt(ConstNet.HEADED_…D_VALUE_SIGN_PRIVATE_KEY)");
        aVar2.b("sign", strA2);
        aVar2.b("accept", "application/vnd.appmarket.3.1.tag+json");
        aVar2.g(qj.a.a() + "user/login");
        Intrinsics.checkNotNullParameter(body, "body");
        aVar2.d("POST", body);
        ln lnVarB2 = bmVar.b(aVar2.a());
        Gson gson2 = ce.a;
        String json = gson2 != null ? gson2.toJson(tokenReq) : null;
        mn mnVar2 = lnVarB2.h;
        String strD = mnVar2 != null ? b.d(mnVar2) : "";
        StringBuilder sbL5 = d.l("interceptor freshToken url:", strB, " reqTokenJson:", json, " rspTokenJson:");
        sbL5.append(strD);
        ct.a(sbL5.toString(), new Object[0]);
        try {
            Result.Companion companion9 = Result.INSTANCE;
            objM63constructorimpl4 = Result.m63constructorimpl((TokeWrapRsp) ce.a(strD, TokeWrapRsp.class));
        } catch (Throwable th5) {
            Result.Companion companion10 = Result.INSTANCE;
            objM63constructorimpl4 = Result.m63constructorimpl(ResultKt.createFailure(th5));
        }
        if (Result.m69isFailureimpl(objM63constructorimpl4)) {
            objM63constructorimpl4 = null;
        }
        TokeWrapRsp tokeWrapRsp = (TokeWrapRsp) objM63constructorimpl4;
        if (tokeWrapRsp == null) {
            StringBuilder sbL6 = d.l("interceptor freshToken error tokenWrapRsp is null url:", strB, " reqTokenJson:", json, " rspTokenJson:");
            sbL6.append(strD);
            ct.b(sbL6.toString(), new Object[0]);
            return lnVarB2;
        }
        if (tokeWrapRsp.getCode() != 0) {
            StringBuilder sbL7 = d.l("interceptor freshToken error url:", strB, " reqTokenJson:", json, " rspTokenJson:");
            sbL7.append(strD);
            ct.b(sbL7.toString(), new Object[0]);
            return lnVarB2;
        }
        if (tokeWrapRsp.getData() == null) {
            StringBuilder sbL8 = d.l("interceptor freshToken error data is null url:", strB, " reqTokenJson:", json, " rspTokenJson:");
            sbL8.append(strD);
            ct.b(sbL8.toString(), new Object[0]);
            return lnVarB2;
        }
        String token2 = tokeWrapRsp.getData().getToken();
        token = token2 != null ? token2 : "";
        if (TextUtils.isEmpty(token)) {
            StringBuilder sbL9 = d.l("interceptor freshToken token is empty url:", strB, " reqTokenJson:", json, " rspTokenJson:");
            sbL9.append(strD);
            ct.b(sbL9.toString(), new Object[0]);
            return lnVarB2;
        }
        SettingUtil settingUtil2 = SettingUtil.a;
        Context context2 = this.a;
        Objects.requireNonNull(settingUtil2);
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(token, "token");
        StringBuilder sbL10 = d.l("interceptor network freshToken success,continue start url:", strB, " reqTokenJson:", json, " rspTokenJson:");
        sbL10.append(strD);
        ct.a(sbL10.toString(), new Object[0]);
        ln lnVarB3 = b(chain, token);
        ct.a("interceptor network freshToken success,continue finish url:" + strB + " netCode:" + lnVarB3.e, new Object[0]);
        return lnVarB3;
    }

    public final ln b(ag.a aVar, String str) {
        String strE;
        bm bmVar = (bm) aVar;
        mm mmVar = bmVar.f.e;
        if (mmVar == null || (strE = b.e(mmVar, str, this.a)) == null) {
            strE = "";
        }
        mm body = mm.Companion.a(strE, null);
        im imVar = bmVar.f;
        Objects.requireNonNull(imVar);
        im.a aVar2 = new im.a(imVar);
        aVar2.b("content-type", "application/json");
        String strA = gi.a("appmarketapi" + strE + "1234567887654321");
        Intrinsics.checkNotNullExpressionValue(strA, "encrypt(ConstNet.HEADED_…D_VALUE_SIGN_PRIVATE_KEY)");
        aVar2.b("sign", strA);
        aVar2.b("accept", "application/vnd.appmarket.3.1.tag+json");
        Intrinsics.checkNotNullParameter(body, "body");
        aVar2.d("POST", body);
        return bmVar.b(aVar2.a());
    }
}
