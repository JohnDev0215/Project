package com.byd.profile.request.cell;

import android.content.Context;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class MusicRequest extends BaseRequest<MusicValue> {
    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(MusicValue requestValue) {
        return true;
    }

    public MusicRequest(Context context, MusicValue wantedValue) {
        super(context, wantedValue);
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(MusicValue wantValue) {
        BydLogHelper.logD("handleRequest MusicValue");
    }

    public static class MusicValue extends BaseValue {
        private boolean isPlay;

        public MusicValue(boolean isPlay) {
            this.isPlay = isPlay;
        }
    }
}
