package cerence.ark.assistant.api.controller;

import cerence.ark.assistant.api.Activated;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class Domain implements Activated {
    private final Dhi mDhi;

    public abstract void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map);

    protected Domain(Dhi dhi) {
        this.mDhi = dhi;
    }

    public Dhi getDhi() {
        return this.mDhi;
    }
}
