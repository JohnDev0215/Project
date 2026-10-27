package com.ts.qnxapp.util;

import android.os.Bundle;
import com.autolink.animation.animationProto;
import com.ts.qnxapp.fdbusClientForAndroid.FdbusProtoBuilder;
import ipc.fdbus.FdbusMessage;
import ipc.fdbus.FdbusMsgBuilder;

/* JADX INFO: loaded from: classes.dex */
public class AnimationToBundle {
    private static final String ANDROID_STATUS = "AndroidStatus";
    private static volatile AnimationToBundle sInstance;

    public static AnimationToBundle getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (AnimationToBundle.class) {
            if (sInstance == null) {
                sInstance = new AnimationToBundle();
            }
        }
        return sInstance;
    }

    public Bundle messageAnimationToBundle(FdbusMessage msg, int flag) {
        return null;
    }

    public Object getAnimationMessage(int subType, String content, Bundle bundle) {
        if (subType != 1) {
            return null;
        }
        try {
            animationProto.Animation.Builder animationBuilder = animationProto.Animation.newBuilder();
            if (bundle.containsKey(ANDROID_STATUS)) {
                animationProto.AndroidStatus androidStatus = animationProto.AndroidStatus.valueOf(bundle.getInt(ANDROID_STATUS));
                animationBuilder.setAndroidStatus(androidStatus);
            }
            animationProto.Animation req = animationBuilder.build();
            FdbusMsgBuilder msg = new FdbusProtoBuilder(req);
            return msg;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
