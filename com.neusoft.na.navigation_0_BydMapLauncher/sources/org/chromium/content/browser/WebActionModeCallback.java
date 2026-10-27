package org.chromium.content.browser;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.webkit.internal.AssetHelper;
import java.util.List;
import org.chromium.content.R;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: loaded from: classes3.dex */
public class WebActionModeCallback implements ActionMode.Callback {
    public static final int MENU_ITEM_PROCESS_TEXT = 4;
    public static final int MENU_ITEM_SHARE = 1;
    public static final int MENU_ITEM_WEB_SEARCH = 2;
    protected final ActionHandler mActionHandler;
    private final Context mContext;
    private boolean mEditable;
    private boolean mIsDestroyed;
    private boolean mIsInsertion;
    private boolean mIsPasswordType;

    public interface ActionHandler {
        void copy();

        void cut();

        boolean isIncognito();

        boolean isInsertion();

        boolean isSelectActionModeAllowed(int i);

        boolean isSelectionEditable();

        boolean isSelectionPassword();

        void onDestroyActionMode();

        void onGetContentRect(Rect rect);

        void paste();

        void processText(Intent intent);

        void search();

        void selectAll();

        void share();
    }

    public WebActionModeCallback(Context context, ActionHandler actionHandler) {
        this.mContext = context;
        this.mActionHandler = actionHandler;
    }

    protected Context getContext() {
        return this.mContext;
    }

    @Override // android.view.ActionMode.Callback
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.setTitle(DeviceFormFactor.isTablet(getContext()) ? getContext().getString(R.string.actionbar_textselection_title) : null);
        actionMode.setSubtitle((CharSequence) null);
        this.mEditable = this.mActionHandler.isSelectionEditable();
        this.mIsPasswordType = this.mActionHandler.isSelectionPassword();
        this.mIsInsertion = this.mActionHandler.isInsertion();
        createActionMenu(actionMode, menu);
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        boolean zIsSelectionEditable = this.mActionHandler.isSelectionEditable();
        boolean zIsSelectionPassword = this.mActionHandler.isSelectionPassword();
        boolean zIsInsertion = this.mActionHandler.isInsertion();
        if (this.mEditable == zIsSelectionEditable && this.mIsPasswordType == zIsSelectionPassword && this.mIsInsertion == zIsInsertion) {
            return false;
        }
        this.mEditable = zIsSelectionEditable;
        this.mIsPasswordType = zIsSelectionPassword;
        this.mIsInsertion = zIsInsertion;
        menu.clear();
        createActionMenu(actionMode, menu);
        return true;
    }

    private void createActionMenu(ActionMode actionMode, Menu menu) {
        try {
            actionMode.getMenuInflater().inflate(R.menu.select_action_menu, menu);
        } catch (Resources.NotFoundException unused) {
            new MenuInflater(getContext()).inflate(R.menu.select_action_menu, menu);
        }
        if (this.mIsInsertion) {
            menu.removeItem(R.id.select_action_menu_select_all);
            menu.removeItem(R.id.select_action_menu_cut);
            menu.removeItem(R.id.select_action_menu_copy);
            menu.removeItem(R.id.select_action_menu_share);
            menu.removeItem(R.id.select_action_menu_web_search);
            return;
        }
        if (!this.mEditable || !canPaste()) {
            menu.removeItem(R.id.select_action_menu_paste);
        }
        if (!this.mEditable) {
            menu.removeItem(R.id.select_action_menu_cut);
        }
        if (this.mEditable || !this.mActionHandler.isSelectActionModeAllowed(1)) {
            menu.removeItem(R.id.select_action_menu_share);
        }
        if (this.mEditable || this.mActionHandler.isIncognito() || !this.mActionHandler.isSelectActionModeAllowed(2)) {
            menu.removeItem(R.id.select_action_menu_web_search);
        }
        if (this.mIsPasswordType) {
            menu.removeItem(R.id.select_action_menu_copy);
            menu.removeItem(R.id.select_action_menu_cut);
        } else {
            initializeTextProcessingMenu(menu);
        }
    }

    @Override // android.view.ActionMode.Callback
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (this.mIsDestroyed) {
            return true;
        }
        int itemId = menuItem.getItemId();
        int groupId = menuItem.getGroupId();
        if (itemId == R.id.select_action_menu_select_all) {
            this.mActionHandler.selectAll();
        } else if (itemId == R.id.select_action_menu_cut) {
            this.mActionHandler.cut();
            actionMode.finish();
        } else if (itemId == R.id.select_action_menu_copy) {
            this.mActionHandler.copy();
            actionMode.finish();
        } else if (itemId == R.id.select_action_menu_paste) {
            this.mActionHandler.paste();
            actionMode.finish();
        } else if (itemId == R.id.select_action_menu_share) {
            this.mActionHandler.share();
            actionMode.finish();
        } else if (itemId == R.id.select_action_menu_web_search) {
            this.mActionHandler.search();
            actionMode.finish();
        } else {
            if (groupId != R.id.select_action_menu_text_processing_menus) {
                return false;
            }
            this.mActionHandler.processText(menuItem.getIntent());
        }
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public void onDestroyActionMode(ActionMode actionMode) {
        this.mIsDestroyed = true;
        this.mActionHandler.onDestroyActionMode();
    }

    public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
        if (this.mIsDestroyed) {
            return;
        }
        this.mActionHandler.onGetContentRect(rect);
    }

    private boolean canPaste() {
        return ((ClipboardManager) getContext().getSystemService("clipboard")).hasPrimaryClip();
    }

    private void initializeTextProcessingMenu(Menu menu) {
        if (this.mActionHandler.isSelectActionModeAllowed(4)) {
            List<ResolveInfo> listQueryIntentActivities = getContext().getPackageManager().queryIntentActivities(createProcessTextIntent(), 0);
            for (int i = 0; i < listQueryIntentActivities.size(); i++) {
                ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
                menu.add(R.id.select_action_menu_text_processing_menus, 0, i, resolveInfo.loadLabel(getContext().getPackageManager())).setIntent(createProcessTextIntentForResolveInfo(resolveInfo)).setShowAsAction(1);
            }
        }
    }

    private Intent createProcessTextIntent() {
        return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType(AssetHelper.DEFAULT_MIME_TYPE);
    }

    private Intent createProcessTextIntentForResolveInfo(ResolveInfo resolveInfo) {
        return createProcessTextIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !this.mActionHandler.isSelectionEditable()).setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
    }
}
