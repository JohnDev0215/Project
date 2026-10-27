package android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.HardwareRenderer;
import android.graphics.Picture;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RenderNode;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.provider.SettingsStringUtil;
import android.telephony.SmsManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import libcore.util.HexEncoding;

/* JADX INFO: loaded from: classes3.dex */
public class ViewDebug {
    private static final int CAPTURE_TIMEOUT = 6000;
    public static final boolean DEBUG_DRAG = false;
    public static final boolean DEBUG_POSITIONING = false;
    private static final String REMOTE_COMMAND_CAPTURE = "CAPTURE";
    private static final String REMOTE_COMMAND_CAPTURE_LAYERS = "CAPTURE_LAYERS";
    private static final String REMOTE_COMMAND_DUMP = "DUMP";
    public static final String REMOTE_COMMAND_DUMP_ENCODED = "DUMP_ENCODED";
    private static final String REMOTE_COMMAND_DUMP_THEME = "DUMP_THEME";
    private static final String REMOTE_COMMAND_INVALIDATE = "INVALIDATE";
    private static final String REMOTE_COMMAND_OUTPUT_DISPLAYLIST = "OUTPUT_DISPLAYLIST";
    private static final String REMOTE_COMMAND_REQUEST_LAYOUT = "REQUEST_LAYOUT";
    private static final String REMOTE_PROFILE = "PROFILE";

    @Deprecated
    public static final boolean TRACE_HIERARCHY = false;

    @Deprecated
    public static final boolean TRACE_RECYCLER = false;
    private static HashMap<Class<?>, PropertyInfo<CapturedViewProperty, ?>[]> sCapturedViewProperties;
    private static HashMap<Class<?>, PropertyInfo<ExportedProperty, ?>[]> sExportProperties;

    public interface CanvasProvider {
        Bitmap createBitmap();

