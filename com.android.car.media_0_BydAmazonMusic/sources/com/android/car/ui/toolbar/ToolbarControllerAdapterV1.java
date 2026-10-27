package com.android.car.ui.toolbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.android.car.ui.toolbar.TabLayout;
import com.android.car.ui.toolbar.ToolbarControllerAdapterV1;
import com.android.car.ui.toolbar.d;
import com.android.car.ui.toolbar.e;
import com.android.car.ui.toolbar.g;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.xmlpull.v1.XmlPullParserException;
import spi.ab;
import spi.b2;
import spi.bb;
import spi.cb;
import spi.db;
import spi.e7;
import spi.eb;
import spi.g1;
import spi.g3;
import spi.gb;
import spi.h9;
import spi.kb;
import spi.s7;
import spi.u1;
import spi.v9;
import spi.y4;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(30)
public final class ToolbarControllerAdapterV1 implements j {
    private final Context mContext;
    private final kb mOemToolbar;
    private final ProgressBarControllerAdapterV1 mProgressBar;
    private String mSearchHint;
    private final f mSearchWidescreenController;
    private final boolean mSupportsImeSearch;
    private ToolbarAdapterState mAdapterState = new ToolbarAdapterState();
    private final Set<i> mOnTabSelectedListeners = new HashSet();
    private final Set<bb> mDeprecatedBackListeners = new HashSet();
    private final Set<Supplier<Boolean>> mBackListeners = new HashSet();
    private final Set<db> mDeprecatedSearchListeners = new HashSet();
    private final Set<cb> mDeprecatedSearchCompletedListeners = new HashSet();
    private final Set<Consumer<String>> mSearchListeners = new HashSet();
    private final Set<Runnable> mSearchCompletedListeners = new HashSet();
    private List<b> mClientMenuItems = Collections.emptyList();
    private final List<a> mDeprecatedTabs = new ArrayList();
    private boolean mBackgroundShown = true;
    private final e.b mSearchConfigBuilder = e.a();

    /* JADX INFO: renamed from: com.android.car.ui.toolbar.ToolbarControllerAdapterV1$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$android$car$ui$toolbar$SearchMode;
        public static final /* synthetic */ int[] $SwitchMap$com$android$car$ui$toolbar$Toolbar$NavButtonMode;

