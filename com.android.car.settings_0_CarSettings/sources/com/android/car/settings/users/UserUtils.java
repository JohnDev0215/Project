package com.android.car.settings.users;

import android.content.Context;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.UserManager;
import com.android.car.settings.R;

/* JADX INFO: loaded from: classes5.dex */
public class UserUtils {
    private UserUtils() {
    }

    public static UserInfo getUserInfo(Context context, int userId) {
        UserManager userManager = (UserManager) context.getSystemService("user");
        return userManager.getUserInfo(userId);
    }

    public static String getUserDisplayName(Context context, UserInfo userInfo) {
        return UserHelper.getInstance(context).isCurrentProcessUser(userInfo) ? context.getString(R.string.current_user_name, userInfo.name) : userInfo.name;
    }

    public static boolean isAdminViewingNonAdmin(UserManager userManager, UserInfo userInfo) {
        return userManager.isAdminUser() && !userInfo.isAdmin();
    }

    public static BitmapDrawable scaleUserIcon(Resources res, Bitmap icon) {
        int desiredSize = res.getDimensionPixelSize(R.dimen.icon_size);
        Bitmap scaledIcon = Bitmap.createScaledBitmap(icon, desiredSize, desiredSize, true);
        return new BitmapDrawable(res, scaledIcon);
    }
}
