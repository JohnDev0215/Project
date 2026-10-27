package com.neusoft.na.common;

import android.text.TextUtils;
import android.util.Log;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyDBDataIntegrityCheck;
import api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnDBDataIntegrityCheck;
import api.structs.com.neusoft.common.DBPath;
import api.structs.com.neusoft.common.ResultStatus;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: CommonSystem.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lapi/notifications/com/neusoft/common/commonsysapiinverse/NotifyDBDataIntegrityCheck;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
final class CommonSystem$onServerConnect$34$1 extends Lambda implements Function1<NotifyDBDataIntegrityCheck, Unit> {
    final /* synthetic */ CommonSystem this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CommonSystem$onServerConnect$34$1(CommonSystem commonSystem) {
        super(1);
        this.this$0 = commonSystem;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(NotifyDBDataIntegrityCheck notifyDBDataIntegrityCheck) {
        invoke2(notifyDBDataIntegrityCheck);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final NotifyDBDataIntegrityCheck notifyDBDataIntegrityCheck) {
        Log.i("XJCommonSystem", "NotifyDBDataIntegrityCheck");
        final ReqOnDBDataIntegrityCheck reqOnDBDataIntegrityCheck = new ReqOnDBDataIntegrityCheck();
        reqOnDBDataIntegrityCheck.getArguments().setStatus(ResultStatus.E_SUCCESS);
        Log.i("XJCommonSystem", "check all db file size");
        final CommonSystem commonSystem = this.this$0;
        new Thread(new Runnable() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$34$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CommonSystem$onServerConnect$34$1.invoke$lambda$0(notifyDBDataIntegrityCheck, reqOnDBDataIntegrityCheck, commonSystem);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(NotifyDBDataIntegrityCheck notifyDBDataIntegrityCheck, ReqOnDBDataIntegrityCheck reqOnDBDataIntegrityCheck, CommonSystem this$0) {
        Intrinsics.checkNotNullParameter(reqOnDBDataIntegrityCheck, "$reqOnDBDataIntegrityCheck");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DBPath dBDataIntegrityCheck = notifyDBDataIntegrityCheck.getDBDataIntegrityCheck();
        String path = dBDataIntegrityCheck != null ? dBDataIntegrityCheck.getPath() : null;
        Log.i("XJCommonSystem", "dbFilePath == " + path);
        File file = new File(path, "metadata.json");
        if (TextUtils.isEmpty(path) || !file.exists()) {
            Log.i("XJCommonSystem", "db file path is empty, or metadata.json is not exists!");
            reqOnDBDataIntegrityCheck.getArguments().setStatus(ResultStatus.E_FAILURE);
            CommonSysApiInverseRequests commonSysApiInverseRequests = this$0.requests;
            if (commonSysApiInverseRequests != null) {
                commonSysApiInverseRequests.fireOnDBDataIntegrityCheck(reqOnDBDataIntegrityCheck);
                return;
            }
            return;
        }
        try {
            String text$default = FilesKt.readText$default(file, null, 1, null);
            Log.i("XJCommonSystem", "metadataFileContent == " + text$default);
            JSONObject jSONObject = new JSONObject(text$default);
            JSONArray rootDbJSONArray = jSONObject.getJSONObject("ndsDb").getJSONObject("rootDb").getJSONArray("files");
            Log.i("XJCommonSystem", "rootDbJSONArray == " + rootDbJSONArray);
            Intrinsics.checkNotNullExpressionValue(rootDbJSONArray, "rootDbJSONArray");
            boolean zCheckFilesSize = this$0.checkFilesSize(path, rootDbJSONArray);
            Log.i("XJCommonSystem", "rootDbJSONArray dbCheckResult == " + zCheckFilesSize);
            if (!zCheckFilesSize) {
                reqOnDBDataIntegrityCheck.getArguments().setStatus(ResultStatus.E_FAILURE);
                CommonSysApiInverseRequests commonSysApiInverseRequests2 = this$0.requests;
                if (commonSysApiInverseRequests2 != null) {
                    commonSysApiInverseRequests2.fireOnDBDataIntegrityCheck(reqOnDBDataIntegrityCheck);
                    return;
                }
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONObject("ndsDb").getJSONArray("products").getJSONObject(0).getJSONArray("updateRegions");
            Log.i("XJCommonSystem", "updateRegionsJSONArray == " + jSONArray);
            int length = jSONArray.length();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    Object obj = jSONArray.get(i);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                    JSONObject jSONObject2 = (JSONObject) obj;
                    if (jSONObject2.has("files")) {
                        JSONArray groupArray = jSONObject2.getJSONArray("files");
                        Intrinsics.checkNotNullExpressionValue(groupArray, "groupArray");
                        boolean zCheckFilesSize2 = this$0.checkFilesSize(path, groupArray);
                        Log.i("XJCommonSystem", "updateRegionsJSONArray dbCheckResult == " + zCheckFilesSize2);
                        if (!zCheckFilesSize2) {
                            reqOnDBDataIntegrityCheck.getArguments().setStatus(ResultStatus.E_FAILURE);
                            CommonSysApiInverseRequests commonSysApiInverseRequests3 = this$0.requests;
                            if (commonSysApiInverseRequests3 != null) {
                                commonSysApiInverseRequests3.fireOnDBDataIntegrityCheck(reqOnDBDataIntegrityCheck);
                                return;
                            }
                            return;
                        }
                    }
                }
            }
            JSONArray productsFilesJSONArray = jSONObject.getJSONObject("ndsDb").getJSONArray("products").getJSONObject(1).getJSONArray("files");
            Log.i("XJCommonSystem", "productsFilesJSONArray == " + productsFilesJSONArray);
            Intrinsics.checkNotNullExpressionValue(productsFilesJSONArray, "productsFilesJSONArray");
            boolean zCheckFilesSize3 = this$0.checkFilesSize(path, productsFilesJSONArray);
            Log.i("XJCommonSystem", "productsFilesJSONArray dbCheckResult == " + zCheckFilesSize3);
            if (!zCheckFilesSize3) {
                reqOnDBDataIntegrityCheck.getArguments().setStatus(ResultStatus.E_FAILURE);
                CommonSysApiInverseRequests commonSysApiInverseRequests4 = this$0.requests;
                if (commonSysApiInverseRequests4 != null) {
                    commonSysApiInverseRequests4.fireOnDBDataIntegrityCheck(reqOnDBDataIntegrityCheck);
                    return;
                }
                return;
            }
            Log.i("XJCommonSystem", "DB check success.");
            reqOnDBDataIntegrityCheck.getArguments().setStatus(ResultStatus.E_SUCCESS);
            CommonSysApiInverseRequests commonSysApiInverseRequests5 = this$0.requests;
            if (commonSysApiInverseRequests5 != null) {
                commonSysApiInverseRequests5.fireOnDBDataIntegrityCheck(reqOnDBDataIntegrityCheck);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("XJCommonSystem", "DB check Exception.");
            reqOnDBDataIntegrityCheck.getArguments().setStatus(ResultStatus.E_FAILURE);
            CommonSysApiInverseRequests commonSysApiInverseRequests6 = this$0.requests;
            if (commonSysApiInverseRequests6 != null) {
                commonSysApiInverseRequests6.fireOnDBDataIntegrityCheck(reqOnDBDataIntegrityCheck);
            }
        }
    }
}
