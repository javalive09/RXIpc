package com.javalive09.ipc;

import com.javalive09.ipclib.IPCHelper;
import com.javalive09.ipclib.IMethod;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IPCHelper.registerMethod(iMethod, "test");
        startService(new Intent(this, Ser.class));
    }

    private IMethod iMethod = new IMethod() {
        @Override
        public Bundle onCall(@NonNull String method, @Nullable String arg,
                             @Nullable Bundle extras) {
            SystemClock.sleep(5 * 1000);
            Bundle bundle = new Bundle();
            bundle.putString("ipc", "ipcStr !!!");
            return bundle;
        }
    };

    public void onClick(View view) {
        setText("...");
        Context cxt = getApplication();
        disposable = IPCHelper.call(cxt, getPackageName(), "test", null, null)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        new Consumer<Bundle>() {
                            @Override
                            public void accept(Bundle bundle) throws Exception {
                                String str = bundle.getString("ipc");
                                setText(str);
                            }
                        });
    }

    private void setText(String str) {
        TextView textView = findViewById(R.id.hello);
        textView.setText(str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IPCHelper.unregisterMethod(iMethod);
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
