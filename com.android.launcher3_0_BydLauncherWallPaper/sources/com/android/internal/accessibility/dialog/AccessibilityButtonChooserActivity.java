package com.android.internal.accessibility.dialog;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.internal.accessibility.util.AccessibilityStatsLogUtils;
import com.android.internal.widget.ResolverDrawerLayout;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AccessibilityButtonChooserActivity extends Activity {
    private final List<AccessibilityTarget> mTargets = new ArrayList();

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.accessibility_button_chooser);
        ResolverDrawerLayout resolverDrawerLayout = (ResolverDrawerLayout) findViewById(R.id.contentPanel);
        if (resolverDrawerLayout != null) {
            resolverDrawerLayout.setOnDismissedListener(new ResolverDrawerLayout.OnDismissedListener() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$gmot0xMy93gmSNfMEeUBrgmZ374
                @Override // com.android.internal.widget.ResolverDrawerLayout.OnDismissedListener
                public final void onDismissed() {
                    this.f$0.finish();
                }
            });
        }
        String string = Settings.Secure.getString(getContentResolver(), Settings.Secure.ACCESSIBILITY_BUTTON_TARGET_COMPONENT);
        boolean zIsTouchExplorationEnabled = ((AccessibilityManager) getSystemService(AccessibilityManager.class)).isTouchExplorationEnabled();
        boolean z = 2 == getResources().getInteger(R.integer.config_navBarInteractionMode);
        if (z) {
            ((TextView) findViewById(R.id.accessibility_button_prompt_prologue)).setText(zIsTouchExplorationEnabled ? R.string.accessibility_gesture_3finger_prompt_text : R.string.accessibility_gesture_prompt_text);
        }
        if (TextUtils.isEmpty(string)) {
            TextView textView = (TextView) findViewById(R.id.accessibility_button_prompt);
            if (z) {
                textView.setText(zIsTouchExplorationEnabled ? R.string.accessibility_gesture_3finger_instructional_text : R.string.accessibility_gesture_instructional_text);
            }
            textView.setVisibility(0);
        }
        this.mTargets.addAll(AccessibilityTargetHelper.getTargets(this, 0));
        GridView gridView = (GridView) findViewById(R.id.accessibility_button_chooser_grid);
        gridView.setAdapter((ListAdapter) new ButtonTargetAdapter(this.mTargets));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.internal.accessibility.dialog.-$$Lambda$AccessibilityButtonChooserActivity$sRE0tCLyfagIWoUnvHF1jMlTUQ8
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                this.f$0.lambda$onCreate$0$AccessibilityButtonChooserActivity(adapterView, view, i, j);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$AccessibilityButtonChooserActivity(AdapterView adapterView, View view, int i, long j) {
        String id = this.mTargets.get(i).getId();
        if (id.equals("com.android.server.accessibility.MagnificationController")) {
            id = AccessibilityShortcutController.MAGNIFICATION_COMPONENT_NAME.flattenToString();
        }
        AccessibilityStatsLogUtils.logAccessibilityButtonLongPressStatus(ComponentName.unflattenFromString(id));
        Settings.Secure.putString(getContentResolver(), Settings.Secure.ACCESSIBILITY_BUTTON_TARGET_COMPONENT, this.mTargets.get(i).getId());
        finish();
    }
}
