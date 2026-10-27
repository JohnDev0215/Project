package androidx.leanback.widget;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;
import androidx.leanback.widget.GuidedActionAutofillSupport;
import androidx.leanback.widget.GuidedActionsStylist;
import androidx.leanback.widget.ImeKeyMonitor;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GuidedActionAdapter extends RecyclerView.Adapter {
    static final boolean DEBUG = false;
    static final boolean DEBUG_EDIT = false;
    static final String TAG = "GuidedActionAdapter";
    static final String TAG_EDIT = "EditableAction";
    private final ActionAutofillListener mActionAutofillListener;
    private final ActionEditListener mActionEditListener;
    private final ActionOnFocusListener mActionOnFocusListener;
    private final ActionOnKeyListener mActionOnKeyListener;
    final List<GuidedAction> mActions;
    private ClickListener mClickListener;
    DiffCallback<GuidedAction> mDiffCallback;
    GuidedActionAdapterGroup mGroup;
    private final boolean mIsSubAdapter;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: androidx.leanback.widget.GuidedActionAdapter.1
        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (v != null && v.getWindowToken() != null && GuidedActionAdapter.this.getRecyclerView() != null) {
                GuidedActionsStylist.ViewHolder avh = (GuidedActionsStylist.ViewHolder) GuidedActionAdapter.this.getRecyclerView().getChildViewHolder(v);
                GuidedAction action = avh.getAction();
                if (action.hasTextEditable()) {
                    GuidedActionAdapter.this.mGroup.openIme(GuidedActionAdapter.this, avh);
                    return;
                }
                if (action.hasEditableActivatorView()) {
                    GuidedActionAdapter.this.performOnActionClick(avh);
                    return;
                }
                GuidedActionAdapter.this.handleCheckedActions(avh);
                if (action.isEnabled() && !action.infoOnly()) {
                    GuidedActionAdapter.this.performOnActionClick(avh);
                }
            }
        }
    };
    final GuidedActionsStylist mStylist;

    public interface ClickListener {
        void onGuidedActionClicked(GuidedAction guidedAction);
    }

    public interface EditListener {
        void onGuidedActionEditCanceled(GuidedAction guidedAction);

        long onGuidedActionEditedAndProceed(GuidedAction guidedAction);

        void onImeClose();

        void onImeOpen();
    }

    public interface FocusListener {
        void onGuidedActionFocused(GuidedAction guidedAction);
    }

    public GuidedActionAdapter(List<GuidedAction> actions, ClickListener clickListener, FocusListener focusListener, GuidedActionsStylist presenter, boolean isSubAdapter) {
        this.mActions = actions == null ? new ArrayList() : new ArrayList(actions);
        this.mClickListener = clickListener;
        this.mStylist = presenter;
        this.mActionOnKeyListener = new ActionOnKeyListener();
        this.mActionOnFocusListener = new ActionOnFocusListener(focusListener);
        this.mActionEditListener = new ActionEditListener();
        this.mActionAutofillListener = new ActionAutofillListener();
        this.mIsSubAdapter = isSubAdapter;
        if (!isSubAdapter) {
            this.mDiffCallback = GuidedActionDiffCallback.getInstance();
        }
    }

    public void setDiffCallback(DiffCallback<GuidedAction> diffCallback) {
        this.mDiffCallback = diffCallback;
    }

    public void setActions(List<GuidedAction> actions) {
        if (!this.mIsSubAdapter) {
            this.mStylist.collapseAction(false);
        }
        this.mActionOnFocusListener.unFocus();
        if (this.mDiffCallback != null) {
            final List<GuidedAction> oldActions = new ArrayList<>();
            oldActions.addAll(this.mActions);
            this.mActions.clear();
            this.mActions.addAll(actions);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() { // from class: androidx.leanback.widget.GuidedActionAdapter.2
                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int getOldListSize() {
                    return oldActions.size();
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int getNewListSize() {
                    return GuidedActionAdapter.this.mActions.size();
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return GuidedActionAdapter.this.mDiffCallback.areItemsTheSame((GuidedAction) oldActions.get(oldItemPosition), GuidedActionAdapter.this.mActions.get(newItemPosition));
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return GuidedActionAdapter.this.mDiffCallback.areContentsTheSame((GuidedAction) oldActions.get(oldItemPosition), GuidedActionAdapter.this.mActions.get(newItemPosition));
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public Object getChangePayload(int oldItemPosition, int newItemPosition) {
                    return GuidedActionAdapter.this.mDiffCallback.getChangePayload((GuidedAction) oldActions.get(oldItemPosition), GuidedActionAdapter.this.mActions.get(newItemPosition));
                }
            });
            diffResult.dispatchUpdatesTo(this);
            return;
        }
        this.mActions.clear();
        this.mActions.addAll(actions);
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mActions.size();
    }

    public GuidedAction getItem(int position) {
        return this.mActions.get(position);
    }

    public int indexOf(GuidedAction action) {
        return this.mActions.indexOf(action);
    }

    public GuidedActionsStylist getGuidedActionsStylist() {
        return this.mStylist;
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public void setFocusListener(FocusListener focusListener) {
        this.mActionOnFocusListener.setFocusListener(focusListener);
    }

    public List<GuidedAction> getActions() {
        return new ArrayList(this.mActions);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.mStylist.getItemViewType(this.mActions.get(position));
    }

    RecyclerView getRecyclerView() {
        return this.mIsSubAdapter ? this.mStylist.getSubActionsGridView() : this.mStylist.getActionsGridView();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GuidedActionsStylist.ViewHolder vh = this.mStylist.onCreateViewHolder(parent, viewType);
        View v = vh.itemView;
        v.setOnKeyListener(this.mActionOnKeyListener);
        v.setOnClickListener(this.mOnClickListener);
        v.setOnFocusChangeListener(this.mActionOnFocusListener);
        setupListeners(vh.getEditableTitleView());
        setupListeners(vh.getEditableDescriptionView());
        return vh;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setupListeners(EditText editText) {
        if (editText != 0) {
            editText.setPrivateImeOptions("escapeNorth");
            editText.setOnEditorActionListener(this.mActionEditListener);
            if (editText instanceof ImeKeyMonitor) {
                ImeKeyMonitor monitor = (ImeKeyMonitor) editText;
                monitor.setImeKeyListener(this.mActionEditListener);
            }
            if (editText instanceof GuidedActionAutofillSupport) {
                ((GuidedActionAutofillSupport) editText).setOnAutofillListener(this.mActionAutofillListener);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= this.mActions.size()) {
            return;
        }
        GuidedActionsStylist.ViewHolder avh = (GuidedActionsStylist.ViewHolder) holder;
        GuidedAction action = this.mActions.get(position);
        this.mStylist.onBindViewHolder(avh, action);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mActions.size();
    }

    private class ActionOnFocusListener implements View.OnFocusChangeListener {
        private FocusListener mFocusListener;
        private View mSelectedView;

        ActionOnFocusListener(FocusListener focusListener) {
            this.mFocusListener = focusListener;
        }

        public void setFocusListener(FocusListener focusListener) {
            this.mFocusListener = focusListener;
        }

        public void unFocus() {
            if (this.mSelectedView != null && GuidedActionAdapter.this.getRecyclerView() != null) {
                RecyclerView.ViewHolder vh = GuidedActionAdapter.this.getRecyclerView().getChildViewHolder(this.mSelectedView);
                if (vh != null) {
                    GuidedActionsStylist.ViewHolder avh = (GuidedActionsStylist.ViewHolder) vh;
                    GuidedActionAdapter.this.mStylist.onAnimateItemFocused(avh, false);
                } else {
                    Log.w(GuidedActionAdapter.TAG, "RecyclerView returned null view holder", new Throwable());
                }
            }
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            if (GuidedActionAdapter.this.getRecyclerView() == null) {
                return;
            }
            GuidedActionsStylist.ViewHolder avh = (GuidedActionsStylist.ViewHolder) GuidedActionAdapter.this.getRecyclerView().getChildViewHolder(v);
            if (hasFocus) {
                this.mSelectedView = v;
                FocusListener focusListener = this.mFocusListener;
                if (focusListener != null) {
                    focusListener.onGuidedActionFocused(avh.getAction());
                }
            } else if (this.mSelectedView == v) {
                GuidedActionAdapter.this.mStylist.onAnimateItemPressedCancelled(avh);
                this.mSelectedView = null;
            }
            GuidedActionAdapter.this.mStylist.onAnimateItemFocused(avh, hasFocus);
        }
    }

    public GuidedActionsStylist.ViewHolder findSubChildViewHolder(View v) {
        if (getRecyclerView() == null) {
            return null;
        }
        ViewParent parent = v.getParent();
        while (parent != getRecyclerView() && parent != null && v != null) {
            v = parent;
            parent = parent.getParent();
        }
        if (parent == null || v == null) {
            return null;
        }
        GuidedActionsStylist.ViewHolder result = (GuidedActionsStylist.ViewHolder) getRecyclerView().getChildViewHolder(v);
        return result;
    }

    public void handleCheckedActions(GuidedActionsStylist.ViewHolder avh) {
        GuidedAction action = avh.getAction();
        int actionCheckSetId = action.getCheckSetId();
        if (getRecyclerView() != null && actionCheckSetId != 0) {
            if (actionCheckSetId != -1) {
                int size = this.mActions.size();
                for (int i = 0; i < size; i++) {
                    GuidedAction a = this.mActions.get(i);
                    if (a != action && a.getCheckSetId() == actionCheckSetId && a.isChecked()) {
                        a.setChecked(false);
                        GuidedActionsStylist.ViewHolder vh = (GuidedActionsStylist.ViewHolder) getRecyclerView().findViewHolderForPosition(i);
                        if (vh != null) {
                            this.mStylist.onAnimateItemChecked(vh, false);
                        }
                    }
                }
            }
            if (!action.isChecked()) {
                action.setChecked(true);
                this.mStylist.onAnimateItemChecked(avh, true);
            } else if (actionCheckSetId == -1) {
                action.setChecked(false);
                this.mStylist.onAnimateItemChecked(avh, false);
            }
        }
    }

    public void performOnActionClick(GuidedActionsStylist.ViewHolder avh) {
        ClickListener clickListener = this.mClickListener;
        if (clickListener != null) {
            clickListener.onGuidedActionClicked(avh.getAction());
        }
    }

    private class ActionOnKeyListener implements View.OnKeyListener {
        private boolean mKeyPressed = false;

        ActionOnKeyListener() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (v == null || event == null || GuidedActionAdapter.this.getRecyclerView() == null) {
                return false;
            }
            if (keyCode == 23 || keyCode == 66 || keyCode == 160 || keyCode == 99 || keyCode == 100) {
                GuidedActionsStylist.ViewHolder avh = (GuidedActionsStylist.ViewHolder) GuidedActionAdapter.this.getRecyclerView().getChildViewHolder(v);
                GuidedAction action = avh.getAction();
                if (!action.isEnabled() || action.infoOnly()) {
                    event.getAction();
                    return true;
                }
                int action2 = event.getAction();
                if (action2 == 0) {
                    if (!this.mKeyPressed) {
                        this.mKeyPressed = true;
                        GuidedActionAdapter.this.mStylist.onAnimateItemPressed(avh, this.mKeyPressed);
                    }
                } else if (action2 == 1 && this.mKeyPressed) {
                    this.mKeyPressed = false;
                    GuidedActionAdapter.this.mStylist.onAnimateItemPressed(avh, this.mKeyPressed);
                }
            }
            return false;
        }
    }

    private class ActionEditListener implements TextView.OnEditorActionListener, ImeKeyMonitor.ImeKeyListener {
        ActionEditListener() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == 5 || actionId == 6) {
                GuidedActionAdapter.this.mGroup.fillAndGoNext(GuidedActionAdapter.this, v);
                return true;
            }
            if (actionId != 1) {
                return false;
            }
            GuidedActionAdapter.this.mGroup.fillAndStay(GuidedActionAdapter.this, v);
            return true;
        }

        @Override // androidx.leanback.widget.ImeKeyMonitor.ImeKeyListener
        public boolean onKeyPreIme(EditText editText, int keyCode, KeyEvent event) {
            if (keyCode == 4 && event.getAction() == 1) {
                GuidedActionAdapter.this.mGroup.fillAndStay(GuidedActionAdapter.this, editText);
                return true;
            }
            if (keyCode == 66 && event.getAction() == 1) {
                GuidedActionAdapter.this.mGroup.fillAndGoNext(GuidedActionAdapter.this, editText);
                return true;
            }
            return false;
        }
    }

    private class ActionAutofillListener implements GuidedActionAutofillSupport.OnAutofillListener {
        ActionAutofillListener() {
        }

        @Override // androidx.leanback.widget.GuidedActionAutofillSupport.OnAutofillListener
        public void onAutofill(View view) {
            GuidedActionAdapter.this.mGroup.fillAndGoNext(GuidedActionAdapter.this, (EditText) view);
        }
    }
}
