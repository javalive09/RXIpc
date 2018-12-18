package com.javalive09.rxipc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by peter on 2018/12/17
 */
public class IPCHelper extends ContentProvider {

    private static final HashMap<String, IMethod> METHODS = new HashMap<>();

    public static Uri uri(String packageName) {
        String uriStr = "content://" + packageName + ".ipchelper";
        return Uri.parse(uriStr);
    }

    public static void registerMethod(IMethod method, String... orders) {
        try {
            for (String order : orders) {
                IMethod iMethod = METHODS.put(order, method);
                if (iMethod != null) {
                    throw new Exception("you have already register order :" + order + " ! please keep order atomic !!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unregisterMethod(IMethod method) {

        Set<Map.Entry<String, IMethod>> entrySet = new HashSet<>(METHODS.entrySet());
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, IMethod> entry : entrySet) {
            if (entry.getValue() == method) {
                keys.add(entry.getKey());
            }
        }
        for (String key : keys) {
            METHODS.remove(key);
        }
    }

    public static Observable<Bundle> call(final @NonNull Context context,
                                          final @NonNull String packageName,
                                          final @NonNull String method,
                                          final @Nullable String arg,
                                          final @Nullable Bundle extras) {
        return Observable.create(new ObservableOnSubscribe<Bundle>() {
            @Override
            public void subscribe(ObservableEmitter<Bundle> emitter) throws Exception {
                Context appContext = context.getApplicationContext();
                try {
                    Bundle bundle = appContext.getContentResolver().call(uri(packageName), method, arg, extras);
                    emitter.onNext(bundle == null ? new Bundle() : bundle);
                } catch (Exception e) {
                    emitter.onError(e.getCause());
                } finally {
                    emitter.onComplete();
                }
            }
        });
    }

    @Nullable
    @Override
    public Bundle call(@NonNull final String method, @Nullable final String arg, @Nullable final Bundle extras) {
        final IMethod iMethod = METHODS.get(method);
        if (iMethod != null) {
            return iMethod.onCall(method, arg, extras);
        }
        return super.call(method, arg, extras);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }
}
