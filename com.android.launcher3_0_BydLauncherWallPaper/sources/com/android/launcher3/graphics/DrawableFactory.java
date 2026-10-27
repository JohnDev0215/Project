package com.android.launcher3.graphics;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.os.UserHandle;
import android.util.ArrayMap;
import com.android.launcher3.FastBitmapDrawable;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.R;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.util.MainThreadInitializedObject;
import com.android.launcher3.util.ResourceBasedOverride;

/* JADX INFO: loaded from: classes4.dex */
public class DrawableFactory implements ResourceBasedOverride {
    public static final MainThreadInitializedObject<DrawableFactory> INSTANCE = new MainThreadInitializedObject<>(new MainThreadInitializedObject.ObjectProvider() { // from class: com.android.launcher3.graphics.-$$Lambda$DrawableFactory$FTYxVTvyOkCJlhoQiAVQnDi6rGg
        @Override // com.android.launcher3.util.MainThreadInitializedObject.ObjectProvider
        public final Object get(Context context) {
            return DrawableFactory.lambda$static$0(context);
        }
    });
    protected final UserHandle mMyUser = Process.myUserHandle();
    protected final ArrayMap<UserHandle, Bitmap> mUserBadges = new ArrayMap<>();

    static /* synthetic */ DrawableFactory lambda$static$0(Context context) {
        return (DrawableFactory) ResourceBasedOverride.Overrides.getObject(DrawableFactory.class, context.getApplicationContext(), R.string.drawable_factory_class);
    }

    public FastBitmapDrawable newIcon(Context context, ItemInfoWithIcon info) {
        FastBitmapDrawable fastBitmapDrawable;
        if (info.usingLowResIcon()) {
            fastBitmapDrawable = new PlaceHolderIconDrawable(info, IconShape.getShapePath(), context);
        } else {
            fastBitmapDrawable = new FastBitmapDrawable(info);
        }
        fastBitmapDrawable.setIsDisabled(info.isDisabled());
        return fastBitmapDrawable;
    }

    public FastBitmapDrawable newIcon(Context context, BitmapInfo info, ActivityInfo target) {
        if (info.isLowRes()) {
            return new PlaceHolderIconDrawable(info, IconShape.getShapePath(), context);
        }
        return new FastBitmapDrawable(info);
    }

    public PreloadIconDrawable newPendingIcon(Context context, ItemInfoWithIcon info) {
        return new PreloadIconDrawable(info, IconShape.getShapePath(), context);
    }

    public Drawable getBadgeForUser(UserHandle user, Context context) {
        if (this.mMyUser.equals(user)) {
            return null;
        }
        Bitmap userBadge = getUserBadge(user, context);
        FastBitmapDrawable fastBitmapDrawable = new FastBitmapDrawable(userBadge);
        fastBitmapDrawable.setFilterBitmap(true);
        fastBitmapDrawable.setBounds(0, 0, userBadge.getWidth(), userBadge.getHeight());
        return fastBitmapDrawable;
    }

    protected synchronized Bitmap getUserBadge(UserHandle user, Context context) {
        Bitmap bitmap = this.mUserBadges.get(user);
        if (bitmap != null) {
            return bitmap;
        }
        Resources resources = context.getApplicationContext().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.profile_badge_size);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
        Drawable userBadgedDrawableForDensity = context.getPackageManager().getUserBadgedDrawableForDensity(new BitmapDrawable(resources, bitmapCreateBitmap), user, new Rect(0, 0, dimensionPixelSize, dimensionPixelSize), 0);
        if (userBadgedDrawableForDensity instanceof BitmapDrawable) {
            bitmapCreateBitmap = ((BitmapDrawable) userBadgedDrawableForDensity).getBitmap();
        } else {
            bitmapCreateBitmap.eraseColor(0);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            userBadgedDrawableForDensity.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            userBadgedDrawableForDensity.draw(canvas);
            canvas.setBitmap(null);
        }
        this.mUserBadges.put(user, bitmapCreateBitmap);
        return bitmapCreateBitmap;
    }
}