        Canvas getCanvas(View view, int i, int i2);
    }

    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CapturedViewProperty {
        boolean retrieveReturn() default false;
    }

    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExportedProperty {
        String category() default "";

        boolean deepExport() default false;

        FlagToString[] flagMapping() default {};

        boolean formatToHexString() default false;

        boolean hasAdjacentMapping() default false;

        IntToString[] indexMapping() default {};

        IntToString[] mapping() default {};

        String prefix() default "";

        boolean resolveId() default false;
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FlagToString {
        int equals();

        int mask();

        String name();

        boolean outputIf() default true;
    }

    public interface HierarchyHandler {
        void dumpViewHierarchyWithProperties(BufferedWriter bufferedWriter, int i);

        View findHierarchyView(String str, int i);
    }

    @Deprecated
    public enum HierarchyTraceType {
        INVALIDATE,
        INVALIDATE_CHILD,
        INVALIDATE_CHILD_IN_PARENT,
        REQUEST_LAYOUT,
        ON_LAYOUT,
        ON_MEASURE,
        DRAW,
        BUILD_CACHE
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface IntToString {
        int from();

        String to();
    }

    @Deprecated
    public enum RecyclerTraceType {
        NEW_VIEW,
        BIND_VIEW,
        RECYCLE_FROM_ACTIVE_HEAP,
        RECYCLE_FROM_SCRAP_HEAP,
        MOVE_TO_SCRAP_HEAP,
        MOVE_FROM_ACTIVE_TO_SCRAP_HEAP
    }

    interface ViewOperation {
        default void pre() {
        }

        void run();
    }

    static /* synthetic */ boolean lambda$convertToPropertyInfos$8(Object obj) {
        return obj != null;
    }

    @Deprecated
    public static void startHierarchyTracing(String str, View view) {
    }

    @Deprecated
    public static void startRecyclerTracing(String str, View view) {
    }

    @Deprecated
    public static void stopHierarchyTracing() {
    }

    @Deprecated
    public static void stopRecyclerTracing() {
    }

    @Deprecated
    public static void trace(View view, HierarchyTraceType hierarchyTraceType) {
    }

    @Deprecated
    public static void trace(View view, RecyclerTraceType recyclerTraceType, int... iArr) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class PropertyInfo<T extends Annotation, R extends AccessibleObject & Member> {
        public final R member;
        public final String name;
        public final T property;
        public final Class<?> returnType;
        public String entrySuffix = "";
        public String valueSuffix = "";

        public abstract Object invoke(Object obj) throws Exception;

        PropertyInfo(Class<T> cls, R r, Class<?> cls2) {
            this.member = r;
            this.name = r.getName();
            this.property = (T) r.getAnnotation(cls);
            this.returnType = cls2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static <T extends Annotation> PropertyInfo<T, ?> forMethod(Method method, Class<T> cls) {
            try {
                if (method.getReturnType() == Void.class) {
                    return null;
                }
                if (method.getParameterTypes().length != 0 || !method.isAnnotationPresent(cls)) {
                    return null;
                }
                method.setAccessible(true);
                MethodPI methodPI = new MethodPI(method, cls);
                methodPI.entrySuffix = "()";
                methodPI.valueSuffix = ";";
                return methodPI;
            } catch (NoClassDefFoundError unused) {
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static <T extends Annotation> PropertyInfo<T, ?> forField(Field field, Class<T> cls) {
            if (!field.isAnnotationPresent(cls)) {
                return null;
            }
            field.setAccessible(true);
            return new FieldPI(field, cls);
        }
    }

    private static class MethodPI<T extends Annotation> extends PropertyInfo<T, Method> {
        MethodPI(Method method, Class<T> cls) {
            super(cls, method, method.getReturnType());
        }

        @Override // android.view.ViewDebug.PropertyInfo
        public Object invoke(Object obj) throws Exception {
            return ((Method) this.member).invoke(obj, new Object[0]);
        }
    }

    private static class FieldPI<T extends Annotation> extends PropertyInfo<T, Field> {
        FieldPI(Field field, Class<T> cls) {
            super(cls, field, field.getType());
        }

        @Override // android.view.ViewDebug.PropertyInfo
        public Object invoke(Object obj) throws Exception {
            return ((Field) this.member).get(obj);
        }
    }

    public static long getViewInstanceCount() {
        return Debug.countInstancesOfClass(View.class);
    }

    public static long getViewRootImplCount() {
        return Debug.countInstancesOfClass(ViewRootImpl.class);
    }

    static void dispatchCommand(View view, String str, String str2, OutputStream outputStream) throws Throwable {
        View rootView = view.getRootView();
        if (REMOTE_COMMAND_DUMP.equalsIgnoreCase(str)) {
            dump(rootView, false, true, outputStream);
            return;
        }
        if (REMOTE_COMMAND_DUMP_THEME.equalsIgnoreCase(str)) {
            dumpTheme(rootView, outputStream);
            return;
        }
        if (REMOTE_COMMAND_DUMP_ENCODED.equalsIgnoreCase(str)) {
            dumpEncoded(rootView, outputStream);
            return;
        }
        if (REMOTE_COMMAND_CAPTURE_LAYERS.equalsIgnoreCase(str)) {
            captureLayers(rootView, new DataOutputStream(outputStream));
            return;
        }
        String[] strArrSplit = str2.split(" ");
        if (REMOTE_COMMAND_CAPTURE.equalsIgnoreCase(str)) {
            capture(rootView, outputStream, strArrSplit[0]);
            return;
        }
        if (REMOTE_COMMAND_OUTPUT_DISPLAYLIST.equalsIgnoreCase(str)) {
            outputDisplayList(rootView, strArrSplit[0]);
            return;
        }
        if (REMOTE_COMMAND_INVALIDATE.equalsIgnoreCase(str)) {
            invalidate(rootView, strArrSplit[0]);
        } else if (REMOTE_COMMAND_REQUEST_LAYOUT.equalsIgnoreCase(str)) {
            requestLayout(rootView, strArrSplit[0]);
        } else if (REMOTE_PROFILE.equalsIgnoreCase(str)) {
            profile(rootView, outputStream, strArrSplit[0]);
        }
    }

    public static View findView(View view, String str) {
        if (str.indexOf(64) != -1) {
            String[] strArrSplit = str.split("@");
            String str2 = strArrSplit[0];
            int i = (int) Long.parseLong(strArrSplit[1], 16);
            View rootView = view.getRootView();
            if (rootView instanceof ViewGroup) {
                return findView((ViewGroup) rootView, str2, i);
            }
            return null;
        }
        return view.getRootView().findViewById(view.getResources().getIdentifier(str, null, null));
    }

    private static void invalidate(View view, String str) {
        View viewFindView = findView(view, str);
        if (viewFindView != null) {
            viewFindView.postInvalidate();
        }
    }

    private static void requestLayout(View view, String str) {
        final View viewFindView = findView(view, str);
        if (viewFindView != null) {
            view.post(new Runnable() { // from class: android.view.ViewDebug.1
                @Override // java.lang.Runnable
                public void run() {
                    viewFindView.requestLayout();
                }
            });
        }
    }

    private static void profile(View view, OutputStream outputStream, String str) throws Throwable {
        View viewFindView = findView(view, str);
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream), 32768);
                try {
                    if (viewFindView != null) {
                        profileViewAndChildren(viewFindView, bufferedWriter2);
                    } else {
                        bufferedWriter2.write("-1 -1 -1");
                        bufferedWriter2.newLine();
                    }
                    bufferedWriter2.write("DONE.");
                    bufferedWriter2.newLine();
                    bufferedWriter2.close();
                } catch (Exception e) {
                    e = e;
                    bufferedWriter = bufferedWriter2;
                    Log.w("View", "Problem profiling the view:", e);
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void profileViewAndChildren(View view, BufferedWriter bufferedWriter) throws IOException {
        profileViewAndChildren(view, RenderNode.create("ViewDebug", null), bufferedWriter, true);
    }

    private static void profileViewAndChildren(View view, RenderNode renderNode, BufferedWriter bufferedWriter, boolean z) throws IOException {
        long jProfileViewMeasure = (z || (view.mPrivateFlags & 2048) != 0) ? profileViewMeasure(view) : 0L;
        long jProfileViewLayout = (z || (view.mPrivateFlags & 8192) != 0) ? profileViewLayout(view) : 0L;
        long jProfileViewDraw = (!z && view.willNotDraw() && (view.mPrivateFlags & 32) == 0) ? 0L : profileViewDraw(view, renderNode);
        bufferedWriter.write(String.valueOf(jProfileViewMeasure));
        bufferedWriter.write(32);
        bufferedWriter.write(String.valueOf(jProfileViewLayout));
        bufferedWriter.write(32);
        bufferedWriter.write(String.valueOf(jProfileViewDraw));
        bufferedWriter.newLine();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                profileViewAndChildren(viewGroup.getChildAt(i), renderNode, bufferedWriter, false);
            }
        }
    }

    private static long profileViewMeasure(final View view) {
        return profileViewOperation(view, new ViewOperation() { // from class: android.view.ViewDebug.2
            @Override // android.view.ViewDebug.ViewOperation
            public void pre() {
                forceLayout(view);
            }

            private void forceLayout(View view2) {
                view2.forceLayout();
                if (view2 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    int childCount = viewGroup.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        forceLayout(viewGroup.getChildAt(i));
                    }
                }
            }

            @Override // android.view.ViewDebug.ViewOperation
            public void run() {
                View view2 = view;
                view2.measure(view2.mOldWidthMeasureSpec, view.mOldHeightMeasureSpec);
            }
        });
    }

    private static long profileViewLayout(final View view) {
        return profileViewOperation(view, new ViewOperation() { // from class: android.view.-$$Lambda$ViewDebug$KjjRb8iNM1tS4RhDjp3Il7Cd6mo
            @Override // android.view.ViewDebug.ViewOperation
            public final void run() {
                View view2 = view;
                view2.layout(view2.mLeft, view2.mTop, view2.mRight, view2.mBottom);
            }
        });
    }

    private static long profileViewDraw(final View view, RenderNode renderNode) {
        DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        if (displayMetrics == null) {
            return 0L;
        }
        if (view.isHardwareAccelerated()) {
            final RecordingCanvas recordingCanvasBeginRecording = renderNode.beginRecording(displayMetrics.widthPixels, displayMetrics.heightPixels);
            try {
                return profileViewOperation(view, new ViewOperation() { // from class: android.view.-$$Lambda$ViewDebug$eY5-5-ddRdiK71CQBBR6lR4-IfA
                    @Override // android.view.ViewDebug.ViewOperation
                    public final void run() {
                        view.draw(recordingCanvasBeginRecording);
                    }
                });
            } finally {
                renderNode.endRecording();
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(displayMetrics, displayMetrics.widthPixels, displayMetrics.heightPixels, Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmapCreateBitmap);
        try {
            return profileViewOperation(view, new ViewOperation() { // from class: android.view.-$$Lambda$ViewDebug$B72BTcBlWhn1JEH3nwGbfDCHlIA
                @Override // android.view.ViewDebug.ViewOperation
                public final void run() {
                    view.draw(canvas);
                }
            });
        } finally {
            canvas.setBitmap(null);
            bitmapCreateBitmap.recycle();
        }
    }

    private static long profileViewOperation(View view, final ViewOperation viewOperation) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final long[] jArr = new long[1];
        view.post(new Runnable() { // from class: android.view.-$$Lambda$ViewDebug$_snuW238lL27-fA4eG2Fr0opERc
            @Override // java.lang.Runnable
            public final void run() {
                ViewDebug.lambda$profileViewOperation$3(viewOperation, jArr, countDownLatch);
            }
        });
        try {
            if (!countDownLatch.await(6000L, TimeUnit.MILLISECONDS)) {
                Log.w("View", "Could not complete the profiling of the view " + view);
                return -1L;
            }
            return jArr[0];
        } catch (InterruptedException unused) {
            Log.w("View", "Could not complete the profiling of the view " + view);
            Thread.currentThread().interrupt();
            return -1L;
        }
    }

    static /* synthetic */ void lambda$profileViewOperation$3(ViewOperation viewOperation, long[] jArr, CountDownLatch countDownLatch) {
        try {
            viewOperation.pre();
            long jThreadCpuTimeNanos = Debug.threadCpuTimeNanos();
            viewOperation.run();
            jArr[0] = Debug.threadCpuTimeNanos() - jThreadCpuTimeNanos;
        } finally {
            countDownLatch.countDown();
        }
    }

    public static void captureLayers(View view, DataOutputStream dataOutputStream) throws IOException {
        try {
            Rect rect = new Rect();
            try {
                view.mAttachInfo.mSession.getDisplayFrame(view.mAttachInfo.mWindow, rect);
            } catch (RemoteException unused) {
            }
            dataOutputStream.writeInt(rect.width());
            dataOutputStream.writeInt(rect.height());
            captureViewLayer(view, dataOutputStream, true);
            dataOutputStream.write(2);
        } finally {
            dataOutputStream.close();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v3 */
    private static void captureViewLayer(View view, DataOutputStream dataOutputStream, boolean z) throws IOException {
        ?? r8 = (view.getVisibility() == 0 && z) ? 1 : 0;
        if ((view.mPrivateFlags & 128) != 128) {
            int id = view.getId();
            String simpleName = view.getClass().getSimpleName();
            if (id != -1) {
                simpleName = resolveId(view.getContext(), id).toString();
            }
            dataOutputStream.write(1);
            dataOutputStream.writeUTF(simpleName);
            dataOutputStream.writeByte(r8);
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            dataOutputStream.writeInt(iArr[0]);
            dataOutputStream.writeInt(iArr[1]);
            dataOutputStream.flush();
            Bitmap bitmapPerformViewCapture = performViewCapture(view, true);
            if (bitmapPerformViewCapture != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bitmapPerformViewCapture.getWidth() * bitmapPerformViewCapture.getHeight() * 2);
                bitmapPerformViewCapture.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                dataOutputStream.writeInt(byteArrayOutputStream.size());
                byteArrayOutputStream.writeTo(dataOutputStream);
            }
            dataOutputStream.flush();
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                captureViewLayer(viewGroup.getChildAt(i), dataOutputStream, r8);
            }
        }
        if (view.mOverlay != null) {
            captureViewLayer(view.getOverlay().mOverlayViewGroup, dataOutputStream, r8);
        }
    }

    private static void outputDisplayList(View view, String str) throws IOException {
        View viewFindView = findView(view, str);
        viewFindView.getViewRootImpl().outputDisplayList(viewFindView);
    }

    public static void outputDisplayList(View view, View view2) {
        view.getViewRootImpl().outputDisplayList(view2);
    }

    private static class PictureCallbackHandler implements AutoCloseable, HardwareRenderer.PictureCapturedCallback, Runnable {
        private final Function<Picture, Boolean> mCallback;
        private final Executor mExecutor;
        private final ReentrantLock mLock;
        private final ArrayDeque<Picture> mQueue;
        private Thread mRenderThread;
        private final HardwareRenderer mRenderer;
        private boolean mStopListening;

        private PictureCallbackHandler(HardwareRenderer hardwareRenderer, Function<Picture, Boolean> function, Executor executor) {
            this.mLock = new ReentrantLock(false);
            this.mQueue = new ArrayDeque<>(3);
            this.mRenderer = hardwareRenderer;
            this.mCallback = function;
            this.mExecutor = executor;
            hardwareRenderer.setPictureCaptureCallback(this);
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            this.mLock.lock();
            this.mStopListening = true;
            this.mLock.unlock();
            this.mRenderer.setPictureCaptureCallback(null);
        }

        @Override // android.graphics.HardwareRenderer.PictureCapturedCallback
        public void onPictureCaptured(Picture picture) {
            this.mLock.lock();
            if (this.mStopListening) {
                this.mLock.unlock();
                this.mRenderer.setPictureCaptureCallback(null);
                return;
            }
            if (this.mRenderThread == null) {
                this.mRenderThread = Thread.currentThread();
            }
            Picture pictureRemoveLast = this.mQueue.size() == 3 ? this.mQueue.removeLast() : null;
            this.mQueue.add(picture);
            this.mLock.unlock();
            if (pictureRemoveLast == null) {
                this.mExecutor.execute(this);
            } else {
                pictureRemoveLast.close();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mLock.lock();
            Picture picturePoll = this.mQueue.poll();
            boolean z = this.mStopListening;
            this.mLock.unlock();
            if (Thread.currentThread() == this.mRenderThread) {
                close();
                throw new IllegalStateException("ViewDebug#startRenderingCommandsCapture must be given an executor that invokes asynchronously");
            }
            if (z) {
                picturePoll.close();
            } else {
                if (this.mCallback.apply(picturePoll).booleanValue()) {
                    return;
                }
                close();
            }
        }
    }

    @Deprecated
    public static AutoCloseable startRenderingCommandsCapture(View view, Executor executor, Function<Picture, Boolean> function) {
        View.AttachInfo attachInfo = view.mAttachInfo;
        if (attachInfo == null) {
            throw new IllegalArgumentException("Given view isn't attached");
        }
        if (attachInfo.mHandler.getLooper() != Looper.myLooper()) {
            throw new IllegalStateException("Called on the wrong thread. Must be called on the thread that owns the given View");
        }
        ThreadedRenderer threadedRenderer = attachInfo.mThreadedRenderer;
        if (threadedRenderer != null) {
            return new PictureCallbackHandler(threadedRenderer, function, executor);
        }
        return null;
    }

    private static class StreamingPictureCallbackHandler implements AutoCloseable, HardwareRenderer.PictureCapturedCallback, Runnable {
        private final ByteArrayOutputStream mByteStream;
        private final Callable<OutputStream> mCallback;
        private final Executor mExecutor;
        private final ReentrantLock mLock;
        private final ArrayDeque<byte[]> mQueue;
        private Thread mRenderThread;
        private final HardwareRenderer mRenderer;
        private boolean mStopListening;

        private StreamingPictureCallbackHandler(HardwareRenderer hardwareRenderer, Callable<OutputStream> callable, Executor executor) {
            this.mLock = new ReentrantLock(false);
            this.mQueue = new ArrayDeque<>(3);
            this.mByteStream = new ByteArrayOutputStream();
            this.mRenderer = hardwareRenderer;
            this.mCallback = callable;
            this.mExecutor = executor;
            hardwareRenderer.setPictureCaptureCallback(this);
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            this.mLock.lock();
            this.mStopListening = true;
            this.mLock.unlock();
            this.mRenderer.setPictureCaptureCallback(null);
        }

        @Override // android.graphics.HardwareRenderer.PictureCapturedCallback
        public void onPictureCaptured(Picture picture) {
            this.mLock.lock();
            if (this.mStopListening) {
                this.mLock.unlock();
                this.mRenderer.setPictureCaptureCallback(null);
                return;
            }
            if (this.mRenderThread == null) {
                this.mRenderThread = Thread.currentThread();
            }
            boolean z = true;
            if (this.mQueue.size() == 3) {
                this.mQueue.removeLast();
                z = false;
            }
            picture.writeToStream(this.mByteStream);
            this.mQueue.add(this.mByteStream.toByteArray());
            this.mByteStream.reset();
            this.mLock.unlock();
            if (z) {
                this.mExecutor.execute(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() throws Exception {
            this.mLock.lock();
            byte[] bArrPoll = this.mQueue.poll();
            boolean z = this.mStopListening;
            this.mLock.unlock();
            if (Thread.currentThread() == this.mRenderThread) {
                close();
                throw new IllegalStateException("ViewDebug#startRenderingCommandsCapture must be given an executor that invokes asynchronously");
            }
            if (z) {
                return;
            }
            OutputStream outputStreamCall = null;
            try {
                outputStreamCall = this.mCallback.call();
            } catch (Exception e) {
                Log.w("ViewDebug", "Aborting rendering commands capture because callback threw exception", e);
            }
            if (outputStreamCall != null) {
                try {
                    outputStreamCall.write(bArrPoll);
                    return;
                } catch (IOException e2) {
                    Log.w("ViewDebug", "Aborting rendering commands capture due to IOException writing to output stream", e2);
                    return;
                }
            }
            close();
        }
    }

    public static AutoCloseable startRenderingCommandsCapture(View view, Executor executor, Callable<OutputStream> callable) {
        View.AttachInfo attachInfo = view.mAttachInfo;
        if (attachInfo == null) {
            throw new IllegalArgumentException("Given view isn't attached");
        }
        if (attachInfo.mHandler.getLooper() != Looper.myLooper()) {
            throw new IllegalStateException("Called on the wrong thread. Must be called on the thread that owns the given View");
        }
        ThreadedRenderer threadedRenderer = attachInfo.mThreadedRenderer;
        if (threadedRenderer != null) {
            return new StreamingPictureCallbackHandler(threadedRenderer, callable, executor);
        }
        return null;
    }

    private static void capture(View view, OutputStream outputStream, String str) throws Throwable {
        capture(view, outputStream, findView(view, str));
    }

    public static void capture(View view, OutputStream outputStream, View view2) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        Throwable th;
        Bitmap bitmapPerformViewCapture = performViewCapture(view2, false);
        if (bitmapPerformViewCapture == null) {
            Log.w("View", "Failed to create capture bitmap!");
            bitmapPerformViewCapture = Bitmap.createBitmap(view.getResources().getDisplayMetrics(), 1, 1, Bitmap.Config.ARGB_8888);
        }
        try {
            bufferedOutputStream = new BufferedOutputStream(outputStream, 32768);
        } catch (Throwable th2) {
            bufferedOutputStream = null;
            th = th2;
        }
        try {
            bitmapPerformViewCapture.compress(Bitmap.CompressFormat.PNG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            bitmapPerformViewCapture.recycle();
        } catch (Throwable th3) {
            th = th3;
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            bitmapPerformViewCapture.recycle();
            throw th;
        }
    }

    private static Bitmap performViewCapture(final View view, final boolean z) {
        if (view == null) {
            return null;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Bitmap[] bitmapArr = new Bitmap[1];
        view.post(new Runnable() { // from class: android.view.-$$Lambda$ViewDebug$XH275olUuMuKB4MnnRDepR4XTJ0
            @Override // java.lang.Runnable
            public final void run() {
                ViewDebug.lambda$performViewCapture$4(view, bitmapArr, z, countDownLatch);
            }
        });
        try {
            countDownLatch.await(6000L, TimeUnit.MILLISECONDS);
            return bitmapArr[0];
        } catch (InterruptedException unused) {
            Log.w("View", "Could not complete the capture of the view " + view);
            Thread.currentThread().interrupt();
            return null;
        }
    }

    static /* synthetic */ void lambda$performViewCapture$4(View view, Bitmap[] bitmapArr, boolean z, CountDownLatch countDownLatch) {
        try {
            try {
                bitmapArr[0] = view.createSnapshot(view.isHardwareAccelerated() ? new HardwareCanvasProvider() : new SoftwareCanvasProvider(), z);
            } catch (OutOfMemoryError unused) {
                Log.w("View", "Out of memory for bitmap");
            }
        } finally {
            countDownLatch.countDown();
        }
    }

    @Deprecated
    public static void dump(View view, boolean z, boolean z2, OutputStream outputStream) throws Throwable {
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"), 32768);
                try {
                    View rootView = view.getRootView();
                    if (rootView instanceof ViewGroup) {
                        ViewGroup viewGroup = (ViewGroup) rootView;
                        dumpViewHierarchy(viewGroup.getContext(), viewGroup, bufferedWriter2, 0, z, z2);
                    }
                    bufferedWriter2.write("DONE.");
                    bufferedWriter2.newLine();
                    bufferedWriter2.close();
                } catch (Exception e) {
                    e = e;
                    bufferedWriter = bufferedWriter2;
                    Log.w("View", "Problem dumping the view:", e);
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static void dumpv2(final View view, ByteArrayOutputStream byteArrayOutputStream) throws InterruptedException {
        final ViewHierarchyEncoder viewHierarchyEncoder = new ViewHierarchyEncoder(byteArrayOutputStream);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        view.post(new Runnable() { // from class: android.view.ViewDebug.3
            @Override // java.lang.Runnable
            public void run() {
                viewHierarchyEncoder.addProperty("window:left", view.mAttachInfo.mWindowLeft);
                viewHierarchyEncoder.addProperty("window:top", view.mAttachInfo.mWindowTop);
                view.encode(viewHierarchyEncoder);
                countDownLatch.countDown();
            }
        });
        countDownLatch.await(2L, TimeUnit.SECONDS);
        viewHierarchyEncoder.endStream();
    }

    private static void dumpEncoded(View view, OutputStream outputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ViewHierarchyEncoder viewHierarchyEncoder = new ViewHierarchyEncoder(byteArrayOutputStream);
        viewHierarchyEncoder.setUserPropertiesEnabled(false);
        viewHierarchyEncoder.addProperty("window:left", view.mAttachInfo.mWindowLeft);
        viewHierarchyEncoder.addProperty("window:top", view.mAttachInfo.mWindowTop);
        view.encode(viewHierarchyEncoder);
        viewHierarchyEncoder.endStream();
        outputStream.write(byteArrayOutputStream.toByteArray());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    public static void dumpTheme(View view, OutputStream outputStream) throws Throwable {
        ?? r1 = 0;
        int length = 0;
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"), 32768);
                try {
                    String[] styleAttributesDump = getStyleAttributesDump(view.getContext().getResources(), view.getContext().getTheme());
                    if (styleAttributesDump != null) {
                        int i = 0;
                        while (true) {
                            length = styleAttributesDump.length;
                            if (i >= length) {
                                break;
                            }
                            if (styleAttributesDump[i] != null) {
                                bufferedWriter2.write(styleAttributesDump[i] + "\n");
                                bufferedWriter2.write(styleAttributesDump[i + 1] + "\n");
                            }
                            i += 2;
                        }
                    }
                    bufferedWriter2.write("DONE.");
                    bufferedWriter2.newLine();
                    bufferedWriter2.close();
                    r1 = length;
                } catch (Exception e) {
                    e = e;
                    bufferedWriter = bufferedWriter2;
                    Log.w("View", "Problem dumping View Theme:", e);
                    r1 = bufferedWriter;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                        r1 = bufferedWriter;
                    }
                } catch (Throwable th) {
                    th = th;
                    r1 = bufferedWriter2;
                    if (r1 != 0) {
                        r1.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static String[] getStyleAttributesDump(Resources resources, Resources.Theme theme) {
        TypedValue typedValue = new TypedValue();
        int[] allAttributes = theme.getAllAttributes();
        String[] strArr = new String[allAttributes.length * 2];
        int i = 0;
        for (int i2 : allAttributes) {
            try {
                strArr[i] = resources.getResourceName(i2);
                strArr[i + 1] = theme.resolveAttribute(i2, typedValue, true) ? typedValue.coerceToString().toString() : "null";
                i += 2;
                if (typedValue.type == 1) {
                    strArr[i - 1] = resources.getResourceName(typedValue.resourceId);
                }
            } catch (Resources.NotFoundException unused) {
            }
        }
        return strArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static View findView(ViewGroup viewGroup, String str, int i) {
        View viewFindHierarchyView;
        View viewFindView;
        if (isRequestedView(viewGroup, str, i)) {
            return viewGroup;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof ViewGroup) {
                View viewFindView2 = findView((ViewGroup) childAt, str, i);
                if (viewFindView2 != null) {
                    return viewFindView2;
                }
            } else if (isRequestedView(childAt, str, i)) {
                return childAt;
            }
            if (childAt.mOverlay != null && (viewFindView = findView(childAt.mOverlay.mOverlayViewGroup, str, i)) != null) {
                return viewFindView;
            }
            if ((childAt instanceof HierarchyHandler) && (viewFindHierarchyView = ((HierarchyHandler) childAt).findHierarchyView(str, i)) != null) {
                return viewFindHierarchyView;
            }
        }
        return null;
    }

    private static boolean isRequestedView(View view, String str, int i) {
        if (view.hashCode() != i) {
            return false;
        }
        String name = view.getClass().getName();
        if (str.equals("ViewOverlay")) {
            return name.equals("android.view.ViewOverlay$OverlayViewGroup");
        }
        return str.equals(name);
    }

    private static void dumpViewHierarchy(final Context context, final ViewGroup viewGroup, final BufferedWriter bufferedWriter, final int i, final boolean z, final boolean z2) {
        cacheExportedProperties(viewGroup.getClass());
        if (!z) {
            cacheExportedPropertiesForChildren(viewGroup);
        }
        Handler handler = viewGroup.getHandler();
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        if (handler.getLooper() == Looper.myLooper()) {
            dumpViewHierarchyOnUIThread(context, viewGroup, bufferedWriter, i, z, z2);
            return;
        }
        FutureTask futureTask = new FutureTask(new Runnable() { // from class: android.view.-$$Lambda$ViewDebug$RUjXp6XJu8_6D9eegdAyJ3GlcKQ
            @Override // java.lang.Runnable
            public final void run() {
                ViewDebug.dumpViewHierarchyOnUIThread(context, viewGroup, bufferedWriter, i, z, z2);
            }
        }, null);
        Message messageObtain = Message.obtain(handler, futureTask);
        messageObtain.setAsynchronous(true);
        handler.sendMessage(messageObtain);
        while (true) {
            try {
                futureTask.get(6000L, TimeUnit.MILLISECONDS);
                return;
            } catch (InterruptedException unused) {
            } catch (ExecutionException | TimeoutException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void cacheExportedPropertiesForChildren(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            cacheExportedProperties(childAt.getClass());
            if (childAt instanceof ViewGroup) {
                cacheExportedPropertiesForChildren((ViewGroup) childAt);
            }
        }
    }

    private static void cacheExportedProperties(Class<?> cls) {
        HashMap<Class<?>, PropertyInfo<ExportedProperty, ?>[]> map = sExportProperties;
        if (map == null || !map.containsKey(cls)) {
            do {
                for (PropertyInfo<ExportedProperty, ?> propertyInfo : getExportedProperties(cls)) {
                    if (!propertyInfo.returnType.isPrimitive() && ((ExportedProperty) propertyInfo.property).deepExport()) {
                        cacheExportedProperties(propertyInfo.returnType);
                    }
                }
                cls = cls.getSuperclass();
            } while (cls != Object.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static void dumpViewHierarchyOnUIThread(Context context, ViewGroup viewGroup, BufferedWriter bufferedWriter, int i, boolean z, boolean z2) {
        if (dumpView(context, viewGroup, bufferedWriter, i, z2) && !z) {
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt instanceof ViewGroup) {
                    dumpViewHierarchyOnUIThread(context, (ViewGroup) childAt, bufferedWriter, i + 1, z, z2);
                } else {
                    dumpView(context, childAt, bufferedWriter, i + 1, z2);
                }
                if (childAt.mOverlay != null) {
                    dumpViewHierarchyOnUIThread(context, childAt.getOverlay().mOverlayViewGroup, bufferedWriter, i + 2, z, z2);
                }
            }
            if (viewGroup instanceof HierarchyHandler) {
                ((HierarchyHandler) viewGroup).dumpViewHierarchyWithProperties(bufferedWriter, i + 1);
            }
        }
    }

    private static boolean dumpView(Context context, View view, BufferedWriter bufferedWriter, int i, boolean z) {
        for (int i2 = 0; i2 < i; i2++) {
            try {
                bufferedWriter.write(32);
            } catch (IOException unused) {
                Log.w("View", "Error while dumping hierarchy tree");
                return false;
            }
        }
        String name = view.getClass().getName();
        if (name.equals("android.view.ViewOverlay$OverlayViewGroup")) {
            name = "ViewOverlay";
        }
        bufferedWriter.write(name);
        bufferedWriter.write(64);
        bufferedWriter.write(Integer.toHexString(view.hashCode()));
        bufferedWriter.write(32);
        if (z) {
            dumpViewProperties(context, view, bufferedWriter);
        }
        bufferedWriter.newLine();
        return true;
    }

    private static <T extends Annotation> PropertyInfo<T, ?>[] convertToPropertyInfos(Method[] methodArr, Field[] fieldArr, final Class<T> cls) {
        return (PropertyInfo[]) Stream.of((Object[]) new Stream[]{Arrays.stream(methodArr).map(new Function() { // from class: android.view.-$$Lambda$ViewDebug$w-Jul_CkzUncT7cttbWvgfiWzeg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ViewDebug.PropertyInfo.forMethod((Method) obj, cls);
            }
        }), Arrays.stream(fieldArr).map(new Function() { // from class: android.view.-$$Lambda$ViewDebug$3CCkhh97ahFOTOn2sV5KpOECimg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ViewDebug.PropertyInfo.forField((Field) obj, cls);
            }
        })}).flatMap(Function.identity()).filter(new Predicate() { // from class: android.view.-$$Lambda$ViewDebug$gXOgOWVRw9-qv_029mpjZrWoI9k
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ViewDebug.lambda$convertToPropertyInfos$8(obj);
            }
        }).toArray(new IntFunction() { // from class: android.view.-$$Lambda$ViewDebug$R79p4xn2_iW8zCHqadNpb1IR7KM
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return ViewDebug.lambda$convertToPropertyInfos$9(i);
            }
        });
    }

    static /* synthetic */ PropertyInfo[] lambda$convertToPropertyInfos$9(int i) {
        return new PropertyInfo[i];
    }

    private static PropertyInfo<ExportedProperty, ?>[] getExportedProperties(Class<?> cls) {
        if (sExportProperties == null) {
            sExportProperties = new HashMap<>();
        }
        HashMap<Class<?>, PropertyInfo<ExportedProperty, ?>[]> map = sExportProperties;
        PropertyInfo<ExportedProperty, ?>[] propertyInfoArr = map.get(cls);
        if (propertyInfoArr != null) {
            return propertyInfoArr;
        }
        PropertyInfo<ExportedProperty, ?>[] propertyInfoArrConvertToPropertyInfos = convertToPropertyInfos(cls.getDeclaredMethodsUnchecked(false), cls.getDeclaredFieldsUnchecked(false), ExportedProperty.class);
        map.put(cls, propertyInfoArrConvertToPropertyInfos);
        return propertyInfoArrConvertToPropertyInfos;
    }

    private static void dumpViewProperties(Context context, Object obj, BufferedWriter bufferedWriter) throws IOException {
        dumpViewProperties(context, obj, bufferedWriter, "");
    }

    private static void dumpViewProperties(Context context, Object obj, BufferedWriter bufferedWriter, String str) throws IOException {
        if (obj == null) {
            bufferedWriter.write(str + "=4,null ");
            return;
        }
        Class<?> superclass = obj.getClass();
        do {
            writeExportedProperties(context, obj, bufferedWriter, superclass, str);
            superclass = superclass.getSuperclass();
        } while (superclass != Object.class);
    }

    private static String formatIntToHexString(int i) {
        return "0x" + Integer.toHexString(i).toUpperCase();
    }

    private static void writeExportedProperties(Context context, Object obj, BufferedWriter bufferedWriter, Class<?> cls, String str) throws IOException {
        Object objInvoke;
        String str2;
        boolean z;
        for (PropertyInfo<ExportedProperty, ?> propertyInfo : getExportedProperties(cls)) {
            try {
                objInvoke = propertyInfo.invoke(obj);
                str2 = ((ExportedProperty) propertyInfo.property).category().length() != 0 ? ((ExportedProperty) propertyInfo.property).category() + SettingsStringUtil.DELIMITER : "";
            } catch (Exception unused) {
            }
            if (propertyInfo.returnType == Integer.TYPE || propertyInfo.returnType == Byte.TYPE) {
                if (((ExportedProperty) propertyInfo.property).resolveId() && context != null) {
                    objInvoke = resolveId(context, ((Integer) objInvoke).intValue());
                } else if (((ExportedProperty) propertyInfo.property).formatToHexString()) {
                    if (propertyInfo.returnType == Integer.TYPE) {
                        objInvoke = formatIntToHexString(((Integer) objInvoke).intValue());
                    } else if (propertyInfo.returnType == Byte.TYPE) {
                        objInvoke = "0x" + HexEncoding.encodeToString(((Byte) objInvoke).byteValue(), true);
                    }
                } else {
                    FlagToString[] flagToStringArrFlagMapping = ((ExportedProperty) propertyInfo.property).flagMapping();
                    if (flagToStringArrFlagMapping.length > 0) {
                        exportUnrolledFlags(bufferedWriter, flagToStringArrFlagMapping, ((Integer) objInvoke).intValue(), str2 + str + propertyInfo.name + '_');
                    }
                    IntToString[] intToStringArrMapping = ((ExportedProperty) propertyInfo.property).mapping();
                    if (intToStringArrMapping.length > 0) {
                        int iIntValue = ((Integer) objInvoke).intValue();
                        int length = intToStringArrMapping.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                z = false;
                                break;
                            }
                            IntToString intToString = intToStringArrMapping[i];
                            if (intToString.from() == iIntValue) {
                                objInvoke = intToString.to();
                                z = true;
                                break;
                            }
                            i++;
                        }
                        if (!z) {
                            objInvoke = Integer.valueOf(iIntValue);
                        }
                    }
                }
            } else {
                if (propertyInfo.returnType == int[].class) {
                    exportUnrolledArray(context, bufferedWriter, (ExportedProperty) propertyInfo.property, (int[]) objInvoke, str2 + str + propertyInfo.name + '_', propertyInfo.entrySuffix);
                } else if (propertyInfo.returnType == String[].class) {
                    String[] strArr = (String[]) objInvoke;
                    if (((ExportedProperty) propertyInfo.property).hasAdjacentMapping() && strArr != null) {
                        for (int i2 = 0; i2 < strArr.length; i2 += 2) {
                            if (strArr[i2] != null) {
                                int i3 = i2 + 1;
                                writeEntry(bufferedWriter, str2 + str, strArr[i2], propertyInfo.entrySuffix, strArr[i3] == null ? "null" : strArr[i3]);
                            }
                        }
                    }
                } else if (!propertyInfo.returnType.isPrimitive() && ((ExportedProperty) propertyInfo.property).deepExport()) {
                    dumpViewProperties(context, objInvoke, bufferedWriter, str + ((ExportedProperty) propertyInfo.property).prefix());
                }
            }
            writeEntry(bufferedWriter, str2 + str, propertyInfo.name, propertyInfo.entrySuffix, objInvoke);
        }
    }

    private static void writeEntry(BufferedWriter bufferedWriter, String str, String str2, String str3, Object obj) throws IOException {
        bufferedWriter.write(str);
        bufferedWriter.write(str2);
        bufferedWriter.write(str3);
        bufferedWriter.write("=");
        writeValue(bufferedWriter, obj);
        bufferedWriter.write(32);
    }

    private static void exportUnrolledFlags(BufferedWriter bufferedWriter, FlagToString[] flagToStringArr, int i, String str) throws IOException {
        for (FlagToString flagToString : flagToStringArr) {
            boolean zOutputIf = flagToString.outputIf();
            int iMask = flagToString.mask() & i;
            boolean z = iMask == flagToString.equals();
            if ((z && zOutputIf) || (!z && !zOutputIf)) {
                writeEntry(bufferedWriter, str, flagToString.name(), "", formatIntToHexString(iMask));
            }
        }
    }

    public static String intToString(Class<?> cls, String str, int i) {
        IntToString[] mapping = getMapping(cls, str);
        if (mapping == null) {
            return Integer.toString(i);
        }
        for (IntToString intToString : mapping) {
            if (intToString.from() == i) {
                return intToString.to();
            }
        }
        return Integer.toString(i);
    }

    public static String flagsToString(Class<?> cls, String str, int i) {
        FlagToString[] flagMapping = getFlagMapping(cls, str);
        if (flagMapping == null) {
            return Integer.toHexString(i);
        }
        StringBuilder sb = new StringBuilder();
        int length = flagMapping.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            FlagToString flagToString = flagMapping[i2];
            boolean zOutputIf = flagToString.outputIf();
            if (((flagToString.mask() & i) == flagToString.equals()) && zOutputIf) {
                sb.append(flagToString.name()).append(' ');
            }
            i2++;
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private static FlagToString[] getFlagMapping(Class<?> cls, String str) {
        try {
            return ((ExportedProperty) cls.getDeclaredField(str).getAnnotation(ExportedProperty.class)).flagMapping();
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    private static IntToString[] getMapping(Class<?> cls, String str) {
        try {
            return ((ExportedProperty) cls.getDeclaredField(str).getAnnotation(ExportedProperty.class)).mapping();
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    private static void exportUnrolledArray(Context context, BufferedWriter bufferedWriter, ExportedProperty exportedProperty, int[] iArr, String str, String str2) throws IOException {
        IntToString[] intToStringArrIndexMapping = exportedProperty.indexMapping();
        boolean z = intToStringArrIndexMapping.length > 0;
        IntToString[] intToStringArrMapping = exportedProperty.mapping();
        boolean z2 = intToStringArrMapping.length > 0;
        boolean z3 = exportedProperty.resolveId() && context != null;
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            String strValueOf = null;
            int i2 = iArr[i];
            String strValueOf2 = String.valueOf(i);
            if (z) {
                int length2 = intToStringArrIndexMapping.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        break;
                    }
                    IntToString intToString = intToStringArrIndexMapping[i3];
                    if (intToString.from() == i) {
                        strValueOf2 = intToString.to();
                        break;
                    }
                    i3++;
                }
            }
            if (z2) {
                int length3 = intToStringArrMapping.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length3) {
                        break;
                    }
                    IntToString intToString2 = intToStringArrMapping[i4];
                    if (intToString2.from() == i2) {
                        strValueOf = intToString2.to();
                        break;
                    }
                    i4++;
                }
            }
            if (!z3) {
                strValueOf = String.valueOf(i2);
            } else if (strValueOf == null) {
                strValueOf = (String) resolveId(context, i2);
            }
            writeEntry(bufferedWriter, str, strValueOf2, str2, strValueOf);
        }
    }

    static Object resolveId(Context context, int i) {
        Resources resources = context.getResources();
        if (i < 0) {
            return "NO_ID";
        }
        try {
            return resources.getResourceTypeName(i) + '/' + resources.getResourceEntryName(i);
        } catch (Resources.NotFoundException unused) {
            return "id/" + formatIntToHexString(i);
        }
    }

    private static void writeValue(BufferedWriter bufferedWriter, Object obj) throws IOException {
        if (obj != null) {
            try {
                String strReplace = obj.toString().replace("\n", "\\n");
                bufferedWriter.write(String.valueOf(strReplace.length()));
                bufferedWriter.write(SmsManager.REGEX_PREFIX_DELIMITER);
                bufferedWriter.write(strReplace);
                return;
            } catch (Throwable th) {
                bufferedWriter.write(String.valueOf(11));
                bufferedWriter.write(SmsManager.REGEX_PREFIX_DELIMITER);
                bufferedWriter.write("[EXCEPTION]");
                throw th;
            }
        }
        bufferedWriter.write("4,null");
    }

    private static PropertyInfo<CapturedViewProperty, ?>[] getCapturedViewProperties(Class<?> cls) {
        if (sCapturedViewProperties == null) {
            sCapturedViewProperties = new HashMap<>();
        }
        HashMap<Class<?>, PropertyInfo<CapturedViewProperty, ?>[]> map = sCapturedViewProperties;
        PropertyInfo<CapturedViewProperty, ?>[] propertyInfoArr = map.get(cls);
        if (propertyInfoArr != null) {
            return propertyInfoArr;
        }
        PropertyInfo<CapturedViewProperty, ?>[] propertyInfoArrConvertToPropertyInfos = convertToPropertyInfos(cls.getMethods(), cls.getFields(), CapturedViewProperty.class);
        map.put(cls, propertyInfoArrConvertToPropertyInfos);
        return propertyInfoArrConvertToPropertyInfos;
    }

    private static String exportCapturedViewProperties(Object obj, Class<?> cls, String str) {
        if (obj == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for (PropertyInfo<CapturedViewProperty, ?> propertyInfo : getCapturedViewProperties(cls)) {
            try {
                Object objInvoke = propertyInfo.invoke(obj);
                if (((CapturedViewProperty) propertyInfo.property).retrieveReturn()) {
                    sb.append(exportCapturedViewProperties(objInvoke, propertyInfo.returnType, propertyInfo.name + "#"));
                } else {
                    sb.append(str).append(propertyInfo.name).append(propertyInfo.entrySuffix).append("=");
                    if (objInvoke != null) {
                        sb.append(objInvoke.toString().replace("\n", "\\n"));
                    } else {
                        sb.append("null");
                    }
                    sb.append(propertyInfo.valueSuffix).append(" ");
                }
            } catch (Exception unused) {
            }
        }
        return sb.toString();
    }

    public static void dumpCapturedView(String str, Object obj) {
        Class<?> cls = obj.getClass();
        Log.d(str, (cls.getName() + ": ") + exportCapturedViewProperties(obj, cls, ""));
    }

    public static Object invokeViewMethod(final View view, final Method method, final Object[] objArr) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        view.post(new Runnable() { // from class: android.view.ViewDebug.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    atomicReference.set(method.invoke(view, objArr));
                } catch (InvocationTargetException e) {
                    atomicReference2.set(e.getCause());
                } catch (Exception e2) {
                    atomicReference2.set(e2);
                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            if (atomicReference2.get() != null) {
                throw new RuntimeException((Throwable) atomicReference2.get());
            }
            return atomicReference.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setLayoutParameter(final View view, String str, int i) throws IllegalAccessException, NoSuchFieldException {
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Field field = layoutParams.getClass().getField(str);
        if (field.getType() != Integer.TYPE) {
            throw new RuntimeException("Only integer layout parameters can be set. Field " + str + " is of type " + field.getType().getSimpleName());
        }
        field.set(layoutParams, Integer.valueOf(i));
        view.post(new Runnable() { // from class: android.view.ViewDebug.5
            @Override // java.lang.Runnable
            public void run() {
                view.setLayoutParams(layoutParams);
            }
        });
    }

    public static class SoftwareCanvasProvider implements CanvasProvider {
        private Bitmap mBitmap;
        private Canvas mCanvas;
        private boolean mEnabledHwBitmapsInSwMode;

        @Override // android.view.ViewDebug.CanvasProvider
        public Canvas getCanvas(View view, int i, int i2) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getResources().getDisplayMetrics(), i, i2, Bitmap.Config.ARGB_8888);
            this.mBitmap = bitmapCreateBitmap;
            if (bitmapCreateBitmap == null) {
                throw new OutOfMemoryError();
            }
            bitmapCreateBitmap.setDensity(view.getResources().getDisplayMetrics().densityDpi);
            if (view.mAttachInfo != null) {
                this.mCanvas = view.mAttachInfo.mCanvas;
            }
            if (this.mCanvas == null) {
                this.mCanvas = new Canvas();
            }
            this.mEnabledHwBitmapsInSwMode = this.mCanvas.isHwBitmapsInSwModeEnabled();
            this.mCanvas.setBitmap(this.mBitmap);
            return this.mCanvas;
        }

        @Override // android.view.ViewDebug.CanvasProvider
        public Bitmap createBitmap() {
            this.mCanvas.setBitmap(null);
            this.mCanvas.setHwBitmapsInSwModeEnabled(this.mEnabledHwBitmapsInSwMode);
            return this.mBitmap;
        }
    }

    public static class HardwareCanvasProvider implements CanvasProvider {
        private Picture mPicture;

        @Override // android.view.ViewDebug.CanvasProvider
        public Canvas getCanvas(View view, int i, int i2) {
            Picture picture = new Picture();
            this.mPicture = picture;
            return picture.beginRecording(i, i2);
        }

        @Override // android.view.ViewDebug.CanvasProvider
        public Bitmap createBitmap() {
            this.mPicture.endRecording();
            return Bitmap.createBitmap(this.mPicture);
        }
    }
}
