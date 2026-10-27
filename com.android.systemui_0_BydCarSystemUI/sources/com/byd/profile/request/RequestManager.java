package com.byd.profile.request;

import android.content.Context;
import com.byd.profile.request.cell.BaseRequest;
import com.byd.profile.request.cell.RequestValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class RequestManager {
    protected RequestValue mCurrentValue;
    protected List<BaseRequest> mRequestList = new ArrayList();
    protected RequestValue mWantedValue;

    public abstract void initRequests(Context context, List<BaseRequest> requestList);

    protected RequestManager() {
    }

    public boolean setWanted(Context context) {
        this.mRequestList.clear();
        initRequests(context, this.mRequestList);
        return handleRequest(true);
    }

    public boolean reset(Context context) {
        return handleRequest(false);
    }

    public boolean handleRequest(boolean isWanted) {
        Iterator<BaseRequest> it = this.mRequestList.iterator();
        while (it.hasNext()) {
            it.next().execute(isWanted);
        }
        return true;
    }
}
