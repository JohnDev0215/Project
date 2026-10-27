package com.android.launcher3.graphics;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.R;
import com.android.launcher3.util.LooperExecutor;
import com.android.launcher3.util.UiThreadHelper;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
public class GridOptionsProvider extends ContentProvider {
    public static final ContentProvider.PipeDataWriter<Future<Bitmap>> BITMAP_WRITER = new ContentProvider.PipeDataWriter<Future<Bitmap>>() { // from class: com.android.launcher3.graphics.GridOptionsProvider.1
        @Override // android.content.ContentProvider.PipeDataWriter
        public void writeDataToPipe(ParcelFileDescriptor output, Uri uri, String s, Bundle bundle, Future<Bitmap> bitmap) {
            try {
                ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(output);
                try {
                    bitmap.get().compress(Bitmap.CompressFormat.PNG, 100, autoCloseOutputStream);
                    autoCloseOutputStream.close();
                } finally {
                }
            } catch (Exception e) {
                Log.w(GridOptionsProvider.TAG, "fail to write to pipe", e);
            }
        }
    };
    private static final String KEY_COLS = "cols";
    private static final String KEY_DEFAULT_GRID = "/default_grid";
    private static final String KEY_IS_DEFAULT = "is_default";
    private static final String KEY_LIST_OPTIONS = "/list_options";
    private static final String KEY_NAME = "name";
    private static final String KEY_PREVIEW = "preview";
    private static final String KEY_PREVIEW_COUNT = "preview_count";
    private static final String KEY_ROWS = "rows";
    private static final String MIME_TYPE_PNG = "image/png";
    private static final String TAG = "GridOptionsProvider";

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues initialValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (!KEY_LIST_OPTIONS.equals(uri.getPath())) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"name", KEY_ROWS, KEY_COLS, KEY_PREVIEW_COUNT, KEY_IS_DEFAULT});
        InvariantDeviceProfile invariantDeviceProfileLambda$get$0$MainThreadInitializedObject = InvariantDeviceProfile.INSTANCE.lambda$get$0$MainThreadInitializedObject(getContext());
        for (InvariantDeviceProfile.GridOption gridOption : parseAllGridOptions()) {
            boolean z = true;
            MatrixCursor.RowBuilder rowBuilderAdd = matrixCursor.newRow().add("name", gridOption.name).add(KEY_ROWS, Integer.valueOf(gridOption.numRows)).add(KEY_COLS, Integer.valueOf(gridOption.numColumns)).add(KEY_PREVIEW_COUNT, (Object) 1);
            if (invariantDeviceProfileLambda$get$0$MainThreadInitializedObject.numColumns != gridOption.numColumns || invariantDeviceProfileLambda$get$0$MainThreadInitializedObject.numRows != gridOption.numRows) {
                z = false;
            }
            rowBuilderAdd.add(KEY_IS_DEFAULT, Boolean.valueOf(z));
        }
        return matrixCursor;
    }

    private List<InvariantDeviceProfile.GridOption> parseAllGridOptions() {
        ArrayList arrayList = new ArrayList();
        try {
            XmlResourceParser xml = getContext().getResources().getXml(R.xml.device_profiles);
            try {
                int depth = xml.getDepth();
                while (true) {
                    int next = xml.next();
                    if ((next == 3 && xml.getDepth() <= depth) || next == 1) {
                        break;
                    }
                    if (next == 2 && InvariantDeviceProfile.GridOption.TAG_NAME.equals(xml.getName())) {
                        arrayList.add(new InvariantDeviceProfile.GridOption(getContext(), Xml.asAttributeSet(xml)));
                    }
                }
                if (xml != null) {
                    xml.close();
                }
                return arrayList;
            } catch (Throwable th) {
                if (xml != null) {
                    try {
                        xml.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException | XmlPullParserException e) {
            Log.e(TAG, "Error parsing device profile", e);
            return Collections.emptyList();
        }
    }

    @Override // android.content.ContentProvider, android.content.ContentInterface
    public String getType(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        return (pathSegments.size() <= 0 || !"preview".equals(pathSegments.get(0))) ? "vnd.android.cursor.dir/launcher_grid" : "image/png";
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (!KEY_DEFAULT_GRID.equals(uri.getPath())) {
            return 0;
        }
        String asString = values.getAsString("name");
        InvariantDeviceProfile.GridOption gridOption = null;
        Iterator<InvariantDeviceProfile.GridOption> it = parseAllGridOptions().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            InvariantDeviceProfile.GridOption next = it.next();
            if (next.name.equals(asString)) {
                gridOption = next;
                break;
            }
        }
        if (gridOption == null) {
            return 0;
        }
        InvariantDeviceProfile.INSTANCE.lambda$get$0$MainThreadInitializedObject(getContext()).setCurrentGrid(getContext(), asString);
        return 1;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() < 2 || !"preview".equals(pathSegments.get(0))) {
            throw new FileNotFoundException("Invalid preview url");
        }
        String str = pathSegments.get(1);
        if (TextUtils.isEmpty(str)) {
            throw new FileNotFoundException("Invalid preview url");
        }
        try {
            InvariantDeviceProfile invariantDeviceProfile = new InvariantDeviceProfile(getContext(), str);
            try {
                return openPipeHelper(uri, "image/png", null, new LooperExecutor(UiThreadHelper.getBackgroundLooper()).submit(new LauncherPreviewRenderer(getContext(), invariantDeviceProfile)), BITMAP_WRITER);
            } catch (Exception e) {
                throw new FileNotFoundException(e.getMessage());
            }
        } catch (Exception e2) {
            throw new FileNotFoundException(e2.getMessage());
        }
    }
}
