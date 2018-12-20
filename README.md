# RxIpc

a lib for IPC between apps(have same signature)

## Feature
use IPC with one line code

## Import Library
both apps need depends library
```
implementation 'com.javalive09.rxipc:rxipc:1.0.5'
```

## Usage
app A call app B test() method need 3 step as follow:

### 1. app B register Callee
```
    IPCHelper.registerCallee(iCallee, "order_test");
    private ICallee iCallee = new ICallee() {
        @Override
        public Bundle onCall(@NonNull String method, @Nullable String arg,
                             @Nullable Bundle extras) {
            Bundle bundle = new Bundle();
            bundle.putString("return", test());
            return bundle;
        }
    };
    private String test() {
        return "return ipc test() result"
    }

```

### 2. app A config permission in AndroidManifest.xml
```
    <uses-permission android:name="appBpackagename.permission.IPC"/>
```

### 3. app A call app B
```
    Observable<Bundle> result = IPCHelper.call(cxt, appBpackagename, "order_test", null, null);
```

## License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```