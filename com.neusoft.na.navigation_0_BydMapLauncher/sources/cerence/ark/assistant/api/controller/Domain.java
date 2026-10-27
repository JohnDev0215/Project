package cerence.ark.assistant.api.controller;

import android.content.Context;
import cerence.ark.assistant.api.Activated;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.core.ActiveZoneListener;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Domain implements Activated, ActiveZoneListener {
    protected String mActiveZone = "";
    private final Dhi mDhi;

    public abstract void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map);

    protected Domain(Dhi dhi) {
        this.mDhi = dhi;
    }

    public Dhi getDhi() {
        return this.mDhi;
    }

    public Context getContext() {
        Dhi dhi = this.mDhi;
        if (dhi == null) {
            return null;
        }
        return dhi.getContext();
    }

    public String getActiveZone() {
        return this.mActiveZone;
    }

    @Override // cerence.ark.assistant.core.ActiveZoneListener
    public void onActiveZoneChanged(String str) {
        this.mActiveZone = str;
    }
}
