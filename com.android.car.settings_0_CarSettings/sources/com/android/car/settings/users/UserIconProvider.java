package com.android.car.settings.users;

import android.content.Context;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.UserManager;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.android.internal.util.UserIcons;

/* JADX INFO: loaded from: classes5.dex */
public class UserIconProvider {
    public RoundedBitmapDrawable getRoundedUserIcon(UserInfo userInfo, Context context) {
        UserManager userManager = UserManager.get(context);
        Resources res = context.getResources();
        Bitmap icon = userManager.getUserIcon(userInfo.id);
        if (icon == null) {
            icon = assignDefaultIcon(userManager, res, userInfo);
        }
        return createScaledRoundIcon(res, icon);
    }

    public RoundedBitmapDrawable getRoundedGuestDefaultIcon(Resources resources) {
        return createScaledRoundIcon(resources, getGuestUserDefaultIcon(resources));
    }

    private RoundedBitmapDrawable createScaledRoundIcon(Resources resources, Bitmap icon) {
        BitmapDrawable scaledIcon = UserUtils.scaleUserIcon(resources, icon);
        RoundedBitmapDrawable circleIcon = RoundedBitmapDrawableFactory.create(resources, scaledIcon.getBitmap());
        circleIcon.setCircular(true);
        return circleIcon;
    }

    public Bitmap assignDefaultIcon(UserManager userManager, Resources resources, UserInfo userInfo) {
        Bitmap bitmap;
        if (userInfo.isGuest()) {
            bitmap = getGuestUserDefaultIcon(resources);
        } else {
            bitmap = getUserDefaultIcon(resources, userInfo.id);
        }
        userManager.setUserIcon(userInfo.id, bitmap);
        return bitmap;
    }

    private Bitmap getUserDefaultIcon(Resources resources, int id) {
        return UserIcons.convertToBitmap(UserIcons.getDefaultUserIcon(resources, id, false));
    }

    private Bitmap getGuestUserDefaultIcon(Resources resources) {
        return getUserDefaultIcon(resources, -10000);
    }
}
