package com.neusoft.na.byd;

import android.util.Log;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyReqGPSInfo;
import api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests;
import api.requests.com.neusoft.byd.bydnavapi.ReqOnGPSInfo;
import api.structs.com.neusoft.byd.GPSAuthorizedStatus;
import api.structs.com.neusoft.byd.GPSInfo;
import com.bydux.api.common.tools.util.ListUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Byd8155fSystem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lapi/notifications/com/neusoft/byd/bydnavapi/NotifyReqGPSInfo;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
final class Byd8155fSystem$onServerConnect$11$1 extends Lambda implements Function1<NotifyReqGPSInfo, Unit> {
    final /* synthetic */ Byd8155fSystem this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Byd8155fSystem$onServerConnect$11$1(Byd8155fSystem byd8155fSystem) {
        super(1);
        this.this$0 = byd8155fSystem;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(NotifyReqGPSInfo notifyReqGPSInfo) {
        invoke2(notifyReqGPSInfo);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(NotifyReqGPSInfo notifyReqGPSInfo) {
        Log.d("Byd8155fSystem", "NotifyReqGPSInfo");
        final ReqOnGPSInfo reqOnGPSInfo = new ReqOnGPSInfo(notifyReqGPSInfo.getReqGPSInfo().getTokenId(), new GPSInfo());
        final Byd8155fSystem byd8155fSystem = this.this$0;
        new Thread(new Runnable() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$11$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Byd8155fSystem$onServerConnect$11$1.invoke$lambda$0(reqOnGPSInfo, byd8155fSystem);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(ReqOnGPSInfo reqOnGPSInfo, Byd8155fSystem this$0) {
        Intrinsics.checkNotNullParameter(reqOnGPSInfo, "$reqOnGPSInfo");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 20981), 0);
            InputStream inputStream = socket.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "socket.getInputStream()");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String data = bufferedReader.readLine();
            bufferedReader.close();
            inputStream.close();
            socket.close();
            if (Intrinsics.areEqual(data, "-1")) {
                Log.i("Byd8155fSystem", "activateLocation == null");
                reqOnGPSInfo.getArguments().getGPSInfo().setStatus(GPSAuthorizedStatus.E_GPS_UNAUTHORIZED);
            } else {
                Intrinsics.checkNotNullExpressionValue(data, "data");
                List listSplit$default = StringsKt.split$default((CharSequence) data, new String[]{ListUtils.DEFAULT_JOIN_SEPARATOR}, false, 0, 6, (Object) null);
                Log.i("Byd8155fSystem", "longitude == " + ((String) listSplit$default.get(0)) + " , latitude == " + ((String) listSplit$default.get(1)));
                reqOnGPSInfo.getArguments().getGPSInfo().setStatus(GPSAuthorizedStatus.E_GPS_AUTHORIZED);
                reqOnGPSInfo.getArguments().getGPSInfo().getGeoPosition().setLongitude(Double.valueOf(Double.parseDouble((String) listSplit$default.get(0))));
                reqOnGPSInfo.getArguments().getGPSInfo().getGeoPosition().setLatitude(Double.valueOf(Double.parseDouble((String) listSplit$default.get(1))));
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Byd8155fSystem", String.valueOf(e.getMessage()));
            reqOnGPSInfo.getArguments().getGPSInfo().setStatus(GPSAuthorizedStatus.E_UNKNOWN);
        }
        BydNavApiRequests bydNavApiRequests = this$0.bydSysApiRequests;
        if (bydNavApiRequests != null) {
            bydNavApiRequests.fireOnGPSInfo(reqOnGPSInfo);
        }
        Log.d("Byd8155fSystem", "NotifyReqGPSInfo end");
    }
}
