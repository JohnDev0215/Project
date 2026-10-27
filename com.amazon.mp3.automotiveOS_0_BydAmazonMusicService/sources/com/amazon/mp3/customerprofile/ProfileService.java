package com.amazon.mp3.customerprofile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.CookieManager;
import android.webkit.MimeTypeMap;
import com.amazon.mp3.customerprofile.CustomerProfileService;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.stability.StabilityManager;
import com.amazon.mp3.store.html5.map.MAPCookiesCallback;
import com.amazon.mp3.store.html5.map.MAPCookiesUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.platform.graphql.generated.SocialProfileImageUploadFormQuery;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.amazonaws.http.HttpHeader;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import com.visualon.OSMPUtils.voOSTypePrivate;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;

/* JADX INFO: loaded from: classes3.dex */
public final class ProfileService {
    private static final String TAG = "ProfileService";
    private final Context context;
    private final boolean nameUpdated;
    private final boolean shareListeningHistoryUpdated;
    private final boolean statusUpdated;

    public ProfileService(final Context context, final boolean nameUpdated, final boolean statusUpdated, final boolean shareListeningHistoryUpdated) {
        this.context = context;
        this.nameUpdated = nameUpdated;
        this.statusUpdated = statusUpdated;
        this.shareListeningHistoryUpdated = shareListeningHistoryUpdated;
    }

    public void updateAvatar(final Uri avatarUri, final CustomerProfileService.Callback callback, final int imageRotation) {
        Log.debug(TAG, "Initiating Avatar Service profile image update: " + avatarUri.getPath());
        MAPCookiesUtil.fetchCookies(new AuthorizationCallback(avatarUri, callback, imageRotation), true);
    }

    public void updateAvatar(final Uri avatarUri, final SocialProfileImageUploadFormQuery.ProfileImageUploadForm profileImageForm, final CustomerProfileService.Callback callback, final int imageRotation) {
        Log.debug(TAG, "Initiating Firefly profile image update: " + avatarUri.getPath());
        MAPCookiesUtil.fetchCookies(new AuthorizationCallback(avatarUri, profileImageForm, callback, imageRotation), true);
    }

    private class AuthorizationCallback extends MAPCookiesCallback {
        private CustomerProfileService.Callback callback;
        private int imageRotation;
        private Uri imageUri;
        private SocialProfileImageUploadFormQuery.ProfileImageUploadForm profileImageForm;

        AuthorizationCallback(final Uri imageUri, final CustomerProfileService.Callback callback, final int imageRotation) {
            super(ProfileService.this.context);
            this.profileImageForm = null;
            this.imageUri = imageUri;
            this.callback = callback;
            this.imageRotation = imageRotation;
        }

        AuthorizationCallback(final Uri imageUri, final SocialProfileImageUploadFormQuery.ProfileImageUploadForm profileImageForm, final CustomerProfileService.Callback callback, final int imageRotation) {
            super(ProfileService.this.context);
            this.imageUri = imageUri;
            this.callback = callback;
            this.imageRotation = imageRotation;
            this.profileImageForm = profileImageForm;
        }

        @Override // com.amazon.mp3.store.html5.map.MAPCookiesCallback, com.amazon.identity.auth.device.api.Callback
        public void onError(final Bundle errorBundle) {
            Log.error(ProfileService.TAG, "Error encountered while retrieving authorization from MAP");
            super.onError(errorBundle);
            this.callback.onError(ProfileService.this.generateUpdateException(voOSTypePrivate.VOOSMP_SRC_ADAPTIVE_STREAMING_INFO_EVENT_INTERNAL_SEEK_POS));
        }

