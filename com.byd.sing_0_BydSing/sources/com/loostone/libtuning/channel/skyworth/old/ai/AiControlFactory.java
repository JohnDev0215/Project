package com.loostone.libtuning.channel.skyworth.old.ai;

import android.content.Context;
import com.loostone.libtuning.channel.skyworth.old.ai.impl.CommonAiControl;

/* JADX INFO: loaded from: classes.dex */
public class AiControlFactory {
    private static IAiControl mAiControl;

    public static synchronized void createAiControl(Context context, int i) {
        mAiControl = new CommonAiControl(context.getApplicationContext());
    }

    public static IAiControl getAiControl() {
        return mAiControl;
    }
}
