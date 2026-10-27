package android.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.internal.R;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class LayoutInflater {
    private static final String ATTR_LAYOUT = "layout";
    private static final String COMPILED_VIEW_DEX_FILE_NAME = "/compiled_view.dex";
    private static final boolean DEBUG = false;
    private static final String TAG_1995 = "blink";
    private static final String TAG_INCLUDE = "include";
    private static final String TAG_MERGE = "merge";
    private static final String TAG_REQUEST_FOCUS = "requestFocus";
    private static final String TAG_TAG = "tag";
    private static final String USE_PRECOMPILED_LAYOUT = "view.precompiled_layout_enabled";
    final Object[] mConstructorArgs;
    protected final Context mContext;
    private Factory mFactory;
    private Factory2 mFactory2;
    private boolean mFactorySet;
    private Filter mFilter;
    private HashMap<String, Boolean> mFilterMap;
    private ClassLoader mPrecompiledClassLoader;
    private Factory2 mPrivateFactory;
    private TypedValue mTempValue;
    private boolean mUseCompiledView;
    private static final String TAG = LayoutInflater.class.getSimpleName();
    private static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];
    static final Class<?>[] mConstructorSignature = {Context.class, AttributeSet.class};
    private static final HashMap<String, Constructor<? extends View>> sConstructorMap = new HashMap<>();
    private static final int[] ATTRS_THEME = {16842752};
    private static final ClassLoader BOOT_CLASS_LOADER = LayoutInflater.class.getClassLoader();

    public interface Factory {
        View onCreateView(String str, Context context, AttributeSet attributeSet);
    }

    public interface Factory2 extends Factory {
        View onCreateView(View view, String str, Context context, AttributeSet attributeSet);
    }

    public interface Filter {
        boolean onLoadClass(Class cls);
    }

    public abstract LayoutInflater cloneInContext(Context context);

    private static class FactoryMerger implements Factory2 {
        private final Factory mF1;
        private final Factory2 mF12;
        private final Factory mF2;
        private final Factory2 mF22;

        FactoryMerger(Factory factory, Factory2 factory2, Factory factory3, Factory2 factory22) {
            this.mF1 = factory;
            this.mF2 = factory3;
            this.mF12 = factory2;
            this.mF22 = factory22;
        }

        @Override // android.view.LayoutInflater.Factory
        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            View viewOnCreateView = this.mF1.onCreateView(str, context, attributeSet);
            return viewOnCreateView != null ? viewOnCreateView : this.mF2.onCreateView(str, context, attributeSet);
        }

        @Override // android.view.LayoutInflater.Factory2
        public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
            Factory2 factory2 = this.mF12;
            View viewOnCreateView = factory2 != null ? factory2.onCreateView(view, str, context, attributeSet) : this.mF1.onCreateView(str, context, attributeSet);
            if (viewOnCreateView != null) {
                return viewOnCreateView;
            }
            Factory2 factory22 = this.mF22;
            return factory22 != null ? factory22.onCreateView(view, str, context, attributeSet) : this.mF2.onCreateView(str, context, attributeSet);
        }
    }

    protected LayoutInflater(Context context) {
        this.mConstructorArgs = new Object[2];
        this.mContext = context;
        initPrecompiledViews();
    }

    protected LayoutInflater(LayoutInflater layoutInflater, Context context) {
        this.mConstructorArgs = new Object[2];
        this.mContext = context;
        this.mFactory = layoutInflater.mFactory;
        this.mFactory2 = layoutInflater.mFactory2;
        this.mPrivateFactory = layoutInflater.mPrivateFactory;
        setFilter(layoutInflater.mFilter);
        initPrecompiledViews();
    }

    public static LayoutInflater from(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater != null) {
            return layoutInflater;
        }
        throw new AssertionError("LayoutInflater not found.");
    }

    public Context getContext() {
        return this.mContext;
    }

    public final Factory getFactory() {
        return this.mFactory;
    }

    public final Factory2 getFactory2() {
        return this.mFactory2;
    }

    public void setFactory(Factory factory) {
        if (this.mFactorySet) {
            throw new IllegalStateException("A factory has already been set on this LayoutInflater");
        }
        if (factory == null) {
            throw new NullPointerException("Given factory can not be null");
        }
        this.mFactorySet = true;
        if (this.mFactory == null) {
            this.mFactory = factory;
        } else {
            this.mFactory = new FactoryMerger(factory, null, this.mFactory, this.mFactory2);
        }
    }

    public void setFactory2(Factory2 factory2) {
        if (this.mFactorySet) {
            throw new IllegalStateException("A factory has already been set on this LayoutInflater");
        }
        if (factory2 == null) {
            throw new NullPointerException("Given factory can not be null");
        }
        this.mFactorySet = true;
        if (this.mFactory == null) {
            this.mFactory2 = factory2;
            this.mFactory = factory2;
        } else {
            FactoryMerger factoryMerger = new FactoryMerger(factory2, factory2, this.mFactory, this.mFactory2);
            this.mFactory2 = factoryMerger;
            this.mFactory = factoryMerger;
        }
    }

    public void setPrivateFactory(Factory2 factory2) {
        if (this.mPrivateFactory == null) {
            this.mPrivateFactory = factory2;
        } else {
            Factory2 factory22 = this.mPrivateFactory;
            this.mPrivateFactory = new FactoryMerger(factory2, factory2, factory22, factory22);
        }
    }

    public Filter getFilter() {
        return this.mFilter;
    }

    public void setFilter(Filter filter) {
        this.mFilter = filter;
        if (filter != null) {
            this.mFilterMap = new HashMap<>();
        }
    }

    private void initPrecompiledViews() {
        initPrecompiledViews(false);
    }

    private void initPrecompiledViews(boolean z) {
        this.mUseCompiledView = z;
        if (!z) {
            this.mPrecompiledClassLoader = null;
            return;
        }
        ApplicationInfo applicationInfo = this.mContext.getApplicationInfo();
        if (applicationInfo.isEmbeddedDexUsed() || applicationInfo.isPrivilegedApp()) {
            this.mUseCompiledView = false;
            return;
        }
        try {
            this.mPrecompiledClassLoader = this.mContext.getClassLoader();
            String str = this.mContext.getCodeCacheDir() + COMPILED_VIEW_DEX_FILE_NAME;
            if (new File(str).exists()) {
                this.mPrecompiledClassLoader = new PathClassLoader(str, this.mPrecompiledClassLoader);
            } else {
                this.mUseCompiledView = false;
            }
        } catch (Throwable unused) {
            this.mUseCompiledView = false;
        }
        if (this.mUseCompiledView) {
            return;
        }
        this.mPrecompiledClassLoader = null;
    }

    public void setPrecompiledLayoutsEnabledForTesting(boolean z) {
        initPrecompiledViews(z);
    }

    public View inflate(int i, ViewGroup viewGroup) {
        return inflate(i, viewGroup, viewGroup != null);
    }

    public View inflate(XmlPullParser xmlPullParser, ViewGroup viewGroup) {
        return inflate(xmlPullParser, viewGroup, viewGroup != null);
    }

    public View inflate(int i, ViewGroup viewGroup, boolean z) {
        Resources resources = getContext().getResources();
        View viewTryInflatePrecompiled = tryInflatePrecompiled(i, resources, viewGroup, z);
        if (viewTryInflatePrecompiled != null) {
            return viewTryInflatePrecompiled;
        }
        XmlResourceParser layout = resources.getLayout(i);
        try {
            return inflate(layout, viewGroup, z);
        } finally {
            layout.close();
        }
    }

    private View tryInflatePrecompiled(int i, Resources resources, ViewGroup viewGroup, boolean z) {
        if (!this.mUseCompiledView) {
            return null;
        }
        Trace.traceBegin(8L, "inflate (precompiled)");
        String resourcePackageName = resources.getResourcePackageName(i);
        try {
            View view = (View) Class.forName("" + resourcePackageName + ".CompiledView", false, this.mPrecompiledClassLoader).getMethod(resources.getResourceEntryName(i), Context.class, Integer.TYPE).invoke(null, this.mContext, Integer.valueOf(i));
            if (view != null && viewGroup != null) {
                XmlResourceParser layout = resources.getLayout(i);
                try {
                    AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(layout);
                    advanceToRootNode(layout);
                    ViewGroup.LayoutParams layoutParamsGenerateLayoutParams = viewGroup.generateLayoutParams(attributeSetAsAttributeSet);
                    if (z) {
                        viewGroup.addView(view, layoutParamsGenerateLayoutParams);
                    } else {
                        view.setLayoutParams(layoutParamsGenerateLayoutParams);
                    }
                    layout.close();
                } catch (Throwable th) {
                    layout.close();
                    throw th;
                }
            }
            Trace.traceEnd(8L);
            return view;
        } catch (Throwable unused) {
            Trace.traceEnd(8L);
            return null;
        }
    }

    private void advanceToRootNode(XmlPullParser xmlPullParser) throws XmlPullParserException, InflateException, IOException {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return;
        }
        throw new InflateException(xmlPullParser.getPositionDescription() + ": No start tag found!");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.View, android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r17v0, types: [android.view.LayoutInflater] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v4 */
    public View inflate(XmlPullParser xmlPullParser, ViewGroup viewGroup, boolean z) {
        char c;
        XmlPullParser xmlPullParser2;
        ViewGroup.LayoutParams layoutParamsGenerateLayoutParams;
        ?? r0 = viewGroup;
        synchronized (this.mConstructorArgs) {
            ?? r9 = 8;
            Trace.traceBegin(8L, "inflate");
            Context context = this.mContext;
            AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xmlPullParser);
            Context context2 = (Context) this.mConstructorArgs[0];
            this.mConstructorArgs[0] = context;
            try {
                try {
                    advanceToRootNode(xmlPullParser);
                    String name = xmlPullParser.getName();
                    try {
                        if (!TAG_MERGE.equals(name)) {
                            c = 1;
                            View viewCreateViewFromTag = createViewFromTag(r0, name, context, attributeSetAsAttributeSet);
                            if (r0 != 0) {
                                layoutParamsGenerateLayoutParams = r0.generateLayoutParams(attributeSetAsAttributeSet);
                                if (!z) {
                                    viewCreateViewFromTag.setLayoutParams(layoutParamsGenerateLayoutParams);
                                }
                                xmlPullParser2 = xmlPullParser;
                            } else {
                                xmlPullParser2 = xmlPullParser;
                                layoutParamsGenerateLayoutParams = null;
                            }
                            rInflateChildren(xmlPullParser2, viewCreateViewFromTag, attributeSetAsAttributeSet, true);
                            if (r0 != 0 && z) {
                                r0.addView(viewCreateViewFromTag, layoutParamsGenerateLayoutParams);
                            }
                            if (r0 == 0 || !z) {
                                r0 = viewCreateViewFromTag;
                            }
                        } else {
                            if (r0 == 0 || !z) {
                                throw new InflateException("<merge /> can be used only with a valid ViewGroup root and attachToRoot=true");
                            }
                            c = 1;
                            rInflate(xmlPullParser, viewGroup, context, attributeSetAsAttributeSet, false);
                        }
                        this.mConstructorArgs[0] = context2;
                        this.mConstructorArgs[c] = null;
                        Trace.traceEnd(8L);
                    } catch (XmlPullParserException e) {
                        e = e;
                        InflateException inflateException = new InflateException(e.getMessage(), e);
                        inflateException.setStackTrace(EMPTY_STACK_TRACE);
                        throw inflateException;
                    } catch (Exception e2) {
                        e = e2;
                        InflateException inflateException2 = new InflateException(getParserStateDescription(context, attributeSetAsAttributeSet) + ": " + e.getMessage(), e);
                        inflateException2.setStackTrace(EMPTY_STACK_TRACE);
                        throw inflateException2;
                    }
                } catch (XmlPullParserException e3) {
                    e = e3;
                } catch (Exception e4) {
                    e = e4;
                } catch (Throwable th) {
                    th = th;
                    r9 = 1;
                    this.mConstructorArgs[0] = context2;
                    this.mConstructorArgs[r9] = null;
                    Trace.traceEnd(8L);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                this.mConstructorArgs[0] = context2;
                this.mConstructorArgs[r9] = null;
                Trace.traceEnd(8L);
                throw th;
            }
        }
        return r0;
    }

    private static String getParserStateDescription(Context context, AttributeSet attributeSet) {
        int attributeSetSourceResId = Resources.getAttributeSetSourceResId(attributeSet);
        if (attributeSetSourceResId == 0) {
            return attributeSet.getPositionDescription();
        }
        return attributeSet.getPositionDescription() + " in " + context.getResources().getResourceName(attributeSetSourceResId);
    }

    private final boolean verifyClassLoader(Constructor<? extends View> constructor) {
        ClassLoader classLoader = constructor.getDeclaringClass().getClassLoader();
        if (classLoader == BOOT_CLASS_LOADER) {
            return true;
        }
        ClassLoader classLoader2 = this.mContext.getClassLoader();
        while (classLoader != classLoader2) {
            classLoader2 = classLoader2.getParent();
            if (classLoader2 == null) {
                return false;
            }
        }
        return true;
    }

    public final View createView(String str, String str2, AttributeSet attributeSet) throws InflateException, ClassNotFoundException {
        Context context = (Context) this.mConstructorArgs[0];
        if (context == null) {
            context = this.mContext;
        }
        return createView(context, str, str2, attributeSet);
    }

    public final View createView(Context context, String str, String str2, AttributeSet attributeSet) throws InflateException, ClassNotFoundException {
        String str3;
        String str4;
        Objects.requireNonNull(context);
        Objects.requireNonNull(str);
        Constructor<? extends View> constructor = sConstructorMap.get(str);
        Class cls = null;
        if (constructor != null && !verifyClassLoader(constructor)) {
            sConstructorMap.remove(str);
            constructor = null;
        }
        try {
            try {
                try {
                    Trace.traceBegin(8L, str);
                    if (constructor == null) {
                        if (str2 != null) {
                            str4 = str2 + str;
                        } else {
                            str4 = str;
                        }
                        Class clsAsSubclass = Class.forName(str4, false, this.mContext.getClassLoader()).asSubclass(View.class);
                        if (this.mFilter != null && clsAsSubclass != null && !this.mFilter.onLoadClass(clsAsSubclass)) {
                            failNotAllowed(str, str2, context, attributeSet);
                        }
                        constructor = clsAsSubclass.getConstructor(mConstructorSignature);
                        constructor.setAccessible(true);
                        sConstructorMap.put(str, constructor);
                    } else if (this.mFilter != null) {
                        Boolean bool = this.mFilterMap.get(str);
                        if (bool == null) {
                            if (str2 != null) {
                                str3 = str2 + str;
                            } else {
                                str3 = str;
                            }
                            Class clsAsSubclass2 = Class.forName(str3, false, this.mContext.getClassLoader()).asSubclass(View.class);
                            boolean z = clsAsSubclass2 != null && this.mFilter.onLoadClass(clsAsSubclass2);
                            this.mFilterMap.put(str, Boolean.valueOf(z));
                            if (!z) {
                                failNotAllowed(str, str2, context, attributeSet);
                            }
                        } else if (bool.equals(Boolean.FALSE)) {
                            failNotAllowed(str, str2, context, attributeSet);
                        }
                    }
                    Object obj = this.mConstructorArgs[0];
                    this.mConstructorArgs[0] = context;
                    Object[] objArr = this.mConstructorArgs;
                    objArr[1] = attributeSet;
                    try {
                        View viewNewInstance = constructor.newInstance(objArr);
                        if (viewNewInstance instanceof ViewStub) {
                            ((ViewStub) viewNewInstance).setLayoutInflater(cloneInContext((Context) objArr[0]));
                        }
                        return viewNewInstance;
                    } finally {
                        this.mConstructorArgs[0] = obj;
                    }
                } catch (ClassNotFoundException e) {
                    throw e;
                } catch (Exception e2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(getParserStateDescription(context, attributeSet));
                    sb.append(": Error inflating class ");
                    sb.append(0 == 0 ? LocalMusicUtils.BYD_SHOW_CAR_ARTIST : cls.getName());
                    InflateException inflateException = new InflateException(sb.toString(), e2);
                    inflateException.setStackTrace(EMPTY_STACK_TRACE);
                    throw inflateException;
                }
            } catch (ClassCastException e3) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(getParserStateDescription(context, attributeSet));
                sb2.append(": Class is not a View ");
                if (str2 != null) {
                    str = str2 + str;
                }
                sb2.append(str);
                InflateException inflateException2 = new InflateException(sb2.toString(), e3);
                inflateException2.setStackTrace(EMPTY_STACK_TRACE);
                throw inflateException2;
            } catch (NoSuchMethodException e4) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(getParserStateDescription(context, attributeSet));
                sb3.append(": Error inflating class ");
                if (str2 != null) {
                    str = str2 + str;
                }
                sb3.append(str);
                InflateException inflateException3 = new InflateException(sb3.toString(), e4);
                inflateException3.setStackTrace(EMPTY_STACK_TRACE);
                throw inflateException3;
            }
        } finally {
            Trace.traceEnd(8L);
        }
    }

    private void failNotAllowed(String str, String str2, Context context, AttributeSet attributeSet) {
        StringBuilder sb = new StringBuilder();
        sb.append(getParserStateDescription(context, attributeSet));
        sb.append(": Class not allowed to be inflated ");
        if (str2 != null) {
            str = str2 + str;
        }
        sb.append(str);
        throw new InflateException(sb.toString());
    }

    protected View onCreateView(String str, AttributeSet attributeSet) throws ClassNotFoundException {
        return createView(str, "android.view.", attributeSet);
    }

    protected View onCreateView(View view, String str, AttributeSet attributeSet) throws ClassNotFoundException {
        return onCreateView(str, attributeSet);
    }

    public View onCreateView(Context context, View view, String str, AttributeSet attributeSet) throws ClassNotFoundException {
        return onCreateView(view, str, attributeSet);
    }

    private View createViewFromTag(View view, String str, Context context, AttributeSet attributeSet) {
        return createViewFromTag(view, str, context, attributeSet, false);
    }

    View createViewFromTag(View view, String str, Context context, AttributeSet attributeSet, boolean z) {
        View viewCreateView;
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        if (!z) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ATTRS_THEME);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            if (resourceId != 0) {
                context = new ContextThemeWrapper(context, resourceId);
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        try {
            View viewTryCreateView = tryCreateView(view, str, context, attributeSet);
            if (viewTryCreateView != null) {
                return viewTryCreateView;
            }
            Object obj = this.mConstructorArgs[0];
            this.mConstructorArgs[0] = context;
            try {
                if (-1 == str.indexOf(46)) {
                    viewCreateView = onCreateView(context, view, str, attributeSet);
                } else {
                    viewCreateView = createView(context, str, null, attributeSet);
                }
                this.mConstructorArgs[0] = obj;
                return viewCreateView;
            } catch (Throwable th) {
                this.mConstructorArgs[0] = obj;
                throw th;
            }
        } catch (InflateException e) {
            throw e;
        } catch (ClassNotFoundException e2) {
            InflateException inflateException = new InflateException(getParserStateDescription(context, attributeSet) + ": Error inflating class " + str, e2);
            inflateException.setStackTrace(EMPTY_STACK_TRACE);
            throw inflateException;
        } catch (Exception e3) {
            InflateException inflateException2 = new InflateException(getParserStateDescription(context, attributeSet) + ": Error inflating class " + str, e3);
            inflateException2.setStackTrace(EMPTY_STACK_TRACE);
            throw inflateException2;
        }
    }

    public final View tryCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView;
        Factory2 factory2;
        if (str.equals(TAG_1995)) {
            return new BlinkLayout(context, attributeSet);
        }
        Factory2 factory22 = this.mFactory2;
        if (factory22 != null) {
            viewOnCreateView = factory22.onCreateView(view, str, context, attributeSet);
        } else {
            Factory factory = this.mFactory;
            viewOnCreateView = factory != null ? factory.onCreateView(str, context, attributeSet) : null;
        }
        return (viewOnCreateView != null || (factory2 = this.mPrivateFactory) == null) ? viewOnCreateView : factory2.onCreateView(view, str, context, attributeSet);
    }

    final void rInflateChildren(XmlPullParser xmlPullParser, View view, AttributeSet attributeSet, boolean z) throws XmlPullParserException, IOException {
        rInflate(xmlPullParser, view, view.getContext(), attributeSet, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0075, code lost:
    
        if (r1 == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0077, code lost:
    
        r8.restoreDefaultFocus();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007a, code lost:
    
        if (r11 == false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007c, code lost:
    
        r8.onFinishInflate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007f, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void rInflate(org.xmlpull.v1.XmlPullParser r7, android.view.View r8, android.content.Context r9, android.util.AttributeSet r10, boolean r11) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r6 = this;
            int r0 = r7.getDepth()
            r1 = 0
        L5:
            int r2 = r7.next()
            r3 = 3
            if (r2 != r3) goto L12
            int r3 = r7.getDepth()
            if (r3 <= r0) goto L75
        L12:
            r3 = 1
            if (r2 == r3) goto L75
            r4 = 2
            if (r2 == r4) goto L19
            goto L5
        L19:
            java.lang.String r2 = r7.getName()
            java.lang.String r4 = "requestFocus"
            boolean r4 = r4.equals(r2)
            if (r4 == 0) goto L2b
            consumeChildElements(r7)
            r1 = r3
            goto L5
        L2b:
            java.lang.String r4 = "tag"
            boolean r4 = r4.equals(r2)
            if (r4 == 0) goto L38
            r6.parseViewTag(r7, r8, r10)
            goto L5
        L38:
            java.lang.String r4 = "include"
            boolean r4 = r4.equals(r2)
            if (r4 == 0) goto L52
            int r2 = r7.getDepth()
            if (r2 == 0) goto L4a
            r6.parseInclude(r7, r9, r8, r10)
            goto L5
        L4a:
            android.view.InflateException r7 = new android.view.InflateException
            java.lang.String r8 = "<include /> cannot be the root element"
            r7.<init>(r8)
            throw r7
        L52:
            java.lang.String r4 = "merge"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L6d
            android.view.View r2 = r6.createViewFromTag(r8, r2, r9, r10)
            r4 = r8
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4
            android.view.ViewGroup$LayoutParams r5 = r4.generateLayoutParams(r10)
            r6.rInflateChildren(r7, r2, r10, r3)
            r4.addView(r2, r5)
            goto L5
        L6d:
            android.view.InflateException r7 = new android.view.InflateException
            java.lang.String r8 = "<merge /> must be the root element"
            r7.<init>(r8)
            throw r7
        L75:
            if (r1 == 0) goto L7a
            r8.restoreDefaultFocus()
        L7a:
            if (r11 == 0) goto L7f
            r8.onFinishInflate()
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.LayoutInflater.rInflate(org.xmlpull.v1.XmlPullParser, android.view.View, android.content.Context, android.util.AttributeSet, boolean):void");
    }

    private void parseViewTag(XmlPullParser xmlPullParser, View view, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.ViewTag);
        view.setTag(typedArrayObtainStyledAttributes.getResourceId(1, 0), typedArrayObtainStyledAttributes.getText(0));
        typedArrayObtainStyledAttributes.recycle();
        consumeChildElements(xmlPullParser);
    }

    private void parseInclude(XmlPullParser xmlPullParser, Context context, View view, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        int next;
        Context contextThemeWrapper = context;
        if (!(view instanceof ViewGroup)) {
            throw new InflateException("<include /> can only be used inside of a ViewGroup");
        }
        TypedArray typedArrayObtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(attributeSet, ATTRS_THEME);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        boolean z = resourceId != 0;
        if (z) {
            contextThemeWrapper = new ContextThemeWrapper(contextThemeWrapper, resourceId);
        }
        typedArrayObtainStyledAttributes.recycle();
        ViewGroup.LayoutParams layoutParamsGenerateLayoutParams = null;
        int attributeResourceValue = attributeSet.getAttributeResourceValue(null, "layout", 0);
        if (attributeResourceValue == 0) {
            String attributeValue = attributeSet.getAttributeValue(null, "layout");
            if (attributeValue == null || attributeValue.length() <= 0) {
                throw new InflateException("You must specify a layout in the include tag: <include layout=\"@layout/layoutID\" />");
            }
            attributeResourceValue = contextThemeWrapper.getResources().getIdentifier(attributeValue.substring(1), "attr", contextThemeWrapper.getPackageName());
        }
        if (this.mTempValue == null) {
            this.mTempValue = new TypedValue();
        }
        if (attributeResourceValue != 0 && contextThemeWrapper.getTheme().resolveAttribute(attributeResourceValue, this.mTempValue, true)) {
            attributeResourceValue = this.mTempValue.resourceId;
        }
        if (attributeResourceValue == 0) {
            throw new InflateException("You must specify a valid layout reference. The layout ID " + attributeSet.getAttributeValue(null, "layout") + " is not valid.");
        }
        if (tryInflatePrecompiled(attributeResourceValue, contextThemeWrapper.getResources(), (ViewGroup) view, true) == null) {
            XmlResourceParser layout = contextThemeWrapper.getResources().getLayout(attributeResourceValue);
            try {
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(layout);
                do {
                    next = layout.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new InflateException(getParserStateDescription(contextThemeWrapper, attributeSetAsAttributeSet) + ": No start tag found!");
                }
                String name = layout.getName();
                if (TAG_MERGE.equals(name)) {
                    rInflate(layout, view, contextThemeWrapper, attributeSetAsAttributeSet, false);
                } else {
                    View viewCreateViewFromTag = createViewFromTag(view, name, contextThemeWrapper, attributeSetAsAttributeSet, z);
                    ViewGroup viewGroup = (ViewGroup) view;
                    TypedArray typedArrayObtainStyledAttributes2 = contextThemeWrapper.obtainStyledAttributes(attributeSet, R.styleable.Include);
                    int resourceId2 = typedArrayObtainStyledAttributes2.getResourceId(0, -1);
                    int i = typedArrayObtainStyledAttributes2.getInt(1, -1);
                    typedArrayObtainStyledAttributes2.recycle();
                    try {
                        layoutParamsGenerateLayoutParams = viewGroup.generateLayoutParams(attributeSet);
                    } catch (RuntimeException unused) {
                    }
                    if (layoutParamsGenerateLayoutParams == null) {
                        layoutParamsGenerateLayoutParams = viewGroup.generateLayoutParams(attributeSetAsAttributeSet);
                    }
                    viewCreateViewFromTag.setLayoutParams(layoutParamsGenerateLayoutParams);
                    rInflateChildren(layout, viewCreateViewFromTag, attributeSetAsAttributeSet, true);
                    if (resourceId2 != -1) {
                        viewCreateViewFromTag.setId(resourceId2);
                    }
                    if (i == 0) {
                        viewCreateViewFromTag.setVisibility(0);
                    } else if (i == 1) {
                        viewCreateViewFromTag.setVisibility(4);
                    } else if (i == 2) {
                        viewCreateViewFromTag.setVisibility(8);
                    }
                    viewGroup.addView(viewCreateViewFromTag);
                }
            } finally {
                layout.close();
            }
        }
        consumeChildElements(xmlPullParser);
    }

    static final void consumeChildElements(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int next;
        int depth = xmlPullParser.getDepth();
        do {
            next = xmlPullParser.next();
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
        } while (next != 1);
    }

    private static class BlinkLayout extends FrameLayout {
        private static final int BLINK_DELAY = 500;
        private static final int MESSAGE_BLINK = 66;
        private boolean mBlink;
        private boolean mBlinkState;
        private final Handler mHandler;

        public BlinkLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mHandler = new Handler(new Handler.Callback() { // from class: android.view.LayoutInflater.BlinkLayout.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    if (message.what != 66) {
                        return false;
                    }
                    if (BlinkLayout.this.mBlink) {
                        BlinkLayout.this.mBlinkState = !r3.mBlinkState;
                        BlinkLayout.this.makeBlink();
                    }
                    BlinkLayout.this.invalidate();
                    return true;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void makeBlink() {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(66), 500L);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.mBlink = true;
            this.mBlinkState = true;
            makeBlink();
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.mBlink = false;
            this.mBlinkState = true;
            this.mHandler.removeMessages(66);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void dispatchDraw(Canvas canvas) {
            if (this.mBlinkState) {
                super.dispatchDraw(canvas);
            }
        }
    }
}
