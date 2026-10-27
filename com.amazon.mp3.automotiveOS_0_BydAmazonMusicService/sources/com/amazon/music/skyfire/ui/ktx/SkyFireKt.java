package com.amazon.music.skyfire.ui.ktx;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import TemplateContainerInterface.v1_0.CreateAndBindStandaloneContainerMethod;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.skyfire.MethodListener;
import com.amazon.music.skyfire.ui.skyfire.SkillMethodBuilder;
import com.amazon.music.skyfire.ui.utils.ReflectionKt;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: SkyFire.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a%\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t*\b\u0012\u0004\u0012\u00020\n0\u0007H\u0086\b\u001a#\u0010\u000b\u001a\u00020\n*\u00020\f2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u0011\u001a/\u0010\u0012\u001a\u00020\u0010*\u00020\u00132\u0012\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0015\"\u00020\n2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0017\u001a$\u0010\u0012\u001a\u00020\u0010*\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u001a\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u0001*\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005\u001a\n\u0010\u0019\u001a\u00020\u0005*\u00020\u001a\u001a\u001e\u0010\u001b\u001a\u00020\u0010*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u0007¨\u0006\u001f"}, d2 = {"findWidgetElementById", "LTouch/WidgetsInterface/v1_0/WidgetElement;", "obj", "", "widgetId", "", "bindableTemplates", "", "T", "LCoreInterface/v1_0/Template;", "LCoreInterface/v1_0/Method;", "buildSkillMethod", "Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "block", "Lkotlin/Function1;", "Lcom/amazon/music/skyfire/ui/skyfire/SkillMethodBuilder;", "", "Lkotlin/ExtensionFunctionType;", "dispatch", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "methods", "", ParserUtil.TAG_QUERY_PARAM_NAME, "(Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;[LCoreInterface/v1_0/Method;Ljava/lang/String;)V", "findElementById", "generateUniqueId", "Lcom/amazon/music/skyfire/ui/skyfire/MethodListener;", "setValue", "LCoreInterface/v1_0/Element;", "propertyName", "value", "DMSFCoreAndroidUI_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class SkyFireKt {
    public static final String generateUniqueId(MethodListener methodListener) {
        Intrinsics.checkNotNullParameter(methodListener, "<this>");
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        return Intrinsics.stringPlus("owner:/", StringsKt__StringsJVMKt.replace$default(string, "-", "", false, 4, (Object) null));
    }

    public static /* synthetic */ void dispatch$default(DispatcherContext dispatcherContext, Method[] methodArr, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        dispatch(dispatcherContext, methodArr, str);
    }

    public static final void dispatch(DispatcherContext dispatcherContext, Method[] methods, String str) {
        Intrinsics.checkNotNullParameter(dispatcherContext, "<this>");
        Intrinsics.checkNotNullParameter(methods, "methods");
        dispatch(dispatcherContext, (List<? extends Method>) CollectionsKt__CollectionsKt.listOf(Arrays.copyOf(methods, methods.length)), str);
    }

    public static /* synthetic */ void dispatch$default(DispatcherContext dispatcherContext, List list, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        dispatch(dispatcherContext, (List<? extends Method>) list, str);
    }

    public static final void dispatch(DispatcherContext dispatcherContext, List<? extends Method> methods, String str) {
        Intrinsics.checkNotNullParameter(dispatcherContext, "<this>");
        Intrinsics.checkNotNullParameter(methods, "methods");
        if (str != null) {
            dispatcherContext.getSkyFireApplication().dispatchMethods(dispatcherContext.getListenerId(), methods, str);
        } else {
            dispatcherContext.getSkyFireApplication().dispatchMethods(dispatcherContext.getListenerId(), methods);
        }
    }

    public static final Method buildSkillMethod(SkyFireConfigurationProvider skyFireConfigurationProvider, Function1<? super SkillMethodBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter(skyFireConfigurationProvider, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        SkillMethodBuilder skillMethodBuilder = new SkillMethodBuilder(skyFireConfigurationProvider);
        block.invoke(skillMethodBuilder);
        return skillMethodBuilder.build();
    }

    public static final WidgetElement findElementById(Template template, String widgetId) {
        Intrinsics.checkNotNullParameter(template, "<this>");
        Intrinsics.checkNotNullParameter(widgetId, "widgetId");
        return findWidgetElementById(template, widgetId);
    }

    public static final void setValue(Element element, String propertyName, Object obj) throws IllegalAccessException, NoSuchFieldException {
        Intrinsics.checkNotNullParameter(element, "<this>");
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        Field declaredField = element.getClass().getDeclaredField(propertyName);
        declaredField.setAccessible(true);
        declaredField.set(element, obj);
    }

    private static final WidgetElement findWidgetElementById(Object obj, String str) throws IllegalAccessException {
        List<Field> listDeclaredInheritedFields;
        WidgetElement widgetElementFindWidgetElementById;
        WidgetElement widgetElementFindWidgetElementById2;
        WidgetElement widgetElement = obj instanceof WidgetElement ? (WidgetElement) obj : null;
        if (Intrinsics.areEqual(widgetElement == null ? null : widgetElement.widgetId(), str)) {
            return (WidgetElement) obj;
        }
        if (obj != null && (listDeclaredInheritedFields = ReflectionKt.declaredInheritedFields(obj.getClass())) != null) {
            for (Field field : listDeclaredInheritedFields) {
                field.setAccessible(true);
                Object obj2 = field.get(obj);
                if (!(obj2 instanceof List)) {
                    if ((obj2 instanceof Element) && (widgetElementFindWidgetElementById2 = findWidgetElementById(obj2, str)) != null) {
                        return widgetElementFindWidgetElementById2;
                    }
                } else {
                    for (Object obj3 : (Iterable) obj2) {
                        if ((obj3 instanceof Element) && (widgetElementFindWidgetElementById = findWidgetElementById(obj3, str)) != null) {
                            return widgetElementFindWidgetElementById;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static final /* synthetic */ <T extends Template> List<T> bindableTemplates(List<? extends Method> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        ArrayList arrayList = new ArrayList();
        for (Method method : list) {
            CreateAndBindStandaloneContainerMethod createAndBindStandaloneContainerMethod = method instanceof CreateAndBindStandaloneContainerMethod ? (CreateAndBindStandaloneContainerMethod) method : null;
            if (createAndBindStandaloneContainerMethod != null) {
                arrayList.add(createAndBindStandaloneContainerMethod);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Template template = ((CreateAndBindStandaloneContainerMethod) it.next()).template();
            Intrinsics.reifiedOperationMarker(2, "T");
            if (template != null) {
                arrayList2.add(template);
            }
        }
        return arrayList2;
    }
}
