package android.car.userlib;

import android.content.Context;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.os.UserManager;
import com.android.internal.util.Preconditions;
import com.android.internal.util.UserIcons;
import com.google.android.collect.Sets;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class UserHelper {
    private static final Set<String> DEFAULT_NON_ADMIN_RESTRICTIONS = Sets.newArraySet(new String[]{"no_factory_reset"});

    private UserHelper() {
        throw new UnsupportedOperationException("contains only static methods");
    }

    public static String safeName(String name) {
        if (name == null) {
            return name;
        }
        return name.length() + "_chars";
    }

    public static boolean isHeadlessSystemUser(int userId) {
        return userId == 0 && UserManager.isHeadlessSystemUserMode();
    }

    public static void setDefaultNonAdminRestrictions(Context context, UserInfo user, boolean enable) {
        Preconditions.checkArgument(context != null, "Context cannot be null");
        Preconditions.checkArgument(user != null, "User cannot be null");
        UserManager userManager = UserManager.get(context);
        for (String restriction : DEFAULT_NON_ADMIN_RESTRICTIONS) {
            userManager.setUserRestriction(restriction, enable, user.getUserHandle());
        }
    }

    public static Bitmap assignDefaultIcon(Context context, UserInfo user) {
        Preconditions.checkArgument(context != null, "Context cannot be null");
        Preconditions.checkArgument(user != null, "User cannot be null");
        int idForIcon = user.isGuest() ? -10000 : user.id;
        Bitmap bitmap = UserIcons.convertToBitmap(UserIcons.getDefaultUserIcon(context.getResources(), idForIcon, false));
        UserManager.get(context).setUserIcon(user.id, bitmap);
        return bitmap;
    }
}
