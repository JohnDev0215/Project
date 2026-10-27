package androidx.navigation.ui;

import android.view.Menu;
import androidx.customview.widget.Openable;
import androidx.navigation.NavGraph;
import androidx.navigation.ui.AppBarConfiguration;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: AppBarConfiguration.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\n\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086\b\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\n\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086\b\u001a3\u0010\u0000\u001a\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\n\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0086\b¨\u0006\u000e"}, d2 = {"AppBarConfiguration", "Landroidx/navigation/ui/AppBarConfiguration;", "topLevelMenu", "Landroid/view/Menu;", "drawerLayout", "Landroidx/customview/widget/Openable;", "fallbackOnNavigateUpListener", "Lkotlin/Function0;", "", "navGraph", "Landroidx/navigation/NavGraph;", "topLevelDestinationIds", "", "", "navigation-ui-ktx_release"}, k = 2, mv = {1, 1, 16})
public final class AppBarConfigurationKt {

    /* JADX INFO: renamed from: androidx.navigation.ui.AppBarConfigurationKt$AppBarConfiguration$1, reason: invalid class name */
    /* JADX INFO: compiled from: AppBarConfiguration.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
    public static final class AnonymousClass1 extends Lambda implements Function0<Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            return false;
        }
    }

    public static /* synthetic */ AppBarConfiguration AppBarConfiguration$default(NavGraph navGraph, Openable openable, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            openable = null;
        }
        if ((i & 4) != 0) {
            function0 = AnonymousClass1.INSTANCE;
        }
        AppBarConfiguration appBarConfigurationBuild = new AppBarConfiguration.Builder(navGraph).setOpenableLayout(openable).setFallbackOnNavigateUpListener(new AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(function0)).build();
        Intrinsics.checkExpressionValueIsNotNull(appBarConfigurationBuild, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        return appBarConfigurationBuild;
    }

    public static final AppBarConfiguration AppBarConfiguration(NavGraph navGraph, Openable openable, Function0<Boolean> function0) {
        AppBarConfiguration appBarConfigurationBuild = new AppBarConfiguration.Builder(navGraph).setOpenableLayout(openable).setFallbackOnNavigateUpListener(new AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(function0)).build();
        Intrinsics.checkExpressionValueIsNotNull(appBarConfigurationBuild, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        return appBarConfigurationBuild;
    }

    public static /* synthetic */ AppBarConfiguration AppBarConfiguration$default(Menu menu, Openable openable, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            openable = null;
        }
        if ((i & 4) != 0) {
            function0 = new Function0<Boolean>() { // from class: androidx.navigation.ui.AppBarConfigurationKt.AppBarConfiguration.2
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Boolean invoke() {
                    return Boolean.valueOf(invoke2());
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2() {
                    return false;
                }
            };
        }
        AppBarConfiguration appBarConfigurationBuild = new AppBarConfiguration.Builder(menu).setOpenableLayout(openable).setFallbackOnNavigateUpListener(new AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(function0)).build();
        Intrinsics.checkExpressionValueIsNotNull(appBarConfigurationBuild, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        return appBarConfigurationBuild;
    }

    public static final AppBarConfiguration AppBarConfiguration(Menu menu, Openable openable, Function0<Boolean> function0) {
        AppBarConfiguration appBarConfigurationBuild = new AppBarConfiguration.Builder(menu).setOpenableLayout(openable).setFallbackOnNavigateUpListener(new AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(function0)).build();
        Intrinsics.checkExpressionValueIsNotNull(appBarConfigurationBuild, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        return appBarConfigurationBuild;
    }

    public static /* synthetic */ AppBarConfiguration AppBarConfiguration$default(Set set, Openable openable, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            openable = null;
        }
        if ((i & 4) != 0) {
            function0 = new Function0<Boolean>() { // from class: androidx.navigation.ui.AppBarConfigurationKt.AppBarConfiguration.3
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Boolean invoke() {
                    return Boolean.valueOf(invoke2());
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2() {
                    return false;
                }
            };
        }
        AppBarConfiguration appBarConfigurationBuild = new AppBarConfiguration.Builder((Set<Integer>) set).setOpenableLayout(openable).setFallbackOnNavigateUpListener(new AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(function0)).build();
        Intrinsics.checkExpressionValueIsNotNull(appBarConfigurationBuild, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        return appBarConfigurationBuild;
    }

    public static final AppBarConfiguration AppBarConfiguration(Set<Integer> set, Openable openable, Function0<Boolean> function0) {
        AppBarConfiguration appBarConfigurationBuild = new AppBarConfiguration.Builder(set).setOpenableLayout(openable).setFallbackOnNavigateUpListener(new AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(function0)).build();
        Intrinsics.checkExpressionValueIsNotNull(appBarConfigurationBuild, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        return appBarConfigurationBuild;
    }
}
