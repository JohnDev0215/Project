package com.byd.profile.request;

import android.content.Context;
import com.byd.profile.Constants;
import com.byd.profile.request.cell.AirRequest;
import com.byd.profile.request.cell.AtmosphereRequest;
import com.byd.profile.request.cell.BaseRequest;
import com.byd.profile.request.cell.DoorRequest;
import com.byd.profile.request.cell.HeadlightsRequest;
import com.byd.profile.request.cell.InstrumentRequest;
import com.byd.profile.request.cell.MediaRequest;
import com.byd.profile.request.cell.ReadLightRequest;
import com.byd.profile.request.cell.SeatRequest;
import com.byd.profile.request.cell.WindowRequest;
import com.byd.profile.utils.SharedPreferencesUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RestRequestManager extends RequestManager {
    private static final int BACKREST_PERCENT = 39;
    private static final int CUSHION_PERCENT = 78;
    private static final int FOOTREST_PERCENT = 37;

    @Override // com.byd.profile.request.RequestManager
    public void initRequests(Context context, List<BaseRequest> requestList) {
        BydLogHelper.logD("initRequests");
        if (SharedPreferencesUtil.getBoolean(context, Constants.SP_KEY_MEDIA_REST, true)) {
            requestList.add(new MediaRequest(context, new MediaRequest.MediaValue(SharedPreferencesUtil.getInt(context, Constants.SP_KEY_MEDIA_VOLUME_REST, 5))));
        }
        requestList.add(new AirRequest(context, new AirRequest.AirValue(false)));
        requestList.add(new DoorRequest(context, new DoorRequest.DoorValue()));
        requestList.add(new WindowRequest(context, WindowRequest.WindowValue.createWindowValue(true)));
        requestList.add(new InstrumentRequest(context, new InstrumentRequest.InstrumentValue(0, 1)));
        requestList.add(new AtmosphereRequest(context, new AtmosphereRequest.AtmosphereValue(false, 1)));
        requestList.add(new HeadlightsRequest(context, new HeadlightsRequest.HeadlightsValue(false)));
        requestList.add(new ReadLightRequest(context, new ReadLightRequest.ReadLightValue(1)));
        requestList.add(new SeatRequest(context, new SeatRequest.SeatValue(1, 39, 78, 37)));
    }

    @Override // com.byd.profile.request.RequestManager
    public boolean handleRequest(boolean isWanted) {
        BydLogHelper.logD(isWanted ? ">>>start set nap request" : ">>>start reset nap request");
        boolean zHandleRequest = super.handleRequest(isWanted);
        BydLogHelper.logD(isWanted ? "end set nap request<<<" : "end reset nap request<<<");
        return zHandleRequest;
    }
}