        @Override // com.amazon.mp3.store.html5.map.MAPCookiesCallback, com.amazon.identity.auth.device.api.Callback
        public void onSuccess(final Bundle successBundle) {
            Log.debug(ProfileService.TAG, "Successfully retrieved authorization from MAP");
            super.onSuccess(successBundle);
            String cookie = CookieManager.getInstance().getCookie(EndpointsProvider.get().getProfileAvatarServiceEndpoint());
            SocialProfileImageUploadFormQuery.ProfileImageUploadForm profileImageUploadForm = this.profileImageForm;
            if (profileImageUploadForm != null) {
                ProfileService.this.sendUploadRequest(cookie, this.imageUri, profileImageUploadForm, this.callback, this.imageRotation);
            } else {
                ProfileService.this.sendUploadRequest(cookie, this.imageUri, this.callback, this.imageRotation);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUploadRequest(final String cookie, final Uri imageUri, final SocialProfileImageUploadFormQuery.ProfileImageUploadForm profileImageForm, final CustomerProfileService.Callback callback, int imageRotation) {
        Log.debug(TAG, "Sending Firefly image upload request");
        OkHttpClient okHttpClientBuild = UnifiedOkHttpClient.newBuilder().eventListener(StabilityManager.INSTANCE.getBugsnagOkHttpPlugin()).build();
        HttpUrl httpUrl = HttpUrl.parse(profileImageForm.getUrl());
        try {
            RequestBody requestBodyGenerateFormData = generateFormData(imageUri, imageRotation);
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            for (SocialProfileImageUploadFormQuery.FormField formField : profileImageForm.getFormFields()) {
                builder.addFormDataPart(formField.getKey(), formField.getValue());
            }
            builder.addFormDataPart(profileImageForm.getFileFieldName(), profileImageForm.getFileFieldName(), requestBodyGenerateFormData);
            try {
                Response responseExecute = FirebasePerfOkHttpClient.execute(okHttpClientBuild.newCall(new Request.Builder().url(httpUrl).header("cookie", cookie).header(HttpHeader.CONTENT_TYPE, "multipart/form-data").post(builder.build()).build()));
                responseExecute.body().close();
                if (!responseExecute.isSuccessful()) {
                    callback.onError(generateUpdateException(DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT));
                    return;
                }
                MetricsLogger.sendEvent(FlexEvent.builder("customerProfile").withFlexStr1("profile.update.photo.success").build());
                Log.debug(TAG, "Avatar successfully uploaded");
                callback.onSuccess();
            } catch (IOException e) {
                Log.error(TAG, "Encountered IOException while uploading image", e.toString());
                callback.onError(generateUpdateException(WearableStatusCodes.UNKNOWN_LISTENER));
            }
        } catch (IOException e2) {
            Log.error(TAG, "Encountered IOException while reading image", e2.toString());
            callback.onError(generateUpdateException(WearableStatusCodes.DUPLICATE_LISTENER));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUploadRequest(final String cookie, final Uri imageUri, final CustomerProfileService.Callback callback, int imageRotation) {
        String str = TAG;
        Log.debug(str, "Sending Avatar Service image upload request");
        try {
            try {
                Response responseExecute = FirebasePerfOkHttpClient.execute(UnifiedOkHttpClient.newBuilder().eventListener(StabilityManager.INSTANCE.getBugsnagOkHttpPlugin()).build().newCall(new Request.Builder().url(HttpUrl.parse(EndpointsProvider.get().getProfileAvatarServiceEndpoint())).header("cookie", cookie).header("x-requested-with", "XMLHttpRequest").post(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("avatar", "avatar", generateFormData(imageUri, imageRotation)).build()).build()));
                responseExecute.body().close();
                if (!responseExecute.isSuccessful()) {
                    callback.onError(generateUpdateException(DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT));
                    return;
                }
                MetricsLogger.sendEvent(FlexEvent.builder("customerProfile").withFlexStr1("profile.update.photo.success").build());
                Log.debug(str, "Avatar successfully uploaded");
                callback.onSuccess();
            } catch (IOException e) {
                Log.error(TAG, "Encountered IOException while uploading image", e.toString());
                callback.onError(generateUpdateException(WearableStatusCodes.UNKNOWN_LISTENER));
            }
        } catch (IOException e2) {
            Log.error(TAG, "Encountered IOException while reading image", e2.toString());
            callback.onError(generateUpdateException(WearableStatusCodes.DUPLICATE_LISTENER));
        }
    }

    private RequestBody generateFormData(final Uri imageUri, int imageRotation) throws IOException {
        if ("content".equals(imageUri.getScheme())) {
            return RequestBody.create(MediaType.parse(this.context.getContentResolver().getType(imageUri)), retrieveFileData(imageUri, imageRotation));
        }
        return RequestBody.create(MediaType.parse(MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(imageUri.toString()).toLowerCase())), new File(imageUri.getPath()));
    }

    private byte[] retrieveFileData(final Uri imageUri, int imageRotation) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.context.getContentResolver(), imageUri);
        Matrix matrix = new Matrix();
        matrix.preRotate(imageRotation);
        Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true).compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CustomerProfileService.UpdateException generateUpdateException(final int errorCode) {
        return new CustomerProfileService.UpdateException(errorCode, false, this.nameUpdated, this.statusUpdated, this.shareListeningHistoryUpdated);
    }
}
