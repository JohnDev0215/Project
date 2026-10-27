package androidx.leanback.preference;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.TextView;
import androidx.collection.ArraySet;
import androidx.leanback.widget.VerticalGridView;
import androidx.preference.DialogPreference;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class LeanbackListPreferenceDialogFragment extends LeanbackPreferenceDialogFragment {
    private static final String SAVE_STATE_ENTRIES = "LeanbackListPreferenceDialogFragment.entries";
    private static final String SAVE_STATE_ENTRY_VALUES = "LeanbackListPreferenceDialogFragment.entryValues";
    private static final String SAVE_STATE_INITIAL_SELECTION = "LeanbackListPreferenceDialogFragment.initialSelection";
    private static final String SAVE_STATE_INITIAL_SELECTIONS = "LeanbackListPreferenceDialogFragment.initialSelections";
    private static final String SAVE_STATE_IS_MULTI = "LeanbackListPreferenceDialogFragment.isMulti";
    private static final String SAVE_STATE_MESSAGE = "LeanbackListPreferenceDialogFragment.message";
    private static final String SAVE_STATE_TITLE = "LeanbackListPreferenceDialogFragment.title";
    private CharSequence mDialogMessage;
    private CharSequence mDialogTitle;
    private CharSequence[] mEntries;
    private CharSequence[] mEntryValues;
    private String mInitialSelection;
    Set<String> mInitialSelections;
    private boolean mMulti;

    public static LeanbackListPreferenceDialogFragment newInstanceSingle(String key) {
        Bundle args = new Bundle(1);
        args.putString("key", key);
        LeanbackListPreferenceDialogFragment fragment = new LeanbackListPreferenceDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static LeanbackListPreferenceDialogFragment newInstanceMulti(String key) {
        Bundle args = new Bundle(1);
        args.putString("key", key);
        LeanbackListPreferenceDialogFragment fragment = new LeanbackListPreferenceDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override // androidx.leanback.preference.LeanbackPreferenceDialogFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            DialogPreference preference = getPreference();
            this.mDialogTitle = preference.getDialogTitle();
            this.mDialogMessage = preference.getDialogMessage();
            if (preference instanceof ListPreference) {
                this.mMulti = false;
                this.mEntries = ((ListPreference) preference).getEntries();
                this.mEntryValues = ((ListPreference) preference).getEntryValues();
                this.mInitialSelection = ((ListPreference) preference).getValue();
                return;
            }
            if (preference instanceof MultiSelectListPreference) {
                this.mMulti = true;
                this.mEntries = ((MultiSelectListPreference) preference).getEntries();
                this.mEntryValues = ((MultiSelectListPreference) preference).getEntryValues();
                this.mInitialSelections = ((MultiSelectListPreference) preference).getValues();
                return;
            }
            throw new IllegalArgumentException("Preference must be a ListPreference or MultiSelectListPreference");
        }
        this.mDialogTitle = savedInstanceState.getCharSequence(SAVE_STATE_TITLE);
        this.mDialogMessage = savedInstanceState.getCharSequence(SAVE_STATE_MESSAGE);
        this.mMulti = savedInstanceState.getBoolean(SAVE_STATE_IS_MULTI);
        this.mEntries = savedInstanceState.getCharSequenceArray(SAVE_STATE_ENTRIES);
        this.mEntryValues = savedInstanceState.getCharSequenceArray(SAVE_STATE_ENTRY_VALUES);
        if (this.mMulti) {
            String[] initialSelections = savedInstanceState.getStringArray(SAVE_STATE_INITIAL_SELECTIONS);
            ArraySet arraySet = new ArraySet(initialSelections != null ? initialSelections.length : 0);
            this.mInitialSelections = arraySet;
            if (initialSelections != null) {
                Collections.addAll(arraySet, initialSelections);
                return;
            }
            return;
        }
        this.mInitialSelection = savedInstanceState.getString(SAVE_STATE_INITIAL_SELECTION);
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(SAVE_STATE_TITLE, this.mDialogTitle);
        outState.putCharSequence(SAVE_STATE_MESSAGE, this.mDialogMessage);
        outState.putBoolean(SAVE_STATE_IS_MULTI, this.mMulti);
        outState.putCharSequenceArray(SAVE_STATE_ENTRIES, this.mEntries);
        outState.putCharSequenceArray(SAVE_STATE_ENTRY_VALUES, this.mEntryValues);
        if (this.mMulti) {
            Set<String> set = this.mInitialSelections;
            outState.putStringArray(SAVE_STATE_INITIAL_SELECTIONS, (String[]) set.toArray(new String[set.size()]));
        } else {
            outState.putString(SAVE_STATE_INITIAL_SELECTION, this.mInitialSelection);
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TypedValue tv = new TypedValue();
        getActivity().getTheme().resolveAttribute(androidx.preference.R.attr.preferenceTheme, tv, true);
        int theme = tv.resourceId;
        if (theme == 0) {
            theme = R.style.PreferenceThemeOverlayLeanback;
        }
        Context styledContext = new ContextThemeWrapper(getActivity(), theme);
        LayoutInflater styledInflater = inflater.cloneInContext(styledContext);
        View view = styledInflater.inflate(R.layout.leanback_list_preference_fragment, container, false);
        VerticalGridView verticalGridView = (VerticalGridView) view.findViewById(android.R.id.list);
        verticalGridView.setWindowAlignment(3);
        verticalGridView.setFocusScrollStrategy(0);
        verticalGridView.setAdapter(onCreateAdapter());
        verticalGridView.requestFocus();
        CharSequence title = this.mDialogTitle;
        if (!TextUtils.isEmpty(title)) {
            TextView titleView = (TextView) view.findViewById(R.id.decor_title);
            titleView.setText(title);
        }
        CharSequence message = this.mDialogMessage;
        if (!TextUtils.isEmpty(message)) {
            TextView messageView = (TextView) view.findViewById(android.R.id.message);
            messageView.setVisibility(0);
            messageView.setText(message);
        }
        return view;
    }

    public RecyclerView.Adapter onCreateAdapter() {
        if (this.mMulti) {
            return new AdapterMulti(this.mEntries, this.mEntryValues, this.mInitialSelections);
        }
        return new AdapterSingle(this.mEntries, this.mEntryValues, this.mInitialSelection);
    }

    @Deprecated
    public class AdapterSingle extends RecyclerView.Adapter<ViewHolder> implements ViewHolder.OnItemClickListener {
        private final CharSequence[] mEntries;
        private final CharSequence[] mEntryValues;
        private CharSequence mSelectedValue;

        public AdapterSingle(CharSequence[] entries, CharSequence[] entryValues, CharSequence selectedValue) {
            this.mEntries = entries;
            this.mEntryValues = entryValues;
            this.mSelectedValue = selectedValue;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.leanback_list_preference_item_single, parent, false);
            return new ViewHolder(view, this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getWidgetView().setChecked(TextUtils.equals(this.mEntryValues[position].toString(), this.mSelectedValue));
            holder.getTitleView().setText(this.mEntries[position]);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mEntries.length;
        }

        @Override // androidx.leanback.preference.LeanbackListPreferenceDialogFragment.ViewHolder.OnItemClickListener
        public void onItemClick(ViewHolder viewHolder) {
            int index = viewHolder.getAbsoluteAdapterPosition();
            if (index == -1) {
                return;
            }
            CharSequence entry = this.mEntryValues[index];
            ListPreference preference = (ListPreference) LeanbackListPreferenceDialogFragment.this.getPreference();
            if (index >= 0) {
                String value = this.mEntryValues[index].toString();
                if (preference.callChangeListener(value)) {
                    preference.setValue(value);
                    this.mSelectedValue = entry;
                }
            }
            LeanbackListPreferenceDialogFragment.this.getFragmentManager().popBackStack();
            notifyDataSetChanged();
        }
    }

    @Deprecated
    public class AdapterMulti extends RecyclerView.Adapter<ViewHolder> implements ViewHolder.OnItemClickListener {
        private final CharSequence[] mEntries;
        private final CharSequence[] mEntryValues;
        private final Set<String> mSelections;

        public AdapterMulti(CharSequence[] entries, CharSequence[] entryValues, Set<String> initialSelections) {
            this.mEntries = entries;
            this.mEntryValues = entryValues;
            this.mSelections = new HashSet(initialSelections);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.leanback_list_preference_item_multi, parent, false);
            return new ViewHolder(view, this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getWidgetView().setChecked(this.mSelections.contains(this.mEntryValues[position].toString()));
            holder.getTitleView().setText(this.mEntries[position]);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mEntries.length;
        }

        @Override // androidx.leanback.preference.LeanbackListPreferenceDialogFragment.ViewHolder.OnItemClickListener
        public void onItemClick(ViewHolder viewHolder) {
            int index = viewHolder.getAbsoluteAdapterPosition();
            if (index == -1) {
                return;
            }
            String entry = this.mEntryValues[index].toString();
            if (this.mSelections.contains(entry)) {
                this.mSelections.remove(entry);
            } else {
                this.mSelections.add(entry);
            }
            MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) LeanbackListPreferenceDialogFragment.this.getPreference();
            if (multiSelectListPreference.callChangeListener(new HashSet(this.mSelections))) {
                multiSelectListPreference.setValues(new HashSet(this.mSelections));
                LeanbackListPreferenceDialogFragment.this.mInitialSelections = this.mSelections;
            } else if (this.mSelections.contains(entry)) {
                this.mSelections.remove(entry);
            } else {
                this.mSelections.add(entry);
            }
            notifyDataSetChanged();
        }
    }

    @Deprecated
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ViewGroup mContainer;
        private final OnItemClickListener mListener;
        private final TextView mTitleView;
        private final Checkable mWidgetView;

        public interface OnItemClickListener {
            void onItemClick(ViewHolder viewHolder);
        }

        public ViewHolder(View view, OnItemClickListener listener) {
            super(view);
            this.mWidgetView = (Checkable) view.findViewById(R.id.button);
            this.mContainer = (ViewGroup) view.findViewById(R.id.container);
            this.mTitleView = (TextView) view.findViewById(android.R.id.title);
            this.mContainer.setOnClickListener(this);
            this.mListener = listener;
        }

        public Checkable getWidgetView() {
            return this.mWidgetView;
        }

        public TextView getTitleView() {
            return this.mTitleView;
        }

        public ViewGroup getContainer() {
            return this.mContainer;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            this.mListener.onItemClick(this);
        }
    }
}
