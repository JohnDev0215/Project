package cerence.ark.assistant.controller.domain.system;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.system.param.CheckPermissionParam;
import cerence.ark.assistant.controller.domain.system.result.CheckPermissionResult;
import cerence.ark.assistant.controller.domain.system.result.GetNetworkStatusResult;
import cerence.ark.assistant.controller.enumeration.PermissionNameEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.core.AudioFocus;
import cerence.ark.assistant.library.Utils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SystemDomain extends AbsSystemDomain {
    private final AudioFocus mAudioFocus;

    protected SystemDomain(Dhi dhi) {
        super(dhi);
        this.mAudioFocus = new AudioFocus(Utils.getContext(), "dialog");
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void getNetworkStatus(IResponseSender<GetNetworkStatusResult> iResponseSender) {
        GetNetworkStatusResult getNetworkStatusResult = new GetNetworkStatusResult();
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
        getNetworkStatusResult.setIsNetworkConnected(activeNetworkInfo != null && activeNetworkInfo.isConnected());
        iResponseSender.send(new Response(ResultCodeEnum.OK, getNetworkStatusResult));
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void checkPermission(CheckPermissionParam checkPermissionParam, IResponseSender<CheckPermissionResult> iResponseSender) {
        Response response = new Response(ResultCodeEnum.OK);
        CheckPermissionResult checkPermissionResult = new CheckPermissionResult();
        String permission = checkPermissionParam.getPermission();
        if (PermissionNameEnum.READ_CONTACTS.equals(permission)) {
            checkPermissionResult.setGranted(Utils.checkSelfPermission("android.permission.READ_CONTACTS"));
            response.setData(checkPermissionResult);
        } else if (PermissionNameEnum.READ_CALL_LOGS.equals(permission)) {
            checkPermissionResult.setGranted(Utils.checkSelfPermission("android.permission.READ_CALL_LOG"));
            response.setData(checkPermissionResult);
        } else {
            response = new Response(ResultCodeEnum.ERROR);
        }
        iResponseSender.send(response);
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void requestAudioFocus(IResponseSender<BaseResult> iResponseSender) {
        if (this.mAudioFocus.requestAudioFocus(2, 3, null) == 1) {
            iResponseSender.send(new Response(ResultCodeEnum.OK));
        } else {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void releaseAudioFocus(IResponseSender<BaseResult> iResponseSender) {
        this.mAudioFocus.releaseAudioFocus();
        iResponseSender.send(new Response(ResultCodeEnum.OK));
    }
}
