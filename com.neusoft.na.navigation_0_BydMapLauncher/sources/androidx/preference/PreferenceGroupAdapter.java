package androidx.preference;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PreferenceGroupAdapter extends RecyclerView.Adapter<PreferenceViewHolder> implements Preference.OnPreferenceChangeInternalListener, PreferenceGroup.PreferencePositionCallback {
    private Handler mHandler;
    private PreferenceGroup mPreferenceGroup;
    private CollapsiblePreferenceGroupController mPreferenceGroupController;
    private List<PreferenceLayout> mPreferenceLayouts;
    private List<Preference> mPreferenceList;
    private List<Preference> mPreferenceListInternal;
    private Runnable mSyncRunnable;
    private PreferenceLayout mTempPreferenceLayout;

    private static class PreferenceLayout {
        String mName;
        int mResId;
        int mWidgetResId;

        PreferenceLayout() {
        }

        PreferenceLayout(PreferenceLayout preferenceLayout) {
            this.mResId = preferenceLayout.mResId;
            this.mWidgetResId = preferenceLayout.mWidgetResId;
            this.mName = preferenceLayout.mName;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PreferenceLayout)) {
                return false;
            }
            PreferenceLayout preferenceLayout = (PreferenceLayout) obj;
            return this.mResId == preferenceLayout.mResId && this.mWidgetResId == preferenceLayout.mWidgetResId && TextUtils.equals(this.mName, preferenceLayout.mName);
        }

        public int hashCode() {
            return ((((527 + this.mResId) * 31) + this.mWidgetResId) * 31) + this.mName.hashCode();
        }
    }

    public PreferenceGroupAdapter(PreferenceGroup preferenceGroup) {
        this(preferenceGroup, new Handler());
    }

    private PreferenceGroupAdapter(PreferenceGroup preferenceGroup, Handler handler) {
        this.mTempPreferenceLayout = new PreferenceLayout();
        this.mSyncRunnable = new Runnable() { // from class: androidx.preference.PreferenceGroupAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                PreferenceGroupAdapter.this.syncMyPreferences();
            }
        };
        this.mPreferenceGroup = preferenceGroup;
        this.mHandler = handler;
        this.mPreferenceGroupController = new CollapsiblePreferenceGroupController(preferenceGroup, this);
        this.mPreferenceGroup.setOnPreferenceChangeInternalListener(this);
        this.mPreferenceList = new ArrayList();
        this.mPreferenceListInternal = new ArrayList();
        this.mPreferenceLayouts = new ArrayList();
        PreferenceGroup preferenceGroup2 = this.mPreferenceGroup;
        if (preferenceGroup2 instanceof PreferenceScreen) {
            setHasStableIds(((PreferenceScreen) preferenceGroup2).shouldUseGeneratedIds());
        } else {
            setHasStableIds(true);
        }
        syncMyPreferences();
    }

    static PreferenceGroupAdapter createInstanceWithCustomHandler(PreferenceGroup preferenceGroup, Handler handler) {
        return new PreferenceGroupAdapter(preferenceGroup, handler);
    }

    void syncMyPreferences() {
        Iterator<Preference> it = this.mPreferenceListInternal.iterator();
        while (it.hasNext()) {
            it.next().setOnPreferenceChangeInternalListener(null);
        }
        ArrayList arrayList = new ArrayList(this.mPreferenceListInternal.size());
        flattenPreferenceGroup(arrayList, this.mPreferenceGroup);
        final List<Preference> listCreateVisiblePreferencesList = this.mPreferenceGroupController.createVisiblePreferencesList(this.mPreferenceGroup);
        final List<Preference> list = this.mPreferenceList;
        this.mPreferenceList = listCreateVisiblePreferencesList;
        this.mPreferenceListInternal = arrayList;
        PreferenceManager preferenceManager = this.mPreferenceGroup.getPreferenceManager();
        if (preferenceManager != null && preferenceManager.getPreferenceComparisonCallback() != null) {
            final PreferenceManager.PreferenceComparisonCallback preferenceComparisonCallback = preferenceManager.getPreferenceComparisonCallback();
            DiffUtil.calculateDiff(new DiffUtil.Callback() { // from class: androidx.preference.PreferenceGroupAdapter.2
                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int getOldListSize() {
                    return list.size();
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int getNewListSize() {
                    return listCreateVisiblePreferencesList.size();
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean areItemsTheSame(int i, int i2) {
                    return preferenceComparisonCallback.arePreferenceItemsTheSame((Preference) list.get(i), (Preference) listCreateVisiblePreferencesList.get(i2));
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean areContentsTheSame(int i, int i2) {
                    return preferenceComparisonCallback.arePreferenceContentsTheSame((Preference) list.get(i), (Preference) listCreateVisiblePreferencesList.get(i2));
                }
            }).dispatchUpdatesTo(this);
        } else {
            notifyDataSetChanged();
        }
        Iterator<Preference> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            it2.next().clearWasDetached();
        }
    }

    private void flattenPreferenceGroup(List<Preference> list, PreferenceGroup preferenceGroup) {
        preferenceGroup.sortPreferences();
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceGroup.getPreference(i);
            list.add(preference);
            addPreferenceClassName(preference);
            if (preference instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup2 = (PreferenceGroup) preference;
                if (preferenceGroup2.isOnSameScreenAsChildren()) {
                    flattenPreferenceGroup(list, preferenceGroup2);
                }
            }
            preference.setOnPreferenceChangeInternalListener(this);
        }
    }

    private PreferenceLayout createPreferenceLayout(Preference preference, PreferenceLayout preferenceLayout) {
        if (preferenceLayout == null) {
            preferenceLayout = new PreferenceLayout();
        }
        preferenceLayout.mName = preference.getClass().getName();
        preferenceLayout.mResId = preference.getLayoutResource();
        preferenceLayout.mWidgetResId = preference.getWidgetLayoutResource();
        return preferenceLayout;
    }

    private void addPreferenceClassName(Preference preference) {
        PreferenceLayout preferenceLayoutCreatePreferenceLayout = createPreferenceLayout(preference, null);
        if (this.mPreferenceLayouts.contains(preferenceLayoutCreatePreferenceLayout)) {
            return;
        }
        this.mPreferenceLayouts.add(preferenceLayoutCreatePreferenceLayout);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mPreferenceList.size();
    }

    public Preference getItem(int i) {
        if (i < 0 || i >= getItemCount()) {
            return null;
        }
        return this.mPreferenceList.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        if (hasStableIds()) {
            return getItem(i).getId();
        }
        return -1L;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeInternalListener
    public void onPreferenceChange(Preference preference) {
        int iIndexOf = this.mPreferenceList.indexOf(preference);
        if (iIndexOf != -1) {
            notifyItemChanged(iIndexOf, preference);
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeInternalListener
    public void onPreferenceHierarchyChange(Preference preference) {
        this.mHandler.removeCallbacks(this.mSyncRunnable);
        this.mHandler.post(this.mSyncRunnable);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeInternalListener
    public void onPreferenceVisibilityChange(Preference preference) {
        if (this.mPreferenceListInternal.contains(preference) && !this.mPreferenceGroupController.onPreferenceVisibilityChange(preference)) {
            if (preference.isVisible()) {
                int i = -1;
                for (Preference preference2 : this.mPreferenceListInternal) {
                    if (preference.equals(preference2)) {
                        break;
                    } else if (preference2.isVisible()) {
                        i++;
                    }
                }
                int i2 = i + 1;
                this.mPreferenceList.add(i2, preference);
                notifyItemInserted(i2);
                return;
            }
            int size = this.mPreferenceList.size();
            int i3 = 0;
            while (i3 < size && !preference.equals(this.mPreferenceList.get(i3))) {
                if (i3 == size - 1) {
                    return;
                } else {
                    i3++;
                }
            }
            this.mPreferenceList.remove(i3);
            notifyItemRemoved(i3);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        PreferenceLayout preferenceLayoutCreatePreferenceLayout = createPreferenceLayout(getItem(i), this.mTempPreferenceLayout);
        this.mTempPreferenceLayout = preferenceLayoutCreatePreferenceLayout;
        int iIndexOf = this.mPreferenceLayouts.indexOf(preferenceLayoutCreatePreferenceLayout);
        if (iIndexOf != -1) {
            return iIndexOf;
        }
        int size = this.mPreferenceLayouts.size();
        this.mPreferenceLayouts.add(new PreferenceLayout(this.mTempPreferenceLayout));
        return size;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public PreferenceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        PreferenceLayout preferenceLayout = this.mPreferenceLayouts.get(i);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        TypedArray typedArrayObtainStyledAttributes = viewGroup.getContext().obtainStyledAttributes((AttributeSet) null, R.styleable.BackgroundStyle);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.BackgroundStyle_android_selectableItemBackground);
        if (drawable == null) {
            drawable = ContextCompat.getDrawable(viewGroup.getContext(), android.R.drawable.list_selector_background);
        }
        typedArrayObtainStyledAttributes.recycle();
        View viewInflate = layoutInflaterFrom.inflate(preferenceLayout.mResId, viewGroup, false);
        if (viewInflate.getBackground() == null) {
            ViewCompat.setBackground(viewInflate, drawable);
        }
        ViewGroup viewGroup2 = (ViewGroup) viewInflate.findViewById(android.R.id.widget_frame);
        if (viewGroup2 != null) {
            if (preferenceLayout.mWidgetResId != 0) {
                layoutInflaterFrom.inflate(preferenceLayout.mWidgetResId, viewGroup2);
            } else {
                viewGroup2.setVisibility(8);
            }
        }
        return new PreferenceViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder, int i) {
        getItem(i).onBindViewHolder(preferenceViewHolder);
    }

    @Override // androidx.preference.PreferenceGroup.PreferencePositionCallback
    public int getPreferenceAdapterPosition(String str) {
        int size = this.mPreferenceList.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(str, this.mPreferenceList.get(i).getKey())) {
                return i;
            }
        }
        return -1;
    }

    @Override // androidx.preference.PreferenceGroup.PreferencePositionCallback
    public int getPreferenceAdapterPosition(Preference preference) {
        int size = this.mPreferenceList.size();
        for (int i = 0; i < size; i++) {
            Preference preference2 = this.mPreferenceList.get(i);
            if (preference2 != null && preference2.equals(preference)) {
                return i;
            }
        }
        return -1;
    }
}
