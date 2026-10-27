package org.chromium.ui.resources.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.chromium.base.TraceEvent;
import org.chromium.ui.resources.Resource;

/* JADX INFO: loaded from: classes4.dex */
public class CrushedSpriteResource implements Resource {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Rect EMPTY_RECT = new Rect();
    private Bitmap mBitmap;
    private final Rect mBitmapSize;
    private int[][] mRectangles;
    private float mScaledSpriteHeight;
    private float mScaledSpriteWidth;
    private int mUnscaledSpriteHeight;
    private int mUnscaledSpriteWidth;

    public CrushedSpriteResource(int i, int i2, Resources resources) {
        Rect rect = new Rect();
        this.mBitmapSize = rect;
        Bitmap bitmapLoadBitmap = loadBitmap(i, resources);
        this.mBitmap = bitmapLoadBitmap;
        if (bitmapLoadBitmap != null) {
            rect.set(0, 0, bitmapLoadBitmap.getWidth(), this.mBitmap.getHeight());
            try {
                TraceEvent.begin("CrushedSpriteResource.parseMetadata");
                parseMetadata(i2, this.mBitmap.getDensity(), resources);
                TraceEvent.end("CrushedSpriteResource.parseMetadata");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap loadBitmap(int i, Resources resources) {
        TraceEvent.begin("CrushedSpriteResource.loadBitmap");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(resources, i, options);
        TraceEvent.end("CrushedSpriteResource.loadBitmap");
        return bitmapDecodeResource;
    }

    @Override // org.chromium.ui.resources.Resource
    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    @Override // org.chromium.ui.resources.Resource
    public Rect getBitmapSize() {
        return this.mBitmapSize;
    }

    @Override // org.chromium.ui.resources.Resource
    public Rect getPadding() {
        return EMPTY_RECT;
    }

    @Override // org.chromium.ui.resources.Resource
    public Rect getAperture() {
        return EMPTY_RECT;
    }

    public float getScaledSpriteWidth() {
        return this.mScaledSpriteWidth;
    }

    public float getScaledSpriteHeight() {
        return this.mScaledSpriteHeight;
    }

    public int getUnscaledSpriteWidth() {
        return this.mUnscaledSpriteWidth;
    }

    public int getUnscaledSpriteHeight() {
        return this.mUnscaledSpriteHeight;
    }

    public int[][] getFrameRectangles() {
        return (int[][]) this.mRectangles.clone();
    }

    void parseMetadata(int i, int i2, Resources resources) throws IOException {
        InputStream inputStreamOpenRawResource = resources.openRawResource(i);
        JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStreamOpenRawResource));
        try {
            jsonReader.beginObject();
            jsonReader.nextName();
            jsonReader.nextString();
            float f = resources.getDisplayMetrics().density;
            jsonReader.nextName();
            this.mScaledSpriteWidth = jsonReader.nextInt() * f;
            jsonReader.nextName();
            this.mScaledSpriteHeight = jsonReader.nextInt() * f;
            jsonReader.nextName();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                jsonReader.beginObject();
                boolean metadataForDensity = parseMetadataForDensity(jsonReader, i2);
                jsonReader.endObject();
                if (metadataForDensity) {
                    break;
                }
            }
        } finally {
            jsonReader.close();
            inputStreamOpenRawResource.close();
        }
    }

    private boolean parseMetadataForDensity(JsonReader jsonReader, int i) throws IOException {
        jsonReader.nextName();
        if (jsonReader.nextInt() != i) {
            jsonReader.skipValue();
            jsonReader.skipValue();
            jsonReader.skipValue();
            jsonReader.skipValue();
            jsonReader.skipValue();
            jsonReader.skipValue();
            return false;
        }
        jsonReader.nextName();
        this.mUnscaledSpriteWidth = jsonReader.nextInt();
        jsonReader.nextName();
        this.mUnscaledSpriteHeight = jsonReader.nextInt();
        jsonReader.nextName();
        parseFrameRectangles(jsonReader);
        return true;
    }

    private void parseFrameRectangles(JsonReader jsonReader) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        int i = 0;
        while (jsonReader.hasNext()) {
            ArrayList arrayList2 = new ArrayList();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                arrayList2.add(Integer.valueOf(jsonReader.nextInt()));
            }
            jsonReader.endArray();
            arrayList.add(arrayList2);
            i++;
        }
        jsonReader.endArray();
        this.mRectangles = new int[i][];
        for (int i2 = 0; i2 < i; i2++) {
            ArrayList arrayList3 = (ArrayList) arrayList.get(i2);
            int[] iArr = new int[arrayList3.size()];
            for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                iArr[i3] = ((Integer) arrayList3.get(i3)).intValue();
            }
            this.mRectangles[i2] = iArr;
        }
    }
}
