package com.permissionx.guolindev.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.permissionx.guolindev.R;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0002J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0016\u001a\u00020\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u000fH\u0002J\b\u0010\u001a\u001a\u00020\u000fH\u0002R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/permissionx/guolindev/dialog/DefaultDialog;", "Lcom/permissionx/guolindev/dialog/RationaleDialog;", "context", "Landroid/content/Context;", "permissions", "", "", "message", "positiveText", "negativeText", "lightColor", "", "darkColor", "(Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "buildPermissionsLayout", "", "getNegativeButton", "Landroid/view/View;", "getPermissionsToRequest", "getPositiveButton", "isDarkTheme", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupText", "setupWindow", "library_release"}, k = 1, mv = {1, 4, 0})
public final class DefaultDialog extends RationaleDialog {
    private final int darkColor;
    private final int lightColor;
    private final String message;
    private final String negativeText;
    private final List<String> permissions;
    private final String positiveText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultDialog(Context context, List<String> permissions, String message, String positiveText, String str, int i, int i2) {
        super(context, R.style.PermissionXDefaultDialog);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(positiveText, "positiveText");
        this.permissions = permissions;
        this.message = message;
        this.positiveText = positiveText;
        this.negativeText = str;
        this.lightColor = i;
        this.darkColor = i2;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissionx_default_dialog_layout);
        setupText();
        buildPermissionsLayout();
        setupWindow();
    }

    @Override // com.permissionx.guolindev.dialog.RationaleDialog
    public View getPositiveButton() {
        Button positiveBtn = (Button) findViewById(R.id.positiveBtn);
        Intrinsics.checkNotNullExpressionValue(positiveBtn, "positiveBtn");
        return positiveBtn;
    }

    @Override // com.permissionx.guolindev.dialog.RationaleDialog
    public View getNegativeButton() {
        if (this.negativeText == null) {
            return null;
        }
        return (Button) findViewById(R.id.negativeBtn);
    }

    @Override // com.permissionx.guolindev.dialog.RationaleDialog
    public List<String> getPermissionsToRequest() {
        return this.permissions;
    }

    private final void setupText() {
        TextView messageText = (TextView) findViewById(R.id.messageText);
        Intrinsics.checkNotNullExpressionValue(messageText, "messageText");
        messageText.setText(this.message);
        Button positiveBtn = (Button) findViewById(R.id.positiveBtn);
        Intrinsics.checkNotNullExpressionValue(positiveBtn, "positiveBtn");
        positiveBtn.setText(this.positiveText);
        if (this.negativeText != null) {
            LinearLayout negativeLayout = (LinearLayout) findViewById(R.id.negativeLayout);
            Intrinsics.checkNotNullExpressionValue(negativeLayout, "negativeLayout");
            negativeLayout.setVisibility(0);
            Button negativeBtn = (Button) findViewById(R.id.negativeBtn);
            Intrinsics.checkNotNullExpressionValue(negativeBtn, "negativeBtn");
            negativeBtn.setText(this.negativeText);
        } else {
            LinearLayout negativeLayout2 = (LinearLayout) findViewById(R.id.negativeLayout);
            Intrinsics.checkNotNullExpressionValue(negativeLayout2, "negativeLayout");
            negativeLayout2.setVisibility(8);
        }
        if (isDarkTheme()) {
            if (this.darkColor != -1) {
                ((Button) findViewById(R.id.positiveBtn)).setTextColor(this.darkColor);
                ((Button) findViewById(R.id.negativeBtn)).setTextColor(this.darkColor);
                return;
            }
            return;
        }
        if (this.lightColor != -1) {
            ((Button) findViewById(R.id.positiveBtn)).setTextColor(this.lightColor);
            ((Button) findViewById(R.id.negativeBtn)).setTextColor(this.lightColor);
        }
    }

    private final void buildPermissionsLayout() {
        String str;
        HashSet hashSet = new HashSet();
        int i = Build.VERSION.SDK_INT;
        for (String str2 : this.permissions) {
            if (i == 29) {
                str = PermissionMapKt.getPermissionMapOnQ().get(str2);
            } else if (i == 30) {
                str = PermissionMapKt.getPermissionMapOnR().get(str2);
            } else {
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                str = context.getPackageManager().getPermissionInfo(str2, 0).group;
            }
            if (str != null && !hashSet.contains(str)) {
                View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.permissionx_permission_item, (ViewGroup) findViewById(R.id.permissionsLayout), false);
                if (viewInflate == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
                }
                LinearLayout linearLayout = (LinearLayout) viewInflate;
                TextView textView = (TextView) linearLayout.findViewById(R.id.permissionText);
                Intrinsics.checkNotNullExpressionValue(textView, "layout.permissionText");
                Context context2 = getContext();
                Context context3 = getContext();
                Intrinsics.checkNotNullExpressionValue(context3, "context");
                textView.setText(context2.getString(context3.getPackageManager().getPermissionGroupInfo(str, 0).labelRes));
                ImageView imageView = (ImageView) linearLayout.findViewById(R.id.permissionIcon);
                Context context4 = getContext();
                Intrinsics.checkNotNullExpressionValue(context4, "context");
                imageView.setImageResource(context4.getPackageManager().getPermissionGroupInfo(str, 0).icon);
                if (isDarkTheme()) {
                    if (this.darkColor != -1) {
                        ((ImageView) linearLayout.findViewById(R.id.permissionIcon)).setColorFilter(this.darkColor, PorterDuff.Mode.SRC_ATOP);
                    }
                } else if (this.lightColor != -1) {
                    ((ImageView) linearLayout.findViewById(R.id.permissionIcon)).setColorFilter(this.lightColor, PorterDuff.Mode.SRC_ATOP);
                }
                ((LinearLayout) findViewById(R.id.permissionsLayout)).addView(linearLayout);
                hashSet.add(str);
            }
        }
    }

    private final void setupWindow() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "context.resources");
        int i = resources.getDisplayMetrics().widthPixels;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        Resources resources2 = context2.getResources();
        Intrinsics.checkNotNullExpressionValue(resources2, "context.resources");
        if (i < resources2.getDisplayMetrics().heightPixels) {
            Window it = getWindow();
            if (it != null) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                WindowManager.LayoutParams attributes = it.getAttributes();
                it.setGravity(17);
                attributes.width = (int) (((double) i) * 0.86d);
                it.setAttributes(attributes);
                return;
            }
            return;
        }
        Window it2 = getWindow();
        if (it2 != null) {
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            WindowManager.LayoutParams attributes2 = it2.getAttributes();
            it2.setGravity(17);
            attributes2.width = (int) (((double) i) * 0.6d);
            it2.setAttributes(attributes2);
        }
    }

    private final boolean isDarkTheme() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "context.resources");
        return (resources.getConfiguration().uiMode & 48) == 32;
    }
}
