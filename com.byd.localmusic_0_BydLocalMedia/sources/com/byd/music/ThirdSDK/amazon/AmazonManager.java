package com.byd.music.ThirdSDK.amazon;

import android.content.Intent;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: loaded from: classes5.dex */
public class AmazonManager {
    public static final String AmazonAudioFocusPKG = "com.amazon.mp3.automotiveOS";
    public static final String AmazonMemoryPlayAction = "byd.intent.action.AMAZON_MEMORY_PLAY";
    public static final String AmazonNextAction = "byd.intent.action.MEDIA_AMAZON.NEXT";
    public static final String AmazonPKG = "com.android.car.media";
    public static final String AmazonPauseAction = "byd.intent.action.MEDIA_AMAZON.PAUSE";
    public static final String AmazonPlayAction = "byd.intent.action.MEDIA_AMAZON.PLAY";
    public static final String AmazonPreAction = "byd.intent.action.MEDIA_AMAZON.PREVIOUS";

    public static void playOrStop(final boolean z) {
        Utils.getApp().sendBroadcast(new Intent() { // from class: com.byd.music.ThirdSDK.amazon.AmazonManager.1
            {
                setPackage(AmazonManager.AmazonPKG);
                setAction(z ? AmazonManager.AmazonPlayAction : AmazonManager.AmazonPauseAction);
            }
        });
    }

    public static void playNext() {
        Utils.getApp().sendBroadcast(new Intent() { // from class: com.byd.music.ThirdSDK.amazon.AmazonManager.2
            {
                setPackage(AmazonManager.AmazonPKG);
                setAction(AmazonManager.AmazonNextAction);
            }
        });
    }

    public static void playPre() {
        Utils.getApp().sendBroadcast(new Intent() { // from class: com.byd.music.ThirdSDK.amazon.AmazonManager.3
            {
                setPackage(AmazonManager.AmazonPKG);
                setAction(AmazonManager.AmazonPreAction);
            }
        });
    }
}
