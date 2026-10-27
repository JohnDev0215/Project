package com.amazon.mp3.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.util.BauhausDialogBuilder;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PurchasedStorageLocationChangeDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0003\u001a\u00020\u0002H\u0014R3\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/library/dialog/PurchasedStorageLocationChangeDialog;", "Lcom/amazon/mp3/activity/DialogActivity;", "Landroid/app/Dialog;", "createDialog", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "urlMap", "Ljava/util/HashMap;", "getUrlMap", "()Ljava/util/HashMap;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PurchasedStorageLocationChangeDialog extends DialogActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final HashMap<String, String> urlMap = MapsKt__MapsKt.hashMapOf(TuplesKt.to(MusicTerritory.US.value, "https://www.amazon.com/gp/help/customer/display.html?nodeId=GUW62GZFBPEDU7B6"), TuplesKt.to(MusicTerritory.UK.value, "https://www.amazon.co.uk/gp/help/customer/display.html?nodeId=GUW62GZFBPEDU7B6"), TuplesKt.to(MusicTerritory.GERMANY.value, "https://www.amazon.de/gp/help/customer/display.html?nodeId=GUW62GZFBPEDU7B6"), TuplesKt.to(MusicTerritory.JAPAN.value, "https://www.amazon.co.jp/gp/help/customer/display.html?nodeId=GUW62GZFBPEDU7B6"), TuplesKt.to(MusicTerritory.ITALY.value, "https://www.amazon.it/gp/help/customer/display.html?nodeId=GUW62GZFBPEDU7B6"), TuplesKt.to(MusicTerritory.CANADA.value, "https://www.amazon.ca/gp/help/customer/display.html?nodeId=GUW62GZFBPEDU7B6"), TuplesKt.to(MusicTerritory.FRANCE.value, "https://www.amazon.fr/gp/help/customer/display.html?nodeId=GUW62GZFBPEDU7B6"), TuplesKt.to(MusicTerritory.SPAIN.value, "https://www.amazon.es/gp/help/customer/display.html?nodeId=GUW62GZFBPEDU7B6"), TuplesKt.to(MusicTerritory.BRAZIL.value, "https://www.amazon.com.br/gp/help/customer/display.html?nodeId=GUW62GZFBPEDU7B15"), TuplesKt.to(MusicTerritory.MEXICO.value, "https://www.amazon.com.mx/gp/help/customer/display.html?nodeId=GUW62GZFBPEDU7B16"), TuplesKt.to(MusicTerritory.AUSTRALIA.value, "https://www.amazon.com.au/gp/help/customer/display.html?nodeId=GUW62GZFBPEDU7B17"));

    /* JADX INFO: compiled from: PurchasedStorageLocationChangeDialog.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/library/dialog/PurchasedStorageLocationChangeDialog$Companion;", "", "()V", "HAS_SHOWN_PURCHASED_DIALOG", "", "resetDialogShownState", "", "context", "Landroid/content/Context;", "setHasShownPurchasedDialog", "hasShown", "", "shouldShowDialog", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean shouldShowDialog(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return !PreferenceManager.getDefaultSharedPreferences(context).getBoolean("HAS_SHOWN_PURCHASED_DIALOG", false);
        }

        public final void resetDialogShownState(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            setHasShownPurchasedDialog(context, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setHasShownPurchasedDialog(Context context, boolean hasShown) {
            SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
            editorEdit.putBoolean("HAS_SHOWN_PURCHASED_DIALOG", hasShown);
            editorEdit.apply();
        }
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        Dialog dialogCreate = new BauhausDialogBuilder(this).setTitle(R.string.dmusic_purchased_storage_location_title).setMessage(R.string.dmusic_purchased_storage_location_body).setPositiveButton(R.string.dmusic_purchased_storage_location_positive_button, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.PurchasedStorageLocationChangeDialog$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PurchasedStorageLocationChangeDialog.m1231createDialog$lambda0(this.f$0, dialogInterface, i);
            }
        }).setNegativeButton(R.string.dmusic_purchased_storage_location_negative_button, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.PurchasedStorageLocationChangeDialog$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PurchasedStorageLocationChangeDialog.m1232createDialog$lambda1(this.f$0, dialogInterface, i);
            }
        }).create();
        INSTANCE.setHasShownPurchasedDialog(this, true);
        return dialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createDialog$lambda-0, reason: not valid java name */
    public static final void m1231createDialog$lambda0(PurchasedStorageLocationChangeDialog this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createDialog$lambda-1, reason: not valid java name */
    public static final void m1232createDialog$lambda1(PurchasedStorageLocationChangeDialog this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this$0.urlMap.get(AccountDetailUtil.getMusicTerritoryOfResidence()))));
        this$0.finish();
    }
}
