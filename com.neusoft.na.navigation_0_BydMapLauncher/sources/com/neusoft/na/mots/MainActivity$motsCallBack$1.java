package com.neusoft.na.mots;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.navigation.IMotsServiceCallBack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016¨\u0006\n"}, d2 = {"com/neusoft/na/mots/MainActivity$motsCallBack$1", "Lcom/neusoft/na/navigation/IMotsServiceCallBack$Stub;", "onDayNightModeChanged", "", ServiceBase.PAYLOAD_MODE, "", "onPlaySoundEffect", "onWebViewShowHmi", "onXWalkInitFailed", "onXWalkViewShowHmi", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MainActivity$motsCallBack$1 extends IMotsServiceCallBack.Stub {
    final /* synthetic */ MainActivity this$0;

    @Override // com.neusoft.na.navigation.IMotsServiceCallBack
    public void onDayNightModeChanged(int mode) {
    }

    MainActivity$motsCallBack$1(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    @Override // com.neusoft.na.navigation.IMotsServiceCallBack
    public void onXWalkViewShowHmi() {
        Log.i(MainActivity.TAG, "onXWalkViewShowHmi");
        this.this$0.xWalkViewShowHmi();
    }

    @Override // com.neusoft.na.navigation.IMotsServiceCallBack
    public void onWebViewShowHmi() {
        Log.i(MainActivity.TAG, "onWebViewShowHmi");
        this.this$0.webViewShowHmi();
    }

    @Override // com.neusoft.na.navigation.IMotsServiceCallBack
    public void onPlaySoundEffect() {
        Log.i(MainActivity.TAG, "onPlaySoundEffect");
        ((Button) this.this$0._$_findCachedViewById(R.id.soundButton)).playSoundEffect(0);
    }

    @Override // com.neusoft.na.navigation.IMotsServiceCallBack
    public void onXWalkInitFailed() {
        final MainActivity mainActivity = this.this$0;
        new AlertDialog.Builder(this.this$0).setMessage("This application needs Crosswalk to run! Please install from Play Store or download manually.").setPositiveButton("okay", new DialogInterface.OnClickListener() { // from class: com.neusoft.na.mots.MainActivity$motsCallBack$1$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity$motsCallBack$1.onXWalkInitFailed$lambda$2(mainActivity, dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onXWalkInitFailed$lambda$2(MainActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=org.xwalk.core"));
        intent.addFlags(268435456);
        intent.addFlags(2097152);
        intent.addFlags(67108864);
        if (intent.resolveActivity(this$0.getPackageManager()) != null) {
            this$0.startActivity(intent);
        } else {
            this$0.finish();
        }
    }
}
