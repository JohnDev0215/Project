package com.amazon.music.skyfire.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.skyfire.ui.ktx.AnyKt;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

/* JADX INFO: compiled from: ViewFinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001J\u0016\u0010\u0005\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0001J\u001e\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000eH\u0002J\u0014\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/skyfire/ui/utils/ViewFinder;", "", "()V", "capitalsRegex", "Lkotlin/text/Regex;", "findViews", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "obj", "Landroid/view/View;", "view", "mapOutViewTree", "array", "Landroid/util/SparseArray;", "resolveLayoutId", "", "Landroid/content/Context;", "field", "Ljava/lang/reflect/Field;", "AView", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ViewFinder {
    public static final ViewFinder INSTANCE = new ViewFinder();
    private static final Regex capitalsRegex = new Regex("[A-Z]");

    /* JADX INFO: compiled from: ViewFinder.kt */
    @Target({ElementType.FIELD})
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/amazon/music/skyfire/ui/utils/ViewFinder$AView;", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    public @interface AView {
    }

    private ViewFinder() {
    }

    public final void findViews(Activity activity) throws IllegalAccessException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        findViews(AnyKt.getContentView(activity), activity);
    }

    public final void findViews(Activity activity, Object obj) throws IllegalAccessException {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(obj, "obj");
        findViews(AnyKt.getContentView(activity), obj);
    }

    public final View findViews(View view, Object obj) throws IllegalAccessException {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(obj, "obj");
        SparseArray<View> sparseArray = new SparseArray<>();
        mapOutViewTree(view, sparseArray);
        for (Field field : ReflectionKt.declaredInheritedFields(obj.getClass())) {
            field.setAccessible(true);
            if (((AView) field.getAnnotation(AView.class)) != null) {
                ViewFinder viewFinder = INSTANCE;
                Context context = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "view.context");
                int iResolveLayoutId = viewFinder.resolveLayoutId(context, field);
                if (iResolveLayoutId != 0) {
                    field.set(obj, sparseArray.get(iResolveLayoutId));
                }
            }
        }
        return view;
    }

    private final void mapOutViewTree(View view, SparseArray<View> array) {
        Iterable<View> children;
        if (view.getId() != -1) {
            array.put(view.getId(), view);
        }
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup == null || (children = ViewGroupKt.getChildren(viewGroup)) == null) {
            return;
        }
        Iterator<View> it = children.iterator();
        while (it.hasNext()) {
            INSTANCE.mapOutViewTree(it.next(), array);
        }
    }

    @IdRes
    private final int resolveLayoutId(Context context, Field field) {
        Resources resources = context.getResources();
        String name = field.getName();
        Intrinsics.checkNotNullExpressionValue(name, "field.name");
        return resources.getIdentifier(capitalsRegex.replace(name, new Function1<MatchResult, CharSequence>() { // from class: com.amazon.music.skyfire.ui.utils.ViewFinder.resolveLayoutId.1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                String value = it.getValue();
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                String lowerCase = value.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                return Intrinsics.stringPlus(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, lowerCase);
            }
        }), "id", context.getPackageName());
    }
}
