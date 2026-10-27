package androidx.navigation.ui;

import android.annotation.SuppressLint;
import android.view.Menu;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.Openable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavGraph;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class AppBarConfiguration {

    @Nullable
    private final OnNavigateUpListener mFallbackOnNavigateUpListener;

    @Nullable
    private final Openable mOpenableLayout;

    @NonNull
    private final Set<Integer> mTopLevelDestinations;

    public interface OnNavigateUpListener {
        boolean onNavigateUp();
    }

    private AppBarConfiguration(@NonNull Set<Integer> set, @Nullable Openable openable, @Nullable OnNavigateUpListener onNavigateUpListener) {
        this.mTopLevelDestinations = set;
        this.mOpenableLayout = openable;
        this.mFallbackOnNavigateUpListener = onNavigateUpListener;
    }

    @NonNull
    public Set<Integer> getTopLevelDestinations() {
        return this.mTopLevelDestinations;
    }

    @Nullable
    public Openable getOpenableLayout() {
        return this.mOpenableLayout;
    }

    @Nullable
    @Deprecated
    public DrawerLayout getDrawerLayout() {
        DrawerLayout drawerLayout = this.mOpenableLayout;
        if (drawerLayout instanceof DrawerLayout) {
            return drawerLayout;
        }
        return null;
    }

    @Nullable
    public OnNavigateUpListener getFallbackOnNavigateUpListener() {
        return this.mFallbackOnNavigateUpListener;
    }

    public static final class Builder {

        @Nullable
        private OnNavigateUpListener mFallbackOnNavigateUpListener;

        @Nullable
        private Openable mOpenableLayout;

        @NonNull
        private final Set<Integer> mTopLevelDestinations;

        @NonNull
        @Deprecated
        public Builder setDrawerLayout(@Nullable DrawerLayout drawerLayout) {
            throw null;
        }

        public Builder(@NonNull NavGraph navGraph) {
            HashSet hashSet = new HashSet();
            this.mTopLevelDestinations = hashSet;
            hashSet.add(Integer.valueOf(NavigationUI.findStartDestination(navGraph).getId()));
        }

        public Builder(@NonNull Menu menu) {
            this.mTopLevelDestinations = new HashSet();
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                this.mTopLevelDestinations.add(Integer.valueOf(menu.getItem(i).getItemId()));
            }
        }

        public Builder(@NonNull int... iArr) {
            this.mTopLevelDestinations = new HashSet();
            for (int i : iArr) {
                this.mTopLevelDestinations.add(Integer.valueOf(i));
            }
        }

        public Builder(@NonNull Set<Integer> set) {
            HashSet hashSet = new HashSet();
            this.mTopLevelDestinations = hashSet;
            hashSet.addAll(set);
        }

        @NonNull
        public Builder setOpenableLayout(@Nullable Openable openable) {
            this.mOpenableLayout = openable;
            return this;
        }

        @NonNull
        public Builder setFallbackOnNavigateUpListener(@Nullable OnNavigateUpListener onNavigateUpListener) {
            this.mFallbackOnNavigateUpListener = onNavigateUpListener;
            return this;
        }

        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public AppBarConfiguration build() {
            return new AppBarConfiguration(this.mTopLevelDestinations, this.mOpenableLayout, this.mFallbackOnNavigateUpListener);
        }
    }
}