        static {
            int[] iArr = new int[v9.values().length];
            $SwitchMap$com$android$car$ui$toolbar$SearchMode = iArr;
            try {
                iArr[v9.SEARCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$car$ui$toolbar$SearchMode[v9.EDIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[ab.values().length];
            $SwitchMap$com$android$car$ui$toolbar$Toolbar$NavButtonMode = iArr2;
            try {
                iArr2[ab.BACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$car$ui$toolbar$Toolbar$NavButtonMode[ab.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$car$ui$toolbar$Toolbar$NavButtonMode[ab.CLOSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$android$car$ui$toolbar$Toolbar$NavButtonMode[ab.DISABLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static class ToolbarAdapterState {
        private final Drawable mLogo;
        private final boolean mLogoDirty;

        @NonNull
        private final List<MenuItemAdapterV1> mMenuItems;
        private final s7 mNavButtonMode;
        private final v9 mSearchMode;
        private final int mSelectedTab;
        private final boolean mShowMenuItemsWhileSearching;
        private final boolean mShowTabsInSubpage;
        private final eb mState;
        private final boolean mStateSet;
        private final String mSubtitle;

        @NonNull
        private final List<TabAdapterV1> mTabs;
        private final boolean mTabsDirty;
        private final String mTitle;

        public static class Builder {
            private Drawable mLogo;

            @NonNull
            private List<MenuItemAdapterV1> mMenuItems;
            private s7 mNavButtonMode;
            private v9 mSearchMode;
            private int mSelectedTab;
            private boolean mShowMenuItemsWhileSearching;
            private boolean mShowTabsInSubpage;
            private eb mState;
            private final ToolbarAdapterState mStateClonedFrom;
            private boolean mStateSet;
            private String mSubtitle;

            @NonNull
            private List<TabAdapterV1> mTabs;
            private String mTitle;
            private boolean mWasChanged = false;
            private boolean mTabsDirty = false;
            private boolean mLogoDirty = false;

            public Builder(ToolbarAdapterState toolbarAdapterState) {
                this.mStateClonedFrom = toolbarAdapterState;
                this.mState = toolbarAdapterState.mState;
                this.mStateSet = toolbarAdapterState.mStateSet;
                this.mShowTabsInSubpage = toolbarAdapterState.mShowTabsInSubpage;
                this.mTabs = toolbarAdapterState.mTabs;
                this.mMenuItems = toolbarAdapterState.mMenuItems;
                this.mShowMenuItemsWhileSearching = toolbarAdapterState.mShowMenuItemsWhileSearching;
                this.mSelectedTab = toolbarAdapterState.mSelectedTab;
                this.mTitle = toolbarAdapterState.mTitle;
                this.mSubtitle = toolbarAdapterState.mSubtitle;
                this.mLogo = toolbarAdapterState.mLogo;
                this.mNavButtonMode = toolbarAdapterState.mNavButtonMode;
                this.mSearchMode = toolbarAdapterState.mSearchMode;
            }

            public Builder addTab(@NonNull TabAdapterV1 tabAdapterV1) {
                ArrayList arrayList = new ArrayList(this.mTabs);
                arrayList.add(tabAdapterV1);
                this.mTabs = Collections.unmodifiableList(arrayList);
                this.mWasChanged = true;
                this.mTabsDirty = true;
                return this;
            }

            public ToolbarAdapterState build() {
                return !this.mWasChanged ? this.mStateClonedFrom : new ToolbarAdapterState(this, null);
            }

            public Builder setLogo(Drawable drawable) {
                if (this.mLogo != drawable) {
                    this.mLogo = drawable;
                    this.mWasChanged = true;
                    this.mLogoDirty = true;
                }
                return this;
            }

            public Builder setMenuItems(List<MenuItemAdapterV1> list) {
                if (list == null) {
                    list = Collections.emptyList();
                }
                if (!Objects.equals(this.mMenuItems, list)) {
                    this.mMenuItems = list;
                    this.mWasChanged = true;
                }
                return this;
            }

            public Builder setNavButtonMode(s7 s7Var) {
                if (s7Var != this.mNavButtonMode) {
                    this.mNavButtonMode = s7Var;
                    this.mWasChanged = true;
                }
                return this;
            }

            public Builder setSearchMode(v9 v9Var) {
                if (this.mStateSet) {
                    throw new IllegalStateException("Cannot use setSearchMode() with setState()");
                }
                if (this.mSearchMode != v9Var) {
                    this.mSearchMode = v9Var;
                    this.mWasChanged = true;
                }
                return this;
            }

            public Builder setSelectedTab(int i) {
                if (this.mSelectedTab != i) {
                    this.mSelectedTab = i;
                    this.mWasChanged = true;
                }
                return this;
            }

            public Builder setShowMenuItemsWhileSearching(boolean z) {
                if (this.mShowMenuItemsWhileSearching != z) {
                    this.mShowMenuItemsWhileSearching = z;
                    this.mWasChanged = true;
                }
                return this;
            }

            public Builder setShowTabsInSubpage(boolean z) {
                if (this.mShowTabsInSubpage != z) {
                    this.mShowTabsInSubpage = z;
                    this.mWasChanged = true;
                }
                return this;
            }

            public Builder setState(eb ebVar) {
                if (this.mSearchMode != v9.DISABLED) {
                    throw new IllegalStateException("Cannot use setSearchMode() with setState()");
                }
                if (ebVar != this.mState) {
                    this.mState = ebVar;
                    this.mStateSet = true;
                    this.mWasChanged = true;
                }
                return this;
            }

            public Builder setSubtitle(String str) {
                if (!Objects.equals(this.mSubtitle, str)) {
                    this.mSubtitle = str;
                    this.mWasChanged = true;
                }
                return this;
            }

            public Builder setTabs(@Nullable List<TabAdapterV1> list) {
                if (list == null) {
                    list = Collections.emptyList();
                }
                if (!Objects.equals(list, this.mTabs)) {
                    List<TabAdapterV1> listUnmodifiableList = Collections.unmodifiableList(list);
                    this.mTabs = listUnmodifiableList;
                    this.mSelectedTab = listUnmodifiableList.isEmpty() ? -1 : 0;
                    this.mWasChanged = true;
                    this.mTabsDirty = true;
                }
                return this;
            }

            public Builder setTitle(String str) {
                if (!Objects.equals(this.mTitle, str)) {
                    this.mTitle = str;
                    this.mWasChanged = true;
                }
                return this;
            }
        }

        public /* synthetic */ ToolbarAdapterState(Builder builder, AnonymousClass1 anonymousClass1) {
            this(builder);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public v9 getSearchMode() {
            if (!this.mStateSet) {
                return this.mSearchMode;
            }
            eb ebVar = this.mState;
            return ebVar == eb.SEARCH ? v9.SEARCH : ebVar == eb.EDIT ? v9.EDIT : v9.DISABLED;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<e7> getShownMenuItems() {
            v9 searchMode = getSearchMode();
            Stream<MenuItemAdapterV1> stream = this.mMenuItems.stream();
            if (searchMode == v9.EDIT && !this.mShowMenuItemsWhileSearching) {
                stream = Stream.empty();
            } else if (searchMode == v9.SEARCH) {
                stream = this.mShowMenuItemsWhileSearching ? this.mMenuItems.stream().filter(l.b) : Stream.empty();
            }
            return Collections.unmodifiableList((List) stream.filter(l.c).map(k.g).collect(Collectors.toList()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasTabs() {
            if (!this.mStateSet) {
                return !getTabs().isEmpty();
            }
            eb ebVar = this.mState;
            return (ebVar == eb.HOME || (ebVar == eb.SUBPAGE && getShowTabsInSubpage())) && !getTabs().isEmpty();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean lambda$getShownMenuItems$0(MenuItemAdapterV1 menuItemAdapterV1) {
            return !menuItemAdapterV1.getClientMenuItem().c;
        }

        public Builder copy() {
            return new Builder(this);
        }

        public Drawable getLogo() {
            return this.mLogo;
        }

        public boolean getLogoDirty() {
            return this.mLogoDirty;
        }

        public s7 getNavButtonMode() {
            return (this.mStateSet && this.mNavButtonMode == s7.DISABLED && this.mState != eb.HOME) ? s7.BACK : this.mNavButtonMode;
        }

        public int getSelectedTab() {
            return this.mSelectedTab;
        }

        public boolean getShowMenuItemsWhileSearching() {
            return this.mShowMenuItemsWhileSearching;
        }

        public boolean getShowTabsInSubpage() {
            return this.mShowTabsInSubpage;
        }

        public Drawable getShownLogo() {
            eb ebVar;
            if (!this.mStateSet || (ebVar = this.mState) == eb.HOME || ebVar == eb.SUBPAGE) {
                return this.mLogo;
            }
            return null;
        }

        public String getShownSubtitle() {
            String str;
            eb ebVar;
            return ((!this.mStateSet || (ebVar = this.mState) == eb.HOME || ebVar == eb.SUBPAGE) && (str = this.mSubtitle) != null) ? str : "";
        }

        public String getShownTitle() {
            String str;
            eb ebVar;
            return ((!this.mStateSet || (ebVar = this.mState) == eb.HOME || ebVar == eb.SUBPAGE) && (str = this.mTitle) != null) ? str : "";
        }

        public eb getState() {
            return this.mState;
        }

        public String getSubtitle() {
            String str = this.mSubtitle;
            return str == null ? "" : str;
        }

        @NonNull
        public List<TabAdapterV1> getTabs() {
            return this.mTabs;
        }

        public boolean getTabsDirty() {
            return this.mTabsDirty;
        }

        public String getTitle() {
            String str = this.mTitle;
            return str == null ? "" : str;
        }

        public boolean isStateSet() {
            return this.mStateSet;
        }

        public ToolbarAdapterState() {
            this.mState = eb.HOME;
            this.mStateSet = false;
            this.mShowTabsInSubpage = false;
            this.mTabs = Collections.emptyList();
            this.mMenuItems = Collections.emptyList();
            this.mSelectedTab = -1;
            this.mTitle = null;
            this.mSubtitle = null;
            this.mLogo = null;
            this.mShowMenuItemsWhileSearching = false;
            this.mTabsDirty = false;
            this.mLogoDirty = false;
            this.mNavButtonMode = s7.DISABLED;
            this.mSearchMode = v9.DISABLED;
        }

        private ToolbarAdapterState(Builder builder) {
            this.mState = builder.mState;
            this.mStateSet = builder.mStateSet;
            this.mShowTabsInSubpage = builder.mShowTabsInSubpage;
            this.mTabs = builder.mTabs;
            this.mMenuItems = builder.mMenuItems;
            this.mSelectedTab = builder.mSelectedTab;
            this.mTitle = builder.mTitle;
            this.mSubtitle = builder.mSubtitle;
            this.mLogo = builder.mLogo;
            this.mShowMenuItemsWhileSearching = builder.mShowMenuItemsWhileSearching;
            this.mTabsDirty = builder.mTabsDirty;
            this.mLogoDirty = builder.mLogoDirty;
            this.mNavButtonMode = builder.mNavButtonMode;
            this.mSearchMode = builder.mSearchMode;
        }
    }

    public ToolbarControllerAdapterV1(@NonNull Context context, @NonNull kb kbVar) {
        this.mOemToolbar = kbVar;
        this.mProgressBar = new ProgressBarControllerAdapterV1(kbVar.e());
        this.mContext = context;
        b2.e(context);
        this.mSearchWidescreenController = new f(context);
        y4 y4VarM = kbVar.m();
        this.mSupportsImeSearch = y4VarM != null;
        if (y4VarM != null) {
            y4VarM.a();
            y4VarM.b();
        }
        kbVar.i();
        kbVar.b();
        kbVar.h();
    }

    private Drawable getDrawable(@DrawableRes int i) {
        if (i == 0) {
            return null;
        }
        return ContextCompat.getDrawable(this.mContext, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addTab$3(TabLayout.a aVar, g gVar) {
        Iterator<i> it = this.mOnTabSelectedListeners.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Activity activity) {
        Iterator<bb> it = this.mDeprecatedBackListeners.iterator();
        boolean zBooleanValue = false;
        while (it.hasNext()) {
            zBooleanValue |= it.next().a();
        }
        Iterator<Supplier<Boolean>> it2 = this.mBackListeners.iterator();
        while (it2.hasNext()) {
            zBooleanValue |= it2.next().get().booleanValue();
        }
        if (zBooleanValue || activity == null) {
            return;
        }
        activity.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(String str) {
        Iterator<db> it = this.mDeprecatedSearchListeners.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        Iterator<Consumer<String>> it2 = this.mSearchListeners.iterator();
        while (it2.hasNext()) {
            it2.next().accept(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2() {
        Iterator<cb> it = this.mDeprecatedSearchCompletedListeners.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        Iterator<Runnable> it2 = this.mSearchCompletedListeners.iterator();
        while (it2.hasNext()) {
            it2.next().run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ MenuItemAdapterV1 lambda$setMenuItems$4(b bVar) {
        return new MenuItemAdapterV1(this, bVar);
    }

    private void update(ToolbarAdapterState toolbarAdapterState) {
        ToolbarAdapterState toolbarAdapterState2 = this.mAdapterState;
        this.mAdapterState = toolbarAdapterState;
        if (!TextUtils.equals(toolbarAdapterState.getShownTitle(), toolbarAdapterState2.getShownTitle())) {
            kb kbVar = this.mOemToolbar;
            toolbarAdapterState.getTitle();
            kbVar.j();
        }
        if (!TextUtils.equals(toolbarAdapterState.getShownSubtitle(), toolbarAdapterState2.getShownSubtitle())) {
            kb kbVar2 = this.mOemToolbar;
            toolbarAdapterState.getSubtitle();
            kbVar2.k();
        }
        if (toolbarAdapterState.getShownLogo() != toolbarAdapterState2.getShownLogo()) {
            kb kbVar3 = this.mOemToolbar;
            toolbarAdapterState.getShownLogo();
            kbVar3.q();
        } else if (toolbarAdapterState.getShownLogo() != null && toolbarAdapterState.getLogoDirty()) {
            kb kbVar4 = this.mOemToolbar;
            toolbarAdapterState.getShownLogo();
            kbVar4.q();
        }
        boolean z = false;
        if (toolbarAdapterState.getSearchMode() != toolbarAdapterState2.getSearchMode()) {
            int i = AnonymousClass1.$SwitchMap$com$android$car$ui$toolbar$SearchMode[toolbarAdapterState.getSearchMode().ordinal()];
            if (i == 1 || i == 2) {
                this.mOemToolbar.g();
            } else {
                this.mOemToolbar.g();
            }
        }
        if (toolbarAdapterState2.getNavButtonMode() != toolbarAdapterState.getNavButtonMode()) {
            if (toolbarAdapterState.getNavButtonMode() == s7.DISABLED || toolbarAdapterState.getNavButtonMode() == s7.CLOSE || toolbarAdapterState.getNavButtonMode() == s7.DOWN) {
                this.mOemToolbar.l();
            } else {
                this.mOemToolbar.l();
            }
        }
        boolean z2 = toolbarAdapterState.hasTabs() && !toolbarAdapterState2.hasTabs();
        if (!toolbarAdapterState.hasTabs() && toolbarAdapterState2.hasTabs()) {
            z = true;
        }
        if (z2) {
            kb kbVar5 = this.mOemToolbar;
            toolbarAdapterState.getSelectedTab();
            kbVar5.f();
        } else if (z) {
            kb kbVar6 = this.mOemToolbar;
            Collections.emptyList();
            kbVar6.f();
        } else if (toolbarAdapterState.hasTabs() && toolbarAdapterState.getTabsDirty()) {
            kb kbVar7 = this.mOemToolbar;
            toolbarAdapterState.getSelectedTab();
            kbVar7.f();
        } else if (toolbarAdapterState.hasTabs() && toolbarAdapterState.getSelectedTab() != toolbarAdapterState2.getSelectedTab()) {
            kb kbVar8 = this.mOemToolbar;
            toolbarAdapterState.getSelectedTab();
            kbVar8.a();
        }
        if (Objects.equals(toolbarAdapterState.getShownMenuItems(), toolbarAdapterState2.getShownMenuItems())) {
            return;
        }
        kb kbVar9 = this.mOemToolbar;
        toolbarAdapterState.getShownMenuItems();
        kbVar9.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateModernTabsFromDeprecatedOnes() {
        ArrayList arrayList = new ArrayList();
        for (a aVar : this.mDeprecatedTabs) {
            Objects.requireNonNull(aVar);
            g.b bVarA = g.a();
            bVarA.a = aVar.c;
            bVarA.b = aVar.d;
            bVarA.c = aVar.b;
            arrayList.add(bVarA.a());
        }
        update(this.mAdapterState.copy().setTabs(b2.c(arrayList, k.b)).build());
    }

    public void addTab(TabLayout.a aVar) {
        final int i = 1;
        this.mDeprecatedTabs.add(new a(this.mContext, aVar, new Runnable(this) { // from class: spi.fb
            public final /* synthetic */ ToolbarControllerAdapterV1 b;

            {
                this.b = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i) {
                    case 0:
                        this.b.lambda$new$2();
                        break;
                    default:
                        this.b.updateModernTabsFromDeprecatedOnes();
                        break;
                }
            }
        }, new g3(this, aVar, 2)));
        updateModernTabsFromDeprecatedOnes();
    }

    public boolean canShowSearchResultItems() {
        return getSearchCapabilities().b;
    }

    public boolean canShowSearchResultsView() {
        return getSearchCapabilities().a;
    }

    public void clearAllTabs() {
        setTabs(Collections.emptyList());
    }

    public void closeEditTextFocus() {
    }

    @Nullable
    public b findMenuItemById(int i) {
        for (b bVar : getMenuItems()) {
            if (bVar.f == i) {
                return bVar;
            }
        }
        return null;
    }

    public boolean getBackgroundShown() {
        return this.mBackgroundShown;
    }

    @NonNull
    public List<b> getMenuItems() {
        return this.mClientMenuItems;
    }

    @Override // com.android.car.ui.toolbar.j
    public s7 getNavButtonMode() {
        return this.mAdapterState.getNavButtonMode();
    }

    public h9 getProgressBar() {
        return this.mProgressBar;
    }

    @Override // com.android.car.ui.toolbar.j
    public d getSearchCapabilities() {
        return !this.mSupportsImeSearch ? new d(new d.b()) : f.e(this.mSearchWidescreenController.b);
    }

    public CharSequence getSearchHint() {
        return this.mSearchHint;
    }

    @Override // com.android.car.ui.toolbar.j
    public v9 getSearchMode() {
        return this.mAdapterState.getSearchMode();
    }

    public int getSelectedTab() {
        int selectedTab = this.mAdapterState.getSelectedTab();
        if (!this.mAdapterState.getTabs().isEmpty() || selectedTab == -1) {
            return selectedTab;
        }
        throw new IllegalStateException("mSelectedTab should've been -1");
    }

    public boolean getShowMenuItemsWhileSearching() {
        return this.mAdapterState.getShowMenuItemsWhileSearching();
    }

    public boolean getShowTabsInSubpage() {
        return this.mAdapterState.getShowTabsInSubpage();
    }

    public eb getState() {
        return this.mAdapterState.getState();
    }

    public CharSequence getSubtitle() {
        return this.mAdapterState.getSubtitle();
    }

    public TabLayout.a getTab(int i) {
        return this.mDeprecatedTabs.get(i).a;
    }

    public int getTabCount() {
        return this.mDeprecatedTabs.size();
    }

    public int getTabPosition(TabLayout.a aVar) {
        for (int i = 0; i < this.mDeprecatedTabs.size(); i++) {
            if (this.mDeprecatedTabs.get(i).a == aVar) {
                return i;
            }
        }
        return -1;
    }

    public List<g> getTabs() {
        return Collections.unmodifiableList((List) this.mAdapterState.getTabs().stream().map(k.d).collect(Collectors.toList()));
    }

    public CharSequence getTitle() {
        return this.mAdapterState.getTitle();
    }

    @Override // com.android.car.ui.toolbar.j
    public boolean isStateSet() {
        return this.mAdapterState.isStateSet();
    }

    public void registerBackListener(Supplier<Boolean> supplier) {
        this.mBackListeners.add(supplier);
    }

    @Override // com.android.car.ui.toolbar.j
    public void registerOnBackListener(bb bbVar) {
        this.mDeprecatedBackListeners.add(bbVar);
    }

    @Override // com.android.car.ui.toolbar.j
    public void registerOnSearchCompletedListener(cb cbVar) {
        this.mDeprecatedSearchCompletedListeners.add(cbVar);
    }

    @Override // com.android.car.ui.toolbar.j
    public void registerOnSearchListener(db dbVar) {
        this.mDeprecatedSearchListeners.add(dbVar);
    }

    @Override // com.android.car.ui.toolbar.j
    public void registerOnTabSelectedListener(i iVar) {
        this.mOnTabSelectedListeners.add(iVar);
    }

    @Override // com.android.car.ui.toolbar.j
    public void registerSearchCompletedListener(Runnable runnable) {
        this.mSearchCompletedListeners.add(runnable);
    }

    @Override // com.android.car.ui.toolbar.j
    public void registerSearchListener(Consumer<String> consumer) {
        this.mSearchListeners.add(consumer);
    }

    @NonNull
    public b requireMenuItemById(int i) {
        b bVarFindMenuItemById = findMenuItemById(i);
        if (bVarFindMenuItemById != null) {
            return bVarFindMenuItemById;
        }
        throw new IllegalArgumentException("ID does not reference a MenuItem on this Toolbar");
    }

    @Override // com.android.car.ui.toolbar.j
    public void selectTab(int i) {
        if (i < 0 || i >= this.mAdapterState.getTabs().size()) {
            throw new IllegalArgumentException(spi.c.d("Tab position is invalid: ", i));
        }
        update(this.mAdapterState.copy().setSelectedTab(i).build());
    }

    @Override // com.android.car.ui.toolbar.j
    public void setBackground(int i) {
    }

    @Override // com.android.car.ui.toolbar.j
    public void setBackgroundShown(boolean z) {
        this.mBackgroundShown = z;
        this.mOemToolbar.n();
    }

    public void setLogo(@IdRes int i) {
        setLogo(getDrawable(i));
    }

    @Override // com.android.car.ui.toolbar.j
    public void setMenuItems(@Nullable List<b> list) {
        this.mClientMenuItems = list;
        update(this.mAdapterState.copy().setMenuItems(b2.c(list, new gb(this, 0))).build());
    }

    @Override // com.android.car.ui.toolbar.j
    public void setNavButtonMode(ab abVar) {
        int i = AnonymousClass1.$SwitchMap$com$android$car$ui$toolbar$Toolbar$NavButtonMode[abVar.ordinal()];
        if (i == 1) {
            setNavButtonMode(s7.BACK);
            return;
        }
        if (i == 2) {
            setNavButtonMode(s7.DOWN);
        } else if (i != 3) {
            setNavButtonMode(s7.DISABLED);
        } else {
            setNavButtonMode(s7.CLOSE);
        }
    }

    @Override // com.android.car.ui.toolbar.j
    public void setSearchBack(int i) {
    }

    @Override // com.android.car.ui.toolbar.j
    public void setSearchConfig(e eVar) {
        f fVar = this.mSearchWidescreenController;
        Objects.requireNonNull(fVar);
        if (eVar == null) {
            eVar = e.a().a();
        }
        fVar.k = eVar;
    }

    public void setSearchHint(int i) {
        setSearchHint(this.mContext.getString(i));
    }

    public void setSearchIcon(int i) {
        setSearchIcon(getDrawable(i));
    }

    @Override // com.android.car.ui.toolbar.j
    public void setSearchMode(v9 v9Var) {
        update(this.mAdapterState.copy().setSearchMode(v9Var).build());
    }

    @Override // com.android.car.ui.toolbar.j
    public void setSearchQuery(String str) {
        this.mOemToolbar.p();
    }

    public void setSearchResultItems(List<? extends g1> list) {
        e.b bVar = this.mSearchConfigBuilder;
        Objects.requireNonNull(bVar);
        if (list == null || list.size() == 0) {
            bVar.c = null;
        } else {
            bVar.c = Collections.unmodifiableList(new ArrayList(list));
        }
        setSearchConfig(this.mSearchConfigBuilder.a());
    }

    public void setSearchResultsInputViewIcon(Drawable drawable) {
        e.b bVar = this.mSearchConfigBuilder;
        bVar.b = drawable;
        setSearchConfig(bVar.a());
    }

    public void setSearchResultsView(View view) {
        e.b bVar = this.mSearchConfigBuilder;
        bVar.a = view;
        setSearchConfig(bVar.a());
    }

    public void setShowMenuItemsWhileSearching(boolean z) {
        update(this.mAdapterState.copy().setShowMenuItemsWhileSearching(z).build());
    }

    public void setShowTabsInSubpage(boolean z) {
        update(this.mAdapterState.copy().setShowTabsInSubpage(z).build());
    }

    @Override // com.android.car.ui.toolbar.j
    public void setState(eb ebVar) {
        update(this.mAdapterState.copy().setState(ebVar).build());
    }

    public void setSubtitle(int i) {
        setSubtitle(i == 0 ? null : this.mContext.getString(i));
    }

    @Override // com.android.car.ui.toolbar.j
    public void setTabs(@Nullable List<g> list) {
        setTabs(list, 0);
    }

    @Override // com.android.car.ui.toolbar.j
    public void setTitle(int i) {
        setTitle(i == 0 ? null : this.mContext.getString(i));
    }

    @Override // com.android.car.ui.toolbar.j
    public void showLineView(boolean z) {
    }

    @Override // com.android.car.ui.toolbar.j
    public void showOtherView() {
    }

    @Override // com.android.car.ui.toolbar.j
    public void showSignInView() {
    }

    public boolean unregisterBackListener(Supplier<Boolean> supplier) {
        return this.mBackListeners.remove(supplier);
    }

    public boolean unregisterOnBackListener(bb bbVar) {
        return this.mDeprecatedBackListeners.remove(bbVar);
    }

    public boolean unregisterOnSearchCompletedListener(cb cbVar) {
        return this.mDeprecatedSearchCompletedListeners.remove(cbVar);
    }

    public boolean unregisterOnSearchListener(db dbVar) {
        return this.mDeprecatedSearchListeners.remove(dbVar);
    }

    public boolean unregisterOnTabSelectedListener(i iVar) {
        return this.mOnTabSelectedListeners.remove(iVar);
    }

    public boolean unregisterSearchCompletedListener(Runnable runnable) {
        return this.mSearchCompletedListeners.remove(runnable);
    }

    public boolean unregisterSearchListener(Consumer<String> consumer) {
        return this.mSearchListeners.remove(consumer);
    }

    public void updateMenuItems() {
        kb kbVar = this.mOemToolbar;
        this.mAdapterState.getShownMenuItems();
        kbVar.c();
    }

    @Override // com.android.car.ui.toolbar.j
    public void setLogo(Drawable drawable) {
        update(this.mAdapterState.copy().setLogo(drawable).build());
    }

    public void setSearchHint(CharSequence charSequence) {
        this.mSearchHint = b2.b(charSequence);
        this.mOemToolbar.o();
    }

    @Override // com.android.car.ui.toolbar.j
    public void setSearchIcon(Drawable drawable) {
        this.mOemToolbar.d();
    }

    @Override // com.android.car.ui.toolbar.j
    public void setSubtitle(CharSequence charSequence) {
        update(this.mAdapterState.copy().setSubtitle(b2.b(charSequence)).build());
    }

    @Override // com.android.car.ui.toolbar.j
    public void setTabs(@Nullable List<g> list, int i) {
        this.mDeprecatedTabs.clear();
        if (list == null || list.isEmpty()) {
            i = -1;
        } else if (i < 0 || i >= list.size()) {
            throw new IllegalArgumentException(spi.c.d("Tab position is invalid: ", i));
        }
        update(this.mAdapterState.copy().setTabs(b2.c(list, k.c)).setSelectedTab(i).build());
    }

    @Override // com.android.car.ui.toolbar.j
    public void setTitle(CharSequence charSequence) {
        update(this.mAdapterState.copy().setTitle(b2.b(charSequence)).build());
    }

    public void setSubtitle(u1 u1Var) {
        update(this.mAdapterState.copy().setSubtitle(b2.b(u1Var == null ? null : u1Var.b())).build());
    }

    public void setTitle(u1 u1Var) {
        update(this.mAdapterState.copy().setTitle(b2.b(u1Var == null ? null : u1Var.toString())).build());
    }

    public List<b> setMenuItems(int i) {
        List<b> arrayList;
        Context context = this.mContext;
        if (i == 0) {
            arrayList = new ArrayList<>();
        } else {
            try {
                XmlResourceParser xml = context.getResources().getXml(i);
                try {
                    AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                    ArrayList arrayList2 = new ArrayList();
                    xml.next();
                    xml.next();
                    xml.require(2, null, "MenuItems");
                    while (xml.next() != 3) {
                        arrayList2.add(c.a(context, xml, attributeSetAsAttributeSet));
                    }
                    xml.close();
                    arrayList = arrayList2;
                } catch (Throwable th) {
                    if (xml != null) {
                        try {
                            xml.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException | XmlPullParserException e) {
                throw new RuntimeException("Unable to parse Menu Items", e);
            }
        }
        setMenuItems(arrayList);
        return arrayList;
    }

    @Override // com.android.car.ui.toolbar.j
    public void setNavButtonMode(s7 s7Var) {
        update(this.mAdapterState.copy().setNavButtonMode(s7Var).build());
    }
}
