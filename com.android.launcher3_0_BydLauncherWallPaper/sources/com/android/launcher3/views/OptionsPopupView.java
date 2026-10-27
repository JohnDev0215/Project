package com.android.launcher3.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.popup.ArrowPopup;
import com.android.launcher3.shortcuts.DeepShortcutView;
import com.android.launcher3.widget.WidgetsFullSheet;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class OptionsPopupView extends ArrowPopup implements View.OnClickListener, View.OnLongClickListener {
    private final ArrayMap<View, OptionItem> mItemMap;
    private RectF mTargetRect;

    @Override // com.android.launcher3.AbstractFloatingView
    protected boolean isOfType(int type) {
        return (type & 1024) != 0;
    }

    @Override // com.android.launcher3.AbstractFloatingView
    public void logActionCommand(int command) {
    }

    public OptionsPopupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OptionsPopupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mItemMap = new ArrayMap<>();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        handleViewClick(view, 0);
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        return handleViewClick(view, 1);
    }

    private boolean handleViewClick(View view, int action) {
        OptionItem optionItem = this.mItemMap.get(view);
        if (optionItem == null) {
            return false;
        }
        if (optionItem.mControlTypeForLog > 0) {
            logTap(action, optionItem.mControlTypeForLog);
        }
        if (!optionItem.mClickListener.onLongClick(view)) {
            return false;
        }
        close(true);
        return true;
    }

    private void logTap(int action, int controlType) {
        this.mLauncher.getUserEventDispatcher().logActionOnControl(action, controlType);
    }

    @Override // com.android.launcher3.util.TouchController
    public boolean onControllerInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() != 0 || getPopupContainer().isEventOverView(this, ev)) {
            return false;
        }
        close(true);
        return true;
    }

    @Override // com.android.launcher3.popup.ArrowPopup
    protected void getTargetObjectLocation(Rect outPos) {
        this.mTargetRect.roundOut(outPos);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static void show(Launcher launcher, RectF targetRect, List<OptionItem> items) {
        OptionsPopupView optionsPopupView = (OptionsPopupView) launcher.getLayoutInflater().inflate(R.layout.longpress_options_menu, (ViewGroup) launcher.getDragLayer(), false);
        optionsPopupView.mTargetRect = targetRect;
        for (OptionItem optionItem : items) {
            DeepShortcutView deepShortcutView = (DeepShortcutView) optionsPopupView.inflateAndAdd(R.layout.system_shortcut, optionsPopupView);
            deepShortcutView.getIconView().setBackgroundResource(optionItem.mIconRes);
            deepShortcutView.getBubbleText().setText(optionItem.mLabelRes);
            deepShortcutView.setDividerVisibility(4);
            deepShortcutView.setOnClickListener(optionsPopupView);
            deepShortcutView.setOnLongClickListener(optionsPopupView);
            optionsPopupView.mItemMap.put(deepShortcutView, optionItem);
        }
        optionsPopupView.reorderAndShow(optionsPopupView.getChildCount());
    }

    public static ArrowPopup getOptionsPopup(Launcher launcher) {
        return (ArrowPopup) launcher.findViewById(R.id.deep_shortcuts_container);
    }

    public static void showDefaultOptions(Launcher launcher, float x, float y) {
        float dimension = launcher.getResources().getDimension(R.dimen.options_menu_thumb_size) / 2.0f;
        if (x < 0.0f || y < 0.0f) {
            x = launcher.getDragLayer().getWidth() / 2;
            y = launcher.getDragLayer().getHeight() / 2;
        }
        RectF rectF = new RectF(x - dimension, y - dimension, x + dimension, y + dimension);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OptionItem(Utilities.existsStyleWallpapers(launcher) ? R.string.styles_wallpaper_button_text : R.string.wallpaper_button_text, Utilities.existsStyleWallpapers(launcher) ? R.drawable.ic_palette : R.drawable.ic_wallpaper, 3, new View.OnLongClickListener() { // from class: com.android.launcher3.views.-$$Lambda$NF9IRgQh6XF9g8qF1c_COh67JiE
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return OptionsPopupView.startWallpaperPicker(view);
            }
        }));
        arrayList.add(new OptionItem(R.string.widget_button_text, R.drawable.ic_widget, 2, new View.OnLongClickListener() { // from class: com.android.launcher3.views.-$$Lambda$pAmZ81tgdasP-vyIfkVDZFKhjAk
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return OptionsPopupView.onWidgetsClicked(view);
            }
        }));
        arrayList.add(new OptionItem(R.string.settings_button_text, R.drawable.ic_setting, 4, new View.OnLongClickListener() { // from class: com.android.launcher3.views.-$$Lambda$6qGgmQNuWcO7ZripzAwtPnpRTgk
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return OptionsPopupView.startSettings(view);
            }
        }));
        show(launcher, rectF, arrayList);
    }

    public static boolean onWidgetsClicked(View view) {
        return openWidgets(Launcher.getLauncher(view.getContext()));
    }

    public static boolean openWidgets(Launcher launcher) {
        if (launcher.getPackageManager().isSafeMode()) {
            Toast.makeText(launcher, R.string.safemode_widget_error, 0).show();
            return false;
        }
        WidgetsFullSheet.show(launcher, true);
        return true;
    }

    public static boolean startSettings(View view) {
        Launcher launcher = Launcher.getLauncher(view.getContext());
        launcher.startActivity(new Intent(Intent.ACTION_APPLICATION_PREFERENCES).setPackage(launcher.getPackageName()).addFlags(268435456));
        return true;
    }

    public static boolean startWallpaperPicker(View v) {
        Launcher launcher = Launcher.getLauncher(v.getContext());
        if (!Utilities.isWallpaperAllowed(launcher)) {
            Toast.makeText(launcher, R.string.msg_disabled_by_admin, 0).show();
            return false;
        }
        Intent intentPutExtra = new Intent(Intent.ACTION_SET_WALLPAPER).addFlags(32768).putExtra(Utilities.EXTRA_WALLPAPER_OFFSET, launcher.getWorkspace().getWallpaperOffsetForCenterPage());
        if (!Utilities.existsStyleWallpapers(launcher)) {
            intentPutExtra.putExtra(Utilities.EXTRA_WALLPAPER_FLAVOR, "wallpaper_only");
        } else {
            intentPutExtra.putExtra(Utilities.EXTRA_WALLPAPER_FLAVOR, "focus_wallpaper");
        }
        String string = launcher.getString(R.string.wallpaper_picker_package);
        if (!TextUtils.isEmpty(string)) {
            intentPutExtra.setPackage(string);
        }
        return launcher.lambda$startActivitySafely$0$Launcher(v, intentPutExtra, null, null);
    }

    public static class OptionItem {
        private final View.OnLongClickListener mClickListener;
        private final int mControlTypeForLog;
        private final int mIconRes;
        private final int mLabelRes;

        public OptionItem(int labelRes, int iconRes, int controlTypeForLog, View.OnLongClickListener clickListener) {
            this.mLabelRes = labelRes;
            this.mIconRes = iconRes;
            this.mControlTypeForLog = controlTypeForLog;
            this.mClickListener = clickListener;
        }
    }
}
