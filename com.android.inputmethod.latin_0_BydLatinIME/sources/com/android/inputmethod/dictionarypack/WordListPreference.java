package com.android.inputmethod.dictionarypack;

import android.content.Context;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.TextView;
import com.android.inputmethod.latin.R;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class WordListPreference extends Preference {
    private static final int ACTION_DELETE_DICT = 3;
    private static final int ACTION_DISABLE_DICT = 2;
    private static final int ACTION_ENABLE_DICT = 1;
    private static final int ACTION_UNKNOWN = 0;
    private static final String NO_STATUS_MESSAGE = "";
    private static final String TAG = "WordListPreference";
    private static final int[][] sStatusActionList = {new int[0], new int[]{1, 1}, new int[]{2, 2}, new int[]{3, 3}, new int[]{3, 3}, new int[]{1, 1}};
    private final String mClientId;
    public final String mDescription;
    private final int mFilesize;
    private final DictionaryListInterfaceState mInterfaceState;
    public final Locale mLocale;
    private int mStatus;
    public final int mVersion;
    public final String mWordlistId;

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Preference another) {
        return super.compareTo(another);
    }

    public WordListPreference(final Context context, final DictionaryListInterfaceState dictionaryListInterfaceState, final String clientId, final String wordlistId, final int version, final Locale locale, final String description, final int status, final int filesize) {
        super(context, null);
        this.mInterfaceState = dictionaryListInterfaceState;
        this.mClientId = clientId;
        this.mVersion = version;
        this.mWordlistId = wordlistId;
        this.mFilesize = filesize;
        this.mLocale = locale;
        this.mDescription = description;
        setLayoutResource(R.layout.dictionary_line);
        setTitle(description);
        setStatus(status);
        setKey(wordlistId);
    }

    public void setStatus(final int status) {
        if (status == this.mStatus) {
            return;
        }
        this.mStatus = status;
        setSummary(getSummary(status));
    }

    public boolean hasStatus(final int status) {
        return status == this.mStatus;
    }

    @Override // android.preference.Preference
    public View onCreateView(final ViewGroup parent) {
        View viewFindFirstOrphanedView = this.mInterfaceState.findFirstOrphanedView();
        if (viewFindFirstOrphanedView != null) {
            return viewFindFirstOrphanedView;
        }
        return this.mInterfaceState.addToCacheAndReturnView(super.onCreateView(parent));
    }

    public boolean hasPriorityOver(final int otherPrefStatus) {
        return this.mStatus > otherPrefStatus;
    }

    private String getSummary(final int status) {
        Context context = getContext();
        if (status != 1) {
            if (status == 2) {
                return context.getString(R.string.dictionary_downloading);
            }
            if (status == 3) {
                return context.getString(R.string.dictionary_installed);
            }
            if (status == 4) {
                return context.getString(R.string.dictionary_disabled);
            }
            if (status != 5) {
                return "";
            }
        }
        return context.getString(R.string.dictionary_available);
    }

    static int getButtonSwitcherStatus(final int status) {
        int[][] iArr = sStatusActionList;
        if (status >= iArr.length) {
            Log.e(TAG, "Unknown status " + status);
            return 0;
        }
        return iArr[status][0];
    }

    static int getActionIdFromStatusAndMenuEntry(final int status) {
        int[][] iArr = sStatusActionList;
        if (status >= iArr.length) {
            Log.e(TAG, "Unknown status " + status);
            return 0;
        }
        return iArr[status][1];
    }

    private void disableDict() {
        Context context = getContext();
        CommonPreferences.disable(CommonPreferences.getCommonPreferences(context), this.mWordlistId);
        UpdateHandler.markAsUnused(context, this.mClientId, this.mWordlistId, this.mVersion, this.mStatus);
        int i = this.mStatus;
        if (2 == i) {
            setStatus(1);
        } else if (3 == i) {
            setStatus(4);
        } else {
            Log.e(TAG, "Unexpected state of the word list for disabling " + this.mStatus);
        }
    }

    private void enableDict() {
        Context context = getContext();
        CommonPreferences.enable(CommonPreferences.getCommonPreferences(context), this.mWordlistId);
        UpdateHandler.markAsUsed(context, this.mClientId, this.mWordlistId, this.mVersion, this.mStatus, true);
        int i = this.mStatus;
        if (1 == i) {
            setStatus(2);
        } else if (4 == i || 5 == i) {
            setStatus(3);
        } else {
            Log.e(TAG, "Unexpected state of the word list for enabling " + this.mStatus);
        }
    }

    private void deleteDict() {
        Context context = getContext();
        CommonPreferences.disable(CommonPreferences.getCommonPreferences(context), this.mWordlistId);
        setStatus(5);
        UpdateHandler.markAsDeleting(context, this.mClientId, this.mWordlistId, this.mVersion, this.mStatus);
    }

    @Override // android.preference.Preference
    protected void onBindView(final View view) {
        super.onBindView(view);
        ((ViewGroup) view).setLayoutTransition(null);
        DictionaryDownloadProgressBar dictionaryDownloadProgressBar = (DictionaryDownloadProgressBar) view.findViewById(R.id.dictionary_line_progress_bar);
        TextView textView = (TextView) view.findViewById(android.R.id.summary);
        dictionaryDownloadProgressBar.setIds(this.mClientId, this.mWordlistId);
        dictionaryDownloadProgressBar.setMax(this.mFilesize);
        int i = this.mStatus;
        boolean z = 2 == i;
        setSummary(getSummary(i));
        textView.setVisibility(z ? 4 : 0);
        dictionaryDownloadProgressBar.setVisibility(z ? 0 : 4);
        ButtonSwitcher buttonSwitcher = (ButtonSwitcher) view.findViewById(R.id.wordlist_button_switcher);
        buttonSwitcher.reset(this.mInterfaceState);
        if (this.mInterfaceState.isOpen(this.mWordlistId)) {
            int status = this.mInterfaceState.getStatus(this.mWordlistId);
            buttonSwitcher.setStatusAndUpdateVisuals(getButtonSwitcherStatus(status));
            int i2 = this.mStatus;
            if (status != i2) {
                buttonSwitcher.setStatusAndUpdateVisuals(getButtonSwitcherStatus(i2));
                this.mInterfaceState.setOpen(this.mWordlistId, this.mStatus);
            }
        } else {
            buttonSwitcher.setStatusAndUpdateVisuals(0);
        }
        buttonSwitcher.setInternalOnClickListener(new View.OnClickListener() { // from class: com.android.inputmethod.dictionarypack.WordListPreference.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                WordListPreference.this.onActionButtonClicked();
            }
        });
        view.setOnClickListener(new View.OnClickListener() { // from class: com.android.inputmethod.dictionarypack.WordListPreference.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                WordListPreference.this.onWordListClicked(v);
            }
        });
    }

    void onWordListClicked(final View v) {
        int iIndexOfChild;
        ViewParent parent = v.getParent();
        if (parent instanceof ListView) {
            ListView listView = (ListView) parent;
            boolean zIsOpen = this.mInterfaceState.isOpen(this.mWordlistId);
            this.mInterfaceState.closeAll();
            if (zIsOpen) {
                iIndexOfChild = -1;
            } else {
                this.mInterfaceState.setOpen(this.mWordlistId, this.mStatus);
                iIndexOfChild = listView.indexOfChild(v);
            }
            int lastVisiblePosition = listView.getLastVisiblePosition() - listView.getFirstVisiblePosition();
            for (int i = 0; i <= lastVisiblePosition; i++) {
                ButtonSwitcher buttonSwitcher = (ButtonSwitcher) listView.getChildAt(i).findViewById(R.id.wordlist_button_switcher);
                if (i == iIndexOfChild) {
                    buttonSwitcher.setStatusAndUpdateVisuals(getButtonSwitcherStatus(this.mStatus));
                } else {
                    buttonSwitcher.setStatusAndUpdateVisuals(0);
                }
            }
        }
    }

    void onActionButtonClicked() {
        int actionIdFromStatusAndMenuEntry = getActionIdFromStatusAndMenuEntry(this.mStatus);
        if (actionIdFromStatusAndMenuEntry == 1) {
            enableDict();
            return;
        }
        if (actionIdFromStatusAndMenuEntry == 2) {
            disableDict();
        } else if (actionIdFromStatusAndMenuEntry == 3) {
            deleteDict();
        } else {
            Log.e(TAG, "Unknown menu item pressed");
        }
    }
}
