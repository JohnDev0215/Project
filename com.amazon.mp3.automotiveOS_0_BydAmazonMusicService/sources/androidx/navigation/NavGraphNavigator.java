package androidx.navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.navigation.Navigator;

/* JADX INFO: loaded from: classes2.dex */
@Navigator.Name(NotificationCompat.CATEGORY_NAVIGATION)
public class NavGraphNavigator extends Navigator<NavGraph> {
    private final NavigatorProvider mNavigatorProvider;

    @Override // androidx.navigation.Navigator
    public boolean popBackStack() {
        return true;
    }

    public NavGraphNavigator(@NonNull NavigatorProvider navigatorProvider) {
        this.mNavigatorProvider = navigatorProvider;
    }

    @Override // androidx.navigation.Navigator
    @NonNull
    public NavGraph createDestination() {
        return new NavGraph(this);
    }

    @Override // androidx.navigation.Navigator
    @Nullable
    public NavDestination navigate(@NonNull NavGraph navGraph, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        int startDestination = navGraph.getStartDestination();
        if (startDestination == 0) {
            throw new IllegalStateException("no start destination defined via app:startDestination for " + navGraph.getDisplayName());
        }
        NavDestination navDestinationFindNode = navGraph.findNode(startDestination, false);
        if (navDestinationFindNode == null) {
            throw new IllegalArgumentException("navigation destination " + navGraph.getStartDestDisplayName() + " is not a direct child of this NavGraph");
        }
        return this.mNavigatorProvider.getNavigator(navDestinationFindNode.getNavigatorName()).navigate(navDestinationFindNode, navDestinationFindNode.addInDefaultArgs(bundle), navOptions, extras);
    }
}
