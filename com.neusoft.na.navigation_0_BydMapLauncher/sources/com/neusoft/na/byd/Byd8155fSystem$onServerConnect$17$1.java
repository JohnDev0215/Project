package com.neusoft.na.byd;

import android.util.Log;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyExtractFiles;
import api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnExtractFiles;
import api.structs.com.neusoft.common.ExtractStatus;
import com.neusoft.na.byd.util.ChangeHistoryUtil;
import com.neusoft.na.common.FileUtils;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Byd8155fSystem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "status", "Lapi/notifications/com/neusoft/common/commonsysapiinverse/NotifyExtractFiles;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
final class Byd8155fSystem$onServerConnect$17$1 extends Lambda implements Function1<NotifyExtractFiles, Unit> {
    final /* synthetic */ ChangeHistoryUtil $util;
    final /* synthetic */ Byd8155fSystem this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Byd8155fSystem$onServerConnect$17$1(ChangeHistoryUtil changeHistoryUtil, Byd8155fSystem byd8155fSystem) {
        super(1);
        this.$util = changeHistoryUtil;
        this.this$0 = byd8155fSystem;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(NotifyExtractFiles notifyExtractFiles) {
        invoke2(notifyExtractFiles);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final NotifyExtractFiles notifyExtractFiles) {
        Log.i("Byd8155fSystem", "NotifyDecompressFiles");
        final String path = notifyExtractFiles.getExtractFiles().getSourceFilesInfo().getPath();
        ChangeHistoryUtil changeHistoryUtil = this.$util;
        final Byd8155fSystem byd8155fSystem = this.this$0;
        changeHistoryUtil.unzipFile(path, new ChangeHistoryUtil.IChangeHistoryListener() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$17$1$$ExternalSyntheticLambda0
            @Override // com.neusoft.na.byd.util.ChangeHistoryUtil.IChangeHistoryListener
            public final void onDone(boolean z) {
                Byd8155fSystem$onServerConnect$17$1.invoke$lambda$0(path, byd8155fSystem, notifyExtractFiles, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(String str, Byd8155fSystem this$0, NotifyExtractFiles notifyExtractFiles, boolean z) {
        ExtractStatus extractStatus;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i("Byd8155fSystem", "unzipFile status " + z);
        if (z) {
            String name = new File(str).getName();
            Intrinsics.checkNotNullExpressionValue(name, "file.name");
            String str2 = (String) StringsKt.split$default((CharSequence) name, new String[]{"-"}, false, 0, 6, (Object) null).get(1);
            String strSubstring = str2.substring(0, StringsKt.indexOf$default((CharSequence) str2, ".zip", 0, false, 6, (Object) null));
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            FileUtils.getInstance(this$0.context).updatePrivacyVersion(strSubstring);
            extractStatus = ExtractStatus.E_SUCCESS;
        } else {
            extractStatus = ExtractStatus.E_EXTRACT_FAILED;
        }
        ReqOnExtractFiles reqOnExtractFiles = new ReqOnExtractFiles(notifyExtractFiles.getExtractFiles().getSourceFilesInfo().getTokenId(), extractStatus);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this$0.commonRequests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireOnExtractFiles(reqOnExtractFiles);
        }
    }
}
