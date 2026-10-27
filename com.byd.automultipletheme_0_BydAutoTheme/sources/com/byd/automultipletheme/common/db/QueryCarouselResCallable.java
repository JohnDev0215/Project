package com.byd.automultipletheme.common.db;

import android.database.Cursor;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.bean.CarouselPicture;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import kotlin.Metadata;

/* JADX INFO: compiled from: DBUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u00040\u0001B\u0005¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004H\u0016¨\u0006\u0007"}, d2 = {"Lcom/byd/automultipletheme/common/db/QueryCarouselResCallable;", "Ljava/util/concurrent/Callable;", "Ljava/util/ArrayList;", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "Lkotlin/collections/ArrayList;", "()V", NotificationCompat.CATEGORY_CALL, "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class QueryCarouselResCallable implements Callable<ArrayList<CarouselPicture>> {
    @Override // java.util.concurrent.Callable
    public ArrayList<CarouselPicture> call() {
        Cursor cursorQuery = Utils.getApp().getContentResolver().query(Uri.parse(DB.CAROUSEL_RES_URI), null, null, null, null);
        ArrayList<CarouselPicture> arrayList = new ArrayList<>();
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                int columnIndex = cursorQuery.getColumnIndex(DB.CAROUSEL_RES_PATH0);
                int columnIndex2 = cursorQuery.getColumnIndex(DB.CAROUSEL_RES_PATH1);
                int columnIndex3 = cursorQuery.getColumnIndex(DB.CAROUSEL_RES_PATH2);
                int columnIndex4 = cursorQuery.getColumnIndex(DB.CAROUSEL_RES_PATH3);
                String string = cursorQuery.getString(columnIndex);
                String string2 = cursorQuery.getString(columnIndex2);
                String string3 = cursorQuery.getString(columnIndex3);
                String string4 = cursorQuery.getString(columnIndex4);
                CarouselPicture carouselPicture = new CarouselPicture(null, null, null, null, false, null, 63, null);
                carouselPicture.setPortLight(string);
                carouselPicture.setPortNight(string2);
                carouselPicture.setLandLight(string3);
                carouselPicture.setLandNight(string4);
                arrayList.add(carouselPicture);
            }
            cursorQuery.close();
        }
        return arrayList;
    }
}
